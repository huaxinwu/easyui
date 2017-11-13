/**
 * wxh Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.wxh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 查看请求内容
 * -----------------------------80233217916595  本次请求类型及表单域分隔符
 * Content-Disposition: form-data; name="myText"  
  
 * test  
 * -----------------------------80233217916595  
 * Content-Disposition: form-data; name="myFile"; filename="测试文件.txt"  
 * Content-Type: text/plain  
  
 * 测试内容  
 * -----------------------------80233217916595--  
 * @author wxh
 * @version $Id: SeeRequestContentServlet.java, v 0.1 2017年11月10日 上午10:57:44 wxh Exp $
 */
public class SeeRequestContentServlet extends HttpServlet {

    /** */
    private static final long serialVersionUID = -8150867512393600334L;

    public SeeRequestContentServlet() {
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

    public void doPost(HttpServletRequest request, HttpServletResponse response)
                                                                                throws ServletException,
                                                                                IOException {

        System.out.println("本次请求正文长度为：" + request.getHeader("Content-Length"));

        System.out.println("本次请求类型及表单域分隔符：" + request.getContentType());

        // 读取流
        ServletInputStream sis = request.getInputStream();
        // 写出流
        ServletOutputStream sos = response.getOutputStream();
        // 缓冲区 写的更快
        byte[] buffer = new byte[2048];
        int len = 0;
        while ((len = sis.read(buffer)) != -1) {
            // 缓冲区有数据，就一直读取数据，写入缓冲区
            sos.write(buffer, 0, len);
        }
        // 关闭资源
        sos.flush();
        sos.close();
        sis.close();
    }

    public void init() throws ServletException {
        // Put your code here
    }

}
