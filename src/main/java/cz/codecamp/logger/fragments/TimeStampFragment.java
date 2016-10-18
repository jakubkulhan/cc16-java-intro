package cz.codecamp.logger.fragments;

public class TimeStampFragment implements LogMessageFragment {

    private final String timestamp;

    public TimeStampFragment(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String getType() {
        return "ts";
    }

    @Override
    public String getValue() {
        return timestamp;
    }

    @Override
    public String getPlainLineDisplayedText() {
        return "(" + timestamp + ") ";
    }
}
