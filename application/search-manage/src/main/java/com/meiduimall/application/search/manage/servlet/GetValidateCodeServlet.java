package com.meiduimall.application.search.manage.servlet;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.meiduimall.application.search.manage.utility.VCodeGenerator;

public class GetValidateCodeServlet extends HttpServlet{
	
	
	private static Logger log = LoggerFactory.getLogger(GetValidateCodeServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		OutputStream os=null;
		String vcode;
		try {
			os = response.getOutputStream();
			VCodeGenerator vg = new VCodeGenerator(os);
			vcode = vg.drawCode();
			//将验证码的值写到session中
			request.getSession().setAttribute("vcode", vcode);
		} catch (IOException e) {
			log.error("验证码生成异常：{}",e);
		}finally {
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					os=null;
					log.error("验证码生成io关闭异常：{}",e);
				}
			}
		}
		
	}
}
