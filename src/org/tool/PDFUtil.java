package org.tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.view.VLogId;
import org.view.VSwitchAlarmId;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * pdf工具类
 * 
 * @author Administrator
 * 
 */
public class PDFUtil {
	/**
	 * 生成pdf文件
	 * 
	 * @param imageFile
	 *            水印文件的绝对路径
	 * @param data
	 *            数据数组
	 * @param type
	 *            0开关量报警记录，1暂无，2操作记录
	 */
	public static String buidPDF(String imageFile, List data, Integer type) {
		// File file = File.createTempFile("tempFile", ".pdf"); // 创建临时文件
		File file = new File(Constans.pdfDir + "test.pdf");// 模板文件
		Double a = 100000 + Math.random() * 899999;// 6位随机数
		String filename = ChangeTime.timeStamp() + a.intValue();
		Integer currentDate = ChangeTime.currentDate();
		String pdfFile = Constans.pdfDir + currentDate + Constans.dot
				+ filename + ".pdf"; // 输出文件
		File file1 = new File(pdfFile);
		if (!file1.getParentFile().exists()) {
			System.out.println("目标文件的目录不存在，准备创建目录...");
			if (!file1.getParentFile().mkdirs()) {
				System.out.println("创建目录失败");
			}
			try {
				System.out.println(file1.createNewFile());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("删除失败的旧文件数：" + deleteTemp() + "个");
		switch (type) {
		case 0:
			if (createSwitchAlarmPDF(file, data)) {
				System.out.println("添加水印");
				waterMark(file.getPath(), imageFile, pdfFile); // 添加水印
			}
			break;
		case 2:
			if (createLogPDF(file, data)) {
				System.out.println("添加水印");
				waterMark(file.getPath(), imageFile, pdfFile); // 添加水印
			}
			break;
		default:
			break;
		}

		return Constans.pdfUrl + currentDate + "/" + filename + ".pdf";
	}

	/**
	 * 删除今天以前的所有临时文件夹
	 * 
	 * @return 返回未成功删除的文件夹个数
	 */
	private static int deleteTemp() {
		File file = new File(Constans.pdfDir);
		int i = 0;
		for (File f : file.listFiles()) {
			if (!f.getName().equals("" + ChangeTime.currentDate())) {
				try {
					if (f.isDirectory()) {
						FileUtils.deleteDirectory(f);
					} else {
						f.delete();
					}
				} catch (IOException e) {
					e.printStackTrace();
					i++;
				}
			}
		}
		return i;
	}

	/**
	 * 创建PDF文件
	 */
	private static boolean createLogPDF(File file, List<VLogId> list) {
		System.out.println("生成pdf");
		Rectangle rect = new Rectangle(PageSize.A4);
		Document doc = new Document(rect, 50.0F, 50.0F, 50.0F, 50.0F);
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(file));
			doc.open();

			// 字体处理，中文需要用到
			FontSelector selector = new FontSelector();
			// 设置英文显示字体TIMES_ROMAN及大小
			selector.addFont(FontFactory.getFont(FontFactory.TIMES_ROMAN, 10));
			// 设置中文显示字体STSong-Light
			Font cf1 = FontFactory.getFont("STSong-Light", "UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED, 10);
			selector.addFont(cf1);
			// 设置标题显示字体STSong-Light及大小
			Font headerFont = FontFactory.getFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED, 18);
			PdfPTable table = new PdfPTable(5);
			PdfPCell cell = new PdfPCell(new Paragraph("日志信息记录", headerFont));
			cell.setHorizontalAlignment(1);
			cell.setColspan(5);
			table.addCell(cell);
			table.addCell(selector.process("记录id"));
			table.addCell(selector.process("用户名"));
			table.addCell(selector.process("操作"));
			table.addCell(selector.process("操作消息"));
			table.addCell(selector.process("操作时间"));

			for (VLogId l : list) {
				table.addCell(selector.process("" + l.getId()));
				table.addCell(selector.process("" + l.getUsername()));
				table.addCell(selector.process("" + l.getName()));
				table.addCell(selector.process("" + l.getMsg()));
				table.addCell(selector.process("" + l.getVtime()));
			}
			doc.add(table);
			doc.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 创建PDF文件
	 */
	private static boolean createSwitchAlarmPDF(File file,
			List<VSwitchAlarmId> list) {
		System.out.println("生成pdf");
		Rectangle rect = new Rectangle(PageSize.A4);
		Document doc = new Document(rect, 50.0F, 50.0F, 50.0F, 50.0F);
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(file));
			doc.open();

			// 字体处理，中文需要用到
			FontSelector selector = new FontSelector();
			// 设置英文显示字体TIMES_ROMAN及大小
			selector.addFont(FontFactory.getFont(FontFactory.TIMES_ROMAN, 10));
			// 设置中文显示字体STSong-Light
			Font cf1 = FontFactory.getFont("STSong-Light", "UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED, 10);
			selector.addFont(cf1);
			// 设置标题显示字体STSong-Light及大小
			Font headerFont = FontFactory.getFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED, 18);
			PdfPTable table = new PdfPTable(3);
			PdfPCell cell = new PdfPCell(new Paragraph("告警信息记录", headerFont));
			cell.setHorizontalAlignment(1);
			cell.setColspan(3);
			table.addCell(cell);
			table.addCell(selector.process("设备名"));
			table.addCell(selector.process("告警时间"));
			table.addCell(selector.process("确认状态"));

			for (VSwitchAlarmId sa : list) {
				table.addCell(selector.process("" + sa.getName()));
				table.addCell(selector.process("" + sa.getTime()));
				table.addCell(selector.process("" + sa.getAck()));
			}
			doc.add(table);
			doc.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private static void waterMark(String inputFile, String imageFile,
			String outputFile) {
		try {
			PdfReader reader = new PdfReader(inputFile);
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(
					outputFile));
			// // 获取总页数
			// int total = reader.getNumberOfPages() + 1;
			// Image image = Image.getInstance(imageFile);
			//
			// // 图片位置
			// image.setAbsolutePosition(0, 0);
			// image.scaleToFit(100, 200);
			// PdfContentByte under;
			// for (int i = 1; i < total; i++) {
			// under = stamper.getUnderContent(i);
			// under.beginText();
			//
			// // 添加水印文字
			// under.endText();
			//
			// // 添加水印图片
			// PdfGState gs = new PdfGState();
			// gs.setFillOpacity(0.15f);// 设置透明度为0.2
			// under.setGState(gs);
			//
			// // under.addImage(image);
			// for (int l = 0; l < 6; l++) {
			// for (int t = 0; t < 16; t++) {
			// image.setAbsolutePosition(l * 100, t * 50);
			// under.addImage(image);
			// }
			// }
			// // 画个圈
			// // under.ellipse(250, 450, 350, 550);
			// under.setLineWidth(1f);
			// under.stroke();
			// }
			stamper.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// public static void main(String[] args) {
	// String watermark = "C:\\Users\\Administrator\\Desktop\\timg.jpg"; // 水印图片
	//
	// ZAlarmDao aDao = new ZAlarmDaoImp();
	// buidPDF( watermark, "正版授权", aDao.getAlarmList(0, -1, 2), 0);
	// ZExceptionDao eDao = new ZExceptionDaoImp();
	// buidPDF( watermark, "正版授权", eDao.getExceptionList(0, -1, 2), 1);
	// ZLogDao lDao = new ZLogDaoImp();
	// buidPDF(source, watermark, "正版授权", lDao.getLogList(0, -1), 2);
	//
	// }
}
