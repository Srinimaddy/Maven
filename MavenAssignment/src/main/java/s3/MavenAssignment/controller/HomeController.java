package s3.MavenAssignment.controller;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import s3.MavenAssignment.config.AppDao;
import s3.MavenAssignment.config.AppDaoimpl;
import s3.MavenAssignment.config.User;


@Controller
public class HomeController {

	boolean finalflag;
	
	
	public boolean isFinalflag() {
		return finalflag;
	}
	public void setFinalflag(boolean finalflag) {
		this.finalflag = finalflag;
	}
	@RequestMapping("/")
	public ModelAndView homepage() {
		ModelAndView model = new ModelAndView("home");
		
		return model;
	}
	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response
	  ) {
		User user= new User();
		
		String email= request.getParameter("email");
		String password= request.getParameter("pwd");
		user.setEmail(email);
		user.setpassword(password);
		ClassPathXmlApplicationContext context	= new ClassPathXmlApplicationContext("/s3/MavenAssignment/config/Spring-AppDAOConfig.xml");
		AppDaoimpl DAO = context.getBean("DAOBean", AppDaoimpl.class);
		int login=DAO.listUsers(email,password);
		
		if(login==1)
		{
			
			try {
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		else
		{
			try {
				request.getRequestDispatcher("fail.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		ModelAndView model;
		 model = new ModelAndView("Login");
	    return model;
	  }
	
	public Boolean validate()
	{
		
		boolean finalstatus=isFinalflag();
		System.out.println("finalflag"+finalstatus);
		return finalstatus;
	}
}




