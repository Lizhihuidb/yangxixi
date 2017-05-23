package com.lyp.yangxixi.photo.utils;

import java.io.IOException;
import java.io.Serializable;

import android.graphics.Bitmap;

/**
 *
 * @author Administrator
 * 图片实体类
 */
public class ImageItem implements Serializable {
	public String imageId;//图片id
	public String thumbnailPath;//图片缩略图
	public String imagePath;//图片路径
	private Bitmap bitmap;//图片
	public boolean isSelected = false;

	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getThumbnailPath() {
		return thumbnailPath;
	}
	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	public Bitmap getBitmap() {
		if(bitmap == null){
			try {
				bitmap = Bimp.revitionImageSize(imagePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}



}
