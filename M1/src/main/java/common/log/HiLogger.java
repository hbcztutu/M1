

package common.log;

import java.lang.reflect.Method;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * 系统日志类<br>
 * 系统日志初始化方式：<br>
 *  1. Save config file in file system.<br>
 *  2. Define "system.runtime.mode" system property. <br>
 *  3. Define "system.config.file.path" system property. <br>
 *  4. Log4j config file name: ["log4j_" + systemRuntimeMode + "_" + systemType + ".xml/.properties"]<br>
 *  5. Init Log4j: [DOMConfigurator.configure(configFilePath + configFileName)]<br>
 *                 [PropertyConfigurator.configure(configFilePath + configFileName)]<br>
 */
public class HiLogger {
	// Our fully qualified class name.
	private static final String THROWABLE_LOGGER_PRE = "rootThrowableLogger";
	private static String FQCN = HiLogger.class.getName();
	private static boolean JDK14 = false;
	
	static {
		String version = System.getProperty("java.version");
		if (version != null) {
			JDK14 = version.startsWith("1.4");
		}
	}
	
	private Logger logger;
	private Logger throwableLogger;
	
	private HiLogger(String name) {
		this.logger = Logger.getLogger(name);
		this.throwableLogger = Logger.getLogger(THROWABLE_LOGGER_PRE + "." + name);
	}
	
	private HiLogger(Class clazz) {
		this(clazz.getName());
	}
	
	/**
	 * 取得日志记录对象
	 * 
	 * @param name 在其中记录日志的类名
	 * 
	 * @return 日志记录对象
	 */
	public static HiLogger getHiLogger(String name){
		return new HiLogger(name);
	}
	
	/**
	 * 取得日志记录对象
	 * 
	 * @param name 在其中记录日志的类
	 * 
	 * @return 日志记录对象
	 */
	public static HiLogger getHiLogger(Class clazz){
		return new HiLogger(clazz);
	}

	public void debug(Object msg) {
		logger.log(FQCN, Level.DEBUG, msg, null);
	}
	
	public void debug(Object msg, Throwable t) {
		throwableLogger.log(FQCN, Level.DEBUG, msg, t);
		logNestedException(Level.DEBUG, msg, t);
	}
	
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}
	
	public void info(Object msg) {
		logger.log(FQCN, Level.INFO, msg, null);
	}
	
	public void info(Object msg, Throwable t) {
		throwableLogger.log(FQCN, Level.INFO, msg, t);
		logNestedException(Level.INFO, msg, t);
	}
	
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}
	
	public void warn(Object msg) {
		logger.log(FQCN, Level.WARN, msg, null);
	}
	
	public void warn(Object msg, Throwable t) {
		throwableLogger.log(FQCN, Level.WARN, msg, t);
		logNestedException(Level.WARN, msg, t);
	}
	
	public void error(Object msg) {
		logger.log(FQCN, Level.ERROR, msg, null);
	}
	
	public void error(Object msg, Throwable t) {
		throwableLogger.log(FQCN, Level.ERROR, msg, t);
		logNestedException(Level.ERROR, msg, t);
	}
	
	public void fatal(Object msg) {
		logger.log(FQCN, Level.FATAL, msg, null);
	}
	
	public void fatal(Object msg, Throwable t) {
		throwableLogger.log(FQCN, Level.FATAL, msg, t);
		logNestedException(Level.FATAL, msg, t);
	}
	
	void logNestedException(Level level, Object msg, Throwable t) {
		if (t == null){
			return;
		}

		try {
			Class tC = t.getClass();
			Method mA[] = tC.getMethods();
			Method nextThrowableMethod = null;
			for (int i = 0; i < mA.length; i++) {
				if (("getCause".equals(mA[i].getName()) && JDK14)
					|| "getRootCause".equals(mA[i].getName())
					|| "getNextException".equals(mA[i].getName())
					|| "getException".equals(mA[i].getName())) {
					// check param types
					Class params[] = mA[i].getParameterTypes();
					if (params == null || params.length == 0) {
						// just found the getter for the nested throwable
						nextThrowableMethod = mA[i];
						break; // no need to search further
					}
				}
			}
			
			if (nextThrowableMethod != null) {
				// get the nested throwable and log it
				Throwable next = (Throwable) nextThrowableMethod.invoke(t, new Object[0]);
				if (next != null) {
//					throwableLogger.log(FQCN, level, "Previous log CONTINUED", next);
					throwableLogger.log(FQCN, level, msg, next);
				}
			}
		} catch (Exception e) {
			// do nothing
		}
	}
}