package com.refill.basic.servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.refill.base.servlet.BaseServlet;
import com.refill.util.DateUtil;
import com.refill.util.ExportUtil;
import com.refill.util.StringUtil;
import jxl.write.WriteException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ExportServlet
 * @Description: 文件导出通用servlet
 * @author: Peter Guo
 * @date: 17/04/25
 * @version: 0.1
 *
 */
@WebServlet(urlPatterns = "/exprotTable")
public class ExportServlet extends BaseServlet {

    /**
     * 处理post请求导出
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 添加导出Servlet，调整shiro
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line;
        //标题
        String titleMapStr = "";
        //数据
        String dataStr = "";
        //类型
        String fileType = "";
        //是否是参数
        boolean isParam =false;
        //是否是空
        boolean isNull = false;

        boolean isTitle = false;
        boolean isType = false;
        boolean isData = false;

        while ((line = br.readLine()) != null) {
            if(line.indexOf("form-data")>0){
                isParam=true;
                if(line.indexOf("title")>0){
                    isTitle = true;
                }
                if(line.indexOf("type")>0){
                    isType = true;
                }
                if(line.indexOf("data")>0){
                    isData = true;
                }
                continue;
            }
            if(StringUtil.isBlank(line)){
                isNull = true;
                continue;
            }
            if(isParam && isNull){
                if(isData){
                    dataStr =line;
                    isData = false;
                }
                if(isType){
                    fileType = line;
                    isType = false;
                }
                if(isTitle){
                    titleMapStr = line;
                    isTitle = false;
                }
                isParam=false;
                isNull = false;
            }
        }

        if(StringUtil.isBlank(dataStr)){
            LOG.debug("当前没有数据");
            return;
        }
        LOG.debug(dataStr);
        //文件名
        String fileName = DateUtil.dateFmtToString(new Date(), DateUtil.ORA_DATE_TIME_EXTENDED_FORMAT);

        //显示的数据
        Map titleMap = JSONObject.parseObject(titleMapStr.trim(),Map.class);
        //数据源
        List<Map> list = JSONArray.parseArray(dataStr.trim(),Map.class);

        // 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data;charset=UTF-8");

        //输出流
        OutputStream out=response.getOutputStream();

        try {
            if(fileType.trim().equals(ExportUtil.XLS)){
                // 2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
                response.setHeader("Content-Disposition","attachment; filename=" + URLEncoder.encode(fileName+ ExportUtil.XLS, "UTF-8"));
               ExportUtil.createExcelFileByJxl(out, titleMap, list);
            }else{
                // 2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
                response.setHeader("Content-Disposition","attachment; filename=" + URLEncoder.encode(fileName+ ExportUtil.CSV, "UTF-8"));
                ExportUtil.createCSVFile(out, titleMap, list);
            }
        } catch (WriteException e) {
            e.printStackTrace();
        }
        out.close();
        response.flushBuffer();//强行将响应缓存中的内容发送到目的地
    }
}
