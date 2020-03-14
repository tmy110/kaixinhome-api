package com.kaixin8848.home.utility.Log4J2;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Log4j2 ConnectionFactory
 *
 * @author fengxuechao
 */
public class ConnectionFactoryConfig {

    private DruidDataSource dataSource;

    private  static ConnectionFactoryConfig connectionFactoryConfig;

    private Connection getConnection() throws SQLException {
        System.err.println("实例化");
        System.out.println("实例化1");
        Properties properties = new Properties();
        String user = "root";
        String password = "123456";
        String url = "jdbc:mysql://172.16.92.74:3306/kaixinhome_log?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";

        String driverClassName = "com.mysql.cj.jdbc.Driver";
        properties.put("driverClassName",driverClassName);
        properties.put("url",url);
        properties.put("username",user);
        properties.put("password",password);
        System.out.println("实例化2");
        try {
            System.out.println("实例化3");
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            try {
                init();
            } catch (Exception e2) {
            }
        }
        return  dataSource.getConnection();
    }

    public static Connection getDatabaseConnection() throws SQLException {
        if(connectionFactoryConfig==null){
            connectionFactoryConfig = new ConnectionFactoryConfig();
        }
        return connectionFactoryConfig.getConnection();

    }

    public void init(){
        try {
            if (dataSource != null)
                dataSource.close();
        } catch (Exception e) {
        }
    }
}