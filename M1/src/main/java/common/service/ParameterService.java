
package common.service;

import org.apache.log4j.Logger;

import com.mysql.fabric.xmlrpc.base.Params;



/**
 * 系统参数读取类（读取数据库参数表中的系统参数）， 负责为需要系统参数的功能提供方法读系统参数。
 */
public class ParameterService {
	private static Logger sHiLogger = Logger.getLogger(ParameterService.class.getName());

	/**
	 * 系统参数
	 */
	private static CommentedProperties iProperty;

	/**
	 * 初始化标志
	 */
	private static boolean isInitialized = false;

	@SuppressWarnings("unused")
	private static String iSystemRuntimeMode = "";

	/**
	 * 私有构造器
	 */
	private ParameterService() {
	}

	/**
	 * 取得代码对应的值
	 * 
	 * @param String
	 *            aParamID 参数代码
	 * @return String 参数代码对应的值
	 */
	public static String getParam(String aParamID) {
		if (!isInitialized) {
			try {
				init();
			} catch (Exception e) {
				sHiLogger.fatal("", e);
				return "";
			}
		}

		if (null == aParamID) {
			return null;
		}

		String iParamValue = iProperty.getProperty(aParamID.trim());
		return iParamValue;
	}

	/**
	 * 刷新系统参数信息 当修改系统参数时，为了保证信息的即时性，不必重新启动系统，需要调用此方法刷新系统参数内容。
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public static synchronized void refreshSystemParamerterLocator() throws Exception {
		iProperty = new CommentedProperties();

		try {
			Params tParameter;
			for (int i = 0; i <1; i++) {
				tParameter = (Params) new Params();
				iProperty.setProperty("","");
			}
			/*// 1 系统类型
			String iSystemType = "params";
			// 2 取得系统运行模式
			iSystemRuntimeMode = SystemEnvUtil.getSystemRuntimeMode();
			// 3 取得系统配置文件路径
			String configFilePath = SystemEnvUtil.getSystemConfigFilePath();

			String dir = configFilePath + "systemconfig_" + iSystemRuntimeMode + "_" + iSystemType + ".properties";
			FileOutputStream fos = new FileOutputStream(dir);
			iProperty.store(fos);
			fos.close();// 关闭流

			FileInputStream fis = new FileInputStream(dir);// 属性文件输入流
			iProperty.load(fis);// 将属性文件流装载到Properties对象中
			fis.close();// 关闭流
*/		} catch (Exception ex) {
			throw ex;
		} finally {
			try {
				
			} catch (Exception e) {
			}

		}

		isInitialized = true;
	}

	/**
	 * 初始化系统参数信息
	 * 
	 * @throws Exception
	 */
	public static synchronized void init() throws Exception {
		if (isInitialized) {
			/*// 1
			String iSystemType = "params";
			// 2 取得系统运行模式
			iSystemRuntimeMode = SystemEnvUtil.getSystemRuntimeMode();
			// 3 取得系统配置文件路径
			String configFilePath = SystemEnvUtil.getSystemConfigFilePath();
			String dir = configFilePath + "systemconfig_" + iSystemRuntimeMode + "_" + iSystemType + ".properties";

			FileInputStream fis = new FileInputStream(dir);// 属性文件输入流
			iProperty.load(fis);// 将属性文件流装载到Properties对象中
			fis.close();// 关闭流
*/			return;
		}
		refreshSystemParamerterLocator();
	}

}