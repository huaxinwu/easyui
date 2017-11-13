/**
 * wxh Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.wxh.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 下载文件
 * @author wxh
 * @version $Id: DownloadServlet.java, v 0.1 2017年11月10日 下午2:55:28 wxh Exp $
 */
public class DownloadServlet extends HttpServlet {

    /** */
    private static final long serialVersionUID = 2191132682028524665L;

    public DownloadServlet() {
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
     * 下载文件
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
                                                                                throws ServletException,
                                                                                IOException {

        // 转换文件编码
        String fileName = new String(request.getParameter("fileName").getBytes("iso-8859-1"),
            "utf-8");

        // 文件读取流
        FileInputStream fis = new FileInputStream(new File(getServletContext().getRealPath(
            "WEB-INF/download")
                                                           + File.separator + fileName));
        System.out.println("客户端类型：" + request.getHeader("User-Agent"));

        // 火狐浏览器、微软IE浏览器处理
        if (request.getHeader("User-Agent").contains("Firefox")) {
            response.addHeader("content-disposition",
                "attachment;filename=" + request.getParameter("fileName"));
        } else if (request.getHeader("User-Agent").contains("MSIE")) {
            response.addHeader("content-disposition",
                "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
        }

        // 将读取的文件内容写到输出流中
        ServletOutputStream sos = response.getOutputStream();
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = fis.read(buffer)) != -1) {
            sos.write(buffer, 0, len);
        }
        sos.flush();
        sos.close();
        fis.close();
    }

    public void init() throws ServletException {
        // Put your code here
    }

}
