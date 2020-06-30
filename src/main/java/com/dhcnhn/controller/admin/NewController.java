package com.dhcnhn.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dhcnhn.constant.SystemConstant;
import com.dhcnhn.model.NewModel;
import com.dhcnhn.paging.PageRequest;
import com.dhcnhn.paging.Pageble;
import com.dhcnhn.service.ICategoryService;
import com.dhcnhn.service.INewService;
import com.dhcnhn.sort.sorter;
import com.dhcnhn.utils.FormUtil;

@WebServlet(urlPatterns = {"/admin-new"})
public class NewController extends HttpServlet {
	
	private static final long serialVersionUID = 2686801510274002166L;
	@Inject
	private INewService newService;
	
	@Inject
	private ICategoryService categorySerVice;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NewModel model = FormUtil.toModel(NewModel.class, request);
		String view ="";
		if(model.getType().equals(SystemConstant.LIST))
		{
			Pageble pageble = new PageRequest(model.getPage(),model.getMaxPageItem(),new sorter(model.getSortName(), model.getSortBy()));
			model.setListResult(newService.findAll(pageble));
			model.setTotalItem(newService.getTotalItem());
			model.setTotalPage((int)Math.ceil((double)model.getTotalItem()/model.getMaxPageItem()));
			view="/views/admin/new/list.jsp";
		
		}
		else if(model.getType().equals(SystemConstant.EDIT))
		{
			if(model.getId()!=null)
			{
				model= newService.findOne(model.getId());
			}
			else
			{
				
			}
			request.setAttribute("categories", categorySerVice.findAll());
			view="/views/admin/new/Edit.jsp";
			
		}
		request.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}
