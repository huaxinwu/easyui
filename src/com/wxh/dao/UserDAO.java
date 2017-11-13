/**
 * wxh Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.wxh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wxh.common.IGlobalConstant;
import com.wxh.pojo.User;
import com.wxh.util.DbUtil;

/**
 * 数据库操作
 * @author wxh
 * @version $Id: UserDAO.java, v 0.1 2017年11月8日 下午3:10:36 wxh Exp $
 */
public class UserDAO {
    private static Connection        conn;
    private static PreparedStatement ps;
    private static ResultSet         rs;
    private static List<User>        users;

    /**
     * 查询每页的所有用户信息
     * @return
     */
    public List<User> queryAll(Integer page, Integer rows) {
        users = new ArrayList<User>();
        try {
            String sql = "select * from t_user limit ?,?";
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);

            page = (page == null ? 1 : page);
            rows = (rows == null ? 5 : rows);

            //设置分页起始位置，每页显示多少条数据
            ps.setInt(1, (page - 1) * rows);
            ps.setInt(2, rows);
            System.out.println(ps.toString());

            rs = ps.executeQuery();
            // 将数据库接口的数据封装到Java对象中
            while (rs.next()) {
                User user = new User();
                user.setuId(rs.getLong(1));
                user.setUserName(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setUserSex(rs.getString(4));
                user.setUserAge(rs.getLong(5));
                user.setEducation(rs.getString(6));
                user.setAddress(rs.getString(7));
                users.add(user);
            }
        } catch (SQLException e) {
            // 日志暂时写入堆栈中，正常开发应该写入日志文件中
            e.printStackTrace();
        }
        return users;
    }

    /**
     * 获取用户信息的总条数
     * @return
     */
    public int getTotal() {
        int count = 0;
        try {
            String sql = "select count(*) from t_user";
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 新增用户信息
     * 因为插入一条记录影响的是行数，所以返回int
     * @param user
     * @return
     */
    public int insertUser(User user) {
        try {
            String sql = "insert into t_user(user_name,password,user_sex,user_age,education,address) values(?,?,?,?,?,?)";
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            // 设置参数值,将Java对象封装到数据库接口中
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUserSex());
            ps.setLong(4, user.getUserAge());
            ps.setString(5, user.getEducation());
            ps.setString(6, user.getAddress());
            System.out.println(ps.toString());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return IGlobalConstant.FAILURE;
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public int updateUser(User user) {
        try {
            String sql = "update t_user set user_name=?,password=?,user_sex=?,user_age=?,education=?,address=? where u_id=?";
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            // 设置参数值
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUserSex());
            ps.setLong(4, user.getUserAge());
            ps.setString(5, user.getEducation());
            ps.setString(6, user.getAddress());
            ps.setLong(7, user.getuId());
            System.out.println(ps.toString());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return IGlobalConstant.FAILURE;
    }

    /**
     * 删除用户信息
     * @param uId
     * @return
     */
    public int deleteUser(Long uId) {
        try {
            String sql = "delete from t_user where u_id=?";
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, uId);
            System.out.println(ps.toString());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return IGlobalConstant.FAILURE;

    }
}
