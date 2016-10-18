package cz.codecamp.logger.decorators;

import cz.codecamp.logger.LogMessage;
import cz.codecamp.logger.fragments.InvokersCodeLocationFragment;

import java.util.List;
import java.util.function.Function;

import static java.util.Arrays.asList;

public class InvokersCodeLocationDecorator implements Function<LogMessage, LogMessage> {

    private static final List<String> PACKAGE_BLACK_LIST = asList("java.", "cz.codecamp.logger", "org.codehaus.groovy");


    @Override
    public LogMessage apply(LogMessage logMessage) {
        StackTraceElement e = retrieveCodeLocation();
        return logMessage.addFragment(new InvokersCodeLocationFragment(e.getClassName(), e.getMethodName(), e.getLineNumber()));
    }

    private StackTraceElement retrieveCodeLocation() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return findFirstClassNotInBlackList(stackTrace);
    }

    private StackTraceElement findFirstClassNotInBlackList(StackTraceElement[] stackTrace) {
        for (StackTraceElement e : stackTrace) {
            if (classNotInBlacklist(e.getClassName())) {
                return e;
            }
        }
        throw new IllegalStateException("Using Logger from the blacklisted package.");
    }

    private boolean classNotInBlacklist(String className) {
        return PACKAGE_BLACK_LIST.stream().noneMatch((String blacklistedPackage) -> className.startsWith(blacklistedPackage));
    }
}
