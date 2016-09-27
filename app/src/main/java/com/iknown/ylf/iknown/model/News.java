package com.iknown.ylf.iknown.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/7.
 */
public class News implements Serializable {
	private static final long serialVersionUID = -2530777653373065221L;
	/**
     * title : 不雅照过去八年了，为何至今没人敢娶她？
     * date : 2016-09-07 09:02
     * author_name : 北京时间
     * thumbnail_pic_s : http://00.imgmini.eastday.com/mobile/20160907/20160907090201_c97dbbc21b24d13829524cce24fb134c_1_mwpm_03200403.jpeg
     * thumbnail_pic_s02 : http://00.imgmini.eastday.com/mobile/20160907/20160907090201_c97dbbc21b24d13829524cce24fb134c_1_mwpl_05500201.jpeg
     * thumbnail_pic_s03 : http://00.imgmini.eastday.com/mobile/20160907/20160907090201_c97dbbc21b24d13829524cce24fb134c_1_mwpl_05500201.jpeg
     * url : http://mini.eastday.com/mobile/160907090201192.html?qid=juheshuju
     * uniquekey : 160907090201192
     * type : 头条
     * realtype : 娱乐
     */

    private String title;
    private String date;
    private String author_name;
    private String thumbnail_pic_s;
    private String thumbnail_pic_s02;
    private String thumbnail_pic_s03;
    private String url;
    private String uniquekey;
    private String type;
    private String realtype;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }

    public String getThumbnail_pic_s02() {
        return thumbnail_pic_s02;
    }

    public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
        this.thumbnail_pic_s02 = thumbnail_pic_s02;
    }

    public String getThumbnail_pic_s03() {
        return thumbnail_pic_s03;
    }

    public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
        this.thumbnail_pic_s03 = thumbnail_pic_s03;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUniquekey() {
        return uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRealtype() {
        return realtype;
    }

    public void setRealtype(String realtype) {
        this.realtype = realtype;
    }
}