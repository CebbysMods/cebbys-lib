package lv.cebbys.celib.loggers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.apache.logging.log4j.Level.*;

public class CelibLogger {
	private static final Logger LOGGER = LogManager.getLogger();

	@Deprecated
	public static void flog(String pattern, Object... parts) {
		CelibLogger.format(INFO, name(), String.format(pattern, parts));
	}
	
	public static void log(String msg) {
		CelibLogger.format(INFO, name(), msg);
	}
	public static void log(String pattern, Object... parts) {
		CelibLogger.format(INFO, name(), String.format(pattern, parts));
	}

	public static void fwarn(String pattern, Object... parts) {
		CelibLogger.format(WARN, name(), String.format(pattern, parts));
	}
	
	public static void warn(String msg) {
		CelibLogger.format(WARN, name(), msg);
	}
	
	@Deprecated
	public static void ferror(String pattern, Object... parts) {
		CelibLogger.format(ERROR, name(), String.format(pattern, parts));
	}
	
	public static void error(Object msg) {
		CelibLogger.format(ERROR, name(), msg.toString());
	}
	public static void error(String pattern, Object... parts) {
		CelibLogger.format(ERROR, name(), String.format(pattern, parts));
	}
	
	private static String name() {
		String name = new Exception().getStackTrace()[2].getClassName();
		return name.substring(name.lastIndexOf('.') + 1);
	}
	
	private static void format(Level level, String name, String msg) {
		CelibLogger.finalForm(level, String.format("(%s): %s", name, msg));
	}
	
	private static void finalForm(Level level, String msg) {
		LOGGER.log(level, msg);
	}
	
	@Deprecated
	/**
	 * Use log(String msg) or flog(String format, Object... args) instead
	 */
	public static void log(String v0, String v1) {
	}

	@Deprecated
	/**
	 * Use warn(String msg) or fwarn(String format, Object... args) instead
	 */
	public static void warn(String v0, String v1) {
	}
	
	@Deprecated
	/**
	 * Use error(String msg) or ferror(String format, Object... args) instead
	 */
	public static void error(String v0, String v1) {
	}
}
