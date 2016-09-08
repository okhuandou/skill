package com.example.lk.learnandroid01;

import android.graphics.Bitmap;

public interface RequestCallBack {
	void successLoadImg(Bitmap bitmapImg);
	void failed();
}
