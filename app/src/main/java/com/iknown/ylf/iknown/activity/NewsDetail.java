package com.iknown.ylf.iknown.activity;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.iknown.ylf.iknown.R;

public class NewsDetail extends BaseActivity implements OnClickListener {
	private WebView wv;
	private String url;
	private ProgressBar pb;
	private Button iv_finish;
	private ImageView iv_logo;
	private TextView tv_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_news_detail);
		initView();
	}


	private void initView(){
		int index=getIntent().getIntExtra("index",1);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		wv = (WebView) findViewById(R.id.wv);
		pb=(ProgressBar) findViewById(R.id.pb);
		iv_logo= (ImageView) findViewById(R.id.iv_logo);
		tv_title=(TextView)findViewById(R.id.tv_title);
		tv_title.setVisibility(View.VISIBLE);
		if(index==1){
			tv_title.setText("最新资讯");
		}else {
			tv_title.setText("微信精选");
		}
		iv_logo.setVisibility(View.GONE);
		final WebSettings setting = wv.getSettings();
		setting.setJavaScriptEnabled(true);
		setting.setRenderPriority(WebSettings.RenderPriority.HIGH);
		setting.setCacheMode(WebSettings.LOAD_NO_CACHE);
		setting.setDomStorageEnabled(true);
		setting.setDatabaseEnabled(true);
		setting.setAppCacheEnabled(true);
		setting.setBlockNetworkImage(true);
		//wv.setWebChromeClient(new WebChromeClient());
		wv.setWebViewClient(new WebViewClient(){

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				pb.setVisibility(View.GONE);
				wv.setVisibility(View.VISIBLE);
				setting.setBlockNetworkImage(false);
			}

		});
		pb.setVisibility(View.VISIBLE);
		wv.setVisibility(View.GONE);
		iv_finish = (Button) findViewById(R.id.iv_finish);
		iv_finish.setVisibility(View.VISIBLE);
		iv_finish.setOnClickListener(this);
		url = getIntent().getStringExtra("url");
		wv.loadUrl(url);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_finish:
			this.finish();
			break;
		default:
			break;
		}

	}

}
