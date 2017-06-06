package com.meiduimall.platform.config.constant;

import com.meiduimall.core.BaseApiCode;

/**
 * @author:   jianhua.huang 
 * @version:  2017年5月23日 下午3:50:27 0.1 
 * Description: 配置管理常量
 */
public class ApiStatusConst extends BaseApiCode {
	public final static String SUCCESS_C = "Success"; 
	public final static String SUCCESS_M = "操作成功"; 
	/**
	 * 读取资源文件url错误
	 */
	public final static Integer READ_RESOURCES_FILE_ERROR=8801;
	/**
	 * 加载资源文件数据错误
	 */
	public final static Integer LOAD_RESOURCES_FILE_ERROR=8802;
	
	/**
	 * 写入资源文件数据错误
	 */
	public final static Integer WRITE_RESOURCES_FILE_ERROR=8803;
	/**
	 * config服务 提交git异常
	 */
	public final static Integer GIT_COMMINT_FILES_ERROR=8804;
	
	/**
	 * Git下载远程仓库到本地异常
	 */
	public final static Integer GIT_CLONE_REPOSITORY_ERROR=8805;
	
	/**
	 * 配置文件 复制异常
	 */
	public final static Integer FILE_COPY_ERROR=8806;
	
	/**
	 * 修改配置文件异常
	 */
	public final static Integer UPDATE_CONFIG_FILE_ERROR=8807;
	
	
	static {
		zhMsgMap.put(READ_RESOURCES_FILE_ERROR, "读取资源文件url异常");
		zhMsgMap.put(LOAD_RESOURCES_FILE_ERROR, "加载资源文件数据异常");
		zhMsgMap.put(WRITE_RESOURCES_FILE_ERROR, "写入资源文件数据异常");
		zhMsgMap.put(GIT_COMMINT_FILES_ERROR, "提交到Git服务异常");
		zhMsgMap.put(GIT_CLONE_REPOSITORY_ERROR, "Git下载远程仓库到本地异常");
		zhMsgMap.put(FILE_COPY_ERROR, "配置文件复制异常");
		zhMsgMap.put(UPDATE_CONFIG_FILE_ERROR, "修改配置文件异常");
		
		/*zhMsgMap.put(DECRYPTION_EXCEPTION, "解密程序异常");
		zhMsgMap.put(ENCRYPTION_EXCEPTION, "加密程序异常");
		zhMsgMap.put(HTTP_EXCEPTION, "HTTP请求异常");
		zhMsgMap.put(PARSE_DATE_EXCEPTION, "日期解析异常");
		zhMsgMap.put(DB_SELECT_EXCEPTION, "数据库查询失败");
		zhMsgMap.put(DB_UPDATE_EXCEPTION, "数据库更新失败");
		zhMsgMap.put(DB_DELETE_EXCEPTION, "数据库删除失败");
		zhMsgMap.put(DB_INSERT_EXCEPTION, "数据库插入失败");*/

		
	}

}
