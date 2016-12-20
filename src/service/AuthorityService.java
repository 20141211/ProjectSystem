package service;

import java.util.List;

import po.Authority;

import dao.AuthorityDao;

public class AuthorityService {
	AuthorityDao dao=new AuthorityDao();
	//selectAllAuthoritiesByEmpno
	public List<Authority> getAuthorities(int empno){
		return dao.selectAllAuthoritiesByEmpno(empno);
	}

}
