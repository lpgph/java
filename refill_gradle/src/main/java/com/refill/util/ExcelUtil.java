package com.refill.util;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用excel文件处理类
 * 
 * @author 王天培QQ:78750478
 * @version 版本号：100-000-000<br/>
 *          创建日期：2012-03-15<br/>
 *          历史修订：<br/>
 */
public class ExcelUtil {

	/**
	 * 导出excel文件
	 * 
	 * @param fileName
	 *            EXCEL文件名称
	 * @param titles
	 *            EXCEL文件第一行列标题集合
	 * @param listContent
	 *            EXCEL文件正文数据集合
	 * @param response
	 *            请求
	 * @return 返回提示信息
	 */
	public final static String exportExcel(String fileName, String[] titles, List<Object> listContent,
			HttpServletResponse response) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			// 定义输出流，以便打开保存对话框_______________________end

			// 创建工作表
			WritableWorkbook workbook = jxl.Workbook.createWorkbook(os);

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
			for (int i = 0; i < titles.length; i++) {
				sheet.addCell(new Label(i, 0, titles[i], wcf_center));
			}
			/** ***************以下是EXCEL正文数据********************* */
			int i = 1;
			for (Object obj : listContent) {
				int j = 0;
				for (Object field : (Object[]) obj) {
					if (field instanceof Map) {
						if (field != null) {
							Map temp = (Map) field;
							if (temp.get("format") != null && temp.get("format") instanceof WritableCellFormat) {
								sheet.addCell(new Label(j, i, field != null ? String.valueOf(temp.get("value")) : "",
										(WritableCellFormat) temp.get("format")));
							} else {
								sheet.addCell(new Label(j, i, field != null ? String.valueOf(field) : "", wcf_left));
							}
						}
					} else {
						sheet.addCell(new Label(j, i, field != null ? String.valueOf(field) : "", wcf_left));
					}

					j++;
				}
				i++;
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			System.out.println(result);
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 检查文件是否存在和文件类型是否正确
	 * 
	 * @param filePath
	 *            文件路径
	 * @return workbook 对象
	 * @throws IOException
	 *             读取文件错误
	 */
	public static Workbook fileChecker(String filePath) throws IOException {
		File file = new File(filePath);
		// 判断传入的文件是否存在
		if (!file.exists()) {
			return null;
		}
		// 判断传入的文件是否为excle文件,支持的excle的文件格式为:xls和xlsx
		if (!(filePath.endsWith(filePath) || filePath.endsWith(filePath))) {
			return null;
		}
		// 获取workbook对象
		Workbook workbook = null;
		InputStream is = new FileInputStream(filePath);

		// 判断文件类型,实例化不同的解析类
		if (filePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(is);
		} else if (filePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(is);
		}
		return workbook;
	}

	/**
	 * 读取Excel返回List<Map<String,String>>
	 * 
	 * @param filePath
	 *            excle文件路径
	 * @return 获取到的excel列表数据,以行为单元数据</br>
	 *         Map(key:表头名称 value:对应的值)
	 * @throws IOException
	 *             读取文件错误
	 */
	public static List<Map<String, String>> readExcel(String filePath) throws IOException {
		// 读取Excle文件
		Workbook workBook = fileChecker(filePath);
		if (workBook == null) {
			return null;
		}

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			for (int numSheet = 0; numSheet < workBook.getNumberOfSheets(); numSheet++) {
				// 当前工作表
				Sheet sheet = ((Workbook) workBook).getSheetAt(numSheet);
				if (sheet == null) {
					continue;
				}
				// 工作表第一行
				int firstRowIndex = sheet.getFirstRowNum();
				// 工作表最后一行
				int lastRowIndex = sheet.getLastRowNum();
				// 读取首行 即,表头
				Row firstRow = sheet.getRow(firstRowIndex);
				// 工作表列总数
				int cellCount = firstRow.getLastCellNum();
				// 表头数组
				String[] sheetTitle = new String[cellCount];

				// 循环遍历表头并将表头存储到表头数组
				for (int i = firstRow.getFirstCellNum(); i < firstRow.getLastCellNum(); i++) {
					Cell cell = firstRow.getCell(i);
					String cellValue = getCellValue(cell, true);
					sheetTitle[i] = cellValue;
				}
				// 遍历工作表行
				for (int rowIndex = firstRowIndex + 1; rowIndex <= lastRowIndex; rowIndex++) {
					// 当前行
					Row currentRow = sheet.getRow(rowIndex);
					if (currentRow == null) {
						continue;
					}
					// 存放工作表的行Map<标题,单元格>
					Map<String, String> map = new HashMap<String, String>();
					// 遍历当前行中的单元格
					for (int columnIndex = firstRow.getFirstCellNum(); columnIndex < firstRow
							.getLastCellNum(); columnIndex++) {
						// 当前单元格对象
						Cell currentCell = currentRow.getCell(columnIndex);
						// 当前单元格所处的表头名称
						String titileCellValue = sheetTitle[columnIndex];
						// 当前单元格对象为空时当前行数默认为""值
						if (currentCell == null) {
							map.put(titileCellValue, "");
						} else {
							// 当前单元格的值
							String currentCellValue = getCellValue(currentCell, true);
							map.put(titileCellValue, currentCellValue);
						}
					}
					list.add(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 取单元格的值
	 * 
	 * @param cell
	 *            单元格对象
	 * @param treatAsStr
	 *            为true时，当做文本来取值 (取到的是文本，不会把“1”取成“1.0”)
	 * @return 当前单元格的值
	 */
	private static String getCellValue(Cell cell, boolean treatAsStr) {
		if (cell == null) {
			return "";
		}
		if (treatAsStr) {
			// 虽然excel中设置的都是文本，但是数字文本还被读错，如“1”取成“1.0”
			// 加上下面这句，临时把它当做文本来读取
			cell.setCellType(Cell.CELL_TYPE_STRING);
		}
		if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else {
			return String.valueOf(cell.getStringCellValue());
		}
	}
}