/**
 * wxh Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.wxh.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 文件上传
 * @author wxh
 * @version $Id: UploadServlet.java, v 0.1 2017年11月9日 下午3:26:20 wxh Exp $
 */
public class UploadServlet extends HttpServlet {

    /** */
    private static final long   serialVersionUID = 5838350294534891967L;

    /** 上传文件存储目录  */
    private static final String UPLOAD_DIRECTORY = "upload";

    public UploadServlet() {
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
     * 文件上传post请求
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
                                                                                throws ServletException,
                                                                                IOException {

        try {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("utf8");//支持中文文件名  
            List<FileItem> items = upload.parseRequest(request);

            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();
                if (item.isFormField()) {
                    System.out.println("查找到一个普通文本数据");
                    System.out.println("该文本数据的name为：" + item.getFieldName());
                    System.out.println("该文本数据的value为：" + item.getString());
                    System.out.println();
                } else {
                    System.out.println("查找到一个二进制数据");
                    System.out.println("该文件表单name为：" + item.getFieldName());
                    System.out.println("该文件文件名为：" + item.getName());
                    System.out.println("该文件文件类型为：" + item.getContentType());
                    System.out.println("该文件文件大小为：" + item.getSize());
                    System.out.println();
                    File uploadPath = new File(this.getServletContext().getRealPath("/") + "\\"
                                               + UPLOAD_DIRECTORY);
                    if (!uploadPath.exists() && !uploadPath.isDirectory()) {
                        uploadPath.mkdir();
                    }
                    File uploadedFile = new File(this.getServletContext().getRealPath("/")
                                                 + File.separator + UPLOAD_DIRECTORY
                                                 + File.separator + item.getName());

                    item.write(uploadedFile);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("message.jsp");
    }

    public void init() throws ServletException {
        // Put your code here
    }

}
