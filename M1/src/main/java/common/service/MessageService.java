
package common.service;

import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * 代码－信息映射。单例模式设计。该类负责从数据库中读取代码对应的信息，
 * 代码包含错误代码和成功代码。
 */
public class MessageService {
	private static Logger sHiLogger = Logger.getLogger(MessageService.class);
	private static MessageService uniqueInstanceOf_MessageService = null;
	private static Properties iProperty = new Properties();
	@SuppressWarnings("unused")
	private static boolean isInitialized = false;

	/**
	 * 私有构造器，从数据库中取得所有的代码及其对应的信息，并添加到Properties中
	 * 初始化系统变量
	 * @throws Exception
	 */
	private MessageService() throws Exception {
		sHiLogger.info("MessageService");
		/*
		try {
			Message tMessage;
			List tList = new MessageDAO().findAll();
			for (int i = 0; i < tList.size(); i++) {
				tMessage = (Message) tList.get(i);
				iProperty.setProperty(tMessage.getMsgcode().trim(), tMessage.getMsgtext());
			}
		} catch (Exception ex) {
			sHiLogger.error("",ex);
			throw ex;
		} finally {
			try {
				_BaseRootDAO.closeCurrentSession();
			} catch (Exception e) {
			}

		}

	*/}

	/**
	 * 取得代码对应的信息
	 * @param code 信息代码
	 * @return String 信息代码对应的中文描述
	 */
	public static String getMessage(String code) {
		String iInfo = iProperty.getProperty(code.trim());
		return iInfo;
	}

	/**
	 * 初始化信息服务类
	 * @throws Exception
	 */
	private static synchronized void init() throws Exception {
		if (uniqueInstanceOf_MessageService == null) {
			uniqueInstanceOf_MessageService = new MessageService();
			isInitialized = true;
		}
	}

	/**
	 * 取得信息服务类的实例　
	 * @return
	 * @throws Exception
	 */
	public static MessageService getUniqueInstance() throws Exception {
		if (uniqueInstanceOf_MessageService == null) {
			init();
		}
		return uniqueInstanceOf_MessageService;
	}

}
