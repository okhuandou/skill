package org.lk.util.http;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Map;

public abstract class AbstractHttp {

	public String post(String path, Map<Object, Object> param) throws Exception {
		StringBuffer params = new StringBuffer();
		if (param != null && param.keySet().size() > 0) {
			for (Object key : param.keySet()) {
				params.append(key + "=" + URLEncoder.encode(param.get(key).toString(), "UTF-8") + "&");
			}
		}
		return new String(post(path, params.toString()));
	}

	public abstract String post(String path, String params) throws Exception;

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
}
