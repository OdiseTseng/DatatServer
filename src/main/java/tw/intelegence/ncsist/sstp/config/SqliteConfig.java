package tw.intelegence.ncsist.sstp.config;

//import jakarta.activation.DataSource;
//import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;

import java.sql.SQLException;

@Configuration
@EnableAutoConfiguration
@PropertySource("classpath:persistence.properties")
public class SqliteConfig {

    private static Logger logger = LoggerFactory.getLogger(SqliteConfig.class);

    @Value("${remote.sqlite.url}")
    private String dataSourceUrl;
    @Value("${local.sqlite.init}")
    private String initDBs;
    @Autowired
    private ResourceLoader resourceLoader;

    @Bean(name= "sqliteDataSource")
    public DataSource sqliteDataSource(){
        //建立資料夾
        SqliteUtils.initSqliteFile(SqliteUtils.getFilePath(dataSourceUrl));

        //建立DB
        DataSource dataSource  = SqliteBuilder.create().url(dataSourceUrl).build();

        String key = "";

        String[] dbs = initDBs.split("-");

        try {
            //嘗試初始畫資料庫，資料表不存在時創建。
            //嘗試初始化DB，資料表不存在時建立。
//            SqliteUtils.initUserDb(dataSource.getConnection());
//            key = "User";
//            SqliteUtils.initCommDb(dataSource.getConnection(), key);
//            key = "Course";
//            SqliteUtils.initCommDb(dataSource.getConnection(), key);
//            key = "Unit";
//            SqliteUtils.initCommDb(dataSource.getConnection(), key);
//            key = "Content";
//            SqliteUtils.initCommDb(dataSource.getConnection(), key);
//            key = "Quiz";
//            SqliteUtils.initCommDb(dataSource.getConnection(), key);
//            key = "Tip";
//            SqliteUtils.initCommDb(dataSource.getConnection(), key);
//            key = "Attendance";
//            SqliteUtils.initCommDb(dataSource.getConnection(), key);
//            key = "QuestionAnswer";
//            SqliteUtils.initCommDb(dataSource.getConnection(), key);
            for (String db: dbs){
                key = db;
                SqliteUtils.initCommDb(dataSource.getConnection(), key, resourceLoader);
            }


        } catch (SQLException e) {
//            e.printStackTrace();
            logger.debug("SQLException : " + key + " ; " +  e.getMessage());

        }
        return dataSource;

    }

}