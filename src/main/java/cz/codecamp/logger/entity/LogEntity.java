package cz.codecamp.logger.entity;

import cz.codecamp.logger.LogLevelEnum;

/**
 * Created by vkorecky on 7.10.16.
 */
public class LogEntity {
    private LogLevelEnum lvl;
    private String ts;
    private String msg;

    public LogEntity() {
    }

    public LogEntity(LogLevelEnum lvl, String ts, String msg) {
        this.lvl = lvl;
        this.ts = ts;
        this.msg = msg;
    }

    public LogLevelEnum getLvl() {
        return lvl;
    }

    public void setLvl(LogLevelEnum lvl) {
        this.lvl = lvl;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
