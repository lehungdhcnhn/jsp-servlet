package com.dhcnhn.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dhcnhn.model.NewModel;
import com.dhcnhn.model.UserModel;
import com.dhcnhn.service.INewService;
import com.dhcnhn.utils.HttpUtil;
import com.dhcnhn.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
@WebServlet(urlPatterns = {"/api-admin-new"})
public class NewAPI extends HttpServlet{
	@Inject
	private INewService newServices;
	/**
	 * 
	 */
	private static final long serialVersionUID = -8444722004388210011L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		NewModel newModel = HttpUtil.Of(request.getReader()).toModel(NewModel.class); 
		newModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
		newModel = newServices.save(newModel);
		mapper.writeValue(response.getOutputStream(), newModel);
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		NewModel updateNew=HttpUtil.Of(request.getReader()).toModel(NewModel.class);
		updateNew.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
		updateNew = newServices.update(updateNew);
		mapper.writeValue(response.getOutputStream(), updateNew);
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		NewModel newModel = HttpUtil.Of(request.getReader()).toModel(NewModel.class);
		newServices.delete(newModel.getIds());
		mapper.writeValue(response.getOutputStream(), "{}");
	}
	
}
