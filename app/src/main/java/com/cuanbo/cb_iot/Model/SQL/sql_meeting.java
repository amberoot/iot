package com.cuanbo.cb_iot.Model.SQL;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.cuanbo.cb_iot.Model.Meeting;
import com.cuanbo.cb_iot.View.activityUtil.MyApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xww on 2018/5/8.
 */

public class sql_meeting implements Runnable {



    @Override
    public void run() {
        //此处关键，数据库的连接需放在子线程中操作
        String userName = "sa";//用户名
        String password = "123456ht";//密码
        Connection con = null;
        try {
            // 使用Class加载驱动程序
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //连接数据库
            con = DriverManager.getConnection(
                    "jdbc:jtds:sqlserver://192.168.88.112:1433/CB_IOT", userName,
                    password);
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动程序出错");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            updateDataToSQL(con);
            readSqlData(con);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    private void readSqlData(Connection con) throws java.sql.SQLException {
        try {
            /*SQL指令对大小不敏感*/
            //查询表名为“LoginUser”的所有内容
            String sql = "SELECT * FROM MeetingList";
            //查询列UserName内容
            String sql2 = "select UserName from LoginUser";
            //查询 列UserName中不同的数据，即列数据中有相同的数据只显示一次
            String sql3 = "SELECT DISTINCT UserName FROM MeetingList ";
            //条件查询文本值-where
            String sql4 = "SELECT * FROM MeetingList WHERE UserName='admin'";
            //条件查询数值-where
            String sql5 = "SELECT * FROM MeetingList WHERE OrderNumber>16";
            //条件查询-and\or
            String sql6 = "SELECT * FROM MeetingList WHERE UserName='admin' AND OrderNumber<16";
            //按字母顺序显示用户名-order by
            String sql7 = "SELECT UserName, UserPwd FROM MeetingList ORDER BY UserName";
            //以逆字母顺序显示公司名称，并以数字顺序显示顺序号
            String sql8 = "SELECT UserName, OrderNumber FROM MeetingList ORDER BY UserName DESC, OrderNumber ASC";
            /**
             * 更多SQL操作符：top\like\not\
             */
            //选取表中的头两行-top
            String sql14 = "SELECT TOP 2 * FROM MeetingList";
            //选取表中UserName以'a'开头的用户名-like
            String sql15 = "SELECT * FROM MeetingList WHERE UserName LIKE 'a%'";
            //选取表中UserName不含有'ha'的用户名-not
            String sql16 = "SELECT * FROM MeetingList WHERE UserName NOT LIKE '%ha%'";
            //
            String sql17 = "";
            String sql18 = "";
            String sql19 = "";

            //创建Statement,操作数据
            Statement state = con.createStatement();

            //读取数据：生成结果集ResultSet////////////
            ResultSet rs = state.executeQuery(sql);

            List<Meeting> meetingList = new ArrayList<>();
            while (rs.next()) {

                String MetName = rs.getString("MetName");
                String MetRoom = rs.getString("MetRoom");
                Time MetStartTime = rs.getTime("MetStartTime");
                Time MetEndTime = rs.getTime("MetEndTime");
                Date MetData = rs.getDate("MetStartTime");
                int MetNumber = rs.getInt("MetNumber");


            }

            rs.close();
            state.close();

        } catch (SQLException e) {
            Log.e("读取数据库错误",e.getMessage());
        } finally {
            if (con != null)
                try {
                    con.close();  //数据库的关闭
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    private void updateDataToSQL(Connection con) throws java.sql.SQLException {
        try {
            /*SQL指令对大小不敏感(数据表中主键必须是唯一的，当前主键是UserName)*/
            //插入数据-INSERT INTO
            String sql9 = "INSERT INTO LoginUser VALUES ('amberoot', '12345', 'www')";
            //插入指定数据到表LoginUser
            String sql10 = "INSERT INTO LoginUser (UserName, UserPwd, PerLevels) VALUES ('haha', '888888','22')";
            //修改表中数据
            String sql11 = "UPDATE LoginUser SET UserPwd = '666' WHERE UserName = 'haha' ";
            //删除表中数据
            String sql12 = "DELETE FROM LoginUser WHERE UserName = 'amber' ";
            //删除表中所有数据
            String sql13 = "DELETE * FROM LoginUser";


            //创建Statement,操作数据
            Statement state = con.createStatement();
            //插入数据/////////////////////
            int insertSeccess = state.executeUpdate(sql10);
            if (insertSeccess == 1) {
                Log.i("更新数据库返回结果", "成功");
            }


            state.close();

        } catch (SQLException e) {
            Log.e("更新数据库错误", e.getMessage());
        }
//        finally {
//            if (con != null)
//                try {
//                    con.close();  //数据库的关闭
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//        }


    }
}
