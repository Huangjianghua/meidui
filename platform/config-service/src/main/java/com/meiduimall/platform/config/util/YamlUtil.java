package com.meiduimall.platform.config.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.MdBizException;
import com.meiduimall.platform.config.constant.ApiStatusConst;
import com.meiduimall.platform.config.constant.Constant;
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
	public static List<ConfigerMsg> loadData(String typeConfig) throws MdBizException {
		ArrayList<ConfigerMsg> arraylist=null;
		//step1 加载文件 判断是否存在
		String courseFile  = System.getProperty(Constant.PROJECT_DIR)+findSrcResourceUrl+typeConfig+configName;
		File file=new File(courseFile);
		if(!file.exists()) return arraylist;
	    try {
			 Object obj =(Object)yaml.load(new FileInputStream(courseFile));
			 if(obj==null){
				 return arraylist;
			 }
			 List<ConfigerMsg> list=JsonUtils.jsonToList(obj.toString(), ConfigerMsg.class);
			 arraylist = new ArrayList<>(list);
		} catch (Exception e) {
			logger.error("加载资源文件数据异常:{}", e);
			throw new MdBizException(ApiStatusConst.LOAD_RESOURCES_FILE_ERROR);
		}
		return arraylist;
	}
	/**
	 * 新增配置管理信息
	 * @param configerMsg
	 * @author: jianhua.huang  2017年5月23日 下午5:33:00
	 */
	public static void addDumpConfigManage(ConfigerMsg configerMsg)throws MdBizException{
		URL url = YamlUtil.class.getClassLoader().getResource(configerMsg.getType() + configName);
		//step1判断是否存在配置资源文件
		List<ConfigerMsg> listConfig=new ArrayList<>();
		if (url != null) {
			//step2 加载已有的数据
			listConfig=loadData(configerMsg.getType());
		}
		//step3 新增配置信息
		listConfig.add(configerMsg);
		operateYml(listConfig,configerMsg.getType());
	}
	
	/**
	 * 更新配置管理信息
	 * @param configerMsg
	 * @author: jianhua.huang  2017年5月23日 下午5:33:00
	 */
	public static void updateDumpConfigManage(ConfigerMsg configerMsg)throws MdBizException{
		//step1 首先去加载文件 
		List<ConfigerMsg> list=loadData(configerMsg.getType());
		 if(list==null){
			logger.error("加载{}类型资源文件数据异常",configerMsg.getType());
			throw new MdBizException(ApiStatusConst.UPDATE_CONFIG_FILE_ERROR);
		 }
		 //step2 查找修改的配置信息   根据key判断
		 for(int i=0;i<list.size();i++){
			 ConfigerMsg conf=list.get(i);
			 if(conf.getKey().equals(configerMsg.getKey())){
				 list.remove(i);
				 break;
			 }
		 }
		 list.add(configerMsg);
		 //step2 修改yml文件
		 operateYml(list,configerMsg.getType());
	}
	/**
	 * 写操作yml
	 * @param list
	 * @author: jianhua.huang  2017年5月23日 下午10:09:43
	 */
	private static void operateYml(List<ConfigerMsg> list,String typeConfig) throws MdBizException{
		 try {
			 String object=JsonUtils.beanToJson(list);
			 String fileName=typeConfig+configName; //文件名称
			 logger.info("operateYml开始操作配置文件信息,文件名称:{}",fileName);
			 //step1 创建文件
			 yaml.dump(object, new FileWriter(srcResourceUrl+fileName));
			 //step2 提交到config service git服务器
			 String projectURL = System.getProperty(Constant.PROJECT_DIR);   //项目路径 
			 String configProjectURL=projectURL.substring(0,projectURL.indexOf(Constant.PROJECT_NAME)-1);
			 commintFilesToGitService(fileName,configProjectURL);
			 //step3 提交到service-config-repo
			 String fileSourceUrl=projectURL+findSrcResourceUrl+fileName; //生成的文件 绝对路径
			 commintServiceConfigRepo(fileName,fileSourceUrl);
		} catch (Exception e) {
			logger.error("写入资源文件数据异常:{}", e);
			throw new MdBizException(ApiStatusConst.WRITE_RESOURCES_FILE_ERROR);
		}
	}
	
	/**
	 * 提交文件到git服务器上
	 * @param fileNames
	 * @param projectUrl
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年5月25日 下午10:30:00
	 */
	private static void commintFilesToGitService(String fileNames,String projectUrl)throws MdBizException{
		 try {
			 	long beginDate= System.currentTimeMillis();
			 	logger.info("开始往Git服务器提交文件:{},提交开始时间:{}",projectUrl+fileNames,beginDate);
			 	//获取本地分支信息
	            Git git = Git.open(new File(projectUrl));
	            AddCommand addCommand = git.add();
	            String[] fileArr = fileNames.split(",");
	            for (String file : fileArr) {
	                addCommand.addFilepattern(file);
	            }
	            //step1 本地提交
	            addCommand.call();
	            CommitCommand commitCommand = git.commit();
	            commitCommand.setMessage(Constant.GIT_COMMINT_MESSAGE);
	            commitCommand.setAll(true);
	            commitCommand.call();
	            //step2提交远程
	            PushCommand pushCommand = git.push();
	            CredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider(
	            		Constant.GIT_COMMINT_USER, Constant.GIT_COMMINT_USER_PASSWORD);
	            pushCommand.setCredentialsProvider(credentialsProvider);
	            pushCommand.setForce(true).setPushAll();
	            pushCommand.call();
	            long endDate= System.currentTimeMillis();
	            logger.info("结束提交文件:{}到Git服务器,提交结束时间:{},耗时:{}s",fileNames,endDate,(endDate-beginDate)/1000);
	        } catch (Exception e) {
	        	logger.error("配置文件提交到Git服务器异常:{}", e);
				throw new MdBizException(ApiStatusConst.GIT_COMMINT_FILES_ERROR);
	        }
	}
	
	/**
	 * 检测http://git.meiduimall.com/service/service-config-repo.git  是否存在本地分支
	 * @param fileNames    文件名称
	 * @param fileUrl  源文件所在绝对路径
	 * @return
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年5月25日 下午10:34:53
	 * @throws IOException 
	 */
	private static void commintServiceConfigRepo(String fileNames,String fileUrl) throws MdBizException, IOException{
		//step1 判断本地是否存在分支
		File file=new File(Constant.DIR_TEM+fileNames);
		if(!file.exists()){
			//step2 下载分支代码
			cloneRepository(Constant.DOWNLOAD_GIT_URL,Constant.DIR_TEM);
		}
		//step3复制文件 
		copyFile(new File(fileUrl),new File(Constant.DIR_TEM+fileNames));
		//step4  执行提交代码操作
		commintFilesToGitService(fileNames,Constant.DIR_TEM);
	}
	
	 /**
     * 下载仓库
     * @param url 远程url
     * @param localPath 本地主仓路径
     */
    private static void cloneRepository(String url, String localPath) throws  MdBizException{
        try {
            CloneCommand cloneCommand = Git.cloneRepository().setURI(url);
            cloneCommand.setDirectory(new File(localPath)).call();
        } catch (Exception e) {
        	logger.error("从Git服务器url:{},下载代码到本地仓库:{},异常:{}",url,localPath,e);
			throw new MdBizException(ApiStatusConst.GIT_CLONE_REPOSITORY_ERROR);
        }
    }
    /**
     * copy 文件信息
     * @param source  源文件路径
     * @param dest    目标文件路径
     * @throws MdBizException
     * @throws IOException
     * @author: jianhua.huang  2017年5月26日 上午10:06:59
     */
	private static void copyFile(File source, File dest)throws MdBizException,IOException {
		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		FileInputStream fis = null;
	    FileOutputStream fos = null;
		try {
			fis = new FileInputStream(source);
			fos = new FileOutputStream(dest);
			inputChannel = fis.getChannel();
			outputChannel = fos.getChannel();
			outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
		} catch(Exception e){
			logger.error("源文件url:{},目标路径url:{},配置文件复制异常:{}",source.getPath(),dest.getPath(),e);
			throw new MdBizException(ApiStatusConst.FILE_COPY_ERROR);
		}finally {
			fis.close();
			fos.close();
			inputChannel.close();
			outputChannel.close();
		}
	}
}	
