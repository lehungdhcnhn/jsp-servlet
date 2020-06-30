package com.dhcnhn.controller.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.omg.CORBA.RepositoryIdHelper;

import com.dhcnhn.model.NewModel;
import com.dhcnhn.model.UserModel;
import com.dhcnhn.service.ICategoryService;
import com.dhcnhn.service.INewService;
import com.dhcnhn.service.IUserService;
import com.dhcnhn.utils.FormUtil;
import com.dhcnhn.utils.SessionUtil;

@WebServlet(urlPatterns = {"/trang-chu","/dang-nhap","/thoat"})
public class HomeController extends HttpServlet {
	@Inject
	private ICategoryService categoryService;
	@Inject
	private INewService newService;
	@Inject
	private IUserService userService;
	private static final long serialVersionUID = 2686801510274002166L;
	ResourceBundle resouceBundle = ResourceBundle.getBundle("message");
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		//request.setAttribute("categories", categoryService.findAll());
		String action = request.getParameter("action");
		if(action!=null&&action.equals("login"))
			
		{
			String message = request.getParameter("message");
			String alert= request.getParameter("alert");
			if(message!=null && alert!=null)
			{
				request.setAttribute("message", resouceBundle.getString(message));
				request.setAttribute("alert", alert);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
		} else if(action!=null&&action.equals("logout"))
		{
			SessionUtil.getInstance().removeValue(request, "USERMODEL");
			response.sendRedirect(request.getContextPath()+"/trang-chu");
		}else
		{
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(request, response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action!=null&&action.equals("login"))
		{
			UserModel model = FormUtil.toModel(UserModel.class, request);
			model = userService.findByUserNameAndPasswordAndStatus(model.getUserName(), model.getPassword(), 1);
			if(model!=null)
				
			{
				SessionUtil.getInstance().putValue(request, "USERMODEL", model);
				if(model.getRole().getCode().equals("USER")) 
				{
					response.sendRedirect(request.getContextPath()+"/trang-chu");
				}else if(model.getRole().getCode().equals("ADMIN"))
				{
					response.sendRedirect(request.getContextPath()+"/admin-home");
				}
			}
			else
			{
				response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=username_password_invalid&alert=danger");
			}
		}
	}
}
