package com.iknown.ylf.iknown.model;

import cn.bmob.v3.BmobObject;
import android.R.integer;

public class Video extends BmobObject{
	
	private int id;
	private  String title;
	private String imagePic;
	private String videoUr;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImagePic() {
		return imagePic;
	}
	public void setImagePic(String imagePic) {
		this.imagePic = imagePic;
	}
	public String getVideoUr() {
		return videoUr;
	}
	public void setVideoUr(String videoUr) {
		this.videoUr = videoUr;
	}
}
