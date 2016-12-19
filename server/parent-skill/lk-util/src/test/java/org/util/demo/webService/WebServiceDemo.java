package org.util.demo.webService;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 *  
 * @author lk
 *
 */
@WebService
public class WebServiceDemo {

	public String getValue(String name){
		return "我叫"+name;
	}
	
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9001/service/hello", new WebServiceDemo());
	}
}
