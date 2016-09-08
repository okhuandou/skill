package com.example.sdk_demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.slion.sdk.InfoAd;
import com.slion.sdk.InfoCallBack;
import com.slion.sdk.InfoAd.InfoItem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivtiy extends Activity{
	private ListView mListInfo;
	private List<Object> mList;
	private Map<Integer, InfoItem> mMapForReport;
	// 控制分页的个数
	private int mCount;
	private MyAdapter mAdapter;
	private String mLink;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		
	}

	private void initView() {
		setContentView(R.layout.activity_info);
		//map集合用于控制点击上报和展示上报（开发者必须要解决上报问题）
		mListInfo = (ListView)findViewById(R.id.list_info);
		mMapForReport = new HashMap<Integer, InfoItem>();
		// 创建一个list
		mList = new ArrayList<Object>();
		List<Object> listData = loadData();
		requestInfoAd(listData);
		// 给listView设置点击监听
		mListInfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 点击监听，判断是否是否是javaBean
				if (mList != null) {
					if (mList.get(position) instanceof InfoItem) {
						final InfoItem responseBean = (InfoItem) mList
								.get(position);
						// 从map集合中获取responseBean
						InfoItem positionAdInfo = (InfoItem) mMapForReport
								.get(position);
						if (!positionAdInfo.isClicked) {
							// 发送展示上报
							positionAdInfo.reportClick();
							positionAdInfo.isClicked = true;
						}
//						// 点击后跳转到落地页
						mLink = responseBean.ads.get(0).link;
						if (!TextUtils.isEmpty(mLink)) {
							Intent infoIntent = new Intent(InfoActivtiy.this,
									InfoAdLinkActivity.class);
							infoIntent.putExtra("link", mLink);
							startActivity(infoIntent);
						}

					}
				}

			}
		});
		// 给listView设置监听
		mListInfo.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				switch (scrollState) {
				case OnScrollListener.SCROLL_STATE_IDLE:

					if (view.getLastVisiblePosition() == view.getCount() - 1) {
						if (mCount < 5) {
							// 创建基本数据
							List<Object> listData = loadData();
							// 准备信息流
							requestInfoAd(listData);
							return;
						}
						// 没有数据显示
						Toast.makeText(getApplicationContext(), "没有数据显示",
								Toast.LENGTH_SHORT).show();
					}
					break;
				case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
					break;
				case OnScrollListener.SCROLL_STATE_FLING:
					break;
				}

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

			}
		});
	}

	private void requestInfoAd(final List<Object> list) {
		 //信息流数据
        InfoAd infoAd = new InfoAd(getApplicationContext());
        infoAd.loadAd(getApplicationContext(), "B1B6878E28444A55A6B0241E906BBF60", "9EA0BFF825464FAC94213903EE2EB3F4", new InfoCallBack() {
			
			@Override
			public void success(final InfoItem infoItem) {
				System.out.println("成功");
				// 获取数据库的信息
				// 两种情况1responseBean就没有值，2有值无广告
				if ((infoItem != null) && (infoItem.code == 0)) {
					insertDataAndInfo(list, infoItem);
				}else {
					insertDataAndInfo(list, null);
				}
			}
			
			@Override
			public void fail() {
				insertDataAndInfo(list, null);
			}
		});
		
	}

	protected void insertDataAndInfo(List<Object> listData, InfoItem infoItem) {

		
			// 首先我要判断是否有正常数据，没有正常数据都不用再做了
			if (listData.size() > 0) {
				if ((infoItem != null) && (infoItem.code == 0)) {
					// 有广告就把广告放进去
					int middleSize = listData.size() / 2;
					listData.add(middleSize, infoItem);
					mList.addAll(listData);
					if (mAdapter == null) {
						mAdapter = new MyAdapter();
						mListInfo.setAdapter(mAdapter);
					}
					mAdapter.notifyDataSetChanged();
					mCount++;
					return;
				}
				// 如果没有广告就只方数据
				mList.addAll(listData);
				if (mAdapter == null) {
					mAdapter = new MyAdapter();
					mListInfo.setAdapter(mAdapter);
				}
				mAdapter.notifyDataSetChanged();
				mCount++;

			}
	}

	private List<Object> loadData() {
		List<Object> listData = new ArrayList<Object>();
		try {
			for (int i = 0; i < 20; i++) {
				listData.add("资源数据" + i);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listData;	
	}
	
	public class MyAdapter extends BaseAdapter {

		private AdHolder mAdHolder;

		@Override
		public int getCount() {
			System.out.println("mList.size" + mList.size());
			return mList.size();
		}

		@Override
		public Object getItem(int position) {

			return null;
		}

		@Override
		public long getItemId(int position) {

			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			try {
				if (convertView == null) {
					convertView = View.inflate(getApplicationContext(),
							R.layout.info_item, null);
					mAdHolder = new AdHolder();
					mAdHolder.tvTitle = (TextView) convertView
							.findViewById(R.id.title_tv);
					mAdHolder.tvDesc = (TextView) convertView
							.findViewById(R.id.desc_tv);
					mAdHolder.imgIcon = (ImageView) convertView
							.findViewById(R.id.ad_icon);
					convertView.setTag(mAdHolder);
				} else {
					mAdHolder = (AdHolder) convertView.getTag();
				}
				if (mList.get(position) instanceof InfoItem) {
					// 创建信息流对象
					if (!mMapForReport.containsKey(position)) {
						// 这里是控制点击和展示上报的
						mMapForReport.put(position, (InfoItem) mList.get(position));
					}
					final InfoItem responseBean = (InfoItem) mList
							.get(position);
					mAdHolder.tvTitle.setVisibility(View.VISIBLE);
					mAdHolder.tvDesc.setVisibility(View.VISIBLE);
					mAdHolder.imgIcon.setVisibility(View.VISIBLE);

					if (responseBean.ads.get(0).title != null) {
						mAdHolder.tvTitle.setText(responseBean.ads.get(0).title);
					}

					if (responseBean.ads.get(0).desc != null) {
						mAdHolder.tvDesc.setText(responseBean.ads.get(0).desc);
					}
					
					SendRequest sendHttpRequest = new SendRequest();
					sendHttpRequest.sendBitmap(getApplicationContext(), responseBean.ads.get(0).src, new RequestCallBack() {
						
						@Override
						public void successLoadImg(Bitmap bitmapImg) {
							mAdHolder.imgIcon.setImageBitmap(bitmapImg);
						}
						
						@Override
						public void failed() {
							Toast.makeText(getApplicationContext(), "无图片", Toast.LENGTH_SHORT).show();	
						}
					});						
					// 获取adInfo
					InfoItem positionAdInfo = (InfoItem) mMapForReport.get(position);
					System.out.println("isDisplayed =========== "
							+ positionAdInfo.isDisplayed);
					if (!positionAdInfo.isDisplayed) {
						// 发送展示上报
						positionAdInfo.reportDisplay();
						positionAdInfo.isDisplayed = true;
					}

					return convertView;

				}
				System.out.println("position******" + position);
				mAdHolder.tvDesc.setVisibility(View.INVISIBLE);
				mAdHolder.imgIcon.setVisibility(View.INVISIBLE);
				mAdHolder.tvTitle.setText((String) mList.get(position));
				return convertView;
			} catch (Exception e) {
				// TODO: handle exception
			}
			return convertView;
	
		}

	}
	
	public static class AdHolder {

		TextView tvTitle;
		TextView tvDesc;
		ImageView imgIcon;

	}

	@Override
	public void onBackPressed() {

		super.onBackPressed();
		mList = null;
		mCount = 0;

	}
}
