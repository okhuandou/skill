package org.util.demo.webService;

public class WebServiceClient {  
  
	/**
	 * @param args
	 * @throws AxisFault
	 */
	// public static void main(String[] args) throws AxisFault {
	// // 使用RPC方式调用WebService
	// RPCServiceClient serviceClient = new RPCServiceClient();
	// Options options = serviceClient.getOptions();
	// // 指定调用WebService的URL
	// EndpointReference targetEPR = new EndpointReference(
	// "http://localhost:9001/service/hello");
	// options.setTo(targetEPR);
	// // 调用方法的参数值
	// Object[] entryArgs = new Object[] {1, 2};
	// // 调用方法返回值的数据类型的Class对象
	// Class[] classes = new Class[] { float.class };
	// // 调用方法名及WSDL文件的命名空间
	// //
	// 命名空间是http://localhost:8080/axis2/services/CalculateService?wsdl中wsdl:definitions标签targetNamespace属性
	// QName opName = new QName("http://test.webservice",
	// "WebServiceDemoService");
	// // 执行方法获取返回值
	// // 没有返回值的方法使用serviceClient.invokeRobust(opName, entryArgs)
	// Object result =serviceClient.invokeBlocking(opName, entryArgs,
	// classes)[0];
	// System.out.println(result);
	// // out: 3.0
	// }
  
}