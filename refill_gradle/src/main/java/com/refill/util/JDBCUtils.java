package com.refill.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JDBC数据库连接工具类
 *
 * @author 郭鹏辉
 * @version 版本号：</br>
 * 创建日期：@date 2016年5月10日</br>
 * 历史修订：</br>
 * @Package com.mingsoft.util
 */
public class JDBCUtils {

    /**
     * slf4j日志记录
     */
    protected static final Logger logger = LoggerFactory.getLogger(JDBCUtils.class);

    /**
     * 连接数据
     *
     * @return conn
     */
    public static Connection getConnection(String driver, String url, String username, String password) throws Exception {
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * 增删改操作
     *
     * @param conn
     * @param sql   sql语句
     * @param param 参数
     * @return
     */
    public static int executeUpdate(Connection conn, String sql, Object[] param) throws Exception {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        if (param != null) {
            for (int i = 0; i < param.length; i++) {
                pstmt.setObject(i + 1, param[i]);
            }
        }
        int result = pstmt.executeUpdate();
        pstmt.close();
        return result;
    }

    /**
     * 获取单条信息,如果获取多条，或没有则返回null
     *
     * @return ResultSet  需要自己手动关闭
     * @date 2015-7-25 上午11:10:06
     */
    public static Map<String, Object> getMap(Connection conn, String sql, Object[] param) throws Exception {
        List<Map<String, Object>> list = getListMap(conn, sql, param);
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 执行查询语句
     *
     * @param conn  链接
     * @param sql   查询语句
     * @param param 占位符对应的参数
     * @return
     * @throws Exception
     */
    public static List<Map<String, Object>> getListMap(Connection conn, String sql, Object[] param) throws Exception {
        List<Map<String, Object>> list = new ArrayList<>();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        if (param != null) {
            for (int i = 0; i < param.length; i++) {
                pstmt.setObject(i + 1, param[i]);
            }
        }
        //执行查询
        ResultSet result = pstmt.executeQuery();
        //获取结果元数据
        ResultSetMetaData metaData = result.getMetaData();
        while (result.next()) {
            Map<String, Object> map = new HashMap<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                //获取当前栏目
                String colName = metaData.getColumnLabel(i);
                map.put(colName, result.getObject(colName));
            }
            list.add(map);
        }
        result.close();
        pstmt.close();
        return list;
    }

    /**
     * 关闭资源
     *
     * @param con
     */
    public static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
