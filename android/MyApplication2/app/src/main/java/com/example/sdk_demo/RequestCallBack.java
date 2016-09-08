package com.example.sdk_demo;

import android.graphics.Bitmap;

public interface RequestCallBack {
	void successLoadImg(Bitmap bitmapImg);
	void failed();
}
