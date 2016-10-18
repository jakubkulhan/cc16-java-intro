package cz.codecamp.logger.loggers;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dmachace on 16.10.2016.
 */
public class Logger implements LoggerInterface {

    private static final Properties props = Properties.INSTANCE;
    private static FileHandler fileHandler;
    private static final Date date = new Date();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(props.getDateInputFormat());


    public Logger() {
        fileHandler = new FileHandler();
    }


    private void addMessage(String content, String level, boolean stdOut) {
        String finalContent = "[" + level + "]  [" + dateFormat.format(date) + "] " + content;

        if (props.getLogCallerName().equals("Y")) {
            finalContent = "["  + (getCallerName() + "] ") + finalContent;
        }
        fileHandler.write(finalContent);
        if (stdOut) {
            System.out.println(finalContent);
        }
    }

    public void system(String content) {
        String finalContent = "[" + dateFormat.format(date) + "] " + content;
        if (props.getSystemLog().equals("Y")) {
            fileHandler.systemWrite(finalContent);
        }
        System.out.println(finalContent);
    }

    public void info(String content) {
        addMessage(content, props.getInfoLevel(), false);
    }

    public void info(String content, boolean stdOut) {
        addMessage(content, props.getInfoLevel(), stdOut);
    }

    public void debug(String content) {
        addMessage(content, props.getDebugLevel(), false);
    }

    public void debug(String content, boolean stdOut) {
        addMessage(content, props.getDebugLevel(), stdOut);
    }

    public void warning(String content) {
        addMessage(content, props.getWarningLevel(), false);
    }

    public void warning(String content, boolean stdOut) {
        addMessage(content, props.getWarningLevel(), stdOut);
    }

    public void error(String content) {
        addMessage(content, props.getErrorLevel(), false);
    }

    public void error(String content, boolean stdOut) {
        addMessage(content, props.getErrorLevel(), stdOut);
    }

    private String getCallerName() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String caller = stackTraceElements[4].toString().substring(0, stackTraceElements[4].toString().lastIndexOf("(")) +
                " - line" +
                stackTraceElements[4].toString().substring(stackTraceElements[4].toString().indexOf(":"), stackTraceElements[4].toString().lastIndexOf(")")) ;
        return caller;
    }

    public void log(LogLevelEnum levelEnum, String content) {
        switch (levelEnum){
            case INFO:
                addMessage(content, props.getInfoLevel(), false);
                break;
            case WARNING:
                addMessage(content, props.getWarningLevel(), false);
                break;
            case DEBUG:
                addMessage(content, props.getDebugLevel(), false);
                break;
            case ERROR:
                addMessage(content, props.getErrorLevel(), false);
                break;
        }
    }

    public void log(String level, String content) {
        switch (level){
            case "i":
                addMessage(content, props.getInfoLevel(), false);
                break;
            case "w":
                addMessage(content, props.getWarningLevel(), false);
                break;
            case "d":
                addMessage(content, props.getDebugLevel(), false);
                break;
            case "e":
                addMessage(content, props.getErrorLevel(), false);
                break;
        }
    }

}
