package com.example.sdk_demo;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.sdk_demo.RequestHandler.ResBean;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Message;

public class SendRequest {
	private Context contextSend;
	private RequestHandler rqHandler;
	public SendRequest() {
		super();
		rqHandler = new RequestHandler();
	}
		//请求数据后返回的数据为bitmap
		public void sendBitmap(Context context, final String stringUrl,
				final RequestCallBack callBack) {
			contextSend = context;
			if (callBack != null) {
				// 创建一个输出流
				new Thread() {
					public void run() {
						if (isNetWorkConnected()) {
							// 将url转换成URL
							URL url;
							HttpURLConnection conn;
							try {
								url = new URL(stringUrl);
								conn = (HttpURLConnection) url.openConnection();
								// 设置请求方式
								conn.setRequestMethod("GET");
								// 设置超时时间
								conn.setConnectTimeout(5 * 1000);
								// 设置读取超时
								conn.setReadTimeout(5 * 1000);
								// //建立连接
								// conn.connect();
								if (conn.getResponseCode() == 200) {
									// 获取连接的输入流
									InputStream inputStream = conn.getInputStream();
									// 创建一个BitMapFactory
									Bitmap bitmapImg = BitmapFactory
											.decodeStream(inputStream);
									Message msg = rqHandler.obtainMessage();
									msg.what = 1;
									ResBean resBean = rqHandler.new ResBean();
									resBean.bitmapImg = bitmapImg;
									resBean.requestCallBack = callBack;
									msg.obj = resBean;
									rqHandler.sendMessage(msg);
									return;
								}

							} catch (Exception e) {
								callBack.failed();
								System.out.println("图片数据：" + e.getMessage());
								Message msg = rqHandler.obtainMessage();
								msg.what = 0;
								ResBean resBean = rqHandler.new ResBean();
								resBean.requestCallBack = callBack;
								msg.obj = resBean;
								rqHandler.sendMessage(msg);
								e.printStackTrace();
							}

						} else {
							Message msg = rqHandler.obtainMessage();
							msg.what = 0;
							ResBean resBean = rqHandler.new ResBean();
							resBean.requestCallBack = callBack;
							msg.obj = resBean;
							rqHandler.sendMessage(msg);
						}
					};
				}.start();
			}

			return;
		}
		
		protected boolean isNetWorkConnected() {
			if (contextSend != null) {
				// 获得连接管理器
				ConnectivityManager cm = (ConnectivityManager) contextSend
						.getSystemService(Context.CONNECTIVITY_SERVICE);
				// 获得网络信息
				NetworkInfo networkInfo = cm.getActiveNetworkInfo();
				if (networkInfo != null) {
					return networkInfo.isAvailable();
				}
			}
			return false;
		}	
}
