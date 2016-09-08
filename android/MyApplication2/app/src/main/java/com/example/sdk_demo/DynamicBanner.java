package com.example.sdk_demo;

import com.slion.sdk.AdView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

public class DynamicBanner extends Activity {

	AdView mAdView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.dynamic_banner);

		BannerMonitor monitor = new BannerMonitor(
				"1591F59C-403C-436C-A7FB-DA6EDBB1EE8E", this);
		mAdView = new AdView("1591F59C-403C-436C-A7FB-DA6EDBB1EE8E", this,
				"CE8E808BAEE727377BD0285C95917F8A", true, true, monitor);
		FrameLayout root = (FrameLayout) findViewById(R.id.banner_container);
		
		root.addView(mAdView);

	}

	public class BannerMonitor implements com.slion.sdk.BannerListener {
		private String loactionID;
		private Context mContext;

		public BannerMonitor(String locationID, Context context) {
			this.loactionID = locationID;
			this.mContext = context;
		}

		@Override
		public void bannerClicked() {
			Toast.makeText(getApplicationContext(), "banner点击了",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void adpageClosed() {
			Toast.makeText(getApplicationContext(), "来自banner的广告详情页关闭了",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void bannerClosed() {
			Toast.makeText(getApplicationContext(), "banner关闭了",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void bannerShown(String json) {
			Toast.makeText(getApplicationContext(), "banner展示了",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void noAdFound() {
			Toast.makeText(getApplicationContext(), "banner无广告",
					Toast.LENGTH_SHORT).show();
		}
	}
}
