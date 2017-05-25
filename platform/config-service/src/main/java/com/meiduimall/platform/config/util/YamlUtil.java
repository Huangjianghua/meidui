/**
 * 
 */
package com.meiduimall.platform.config.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.MdBizException;
import com.meiduimall.platform.config.constant.ApiStatusConst;
import com.meiduimall.platform.config.model.ConfigerMsg;

/**
 * @author:   jianhua.huang 
 * @version:  2017年5月23日 下午3:01:28 0.1 
 * Description: 操作yaml文件工具类
 */
public class YamlUtil {	
	private final static Logger logger=LoggerFactory.getLogger(YamlUtil.class);
	/**
	 * 配置文件名称
	 */
	private static final String configName="-service-config.yml";
	
	private static final String srcResourceUrl="src/main/resources/config/";
	
	private static final String findSrcResourceUrl="\\src\\main\\resources\\config\\";
	
	private static  Yaml yaml = new Yaml();
	
	/**
	 * 查询配置信息
	 * @param typeConfig   区分配置名称   获取不同配置信息
	 * @return
	 * @author: jianhua.huang  2017年5月23日 下午4:38:35
	 */
	public static List<ConfigerMsg> loadData(String typeConfig)  {
		ArrayList<ConfigerMsg> arraylist=null;
	    String courseFile  = System.getProperty("user.dir")+findSrcResourceUrl+typeConfig+configName;
		try {
			 Object obj =(Object)yaml.load(new FileInputStream(courseFile));
			 if(obj==null){
				 return null;
			 }
			 List<ConfigerMsg> list=JsonUtils.jsonToList(obj.toString(), ConfigerMsg.class);
			 arraylist = new ArrayList<>(list);
		} catch (FileNotFoundException e) {
			logger.error("加载资源文件数据异常:{}", e);
			return arraylist;
		}
		return arraylist;
	}
	/**
	 * 新增配置管理信息
	 * @param configerMsg
	 * @author: jianhua.huang  2017年5月23日 下午5:33:00
	 */
	public static void addDumpConfigManage(ConfigerMsg configerMsg){
		URL url = YamlUtil.class.getClassLoader().getResource(configerMsg.getType() + configName);
		//判断是否存在配置资源文件
		List<ConfigerMsg> listConfig=new ArrayList<>();
		if (url == null) {
			listConfig = new ArrayList<>();
			listConfig.add(configerMsg);
			operateYml(listConfig,configerMsg.getType());
		}else{
			listConfig=loadData(configerMsg.getType());
			if(listConfig==null){
			return; 
		}
			listConfig.add(configerMsg);
			operateYml(listConfig,configerMsg.getType());
		}
		//commintFilesToGitService();
	}
	
	/**
	 * 更新配置管理信息
	 * @param configerMsg
	 * @author: jianhua.huang  2017年5月23日 下午5:33:00
	 */
	public static void updateDumpConfigManage(ConfigerMsg configerMsg){
		 List<ConfigerMsg> list=loadData(configerMsg.getType());
		 if(list==null){
			return; 
		 }
		 //查找修改的配置信息   根据key判断
		 for(int i=0;i<list.size();i++){
			 ConfigerMsg conf=list.get(i);
			 if(conf.getKey().equals(configerMsg.getKey())){
				 list.remove(i);
				 break;
			 }
		 }
		 list.add(configerMsg);
		 //修改yml文件
		 operateYml(list,configerMsg.getType());
	}
	/**
	 * 写操作yml
	 * @param list
	 * @author: jianhua.huang  2017年5月23日 下午10:09:43
	 */
	private static void operateYml(List<ConfigerMsg> list,String typeConfig){
		 try {
			 String object=JsonUtils.beanToJson(list);
			 //创建文件
			 yaml.dump(object, new FileWriter(srcResourceUrl+typeConfig+configName));
		} catch (IOException e) {
			logger.error("写入资源文件数据异常:{}", e);
			throw new MdBizException(ApiStatusConst.WRITE_RESOURCES_FILE_ERROR);
		}
	}
	
	/**
	 * 提交文件到git服务器上
	 * @throws Exception
	 * @author: jianhua.huang  2017年5月25日 下午3:52:21
	 */
	private void commintFilesToGitService(String fileNames)throws MdBizException{
		//step1提交本地
		 try {
			 	String projectURL = System.getProperty("user.dir");  
			 
	            Git git = Git.open(new File(projectURL));
	            AddCommand addCommand = git.add();
	            String[] fileArr = fileNames.split(",");
	            for (String file : fileArr) {
	                addCommand.addFilepattern(file);
	            }
	            addCommand.call();
	            CommitCommand commitCommand = git.commit();
	            commitCommand.setMessage("配置文件变动提交");
	            commitCommand.setAll(true);
	            commitCommand.call();
	        } catch (Exception e) {
	            e.printStackTrace();
	           
	        }
		//step2提交远程
	}
}	
