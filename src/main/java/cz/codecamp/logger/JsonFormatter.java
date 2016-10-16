package cz.codecamp.logger;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by honzapua on 16.10.2016.
 */
public class JsonFormatter implements FormatterInterface {

    @Override
    public String format(LogLevelEnum level, String message) {
        Gson gson = new Gson();

        LogDTO dto = new LogDTO();

        dto.setLvl(level.name());
        dto.setTs(new Date().getTime());
        dto.setMsg(message);

        String json = gson.toJson(dto);

        return json;
    }

    class LogDTO implements Serializable {

        private static final long serialVersionUID = -1;

        private String lvl;

        private long ts;

        private String msg;

        public String getLvl() {
            return lvl;
        }

        public void setLvl(String lvl) {
            this.lvl = lvl;
        }

        public long getTs() {
            return ts;
        }

        public void setTs(long ts) {
            this.ts = ts;
        }


        public String getMsg() {
            return msg;
        }


        public void setMsg(String msg) {
            this.msg = msg;
        }

    }

}

