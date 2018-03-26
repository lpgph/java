package com.refill.util;

import com.refill.base.constant.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLDecoder;
import java.util.Properties;

public class PropertiesUtil {

	private static final Logger LOG = LoggerFactory.getLogger(PropertiesUtil.class);

	public static String getConfig(String key,String fileName) {
		String ret = "";
		// 获取配置文件内容
		Properties p = new Properties();
		InputStream is = null;
		try {
			String startPath = URLDecoder.decode(PropertiesUtil.class.getProtectionDomain().getCodeSource().getLocation().getFile(), "utf-8");
			if (startPath.endsWith(".jar")) {// 可执行jar包运行的结果里包含".jar"
				// 截取路径中的jar包名
				startPath = startPath.substring(0, startPath.lastIndexOf(File.separator) + 1);
				startPath = startPath + "conf/";
			}
			//LOG.info("start path : " + startPath);
			String configFilePath = startPath + "" + fileName;
			LOG.info("start path : " + configFilePath);
			if (FileUtil.exist(configFilePath)) {
				is = new BufferedInputStream(new FileInputStream(configFilePath));
				p.load(new InputStreamReader(is,"UTF-8"));
				ret = p.getProperty(key).trim().toString();
			}else{
				is  = PropertiesUtil.class.getClassLoader().getResourceAsStream(File.separator+ fileName);
				BufferedReader bf = new BufferedReader(new InputStreamReader(is,Const.UTF8));
				p.load(bf);
				ret = p.getProperty(key).trim().toString();
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				LOG.error(e.getMessage());
				e.printStackTrace();
			}
		}
		return ret;
	}

}
