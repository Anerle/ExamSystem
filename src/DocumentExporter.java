
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
			// �����ֱ���д������
			WritableSheet ws = wwb.createSheet("����", 0);
			// ��ӱ���
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
			// �����
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
			JOptionPane.showMessageDialog(null, "��������ǰ��رչ�����");
		}
	}
	public void exportWord(String PaperID,File file) throws DocumentException,IOException {
		try {
			// ����ֽ�Ŵ�С
			Document document = new Document(PageSize.A4);
			// ����һ����д��(Writer)��document���������ͨ����д��(Writer)���Խ��ĵ�д�뵽������
			RtfWriter2.getInstance(document, new FileOutputStream(file));
			document.open();
			// ������������
			BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			// ����������
			Font titleFont = new Font(bfChinese, 16, Font.BOLD);
			// ����������
			Font TypeFont = new Font(bfChinese, 11, Font.BOLD);
			// ����������
			Font contextFont = new Font(bfChinese, 11, Font.NORMAL);
			//����
			Paragraph title = new Paragraph("���пƼ���ѧ2012-2013�ȵ�һѧ��"+"\n"+"����ϵ2010����������·�������ԡ���ĩ�Ծ�"+"\n\n");
			// ���ñ����ʽ���뷽ʽ
			title.setAlignment(Element.ALIGN_CENTER);
			title.setFont(titleFont);
			document.add(title);
			
			DButil dbutil=new DButil();
			try {
				String[] IDs=dbutil.checkPaper(PaperID);
				double[] Scores=dbutil.showTypeScore(PaperID);
				Paragraph Type1 = new Paragraph("\nһ��ѡ����");
				Type1.setFont(TypeFont);
				Type1.setAlignment(Element.ALIGN_LEFT);
				document.add(Type1);
				for(int i=0,m=1;i<IDs.length;i++){
					Object[] QuestionInformation=dbutil.selectById(IDs[i]);//��ȡ������Ϣ
					if(QuestionInformation[0]==null) continue;
					if ((int)QuestionInformation[3]==0) {
						String QuestionText = m + "��" + "("
								+ Scores[(int) QuestionInformation[3]] + "��) "
								+ (String) QuestionInformation[0];
						Paragraph Question = new Paragraph(QuestionText);
						Question.setFont(contextFont);
						Question.setAlignment(Element.ALIGN_LEFT);
						document.add(Question);
						IOImage ioimage = new IOImage();
						PictureUtils picture = new PictureUtils();
						byte[] buffer = ioimage.readBolb(IDs[i]);
						if (buffer != null) {//��ͼƬ����ʾ
							Image image = Image.getInstance(buffer);
							image.setAlignment(Image.RIGHT);
							document.add(image);
						}
						m++;
					}
				}
				Paragraph Type2 = new Paragraph("\n���������");
				Type2.setFont(TypeFont);
				Type2.setAlignment(Element.ALIGN_LEFT);
				document.add(Type2);
				for(int i=0,m=1;i<IDs.length;i++){
					Object[] QuestionInformation=dbutil.selectById(IDs[i]);//��ȡ������Ϣ
					if(QuestionInformation[0]==null) continue;
					if ((int)QuestionInformation[3]==1) {
						String QuestionText = m + "��" + "("
								+ Scores[(int) QuestionInformation[3]] + "��) "
								+ (String) QuestionInformation[0];
						Paragraph Question = new Paragraph(QuestionText);
						Question.setFont(contextFont);
						Question.setAlignment(Element.ALIGN_LEFT);
						document.add(Question);
						IOImage ioimage = new IOImage();
						PictureUtils picture = new PictureUtils();
						byte[] buffer = ioimage.readBolb(IDs[i]);
						if (buffer != null) {//��ͼƬ����ʾ
							Image image = Image.getInstance(buffer);
							image.setAlignment(Image.RIGHT);
							document.add(image);
						}
						m++;
					}
				}
				Paragraph Type3 = new Paragraph("\n�����ж���");
				Type3.setFont(TypeFont);
				Type3.setAlignment(Element.ALIGN_LEFT);
				document.add(Type3);
				for(int i=0,m=1;i<IDs.length;i++){
					Object[] QuestionInformation=dbutil.selectById(IDs[i]);//��ȡ������Ϣ
					if(QuestionInformation[0]==null) continue;
					if ((int)QuestionInformation[3]==2) {
						String QuestionText = m + "��" + "("
								+ Scores[(int) QuestionInformation[3]] + "��) "
								+ (String) QuestionInformation[0];
						Paragraph Question = new Paragraph(QuestionText);
						Question.setFont(contextFont);
						Question.setAlignment(Element.ALIGN_LEFT);
						document.add(Question);
						IOImage ioimage = new IOImage();
						PictureUtils picture = new PictureUtils();
						byte[] buffer = ioimage.readBolb(IDs[i]);
						if (buffer != null) {//��ͼƬ����ʾ
							Image image = Image.getInstance(buffer);
							image.setAlignment(Image.RIGHT);
							document.add(image);
						}
						m++;
					}
				}
				Paragraph Type4 = new Paragraph("\n�ġ������");
				Type4.setFont(TypeFont);
				Type4.setAlignment(Element.ALIGN_LEFT);
				document.add(Type4);
				for(int i=0,m=1;i<IDs.length;i++){
					Object[] QuestionInformation=dbutil.selectById(IDs[i]);//��ȡ������Ϣ
					if(QuestionInformation[0]==null) continue;
					if ((int)QuestionInformation[3]==3) {
						String QuestionText = m + "��" + "("
								+ Scores[(int) QuestionInformation[3]] + "��) "
								+ (String) QuestionInformation[0];
						Paragraph Question = new Paragraph(QuestionText);
						Question.setFont(contextFont);
						Question.setAlignment(Element.ALIGN_LEFT);
						document.add(Question);
						IOImage ioimage = new IOImage();
						PictureUtils picture = new PictureUtils();
						byte[] buffer = ioimage.readBolb(IDs[i]);
						if (buffer != null) {//��ͼƬ����ʾ
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
			JOptionPane.showMessageDialog(null, "����ǰ��ر��ĵ�");
		}
	}
}
