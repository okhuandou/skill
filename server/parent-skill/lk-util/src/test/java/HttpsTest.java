import org.junit.Test;
import org.lk.util.http.HttpsUtil;

public class HttpsTest {

	@Test
	public void post() throws Exception{
		String post = new HttpsUtil().post("http://172.16.101.78/csfpf/newproduct/sy/1/10", null
				, " {'NC':'13921111130','PASSWORD':'e10adc3949ba59abbe56e057f20f883e','YDDH':'13921111130','ZCQD':'5'}");
		System.out.println(post);
	}
//	接口url ： https://172.16.101.78:443/csfpf/person/register
//		请求json  ：
}
