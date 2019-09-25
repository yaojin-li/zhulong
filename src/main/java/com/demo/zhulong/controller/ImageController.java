package com.demo.zhulong.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.zhulong.common.FileRelated;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.zhulong.common.FileRelated;
import com.demo.zhulong.common.HDFSConstants;
import com.demo.zhulong.dao.ImageMapper;
import com.demo.zhulong.dao.ImageMapperCustom;
import com.demo.zhulong.entity.Image;
import com.demo.zhulong.entity.ImageCustom;
import com.demo.zhulong.service.ImageService;

/**
 * @Description: 图像的controller
 * @Author: John
 * @Tags:
 * @Date: 2017年8月4日 下午4:45:17
 */

@Controller
@RequestMapping("/imageController")
public class ImageController {

    // 定义私有的图像service
    @Autowired
    private ImageService imageService;


    //存储数据的jsonObject
    private static JSONObject jsonObject = new JSONObject();

    @Autowired
    private static final Logger LOGGER = Logger.getLogger(ImageController.class);

    /**
     * @Description: 查询图像列表
     * 前端直接访问：http://localhost:8080/maven-web-demo/imageController/queryImage
     * @Author: John
     * @Tags: @return
     * @Tags: @throws Exception
     * @Date: 2017年8月6日 下午10:16:36
     * @return:ModelAndView
     */
    @RequestMapping(value = "queryImage", produces = "application/json;charset=utf-8")
    @ResponseBody
    public ModelAndView queryImage() throws Exception {

        System.out.println("imgController::::");

        // 调用service查找 数据库，查询商品列表
        List<ImageCustom> imageList = imageService.findImageList(null);

        // 返回ModelAndView
        ModelAndView modelAndView = new ModelAndView();

        // 相对于request中的setAttribute，在jsp页面中通过imageList取数据
        modelAndView.addObject("imageList", imageList);

        System.out.println("+++");
        LOGGER.info(JSON.toJSONStringWithDateFormat(modelAndView, "yyyy-MM-dd HH:mm:ss"));

        // 指定视图
        modelAndView.setViewName("example/imgFileList");

        return modelAndView;

    }


    /**
     * @Description: 上传图像
     * @Author: Administrator
     * @Tags: @param request
     * @Tags: @param response
     * @Tags: @return
     * @Tags: @throws Exception
     * @Date: 2017年8月8日 下午9:15:48
     * @return: ModelAndView
     */
    @RequestMapping("/insertImage")
    public ModelAndView insertImage(MultipartFile fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("insertImage::::");

        //设置request编码
        request.setCharacterEncoding("utf-8");

        //存储数据的jsonObject
        JSONObject jsonObject = new JSONObject();

        Object[] res = new Object[2];

        /**-- 1.获取上传信息 --**/

        res = FileRelated.getUplodInfo(fileName, request);

        //获得含有UUID的文件名
        String saveFileName = res[1].toString();

        //获得上传信息的jsonobject对象
        jsonObject = (JSONObject) res[0];

        try {


            /**-- 2.文件缓存 --**/

            String[] result = FileRelated.imgFileCache(fileName, saveFileName);

            //获得上传文件的缓存目录
            String realSavePath = result[0];

            if ("true".equals(result[1])) {
                System.out.println("上传缓存成功");
            } else {
                System.out.println("上传缓存失败");
            }


            /**-- 3.文件上传到HDFS --**/
            boolean flag_uploadToHDFS = FileRelated.uploadToHDFS(realSavePath, saveFileName);

            if (flag_uploadToHDFS) {
                System.out.println("上传HDFS成功。");
            } else {
                System.out.println("上传HDFS失败。");
            }


            /**-- 4.清除本地缓存文件 --**/
            boolean flag_delAllFile = FileRelated.DeleteFolder(realSavePath);

            if (flag_delAllFile) {
                System.out.println("清除本地缓存文件成功");
            } else {
                System.out.println("清除本地缓存文件失败");
            }


        } catch (IOException ioException) {
            ioException.printStackTrace();
            request.setAttribute("message", "上传失败");
            request.getRequestDispatcher("/WEB-INF/jsp/example/message.jsp").forward(request, response);

        } catch (MaxUploadSizeExceededException mException) {
            mException.printStackTrace();
            request.setAttribute("message", "文件过大");
            request.getRequestDispatcher("/WEB-INF/jsp/example/message.jsp").forward(request, response);

        }


        ModelAndView modelAndView = new ModelAndView();
        LOGGER.info(JSON.toJSONStringWithDateFormat(jsonObject, "yyyy-MM-dd"));

        System.out.println("+++++++++++");

        //json对象转成imagecustom对象
        ImageCustom imageCustom = JSON.toJavaObject(jsonObject, ImageCustom.class);


        LOGGER.info(JSON.toJSONStringWithDateFormat(imageCustom, "yyyy-MM-dd"));

        //插入数据
        imageService.insertImage(imageCustom);

        // 错误点：JSON.toJSONStringWithDateFormat参数为对象，不能为接口
        // LOGGER.info(JSON.toJSONStringWithDateFormat(imageService, "yyyy-MM-dd"));


        modelAndView.addObject("message", "上传成功");

        modelAndView.setViewName("example/message");

        return modelAndView;


    }


