package com.yjr1100.qrcodebackend.utils;/*
 * @Auther:YJR-1100
 * @Date:2021/11/18 - 11 - 18 - 9:31
 * @Version:1.0
 * @Description:
 *druid的工具栏
 */

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DRUIDUtils {
//定义成员变量 DataSource
    private static DataSource ds;
    static {
        try {
//        1加载配置文件
            Properties pro = new Properties();
            InputStream is = DRUIDUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);
//        2 获取DataSource
            ds = DruidDataSourceFactory.createDataSource(pro);
            System.out.println("-------");
            System.out.println(ds);
            System.out.println("-------");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
     * @Author YJR-1100
     * @Description //TODO
     * @Date 9:54
     * @Param []
     * @return java.sql.Connection
     * 获取连接
     **/
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /*
     * @Author YJR-1100
     * @Description //TODO
     * @Date 10:03
     * @Param [stmt, conn]
     * @return void
     * 归还连接
     **/
    public static void close(Statement stmt,Connection conn){
        close(null,stmt,conn);
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if(stmt!=null){
            try{
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if(conn!=null){
            try{
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public static DataSource getDataSource(){
        System.out.println(ds);
        return ds;
    }
}
