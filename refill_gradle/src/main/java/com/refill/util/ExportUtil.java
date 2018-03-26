package com.refill.util;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * 导出工具
 * @author Penn Kwok
 * @version 1.0
 * @since version 1.0
 * Date: 4/19/16
 * Time: 15:26 PM
 */
public class ExportUtil {

    /**
     * 可以导出的文件后缀
     */
    public final static String JSON=".json";
    public final static String XML=".xml";
    public final static String SQL=".sql";
    public final static String CSV=".csv";
    public final static String TXT=".txt";
    public final static String XLS=".xls";
    public final static String WORD=".word";
    public final static String PDF=".pdf";
    public final static String PNG=".png";
    /*
     * slf4j日志记录
     */
    protected static final Logger LOG =  LoggerFactory.getLogger(ExportUtil.class);

    /**
     * 将List<Map<String,Objec>> list转换成List<List<String>>
     * @param set
     * @param list
     * @return
     */
    private static List<List<String>> getContentList(Set<String> set,List<Map> list){
        List<List<String>> contentList = new ArrayList<>();
        for (Map map:list) {
            Map<String,String> newMap = new HashMap<>();
            for (String title:set) {
                newMap.put(title,map.get(title)!=null?map.get(title).toString():"");
            }
            List val = new ArrayList<>(newMap.values());
            //进行倒序
//            Collections.reverse(val);
            contentList.add(val);
        }
        return contentList;
    }

    /**
     * 使用jxl生成excel文件
     * @param out 输出流
     * @param titleMap 列表头
     * @param list 源数据List
     * @return excel文件
     */
    public final static void createExcelFileByJxl(OutputStream out,Map<String,String> titleMap, List<Map> list) throws IOException,WriteException {
        //内容集合
        List<List<String>> contentList = getContentList(titleMap.keySet(),list);
        //标题集合
        List<String> titleList = new ArrayList<>(titleMap.values());
        //对标题进行倒序
//        Collections.reverse(titleList);

//        File file = null;
        WritableWorkbook workbook=null;
        // 以下开始输出到EXCEL
        try {
            //定义文件名格式并创建
            // 创建工作表
            workbook = jxl.Workbook.createWorkbook(out);

            WritableSheet sheet = workbook.createSheet("Sheet1", 0);

            // 设置纵横打印（默认为纵打）、打印纸
            jxl.SheetSettings sheetset = sheet.getSettings();
            sheetset.setProtected(false);

            // 设置单元格字体
            WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
            WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);

            /** 以下设置三种单元格样式，灵活备用 */
            // 用于标题居中
            WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
            wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
            wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
            wcf_center.setWrap(false); // 文字是否换行

            // 用于正文居左
            WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
            wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
            wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
            wcf_left.setWrap(false); // 文字是否换行

            /** ***************以下是EXCEL开头大标题，暂时省略********************* */
            // sheet.mergeCells(0, 0, colWidth, 0);
            // sheet.addCell(new Label(0, 0, "XX报表", wcf_center));
            /** ***************以下是EXCEL第一行列标题********************* */
            for (int i = 0; i < titleList.size(); i++) {
                sheet.addCell(new Label(i, 0, titleList.get(i), wcf_center));
            }
            /** ***************以下是EXCEL正文数据********************* */
            int i=1;
            for(List<String> filedList:contentList){
                int j=0;
                for(String filed:filedList){
                    sheet.addCell(new Label(j, i,filed,wcf_left));
                    j++;
                }
                i++;
            }
            /** **********将以上缓存中的内容写到EXCEL文件中******** */
            workbook.write();
            /** *********关闭文件************* */

        } finally {
            if(workbook!=null) workbook.close();
        }
    }

    /**
     * 生成为CVS文件
     * @param out 文件名称
     * @param titleMap 列表头
     * @param list 源数据List
     * @return
     */
    public final static void createCSVFile(OutputStream out,Map<String,String> titleMap, List<Map> list) throws IOException {

        //内容集合
        List<List<String>> contentList = getContentList(titleMap.keySet(),list);
        //标题集合
        List<String> titleList = new ArrayList<>(titleMap.values());
        //对标题进行倒序
        Collections.reverse(titleList);

        BufferedWriter bw = null;
        try {
            // UTF-8使正确读取分隔符","
            bw = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"),1024);

            // 写入文件头部
            for (Iterator propertyIterator = titleList.iterator(); propertyIterator.hasNext();) {
                String field = (String) propertyIterator.next();

                bw.write("\""+field+"\"");

                //判断下一级是否存在元素，存在则天机
                if (propertyIterator.hasNext()) {
                    bw.write(",");
                }
            }
            bw.newLine();
            // 写入文件内容
            for (Iterator iterator = contentList.iterator(); iterator.hasNext();) {
                Collection<String> row = (Collection<String>) iterator.next();
                for (Iterator propertyIterator = row.iterator(); propertyIterator.hasNext();) {
                    String field = (String) propertyIterator.next();
                    bw.write("\""+field+"\"");
                    //判断下一级是否存在元素，存在则天机
                    if (propertyIterator.hasNext()) {
                        bw.write(",");
                    }
                }
                if (iterator.hasNext()) {
                    bw.newLine();
                }
            }
            bw.flush();
        } finally {
            if(bw!=null)bw.close();
        }
    }

    /**
     * 下载文件
     * @param response
     * @param file 文件
     * @throws IOException
     */
    public static void downloadFile(HttpServletResponse response, File file) throws IOException{
        // 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data;charset=UTF-8");
        // 2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
        response.setHeader("Content-Disposition","attachment; filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
        //输出流
        OutputStream out=null;
        //创建冲输入流
        BufferedInputStream in =null;
        try{
            out = response.getOutputStream();
            in = new BufferedInputStream(new FileInputStream(file));
            byte buffBytes[] = new byte[1024];
            int allLength = 0;
            int read=0;
            while ((read=in.read(buffBytes))!=-1) {
                allLength += read;
                out.write(buffBytes, 0, read);
            }
        }finally {
            if(out!=null) out.close();
            if(in!=null) in.close();
        }
    }
}
