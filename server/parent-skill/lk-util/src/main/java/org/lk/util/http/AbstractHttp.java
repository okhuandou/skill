package org.lk.util.http;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.Map;

public abstract class AbstractHttp {

	public String post(String path, Map<Object, Object> header, Map<Object, Object> param)
			throws Exception {
		StringBuffer params = new StringBuffer();
		if (param != null && param.keySet().size() > 0) {
			for (Object key : param.keySet()) {
				params.append(key + "=" + URLEncoder.encode(param.get(key).toString(), "UTF-8") + "&");
			}
		}
		return new String(post(path, header, params.toString()));
	}

	public void setHeader(Map<Object, Object> header, HttpURLConnection conn) {
		if (header != null) {
			for (Object key : header.keySet()) {
				conn.setRequestProperty(StirngToNotNull(key), StirngToNotNull(header.get(key)));
			}
		}
	}
	public abstract String post(String path, Map<Object, Object> header, String params)
			throws Exception;

	public  String readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();// 网页的二进制数据
		outStream.close();
		inStream.close();
		return new String(data);
	}

	private String StirngToNotNull(Object obj) {
		if (obj == null)
			return "";
		else
			return obj.toString();
	}
}
