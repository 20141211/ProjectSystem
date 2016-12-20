package action;



import service.AuthorityService;

public class AuthorityAction {
	AuthorityService service =new AuthorityService();
	//
	/*public String showAuthorities(){		
		ReqAndResp reqResp=(ReqAndResp)ParameterUtil.get();
		HttpServletRequest request=reqResp.getReq();
		HttpSession session=request.getSession();
		//System.out.println((Integer)session.getAttribute("empno"));
		List<Authority>authlist=service.getAuthorities((Integer)session.getAttribute("empno"));
		//System.out.println(authlist+"000");
		request.setAttribute("authlist", authlist);
		return "/left.jsp";
	}*/
}
