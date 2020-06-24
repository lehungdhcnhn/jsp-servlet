package com.dhcnhn.controller.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.RepositoryIdHelper;

import com.dhcnhn.model.NewModel;
import com.dhcnhn.model.UserModel;
import com.dhcnhn.service.ICategoryService;
import com.dhcnhn.service.INewService;
import com.dhcnhn.service.IUserService;
import com.dhcnhn.utils.FormUtil;

@WebServlet(urlPatterns = {"/trang-chu","/dang-nhap"})
public class HomeController extends HttpServlet {
	@Inject
	private ICategoryService categoryService;
	@Inject
	private INewService newService;
	@Inject
	private IUserService userService;
	private static final long serialVersionUID = 2686801510274002166L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String title="bai viet 4";
		//String content="bai viet 4";
		//Long categoryId = 1L;
		//Long id =1L;
		//NewModel  newModel= new NewModel(); 
		//newModel.setContent(content);
		//newModel.setTitle(title);
		//newModel.setCategoryId(categoryId);
		//newService.delete(id);
		//newService.save(newModel);
		
		//request.setAttribute("categories", categoryService.findAll());
		String action = request.getParameter("action");
		if(action!=null&&action.equals("login"))
		{
			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
		} else if(action!=null&&action.equals("logout"))
		{
			
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
			model = userService.findByUserNameAndPasswordAndStatus(model.getUserName(), model.getPassword(), model.getStatus());
			if(model!=null)
			{
				if(model.getRole().getCode().equals("USER")) 
				{
					response.sendRedirect(request.getContextPath()+"/trang-chu");
				}else if(model.getRole().getCode().equals("ADMIN"))
				{
					response.sendRedirect(request.getContextPath()+"/admin-home");
				}
			}else
			{
				response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login");
			}
		}
	}
}
