/**
 * wxh Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.wxh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wxh.common.IGlobalConstant;
import com.wxh.dao.UserDAO;
import com.wxh.pojo.User;

/**
 * servlet 控制器
 * @author wxh
 * @version $Id: UserServlet.java, v 0.1 2017年11月8日 下午2:25:21 wxh Exp $
 */
public class UserServlet extends HttpServlet {

    /** */
    private static final long serialVersionUID = 7289151603100229848L;

    public UserServlet() {
        super();
    }

    public void destroy() {
        super.destroy();
        // Put your code here
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
                                                                               throws ServletException,
                                                                               IOException {

        doPost(request, response);

    }

    /** 
     * 处理post请求
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
                                                                                throws ServletException,
                                                                                IOException {

        // 设置请求/响应的视图的编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");

        // 字符输出流
        PrintWriter out = response.getWriter();

        // 工具类
        UserDAO userDAO = new UserDAO();
        JsonObject jsonObject = new JsonObject();

        // 请求标志 查询还是新增还是修改还是删除
        String flag = request.getParameter("flag");
        if (flag.equals("query")) {
            // 收集参数(easyui 分页组件分页时会内置参数page和rows进行分页)
            Integer page = Integer.parseInt(request.getParameter("page"));
            Integer rows = Integer.parseInt(request.getParameter("rows"));
            // 每页的数据
            List<User> users = userDAO.queryAll(page, rows);
            // 总条数
            int total = userDAO.getTotal();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("total", total);
            map.put("rows", users);
            // 将json字符串写入到流中
            out.write(new Gson().toJson(map));
        } else if (flag.equals("add")) {
            // 收集参数
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String userSex = request.getParameter("userSex");
            Long userAge = Long.parseLong(request.getParameter("userAge"));
            String education = request.getParameter("education");
            String address = request.getParameter("address");

            User user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            user.setUserSex(userSex);
            user.setUserAge(userAge);
            user.setEducation(education);
            user.setAddress(address);

            System.out.println("姓名：" + userName);

            // 判断是否插入成功,返回相应提示
            if (IGlobalConstant.SUCCESS == userDAO.insertUser(user)) {
                jsonObject.addProperty("success", true);
                jsonObject.addProperty("message", "新增成功");
            } else {
                jsonObject.addProperty("success", false);
                jsonObject.addProperty("message", "新增失败");
            }
            // 将json字符串写入到流中
            out.write(jsonObject.toString());
        } else if (flag.equals("update")) {
            // 收集参数
            Long uId = Long.parseLong(request.getParameter("uId"));
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String userSex = request.getParameter("userSex");
            Long userAge = Long.parseLong(request.getParameter("userAge"));
            String education = request.getParameter("education");
            String address = request.getParameter("address");

            User user = new User(uId, userName, password, userSex, userAge, education, address);

            System.out.println("编号:" + uId);
            if (IGlobalConstant.SUCCESS == userDAO.updateUser(user)) {
                jsonObject.addProperty("success", true);
                jsonObject.addProperty("message", "修改成功");
            } else {
                jsonObject.addProperty("success", false);
                jsonObject.addProperty("message", "修改失败");
            }
            // 将json字符串写入到流中
            out.write(jsonObject.toString());
        } else if (flag.equals("delete")) {
            // 收集参数
            Long uId = Long.parseLong(request.getParameter("uId"));
            if (IGlobalConstant.SUCCESS == userDAO.deleteUser(uId)) {
                jsonObject.addProperty("success", true);
                jsonObject.addProperty("message", "删除成功");
            } else {
                jsonObject.addProperty("success", false);
                jsonObject.addProperty("message", "删除失败");
            }
            // 将json字符串写入到流中
            out.write(jsonObject.toString());
        }

        // 关闭流
        out.flush();
        out.close();
    }

    public void init() throws ServletException {
        // Put your code here
    }

}
