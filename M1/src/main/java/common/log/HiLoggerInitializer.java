

package common.log;

import java.util.MissingResourceException;

import org.apache.log4j.xml.DOMConfigurator;

import common.systemUtil.SystemEnvUtil;

/**
 * 系统日志初始化类
 */
public class HiLoggerInitializer {
	/**
	 * 初始化系统日志环境
	 * 
	 * @param systemType 系统类型（由应用系统定义，配置文件要以此命名）
	 */
	public static void initHiLogger(String systemType){
		//1 验证系统类型
		if (systemType == null)
			throw new MissingResourceException("Invalid system type.", HiLoggerInitializer.class.getName(), "");
		  
		//2 取得系统运行模式
		String systemRuntimeMode = SystemEnvUtil.getSystemRuntimeMode();
		
		//3 生成Log4j配置文件名称
		String configFileName = "log4j_" + systemRuntimeMode + "_" + systemType + ".xml";

		//4 取得系统配置文件路径()
		String configFilePath = SystemEnvUtil.getSystemConfigFilePath();
		
		//5 初始化HiLogger
		//5.1 XML格式的配置文件
		DOMConfigurator.configure(configFilePath + configFileName);
		//5.2 Properties格式的配置文件
		//PropertyConfigurator.configure(configFilePath + configFileName);
	}

    /**
     * 初始化系统日志环境(For 客户端)
     * 
     */
    public static void initHiLoggerForClient(){
        //Log4j配置文件名称
        String configFileName = "log4j_ups_client.xml";

        //取得系统配置文件路径()
        String configFilePath = SystemEnvUtil.getSystemConfigFilePath();
        
        //初始化HiLogger
        ////XML格式的配置文件
        DOMConfigurator.configure(configFilePath + configFileName);
    }

}
