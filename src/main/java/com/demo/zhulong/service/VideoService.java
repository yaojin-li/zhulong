package com.demo.zhulong.service;

import com.alibaba.fastjson.JSONObject;
import com.demo.zhulong.base.beans.Videos;
import com.demo.zhulong.base.dao.VideosMapper;
import com.demo.zhulong.config.HdfsConfig;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: VideoService
 * --------------------------------------
 * @ClassName: VideoService.java
 * @Date: 2019/9/30 20:19
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Service
public class VideoService {
    // TODO LXJ 操作数据库失败添加事务

    public static final Logger logger = LoggerFactory.getLogger(VideoService.class);

    @Autowired
    @Lazy
    private VideosMapper videosMapper;

    public List<Videos> selectAll() throws Exception {
        return videosMapper.selectAll();
    }


    /**
     * @Description: 插入视频
     */
    public int insertVideo(Map<String, Object> videoInfo) throws Exception {
        Videos video = new Videos();
        video.setTitle((String) videoInfo.get("title"));
        video.setType((String) videoInfo.get("type"));
        video.setSize((Long) videoInfo.get("size"));
        video.setHdfsPosition((String) videoInfo.get("hdfsPosition"));
        video.setServerPosition((String) videoInfo.get("serverPosition"));
        video.setUuid((String) videoInfo.get("uuid"));
        video.setUploader((String) videoInfo.get("uploader"));
        video.setRemark((String) videoInfo.get("remark"));
        video.setUploadTitle((String) videoInfo.get("uploadTitle"));
        return videosMapper.insertSelective(video);
    }


    /**
     * @Description: 数据库删除图像
     */
    public int deleteVideoByUploadTitle(String uploadTitle) throws Exception {
        return videosMapper.deleteVideoByUploadTitle(uploadTitle);
    }


    /**
     * @Description: 从 HDFS 中删除
     */
    public Boolean deleteVideoFromHdfs(String uploadTitle) throws Exception {
        String filePath = HdfsConfig.getMasterAddress() + uploadTitle;
        logger.info(String.format("HDFS 中待删除文件[%s]", filePath));
        try {
            Configuration configuration = new Configuration();
            FileSystem fileSystem = FileSystem.get(URI.create(filePath), configuration, HdfsConfig.getHdfsUser());
            Path saveFilePath = new Path(filePath);
            if (!fileSystem.exists(saveFilePath)) {
                logger.error(String.format("HDFS 中文件路径不存在！filePath:[%s]", filePath));
                return false;
            }
            // 删除
            return fileSystem.delete(saveFilePath, true);
        } catch (Exception e) {
            logger.error(String.format("HDFS 中删除文件异常！uploadTitle:[%s]", uploadTitle), e);
        }
        return false;
    }


    /**
     * @Description: 更新图像信息（仅限服务器修改，暂时不支持 HDFS 中修改）
     */
    public int updateVideo(JSONObject videoModInfo) throws Exception {
        String uuid = (String) videoModInfo.get("uuid");
        String title = (String) videoModInfo.get("title");
        String uploader = (String) videoModInfo.get("uploader");

        Videos videos = new Videos();
        videos.setUuid(uuid);
        videos.setTitle(title);
        videos.setUploader(uploader);
        videos.setUpdateTime(new Date());
        return videosMapper.updateVideoByUuid(videos);
    }


}




















