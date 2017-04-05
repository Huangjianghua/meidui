package com.first.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.first.utility.VCodeGenerator;

public class GetValidateCodeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OutputStream os =  response.getOutputStream();
		VCodeGenerator vg = new VCodeGenerator(os);
		String vcode =  vg.drawCode();
		//将验证码的值写到session中
		request.getSession().setAttribute("vcode", vcode);
		os.close();
	}
}
