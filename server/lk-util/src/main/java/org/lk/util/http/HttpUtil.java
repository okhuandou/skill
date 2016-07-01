package org.lk.util.http;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil extends AbstractHttp{

	public void httpset(HttpURLConnection conn) {
		// userId=00000000-6ffd-a873-7a82-3a900033c587+18:dc:56:b8:12:29+18:DC:56:BA:12:29
		// 10011889635/Android/4.3/OTCTOP/Phone/2.0.0/Coolpa d
		// 8702//18:dc:56:b8:12:29/18:DC:56:BA:12:29//ARMv7 Processor rev 3
		// (v7l)/00000000-6ffd-a873-7a82-3a900033c587/480/4AF6132E3CE5E4845C09E0619F64D6CC
		// user-agent=Android_4.0/TDRMPC_Pad(orPhone)_clientVersion/LG778;
		conn.setRequestProperty("user-agent", "Android_5.0.1/OTCTOP_Phone_0.0.1/HUAWEI GRA-UL10");
		// di= wifiMAC/BLUETOOTH/CPU/UUID/Resolution;
		conn.setRequestProperty("di",
				"18:dc:56:b8:12:29/18:DC:56:BA:12:29/AArch64 Processor rev 3 (aarch64)/00000000-6ffd-a873-7a82-3a900033c587/1080");
	}


	public String post(String path, String params) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		httpset(conn);
		conn.setRequestMethod("POST");// 提交模式
		// conn.setConnectTimeout(10000);//连接超时 单位毫秒
		// conn.setReadTimeout(2000);//读取超时 单位毫秒
		conn.setDoOutput(true);// 是否输入参数
		byte[] bypes = params.toString().getBytes();
		conn.getOutputStream().write(bypes);// 输入参数
		InputStream inStream = conn.getInputStream();
		return readInputStream(inStream);
	}

}
