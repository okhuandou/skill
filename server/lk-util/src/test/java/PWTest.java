import java.net.URLEncoder;

import org.junit.Test;
import org.lk.util.http.HttpUtil;

public class PWTest {

	HttpUtil httpUtil = new HttpUtil();

	@Test
	public void post() throws Exception {
		String post = httpUtil.post("http://localhost:8080/bbs/user/isAttention/2", "ids=" + URLEncoder.encode("4"));
		System.out.println(1);
		System.out.println(new String(post));
	}

	/**
	 * 用户加密、解密token type 为0加密，1解密
	 * 
	 * @throws Exception
	 */
	@Test
	public void userTokenEnt() throws Exception {
		String post = httpUtil.post("http://172.16.130.102:8090/pw/user/encrypt.action",
				// byte[] post =
				// httpUtil.post("http://127.0.0.1:8080/pw/user/encrypt.action",
				"content=123456&"
						+ "userId=00000000-6ffd-a873-7a82-3a900033c587%2B18:dc:56:b8:12:29%2B18:DC:56:BA:12:29"
						+ "&type=0");
		System.out.println(1);
		System.out.println(new String(post));
	}

	/**
	 * 用户数据解密后摘要
	 * 
	 * @throws Exception
	 */
	@Test
	public void userdecMd() throws Exception {
		// byte[] post =
		// httpUtil.post("http://172.16.130.102:8090/pw/user/encrypt.action",
		String post = httpUtil.post("http://127.0.0.1:8080/pw/user/decMd.action",
				"content=6af93fa45cfc39e697ee658d2dc8c25f&"
						+ "userId=00000000-6ffd-a873-7a82-3a900033c587%2B18:dc:56:b8:12:29%2B18:DC:56:BA:12:29&"
						+ "system=1&mdType=0&business=2");
		System.out.println(1);
		System.out.println(new String(post));
	}

	/**
	 * 用户转加密 AES 充值 ，提现 web时system=0000 app为空
	 * 
	 * @throws Exception
	 */
	@Test
	public void usertransEnc() throws Exception {
		// byte[] post =
		// httpUtil.post("http://172.16.130.102:8090/pw/user/transEnc.action",
		String post = httpUtil.post("http://127.0.0.1:8080/pw/user/transEnc.action",
				"content=6af93fa45cfc39e697ee658d2dc8c25f&"
						+ "userId=00000000-6ffd-a873-7a82-3a900033c587%2B18:dc:56:b8:12:29%2B18:DC:56:BA:12:29&"
						+ "target=1&" + "business=&system=&" + "encTyp=0");
		System.out.println(1);
		System.out.println(new String(post));
	}

	/**
	 * 协商密钥
	 * 
	 * @throws Exception
	 */
	@Test
	public void getUserKey() throws Exception {
		// byte[] post =
		// httpUtil.post("http://127.0.0.1:8080/pw/getUserKey.action",
		// "r1=12345678");
		String post = httpUtil.post("http://172.16.130.102:8090/pw/getUserKey.action", "r1=12345678");
		System.out.println(1);
		System.out.println(new String(post));
	}
}
