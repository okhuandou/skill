package com.example.lk.learnandroid01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class ContantData {
	public static final String PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
	public static final String SDPATHINFO = PATH + "/sdinfo.txt";
	//存储图片
		public static void saveImg(String path, Bitmap bitmapImg) {
			System.out.println("加载图片");
			File file = new File(path);
			try {
				FileOutputStream fos = new FileOutputStream(file);
//			//将图片存入到sd卡中
				bitmapImg.compress(Bitmap.CompressFormat.PNG, 100, fos);
				fos.flush();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		//判断图片是否存在
		public static boolean imgExist(String path) {
			try {

				File file = new File(path);

				if (file.exists()) {
					return true;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
		//本地获取图片
		public static Bitmap getImg(String path) {
			try {
				
				File file = new File(path);
				System.out.println("file.exists()" + file.exists());
				
				if (file.exists()) {
					// 创建一个输入流
					FileInputStream fis = new FileInputStream(file);
					// 将流转换成图片
					Bitmap bitmap = BitmapFactory.decodeStream(fis);
					// 删除文件
					if (file.isFile() && file.exists()) {
						file.delete();
					}
					return bitmap;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}
	
}