    /**
     * @Description: 下载图像
     * @Author: Administrator
     * @Tags: @param request
     * @Tags: @param response
     * @Tags: @throws Exception
     * @Date: 2017年8月10日 下午2:49:18
     * @return: void
     */
    @RequestMapping("/downloadImage")
    public void downloadImage(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("downloadImage::::");

        request.setCharacterEncoding("utf-8");

        String HDFSAddress = HDFSConstants.HDFSAddress;

        System.out.println("HDFS中的路径为：" + HDFSAddress);

        FileSystem hdfs = null;

        String img_UUID = request.getParameter("img_UUID");
        String title = request.getParameter("title");
        System.out.println(img_UUID);


        String fileName = img_UUID + "_" + title;

        System.out.println(fileName);


        try {

            // 创建配置器
            Configuration config = new Configuration();

            // 创建HDFS filesystem实例对象
            hdfs = FileSystem.get(new URI(HDFSConstants.HDFSIPPort), config, "lixiangjie");

            System.out.println("新建filesystem为：" + hdfs);

            fileName = HDFSConstants.HDFSPath + fileName;

            // 得到要下载的文件名
            System.out.println("要下载文件的完整路径为（路径+文件名）：" + fileName);

            // 获得文件名
            String realFileName = fileName.substring(fileName.indexOf("_") + 1);
            System.out.println("文件名" + realFileName);

            // 以文件名获得文件名的路径
            Path path = new Path(fileName);

            System.out.println("文件名的路径:" + path);


            // 如果文件不存在
            if (!hdfs.exists(path)) {
                request.setAttribute("message", "您要下载的资源已被删除！！");
                request.getRequestDispatcher("/WEB-INF/jsp/example/message.jsp").forward(request, response);
                return;
            } else {

                System.out.println("文件存在！");

                // 文件输入流
                FSDataInputStream inputStream = hdfs.open(path);

                // 设置下载文件相关信息---header中只支持ASCII，所以传输的文件名必须是ASCII，当文件名为中文时，必须要将该中文转换成ASCII，转换格式，防止中文名丢失。
                response.setContentType("application/octet-stream");
                response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(realFileName, "utf-8"));

                // 文件输出流
                BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());

                // 缓存
                byte[] buffer = new byte[2048];

                int byteRead = 0;

                while ((byteRead = (inputStream.read(buffer, 0, buffer.length))) != -1) {

                    outputStream.write(buffer, 0, byteRead);

                }

                // 刷新缓冲输出流
                outputStream.flush();

                inputStream.close();
                outputStream.close();

            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }


    }


    /**
     * @Description: 删除图像
     * @Author: Administrator
     * @Tags: @param request
     * @Tags: @param response
     * @Tags: @throws Exception
     * @Date: 2017年8月11日 下午1:39:05
     * @return: void
     */
    @RequestMapping("/deleteImage")
    public ModelAndView deleteImage(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        String message = "";

        String getID = request.getParameter("id");
        String img_UUID = request.getParameter("img_UUID");
        String title = request.getParameter("title");

        int id = Integer.parseInt(getID);
        System.out.println("id=" + id);

        String fileName = new String(title.getBytes("utf-8"), "utf-8");

        System.out.println("--------------");
        System.out.println(fileName);

        //数据库中删除
        imageService.deleteImage(id);


        //HDFS中删除
        try {

            String saveFileName = HDFSConstants.HDFSAddress + img_UUID + "_" + fileName;

            System.out.println("--------------+");
            System.out.println("保存文件名：" + saveFileName);

            Configuration configuration = new Configuration();

            FileSystem fileSystem = FileSystem.get(URI.create(saveFileName), configuration);

            Path saveFilePath = new Path(saveFileName);

            if (!fileSystem.exists(saveFilePath)) {
                message = "文件路径不存在";
                request.setAttribute("message", message);
                modelAndView.addObject("message", message);
                modelAndView.setViewName("example/message");
                return modelAndView;
            }

            //删除
            fileSystem.delete(saveFilePath, true);

            message = "文件删除成功";

        } catch (Exception e) {
            e.printStackTrace();
        }

        modelAndView.addObject("message", message);
        modelAndView.setViewName("example/message");
        return modelAndView;

    }


    /**
     * @Description: 更改图像信息
     * @Author: Administrator
     * @Tags: @param file_changed
     * @Tags: @param request
     * @Tags: @param response
     * @Tags: @return
     * @Tags: @throws Exception
     * @Date: 2017年8月29日 上午10:21:55
     * @return: ModelAndView
     */
