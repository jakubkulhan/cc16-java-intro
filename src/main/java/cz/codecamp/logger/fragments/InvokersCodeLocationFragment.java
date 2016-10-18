package cz.codecamp.logger.fragments;

public class InvokersCodeLocationFragment implements LogMessageFragment {

    private final String className;
    private final String methodName;
    private final int lineNumber;

    public InvokersCodeLocationFragment(String className, String methodName, int lineNumber) {
        this.className = className;
        this.methodName = methodName;
        this.lineNumber = lineNumber;
    }

    @Override
    public String getType() {
        return "invoked from";
    }

    @Override
    public String getValue() {
        return className + "#" + methodName + ":" + lineNumber;
    }

    @Override
    public String getPlainLineDisplayedText() {
        return "| " + getValue() + " |";
    }
}
