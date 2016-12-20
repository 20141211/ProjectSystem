package servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReqAndResp {
	private HttpServletRequest req;
	private HttpServletResponse resp;
	public ReqAndResp(HttpServletRequest req,HttpServletResponse resp){
		this.req=req;
		this.resp=resp;		
	}
	public HttpServletRequest getReq() {
		return req;
	}
	public HttpServletResponse getResp() {
		return resp;
	}
	
}
