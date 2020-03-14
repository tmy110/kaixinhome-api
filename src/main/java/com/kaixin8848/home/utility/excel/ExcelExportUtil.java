package com.kaixin8848.home.utility.excel;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

/**
 * @Author: chaoxianfei
 * @Date: 2019/4/20 9:33
 * @Description: 导出excel工具
 */
public class ExcelExportUtil {

    private static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    /*
     * @Description: 利用阿里巴巴easyexcel导出excel
     * @author: chaoxianfei
     * @Date: 2019/4/26 9:56
     */
    public static void excelExport(HttpServletResponse response, String[] headers, List<List<String>> dataList, String fileName) {
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            Sheet sheet = new Sheet(1, 0);
            sheet.setSheetName(fileName);
            Table table = new Table(1);
            List<List<String>> headerList = new ArrayList<>();
            if (headers != null && headers.length > 0) {
                for (String header : headers) {
                    headerList.add(Arrays.asList(header));
                }
            }
            table.setHead(headerList);
            writer.write0(dataList, sheet, table);
            // 下载EXCEL
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            writer.finish();
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
