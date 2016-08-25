

package common.systemUtil;

import java.util.MissingResourceException;

/**
 * 系统环境信息获取工具类
 */
public class SystemEnvUtil {
	/** 系统运行模式 -  单元测试环境（代码） */
	public final static String RUNTIME_MODE_UNIT_TEST  = "unitest";
    
	/** 系统运行模式 -  开发环境（代码） */
	public final static String RUNTIME_MODE_DEVELOP    = "develop";
    
	/** 系统运行模式 -  集成测试环境（代码） */
	public final static String RUNTIME_MODE_INT_TEST   = "inttest";
    
	/** 系统运行模式 -  性能测试环境（代码） */
	public final static String RUNTIME_MODE_PER_TEST   = "pertest"; 
    
	/** 系统运行模式 -  生产测试环境（代码） */
	public final static String RUNTIME_MODE_PRO_TEST   = "protest";
    
	/** 系统运行模式 -  生产环境（代码） */
	public final static String RUNTIME_MODE_PRODUCTION = "product";
	
	private static String iSystemRuntimeMode = null;

	/**
	 * 取得系统运行模式
	 * 
	 * 在系统环境量中配置[系统运行模式变量]：system.runtime.mode<br>
	 * 如果有多个应用程序部署在同一个应用服务器，而且使用相同的方式读取配置文件，那么，应使用相同的运行模式.<br>
	 * 
	 * 如果多个系统使用不同的运行模式，请部署在各自独立的应用服务器中。
	 * 
	 * @return String 系统运行模式
	 */
	public static String getSystemRuntimeMode(){
		if(null != iSystemRuntimeMode){
			return iSystemRuntimeMode;
		}
		
		//1 取得系统运行模式
		String systemRuntimeMode = System.getProperty("system.runtime.mode");
		//1.1 验证系统运行模式
		if (systemRuntimeMode == null)
			throw new MissingResourceException("Not set [system.runtime.mode], please config it in [server->java->procedure->JVM->custom attribute].", SystemEnvUtil.class.getName(), "");
		if (!systemRuntimeMode.equalsIgnoreCase(RUNTIME_MODE_UNIT_TEST))
			if (!systemRuntimeMode.equalsIgnoreCase(RUNTIME_MODE_DEVELOP))
				if (!systemRuntimeMode.equalsIgnoreCase(RUNTIME_MODE_INT_TEST))
					if (!systemRuntimeMode.equalsIgnoreCase(RUNTIME_MODE_PER_TEST))
						if (!systemRuntimeMode.equalsIgnoreCase(RUNTIME_MODE_PRO_TEST))
							if (!systemRuntimeMode.equalsIgnoreCase(RUNTIME_MODE_PRODUCTION))
								throw new MissingResourceException("Invalid [system.runtime.mode] setting [" + systemRuntimeMode + "]", SystemEnvUtil.class.getName(), "system.runtime.mode");
		iSystemRuntimeMode = systemRuntimeMode.toLowerCase();
		
		//2 返回系统运行模式
		return iSystemRuntimeMode;
	}
	
	/**
	 * 取得系统配置文件路径<br>
	 * 
	 * 在系统环境变量中配置[配置文件路径变量]：system.config.file.path<br>
	 * 如果有多个应用程序部署在同一个应用服务器，如果都是用外部文件系统的配置文件，<br>
	 * 而且使用本common包定义的方式读取配置文件，那么，配置文件存放的路径应相同.<br>
	 * 
	 * @return systemConfigFilePath which end with "/"
	 */
	public static String getSystemConfigFilePath(){
		//1 取得系统配置文件路径
		String configFilePath = System.getProperty("system.config.file.path");
		//1.1 验证系统配置文件路径
		if (configFilePath == null)
			throw new MissingResourceException("Not set [system.config.file.path], please config it in [server->java->procedure->JVM->custom attribute].", SystemEnvUtil.class.getName(), "");

		if(configFilePath.endsWith("\\") || configFilePath.endsWith("/")){
			configFilePath = configFilePath.substring(0, configFilePath.length() - 1);	
		}
		configFilePath = configFilePath + "/";

		return configFilePath;
	}
	
	
}



