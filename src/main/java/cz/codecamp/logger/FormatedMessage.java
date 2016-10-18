package cz.codecamp.logger;

public class FormatedMessage {

    private LogLevelEnum lvl;
    long ts;
    String msg;

    public FormatedMessage(LogLevelEnum lvl, long ts, String msg){
        this.lvl = lvl;
        this.ts = ts;
        this.msg = msg;
    }



}
