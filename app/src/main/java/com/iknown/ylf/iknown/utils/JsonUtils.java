package com.iknown.ylf.iknown.utils;
import com.iknown.ylf.iknown.model.Article;
import com.iknown.ylf.iknown.model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

	public static List<News> getNewsList(String result) {
		List<News> newsArray = new ArrayList<News>();
		try {
			JSONObject object = new JSONObject(result);
			JSONObject res = object.getJSONObject("result");
			JSONArray array = res.getJSONArray("data");
			for (int i = 0; i < array.length(); i++) {
				JSONObject n = array.getJSONObject(i);
				String title = n.optString("title","");
				String thumbnail_pic_s = n.optString("thumbnail_pic_s","");
				String date = n.optString("date","");
				String url = n.optString("url","");
				News news = new News();
				news.setTitle(title);
				news.setUrl(url);
				news.setThumbnail_pic_s(thumbnail_pic_s);
				news.setDate(date);
				if(title.length()>0){
					newsArray.add(news);
				}
			}
			return newsArray;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static List<Article> getArticleList(String result, List<Article> articles) {
		try {
			JSONObject object = new JSONObject(result);
			String reason = object.getString("reason");
			if(reason.equals("success")){
				JSONObject resu=object.getJSONObject("result");
				JSONArray array = resu.getJSONArray("list");
				for (int i = 0; i < array.length(); i++) {
					JSONObject n = array.getJSONObject(i);
					String title = n.getString("title");
					String firstImg = n.getString("firstImg");
					String id = n.getString("id");
					String url = n.getString("url");
					String source=n.getString("source");
					Article article = new Article();
					article.setTitle(title);
					article.setUrl(url);
					article.setId(id);
					article.setFirstImage(firstImg);
					article.setSource(source);
					articles.add(article);
				}
				return articles ;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;

	}

}
