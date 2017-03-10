package com.meiduimall;
import java.util.function.Supplier;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RespPackUtil {
	
	private final static Logger logger = LoggerFactory.getLogger(RespPackUtil.class);
	
	public static ResBodyData execInvokeService(final Request req,
			final ResBodyData response,Supplier<Object> serviceWrapper) {
		
		StackTraceElement ste = new Exception().getStackTrace()[1];
		//服务方法名
		String methodName = ste.getMethodName();
		Object res=null;
		//数据检查
		String checkResult = req.check();
		if (StringUtils.isBlank(checkResult)) {
			try {
				res=serviceWrapper.get();
				response.setStatus_code(BaseApiCode.SUCCESS);
				response.setResult_msg(BaseApiCode.getZhMsg(BaseApiCode.SUCCESS));
				response.setData(res);
			} catch (Exception e) {
				logger.error("服务方法:{},异常信息:{}",methodName,ExceptionUtils.getStackTrace(e));
				response.setStatus_code(BaseApiCode.OPERATE_SYSTEM_FAIL);
				response.setResult_msg(BaseApiCode.getZhMsg(BaseApiCode.OPERATE_SYSTEM_FAIL));
			}	
		}else{
			res=BaseApiCode.CLIENTID_PARAM_INVALID;
			response.setResult_msg(BaseApiCode.getZhMsg(BaseApiCode.CLIENTID_PARAM_INVALID));
		}
		return response;
	}
	
	


}
