package com.demo.zhulong.service;

import com.alibaba.fastjson.JSONObject;
import com.demo.zhulong.base.beans.Images;
import com.demo.zhulong.base.dao.ImagesMapper;
import com.demo.zhulong.common.CommonResponse;
import com.demo.zhulong.common.enums.ResultCode;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: ImageService
 * --------------------------------------
 * @ClassName: ImageService.java
 * @Date: 2019/9/30 20:19
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Service
public class ImageService {
    // TODO LXJ 操作数据库失败添加事务

    public static final Logger logger = LoggerFactory.getLogger(ImageService.class);

    @Autowired
    @Lazy
    private ImagesMapper imagesMapper;

    public List<Images> selectAll() throws Exception {
        return imagesMapper.selectAll();
    }


    /**
     * @Description: 插入图像
     */
//	public int insertImage(ImageCustom imageCustom) throws Exception;


    /**
     * @Description: 数据库删除图像
     */
    public int deleteImageByUuid(String uuid) throws Exception {
        Images images = new Images();
        images.setUuid(uuid);
        return imagesMapper.delete(images);
    }

    /**
     * @Description: 从 HDFS 中删除
     */
    public CommonResponse deleteImageFromHdfs(String uuid) throws Exception {
        logger.info("delete file from HDFS begin...");
        Map<String, Object> map = new HashMap<>();
        CommonResponse response = new CommonResponse();

        String saveFileName = HdfsConfig.getMasterAddress() + uuid;
        logger.info(String.format("HDFS 中待删除文件[%s]", saveFileName));
        try {
            Configuration configuration = new Configuration();
            FileSystem fileSystem = FileSystem.get(URI.create(saveFileName), configuration);
            Path saveFilePath = new Path(saveFileName);
            if (!fileSystem.exists(saveFilePath)) {
                logger.error(String.format("HDFS 中文件路径不存在！saveFileName[%s]", saveFileName));
                response.setMsg("HDFS 中文件路径不存在");
                response.setResult(ResultCode.FAIL.getCode());
                return response;
            }
            // 删除
            boolean delRes = fileSystem.delete(saveFilePath, true);
            if (delRes){
                response.setMsg("文件删除成功");
                response.setResult(ResultCode.FAIL.getCode());
            }else {
                logger.error(String.format("文件删除失败！saveFileName[%s]", saveFileName));
                response.setMsg("文件删除失败");
                response.setResult(ResultCode.FAIL.getCode());
            }
        } catch (Exception e) {
            logger.error(String.format("HDFS 中删除文件异常！saveFileName[%s]", saveFileName), e);
            response.setMsg("文件删除异常");
            response.setResult(ResultCode.EXCEPTION.getCode());
        }
        return response;
    }



    /**
     * @Description: 更新图像信息（仅限服务器修改，暂时不支持 HDFS 中修改）
     */
    public int updateImage(String imgModInfo) throws Exception {
        JSONObject imgModJson = JSONObject.parseObject(imgModInfo);
        String uuid = (String) imgModJson.get("uuid");
        String title = (String) imgModJson.get("title");
        String uploader = (String) imgModJson.get("uploader");

        Images images = new Images();
        images.setUuid(uuid);
        images.setTitle(title);
        images.setUploader(uploader);
        images.setUpdateTime(new Date());

        return imagesMapper.updateImgByUuid(images);
    }


}




















