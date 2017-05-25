/**
 * 
 */
package com.meiduimall.platform.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.yaml.snakeyaml.Yaml;

import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.platform.config.model.ConfigerMsg;

/**
 * @author: jianhua.huang
 * @version: 2017年5月23日 上午11:44:28 0.1 Description:
 */
public class Test {
	public static void main(String[] args) throws IOException, GitAPIException {
		//method1();
		//method2();
		//cloneRepository("http://git.meiduimall.com/service/meiduimall.git","d:\\tmp\\test");
		//commitRepository("D:\\gitProject\\featureV4.0.2\\meiduimall","020-service-config.yml","测试提交jgit");
		pushRepository("D:\\gitProject\\featureV4.0.2\\meiduimall");
	}
	
	   /**
     * 下载仓库
     * @param url 远程url
     * @param localPath 本地主仓路径
     */
    public static String cloneRepository(String url, String localPath) {
        try {
            System.out.println("开始下载主仓。。。");
            CloneCommand cloneCommand = Git.cloneRepository().setURI(url);
            cloneCommand.setDirectory(new File(localPath)).call();
            System.out.println("主仓下载完成。。。");
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
    
   
	 /**
     * 拿到当前本地分支名
     * @param localPath 主仓
     * @return
     * @throws IOException
     */
    public static String getCurrentBranch(String localPath) throws IOException{
            Git git = Git.open(new File(localPath));
            return git.getRepository().getBranch();
    }
	
	//上传git服务器
	public static void method2() throws IOException, GitAPIException {
		try {
			 FileRepositoryBuilder builder = new FileRepositoryBuilder();  
		        String projectURL = System.getProperty("user.dir");  
		        Repository repository = builder.setGitDir(new File(projectURL.substring(0, projectURL.lastIndexOf("\\"))+"\\.git"))  
		                .readEnvironment() // scan environment GIT_* variables  
		                .findGitDir() // scan up the file system tree  
		                .build();  
		        Git git = new Git(repository);  
		        AddCommand add = git.add();  
		        add.addFilepattern(",").call();//git add .  
		        CommitCommand commit = git.commit();  
		        /**-Dusername=%teamcity.build.username%**/  
		        commit.setCommitter("jianhua", "jianhua.huang@meiduimall.com");  
		        commit.setAuthor("jianhua","jianhua.huang@meiduimall.com");  
		        commit.setAll(true);  
		        commit.setMessage("use jgit");
		        /*commit.setCommitter(new PersonIdent(repository));  */
		        RevCommit revCommit = commit.call();//git commit -m "use jgit"  
		        String commitId = revCommit.getId().name();  
		        System.out.println(commitId);  
		        //PushCommand push = git.push();  
		        //push.call();//git push  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	   /** push到远程仓库
	     * 
	     */
	    public static String pushRepository(String localPath) {
	        try {
	            Git git = Git.open(new File(localPath));
	            PushCommand pushCommand = git.push();
	            CredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider(
	                    "jianhua.huang@meiduimall.com", "huang.123");
	            pushCommand.setCredentialsProvider(credentialsProvider);
	            // pushCommand.add(fileNames);
	            pushCommand.setForce(true).setPushAll();
	            Iterable<PushResult> iterable = pushCommand.call();
	            for (PushResult pushResult : iterable) {
	                System.out.println(pushResult.toString());
	            }
	            return null;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return e.getMessage();
	        }
	    }
	 /**
     * 提交本地代码
     * @param localPath 主仓
     * @param fileNames 文件名集合
     * @param message 备注 
     * @return 
     */
	
    public static String commitRepository(String localPath, String fileNames,
            String message) {
        try {
            Git git = Git.open(new File(localPath));
            AddCommand addCommand = git.add();
            String[] fileArr = fileNames.split(",");
            for (String file : fileArr) {
                addCommand.addFilepattern(file);
            }
            addCommand.call();
            CommitCommand commitCommand = git.commit();
            commitCommand.setMessage(message);
            commitCommand.setAll(true);
            commitCommand.call();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
	
	public static void method1() throws IOException {
		Yaml yaml = new Yaml();
		// InputStream io=new

		// 加载数据
		URL url = Test.class.getClassLoader().getResource("o2o-service-config.yml");
		File directory = new File("");// 参数为空
	    String courseFile = directory.getCanonicalPath()+"\\src\\main\\resources\\o2o-service-config.yml";
	    System.out.println(courseFile);
		System.out.println(url);
		System.out.println(System.getProperty("o2o-service-config.yml"));
		url=Thread.currentThread().getContextClassLoader().getResource("o2o-service-config.yml");
		System.err.println(Test.class.getResource("o2o-service-config.yml"));
		Object map = (Object) yaml.load(new FileInputStream(url.getFile()));
		if (map == null) {
			map = new Object();
		}
		/*
		 * List<ConfigerMsg> list=JsonUtils.jsonToList(map.toString(),
		 * ConfigerMsg.class); ArrayList<ConfigerMsg> newList = new
		 * ArrayList<>(list); for(ConfigerMsg configerMsg:list){
		 * System.out.println(configerMsg.getName()); }
		 */
		List<ConfigerMsg> configerMsgs = new ArrayList<>();

		ConfigerMsg msg = new ConfigerMsg("个代分润比例12", "开启12", "O2O1", "21", "sysadmin1", "1111111111");
		configerMsgs.add(msg);
		String object = JsonUtils.beanToJson(configerMsgs);
		// List list=Arrays.asList(msg);
		// 创建文件
		yaml.dump(object, new FileWriter("src/main/resources/o2o-service-config.yml"));

		System.out.println(map.toString());
	}
}
