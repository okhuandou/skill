import com.alibaba.fastjson.JSONObject;

public class Main {

	public static void main(String[] args) {

		String str = "{'message':'成功','increase':'1000.0元','pagetype':'2','code':'1','records':[{'key':'head','value':[{'key':'head1','value':[{'key':'预期固定收益率(%)','value':'10'}]},{'key':'head2','value':[{'key':'最低认购','value':'10.0万元'},{'key':'浮动收益率','value':''},{'key':'风险等级','value':'较低'}]}]},{'key':'body','value':[{'key':'基本信息','value':[{'key':'产品类型','value':''},{'key':'产品代码','value':'S76800'},{'key':'产品简称','value':'收益凭证01'},{'key':'发行人全称','value':'发行人全称'},{'key':'预期固定收益率(%)','value':'10'},{'key':'浮动收益率','value':''},{'key':'挂钩标的名称','value':''},{'key':'挂钩标的名称','value':''},{'key':'风险等级','value':'较低'},{'key':'产品募集期开始日','value':'2016-07-26'},{'key':'产品募集期终止日','value':'2016-12-26'},{'key':'产品存续期起始日','value':'2017-01-01'},{'key':'产品存续期到期日','value':'2017-02-26'},{'key':'是否提供转让','value':'是'}]},{'key':'交易信息','value':[{'key':'认购起点金额','value':'10.0万元'},{'key':'最小追加认购单位','value':'1000.0元'},{'key':'单笔最高认购金额','value':''}]},{'key':'附件信息','value':[{'key':'产品合同','url':'http://111.207.167.241/xbrl/product/otc/download_do_support_tm.g_form_ctrl?attachmentId=2c90e5b15620edd30156262980c00032&proxyType=true','value':'测试.pdf'},{'key':'产品募集说明书','url':'','value':''},{'key':'风险揭示书','url':'','value':''}]}]}]}";
		JSONObject req = JSONObject.parseObject(str);
	}

}
