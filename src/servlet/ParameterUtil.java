package servlet;

import javax.servlet.http.HttpServletRequest;

public class ParameterUtil {
	private static ThreadLocal<Object> parameter=new ThreadLocal<Object>();
	public static void set(Object value){
		parameter.set(value);
	}
	public static Object get(){
		Object object=parameter.get();
		parameter.remove();		
		return object;
	}
	public static HttpServletRequest getRequest(){
		ReqAndResp reqAndResp=(ReqAndResp)parameter.get();
		HttpServletRequest request=reqAndResp.getReq();
		parameter.remove();		
		return request;
	}

}
