package com.example.sdk_demo;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

import com.example.sdk_demo.RequestCallBack;

public class RequestHandler extends Handler{

	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		switch (msg.what) {
		case 0:
			//请求失败
			//获得msg对象
			if(msg.obj != null) {
				ResBean failRes = (ResBean)msg.obj;
				if(failRes.requestCallBack != null) {
					failRes.requestCallBack.failed();
				}
			}
			break;
		case 1:
			if(msg.obj != null) {
				ResBean successRes = (ResBean)msg.obj;
				if(successRes.requestCallBack != null) {
					successRes.requestCallBack.successLoadImg(successRes.bitmapImg);
				}
			}
			break;
		}
	}
	
	class ResBean {
		RequestCallBack requestCallBack;
		Bitmap bitmapImg;
	}
}
 
