

package common.systemUtil;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import org.apache.log4j.Logger;

/**
 * 系统参数解析类，单例模式设计，从配置文件中读取配置参数
 */
public class SystemConfigurator {
	/** 系统运行模式（代码） */
	private static String iSystemRuntimeMode = "";

	/** 系统类型 */
	private static String iSystemType = null;

	/** 公共配置资源 */
	private static Properties iCommProperty = null;

	/** 应用系统配置资源 */
	private static Properties iAppSystemProperty = null;

	private static Logger logger = Logger.getLogger(SystemConfigurator.class);
	private static boolean iInitialed = false;

	private static SystemConfigurator sUniqueInstanceOfSystemConfigurator =
		null;

	/**
	 * 默认构造方法
	 * @param aSystemType 系统类型。由项目自行定义
	 * @throws java.util.MissingResourceException
	 */
	private SystemConfigurator(String systemType)
		throws Exception {
		//1 验证系统类型
		if (systemType == null)
			throw new MissingResourceException(
				"Invalid system type.",
				SystemConfigurator.class.getName(),
				"");
		iSystemType = systemType;

		//2 取得系统运行模式
		iSystemRuntimeMode = SystemEnvUtil.getSystemRuntimeMode();
		
		//3 取得系统配置文件路径
		String configFilePath = SystemEnvUtil.getSystemConfigFilePath();

		//4 绑定系统参数文件
		//4.1 绑定公共配置资源文件
		
		iCommProperty = new Properties();
		iCommProperty.load(new FileInputStream(new File(configFilePath + 
		"systemconfig_" + iSystemRuntimeMode + "_common.properties")));
		//4.2 绑定应用系统配置资源文件
		iAppSystemProperty = new Properties();
		iAppSystemProperty.load(new FileInputStream(new File(configFilePath +
				"systemconfig_" + iSystemRuntimeMode + "_" + iSystemType + ".properties")));
		logger.info("**********************SystemConfigurator*******************************");
		logger.info("1、绑定应用系统配置资源文件"+configFilePath +
				"systemconfig_" + iSystemRuntimeMode + "_" + iSystemType + ".properties");
		iInitialed = true;
	}


    /**
     * 默认构造方法(For 客户端)
     * 
     * @throws java.util.MissingResourceException
     */
    private SystemConfigurator()
        throws Exception {
        //取得系统配置文件路径
        String configFilePath = SystemEnvUtil.getSystemConfigFilePath();

        //绑定系统参数文件
        ////绑定公共配置资源文件
        
        iCommProperty = new Properties();
        ////绑定应用系统配置资源文件
        iAppSystemProperty = new Properties();
        iAppSystemProperty.load(new FileInputStream(new File(configFilePath + "systemconfig_ups_client.properties")));
        
        logger.info("2、绑定应用系统配置资源文件"+configFilePath +"systemconfig_ups_client.properties");
        iInitialed = true;
    }

	/**
	 * 取得指定参数名称的参数值
	 * @param aParamName
	 * @return java.lang.String
	 *
	 * @throws java.util.MissingResourceException
	 */
	public static String getParam(String aParamName)
		throws MissingResourceException {
		if (!iInitialed) {
			throw new RuntimeException("[System Configurator parser class not initialized!");
		}

		String tValue = null;
		//1、首先在公共配置文档中取
		tValue = iCommProperty.getProperty(aParamName);

		//2、如果在公共配置文档中没有指定的参数，则继续读取应用系统配置文档
		if (null == tValue) {
			tValue = iAppSystemProperty.getProperty(aParamName);
		}
		logger.info("SystemConfigurator.aParamName="+aParamName);
		logger.info("SystemConfigurator.tValue="+tValue);
		logger.info("*************************SystemConfigurator****************************");
		return tValue;
	}

	/**
	 * 取得系统参数解析类的实例
	 * @return 系统参数解析类的实例
	 */
	public static synchronized SystemConfigurator getInstance(String systemType) throws Exception {
		if (sUniqueInstanceOfSystemConfigurator == null)
			sUniqueInstanceOfSystemConfigurator =
				new SystemConfigurator(systemType);

		return sUniqueInstanceOfSystemConfigurator;
	}

    /**
     * 取得系统参数解析类的实例(For 客户端)
     * @return 系统参数解析类的实例
     */
    public static synchronized SystemConfigurator getInstanceForClient() throws Exception {
        if (sUniqueInstanceOfSystemConfigurator == null) {
            sUniqueInstanceOfSystemConfigurator = new SystemConfigurator();
        }
           
        return sUniqueInstanceOfSystemConfigurator;
    }

	/**
	 * 系统运行模式
	 * @return 系统运行模式
	 */
	public static String getSystemRuntimeMode() {
		return iSystemRuntimeMode;
	}
}
