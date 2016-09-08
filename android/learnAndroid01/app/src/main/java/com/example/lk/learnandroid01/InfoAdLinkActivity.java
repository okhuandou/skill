package com.example.lk.learnandroid01;



import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.lk.learnandroid01.R;

public class InfoAdLinkActivity extends Activity {
	private WebView mWb;
	private RelativeLayout mRLayout;
	private ImageButton mImgBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		initView();
	}

	private void initView() {
		setContentView(R.layout.ad_link_info);
		try {
			Intent intent = getIntent();
			String stringUrl = intent.getStringExtra("link");
			mWb = (WebView) findViewById(R.id.wb_link_info);
//			mWb.loadUrl(stringUrl);
			mWb.loadUrl("http://www.yin3600.net/qqgs.jspx");
			mWb.setWebChromeClient(new WebChromeClient(){
				
			});
			mWb.setWebViewClient(new WebViewClient() {
				
				//页面加载结束
				@Override
				public void onPageFinished(WebView view, String url) {
					super.onPageFinished(view, url);
					mWb.setVisibility(View.VISIBLE);
				}
				
				
				//不叫其跳转，强制在本页面显示
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					if(!TextUtils.isEmpty(url)) {
						view.loadUrl(url);
						//隐士意图
						final Intent intent = new Intent(Intent.ACTION_VIEW, Uri
		                        .parse(url));
		                if (isDeepLink(url) && deviceCanHandleIntent(getApplicationContext(), intent)) {
		                	startActivity(intent);
		                	finish();
		                }
					}
				
					return true;
				}
			});
			
			mWb.setDownloadListener(new DownloadListener() {
				
				@Override
				public void onDownloadStart(String url, String userAgent,
						String contentDisposition, String mimetype,
						long contentLength) {
					
					if(!TextUtils.isEmpty(url)) {
//						Uri uri = Uri.parse(url);
//						Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//						startActivity(intent);
//						downloadFile.downloadFile(getApplicationContext(), url, FileDownLoadUtils.DOWNLOAD_FOR_ONESELF);
						FileDownLoadUtils downloadFile = new FileDownLoadUtils(InfoAdLinkActivity.this);
						downloadFile.downloadFile(getApplicationContext(), url, FileDownLoadUtils.DOWNLOAD_BY_MANAGER);
					}
					InfoAdLinkActivity.this.finish();
					InfoAdLinkActivity.this.onDestroy();

				}
			});

		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
	public static boolean isDeepLink(final String url) {
		
		return !isHttpUrl(url);
	}

	public static boolean deviceCanHandleIntent(final Context context,
			final Intent intent) {
		try {
			final PackageManager packageManager = context.getPackageManager();
			final List<ResolveInfo> activities = packageManager
					.queryIntentActivities(intent, 0);
			return !activities.isEmpty();
		} catch (NullPointerException e) {
			return false;
		}
	}

	public static boolean isHttpUrl(final String url) {
		if (url == null) {
			return false;
		}

		final String scheme = Uri.parse(url).getScheme();
		return ("HTTP".equals(scheme) || "HTTPS".equals(scheme));
	}
}
