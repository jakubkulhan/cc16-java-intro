package cz.codecamp.logger;

/**
 * Created by honzapua on 16.10.2016.
 */
public class CalleeFormatter implements FormatterInterface {

    @Override
    public String format(LogLevelEnum level, String message) {
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        //vychozi hodnoty -1 je outofbound nelze
        String className = "<UNKNOWN>";
        int lineNo = -1;

        //i definovano vne cyklu abych vedel v jakem stavu opustil cycklus
        // 0 je Thread, 1 CaleeFormater, >2 jsou bud z logovaciho modulu nebo volajici
        int i;
        for (i = 2; i < stackTrace.length; i++) {
            //prevedeni nazvu tridy ze stringu na objekt Class
            Class<?> stackTraceElementClass = stackTraceElementToClass(stackTrace[i]);

            //preskocim vsechny tridy z logovaciho modulu
            if (stackTraceElementClass != null && !LoggerInterface.class.isAssignableFrom(stackTraceElementClass)) {
                //opoustim tridy na 1. volajici tride mimo modul, tim padem nedojde k inkrementaci i
                break;
            }
        }
        //kdyz naleznu volajicho tak ho pouziju
        if (i < stackTrace.length) {
            final StackTraceElement stackTraceElement = stackTrace[i];

            className = stackTraceElement.getClassName();
            lineNo = stackTraceElement.getLineNumber();
        }

        String result = String.format("[%s] [%s:%d] %s", level.name(), className, lineNo, message);

        return result;
    }

    private static Class<?> stackTraceElementToClass(StackTraceElement ste) {
        try {
            return Class.forName(ste.getClassName());
        } catch (ClassNotFoundException ex) {
            return null;
        }
    }

}
