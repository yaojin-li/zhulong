package com.demo.zhulong.entity;

/**
 * @Description: 视频包装对象：包括原始的视频类对象+视频类的扩展类对象
 * @Author: John
 * @Tags: 
 * @Date: 2017年8月4日 下午4:59:21
 */
public class VideoQueryVo {

	//图像信息
	private Video video;
	
	//为了提高系统的可扩展性，对原始生成的po进行扩展
	private VideoCustom videoCustom;

	//定义video类的get和set方法
	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	//定义videoCustom类的get和set方法
	public VideoCustom getVideoCustom() {
		return videoCustom;
	}

	public void setVideoCustom(VideoCustom videoCustom) {
		this.videoCustom = videoCustom;
	}



	
	
	
}



