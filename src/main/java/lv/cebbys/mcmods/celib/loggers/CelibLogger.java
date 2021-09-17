package lv.cebbys.mcmods.celib.loggers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.apache.logging.log4j.Level.ERROR;
import static org.apache.logging.log4j.Level.INFO;
import static org.apache.logging.log4j.Level.WARN;

public class CelibLogger {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void info(Object msg) {
        logMessage(INFO, msg);
    }

    public static void info(String format, Object... elements) {
        logMessage(INFO, String.format(format, elements));
    }

    public static void info(Throwable t, Object msg) {
        logThrowable(INFO, msg, t);
    }

    public static void info(Throwable t, String format, Object... elements) {
        logThrowable(INFO, String.format(format, elements), t);
    }

    public static void error(Object msg) {
        logMessage(ERROR, msg);
    }

    public static void error(String format, Object... elements) {
        logMessage(ERROR, String.format(format, elements));
    }

    public static void error(Throwable t, Object msg) {
        logThrowable(ERROR, msg, t);
    }

    public static void error(Throwable t, String format, Object... elements) {
        logThrowable(ERROR, String.format(format, elements), t);
    }

    public static void warn(Object msg) {
        logMessage(WARN, msg);
    }

    public static void warn(String format, Object... elements) {
        logMessage(WARN, String.format(format, elements));
    }

    public static void warn(Throwable t, Object msg) {
        logThrowable(WARN, msg, t);
    }

    public static void warn(Throwable t, String format, Object... elements) {
        logThrowable(WARN, String.format(format, elements), t);
    }

    private static void logMessage(Level level, Object msg) {
        String formatted = formatMessage(msg);
        LOGGER.log(level, formatted);
    }

    private static void logThrowable(Level level, Object msg, Throwable throwable) {
        String t = "";
        try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            pw.flush();
            t = sw.toString();
        } catch (IOException ignored) {
        }
        String formatted = formatMessage(msg) + "\n" + t;
        LOGGER.log(level, formatted);
    }

    private static String formatMessage(Object msg) {
        StackTraceElement trace = Thread.currentThread().getStackTrace()[4];
        String className = trace.getClassName();
        className = className.substring(className.lastIndexOf(".") + 1);
        String methodName = trace.getMethodName();
        if(methodName.startsWith("redirect$")) {
            methodName = methodName.substring(methodName.lastIndexOf("$") + 1);
        }
        if(methodName.equals("<init>")) methodName = "constructor";
        return String.format("[%s.%s]: %s", className, methodName, msg);
    }
}
