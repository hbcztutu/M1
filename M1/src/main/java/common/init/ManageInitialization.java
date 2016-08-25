
package common.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import common.log.HiLogger;


public class ManageInitialization implements ServletContextListener {
    
    
    private static HiLogger logger;

    protected static boolean iInitFlag = false;
    
    public static boolean getInitFlag() {
        return iInitFlag;
    }

    /**
     * UPS管理系统注销，一切从这里结果
     */
    public void contextDestroyed(ServletContextEvent arg0) {

    }

    /**
     * UPS管理系统初始化，一切从这里开始
     * @param arg0
     */
    public void contextInitialized(ServletContextEvent arg0) {
        
        try {
            //系统显示日志初始化
            SystemInitializationService.initHiLogger("manage");
            logger = HiLogger.getHiLogger(ManageInitialization.class);
            logger.info("==========start UPS  manage system initializtion =================");
            // 系统数据库相应配置初始化
            SystemInitializationService.initDBAccess();
            // 系统配置文件读取初始化
            SystemInitializationService.initSystemConfigurator("manage");
            //系统参数表初始化
            //SystemInitializationService.initParameter();
            //系统信息表初始化
            //SystemInitializationService.initMessage();
            // 系统XML工厂初始化
            //Init.initAll();
            // 银行前置客户端初始化
            //BankTxClient.initBankTxClient();
            //系统服务证书初始化
            //DataGramService.initSignKey();
            iInitFlag = true;
            logger.info("===============UPS manage sytem initialization complete================");
        } catch (Exception ex) {
            logger.error("UPS context Initialization error", ex);
        }finally {
        }
    }

}

