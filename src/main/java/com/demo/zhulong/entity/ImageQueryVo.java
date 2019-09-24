package com.demo.zhulong.entity;

/**
 * @Description: 图像包装对象：包括原始的图像类对象+图像类的扩展类对象
 * @Author: John
 * @Tags: 
 * @Date: 2017年8月4日 下午4:59:21
 */
public class ImageQueryVo {

	//图像信息
	private Image image;
	
	//为了提高系统的可扩展性，对原始生成的po进行扩展
	private ImageCustom imageCustom;

	//定义image类的get和set方法
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	//定义imageCustom类的get和set方法
	public ImageCustom getImageCustom() {
		return imageCustom;
	}

	public void setImageCustom(ImageCustom imageCustom) {
		this.imageCustom = imageCustom;
	}
	
	
	
	
	
}



