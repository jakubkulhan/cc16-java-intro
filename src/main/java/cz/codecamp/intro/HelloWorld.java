package cz.codecamp.intro;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.loggers.StdoutLogger;

public class HelloWorld {
    static StdoutLogger stdoutLogger = new StdoutLogger();
    public static void main(String[] args) {
        System.out.println("hello, codecamp!");
        stdoutLogger.debug("pls");
    }
}
