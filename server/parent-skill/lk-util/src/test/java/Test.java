import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.lk.util.http.HttpUtil;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * ClassName: Test <br/>
 * Function: TODO <br/>
 * Reason: TODO <br/>
 * date: 2016年10月18日 上午9:17:02 <br/>
 *
 * @author lk
 * @version
 * @since JDK 1.7
 */
public class Test {
	static HttpUtil http = null;
	static String sessionid = null;

	public static void systemLogin() throws Exception {
		Map<String, String> header = new HashMap<String, String>();
		header.put("channel", "");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userid", "SACOS");
		params.put("password", "123.Q");
		params.put("scheme", "");
		params.put("algorithm", "plain");
		params.put("securityCode", "");
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, String>>() {
		}.getType();
		String json = gson.toJson(params, type);
		Map<Object, Object> map = setHeader();
		System.out.println(json);
		String resultJson = http.get("http://localhost:8080/interflow/system/login", map);
		System.out.println("download = " + resultJson);
		JSONObject req = JSONObject.parseObject(resultJson);
		sessionid = req.getString("sessionId");
	}

	private static Map<Object, Object> setHeader() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("user-agent", "Android_5.0.1/OTCTOP_Phone_0.0.1/HUAWEI GRA-UL10");
		map.put("di",
				"18:dc:56:b8:12:29/18:DC:56:BA:12:29/AArch64 Processor rev 3 (aarch64)/00000000-6ffd-a873-7a82-3a900033c587/1080");
		map.put("sessionId", sessionid);
		return map;
	}

	public static void main(String[] args) throws Exception {
		http = new HttpUtil();
		systemLogin();
		System.out.println(sessionid);
		String params = "{'amount':'100000','counterId':'600037','cpdm':'S76800','customerId':'600037001741','mac':'20f8c913298d83ff3fdd2b484a50ea7b2e1f79ff266d7f7e78cd31c653c8e3d2','mobile':'18588261617','tradePassword':'4EC37847ACBFE60E120C3238CE582A02','type':'1'}";
		Map<Object, Object> map = setHeader();
		String post = http.post("http://172.16.101.78/csfpf/yg/trade/buy/18588261617", map, params);
		System.out.println(post);
		// System.out.println(URLEncoder.encode("+++"));
		// Long currentTimeMillis = System.currentTimeMillis();
		// System.out.println(currentTimeMillis.toString().substring(7));
		int num = -1;
		System.out.println(new BigDecimal(-num));

		// {"title":"test","content":"test","dataurl":"test"}
	}
}