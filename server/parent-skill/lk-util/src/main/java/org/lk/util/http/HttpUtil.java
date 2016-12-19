package org.lk.util.http;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpUtil extends AbstractHttp {



	@Override
	public String post(String path, Map<Object, Object> header, String params) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		setHeader(header, conn);
		conn.setRequestMethod("POST");// 提交模式
		conn.setConnectTimeout(10000);// 连接超时 单位毫秒
		conn.setReadTimeout(10000);// 读取超时 单位毫秒
		conn.setDoOutput(true);// 是否输入参数
		byte[] bypes = params.toString().getBytes();
		conn.getOutputStream().write(bypes);// 输入参数
		InputStream inStream = conn.getInputStream();
		return readInputStream(inStream);
	}

	public String get(String path, Map<Object, Object> header) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		setHeader(header, conn);
		conn.setRequestMethod("GET");// 提交模式
		conn.setConnectTimeout(10000);// 连接超时 单位毫秒
		conn.setReadTimeout(2000);// 读取超时 单位毫秒
		conn.setDoOutput(true);// 是否输入参数
		InputStream inStream = conn.getInputStream();
		return readInputStream(inStream);
	}

	public static void main(String[] args) throws Exception {
		String path = "http://172.16.130.244:7050/chain/blocks/6";
		System.out.println(new HttpUtil().get(path, null));
	}
}
