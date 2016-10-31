package jdbi;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;
import org.skife.jdbi.v2.util.StringColumnMapper;
import org.h2.tools.Csv;
import org.h2.tools.SimpleResultSet;
import org.h2.jdbcx.JdbcConnectionPool;
import javax.sql.*;

/**
 * Created by jbares on 24.10.2016.
 */
public class DemoJDBI {
    public static void main(String[] args){
        DataSource ds = JdbcConnectionPool.create("jdbc:h2:mem:test",
                //"jdbc:h2:c:\Users\jbares\Documents\Github\cc16-java-intro\codecamp
                "jbares",
                "aaaa");
        DBI dbi = new DBI(ds);
        Handle h = dbi.open();
        h.execute("create table something (id int primary key, name varchar(100))");

        h.execute("insert into something (id, name) values (?, ?)", 1, "Brian");

        String name = h.createQuery("select name from something where id = :id")
                .bind("id", 1)
                .map(StringColumnMapper.INSTANCE)
                .first();

        System.out.println(name);

        h.close();
    }
}