//    @ResponseBody
//    @RequestMapping(value = "changeImageInfo", method = {RequestMethod.GET, RequestMethod.POST})
//    public ModelAndView changeImageInfo(String file_changed, HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//
//        System.out.println("changeImageInfo::");
//
//        String changeData = request.getParameter("file_changed");
//
//        String message = "";
//
//        try {
//
//            // json字符串转JSONArray对象
//            net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(changeData);
//
//            String[] aStrings = new String[jsonArray.size()];
//
//            for (int i = 0; i < jsonArray.size(); i++) {
//                aStrings[i] = jsonArray.get(i).toString();
//            }
//
//            for (int i = 0; i < aStrings.length; i++) {
//                System.out.println(i + aStrings[i]);
//            }
//
//            JSONObject jsonObject = new JSONObject();
//
//
//            jsonObject.put("id", aStrings[0]);
//            jsonObject.put("new_title", aStrings[1]);
//            jsonObject.put("description", aStrings[2]);
//            jsonObject.put("remark", aStrings[3]);
//            jsonObject.put("modify_time", new Timestamp(System.currentTimeMillis()));
//
//            ImageCustom imageCustom = JSON.toJavaObject(jsonObject, ImageCustom.class);
//
//            LOGGER.info(JSON.toJSONStringWithDateFormat(imageCustom, "yyyy-MM-dd HH:mm:ss"));
//
//            //更新数据
//            imageService.updateImage(imageCustom);
//
//            message = "修改成功";
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            request.setAttribute("message", "修改失败");
//            request.getRequestDispatcher("/WEB-INF/jsp/example/message.jsp").forward(request, response);
//        }
//
//        ModelAndView modelAndView = new ModelAndView();
//
//        modelAndView.addObject("message", message);
//        modelAndView.setViewName("example/imgFileList");
//
//        return modelAndView;
//
//    }
//

}
	
	
	
	
	





