package tw.intelegence.ncsist.sstp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

public class SqliteUtils {

    private static Logger logger = LoggerFactory.getLogger(SqliteUtils.class);

//    SqliteUtils(){
//        resourceLoader;
//    }

    /**
     * 初始化项目db
     * @param connection
     */
    public static void initUserDb(Connection connection){
        //判斷TABLE是否存在
        boolean hasPro = false;
        try {
            hasPro = true;
            //測試TABLE是否存在
            connection.prepareStatement("select * from user").execute();
        }catch (SQLException e){
            //不存在
            logger.debug("table user is not exist");
            System.out.println("SQLException : " + e.getMessage());
            hasPro = false;
        }
        //不存在時創建DB
        if(!hasPro) {
            logger.debug(">>>start init test db");
            File file = null;
            try {
                //讀取初始畫數據SQL
                file = ResourceUtils.getFile("classpath:sql/initUser.sql");
            } catch (FileNotFoundException e) {
//                e.printStackTrace();
                logger.debug("table : " + e.getMessage());
                System.out.println("FileNotFoundException : " + e.getMessage());
            }

            //獲取SQL
            String sql = "";
            FileInputStream fis = null;
            InputStreamReader isr = null;
            try {
                fis = new FileInputStream(file);
                isr = new InputStreamReader(fis, "UTF-8");
                BufferedReader bf = new BufferedReader(isr);
                String content = "";
                StringBuilder sb = new StringBuilder();
                while (content != null) {
                    content = bf.readLine();
                    if (content == null) {
                        break;
                    }
                    sb.append(content.trim());
                }
                sql = sb.toString();
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
//                e.printStackTrace();
                logger.debug("FileNotFoundException / UnsupportedEncodingException : " + e.getMessage());
                System.out.println("FileNotFoundException / UnsupportedEncodingException : " + e.getMessage());
            } catch (IOException e) {
//                e.printStackTrace();
                logger.debug("IOException : " + e.getMessage());
                System.out.println("IOException : " + e.getMessage());
            } finally {
                try {
                    isr.close();
                    fis.close();
                } catch (IOException e) {
//                    e.printStackTrace();
                    logger.debug("IOException : " + e.getMessage());
                    System.out.println("IOException : " + e.getMessage());
                }
            }
            //分割SQL
            String[] sqls = sql.split(";");

            try {
                for (String str : sqls) {
                    //開始初始化DB
                    connection.setAutoCommit(false);
                    connection.prepareStatement(str).execute();
                }
                //提交sql
                connection.commit();
            } catch (SQLException e) {
//                e.printStackTrace();
                logger.debug("SQLException : " + e.getMessage());
                System.out.println("SQLException : " + e.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
//                    e.printStackTrace();
                    logger.debug("SQLException : " + e.getMessage());
                    System.out.println("SQLException : " + e.getMessage());
                }
            }
            logger.debug("finish init test db>>>");
        }else {
            logger.debug("test db is exist");
        }
    }

    /**
     * 初始化项目db
     * @param connection
     */
    public static void initCommDb(Connection connection, String sqlKey, ResourceLoader resourceLoader){
        System.out.println("initCommDb : sqlKey;; " + sqlKey);
        //判斷TABLE是否存在
        boolean hasTable = false;
        try {
            hasTable = true;
            //測試TABLE是否存在
            connection.prepareStatement("select * from " + sqlKey.toLowerCase()).execute();
        }catch (SQLException e){
            //不存在
            logger.debug("table " + sqlKey.toLowerCase() + " is not exist");
            System.out.println("SQLException : " + e.getMessage());
            hasTable = false;
        }
        //不存在時創建DB
        if(!hasTable) {
            logger.debug(">>>start init test db");
            File file = null;
            try {
                //讀取初始畫數據SQL
//                ResourceUtils.get
                Resource resource = resourceLoader.getResource("classpath:sql/init" + sqlKey + ".sql");
                file = resource.getFile();
//                file = ResourceUtils.getFile("classpath:sql/init" + sqlKey + ".sql");

            } catch (FileNotFoundException e) {
//                e.printStackTrace();
                logger.debug("table : " + e.getMessage());
                System.out.println("FileNotFoundException : " + e.getMessage());
            } catch (IOException e) {
                System.out.println("IOException : " + e.getMessage());
                throw new RuntimeException(e);
            }


            System.out.println("file : " + file);

            if(file != null){
                System.out.println("file path: " + file.getPath());
            }

            //獲取SQL
            String sql = "";
            FileInputStream fis = null;
            InputStreamReader isr = null;
            try {
                fis = new FileInputStream(file);
                isr = new InputStreamReader(fis, "UTF-8");
                BufferedReader bf = new BufferedReader(isr);
                String content = "";
                StringBuilder sb = new StringBuilder();
                while (content != null) {
                    content = bf.readLine();
                    if (content == null) {
                        break;
                    }
                    sb.append(content.trim());
                }
                sql = sb.toString();
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
//                e.printStackTrace();
                logger.debug("FileNotFoundException / UnsupportedEncodingException : " + e.getMessage());
                System.out.println("FileNotFoundException / UnsupportedEncodingException : " + e.getMessage());
            } catch (IOException e) {
//                e.printStackTrace();
                logger.debug("IOException : " + e.getMessage());
                System.out.println("IOException : " + e.getMessage());
            } finally {
                try {
                    isr.close();
                    fis.close();
                } catch (IOException e) {
//                    e.printStackTrace();
                    logger.debug("IOException : " + e.getMessage());
                    System.out.println("IOException : " + e.getMessage());
                }
            }
            //分割SQL
            String[] sqls = sql.split(";");

            try {
                for (String str : sqls) {
                    //開始初始化DB
                    connection.setAutoCommit(false);
                    connection.prepareStatement(str).execute();
                }
                //提交sql
                connection.commit();
            } catch (SQLException e) {
//                e.printStackTrace();
                logger.debug("SQLException : " + e.getMessage());
                System.out.println("SQLException : " + e.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
//                    e.printStackTrace();
                    logger.debug("SQLException : " + e.getMessage());
                    System.out.println("SQLException : " + e.getMessage());
                }
            }
            logger.debug("finish init test db>>>");
        }else {
            logger.debug("test db is exist");
        }

        try {
            if(!connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void initDb(Connection connection,String... sqls){
        logger.debug(">>>start initDb:{}", sqls);
        try {
            for(String str:sqls) {
                connection.setAutoCommit(false);
                connection.prepareStatement(str).execute();
            }
            connection.commit();
        } catch (SQLException e) {
//            e.printStackTrace();
            logger.debug("SQLException : " + e.getMessage());
            System.out.println("SQLException : " + e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
//                e.printStackTrace();
                logger.debug("SQLException : " + e.getMessage());
                System.out.println("SQLException : " + e.getMessage());
            }
        }
        logger.debug("finish initDb>>>");
    }

    public static void initSqliteFile(String filePath){
        File file = new File(filePath);

            File dir = file.getParentFile();
            if(dir.exists()){
                dir.mkdirs();
            }

            if(!file.exists()){
                try {
                    file.createNewFile();
                }catch (IOException e){
                    logger.debug("IOException : " + e.getMessage());
                    System.out.println("IOException : " + e.getMessage());
                }
            }

    }

    public static String getFilePath(String url){
        System.out.println("url : " + url);
        url = url.replace("jdbc:sqlite:", "");
        return url;
    }
}
