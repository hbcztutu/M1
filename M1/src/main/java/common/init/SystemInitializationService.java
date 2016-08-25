package common.init;

import org.apache.log4j.Logger;

import common.log.HiLoggerInitializer;
import common.service.MessageService;
import common.service.ParameterService;
import common.systemUtil.SystemConfigurator;

/**
 * 系统初始化服务类
 * 
 * 调用此类中的初始化方法进行相关资料的初始化。
 * 初始化要求：应首先初始化Log4j，然后是系统参数配置文件，然后是数据库，最后再初始化其它数据。
 */
public class SystemInitializationService {
	private static Logger sHiLogger = null;
	
	/**
	 * 初始化日志处理类HiLogger
	 * 
	 * @param systemType
	 */
	public static void initHiLogger(String systemType){
		HiLoggerInitializer.initHiLogger(systemType);
		sHiLogger = Logger.getLogger(SystemInitializationService.class);
		sHiLogger.info("Initialize [HiLogger] ............[OK!]");
	}
	
	/**
	 * 初始化系统参数配置文件（配置文件）
	 * 
	 * @param systemType 
	 */
	public static void initSystemConfigurator(String systemType) throws Exception{
		sHiLogger.info("Initialize [SystemConfigurator] ............[BEGIN]");
		SystemConfigurator.getInstance(systemType);
		sHiLogger.info("Initialize [SystemConfigurator] ............[OK!]");
	}

	/**
	 * 初始化数据库连接(可以指定系统类型，读取应用系统自己的Hibernate配置文件)
	 */
	public static void initDBAccess(String systemType){
		sHiLogger.info("Initialize [DataBase Connection] with system type ............[BEGIN]");
		//_BaseRootDAO.init(systemType);
		sHiLogger.info("Initialize [DataBase Connection] with system type ............[OK!]");
	}
	
    /**
     * 初始化数据库连接（读取所有系统共用的Hibernate配置文件）
     */
    public static void initDBAccess(){
        sHiLogger.info("Initialize [DataBase Connection] without system type ............[BEGIN]");
        //_BaseRootDAO.init();
        sHiLogger.info("Initialize [DataBase Connection] without system type ............[OK!]");
    }

	/**
	 * 初始化系统信息表
	 * 
	 * @throws Exception
	 */
	public static void initMessage() throws Exception{
		sHiLogger.info("Initialize [Message] ............[BEGIN]");
		MessageService.getUniqueInstance();
		sHiLogger.info("Initialize [Message] ............[OK!]");
	}
	
	/**
	 * 初始化系统参数表（数据库表）
	 * 
	 * @throws Exception
	 */
	public static void initParameter() throws Exception{
		sHiLogger.info("Initialize [Parameter] ............[BEGIN]");
		ParameterService.init();
		sHiLogger.info("Initialize [Parameter] ............[OK!]");
	}
}
