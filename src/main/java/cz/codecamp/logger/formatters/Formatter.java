package cz.codecamp.logger.formatters;

import java.util.NoSuchElementException;

public abstract class Formatter {

    private String getTopDomainPackageName(String fullClassName) {
        if ((null == fullClassName) || ("".equals(fullClassName)))
            return "";

        int firstDot = fullClassName.indexOf('.');
        if ( firstDot < 0) {
            return fullClassName;
        }

       return fullClassName.substring(0,  firstDot);
    }

    protected StackTraceElement findCallerClass() {

        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        int callerIndex = 1;

        while (callerIndex < ste.length) {

            String packageName = getTopDomainPackageName(ste[callerIndex].getClassName());
            String nextPackageName = packageName;
            if (callerIndex+1 < ste.length) {
                nextPackageName = getTopDomainPackageName(ste[callerIndex+1].getClassName());
            }

            if (!packageName.equals("cz") && !nextPackageName.equals("cz"))
                break;

            callerIndex++;
        }
        callerIndex--; // decrement because last "cz" package is needed

        if (callerIndex == ste.length) {
            throw new NoSuchElementException();
        }

        return ste[callerIndex];
    }

}
