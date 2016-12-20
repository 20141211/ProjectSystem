package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CoreServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map<String,String> properties=new HashMap<String,String>();
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		Properties properties=new Properties();
		ServletContext application=config.getServletContext();
		String path=application.getRealPath("/WEB-INF/classes/"+config.getInitParameter("myServletMapping"));
		File file=new File(path);
		InputStream is;
		try {
			is = new FileInputStream(file);
			properties.load(is);
			for(Object string:properties.keySet()){
				this.properties.put((String)string,(String) properties.get(string));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String methodName=req.getParameter("method");
		//System.out.println(methodName+"------------method");
		String uri=req.getRequestURI();
		uri=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		String className=properties.get(uri);
		//System.out.println(uri);
		try {
			Class clazz=Class.forName(className);
			//System.out.println(className);
			Method method=clazz.getMethod(methodName);//, HttpServletRequest.class,HttpServletResponse.class);//req.getClass()?????????Ϊʲô���У���������������������//
			ReqAndResp reqResp=new ReqAndResp(req,resp);
			ParameterUtil.set(reqResp);
			//System.out.println("--------------------------------");
			String ret=(String)method.invoke(clazz.newInstance());
			
			if(ret!=null){
				//System.out.println(ret);
				req.getRequestDispatcher(ret).forward(req, resp);
			}
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
