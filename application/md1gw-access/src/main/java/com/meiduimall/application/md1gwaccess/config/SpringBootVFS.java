package com.meiduimall.application.md1gwaccess.config;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.VFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/*
 * fix the bug:Springboot Mybatis,通过setTypeAliasesPackage配置bean的别名， 打包jar后扫描不到 bean的bug。
 * 解决方案参考:
 *http://blog.csdn.net/cml_blog/article/details/53138851
 *http://www.scienjus.com/mybatis-vfs-bug/
 *https://github.com/mybatis/mybatis-3/issues/325
 *https://github.com/mybatis/spring-boot-starter/tree/master/mybatis-spring-boot-autoconfigure/src/main/java/org/mybatis/spring/boot/autoconfigure
 */
public class SpringBootVFS extends VFS {
	
	private static final Logger logger=LoggerFactory.getLogger("SpringBootVFS.class");

	private final ResourcePatternResolver resourceResolver;

	public SpringBootVFS() {
		this.resourceResolver = new PathMatchingResourcePatternResolver(getClass().getClassLoader());
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	protected List<String> list(URL url, String path) throws IOException {
		
		logger.info("SpringBootVFS list():url:" + url + " path:" + path);	
		Resource[] resources = resourceResolver.getResources("classpath*:" + path + "/**/*.class");
		List<String> resourcePaths = new ArrayList<String>();
		for (Resource resource : resources) {
			resourcePaths.add(preserveSubpackageName(resource.getURI(), path));
		}
		return resourcePaths;
	}

	private static String preserveSubpackageName(final URI uri, final String rootPath) {
		logger.info("SpringBootVFS preserveSubpackageName():uri:" + uri + " rootPath:" + rootPath);
		final String uriStr = uri.toString();
		final int start = uriStr.indexOf(rootPath);
		return uriStr.substring(start);
	}

}
