/**
 * wxh Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.wxh.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import com.wxh.pojo.User;

/**
 * 将文件导出为excel
 * @author wxh
 * @version $Id: ExcelUtil.java, v 0.1 2017年11月10日 下午3:43:39 wxh Exp $
 */
public class ExcelUtil {
    private static HSSFWorkbook workBook;

    /**
     * 防止被实例化
     */
    private ExcelUtil() {

    }

    /**
     * 手动构建数据(实际开发中查询表获取数据)
     * @return
     */
    private static List<User> getUserList() {
        List<User> users = new ArrayList<User>();
        User user1 = new User(1L, "李晓兰", "1234", "女", 18L, "本科", "河北唐山1");
        User user2 = new User(2L, "李晓燕", "1234", "女", 18L, "本科", "河北唐山2");
        User user3 = new User(3L, "李晓红", "1234", "女", 18L, "本科", "河北唐山3");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        return users;
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        workBook = new HSSFWorkbook();
        // 第二步，在wrokbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = workBook.createSheet("用户表一");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle cellStyle = workBook.createCellStyle();
        // 创建一个居中格式
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        // 创建表格表头
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("编号");
        cell.setCellStyle(cellStyle);

        cell = row.createCell((short) 1);
        cell.setCellValue("姓名");
        cell.setCellStyle(cellStyle);

        cell = row.createCell((short) 2);
        cell.setCellValue("密码");
        cell.setCellStyle(cellStyle);

        cell = row.createCell((short) 3);
        cell.setCellValue("性别");
        cell.setCellStyle(cellStyle);

        cell = row.createCell((short) 4);
        cell.setCellValue("年龄");
        cell.setCellStyle(cellStyle);

        cell = row.createCell((short) 5);
        cell.setCellValue("学历");
        cell.setCellStyle(cellStyle);

        cell = row.createCell((short) 6);
        cell.setCellValue("地址");
        cell.setCellStyle(cellStyle);

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到
        List<User> users = getUserList();
        // 防止高并发时，不停的计算，增加CPU的负担
        int length = users.size();
        // 遍历将数据填充到excel中
        for (int i = 0; i < length; i++) {
            // 创建一行
            row = sheet.createRow((int) i + 1);
            User user = users.get(i);
            // 创建单元格，并设置值
            row.createCell((short) 0).setCellValue(user.getuId());
            row.createCell((short) 1).setCellValue(user.getUserName());
            row.createCell((short) 2).setCellValue(user.getPassword());
            row.createCell((short) 3).setCellValue(user.getUserSex());
            row.createCell((short) 4).setCellValue(user.getUserAge());
            row.createCell((short) 5).setCellValue(user.getEducation());
            row.createCell((short) 6).setCellValue(user.getAddress());
        }

        // 第六步，将文件存到指定位置
        try {
            FileOutputStream fos = new FileOutputStream(
                "E:\\work\\easyui\\WebRoot\\WEB-INF\\excel\\User.xls");
            workBook.write(fos);
            System.out.println("文件导出到Excel成功");
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件导出到Excel失败" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件导出到Excel失败" + e.getMessage());
        }
    }
}
