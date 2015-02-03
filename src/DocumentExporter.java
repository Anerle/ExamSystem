
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;

import jxl.*;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
	
public class DocumentExporter {
	public void exportTable(JTable table, File file) throws IOException {
		try {
			OutputStream out = new FileOutputStream(file);
			TableModel model = table.getModel();
			WritableWorkbook wwb = Workbook.createWorkbook(out);
			// 创建字表，并写入数据
			WritableSheet ws = wwb.createSheet("中文", 0);
			// 添加标题
			for (int i = 0; i < model.getColumnCount(); i++) {
				jxl.write.Label labelN = new jxl.write.Label(i, 0, model.getColumnName(i));
				try {
					ws.addCell(labelN);
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// 添加列
			for (int i = 0; i < model.getColumnCount(); i++) {
				for (int j = 1; j <= model.getRowCount(); j++) {
					jxl.write.Label labelN = new jxl.write.Label(i, j, model.getValueAt(j - 1, i).toString());
					try {
						ws.addCell(labelN);
					} catch (RowsExceededException e) {
						e.printStackTrace();
					} catch (WriteException e) {
						e.printStackTrace();
					}
				}
			}
			wwb.write();
			try {
				wwb.close();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "导入数据前请关闭工作表");
		}
	}
	public void exportWord(String PaperID,File file) throws DocumentException,IOException {
		try {
			// 设置纸张大小
			Document document = new Document(PageSize.A4);
			// 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中
			RtfWriter2.getInstance(document, new FileOutputStream(file));
			document.open();
			// 设置中文字体
			BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			// 标题字体风格
			Font titleFont = new Font(bfChinese, 16, Font.BOLD);
			// 题型字体风格
			Font TypeFont = new Font(bfChinese, 11, Font.BOLD);
			// 正文字体风格
			Font contextFont = new Font(bfChinese, 11, Font.NORMAL);
			//标题
			Paragraph title = new Paragraph("华中科技大学2012-2013度第一学期"+"\n"+"电信系2010级《电子线路设计与测试》期末试卷"+"\n\n");
			// 设置标题格式对齐方式
			title.setAlignment(Element.ALIGN_CENTER);
			title.setFont(titleFont);
			document.add(title);
			
			DButil dbutil=new DButil();
			try {
				String[] IDs=dbutil.checkPaper(PaperID);
				double[] Scores=dbutil.showTypeScore(PaperID);
				Paragraph Type1 = new Paragraph("\n一、选择题");
				Type1.setFont(TypeFont);
				Type1.setAlignment(Element.ALIGN_LEFT);
				document.add(Type1);
				for(int i=0,m=1;i<IDs.length;i++){
					Object[] QuestionInformation=dbutil.selectById(IDs[i]);//获取试题信息
					if(QuestionInformation[0]==null) continue;
					if ((int)QuestionInformation[3]==0) {
						String QuestionText = m + "、" + "("
								+ Scores[(int) QuestionInformation[3]] + "分) "
								+ (String) QuestionInformation[0];
						Paragraph Question = new Paragraph(QuestionText);
						Question.setFont(contextFont);
						Question.setAlignment(Element.ALIGN_LEFT);
						document.add(Question);
						IOImage ioimage = new IOImage();
						PictureUtils picture = new PictureUtils();
						byte[] buffer = ioimage.readBolb(IDs[i]);
						if (buffer != null) {//有图片则显示
							Image image = Image.getInstance(buffer);
							image.setAlignment(Image.RIGHT);
							document.add(image);
						}
						m++;
					}
				}
				Paragraph Type2 = new Paragraph("\n二、填空题");
				Type2.setFont(TypeFont);
				Type2.setAlignment(Element.ALIGN_LEFT);
				document.add(Type2);
				for(int i=0,m=1;i<IDs.length;i++){
					Object[] QuestionInformation=dbutil.selectById(IDs[i]);//获取试题信息
					if(QuestionInformation[0]==null) continue;
					if ((int)QuestionInformation[3]==1) {
						String QuestionText = m + "、" + "("
								+ Scores[(int) QuestionInformation[3]] + "分) "
								+ (String) QuestionInformation[0];
						Paragraph Question = new Paragraph(QuestionText);
						Question.setFont(contextFont);
						Question.setAlignment(Element.ALIGN_LEFT);
						document.add(Question);
						IOImage ioimage = new IOImage();
						PictureUtils picture = new PictureUtils();
						byte[] buffer = ioimage.readBolb(IDs[i]);
						if (buffer != null) {//有图片则显示
							Image image = Image.getInstance(buffer);
							image.setAlignment(Image.RIGHT);
							document.add(image);
						}
						m++;
					}
				}
				Paragraph Type3 = new Paragraph("\n三、判断题");
				Type3.setFont(TypeFont);
				Type3.setAlignment(Element.ALIGN_LEFT);
				document.add(Type3);
				for(int i=0,m=1;i<IDs.length;i++){
					Object[] QuestionInformation=dbutil.selectById(IDs[i]);//获取试题信息
					if(QuestionInformation[0]==null) continue;
					if ((int)QuestionInformation[3]==2) {
						String QuestionText = m + "、" + "("
								+ Scores[(int) QuestionInformation[3]] + "分) "
								+ (String) QuestionInformation[0];
						Paragraph Question = new Paragraph(QuestionText);
						Question.setFont(contextFont);
						Question.setAlignment(Element.ALIGN_LEFT);
						document.add(Question);
						IOImage ioimage = new IOImage();
						PictureUtils picture = new PictureUtils();
						byte[] buffer = ioimage.readBolb(IDs[i]);
						if (buffer != null) {//有图片则显示
							Image image = Image.getInstance(buffer);
							image.setAlignment(Image.RIGHT);
							document.add(image);
						}
						m++;
					}
				}
				Paragraph Type4 = new Paragraph("\n四、简答题");
				Type4.setFont(TypeFont);
				Type4.setAlignment(Element.ALIGN_LEFT);
				document.add(Type4);
				for(int i=0,m=1;i<IDs.length;i++){
					Object[] QuestionInformation=dbutil.selectById(IDs[i]);//获取试题信息
					if(QuestionInformation[0]==null) continue;
					if ((int)QuestionInformation[3]==3) {
						String QuestionText = m + "、" + "("
								+ Scores[(int) QuestionInformation[3]] + "分) "
								+ (String) QuestionInformation[0];
						Paragraph Question = new Paragraph(QuestionText);
						Question.setFont(contextFont);
						Question.setAlignment(Element.ALIGN_LEFT);
						document.add(Question);
						IOImage ioimage = new IOImage();
						PictureUtils picture = new PictureUtils();
						byte[] buffer = ioimage.readBolb(IDs[i]);
						if (buffer != null) {//有图片则显示
							Image image = Image.getInstance(buffer);
							image.setAlignment(Image.RIGHT);
							document.add(image);
						}
						Paragraph Space=new Paragraph("\n\n\n\n\n\n\n");
						document.add(Space);
						m++;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			document.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "保存前请关闭文档");
		}
	}
}
