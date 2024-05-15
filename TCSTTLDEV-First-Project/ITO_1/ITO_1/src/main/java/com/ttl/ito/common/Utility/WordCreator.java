
package com.ttl.ito.common.Utility;

import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;

import javax.xml.namespace.QName;

import com.microsoft.schemas.office.office.CTLock;
import com.microsoft.schemas.office.office.CTOLEObject;
import com.microsoft.schemas.office.office.OLEObjectDocument;
import com.microsoft.schemas.office.office.STConnectType;
import com.microsoft.schemas.office.office.STOLEDrawAspect;
import com.microsoft.schemas.office.office.STOLEType;
import com.microsoft.schemas.office.office.STTrueFalseBlank;
import com.microsoft.schemas.vml.CTFormulas;
import com.microsoft.schemas.vml.CTGroup;
import com.microsoft.schemas.vml.CTImageData;
import com.microsoft.schemas.vml.CTPath;
import com.microsoft.schemas.vml.CTShape;
import com.microsoft.schemas.vml.CTShapetype;
import com.microsoft.schemas.vml.STExt;
import com.microsoft.schemas.vml.STStrokeJoinStyle;
import com.microsoft.schemas.vml.STTrueFalse;
import org.apache.poi.hpsf.ClassID;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.poifs.filesystem.Ole10Native;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xslf.usermodel.XSLFRelation;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlCursor;
import org.junit.Test;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtension;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;

import org.apache.log4j.Logger;
//import org.apache.poi.hssf.util.HSSFColor.BLACK;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTable.XWPFBorderType;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.bouncycastle.util.Times;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSimpleField;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblLayoutType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STStyleType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblLayoutType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPTable;
import com.sun.org.apache.xpath.internal.operations.Equals;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Single;
import com.ttl.ito.business.beans.QuotationForm;
import com.ttl.ito.business.beans.ReportBean;
import com.ttl.ito.business.daoImpl.QuotationDaoImpl;
import com.ttl.ito.internal.beans.PdfStylAttributes;

@Component
public class WordCreator {

	@Autowired
	private RunTimeExec runTimeExec;
	private static Logger logger = Logger.getLogger(WordCreator.class);

	public static void addWordContent(XWPFDocument document, QuotationForm quotationForm, ReportBean reportBean)
			throws InvalidFormatException, IOException {
		logger.info("make");
//		XWPFStyles styles = document.createStyles();
//
//		CTFonts fonts = CTFonts.Factory.newInstance();
//		fonts.setEastAsia("Arial");
//		fonts.setHAnsi("Arial");

		//styles.setDefaultFonts(fonts);
				// XWPFStyles styles = document.createStyles();
		//
//		 CTFonts fonts = CTFonts.Factory.newInstance();
//		 fonts.setEastAsia("CAMBRIA");
//		 fonts.setHAnsi("CAMBRIA");
//		 styles.setDefaultFonts(fonts);
		XWPFParagraph paragraph1 = null;
		XWPFRun run1 = null;
		XWPFParagraph paragraphtable = null;
		XWPFRun runtable = null;
		XWPFParagraph paragraph = null;
		XWPFRun run = null;
		
		List<ReportBean> data = new ArrayList<>();
		data = reportBean.getWordData();
		//variables for new tech offer
		String oppNumber=reportBean.getOpportunitySeqNum();
		String newDate=reportBean.getDate();
		String newOppNumner="";
		String newOppAddress=reportBean.getOppContactAddress();
		String custName=reportBean.getCustContactPersonName();
		String typeOfOff=reportBean.getTypeOfOff()+reportBean.getSubject();
        String enquiryReference=reportBean.getEnquiryReference();
        String userName=reportBean.getUserName();
		String newDesignation=reportBean.getDesignation()+reportBean.getDeptName();
		String newDept=reportBean.getDeptName();
		//
//				XWPFParagraph p1 = document.createParagraph();
//		p1.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r1 = p1.createRun();
//		XWPFRun r111 = p1.createRun();
//		XWPFRun r1111 = p1.createRun();
//		XWPFRun r112 = p1.createRun();
//
//		r1.setText("Reference No: ");
//		r1.setFontSize(11);
//		r111.setText(reportBean.getOpportunitySeqNum());
//		r111.setFontSize(11);
//		r111.setText("                                                                           		    ");
//		r1111.setText("Date: ");
//		r1111.setFontSize(11);
//		r112.setText(reportBean.getDate());
//		r112.setFontSize(11);
//		r112.addBreak();
//		r112.addBreak();
//
//		XWPFParagraph p2 = document.createParagraph();
//		p2.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r2 = p2.createRun();
//		XWPFRun r222 = p2.createRun();
		// r2.setText("Purchaser:");
//		r2.setFontSize(11);
		String word = reportBean.getOppName();
		if (word.contains(reportBean.getOpportunitySeqNum())) {
			String result = word.replace(reportBean.getOpportunitySeqNum(), "");
			String result1 = result.trim();
			newOppNumner = result1.replaceAll(".$", "");
			//r222.setText(reportBean.getCustSpace());
			//r222.setText(result2);
			
		}
		logger.info("in");
		int countt=0;
		int year = Calendar.getInstance().get(Calendar.YEAR);
int yearplus=year+1;
yearplus=yearplus-2000;
int counter1=0;
int counter2=0;


		for (XWPFParagraph p : document.getParagraphs()) {
		    List<XWPFRun> runs = p.getRuns();
		    if (runs != null) {
		    	countt++;
        		
		        for (XWPFRun r : runs) {
		            String text = r.getText(0);
		          
logger.info(text);
		            if (text != null && text.contains("Offer**12")) {
		        		logger.info("in1");
                        if(reportBean.getTypeOfOff().equalsIgnoreCase("Techno Commercial"))
                        {
        	                text = text.replace("Offer**12", "TECHNO-COMMERCIAL");
    		                r.setText(text, 0);
                        }
                        else
                        {
        	                text = text.replace("Offer**12", "TECHNICAL");
    		                r.setText(text, 0);
                        }
	
		            }

		            if (text != null && text.contains("DateOf**12")) {
		        		logger.info("in1");

		                text = text.replace("DateOf**12", year +"-" +yearplus );
		                r.setText(text, 0);
		            }

		            if (text != null && text.contains("Date**12")) {
		        		logger.info("in1");

		                text = text.replace("Date**12", newDate);
		                r.setText(text, 0);
		            }

		            if (text != null && text.contains("NameOpp**12")) {
		        		logger.info("in1");

		                text = text.replace("NameOpp**12", oppNumber);
		                r.setText(text, 0);
		            }
					if (text != null && text.contains("Name**12")) {
		        		logger.info("in2");

		                text = text.replace("Name**12", newOppNumner);
		                r.setText(text, 0);
		            }
					if (text != null && text.contains("Address**12")) {
		        		logger.info("in3");
                      if(newOppAddress!=null)
                      {
                    	  text = text.replace("Address**12", newOppAddress);
  		                r.setText(text, 0);
                      }
                      else
                      {
                    	  text = text.replace("Address**12", "");
  		                r.setText(text, 0); 
                      }
		              
		            }
					if (text != null && text.contains("NameQwe**12")) {
		        		logger.info("in4");
if(reportBean.getCustContactPersonName()!=null)
{
	text = text.replace("NameQwe**12", reportBean.getCustContactPersonName());
    r.setText(text, 0);

}
else
{
	text = text.replace("NameQwe**12", "");
    r.setText(text, 0);

}
		                		            }
					if (text != null && text.contains("NameSub**12")) {
		        		logger.info("in5");

		                text = text.replace("NameSub**12", typeOfOff);
		                r.setText(text, 0);
		            }
					if (text != null && text.contains("Enquiry**12")) {
		        		logger.info("in6");
if(enquiryReference!=null)
{
	
		                text = text.replace("Enquiry**12", enquiryReference);
		                r.setText(text, 0);
}
else
{
	 text = text.replace("Enquiry**12", "");
     r.setText(text, 0);

}


		            }
					if (text != null && text.contains("NameFisrt**2")) {
		        		logger.info("in7");

		                text = text.replace("NameFisrt**2", userName);
		                r.setText(text, 0);
		            }
					if (text != null && text.contains("Designation**12")) {
		        		logger.info("in8");

		                text = text.replace("Designation**12", newDesignation);
		                r.setText(text, 0);
		            }
					if (reportBean.getRegion() != null
							&& (reportBean.getRegion().equals("MENA") || reportBean.getRegion().equals("MEA"))) {
						if (text != null && text.contains("TURBINE") && counter1 == 0) {
							text = text.replace("TURBINE", "TURBINES");
							counter1 += 1;
							r.setText(text, 0);

						}
					}
					if (reportBean.getRegion() != null
							&& (reportBean.getRegion().equals("MENA") || reportBean.getRegion().equals("MEA"))) {
						if (text != null && text.contains("LIMITED") && counter2 == 0) {
							counter2 += 1;
							text = text.replace("LIMITED", "DMCC");
							r.setText(text, 0);

						}
					}
//					if (reportBean.getRegion() != null
//							&& (reportBean.getRegion().equals("MENA") || reportBean.getRegion().equals("MEA"))) {
//						if (text != null && text.contains("12-A,")) {
//							text = text.replace("12-A,",
//									"Regd.Off.:Unit No:4502-016,Mazaya BusinessAvenue BB2,Plot No:JLTE-PH2-BB2,Jumeirah Lakes Towers,Dubai,United Arab Emirates Tel: +971-4567 0752");
//							r.setText(text, 0);
//
//						}
//					}
					// if(text != null && text.contains("Tel:")){
					// text = text.replace("Tel:", "VAT No. 100043447000003");
					// r.setText(text, 0);
					//
					// }
//					if (reportBean.getRegion() != null
//							&& (reportBean.getRegion().equals("MENA") || reportBean.getRegion().equals("MEA"))) {
//						if (text != null) {
//							if (text.contains("Peenya") || text.contains("Industrial") || text.contains("Area,")
//									|| text.contains("Phase") || text.contains("-1,") || text.contains("Bengaluru,")
//									|| text.contains("Karnataka, 560058") || text.contains("4100")
//									|| text.contains("4000") || text.contains("Fax:") || text.contains("+91-80-2216")) {
//								text = text.replace(text, "");
//								r.setText(text, 0);
//							}
//
//						}
//					}
		        }
		    }
		}
		logger.info("12wertyhello");

    	logger.info(countt);

//		r222.setFontSize(11);
//		logger.info("out");
//
//		XWPFParagraph p3m = document.createParagraph();
//		p3m.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r3m = p3m.createRun();
//		XWPFRun r333m = p3m.createRun();
//
//		r333m.setText(reportBean.getOppContactAddress());
//		r333m.setFontSize(11);
//
//		XWPFParagraph p3 = document.createParagraph();
//		p3.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r3 = p3.createRun();
//		XWPFRun r333 = p3.createRun();
	String TurbineCode = null;
	TurbineCode = reportBean.getTurbineCode();
////		if (reportBean.getEndUserName() != null) {
////
////			r3.setText("Project Name:");
////			r3.setFontSize(11);
////
////			r333.setText(reportBean.getCustSpace());
////			r333.setText(reportBean.getEndUserName());
////			r333.setFontSize(11);
////			r333.addBreak();
////			r333.addBreak();
////
////		}
//
//		XWPFParagraph p5 = document.createParagraph();
//		p5.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r5 = p5.createRun();
//		XWPFRun r13 = p5.createRun();
//		r5.setText("Kind Attention:");
//		r5.setBold(true);
//		r5.setFontSize(11);
//		r13.setText(reportBean.getCustSpace());
//		r13.setText(reportBean.getCustContactPersonName());
//		r13.setFontSize(11);
//
//		XWPFParagraph p7 = document.createParagraph();
//		p7.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r7 = p7.createRun();
//		XWPFRun r17 = p7.createRun();
//		r7.setText("Sub:");
//		r7.setFontSize(11);
//		r7.setBold(true);
//		r17.setText(reportBean.getCustSpace());
//		r17.setText(reportBean.getTypeOfOff());
//		r17.setText(reportBean.getSubject());
//		r17.setFontSize(11);
//		r17.setUnderline(UnderlinePatterns.THICK);
//
//		XWPFParagraph p7m = document.createParagraph();
//		p7m.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r7m = p7m.createRun();
//		// XWPFRun r17 = p7.createRun();
//		r7m.setText("Enquiry Reference:");
//		r7m.setFontSize(11);
//		r7m.setText(reportBean.getCustSpace());
//		r7m.setText(reportBean.getEnquiryReference());
//		r7m.setFontSize(11);
//		r7m.addBreak();
//		r7m.addBreak();
//
//		XWPFParagraph p8 = document.createParagraph();
//		p8.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r8 = p8.createRun();
//		r8.setText("Dear Sir,");
//		r8.setFontSize(11);
//		r8.addBreak();
//
//		XWPFParagraph p9 = document.createParagraph();
//		p9.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r9 = p9.createRun();
//		r9.setText("We thank you for your enquiry. Further to the same, we are pleased to submit herewith our"
//				+ reportBean.getCustSpace() + reportBean.getTypeOfOff() + " offer for your kind perusal.");
//		r9.setFontSize(11);
//		r9.addBreak();
//
//		XWPFParagraph p10 = document.createParagraph();
//		p10.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r10 = p10.createRun();
//		r10.setText(
//				"We hope you will find the same in line with your requirements. However, shall there be any clarifications, please feel free to get in touch with us. ");
//		r10.setFontSize(11);
//		r10.addBreak();
//
//		XWPFParagraph p11 = document.createParagraph();
//		p11.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r11 = p11.createRun();
//		r11.setText("Thanking you and assuring you of our best attention at all times.");
//		r11.setFontSize(11);
//		r11.addBreak();
//
//		XWPFParagraph p12 = document.createParagraph();
//		p12.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r12 = p12.createRun();
//		XWPFRun r14 = p12.createRun();
//		r12.setText("Yours truly,");
//		r12.addBreak();
//		r14.setText("for TRIVENI TURBINE LTD.");
//		r14.setBold(true);
//		r12.setFontSize(11);
//
//		XWPFParagraph p11m = document.createParagraph();
//		p11m.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r11m = p11m.createRun();
//		XWPFRun r11n = p11m.createRun();
//		XWPFRun r11o = p11m.createRun();
//		r11m.setText(reportBean.getUserName());
//		r11m.addBreak();
//		r11n.setText(reportBean.getDesignation());
//		r11n.addBreak();
//		r11o.setText(reportBean.getDeptName());
//		r11m.setBold(true);
//		r11n.setBold(true);
//		r11o.setBold(true);
//		r11m.setFontSize(11);
	//		XWPFParagraph p21 = document.createParagraph();
//		p21.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r21 = p21.createRun();
//		p21.setPageBreak(true);
//		r21.setText("Dear Customer, ");
//		// r21.setFontFamily("Cambria (Headings)");
//		r21.setFontSize(11);
//		r21.addBreak();
//		
//		
//		XWPFParagraph p22 = document.createParagraph();
//		p22.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r22 = p22.createRun();
//		r22.setText(
//				"Triveni Turbines is an AS 9100D (Based on and including ISO 9001:2015) and ISO 14001:2015 And also OHSAS 45001:2018 certified company.  As a commitment to environment we have laid out our environment policy which will be practiced during our operations, manufacturing and other areas of operation.   ");
//		// r22.setFontFamily("Cambria (Headings)");
//		r22.setFontSize(11);
//		r22.addBreak();
//
//		XWPFParagraph p23 = document.createParagraph();
//		p23.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r23 = p23.createRun();
//		r23.setText("All our products will confine to broad guidelines laid out by our EOHS policy. ");
//		// r23.setFontFamily("Cambria (Headings)");
//		r23.setFontSize(11);
//		r23.addBreak();
//
//		XWPFParagraph p24 = document.createParagraph();
//		p24.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r24 = p24.createRun();
//		r24.setText("For TRIVENI TURBINE LTD., ");
//		// r24.setFontFamily("Cambria (Headings)");
//		r24.setFontSize(11);
//		r24.addBreak();
//
//		XWPFParagraph p25 = document.createParagraph();
//		p25.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r25 = p25.createRun();
//		r25.setText("Authorized Signatory ");
//		// r25.setFontFamily("Cambria (Headings)");
//		r25.setFontSize(11);
//		r25.addBreak();
//
//		XWPFParagraph p26 = document.createParagraph();
//		p26.setAlignment(ParagraphAlignment.CENTER);
//		XWPFRun r26 = p26.createRun();
//		r26.setText("ENVIRONMENT AND OCCUPATION HEALTH & SAFETY (EOHS) POLICY  ");
//		r26.setBold(true);
//		// r26.setFontFamily("Cambria (Headings)");
//		r26.setUnderline(UnderlinePatterns.THICK);
//		r26.setFontSize(11);
//
//		XWPFParagraph p27 = document.createParagraph();
//		p27.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r27 = p27.createRun();
//		r27.setText(
//				"We at Triveni Turbine Limited manufacturing Steam Turbines and related products including the accessories are committed to:");
//		// r27.setFontFamily("Cambria (Headings)");
//		r27.setFontSize(11);
//		r27.addBreak();
//
//		XWPFParagraph p28 = document.createParagraph();
//		p28.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r28 = p28.createRun();
//		r28.setText(
//				"*	Produce the product and provide services to enhance the Customer satisfaction by ensuring prevention of pollution, reduction of waste, conservation of resources and creating safe working environment by ensuring prevention of injury and ill health");
//		// r28.setFontFamily("Cambria (Headings)");
//		r28.setFontSize(11);
//
//		XWPFParagraph p29 = document.createParagraph();
//		p29.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r29 = p29.createRun();
//		r29.setText("*	Comply will all applicable legal and other requirements with relation to EOHS.");
//		// r29.setFontFamily("Cambria (Headings)");
//		r29.setFontSize(11);
//
//		XWPFParagraph p30 = document.createParagraph();
//		p30.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r30 = p30.createRun();
//		r30.setText("*	Establish appropriate objectives in respect of EOHS to facilitate the continual improvement.");
//		// r30.setFontFamily("Cambria (Headings)");
//		r30.setFontSize(11);
//
//		XWPFParagraph p31 = document.createParagraph();
//		p31.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r31 = p31.createRun();
//		r31.setText(
//				"*	Communicate the policy effectively to all persons working under the control of the organization including relevant interested parties such as Sub contractors, Suppliers, Transporters and Customers.");
//		// r31.setFontFamily("Cambria (Headings)");
//		r31.setFontSize(11);
//
//		XWPFParagraph p32 = document.createParagraph();
//		p32.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r32 = p32.createRun();
//		r32.setText(
//				"*	Persons working on behalf of the organization shall be provided with necessary competency in respect of EOHS with the intent that they are made aware of their individual obligations.");
//		// r32.setFontFamily("Cambria (Headings)");
//		r32.setFontSize(11);
//		r32.addBreak();
//		r32.addBreak();
//
//		XWPFParagraph p33 = document.createParagraph();
//		p33.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r33 = p33.createRun();
//		r33.setText("EXECUTIVE DIRECTOR");
//		// r33.setFontFamily("Cambria (Headings)");
//		r33.setFontSize(11);
//		// r33.addBreak();
//		// r33.addBreak();
//		// r33.addBreak();
//		// r33.addBreak();
//		// r33.addBreak();
//
//		XWPFParagraph paragraph123 = document.createParagraph();
//		paragraph123.setPageBreak(true);
//
//		XWPFParagraph p34 = document.createParagraph();
//		p34.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r34 = p34.createRun();
//		r34.setText("Why Triveni ");
//		r34.setBold(true);
//		// r34.setFontFamily("Cambria (Headings)");
//		r34.setUnderline(UnderlinePatterns.THICK);
//		r34.setFontSize(11);
//		r34.addBreak();
//
//		XWPFParagraph p35 = document.createParagraph();
//		p35.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r35 = p35.createRun();
//		r35.setText("*	Most preferred supplier, with a market share of over 60% in India in the range up to 30MW");
//		// r35.setFontFamily("Cambria (Headings)");
//		r35.setFontSize(11);
//		r35.addBreak();
//
//		XWPFParagraph p36 = document.createParagraph();
//		p36.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r36 = p36.createRun();
//		r36.setText("*	Over 4000 installations across the globe in over 70 countries in all industry segments");
//		// r36.setFontFamily("Cambria (Headings)");
//		r36.setFontSize(11);
//		r36.addBreak();
//
//		XWPFParagraph p37 = document.createParagraph();
//		p37.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r37 = p37.createRun();
//		r37.setText(
//				"*	Over 500 turbines in process co-generation plants and nearly 100 turbines in biomass based IPPs");
//		// r37.setFontFamily("Cambria (Headings)");
//		r37.setFontSize(11);
//		r37.addBreak();
//
//		XWPFParagraph p38 = document.createParagraph();
//		p38.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r38 = p38.createRun();
//		r38.setText("*	Industry Bench Mark Delivery time. Helps our customers commission their power plants faster");
//		// r38.setFontFamily("Cambria (Headings)");
//		r38.setFontSize(11);
//		r38.addBreak();
//
//		XWPFParagraph p39 = document.createParagraph();
//		p39.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r39 = p39.createRun();
//		r39.setText(
//				"	* Best in class manufacturing plant in Bangalore with fleet of 4 Axis / 5 Axis machine. Manufacturing capacity of 200 turbines per year");
//		// r39.setFontFamily("Cambria (Headings)");
//		r39.setFontSize(11);
//		r39.addBreak();
//
//		XWPFParagraph p40 = document.createParagraph();
//		p40.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r40 = p40.createRun();
//		r40.setText(
//				"* Every turbine is put through a Mechanical Steam Run Test (at factory, before dispatch) at Full speed to test vibration, safety trip etc.");
//		// r40.setFontFamily("Cambria (Headings)");
//		r40.setFontSize(11);
//		r40.addBreak();
//
//		XWPFParagraph p41 = document.createParagraph();
//		p41.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r41 = p41.createRun();
//		r41.setText(
//				"*	SCHENK High Speed Vacuum Tunnel balancing for precise dynamic balancing of large rotors. Ensures operation reliability");
//		// r41.setFontFamily("Cambria (Headings)");
//		r41.setFontSize(11);
//		r41.addBreak();
//
//		XWPFParagraph p42 = document.createParagraph();
//		p42.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r42 = p42.createRun();
//		r42.setText(
//				"*	24 x 7 Service centres closer to our customers - 13 service offices across India with over 150 service personnel. We respond within 8 hours of an emergency call");
//		// r42.setFontFamily("Cambria (Headings)");
//		r42.setFontSize(11);
//		r42.addBreak();
//
//		XWPFParagraph p43 = document.createParagraph();
//		p43.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r43 = p43.createRun();
//		r43.setText("* Life time support for spares and services to maximize the return on investment. ");
//		// r43.setFontFamily("Cambria (Headings)");
//		r43.setFontSize(11);
//		r43.addBreak();
//
//		XWPFParagraph p44 = document.createParagraph();
//		p44.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r44 = p44.createRun();
//		r44.setText(
//				"*	Refurbishing solutions offered up to 150 MW for any make of turbine to enhance efficiency, availability and reliability.");
//		// r44.setFontFamily("Cambria (Headings)");
//		r44.setFontSize(11);
//		r44.addBreak();
//
//		XWPFParagraph p45 = document.createParagraph();
//		p45.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r45 = p45.createRun();
//		r45.setText(
//				"*A decade old Research and Development department for continuous improvement of existing models and development of new models. Association with world-renowned design houses and academia - IISc., Cambridge , Polimi, Impact Tech. (Lockheed Martin),Concepts NREC, USA. ");
//		// r45.setFontFamily("Cambria (Headings)");
//
//		r45.setFontSize(11);
		// r45.addBreak();

		// // run1.addBreak(BreakType.PAGE);
		// //// paragraph1.setBorderTop(Borders.SINGLE);
		//
		// addCustomHeadingStyle(document, "heading 1", 1);
		// addCustomHeadingStyle(document, "heading 2", 2);
		//
		// // the body content

		// Print table of contents

		int i = 0;
		int chapter = 0;
		String chapterindent = "";
		String prevcategoryname = null;

		XWPFParagraph p123 = document.createParagraph();

		p123.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r123 = p123.createRun();
		p123.setPageBreak(true);
		r123.setText("INDEX ");
		r123.setBold(true);
		// r123.setFontFamily("Cambria (Headings)");
		r123.setUnderline(UnderlinePatterns.THICK);
		r123.setFontSize(11);

		for (i = 0; i < reportBean.getWordData().size(); i++) {

			if (i == QuotationDaoImpl.size)
				break;

			String categoryname = reportBean.getWordData().get(i).getCategoryName();

			String subcategorytemp = reportBean.getWordData().get(i).getSubCategoryName();

			if (!categoryname.equals(prevcategoryname)) {
				paragraph = document.createParagraph();
				run = paragraph.createRun();

				chapter = chapter + 1;
				chapterindent = chapter + ".";
				run.setText(chapterindent);
				run.setText("         ");
				run.setText(categoryname);
				logger.info("inside index");
				logger.info("Previous Category: " + prevcategoryname + "  Present Category: " + categoryname);
				 run.setFontFamily("Cambria (Headings)");
				run.setFontSize(11);
				paragraph.setStyle("heading 1");
			}

			if (subcategorytemp != null) // Compare two strings
			{
				paragraph = document.createParagraph();
				run = paragraph.createRun();
				run.setText("              ");
				run.setText(reportBean.getWordData().get(i).getSubCategoryName());
				logger.info(subcategorytemp);
				// run.setFontFamily("Cambria (Headings)");
				run.setFontSize(11);
				paragraph.setStyle("heading 2");
			}

			prevcategoryname = categoryname;
		}
		// run.addBreak();
		// run.addBreak();
		// run.addBreak();
		// run.addBreak();
		// run.addBreak();
		// run.addBreak();
		// run.addBreak();
		// run.addBreak();
		// paragraph123 = document.createParagraph();''
		// paragraph123.setPageBreak(true);
		XWPFParagraph paragraph1234 = document.createParagraph();
		paragraph1234.setPageBreak(true);

		// To print scope of supply(chapter 1)
		// to print category name
		if (QuotationDaoImpl.size1 != 0) {

			paragraph = document.createParagraph();
			run = paragraph.createRun();
			run.setText("1  Scope of Supply & Offer Annexure");
			run.setBold(true);
			 run.setFontFamily("Cambria (Headings)");
			run.setFontSize(13);
			//run.setUnderline(UnderlinePatterns.THICK);

			paragraph.setStyle("heading 1");
		}

		String prevcategoryname1 = null;
		String prevsubcategoryname = null;
		String previtemtemp = null;
		String prevsubCatName = null;
		int k = 0;
		if (QuotationDaoImpl.size1 != 0) {
			for (k = i; k < reportBean.getWordData().size(); k++) {

				if (k == QuotationDaoImpl.size1)
					break;

				String categoryname = reportBean.getWordData().get(k).getCategoryName();
				String subcategoryname = reportBean.getWordData().get(k).getSubCategoryName();
				String itemtemp = reportBean.getWordData().get(k).getItemName();
				String subCatName = reportBean.getWordData().get(k).getSubCatName();

				// to print subcategory name
				if (!subcategoryname.equals(prevsubcategoryname)) {
					paragraph = document.createParagraph();
					run = paragraph.createRun();

					run.setText(subcategoryname);
					run.setBold(true);
					logger.info("inside scope of supply");
					logger.info(subcategoryname);
					// run.setFontFamily("Cambria (Headings)");
					run.setFontSize(11);
					paragraph.setStyle("heading 1");
				}

				// to print Item name
				if (!itemtemp.equals(previtemtemp)) {
					paragraph = document.createParagraph();
					run = paragraph.createRun();
					run.setText("           ");
					run.setText(reportBean.getWordData().get(k).getItemName());
					logger.info(itemtemp);
					// run.setFontFamily("Cambria (Headings)");
					run.setFontSize(11);

				}

				// to print sub-cat-nm
				if (subCatName != null) {
					if (!subCatName.equalsIgnoreCase("NULL")) {

						if (!subCatName.equals(prevsubCatName)) {
							paragraph = document.createParagraph();
							run = paragraph.createRun();
							run.setText("           " + "    ");

							run.setText(reportBean.getWordData().get(k).getSubCatName());
							logger.info(subCatName);
							// run.setFontFamily("Cambria (Headings)");
							run.setFontSize(11);

						}
					}
				}
				// if (itemtemp.equals("A.9 Mechanical Auxiliaries")) {
				// paragraph = document.createParagraph();
				// run1 = paragraph.createRun();
				// run1.setText(" ");
				// run1.setText(subCatName);
				//
				// run1.setFontFamily("Cambria (Headings)");
				// run1.setFontSize(11);
				// }
				//
				// if (itemtemp.equals("A.10 Mechanical Extended Scope")) {
				// paragraph = document.createParagraph();
				// XWPFRun r99 = paragraph.createRun();
				// r99 = paragraph.createRun();
				// r99.setText(" ");
				// r99.setText(subCatName);
				//
				// r99.setFontFamily("Cambria (Headings)");
				// r99.setFontSize(11);
				// }

				// prevcategoryname1 = categoryname;
				prevsubcategoryname = subcategoryname;
				previtemtemp = itemtemp;
				prevsubCatName = subCatName;

			}

		}

		// To print the technical details / technical specifications(chapter 2)

		int j = 0;
		int prevqtytemp = 0;
		String prevcategoryname2 = null;
		String prevsubcategoryname1 = null;
		String previtemname = null;
		String prevsubitemname = null;
		String prevsubitemtypename = null;
		String previtemcode = null;
		String prevtechremarks = null;
		String prevfixedtext1 = null;
		String prevfixedtext5 = null;
		XWPFTable table = null;
		int countforf2floop = 0;
		int countsd = 0;
		for (j = k; j < reportBean.getWordData().size(); j++) {

			String categoryname = null;
			String subcategoryname = null;
			String itemcode = null;
			String colValCd = null;
			String techremarks = null;
			String Category = null;
			String colName = null;
			String FixedText1 = null;
			String FixedText2 = null;
			String FixedText3 = null;
			String FixedText4 = null;
			String FixedText5 = null;
			// String FixedText6 = null;
			String VarianttypeName = null;
			String SpaceF2f = null;
			int numberOfRows = 0;
			int qty = 0;
			int iData = 0;
			// get row
			XWPFTableRow tableRowOne1 = null;

			if (j == QuotationDaoImpl.size2) {
				if (previtemcode != null) {
					if (previtemcode.equals("COND_SYST") && countforf2floop > 0) {

						paragraph = document.createParagraph();
						run1 = paragraph.createRun();

						run1.addBreak();
						run1.setText("Accessories :");
						run1.addBreak();
						run1.addBreak();
						run1.setText("-	Rupture disc for condenser protection.");
						run1.addBreak();
						run1.addBreak();
						run1.setText("-	Dry air/vapour line within specified battery limit.");
						// run1.setFontFamily("Cambria (Headings)");
						run1.setFontSize(11);

					}
				}
				break;
			}
			countforf2floop = countforf2floop + 1;

			categoryname = reportBean.getWordData().get(j).getCategoryName();
			subcategoryname = reportBean.getWordData().get(j).getSubCategoryName();
			itemcode = reportBean.getWordData().get(j).getItemCode();
			techremarks = reportBean.getWordData().get(j).getTechRemarks();
			VarianttypeName = reportBean.getWordData().get(j).getVarientTypeName();
			Category = reportBean.getWordData().get(j).getCategory();
			FixedText1 = reportBean.getWordData().get(j).getFixedText1();
			FixedText2 = reportBean.getWordData().get(j).getFixedText2();
			FixedText3 = reportBean.getWordData().get(j).getFixedText3();
			FixedText4 = reportBean.getWordData().get(j).getFixedText4();
			FixedText5 = reportBean.getWordData().get(j).getFixedText5();
			// FixedText6 = reportBean.getWordData().get(j).getFixedText6();
			// logger.info(FixedText6);
			colValCd = reportBean.getWordData().get(j).getColValCd();
			numberOfRows = reportBean.getWordData().get(j).getNumberOfRows();
			SpaceF2f = reportBean.getWordData().get(j).getSpaceF2f();

			String itemtemp = null;
			int qtytemp = 0;
			String subitemname = null;
			String subitemtypename = null;

			itemtemp = reportBean.getWordData().get(j).getItemName();
			qtytemp = reportBean.getWordData().get(j).getQuantity();
			subitemname = reportBean.getWordData().get(j).getSubItemName();
			subitemtypename = reportBean.getWordData().get(j).getSubItemTypeName();
			colName = reportBean.getWordData().get(j).getColNm();
			// to print category name
			if (!categoryname.equals(prevcategoryname2)) {
				paragraph = document.createParagraph();
				run = paragraph.createRun();
				// XWPFParagraph paragraph1234 = document.createParagraph();
				paragraph.setPageBreak(true);
				run.setText(categoryname);
				run.setBold(true);
				run.setFontFamily("Cambria (Headings)");
				run.setFontSize(13);
				paragraph.setStyle("heading 1");
			}

			// to print subcategory name
			if (!subcategoryname.equals(prevsubcategoryname1)) {
				paragraph = document.createParagraph();
				run = paragraph.createRun();

				run.setText(subcategoryname);
				run.setBold(true);
				run.setFontFamily("Cambria (Headings)");
				run.setFontSize(11);
				paragraph.setStyle("heading 1");

			}

			// to print item name only once
			if (!itemtemp.equals(previtemname)) {
				paragraph = document.createParagraph();
				run = paragraph.createRun();
				// run.setText(" ");
				run.setText(reportBean.getWordData().get(j).getItemName());
				if ((qtytemp) != prevqtytemp) {
					if (reportBean.getWordData().get(j).getQuantity() > 1) {
						run.setText(SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f
								+ SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f
								+ SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f
								+ SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f
								+ SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f
								+ SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f
								+ SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f
								+ SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f
								+ SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f
								+ SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f
								+ SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f + SpaceF2f
								+ SpaceF2f + SpaceF2f
								+ String.valueOf("Quantity :" + reportBean.getWordData().get(j).getQuantity()));
					}
				}
				run.setBold(true);
				run.setFontFamily("Cambria (Headings)");
				run.setFontSize(12);

				
				if (itemcode.equals("GLAND_SYS")) {
					paragraph = document.createParagraph();
					run1 = paragraph.createRun();
					if (colValCd.equals("Manual")) {
						// run1.setText(" ");
						run1.setText(FixedText1);
					} else {
						// run1.setText(" ");
						run1.setText(colValCd);
						run1.setText(",  ");
						run1.setText(FixedText1);
					}
					// run1.setFontFamily("Cambria (Headings)");
					run1.setFontSize(11);
				}

				// To print the static text of steam turbines back pressure
				if (TurbineCode != null && TurbineCode.equals("BP")) {
					if ((FixedText1 != null) && ((itemcode.equals("STEM_MD")) && (!itemcode.equals("GLAND_SYS")))) {
						if (!FixedText1.equals(prevfixedtext1)) {
							paragraph = document.createParagraph();
							run = paragraph.createRun();
							// run.setText(" ");
							if (FixedText1 != null) {
								if (!FixedText1.equals("NULL")) {
									run.setText(FixedText1);

								}
							}
							if (FixedText2 != null) {
								if (!FixedText2.equals("NULL")) {

									run.setText(FixedText2);

								}
							}
							if (FixedText3 != null) {
								if (!FixedText3.equals("NULL")) {

									run.setText(FixedText3);
									run.setText(SpaceF2f);

								}
							}
							if (VarianttypeName != null)
								if (!VarianttypeName.equals("NULL")) {
									run.setText(SpaceF2f);
									run.setText(VarianttypeName);

								}
						}
						if (FixedText4 != null) {
							if (!FixedText4.equals("NULL")) {
								run.setText(SpaceF2f);
								run.setText(FixedText4);

							}
						}

//						if (FixedText5 != null) {
//							if (!FixedText5.equals("NULL")) {
//								if (!itemcode.equals("STEM_MD")) {
//									run.setText(FixedText5);
//								}
//							}
//
//							// run.setFontFamily("Cambria (Headings)");
//							run.setFontSize(11);
//
//						}
					}
				}
				if (TurbineCode != null && TurbineCode.equals("CD")) {
					if ((FixedText1 != null) && ((itemcode.equals("STEM_MD")) && (!itemcode.equals("GLAND_SYS")))) {
						if (!FixedText1.equals(prevfixedtext1)) {
							paragraph = document.createParagraph();
							run = paragraph.createRun();
							//// run.setText(" ");
							if (FixedText1 != null) {
								if (!FixedText1.equals("NULL")) {
									run.setText(FixedText1);

								}
							}
							if (FixedText2 != null) {
								if (!FixedText2.equals("NULL")) {

									run.setText(FixedText2);

								}
							}

							if (FixedText3 != null) {
								if (!FixedText3.equals("NULL")) {

									run.setText(FixedText3);
									run.setText(SpaceF2f);
								}
							}
							if (VarianttypeName != null) {
								if (!VarianttypeName.equals("NULL")) {
									run.setText(SpaceF2f);
									run.setText(VarianttypeName);

								}
							}
							if (FixedText4 != null) {
								if (!FixedText4.equals("NULL")) {
									run.setText(SpaceF2f);
									run.setText(FixedText4);

								}
							}
//							if (FixedText5 != null) {
//								if (!FixedText5.equals("NULL")) {
//									if (!itemcode.equals("STEM_MD")) {
//										run.setText(FixedText5);
//									}
//								}
//							}

							// run.setFontFamily("Cambria (Headings)");
							run.setFontSize(11);

						}
					}
				}

				// // To print the static text of lubeoil control system
				//
				if (Category != null && (Category.equals("A") || Category.equals("B") || Category.equals("C")
						|| Category.equals("D") || Category.equals("E") || Category.equals("F") || Category.equals("G")
						|| Category.equals("H") || Category.equals("I") || Category.equals("J") || Category.equals("K")
						|| Category.equals("L") || Category.equals("M"))) {
					if ((FixedText1 != null) && ((itemcode.equals("LUB_OIL")) && (!itemcode.equals("GLAND_SYS")))) {
						if (!FixedText1.equals(prevfixedtext1)) {
							paragraph = document.createParagraph();
							run = paragraph.createRun();
							// run.setText(" ");
							if (FixedText1 != null) {
								if (!FixedText1.equals("NULL")) {
									run.setText(FixedText1);
								}
							}

							if (FixedText2 != null) {
								if (!FixedText2.equals("NULL")) {
									run.setText(FixedText2);
								}
							}
							if (FixedText3 != null) {
								if (!FixedText3.equals("NULL")) {

									if (FixedText3.contains("\n")) {

										String[] arrOfStr = FixedText3.split("\n", 20);
										for (int x1 = 0; x1 < arrOfStr.length; x1++) {
											XWPFParagraph paragraph21 = document.createParagraph();

											// System.out.println(table.getNumberOfRows());
											// paragraph21.setAlignment(ParagraphAlignment.CENTER);
											XWPFRun run123 = paragraph21.createRun();
											run123.setFontSize(11);
											// run123.setFontFamily("Cambria
											// (Headings)");
											run123.setText(arrOfStr[x1]);

											// run123.addBreak();

										}
									} else {
										paragraph1 = document.createParagraph();
										run = paragraph1.createRun();

										run.setText(FixedText3);
										run.setFontSize(11);
									}

									// run.setText(FixedText3);
								}

							}

						}
					}

				}

				if (itemcode.equals("COND_SYST")) {
					paragraph = document.createParagraph();
					run1 = paragraph.createRun();

					run1.setText("Condensing system comprising of:");

					// run1.setFontFamily("Cambria (Headings)");
					run1.setFontSize(11);
				}
				if (reportBean.getWordData().get(j).getSubItemName() == null
						&& itemcode.equalsIgnoreCase(reportBean.getWordData().get(j + 1).getItemCode())) {

					table = document.createTable();
					CTTblLayoutType type = table.getCTTbl().getTblPr().addNewTblLayout();
					type.setType(STTblLayoutType.FIXED);
					// CTTblWidth width =
					// table.getCTTbl().addNewTblPr().addNewTblW();
					// width.setType(STTblWidth.DXA);
					// width.setW(BigInteger.valueOf(9072));
					CTTblPr tblpro = table.getCTTbl().getTblPr();

					CTTblBorders borders = tblpro.addNewTblBorders();
					borders.addNewBottom().setVal(STBorder.NONE);
					borders.addNewLeft().setVal(STBorder.NONE);
					borders.addNewRight().setVal(STBorder.NONE);
					borders.addNewTop().setVal(STBorder.NONE);
					// also inner borders
					borders.addNewInsideH().setVal(STBorder.NONE);
					borders.addNewInsideV().setVal(STBorder.NONE);
					table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
					table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1450));
					table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
					setTableAlign(table, ParagraphAlignment.LEFT);// Creating
																	// table
																	// only once
					tableRowOne1 = table.getRow(0);

				}

				// This is to print the technical remarks at the end of the item
				if (!itemcode.equals(previtemcode) && itemcode != null) {
					logger.info("inside f2f tech remarks if 1");
					if (techremarks != null && (!techremarks.equals("NULL"))) {
						logger.info("inside f2f tech remarks if 2");
						if (!techremarks.equals(prevtechremarks)) {
							logger.info("inside f2f tech remarks if 3");
							paragraph = document.createParagraph();
							run = paragraph.createRun();
							logger.info(techremarks);
							run.setText(techremarks);

							// run.setFontFamily("Cambria (Headings)");
							run.setFontSize(11);
						} // if (techremarks != null)
					} // if (techremarks != null)

					

				} // if (!itemcode.equals(previtemcode) && itemcode != null)

			}

			// to print sub item name
			// To print the sub item name only once
			if (subitemname != null) {
				if (!subitemname.equals(prevsubitemname)) {
					paragraph = document.createParagraph();
					run = paragraph.createRun();
					// run.setText(" ");
					run.setText(reportBean.getWordData().get(j).getSubItemName());
					run.setText(":");
					run.setBold(true);
					run.setFontFamily("Cambria (Headings)");
					run.setFontSize(12);

					boolean isFound = false;
					isFound = subitemname.contains("Bearings");
					if (isFound == false) {

						if (reportBean.getWordData().get(j).getSubItemTypeName() == null) {
							table = document.createTable();

							CTTblLayoutType type = table.getCTTbl().getTblPr().addNewTblLayout();
							type.setType(STTblLayoutType.FIXED);

							CTTblPr tblpro = table.getCTTbl().getTblPr();

							CTTblBorders borders = tblpro.addNewTblBorders();
							borders.addNewBottom().setVal(STBorder.NONE);
							borders.addNewLeft().setVal(STBorder.NONE);
							borders.addNewRight().setVal(STBorder.NONE);
							borders.addNewTop().setVal(STBorder.NONE);
							// also inner borders
							borders.addNewInsideH().setVal(STBorder.NONE);
							borders.addNewInsideV().setVal(STBorder.NONE);
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1450));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));

							setTableAlign(table, ParagraphAlignment.LEFT);
							tableRowOne1 = table.getRow(0);
						}

					}

					if (subitemname.equals("ACCESSORIES")) {
						paragraph = document.createParagraph();
						run1 = paragraph.createRun();
						run1.setText("-	Rupture disc for condenser protection.");
						run1.addBreak();
						run1.addBreak();
						run1.setText("-	Dry air/vapor line within specified battery limit.");
						// run1.setFontFamily("Cambria (Headings)");
						run1.setFontSize(11);
					}

				}

				// To print the lhs and rhs of sub item
				if (subitemname != null && subitemtypename == null && !subitemname.equals("ACCESSORIES")) // Compare
																											// two
																											// strings
				{
					String colunmname = reportBean.getWordData().get(j).getColNm();

					String colValCode = reportBean.getWordData().get(j).getColValCd();

					if (colunmname != null && colValCode != null) {

						if (tableRowOne1 != null) {
							tableRowOne1.getCell(0).setText(colunmname);
							// tableRowOne1.addNewTableCell().setText(" ");

							// tableRowOne1.addNewTableCell().setText(colunmname);
							tableRowOne1.addNewTableCell();
							XWPFParagraph paragraph12 = tableRowOne1.getCell(1).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");
							tableRowOne1.addNewTableCell().setText(colValCode);
							tableRowOne1 = null;
						}

						else {
							XWPFTableRow tableRowOne = table.createRow();
							tableRowOne.getCell(0).setText(colunmname);
							// tableRowOne.getCell(1).setText(" ");

							// tableRowOne.getCell(1).setText(":");
							XWPFParagraph paragraph12 = tableRowOne.getCell(1).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");
							// tableRowOne.getCell(2).setText(colValCode);
							if (colValCode.contains("\n")) {

								String[] arrOfStr = colValCode.split("\n", 20);
								for (int x1 = 0; x1 < arrOfStr.length; x1++) {
									XWPFParagraph paragraph21 = tableRowOne.getCell(2).getParagraphs().get(0);

									// System.out.println(table.getNumberOfRows());
									// paragraph21.setAlignment(ParagraphAlignment.CENTER);
									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									// run123.setFontFamily("Cambria
									// (Headings)");
									run123.setText(arrOfStr[x1]);
									run123.addBreak();

								}
							} else {
								tableRowOne.getCell(2).setText(colValCode);
							}

							// tableRowOne.getCell(3).setText(colValCode);

						}
						// run.setFontFamily("Cambria (Headings)");
						run.setFontSize(11);
					}
					// To print technical remarks of the sub itemq

					if (j + 1 == reportBean.getWordData().size()) {
						paragraph = document.createParagraph();
						run = paragraph.createRun();
						// run.setText(" ");
						run.setText(techremarks);
						run.addBreak();
						run.addBreak();
						// run.setFontFamily("Cambria (Headings)");
						run.setFontSize(11);
					}
				}
			} // if (subitemname != null)

			if (subitemtypename != null)
				if (!subitemtypename.equals(prevsubitemtypename)) {
					STBorder.Enum borderType = STBorder.SINGLE;

					paragraph = document.createParagraph();
					run = paragraph.createRun();
					// run.setText(" ");
					run.setText(reportBean.getWordData().get(j).getSubItemTypeName());
					run.setBold(true);
					run.setFontFamily("Cambria (Headings)");
					run.setFontSize(12);

					table = document.createTable();
					CTTblLayoutType type = table.getCTTbl().getTblPr().addNewTblLayout();
					type.setType(STTblLayoutType.FIXED);

					CTTblPr tblpro = table.getCTTbl().getTblPr();

					CTTblBorders borders = tblpro.addNewTblBorders();
					borders.addNewBottom().setVal(STBorder.NONE);
					borders.addNewLeft().setVal(STBorder.NONE);
					borders.addNewRight().setVal(STBorder.NONE);
					borders.addNewTop().setVal(STBorder.NONE);
					// also inner borders
					borders.addNewInsideH().setVal(STBorder.NONE);
					borders.addNewInsideV().setVal(STBorder.NONE);
					table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
					table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1450));
					table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));

					setTableAlign(table, ParagraphAlignment.LEFT);// Creating
																	// table
																	// only once
					tableRowOne1 = table.getRow(0);

				}

			if (subitemtypename != null)// Compare two strings
			{
				String colunmname = reportBean.getWordData().get(j).getColNm();

				String colValCode = reportBean.getWordData().get(j).getColValCd();

				// We should not print if lhs is null
				if (colunmname != null && colValCode != null) {

					if (tableRowOne1 != null) {
						tableRowOne1.getCell(0).setText(colunmname);
						tableRowOne1.addNewTableCell();
						XWPFParagraph paragraph12 = tableRowOne1.getCell(1).getParagraphs().get(0);

						// System.out.println(table.getNumberOfRows());
						paragraph12.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run12 = paragraph12.createRun();
						run12.setFontSize(14);
						run12.setFontFamily("Cambria (Headings)");
						run12.setText(":");
						tableRowOne1.addNewTableCell().setText(colValCode);

						tableRowOne1 = null;
					}

					else {
						XWPFTableRow tableRowOne = table.createRow();

						// tableRowOne.getCell(0).setText(" ");
						tableRowOne.getCell(0).setText(colunmname);
						// tableRowOne.getCell(1).setText(":");
						XWPFParagraph paragraph12 = tableRowOne.getCell(1).getParagraphs().get(0);

						// System.out.println(table.getNumberOfRows());
						paragraph12.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run12 = paragraph12.createRun();
						run12.setFontSize(14);
						run12.setFontFamily("Cambria (Headings)");
						run12.setText(":");
						// tableRowOne.getCell(2).setText(colValCode);
						if (colValCode.contains("\n")) {

							String[] arrOfStr = colValCode.split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = tableRowOne.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(arrOfStr[x1]);
								run123.addBreak();

							}
						} else {
							tableRowOne.getCell(2).setText(colValCode);
						}

					}
					// run.setFontFamily("Cambria (Headings)");
					run.setFontSize(11);
				}

				// To print the technical remark of sub item type

				if (j + 1 == reportBean.getWordData().size()) {
					paragraph = document.createParagraph();
					run = paragraph.createRun();
					// run.setText(" ");
					run.setText(techremarks);
					run.addBreak();
					run.addBreak();
					// run.setFontFamily("Cambria (Headings)");
					run.setFontSize(11);

				}

			}
			
			
			// This is to check that only item lhs and rhs should print of item
			if (itemtemp != null && subitemname == null) // Compare two strings
			{
				String colunmname = reportBean.getWordData().get(j).getColNm();

				String colValCode = reportBean.getWordData().get(j).getColValCd();
				
				

				// We should not print if lhs is null
				if (colunmname != null && colValCode != null) {

					if (tableRowOne1 != null) {
						tableRowOne1.getCell(0).setText(colunmname);
						tableRowOne1.addNewTableCell();
						XWPFParagraph paragraph12 = tableRowOne1.getCell(1).getParagraphs().get(0);

						// System.out.println(table.getNumberOfRows());
						paragraph12.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run12 = paragraph12.createRun();
						run12.setFontSize(14);
						run12.setFontFamily("Cambria (Headings)");
						run12.setText(":");
						tableRowOne1.addNewTableCell().setText(colValCode);
						tableRowOne1 = null;
					}
					

					else {
						XWPFTableRow tableRowOne = table.createRow();
						tableRowOne.getCell(0).setText(colunmname);
						XWPFParagraph paragraph12 = tableRowOne.getCell(1).getParagraphs().get(0);

						// System.out.println(table.getNumberOfRows());
						paragraph12.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run12 = paragraph12.createRun();
						run12.setFontSize(14);
						run12.setFontFamily("Cambria (Headings)");
						run12.setText(":");
						// tableRowOne.getCell(1).setText(":");
						// tableRowOne.getCell(2).setText(colValCode);
						if (colValCode.contains("\n")) {

							String[] arrOfStr = colValCode.split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = tableRowOne.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(arrOfStr[x1]);
								run123.addBreak();
								

							}
						} else {
							tableRowOne.getCell(2).setText(colValCode);
						}

					}
					// run.setFontFamily("Cambria (Headings)");
					
					run.setFontSize(11);

				}
				if (prevfixedtext5 != null) {
					// This is to print safety devices for steam turbine
					// model
					// only
					if ((previtemcode.equals("STEM_MD")&& (countsd == 0 ))) {
						paragraph = document.createParagraph();
						run1 = paragraph.createRun();

						run.setText("				");
						run1.setText(" A.1.1 Safety Devices ");
						run1.setText("                      ");
						run1.setBold(true);
						// run1.setFontFamily("Cambria (Headings)");
						run1.setFontSize(11);

						if (prevfixedtext5.contains("\n")) {

							String[] arrOfStr = prevfixedtext5.split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = document.createParagraph();

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria
								// (Headings)");
								run123.setText(arrOfStr[x1]);

								// run123.addBreak();

							}
						} else {
							paragraph1 = document.createParagraph();
							run = paragraph1.createRun();

							run.setText(prevfixedtext5);
							run.setFontSize(11);
						}

					}
					countsd=countsd+1;
						
					
				} // if (prevfixedtext5 != null)

				// This is to print the technical remarks of item at the end
				// after
				// lhs and rhs

				if (j + 1 == reportBean.getWordData().size()) {
					if ((!techremarks.equals("NULL")) && techremarks != null)
						paragraph = document.createParagraph();
					run = paragraph.createRun();
					run.setText(techremarks);

					// run.setFontFamily("Cambria (Headings)");
					run.setFontSize(11);
				}

			}

			prevcategoryname2 = categoryname;
			prevsubcategoryname1 = subcategoryname;
			previtemname = itemtemp;
			prevqtytemp = qtytemp;
			prevsubitemname = subitemname;
			prevsubitemtypename = subitemtypename;
			previtemcode = itemcode;
			prevtechremarks = techremarks;
			prevfixedtext1 = FixedText1;
			prevfixedtext5 = FixedText5;

		}

		// To print the technical details / technical specifications(chapter
		// 2)(new result set)

		int m = 0;
		int chapteraux = 0;
		String first3Chars = "";
		String chapterindentaux = "";
		String prevcategoryaux = null;
		String prevsubcategoryaux = null;
		String previtemnameaux = null;
		String prevsubitemnameaux = null;
		String previtemcodeaux = null;
		String prevtechremarksaux = null;
		String qcnrv = null;
		String isv = null;
		String nrv = null;
		String flowo = null;
		String flown = null;
		int prevmechqty = 0;
		for (m = j; m < reportBean.getWordData().size(); m++)

		{
			int qty = 0;
			String categorynameaux = null;
			String subcategorynameaux = null;
			int itemidaux = 0;
			String itemnameaux = null;
			String itemcodeaux = null;
			String subitemcodeaux = null;
			String subitemnameaux = null;
			int qunatityaux = 0;
			String techremarksaux = null;
			String techcommentsaux = null;
			int numberofsubitems = 0;
			int subitemid = 0;
			String auxspace = null;
			String auxnewline = null;
			String doublequote = null;
			XWPFTableRow tableRowOne1 = null;

			if (m == QuotationDaoImpl.size3)
				break;

			categorynameaux = reportBean.getWordData().get(m).getCategoryName();
			subcategorynameaux = reportBean.getWordData().get(m).getSubCategoryName();
			itemidaux = reportBean.getWordData().get(m).getItemId();
			itemnameaux = reportBean.getWordData().get(m).getItemName();
			itemcodeaux = reportBean.getWordData().get(m).getItemCode();
			subitemcodeaux = reportBean.getWordData().get(m).getSubItemCode();
			subitemnameaux = reportBean.getWordData().get(m).getSubItemName();
			techremarksaux = reportBean.getWordData().get(m).getTechRemarks();
			techcommentsaux = reportBean.getWordData().get(m).getTechComments();
			numberofsubitems = reportBean.getWordData().get(m).getNumberOfSubItems();
			subitemid = reportBean.getWordData().get(m).getSubItemId();
			auxspace = reportBean.getWordData().get(m).getAuxSpace();
			auxnewline = reportBean.getWordData().get(m).getAuxNewline();
			doublequote = reportBean.getWordData().get(m).getDoubleQuot();
			qty = reportBean.getWordData().get(m).getItemQuantity();
			// This is to print the technical remarks at the end of the item
			if (!itemcodeaux.equals(previtemcodeaux) && itemcodeaux != null) {
				if (techremarksaux != null) {
					if (!techremarksaux.equals(prevtechremarksaux)) {
						paragraph = document.createParagraph();
						run = paragraph.createRun();
						run.setText(prevtechremarksaux);
						run.addBreak();
						run.addBreak();
						// run.setFontFamily("Cambria (Headings)");
						run.setFontSize(11);
					} // if (!techremarksaux.equals(prevtechremarksaux))
				} // if (techremarksaux != null)
			} // (!itemcodeaux.equals(previtemcodeaux) && itemcodeaux != null)

			itemnameaux = reportBean.getWordData().get(m).getItemName();
			subitemcodeaux = reportBean.getWordData().get(m).getSubItemCode();
			// to take first 3 characters

			if (itemcodeaux.equals("MECH_AUX"))

			{
				String input = itemnameaux; // input string
				// String first3Chars = ""; //substring containing first 3
				// characters

				if (input.length() > 3) {
					first3Chars = input.substring(0, 3);
				} else {
					first3Chars = input;
				}

				logger.info(first3Chars);
			}

			// to print item name only once
			if (!itemnameaux.equals(previtemnameaux)) {
				paragraph = document.createParagraph();
				run = paragraph.createRun();
				paragraph.setPageBreak(true);
				run.setText(reportBean.getWordData().get(m).getItemName());
				run.setBold(true);
				run.setFontFamily("Cambria (Headings)");
				run.setFontSize(11);

			} // if (!itemnameaux.equals(previtemnameaux))

			String colunmname = reportBean.getWordData().get(m).getColNm();
			String colValCode = reportBean.getWordData().get(m).getColValCd();

			// To print the lhs and rhs of sub item
			if (subitemcodeaux != null) // Compare two strings
			{
				System.out.println("inside if 1");

				if (colunmname != null && colValCode != null) {
					System.out.println("inside if 2");

					if (subitemcodeaux.equals("FFRV") && colunmname.equals("Loose Supply")
							&& colValCode.equals("Yes")) {

						System.out.println("inside if 3");

						chapteraux = chapteraux + 1;
						chapterindentaux = "." + chapteraux;

						table = document.createTable();
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);
						setTableAlign(table, ParagraphAlignment.CENTER);

						CTTblPr tblpro = table.getCTTbl().getTblPr();

						CTTblBorders borders = tblpro.addNewTblBorders();
						borders.addNewBottom().setVal(STBorder.NONE);
						borders.addNewLeft().setVal(STBorder.NONE);
						borders.addNewRight().setVal(STBorder.NONE);
						borders.addNewTop().setVal(STBorder.NONE);
						// also inner borders
						borders.addNewInsideH().setVal(STBorder.NONE);
						borders.addNewInsideV().setVal(STBorder.NONE);
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));

						tableRowOne1 = table.getRow(0);

						if (tableRowOne1 != null) {
							tableRowOne1.getCell(0).setText(first3Chars + chapterindentaux);
							tableRowOne1.addNewTableCell().setText(subitemnameaux + auxspace + auxspace + "for"
									+ auxspace + auxspace + techremarksaux);

							tableRowOne1.addNewTableCell();
							XWPFParagraph paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");
							if ((qty) != prevmechqty) {
								if (reportBean.getWordData().get(m).getItemQuantity() > 1) {

									tableRowOne1.addNewTableCell()
											.setText(String.valueOf(reportBean.getWordData().get(m).getItemQuantity()
													+ auxspace + auxspace + "No's" + auxspace + auxspace
													+ "Loose Supply"));
								} else {
									tableRowOne1.addNewTableCell().setText("Loose Supply");

								}
								tableRowOne1 = null;
							}
						}

						else {
							XWPFTableRow tableRowOne = table.createRow();
							tableRowOne.getCell(0).setText(first3Chars + chapterindentaux);
							tableRowOne.getCell(1).setText(subitemnameaux + auxspace + auxspace + "for" + auxspace
									+ auxspace + techremarksaux);
							tableRowOne.getCell(2).setText(":");
							tableRowOne.getCell(3).setText("Loose Supply");

						}
						run.setFontSize(11);
					}

					if (subitemcodeaux.equals("FFRV") && colunmname.equals("Loose Supply") && colValCode.equals("No")) {
						System.out.println("inside if 3");

						chapteraux = chapteraux + 1;
						chapterindentaux = "." + chapteraux;

						table = document.createTable();
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);
						setTableAlign(table, ParagraphAlignment.CENTER);

						CTTblPr tblpro = table.getCTTbl().getTblPr();

						CTTblBorders borders = tblpro.addNewTblBorders();
						borders.addNewBottom().setVal(STBorder.NONE);
						borders.addNewLeft().setVal(STBorder.NONE);
						borders.addNewRight().setVal(STBorder.NONE);
						borders.addNewTop().setVal(STBorder.NONE);
						// also inner borders
						borders.addNewInsideH().setVal(STBorder.NONE);
						borders.addNewInsideV().setVal(STBorder.NONE);
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));

						tableRowOne1 = table.getRow(0);

						if (tableRowOne1 != null) {
							tableRowOne1.getCell(0).setText(first3Chars + chapterindentaux);
							tableRowOne1.addNewTableCell().setText(subitemnameaux + auxspace + auxspace + "for"
									+ auxspace + auxspace + techremarksaux);
							tableRowOne1.addNewTableCell().setText("");
							tableRowOne1.addNewTableCell().setText("");
							tableRowOne1 = null;
						}

						else {
							XWPFTableRow tableRowOne = table.createRow();
							tableRowOne.getCell(0).setText(first3Chars + chapterindentaux);
							tableRowOne.getCell(1).setText(subitemnameaux + auxspace + auxspace + "for" + auxspace
									+ auxspace + techremarksaux);
							tableRowOne.getCell(2).setText("");
							tableRowOne.getCell(3).setText("");

						}

						run.setFontSize(11);
					}

					if (subitemcodeaux.equals("OIL_CENTRIFUGE") && colunmname.equals("Capacity")) {
						System.out.println("inside if 7");

						chapteraux = chapteraux + 1;
						chapterindentaux = "." + chapteraux;

						table = document.createTable();
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);
						setTableAlign(table, ParagraphAlignment.CENTER);

						CTTblPr tblpro = table.getCTTbl().getTblPr();

						CTTblBorders borders = tblpro.addNewTblBorders();
						borders.addNewBottom().setVal(STBorder.NONE);
						borders.addNewLeft().setVal(STBorder.NONE);
						borders.addNewRight().setVal(STBorder.NONE);
						borders.addNewTop().setVal(STBorder.NONE);
						// also inner borders
						borders.addNewInsideH().setVal(STBorder.NONE);
						borders.addNewInsideV().setVal(STBorder.NONE);
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));

						tableRowOne1 = table.getRow(0);

						if (tableRowOne1 != null) {
							tableRowOne1.getCell(0).setText(first3Chars + chapterindentaux);
							tableRowOne1.addNewTableCell().setText(subitemnameaux);
							tableRowOne1.addNewTableCell();
							XWPFParagraph paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");

							if ((qty) != prevmechqty) {
								if (reportBean.getWordData().get(m).getItemQuantity() > 1) {

									tableRowOne1.addNewTableCell()
											.setText(String.valueOf(reportBean.getWordData().get(m).getItemQuantity()
													+ auxspace + auxspace + "No's" + auxspace + auxspace + colValCode));
								} else {
									tableRowOne1.addNewTableCell().setText(colValCode);
								}

								tableRowOne1 = null;
							}
						}

						else {
							XWPFTableRow tableRowOne = table.createRow();
							tableRowOne.getCell(0).setText(first3Chars + chapterindentaux);
							tableRowOne.getCell(1).setText(subitemnameaux);
							tableRowOne.getCell(2).setText(":");
							tableRowOne.getCell(3).setText(colValCode);

						}

						run.setFontSize(11);
					}

					if (subitemcodeaux.equals("OV_TANK") && colunmname.equals("Capacity")) {
						System.out.println("inside if 8");

						chapteraux = chapteraux + 1;
						chapterindentaux = "." + chapteraux;

						table = document.createTable();
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);
						setTableAlign(table, ParagraphAlignment.CENTER);

						CTTblPr tblpro = table.getCTTbl().getTblPr();

						CTTblBorders borders = tblpro.addNewTblBorders();
						borders.addNewBottom().setVal(STBorder.NONE);
						borders.addNewLeft().setVal(STBorder.NONE);
						borders.addNewRight().setVal(STBorder.NONE);
						borders.addNewTop().setVal(STBorder.NONE);
						// also inner borders
						borders.addNewInsideH().setVal(STBorder.NONE);
						borders.addNewInsideV().setVal(STBorder.NONE);
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));

						tableRowOne1 = table.getRow(0);

						if (tableRowOne1 != null) {
							tableRowOne1.getCell(0).setText(first3Chars + chapterindentaux);
							tableRowOne1.addNewTableCell().setText(subitemnameaux);
							tableRowOne1.addNewTableCell();
							XWPFParagraph paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");

							if ((qty) != prevmechqty) {
								if (reportBean.getWordData().get(m).getItemQuantity() > 1) {

									tableRowOne1.addNewTableCell()
											.setText(String.valueOf(reportBean.getWordData().get(m).getItemQuantity()
													+ auxspace + auxspace + "No's" + auxspace + auxspace + colValCode
													+ auxspace + auxspace + "Litres"));
								} else {
									tableRowOne1.addNewTableCell().setText(colValCode + auxspace + auxspace + "Litres");

								}

								// tableRowOne1.addNewTableCell().setText(":");

								tableRowOne1 = null;
							}
						}

						else {
							XWPFTableRow tableRowOne = table.createRow();
							tableRowOne.getCell(0).setText(first3Chars + chapterindentaux);
							tableRowOne.getCell(1).setText(subitemnameaux);
							tableRowOne1.addNewTableCell();
							XWPFParagraph paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");

							// tableRowOne1.addNewTableCell().setText(":");
							tableRowOne.getCell(3).setText(colValCode + auxspace + auxspace + "Liters");

						}

						run.setFontSize(11);
					}

					if (subitemcodeaux.equals("ISV") && colunmname.equals("Size (Inch)")) {
						isv = null;
						isv = colValCode;
						chapteraux = chapteraux + 1;
						chapterindentaux = "." + chapteraux;

					}

					if (subitemcodeaux.equals("ISV") && colunmname.equals("Rating")) {
						isv = isv + "," + auxspace + colValCode + "#";

					}

					if (subitemcodeaux.equals("ISV") && colunmname.equals("Type")) {
						isv = isv + "," + auxspace + colValCode;

					}

					if (subitemcodeaux.equals("ISV") && colunmname.equals("Loose Supply") && colValCode.equals("Yes")) {
						isv = isv + "," + auxspace + colunmname;

						table = document.createTable();
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);
						setTableAlign(table, ParagraphAlignment.CENTER);

						CTTblPr tblpro = table.getCTTbl().getTblPr();

						CTTblBorders borders = tblpro.addNewTblBorders();
						borders.addNewBottom().setVal(STBorder.NONE);
						borders.addNewLeft().setVal(STBorder.NONE);
						borders.addNewRight().setVal(STBorder.NONE);
						borders.addNewTop().setVal(STBorder.NONE);
						// also inner borders
						borders.addNewInsideH().setVal(STBorder.NONE);
						borders.addNewInsideV().setVal(STBorder.NONE);
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));

						tableRowOne1 = table.getRow(0);

						if (tableRowOne1 != null) {
							tableRowOne1.getCell(0).setText(first3Chars + chapterindentaux);
							tableRowOne1.addNewTableCell().setText(subitemnameaux + auxspace + auxspace + "for"
									+ auxspace + auxspace + techremarksaux);
							tableRowOne1.addNewTableCell();
							XWPFParagraph paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");
							if ((qty) != prevmechqty) {
								if (reportBean.getWordData().get(m).getItemQuantity() > 1) {

									tableRowOne1.addNewTableCell()
											.setText(String.valueOf(reportBean.getWordData().get(m).getItemQuantity()
													+ auxspace + auxspace + "No's" + auxspace + auxspace + isv));
								} else {
									tableRowOne1.addNewTableCell().setText(isv);
								}

							}
						}
					}

					if (subitemcodeaux.equals("ISV") && colunmname.equals("Loose Supply") && colValCode.equals("No")) {
						isv = isv;

						table = document.createTable();
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);
						setTableAlign(table, ParagraphAlignment.CENTER);

						CTTblPr tblpro = table.getCTTbl().getTblPr();

						CTTblBorders borders = tblpro.addNewTblBorders();
						borders.addNewBottom().setVal(STBorder.NONE);
						borders.addNewLeft().setVal(STBorder.NONE);
						borders.addNewRight().setVal(STBorder.NONE);
						borders.addNewTop().setVal(STBorder.NONE);
						// also inner borders
						borders.addNewInsideH().setVal(STBorder.NONE);
						borders.addNewInsideV().setVal(STBorder.NONE);
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));

						tableRowOne1 = table.getRow(0);

						if (tableRowOne1 != null) {
							tableRowOne1.getCell(0).setText(first3Chars + chapterindentaux);
							tableRowOne1.addNewTableCell().setText(subitemnameaux + auxspace + auxspace + "for"
									+ auxspace + auxspace + techremarksaux);
							tableRowOne1.addNewTableCell();
							XWPFParagraph paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");
							if ((qty) != prevmechqty) {
								if (reportBean.getWordData().get(m).getItemQuantity() > 1) {

									tableRowOne1.addNewTableCell()
											.setText(String.valueOf(reportBean.getWordData().get(m).getItemQuantity()
													+ auxspace + auxspace + "No's" + auxspace + auxspace + isv));
								} else {
									tableRowOne1.addNewTableCell().setText(isv);
								}

							}

						}
					}

					if (subitemcodeaux.equals("NRV") && colunmname.equals("Size (Inch)")) {
						nrv = null;
						nrv = colValCode;
						chapteraux = chapteraux + 1;
						chapterindentaux = "." + chapteraux;

					}

					if (subitemcodeaux.equals("NRV") && colunmname.equals("Rating")) {
						nrv = nrv + "," + auxspace + colValCode + "#";

					}

					if (subitemcodeaux.equals("NRV") && colunmname.equals("Loose Supply") && colValCode.equals("Yes")) {
						nrv = nrv + "," + auxspace + colunmname;

						table = document.createTable();
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);
						setTableAlign(table, ParagraphAlignment.CENTER);

						CTTblPr tblpro = table.getCTTbl().getTblPr();

						CTTblBorders borders = tblpro.addNewTblBorders();
						borders.addNewBottom().setVal(STBorder.NONE);
						borders.addNewLeft().setVal(STBorder.NONE);
						borders.addNewRight().setVal(STBorder.NONE);
						borders.addNewTop().setVal(STBorder.NONE);
						// also inner borders
						borders.addNewInsideH().setVal(STBorder.NONE);
						borders.addNewInsideV().setVal(STBorder.NONE);
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));

						tableRowOne1 = table.getRow(0);

						if (tableRowOne1 != null) {
							tableRowOne1.getCell(0).setText(first3Chars + chapterindentaux);
							tableRowOne1.addNewTableCell().setText(subitemnameaux + auxspace + auxspace + "for"
									+ auxspace + auxspace + techremarksaux);
							tableRowOne1.addNewTableCell();
							XWPFParagraph paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");
							if ((qty) != prevmechqty) {
								if (reportBean.getWordData().get(m).getItemQuantity() > 1) {

									tableRowOne1.addNewTableCell()
											.setText(String.valueOf(reportBean.getWordData().get(m).getItemQuantity()
													+ auxspace + auxspace + "No's" + auxspace + auxspace + nrv));
								} else {
									tableRowOne1.addNewTableCell().setText(nrv);
								}

							}

						}

					}
					if (subitemcodeaux.equals("NRV") && colunmname.equals("Loose Supply") && colValCode.equals("No")) {
						nrv = nrv;

						table = document.createTable();
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);
						setTableAlign(table, ParagraphAlignment.CENTER);

						CTTblPr tblpro = table.getCTTbl().getTblPr();

						CTTblBorders borders = tblpro.addNewTblBorders();
						borders.addNewBottom().setVal(STBorder.NONE);
						borders.addNewLeft().setVal(STBorder.NONE);
						borders.addNewRight().setVal(STBorder.NONE);
						borders.addNewTop().setVal(STBorder.NONE);
						// also inner borders
						borders.addNewInsideH().setVal(STBorder.NONE);
						borders.addNewInsideV().setVal(STBorder.NONE);
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));

						tableRowOne1 = table.getRow(0);

						if (tableRowOne1 != null) {
							tableRowOne1.getCell(0).setText(first3Chars + chapterindentaux);
							tableRowOne1.addNewTableCell().setText(subitemnameaux + auxspace + auxspace + "for"
									+ auxspace + auxspace + techremarksaux);
							tableRowOne1.addNewTableCell();
							XWPFParagraph paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");
							if ((qty) != prevmechqty) {

								if (reportBean.getWordData().get(m).getItemQuantity() > 1) {

									tableRowOne1.addNewTableCell()
											.setText(String.valueOf(reportBean.getWordData().get(m).getItemQuantity()
													+ auxspace + auxspace + "No's" + auxspace + auxspace + nrv));
								} else {
									tableRowOne1.addNewTableCell().setText(nrv);
								}

							}

						}
					}

					if (subitemcodeaux.equals("ATR_VAL")) {
						System.out.println("inside if 11");

						chapteraux = chapteraux + 1;
						chapterindentaux = "." + chapteraux;

						table = document.createTable();
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);
						setTableAlign(table, ParagraphAlignment.CENTER);

						CTTblPr tblpro = table.getCTTbl().getTblPr();

						CTTblBorders borders = tblpro.addNewTblBorders();
						borders.addNewBottom().setVal(STBorder.NONE);
						borders.addNewLeft().setVal(STBorder.NONE);
						borders.addNewRight().setVal(STBorder.NONE);
						borders.addNewTop().setVal(STBorder.NONE);
						// also inner borders
						borders.addNewInsideH().setVal(STBorder.NONE);
						borders.addNewInsideV().setVal(STBorder.NONE);
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));

						tableRowOne1 = table.getRow(0);

						if (tableRowOne1 != null) {
							tableRowOne1.getCell(0).setText(first3Chars + chapterindentaux);
							tableRowOne1.addNewTableCell()
									.setText(subitemnameaux + auxspace + auxspace + auxspace + auxspace + "size");
							tableRowOne1.addNewTableCell();
							XWPFParagraph paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");
							if ((qty) != prevmechqty) {
								if (reportBean.getWordData().get(m).getItemQuantity() > 1) {

									tableRowOne1.addNewTableCell()
											.setText(String.valueOf(reportBean.getWordData().get(m).getItemQuantity()
													+ auxspace + auxspace + "No's" + auxspace + auxspace + "standard 10"
													+ doublequote + auxspace + auxspace));
								} else {
									tableRowOne1.addNewTableCell()
											.setText("standard 10" + doublequote + auxspace + auxspace);

								}
							}

							tableRowOne1 = null;
						}

						else {
							XWPFTableRow tableRowOne = table.createRow();
							tableRowOne.getCell(0).setText(first3Chars + chapterindentaux);
							tableRowOne.getCell(1)
									.setText(subitemnameaux + auxspace + auxspace + "(" + auxspace + auxspace + "size");
							tableRowOne1.addNewTableCell();
							XWPFParagraph paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");

							// tableRowOne1.addNewTableCell().setText(":");
							tableRowOne.getCell(3).setText("standard 10" + doublequote + auxspace + auxspace + ")");

						}

						run.setFontSize(11);
					}

					if (subitemcodeaux.equals("VACC_BVL")) {
						System.out.println("inside if 12");

						chapteraux = chapteraux + 1;
						chapterindentaux = "." + chapteraux;

						table = document.createTable();
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);
						setTableAlign(table, ParagraphAlignment.CENTER);

						CTTblPr tblpro = table.getCTTbl().getTblPr();

						CTTblBorders borders = tblpro.addNewTblBorders();
						borders.addNewBottom().setVal(STBorder.NONE);
						borders.addNewLeft().setVal(STBorder.NONE);
						borders.addNewRight().setVal(STBorder.NONE);
						borders.addNewTop().setVal(STBorder.NONE);
						// also inner borders
						borders.addNewInsideH().setVal(STBorder.NONE);
						borders.addNewInsideV().setVal(STBorder.NONE);
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));

						tableRowOne1 = table.getRow(0);

						if (tableRowOne1 != null) {
							tableRowOne1.getCell(0).setText(first3Chars + chapterindentaux);
							tableRowOne1.addNewTableCell().setText(subitemnameaux);
							tableRowOne1.addNewTableCell();
							XWPFParagraph paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");
							if ((qty) != prevmechqty) {
								if (reportBean.getWordData().get(m).getItemQuantity() > 1) {

									tableRowOne1.addNewTableCell()
											.setText(String.valueOf(reportBean.getWordData().get(m).getItemQuantity()
													+ auxspace + auxspace + "No's" + auxspace + auxspace + colValCode));
								} else {
									tableRowOne1.addNewTableCell().setText(colValCode);

								}
							}
							tableRowOne1 = null;
						}

						else {
							XWPFTableRow tableRowOne = table.createRow();
							tableRowOne.getCell(0).setText(first3Chars + chapterindentaux);
							tableRowOne.getCell(1).setText(subitemnameaux);
							tableRowOne1.addNewTableCell();
							XWPFParagraph paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");
							if ((qty) != prevmechqty) {
								if (reportBean.getWordData().get(m).getItemQuantity() > 1) {

									tableRowOne.addNewTableCell()
											.setText(String.valueOf(reportBean.getWordData().get(m).getItemQuantity()
													+ auxspace + auxspace + "No's" + auxspace + auxspace + colValCode));
								} else {
									tableRowOne.addNewTableCell().setText(colValCode);

								}
							}
						}

						run.setFontSize(11);
					}

					if (subitemcodeaux.equals("MET_EXP_BEL") && colunmname.equals("Rating (#)")) {
						System.out.println("inside if 10");

						chapteraux = chapteraux + 1;
						chapterindentaux = "." + chapteraux;

						table = document.createTable();
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);
						setTableAlign(table, ParagraphAlignment.CENTER);

						CTTblPr tblpro = table.getCTTbl().getTblPr();

						CTTblBorders borders = tblpro.addNewTblBorders();
						borders.addNewBottom().setVal(STBorder.NONE);
						borders.addNewLeft().setVal(STBorder.NONE);
						borders.addNewRight().setVal(STBorder.NONE);
						borders.addNewTop().setVal(STBorder.NONE);
						// also inner borders
						borders.addNewInsideH().setVal(STBorder.NONE);
						borders.addNewInsideV().setVal(STBorder.NONE);
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));

						tableRowOne1 = table.getRow(0);

						if (tableRowOne1 != null) {
							tableRowOne1.getCell(0).setText(first3Chars + chapterindentaux);
							tableRowOne1.addNewTableCell().setText(subitemnameaux);
							tableRowOne1.addNewTableCell();
							XWPFParagraph paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");
							if ((qty) != prevmechqty) {
								if (reportBean.getWordData().get(m).getItemQuantity() > 1) {

									tableRowOne1.addNewTableCell()
											.setText(String.valueOf(reportBean.getWordData().get(m).getItemQuantity()
													+ auxspace + auxspace + "No's" + auxspace + auxspace
													+ ("Turbine Exhaust line")));
								} else {
									tableRowOne1.addNewTableCell().setText("Turbine Exhaust line");

								}
							}

							tableRowOne1 = null;
						}

						else {
							XWPFTableRow tableRowOne = table.createRow();
							tableRowOne.getCell(0).setText(first3Chars + chapterindentaux);
							tableRowOne.getCell(1).setText(subitemnameaux);
							tableRowOne1.addNewTableCell();
							XWPFParagraph paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");
							if ((qty) != prevmechqty) {
								if (reportBean.getWordData().get(m).getItemQuantity() > 1) {

									tableRowOne1.addNewTableCell()
											.setText(String.valueOf(reportBean.getWordData().get(m).getItemQuantity()
													+ auxspace + auxspace + "No's" + auxspace + auxspace
													+ ("Turbine Exhaust line")));
								} else {
									tableRowOne1.addNewTableCell().setText("Turbine Exhaust line");

								}
							}

						}

						run.setFontSize(11);
					}

					if (subitemcodeaux.equals("FLOW_ORIFICE") && colunmname.equals("Size (Inch)")) {
						flowo = null;
						flowo = colValCode;
						chapteraux = chapteraux + 1;
						chapterindentaux = "." + chapteraux;

					}

					// if (subitemcodeaux.equals("FLOW_ORIFICE") &&
					// colunmname.equals("MOC")) {
					// flowo = flowo + "," + auxspace + colValCode;
					//
					// }

					if (subitemcodeaux.equals("FLOW_ORIFICE") && colunmname.equals("Loose Supply")
							&& colValCode.equals("Yes")) {
						flowo = flowo + "," + auxspace + colunmname;
						table = document.createTable();
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);
						setTableAlign(table, ParagraphAlignment.CENTER);

						CTTblPr tblpro = table.getCTTbl().getTblPr();

						CTTblBorders borders = tblpro.addNewTblBorders();
						borders.addNewBottom().setVal(STBorder.NONE);
						borders.addNewLeft().setVal(STBorder.NONE);
						borders.addNewRight().setVal(STBorder.NONE);
						borders.addNewTop().setVal(STBorder.NONE);
						// also inner borders
						borders.addNewInsideH().setVal(STBorder.NONE);
						borders.addNewInsideV().setVal(STBorder.NONE);
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));

						tableRowOne1 = table.getRow(0);

						if (tableRowOne1 != null) {
							tableRowOne1.getCell(0).setText(first3Chars + chapterindentaux);
							tableRowOne1.addNewTableCell().setText(subitemnameaux + auxspace + auxspace + "for"
									+ auxspace + auxspace + techremarksaux);
							tableRowOne1.addNewTableCell();
							XWPFParagraph paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");
							if ((qty) != prevmechqty) {
								if (reportBean.getWordData().get(m).getItemQuantity() > 1) {

									tableRowOne1.addNewTableCell()
											.setText(String.valueOf(reportBean.getWordData().get(m).getItemQuantity()
													+ auxspace + auxspace + "No's" + auxspace + auxspace + flowo));
								} else {
									tableRowOne1.addNewTableCell().setText(flowo);

								}
							}

						}
					}

					if (subitemcodeaux.equals("FLOW_ORIFICE") && colunmname.equals("Loose Supply")
							&& colValCode.equals("No")) {
						flowo = flowo;
						table = document.createTable();
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);
						setTableAlign(table, ParagraphAlignment.CENTER);

						CTTblPr tblpro = table.getCTTbl().getTblPr();

						CTTblBorders borders = tblpro.addNewTblBorders();
						borders.addNewBottom().setVal(STBorder.NONE);
						borders.addNewLeft().setVal(STBorder.NONE);
						borders.addNewRight().setVal(STBorder.NONE);
						borders.addNewTop().setVal(STBorder.NONE);
						// also inner borders
						borders.addNewInsideH().setVal(STBorder.NONE);
						borders.addNewInsideV().setVal(STBorder.NONE);
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));

						tableRowOne1 = table.getRow(0);

						if (tableRowOne1 != null) {
							tableRowOne1.getCell(0).setText(first3Chars + chapterindentaux);
							tableRowOne1.addNewTableCell().setText(subitemnameaux + auxspace + auxspace + "for"
									+ auxspace + auxspace + techremarksaux);
							tableRowOne1.addNewTableCell();
							XWPFParagraph paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");
							if ((qty) != prevmechqty) {
								if (reportBean.getWordData().get(m).getItemQuantity() > 1) {

									tableRowOne1.addNewTableCell()
											.setText(String.valueOf(reportBean.getWordData().get(m).getItemQuantity()
													+ auxspace + auxspace + "No's" + auxspace + auxspace + flowo));
								} else {
									tableRowOne1.addNewTableCell().setText(flowo);

								}
							}

						}
					}

					if (subitemcodeaux.equals("FLOW_NOZZLE") && colunmname.equals("Size (Inch)")) {

						flown = null;
						flown = colValCode;
						chapteraux = chapteraux + 1;
						chapterindentaux = "." + chapteraux;

					}

					// if (subitemcodeaux.equals("FLOW_NOZZLE") &&
					// colunmname.equals("MOC")) {
					// flown = flown + "," + auxspace + colValCode;
					//
					// }

					if (subitemcodeaux.equals("FLOW_NOZZLE") && colunmname.equals("Loose Supply")
							&& colValCode.equals("Yes")) {
						flown = flown + "," + auxspace + colunmname;
						table = document.createTable();
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);
						setTableAlign(table, ParagraphAlignment.CENTER);

						CTTblPr tblpro = table.getCTTbl().getTblPr();

						CTTblBorders borders = tblpro.addNewTblBorders();
						borders.addNewBottom().setVal(STBorder.NONE);
						borders.addNewLeft().setVal(STBorder.NONE);
						borders.addNewRight().setVal(STBorder.NONE);
						borders.addNewTop().setVal(STBorder.NONE);
						// also inner borders
						borders.addNewInsideH().setVal(STBorder.NONE);
						borders.addNewInsideV().setVal(STBorder.NONE);
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));

						tableRowOne1 = table.getRow(0);

						if (tableRowOne1 != null) {
							tableRowOne1.getCell(0).setText(first3Chars + chapterindentaux);
							tableRowOne1.addNewTableCell().setText(subitemnameaux + auxspace + auxspace + "for"
									+ auxspace + auxspace + techremarksaux);
							tableRowOne1.addNewTableCell();
							XWPFParagraph paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");
							if ((qty) != prevmechqty) {
								if (reportBean.getWordData().get(m).getItemQuantity() > 1) {

									tableRowOne1.addNewTableCell()
											.setText(String.valueOf(reportBean.getWordData().get(m).getItemQuantity()
													+ auxspace + auxspace + "No's" + auxspace + auxspace + flown));
								} else {
									tableRowOne1.addNewTableCell().setText(flown);

								}
							}

						}
					}
					if (subitemcodeaux.equals("FLOW_NOZZLE") && colunmname.equals("Loose Supply")
							&& colValCode.equals("No")) {
						flown = flown;
						table = document.createTable();
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);
						setTableAlign(table, ParagraphAlignment.CENTER);

						CTTblPr tblpro = table.getCTTbl().getTblPr();

						CTTblBorders borders = tblpro.addNewTblBorders();
						borders.addNewBottom().setVal(STBorder.NONE);
						borders.addNewLeft().setVal(STBorder.NONE);
						borders.addNewRight().setVal(STBorder.NONE);
						borders.addNewTop().setVal(STBorder.NONE);
						// also inner borders
						borders.addNewInsideH().setVal(STBorder.NONE);
						borders.addNewInsideV().setVal(STBorder.NONE);
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));

						tableRowOne1 = table.getRow(0);

						if (tableRowOne1 != null) {
							tableRowOne1.getCell(0).setText(first3Chars + chapterindentaux);
							tableRowOne1.addNewTableCell().setText(subitemnameaux + auxspace + auxspace + "for"
									+ auxspace + auxspace + techremarksaux);
							tableRowOne1.addNewTableCell();
							XWPFParagraph paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");
							if ((qty) != prevmechqty) {
								if (reportBean.getWordData().get(m).getItemQuantity() > 1) {

									tableRowOne1.addNewTableCell()
											.setText(String.valueOf(reportBean.getWordData().get(m).getItemQuantity()
													+ auxspace + auxspace + "No's" + auxspace + auxspace + flown));
								} else {
									tableRowOne1.addNewTableCell().setText(flown);

								}
							}

						}
					}
					if (subitemcodeaux.equals("QCNRV") && colunmname.equals("Size (Inch)")) {
						qcnrv = null;
						qcnrv = colValCode;
						chapteraux = chapteraux + 1;
						chapterindentaux = "." + chapteraux;

					}

					if (subitemcodeaux.equals("QCNRV") && colunmname.equals("Rating (#)")) {
						qcnrv = qcnrv + "," + auxspace + colValCode;
						table = document.createTable();
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);
						setTableAlign(table, ParagraphAlignment.CENTER);

						CTTblPr tblpro = table.getCTTbl().getTblPr();

						CTTblBorders borders = tblpro.addNewTblBorders();
						borders.addNewBottom().setVal(STBorder.NONE);
						borders.addNewLeft().setVal(STBorder.NONE);
						borders.addNewRight().setVal(STBorder.NONE);
						borders.addNewTop().setVal(STBorder.NONE);
						// also inner borders
						borders.addNewInsideH().setVal(STBorder.NONE);
						borders.addNewInsideV().setVal(STBorder.NONE);
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
						table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));

						tableRowOne1 = table.getRow(0);

						if (tableRowOne1 != null) {
							tableRowOne1.getCell(0).setText(first3Chars + chapterindentaux);
							tableRowOne1.addNewTableCell().setText(subitemnameaux + auxspace + auxspace + "for"
									+ auxspace + auxspace + techremarksaux);
							tableRowOne1.addNewTableCell();
							XWPFParagraph paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");
							if ((qty) != prevmechqty) {
								if (reportBean.getWordData().get(m).getItemQuantity() > 1) {

									tableRowOne1.addNewTableCell()
											.setText(String.valueOf(reportBean.getWordData().get(m).getItemQuantity()
													+ auxspace + auxspace + "No's" + auxspace + auxspace + qcnrv));
								} else {
									tableRowOne1.addNewTableCell().setText(qcnrv);

								}
							}

						}

					}

					// To print technical remarks of the sub itemq

					if (m + 1 == reportBean.getWordData().size()) {
						if (techremarksaux != null && !techremarksaux.equals("NULL")) {
							paragraph = document.createParagraph();
							run = paragraph.createRun();
							run.setText(techremarksaux);

							// run.setFontFamily("Cambria (Headings)");
							run.setFontSize(11);
						}
					} // if (m + 1 == reportBean.getWordData().size())

				} // if (subitemnameaux != null )

			}
			// prevmechqty = qty;
			previtemnameaux = itemnameaux;
			prevsubitemnameaux = subitemnameaux;
			previtemcodeaux = itemcodeaux;
			prevtechremarksaux = techremarksaux;

		} // for loop

		// To print the technical details / technical specifications(chapter
		// 2)(new result set 10)

		int n = 0;
		int prevqunatityextscp = 0;
		String prevcategoryextscp = null;
		String prevsubcategoryextscp = null;
		String previtemnameextscp = null;
		String prevsubitemnameextscp = null;
		String previtemcodeextscp = null;
		String prevtechremarksextscp = null;
		String prevtechcommentsextscp = null;
		int count77 = 0;
		for (n = m; n < reportBean.getWordData().size(); n++)

		{

			String categorynameextscp = null;
			String subcategorynameextscp = null;
			int itemidextscp = 0;
			String itemnameextscp = null;
			String itemcodeextscp = null;
			String subitemcodeextscp = null;
			String subitemnameextscp = null;
			String space = null;
			int qunatityextscp = 0;
			String techremarksextscp = null;
			String techcommentsextscp = null;
			int numberofsubitemsextscp = 0;
			XWPFTableRow tableRowOne1 = null;
			if (n == QuotationDaoImpl.size4 && count77 > 0) {
				if (reportBean.getWordData().get(n - 1).getTechRemarks() != null) {

					paragraph = document.createParagraph();
					run = paragraph.createRun();
					run.setText(reportBean.getWordData().get(n - 1).getTechRemarks());
					// run.setFontFamily("Cambria (Headings)");
					run.setFontSize(11);
				}
			}
			if (n == QuotationDaoImpl.size4) {
				break;
			}
			categorynameextscp = reportBean.getWordData().get(n).getCategoryName();
			subcategorynameextscp = reportBean.getWordData().get(n).getSubCategoryName();
			itemidextscp = reportBean.getWordData().get(n).getItemId();
			itemnameextscp = reportBean.getWordData().get(n).getItemName();
			itemcodeextscp = reportBean.getWordData().get(n).getItemCode();
			subitemcodeextscp = reportBean.getWordData().get(n).getSubItemCode();
			subitemnameextscp = reportBean.getWordData().get(n).getSubItemName();
			space = reportBean.getWordData().get(n).getSpaceF2f();
			qunatityextscp = reportBean.getWordData().get(n).getQuantityAux();
			techremarksextscp = reportBean.getWordData().get(n).getTechRemarks();
			techcommentsextscp = reportBean.getWordData().get(n).getTechComments();
			numberofsubitemsextscp = reportBean.getWordData().get(n).getNumberOfSubItems();

			// This is to print the technical remarks at the end of the item
			if (itemcodeextscp != null) {
				if (techcommentsextscp != null) {
					if (!techcommentsextscp.equals(prevtechcommentsextscp)) {
						paragraph = document.createParagraph();
						run = paragraph.createRun();
						run.setText(techcommentsextscp);

						// run.setFontFamily("Cambria (Headings)");
						run.setFontSize(11);
					} // if (!techremarkextscp.equals(prevtechremarksextscp))
				} // if (techremarksextscp != null)
			} // (!itemcodeextscp.equals(previtemcodeextscp) && itemcodeextscp
				// != null)

			itemnameextscp = reportBean.getWordData().get(n).getItemName();
			subitemnameextscp = reportBean.getWordData().get(n).getSubItemName();

			// to print item name only once
			if (!itemnameextscp.equals(previtemnameextscp)) {
				paragraph = document.createParagraph();
				run = paragraph.createRun();
				run.setText(reportBean.getWordData().get(n).getItemName());
				run.setBold(true);
				run.setFontFamily("Cambria (Headings)");
				run.setFontSize(12);

				// table = document.createTable();
				// setTableAlign(table, ParagraphAlignment.LEFT);// Creating
				// // table
				// // only once
				// tableRowOne1 = table.getRow(0);

			} // if (!itemnameextscp.equals(previtemnameextscp))

			// To print the sub item name only once
			if (subitemnameextscp != null) {
				if (!subitemnameextscp.equals(prevsubitemnameextscp)) {
					if (prevsubitemnameextscp != null) {
						if (prevtechremarksextscp != null) {

							paragraph = document.createParagraph();
							run = paragraph.createRun();
							run.setText(prevtechremarksextscp);
							// run.setFontFamily("Cambria (Headings)");
							run.setFontSize(11);
						}
					}

					paragraph = document.createParagraph();
					run = paragraph.createRun();
					run.setText(reportBean.getWordData().get(n).getSubItemName());
					run.setText(":");
					if ((qunatityextscp) != prevqunatityextscp) {
						if (reportBean.getWordData().get(n).getQuantityAux() > 1) {
							run.setText(space + space + space + space + space + space + space + space + space + space
									+ space + space + space + space + space + space + space + space + space + space
									+ space + space + space + space + space + space + space + space + space + space
									+ space + space + space + space + space + space
									+ String.valueOf("Quantity :" + reportBean.getWordData().get(n).getQuantityAux()));
						}
					}
					run.setBold(true);
					run.setFontFamily("Cambria (Headings)");
					run.setFontSize(11);
					if (reportBean.getWordData().get(n).getColNm() != null) {
						table = document.createTable();
						CTTblPr tblpro = table.getCTTbl().getTblPr();

						CTTblBorders borders = tblpro.addNewTblBorders();
						borders.addNewBottom().setVal(STBorder.NONE);
						borders.addNewLeft().setVal(STBorder.NONE);
						borders.addNewRight().setVal(STBorder.NONE);
						borders.addNewTop().setVal(STBorder.NONE);
						// also inner borders
						borders.addNewInsideH().setVal(STBorder.NONE);
						borders.addNewInsideV().setVal(STBorder.NONE);
						setTableAlign(table, ParagraphAlignment.LEFT);
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);

						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));// Creating
						// table
						// only once
						tableRowOne1 = table.getRow(0);
					}
				} // if (!subitemnameextscp.equals(prevsubitemnameextscp))

				// To print the lhs and rhs of sub item
				if (subitemnameextscp != null) // Compare two strings
				{
					String colunmname = reportBean.getWordData().get(n).getColNm();
					String colValCode = reportBean.getWordData().get(n).getColValCd();

					if (colunmname != null && colValCode != null) {
						// paragraph = document.createParagraph();
						// run = paragraph.createRun();
						// run.setText(colunmname);
						// run.setText(":");
						// run.setText(colValCode);
						// run.setFontFamily("Cambria (Headings)");
						// run.setFontSize(11);

						if (tableRowOne1 != null) {
							tableRowOne1.getCell(0).setText(colunmname);
							tableRowOne1.addNewTableCell();
							XWPFParagraph paragraph12 = tableRowOne1.getCell(1).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");
							tableRowOne1.addNewTableCell().setText(colValCode);
							tableRowOne1 = null;
						}

						else {
							XWPFTableRow tableRowOne = table.createRow();
							tableRowOne.getCell(0).setText(colunmname);
							tableRowOne.addNewTableCell();
							XWPFParagraph paragraph12 = tableRowOne.getCell(1).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");
							tableRowOne.getCell(2).setText(colValCode);

						}
					}

					// To print technical remarks of the sub itemq

					// if (n + 1 == reportBean.getWordData().size()) {
					// paragraph = document.createParagraph();
					// run = paragraph.createRun();
					// run.setText(techremarksextscp);
					//
					// run.setFontFamily("Cambria (Headings)");
					// run.setFontSize(11);
					// } // if (n + 1 == reportBean.getWordData().size())

				} // if (subitemnameextscp != null )

			} // if (subitemnameextscp != null)
			prevqunatityextscp = qunatityextscp;
			previtemnameextscp = itemnameextscp;
			prevsubitemnameextscp = subitemnameextscp;
			previtemcodeextscp = itemcodeextscp;
			prevtechremarksextscp = techremarksextscp;
			prevtechcommentsextscp = techcommentsextscp;
			count77 = count77 + 1;
		
		} // for loop
		// Begin Added by MT 23/03/2023
		paragraph = document.createParagraph();
		run = paragraph.createRun();
		
		run.setBold(true);
		run.setFontFamily("Cambria (Headings)");
		run.setFontSize(11);
		run.setText("Note:- ");
		run.setBold(false);
		run.setFontFamily("Calibri");
		run.setFontSize(10);
		run.setText("Pipe sizing with a velocity of maximum 35 m/sec as per industrial standard. If customer require velocity less than 35 m/sec, it calls for additional commercial implications which can be mutually discussed & agreed.");		
		// End added by MT 23/03/2023
		// To print the technical details / technical specifications(chapter
		// 2)(new result set 10) electrical

		int x = 0;
		String prevcategoryele = null;
		String prevsubcategoryele = null;
		String previtemnameele = null;
		int prevqty = 0;
		String prevdesitemnameele = null;
		String prevdessubitemnameele = null;
		String previtemcodeele = null;
		String prevtechremarksele = null;
		String prevheadertextele = null;
		String prevfootertextele = null;
		int counter4 = 0;
		int count = 0;
		int count6 = 0;
		for (x = n; x < reportBean.getWordData().size(); x++)

		{

			String categorynameele = null;
			String subcategorynameele = null;
			int itemidele = 0;
			String itemnameele = null;
			int qty = 0;
			String space = null;
			String desitemnameele = null;
			String dessubitemnameele = null;
			String itemcodeele = null;
			String subitemcodeele = null;
			String subitemnameele = null;

			int qunatityele = 0;
			String techremarksele = null;
			String techcommentsele = null;
			int numberofsubitemsele = 0;
			int orderidele = 0;
			int dessubitemorderidele = 0;
			String headertextele = null;
			String footertextele = null;
			String subColValCodeele = null;
			int desItemOrderId = 0;
			int addOnNewColFlag = 0;
			String descolorderidele = null;
			int linkFlag = 0;

			XWPFTableRow tableRowOne1 = null;

			if (x == QuotationDaoImpl.size5) {
				if (counter4 > 0) {
					if ((!reportBean.getWordData().get(x - 1).getFooterText().equals("NULL"))
							&& (reportBean.getWordData().get(x - 1).getFooterText() != null)) {
						if (reportBean.getWordData().get(x - 1).getFooterText().contains("\n")) {

							String[] arrOfStr = reportBean.getWordData().get(x - 1).getFooterText().split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = document.createParagraph();

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(arrOfStr[x1]);
								logger.info("rhs word start");
								logger.info(arrOfStr[x1]);
								logger.info("rhs word end");
							}
						} else {

							XWPFParagraph paragraph21 = document.createParagraph();

							// System.out.println(table.getNumberOfRows());
							// paragraph21.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run123 = paragraph21.createRun();
							run123.setFontSize(11);
							// run123.setFontFamily("Cambria (Headings)");
							run123.setText(reportBean.getWordData().get(x - 1).getFooterText());
						}
					}
					if ((reportBean.getWordData().get(x - 1).getTechRemarks() != null)) {

						if ((!reportBean.getWordData().get(x - 1).getTechRemarks().equals("NULL"))) {
							if (reportBean.getWordData().get(x - 1).getTechRemarks().contains("\n")) {

								String[] arrOfStr = reportBean.getWordData().get(x - 1).getTechRemarks().split("\n",
										20);
								for (int x1 = 0; x1 < arrOfStr.length; x1++) {
									XWPFParagraph paragraph21 = document.createParagraph();

									// System.out.println(table.getNumberOfRows());
									// paragraph21.setAlignment(ParagraphAlignment.CENTER);
									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									// run123.setFontFamily("Cambria
									// (Headings)");
									run123.setText(arrOfStr[x1]);
									logger.info("rhs word start");
									logger.info(arrOfStr[x1]);
									logger.info("rhs word end");
								}
							} else {

								XWPFParagraph paragraph21 = document.createParagraph();

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(reportBean.getWordData().get(x - 1).getTechRemarks());
							}
						}
					}
				}
				break;
			}
			categorynameele = reportBean.getWordData().get(x).getCategoryName();
			subcategorynameele = reportBean.getWordData().get(x).getSubCategoryName();
			itemidele = reportBean.getWordData().get(x).getItemId();
			itemnameele = reportBean.getWordData().get(x).getItemName();
			desitemnameele = reportBean.getWordData().get(x).getDesItemName();
			dessubitemnameele = reportBean.getWordData().get(x).getDesSubItemName();
			itemcodeele = reportBean.getWordData().get(x).getItemCode();
			subitemcodeele = reportBean.getWordData().get(x).getSubItemCode();
			subitemnameele = reportBean.getWordData().get(x).getSubItemName();
			qunatityele = reportBean.getWordData().get(x).getQuantityEle();
			techremarksele = reportBean.getWordData().get(x).getTechRemarks();
			techcommentsele = reportBean.getWordData().get(x).getTechComments();
			numberofsubitemsele = reportBean.getWordData().get(x).getNumberOfSubItems();
			orderidele = reportBean.getWordData().get(x).getOrderId();
			dessubitemorderidele = reportBean.getWordData().get(x).getDesSubItemOrderId();
			descolorderidele = reportBean.getWordData().get(x).getDesColOrderId();
			subColValCodeele = reportBean.getWordData().get(x).getSubColValCode();
			headertextele = reportBean.getWordData().get(x).getHeaderText();
			footertextele = reportBean.getWordData().get(x).getFooterText();
			linkFlag = reportBean.getWordData().get(x).getLinkFlag();
			desItemOrderId = reportBean.getWordData().get(x).getDesItemOrderId();
			qty = reportBean.getWordData().get(x).getItemQuantity();
			addOnNewColFlag = reportBean.getWordData().get(x).getAddOnNewColFlag();
			space = reportBean.getWordData().get(x).getSpaceF2f();
			int counter3 = 0;

			int[] cols = { 4896, 1872, 4032, 1728, 1440 };
			//
			// if (!itemcodeele.equals(previtemcodeele) && itemcodeele !=
			// null)

			itemnameele = reportBean.getWordData().get(x).getItemName();
			desitemnameele = reportBean.getWordData().get(x).getDesItemName();

			// to print subcategory name
			if (!subcategorynameele.equals(prevsubcategoryele)) {
				paragraph = document.createParagraph();
				run = paragraph.createRun();

				run.setText("");
				paragraph.setPageBreak(true);

				XWPFParagraph paragraphcat = document.createParagraph();
				XWPFRun runcat = paragraphcat.createRun();
				runcat.setText(subcategorynameele);
				runcat.setBold(true);
				runcat.setFontFamily("Cambria (Headings)");
				runcat.setFontSize(14);
				paragraphcat.setStyle("heading 1");

			}

			// to print item name only once
			if (!itemnameele.equals(previtemnameele)) {

				counter4 = counter4 + 1;
				if (counter4 > 1) {
					if ((prevfootertextele != null) && !(prevfootertextele.equals("NULL"))
							&& !(prevfootertextele.equals("NULL:"))) {
						if (!footertextele.equals(prevfootertextele)) {
							if (prevfootertextele.contains("\n")) {

								String[] arrOfStr = prevfootertextele.split("\n", 20);
								for (int x1 = 0; x1 < arrOfStr.length; x1++) {
									XWPFParagraph paragraph21 = document.createParagraph();

									// System.out.println(table.getNumberOfRows());
									// paragraph21.setAlignment(ParagraphAlignment.CENTER);
									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									// run123.setFontFamily("Cambria
									// (Headings)");
									run123.setText(arrOfStr[x1]);
									logger.info("rhs word start");
									logger.info(arrOfStr[x1]);
									logger.info("rhs word end");
								}
							} else {

								XWPFParagraph paragraph21 = document.createParagraph();

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(prevfootertextele);
							}
						}
					}
					if ((reportBean.getWordData().get(x - 1).getTechRemarks() != null)) {
						if (!(reportBean.getWordData().get(x - 1).getTechRemarks().equalsIgnoreCase("NULL"))) {
							if (reportBean.getWordData().get(x - 1).getTechRemarks().contains("\n")) {

								String[] arrOfStr = reportBean.getWordData().get(x - 1).getTechRemarks().split("\n",
										20);
								for (int x1 = 0; x1 < arrOfStr.length; x1++) {
									XWPFParagraph paragraph21 = document.createParagraph();

									// System.out.println(table.getNumberOfRows());
									// paragraph21.setAlignment(ParagraphAlignment.CENTER);
									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									// run123.setFontFamily("Cambria
									// (Headings)");
									run123.setText(arrOfStr[x1]);
									logger.info("rhs word start");
									logger.info(arrOfStr[x1]);
									logger.info("rhs word end");
								}
							} else {

								XWPFParagraph paragraph21 = document.createParagraph();

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(reportBean.getWordData().get(x - 1).getTechRemarks());
							}

						}
					}
				}

				XWPFParagraph paragraph4 = document.createParagraph();
				XWPFRun run4 = paragraph4.createRun();

				run4.setText(itemnameele);
				if ((qty) != prevqty) {
					if (reportBean.getWordData().get(x).getItemQuantity() > 1) {
						run4.setText(space + space + space + space + space + space + space + space + space + space
								+ space + space + space + space + space + space + space + space + space + space + space
								+ space + space + space + space
								+ String.valueOf("Quantity :" + reportBean.getWordData().get(x).getItemQuantity()));
					}
				}
				run4.setBold(true);
				run4.setFontFamily("Cambria (Headings)");
				run4.setFontSize(13);

				// table = document.createTable();
				//
				// CTTblLayoutType type =
				// table.getCTTbl().getTblPr().addNewTblLayout();
				// type.setType(STTblLayoutType.FIXED);
				// CTTblPr tblpro = table.getCTTbl().getTblPr();
				//
				// CTTblBorders borders = tblpro.addNewTblBorders();
				// borders.addNewBottom().setVal(STBorder.NONE);
				// borders.addNewLeft().setVal(STBorder.NONE);
				// borders.addNewRight().setVal(STBorder.NONE);
				// borders.addNewTop().setVal(STBorder.NONE);
				// // also inner borders
				// borders.addNewInsideH().setVal(STBorder.NONE);
				// borders.addNewInsideV().setVal(STBorder.NONE);
				// table.setWidth(2000);
				// CTTblWidth width =
				// table.getCTTbl().addNewTblPr().addNewTblW();
				// width.setType(STTblWidth.DXA);
				// width.setW(BigInteger.valueOf(9072));
				// CTTblPr tblpro = table.getCTTbl().getTblPr();
				//
				// CTTblBorders borders = tblpro.addNewTblBorders();
				// borders.addNewBottom().setVal(STBorder.SINGLE);
				// borders.addNewLeft().setVal(STBorder.SINGLE);
				// borders.addNewRight().setVal(STBorder.SINGLE);
				// borders.addNewTop().setVal(STBorder.SINGLE);
				// //also inner borders
				// borders.addNewInsideH().setVal(STBorder.SINGLE);
				// borders.addNewInsideV().setVal(STBorder.SINGLE);
				// setTableAlign(table, ParagraphAlignment.LEFT);// Creating
				// // table
				// // only once
				// table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
				// table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(2000));
				// table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));

				// tableRowOne1 = table.getRow(0);
			} // if (!itemnameele.equals(previtemnameele))
				// This is to print the technical remarks at the end of the item
			if (!itemcodeele.equals(previtemcodeele) && itemcodeele != null) {
				logger.info("inside tech remarks of electrical if 1");
				if (techremarksele != null) {
					logger.info("inside tech remarks of electrical if 2");
					// if (!techremarksele.equals("NULL")) {
					// logger.info("inside tech remarks of electrical if 3");
					// paragraph = document.createParagraph();
					// run = paragraph.createRun();
					// run.setText(techremarksele);
					// // run.setFontFamily("Cambria (Headings)");
					// run.setFontSize(11);
					// } // if (!techremarksele.equals(prevtechremarksele))
				} // if (techremarksele != null)
			}
			if (desitemnameele != null) {
				if (!desitemnameele.equals(prevdesitemnameele)) {
					if (count6 > 0) {

						if (prevdesitemnameele != null) {

							if (prevdesitemnameele.equalsIgnoreCase(
									"Multifunctional digital generator protection relay shall have following protections features")) {
								paragraph = document.createParagraph();
								run = paragraph.createRun();
								run.setText("");
							}
						}
						if (prevdesitemnameele.equals("Design Data")
								&& reportBean.getWordData().get(x - 1).getLinkFlag() == 1) {
							paragraph = document.createParagraph();
							run = paragraph.createRun();
							run.setText("");
						}
					}
					count6 = count6 + 1;
					count = 0;
					XWPFParagraph paragraph23 = document.createParagraph();
					XWPFRun run12 = paragraph23.createRun();
					run12.setText(String.valueOf(desItemOrderId));
					run12.setFontSize(12);
					run12.setText("   ");
					run12.setFontSize(12);
					run12.setText(desitemnameele);
					run12.setFontSize(12);
					run12.setBold(true);
					run12.setFontFamily("Cambria (Headings)");

					if ((headertextele != null) && !headertextele.equals("NULL")) {
						if (!headertextele.equals(prevheadertextele)) {
							if (headertextele.contains("\n")) {

								String[] arrOfStr = headertextele.split("\n", 20);
								for (int x1 = 0; x1 < arrOfStr.length; x1++) {
									XWPFParagraph paragraph21 = document.createParagraph();

									// System.out.println(table.getNumberOfRows());
									// paragraph21.setAlignment(ParagraphAlignment.CENTER);
									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									// run123.setFontFamily("Cambria
									// (Headings)");
									run123.setText(arrOfStr[x1]);
									// run123.addBreak();
									logger.info("rhs word start");
									logger.info(arrOfStr[x1]);
									logger.info("rhs word end");
								}
							} else {
								XWPFParagraph paragraph21 = document.createParagraph();
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(headertextele);

							}
						}
					}
					if (dessubitemnameele == null) {
						table = document.createTable();

						// table.setWidth(2000);
						CTTblLayoutType type = table.getCTTbl().getTblPr().addNewTblLayout();
						type.setType(STTblLayoutType.FIXED);

						// table.setInsideHBorder(XWPFBorderType.SINGLE, 8, 5,
						// "0000FF");
						// table.setInsideVBorder(XWPFBorderType.SINGLE, 8, 5,
						// "0000FF");

						// CTTblWidth width =
						// table.getCTTbl().addNewTblPr().addNewTblW();
						// width.setType(STTblWidth.DXA);
						// width.setW(BigInteger.valueOf(9072));
						if (desitemnameele.equalsIgnoreCase(
								"Multifunctional digital generator protection relay shall have following protections features")) {
							// CTTblPr tblpro = table.getCTTbl().getTblPr();
							//
							// CTTblBorders borders = tblpro.addNewTblBorders();
							// borders.addNewBottom().setVal(STBorder.THICK);
							// borders.addNewLeft().setVal(STBorder.THICK);
							// borders.addNewRight().setVal(STBorder.THICK);
							// borders.addNewTop().setVal(STBorder.THICK);
							//
							//
							// // also inner borders
							// borders.addNewInsideH().setVal(STBorder.THICK);
							// borders.addNewInsideV().setVal(STBorder.THICK);
							// borders.getInsideH().setSz(BigInteger.valueOf(12));
							// borders.getInsideV().setSz(BigInteger.valueOf(12));
							// borders.getTop().setSz(BigInteger.valueOf(12));
							// borders.getBottom().setSz(BigInteger.valueOf(12));
							// borders.getLeft().setSz(BigInteger.valueOf(12));
							// borders.getRight().setSz(BigInteger.valueOf(12));
							// table.getCTTbl().getTblPr().getTblBorders().getTop().setSz(BigInteger.valueOf(12));
							// table.getCTTbl().getTblPr().getTblBorders().getLeft().setSz(BigInteger.valueOf(12));
							// table.getCTTbl().getTblPr().getTblBorders().getRight().setSz(BigInteger.valueOf(12));
							// table.getCTTbl().getTblPr().getTblBorders().getBottom().setSz(BigInteger.valueOf(12));
							// table.getCTTbl().getTblPr().getTblBorders().getInsideH().setSz(BigInteger.valueOf(12));
							// table.getCTTbl().getTblPr().getTblBorders().getInsideV().setSz(BigInteger.valueOf(12));
							table.setCellMargins(200, 100, 100, 100); // set
																		// margins
																		// here

							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(5000));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
						} else if (desitemnameele.equals("Design Data") && linkFlag == 1) {
							CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
							type1.setType(STTblLayoutType.FIXED);
							setTableAlign(table, ParagraphAlignment.CENTER);

							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(2000));
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));

						} else {
							CTTblPr tblpro = table.getCTTbl().getTblPr();

							CTTblBorders borders = tblpro.addNewTblBorders();
							borders.addNewBottom().setVal(STBorder.NONE);
							borders.addNewLeft().setVal(STBorder.NONE);
							borders.addNewRight().setVal(STBorder.NONE);
							borders.addNewTop().setVal(STBorder.NONE);
							// also inner borders
							borders.addNewInsideH().setVal(STBorder.NONE);
							borders.addNewInsideV().setVal(STBorder.NONE);
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));

						}

						setTableAlign(table, ParagraphAlignment.LEFT);// Creating
																		// table
																		// only
																		// once
						tableRowOne1 = table.getRow(0);
					}

				} // if (!subitemnameele.equals(prevdesitemnameele))
				else {
					if (!itemnameele.equals(previtemnameele)) {
						count = 0;
						XWPFParagraph paragraph23 = document.createParagraph();
						XWPFRun run12 = paragraph23.createRun();
						run12.setText(String.valueOf(desItemOrderId));
						run12.setFontSize(12);
						run12.setText("   ");
						run12.setFontSize(12);
						run12.setText(desitemnameele);
						run12.setFontSize(12);
						run12.setBold(true);
						run12.setFontFamily("Cambria (Headings)");

						if ((headertextele != null) && !headertextele.equals("NULL")) {
							if (!headertextele.equals(prevheadertextele)) {
								if (headertextele.contains("\n")) {

									String[] arrOfStr = headertextele.split("\n", 20);
									for (int x1 = 0; x1 < arrOfStr.length; x1++) {
										XWPFParagraph paragraph21 = document.createParagraph();

										// System.out.println(table.getNumberOfRows());
										// paragraph21.setAlignment(ParagraphAlignment.CENTER);
										XWPFRun run123 = paragraph21.createRun();
										run123.setFontSize(11);
										// run123.setFontFamily("Cambria
										// (Headings)");
										run123.setText(arrOfStr[x1]);
										// run123.addBreak();
										logger.info("rhs word start");
										logger.info(arrOfStr[x1]);
										logger.info("rhs word end");
									}
								} else {
									XWPFParagraph paragraph21 = document.createParagraph();
									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									// run123.setFontFamily("Cambria
									// (Headings)");
									run123.setText(headertextele);

								}

							}
						}
						if (dessubitemnameele == null) {
							table = document.createTable();

							// table.setWidth(2000);
							CTTblLayoutType type = table.getCTTbl().getTblPr().addNewTblLayout();
							type.setType(STTblLayoutType.FIXED);
							// table.setInsideHBorder(XWPFBorderType.SINGLE, 8,
							// 5,
							// "0000FF");
							// table.setInsideVBorder(XWPFBorderType.SINGLE, 8,
							// 5,
							// "0000FF");

							// CTTblWidth width =
							// table.getCTTbl().addNewTblPr().addNewTblW();
							// width.setType(STTblWidth.DXA);
							// width.setW(BigInteger.valueOf(9072));
							if (desitemnameele.equalsIgnoreCase(
									"Multifunctional digital generator protection relay shall have following protections features")) {
								// CTTblPr tblpro = table.getCTTbl().getTblPr();
								//
								// CTTblBorders borders =
								// tblpro.addNewTblBorders();
								// borders.addNewBottom().setVal(STBorder.THICK);
								// borders.addNewLeft().setVal(STBorder.THICK);
								// borders.addNewRight().setVal(STBorder.THICK);
								// borders.addNewTop().setVal(STBorder.THICK);
								// // also inner borders
								// borders.addNewInsideH().setVal(STBorder.THICK);
								// borders.addNewInsideV().setVal(STBorder.THICK);
								// borders.addNewTop().setVal(STBorder.THICK);
								//
								// borders.getTop().setSz(BigInteger.valueOf(12));
								// borders.getBottom().setSz(BigInteger.valueOf(12));
								// borders.getLeft().setSz(BigInteger.valueOf(12));
								// borders.getRight().setSz(BigInteger.valueOf(12));
								// borders.getInsideH().setSz(BigInteger.valueOf(12));
								// borders.getInsideV().setSz(BigInteger.valueOf(12));
								// table.getCTTbl().getTblPr().getTblBorders().getTop().setSz(BigInteger.valueOf(12));
								// table.getCTTbl().getTblPr().getTblBorders().getLeft().setSz(BigInteger.valueOf(12));
								// table.getCTTbl().getTblPr().getTblBorders().getRight().setSz(BigInteger.valueOf(12));
								// table.getCTTbl().getTblPr().getTblBorders().getBottom().setSz(BigInteger.valueOf(12));
								// table.getCTTbl().getTblPr().getTblBorders().getInsideH().setSz(BigInteger.valueOf(12));
								// table.getCTTbl().getTblPr().getTblBorders().getInsideV().setSz(BigInteger.valueOf(12));
								table.setCellMargins(200, 100, 100, 100); // set
																			// margins
																			// here

								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));
								table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(5000));
								table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
							} else if (desitemnameele.equals("Design Data") && linkFlag == 1) {
								CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
								type1.setType(STTblLayoutType.FIXED);
								setTableAlign(table, ParagraphAlignment.CENTER);

								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(2000));
								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1500));

							} else {
								CTTblPr tblpro = table.getCTTbl().getTblPr();

								CTTblBorders borders = tblpro.addNewTblBorders();
								borders.addNewBottom().setVal(STBorder.NONE);
								borders.addNewLeft().setVal(STBorder.NONE);
								borders.addNewRight().setVal(STBorder.NONE);
								borders.addNewTop().setVal(STBorder.NONE);
								// also inner borders
								borders.addNewInsideH().setVal(STBorder.NONE);
								borders.addNewInsideV().setVal(STBorder.NONE);
								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
								table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
								table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1500));

							}

							setTableAlign(table, ParagraphAlignment.LEFT);// Creating
																			// table
																			// only
																			// once
							tableRowOne1 = table.getRow(0);

						}

					}
				}

				if (dessubitemnameele != null) {
					if (!dessubitemnameele.equals(prevdessubitemnameele)) {
						counter3 = 1;
						XWPFParagraph paragraph3 = document.createParagraph();
						XWPFRun run3 = paragraph3.createRun();
						run3.setBold(true);
						run3.setText("1." + dessubitemorderidele + "   " + dessubitemnameele);
						run3.setFontFamily("Cambria (Headings)");
						run3.setFontSize(12);

						table = document.createTable();

						// table.setInsideHBorder(XWPFTable.XWPFBorderType.DASHED,
						// 4, 0, "0000FF");
						// table.setInsideVBorder(XWPFTable.XWPFBorderType.DASHED,
						// 4, 0, "0000FF");

						// table.setCellMargins(1, left, bottom, right);
						CTTblLayoutType type = table.getCTTbl().getTblPr().addNewTblLayout();
						type.setType(STTblLayoutType.FIXED);
						if (dessubitemnameele.equalsIgnoreCase(
								"Multifunctional digital generator protection relay shall have following protections features")) {
							// CTTblPr tblpro = table.getCTTbl().getTblPr();
							//
							// CTTblBorders borders = tblpro.addNewTblBorders();
							// borders.addNewBottom().setVal(STBorder.THICK);
							// borders.addNewLeft().setVal(STBorder.THICK);
							// borders.addNewRight().setVal(STBorder.THICK);
							// borders.addNewTop().setVal(STBorder.THICK);
							// // also inner borders
							// borders.addNewInsideH().setVal(STBorder.THICK);
							// borders.addNewInsideV().setVal(STBorder.THICK);
							// borders.addNewTop().setVal(STBorder.THICK);
							// borders.getTop().setSz(BigInteger.valueOf(12));
							// borders.getBottom().setSz(BigInteger.valueOf(12));
							// borders.getLeft().setSz(BigInteger.valueOf(12));
							// borders.getRight().setSz(BigInteger.valueOf(12));
							// borders.getInsideH().setSz(BigInteger.valueOf(12));
							// borders.getInsideV().setSz(BigInteger.valueOf(12));
							// table.getCTTbl().getTblPr().getTblBorders().getTop().setSz(BigInteger.valueOf(12));
							// table.getCTTbl().getTblPr().getTblBorders().getLeft().setSz(BigInteger.valueOf(12));
							// table.getCTTbl().getTblPr().getTblBorders().getRight().setSz(BigInteger.valueOf(12));
							// table.getCTTbl().getTblPr().getTblBorders().getBottom().setSz(BigInteger.valueOf(12));
							// table.getCTTbl().getTblPr().getTblBorders().getInsideH().setSz(BigInteger.valueOf(12));
							// table.getCTTbl().getTblPr().getTblBorders().getInsideV().setSz(BigInteger.valueOf(12));
							table.setCellMargins(200, 100, 100, 100); // set
																		// margins
																		// here

							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(5000));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
						}

						else {
							CTTblPr tblpro = table.getCTTbl().getTblPr();

							CTTblBorders borders = tblpro.addNewTblBorders();
							borders.addNewBottom().setVal(STBorder.NONE);
							borders.addNewLeft().setVal(STBorder.NONE);
							borders.addNewRight().setVal(STBorder.NONE);
							borders.addNewTop().setVal(STBorder.NONE);
							// also inner borders
							borders.addNewInsideH().setVal(STBorder.NONE);
							borders.addNewInsideV().setVal(STBorder.NONE);
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));

							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1500));

						}

						// CTTblWidth width =
						// table.getCTTbl().addNewTblPr().addNewTblW();
						// width.setType(STTblWidth.DXA);
						// width.setW(BigInteger.valueOf(9072));

						// table.setWidth(2000);
						// CTTblPr tblpro = table.getCTTbl().getTblPr();
						//
						// CTTblBorders borders = tblpro.getTblBorders();
						// borders.getBottom().setVal(STBorder.SINGLE);
						// borders.getBottom().setColor("0000FF");
						//
						// borders.getLeft().setVal(STBorder.BASIC_BLACK_DOTS);
						// borders.getLeft().setColor("0000FF");
						// borders.getRight().setVal(STBorder.BASIC_BLACK_DOTS);
						// borders.getRight().setColor("0000FF");
						// borders.getTop().setVal(STBorder.BASIC_BLACK_DOTS);
						// borders.getTop().setColor("0000FF");
						// //also inner borders
						// borders.getInsideH().setVal(STBorder.BASIC_BLACK_DOTS);
						// borders.getInsideH().setColor("0000FF");
						// borders.getInsideV().setVal(STBorder.BASIC_BLACK_DOTS);
						// borders.getInsideV().setColor("0000FF");

						setTableAlign(table, ParagraphAlignment.LEFT);// Creating
																		// table
																		// only
																		// once
						tableRowOne1 = table.getRow(0);

					}
				}

				// To print the item name only once
				// if (desitemnameele != null ) {
				//
				// if (!desitemnameele.equals(prevdesitemnameele)){
				// paragraph = document.createParagraph();
				// run = paragraph.createRun();
				// run.setText(desitemnameele);
				// run.setText(":");
				// run.setBold(true);
				// run.setFontFamily("Cambria (Headings)");
				// run.setFontSize(11);
				//
				// table = document.createTable();
				// setTableAlign(table, ParagraphAlignment.LEFT);// Creating
				// // table
				// // only once
				// tableRowOne1 = table.getRow(0);
				// } // if (!subitemnameele.equals(prevdesitemnameele))
				//
				// if (dessubitemnameele != null &&
				// !dessubitemnameele.equals(prevdessubitemnameele)) {
				//
				//
				// paragraph = document.createParagraph();
				// run = paragraph.createRun();
				// run.setText(dessubitemorderidele+" "+ dessubitemnameele);
				// run.setFontFamily("Cambria (Headings)");
				// run.setFontSize(11);
				//
				// table = document.createTable();
				// setTableAlign(table, ParagraphAlignment.LEFT);// Creating
				// // table
				// // only once
				// tableRowOne1 = table.getRow(0);
				// }
				//

				// To print the lhs and rhs of des item
				String colunmname = reportBean.getWordData().get(x).getColNm();
				String colValCode = reportBean.getWordData().get(x).getColValCd();
				logger.info(itemnameele);
				logger.info("checkcharvalue");
				logger.info(colValCode);

				if (desitemnameele != null && dessubitemnameele == null) // Compare
																			// two
																			// strings
				{

					if (colunmname != null && colValCode != null) {
						// paragraph = document.createParagraph();
						// run = paragraph.createRun();
						//
						// run.setText(descolorderidele+" "+ colunmname);
						// run.setText(":");
						// run.setText(colValCode);
						// run.setFontFamily("Cambria (Headings)");
						// run.setFontSize(11);

						if (tableRowOne1 != null) {
							if (desitemnameele.equalsIgnoreCase(
									"Multifunctional digital generator protection relay shall have following protections features")) {
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// sdMultifunctional digital generator
								// protection relay shall have following
								// protections featuresd

								// tableRowOne1.getCell(0).setText("SL.NO");
								XWPFParagraph pa451 = tableRowOne1.getCell(0).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());

								pa451.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun ru311 = pa451.createRun();
								ru311.setFontSize(11);
								// ru311.setFontFamily("Cambria (Headings)");

								ru311.setText("Sl.no");

								ru311.setBold(true);

								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne1.addNewTableCell();
								XWPFParagraph pa = tableRowOne1.getCell(1).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								pa.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun ru = pa.createRun();
								ru.setFontSize(11);
								// ru.setFontFamily("Cambria (Headings)");
								ru.setText("Category");
								ru.setBold(true);

								tableRowOne1.addNewTableCell();
								XWPFParagraph pa1 = tableRowOne1.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								pa1.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun ru1 = pa1.createRun();
								ru1.setFontSize(11);
								// ru1.setFontFamily("Cambria (Headings)");
								ru1.setText("Description");
								ru1.setBold(true);

								// tableRowOne1.getCell(0).setColor("808080");
								// tableRowOne1.getCell(1).setColor("808080");
								// tableRowOne1.getCell(2).setColor("808080");
								tableRowOne1 = null;
								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);
								XWPFTableRow tableRowOne = table.createRow();
								// tableRowOne.getCell(0).setText(descolorderidele);
								paragraphtable = tableRowOne.getCell(0).getParagraphs().get(0);
								paragraphtable.setIndentationLeft(100);
								paragraphtable.setIndentationRight(100);
								paragraphtable.setAlignment(ParagraphAlignment.CENTER);
								runtable = paragraphtable.createRun();
								runtable.setFontSize(11);
								// runtable.setFontFamily("Cambria (Headings)");
								runtable.setText(descolorderidele);
								tableRowOne.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne.getCell(1).setText(colunmname);
								if (subColValCodeele != null && !(subColValCodeele.equals("NULL"))) {
									tableRowOne.getCell(2).setText(subColValCodeele);
								} else {
									// tableRowOne.getCell(2).setText(colValCode);
									if (colValCode.contains("\n")) {

										String[] arrOfStr = colValCode.split("\n", 20);
										for (int x1 = 0; x1 < arrOfStr.length; x1++) {
											paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);
											paragraphtable.setIndentationLeft(100);
											paragraphtable.setIndentationRight(100);
											paragraphtable.setAlignment(ParagraphAlignment.CENTER);
											runtable = paragraphtable.createRun();
											runtable.setFontSize(11);
											// runtable.setFontFamily("Cambria
											// (Headings)");
											runtable.setText(arrOfStr[x1]);
											runtable.addBreak();
											tableRowOne.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
										}
									}

									else {
										paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);
										paragraphtable.setIndentationLeft(100);
										paragraphtable.setIndentationRight(100);
										paragraphtable.setAlignment(ParagraphAlignment.CENTER);
										runtable = paragraphtable.createRun();
										runtable.setFontSize(11);
										// runtable.setFontFamily("Cambria
										// (Headings)");
										runtable.setText(colValCode);
										tableRowOne.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
									}

								}

								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							} else if (desitemnameele.equals("Design Data") && linkFlag == 1) {
								tableRowOne1.getCell(0).setText("Sl.no");

								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne1.addNewTableCell().setText("Scope of supply and battery limits");
								tableRowOne1.addNewTableCell();
								XWPFParagraph paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								paragraph12.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run12 = paragraph12.createRun();
								run12.setFontSize(11);
								// run12.setFontFamily("Cambria (Headings)");
								run12.setText("Engineering");

								tableRowOne1.addNewTableCell().setText("Supply");

								tableRowOne1.getCell(0).setColor("808080");
								tableRowOne1.getCell(1).setColor("808080");
								tableRowOne1.getCell(2).setColor("808080");
								tableRowOne1.getCell(3).setColor("808080");
								tableRowOne1 = null;
								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.getCell(0).setText(descolorderidele);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne.getCell(1).setText(colunmname);
								if (colValCode.equalsIgnoreCase("Yes") || colValCode.equalsIgnoreCase("No")) {
									tableRowOne.getCell(2).setText("Yes");
									tableRowOne.getCell(3).setText(colValCode);
								} else {
									tableRowOne.getCell(2).setText(" ");
									CTTc ctTc1 = tableRowOne.getCell(2).getCTTc();
									CTTcPr tcPr1 = ctTc1.addNewTcPr();
									CTHMerge hMerge1 = tcPr1.addNewHMerge();
									hMerge1.setVal(STMerge.RESTART);

									CTTcBorders tblBorders1 = tcPr1.addNewTcBorders();

									tblBorders1.addNewRight().setVal(STBorder.NIL);
									tableRowOne.getCell(3).setText(colValCode);
									CTTc ctTc12 = tableRowOne.getCell(3).getCTTc();
									CTTcPr tcPr12 = ctTc12.addNewTcPr();
									CTHMerge hMerge12 = tcPr12.addNewHMerge();
									hMerge12.setVal(STMerge.RESTART);

									CTTcBorders tblBorders12 = tcPr12.addNewTcBorders();

									tblBorders12.addNewLeft().setVal(STBorder.NIL);

								}

								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							} else {

								tableRowOne1.getCell(0).setText(descolorderidele);
								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne1.addNewTableCell().setText(colunmname);
								;
								tableRowOne1.addNewTableCell();
								XWPFParagraph paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								paragraph12.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run12 = paragraph12.createRun();
								run12.setFontSize(14);
								run12.setFontFamily("Cambria (Headings)");
								run12.setText(":");
								if (subColValCodeele != null && !(subColValCodeele.equals("NULL"))) {
									tableRowOne1.addNewTableCell().setText(subColValCodeele);
								} else {
									tableRowOne1.addNewTableCell().setText(colValCode);
								}

								tableRowOne1 = null;
								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

						}

						else {
							if (desitemnameele.equalsIgnoreCase(
									"Multifunctional digital generator protection relay shall have following protections features")) {

								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.setCantSplitRow(true);
								paragraphtable = tableRowOne.getCell(0).getParagraphs().get(0);
								// paragraphtable.setIndentationLeft(100);
								// paragraphtable.setIndentationRight(100);
								paragraphtable.setAlignment(ParagraphAlignment.CENTER);
								runtable = paragraphtable.createRun();
								runtable.setFontSize(11);
								// runtable.setFontFamily("Cambria (Headings)");
								runtable.setText(descolorderidele);
								// tableRowOne.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
								if (colunmname.contains("\n")) {
									String[] arrOfStr = colunmname.split("\n", 20);
									for (int x1 = 0; x1 < arrOfStr.length; x1++) {
										paragraphtable = tableRowOne.getCell(1).getParagraphs().get(0);
										// paragraphtable.setIndentationLeft(100);
										// paragraphtable.setIndentationRight(100);
										// paragraphtable.setAlignment(ParagraphAlignment.CENTER);
										runtable = paragraphtable.createRun();
										runtable.setFontSize(11);
										// runtable.setFontFamily("Cambria
										// (Headings)");
										runtable.setText(arrOfStr[x1]);
										if (x1 + 1 != arrOfStr.length) {
											runtable.addBreak();
										}
										// tableRowOne.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
									}
								}

								else {
									tableRowOne.getCell(1).setText(colunmname);
								}
								if (subColValCodeele != null && !(subColValCodeele.equals("NULL"))) {
									tableRowOne.getCell(2).setText(subColValCodeele);
								} else {
									// tableRowOne.getCell(2).setText(colValCode);
									if (colValCode.contains("\n")) {

										String[] arrOfStr = colValCode.split("\n", 20);
										paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);
										// paragraphtable.setIndentationLeft(100);
										// paragraphtable.setIndentationRight(100);
										paragraphtable.setAlignment(ParagraphAlignment.CENTER);
										runtable = paragraphtable.createRun();
										runtable.setFontSize(11);
										for (int x1 = 0; x1 < arrOfStr.length; x1++) {

											// runtable.setFontFamily("Cambria
											// (Headings)");
											runtable.setText(arrOfStr[x1]);
											if (x1 + 1 != arrOfStr.length) {
												runtable.addBreak();
											}
											// tableRowOne.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
										}
									}

									else {
										paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);
										// paragraphtable.setIndentationLeft(100);
										// paragraphtable.setIndentationRight(100);
										paragraphtable.setAlignment(ParagraphAlignment.CENTER);
										runtable = paragraphtable.createRun();
										runtable.setFontSize(11);
										// runtable.setFontFamily("Cambria
										// (Headings)");
										runtable.setText(colValCode);
										// tableRowOne.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
									}

								}

								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

							else if ((colunmname.equals("Additional outgoing feeders ( Ampere rating & quantity )"))
									&& count == 0) {
								count = 1;
								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.getCell(0).setText(descolorderidele);
								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
								tableRowOne.getCell(1).setText(colunmname);
								// tableRowOne.getCell(1).setText(" : ");
								XWPFParagraph paragraph12 = tableRowOne.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								paragraph12.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run12 = paragraph12.createRun();
								run12.setFontSize(14);
								run12.setFontFamily("Cambria (Headings)");
								run12.setText(":");
								if (subColValCodeele != null && !(subColValCodeele.equals("NULL"))) {
									tableRowOne.getCell(3).setText(subColValCodeele);
								} else {
									tableRowOne.getCell(3).setText(colValCode + "*" + qunatityele);
								}

								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

							else if (colunmname.equals("Additional outgoing feeders ( Ampere rating & quantity )")
									&& count != 0) {
								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.getCell(0).setText("");
								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne.getCell(1).setText(" ");
								tableRowOne.getCell(2).setText(" ");
								if (subColValCodeele != null && !(subColValCodeele.equals("NULL"))) {
									tableRowOne.getCell(3).setText(subColValCodeele);
								} else {
									tableRowOne.getCell(3).setText(colValCode + "*" + qunatityele);
								}

								// tableRowOne1 = null;
								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

							else if (desitemnameele.equals("Design Data") && linkFlag == 1) {
								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.getCell(0).setText(descolorderidele);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne.getCell(1).setText(colunmname);
								if (colValCode.equalsIgnoreCase("Yes") || colValCode.equalsIgnoreCase("No")) {
									tableRowOne.getCell(2).setText("Yes");
									tableRowOne.getCell(3).setText(colValCode);
								} else {
									tableRowOne.getCell(2).setText(" ");
									CTTc ctTc1 = tableRowOne.getCell(2).getCTTc();
									CTTcPr tcPr1 = ctTc1.addNewTcPr();
									CTHMerge hMerge1 = tcPr1.addNewHMerge();
									hMerge1.setVal(STMerge.RESTART);

									CTTcBorders tblBorders1 = tcPr1.addNewTcBorders();

									tblBorders1.addNewRight().setVal(STBorder.NIL);
									tableRowOne.getCell(3).setText(colValCode);
									CTTc ctTc12 = tableRowOne.getCell(3).getCTTc();
									CTTcPr tcPr12 = ctTc12.addNewTcPr();
									CTHMerge hMerge12 = tcPr12.addNewHMerge();
									hMerge12.setVal(STMerge.RESTART);

									CTTcBorders tblBorders12 = tcPr12.addNewTcBorders();

									tblBorders12.addNewLeft().setVal(STBorder.NIL);

								}

								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

							else {

								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.getCell(0).setText(descolorderidele);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
								tableRowOne.getCell(1).setText(colunmname);
								// tableRowOne.getCell(1).setText(" : ");
								XWPFParagraph paragraph12 = tableRowOne.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								paragraph12.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run12 = paragraph12.createRun();
								run12.setFontSize(14);
								run12.setFontFamily("Cambria (Headings)");
								run12.setText(":");
								if (subColValCodeele != null && !(subColValCodeele.equals("NULL"))) {
									tableRowOne.getCell(3).setText(subColValCodeele);
								} else {
									if (colValCode.contains("\n")) {

										String[] arrOfStr = colValCode.split("\n", 20);
										for (int x1 = 0; x1 < arrOfStr.length; x1++) {
											XWPFParagraph paragraph21 = tableRowOne.getCell(3).getParagraphs().get(0);

											// System.out.println(table.getNumberOfRows());
											// paragraph21.setAlignment(ParagraphAlignment.CENTER);
											XWPFRun run123 = paragraph21.createRun();
											run123.setFontSize(11);
											// run123.setFontFamily("Cambria
											// (Headings)");
											run123.setText(arrOfStr[x1]);
											run123.addBreak();
											logger.info("rhs word start");
											logger.info(arrOfStr[x1]);
											logger.info("rhs word end");
										}
									} else {
										tableRowOne.getCell(3).setText(colValCode);

									}
								}

								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

						}

					}
				} else {

					if (desitemnameele != null) {
						// paragraph = document.createParagraph();
						// run = paragraph.createRun();
						// run.setText(descolorderidele+" "+ colunmname);
						// run.setText(":");
						// run.setText(colValCode);
						// run.setFontFamily("Cambria (Headings)");
						// run.setFontSize(11);

						if (tableRowOne1 != null) {
							if (dessubitemnameele.equalsIgnoreCase(
									"Multifunctional digital generator protection relay shall have following protections features")) {
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// sdMultifunctional digital generator
								// protection relay shall have following
								// protections featuresd

								// tableRowOne1.getCell(0).setText("SL.NO");
								// //
								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
								//
								// tableRowOne1.addNewTableCell().setText("CATEGORY");
								// tableRowOne1.addNewTableCell().setText("DESCRIPTION");
								// tableRowOne1.getCell(0).setColor("808080");
								// tableRowOne1.getCell(1).setColor("808080");
								// tableRowOne1.getCell(2).setColor("808080");
								XWPFParagraph pa451 = tableRowOne1.getCell(0).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								pa451.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun ru311 = pa451.createRun();
								ru311.setFontSize(11);
								// ru311.setFontFamily("Cambria (Headings)");
								ru311.setText("Sl.no");
								ru311.setBold(true);

								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne1.addNewTableCell();
								XWPFParagraph pa = tableRowOne1.getCell(1).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								pa.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun ru = pa.createRun();
								ru.setFontSize(11);
								// ru.setFontFamily("Cambria (Headings)");
								ru.setText("Category");
								ru.setBold(true);

								tableRowOne1.addNewTableCell();
								XWPFParagraph pa1 = tableRowOne1.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								pa1.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun ru1 = pa1.createRun();
								ru1.setFontSize(11);
								// ru1.setFontFamily("Cambria (Headings)");
								ru1.setText("Description");
								ru1.setBold(true);

								// tableRowOne1.getCell(0).setColor("GrayColor");
								// tableRowOne1.getCell(1).setColor("GrayColor");
								// tableRowOne1.getCell(2).setColor("GrayColor");
								tableRowOne1 = null;
								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);
								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.setCantSplitRow(true);
								paragraphtable = tableRowOne.getCell(0).getParagraphs().get(0);
								// paragraphtable.setIndentationLeft(100);
								// paragraphtable.setIndentationRight(100);
								paragraphtable.setAlignment(ParagraphAlignment.CENTER);
								runtable = paragraphtable.createRun();
								runtable.setFontSize(11);
								// runtable.setFontFamily("Cambria (Headings)");
								runtable.setText(descolorderidele);
								// tableRowOne.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
								if (colunmname.contains("\n")) {
									String[] arrOfStr = colunmname.split("\n", 20);
									for (int x1 = 0; x1 < arrOfStr.length; x1++) {
										paragraphtable = tableRowOne.getCell(1).getParagraphs().get(0);
										// paragraphtable.setIndentationLeft(100);
										// paragraphtable.setIndentationRight(100);
										// paragraphtable.setAlignment(ParagraphAlignment.CENTER);
										runtable = paragraphtable.createRun();
										runtable.setFontSize(11);
										// runtable.setFontFamily("Cambria
										// (Headings)");
										runtable.setText(arrOfStr[x1]);
										if (x1 + 1 != arrOfStr.length) {
											runtable.addBreak();
										}
										// tableRowOne.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
									}
								}

								else {
									tableRowOne.getCell(1).setText(colunmname);
								}
								if (subColValCodeele != null && !(subColValCodeele.equals("NULL"))) {
									tableRowOne.getCell(2).setText(subColValCodeele);
								} else {
									// tableRowOne.getCell(2).setText(colValCode);
									if (colValCode.contains("\n")) {

										String[] arrOfStr = colValCode.split("\n", 20);
										paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);
										paragraphtable.setIndentationLeft(100);
										paragraphtable.setIndentationRight(100);
										paragraphtable.setAlignment(ParagraphAlignment.CENTER);
										runtable = paragraphtable.createRun();
										runtable.setFontSize(11);
										for (int x1 = 0; x1 < arrOfStr.length; x1++) {

											// runtable.setFontFamily("Cambria
											// (Headings)");
											runtable.setText(arrOfStr[x1]);
											if (x1 + 1 != arrOfStr.length) {
												runtable.addBreak();
											}
											tableRowOne.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
										}
									}

									else {
										paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);
										paragraphtable.setIndentationLeft(100);
										paragraphtable.setIndentationRight(100);
										paragraphtable.setAlignment(ParagraphAlignment.CENTER);
										runtable = paragraphtable.createRun();
										runtable.setFontSize(11);
										// runtable.setFontFamily("Cambria
										// (Headings)");
										runtable.setText(colValCode);
										tableRowOne.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
									}

								}

								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							} else {
								tableRowOne1.getCell(0).setText(descolorderidele);
								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
								tableRowOne1.addNewTableCell().setText(colunmname);
								tableRowOne1.addNewTableCell();
								XWPFParagraph paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								paragraph12.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run12 = paragraph12.createRun();
								run12.setFontSize(14);
								run12.setFontFamily("Cambria (Headings)");
								run12.setText(":");
								if (subColValCodeele != null && !(subColValCodeele.equals("NULL"))) {
									tableRowOne1.addNewTableCell().setText(subColValCodeele);
								} else {
									tableRowOne1.addNewTableCell().setText(colValCode);
								}

								tableRowOne1 = null;
								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);
							}

						}

						else {
							if (dessubitemnameele.equalsIgnoreCase(
									"Multifunctional digital generator protection relay shall have following protections features")) {

								XWPFTableRow tableRowOne = table.createRow();

								tableRowOne.setCantSplitRow(true);
								paragraphtable = tableRowOne.getCell(0).getParagraphs().get(0);
								// paragraphtable.setIndentationLeft(100);
								// paragraphtable.setIndentationRight(100);
								paragraphtable.setAlignment(ParagraphAlignment.CENTER);
								runtable = paragraphtable.createRun();
								runtable.setFontSize(11);
								// runtable.setFontFamily("Cambria (Headings)");
								runtable.setText(descolorderidele);
								// tableRowOne.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
								if (colunmname.contains("\n")) {
									String[] arrOfStr = colunmname.split("\n", 20);
									for (int x1 = 0; x1 < arrOfStr.length; x1++) {
										paragraphtable = tableRowOne.getCell(1).getParagraphs().get(0);
										// paragraphtable.setIndentationLeft(100);
										// paragraphtable.setIndentationRight(100);
										// paragraphtable.setAlignment(ParagraphAlignment.CENTER);
										runtable = paragraphtable.createRun();
										runtable.setFontSize(11);
										// runtable.setFontFamily("Cambria
										// (Headings)");
										runtable.setText(arrOfStr[x1]);
										if (x1 + 1 != arrOfStr.length) {
											runtable.addBreak();
										}
										// tableRowOne.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
									}
								}

								else {
									tableRowOne.getCell(1).setText(colunmname);
								}
								if (subColValCodeele != null && !(subColValCodeele.equals("NULL"))) {
									tableRowOne.getCell(2).setText(subColValCodeele);
								} else {
									// tableRowOne.getCell(2).setText(colValCode);
									if (colValCode.contains("\n")) {
										logger.info(itemnameele);
										logger.info("checkchar10");

										String[] arrOfStr = colValCode.split("\n", 20);
										paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);
										// paragraphtable.setIndentationLeft(100);
										// paragraphtable.setIndentationRight(100);
										paragraphtable.setAlignment(ParagraphAlignment.CENTER);
										runtable = paragraphtable.createRun();
										runtable.setFontSize(11);
										for (int x1 = 0; x1 < arrOfStr.length; x1++) {

											// runtable.setFontFamily("Cambria
											// (Headings)");
											runtable.setText(arrOfStr[x1]);
											if (x1 + 1 != arrOfStr.length) {
												runtable.addBreak();
											}
											// tableRowOne.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
										}
									}

									else {
										paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);
										// paragraphtable.setIndentationLeft(100);
										// paragraphtable.setIndentationRight(100);
										paragraphtable.setAlignment(ParagraphAlignment.CENTER);
										runtable = paragraphtable.createRun();
										runtable.setFontSize(11);
										// runtable.setFontFamily("Cambria
										// (Headings)");
										runtable.setText(colValCode);
										// tableRowOne.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
									}

								}

								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

							else {

								XWPFTableRow tableRowOne = table.createRow();

								tableRowOne.getCell(0).setText(descolorderidele);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
								tableRowOne.getCell(1).setText(colunmname);
								// tableRowOne.getCell(1).setText(" : ");
								XWPFParagraph paragraph12 = tableRowOne.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								paragraph12.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run12 = paragraph12.createRun();
								run12.setFontSize(14);
								run12.setFontFamily("Cambria (Headings)");
								run12.setText(":");

								if (subColValCodeele != null && !(subColValCodeele.equals("NULL"))) {
									tableRowOne.getCell(3).setText(subColValCodeele);
								} else {
									// tableRowOne.getCell(3).setText(colValCode);
									// if (colValCode.contains("\n")) {test
									// logger.info("rhs word start");
									// logger.info(colValCode);
									// logger.info("rhs word end");
									//
									// }
									if (colValCode.contains("\n")) {

										String[] arrOfStr = colValCode.split("\n", 20);
										for (int x1 = 0; x1 < arrOfStr.length; x1++) {
											XWPFParagraph paragraph21 = tableRowOne.getCell(3).getParagraphs().get(0);

											// System.out.println(table.getNumberOfRows());
											// paragraph21.setAlignment(ParagraphAlignment.CENTER);
											XWPFRun run123 = paragraph21.createRun();
											run123.setFontSize(11);
											// run123.setFontFamily("Cambria
											// (Headings)");
											run123.setText(arrOfStr[x1]);
											run123.addBreak();
											logger.info("rhs word start");
											logger.info(arrOfStr[x1]);
											logger.info("rhs word end");
										}
									} else {
										tableRowOne.getCell(3).setText(colValCode);

									}

								}

								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

						}

					}

				}

				// To print technical remarks of the sub itemq

				if (x + 1 == reportBean.getWordData().size()) {
					paragraph = document.createParagraph();
					run = paragraph.createRun();
					logger.info(techremarksele+"*************************");
					run.setText(techremarksele);
					run.addBreak();
					run.addBreak();
					// run.setFontFamily("Cambria (Headings)");
					run.setFontSize(11);
				} // if (x + 1 == reportBean.getWordData().size())

			} // if (desitemnameele != null)

			previtemnameele = itemnameele;
			prevqty = qty;
			prevdesitemnameele = desitemnameele;
			prevdessubitemnameele = dessubitemnameele;
			previtemcodeele = itemcodeele;
			prevtechremarksele = techremarksele;
			prevsubcategoryele = subcategorynameele;
			prevheadertextele = headertextele;
			prevfootertextele = footertextele;

			if (x + 1 == reportBean.getWordData().size()) {
				if ((footertextele != null) && !(footertextele.equals("NULL")) && !(footertextele.equals("NULL:"))) {

					// paragraph = document.createParagraph();
					// run = paragraph.createRun();
					// run.setText(footertextele);
					// //run.setText(":");
					//
					// run.setFontFamily("Cambria (Headings)");
					// run.setFontSize(11);

				}
			}

		} // for loop
			// ele aux start
			// 8,c

		int c = 0;
		int prevqty1 = 0;

		String prevcategoryele1 = null;
		String prevsubcategoryele1 = null;
		String previtemnameele1 = null;
		String prevsubitemnameele1 = null;
		String prevdesitemnameele1 = null;
		String prevdessubitemnameele1 = null;
		String previtemcodeele1 = null;
		String prevtechremarksele1 = null;
		String prevheadertextele1 = null;
		String prevfootertextele1 = null;
		int counter41 = 0;
		int count11 = 0;
		int count7 = 0;
		int count71 = 0;
		for (c = x; c < reportBean.getWordData().size(); c++)

		{
			logger.info("elaux");

			String categorynameele1 = null;
			String subcategorynameele1 = null;
			int qty1 = 0;
			int itemidele1 = 0;
			int desitemorderid = 0;
			String itemnameele1 = null;
			String desitemnameele1 = null;
			String dessubitemnameele1 = null;
			String itemcodeele1 = null;
			String subitemcodeele1 = null;
			String subitemnameele1 = null;
			String space = null;
			int qunatityele1 = 0;
			String techremarksele1 = null;
			String techcommentsele1 = null;
			int numberofsubitemsele1 = 0;
			int orderidele1 = 0;
			int dessubitemorderidele1 = 0;
			String headertextele1 = null;
			String footertextele1 = null;
			String subColValCodeele1 = null;
			String itemnameele = null;
			int addOnNewColFlag = 0;

			String descolorderidele1 = null;
			int linkFlag1 = 0;

			XWPFTableRow tableRowOne11 = null;

			if (c == QuotationDaoImpl.size8) {
				if (counter41 > 0) {
					if ((!reportBean.getWordData().get(c - 1).getFooterText().equals("NULL"))
							&& (reportBean.getWordData().get(c - 1).getFooterText() != null)) {
						if (reportBean.getWordData().get(c - 1).getFooterText().contains("\n")) {

							String[] arrOfStr = reportBean.getWordData().get(c - 1).getFooterText().split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = document.createParagraph();

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(arrOfStr[x1]);
								logger.info("rhs word start");
								logger.info(arrOfStr[x1]);
								logger.info("rhs word end");
							}
						}

						else {

							XWPFParagraph paragraph21 = document.createParagraph();

							// System.out.println(table.getNumberOfRows());
							// paragraph21.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run123 = paragraph21.createRun();
							run123.setFontSize(11);
							// run123.setFontFamily("Cambria (Headings)");
							run123.setText(reportBean.getWordData().get(c - 1).getFooterText());
						}
					}
					if ((reportBean.getWordData().get(c - 1).getTechRemarks() != null)) {

						if ((!reportBean.getWordData().get(c - 1).getTechRemarks().equals("NULL"))) {
							if (reportBean.getWordData().get(c - 1).getTechRemarks().contains("\n")) {

								String[] arrOfStr = reportBean.getWordData().get(c - 1).getTechRemarks().split("\n",
										20);
								for (int x1 = 0; x1 < arrOfStr.length; x1++) {
									XWPFParagraph paragraph21 = document.createParagraph();

									// System.out.println(table.getNumberOfRows());
									// paragraph21.setAlignment(ParagraphAlignment.CENTER);
									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									// run123.setFontFamily("Cambria
									// (Headings)");
									run123.setText(arrOfStr[x1]);
									logger.info("rhs word start");
									logger.info(arrOfStr[x1]);
									logger.info("rhs word end");
								}
							} else {

								XWPFParagraph paragraph21 = document.createParagraph();

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(reportBean.getWordData().get(c - 1).getTechRemarks());
							}
						}
					}
				}
				break;
			}

			categorynameele1 = reportBean.getWordData().get(c).getCategoryName();
			subcategorynameele1 = reportBean.getWordData().get(c).getSubCategoryName();
			itemidele1 = reportBean.getWordData().get(c).getItemId();
			itemnameele1 = reportBean.getWordData().get(c).getSubItemName();
			desitemnameele1 = reportBean.getWordData().get(c).getDesItemName();
			dessubitemnameele1 = reportBean.getWordData().get(c).getDesSubItemName();
			itemcodeele1 = reportBean.getWordData().get(c).getItemCode();
			subitemcodeele1 = reportBean.getWordData().get(c).getSubItemCode();
			subitemnameele1 = reportBean.getWordData().get(c).getSubItemTypeName();
			qunatityele1 = reportBean.getWordData().get(c).getQuantityEle();
			techremarksele1 = reportBean.getWordData().get(c).getTechRemarks();
			techcommentsele1 = reportBean.getWordData().get(c).getTechComments();
			numberofsubitemsele1 = reportBean.getWordData().get(c).getNumberOfSubItems();
			orderidele1 = reportBean.getWordData().get(c).getOrderId();
			dessubitemorderidele1 = reportBean.getWordData().get(c).getDesSubItemOrderId();
			descolorderidele1 = reportBean.getWordData().get(c).getDesColOrderId();
			subColValCodeele1 = reportBean.getWordData().get(c).getSubColValCode();
			headertextele1 = reportBean.getWordData().get(c).getHeaderText();
			footertextele1 = reportBean.getWordData().get(c).getFooterText();
			linkFlag1 = reportBean.getWordData().get(c).getLinkFlag();
			desitemorderid = reportBean.getWordData().get(c).getDesItemOrderId();
			itemnameele = reportBean.getWordData().get(c).getItemName();
			addOnNewColFlag = reportBean.getWordData().get(c).getAddOnNewColFlag();
			qty1 = reportBean.getWordData().get(c).getItemQuantity();
			space = reportBean.getWordData().get(c).getSpaceF2f();
			logger.info(reportBean.getWordData().get(c).getColValCd());
			int counter31 = 0;

			int[] cols1 = { 4896, 1872, 4032, 1728, 1440 };
			//
			// This is to print the technical remarks at the end of the item
			if (!itemcodeele1.equals(previtemcodeele1) && itemcodeele1 != null) {
				if (techremarksele1 != null) {
					// if (!techremarksele1.equals(prevtechremarksele1)) {
					//
					// paragraph = document.createParagraph();
					// run = paragraph.createRun();
					// run.setText(prevtechremarksele1);
					// run.addBreak();
					// run.addBreak();
					// // run.setFontFamily("Cambria (Headings)");
					// run.setFontSize(11);
					// } // if (!techremarksele.equals(prevtechremarksele))
				} // if (techremarksele != null)
			} // if (!itemcodeele.equals(previtemcodeele) && itemcodeele !=
				// null)

			itemnameele1 = reportBean.getWordData().get(c).getSubItemName();
			desitemnameele1 = reportBean.getWordData().get(c).getDesItemName();

			// // to print subcategory name
			// if (!subcategorynameele1.equals(prevsubcategoryele1)) {
			// paragraph = document.createParagraph();
			// run = paragraph.createRun();
			//
			// run.setText("");
			// paragraph = document.createParagraph();
			// run = paragraph.createRun();
			//
			// run.setText(subcategorynameele1);
			// run.setBold(true);
			// run.setFontFamily("Cambria (Headings)");
			// run.setFontSize(14);
			// paragraph.setStyle("heading 1");
			//
			// }

			// to print item name only once
			if (counter41 == 0) {

				paragraph = document.createParagraph();
				run = paragraph.createRun();
				run.setText(itemnameele);
				run.setFontFamily("Cambria (Headings)");
				run.setBold(true);
				run.setFontSize(13);
			}
			if (!itemnameele1.equals(previtemnameele1)) {

				counter41 = counter41 + 1;
				if (counter41 > 1) {
					if ((prevfootertextele1 != null) && !(prevfootertextele1.equals("NULL"))
							&& !(prevfootertextele1.equals("NULL:"))) {
						if (!footertextele1.equals(prevfootertextele1)) {
							if (prevfootertextele1.contains("\n")) {

								String[] arrOfStr = prevfootertextele1.split("\n", 20);
								for (int x1 = 0; x1 < arrOfStr.length; x1++) {
									XWPFParagraph paragraph21 = document.createParagraph();

									// System.out.println(table.getNumberOfRows());
									// paragraph21.setAlignment(ParagraphAlignment.CENTER);
									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									// run123.setFontFamily("Cambria
									// (Headings)");
									run123.setText(arrOfStr[x1]);
									logger.info("rhs word start");
									logger.info(arrOfStr[x1]);
									logger.info("rhs word end");
								}
							} else {

								XWPFParagraph paragraph21 = document.createParagraph();

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(prevfootertextele1);
							}

						}
					}
					if ((reportBean.getWordData().get(c - 1).getTechRemarks() != null)) {
						if ((!reportBean.getWordData().get(c - 1).getTechRemarks().equalsIgnoreCase("NULL"))) {
							if (reportBean.getWordData().get(c - 1).getTechRemarks().contains("\n")) {

								String[] arrOfStr = reportBean.getWordData().get(c - 1).getTechRemarks().split("\n",
										20);
								for (int x1 = 0; x1 < arrOfStr.length; x1++) {
									XWPFParagraph paragraph21 = document.createParagraph();

									// System.out.println(table.getNumberOfRows());
									// paragraph21.setAlignment(ParagraphAlignment.CENTER);
									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									// run123.setFontFamily("Cambria
									// (Headings)");
									run123.setText(arrOfStr[x1]);
									logger.info("rhs word start");
									logger.info(arrOfStr[x1]);
									logger.info("rhs word end");
								}
							} else {

								XWPFParagraph paragraph21 = document.createParagraph();

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(reportBean.getWordData().get(c - 1).getTechRemarks());
							}

						}
					}
				}

				paragraph = document.createParagraph();
				run = paragraph.createRun();

				run.setText(itemnameele1);
				if ((qty1) != prevqty1) {
					if (reportBean.getWordData().get(c).getItemQuantity() > 1) {
						run.setText(space + space + space + space + space + space + space + space + space + space
								+ space + space + space + space + space + space + space + space + space + space + space
								+ String.valueOf("Quantity :" + reportBean.getWordData().get(c).getItemQuantity()));
					}
				}
				run.setBold(true);
				run.setFontFamily("Cambria (Headings)");
				run.setFontSize(13);

				// table = document.createTable();
				//
				// CTTblLayoutType type =
				// table.getCTTbl().getTblPr().addNewTblLayout();
				// type.setType(STTblLayoutType.FIXED);
				// CTTblPr tblpro = table.getCTTbl().getTblPr();
				//
				// CTTblBorders borders = tblpro.addNewTblBorders();
				// borders.addNewBottom().setVal(STBorder.NONE);
				// borders.addNewLeft().setVal(STBorder.NONE);
				// borders.addNewRight().setVal(STBorder.NONE);
				// borders.addNewTop().setVal(STBorder.NONE);
				// // also inner borders
				// borders.addNewInsideH().setVal(STBorder.NONE);
				// borders.addNewInsideV().setVal(STBorder.NONE);
				// table.setWidth(2000);
				// CTTblWidth width =
				// table.getCTTbl().addNewTblPr().addNewTblW();
				// width.setType(STTblWidth.DXA);
				// width.setW(BigInteger.valueOf(9072));
				// CTTblPr tblpro = table.getCTTbl().getTblPr();
				//
				// CTTblBorders borders = tblpro.addNewTblBorders();
				// borders.addNewBottom().setVal(STBorder.SINGLE);
				// borders.addNewLeft().setVal(STBorder.SINGLE);
				// borders.addNewRight().setVal(STBorder.SINGLE);
				// borders.addNewTop().setVal(STBorder.SINGLE);
				// //also inner borders
				// borders.addNewInsideH().setVal(STBorder.SINGLE);
				// borders.addNewInsideV().setVal(STBorder.SINGLE);
				// setTableAlign(table, ParagraphAlignment.LEFT);// Creating
				// // table
				// // only once
				// table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
				// table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(2000));
				// table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));

				// tableRowOne1 = table.getRow(0);
			} // if (!itemnameele.equals(previtemnameele))
			if (subitemnameele1 != null) {

				if (!subitemnameele1.equals(prevsubitemnameele1)) {
					paragraph = document.createParagraph();
					run = paragraph.createRun();

					run.setText(subitemnameele1);
					run.setBold(true);
					run.setFontFamily("Cambria (Headings)");
					run.setFontSize(12);

					if (counter41 > 1 && prevsubitemnameele1 != null) {
						if ((prevfootertextele1 != null) && !(prevfootertextele1.equals("NULL"))
								&& !(prevfootertextele1.equals("NULL:"))) {
							if (!footertextele1.equals(prevfootertextele1)) {
								if (prevfootertextele1.contains("\n")) {

									String[] arrOfStr = prevfootertextele1.split("\n", 20);
									for (int x1 = 0; x1 < arrOfStr.length; x1++) {
										XWPFParagraph paragraph21 = document.createParagraph();

										// System.out.println(table.getNumberOfRows());
										// paragraph21.setAlignment(ParagraphAlignment.CENTER);
										XWPFRun run123 = paragraph21.createRun();
										run123.setFontSize(11);
										// run123.setFontFamily("Cambria
										// (Headings)");
										run123.setText(arrOfStr[x1]);
										logger.info("rhs word start");
										logger.info(arrOfStr[x1]);
										logger.info("rhs word end");
									}
								} else {

									XWPFParagraph paragraph21 = document.createParagraph();

									// System.out.println(table.getNumberOfRows());
									// paragraph21.setAlignment(ParagraphAlignment.CENTER);
									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									// run123.setFontFamily("Cambria
									// (Headings)");
									run123.setText(prevfootertextele1);
								}

							}
						}
						if ((reportBean.getWordData().get(c - 1).getTechRemarks() != null)) {
							if ((reportBean.getWordData().get(c - 1).getTechRemarks().equalsIgnoreCase("NULL"))) {
								if (reportBean.getWordData().get(c - 1).getTechRemarks().contains("\n")) {

									String[] arrOfStr = reportBean.getWordData().get(c - 1).getTechRemarks().split("\n",
											20);
									for (int x1 = 0; x1 < arrOfStr.length; x1++) {
										XWPFParagraph paragraph21 = document.createParagraph();

										// System.out.println(table.getNumberOfRows());
										// paragraph21.setAlignment(ParagraphAlignment.CENTER);
										XWPFRun run123 = paragraph21.createRun();
										run123.setFontSize(11);
										// run123.setFontFamily("Cambria
										// (Headings)");
										run123.setText(arrOfStr[x1]);
										logger.info("rhs word start");
										logger.info(arrOfStr[x1]);
										logger.info("rhs word end");
									}
								} else {

									XWPFParagraph paragraph21 = document.createParagraph();

									// System.out.println(table.getNumberOfRows());
									// paragraph21.setAlignment(ParagraphAlignment.CENTER);
									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									// run123.setFontFamily("Cambria
									// (Headings)");
									run123.setText(reportBean.getWordData().get(c - 1).getTechRemarks());
								}

							}
						}
					}
				}
			}
			if (desitemnameele1 != null) {
				if (!desitemnameele1.equals(prevdesitemnameele1)) {
					if (count7 > 0) {

						if (prevdesitemnameele1.equals("Design Data")
								&& reportBean.getWordData().get(c - 1).getLinkFlag() == 1) {
							paragraph = document.createParagraph();
							run = paragraph.createRun();
							run.setText("");
						}
					}
					count7 = count7 + 1;
					count11 = 0;
					paragraph = document.createParagraph();
					run = paragraph.createRun();
					run.setText(String.valueOf(desitemorderid));
					run.setText("   ");
					run.setText(desitemnameele1);
					run.setBold(true);
					run.setFontFamily("Cambria (Headings)");
					run.setFontSize(11);

					if ((headertextele1 != null) && !headertextele1.equals("NULL")) {
						if (!headertextele1.equals(prevheadertextele1)) {
							if (headertextele1.contains("\n")) {

								String[] arrOfStr = headertextele1.split("\n", 20);
								for (int x1 = 0; x1 < arrOfStr.length; x1++) {
									XWPFParagraph paragraph21 = document.createParagraph();

									// System.out.println(table.getNumberOfRows());
									// paragraph21.setAlignment(ParagraphAlignment.CENTER);
									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									// run123.setFontFamily("Cambria
									// (Headings)");
									run123.setText(arrOfStr[x1]);
									// run123.addBreak();
									logger.info("rhs word start");
									logger.info(arrOfStr[x1]);
									logger.info("rhs word end");
								}
							} else {
								XWPFParagraph paragraph21 = document.createParagraph();
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(headertextele1);

							}
						}
					}
					if (dessubitemnameele1 == null) {
						table = document.createTable();

						// table.setWidth(2000);
						CTTblLayoutType type = table.getCTTbl().getTblPr().addNewTblLayout();
						type.setType(STTblLayoutType.FIXED);
						// table.setInsideHBorder(XWPFBorderType.SINGLE, 8, 5,
						// "0000FF");
						// table.setInsideVBorder(XWPFBorderType.SINGLE, 8, 5,
						// "0000FF");

						// CTTblWidth width =
						// table.getCTTbl().addNewTblPr().addNewTblW();
						// width.setType(STTblWidth.DXA);
						// width.setW(BigInteger.valueOf(9072));
						if (desitemnameele1.equalsIgnoreCase(
								"Multifunctional digital generator protection relay shall have following protections features")) {
							CTTblPr tblpro = table.getCTTbl().getTblPr();

							CTTblBorders borders = tblpro.addNewTblBorders();
							borders.addNewBottom().setVal(STBorder.THICK);
							borders.addNewLeft().setVal(STBorder.THICK);
							borders.addNewRight().setVal(STBorder.THICK);
							borders.addNewTop().setVal(STBorder.THICK);

							// also inner borders
							borders.addNewInsideH().setVal(STBorder.THICK);
							borders.addNewInsideV().setVal(STBorder.THICK);
							borders.getInsideH().setSz(BigInteger.valueOf(12));
							borders.getInsideV().setSz(BigInteger.valueOf(12));
							borders.getTop().setSz(BigInteger.valueOf(12));
							borders.getBottom().setSz(BigInteger.valueOf(12));
							borders.getLeft().setSz(BigInteger.valueOf(12));
							borders.getRight().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getTop().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getLeft().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getRight().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getBottom().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getInsideH().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getInsideV().setSz(BigInteger.valueOf(12));
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(5000));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
							table.setCellMargins(200, 100, 100, 100);
						} else if (desitemnameele1.equals("Design Data") && linkFlag1 == 1) {
							table.setCellMargins(200, 100, 100, 100);

							CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
							type1.setType(STTblLayoutType.FIXED);
							setTableAlign(table, ParagraphAlignment.CENTER);// Creating

							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(2000));
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));

						} else {
							CTTblPr tblpro = table.getCTTbl().getTblPr();

							CTTblBorders borders = tblpro.addNewTblBorders();
							borders.addNewBottom().setVal(STBorder.NONE);
							borders.addNewLeft().setVal(STBorder.NONE);
							borders.addNewRight().setVal(STBorder.NONE);
							borders.addNewTop().setVal(STBorder.NONE);
							// also inner borders
							borders.addNewInsideH().setVal(STBorder.NONE);
							borders.addNewInsideV().setVal(STBorder.NONE);
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));

						}

						setTableAlign(table, ParagraphAlignment.LEFT);// Creating
																		// table
																		// only
																		// once
						tableRowOne11 = table.getRow(0);
					}
				} // if (!subitemnameele.equals(prevdesitemnameele))
				else {
					if (!itemnameele1.equals(previtemnameele1)) {

						if (prevdesitemnameele1.equals("Design Data")
								&& reportBean.getWordData().get(c - 1).getLinkFlag() == 1) {
							paragraph = document.createParagraph();
							run = paragraph.createRun();
							run.setText("");
						}
						count11 = 0;
						paragraph = document.createParagraph();
						run = paragraph.createRun();
						run.setText(String.valueOf(desitemorderid));
						run.setText("   ");
						run.setText(desitemnameele1);
						run.setBold(true);
						run.setFontFamily("Cambria (Headings)");
						run.setFontSize(11);

						if ((headertextele1 != null) && !headertextele1.equals("NULL")) {
							if (!headertextele1.equals(prevheadertextele1)) {
								paragraph = document.createParagraph();
								run = paragraph.createRun();
								run.setText(headertextele1);
								// run.setText(":");

								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);
							}
						}
						if (dessubitemnameele1 == null) {
							table = document.createTable();

							// table.setWidth(2000);
							CTTblLayoutType type = table.getCTTbl().getTblPr().addNewTblLayout();
							type.setType(STTblLayoutType.FIXED);
							// table.setInsideHBorder(XWPFBorderType.SINGLE, 8,
							// 5,
							// "0000FF");
							// table.setInsideVBorder(XWPFBorderType.SINGLE, 8,
							// 5,
							// "0000FF");

							// CTTblWidth width =
							// table.getCTTbl().addNewTblPr().addNewTblW();
							// width.setType(STTblWidth.DXA);
							// width.setW(BigInteger.valueOf(9072));
							if (desitemnameele1.equalsIgnoreCase(
									"Multifunctional digital generator protection relay shall have following protections features")) {
								CTTblPr tblpro = table.getCTTbl().getTblPr();

								CTTblBorders borders = tblpro.addNewTblBorders();
								borders.addNewBottom().setVal(STBorder.THICK);
								borders.addNewLeft().setVal(STBorder.THICK);
								borders.addNewRight().setVal(STBorder.THICK);
								borders.addNewTop().setVal(STBorder.THICK);
								// also inner borders
								borders.addNewInsideH().setVal(STBorder.THICK);
								borders.addNewInsideV().setVal(STBorder.THICK);
								borders.addNewTop().setVal(STBorder.THICK);

								borders.getTop().setSz(BigInteger.valueOf(12));
								borders.getBottom().setSz(BigInteger.valueOf(12));
								borders.getLeft().setSz(BigInteger.valueOf(12));
								borders.getRight().setSz(BigInteger.valueOf(12));
								borders.getInsideH().setSz(BigInteger.valueOf(12));
								borders.getInsideV().setSz(BigInteger.valueOf(12));
								table.getCTTbl().getTblPr().getTblBorders().getTop().setSz(BigInteger.valueOf(12));
								table.getCTTbl().getTblPr().getTblBorders().getLeft().setSz(BigInteger.valueOf(12));
								table.getCTTbl().getTblPr().getTblBorders().getRight().setSz(BigInteger.valueOf(12));
								table.getCTTbl().getTblPr().getTblBorders().getBottom().setSz(BigInteger.valueOf(12));
								table.getCTTbl().getTblPr().getTblBorders().getInsideH().setSz(BigInteger.valueOf(12));
								table.getCTTbl().getTblPr().getTblBorders().getInsideV().setSz(BigInteger.valueOf(12));
								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));
								table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(5000));
								table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
								table.setCellMargins(200, 100, 100, 100);

							} else if (desitemnameele1.equals("Design Data") && linkFlag1 == 1) {
								table.setCellMargins(200, 100, 100, 100);

								CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
								type1.setType(STTblLayoutType.FIXED);
								setTableAlign(table, ParagraphAlignment.CENTER);
								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(2000));
								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));

							} else {
								CTTblPr tblpro = table.getCTTbl().getTblPr();

								CTTblBorders borders = tblpro.addNewTblBorders();
								borders.addNewBottom().setVal(STBorder.NONE);
								borders.addNewLeft().setVal(STBorder.NONE);
								borders.addNewRight().setVal(STBorder.NONE);
								borders.addNewTop().setVal(STBorder.NONE);
								// also inner borders
								borders.addNewInsideH().setVal(STBorder.NONE);
								borders.addNewInsideV().setVal(STBorder.NONE);
								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
								table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
								table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1500));

							}

							setTableAlign(table, ParagraphAlignment.LEFT);// Creating
																			// table
																			// only
																			// once
							tableRowOne11 = table.getRow(0);

						}
					}
				}

				if (dessubitemnameele1 != null) {
					if (!dessubitemnameele1.equals(prevdessubitemnameele1)) {
						logger.info("3");

						if (count71 > 0) {

							if (prevdessubitemnameele1 != null) {

								if (prevdessubitemnameele1.equalsIgnoreCase(
										"Multifunctional digital generator protection relay shall have following protections features")) {
									paragraph = document.createParagraph();
									run = paragraph.createRun();
									run.setText("");
								}
							}
						}
						count71 = count71 + 1;
						counter31 = 1;
						paragraph = document.createParagraph();
						run = paragraph.createRun();
						run.setBold(true);
						run.setText("1." + dessubitemorderidele1 + "   " + dessubitemnameele1);
						run.setFontFamily("Cambria (Headings)");
						run.setFontSize(11);
						logger.info("test0");

						table = document.createTable();
						logger.info("test0");

						// table.setInsideHBorder(XWPFTable.XWPFBorderType.DASHED,
						// 4, 0, "0000FF");
						// table.setInsideVBorder(XWPFTable.XWPFBorderType.DASHED,
						// 4, 0, "0000FF");

						// table.setCellMargins(1, left, bottom, right);
						CTTblLayoutType type = table.getCTTbl().getTblPr().addNewTblLayout();
						type.setType(STTblLayoutType.FIXED);
						if (dessubitemnameele1.equalsIgnoreCase(
								"Multifunctional digital generator protection relay shall have following protections features")) {
							// CTTblPr tblpro = table.getCTTbl().getTblPr();
							// logger.info("test1");
							//
							// CTTblBorders borders = tblpro.addNewTblBorders();
							// borders.addNewBottom().setVal(STBorder.THICK);
							// borders.addNewLeft().setVal(STBorder.THICK);
							// borders.addNewRight().setVal(STBorder.THICK);
							// borders.addNewTop().setVal(STBorder.THICK);
							// // also inner borders
							// borders.addNewInsideH().setVal(STBorder.THICK);
							// borders.addNewInsideV().setVal(STBorder.THICK);
							// borders.addNewTop().setVal(STBorder.THICK);
							// borders.getTop().setSz(BigInteger.valueOf(12));
							// borders.getBottom().setSz(BigInteger.valueOf(12));
							// borders.getLeft().setSz(BigInteger.valueOf(12));
							// borders.getRight().setSz(BigInteger.valueOf(12));
							// borders.getInsideH().setSz(BigInteger.valueOf(12));
							// borders.getInsideV().setSz(BigInteger.valueOf(12));
							// table.getCTTbl().getTblPr().getTblBorders().getTop().setSz(BigInteger.valueOf(12));
							// table.getCTTbl().getTblPr().getTblBorders().getLeft().setSz(BigInteger.valueOf(12));
							// table.getCTTbl().getTblPr().getTblBorders().getRight().setSz(BigInteger.valueOf(12));
							// table.getCTTbl().getTblPr().getTblBorders().getBottom().setSz(BigInteger.valueOf(12));
							// table.getCTTbl().getTblPr().getTblBorders().getInsideH().setSz(BigInteger.valueOf(12));
							// table.getCTTbl().getTblPr().getTblBorders().getInsideV().setSz(BigInteger.valueOf(12));
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(5000));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
							table.setCellMargins(200, 100, 100, 100);

						} else {
							logger.info("test2");
							CTTblPr tblpro = table.getCTTbl().getTblPr();

							CTTblBorders borders = tblpro.addNewTblBorders();
							borders.addNewBottom().setVal(STBorder.NONE);
							borders.addNewLeft().setVal(STBorder.NONE);
							borders.addNewRight().setVal(STBorder.NONE);
							borders.addNewTop().setVal(STBorder.NONE);
							// also inner borders
							borders.addNewInsideH().setVal(STBorder.NONE);
							borders.addNewInsideV().setVal(STBorder.NONE);
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));

							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1500));

						}

						// CTTblWidth width =
						// table.getCTTbl().addNewTblPr().addNewTblW();
						// width.setType(STTblWidth.DXA);
						// width.setW(BigInteger.valueOf(9072));

						// table.setWidth(2000);
						// CTTblPr tblpro = table.getCTTbl().getTblPr();
						//
						// CTTblBorders borders = tblpro.getTblBorders();
						// borders.getBottom().setVal(STBorder.SINGLE);
						// borders.getBottom().setColor("0000FF");
						//
						// borders.getLeft().setVal(STBorder.BASIC_BLACK_DOTS);
						// borders.getLeft().setColor("0000FF");
						// borders.getRight().setVal(STBorder.BASIC_BLACK_DOTS);
						// borders.getRight().setColor("0000FF");
						// borders.getTop().setVal(STBorder.BASIC_BLACK_DOTS);
						// borders.getTop().setColor("0000FF");
						// //also inner borders
						// borders.getInsideH().setVal(STBorder.BASIC_BLACK_DOTS);
						// borders.getInsideH().setColor("0000FF");
						// borders.getInsideV().setVal(STBorder.BASIC_BLACK_DOTS);
						// borders.getInsideV().setColor("0000FF");

						setTableAlign(table, ParagraphAlignment.LEFT);// Creating
																		// table
																		// only
																		// once
						tableRowOne11 = table.getRow(0);

					}
				}
				logger.info("done");
				// To print the item name only once
				// if (desitemnameele != null ) {
				//
				// if (!desitemnameele.equals(prevdesitemnameele)){
				// paragraph = document.createParagraph();
				// run = paragraph.createRun();
				// run.setText(desitemnameele);
				// run.setText(":");
				// run.setBold(true);
				// run.setFontFamily("Cambria (Headings)");
				// run.setFontSize(11);
				//
				// table = document.createTable();
				// setTableAlign(table, ParagraphAlignment.LEFT);// Creating
				// // table
				// // only once
				// tableRowOne1 = table.getRow(0);
				// } // if (!subitemnameele.equals(prevdesitemnameele))
				//
				// if (dessubitemnameele != null &&
				// !dessubitemnameele.equals(prevdessubitemnameele)) {
				//
				//
				// paragraph = document.createParagraph();
				// run = paragraph.createRun();
				// run.setText(dessubitemorderidele+" "+ dessubitemnameele);
				// run.setFontFamily("Cambria (Headings)");
				// run.setFontSize(11);
				//
				// table = document.createTable();
				// setTableAlign(table, ParagraphAlignment.LEFT);// Creating
				// // table
				// // only once
				// tableRowOne1 = table.getRow(0);
				// }
				//

				// To print the lhs and rhs of des item
				String colunmname1 = reportBean.getWordData().get(c).getColNm();
				String colValCode1 = reportBean.getWordData().get(c).getColValCd();

				if (desitemnameele1 != null && dessubitemnameele1 == null) // Compare
																			// two
																			// strings
				{

					if (colunmname1 != null && colValCode1 != null) {
						// paragraph = document.createParagraph();
						// run = paragraph.createRun();
						//
						// run.setText(descolorderidele+" "+ colunmname);
						// run.setText(":");
						// run.setText(colValCode);
						// run.setFontFamily("Cambria (Headings)");
						// run.setFontSize(11);

						if (tableRowOne11 != null) {
							if (desitemnameele1.equalsIgnoreCase(
									"Multifunctional digital generator protection relay shall have following protections features")) {
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// sdMultifunctional digital generator
								// protection relay shall have following
								// protections featuresd
								XWPFParagraph pa451 = tableRowOne11.getCell(0).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								pa451.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun ru311 = pa451.createRun();
								ru311.setFontSize(11);
								// ru311.setFontFamily("Cambria (Headings)");
								ru311.setText("Sl.no");
								ru311.setBold(true);

								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne11.addNewTableCell();
								XWPFParagraph pa = tableRowOne11.getCell(1).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								pa.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun ru = pa.createRun();
								ru.setFontSize(11);
								// ru.setFontFamily("Cambria (Headings)");
								ru.setText("Category");
								ru.setBold(true);

								tableRowOne11.addNewTableCell();
								XWPFParagraph pa1 = tableRowOne11.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								pa1.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun ru1 = pa1.createRun();
								ru1.setFontSize(11);
								// ru1.setFontFamily("Cambria (Headings)");
								ru1.setText("Description");
								ru1.setBold(true);

								// tableRowOne11.getCell(0).setText("SL.NO");
								// //
								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
								//
								// tableRowOne11.addNewTableCell().setText("CATEGORY");
								// tableRowOne11.addNewTableCell().setText("DESCRIPTION");
								// tableRowOne11.getCell(0).setColor("808080");
								// tableRowOne11.getCell(1).setColor("808080");
								// tableRowOne11.getCell(2).setColor("808080");
								tableRowOne11 = null;
								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);
								XWPFTableRow tableRowOne = table.createRow();
								paragraphtable = tableRowOne.getCell(0).getParagraphs().get(0);
								paragraphtable.setIndentationLeft(100);
								paragraphtable.setIndentationRight(100);
								paragraphtable.setAlignment(ParagraphAlignment.CENTER);
								runtable = paragraphtable.createRun();
								runtable.setFontSize(11);
								// runtable.setFontFamily("Cambria (Headings)");
								runtable.setText(descolorderidele1);
								tableRowOne.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne.getCell(1).setText(colunmname1);
								if (subColValCodeele1 != null && !(subColValCodeele1.equals("NULL"))) {
									tableRowOne.getCell(2).setText(subColValCodeele1);
								} else {
									// tableRowOne.getCell(2).setText(colValCode);
									if (colValCode1.contains("\n")) {

										String[] arrOfStr = colValCode1.split("\n", 20);
										for (int x1 = 0; x1 < arrOfStr.length; x1++) {
											paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);
											paragraphtable.setIndentationLeft(100);
											paragraphtable.setIndentationRight(100);
											paragraphtable.setAlignment(ParagraphAlignment.CENTER);
											runtable = paragraphtable.createRun();
											runtable.setFontSize(11);
											// runtable.setFontFamily("Cambria
											// (Headings)");
											runtable.setText(arrOfStr[x1]);
											runtable.addBreak();
											tableRowOne.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
										}
									}

									else {
										paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);
										paragraphtable.setIndentationLeft(100);
										paragraphtable.setIndentationRight(100);
										paragraphtable.setAlignment(ParagraphAlignment.CENTER);
										runtable = paragraphtable.createRun();
										runtable.setFontSize(11);
										// runtable.setFontFamily("Cambria
										// (Headings)");
										runtable.setText(colValCode1);
										tableRowOne.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
									}

								}

								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							} else if (desitemnameele1.equals("Design Data") && linkFlag1 == 1) {
								// tableRowOne11.getCell(0).setText("SL.NO");
								XWPFParagraph pa451 = tableRowOne11.getCell(0).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								pa451.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun ru311 = pa451.createRun();
								ru311.setFontSize(11);
								// ru311.setFontFamily("Cambria (Headings)");
								ru311.setText("Sl.no");
								ru311.setBold(true);

								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne11.addNewTableCell();
								XWPFParagraph pa45 = tableRowOne11.getCell(1).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								pa45.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun ru31 = pa45.createRun();
								ru31.setFontSize(11);
								// ru31.setFontFamily("Cambria (Headings)");
								ru31.setText("Scope of supply and battery limits");
								ru31.setBold(true);
								tableRowOne11.addNewTableCell();
								XWPFParagraph paragraph12 = tableRowOne11.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								paragraph12.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run12 = paragraph12.createRun();
								run12.setFontSize(11);
								// run12.setFontFamily("Cambria (Headings)");
								run12.setText("Engineering");
								run12.setBold(true);
								tableRowOne11.addNewTableCell();
								XWPFParagraph pa1 = tableRowOne11.getCell(3).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								pa1.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun ru3 = pa1.createRun();
								ru3.setFontSize(11);
								// ru3.setFontFamily("Cambria (Headings)");
								ru3.setText("Supply");
								ru3.setBold(true);
								// tableRowOne11.getCell(0).setColor("808080");
								// tableRowOne11.getCell(1).setColor("808080");
								// tableRowOne11.getCell(2).setColor("808080");
								// tableRowOne11.getCell(3).setColor("808080");
								tableRowOne11 = null;
								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.setCantSplitRow(true);
								// tableRowOne.getCell(0).setText(descolorderidele1);
								paragraphtable = tableRowOne.getCell(0).getParagraphs().get(0);

								paragraphtable.setAlignment(ParagraphAlignment.CENTER);
								runtable = paragraphtable.createRun();
								runtable.setFontSize(11);
								// runtable.setFontFamily("Cambria (Headings)");
								runtable.setText(descolorderidele1);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								// tableRowOne.getCell(1).setText(colunmname1);
								if (colunmname1.contains("\n")) {

									String[] arrOfStr = colunmname1.split("\n", 20);
									paragraphtable = tableRowOne.getCell(1).getParagraphs().get(0);

									runtable = paragraphtable.createRun();
									runtable.setFontSize(11);
									for (int x1 = 0; x1 < arrOfStr.length; x1++) {

										// runtable.setFontFamily("Cambria
										// (Headings)");
										runtable.setText(arrOfStr[x1]);
										if (x1 + 1 != arrOfStr.length) {
											runtable.addBreak();
										}
									}
								}

								else {
									paragraphtable = tableRowOne.getCell(1).getParagraphs().get(0);

									runtable = paragraphtable.createRun();
									runtable.setFontSize(11);
									// runtable.setFontFamily("Cambria
									// (Headings)");
									runtable.setText(colunmname1);
								}
								if (colValCode1.equalsIgnoreCase("Yes") || colValCode1.equalsIgnoreCase("No")) {
									// tableRowOne.getCell(2).setText("YES");
									// tableRowOne.getCell(3).setText(colValCode1);
									paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);

									paragraphtable.setAlignment(ParagraphAlignment.CENTER);
									runtable = paragraphtable.createRun();
									runtable.setFontSize(11);
									// runtable.setFontFamily("Cambria
									// (Headings)");
									runtable.setText("Yes");

									paragraphtable = tableRowOne.getCell(3).getParagraphs().get(0);

									paragraphtable.setAlignment(ParagraphAlignment.CENTER);
									runtable = paragraphtable.createRun();
									runtable.setFontSize(11);
									// runtable.setFontFamily("Cambria
									// (Headings)");
									runtable.setText(colValCode1);

								} else {
									tableRowOne.getCell(2).setText(" ");
									CTTc ctTc1 = tableRowOne.getCell(2).getCTTc();
									CTTcPr tcPr1 = ctTc1.addNewTcPr();
									CTHMerge hMerge1 = tcPr1.addNewHMerge();
									hMerge1.setVal(STMerge.RESTART);

									CTTcBorders tblBorders1 = tcPr1.addNewTcBorders();

									tblBorders1.addNewRight().setVal(STBorder.NIL);
									tableRowOne.getCell(3).setText(colValCode1);
									CTTc ctTc12 = tableRowOne.getCell(3).getCTTc();
									CTTcPr tcPr12 = ctTc12.addNewTcPr();
									CTHMerge hMerge12 = tcPr12.addNewHMerge();
									hMerge12.setVal(STMerge.RESTART);

									CTTcBorders tblBorders12 = tcPr12.addNewTcBorders();

									tblBorders12.addNewLeft().setVal(STBorder.NIL);

								}

								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

							else {
								tableRowOne11.getCell(0).setText(descolorderidele1);
								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne11.addNewTableCell().setText(colunmname1);
								;
								tableRowOne11.addNewTableCell();
								XWPFParagraph paragraph12 = tableRowOne11.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								paragraph12.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run12 = paragraph12.createRun();
								run12.setFontSize(14);
								run12.setFontFamily("Cambria (Headings)");
								run12.setText(":");
								if (subColValCodeele1 != null && !(subColValCodeele1.equals("NULL"))) {
									tableRowOne11.addNewTableCell().setText(subColValCodeele1);
								} else {
									tableRowOne11.addNewTableCell().setText(colValCode1);
								}

								tableRowOne11 = null;
								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

						}

						else {
							if (desitemnameele1.equalsIgnoreCase(
									"Multifunctional digital generator protection relay shall have following protections features")) {

								XWPFTableRow tableRowOne = table.createRow();
								paragraphtable = tableRowOne.getCell(0).getParagraphs().get(0);
								paragraphtable.setIndentationLeft(100);
								paragraphtable.setIndentationRight(100);
								paragraphtable.setAlignment(ParagraphAlignment.CENTER);
								runtable = paragraphtable.createRun();
								runtable.setFontSize(11);
								// runtable.setFontFamily("Cambria (Headings)");
								runtable.setText(descolorderidele1);
								tableRowOne.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne.getCell(1).setText(colunmname1);
								if (subColValCodeele1 != null && !(subColValCodeele1.equals("NULL"))) {
									tableRowOne.getCell(2).setText(subColValCodeele1);
								} else {
									// tableRowOne.getCell(2).setText(colValCode);
									if (colValCode1.contains("\n")) {

										String[] arrOfStr = colValCode1.split("\n", 20);
										for (int x1 = 0; x1 < arrOfStr.length; x1++) {
											paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);
											paragraphtable.setIndentationLeft(100);
											paragraphtable.setIndentationRight(100);
											paragraphtable.setAlignment(ParagraphAlignment.CENTER);
											runtable = paragraphtable.createRun();
											runtable.setFontSize(11);
											// runtable.setFontFamily("Cambria
											// (Headings)");
											runtable.setText(arrOfStr[x1]);
											runtable.addBreak();
											tableRowOne.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
										}
									}

									else {
										paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);
										paragraphtable.setIndentationLeft(100);
										paragraphtable.setIndentationRight(100);
										paragraphtable.setAlignment(ParagraphAlignment.CENTER);
										runtable = paragraphtable.createRun();
										runtable.setFontSize(11);
										// runtable.setFontFamily("Cambria
										// (Headings)");
										runtable.setText(colValCode1);
										tableRowOne.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
									}

								}
								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

							else if ((colunmname1.equals("Additional outgoing feeders ( Ampere rating & quantity )"))
									&& count == 0) {
								count11 = 1;
								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.getCell(0).setText(descolorderidele1);
								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
								tableRowOne.getCell(1).setText(colunmname1);
								// tableRowOne.getCell(1).setText(" : ");
								XWPFParagraph paragraph12 = tableRowOne.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								paragraph12.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run12 = paragraph12.createRun();
								run12.setFontSize(14);
								run12.setFontFamily("Cambria (Headings)");
								run12.setText(":");
								if (subColValCodeele1 != null && !(subColValCodeele1.equals("NULL"))) {
									tableRowOne.getCell(3).setText(subColValCodeele1);
								} else {
									tableRowOne.getCell(3).setText(colValCode1 + "*" + qunatityele1);
								}

								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

							else if (colunmname1.equals("Additional outgoing feeders ( Ampere rating & quantity )")
									&& count11 != 0) {
								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.getCell(0).setText("");
								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne.getCell(1).setText(" ");
								tableRowOne.getCell(2).setText(" ");
								if (subColValCodeele1 != null && !(subColValCodeele1.equals("NULL"))) {
									tableRowOne.getCell(3).setText(subColValCodeele1);
								} else {
									tableRowOne.getCell(3).setText(colValCode1 + "*" + qunatityele1);
								}

								// tableRowOne1 = null;
								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

							else if (desitemnameele1.equals("Design Data") && linkFlag1 == 1) {

								XWPFTableRow tableRowOne = table.createRow();
								// tableRowOne.getCell(0).setText(descolorderidele1);
								// //
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
								//
								// tableRowOne.getCell(1).setText(colunmname1);
								// tableRowOne.getCell(2).setText(" ");
								// CTTc ctTc1 =
								// tableRowOne.getCell(2).getCTTc();
								// CTTcPr tcPr1 = ctTc1.addNewTcPr();
								// CTHMerge hMerge1 = tcPr1.addNewHMerge();
								// hMerge1.setVal(STMerge.RESTART);
								//
								// CTTcBorders tblBorders1 =
								// tcPr1.addNewTcBorders();
								//
								//
								// tblBorders1.addNewRight().setVal(STBorder.NIL);
								// tableRowOne.getCell(3).setText(colValCode1);
								// CTTc ctTc12 =
								// tableRowOne.getCell(3).getCTTc();
								// CTTcPr tcPr12 = ctTc12.addNewTcPr();
								// CTHMerge hMerge12 = tcPr12.addNewHMerge();
								// hMerge12.setVal(STMerge.RESTART);
								//
								// CTTcBorders tblBorders12 =
								// tcPr12.addNewTcBorders();
								//
								//
								// tblBorders12.addNewLeft().setVal(STBorder.NIL);
								//
								tableRowOne.setCantSplitRow(true);
								// tableRowOne.getCell(0).setText(descolorderidele1);
								paragraphtable = tableRowOne.getCell(0).getParagraphs().get(0);

								paragraphtable.setAlignment(ParagraphAlignment.CENTER);
								runtable = paragraphtable.createRun();
								runtable.setFontSize(11);
								// runtable.setFontFamily("Cambria (Headings)");
								runtable.setText(descolorderidele1);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								// tableRowOne.getCell(1).setText(colunmname1);
								if (colunmname1.contains("\n")) {

									String[] arrOfStr = colunmname1.split("\n", 20);
									paragraphtable = tableRowOne.getCell(1).getParagraphs().get(0);

									runtable = paragraphtable.createRun();
									runtable.setFontSize(11);
									for (int x1 = 0; x1 < arrOfStr.length; x1++) {

										// runtable.setFontFamily("Cambria
										// (Headings)");
										runtable.setText(arrOfStr[x1]);
										if (x1 + 1 != arrOfStr.length) {
											runtable.addBreak();
										}
									}
								}

								else {
									paragraphtable = tableRowOne.getCell(1).getParagraphs().get(0);

									runtable = paragraphtable.createRun();
									runtable.setFontSize(11);
									// runtable.setFontFamily("Cambria
									// (Headings)");
									runtable.setText(colunmname1);
								}

								if (colValCode1.equalsIgnoreCase("Yes") || colValCode1.equalsIgnoreCase("No")
										|| reportBean.getWordData().get(c).getAddOnNewColFlag() == 1) {
									// tableRowOne.getCell(2).setText("YES");
									// tableRowOne.getCell(3).setText(colValCode1);
									if (reportBean.getWordData().get(c).getRhsColTechcomments() != null
											|| reportBean.getWordData().get(c).getAddOnNewColFlag() == 1) {
										if (reportBean.getWordData().get(c).getAddOnNewColFlag() == 1
												|| !reportBean.getWordData().get(c).getRhsColTechcomments()
														.equalsIgnoreCase("NULL")) {
											if (reportBean.getWordData().get(c).getRhsColTechcomments() == null
													&& reportBean.getWordData().get(c).getAddOnNewColFlag() == 1) {
												tableRowOne.getCell(2).setText(" ");
												CTTc ctTc1 = tableRowOne.getCell(2).getCTTc();
												CTTcPr tcPr1 = ctTc1.addNewTcPr();
												CTHMerge hMerge1 = tcPr1.addNewHMerge();
												hMerge1.setVal(STMerge.RESTART);

												CTTcBorders tblBorders1 = tcPr1.addNewTcBorders();

												tblBorders1.addNewRight().setVal(STBorder.NIL);
												tableRowOne.getCell(3).setText(colValCode1);
												CTTc ctTc12 = tableRowOne.getCell(3).getCTTc();
												CTTcPr tcPr12 = ctTc12.addNewTcPr();
												CTHMerge hMerge12 = tcPr12.addNewHMerge();
												hMerge12.setVal(STMerge.RESTART);

												CTTcBorders tblBorders12 = tcPr12.addNewTcBorders();

												tblBorders12.addNewLeft().setVal(STBorder.NIL);

											} else {
												paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);

												paragraphtable.setAlignment(ParagraphAlignment.CENTER);
												runtable = paragraphtable.createRun();
												runtable.setFontSize(11);
												// runtable.setFontFamily("Cambria
												// (Headings)");
												runtable.setText(colValCode1);

												paragraphtable = tableRowOne.getCell(3).getParagraphs().get(0);

												paragraphtable.setAlignment(ParagraphAlignment.CENTER);
												runtable = paragraphtable.createRun();
												runtable.setFontSize(11);
												// runtable.setFontFamily("Cambria
												// (Headings)");
												runtable.setText(
														reportBean.getWordData().get(c).getRhsColTechcomments());
											}
										} else {
											paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);

											paragraphtable.setAlignment(ParagraphAlignment.CENTER);
											runtable = paragraphtable.createRun();
											runtable.setFontSize(11);
											// runtable.setFontFamily("Cambria
											// (Headings)");
											runtable.setText("Yes");

											paragraphtable = tableRowOne.getCell(3).getParagraphs().get(0);

											paragraphtable.setAlignment(ParagraphAlignment.CENTER);
											runtable = paragraphtable.createRun();
											runtable.setFontSize(11);
											// runtable.setFontFamily("Cambria
											// (Headings)");
											runtable.setText(colValCode1);

										}
									} else {
										paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);

										paragraphtable.setAlignment(ParagraphAlignment.CENTER);
										runtable = paragraphtable.createRun();
										runtable.setFontSize(11);
										// runtable.setFontFamily("Cambria
										// (Headings)");
										runtable.setText("Yes");

										paragraphtable = tableRowOne.getCell(3).getParagraphs().get(0);

										paragraphtable.setAlignment(ParagraphAlignment.CENTER);
										runtable = paragraphtable.createRun();
										runtable.setFontSize(11);
										// runtable.setFontFamily("Cambria
										// (Headings)");
										runtable.setText(colValCode1);

									}

								} else {
									tableRowOne.getCell(2).setText(" ");
									CTTc ctTc1 = tableRowOne.getCell(2).getCTTc();
									CTTcPr tcPr1 = ctTc1.addNewTcPr();
									CTHMerge hMerge1 = tcPr1.addNewHMerge();
									hMerge1.setVal(STMerge.RESTART);

									CTTcBorders tblBorders1 = tcPr1.addNewTcBorders();

									tblBorders1.addNewRight().setVal(STBorder.NIL);
									tableRowOne.getCell(3).setText(colValCode1);
									CTTc ctTc12 = tableRowOne.getCell(3).getCTTc();
									CTTcPr tcPr12 = ctTc12.addNewTcPr();
									CTHMerge hMerge12 = tcPr12.addNewHMerge();
									hMerge12.setVal(STMerge.RESTART);

									CTTcBorders tblBorders12 = tcPr12.addNewTcBorders();

									tblBorders12.addNewLeft().setVal(STBorder.NIL);

								}

							}

							else {

								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.getCell(0).setText(descolorderidele1);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
								tableRowOne.getCell(1).setText(colunmname1);
								// tableRowOne.getCell(1).setText(" : ");
								XWPFParagraph paragraph12 = tableRowOne.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								paragraph12.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run12 = paragraph12.createRun();
								run12.setFontSize(14);
								run12.setFontFamily("Cambria (Headings)");
								run12.setText(":");
								if (subColValCodeele1 != null && !(subColValCodeele1.equals("NULL"))) {
									tableRowOne.getCell(3).setText(subColValCodeele1);
								} else {
									if (colValCode1.contains("\n")) {

										String[] arrOfStr = colValCode1.split("\n", 20);
										for (int x1 = 0; x1 < arrOfStr.length; x1++) {
											XWPFParagraph paragraph21 = tableRowOne.getCell(3).getParagraphs().get(0);

											// System.out.println(table.getNumberOfRows());
											// paragraph21.setAlignment(ParagraphAlignment.CENTER);
											XWPFRun run123 = paragraph21.createRun();
											run123.setFontSize(11);
											// run123.setFontFamily("Cambria
											// (Headings)");
											run123.setText(arrOfStr[x1]);
											run123.addBreak();
											logger.info("rhs word start");
											logger.info(arrOfStr[x1]);
											logger.info("rhs word end");
										}
									} else {
										tableRowOne.getCell(3).setText(colValCode1);

									}
								}

								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

						}

					}
				} else {

					if (desitemnameele1 != null) {
						// paragraph = document.createParagraph();
						// run = paragraph.createRun();
						// run.setText(descolorderidele+" "+ colunmname);
						// run.setText(":");
						// run.setText(colValCode);
						// run.setFontFamily("Cambria (Headings)");
						// run.setFontSize(11);

						if (tableRowOne11 != null) {
							if (dessubitemnameele1.equalsIgnoreCase(
									"Multifunctional digital generator protection relay shall have following protections features")) {
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// sdMultifunctional digital generator
								// protection relay shall have following
								// protections featuresd
								XWPFParagraph pa451 = tableRowOne11.getCell(0).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								pa451.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun ru311 = pa451.createRun();
								ru311.setFontSize(11);
								// ru311.setFontFamily("Cambria (Headings)");
								ru311.setText("Sl.no");
								ru311.setBold(true);

								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne11.addNewTableCell();
								XWPFParagraph pa = tableRowOne11.getCell(1).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								pa.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun ru = pa.createRun();
								ru.setFontSize(11);
								// ru.setFontFamily("Cambria (Headings)");
								ru.setText("Category");
								ru.setBold(true);

								tableRowOne11.addNewTableCell();
								XWPFParagraph pa1 = tableRowOne11.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								pa1.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun ru1 = pa1.createRun();
								ru1.setFontSize(11);
								// ru1.setFontFamily("Cambria (Headings)");
								ru1.setText("Description");
								ru1.setBold(true);
								// tableRowOne11.getCell(0).setText("SL.NO");
								// //
								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
								//
								// tableRowOne11.addNewTableCell().setText("CATEGORY");
								// tableRowOne11.addNewTableCell().setText("DESCRIPTION");
								// tableRowOne11.getCell(0).setColor("808080");
								// tableRowOne11.getCell(1).setColor("808080");
								// tableRowOne11.getCell(2).setColor("808080");
								// tableRowOne1.getCell(0).setColor("GrayColor");
								// tableRowOne1.getCell(1).setColor("GrayColor");
								// tableRowOne1.getCell(2).setColor("GrayColor");
								tableRowOne11 = null;
								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);
								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.setCantSplitRow(true);
								paragraphtable = tableRowOne.getCell(0).getParagraphs().get(0);
								// paragraphtable.setIndentationLeft(100);
								// paragraphtable.setIndentationRight(100);
								paragraphtable.setAlignment(ParagraphAlignment.CENTER);
								runtable = paragraphtable.createRun();
								runtable.setFontSize(11);
								// runtable.setFontFamily("Cambria (Headings)");
								runtable.setText(descolorderidele1);
								// tableRowOne.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
								if (colunmname1.contains("\n")) {
									String[] arrOfStr = colunmname1.split("\n", 20);
									for (int x1 = 0; x1 < arrOfStr.length; x1++) {
										paragraphtable = tableRowOne.getCell(1).getParagraphs().get(0);
										// paragraphtable.setIndentationLeft(100);
										// paragraphtable.setIndentationRight(100);
										// paragraphtable.setAlignment(ParagraphAlignment.CENTER);
										runtable = paragraphtable.createRun();
										runtable.setFontSize(11);
										// runtable.setFontFamily("Cambria
										// (Headings)");
										runtable.setText(arrOfStr[x1]);
										if (x1 + 1 != arrOfStr.length) {
											runtable.addBreak();
										}
										// tableRowOne.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
									}
								}

								else {
									tableRowOne.getCell(1).setText(colunmname1);
								}
								if (subColValCodeele1 != null && !(subColValCodeele1.equals("NULL"))) {
									tableRowOne.getCell(2).setText(subColValCodeele1);
								} else {
									// tableRowOne.getCell(2).setText(colValCode);
									if (colValCode1.contains("\n")) {

										String[] arrOfStr = colValCode1.split("\n", 20);
										paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);
										// paragraphtable.setIndentationLeft(100);
										// paragraphtable.setIndentationRight(100);
										paragraphtable.setAlignment(ParagraphAlignment.CENTER);
										runtable = paragraphtable.createRun();
										runtable.setFontSize(11);
										for (int x1 = 0; x1 < arrOfStr.length; x1++) {

											// runtable.setFontFamily("Cambria
											// (Headings)");
											runtable.setText(arrOfStr[x1]);
											if (x1 + 1 != arrOfStr.length) {
												runtable.addBreak();
											}
											// tableRowOne.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
										}
									}

									else {
										paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);
										// paragraphtable.setIndentationLeft(100);
										// paragraphtable.setIndentationRight(100);
										paragraphtable.setAlignment(ParagraphAlignment.CENTER);
										runtable = paragraphtable.createRun();
										runtable.setFontSize(11);
										// runtable.setFontFamily("Cambria
										// (Headings)");
										runtable.setText(colValCode1);
										// tableRowOne.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
									}

								}

								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							} else {
								tableRowOne11.getCell(0).setText(descolorderidele1);
								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
								tableRowOne11.addNewTableCell().setText(colunmname1);
								tableRowOne11.addNewTableCell();
								XWPFParagraph paragraph12 = tableRowOne11.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								paragraph12.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run12 = paragraph12.createRun();
								run12.setFontSize(14);
								run12.setFontFamily("Cambria (Headings)");
								run12.setText(":");
								if (subColValCodeele1 != null && !(subColValCodeele1.equals("NULL"))) {
									tableRowOne11.addNewTableCell().setText(subColValCodeele1);
								} else {
									tableRowOne11.addNewTableCell().setText(colValCode1);
								}

								tableRowOne11 = null;
								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);
							}

						}

						else {
							if (dessubitemnameele1.equalsIgnoreCase(
									"Multifunctional digital generator protection relay shall have following protections features")) {

								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.setCantSplitRow(true);
								paragraphtable = tableRowOne.getCell(0).getParagraphs().get(0);
								// paragraphtable.setIndentationLeft(100);
								// paragraphtable.setIndentationRight(100);
								paragraphtable.setAlignment(ParagraphAlignment.CENTER);
								runtable = paragraphtable.createRun();
								runtable.setFontSize(11);
								// runtable.setFontFamily("Cambria (Headings)");
								runtable.setText(descolorderidele1);
								// tableRowOne.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
								if (colunmname1.contains("\n")) {
									String[] arrOfStr = colunmname1.split("\n", 20);
									for (int x1 = 0; x1 < arrOfStr.length; x1++) {
										paragraphtable = tableRowOne.getCell(1).getParagraphs().get(0);
										// paragraphtable.setIndentationLeft(100);
										// paragraphtable.setIndentationRight(100);
										// paragraphtable.setAlignment(ParagraphAlignment.CENTER);
										runtable = paragraphtable.createRun();
										runtable.setFontSize(11);
										// runtable.setFontFamily("Cambria
										// (Headings)");
										runtable.setText(arrOfStr[x1]);
										if (x1 + 1 != arrOfStr.length) {
											runtable.addBreak();
										}
										// tableRowOne.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
									}
								}

								else {
									tableRowOne.getCell(1).setText(colunmname1);
								}
								if (subColValCodeele1 != null && !(subColValCodeele1.equals("NULL"))) {
									tableRowOne.getCell(2).setText(subColValCodeele1);
								} else {
									// tableRowOne.getCell(2).setText(colValCode);
									if (colValCode1.contains("\n")) {

										String[] arrOfStr = colValCode1.split("\n", 20);
										paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);
										paragraphtable.setIndentationLeft(100);
										paragraphtable.setIndentationRight(100);
										paragraphtable.setAlignment(ParagraphAlignment.CENTER);
										runtable = paragraphtable.createRun();
										runtable.setFontSize(11);
										for (int x1 = 0; x1 < arrOfStr.length; x1++) {

											// runtable.setFontFamily("Cambria
											// (Headings)");
											runtable.setText(arrOfStr[x1]);
											if (x1 + 1 != arrOfStr.length) {
												runtable.addBreak();
											}
											tableRowOne.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
										}
									}

									else {
										paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);
										paragraphtable.setIndentationLeft(100);
										paragraphtable.setIndentationRight(100);
										paragraphtable.setAlignment(ParagraphAlignment.CENTER);
										runtable = paragraphtable.createRun();
										runtable.setFontSize(11);
										// runtable.setFontFamily("Cambria
										// (Headings)");
										runtable.setText(colValCode1);
										tableRowOne.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
									}

								}

								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

							else {

								XWPFTableRow tableRowOne = table.createRow();

								tableRowOne.getCell(0).setText(descolorderidele1);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
								tableRowOne.getCell(1).setText(colunmname1);
								// tableRowOne.getCell(1).setText(" : ");
								XWPFParagraph paragraph12 = tableRowOne.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								paragraph12.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run12 = paragraph12.createRun();
								run12.setFontSize(14);
								run12.setFontFamily("Cambria (Headings)");
								run12.setText(":");

								if (subColValCodeele1 != null && !(subColValCodeele1.equals("NULL"))) {
									tableRowOne.getCell(3).setText(subColValCodeele1);
								} else {
									// tableRowOne.getCell(3).setText(colValCode1);
									if (colValCode1.contains("\n")) {

										String[] arrOfStr = colValCode1.split("\n", 200);
										for (int x1 = 0; x1 < arrOfStr.length; x1++) {
											XWPFParagraph paragraph21 = tableRowOne.getCell(3).getParagraphs().get(0);

											// System.out.println(table.getNumberOfRows());
											// paragraph21.setAlignment(ParagraphAlignment.CENTER);
											XWPFRun run123 = paragraph21.createRun();
											run123.setFontSize(11);
											// run123.setFontFamily("Cambria
											// (Headings)");
											run123.setText(arrOfStr[x1]);
											run123.addBreak();
											logger.info("rhs word start");
											logger.info(arrOfStr[x1]);
											logger.info("rhs word end");
										}
									} else {
										tableRowOne.getCell(3).setText(colValCode1);

									}
								}

								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);
								if (colunmname1.equalsIgnoreCase("Number of Module")) {
									if (reportBean.getWordData().get(c).getRhsColTechcomments() != null) {
										if (!reportBean.getWordData().get(c).getRhsColTechcomments().equals("NULL")) {
											if (reportBean.getWordData().get(c).getRhsColTechcomments()
													.contains("\n")) {

												String[] arrOfStr = reportBean.getWordData().get(c)
														.getRhsColTechcomments().split("\n", 200);
												for (int x1 = 0; x1 < arrOfStr.length; x1++) {
													XWPFParagraph paragraph21 = tableRowOne.getCell(3).getParagraphs()
															.get(0);

													// System.out.println(table.getNumberOfRows());
													// paragraph21.setAlignment(ParagraphAlignment.CENTER);
													XWPFRun run123 = paragraph21.createRun();
													if (x1 == 0) {
														run123.addBreak();

													}
													run123.setFontSize(11);
													// run123.setFontFamily("Cambria
													// (Headings)");
													run123.setText(arrOfStr[x1]);
													run123.addBreak();
													logger.info("rhs word start");
													logger.info(arrOfStr[x1]);
													logger.info("rhs word end");
												}
											} else {
												tableRowOne.getCell(3).setText(
														reportBean.getWordData().get(c).getRhsColTechcomments());

											}
										}

									}
								}

							}

						}

					}

				}

				// To print technical remarks of the sub itemq

				if (c + 1 == reportBean.getWordData().size()) {
					if ((techremarksele1 != null)) {
						paragraph = document.createParagraph();
						run = paragraph.createRun();
						run.setText(techremarksele1);
						// run.setFontFamily("Cambria (Headings)");
						run.setFontSize(11);
					}
				} // if (x + 1 == reportBean.getWordData().size())

			} // if (desitemnameele != null)

			previtemnameele1 = itemnameele1;
			prevqty1 = qty1;
			prevsubitemnameele1 = subitemnameele1;
			prevdesitemnameele1 = desitemnameele1;
			prevdessubitemnameele1 = dessubitemnameele1;
			previtemcodeele1 = itemcodeele1;
			prevtechremarksele1 = techremarksele1;
			prevsubcategoryele1 = subcategorynameele1;
			prevheadertextele1 = headertextele1;
			prevfootertextele1 = footertextele1;

			if (c + 1 == reportBean.getWordData().size()) {
				if ((footertextele1 != null) && !(footertextele1.equals("NULL")) && !(footertextele1.equals("NULL:"))) {

					// paragraph = document.createParagraph();
					// run = paragraph.createRun();
					// run.setText(footertextele1);
					// run.setText(":");
					//
					// run.setFontFamily("Cambria (Headings)");
					// run.setFontSize(11);

				}
			}

		} // for loop
			// ele aux ends

		// ele ext start
		int h = 0;
		String prevcategoryextscp1 = null;
		String prevsubcategoryextscp1 = null;
		String previtemnameextscp1 = null;
		int prevqunatityextscp1 = 0;
		String prevsubitemnameextscp1 = null;
		String previtemcodeextscp1 = null;
		String prevtechremarksextscp1 = null;
		String prevtechcommentsextscp1 = null;

		int count88 = 0;
		for (h = c; h < reportBean.getWordData().size(); h++)

		{
			logger.info("eleext");
			String categorynameextscp1 = null;
			String subcategorynameextscp1 = null;
			int itemidextscp1 = 0;
			String itemnameextscp1 = null;
			String itemcodeextscp1 = null;
			String subitemcodeextscp1 = null;
			String subitemnameextscp1 = null;
			int addOnNewColFlag = 0;
			String space = null;
			int qunatityextscp1 = 0;
			String techremarksextscp1 = null;
			String techcommentsextscp1 = null;
			int numberofsubitemsextscp1 = 0;
			XWPFTableRow tableRowOne11 = null;
			logger.info(QuotationDaoImpl.size9);
			if (h == QuotationDaoImpl.size9 && count88 > 0) {
				if (reportBean.getWordData().get(h - 1).getTechRemarks() != null) {

					paragraph = document.createParagraph();
					run = paragraph.createRun();
					run.setText(reportBean.getWordData().get(h - 1).getTechRemarks());
					// run.setFontFamily("Cambria (Headings)");
					run.setFontSize(11);
				}
			}
			if (h == QuotationDaoImpl.size9) {
				break;
			}

			categorynameextscp1 = reportBean.getWordData().get(h).getCategoryName();
			subcategorynameextscp1 = reportBean.getWordData().get(h).getSubCategoryName();
			itemidextscp1 = reportBean.getWordData().get(h).getItemId();
			itemnameextscp1 = reportBean.getWordData().get(h).getItemName();
			itemcodeextscp1 = reportBean.getWordData().get(h).getItemCode();
			subitemcodeextscp1 = reportBean.getWordData().get(h).getSubItemCode();
			subitemnameextscp1 = reportBean.getWordData().get(h).getSubItemName();

			qunatityextscp1 = reportBean.getWordData().get(h).getQuantityAux();
			techremarksextscp1 = reportBean.getWordData().get(h).getTechRemarks();
			techcommentsextscp1 = reportBean.getWordData().get(h).getTechComments();
			numberofsubitemsextscp1 = reportBean.getWordData().get(h).getNumberOfSubItems();
			addOnNewColFlag = reportBean.getWordData().get(h).getAddOnNewColFlag();
			space = reportBean.getWordData().get(h).getSpaceF2f();
			// This is to print the technical remarks at the end of the item
			if (itemcodeextscp1 != null) {
				if (techcommentsextscp1 != null) {
					if (!techcommentsextscp1.equals(prevtechcommentsextscp1)) {
						paragraph = document.createParagraph();
						run = paragraph.createRun();
						run.setText(techcommentsextscp1);

						// run.setFontFamily("Cambria (Headings)");
						run.setFontSize(11);
						run.setBold(true);
					} // if (!techremarkextscp.equals(prevtechremarksextscp))
				} // if (techremarksextscp != null)
			} // (!itemcodeextscp.equals(previtemcodeextscp) && itemcodeextscp
				// != null)

			itemnameextscp1 = reportBean.getWordData().get(h).getItemName();
			subitemnameextscp1 = reportBean.getWordData().get(h).getSubItemName();

			// to print item name only once
			if (!itemnameextscp1.equals(previtemnameextscp1)) {

				paragraph = document.createParagraph();
				run = paragraph.createRun();
				run.setText(reportBean.getWordData().get(h).getItemName());
				run.setBold(true);
				run.setFontFamily("Cambria (Headings)");
				run.setFontSize(12);

				// table = document.createTable();
				// setTableAlign(table, ParagraphAlignment.LEFT);// Creating
				// // table
				// // only once
				// tableRowOne11 = table.getRow(0);

			} // if (!itemnameextscp.equals(previtemnameextscp))

			// To print the sub item name only once
			if (subitemnameextscp1 != null) {
				if (!subitemnameextscp1.equals(prevsubitemnameextscp1)) {
					if (count88 > 0) {
						if (prevtechremarksextscp1 != null) {

							paragraph = document.createParagraph();
							run = paragraph.createRun();
							run.setText(prevtechremarksextscp1);
							// run.setFontFamily("Cambria (Headings)");
							run.setFontSize(11);
						}
					}

					paragraph = document.createParagraph();
					run = paragraph.createRun();
					run.setText(reportBean.getWordData().get(h).getSubItemName());
					run.setText(":");
					if ((qunatityextscp1) != prevqunatityextscp1) {
						if (reportBean.getWordData().get(h).getQuantityAux() > 1) {
							run.setText(space + space + space + space + space + space + space + space + space + space
									+ space + space + space + space + space + space + space + space + space + space
									+ String.valueOf("Quantity :" + reportBean.getWordData().get(h).getQuantityAux()));
						}
					}
					run.setBold(true);
					run.setFontFamily("Cambria (Headings)");
					run.setFontSize(11);
					if (reportBean.getWordData().get(h).getColNm() != null) {

						table = document.createTable();
						CTTblPr tblpro = table.getCTTbl().getTblPr();

						CTTblBorders borders = tblpro.addNewTblBorders();
						borders.addNewBottom().setVal(STBorder.NONE);
						borders.addNewLeft().setVal(STBorder.NONE);
						borders.addNewRight().setVal(STBorder.NONE);
						borders.addNewTop().setVal(STBorder.NONE);
						// also inner borders
						borders.addNewInsideH().setVal(STBorder.NONE);
						borders.addNewInsideV().setVal(STBorder.NONE);
						setTableAlign(table, ParagraphAlignment.LEFT);
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);

						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
						// Creating
						// table
						// only once
						tableRowOne11 = table.getRow(0);
					}
				} // if (!subitemnameextscp.equals(prevsubitemnameextscp))

				// To print the lhs and rhs of sub item
				if (subitemnameextscp1 != null) // Compare two strings
				{
					String colunmname1 = reportBean.getWordData().get(h).getColNm();
					String colValCode1 = reportBean.getWordData().get(h).getColValCd();

					if (colunmname1 != null && colValCode1 != null) {
						// paragraph = document.createParagraph();
						// run = paragraph.createRun();
						// run.setText(colunmname);
						// run.setText(":");
						// run.setText(colValCode);
						// run.setFontFamily("Cambria (Headings)");
						// run.setFontSize(11);

						if (tableRowOne11 != null) {
							tableRowOne11.getCell(0).setText(colunmname1);

							tableRowOne11.addNewTableCell();
							XWPFParagraph paragraph12 = tableRowOne11.getCell(1).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");
							tableRowOne11.addNewTableCell().setText(colValCode1);
							tableRowOne11 = null;
						}

						else {
							XWPFTableRow tableRowOne = table.createRow();
							tableRowOne.getCell(0).setText(colunmname1);
							tableRowOne.addNewTableCell();
							XWPFParagraph paragraph12 = tableRowOne.getCell(1).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");
							tableRowOne.getCell(2).setText(colValCode1);

						}
					}

					// To print technical remarks of the sub itemq

					// if (h + 1 == reportBean.getWordData().size()) {
					// paragraph = document.createParagraph();
					// run = paragraph.createRun();
					// run.setText(techremarksextscp1);
					//
					// run.setFontFamily("Cambria (Headings)");
					// run.setFontSize(11);
					// } // if (n + 1 == reportBean.getWordData().size())

				} // if (subitemnameextscp != null )

			} // if (subitemnameextscp != null)
			prevqunatityextscp1 = qunatityextscp1;
			previtemnameextscp1 = itemnameextscp1;
			prevsubitemnameextscp1 = subitemnameextscp1;
			previtemcodeextscp1 = itemcodeextscp1;
			prevtechremarksextscp1 = techremarksextscp1;
			prevtechcommentsextscp1 = techcommentsextscp1;
			count88 = count88 + 1;
		} // for loop

		// ele ext ends

		// ci result set

		int xci = 0;
		int prevqtyci = 0;
		String prevcategoryci = null;
		String prevsubcategoryci = null;
		String previtemnameci = null;
		String prevdesitemnameci = null;
		String prevdessubitemnameci = null;
		String previtemcodeci = null;
		String prevtechremarksci = null;
		String prevheadertexcitci = null;
		String prevfootertexcitci = null;
		int countcier4ci = 0;
		int countci = 0;
		int countci6 = 0;
		String vmsNotes = null;
		String vmsRemarks = null;
		for (xci = h; xci < reportBean.getWordData().size(); xci++)

		{
			logger.info("ci");
			logger.info(reportBean.getWordData().get(xci).getItemName());
			String categorynameci = null;
			String subcategorynameci = null;
			int itemidci = 0;
			int qtyci = 0;
			String itemnameci = null;
			String desitemnameci = null;
			String dessubitemnameci = null;
			String itemcodeci = null;
			String subitemcodeci = null;
			String subitemnameci = null;
			int desitemorderid = 0;
			int qunatityci = 0;
			String techremarksci = null;
			String techcommentsci = null;
			int numberofsubitemsci = 0;
			int addOnNewColFlag = 0;
			int orderidci = 0;
			int dessubitemorderidci = 0;
			String headertexcitci = null;
			String footertexcitci = null;
			String subColValCodeci = null;
			String space = null;
			String descolorderidci = null;
			int linkFlag = 0;

			XWPFTableRow tableRowOne1 = null;

			if (xci == QuotationDaoImpl.sizeCi) {
				if (countcier4ci > 0) {
					if ((!reportBean.getWordData().get(xci - 1).getFooterText().equals("NULL"))
							&& (reportBean.getWordData().get(xci - 1).getFooterText() != null)) {

						if (reportBean.getWordData().get(xci - 1).getFooterText().contains("\n")) {
							if (!reportBean.getWordData().get(xci - 1).getItemCode().equalsIgnoreCase("VMS")) {
								String[] arrOfStr = reportBean.getWordData().get(xci - 1).getFooterText().split("\n",
										20);
								for (int x1 = 0; x1 < arrOfStr.length; x1++) {
									XWPFParagraph paragraph21 = document.createParagraph();

									// System.out.println(table.getNumberOfRows());
									// paragraph21.setAlignment(ParagraphAlignment.CENTER);
									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									// run123.setFontFamily("Cambria
									// (Headings)");
									run123.setText(arrOfStr[x1]);
									logger.info("rhs word start");
									logger.info(arrOfStr[x1]);
									logger.info("rhs word end");
								}
							} else {
								vmsNotes = reportBean.getWordData().get(xci - 1).getFooterText();
							}
						}

						else {
							if (!reportBean.getWordData().get(xci - 1).getItemCode().equalsIgnoreCase("VMS")) {

								XWPFParagraph paragraph21 = document.createParagraph();

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(reportBean.getWordData().get(xci - 1).getFooterText());
							} else {
								vmsNotes = reportBean.getWordData().get(xci - 1).getFooterText();

							}
						}

					}
					if (reportBean.getWordData().get(xci - 1).getTechRemarks() != null) {

						if ((!reportBean.getWordData().get(xci - 1).getTechRemarks().equals("NULL"))) {
							if (!reportBean.getWordData().get(xci - 1).getItemCode().equalsIgnoreCase("VMS")) {
								if (reportBean.getWordData().get(xci - 1).getTechRemarks().contains("\n")) {

									String[] arrOfStr = reportBean.getWordData().get(xci - 1).getTechRemarks()
											.split("\n", 20);
									for (int x1 = 0; x1 < arrOfStr.length; x1++) {
										XWPFParagraph paragraph21 = document.createParagraph();

										// System.out.println(table.getNumberOfRows());
										// paragraph21.setAlignment(ParagraphAlignment.CENTER);
										XWPFRun run123 = paragraph21.createRun();
										run123.setFontSize(11);
										// run123.setFontFamily("Cambria
										// (Headings)");
										run123.setText(arrOfStr[x1]);
										logger.info("rhs word start");
										logger.info(arrOfStr[x1]);
										logger.info("rhs word end");
									}
								} else {

									XWPFParagraph paragraph21 = document.createParagraph();

									// System.out.println(table.getNumberOfRows());
									// paragraph21.setAlignment(ParagraphAlignment.CENTER);
									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									// run123.setFontFamily("Cambria
									// (Headings)");
									run123.setText(reportBean.getWordData().get(xci - 1).getTechRemarks());
								}
							} else {
								vmsRemarks = reportBean.getWordData().get(xci - 1).getTechRemarks();
							}
						}
					}
				}

				break;
			}
			qtyci = reportBean.getWordData().get(xci).getItemQuantity();
			categorynameci = reportBean.getWordData().get(xci).getCategoryName();
			subcategorynameci = reportBean.getWordData().get(xci).getSubCategoryName();
			itemidci = reportBean.getWordData().get(xci).getItemId();
			itemnameci = reportBean.getWordData().get(xci).getItemName();
			desitemnameci = reportBean.getWordData().get(xci).getDesItemName();
			dessubitemnameci = reportBean.getWordData().get(xci).getDesSubItemName();
			itemcodeci = reportBean.getWordData().get(xci).getItemCode();
			subitemcodeci = reportBean.getWordData().get(xci).getSubItemCode();
			subitemnameci = reportBean.getWordData().get(xci).getSubItemName();
			qunatityci = reportBean.getWordData().get(xci).getQuantity();
			techremarksci = reportBean.getWordData().get(xci).getTechRemarks();
			techcommentsci = reportBean.getWordData().get(xci).getTechComments();
			numberofsubitemsci = reportBean.getWordData().get(xci).getNumberOfSubItems();
			orderidci = reportBean.getWordData().get(xci).getOrderId();
			dessubitemorderidci = reportBean.getWordData().get(xci).getDesSubItemOrderId();
			descolorderidci = reportBean.getWordData().get(xci).getDesColOrderId();
			subColValCodeci = reportBean.getWordData().get(xci).getSubColValCode();
			headertexcitci = reportBean.getWordData().get(xci).getHeaderText();
			footertexcitci = reportBean.getWordData().get(xci).getFooterText();
			linkFlag = reportBean.getWordData().get(xci).getLinkFlag();
			desitemorderid = reportBean.getWordData().get(xci).getDesItemOrderId();
			addOnNewColFlag = reportBean.getWordData().get(xci).getAddOnNewColFlag();
			space = reportBean.getWordData().get(xci).getSpaceF2f();
			int countcier3 = 0;

			int[] cols = { 4896, 1872, 4032, 1728, 1440 };
			//
			// This is to print the technical remarks at the end of the item
			if (!itemcodeci.equals(previtemcodeci) && itemcodeci != null) {
				if (techremarksci != null) {
					// if (!techremarksci.equals(prevtechremarksci)) {
					//
					// paragraph = document.createParagraph();
					// run = paragraph.createRun();
					// run.setText(prevtechremarksci);
					// run.addBreak();
					// // run.setFontFamily("Cambria (Headings)");
					// run.setFontSize(11);
					// } // if (!techremarksci.equals(prevtechremarksci))
				} // if (techremarksci != null)
			} // if (!itemcodeci.equals(previtemcodeci) && itemcodeci !=
				// null)

			itemnameci = reportBean.getWordData().get(xci).getItemName();
			desitemnameci = reportBean.getWordData().get(xci).getDesItemName();

			// to print subcategory name
			if (!subcategorynameci.equals(prevsubcategoryci)) {
				paragraph = document.createParagraph();
				run = paragraph.createRun();

				run.setText("");
				paragraph.setPageBreak(true);

				XWPFParagraph paragraphcat = document.createParagraph();
				XWPFRun runcat = paragraphcat.createRun();

				runcat.setText(subcategorynameci);
				runcat.setBold(true);
				runcat.setFontFamily("Cambria (Headings)");
				runcat.setFontSize(14);
				paragraphcat.setStyle("heading 1");

			}

			// to print item name only once
			if (!itemnameci.equals(previtemnameci)) {

				countcier4ci = countcier4ci + 1;
				if (countcier4ci > 1) {
					if ((prevfootertexcitci != null) && (!(prevfootertexcitci.equals("NULL")))) {
						if (!footertexcitci.equals(prevfootertexcitci)) {
							if (prevfootertexcitci.contains("\n")) {
								if (!itemcodeci.equalsIgnoreCase("VMS")) {
									String[] arrOfStr = prevfootertexcitci.split("\n", 20);
									for (int x1 = 0; x1 < arrOfStr.length; x1++) {
										XWPFParagraph paragraph21 = document.createParagraph();

										// System.out.println(table.getNumberOfRows());
										// paragraph21.setAlignment(ParagraphAlignment.CENTER);
										XWPFRun run123 = paragraph21.createRun();
										run123.setFontSize(11);
										// run123.setFontFamily("Cambria
										// (Headings)");
										run123.setText(arrOfStr[x1]);
										logger.info("rhs word start");
										logger.info(arrOfStr[x1]);
										logger.info("rhs word end");
									}
								} else {
									vmsNotes = prevfootertexcitci;
								}

							} else {
								if (!itemcodeci.equalsIgnoreCase("VMS")) {

									XWPFParagraph paragraph21 = document.createParagraph();

									// System.out.println(table.getNumberOfRows());
									// paragraph21.setAlignment(ParagraphAlignment.CENTER);
									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									// run123.setFontFamily("Cambria
									// (Headings)");
									run123.setText(prevfootertexcitci);
								} else {
									vmsNotes = prevfootertexcitci;

								}
							}

						}
					}
					if ((reportBean.getWordData().get(xci - 1).getTechRemarks() != null)) {
						if ((!reportBean.getWordData().get(xci - 1).getTechRemarks().equalsIgnoreCase("NULL"))) {
							if (reportBean.getWordData().get(xci - 1).getTechRemarks().contains("\n")) {

								String[] arrOfStr = reportBean.getWordData().get(xci - 1).getTechRemarks().split("\n",
										20);
								for (int x1 = 0; x1 < arrOfStr.length; x1++) {
									XWPFParagraph paragraph21 = document.createParagraph();

									// System.out.println(table.getNumberOfRows());
									// paragraph21.setAlignment(ParagraphAlignment.CENTER);
									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									// run123.setFontFamily("Cambria
									// (Headings)");
									run123.setText(arrOfStr[x1]);
									logger.info("rhs word start");
									logger.info(arrOfStr[x1]);
									logger.info("rhs word end");
								}
							} else {

								XWPFParagraph paragraph21 = document.createParagraph();

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(reportBean.getWordData().get(xci - 1).getTechRemarks());
							}

						}
					}
				}

				XWPFParagraph paragraphiname = document.createParagraph();
				XWPFRun runiname = paragraphiname.createRun();

				runiname.setText(itemnameci);
				if ((qtyci) != prevqtyci) {
					if (reportBean.getWordData().get(xci).getItemQuantity() > 1) {
						runiname.setText(space + space + space + space + space + space + space + space + space + space
								+ space + space + space + space + space + space + space + space + space + space + space
								+ space + space + space + space
								+ String.valueOf("Quantity :" + reportBean.getWordData().get(xci).getItemQuantity()));
					}
				}
				runiname.setBold(true);
				runiname.setFontFamily("Cambria (Headings)");
				runiname.setFontSize(13);

				// table = document.createTable();
				//
				// CTTblLayoutType type =
				// table.getCTTbl().getTblPr().addNewTblLayout();
				// type.setType(STTblLayoutType.FIxciED);
				// CTTblPr tblpro = table.getCTTbl().getTblPr();
				//
				// CTTblBorders borders = tblpro.addNewTblBorders();
				// borders.addNewBottom().setVal(STBorder.NONE);
				// borders.addNewLeft().setVal(STBorder.NONE);
				// borders.addNewRight().setVal(STBorder.NONE);
				// borders.addNewTop().setVal(STBorder.NONE);
				// // also inner borders
				// borders.addNewInsideH().setVal(STBorder.NONE);
				// borders.addNewInsideV().setVal(STBorder.NONE);
				// table.setWidth(2000);
				// CTTblWidth width =
				// table.getCTTbl().addNewTblPr().addNewTblW();
				// width.setType(STTblWidth.DxciA);
				// width.setW(BigInteger.valueOf(9072));
				// CTTblPr tblpro = table.getCTTbl().getTblPr();
				//
				// CTTblBorders borders = tblpro.addNewTblBorders();
				// borders.addNewBottom().setVal(STBorder.SINGLE);
				// borders.addNewLeft().setVal(STBorder.SINGLE);
				// borders.addNewRight().setVal(STBorder.SINGLE);
				// borders.addNewTop().setVal(STBorder.SINGLE);
				// //also inner borders
				// borders.addNewInsideH().setVal(STBorder.SINGLE);
				// borders.addNewInsideV().setVal(STBorder.SINGLE);
				// setTableAlign(table, ParagraphAlignment.LEFT);// Creating
				// // table
				// // only once
				// table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
				// table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(2000));
				// table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));

				// tableRowOne1 = table.getRow(0);
			} // if (!itemnameci.equals(previtemnameci))

			if (desitemnameci != null) {
				if (!desitemnameci.equals(prevdesitemnameci)) {
					if (countci6 > 0) {

						if (prevdesitemnameci != null) {

							if (prevdesitemnameci.equalsIgnoreCase(
									"Multifunctional digital generator protection relay shall have following protections features")) {
								paragraph = document.createParagraph();
								run = paragraph.createRun();
								run.setText("");
							}
						}
						if (prevdesitemnameci.equals("Design Data")
								&& reportBean.getWordData().get(xci - 1).getLinkFlag() == 1) {
							paragraph = document.createParagraph();
							run = paragraph.createRun();
							run.setText("");
						}
					}
					countci6 = countci6 + 1;
					countci = 0;
					XWPFParagraph paragraph2 = document.createParagraph();
					XWPFRun run2 = paragraph2.createRun();
					run2.setText(String.valueOf(desitemorderid));
					run2.setText("   ");
					run2.setText(desitemnameci);
					run2.setBold(true);
					run2.setFontFamily("Cambria (Headings)");
					run2.setFontSize(12);

					if ((headertexcitci != null) && !headertexcitci.equals("NULL")) {
						if (!headertexcitci.equals(prevheadertexcitci)) {
							if (headertexcitci.contains("\n")) {

								String[] arrOfStr = headertexcitci.split("\n", 20);
								for (int x1 = 0; x1 < arrOfStr.length; x1++) {
									XWPFParagraph paragraph21 = document.createParagraph();

									// System.out.println(table.getNumberOfRows());
									// paragraph21.setAlignment(ParagraphAlignment.CENTER);
									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									// run123.setFontFamily("Cambria
									// (Headings)");
									run123.setText(arrOfStr[x1]);
									// run123.addBreak();
									logger.info("rhs word start");
									logger.info(arrOfStr[x1]);
									logger.info("rhs word end");
								}
							} else {
								XWPFParagraph paragraph21 = document.createParagraph();
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(headertexcitci);

							}
						}
					}
					if (dessubitemnameci == null) {
						table = document.createTable();

						// table.setWidth(2000);
						CTTblLayoutType type = table.getCTTbl().getTblPr().addNewTblLayout();
						type.setType(STTblLayoutType.FIXED);

						// table.setInsideHBorder(xciWPFBorderTypFe.SINGLE, 8,
						// 5,
						// "0000FF");
						// table.setInsideVBorder(xciWPFBorderType.SINGLE, 8, 5,
						// "0000FF");

						// CTTblWidth width =
						// table.getCTTbl().addNewTblPr().addNewTblW();
						// width.setType(STTblWidth.DxciA);
						// width.setW(BigInteger.valueOf(9072));
						if (desitemnameci.equalsIgnoreCase(
								"Multifunctional digital generator protection relay shall have following protections features")) {
							CTTblPr tblpro = table.getCTTbl().getTblPr();

							CTTblBorders borders = tblpro.addNewTblBorders();
							borders.addNewBottom().setVal(STBorder.THICK);
							borders.addNewLeft().setVal(STBorder.THICK);
							borders.addNewRight().setVal(STBorder.THICK);
							borders.addNewTop().setVal(STBorder.THICK);

							// also inner borders
							borders.addNewInsideH().setVal(STBorder.THICK);
							borders.addNewInsideV().setVal(STBorder.THICK);
							borders.getInsideH().setSz(BigInteger.valueOf(12));
							borders.getInsideV().setSz(BigInteger.valueOf(12));
							borders.getTop().setSz(BigInteger.valueOf(12));
							borders.getBottom().setSz(BigInteger.valueOf(12));
							borders.getLeft().setSz(BigInteger.valueOf(12));
							borders.getRight().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getTop().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getLeft().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getRight().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getBottom().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getInsideH().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getInsideV().setSz(BigInteger.valueOf(12));
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(5000));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
						} else if (desitemnameci.equals("Design Data") && linkFlag == 1) {
							table.setCellMargins(200, 100, 100, 100);

							CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
							type1.setType(STTblLayoutType.FIXED);
							setTableAlign(table, ParagraphAlignment.CENTER);

							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(2000));
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));

						} else {
							CTTblPr tblpro = table.getCTTbl().getTblPr();

							CTTblBorders borders = tblpro.addNewTblBorders();
							borders.addNewBottom().setVal(STBorder.NONE);
							borders.addNewLeft().setVal(STBorder.NONE);
							borders.addNewRight().setVal(STBorder.NONE);
							borders.addNewTop().setVal(STBorder.NONE);
							// also inner borders
							borders.addNewInsideH().setVal(STBorder.NONE);
							borders.addNewInsideV().setVal(STBorder.NONE);
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));

						}

						setTableAlign(table, ParagraphAlignment.LEFT);// Creating
																		// table
																		// only
																		// once
						tableRowOne1 = table.getRow(0);
					}

				} // if (!subitemnameci.equals(prevdesitemnameci))
				else {
					if (!itemnameci.equals(previtemnameci)) {
						countci = 0;
						XWPFParagraph paragraph2 = document.createParagraph();
						XWPFRun run2 = paragraph2.createRun();
						run2.setText(String.valueOf(desitemorderid));
						run2.setText("   ");
						run2.setText(desitemnameci);
						run2.setBold(true);
						run2.setFontFamily("Cambria (Headings)");
						run2.setFontSize(12);

						if ((headertexcitci != null) && !headertexcitci.equals("NULL")) {
							if (!headertexcitci.equals(prevheadertexcitci)) {
								paragraph = document.createParagraph();
								run = paragraph.createRun();
								run.setText(headertexcitci);
								// run.setText(":");

								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);
							}
						}
						if (dessubitemnameci == null) {
							table = document.createTable();

							// table.setWidth(2000);
							CTTblLayoutType type = table.getCTTbl().getTblPr().addNewTblLayout();
							type.setType(STTblLayoutType.FIXED);
							// table.setInsideHBorder(xciWPFBorderType.SINGLE,
							// 8, 5,
							// "0000FF");
							// table.setInsideVBorder(xciWPFBorderType.SINGLE,
							// 8, 5,
							// "0000FF");

							// CTTblWidth width =
							// table.getCTTbl().addNewTblPr().addNewTblW();
							// width.setType(STTblWidth.DxciA);
							// width.setW(BigInteger.valueOf(9072));
							if (desitemnameci.equalsIgnoreCase(
									"Multifunctional digital generator protection relay shall have following protections features")) {
								CTTblPr tblpro = table.getCTTbl().getTblPr();

								CTTblBorders borders = tblpro.addNewTblBorders();
								borders.addNewBottom().setVal(STBorder.THICK);
								borders.addNewLeft().setVal(STBorder.THICK);
								borders.addNewRight().setVal(STBorder.THICK);
								borders.addNewTop().setVal(STBorder.THICK);
								// also inner borders
								borders.addNewInsideH().setVal(STBorder.THICK);
								borders.addNewInsideV().setVal(STBorder.THICK);
								borders.addNewTop().setVal(STBorder.THICK);

								borders.getTop().setSz(BigInteger.valueOf(12));
								borders.getBottom().setSz(BigInteger.valueOf(12));
								borders.getLeft().setSz(BigInteger.valueOf(12));
								borders.getRight().setSz(BigInteger.valueOf(12));
								borders.getInsideH().setSz(BigInteger.valueOf(12));
								borders.getInsideV().setSz(BigInteger.valueOf(12));
								table.getCTTbl().getTblPr().getTblBorders().getTop().setSz(BigInteger.valueOf(12));
								table.getCTTbl().getTblPr().getTblBorders().getLeft().setSz(BigInteger.valueOf(12));
								table.getCTTbl().getTblPr().getTblBorders().getRight().setSz(BigInteger.valueOf(12));
								table.getCTTbl().getTblPr().getTblBorders().getBottom().setSz(BigInteger.valueOf(12));
								table.getCTTbl().getTblPr().getTblBorders().getInsideH().setSz(BigInteger.valueOf(12));
								table.getCTTbl().getTblPr().getTblBorders().getInsideV().setSz(BigInteger.valueOf(12));
								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));
								table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(5000));
								table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
							} else if (desitemnameci.equals("Design Data") && linkFlag == 1) {
								table.setCellMargins(200, 100, 100, 100);

								CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
								type1.setType(STTblLayoutType.FIXED);
								setTableAlign(table, ParagraphAlignment.CENTER);

								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(2000));
								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1500));

							} else {
								CTTblPr tblpro = table.getCTTbl().getTblPr();

								CTTblBorders borders = tblpro.addNewTblBorders();
								borders.addNewBottom().setVal(STBorder.NONE);
								borders.addNewLeft().setVal(STBorder.NONE);
								borders.addNewRight().setVal(STBorder.NONE);
								borders.addNewTop().setVal(STBorder.NONE);
								// also inner borders
								borders.addNewInsideH().setVal(STBorder.NONE);
								borders.addNewInsideV().setVal(STBorder.NONE);
								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
								table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
								table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1500));

							}

							setTableAlign(table, ParagraphAlignment.LEFT);// Creating
																			// table
																			// only
																			// once
							tableRowOne1 = table.getRow(0);

						}

					}
				}

				if (dessubitemnameci != null) {
					if (!dessubitemnameci.equals(prevdessubitemnameci)) {
						countcier3 = 1;
						XWPFParagraph paragraph3 = document.createParagraph();
						XWPFRun run3 = paragraph3.createRun();
						run3.setBold(true);
						run3.setText("1." + dessubitemorderidci + "   " + dessubitemnameci);
						run3.setFontFamily("Cambria (Headings)");
						run3.setFontSize(12);

						table = document.createTable();

						// table.setInsideHBorder(xciWPFTable.xciWPFBorderType.DASHED,
						// 4, 0, "0000FF");
						// table.setInsideVBorder(xciWPFTable.xciWPFBorderType.DASHED,
						// 4, 0, "0000FF");

						// table.setCellMargins(1, left, bottom, right);
						CTTblLayoutType type = table.getCTTbl().getTblPr().addNewTblLayout();
						type.setType(STTblLayoutType.FIXED);
						if (dessubitemnameci.equalsIgnoreCase(
								"Multifunctional digital generator protection relay shall have following protections features")) {
							CTTblPr tblpro = table.getCTTbl().getTblPr();

							CTTblBorders borders = tblpro.addNewTblBorders();
							borders.addNewBottom().setVal(STBorder.THICK);
							borders.addNewLeft().setVal(STBorder.THICK);
							borders.addNewRight().setVal(STBorder.THICK);
							borders.addNewTop().setVal(STBorder.THICK);
							// also inner borders
							borders.addNewInsideH().setVal(STBorder.THICK);
							borders.addNewInsideV().setVal(STBorder.THICK);
							borders.addNewTop().setVal(STBorder.THICK);
							borders.getTop().setSz(BigInteger.valueOf(12));
							borders.getBottom().setSz(BigInteger.valueOf(12));
							borders.getLeft().setSz(BigInteger.valueOf(12));
							borders.getRight().setSz(BigInteger.valueOf(12));
							borders.getInsideH().setSz(BigInteger.valueOf(12));
							borders.getInsideV().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getTop().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getLeft().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getRight().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getBottom().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getInsideH().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getInsideV().setSz(BigInteger.valueOf(12));
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(5000));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
						}

						else {
							CTTblPr tblpro = table.getCTTbl().getTblPr();

							CTTblBorders borders = tblpro.addNewTblBorders();
							borders.addNewBottom().setVal(STBorder.NONE);
							borders.addNewLeft().setVal(STBorder.NONE);
							borders.addNewRight().setVal(STBorder.NONE);
							borders.addNewTop().setVal(STBorder.NONE);
							// also inner borders
							borders.addNewInsideH().setVal(STBorder.NONE);
							borders.addNewInsideV().setVal(STBorder.NONE);
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));

							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1500));

						}

						// CTTblWidth width =
						// table.getCTTbl().addNewTblPr().addNewTblW();
						// width.setType(STTblWidth.DxciA);
						// width.setW(BigInteger.valueOf(9072));

						// table.setWidth(2000);
						// CTTblPr tblpro = table.getCTTbl().getTblPr();
						//
						// CTTblBorders borders = tblpro.getTblBorders();
						// borders.getBottom().setVal(STBorder.SINGLE);
						// borders.getBottom().setColor("0000FF");
						//
						// borders.getLeft().setVal(STBorder.BASIC_BLACK_DOTS);
						// borders.getLeft().setColor("0000FF");
						// borders.getRight().setVal(STBorder.BASIC_BLACK_DOTS);
						// borders.getRight().setColor("0000FF");
						// borders.getTop().setVal(STBorder.BASIC_BLACK_DOTS);
						// borders.getTop().setColor("0000FF");
						// //also inner borders
						// borders.getInsideH().setVal(STBorder.BASIC_BLACK_DOTS);
						// borders.getInsideH().setColor("0000FF");
						// borders.getInsideV().setVal(STBorder.BASIC_BLACK_DOTS);
						// borders.getInsideV().setColor("0000FF");

						setTableAlign(table, ParagraphAlignment.LEFT);// Creating
																		// table
																		// only
																		// once
						tableRowOne1 = table.getRow(0);

					}
				}

				// To print the item name only once
				// if (desitemnameci != null ) {
				//
				// if (!desitemnameci.equals(prevdesitemnameci)){
				// paragraph = document.createParagraph();
				// run = paragraph.createRun();
				// run.setText(desitemnameci);
				// run.setText(":");
				// run.setBold(true);
				// run.setFontFamily("Cambria (Headings)");
				// run.setFontSize(11);
				//
				// table = document.createTable();
				// setTableAlign(table, ParagraphAlignment.LEFT);// Creating
				// // table
				// // only once
				// tableRowOne1 = table.getRow(0);
				// } // if (!subitemnameci.equals(prevdesitemnameci))
				//
				// if (dessubitemnameci != null &&
				// !dessubitemnameci.equals(prevdessubitemnameci)) {
				//
				//
				// paragraph = document.createParagraph();
				// run = paragraph.createRun();
				// run.setText(dessubitemorderidci+" "+ dessubitemnameci);
				// run.setFontFamily("Cambria (Headings)");
				// run.setFontSize(11);
				//
				// table = document.createTable();
				// setTableAlign(table, ParagraphAlignment.LEFT);// Creating
				// // table
				// // only once
				// tableRowOne1 = table.getRow(0);
				// }
				//

				// To print the lhs and rhs of des item
				String colunmname = reportBean.getWordData().get(xci).getColNm();
				String colValCode = reportBean.getWordData().get(xci).getColValCd();

				if (desitemnameci != null && dessubitemnameci == null) // Compare
																		// two
																		// strings
				{

					if (colunmname != null && colValCode != null) {
						// paragraph = document.createParagraph();
						// run = paragraph.createRun();
						//
						// run.setText(descolorderidci+" "+ colunmname);
						// run.setText(":");
						// run.setText(colValCode);
						// run.setFontFamily("Cambria (Headings)");
						// run.setFontSize(11);

						if (tableRowOne1 != null) {
							if (desitemnameci.equalsIgnoreCase(
									"Multifunctional digital generator protection relay shall have following protections features")) {
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// sdMultifunctional digital generator
								// protection relay shall have following
								// protections featuresd

								tableRowOne1.getCell(0).setText("SL .NO");
								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne1.addNewTableCell().setText("CATEGORY");
								tableRowOne1.addNewTableCell().setText("DESCRIPTION");
								tableRowOne1.getCell(0).setColor("808080");
								tableRowOne1.getCell(1).setColor("808080");
								tableRowOne1.getCell(2).setColor("808080");
								tableRowOne1 = null;
								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);
								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.getCell(0).setText(descolorderidci);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne.getCell(1).setText(colunmname);
								if (subColValCodeci != null && !(subColValCodeci.equals("NULL"))) {
									tableRowOne.getCell(2).setText(subColValCodeci);
								} else {
									tableRowOne.getCell(2).setText(colValCode);
								}

								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							} else if (desitemnameci.equals("Design Data") && linkFlag == 1) {
								// tableRowOne1.getCell(0).setText("SL.NO");
								XWPFParagraph paragraph21 = tableRowOne1.getCell(0).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText("SL .NO");
								run123.setBold(true);

								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne1.addNewTableCell();
								XWPFParagraph pa1 = tableRowOne1.getCell(1).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								pa1.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun ru3 = pa1.createRun();
								ru3.setFontSize(11);
								// ru3.setFontFamily("Cambria (Headings)");
								ru3.setText("Scope of supply and battery limits");
								ru3.setBold(true);

								tableRowOne1.addNewTableCell();
								XWPFParagraph paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								paragraph12.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run12 = paragraph12.createRun();
								run12.setFontSize(11);
								// run12.setFontFamily("Cambria (Headings)");
								run12.setText("Engineering");
								run12.setBold(true);

								tableRowOne1.addNewTableCell();
								XWPFParagraph pa4 = tableRowOne1.getCell(3).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								pa4.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run34 = pa4.createRun();
								run34.setFontSize(11);
								// run34.setFontFamily("Cambria (Headings)");
								run34.setText("Supply");
								run34.setBold(true);

								// tableRowOne1.getCell(0).setColor("808080");
								// tableRowOne1.getCell(1).setColor("808080");
								// tableRowOne1.getCell(2).setColor("808080");
								// tableRowOne1.getCell(3).setColor("808080");
								tableRowOne1 = null;
								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.setCantSplitRow(true);
								// tableRowOne.getCell(0).setText(descolorderidele1);
								paragraphtable = tableRowOne.getCell(0).getParagraphs().get(0);

								paragraphtable.setAlignment(ParagraphAlignment.CENTER);
								runtable = paragraphtable.createRun();
								runtable.setFontSize(11);
								// runtable.setFontFamily("Cambria (Headings)");
								runtable.setText(descolorderidci);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								// tableRowOne.getCell(1).setText(colunmname);
								if (colunmname.contains("\n")) {

									String[] arrOfStr = colunmname.split("\n", 20);
									paragraphtable = tableRowOne.getCell(1).getParagraphs().get(0);

									runtable = paragraphtable.createRun();
									runtable.setFontSize(11);
									for (int x1 = 0; x1 < arrOfStr.length; x1++) {

										// runtable.setFontFamily("Cambria
										// (Headings)");
										runtable.setText(arrOfStr[x1]);
										if (x1 + 1 != arrOfStr.length) {
											runtable.addBreak();
										}
									}
								}

								else {
									paragraphtable = tableRowOne.getCell(1).getParagraphs().get(0);

									runtable = paragraphtable.createRun();
									runtable.setFontSize(11);
									// runtable.setFontFamily("Cambria
									// (Headings)");
									runtable.setText(colunmname);
								}
								if (colValCode.equalsIgnoreCase("Yes") || colValCode.equalsIgnoreCase("No")) {
									// tableRowOne.getCell(2).setText("YES");
									// tableRowOne.getCell(3).setText(colValCode1);
									paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);

									paragraphtable.setAlignment(ParagraphAlignment.CENTER);
									runtable = paragraphtable.createRun();
									runtable.setFontSize(11);
									// runtable.setFontFamily("Cambria
									// (Headings)");
									runtable.setText("Yes");

									paragraphtable = tableRowOne.getCell(3).getParagraphs().get(0);

									paragraphtable.setAlignment(ParagraphAlignment.CENTER);
									runtable = paragraphtable.createRun();
									runtable.setFontSize(11);
									// runtable.setFontFamily("Cambria
									// (Headings)");
									runtable.setText(colValCode);

								} else {
									tableRowOne.getCell(2).setText(" ");
									CTTc ctTc1 = tableRowOne.getCell(2).getCTTc();
									CTTcPr tcPr1 = ctTc1.addNewTcPr();
									CTHMerge hMerge1 = tcPr1.addNewHMerge();
									hMerge1.setVal(STMerge.RESTART);

									CTTcBorders tblBorders1 = tcPr1.addNewTcBorders();

									tblBorders1.addNewRight().setVal(STBorder.NIL);
									tableRowOne.getCell(3).setText(colValCode);
									CTTc ctTc12 = tableRowOne.getCell(3).getCTTc();
									CTTcPr tcPr12 = ctTc12.addNewTcPr();
									CTHMerge hMerge12 = tcPr12.addNewHMerge();
									hMerge12.setVal(STMerge.RESTART);

									CTTcBorders tblBorders12 = tcPr12.addNewTcBorders();

									tblBorders12.addNewLeft().setVal(STBorder.NIL);

								}

								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							} else {
								tableRowOne1.getCell(0).setText(descolorderidci);
								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne1.addNewTableCell().setText(colunmname);
								;
								tableRowOne1.addNewTableCell();
								XWPFParagraph paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								paragraph12.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run12 = paragraph12.createRun();
								run12.setFontSize(14);
								run12.setFontFamily("Cambria (Headings)");
								run12.setText(":");
								if (subColValCodeci != null && !(subColValCodeci.equals("NULL"))) {
									tableRowOne1.addNewTableCell().setText(subColValCodeci);
								} else {
									tableRowOne1.addNewTableCell();
									if (colValCode.contains("\n")) {

										String[] arrOfStr = colValCode.split("\n", 20);
										for (int x1 = 0; x1 < arrOfStr.length; x1++) {
											XWPFParagraph paragraph21 = tableRowOne1.getCell(3).getParagraphs().get(0);

											// System.out.println(table.getNumberOfRows());
											// paragraph21.setAlignment(ParagraphAlignment.CENTER);
											XWPFRun run123 = paragraph21.createRun();
											run123.setFontSize(11);
											// run123.setFontFamily("Cambria
											// (Headings)");
											run123.setText(arrOfStr[x1]);
											run123.addBreak();
											logger.info("rhs word start");
											logger.info(arrOfStr[x1]);
											logger.info("rhs word end");
										}
									} else {
										tableRowOne1.getCell(3).setText(colValCode);

									}
								}

								tableRowOne1 = null;
								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

						}

						else {
							if (desitemnameci.equalsIgnoreCase(
									"Multifunctional digital generator protection relay shall have following protections features")) {

								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.getCell(0).setText(descolorderidci);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne.getCell(1).setText(colunmname);
								if (subColValCodeci != null && !(subColValCodeci.equals("NULL"))) {
									tableRowOne.getCell(2).setText(subColValCodeci);
								} else {
									tableRowOne.getCell(2).setText(colValCode);
								}

								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

							else if ((colunmname.equals("Additional outgoing feeders ( Ampere rating & quantity )"))
									&& countci == 0) {
								countci = 1;
								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.getCell(0).setText(descolorderidci);
								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
								tableRowOne.getCell(1).setText(colunmname);
								// tableRowOne.getCell(1).setText(" : ");
								XWPFParagraph paragraph12 = tableRowOne.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								paragraph12.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run12 = paragraph12.createRun();
								run12.setFontSize(14);
								run12.setFontFamily("Cambria (Headings)");
								run12.setText(":");
								if (subColValCodeci != null && !(subColValCodeci.equals("NULL"))) {
									tableRowOne.getCell(3).setText(subColValCodeci);
								} else {
									tableRowOne.getCell(3).setText(colValCode + "*" + qunatityci);
								}

								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

							else if (colunmname.equals("Additional outgoing feeders ( Ampere rating & quantity )")
									&& countci != 0) {
								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.getCell(0).setText("");
								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne.getCell(1).setText(" ");
								tableRowOne.getCell(2).setText(" ");
								if (subColValCodeci != null && !(subColValCodeci.equals("NULL"))) {
									tableRowOne.getCell(3).setText(subColValCodeci);
								} else {
									tableRowOne.getCell(3).setText(colValCode + "*" + qunatityci);
								}

								// tableRowOne1 = null;
								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

							else if (desitemnameci.equals("Design Data") && linkFlag == 1) {
								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.setCantSplitRow(true);
								// tableRowOne.getCell(0).setText(descolorderidele1);
								paragraphtable = tableRowOne.getCell(0).getParagraphs().get(0);

								paragraphtable.setAlignment(ParagraphAlignment.CENTER);
								runtable = paragraphtable.createRun();
								runtable.setFontSize(11);
								// runtable.setFontFamily("Cambria (Headings)");
								runtable.setText(descolorderidci);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								// tableRowOne.getCell(1).setText(colunmname);
								if (colunmname.contains("\n")) {

									String[] arrOfStr = colunmname.split("\n", 20);
									paragraphtable = tableRowOne.getCell(1).getParagraphs().get(0);

									runtable = paragraphtable.createRun();
									runtable.setFontSize(11);
									for (int x1 = 0; x1 < arrOfStr.length; x1++) {

										// runtable.setFontFamily("Cambria
										// (Headings)");
										runtable.setText(arrOfStr[x1]);
										if (x1 + 1 != arrOfStr.length) {
											runtable.addBreak();
										}
									}
								}

								else {
									paragraphtable = tableRowOne.getCell(1).getParagraphs().get(0);

									runtable = paragraphtable.createRun();
									runtable.setFontSize(11);
									// runtable.setFontFamily("Cambria
									// (Headings)");
									runtable.setText(colunmname);
								}

								if (colValCode.equalsIgnoreCase("Yes") || colValCode.equalsIgnoreCase("No")
										|| reportBean.getWordData().get(xci).getAddOnNewColFlag() == 1) {
									// tableRowOne.getCell(2).setText("YES");
									// tableRowOne.getCell(3).setText(colValCode1);
									if (reportBean.getWordData().get(xci).getRhsColTechcomments() != null
											|| reportBean.getWordData().get(xci).getAddOnNewColFlag() == 1) {
										if (reportBean.getWordData().get(xci).getAddOnNewColFlag() == 1
												|| !reportBean.getWordData().get(xci).getRhsColTechcomments()
														.equalsIgnoreCase("NULL")) {
											if (reportBean.getWordData().get(xci).getRhsColTechcomments() == null
													&& reportBean.getWordData().get(xci).getAddOnNewColFlag() == 1) {
												tableRowOne.getCell(2).setText(" ");
												CTTc ctTc1 = tableRowOne.getCell(2).getCTTc();
												CTTcPr tcPr1 = ctTc1.addNewTcPr();
												CTHMerge hMerge1 = tcPr1.addNewHMerge();
												hMerge1.setVal(STMerge.RESTART);

												CTTcBorders tblBorders1 = tcPr1.addNewTcBorders();

												tblBorders1.addNewRight().setVal(STBorder.NIL);

												tableRowOne.getCell(3).setText(colValCode);
												CTTc ctTc12 = tableRowOne.getCell(3).getCTTc();
												CTTcPr tcPr12 = ctTc12.addNewTcPr();
												CTHMerge hMerge12 = tcPr12.addNewHMerge();
												hMerge12.setVal(STMerge.RESTART);

												CTTcBorders tblBorders12 = tcPr12.addNewTcBorders();

												tblBorders12.addNewLeft().setVal(STBorder.NIL);

											} else {

												paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);

												paragraphtable.setAlignment(ParagraphAlignment.CENTER);
												runtable = paragraphtable.createRun();
												runtable.setFontSize(11);
												// runtable.setFontFamily("Cambria
												// (Headings)");
												runtable.setText(colValCode);

												paragraphtable = tableRowOne.getCell(3).getParagraphs().get(0);

												paragraphtable.setAlignment(ParagraphAlignment.CENTER);
												runtable = paragraphtable.createRun();
												runtable.setFontSize(11);
												// runtable.setFontFamily("Cambria
												// (Headings)");
												runtable.setText(
														reportBean.getWordData().get(xci).getRhsColTechcomments());
											}
										} else {
											paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);

											paragraphtable.setAlignment(ParagraphAlignment.CENTER);
											runtable = paragraphtable.createRun();
											runtable.setFontSize(11);
											// runtable.setFontFamily("Cambria
											// (Headings)");
											runtable.setText("Yes");

											paragraphtable = tableRowOne.getCell(3).getParagraphs().get(0);

											paragraphtable.setAlignment(ParagraphAlignment.CENTER);
											runtable = paragraphtable.createRun();
											runtable.setFontSize(11);
											// runtable.setFontFamily("Cambria
											// (Headings)");
											runtable.setText(colValCode);

										}

									} else {
										paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);

										paragraphtable.setAlignment(ParagraphAlignment.CENTER);
										runtable = paragraphtable.createRun();
										runtable.setFontSize(11);
										// runtable.setFontFamily("Cambria
										// (Headings)");
										runtable.setText("Yes");

										paragraphtable = tableRowOne.getCell(3).getParagraphs().get(0);

										paragraphtable.setAlignment(ParagraphAlignment.CENTER);
										runtable = paragraphtable.createRun();
										runtable.setFontSize(11);
										// runtable.setFontFamily("Cambria
										// (Headings)");
										runtable.setText(colValCode);

									}
								} else {
									tableRowOne.getCell(2).setText(" ");
									CTTc ctTc1 = tableRowOne.getCell(2).getCTTc();
									CTTcPr tcPr1 = ctTc1.addNewTcPr();
									CTHMerge hMerge1 = tcPr1.addNewHMerge();
									hMerge1.setVal(STMerge.RESTART);

									CTTcBorders tblBorders1 = tcPr1.addNewTcBorders();

									tblBorders1.addNewRight().setVal(STBorder.NIL);
									tableRowOne.getCell(3).setText(colValCode);
									CTTc ctTc12 = tableRowOne.getCell(3).getCTTc();
									CTTcPr tcPr12 = ctTc12.addNewTcPr();
									CTHMerge hMerge12 = tcPr12.addNewHMerge();
									hMerge12.setVal(STMerge.RESTART);

									CTTcBorders tblBorders12 = tcPr12.addNewTcBorders();

									tblBorders12.addNewLeft().setVal(STBorder.NIL);

								}

								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

							else {

								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.getCell(0).setText(descolorderidci);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
								tableRowOne.getCell(1).setText(colunmname);
								// tableRowOne.getCell(1).setText(" : ");
								XWPFParagraph paragraph12 = tableRowOne.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								paragraph12.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run12 = paragraph12.createRun();
								run12.setFontSize(14);
								run12.setFontFamily("Cambria (Headings)");
								run12.setText(":");
								if (subColValCodeci != null && !(subColValCodeci.equals("NULL"))) {
									tableRowOne.getCell(3).setText(subColValCodeci);
								} else {
									if (colValCode.contains("\n")) {

										String[] arrOfStr = colValCode.split("\n", 20);
										for (int xci1 = 0; xci1 < arrOfStr.length; xci1++) {
											XWPFParagraph paragraph21 = tableRowOne.getCell(3).getParagraphs().get(0);

											// System.out.println(table.getNumberOfRows());
											// paragraph21.setAlignment(ParagraphAlignment.CENTER);
											XWPFRun run123 = paragraph21.createRun();
											run123.setFontSize(11);
											// run123.setFontFamily("Cambria
											// (Headings)");
											run123.setText(arrOfStr[xci1]);
											run123.addBreak();
											logger.info("rhs word start");
											logger.info(arrOfStr[xci1]);
											logger.info("rhs word end");
										}
									} else {
										tableRowOne.getCell(3).setText(colValCode);

									}
								}

								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

						}

					}
				} else {

					if (desitemnameci != null) {
						// paragraph = document.createParagraph();
						// run = paragraph.createRun();
						// run.setText(descolorderidci+" "+ colunmname);
						// run.setText(":");
						// run.setText(colValCode);
						// run.setFontFamily("Cambria (Headings)");
						// run.setFontSize(11);

						if (tableRowOne1 != null) {
							if (dessubitemnameci.equalsIgnoreCase(
									"Multifunctional digital generator protection relay shall have following protections features")) {
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// sdMultifunctional digital generator
								// protection relay shall have following
								// protections featuresd

								tableRowOne1.getCell(0).setText("SL .NO");
								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne1.addNewTableCell().setText("CATEGORY");
								tableRowOne1.addNewTableCell().setText("DESCRIPTION");
								tableRowOne1.getCell(0).setColor("808080");
								tableRowOne1.getCell(1).setColor("808080");
								tableRowOne1.getCell(2).setColor("808080");
								// tableRowOne1.getCell(0).setColor("GrayColor");
								// tableRowOne1.getCell(1).setColor("GrayColor");
								// tableRowOne1.getCell(2).setColor("GrayColor");
								tableRowOne1 = null;
								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);
								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.getCell(0).setText(descolorderidci);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne.getCell(1).setText(colunmname);
								if (subColValCodeci != null && !(subColValCodeci.equals("NULL"))) {
									tableRowOne.getCell(2).setText(subColValCodeci);
								} else {
									tableRowOne.getCell(2).setText(colValCode);
								}

								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							} else {
								tableRowOne1.getCell(0).setText(descolorderidci);
								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
								tableRowOne1.addNewTableCell().setText(colunmname);
								tableRowOne1.addNewTableCell();
								XWPFParagraph paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								paragraph12.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run12 = paragraph12.createRun();
								run12.setFontSize(14);
								run12.setFontFamily("Cambria (Headings)");
								run12.setText(":");
								if (subColValCodeci != null && !(subColValCodeci.equals("NULL"))) {
									tableRowOne1.addNewTableCell().setText(subColValCodeci);
									;
								} else {
									tableRowOne1.addNewTableCell();
									if (colValCode.contains("\n")) {

										String[] arrOfStr = colValCode.split("\n", 20);
										for (int x1 = 0; x1 < arrOfStr.length; x1++) {
											XWPFParagraph paragraph21 = tableRowOne1.getCell(3).getParagraphs().get(0);

											// System.out.println(table.getNumberOfRows());
											// paragraph21.setAlignment(ParagraphAlignment.CENTER);
											XWPFRun run123 = paragraph21.createRun();
											run123.setFontSize(11);
											// run123.setFontFamily("Cambria
											// (Headings)");
											run123.setText(arrOfStr[x1]);
											run123.addBreak();
											logger.info("rhs word start");
											logger.info(arrOfStr[x1]);
											logger.info("rhs word end");
										}
									} else {
										tableRowOne1.getCell(3).setText(colValCode);

									}
								}

								tableRowOne1 = null;
								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);
							}

						}

						else {
							if (dessubitemnameci.equalsIgnoreCase(
									"Multifunctional digital generator protection relay shall have following protections features")) {

								XWPFTableRow tableRowOne = table.createRow();

								tableRowOne.getCell(0).setText(descolorderidci);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne.getCell(1).setText(colunmname);

								if (subColValCodeci != null && !(subColValCodeci.equals("NULL"))) {
									tableRowOne.getCell(2).setText(subColValCodeci);
								} else {
									tableRowOne.getCell(2).setText(colValCode);
								}

								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

							else {

								XWPFTableRow tableRowOne = table.createRow();

								tableRowOne.getCell(0).setText(descolorderidci);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
								tableRowOne.getCell(1).setText(colunmname);
								// tableRowOne.getCell(1).setText(" : ");
								XWPFParagraph paragraph12 = tableRowOne.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								paragraph12.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run12 = paragraph12.createRun();
								run12.setFontSize(14);
								run12.setFontFamily("Cambria (Headings)");
								run12.setText(":");

								if (subColValCodeci != null && !(subColValCodeci.equals("NULL"))) {
									tableRowOne.getCell(3).setText(subColValCodeci);
								} else {
									// tableRowOne.getCell(3).setText(colValCode);
									if (colValCode.contains("\n")) {

										String[] arrOfStr = colValCode.split("\n", 20);
										for (int x1 = 0; x1 < arrOfStr.length; x1++) {
											XWPFParagraph paragraph21 = tableRowOne.getCell(3).getParagraphs().get(0);

											// System.out.println(table.getNumberOfRows());
											// paragraph21.setAlignment(ParagraphAlignment.CENTER);
											XWPFRun run123 = paragraph21.createRun();
											run123.setFontSize(11);
											// run123.setFontFamily("Cambria
											// (Headings)");
											run123.setText(arrOfStr[x1]);
											run123.addBreak();
											logger.info("rhs word start");
											logger.info(arrOfStr[x1]);
											logger.info("rhs word end");
										}
									} else {
										tableRowOne.getCell(3).setText(colValCode);

									}

								}

								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

						}

					}

				}

				// To print technical remarks of the sub itemq

				if (xci + 1 == reportBean.getWordData().size()) {
					paragraph = document.createParagraph();
					run = paragraph.createRun();
					run.setText(techremarksci);
					run.addBreak();
					run.addBreak();
					// run.setFontFamily("Cambria (Headings)");
					run.setFontSize(11);
				} // if (xci + 1 == reportBean.getWordData().size())

			} // if (desitemnameci != null)

			prevqtyci = qtyci;
			previtemnameci = itemnameci;
			prevdesitemnameci = desitemnameci;
			prevdessubitemnameci = dessubitemnameci;
			previtemcodeci = itemcodeci;
			prevtechremarksci = techremarksci;
			prevsubcategoryci = subcategorynameci;
			prevheadertexcitci = headertexcitci;
			prevfootertexcitci = footertexcitci;

			if (xci + 1 == reportBean.getWordData().size()) {
				if ((footertexcitci != null) && !(footertexcitci.equals("NULL")) && !(footertexcitci.equals("NULL:"))) {

					// paragraph = document.createParagraph();
					// run = paragraph.createRun();
					// run.setText(footertexcitci);
					// run.setText(":");
					//
					// run.setFontFamily("Cambria (Headings)");
					// run.setFontSize(11);

				}
			}

		} // for loop

		int q = 0;

		String prevvmsnm = null;
		String prevheadertextvms = null;
		int prevaddprbflag = 2;
		XWPFTableRow tableRowOne1vms = null;
		int countervms = 0;
		int countevms = 0;
		for (q = xci; q < reportBean.getWordData().size(); q++)

		{
			logger.info("vms");
			countervms = countervms + 1;
			int vmsitemid = 0;
			String vmsnm = null;
			String type = null;
			int addprbflag = 0;
			int orderby = 0;
			String tagnm = null;
			String equipment = null;
			String application = null;
			String location = null;
			int quant = 0;
			float cost = 0;
			int newcolvalflag = 0;
			float itemcost = 0;
			String headertext = null;
			String note = null;

			if (q == QuotationDaoImpl.size7) {
				if (countervms > 1) {
					if (reportBean.getWordData().get(q - 1).getNote() != null) {
						if (!reportBean.getWordData().get(q - 1).getNote().equalsIgnoreCase("NULL")) {
							paragraph = document.createParagraph();
							run = paragraph.createRun();
							if (reportBean.getWordData().get(q - 1).getNote().contains("\n")) {

								String[] arrOfStr = reportBean.getWordData().get(q - 1).getNote().split("\n", 20);
								for (int x1 = 0; x1 < arrOfStr.length; x1++) {
									XWPFParagraph paragraph21 = document.createParagraph();

									// System.out.println(table.getNumberOfRows());
									// paragraph21.setAlignment(ParagraphAlignment.CENTER);
									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									run123.setFontFamily("Cambria (Headings)");
									run123.setText(arrOfStr[x1]);
									logger.info("rhs word start");
									logger.info(arrOfStr[x1]);
									logger.info("rhs word end");
								}
							} else {
								XWPFParagraph paragraph21 = document.createParagraph();
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(reportBean.getWordData().get(q - 1).getNote());

							}
						}
					}
				}
				break;

			}

			vmsitemid = reportBean.getWordData().get(q).getItemId();
			vmsnm = reportBean.getWordData().get(q).getItemName();
			type = reportBean.getWordData().get(q).getType();
			addprbflag = reportBean.getWordData().get(q).getAddPrbFlag();
			orderby = reportBean.getWordData().get(q).getOrderBy();
			tagnm = reportBean.getWordData().get(q).getTagNum();
			equipment = reportBean.getWordData().get(q).getEquipment();
			application = reportBean.getWordData().get(q).getApplication();
			location = reportBean.getWordData().get(q).getLocation();
			quant = reportBean.getWordData().get(q).getQuantity();
			cost = reportBean.getWordData().get(q).getCost2();
			newcolvalflag = reportBean.getWordData().get(q).getNewColValFlag();
			itemcost = reportBean.getWordData().get(q).getItemCost();
			headertext = reportBean.getWordData().get(q).getHeaderText();
			note = reportBean.getWordData().get(q).getNote();
			if (!vmsnm.equals(prevvmsnm)) {

				// paragraph = document.createParagraph();
				// run = paragraph.createRun();
				//
				//
				// run.setText(vmsnm);
				//
				// run.setBold(true);
				// run.setFontFamily("Cambria (Headings)");
				// run.setFontSize(11);
			}
			if (addprbflag != prevaddprbflag) {
				if (addprbflag == 1) {

					paragraph = document.createParagraph();
					run = paragraph.createRun();
					run.setText("");
				}
				paragraph = document.createParagraph();
				run = paragraph.createRun();

				if (addprbflag == 1) {
					run.setText("Additional Probes");
					countevms = 0;
				}

				else {
					// run.setText(type);
					countevms = 0;

				}
				run.setBold(true);
				run.setFontFamily("Cambria (Headings)");
				run.setFontSize(11);

				if (headertext != null) {
					if (!headertext.equals(prevheadertextvms)) {
						// paragraph = document.createParagraph();
						// run = paragraph.createRun();
						// run.setText(reportBean.getWordData().get(q).getHeaderText());
						// run.setText(":");
						// run.setBold(true);
						// run.setFontFamily("Cambria (Headings)");
						// run.setFontSize(11);

					}
				}

				table = document.createTable();
				table.setCellMargins(200, 100, 100, 100);

				CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
				type1.setType(STTblLayoutType.FIXED);
				table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1100));
				table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1200));
				table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1700));
				table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(3000));
				table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1500));
				table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1300));

				tableRowOne1vms = table.getRow(0);

			}

			// To print header text only once

			// create table with rows and columns

			// write to first row, first column
			// if(!instrnm.equals("Additional Instruments"))
			// {
			countevms = countevms + 1;
			if (tableRowOne1vms != null) {
				if (headertext == null || headertext.equals("NULL")) {
					// tableRowOne1vms.getCell(0).setText("SL.NO.");
					// tableRowOne1vms.addNewTableCell().setText("Tag NO");
					// tableRowOne1vms.addNewTableCell().setText("Equipment");
					// tableRowOne1vms.addNewTableCell().setText("Application");
					// tableRowOne1vms.addNewTableCell().setText("Location");
					// tableRowOne1vms.addNewTableCell().setText("Quantity");
					XWPFParagraph pa451 = tableRowOne1vms.getCell(0).getParagraphs().get(0);

					// System.out.println(table.getNumberOfRows());
					pa451.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun ru311 = pa451.createRun();
					ru311.setFontSize(11);
					ru311.setFontFamily("Cambria (Headings)");
					ru311.setText("Sl.no");
					ru311.setBold(true);
					////
					tableRowOne1vms.addNewTableCell();
					XWPFParagraph pa2 = tableRowOne1vms.getCell(1).getParagraphs().get(0);
					pa2.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun ru2 = pa2.createRun();
					ru2.setFontSize(11);
					ru2.setFontFamily("Cambria (Headings)");
					ru2.setText("Tag NO");
					ru2.setBold(true);
					////
					////
					tableRowOne1vms.addNewTableCell();
					XWPFParagraph pa3 = tableRowOne1vms.getCell(2).getParagraphs().get(0);
					pa3.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun ru3 = pa3.createRun();
					ru3.setFontSize(11);
					ru3.setFontFamily("Cambria (Headings)");
					ru3.setText("Equipment");
					ru3.setBold(true);
					////
					////
					tableRowOne1vms.addNewTableCell();
					XWPFParagraph pa4 = tableRowOne1vms.getCell(3).getParagraphs().get(0);
					pa4.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun ru4 = pa4.createRun();
					ru4.setFontSize(11);
					ru4.setFontFamily("Cambria (Headings)");
					ru4.setText("Application");
					ru4.setBold(true);
					////
					////
					tableRowOne1vms.addNewTableCell();
					XWPFParagraph pa5 = tableRowOne1vms.getCell(4).getParagraphs().get(0);
					pa5.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun ru5 = pa5.createRun();
					ru5.setFontSize(11);
					ru5.setFontFamily("Cambria (Headings)");
					ru5.setText("Location");
					ru5.setBold(true);

					tableRowOne1vms.addNewTableCell();
					XWPFParagraph pa6 = tableRowOne1vms.getCell(5).getParagraphs().get(0);
					pa6.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun ru6 = pa6.createRun();
					ru6.setFontSize(11);
					ru6.setFontFamily("Cambria (Headings)");
					ru6.setText("Quantity");
					ru6.setBold(true);
					// tableRowOne1vms.getCell(0).setColor("808080");
					// tableRowOne1vms.getCell(1).setColor("808080");
					// tableRowOne1vms.getCell(2).setColor("808080");
					// tableRowOne1vms.getCell(3).setColor("808080");
					// tableRowOne1vms.getCell(4).setColor("808080");
					// tableRowOne1vms.getCell(5).setColor("808080");

				} else {
					tableRowOne1vms.getCell(0).setText(headertext);
					tableRowOne1vms.addNewTableCell().setText("");
					tableRowOne1vms.addNewTableCell().setText("");
					tableRowOne1vms.addNewTableCell().setText("");
					tableRowOne1vms.addNewTableCell().setText("");
					tableRowOne1vms.addNewTableCell().setText("");
					mergeCellHorizontally(table, 0, 0, 5);

					// tableRowOne1vms.getCell(0).setColor("808080");
					// tableRowOne1vms.getCell(1).setColor("808080");
					// tableRowOne1vms.getCell(2).setColor("808080");
					// tableRowOne1vms.getCell(3).setColor("808080");
					// tableRowOne1vms.getCell(4).setColor("808080");
					// tableRowOne1vms.getCell(5).setColor("808080");
					XWPFTableRow tableRowOne1 = table.createRow();
					////
					// tableRowOne1.getCell(0).setText("SL.NO.");
					// tableRowOne1.getCell(1).setText("Tag NO");
					// tableRowOne1.getCell(2).setText("Equipment");
					// tableRowOne1.getCell(3).setText("Application");
					// tableRowOne1.getCell(4).setText("Location");
					// tableRowOne1.getCell(5).setText("Quantity");
					XWPFParagraph pa451 = tableRowOne1.getCell(0).getParagraphs().get(0);

					// System.out.println(table.getNumberOfRows());
					pa451.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun ru311 = pa451.createRun();
					ru311.setFontSize(11);
					ru311.setFontFamily("Cambria (Headings)");
					ru311.setText("Sl.no");
					ru311.setBold(true);
					////
					// .addNewTableCell();
					XWPFParagraph pa2 = tableRowOne1.getCell(1).getParagraphs().get(0);
					pa2.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun ru2 = pa2.createRun();
					ru2.setFontSize(11);
					ru2.setFontFamily("Cambria (Headings)");
					ru2.setText("Tag NO");
					ru2.setBold(true);
					////
					////
					// tableRowOne1.addNewTableCell();
					XWPFParagraph pa3 = tableRowOne1.getCell(2).getParagraphs().get(0);
					pa3.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun ru3 = pa3.createRun();
					ru3.setFontSize(11);
					ru3.setFontFamily("Cambria (Headings)");
					ru3.setText("Equipment");
					ru3.setBold(true);
					////
					////
					// tableRowOne1.addNewTableCell();
					XWPFParagraph pa4 = tableRowOne1.getCell(3).getParagraphs().get(0);
					pa4.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun ru4 = pa4.createRun();
					ru4.setFontSize(11);
					ru4.setFontFamily("Cambria (Headings)");
					ru4.setText("Application");
					ru4.setBold(true);
					////
					////
					// tableRowOne1.addNewTableCell();
					XWPFParagraph pa5 = tableRowOne1.getCell(4).getParagraphs().get(0);
					pa5.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun ru5 = pa5.createRun();
					ru5.setFontSize(11);
					ru5.setFontFamily("Cambria (Headings)");
					ru5.setText("Location");
					ru5.setBold(true);

					// tableRowOne1.addNewTableCell();
					XWPFParagraph pa6 = tableRowOne1.getCell(5).getParagraphs().get(0);
					pa6.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun ru6 = pa6.createRun();
					ru6.setFontSize(11);
					ru6.setFontFamily("Cambria (Headings)");
					ru6.setText("Quantity");
					ru6.setBold(true);

					// tableRowOne1.getCell(0).setColor("808080");
					// tableRowOne1.getCell(1).setColor("808080");
					// tableRowOne1.getCell(2).setColor("808080");
					// tableRowOne1.getCell(3).setColor("808080");
					// tableRowOne1.getCell(4).setColor("808080");
					// tableRowOne1.getCell(5).setColor("808080");
				}

				/////

				tableRowOne1vms = null;
				XWPFTableRow tableRowOne = table.createRow();
				tableRowOne.setCantSplitRow(true);

				// tableRowOne.getCell(0).setText(String.valueOf(orderby));
				// tableRowOne.getCell(1).setText(tagnm);
				// tableRowOne.getCell(2).setText(equipment);
				// tableRowOne.getCell(3).setText(application);
				// tableRowOne.getCell(4).setText(location);
				// tableRowOne.getCell(5).setText(String.valueOf(quant));
				paragraphtable = tableRowOne.getCell(0).getParagraphs().get(0);

				paragraphtable.setAlignment(ParagraphAlignment.CENTER);
				runtable = paragraphtable.createRun();
				runtable.setFontSize(11);
				// runtable.setFontFamily("Cambria (Headings)");
				runtable.setText(String.valueOf(countevms));
				if (tagnm.contains("\n")) {

					String[] arrOfStr = tagnm.split("\n", 20);
					paragraphtable = tableRowOne.getCell(1).getParagraphs().get(0);

					paragraphtable.setAlignment(ParagraphAlignment.CENTER);
					runtable = paragraphtable.createRun();
					runtable.setFontSize(11);
					for (int x1 = 0; x1 < arrOfStr.length; x1++) {

						// runtable.setFontFamily("Cambria (Headings)");
						runtable.setText(arrOfStr[x1]);
						if (x1 + 1 != arrOfStr.length) {
							runtable.addBreak();
						}
					}
				}

				else {
					paragraphtable = tableRowOne.getCell(1).getParagraphs().get(0);

					paragraphtable.setAlignment(ParagraphAlignment.CENTER);
					runtable = paragraphtable.createRun();
					runtable.setFontSize(11);
					// runtable.setFontFamily("Cambria (Headings)");
					runtable.setText(tagnm);
				}
				if (equipment.contains("\n")) {

					String[] arrOfStr = equipment.split("\n", 20);
					paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);

					runtable = paragraphtable.createRun();
					runtable.setFontSize(11);
					for (int x1 = 0; x1 < arrOfStr.length; x1++) {

						// runtable.setFontFamily("Cambria (Headings)");
						runtable.setText(arrOfStr[x1]);
						if (x1 + 1 != arrOfStr.length) {
							runtable.addBreak();
						}
					}
				}

				else {
					paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);

					runtable = paragraphtable.createRun();
					runtable.setFontSize(11);
					// runtable.setFontFamily("Cambria (Headings)");
					runtable.setText(equipment);
				}
				if (application.contains("\n")) {

					String[] arrOfStr = application.split("\n", 20);
					paragraphtable = tableRowOne.getCell(3).getParagraphs().get(0);

					runtable = paragraphtable.createRun();
					runtable.setFontSize(11);
					for (int x1 = 0; x1 < arrOfStr.length; x1++) {

						// runtable.setFontFamily("Cambria (Headings)");
						runtable.setText(arrOfStr[x1]);
						if (x1 + 1 != arrOfStr.length) {
							runtable.addBreak();
						}
					}
				}

				else {
					paragraphtable = tableRowOne.getCell(3).getParagraphs().get(0);

					runtable = paragraphtable.createRun();
					runtable.setFontSize(11);
					// runtable.setFontFamily("Cambria (Headings)");
					runtable.setText(application);
				}

				if (location.contains("\n")) {

					String[] arrOfStr = location.split("\n", 20);
					paragraphtable = tableRowOne.getCell(4).getParagraphs().get(0);
					paragraphtable.setAlignment(ParagraphAlignment.CENTER);

					runtable = paragraphtable.createRun();
					runtable.setFontSize(11);
					for (int x1 = 0; x1 < arrOfStr.length; x1++) {

						runtable.setText(arrOfStr[x1]);
						if (x1 + 1 != arrOfStr.length) {
							runtable.addBreak();
						}
					}
				}

				else {
					paragraphtable = tableRowOne.getCell(4).getParagraphs().get(0);
					paragraphtable.setAlignment(ParagraphAlignment.CENTER);

					runtable = paragraphtable.createRun();
					runtable.setFontSize(11);
					runtable.setText(location);
				}
				paragraphtable = tableRowOne.getCell(5).getParagraphs().get(0);

				paragraphtable.setAlignment(ParagraphAlignment.CENTER);
				runtable = paragraphtable.createRun();
				runtable.setFontSize(11);
				runtable.setText(String.valueOf(quant));
			}

			else {
				XWPFTableRow tableRowOne = table.createRow();
				tableRowOne.setCantSplitRow(true);

				// tableRowOne.getCell(0).setText(String.valueOf(orderby));
				// tableRowOne.getCell(1).setText(tagnm);
				// tableRowOne.getCell(2).setText(equipment);
				// tableRowOne.getCell(3).setText(application);
				// tableRowOne.getCell(4).setText(location);
				// tableRowOne.getCell(5).setText(String.valueOf(quant));
				paragraphtable = tableRowOne.getCell(0).getParagraphs().get(0);

				paragraphtable.setAlignment(ParagraphAlignment.CENTER);
				runtable = paragraphtable.createRun();
				runtable.setFontSize(11);
				// runtable.setFontFamily("Cambria (Headings)");
				runtable.setText(String.valueOf(countevms));
				if (tagnm.contains("\n")) {

					String[] arrOfStr = tagnm.split("\n", 20);
					paragraphtable = tableRowOne.getCell(1).getParagraphs().get(0);

					paragraphtable.setAlignment(ParagraphAlignment.CENTER);
					runtable = paragraphtable.createRun();
					runtable.setFontSize(11);
					for (int x1 = 0; x1 < arrOfStr.length; x1++) {

						// runtable.setFontFamily("Cambria (Headings)");
						runtable.setText(arrOfStr[x1]);
						if (x1 + 1 != arrOfStr.length) {
							runtable.addBreak();
						}
					}
				}

				else {
					paragraphtable = tableRowOne.getCell(1).getParagraphs().get(0);

					paragraphtable.setAlignment(ParagraphAlignment.CENTER);
					runtable = paragraphtable.createRun();
					runtable.setFontSize(11);
					// runtable.setFontFamily("Cambria (Headings)");
					runtable.setText(tagnm);
				}
				if (equipment.contains("\n")) {

					String[] arrOfStr = equipment.split("\n", 20);
					paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);

					runtable = paragraphtable.createRun();
					runtable.setFontSize(11);
					for (int x1 = 0; x1 < arrOfStr.length; x1++) {

						// runtable.setFontFamily("Cambria (Headings)");
						runtable.setText(arrOfStr[x1]);
						if (x1 + 1 != arrOfStr.length) {
							runtable.addBreak();
						}
					}
				}

				else {
					paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);

					runtable = paragraphtable.createRun();
					runtable.setFontSize(11);
					// runtable.setFontFamily("Cambria (Headings)");
					runtable.setText(equipment);
				}
				if (application.contains("\n")) {

					String[] arrOfStr = application.split("\n", 20);
					paragraphtable = tableRowOne.getCell(3).getParagraphs().get(0);

					runtable = paragraphtable.createRun();
					runtable.setFontSize(11);
					for (int x1 = 0; x1 < arrOfStr.length; x1++) {

						// runtable.setFontFamily("Cambria (Headings)");
						runtable.setText(arrOfStr[x1]);
						if (x1 + 1 != arrOfStr.length) {
							runtable.addBreak();
						}
					}
				}

				else {
					paragraphtable = tableRowOne.getCell(3).getParagraphs().get(0);

					runtable = paragraphtable.createRun();
					runtable.setFontSize(11);
					// runtable.setFontFamily("Cambria (Headings)");
					runtable.setText(application);
				}

				if (location.contains("\n")) {

					String[] arrOfStr = location.split("\n", 20);
					paragraphtable = tableRowOne.getCell(4).getParagraphs().get(0);
					paragraphtable.setAlignment(ParagraphAlignment.CENTER);

					runtable = paragraphtable.createRun();
					runtable.setFontSize(11);
					for (int x1 = 0; x1 < arrOfStr.length; x1++) {

						runtable.setText(arrOfStr[x1]);
						if (x1 + 1 != arrOfStr.length) {
							runtable.addBreak();
						}
					}
				}

				else {
					paragraphtable = tableRowOne.getCell(4).getParagraphs().get(0);
					paragraphtable.setAlignment(ParagraphAlignment.CENTER);

					runtable = paragraphtable.createRun();
					runtable.setFontSize(11);
					runtable.setText(location);
				}
				paragraphtable = tableRowOne.getCell(5).getParagraphs().get(0);

				paragraphtable.setAlignment(ParagraphAlignment.CENTER);
				runtable = paragraphtable.createRun();
				runtable.setFontSize(11);
				runtable.setText(String.valueOf(quant));

			}

			prevaddprbflag = addprbflag;
			prevvmsnm = vmsnm;
			prevheadertextvms = headertext;

		}
		logger.info("vmsntes");
		logger.info(vmsNotes);

		if (vmsNotes != null) {
			logger.info("vmsntes1");

			if (!vmsNotes.equalsIgnoreCase("NULL")) {
				paragraph = document.createParagraph();
				run = paragraph.createRun();
				if (vmsNotes.contains("\n")) {

					String[] arrOfStr = vmsNotes.split("\n", 20);
					for (int x1 = 0; x1 < arrOfStr.length; x1++) {
						XWPFParagraph paragraph21 = document.createParagraph();

						// System.out.println(table.getNumberOfRows());
						// paragraph21.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run123 = paragraph21.createRun();
						run123.setFontSize(11);
						run123.setFontFamily("Cambria (Headings)");
						run123.setText(arrOfStr[x1]);
						logger.info("rhs word start");
						logger.info(arrOfStr[x1]);
						logger.info("rhs word end");
					}
				} else {
					XWPFParagraph paragraph21 = document.createParagraph();
					XWPFRun run123 = paragraph21.createRun();
					run123.setFontSize(11);
					// run123.setFontFamily("Cambria (Headings)");
					run123.setText(vmsNotes);

				}
			}
		}
		if (vmsRemarks != null) {

			if ((!vmsRemarks.equals("NULL"))) {
				if (vmsRemarks.contains("\n")) {

					String[] arrOfStr = vmsRemarks.split("\n", 20);
					for (int x1 = 0; x1 < arrOfStr.length; x1++) {
						XWPFParagraph paragraph21 = document.createParagraph();

						// System.out.println(table.getNumberOfRows());
						// paragraph21.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run123 = paragraph21.createRun();
						run123.setFontSize(11);
						// run123.setFontFamily("Cambria (Headings)");
						run123.setText(arrOfStr[x1]);
						logger.info("rhs word start");
						logger.info(arrOfStr[x1]);
						logger.info("rhs word end");
					}
				} else {

					XWPFParagraph paragraph21 = document.createParagraph();

					// System.out.println(table.getNumberOfRows());
					// paragraph21.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun run123 = paragraph21.createRun();
					run123.setFontSize(11);
					// run123.setFontFamily("Cambria (Headings)");
					run123.setText(vmsRemarks);
				}

			}
		}
		// vms end

		// stg table start
		int y = 0;

		String previnstrnm = null;
		String prevheadertext = null;
		XWPFTableRow tableRowOne1 = null;
		int counter = 0;
		int count5 = 0;
		int count51 = 0;
		String previtemnm34 = null;
		int countstg = 0;

		for (y = q; y < reportBean.getWordData().size(); y++)

		{
			logger.info("instruments");

			counter = counter + 1;
			int id = 0;
			String instrcd = null;
			String instrnm = null;
			String instrtypenm = null;
			String instrdesc = null;
			String instrmounting = null;
			int qtylogic = 0;

			int nooftable = 0;
			String headertext = null;
			String footertext = null;
			int itemid = 0;
			int addinstrid = 0;
			String addinstrcd = null;
			String addinstrnm = null;
			String itemnm = null;
			int count34 = 0;

			if (y == QuotationDaoImpl.size6 && count51 > 0) {
				if (reportBean.getWordData().get(y - 1).getNoOfTable() > 1 && counter > 1) {
					int count2 = 0;

					for (int y1 = 1; y1 < reportBean.getWordData().get(y - 1).getNoOfTable(); y1++) {

						int d = document.getTables().size();
						if (count2 == 0) {

							XWPFParagraph paragraph2221 = document.createParagraph();

							run = paragraph2221.createRun();
							run.setText("");
						}
						count2 = count2 + 1;
						paragraph = document.createParagraph();

						run = paragraph.createRun();

						run.setText(reportBean.getWordData().get(y - 1).getInstrNm() + "-" + (y1 + 1));
						run.setBold(true);
						CTTbl ctTbl = CTTbl.Factory.newInstance(); // Create a
																	// new CTTbl
																	// for the
																	// new table
						ctTbl.set(table.getCTTbl()); // Copy the template
														// table's CTTbl
						XWPFTable new_table = new XWPFTable(ctTbl, document); // Cre
						document.createTable();
						document.createParagraph();
						document.setTable(d, new_table);

					}
				}

			}

			if (y == QuotationDaoImpl.size6)
				break;
			if (!reportBean.getWordData().get(y).getInstrNm().equals("Additional Instruments"))

			{
				id = reportBean.getWordData().get(y).getId();
				instrcd = reportBean.getWordData().get(y).getInstrCode();
				instrnm = reportBean.getWordData().get(y).getInstrNm();
				instrtypenm = reportBean.getWordData().get(y).getInstrTypeNm();
				instrdesc = reportBean.getWordData().get(y).getInstrDesc();
				instrmounting = reportBean.getWordData().get(y).getInstrMounting();
				qtylogic = reportBean.getWordData().get(y).getQuantityLogic();

				nooftable = reportBean.getWordData().get(y).getNoOfTable();
				headertext = reportBean.getWordData().get(y).getHeaderText();
				footertext = reportBean.getWordData().get(y).getFooterText();
				itemid = reportBean.getWordData().get(y).getItemId();
				addinstrid = reportBean.getWordData().get(y).getAddInstrId();
				addinstrcd = reportBean.getWordData().get(y).getAddInstrCd();
				addinstrnm = reportBean.getWordData().get(y).getAddInstrNm();
				itemnm = reportBean.getWordData().get(y).getItemNm();

				if (!itemnm.equals(previtemnm34)) {
					paragraph = document.createParagraph();

					run = paragraph.createRun();
					run.setText("");

					paragraph = document.createParagraph();

					run = paragraph.createRun();

					run.setText(itemnm);
					run.setFontFamily("Cambria (Headings)");
					run.setFontSize(12);
					run.setBold(true);
				}

				// to print instr name only once

				if (!instrnm.equals(previnstrnm)) {
					if (reportBean.getWordData().get(y - 1).getNoOfTable() > 1 && counter > 1) {
						count34 = 1;
						int count2 = 0;
						for (int y1 = 1; y1 < reportBean.getWordData().get(y - 1).getNoOfTable(); y1++) {

							int d = document.getTables().size();
							if (count2 == 0) {

								XWPFParagraph paragraph2221 = document.createParagraph();
								run = paragraph2221.createRun();
								run.setText("");

							}
							count2 = count2 + 1;

							paragraph = document.createParagraph();

							run = paragraph.createRun();
							run.setText(previnstrnm + "-" + (y1 + 1));
							run.setBold(true);
							run.setFontFamily("Cambria (Headings)");
							run.setFontSize(11);
							CTTbl ctTbl = CTTbl.Factory.newInstance(); // Create
																		// a new
																		// CTTbl
																		// for
																		// the
																		// new
																		// table
							ctTbl.set(table.getCTTbl()); // Copy the template
															// table's CTTbl
							XWPFTable new_table = new XWPFTable(ctTbl, document); // Cre
							document.createTable();
							document.createParagraph();
							document.setTable(d, new_table);

						}
					}

					if (count51 > 0 && count34 == 0) {
						count34 = 0;
						XWPFParagraph paragraph222 = document.createParagraph();
						run = paragraph222.createRun();
						run.setText("");
					}

					count51 = count51 + 1;

					if (reportBean.getWordData().get(y).getNoOfTable() > 1) {
						paragraph = document.createParagraph();
						run = paragraph.createRun();
						run.setText(reportBean.getWordData().get(y).getInstrNm() + "-1");
						run.setBold(true);
						run.setFontFamily("Cambria (Headings)");
						run.setFontSize(11);
					} else {
						if (reportBean.getWordData().get(y).getInstrNm().equalsIgnoreCase("STG Instruments")) {

						} else {
							paragraph = document.createParagraph();
							run = paragraph.createRun();
							run.setText(reportBean.getWordData().get(y).getInstrNm());
							run.setBold(true);
							run.setFontFamily("Cambria (Headings)");
							run.setFontSize(11);
						}
					}

					if (headertext != null) {
						if (!headertext.equals(prevheadertext)) {
							paragraph = document.createParagraph();
							run = paragraph.createRun();
							run.setText(reportBean.getWordData().get(y).getHeaderText());
							// run.setText(":");
							run.setBold(true);
							// run.setFontFamily("Cambria (Headings)");
							run.setFontSize(11);

						}
					}

					if (!instrnm.equals("Additional Instruments")) {
						countstg = 0;
						table = document.createTable();
						table.setCellMargins(200, 100, 100, 100);

						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1100));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(2500));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(3500));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(2000));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1100));

						tableRowOne1 = table.getRow(0);
					}

				}

				// To print header text only once

				// create table with rows and columns

				// write to first row, first column
				if (!instrnm.equals("Additional Instruments")) {

					if (tableRowOne1 != null) {
						countstg = countstg + 1;
						;

						// tableRowOne1.getCell(0).setText("SL.NO.");
						// tableRowOne1.addNewTableCell().setText("INST. TYPE
						// ");
						// tableRowOne1.addNewTableCell().setText("INSTRUMENT
						// DESCRIPTION");
						// tableRowOne1.addNewTableCell().setText("INSTRUMENT
						// MOUNTING");
						// tableRowOne1.addNewTableCell().setText("Quantity");
						XWPFParagraph pa451 = tableRowOne1.getCell(0).getParagraphs().get(0);

						// System.out.println(table.getNumberOfRows());
						pa451.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun ru311 = pa451.createRun();
						ru311.setFontSize(11);
						ru311.setFontFamily("Cambria (Headings)");
						ru311.setText("Sl.no");
						ru311.setBold(true);
						////
						tableRowOne1.addNewTableCell();
						XWPFParagraph pa2 = tableRowOne1.getCell(1).getParagraphs().get(0);
						pa2.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun ru2 = pa2.createRun();
						ru2.setFontSize(11);
						ru2.setFontFamily("Cambria (Headings)");
						ru2.setText("Inst. Type");
						ru2.setBold(true);
						////
						////
						tableRowOne1.addNewTableCell();
						XWPFParagraph pa3 = tableRowOne1.getCell(2).getParagraphs().get(0);
						pa3.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun ru3 = pa3.createRun();
						ru3.setFontSize(11);
						ru3.setFontFamily("Cambria (Headings)");
						ru3.setText("Instrument Description");
						ru3.setBold(true);
						////
						////
						tableRowOne1.addNewTableCell();
						XWPFParagraph pa4 = tableRowOne1.getCell(3).getParagraphs().get(0);
						pa4.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun ru4 = pa4.createRun();
						ru4.setFontSize(11);
						ru4.setFontFamily("Cambria (Headings)");
						ru4.setText("Instrument Mounting");
						ru4.setBold(true);
						////
						////
						tableRowOne1.addNewTableCell();
						XWPFParagraph pa5 = tableRowOne1.getCell(4).getParagraphs().get(0);
						pa5.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun ru5 = pa5.createRun();
						ru5.setFontSize(11);
						ru5.setFontFamily("Cambria (Headings)");
						ru5.setText("Quantity");
						ru5.setBold(true);

						// tableRowOne1.addNewTableCell().setText("Quantity (
						// for 1oo1 Logic)");
						// tableRowOne1.getCell(0).setColor("808080");
						// tableRowOne1.getCell(1).setColor("808080");
						// tableRowOne1.getCell(2).setColor("808080");
						// tableRowOne1.getCell(3).setColor("808080");
						// tableRowOne1.getCell(4).setColor("808080");

						// tableRowOne1.getCell(4).setColor("808080");
						tableRowOne1 = null;
						XWPFTableRow tableRowOne = table.createRow();
						tableRowOne.setCantSplitRow(true);

						// tableRowOne.getCell(0).setText(String.valueOf(id));
						// tableRowOne.getCell(1).setText(instrtypenm);
						// tableRowOne.getCell(2).setText(instrdesc);
						// tableRowOne.getCell(3).setText(instrmounting);
						// tableRowOne.getCell(4).setText(String.valueOf(qtylogic));
						paragraphtable = tableRowOne.getCell(0).getParagraphs().get(0);

						paragraphtable.setAlignment(ParagraphAlignment.CENTER);
						runtable = paragraphtable.createRun();
						runtable.setFontSize(11);
						// runtable.setFontFamily("Cambria (Headings)");
						runtable.setText(String.valueOf(countstg));
						if (instrtypenm.contains("\n")) {

							String[] arrOfStr = instrtypenm.split("\n", 20);
							paragraphtable = tableRowOne.getCell(1).getParagraphs().get(0);

							runtable = paragraphtable.createRun();
							runtable.setFontSize(11);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {

								// runtable.setFontFamily("Cambria (Headings)");
								runtable.setText(arrOfStr[x1]);
								if (x1 + 1 != arrOfStr.length) {
									runtable.addBreak();
								}
							}
						}

						else {
							paragraphtable = tableRowOne.getCell(1).getParagraphs().get(0);

							runtable = paragraphtable.createRun();
							runtable.setFontSize(11);
							// runtable.setFontFamily("Cambria (Headings)");
							runtable.setText(instrtypenm);
						}
						if (instrdesc.contains("\n")) {

							String[] arrOfStr = instrdesc.split("\n", 20);
							paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);

							runtable = paragraphtable.createRun();
							runtable.setFontSize(11);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {

								// runtable.setFontFamily("Cambria (Headings)");
								runtable.setText(arrOfStr[x1]);
								if (x1 + 1 != arrOfStr.length) {
									runtable.addBreak();
								}
							}
						}

						else {
							paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);

							runtable = paragraphtable.createRun();
							runtable.setFontSize(11);
							// runtable.setFontFamily("Cambria (Headings)");
							runtable.setText(instrdesc);
						}
						if (instrmounting.contains("\n")) {

							String[] arrOfStr = instrmounting.split("\n", 20);
							paragraphtable = tableRowOne.getCell(3).getParagraphs().get(0);

							paragraphtable.setAlignment(ParagraphAlignment.CENTER);
							runtable = paragraphtable.createRun();
							runtable.setFontSize(11);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {

								// runtable.setFontFamily("Cambria (Headings)");
								runtable.setText(arrOfStr[x1]);
								if (x1 + 1 != arrOfStr.length) {
									runtable.addBreak();
								}
							}
						}

						else {
							paragraphtable = tableRowOne.getCell(3).getParagraphs().get(0);

							paragraphtable.setAlignment(ParagraphAlignment.CENTER);
							runtable = paragraphtable.createRun();
							runtable.setFontSize(11);
							// runtable.setFontFamily("Cambria (Headings)");
							runtable.setText(instrmounting);
						}

						paragraphtable = tableRowOne.getCell(4).getParagraphs().get(0);

						paragraphtable.setAlignment(ParagraphAlignment.CENTER);
						runtable = paragraphtable.createRun();
						runtable.setFontSize(11);
						// runtable.setFontFamily("Cambria (Headings)");
						runtable.setText(String.valueOf(qtylogic));

					}

					else {
						XWPFTableRow tableRowOne = table.createRow();
						countstg = countstg + 1;
						;

						tableRowOne.setCantSplitRow(true);

						// tableRowOne.getCell(0).setText(String.valueOf(id));
						// tableRowOne.getCell(1).setText(instrtypenm);
						// tableRowOne.getCell(2).setText(instrdesc);
						// tableRowOne.getCell(3).setText(instrmounting);
						// tableRowOne.getCell(4).setText(String.valueOf(qtylogic));
						paragraphtable = tableRowOne.getCell(0).getParagraphs().get(0);

						paragraphtable.setAlignment(ParagraphAlignment.CENTER);
						runtable = paragraphtable.createRun();
						runtable.setFontSize(11);
						// runtable.setFontFamily("Cambria (Headings)");
						runtable.setText(String.valueOf(countstg));
						if (instrtypenm.contains("\n")) {

							String[] arrOfStr = instrtypenm.split("\n", 20);
							paragraphtable = tableRowOne.getCell(1).getParagraphs().get(0);

							runtable = paragraphtable.createRun();
							runtable.setFontSize(11);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {

								// runtable.setFontFamily("Cambria (Headings)");
								runtable.setText(arrOfStr[x1]);
								if (x1 + 1 != arrOfStr.length) {
									runtable.addBreak();
								}
							}
						}

						else {
							paragraphtable = tableRowOne.getCell(1).getParagraphs().get(0);

							runtable = paragraphtable.createRun();
							runtable.setFontSize(11);
							// runtable.setFontFamily("Cambria (Headings)");
							runtable.setText(instrtypenm);
						}
						if (instrdesc.contains("\n")) {

							String[] arrOfStr = instrdesc.split("\n", 20);
							paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);

							runtable = paragraphtable.createRun();
							runtable.setFontSize(11);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {

								// runtable.setFontFamily("Cambria (Headings)");
								runtable.setText(arrOfStr[x1]);
								if (x1 + 1 != arrOfStr.length) {
									runtable.addBreak();
								}
							}
						}

						else {
							paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);

							runtable = paragraphtable.createRun();
							runtable.setFontSize(11);
							// runtable.setFontFamily("Cambria (Headings)");
							runtable.setText(instrdesc);
						}
						if (instrmounting.contains("\n")) {

							String[] arrOfStr = instrmounting.split("\n", 20);
							paragraphtable = tableRowOne.getCell(3).getParagraphs().get(0);

							paragraphtable.setAlignment(ParagraphAlignment.CENTER);
							runtable = paragraphtable.createRun();
							runtable.setFontSize(11);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {

								// runtable.setFontFamily("Cambria (Headings)");
								runtable.setText(arrOfStr[x1]);
								if (x1 + 1 != arrOfStr.length) {
									runtable.addBreak();
								}
							}
						}

						else {
							paragraphtable = tableRowOne.getCell(3).getParagraphs().get(0);

							paragraphtable.setAlignment(ParagraphAlignment.CENTER);
							runtable = paragraphtable.createRun();
							runtable.setFontSize(11);
							// runtable.setFontFamily("Cambria (Headings)");
							runtable.setText(instrmounting);
						}

						paragraphtable = tableRowOne.getCell(4).getParagraphs().get(0);

						paragraphtable.setAlignment(ParagraphAlignment.CENTER);
						runtable = paragraphtable.createRun();
						runtable.setFontSize(11);
						// runtable.setFontFamily("Cambria (Headings)");
						runtable.setText(String.valueOf(qtylogic));

						// tableRowOne.getCell(4).setText(String.valueOf(qtylogic));

					}
				}

				if (!instrnm.equals(previnstrnm)) {

				}
				previnstrnm = instrnm;
				prevheadertext = headertext;
				previtemnm34 = itemnm;
			}
		}
		// stg table end

		// add instr start

		int d = 0;

		String previtemnm = null;
		int counteradd = 0;
		XWPFTableRow tableRowOne12 = null;
		int count8 = 0;
		int count4 = 0;
		int slno = 0;
		for (d = y; d < reportBean.getWordData().size(); d++)

		{
			if (d == QuotationDaoImpl.size12) {

				break;
			}
			if (reportBean.getWordData().get(d).getColId() != 0) {

				count4 = count4 + 1;
				logger.info("addition instruments");
				counteradd = counteradd + 1;
				int detquotid = 0;
				int itemid = 0;
				String itemnm = null;
				int qty = 0;
				int colid = 0;
				String colnm = null;
				String colvalcd = null;
				String subcolvalcd = null;
				int addonnewcolflag = 0;
				boolean rhsflag = false;
				float rhscolqty = 0;
				int dessubitmeorderid = 0;
				float rhscost = 0;
				float addinstrcost = 0;
				float totaladdinstrcost = 0;
				boolean itemflag = false;
				boolean othersflag = false;
				boolean addinstrflag = false;

				detquotid = reportBean.getWordData().get(d).getDetQuotId();
				itemid = reportBean.getWordData().get(d).getItemId();
				itemnm = reportBean.getWordData().get(d).getItemName();
				qty = reportBean.getWordData().get(d).getQty();
				colid = reportBean.getWordData().get(d).getColId();
				colnm = reportBean.getWordData().get(d).getColNm();
				colvalcd = reportBean.getWordData().get(d).getColValCd();
				subcolvalcd = reportBean.getWordData().get(d).getSubColValCode();
				addonnewcolflag = reportBean.getWordData().get(d).getAddOnNewColFlag();
				rhsflag = reportBean.getWordData().get(d).isRhsFlag();
				rhscolqty = reportBean.getWordData().get(d).getRhsColQuantity();
				dessubitmeorderid = reportBean.getWordData().get(d).getDesSubItemOrderId();
				rhscost = reportBean.getWordData().get(d).getRhsCost();
				addinstrcost = reportBean.getWordData().get(d).getAddInstrCost();
				totaladdinstrcost = reportBean.getWordData().get(d).getTotalAddInstrCost();
				itemflag = reportBean.getWordData().get(d).isItemFlag();
				othersflag = reportBean.getWordData().get(d).isOthersFlag();
				addinstrflag = reportBean.getWordData().get(d).isAddInstrFlag();

				// to print instr name only once
				if (count8 == 0) {

					paragraph = document.createParagraph();
					run = paragraph.createRun();

					run.setText("");
					paragraph = document.createParagraph();
					run = paragraph.createRun();

					run.setText("Additional Instruments");

					run.setBold(true);
					run.setFontFamily("Cambria (Headings)");
					run.setFontSize(11);
				}
				if (!itemnm.equals(previtemnm)) {
					if (count8 > 0) {
						paragraph = document.createParagraph();
						run = paragraph.createRun();

						run.setText("");
					}
					count8 = count8 + 1;
					paragraph = document.createParagraph();
					run = paragraph.createRun();

					run.setText(itemnm);

					run.setBold(true);
					run.setFontFamily("Cambria (Headings)");
					run.setFontSize(11);
					slno = 1;
					// if (headertext != null)
					// {
					// if (!headertext.equals(prevheadertext))
					// {
					// paragraph = document.createParagraph();
					// run = paragraph.createRun();
					// run.setText(reportBean.getWordData().get(y).getHeaderText());
					// run.setText(":");
					// run.setBold(true);
					// run.setFontFamily("Cambria (Headings)");
					// run.setFontSize(11);
					//
					// }
					// }

					table = document.createTable();
					table.setCellMargins(200, 100, 100, 100);
					CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
					type1.setType(STTblLayoutType.FIXED);
					table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1100));
					table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(2500));
					table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(3000));
					table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(2000));
					table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1200));

					tableRowOne12 = table.getRow(0);

				}

				// To print header text only once

				// create table with rows and columns

				// write to first row, first column
				// if(!instrnm.equals("Additional Instruments"))
				// {

				if (tableRowOne12 != null) {
					// tableRowOne12.getCell(0).setText("SL.NO.");
					// tableRowOne12.addNewTableCell();.setText("NAME");
					// tableRowOne12.addNewTableCell().setText("DESCRIPTION");
					// tableRowOne12.addNewTableCell().setText("MOUNTING");
					// tableRowOne12.addNewTableCell().setText("Quantity");
					XWPFParagraph pa451 = tableRowOne12.getCell(0).getParagraphs().get(0);

					// System.out.println(table.getNumberOfRows());
					pa451.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun ru311 = pa451.createRun();
					ru311.setFontSize(11);
					ru311.setFontFamily("Cambria (Headings)");
					ru311.setText("Sl.no");
					ru311.setBold(true);
					////
					tableRowOne12.addNewTableCell();
					XWPFParagraph pa2 = tableRowOne12.getCell(1).getParagraphs().get(0);
					pa2.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun ru2 = pa2.createRun();
					ru2.setFontSize(11);
					ru2.setFontFamily("Cambria (Headings)");
					ru2.setText("Name");
					ru2.setBold(true);
					////
					////
					tableRowOne12.addNewTableCell();
					XWPFParagraph pa3 = tableRowOne12.getCell(2).getParagraphs().get(0);
					pa3.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun ru3 = pa3.createRun();
					ru3.setFontSize(11);
					ru3.setFontFamily("Cambria (Headings)");
					ru3.setText("Description");
					ru3.setBold(true);
					////
					////
					tableRowOne12.addNewTableCell();
					XWPFParagraph pa4 = tableRowOne12.getCell(3).getParagraphs().get(0);
					pa4.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun ru4 = pa4.createRun();
					ru4.setFontSize(11);
					ru4.setFontFamily("Cambria (Headings)");
					ru4.setText("Mounting");
					ru4.setBold(true);
					////
					////
					tableRowOne12.addNewTableCell();
					XWPFParagraph pa5 = tableRowOne12.getCell(4).getParagraphs().get(0);
					pa5.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun ru5 = pa5.createRun();
					ru5.setFontSize(11);
					ru5.setFontFamily("Cambria (Headings)");
					ru5.setText("Quantity");
					ru5.setBold(true);
					////

					// tableRowOne12.getCell(0).setColor("808080");
					// tableRowOne12.getCell(1).setColor("808080");
					// tableRowOne12.getCell(2).setColor("808080");
					// tableRowOne12.getCell(3).setColor("808080");
					// tableRowOne12.getCell(4).setColor("808080");
					tableRowOne12 = null;
					XWPFTableRow tableRowOne = table.createRow();
					tableRowOne.setCantSplitRow(true);

					// tableRowOne.getCell(0).setText(String.valueOf(colid));
					// tableRowOne.getCell(1).setText(colnm);
					// tableRowOne.getCell(2).setText(colvalcd);
					// tableRowOne.getCell(3).setText(subcolvalcd);
					// tableRowOne.getCell(4).setText(String.valueOf(rhscolqty));
					paragraphtable = tableRowOne.getCell(0).getParagraphs().get(0);

					paragraphtable.setAlignment(ParagraphAlignment.CENTER);
					runtable = paragraphtable.createRun();
					runtable.setFontSize(11);
					// runtable.setFontFamily("Cambria (Headings)");
					runtable.setText(String.valueOf(slno));
					if (colnm.contains("\n")) {

						String[] arrOfStr = colnm.split("\n", 20);
						paragraphtable = tableRowOne.getCell(1).getParagraphs().get(0);

						runtable = paragraphtable.createRun();
						runtable.setFontSize(11);
						for (int x1 = 0; x1 < arrOfStr.length; x1++) {

							// runtable.setFontFamily("Cambria (Headings)");
							runtable.setText(arrOfStr[x1]);
							if (x1 + 1 != arrOfStr.length) {
								runtable.addBreak();
							}
						}
					}

					else {
						paragraphtable = tableRowOne.getCell(1).getParagraphs().get(0);

						runtable = paragraphtable.createRun();
						runtable.setFontSize(11);
						// runtable.setFontFamily("Cambria (Headings)");
						runtable.setText(colnm);
					}
					if (colvalcd.contains("\n")) {

						String[] arrOfStr = colvalcd.split("\n", 20);
						paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);

						runtable = paragraphtable.createRun();
						runtable.setFontSize(11);
						for (int x1 = 0; x1 < arrOfStr.length; x1++) {

							// runtable.setFontFamily("Cambria (Headings)");
							runtable.setText(arrOfStr[x1]);
							if (x1 + 1 != arrOfStr.length) {
								runtable.addBreak();
							}
						}
					}

					else {
						paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);

						runtable = paragraphtable.createRun();
						runtable.setFontSize(11);
						// runtable.setFontFamily("Cambria (Headings)");
						runtable.setText(colvalcd);
					}
					if (subcolvalcd.contains("\n")) {

						String[] arrOfStr = subcolvalcd.split("\n", 20);
						paragraphtable = tableRowOne.getCell(3).getParagraphs().get(0);

						paragraphtable.setAlignment(ParagraphAlignment.CENTER);
						runtable = paragraphtable.createRun();
						runtable.setFontSize(11);
						for (int x1 = 0; x1 < arrOfStr.length; x1++) {

							// runtable.setFontFamily("Cambria (Headings)");
							runtable.setText(arrOfStr[x1]);
							if (x1 + 1 != arrOfStr.length) {
								runtable.addBreak();
							}
						}
					}

					else {
						paragraphtable = tableRowOne.getCell(3).getParagraphs().get(0);

						paragraphtable.setAlignment(ParagraphAlignment.CENTER);
						runtable = paragraphtable.createRun();
						runtable.setFontSize(11);
						// runtable.setFontFamily("Cambria (Headings)");
						runtable.setText(subcolvalcd);
					}

					paragraphtable = tableRowOne.getCell(4).getParagraphs().get(0);

					paragraphtable.setAlignment(ParagraphAlignment.CENTER);
					runtable = paragraphtable.createRun();
					runtable.setFontSize(11);
					// runtable.setFontFamily("Cambria (Headings)");
					runtable.setText(String.valueOf(rhscolqty));

				}

				else {
					XWPFTableRow tableRowOne = table.createRow();
					tableRowOne.setCantSplitRow(true);

					// tableRowOne.getCell(0).setText(String.valueOf(colid));
					// tableRowOne.getCell(1).setText(colnm);
					// tableRowOne.getCell(2).setText(colvalcd);
					// tableRowOne.getCell(3).setText(subcolvalcd);
					// tableRowOne.getCell(4).setText(String.valueOf(rhscolqty));
					paragraphtable = tableRowOne.getCell(0).getParagraphs().get(0);

					paragraphtable.setAlignment(ParagraphAlignment.CENTER);
					runtable = paragraphtable.createRun();
					runtable.setFontSize(11);
					// runtable.setFontFamily("Cambria (Headings)");
					runtable.setText(String.valueOf(slno));
					if (colnm.contains("\n")) {

						String[] arrOfStr = colnm.split("\n", 20);
						paragraphtable = tableRowOne.getCell(1).getParagraphs().get(0);

						runtable = paragraphtable.createRun();
						runtable.setFontSize(11);
						for (int x1 = 0; x1 < arrOfStr.length; x1++) {

							// runtable.setFontFamily("Cambria (Headings)");
							runtable.setText(arrOfStr[x1]);
							if (x1 + 1 != arrOfStr.length) {
								runtable.addBreak();
							}
						}
					}

					else {
						paragraphtable = tableRowOne.getCell(1).getParagraphs().get(0);

						runtable = paragraphtable.createRun();
						runtable.setFontSize(11);
						// runtable.setFontFamily("Cambria (Headings)");
						runtable.setText(colnm);
					}
					if (colvalcd.contains("\n")) {

						String[] arrOfStr = colvalcd.split("\n", 20);
						paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);

						runtable = paragraphtable.createRun();
						runtable.setFontSize(11);
						for (int x1 = 0; x1 < arrOfStr.length; x1++) {

							// runtable.setFontFamily("Cambria (Headings)");
							runtable.setText(arrOfStr[x1]);
							if (x1 + 1 != arrOfStr.length) {
								runtable.addBreak();
							}
						}
					}

					else {
						paragraphtable = tableRowOne.getCell(2).getParagraphs().get(0);

						runtable = paragraphtable.createRun();
						runtable.setFontSize(11);
						// runtable.setFontFamily("Cambria (Headings)");
						runtable.setText(colvalcd);
					}
					if (subcolvalcd.contains("\n")) {

						String[] arrOfStr = subcolvalcd.split("\n", 20);
						paragraphtable = tableRowOne.getCell(3).getParagraphs().get(0);

						paragraphtable.setAlignment(ParagraphAlignment.CENTER);
						runtable = paragraphtable.createRun();
						runtable.setFontSize(11);
						for (int x1 = 0; x1 < arrOfStr.length; x1++) {

							// runtable.setFontFamily("Cambria (Headings)");
							runtable.setText(arrOfStr[x1]);
							if (x1 + 1 != arrOfStr.length) {
								runtable.addBreak();
							}
						}
					}

					else {
						paragraphtable = tableRowOne.getCell(3).getParagraphs().get(0);

						paragraphtable.setAlignment(ParagraphAlignment.CENTER);
						runtable = paragraphtable.createRun();
						runtable.setFontSize(11);
						// runtable.setFontFamily("Cambria (Headings)");
						runtable.setText(subcolvalcd);
					}

					paragraphtable = tableRowOne.getCell(4).getParagraphs().get(0);

					paragraphtable.setAlignment(ParagraphAlignment.CENTER);
					runtable = paragraphtable.createRun();
					runtable.setFontSize(11);
					// runtable.setFontFamily("Cambria (Headings)");
					runtable.setText(String.valueOf(rhscolqty));

				}
				// }
				slno = slno + 1;
				previtemnm = itemnm;
				// prevheadertext = headertext;
			}
		}

		// add instr end

		// ci aux start
		// e,10

		// ci ext starts

		// performance

		addWordContent1(document, quotationForm, reportBean, d);
	}

	public static void addWordContent1(XWPFDocument document, QuotationForm quotationForm, ReportBean reportBean, int d)
			throws InvalidFormatException, IOException {

		XWPFParagraph paragraph = null;
		XWPFRun run = null;
		XWPFTable table = null;
		int e = 0;
		int prevqtyciaux = 0;
		String prevcategoryele11 = null;
		String prevsubcategoryele11 = null;
		String previtemnameele11 = null;
		String prevdesitemnameele11 = null;
		String prevdessubitemnameele11 = null;
		String previtemcodeele11 = null;
		String prevtechremarksele11 = null;
		String prevheadertextele11 = null;
		String prevfootertextele11 = null;
		int desitemorderid = 0;
		int counter411 = 0;
		int count111 = 0;
		int count11 = 0;
		int count = 0;

		for (e = d; e < reportBean.getWordData().size(); e++)

		{
			logger.info("CIAUX");
			int qtyciaux = 0;
			String space = null;
			String categorynameele11 = null;
			String subcategorynameele11 = null;
			int itemidele11 = 0;
			String itemnameele11 = null;
			String desitemnameele11 = null;
			String dessubitemnameele11 = null;
			String itemcodeele11 = null;
			String subitemcodeele11 = null;
			String subitemnameele11 = null;

			int qunatityele11 = 0;
			String techremarksele11 = null;
			String techcommentsele11 = null;
			int numberofsubitemsele11 = 0;
			int orderidele11 = 0;
			int dessubitemorderidele11 = 0;
			String headertextele11 = null;
			String footertextele11 = null;
			String subColValCodeele11 = null;

			String descolorderidele11 = null;
			int linkFlag11 = 0;

			XWPFTableRow tableRowOne111 = null;

			if (e == QuotationDaoImpl.size10) {
				if (counter411 > 0) {
					if ((!reportBean.getWordData().get(e - 1).getFooterText().equals("NULL"))
							&& (reportBean.getWordData().get(e - 1).getFooterText() != null)) {
						if (reportBean.getWordData().get(e - 1).getFooterText().contains("\n")) {

							String[] arrOfStr = reportBean.getWordData().get(e - 1).getFooterText().split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = document.createParagraph();

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(arrOfStr[x1]);
								logger.info("rhs word start");
								logger.info(arrOfStr[x1]);
								logger.info("rhs word end");
							}
						}

						else {

							XWPFParagraph paragraph21 = document.createParagraph();

							// System.out.println(table.getNumberOfRows());
							// paragraph21.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run123 = paragraph21.createRun();
							run123.setFontSize(11);
							// run123.setFontFamily("Cambria (Headings)");
							run123.setText(reportBean.getWordData().get(e - 1).getFooterText());
						}
					}
					if (reportBean.getWordData().get(e - 1).getTechRemarks() != null) {

						if ((!reportBean.getWordData().get(e - 1).getTechRemarks().equals("NULL"))) {
							if (reportBean.getWordData().get(e - 1).getTechRemarks().contains("\n")) {

								String[] arrOfStr = reportBean.getWordData().get(e - 1).getTechRemarks().split("\n",
										20);
								for (int x1 = 0; x1 < arrOfStr.length; x1++) {
									XWPFParagraph paragraph21 = document.createParagraph();

									// System.out.println(table.getNumberOfRows());
									// paragraph21.setAlignment(ParagraphAlignment.CENTER);
									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									// run123.setFontFamily("Cambria
									// (Headings)");
									run123.setText(arrOfStr[x1]);
									logger.info("rhs word start");
									logger.info(arrOfStr[x1]);
									logger.info("rhs word end");
								}
							} else {

								XWPFParagraph paragraph21 = document.createParagraph();

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(reportBean.getWordData().get(e - 1).getTechRemarks());
							}
						}
					}
				}
				break;

			}
			categorynameele11 = reportBean.getWordData().get(e).getCategoryName();
			subcategorynameele11 = reportBean.getWordData().get(e).getItemName();
			itemidele11 = reportBean.getWordData().get(e).getItemId();
			itemnameele11 = reportBean.getWordData().get(e).getSubItemName();
			qtyciaux = reportBean.getWordData().get(e).getItemQuantity();
			desitemnameele11 = reportBean.getWordData().get(e).getDesItemName();
			dessubitemnameele11 = reportBean.getWordData().get(e).getDesSubItemName();
			itemcodeele11 = reportBean.getWordData().get(e).getItemCode();
			subitemcodeele11 = reportBean.getWordData().get(e).getSubItemCode();
			subitemnameele11 = reportBean.getWordData().get(e).getSubItemName();
			qunatityele11 = reportBean.getWordData().get(e).getQuantityEle();
			techremarksele11 = reportBean.getWordData().get(e).getTechRemarks();
			techcommentsele11 = reportBean.getWordData().get(e).getTechComments();
			numberofsubitemsele11 = reportBean.getWordData().get(e).getNumberOfSubItems();
			orderidele11 = reportBean.getWordData().get(e).getOrderId();
			dessubitemorderidele11 = reportBean.getWordData().get(e).getDesSubItemOrderId();
			descolorderidele11 = reportBean.getWordData().get(e).getDesColOrderId();
			subColValCodeele11 = reportBean.getWordData().get(e).getSubColValCode();
			headertextele11 = reportBean.getWordData().get(e).getHeaderText();
			footertextele11 = reportBean.getWordData().get(e).getFooterText();
			linkFlag11 = reportBean.getWordData().get(e).getLinkFlag();
			desitemorderid = reportBean.getWordData().get(e).getDesItemOrderId();
			space = reportBean.getWordData().get(e).getSpaceF2f();
			int counter311 = 0;

			int[] cols11 = { 4896, 1872, 4032, 1728, 1440 };
			//
			// This is to print the technical remarks at the end of the item
			if (!itemcodeele11.equals(previtemcodeele11) && itemcodeele11 != null) {
				if (techremarksele11 != null) {
					if (!techremarksele11.equals(prevtechremarksele11)) {

						// paragraph = document.createParagraph();
						// run = paragraph.createRun();
						// run.setText(prevtechremarksele11);
						// run.addBreak();
						// run.addBreak();
						// run.setFontFamily("Cambria (Headings)");
						// run.setFontSize(11);
					} // if (!techremarksele.equals(prevtechremarksele))
				} // if (techremarksele != null)
			} // if (!itemcodeele.equals(previtemcodeele) && itemcodeele !=
				// null)

			itemnameele11 = reportBean.getWordData().get(e).getSubItemName();
			desitemnameele11 = reportBean.getWordData().get(e).getDesItemName();

			// to print subcategory name
			if (!subcategorynameele11.equals(prevsubcategoryele11)) {
				paragraph = document.createParagraph();
				run = paragraph.createRun();

				run.setText("");
				paragraph = document.createParagraph();
				run = paragraph.createRun();

				run.setText(subcategorynameele11);
				run.setBold(true);
				run.setFontFamily("Cambria (Headings)");
				run.setFontSize(14);
				paragraph.setStyle("heading 1");

			}

			// to print item name only once
			if (!itemnameele11.equals(previtemnameele11)) {

				counter411 = counter411 + 1;
				if (counter411 > 1) {
					if ((prevfootertextele11 != null) && !(prevfootertextele11.equals("NULL"))
							&& !(prevfootertextele11.equals("NULL:"))) {
						if (!footertextele11.equals(prevfootertextele11)) {
							if (prevfootertextele11.contains("\n")) {

								String[] arrOfStr = prevfootertextele11.split("\n", 20);
								for (int x1 = 0; x1 < arrOfStr.length; x1++) {
									XWPFParagraph paragraph21 = document.createParagraph();

									// System.out.println(table.getNumberOfRows());
									// paragraph21.setAlignment(ParagraphAlignment.CENTER);
									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									// run123.setFontFamily("Cambria
									// (Headings)");
									run123.setText(arrOfStr[x1]);
									logger.info("rhs word start");
									logger.info(arrOfStr[x1]);
									logger.info("rhs word end");
								}
							} else {

								XWPFParagraph paragraph21 = document.createParagraph();

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(prevfootertextele11);
							}
						}
					}
				}

				paragraph = document.createParagraph();
				run = paragraph.createRun();

				run.setText(itemnameele11);
				if ((qtyciaux) != prevqtyciaux) {
					if (reportBean.getWordData().get(e).getItemQuantity() > 1) {
						run.setText(space + space + space + space + space + space + space + space + space + space
								+ space + space + space + space + space + space + space + space + space + space + space
								+ space + space + space + space
								+ String.valueOf("Quantity :" + reportBean.getWordData().get(e).getItemQuantity()));
					}
				}
				run.setBold(true);
				run.setFontFamily("Cambria (Headings)");
				run.setFontSize(13);

				// table = document.createTable();
				//
				// CTTblLayoutType type =
				// table.getCTTbl().getTblPr().addNewTblLayout();
				// type.setType(STTblLayoutType.FIXED);
				// CTTblPr tblpro = table.getCTTbl().getTblPr();
				//
				// CTTblBorders borders = tblpro.addNewTblBorders();
				// borders.addNewBottom().setVal(STBorder.NONE);
				// borders.addNewLeft().setVal(STBorder.NONE);
				// borders.addNewRight().setVal(STBorder.NONE);
				// borders.addNewTop().setVal(STBorder.NONE);
				// // also inner borders
				// borders.addNewInsideH().setVal(STBorder.NONE);
				// borders.addNewInsideV().setVal(STBorder.NONE);
				// table.setWidth(2000);
				// CTTblWidth width =
				// table.getCTTbl().addNewTblPr().addNewTblW();
				// width.setType(STTblWidth.DXA);
				// width.setW(BigInteger.valueOf(9072));
				// CTTblPr tblpro = table.getCTTbl().getTblPr();
				//
				// CTTblBorders borders = tblpro.addNewTblBorders();
				// borders.addNewBottom().setVal(STBorder.SINGLE);
				// borders.addNewLeft().setVal(STBorder.SINGLE);
				// borders.addNewRight().setVal(STBorder.SINGLE);
				// borders.addNewTop().setVal(STBorder.SINGLE);
				// //also inner borders
				// borders.addNewInsideH().setVal(STBorder.SINGLE);
				// borders.addNewInsideV().setVal(STBorder.SINGLE);
				// setTableAlign(table, ParagraphAlignment.LEFT);// Creating
				// // table
				// // only once
				// table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
				// table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(2000));
				// table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));

				// tableRowOne1 = table.getRow(0);
			} // if (!itemnameele.equals(previtemnameele))

			if (desitemnameele11 != null) {
				if (!desitemnameele11.equals(prevdesitemnameele11)) {
					count11 = 0;
					paragraph = document.createParagraph();
					run = paragraph.createRun();
					run.setText(String.valueOf(desitemorderid));
					run.setText("   ");
					run.setText(desitemnameele11);
					run.setBold(true);
					run.setFontFamily("Cambria (Headings)");
					run.setFontSize(11);

					if ((headertextele11 != null) && !headertextele11.equals("NULL")) {
						if (!headertextele11.equals(prevheadertextele11)) {
							XWPFParagraph paragraph22 = document.createParagraph();
							XWPFRun run22 = paragraph22.createRun();
							run22.setText(headertextele11);
							// run.setText(":");

							// run.setFontFamily("Cambria (Headings)");
							run22.setFontSize(11);
						}
					}
					if (dessubitemnameele11 == null) {

						table = document.createTable();

						// table.setWidth(2000);
						CTTblLayoutType type = table.getCTTbl().getTblPr().addNewTblLayout();
						type.setType(STTblLayoutType.FIXED);
						// table.setInsideHBorder(XWPFBorderType.SINGLE, 8, 5,
						// "0000FF");
						// table.setInsideVBorder(XWPFBorderType.SINGLE, 8, 5,
						// "0000FF");

						// CTTblWidth width =
						// table.getCTTbl().addNewTblPr().addNewTblW();
						// width.setType(STTblWidth.DXA);
						// width.setW(BigInteger.valueOf(9072));
						if (desitemnameele11.equalsIgnoreCase(
								"Multifunctional digital generator protection relay shall have following protections features")) {
							CTTblPr tblpro = table.getCTTbl().getTblPr();

							CTTblBorders borders = tblpro.addNewTblBorders();
							borders.addNewBottom().setVal(STBorder.THICK);
							borders.addNewLeft().setVal(STBorder.THICK);
							borders.addNewRight().setVal(STBorder.THICK);
							borders.addNewTop().setVal(STBorder.THICK);

							// also inner borders
							borders.addNewInsideH().setVal(STBorder.THICK);
							borders.addNewInsideV().setVal(STBorder.THICK);
							borders.getInsideH().setSz(BigInteger.valueOf(12));
							borders.getInsideV().setSz(BigInteger.valueOf(12));
							borders.getTop().setSz(BigInteger.valueOf(12));
							borders.getBottom().setSz(BigInteger.valueOf(12));
							borders.getLeft().setSz(BigInteger.valueOf(12));
							borders.getRight().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getTop().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getLeft().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getRight().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getBottom().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getInsideH().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getInsideV().setSz(BigInteger.valueOf(12));
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(5000));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
						} else {
							CTTblPr tblpro = table.getCTTbl().getTblPr();

							CTTblBorders borders = tblpro.addNewTblBorders();
							borders.addNewBottom().setVal(STBorder.NONE);
							borders.addNewLeft().setVal(STBorder.NONE);
							borders.addNewRight().setVal(STBorder.NONE);
							borders.addNewTop().setVal(STBorder.NONE);
							// also inner borders
							borders.addNewInsideH().setVal(STBorder.NONE);
							borders.addNewInsideV().setVal(STBorder.NONE);
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));

						}

						setTableAlign(table, ParagraphAlignment.LEFT);// Creating
																		// table
																		// only
																		// once
						tableRowOne111 = table.getRow(0);
					}
				} // if (!subitemnameele.equals(prevdesitemnameele))
				else {
					if (!itemnameele11.equals(previtemnameele11)) {
						count11 = 0;
						paragraph = document.createParagraph();
						run = paragraph.createRun();
						run.setText(String.valueOf(desitemorderid));
						run.setText("   ");
						run.setText(desitemnameele11);
						run.setBold(true);
						run.setFontFamily("Cambria (Headings)");
						run.setFontSize(11);

						if ((headertextele11 != null) && !headertextele11.equals("NULL")) {
							if (!headertextele11.equals(prevheadertextele11)) {
								paragraph = document.createParagraph();
								run = paragraph.createRun();
								run.setText(headertextele11);
								// run.setText(":");

								// run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);
							}
						}
						if (dessubitemnameele11 == null) {
							table = document.createTable();

							// table.setWidth(2000);
							CTTblLayoutType type = table.getCTTbl().getTblPr().addNewTblLayout();
							type.setType(STTblLayoutType.FIXED);
							// table.setInsideHBorder(XWPFBorderType.SINGLE, 8,
							// 5,
							// "0000FF");
							// table.setInsideVBorder(XWPFBorderType.SINGLE, 8,
							// 5,
							// "0000FF");

							// CTTblWidth width =
							// table.getCTTbl().addNewTblPr().addNewTblW();
							// width.setType(STTblWidth.DXA);
							// width.setW(BigInteger.valueOf(9072));
							if (desitemnameele11.equalsIgnoreCase(
									"Multifunctional digital generator protection relay shall have following protections features")) {
								CTTblPr tblpro = table.getCTTbl().getTblPr();

								CTTblBorders borders = tblpro.addNewTblBorders();
								borders.addNewBottom().setVal(STBorder.THICK);
								borders.addNewLeft().setVal(STBorder.THICK);
								borders.addNewRight().setVal(STBorder.THICK);
								borders.addNewTop().setVal(STBorder.THICK);
								// also inner borders
								borders.addNewInsideH().setVal(STBorder.THICK);
								borders.addNewInsideV().setVal(STBorder.THICK);
								borders.addNewTop().setVal(STBorder.THICK);

								borders.getTop().setSz(BigInteger.valueOf(12));
								borders.getBottom().setSz(BigInteger.valueOf(12));
								borders.getLeft().setSz(BigInteger.valueOf(12));
								borders.getRight().setSz(BigInteger.valueOf(12));
								borders.getInsideH().setSz(BigInteger.valueOf(12));
								borders.getInsideV().setSz(BigInteger.valueOf(12));
								table.getCTTbl().getTblPr().getTblBorders().getTop().setSz(BigInteger.valueOf(12));
								table.getCTTbl().getTblPr().getTblBorders().getLeft().setSz(BigInteger.valueOf(12));
								table.getCTTbl().getTblPr().getTblBorders().getRight().setSz(BigInteger.valueOf(12));
								table.getCTTbl().getTblPr().getTblBorders().getBottom().setSz(BigInteger.valueOf(12));
								table.getCTTbl().getTblPr().getTblBorders().getInsideH().setSz(BigInteger.valueOf(12));
								table.getCTTbl().getTblPr().getTblBorders().getInsideV().setSz(BigInteger.valueOf(12));
								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));
								table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(5000));
								table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
							} else {
								CTTblPr tblpro = table.getCTTbl().getTblPr();

								CTTblBorders borders = tblpro.addNewTblBorders();
								borders.addNewBottom().setVal(STBorder.NONE);
								borders.addNewLeft().setVal(STBorder.NONE);
								borders.addNewRight().setVal(STBorder.NONE);
								borders.addNewTop().setVal(STBorder.NONE);
								// also inner borders
								borders.addNewInsideH().setVal(STBorder.NONE);
								borders.addNewInsideV().setVal(STBorder.NONE);
								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
								table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
								table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1500));

							}

							setTableAlign(table, ParagraphAlignment.LEFT);// Creating
																			// table
																			// only
																			// once
							tableRowOne111 = table.getRow(0);

						}
					}
				}

				if (dessubitemnameele11 != null) {
					if (!dessubitemnameele11.equals(prevdessubitemnameele11)) {
						counter311 = 1;
						paragraph = document.createParagraph();
						run = paragraph.createRun();
						run.setBold(true);
						run.setText("1." + dessubitemorderidele11 + "   " + dessubitemnameele11);
						run.setFontFamily("Cambria (Headings)");
						run.setFontSize(11);

						table = document.createTable();

						// table.setInsideHBorder(XWPFTable.XWPFBorderType.DASHED,
						// 4, 0, "0000FF");
						// table.setInsideVBorder(XWPFTable.XWPFBorderType.DASHED,
						// 4, 0, "0000FF");

						// table.setCellMargins(1, left, bottom, right);
						CTTblLayoutType type = table.getCTTbl().getTblPr().addNewTblLayout();
						type.setType(STTblLayoutType.FIXED);
						if (dessubitemnameele11.equalsIgnoreCase(
								"Multifunctional digital generator protection relay shall have following protections features")) {
							CTTblPr tblpro = table.getCTTbl().getTblPr();

							CTTblBorders borders = tblpro.addNewTblBorders();
							borders.addNewBottom().setVal(STBorder.THICK);
							borders.addNewLeft().setVal(STBorder.THICK);
							borders.addNewRight().setVal(STBorder.THICK);
							borders.addNewTop().setVal(STBorder.THICK);
							// also inner borders
							borders.addNewInsideH().setVal(STBorder.THICK);
							borders.addNewInsideV().setVal(STBorder.THICK);
							borders.addNewTop().setVal(STBorder.THICK);
							borders.getTop().setSz(BigInteger.valueOf(12));
							borders.getBottom().setSz(BigInteger.valueOf(12));
							borders.getLeft().setSz(BigInteger.valueOf(12));
							borders.getRight().setSz(BigInteger.valueOf(12));
							borders.getInsideH().setSz(BigInteger.valueOf(12));
							borders.getInsideV().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getTop().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getLeft().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getRight().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getBottom().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getInsideH().setSz(BigInteger.valueOf(12));
							table.getCTTbl().getTblPr().getTblBorders().getInsideV().setSz(BigInteger.valueOf(12));
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(5000));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
						} else {
							CTTblPr tblpro = table.getCTTbl().getTblPr();

							CTTblBorders borders = tblpro.addNewTblBorders();
							borders.addNewBottom().setVal(STBorder.NONE);
							borders.addNewLeft().setVal(STBorder.NONE);
							borders.addNewRight().setVal(STBorder.NONE);
							borders.addNewTop().setVal(STBorder.NONE);
							// also inner borders
							borders.addNewInsideH().setVal(STBorder.NONE);
							borders.addNewInsideV().setVal(STBorder.NONE);
							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));

							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
							table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1500));

						}

						// CTTblWidth width =
						// table.getCTTbl().addNewTblPr().addNewTblW();
						// width.setType(STTblWidth.DXA);
						// width.setW(BigInteger.valueOf(9072));

						// table.setWidth(2000);
						// CTTblPr tblpro = table.getCTTbl().getTblPr();
						//
						// CTTblBorders borders = tblpro.getTblBorders();
						// borders.getBottom().setVal(STBorder.SINGLE);
						// borders.getBottom().setColor("0000FF");
						//
						// borders.getLeft().setVal(STBorder.BASIC_BLACK_DOTS);
						// borders.getLeft().setColor("0000FF");
						// borders.getRight().setVal(STBorder.BASIC_BLACK_DOTS);
						// borders.getRight().setColor("0000FF");
						// borders.getTop().setVal(STBorder.BASIC_BLACK_DOTS);
						// borders.getTop().setColor("0000FF");
						// //also inner borders
						// borders.getInsideH().setVal(STBorder.BASIC_BLACK_DOTS);
						// borders.getInsideH().setColor("0000FF");
						// borders.getInsideV().setVal(STBorder.BASIC_BLACK_DOTS);
						// borders.getInsideV().setColor("0000FF");

						setTableAlign(table, ParagraphAlignment.LEFT);// Creating
																		// table
																		// only
																		// once
						tableRowOne111 = table.getRow(0);

					}
				}

				// To print the item name only once
				// if (desitemnameele != null ) {
				//
				// if (!desitemnameele.equals(prevdesitemnameele)){
				// paragraph = document.createParagraph();
				// run = paragraph.createRun();
				// run.setText(desitemnameele);
				// run.setText(":");
				// run.setBold(true);
				// run.setFontFamily("Cambria (Headings)");
				// run.setFontSize(11);
				//
				// table = document.createTable();
				// setTableAlign(table, ParagraphAlignment.LEFT);// Creating
				// // table
				// // only once
				// tableRowOne1 = table.getRow(0);
				// } // if (!subitemnameele.equals(prevdesitemnameele))
				//
				// if (dessubitemnameele != null &&
				// !dessubitemnameele.equals(prevdessubitemnameele)) {
				//
				//
				// paragraph = document.createParagraph();
				// run = paragraph.createRun();
				// run.setText(dessubitemorderidele+" "+ dessubitemnameele);
				// run.setFontFamily("Cambria (Headings)");
				// run.setFontSize(11);
				//
				// table = document.createTable();
				// setTableAlign(table, ParagraphAlignment.LEFT);// Creating
				// // table
				// // only once
				// tableRowOne1 = table.getRow(0);
				// }
				//

				// To print the lhs and rhs of des item
				String colunmname11 = reportBean.getWordData().get(e).getColNm();
				String colValCode11 = reportBean.getWordData().get(e).getColValCd();

				if (desitemnameele11 != null && dessubitemnameele11 == null) // Compare
																				// two
																				// strings
				{

					if (colunmname11 != null && colValCode11 != null) {
						// paragraph = document.createParagraph();
						// run = paragraph.createRun();
						//
						// run.setText(descolorderidele+" "+ colunmname);
						// run.setText(":");
						// run.setText(colValCode);
						// run.setFontFamily("Cambria (Headings)");
						// run.setFontSize(11);

						if (tableRowOne111 != null) {
							if (desitemnameele11.equalsIgnoreCase(
									"Multifunctional digital generator protection relay shall have following protections features")) {
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// sdMultifunctional digital generator
								// protection relay shall have following
								// protections featuresd

								tableRowOne111.getCell(0).setText("SL .NO");
								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne111.addNewTableCell().setText("CATEGORY");
								tableRowOne111.addNewTableCell().setText("DESCRIPTION");
								tableRowOne111.getCell(0).setColor("808080");
								tableRowOne111.getCell(1).setColor("808080");
								tableRowOne111.getCell(2).setColor("808080");
								tableRowOne111 = null;
								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);
								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.getCell(0).setText(descolorderidele11);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne.getCell(1).setText(colunmname11);
								if (subColValCodeele11 != null && !(subColValCodeele11.equals("NULL"))) {
									tableRowOne.getCell(2).setText(subColValCodeele11);
								} else {
									tableRowOne.getCell(2).setText(colValCode11);
								}

								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							} else {
								tableRowOne111.getCell(0).setText(descolorderidele11);
								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne111.addNewTableCell().setText(colunmname11);
								;
								tableRowOne111.addNewTableCell();
								XWPFParagraph paragraph12 = tableRowOne111.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								paragraph12.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run12 = paragraph12.createRun();
								run12.setFontSize(14);
								run12.setFontFamily("Cambria (Headings)");
								run12.setText(":");
								if (subColValCodeele11 != null && !(subColValCodeele11.equals("NULL"))) {
									tableRowOne111.addNewTableCell().setText(subColValCodeele11);
								} else {
									tableRowOne111.addNewTableCell().setText(colValCode11);
								}

								tableRowOne111 = null;
								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

						}

						else {
							if (desitemnameele11.equalsIgnoreCase(
									"Multifunctional digital generator protection relay shall have following protections features")) {

								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.getCell(0).setText(descolorderidele11);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne.getCell(1).setText(colunmname11);
								if (subColValCodeele11 != null && !(subColValCodeele11.equals("NULL"))) {
									tableRowOne.getCell(2).setText(subColValCodeele11);
								} else {
									tableRowOne.getCell(2).setText(colValCode11);
								}

								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

							else if ((colunmname11.equals("Additional outgoing feeders ( Ampere rating & quantity )"))
									&& count == 0) {
								count = 1;
								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.getCell(0).setText(descolorderidele11);
								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
								tableRowOne.getCell(1).setText(colunmname11);
								// tableRowOne.getCell(1).setText(" : ");
								XWPFParagraph paragraph12 = tableRowOne.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								paragraph12.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run12 = paragraph12.createRun();
								run12.setFontSize(14);
								run12.setFontFamily("Cambria (Headings)");
								run12.setText(":");
								if (subColValCodeele11 != null && !(subColValCodeele11.equals("NULL"))) {
									tableRowOne.getCell(3).setText(subColValCodeele11);
								} else {
									tableRowOne.getCell(3).setText(colValCode11 + "*" + qunatityele11);
								}

								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

							else if (colunmname11.equals("Additional outgoing feeders ( Ampere rating & quantity )")
									&& count111 != 0) {
								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.getCell(0).setText("");
								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne.getCell(1).setText(" ");
								tableRowOne.getCell(2).setText(" ");
								if (subColValCodeele11 != null && !(subColValCodeele11.equals("NULL"))) {
									tableRowOne.getCell(3).setText(subColValCodeele11);
								} else {
									tableRowOne.getCell(3).setText(colValCode11 + "*" + qunatityele11);
								}

								// tableRowOne1 = null;
								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

							else {

								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.getCell(0).setText(descolorderidele11);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
								tableRowOne.getCell(1).setText(colunmname11);
								// tableRowOne.getCell(1).setText(" : ");
								XWPFParagraph paragraph12 = tableRowOne.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								paragraph12.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run12 = paragraph12.createRun();
								run12.setFontSize(14);
								run12.setFontFamily("Cambria (Headings)");
								run12.setText(":");
								if (subColValCodeele11 != null && !(subColValCodeele11.equals("NULL"))) {
									tableRowOne.getCell(3).setText(subColValCodeele11);
								} else {
									if (colValCode11.contains("\n")) {

										String[] arrOfStr = colValCode11.split("\n", 20);
										for (int x1 = 0; x1 < arrOfStr.length; x1++) {
											XWPFParagraph paragraph21 = tableRowOne.getCell(3).getParagraphs().get(0);

											// System.out.println(table.getNumberOfRows());
											// paragraph21.setAlignment(ParagraphAlignment.CENTER);
											XWPFRun run123 = paragraph21.createRun();
											run123.setFontSize(11);
											// run123.setFontFamily("Cambria
											// (Headings)");
											run123.setText(arrOfStr[x1]);
											run123.addBreak();
											logger.info("rhs word start");
											logger.info(arrOfStr[x1]);
											logger.info("rhs word end");
										}
									} else {
										tableRowOne.getCell(3).setText(colValCode11);

									}
								}

								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

						}

					}
				} else {

					if (desitemnameele11 != null) {
						// paragraph = document.createParagraph();
						// run = paragraph.createRun();
						// run.setText(descolorderidele+" "+ colunmname);
						// run.setText(":");
						// run.setText(colValCode);
						// run.setFontFamily("Cambria (Headings)");
						// run.setFontSize(11);

						if (tableRowOne111 != null) {
							if (dessubitemnameele11.equalsIgnoreCase(
									"Multifunctional digital generator protection relay shall have following protections features")) {
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// Multifunctional digital generator protection
								// relay shall have following protections
								// features
								// sdMultifunctional digital generator
								// protection relay shall have following
								// protections featuresd

								tableRowOne111.getCell(0).setText("SL .NO");
								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne111.addNewTableCell().setText("CATEGORY");
								tableRowOne111.addNewTableCell().setText("DESCRIPTION");
								// tableRowOne111.getCell(0).setColor("808080");
								// tableRowOne111.getCell(1).setColor("808080");
								// tableRowOne111.getCell(2).setColor("808080");
								// tableRowOne1.getCell(0).setColor("GrayColor");
								// tableRowOne1.getCell(1).setColor("GrayColor");
								// tableRowOne1.getCell(2).setColor("GrayColor");
								tableRowOne111 = null;
								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);
								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.getCell(0).setText(descolorderidele11);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne.getCell(1).setText(colunmname11);
								if (subColValCodeele11 != null && !(subColValCodeele11.equals("NULL"))) {
									tableRowOne.getCell(2).setText(subColValCodeele11);
								} else {
									tableRowOne.getCell(2).setText(colValCode11);
								}

								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							} else {
								tableRowOne111.getCell(0).setText(descolorderidele11);
								// tableRowOne1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
								tableRowOne111.addNewTableCell().setText(colunmname11);
								tableRowOne111.addNewTableCell();
								XWPFParagraph paragraph12 = tableRowOne111.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								paragraph12.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run12 = paragraph12.createRun();
								run12.setFontSize(14);
								run12.setFontFamily("Cambria (Headings)");
								run12.setText(":");
								if (subColValCodeele11 != null && !(subColValCodeele11.equals("NULL"))) {
									tableRowOne111.addNewTableCell().setText(subColValCodeele11);
								} else {
									tableRowOne111.addNewTableCell().setText(colValCode11);
								}

								tableRowOne111 = null;
								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);
							}

						}

						else {
							if (dessubitemnameele11.equalsIgnoreCase(
									"Multifunctional digital generator protection relay shall have following protections features")) {

								XWPFTableRow tableRowOne = table.createRow();

								tableRowOne.getCell(0).setText(descolorderidele11);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

								tableRowOne.getCell(1).setText(colunmname11);

								if (subColValCodeele11 != null && !(subColValCodeele11.equals("NULL"))) {
									tableRowOne.getCell(2).setText(subColValCodeele11);
								} else {
									tableRowOne.getCell(2).setText(colValCode11);
								}

								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

							else {

								XWPFTableRow tableRowOne = table.createRow();

								tableRowOne.getCell(0).setText(descolorderidele11);
								// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
								tableRowOne.getCell(1).setText(colunmname11);
								// tableRowOne.getCell(1).setText(" : ");
								XWPFParagraph paragraph12 = tableRowOne.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								paragraph12.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run12 = paragraph12.createRun();
								run12.setFontSize(14);
								run12.setFontFamily("Cambria (Headings)");
								run12.setText(":");

								if (subColValCodeele11 != null && !(subColValCodeele11.equals("NULL"))) {

									tableRowOne.getCell(3).setText(subColValCodeele11);
								} else {
									// tableRowOne.getCell(3).setText(colValCode11);
									// if(colValCode11.contains("\n")){
									// logger.info("rhs word start");
									// logger.info(colValCode11);
									// logger.info("rhs word end");
									//
									// }
									if (colValCode11.contains("\n")) {

										String[] arrOfStr = colValCode11.split("\n", 20);
										for (int x1 = 0; x1 < arrOfStr.length; x1++) {
											XWPFParagraph paragraph21 = tableRowOne.getCell(3).getParagraphs().get(0);

											// System.out.println(table.getNumberOfRows());
											// paragraph21.setAlignment(ParagraphAlignment.CENTER);
											XWPFRun run123 = paragraph21.createRun();
											run123.setFontSize(11);
											// run123.setFontFamily("Cambria
											// (Headings)");
											run123.setText(arrOfStr[x1]);
											run123.addBreak();
											logger.info("rhs word start");
											logger.info(arrOfStr[x1]);
											logger.info("rhs word end");
										}
									} else {
										tableRowOne.getCell(3).setText(colValCode11);

									}

								}

								run.setFontFamily("Cambria (Headings)");
								run.setFontSize(11);

							}

						}

					}

				}

				// To print technical remarks of the sub itemq

				if (e + 1 == reportBean.getWordData().size()) {
					if ((techremarksele11 != null) && !(techremarksele11.equals("NULL"))
							&& !(techremarksele11.equals("NULL:"))) {

						paragraph = document.createParagraph();
						run = paragraph.createRun();
						run.setText(techremarksele11);

						// run.setFontFamily("Cambria (Headings)");
						run.setFontSize(11);
					}
				} // if (x + 1 == reportBean.getWordData().size())

			} // if (desitemnameele != null)
			prevqtyciaux = qtyciaux;
			previtemnameele11 = itemnameele11;
			prevdesitemnameele11 = desitemnameele11;
			prevdessubitemnameele11 = dessubitemnameele11;
			previtemcodeele11 = itemcodeele11;
			prevtechremarksele11 = techremarksele11;
			prevsubcategoryele11 = subcategorynameele11;
			prevheadertextele11 = headertextele11;
			prevfootertextele11 = footertextele11;

			if (e + 1 == reportBean.getWordData().size()) {
				if ((footertextele11 != null) && !(footertextele11.equals("NULL"))
						&& !(footertextele11.equals("NULL:"))) {

					// paragraph = document.createParagraph();
					// run = paragraph.createRun();
					// run.setText(footertextele11);
					// run.setText(":");
					//
					// run.setFontFamily("Cambria (Headings)");
					// run.setFontSize(11);

				}
			}

		} // for loop

		// ci aux ends

		int f = 0;
		int prevqunatityextscp11 = 0;
		String prevcategoryextscp11 = null;
		String prevsubcategoryextscp11 = null;
		String previtemnameextscp11 = null;
		String prevsubitemnameextscp11 = null;
		String previtemcodeextscp11 = null;
		String prevtechremarksextscp11 = null;
		String prevtechcommentsextscp11 = null;
		int counr99 = 0;
		for (f = e; f < reportBean.getWordData().size(); f++)

		{
			logger.info("CIEXT");
			String categorynameextscp11 = null;
			String subcategorynameextscp11 = null;
			int itemidextscp11 = 0;
			String space = null;
			String itemnameextscp11 = null;
			String itemcodeextscp11 = null;
			String subitemcodeextscp11 = null;
			String subitemnameextscp11 = null;

			int qunatityextscp11 = 0;
			String techremarksextscp11 = null;
			String techcommentsextscp11 = null;
			int numberofsubitemsextscp11 = 0;
			XWPFTableRow tableRowOne111 = null;
			if (f == QuotationDaoImpl.size11 && counr99 > 0) {
				if (reportBean.getWordData().get(f - 1).getTechRemarks() != null) {

					paragraph = document.createParagraph();
					run = paragraph.createRun();
					run.setText(reportBean.getWordData().get(f - 1).getTechRemarks());
					// run.setFontFamily("Cambria (Headings)");
					run.setFontSize(11);
				}
			}
			if (f == QuotationDaoImpl.size11) {
				break;
			}
			categorynameextscp11 = reportBean.getWordData().get(f).getCategoryName();
			subcategorynameextscp11 = reportBean.getWordData().get(f).getSubCategoryName();
			itemidextscp11 = reportBean.getWordData().get(f).getItemId();
			itemnameextscp11 = reportBean.getWordData().get(f).getItemName();
			itemcodeextscp11 = reportBean.getWordData().get(f).getItemCode();
			subitemcodeextscp11 = reportBean.getWordData().get(f).getSubItemCode();
			subitemnameextscp11 = reportBean.getWordData().get(f).getSubItemName();

			qunatityextscp11 = reportBean.getWordData().get(f).getQuantityAux();
			techremarksextscp11 = reportBean.getWordData().get(f).getTechRemarks();
			techcommentsextscp11 = reportBean.getWordData().get(f).getTechComments();
			numberofsubitemsextscp11 = reportBean.getWordData().get(f).getNumberOfSubItems();
			space = reportBean.getWordData().get(f).getSpaceF2f();
			// This is to print the technical remarks at the end of the item
			logger.info("1");
			if (itemcodeextscp11 != null) {
				if (techcommentsextscp11 != null) {
					if (!techcommentsextscp11.equals(prevtechcommentsextscp11)) {
						paragraph = document.createParagraph();
						run = paragraph.createRun();
						run.setText(techcommentsextscp11);

						// run.setFontFamily("Cambria (Headings)");
						run.setFontSize(11);
						run.setBold(true);
					} // if (!techremarkextscp.equals(prevtechremarksextscp))
				} // if (techremarksextscp != null)
			} // (!itemcodeextscp.equals(previtemcodeextscp) && itemcodeextscp
				// != null)
			logger.info("2");

			itemnameextscp11 = reportBean.getWordData().get(f).getItemName();
			subitemnameextscp11 = reportBean.getWordData().get(f).getSubItemName();

			// to print item name only once
			if (!itemnameextscp11.equals(previtemnameextscp11)) {

				paragraph = document.createParagraph();
				run = paragraph.createRun();
				run.setText(reportBean.getWordData().get(f).getItemName());
				run.setBold(true);
				run.setFontFamily("Cambria (Headings)");
				run.setFontSize(12);
				// table = document.createTable();
				// setTableAlign(table, ParagraphAlignment.LEFT);// Creating
				// // table
				// // only once
				// tableRowOne111 = table.getRow(0);

			} // if (!itemnameextscp.equals(previtemnameextscp))

			// To print the sub item name only once
			logger.info("3");

			if (subitemnameextscp11 != null) {
				if (!subitemnameextscp11.equals(prevsubitemnameextscp11)) {
					if (prevsubitemnameextscp11 != null) {
						if (prevtechremarksextscp11 != null) {

							paragraph = document.createParagraph();
							run = paragraph.createRun();
							run.setText(prevtechremarksextscp11);
							// run.setFontFamily("Cambria (Headings)");
							run.setFontSize(11);
						}
					}

					paragraph = document.createParagraph();
					run = paragraph.createRun();
					run.setText(reportBean.getWordData().get(f).getSubItemName());
					run.setText(":");

					if ((qunatityextscp11) != prevqunatityextscp11) {
						if (reportBean.getWordData().get(f).getQuantityAux() > 1) {
							run.setText(space + space + space + space + space + space + space + space + space + space
									+ space + space + space + space + space + space + space + space + space + space
									+ String.valueOf("Quantity :" + reportBean.getWordData().get(f).getQuantityAux()));
						}
					}
					run.setBold(true);
					run.setFontFamily("Cambria (Headings)");
					run.setFontSize(11);
					if (reportBean.getWordData().get(f).getColNm() != null) {
						table = document.createTable();
						CTTblPr tblpro = table.getCTTbl().getTblPr();

						CTTblBorders borders = tblpro.addNewTblBorders();
						borders.addNewBottom().setVal(STBorder.NONE);
						borders.addNewLeft().setVal(STBorder.NONE);
						borders.addNewRight().setVal(STBorder.NONE);
						borders.addNewTop().setVal(STBorder.NONE);
						// also inner borders
						borders.addNewInsideH().setVal(STBorder.NONE);
						borders.addNewInsideV().setVal(STBorder.NONE);
						setTableAlign(table, ParagraphAlignment.LEFT);// Creating
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);

						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000)); // table
						// only once
						tableRowOne111 = table.getRow(0);
					}
				} // if (!subitemnameextscp.equals(prevsubitemnameextscp))

				// To print the lhs and rhs of sub item
				logger.info("4");

				if (subitemnameextscp11 != null) // Compare two strings
				{
					String colunmname11 = reportBean.getWordData().get(f).getColNm();
					String colValCode11 = reportBean.getWordData().get(f).getColValCd();

					if (colunmname11 != null && colValCode11 != null) {
						// paragraph = document.createParagraph();
						// run = paragraph.createRun();
						// run.setText(colunmname);
						// run.setText(":");
						// run.setText(colValCode);
						// run.setFontFamily("Cambria (Headings)");
						// run.setFontSize(11);
						logger.info(colunmname11);
						logger.info(colValCode11);

						if (tableRowOne111 != null) {
							logger.info("start1");
							tableRowOne111.getCell(0).setText(colunmname11);

							tableRowOne111.addNewTableCell();
							XWPFParagraph paragraph12 = tableRowOne111.getCell(1).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");
							tableRowOne111.addNewTableCell().setText(colValCode11);
							logger.info("start2");

							tableRowOne111 = null;

						}

						else {
							XWPFTableRow tableRowOne = table.createRow();
							tableRowOne.getCell(0).setText(colunmname11);
							XWPFParagraph paragraph12 = tableRowOne.getCell(1).getParagraphs().get(0);

							// System.out.println(table.getNumberOfRows());
							paragraph12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run12 = paragraph12.createRun();
							run12.setFontSize(14);
							run12.setFontFamily("Cambria (Headings)");
							run12.setText(":");
							tableRowOne.getCell(2).setText(colValCode11);

						}
					}

					// To print technical remarks of the sub itemq

					// if (f + 1 == reportBean.getWordData().size()) {
					// paragraph = document.createParagraph();
					// run = paragraph.createRun();
					// run.setText(techremarksextscp11);
					//
					// run.setFontFamily("Cambria (Headings)");
					// run.setFontSize(11);
					// } // if (n + 1 == reportBean.getWordData().size())

				} // if (subitemnameextscp != null )

			} // if (subitemnameextscp != null)
			logger.info("5");
			prevqunatityextscp11 = qunatityextscp11;
			previtemnameextscp11 = itemnameextscp11;
			prevsubitemnameextscp11 = subitemnameextscp11;
			previtemcodeextscp11 = itemcodeextscp11;
			prevtechremarksextscp11 = techremarksextscp11;
			prevtechcommentsextscp11 = techcommentsextscp11;
			counr99 = counr99 + 1;
		} // for loop

		// ci ext ends

		int per1 = 0;

		String previtemnameper = null;
		int previtemidper = 0;
		int itemid1 = 0;
		String itemname1 = null;
		int itemid2 = 0;
		String itemname2 = null;
		int itemid3 = 0;
		String itemname3 = null;
		int itemid4 = 0;
		String itemname4 = null;
		String perfName = null;

		for (int x1 = 0; x1 < reportBean.getWordData().size(); x1++) {

			logger.info(reportBean.getWordData().get(x1).getItemNewName());
			logger.info(x1);
			logger.info(QuotationDaoImpl.size13);

		}
		for (per1 = f; per1 < reportBean.getWordData().size(); per1++)

		{

			// for (int x23 =0 ; x23< reportBean.getItemList().size(); x23++){
			// logger.info(reportBean.getItemList().get(x23).getNewNm());
			// }

			logger.info("inside for loop 1");
			int itemidper = 0;
			String itemnameper = null;

			if (per1 == QuotationDaoImpl.size13) {

				break;
			}

			itemidper = reportBean.getWordData().get(per1).getItemId();
			// itemnameper = reportBean.getWordData().get(per1).getItemNm();
			itemnameper = reportBean.getWordData().get(per1).getItemNewName();
			perfName = reportBean.getWordData().get(per1).getCategoryName();
			if (itemidper == 1) {
				itemid1 = 1;
				itemname1 = itemnameper;
			}
			if (itemidper == 2) {
				itemid2 = 1;
				itemname2 = itemnameper;
			}
			if (itemidper == 3) {
				itemid3 = 1;
				itemname3 = itemnameper;
			}
			if (itemidper == 4) {
				itemid4 = 1;
				itemname4 = itemnameper;
			}
			logger.info(itemnameper);

		} // for loop
		if (itemid4 == 1 || itemid1 == 1 || itemid2 == 1 || itemid3 == 1) {
			paragraph = document.createParagraph();
			run = paragraph.createRun();
			run.setText("");
			paragraph = document.createParagraph();
			run = paragraph.createRun();
			paragraph.setPageBreak(true);
			run.setText(perfName);
			run.setFontFamily("Cambria (Headings)");
			run.setFontSize(12);
			run.setBold(true);

		}

		int per2 = 0;
		int counterp = 0;
		int counterperf = 0;
		String previtemnameper1 = null;
		int previtemidper1 = 0;
		ArrayList<Integer> conditions = null;
		String notehmbd = null;
		for (per2 = per1; per2 < reportBean.getWordData().size(); per2++)

		{
			if (per2 == QuotationDaoImpl.size14) {
				if (counterperf > 0) {
					if (reportBean.getWordData().get(per2 - 1).getNote() != null) {
						// XWPFParagraph paragraph212 =
						// document.createParagraph();
						// XWPFRun run1232 = paragraph212.createRun();

						notehmbd = reportBean.getWordData().get(per2 - 1).getNote();
						// if
						// (reportBean.getWordData().get(per2-1).getNote().contains("\n"))
						// {
						//
						// String[] arrOfStr =
						// reportBean.getWordData().get(per2-1).getNote().split("\n",
						// 20);
						// for (int x1 = 0; x1 < arrOfStr.length; x1++) {
						// XWPFParagraph paragraph21 =
						// document.createParagraph();
						//
						// // System.out.println(table.getNumberOfRows());
						// //
						// paragraph21.setAlignment(ParagraphAlignment.CENTER);
						// XWPFRun run123 = paragraph21.createRun();
						// run123.setFontSize(11);
						// run123.setFontFamily("Cambria (Headings)");
						// run123.setText(arrOfStr[x1]);

						// logger.info("rhs word start");
						// logger.info(arrOfStr[x1]);
						// logger.info("rhs word end");
						// }
						// } else {
						// XWPFParagraph paragraph21 =
						// document.createParagraph();
						// XWPFRun run123 = paragraph21.createRun();
						// run123.setFontSize(11);
						// // run123.setFontFamily("Cambria (Headings)");
						// run123.setText(reportBean.getWordData().get(per2-1).getNote());
						//
						// }
						//
					}
				}
				break;
			}
			if (itemid1 == 1) {

				logger.info("inside for loop 2");
				int quotidper1 = 0;
				int itemidper1 = 0;
				int paramidper1 = 0;
				String paramnmper1 = null;
				int unitidper1 = 0;
				String unitnmper1 = null;
				String guaranteedper1 = null;
				int numberOfCond = 0;
				int conditiontableinput = 0;
				int cond1 = 0;
				int cond2 = 0;
				int cond3 = 0;
				int cond4 = 0;
				int cond5 = 0;
				int cond6 = 0;
				int cond7 = 0;
				int cond8 = 0;
				int cond9 = 0;
				int cond10 = 0;
				XWPFTableRow tableRowOne2 = null;
				conditions = new ArrayList<Integer>();

				logger.info(QuotationDaoImpl.size14);

				quotidper1 = reportBean.getWordData().get(per2).getQuotId();
				itemidper1 = reportBean.getWordData().get(per2).getItemId();
				paramidper1 = reportBean.getWordData().get(per2).getParamId();
				paramnmper1 = reportBean.getWordData().get(per2).getParamNm();
				unitidper1 = reportBean.getWordData().get(per2).getUnitId();
				unitnmper1 = reportBean.getWordData().get(per2).getUnitNm();
				guaranteedper1 = reportBean.getWordData().get(per2).getGuaranteed();
				cond1 = reportBean.getWordData().get(per2).getCond1();
				cond2 = reportBean.getWordData().get(per2).getCond2();
				cond3 = reportBean.getWordData().get(per2).getCond3();
				cond4 = reportBean.getWordData().get(per2).getCond4();
				cond5 = reportBean.getWordData().get(per2).getCond5();
				cond6 = reportBean.getWordData().get(per2).getCond6();
				cond7 = reportBean.getWordData().get(per2).getCond7();
				cond8 = reportBean.getWordData().get(per2).getCond8();
				cond9 = reportBean.getWordData().get(per2).getCond9();
				cond10 = reportBean.getWordData().get(per2).getCond10();
				numberOfCond = reportBean.getWordData().get(per2).getNoOfCondition() - 1;
				conditiontableinput = reportBean.getWordData().get(per2).getConditiontableinput();
				conditions.add(cond1);
				conditions.add(cond2);
				conditions.add(cond3);
				conditions.add(cond4);
				conditions.add(cond5);
				conditions.add(cond6);
				conditions.add(cond7);
				conditions.add(cond8);
				conditions.add(cond9);
				conditions.add(cond10);
				logger.info(conditions);
				logger.info(numberOfCond);
				logger.info(guaranteedper1);
				logger.info(paramnmper1);
				logger.info(unitnmper1);
				counterperf = counterperf + 1;
				if (counterp == 0) {

					paragraph = document.createParagraph();
					run = paragraph.createRun();
					run.setText(itemname1);
					run.setFontFamily("Cambria (Headings)");
					run.setFontSize(11);
					run.setBold(true);

					run.setFontFamily("Cambria (Headings)");
					run.setFontSize(11);
					logger.info("helooo2" + counterp);
					table = document.createTable();

					CTTblLayoutType type = table.getCTTbl().getTblPr().addNewTblLayout();

					type.setType(STTblLayoutType.FIXED);
					setTableAlign(table, ParagraphAlignment.CENTER);// Creating
					//
					// table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1100));
					// table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1100));
					// table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1100))
					table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(2000));
					table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));
					table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1500));

					if (numberOfCond < 5) {

						for (int y2 = 0; y2 < numberOfCond; y2++) {

							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));

						}
					} else if (numberOfCond > 5) {

						for (int y2 = 0; y2 < numberOfCond; y2++) {

							table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(500));

						}

					}
					// table
					// only once
					tableRowOne2 = table.getRow(0);

				}
				counterp = counterp + 1;

				if (tableRowOne2 != null) {
					// tableRowOne2.getCell(0).setText("Peformance
					// Paramenters");
					// tableRowOne2.addNewTableCell().setText("Units");
					// tableRowOne2.addNewTableCell().setText("Guaranteed");
					tableRowOne2.addNewTableCell();
					tableRowOne2.addNewTableCell();

					XWPFParagraph paragraph2345212 = tableRowOne2.getCell(0).getParagraphs().get(0);
					paragraph2345212.setIndentationLeft(100);
					paragraph2345212.setIndentationRight(100);
					paragraph2345212.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun run1234212 = paragraph2345212.createRun();
					run1234212.setFontSize(11);
					// run1234212.setFontFamily("Cambria (Headings)");
					run1234212.setText("Performance Parameters");
					run1234212.setBold(true);
					tableRowOne2.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);

					XWPFParagraph paragrap = tableRowOne2.getCell(1).getParagraphs().get(0);
					paragrap.setIndentationLeft(100);
					paragrap.setIndentationRight(100);
					paragrap.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun ru = paragrap.createRun();
					ru.setFontSize(11);
					// ru.setFontFamily("Cambria (Headings)");
					ru.setText("Units");
					ru.setBold(true);
					tableRowOne2.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);

					XWPFParagraph paragrap1 = tableRowOne2.getCell(2).getParagraphs().get(0);
					paragrap1.setIndentationLeft(100);
					paragrap1.setIndentationRight(100);
					paragrap1.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun run21 = paragrap1.createRun();
					run21.setFontSize(11);
					// run21.setFontFamily("Cambria (Headings)");
					run21.setText("Guaranteed");
					run21.setBold(true);
					tableRowOne2.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);

					// tableRowOne2.getCell(0).setColor("808080");
					// tableRowOne2.getCell(1).setColor("808080");
					// tableRowOne2.getCell(2).setColor("808080");

					if (numberOfCond < 5) {
						for (int p = 0; p < numberOfCond; p++) {
							logger.info("hello");

							tableRowOne2.addNewTableCell();
							XWPFParagraph paragrap12 = tableRowOne2.getCell(3 + p).getParagraphs().get(0);
							paragrap12.setIndentationLeft(100);
							paragrap12.setIndentationRight(100);
							paragrap12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run212 = paragrap12.createRun();
							run212.setFontSize(11);
							// run212.setFontFamily("Cambria (Headings)");
							run212.setText("Condition" + (p + 1));
							run212.setBold(true);
							// tableRowOne2.getCell(3+p).setColor("808080");
							tableRowOne2.getCell(3 + p).setVerticalAlignment(XWPFVertAlign.CENTER);

						}
					} else {
						for (int p = 0; p < numberOfCond; p++) {
							logger.info("hello1");
							tableRowOne2.addNewTableCell();
							XWPFParagraph paragrap12 = tableRowOne2.getCell(3 + p).getParagraphs().get(0);
							paragrap12.setIndentationLeft(100);
							paragrap12.setIndentationRight(100);
							paragrap12.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run212 = paragrap12.createRun();
							run212.setFontSize(11);
							// run212.setFontFamily("Cambria (Headings)");
							run212.setText(String.valueOf((p + 1)));
							run212.setBold(true);
							// tableRowOne2.getCell(3+p).setColor("808080");
							tableRowOne2.getCell(3 + p).setVerticalAlignment(XWPFVertAlign.CENTER);

						}
					}

					tableRowOne2 = null;
					XWPFTableRow tableRowOne = table.createRow();
					tableRowOne.getCell(0).setText(paramnmper1);
					tableRowOne.getCell(1).setText(unitnmper1);
					tableRowOne.getCell(2).setText(guaranteedper1);
					for (int p211 = 0; p211 < numberOfCond; p211++) {
						logger.info("hello3");
						tableRowOne.getCell(p211 + 3).setText(String.valueOf(conditions.get(p211)));
					}

				} else {
					XWPFTableRow tableRowOne = table.createRow();
					tableRowOne.getCell(0).setText(paramnmper1);
					tableRowOne.getCell(1).setText(unitnmper1);
					tableRowOne.getCell(2).setText(guaranteedper1);
					for (int p212 = 0; p212 < numberOfCond; p212++) {
						logger.info("hello4");
						tableRowOne.getCell(p212 + 3).setText(String.valueOf(conditions.get(p212)));
					}
				}
				logger.info(conditions);
			}
		} // for loop

		int per3 = 0;
		XWPFParagraph pa1 = null;
		XWPFRun ru3 = null;
		String previtemnameper2 = null;
		int previtemidper2 = 0;
		String previtemtype = null;
		int counter32 = 0;
		String techRemarks = null;
		int count3 = 0;
		String note2 = null;
		for (per3 = per2; per3 < reportBean.getWordData().size(); per3++)

		{
			logger.info("test value");
			logger.info(count3);
			logger.info(reportBean.getWordData().get(per3 - 1).getNote());
			if (per3 == QuotationDaoImpl.size15 && count3 > 0) {
				if (note2 != null) {
					XWPFParagraph paragraph212 = document.createParagraph();
					XWPFRun run1232 = paragraph212.createRun();

					if (note2.contains("\n")) {

						String[] arrOfStr = note2.split("\n", 20);
						for (int x1 = 0; x1 < arrOfStr.length; x1++) {
							XWPFParagraph paragraph21 = document.createParagraph();

							// System.out.println(table.getNumberOfRows());
							// paragraph21.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run123 = paragraph21.createRun();
							run123.setFontSize(11);
							// run123.setFontFamily("Cambria (Headings)");
							run123.setText(arrOfStr[x1]);
						}
					} else {
						XWPFParagraph paragraph21 = document.createParagraph();
						XWPFRun run123 = paragraph21.createRun();
						run123.setFontSize(11);
						// run123.setFontFamily("Cambria (Headings)");
						run123.setText(note2);

					}

				}

			}
			if (per3 == QuotationDaoImpl.size15) {

				break;
			}

			if (itemid2 == 1) {
				count3 = count3 + 1;

				logger.info("inside for loop 3");
				String catnm = null;
				int itemid = 0;
				String itemnm = null;
				int hmbdtableinput = 0;

				int subitemid = 0;
				String subitemnm = null;
				String consumerid = null;
				String startup = null;
				String continuous = null;
				String colvalcd = null;
				int editflag = 0;
				String itemtype = null;
				String itemcd = null;
				String speed = null;
				String voltage = null;
				String feeder = null;
				XWPFTableRow tablerow3 = null;

				if (counter32 == 0) {

					if (reportBean.getWordData().get(per3).getHmbdTableInput() == 1) {
						if (itemid1 == 1) {

						}
						paragraph = document.createParagraph();
						run = paragraph.createRun();
						paragraph = document.createParagraph();
						run = paragraph.createRun();
						run.setText("Note : Refer HMBD ENCLOSED");
						// run.setFontFamily("Cambria (Headings)");
						run.setFontSize(13);
						run.setBold(true);
					}
					if (notehmbd != null) {
						// XWPFParagraph paragraph212 =
						// document.createParagraph();
						// XWPFRun run1232 = paragraph212.createRun();

						if (notehmbd.contains("\n")) {

							String[] arrOfStr = notehmbd.split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = document.createParagraph();

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								run123.setFontFamily("Cambria (Headings)");
								run123.setText(arrOfStr[x1]);
								logger.info("rhs word start");
								logger.info(arrOfStr[x1]);
								logger.info("rhs word end");
							}
						} else {
							XWPFParagraph paragraph21 = document.createParagraph();
							XWPFRun run123 = paragraph21.createRun();
							run123.setFontSize(11);
							// run123.setFontFamily("Cambria (Headings)");
							run123.setText(notehmbd);

						}

					}

					paragraph = document.createParagraph();
					run = paragraph.createRun();
					run.setText(itemname2);
					run.setBold(true);
					run.setFontFamily("Cambria (Headings)");
					run.setFontSize(11);

				}
				catnm = reportBean.getWordData().get(per3).getCategoryName();
				itemid = reportBean.getWordData().get(per3).getItemId();
				itemnm = reportBean.getWordData().get(per3).getItemNm();
				hmbdtableinput = reportBean.getWordData().get(per3).getHmbdTableInput();

				subitemid = reportBean.getWordData().get(per3).getSubItemId();
				subitemnm = reportBean.getWordData().get(per3).getSubItemNm();
				consumerid = reportBean.getWordData().get(per3).getConsumerId1();
				startup = reportBean.getWordData().get(per3).getStartUp();
				continuous = reportBean.getWordData().get(per3).getContinuous();
				colvalcd = reportBean.getWordData().get(per3).getColValCd();
				editflag = reportBean.getWordData().get(per3).getEditFlag();
				itemtype = reportBean.getWordData().get(per3).getItemType();
				itemcd = reportBean.getWordData().get(per3).getItemCd();
				speed = reportBean.getWordData().get(per3).getSpeed();
				voltage = reportBean.getWordData().get(per3).getVoltage();
				feeder = reportBean.getWordData().get(per3).getFeeder();
				techRemarks = reportBean.getWordData().get(per3).getTechRemarks();
				logger.info(subitemid);
				if (subitemid != previtemidper2) {

					if (subitemid == 7 || subitemid == 8 || subitemid == 9) {
						if (subitemid == 7) {
							paragraph = document.createParagraph();
							run = paragraph.createRun();

						}
						// if (counter32 > 1) {
						// paragraph = document.createParagraph();
						// run = paragraph.createRun();
						// run.setText("");
						// }
						paragraph = document.createParagraph();
						run = paragraph.createRun();
						if (continuous != null) {
							run.setText(subitemnm + " - " + colvalcd + " " + continuous);

						} else

						{
							run.setText(subitemnm + " - " + colvalcd);

						}

						run.setFontFamily("Cambria (Headings)");
						run.setFontSize(11);

					} else {
						if (subitemid != 0) {

							// if (counter32 > 1) {
							// paragraph = document.createParagraph();
							// run = paragraph.createRun();
							// run.setText("");
							// }
							if (subitemid == 6) {
								// paragraph = document.createParagraph();
								// run = paragraph.createRun();
							}

							if (colvalcd != null && voltage == null && feeder == null) {
								paragraph = document.createParagraph();
								run = paragraph.createRun();
								run.setText(subitemnm + " - " + colvalcd);

							} else {
								paragraph = document.createParagraph();
								run = paragraph.createRun();
								run.setText(subitemnm);

							}
							// if (colvalcd != null && voltage != null && feeder
							// != null) {
							// XWPFParagraph paragraphtemp =
							// document.createParagraph();
							// XWPFRun runtemp = paragraphtemp.createRun();
							// runtemp.setText(colvalcd);
							// runtemp.addBreak();
							// runtemp.setText(voltage);
							// runtemp.addBreak();
							// runtemp.setText(feeder);
							// runtemp.setFontSize(11);
							//
							// }
							run.setFontFamily("Cambria (Headings)");
							run.setFontSize(11);

							// Creating
							// table
							// only once
							if (subitemid == 5) {
								table = document.createTable();
								setTableAlign(table, ParagraphAlignment.CENTER);
								tablerow3 = table.getRow(0);

							}
							if (subitemid == 10) {
								table = document.createTable();
								setTableAlign(table, ParagraphAlignment.CENTER);

								CTTblLayoutType type = table.getCTTbl().getTblPr().addNewTblLayout();
								type.setType(STTblLayoutType.FIXED);
								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1500));
								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));

								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));

								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1200));

								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1800));

								table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));
								tablerow3 = table.getRow(0);

							}

						}

					}

				}
				if (voltage != null) {
					if (subitemid == 6 && voltage.equalsIgnoreCase("FIXED")) {
						logger.info("456");
						paragraph = document.createParagraph();
						run = paragraph.createRun();
						if (continuous != null && voltage.equalsIgnoreCase("FIXED")
								&& feeder.equalsIgnoreCase("FIXED")) {
							logger.info(consumerid + colvalcd + continuous);
							run.setText(consumerid + "    :    " + colvalcd + "  " + continuous);
							run.setFontSize(11);
							run.setFontFamily("Cambria (Headings)");

						} else {
							logger.info(consumerid + colvalcd);

							run.setText(consumerid + "    :    " + colvalcd);
							run.setFontSize(11);
							run.setFontFamily("Cambria (Headings)");

						}
					}
				}
				if (subitemid == 5) {
					if (tablerow3 != null) {
						// tablerow3.getCell(0).setText("Consumer");
						tablerow3.addNewTableCell();
						tablerow3.addNewTableCell();
						XWPFParagraph paragrap12 = tablerow3.getCell(0).getParagraphs().get(0);
						paragrap12.setIndentationLeft(100);
						paragrap12.setIndentationRight(100);
						paragrap12.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run212 = paragrap12.createRun();
						run212.setFontSize(11);
						// run212.setFontFamily("Cambria (Headings)");
						run212.setText("Consumer");
						run212.setBold(true);
						tablerow3.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);

						XWPFParagraph paragrap123 = tablerow3.getCell(1).getParagraphs().get(0);
						paragrap123.setIndentationLeft(100);
						paragrap123.setIndentationRight(100);
						paragrap123.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run2123 = paragrap123.createRun();
						run2123.setFontSize(11);
						// run2123.setFontFamily("Cambria (Headings)");
						run2123.setText("Start up, Kg/hr");
						run2123.setBold(true);
						tablerow3.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);

						XWPFParagraph paragrap1234 = tablerow3.getCell(2).getParagraphs().get(0);
						paragrap1234.setIndentationLeft(100);
						paragrap1234.setIndentationRight(100);
						paragrap1234.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run21234 = paragrap1234.createRun();
						run21234.setFontSize(11);
						// run21234.setFontFamily("Cambria (Headings)");
						run21234.setText("Continuous, Kg/hr");
						run21234.setBold(true);
						tablerow3.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);

						// tablerow3.getCell(0).setColor("808080");
						// tablerow3.getCell(1).setColor("808080");
						// tablerow3.getCell(2).setColor("808080");

						XWPFTableRow tableRowOne = table.createRow();
						tableRowOne.getCell(0).setText(consumerid);
						tableRowOne.getCell(1).setText(startup);
						tableRowOne.getCell(2).setText(continuous);
						tablerow3 = null;
					} else {
						XWPFTableRow tableRowOne = table.createRow();
						tableRowOne.getCell(0).setText(consumerid);
						tableRowOne.getCell(1).setText(startup);
						tableRowOne.getCell(2).setText(continuous);
					}

				}
				if (subitemid == 6) {
					logger.info("1");

					if (voltage == null) {
						logger.info("2");
						if (reportBean.getWordData().get(per3 - 1).getVoltage() != null) {
							table = document.createTable();
							setTableAlign(table, ParagraphAlignment.CENTER);
							tablerow3 = table.getRow(0);

						}
						if (tablerow3 != null) {
							// tablerow3.getCell(0).setText("Consumer");
							logger.info("3");

							tablerow3.addNewTableCell();
							pa1 = tablerow3.getCell(0).getParagraphs().get(0);
							pa1.setIndentationLeft(100);
							pa1.setIndentationRight(100);
							pa1.setAlignment(ParagraphAlignment.CENTER);
							ru3 = pa1.createRun();
							ru3.setFontSize(11);
							// ru3.setFontFamily("Cambria (Headings)");
							ru3.setText("Consumer/Purpose");
							ru3.setBold(true);
							tablerow3.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);

							pa1 = tablerow3.getCell(1).getParagraphs().get(0);
							pa1.setIndentationLeft(100);
							pa1.setIndentationRight(100);
							pa1.setAlignment(ParagraphAlignment.CENTER);
							ru3 = pa1.createRun();
							ru3.setFontSize(11);
							// ru3.setFontFamily("Cambria (Headings)");
							ru3.setText("Continuous Demand, m³ / hr");
							ru3.setBold(true);
							tablerow3.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);

							// tablerow3.getCell(0).setColor("808080");
							// tablerow3.getCell(1).setColor("808080");
							XWPFTableRow tableRowOne = table.createRow();
							tableRowOne.getCell(0).setText(consumerid);
							tableRowOne.getCell(1).setText(continuous);
							tablerow3 = null;
						} else {
							XWPFTableRow tableRowOne = table.createRow();
							tableRowOne.getCell(0).setText(consumerid);
							if (continuous == null) {
								tableRowOne.getCell(1).setText("");

							} else {
								if (continuous.equals("NULL")) {
									tableRowOne.getCell(1).setText("");

								} else {
									tableRowOne.getCell(1).setText(continuous);

								}

							}
						}
					}
				}
				if (subitemid == 10) {
					note2 = reportBean.getWordData().get(per3).getNote();

					if (tablerow3 != null) {
						// tablerow3.getCell(0).setText("AC Consumer");
						tablerow3.addNewTableCell();
						tablerow3.addNewTableCell();
						tablerow3.addNewTableCell();
						tablerow3.addNewTableCell();
						tablerow3.addNewTableCell();
						tablerow3.setCantSplitRow(true);
						// 1
						pa1 = tablerow3.getCell(0).getParagraphs().get(0);
						pa1.setIndentationLeft(100);
						pa1.setIndentationRight(100);
						pa1.setAlignment(ParagraphAlignment.CENTER);
						ru3 = pa1.createRun();
						ru3.setFontSize(11);
						// ru3.setFontFamily("Cambria (Headings)");
						ru3.setText("AC Consumer");
						ru3.setBold(true);
						tablerow3.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
						/// 2
						pa1 = tablerow3.getCell(1).getParagraphs().get(0);
						pa1.setIndentationLeft(100);
						pa1.setIndentationRight(100);
						pa1.setAlignment(ParagraphAlignment.CENTER);
						ru3 = pa1.createRun();
						ru3.setFontSize(11);
						// ru3.setFontFamily("Cambria (Headings)");
						ru3.setText("Speed");
						ru3.setBold(true);
						tablerow3.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
						// 3
						pa1 = tablerow3.getCell(2).getParagraphs().get(0);
						pa1.setIndentationLeft(100);
						pa1.setIndentationRight(100);
						pa1.setAlignment(ParagraphAlignment.CENTER);
						ru3 = pa1.createRun();
						ru3.setFontSize(11);
						// ru3.setFontFamily("Cambria (Headings)");
						ru3.setText("Voltage,V");
						ru3.setBold(true);
						tablerow3.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
						// 4
						pa1 = tablerow3.getCell(3).getParagraphs().get(0);
						pa1.setIndentationLeft(100);
						pa1.setIndentationRight(100);
						pa1.setAlignment(ParagraphAlignment.CENTER);
						ru3 = pa1.createRun();
						ru3.setFontSize(11);
						// ru3.setFontFamily("Cambria (Headings)");
						ru3.setText("Feeder Type");
						ru3.setBold(true);
						tablerow3.getCell(3).setVerticalAlignment(XWPFVertAlign.CENTER);
						// 5
						pa1 = tablerow3.getCell(4).getParagraphs().get(0);
						pa1.setIndentationLeft(100);
						pa1.setIndentationRight(100);
						pa1.setAlignment(ParagraphAlignment.CENTER);
						ru3 = pa1.createRun();
						ru3.setFontSize(11);
						// ru3.setFontFamily("Cambria (Headings)");
						ru3.setText("Start up/ Stand-by/Intermittant,kW");
						ru3.setBold(true);
						tablerow3.getCell(4).setVerticalAlignment(XWPFVertAlign.CENTER);
						// 6
						pa1 = tablerow3.getCell(5).getParagraphs().get(0);
						pa1.setIndentationLeft(100);
						pa1.setIndentationRight(100);
						pa1.setAlignment(ParagraphAlignment.CENTER);
						ru3 = pa1.createRun();
						ru3.setFontSize(11);
						// ru3.setFontFamily("Cambria (Headings)");
						ru3.setText("Continuous,kW");
						ru3.setBold(true);
						tablerow3.getCell(5).setVerticalAlignment(XWPFVertAlign.CENTER);

						// tablerow3.getCell(0).setColor("808080");
						// tablerow3.getCell(1).setColor("808080");
						// tablerow3.getCell(2).setColor("808080");
						// tablerow3.getCell(3).setColor("808080");
						// tablerow3.getCell(4).setColor("808080");
						// tablerow3.getCell(5).setColor("808080");

						XWPFTableRow tableRowOne = table.createRow();
						tableRowOne.getCell(0).setText(itemcd);
						tableRowOne.getCell(1).setText(speed);
						tableRowOne.getCell(2).setText(voltage);
						tableRowOne.getCell(3).setText(feeder);
						tableRowOne.getCell(4).setText(startup);
						tableRowOne.getCell(5).setText(continuous);

						tablerow3 = null;
					} else {
						if (!itemtype.equals(previtemtype)) {
							if (itemtype.equals("DC")) {
								XWPFTableRow tableRowOne = table.createRow();
								tableRowOne.getCell(0).setText("");
								tableRowOne.getCell(1).setText("");
								tableRowOne.getCell(2).setText("");
								tableRowOne.getCell(3).setText("");
								tableRowOne.getCell(4).setText("");
								tableRowOne.getCell(5).setText("");
								XWPFTableRow tableRowOne21 = table.createRow();
								tableRowOne21.setCantSplitRow(true);

								// tableRowOne21.getCell(0).setText("DC
								// Consumer");
								// tableRowOne21.getCell(1).setText("Speed");
								// tableRowOne21.getCell(2).setText("Voltage,V");
								// tableRowOne21.getCell(3).setText("Feeder
								// Type");
								// tableRowOne21.getCell(4).setText("Start up/
								// Stand-by/Intermittant,kW");
								// tableRowOne21.getCell(5).setText("Continuous,kW");

								pa1 = tableRowOne21.getCell(0).getParagraphs().get(0);
								pa1.setIndentationLeft(100);
								pa1.setIndentationRight(100);
								pa1.setAlignment(ParagraphAlignment.CENTER);
								ru3 = pa1.createRun();
								ru3.setFontSize(11);
								// ru3.setFontFamily("Cambria (Headings)");
								ru3.setText("DC Consumer");
								ru3.setBold(true);
								tableRowOne21.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
								/// 2
								pa1 = tableRowOne21.getCell(1).getParagraphs().get(0);
								pa1.setIndentationLeft(100);
								pa1.setIndentationRight(100);
								pa1.setAlignment(ParagraphAlignment.CENTER);
								ru3 = pa1.createRun();
								ru3.setFontSize(11);
								// ru3.setFontFamily("Cambria (Headings)");
								ru3.setText("Speed");
								ru3.setBold(true);
								tableRowOne21.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
								// 3
								pa1 = tableRowOne21.getCell(2).getParagraphs().get(0);
								pa1.setIndentationLeft(100);
								pa1.setIndentationRight(100);
								pa1.setAlignment(ParagraphAlignment.CENTER);
								ru3 = pa1.createRun();
								ru3.setFontSize(11);
								// ru3.setFontFamily("Cambria (Headings)");
								ru3.setText("Voltage,V");
								ru3.setBold(true);
								tableRowOne21.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
								// 4
								pa1 = tableRowOne21.getCell(3).getParagraphs().get(0);
								pa1.setIndentationLeft(100);
								pa1.setIndentationRight(100);
								pa1.setAlignment(ParagraphAlignment.CENTER);
								ru3 = pa1.createRun();
								ru3.setFontSize(11);
								// ru3.setFontFamily("Cambria (Headings)");
								ru3.setText("Feeder Type");
								ru3.setBold(true);
								tableRowOne21.getCell(3).setVerticalAlignment(XWPFVertAlign.CENTER);
								// 5
								pa1 = tableRowOne21.getCell(4).getParagraphs().get(0);
								pa1.setIndentationLeft(100);
								pa1.setIndentationRight(100);
								pa1.setAlignment(ParagraphAlignment.CENTER);
								ru3 = pa1.createRun();
								ru3.setFontSize(11);
								// ru3.setFontFamily("Cambria (Headings)");
								ru3.setText("Start up/ Stand-by/Intermittant,kW");
								ru3.setBold(true);
								tableRowOne21.getCell(4).setVerticalAlignment(XWPFVertAlign.CENTER);
								// 6
								pa1 = tableRowOne21.getCell(5).getParagraphs().get(0);
								pa1.setIndentationLeft(100);
								pa1.setIndentationRight(100);
								pa1.setAlignment(ParagraphAlignment.CENTER);
								ru3 = pa1.createRun();
								ru3.setFontSize(11);
								// ru3.setFontFamily("Cambria (Headings)");
								ru3.setText("Continuous,kW");
								ru3.setBold(true);
								tableRowOne21.getCell(5).setVerticalAlignment(XWPFVertAlign.CENTER);

							}
						}
						if (itemcd.equals("TOTAL =")) {
							XWPFTableRow tableRowOne = table.createRow();
							tableRowOne.getCell(0).setText("");
							tableRowOne.getCell(1).setText("");

							////
							tableRowOne.getCell(2).setText(" ");
							CTTc ctTc1 = tableRowOne.getCell(2).getCTTc();
							CTTcPr tcPr1 = ctTc1.addNewTcPr();
							CTHMerge hMerge1 = tcPr1.addNewHMerge();
							hMerge1.setVal(STMerge.RESTART);

							CTTcBorders tblBorders1 = tcPr1.addNewTcBorders();

							tblBorders1.addNewRight().setVal(STBorder.NIL);
							tableRowOne.getCell(3).setText("TOTAL =");
							CTTc ctTc12 = tableRowOne.getCell(3).getCTTc();
							CTTcPr tcPr12 = ctTc12.addNewTcPr();
							CTHMerge hMerge12 = tcPr12.addNewHMerge();
							hMerge12.setVal(STMerge.RESTART);

							CTTcBorders tblBorders12 = tcPr12.addNewTcBorders();

							tblBorders12.addNewLeft().setVal(STBorder.NIL);

							////
							tableRowOne.getCell(4).setText(startup);
							tableRowOne.getCell(5).setText(continuous);
						} else {
							XWPFTableRow tableRowOne = table.createRow();
							tableRowOne.setCantSplitRow(true);

							tableRowOne.getCell(0).setText(itemcd);
							tableRowOne.getCell(1).setText(speed);
							tableRowOne.getCell(2).setText(voltage);
							tableRowOne.getCell(3).setText(feeder);
							tableRowOne.getCell(4).setText(startup);
							tableRowOne.getCell(5).setText(continuous);
						}

					}

				}
				counter32 = counter32 + 1;

				previtemtype = itemtype;
				previtemidper2 = subitemid;
			}
		} // for loop

		int per4 = 0;

		String previtemnameper4 = null;
		int previtemidper4 = 0;
		String fixedtext1 = null;
		String fixedtext2 = null;
		String fixedtext3 = null;

		for (per4 = per3; per4 < reportBean.getWordData().size(); per4++)

		{

			logger.info("inside for loop 4");
			int itemid = 0;

			if (per4 == QuotationDaoImpl.size16) {

				break;
			}

			itemid = reportBean.getWordData().get(per4).getItemId();
			logger.info("demo21");
			logger.info(fixedtext1);
			logger.info(fixedtext2);
			logger.info(fixedtext3);

			fixedtext1 = reportBean.getWordData().get(per4).getFixedText1();
			fixedtext2 = reportBean.getWordData().get(per4).getFixedText2();
			fixedtext3 = reportBean.getWordData().get(per4).getFixedText3();

		} // for loop

		int per5 = 0;

		String previtemnameper5 = null;
		int previtemidper5 = 0;
		int counter3 = 0;

		for (per5 = per4; per5 < reportBean.getWordData().size(); per5++)

		{

			logger.info("inside for loop 5");
			int itemid = 0;
			String identifier = null;
			String unit = null;
			String recmd = null;
			String limit = null;
			XWPFTableRow tablerow3 = null;

			if (per5 == QuotationDaoImpl.size17) {
				break;
			}
			if (itemid3 == 1) {

				if (per5 == QuotationDaoImpl.size17) {
					paragraph = document.createParagraph();
					run = paragraph.createRun();
					run.addBreak();

					run.setText(fixedtext3);

					// run.setFontFamily("Cambria (Headings)");
					run.setFontSize(11);

				}
				if (counter3 == 0) {
					paragraph = document.createParagraph();
					run = paragraph.createRun();
					run.setText("");
					paragraph = document.createParagraph();
					run = paragraph.createRun();
					run.setText(itemname3);
					run.setFontFamily("Cambria (Headings)");
					run.setFontSize(11);
					run.setBold(true);

					paragraph = document.createParagraph();
					run = paragraph.createRun();
					run.setText(fixedtext1);
					run.addBreak();

					// run.setFontFamily("Cambria (Headings)");
					run.setFontSize(11);

					table = document.createTable();
					CTTblLayoutType type = table.getCTTbl().getTblPr().addNewTblLayout();
					type.setType(STTblLayoutType.FIXED);
					setTableAlign(table, ParagraphAlignment.CENTER);// Creating
					table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(3800));
					table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
					table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1800));
					//table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));

					// table
					// only once

					tablerow3 = table.getRow(0);

				}
				counter3 = counter3 + 1;
				itemid = reportBean.getWordData().get(per5).getItemId();
				identifier = reportBean.getWordData().get(per5).getIdentifier();
				unit = reportBean.getWordData().get(per5).getUnits();
				recmd = reportBean.getWordData().get(per5).getRecmd();
				limit = reportBean.getWordData().get(per5).getLimit();

				if (tablerow3 != null) {
					// tablerow3.getCell(0).setText("Identifier");
					tablerow3.addNewTableCell();
					tablerow3.addNewTableCell();
					//tablerow3.addNewTableCell();
					// 1
					pa1 = tablerow3.getCell(0).getParagraphs().get(0);
					pa1.setIndentationLeft(100);
					pa1.setIndentationRight(100);
					pa1.setAlignment(ParagraphAlignment.CENTER);
					ru3 = pa1.createRun();
					ru3.setFontSize(11);
					// ru3.setFontFamily("Cambria (Headings)");
					ru3.setText("Identifier");
					ru3.setBold(true);
					tablerow3.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
					// 2
					pa1 = tablerow3.getCell(1).getParagraphs().get(0);
					pa1.setIndentationLeft(100);
					pa1.setIndentationRight(100);
					pa1.setAlignment(ParagraphAlignment.CENTER);
					ru3 = pa1.createRun();
					ru3.setFontSize(11);
					// ru3.setFontFamily("Cambria (Headings)");
					ru3.setText("Units");
					ru3.setBold(true);
					tablerow3.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
					// 3
					pa1 = tablerow3.getCell(2).getParagraphs().get(0);
					pa1.setIndentationLeft(100);
					pa1.setIndentationRight(100);
					pa1.setAlignment(ParagraphAlignment.CENTER);
					ru3 = pa1.createRun();
					ru3.setFontSize(11);
					// ru3.setFontFamily("Cambria (Headings)");
					ru3.setText("Recommended Values");
					ru3.setBold(true);
					tablerow3.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
					// 4
//					pa1 = tablerow3.getCell(3).getParagraphs().get(0);
//					pa1.setIndentationLeft(100);
//					pa1.setIndentationRight(100);
//					pa1.setAlignment(ParagraphAlignment.CENTER);
//					ru3 = pa1.createRun();
//					ru3.setFontSize(11);
//					// ru3.setFontFamily("Cambria (Headings)");
//					ru3.setText("Limit Values");
//					ru3.setBold(true);
//					tablerow3.getCell(3).setVerticalAlignment(XWPFVertAlign.CENTER);

					//
					// tablerow3.getCell(0).setColor("808080");
					// tablerow3.getCell(1).setColor("808080");
					// tablerow3.getCell(2).setColor("808080");
					// tablerow3.getCell(3).setColor("808080");
					XWPFTableRow tableRowOne = table.createRow();
					tableRowOne.getCell(0).setText(identifier);
					tableRowOne.getCell(1).setText(unit);
					tableRowOne.getCell(2).setText(recmd);
//					if (limit.equals("NULL")) {
//						tableRowOne.getCell(3).setText("");
//
//					} else {
//						tableRowOne.getCell(3).setText(limit);
//
//					}
					tablerow3 = null;
				} else {
					XWPFTableRow tableRowOne = table.createRow();
					tableRowOne.getCell(0).setText(identifier);
					tableRowOne.getCell(1).setText(unit);
					tableRowOne.getCell(2).setText(recmd);
//					if (limit.equals("NULL")) {
//						tableRowOne.getCell(3).setText("");
//
//					} else {
//						tableRowOne.getCell(3).setText(limit);
//
//					}
				}
			}
		} // for loop

		int per6 = 0;

		String previtemnameper6 = null;
		int previtemidper6 = 0;
		int counter31 = 0;
		String prevconductivity = null;
		for (per6 = per5; per6 < reportBean.getWordData().size(); per6++)

		{
			logger.info("inside for loop 6");
			int itemid = 0;
			XWPFTableRow tablerow31 = null;

			String colnm = null;

			String conductivity = null;
			String continuous = null;
			String startup = null;

			if (per6 == QuotationDaoImpl.size18) {
				if (itemid3 == 1) {

					paragraph = document.createParagraph();
					run = paragraph.createRun();
					paragraph = document.createParagraph();
					run = paragraph.createRun();
					run.setText(fixedtext3);

					// run.setFontFamily("Cambria (Headings)");
					run.setFontSize(11);
				}
				break;
			}
			if (itemid3 == 1) {
				itemid = reportBean.getWordData().get(per6).getItemId();
				colnm = reportBean.getWordData().get(per6).getColNm();
				String tempcondct = null;
				tempcondct = URLEncoder.encode(reportBean.getWordData().get(per6).getConductivity(), "UTF-8");
				;

				conductivity = URLDecoder.decode(tempcondct, "UTF-8");
				logger.info("check special");
				logger.info(conductivity);

				continuous = reportBean.getWordData().get(per6).getContinuous();
				startup = reportBean.getWordData().get(per6).getStartUp();

				if (counter31 == 0) {
					XWPFParagraph paragraph2155 = document.createParagraph();

					XWPFRun run12355 = paragraph2155.createRun();
					run12355.addBreak();

					if (fixedtext2.contains("\n")) {
						String[] arrOfStr = fixedtext2.split("\n", 200);
						for (int x1 = 0; x1 < arrOfStr.length; x1++) {
							XWPFParagraph paragraph21 = document.createParagraph();

							// System.out.println(table.getNumberOfRows());
							// paragraph21.setAlignment(ParagraphAlignment.CENTER);
							XWPFRun run123 = paragraph21.createRun();
							run123.setFontSize(11);
							// run123.setFontFamily("Cambria (Headings)");
							run123.setText(arrOfStr[x1]);
							logger.info("rhs word start");
							logger.info(arrOfStr[x1]);
							logger.info("rhs word end");
						}
					} else {
						paragraph = document.createParagraph();
						run = paragraph.createRun();
						run.setText(fixedtext2 + "⁰");
						// run.setFontFamily("Cambria (Headings)");
						run.setFontSize(11);

					}

					// paragraph = document.createParagraph();
					// run = paragraph.createRun();
					// run.setText(" ");
					// paragraph = document.createParagraph();
					// run = paragraph.createRun();
					// run.setText(" RECOMMENDED STEAM PURITY LIMITS");
					//
					// run.setFontFamily("Cambria (Headings)");
					// run.setFontSize(11);

					table = document.createTable();
					CTTblLayoutType type = table.getCTTbl().getTblPr().addNewTblLayout();
					type.setType(STTblLayoutType.FIXED);
					setTableAlign(table, ParagraphAlignment.CENTER);// Creating
					table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));

					table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1700));
					table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1700));

					// table
					// only once

					tablerow31 = table.getRow(0);

				}
				// RECOMMENDED STEAM PURITY � LIMITS
				counter31 = counter31 + 1;
				;

				if (tablerow31 != null) {

					// tablerow31.getCell(0).setText("RECOMMENDED STEAM PURITY
					// LIMITS");
					pa1 = tablerow31.getCell(0).getParagraphs().get(0);

					pa1.setAlignment(ParagraphAlignment.CENTER);
					ru3 = pa1.createRun();
					ru3.setFontSize(11);
					// ru3.setFontFamily("Cambria (Headings)");
					ru3.setText("                      RECOMMENDED STEAM PURITY LIMITS");
					ru3.setBold(true);

					tablerow31.addNewTableCell().setText("");
					tablerow31.addNewTableCell().setText("");
					mergeCellHorizontally(table, 0, 0, 2);
					/// 1
					// 2
					XWPFTableRow tableRowOne2 = table.createRow();
					tableRowOne2.getCell(0).setText("");
					pa1 = tableRowOne2.getCell(1).getParagraphs().get(0);
					pa1.setIndentationLeft(100);
					pa1.setIndentationRight(100);
					pa1.setAlignment(ParagraphAlignment.CENTER);
					ru3 = pa1.createRun();
					ru3.setFontSize(11);
					// ru3.setFontFamily("Cambria (Headings)");
					ru3.setText("Continuous");
					ru3.setBold(true);
					tableRowOne2.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
					// 3 pa1ru3
					pa1 = tableRowOne2.getCell(2).getParagraphs().get(0);
					pa1.setIndentationLeft(100);
					pa1.setIndentationRight(100);
					pa1.setAlignment(ParagraphAlignment.CENTER);
					ru3 = pa1.createRun();
					ru3.setFontSize(11);
					// ru3.setFontFamily("Cambria (Headings)");
					ru3.setText("Start Up");
					ru3.setBold(true);
					tableRowOne2.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
					// tableRowOne2.getCell(0).setColor("808080");
					// tableRowOne2.getCell(1).setColor("808080");
					// tableRowOne2.getCell(2).setColor("808080");
					XWPFTableRow tableRowOne = table.createRow();
					// tableRowOne.getCell(0).setText("Conductivity");
					pa1 = tableRowOne.getCell(0).getParagraphs().get(0);
					pa1.setIndentationLeft(100);
					pa1.setIndentationRight(100);
					pa1.setAlignment(ParagraphAlignment.CENTER);
					ru3 = pa1.createRun();
					ru3.setFontSize(11);
					// ru3.setFontFamily("Cambria (Headings)");
					ru3.setText("Conductivity");
					// ru3.setBold(true);
					tableRowOne.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);

					tableRowOne.getCell(1).setText("");
					tableRowOne.getCell(2).setText("");
					XWPFTableRow tableRowOne32 = table.createRow();
					// stableRowOne32.getCell(0).setText(conductivity);
					pa1 = tableRowOne32.getCell(0).getParagraphs().get(0);
					pa1.setIndentationLeft(100);
					pa1.setIndentationRight(100);
					pa1.setAlignment(ParagraphAlignment.CENTER);
					ru3 = pa1.createRun();
					ru3.setFontSize(11);
					// ru3.setFontFamily("Arial Unicode MS");
					ru3.setText(conductivity);
					// ru3.setFontFamily("Cambria (Headings)");
					// ru3.setBold(true);
					tableRowOne32.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
					pa1 = tableRowOne32.getCell(1).getParagraphs().get(0);
					pa1.setIndentationLeft(100);
					pa1.setIndentationRight(100);
					pa1.setAlignment(ParagraphAlignment.CENTER);
					ru3 = pa1.createRun();
					ru3.setFontSize(11);
					// ru3.setFontFamily("Arial Unicode MS");
					ru3.setText("0.2");
					// ru3.setFontFamily("Cambria (Headings)");
					// ru3.setBold(true);
					tableRowOne32.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
					
					
						pa1 = tableRowOne32.getCell(2).getParagraphs().get(0);
					pa1.setIndentationLeft(100);
					pa1.setIndentationRight(100);
					pa1.setAlignment(ParagraphAlignment.CENTER);
					ru3 = pa1.createRun();
					ru3.setFontSize(11);
					// ru3.setFontFamily("Arial Unicode MS");
					ru3.setText("0.3");
					// ru3.setFontFamily("Cambria (Headings)");
					// ru3.setBold(true);
					tableRowOne32.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);

//					tableRowOne32.getCell(1).setText("0.2");
//					tableRowOne32.getCell(2).setText("0.3");
					XWPFTableRow tableRowOne13 = table.createRow();
					// tableRowOne13.getCell(0).setText(colnm);
					pa1 = tableRowOne13.getCell(0).getParagraphs().get(0);
					pa1.setIndentationLeft(100);
					pa1.setIndentationRight(100);
					pa1.setAlignment(ParagraphAlignment.CENTER);
					ru3 = pa1.createRun();
					ru3.setFontSize(11);
					// ru3.setFontFamily("Cambria (Headings)");
					ru3.setText(colnm);
					tableRowOne13.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);

					// tableRowOne13.getCell(1).setText(continuous);
					pa1 = tableRowOne13.getCell(1).getParagraphs().get(0);
					pa1.setIndentationLeft(100);
					pa1.setIndentationRight(100);
					pa1.setAlignment(ParagraphAlignment.CENTER);
					ru3 = pa1.createRun();
					ru3.setFontSize(11);
					// ru3.setFontFamily("Cambria (Headings)");
					ru3.setText(continuous);
					tableRowOne13.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
					// tableRowOne13.getCell(2).setText(startup);
					pa1 = tableRowOne13.getCell(2).getParagraphs().get(0);
					pa1.setIndentationLeft(100);
					pa1.setIndentationRight(100);
					pa1.setAlignment(ParagraphAlignment.CENTER);
					ru3 = pa1.createRun();
					ru3.setFontSize(11);
					// ru3.setFontFamily("Cambria (Headings)");
					ru3.setText(startup);
					tableRowOne13.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);

					tablerow31 = null;
				} else {
					if (!conductivity.equals(prevconductivity)) {
						XWPFTableRow tableRowOne22 = table.createRow();
						tableRowOne22.getCell(0).setText("");
						tableRowOne22.getCell(1).setText("");
						tableRowOne22.getCell(2).setText("");
						XWPFTableRow tableRowOne = table.createRow();
						// tableRowOne.getCell(0).setText(conductivity);
						pa1 = tableRowOne.getCell(0).getParagraphs().get(0);
						pa1.setIndentationLeft(100);
						pa1.setIndentationRight(100);
						pa1.setAlignment(ParagraphAlignment.CENTER);
						ru3 = pa1.createRun();
						ru3.setFontSize(11);
						// ru3.setFontFamily("Cambria (Headings)");

						// ru3.setFontFamily("Arial Unicode MS");
						ru3.setText(conductivity);

						// ru3.setBold(true);
						tableRowOne.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);

						tableRowOne.getCell(1).setText("");
						tableRowOne.getCell(2).setText("");

						XWPFTableRow tableRowOne13 = table.createRow();
						pa1 = tableRowOne13.getCell(0).getParagraphs().get(0);
						pa1.setIndentationLeft(100);
						pa1.setIndentationRight(100);
						pa1.setAlignment(ParagraphAlignment.CENTER);
						ru3 = pa1.createRun();
						ru3.setFontSize(11);
						// ru3.setFontFamily("Cambria (Headings)");
						ru3.setText(colnm);
						tableRowOne13.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);

						// tableRowOne13.getCell(1).setText(continuous);
						pa1 = tableRowOne13.getCell(1).getParagraphs().get(0);
						pa1.setIndentationLeft(100);
						pa1.setIndentationRight(100);
						pa1.setAlignment(ParagraphAlignment.CENTER);
						ru3 = pa1.createRun();
						ru3.setFontSize(11);
						// ru3.setFontFamily("Cambria (Headings)");
						ru3.setText(continuous);
						tableRowOne13.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
						// tableRowOne13.getCell(2).setText(startup);
						pa1 = tableRowOne13.getCell(2).getParagraphs().get(0);
						pa1.setIndentationLeft(100);
						pa1.setIndentationRight(100);
						pa1.setAlignment(ParagraphAlignment.CENTER);
						ru3 = pa1.createRun();
						ru3.setFontSize(11);
						// ru3.setFontFamily("Cambria (Headings)");
						ru3.setText(startup);
						tableRowOne13.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);

					} else {
						XWPFTableRow tableRowOne13 = table.createRow();
						pa1 = tableRowOne13.getCell(0).getParagraphs().get(0);
						pa1.setIndentationLeft(100);
						pa1.setIndentationRight(100);
						pa1.setAlignment(ParagraphAlignment.CENTER);
						ru3 = pa1.createRun();
						ru3.setFontSize(11);
						// ru3.setFontFamily("Cambria (Headings)");
						ru3.setText(colnm);
						tableRowOne13.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);

						// tableRowOne13.getCell(1).setText(continuous);
						pa1 = tableRowOne13.getCell(1).getParagraphs().get(0);
						pa1.setIndentationLeft(100);
						pa1.setIndentationRight(100);
						pa1.setAlignment(ParagraphAlignment.CENTER);
						ru3 = pa1.createRun();
						ru3.setFontSize(11);
						// ru3.setFontFamily("Cambria (Headings)");
						ru3.setText(continuous);
						tableRowOne13.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
						// tableRowOne13.getCell(2).setText(startup);
						pa1 = tableRowOne13.getCell(2).getParagraphs().get(0);
						pa1.setIndentationLeft(100);
						pa1.setIndentationRight(100);
						pa1.setAlignment(ParagraphAlignment.CENTER);
						ru3 = pa1.createRun();
						ru3.setFontSize(11);
						// ru3.setFontFamily("Cambria (Headings)");
						ru3.setText(startup);
						tableRowOne13.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);

					}

				}
				prevconductivity = conductivity;
			}
		} // for loop

		int per7 = 0;

		String previtemnameper7 = null;
		int previtemidper7 = 0;
		int counter323 = 0;
		String prevtype = null;

		for (per7 = per6; per7 < reportBean.getWordData().size(); per7++)

		{
			if (per7 == QuotationDaoImpl.size19) {
				break;
			}
			if (itemid4 == 1) {
				logger.info("inside for loop 7");
				int itemid = 0;
				String parameter = null;
				String type = null;
				String unit = null;
				String cirwater = null;
				XWPFTableRow tablerow34 = null;

				if (counter323 == 0) {
					paragraph = document.createParagraph();
					run = paragraph.createRun();
					run.setText("");
					paragraph = document.createParagraph();
					run = paragraph.createRun();
					run.setText(itemname4);
					run.setFontFamily("Cambria (Headings)");
					run.setFontSize(11);
					run.setBold(true);

					logger.info("conductivity123321" + counter323);
					// paragraph = document.createParagraph();
					// run = paragraph.createRun();
					//
					// run.setFontFamily("Cambria (Headings)");
					// run.setFontSize(11);
					table = document.createTable();
					CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
					type1.setType(STTblLayoutType.FIXED);
					setTableAlign(table, ParagraphAlignment.CENTER);// Creating
					table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(5000));

					table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(2000));
					table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(3000));

					// table
					// only once

					tablerow34 = table.getRow(0);

				}
				counter323 = counter323 + 1;
				itemid = reportBean.getWordData().get(per7).getItemId();
				parameter = reportBean.getWordData().get(per7).getParameter();
				type = reportBean.getWordData().get(per7).getType();
				unit = reportBean.getWordData().get(per7).getUnits();
				cirwater = reportBean.getWordData().get(per7).getCirWater();

				if (tablerow34 != null) {

					// tablerow34.getCell(0).setText("Parameter");
					tablerow34.addNewTableCell();
					tablerow34.addNewTableCell();
					// 1
					pa1 = tablerow34.getCell(0).getParagraphs().get(0);
					pa1.setIndentationLeft(100);
					pa1.setIndentationRight(100);
					pa1.setAlignment(ParagraphAlignment.CENTER);
					ru3 = pa1.createRun();
					ru3.setFontSize(11);
					// ru3.setFontFamily("Cambria (Headings)");
					ru3.setText("Parameter");
					ru3.setBold(true);
					tablerow34.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
					/// 2
					pa1 = tablerow34.getCell(1).getParagraphs().get(0);
					pa1.setIndentationLeft(100);
					pa1.setIndentationRight(100);
					pa1.setAlignment(ParagraphAlignment.CENTER);
					ru3 = pa1.createRun();
					ru3.setFontSize(11);
					// ru3.setFontFamily("Cambria (Headings)");
					ru3.setText("Units");
					ru3.setBold(true);
					tablerow34.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);

					/// 3
					pa1 = tablerow34.getCell(2).getParagraphs().get(0);
					pa1.setIndentationLeft(100);
					pa1.setIndentationRight(100);
					pa1.setAlignment(ParagraphAlignment.CENTER);
					ru3 = pa1.createRun();
					ru3.setFontSize(11);
					// ru3.setFontFamily("Cambria (Headings)");
					ru3.setText("Circulating Water With COC4");
					ru3.setBold(true);
					tablerow34.getCell(2).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);

					// tablerow34.getCell(0).setColor("808080");
					// tablerow34.getCell(1).setColor("808080");
					// tablerow34.getCell(2).setColor("808080");
					XWPFTableRow tableRowOne = table.createRow();
					// tableRowOne.getCell(0).setText(type);
					pa1 = tableRowOne.getCell(0).getParagraphs().get(0);
					pa1.setIndentationLeft(100);
					pa1.setIndentationRight(100);
					pa1.setAlignment(ParagraphAlignment.CENTER);
					ru3 = pa1.createRun();
					ru3.setFontSize(11);
					// ru3.setFontFamily("Cambria (Headings)");
					ru3.setBold(true);
					ru3.setText(type);
					tableRowOne.getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
					tableRowOne.getCell(1).setText("");
					tableRowOne.getCell(2).setText("");

					XWPFTableRow tableRowOne13 = table.createRow();

					// tableRowOne13.getCell(0).setText(parameter);
					pa1 = tableRowOne13.getCell(0).getParagraphs().get(0);
					pa1.setIndentationLeft(100);
					pa1.setIndentationRight(100);
					pa1.setAlignment(ParagraphAlignment.CENTER);
					ru3 = pa1.createRun();
					ru3.setFontSize(11);
					// ru3.setFontFamily("Arial Unicode MS");
					// ru3.setFontFamily("Cambria (Headings)");
					ru3.setText(parameter);
					tableRowOne13.getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
					// tableRowOne13.getCell(1).setText(unit);
					pa1 = tableRowOne13.getCell(1).getParagraphs().get(0);
					pa1.setIndentationLeft(100);
					pa1.setIndentationRight(100);
					pa1.setAlignment(ParagraphAlignment.CENTER);
					ru3 = pa1.createRun();
					ru3.setFontSize(11);
					// ru3.setFontFamily("Arial Unicode MS");

					// ru3.setFontFamily("Cambria (Headings)");
					ru3.setText(unit);
					tableRowOne13.getCell(1).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
					// tableRowOne13.getCell(2).setText(cirwater);
					pa1 = tableRowOne13.getCell(2).getParagraphs().get(0);
					pa1.setIndentationLeft(100);
					pa1.setIndentationRight(100);
					pa1.setAlignment(ParagraphAlignment.CENTER);
					ru3 = pa1.createRun();
					ru3.setFontSize(11);
					// ru3.setFontFamily("Cambria (Headings)");
					ru3.setText(cirwater);
					tableRowOne13.getCell(2).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
					tablerow34 = null;
				} else {
					if (!type.equals(prevtype)) {
						XWPFTableRow tableRowOne22 = table.createRow();
						tableRowOne22.getCell(0).setText("");
						tableRowOne22.getCell(1).setText("");
						tableRowOne22.getCell(2).setText("");
						XWPFTableRow tableRowOne = table.createRow();
						pa1 = tableRowOne.getCell(0).getParagraphs().get(0);
						pa1.setIndentationLeft(100);
						pa1.setIndentationRight(100);
						pa1.setAlignment(ParagraphAlignment.CENTER);
						ru3 = pa1.createRun();
						ru3.setFontSize(11);
						// ru3.setFontFamily("Cambria (Headings)");
						ru3.setBold(true);
						ru3.setText(type);
						tableRowOne.getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);

						tableRowOne.getCell(1).setText("");
						tableRowOne.getCell(2).setText("");

						XWPFTableRow tableRowOne13 = table.createRow();
						// tableRowOne13.getCell(0).setText(parameter);
						pa1 = tableRowOne13.getCell(0).getParagraphs().get(0);
						pa1.setIndentationLeft(100);
						pa1.setIndentationRight(100);
						pa1.setAlignment(ParagraphAlignment.CENTER);
						ru3 = pa1.createRun();
						ru3.setFontSize(11);
						// ru3.setFontFamily("Arial Unicode MS");

						// ru3.setFontFamily("Cambria (Headings)");
						ru3.setText(parameter);
						tableRowOne13.getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
						pa1 = tableRowOne13.getCell(1).getParagraphs().get(0);
						pa1.setIndentationLeft(100);
						pa1.setIndentationRight(100);
						pa1.setAlignment(ParagraphAlignment.CENTER);
						ru3 = pa1.createRun();
						ru3.setFontSize(11);
						// ru3.setFontFamily("Arial Unicode MS");

						ru3.setFontFamily("Cambria (Headings)");
						ru3.setText(unit);
						tableRowOne13.getCell(1).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
						// tableRowOne13.getCell(2).setText(cirwater);
						pa1 = tableRowOne13.getCell(2).getParagraphs().get(0);
						pa1.setIndentationLeft(100);
						pa1.setIndentationRight(100);
						pa1.setAlignment(ParagraphAlignment.CENTER);
						ru3 = pa1.createRun();
						ru3.setFontSize(11);
						// ru3.setFontFamily("Cambria (Headings)");
						ru3.setText(cirwater);
						tableRowOne13.getCell(2).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);

					} else {
						XWPFTableRow tableRowOne13 = table.createRow();
						// tableRowOne13.getCell(0).setText(parameter);
						pa1 = tableRowOne13.getCell(0).getParagraphs().get(0);
						pa1.setIndentationLeft(100);
						pa1.setIndentationRight(100);
						pa1.setAlignment(ParagraphAlignment.CENTER);
						ru3 = pa1.createRun();
						ru3.setFontSize(11);
						// ru3.setFontFamily("Arial Unicode MS");

						// ru3.setFontFamily("Cambria (Headings)");
						ru3.setText(parameter);
						tableRowOne13.getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);

						pa1 = tableRowOne13.getCell(1).getParagraphs().get(0);
						pa1.setIndentationLeft(100);
						pa1.setIndentationRight(100);
						pa1.setAlignment(ParagraphAlignment.CENTER);
						ru3 = pa1.createRun();
						ru3.setFontSize(11);
						// ru3.setFontFamily("Arial Unicode MS");

						// ru3.setFontFamily("Cambria (Headings)");
						ru3.setText(unit);
						tableRowOne13.getCell(1).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
						// tableRowOne13.getCell(2).setText(cirwater);

						if (cirwater.equals("NULL")) {
							pa1 = tableRowOne13.getCell(2).getParagraphs().get(0);
							pa1.setIndentationLeft(100);
							pa1.setIndentationRight(100);
							pa1.setAlignment(ParagraphAlignment.CENTER);
							ru3 = pa1.createRun();
							ru3.setFontSize(11);
							// ru3.setFontFamily("Cambria (Headings)");
							ru3.setText("-");
							tableRowOne13.getCell(2).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
						} else {
							pa1 = tableRowOne13.getCell(2).getParagraphs().get(0);
							pa1.setIndentationLeft(100);
							pa1.setIndentationRight(100);
							pa1.setAlignment(ParagraphAlignment.CENTER);
							ru3 = pa1.createRun();
							ru3.setFontSize(11);
							// ru3.setFontFamily("Cambria (Headings)");
							ru3.setText(cirwater);
							tableRowOne13.getCell(2).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
						}
					}

				}
				prevtype = type;
			}
		} // for loop
		if (techRemarks != null) {
			if (techRemarks.contains("\n")) {

				String[] arrOfStr = techRemarks.split("\n", 20);
				for (int x1 = 0; x1 < arrOfStr.length; x1++) {
					XWPFParagraph paragraph21 = document.createParagraph();

					// System.out.println(table.getNumberOfRows());
					// paragraph21.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun run123 = paragraph21.createRun();
					run123.setFontSize(11);
					run123.setFontFamily("Cambria (Headings)");
					run123.setText(arrOfStr[x1]);
					run123.addBreak();
					logger.info("rhs word start");
					logger.info(arrOfStr[x1]);
					logger.info("rhs word end");
				}
			} else {
				XWPFParagraph paragraph21 = document.createParagraph();
				XWPFRun run123 = paragraph21.createRun();
				run123.setFontSize(11);
				// run123.setFontFamily("Cambria (Headings)");
				run123.setText(techRemarks);

			}

		}

		int per8 = 0;
		String previtemnameper8 = null;
		String prevscopenm = null;
		int previtemidper8 = 0;
		int counter55 = 0;
		int counter56 = 0;
		int count2 = 0;
		String prevnote = null;
		int slno = 0;
		int countercheck = 0;
		for (per8 = per7; per8 < reportBean.getWordData().size(); per8++)

		{

			logger.info("inside for loop 8");
			logger.info(QuotationDaoImpl.size20);
			String catnm = null;
			int seqno = 0;
			int ssid = 0;
			String scopecd = null;
			String scopenm = null;
			int itemid = 0;
			String itemnm = null;
			String information = null;
			String finalts = null;
			String subscopecd = null;
			String description = null;
			String equipment = null;
			String test = null;
			String equivalent = null;
			String paneltype = null;
			String custtype = null;
			String remarks = null;

			String quanity = null;
			float cost = 0;
			int editflag = 0;
			int newcolvalflag = 0;
			String note = null;
			int noofmandays = 0;

			String itemname = null;
			XWPFTableRow tablerow34 = null;

			if (per8 == QuotationDaoImpl.size20) {
				if (countercheck > 0) {
					if (reportBean.getWordData().get(per8 - 1).getRemarks() != null) {
						if (!reportBean.getWordData().get(per8 - 1).getRemarks().equalsIgnoreCase("NULL")
								&& !reportBean.getWordData().get(per8 - 1).getScopeCd().equalsIgnoreCase("SS")) {
							if (reportBean.getWordData().get(per8 - 1).getRemarks().contains("\n")) {

								String[] arrOfStr = reportBean.getWordData().get(per8 - 1).getRemarks().split("\n", 20);
								for (int x1 = 0; x1 < arrOfStr.length; x1++) {
									XWPFParagraph paragraph21 = document.createParagraph();

									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									run123.setText(arrOfStr[x1]);

								}
							} else {
								XWPFParagraph paragraph21 = document.createParagraph();

								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								run123.setText(reportBean.getWordData().get(per8 - 1).getRemarks());
							}
						}
					}
					if (reportBean.getWordData().get(per8 - 1).getNote() != null) {
						if (!reportBean.getWordData().get(per8 - 1).getNote().equalsIgnoreCase("NULL")
								&& !reportBean.getWordData().get(per8 - 1).getScopeCd().equalsIgnoreCase("TP")) {
							if (reportBean.getWordData().get(per8 - 1).getNote().contains("\n")) {

								String[] arrOfStr = reportBean.getWordData().get(per8 - 1).getNote().split("\n", 20);
								for (int x1 = 0; x1 < arrOfStr.length; x1++) {
									XWPFParagraph paragraph21 = document.createParagraph();

									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									run123.setText(arrOfStr[x1]);

								}
							} else {
								XWPFParagraph paragraph21 = document.createParagraph();

								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								run123.setText(reportBean.getWordData().get(per8 - 1).getNote());
							}
						}
					}
				}
				break;
			}
			catnm = reportBean.getWordData().get(per8).getCategoryName();
			seqno = reportBean.getWordData().get(per8).getSeqNo();
			ssid = reportBean.getWordData().get(per8).getSsId();
			scopecd = reportBean.getWordData().get(per8).getScopeCd();
			scopenm = reportBean.getWordData().get(per8).getScopeNm();
			itemid = reportBean.getWordData().get(per8).getItemId();
			itemname = reportBean.getWordData().get(per8).getItemNm();

			information = reportBean.getWordData().get(per8).getInformation();
			finalts = reportBean.getWordData().get(per8).getFinalts();
			subscopecd = reportBean.getWordData().get(per8).getSubScopeCd();
			description = reportBean.getWordData().get(per8).getDescription();
			equipment = reportBean.getWordData().get(per8).getEquipment();
			test = reportBean.getWordData().get(per8).getTest();
			equivalent = reportBean.getWordData().get(per8).getEquivalent();
			paneltype = reportBean.getWordData().get(per8).getPanelType();
			custtype = reportBean.getWordData().get(per8).getCustType();
			quanity = reportBean.getWordData().get(per8).getQuanti();
			cost = reportBean.getWordData().get(per8).getCost1();
			editflag = reportBean.getWordData().get(per8).getEditFlag();
			newcolvalflag = reportBean.getWordData().get(per8).getNewColValFlag();
			note = reportBean.getWordData().get(per8).getNote();
			noofmandays = reportBean.getWordData().get(per8).getNoOfManDays();
			remarks = reportBean.getWordData().get(per8).getRemarks();
			if (!scopenm.equals(prevscopenm)) {

				if (countercheck > 0) {
					if (reportBean.getWordData().get(per8 - 1).getRemarks() != null) {
						if (!reportBean.getWordData().get(per8 - 1).getRemarks().equalsIgnoreCase("NULL")
								&& !reportBean.getWordData().get(per8 - 1).getScopeCd().equalsIgnoreCase("SS")) {
							if (reportBean.getWordData().get(per8 - 1).getRemarks().contains("\n")) {

								String[] arrOfStr = reportBean.getWordData().get(per8 - 1).getRemarks().split("\n", 20);
								for (int x1 = 0; x1 < arrOfStr.length; x1++) {
									XWPFParagraph paragraph21 = document.createParagraph();

									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									run123.setText(arrOfStr[x1]);

								}
							} else {
								XWPFParagraph paragraph21 = document.createParagraph();

								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								run123.setText(reportBean.getWordData().get(per8 - 1).getRemarks());
							}
						}
					}
					if (reportBean.getWordData().get(per8 - 1).getNote() != null) {
						if (!reportBean.getWordData().get(per8 - 1).getNote().equalsIgnoreCase("NULL")
								&& !reportBean.getWordData().get(per8 - 1).getScopeCd().equalsIgnoreCase("TP")) {
							if (reportBean.getWordData().get(per8 - 1).getNote().contains("\n")) {

								String[] arrOfStr = reportBean.getWordData().get(per8 - 1).getNote().split("\n", 20);
								for (int x1 = 0; x1 < arrOfStr.length; x1++) {
									XWPFParagraph paragraph21 = document.createParagraph();

									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									run123.setText(arrOfStr[x1]);

								}
							} else {
								XWPFParagraph paragraph21 = document.createParagraph();

								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								run123.setText(reportBean.getWordData().get(per8 - 1).getNote());
							}
						}
					}
				}
				countercheck = countercheck + 1;

				count2 = 0;
				paragraph = document.createParagraph();
				run = paragraph.createRun();
				run.setText("");

				paragraph = document.createParagraph();
				run = paragraph.createRun();
				paragraph.setPageBreak(true);
				run.setText(catnm);
				run.setBold(true);

				run.setFontFamily("Cambria (Headings)");
				run.setFontSize(12);

			}

			if (itemid != 0) {
				// if(itemid!=previtemidper8)
				// {
				// if(previtemidper8!=0)
				// {
				// if(prevnote!=null )
				// {
				// if(prevnote.contains("\n")){
				//
				//
				// String[] arrOfStr = prevnote.split("\n", 20);
				// for(int x1=0;x1<arrOfStr.length;x1++)
				// {
				// XWPFParagraph paragraph21=document.createParagraph();
				//
				// //System.out.println(table.getNumberOfRows());
				// //paragraph21.setAlignment(ParagraphAlignment.CENTER);
				// XWPFRun run123 = paragraph21.createRun();
				// run123.setFontSize(11);
				// run123.setFontFamily("Cambria (Headings)");
				// run123.setText(arrOfStr[x1]);
				// run123.addBreak();
				// }
				// }
				// else
				// {
				// paragraph=document.createParagraph();
				//
				// run = paragraph.createRun();
				// run.setText(prevnote);
				//
				//
				//
				// run.setFontFamily("Cambria (Headings)");
				// run.setFontSize(11);
				// }
				// }
				// }
				// }

				if (scopecd.equals("SS")) {
					XWPFParagraph paragraphnew = null;
					XWPFRun runnew = null;
					if (information.equalsIgnoreCase("Supervision of Erection Commissioning")) {
						paragraphnew = document.createParagraph();
						run = paragraphnew.createRun();
						run.setText("A1. Supervision of Erection Commissioning");
						run.setFontSize(11);
						run.setBold(true);

						paragraphnew = document.createParagraph();

						run = paragraphnew.createRun();
						run.setText("No of Days" + "     :     " + finalts);
						run.setFontSize(11);
						if (reportBean.getWordData().get(per8).getRemarks().contains("\n")) {

							String[] arrOfStr = reportBean.getWordData().get(per8).getRemarks().split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = document.createParagraph();

								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								run123.setText(arrOfStr[x1]);

							}
						} else {
							XWPFParagraph paragraph21 = document.createParagraph();

							XWPFRun run123 = paragraph21.createRun();
							run123.setFontSize(11);
							run123.setText(reportBean.getWordData().get(per8).getRemarks());
						}

						// run.setBold(true);

					}

					// if(remarks.contains("\n")){
					//
					// String[] arrOfStr = remarks.split("\n", 20);
					// for(int x1=0;x1<arrOfStr.length;x1++)
					// {
					// XWPFParagraph paragraph21=document.createParagraph();
					//
					// XWPFRun run123 = paragraph21.createRun();
					// run123.setFontSize(11);
					// run123.setText(arrOfStr[x1]);
					//
					// }
					// }
					// else
					// {
					// XWPFParagraph paragraph21=document.createParagraph();
					//
					//
					// XWPFRun run123 = paragraph21.createRun();
					// run123.setFontSize(11);
					// run123.setText(remarks);
					// }

					//
					//
					//
					// }
					if (information.equalsIgnoreCase(
							"Erection Commissioning & Supervision of Erection Commissioning not in TTL Scope")) {
						paragraphnew = document.createParagraph();

						run = paragraphnew.createRun();
						run.setText(
								"A1. Erection Commissioning & Supervision of Erection Commissioning not in TTL Scope");
						run.setFontSize(11);
						run.setBold(true);

					}
					//
					if (information.equalsIgnoreCase("Erection & Commissioning")) {
						paragraphnew = document.createParagraph();

						run = paragraphnew.createRun();
						run.setText("A1. Erection & Commissioning");
						run.setFontSize(11);
						run.setBold(true);

						if (reportBean.getWordData().get(per8).getRemarks().contains("\n")) {

							String[] arrOfStr = reportBean.getWordData().get(per8).getRemarks().split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = document.createParagraph();

								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								run123.setText(arrOfStr[x1]);

							}
						} else {
							XWPFParagraph paragraph21 = document.createParagraph();

							XWPFRun run123 = paragraph21.createRun();
							run123.setFontSize(11);
							run123.setText(reportBean.getWordData().get(per8).getRemarks());
						}

					}
					
					// if(remarks.contains("\n")){
					//
					// String[] arrOfStr = remarks.split("\n", 20);
					// for(int x1=0;x1<arrOfStr.length;x1++)
					// {
					// XWPFParagraph paragraph21=document.createParagraph();
					//
					// XWPFRun run123 = paragraph21.createRun();
					// run123.setFontSize(11);
					// run123.setText(arrOfStr[x1]);
					//
					// }
					// }
					// else
					// {
					// XWPFParagraph paragraph21=document.createParagraph();
					//
					//
					// XWPFRun run123 = paragraph21.createRun();
					// run123.setFontSize(11);
					// run123.setText(remarks);
					// }

					// }
					paragraphnew = document.createParagraph();

					run = paragraphnew.createRun();
					run.setText("A2.Service Exclusions");
					run.setFontSize(11);
					run.setBold(true);
					if (reportBean.getWordData().get(per8).getDescription().contains("\n")) {

						String[] arrOfStr = reportBean.getWordData().get(per8).getDescription().split("\n", 20);
						for (int x1 = 0; x1 < arrOfStr.length; x1++) {
							XWPFParagraph paragraph21 = document.createParagraph();

							XWPFRun run123 = paragraph21.createRun();
							run123.setFontSize(11);
							run123.setText(arrOfStr[x1]);

						}
					} else {
						XWPFParagraph paragraph21 = document.createParagraph();

						XWPFRun run123 = paragraph21.createRun();
						run123.setFontSize(11);
						run123.setText(reportBean.getWordData().get(per8).getDescription());
					}

				}

				if (scopecd.equals("QA")) {
					if (counter55 == 0) {
						if (count2 != 0) {

							paragraph = document.createParagraph();
							run = paragraph.createRun();
							run.setText("");
						}

						run.setFontFamily("Cambria (Headings)");
						run.setFontSize(11);
						table = document.createTable();
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);
						setTableAlign(table, ParagraphAlignment.CENTER);// Creating
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(3000));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(3000));
						count2 = 1;

						// table
						// only once

						tablerow34 = table.getRow(0);
					}
					if (tablerow34 != null) {

						// tablerow34.getCell(0).setText("Equipment");

						XWPFParagraph paragraph2345 = tablerow34.getCell(0).getParagraphs().get(0);
						paragraph2345.setIndentationLeft(100);
						paragraph2345.setIndentationRight(100);
						paragraph2345.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run1234 = paragraph2345.createRun();
						run1234.setFontSize(11);
						// run1234.setFontFamily("Cambria (Headings)");
						run1234.setText("Equipment");
						run1234.setBold(true);
						tablerow34.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
						/////
						tablerow34.addNewTableCell();
						XWPFParagraph paragraph23451 = tablerow34.getCell(1).getParagraphs().get(0);
						paragraph23451.setIndentationLeft(100);
						paragraph23451.setIndentationRight(100);
						paragraph23451.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run12341 = paragraph23451.createRun();
						run12341.setFontSize(11);
						// run12341.setFontFamily("Cambria (Headings)");
						run12341.setText("Test");
						run12341.setBold(true);
						tablerow34.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
						// tablerow34.getCell(0).setColor("808080");
						// tablerow34.getCell(1).setColor("808080");

						XWPFTableRow tableRowOne = table.createRow();
						if (equipment.contains("\n")) {

							String[] arrOfStr = equipment.split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = tableRowOne.getCell(0).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(arrOfStr[x1]);
								run123.addBreak();
								logger.info("rhs word start");
								logger.info(arrOfStr[x1]);
								logger.info("rhs word end");
							}
						} else {
							tableRowOne.getCell(0).setText(equipment);
						}
						if (test.contains("\n")) {

							String[] arrOfStr = test.split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = tableRowOne.getCell(1).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(arrOfStr[x1]);
								run123.addBreak();
								logger.info("rhs word start");
								logger.info(arrOfStr[x1]);
								logger.info("rhs word end");
							}
						} else {
							tableRowOne.getCell(1).setText(test);
						}

						tablerow34 = null;
					} else {
						XWPFTableRow tableRowOne = table.createRow();
						if (equipment.contains("\n")) {

							String[] arrOfStr = equipment.split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = tableRowOne.getCell(0).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(arrOfStr[x1]);
								run123.addBreak();
								logger.info("rhs word start");
								logger.info(arrOfStr[x1]);
								logger.info("rhs word end");
							}
						} else {
							tableRowOne.getCell(0).setText(equipment);
						}
						if (test.contains("\n")) {

							String[] arrOfStr = test.split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = tableRowOne.getCell(1).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(arrOfStr[x1]);
								run123.addBreak();
								logger.info("rhs word start");
								logger.info(arrOfStr[x1]);
								logger.info("rhs word end");
							}
						} else {
							tableRowOne.getCell(1).setText(test);
						}

					}
					counter55 = counter55 + 1;

				}
				if (scopecd.equals("DR")) {
					if (counter56 == 0) {
						if (count2 != 0) {

							paragraph = document.createParagraph();
							run = paragraph.createRun();
							run.setText("");
						}

						run.setFontFamily("Cambria (Headings)");
						run.setFontSize(11);
						// if(reportBean.getWordData().get(per8+1).getItemId()!=itemid)
						// {
						// if(note.contains("\n")){
						//
						// String[] arrOfStr = note.split("\n", 20);
						// for(int x1=0;x1<arrOfStr.length;x1++)
						// {
						// XWPFParagraph paragraph21=document.createParagraph();
						//
						// //System.out.println(table.getNumberOfRows());
						// //paragraph21.setAlignment(ParagraphAlignment.CENTER);
						// XWPFRun run123 = paragraph21.createRun();
						// run123.setFontSize(11);
						// run123.setFontFamily("Cambria (Headings)");
						// run123.setText(arrOfStr[x1]);
						// run123.addBreak();
						// logger.info("rhs word start");
						// logger.info(arrOfStr[x1]);
						// logger.info("rhs word end");
						// }
						// }

						// }

						table = document.createTable();
						setTableAlign(table, ParagraphAlignment.CENTER);// Creating
						// table
						// only once
						count2 = 1;
						tablerow34 = table.getRow(0);
					}
					if (tablerow34 != null) {

						// tablerow34.getCell(0).setText("DRAWING /
						// DOCUMENTATION DESCRIPTION");
						XWPFParagraph paragraph2345212 = tablerow34.getCell(0).getParagraphs().get(0);
						paragraph2345212.setIndentationLeft(100);
						paragraph2345212.setIndentationRight(100);
						paragraph2345212.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run1234212 = paragraph2345212.createRun();
						run1234212.setFontSize(11);
						// run1234212.setFontFamily("Cambria (Headings)");
						run1234212.setText("Drawing / Documentation Description");
						run1234212.setBold(true);
						tablerow34.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
						/////
						tablerow34.addNewTableCell();
						XWPFParagraph paragraph234521 = tablerow34.getCell(1).getParagraphs().get(0);
						paragraph234521.setIndentationLeft(100);
						paragraph234521.setIndentationRight(100);
						paragraph234521.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run123421 = paragraph234521.createRun();
						run123421.setFontSize(11);
						// run123421.setFontFamily("Cambria (Headings)");
						run123421.setText("Information (I)  / Approval (A)");
						run123421.setBold(true);
						tablerow34.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);

						tablerow34.addNewTableCell();
						XWPFParagraph paragraph23452 = tablerow34.getCell(2).getParagraphs().get(0);
						paragraph23452.setIndentationLeft(100);
						paragraph23452.setIndentationRight(100);
						paragraph23452.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run12342 = paragraph23452.createRun();
						run12342.setFontSize(11);
						// run12342.setFontFamily("Cambria (Headings)");
						run12342.setText("Final");
						run12342.setBold(true);
						tablerow34.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);

						// tablerow34.getCell(0).setColor("808080");
						// tablerow34.getCell(1).setColor("808080");
						// tablerow34.getCell(2).setColor("808080");
						XWPFTableRow tableRowOne = table.createRow();
						if (description.contains("\n")) {

							String[] arrOfStr = description.split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = tableRowOne.getCell(0).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(arrOfStr[x1]);
								run123.addBreak();
								logger.info("rhs word start");
								logger.info(arrOfStr[x1]);
								logger.info("rhs word end");
							}
						} else {
							tableRowOne.getCell(0).setText(description);
						}
						if (information.contains("\n")) {

							String[] arrOfStr = information.split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = tableRowOne.getCell(1).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(arrOfStr[x1]);
								run123.addBreak();
								logger.info("rhs word start");
								logger.info(arrOfStr[x1]);
								logger.info("rhs word end");
							}
						} else {
							tableRowOne.getCell(1).setText(information);
						}

						tableRowOne.getCell(2).setText(finalts);

						tablerow34 = null;
					} else {
						XWPFTableRow tableRowOne = table.createRow();
						if (description.contains("\n")) {

							String[] arrOfStr = description.split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = tableRowOne.getCell(0).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(arrOfStr[x1]);
								run123.addBreak();
								logger.info("rhs word start");
								logger.info(arrOfStr[x1]);
								logger.info("rhs word end");
							}
						} else {
							tableRowOne.getCell(0).setText(description);
						}
						if (information.contains("\n")) {

							String[] arrOfStr = information.split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = tableRowOne.getCell(1).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(arrOfStr[x1]);
								run123.addBreak();
								logger.info("rhs word start");
								logger.info(arrOfStr[x1]);
								logger.info("rhs word end");
							}
						} else {
							tableRowOne.getCell(1).setText(information);
						}
						tableRowOne.getCell(2).setText(finalts);

					}
					counter56 = counter56 + 1;

				}
				if (scopecd.equals("TP") && itemname != null) {
					slno = slno + 1;

					logger.info("TP");
					logger.info(itemname);
					if (itemid != previtemidper8) {
						slno = 1;
						if (count2 == 0) {

							paragraph = document.createParagraph();
							run = paragraph.createRun();
							run.setText(
									"The offer is complete within the following limits beyond which Purchaser shall arrange any and all equipments:");

						}
						paragraph = document.createParagraph();
						run = paragraph.createRun();
						run.setText(itemname);
						run.setBold(true);

						run.setFontFamily("Cambria (Headings)");
						run.setFontSize(11);
						table = document.createTable();
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(500));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(8500));
						setTableAlign(table, ParagraphAlignment.CENTER);// Creating
						CTTblPr tblpro = table.getCTTbl().getTblPr();

						CTTblBorders borders = tblpro.addNewTblBorders();
						borders.addNewBottom().setVal(STBorder.NONE);
						borders.addNewLeft().setVal(STBorder.NONE);
						borders.addNewRight().setVal(STBorder.NONE);
						borders.addNewTop().setVal(STBorder.NONE);
						// also inner borders
						borders.addNewInsideH().setVal(STBorder.NONE);
						borders.addNewInsideV().setVal(STBorder.NONE);
						setTableAlign(table, ParagraphAlignment.LEFT);// Creating
						// table
						// only once
						tablerow34 = table.getRow(0);
						count2 = 1;

					}
					if (tablerow34 != null) {

						// tablerow34.getCell(0).setText("Description");

						// XWPFTableRow tableRowOne = table.createRow();
						///
						tablerow34.getCell(0).setText(String.valueOf(slno));
						tablerow34.addNewTableCell();
						if (description.contains("\n")) {

							String[] arrOfStr = description.split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = tablerow34.getCell(1).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(arrOfStr[x1]);
								run123.addBreak();
								logger.info("rhs word start");
								logger.info(arrOfStr[x1]);
								logger.info("rhs word end");
							}
						}

						else {
							tablerow34.getCell(1).setText(description);

						}
						///

						tablerow34 = null;
					} else {
						XWPFTableRow tableRowOne = table.createRow();
						///
						tableRowOne.getCell(0).setText(String.valueOf(slno));
						if (description.contains("\n")) {

							String[] arrOfStr = description.split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = tableRowOne.getCell(1).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(arrOfStr[x1]);
								run123.addBreak();
								logger.info("rhs word start");
								logger.info(arrOfStr[x1]);
								logger.info("rhs word end");
							}
						} else {
							tableRowOne.getCell(1).setText(description);

						}
						///

					}
				}
				if (scopecd.equals("EL") && itemname != null) {
					slno = slno + 1;
					if (itemid != previtemidper8) {
						slno = 1;
						if (count2 == 0) {

							paragraph = document.createParagraph();
							run = paragraph.createRun();
							run.setText(
									"The following items are not included in seller's scope of supply and shall be arranged by purchaser:");
						}
						// if(count2!=0)
						// {
						//
						// paragraph=document.createParagraph();
						// run = paragraph.createRun();
						// run.setText("");
						// }

						paragraph = document.createParagraph();
						run = paragraph.createRun();

						run.setText(itemname);
						run.setBold(true);

						run.setFontFamily("Cambria (Headings)");
						run.setFontSize(11);
						table = document.createTable();
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(500));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(8500));
						setTableAlign(table, ParagraphAlignment.CENTER);// Creating
						CTTblPr tblpro = table.getCTTbl().getTblPr();

						CTTblBorders borders = tblpro.addNewTblBorders();
						borders.addNewBottom().setVal(STBorder.NONE);
						borders.addNewLeft().setVal(STBorder.NONE);
						borders.addNewRight().setVal(STBorder.NONE);
						borders.addNewTop().setVal(STBorder.NONE);

						// also inner borders
						borders.addNewInsideH().setVal(STBorder.NONE);
						borders.addNewInsideV().setVal(STBorder.NONE);

						// table
						// only once
						count2 = 1;
						tablerow34 = table.getRow(0);
					}
					if (tablerow34 != null) {

						///
						// XWPFParagraph
						// paragraph2345=tablerow34.getCell(0).getParagraphs().get(0);
						// paragraph2345.setIndentationLeft(100);
						// paragraph2345.setIndentationRight(100);
						// paragraph2345.setAlignment(ParagraphAlignment.CENTER);
						// XWPFRun run1234 = paragraph2345.createRun();
						// run1234.setFontSize(11);
						// run1234.setFontFamily("Cambria (Headings)");
						// run1234.setText("SL.NO");
						// run1234.setBold(true);
						// tablerow34.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
						/////

						// tablerow34.addNewTableCell();
						// ///
						// XWPFParagraph
						// paragraph23451=tablerow34.getCell(1).getParagraphs().get(0);
						// paragraph23451.setIndentationLeft(100);
						// paragraph23451.setIndentationRight(100);
						// paragraph23451.setAlignment(ParagraphAlignment.CENTER);
						// XWPFRun run12341 = paragraph23451.createRun();
						// run12341.setFontSize(11);
						// run12341.setFontFamily("Cambria (Headings)");
						// run12341.setText("Description");
						// run12341.setBold(true);
						// tablerow34.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
						/////

						////
						// tablerow34.getCell(0).setColor("808080");
						// tablerow34.getCell(1).setColor("808080");

						// XWPFTableRow tableRowOne = table.createRow();
						tablerow34.getCell(0).setText(String.valueOf(slno));
						tablerow34.addNewTableCell();
						///
						if (description.contains("\n")) {

							String[] arrOfStr = description.split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = tablerow34.getCell(1).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(arrOfStr[x1]);
								run123.addBreak();
								logger.info("rhs word start");
								logger.info(arrOfStr[x1]);
								logger.info("rhs word end");
							}
						} else {
							tablerow34.getCell(1).setText(description);

						}
						///

						tablerow34 = null;
					} else {
						XWPFTableRow tableRowOne = table.createRow();
						tableRowOne.getCell(0).setText(String.valueOf(slno));
						///
						if (description.contains("\n")) {

							String[] arrOfStr = description.split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = tableRowOne.getCell(1).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(arrOfStr[x1]);
								run123.addBreak();
								logger.info("rhs word start");
								logger.info(arrOfStr[x1]);
								logger.info("rhs word end");
							}
						} else {
							tableRowOne.getCell(1).setText(description);

						}
						///

					}

				}
				if (scopecd.equals("SSL") && itemname != null) {
					slno = slno + 1;
					if (itemid != previtemidper8) {
						slno = 1;
						if (count2 != 0) {

							paragraph = document.createParagraph();
							run = paragraph.createRun();
							run.setText("");
						}
						paragraph = document.createParagraph();
						run = paragraph.createRun();
						run.setBold(true);

						run.setText(itemname);

						run.setFontFamily("Cambria (Headings)");
						run.setFontSize(11);
						table = document.createTable();
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(2500));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(5000));

						setTableAlign(table, ParagraphAlignment.CENTER);// Creating
						// table
						// only once
						count2 = 1;
						tablerow34 = table.getRow(0);
					}
					if (tablerow34 != null) {

						// tablerow34.getCell(0).setText("SL.NO");

						XWPFParagraph paragraph2345 = tablerow34.getCell(0).getParagraphs().get(0);
						paragraph2345.setIndentationLeft(100);
						paragraph2345.setIndentationRight(100);
						paragraph2345.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run1234 = paragraph2345.createRun();
						run1234.setFontSize(11);
						// run1234.setFontFamily("Cambria (Headings)");
						run1234.setText("Sl.no");
						run1234.setBold(true);
						tablerow34.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
						/////
						tablerow34.addNewTableCell();
						XWPFParagraph paragraph23451 = tablerow34.getCell(1).getParagraphs().get(0);
						paragraph23451.setIndentationLeft(100);
						paragraph23451.setIndentationRight(100);
						paragraph23451.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run12341 = paragraph23451.createRun();
						run12341.setFontSize(11);
						// run12341.setFontFamily("Cambria (Headings)");
						run12341.setText("Equipment");
						run12341.setBold(true);
						tablerow34.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
						tablerow34.addNewTableCell();
						XWPFParagraph paragraph23452 = tablerow34.getCell(2).getParagraphs().get(0);
						paragraph23452.setIndentationLeft(100);
						paragraph23452.setIndentationRight(100);
						paragraph23452.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run12342 = paragraph23452.createRun();
						run12342.setFontSize(11);
						// run12342.setFontFamily("Cambria (Headings)");
						run12342.setText("Equivalent");
						run12342.setBold(true);
						tablerow34.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);

						// tablerow34.getCell(0).setColor("808080");
						// tablerow34.getCell(1).setColor("808080");
						// tablerow34.getCell(2).setColor("808080");

						XWPFTableRow tableRowOne = table.createRow();
						tableRowOne.getCell(0).setText(String.valueOf(slno));
						///
						if (equipment.contains("\n")) {

							String[] arrOfStr = equipment.split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = tableRowOne.getCell(1).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(arrOfStr[x1]);
								run123.addBreak();
								logger.info("rhs word start");
								logger.info(arrOfStr[x1]);
								logger.info("rhs word end");
							}
						} else {
							tableRowOne.getCell(1).setText(equipment);

						}

						///
						///
						if (equivalent.contains("\n")) {

							String[] arrOfStr = equivalent.split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = tableRowOne.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(arrOfStr[x1]);
								run123.addBreak();
								logger.info("rhs word start");
								logger.info(arrOfStr[x1]);
								logger.info("rhs word end");
							}
						} else {
							tableRowOne.getCell(2).setText(equivalent);

						}

						///

						tablerow34 = null;
					} else {

						XWPFTableRow tableRowOne = table.createRow();
						tableRowOne.getCell(0).setText(String.valueOf(slno));
						///
						if (equipment.contains("\n")) {

							String[] arrOfStr = equipment.split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = tableRowOne.getCell(1).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(arrOfStr[x1]);
								run123.addBreak();
								logger.info("rhs word start");
								logger.info(arrOfStr[x1]);
								logger.info("rhs word end");
							}
						} else {
							tableRowOne.getCell(1).setText(equipment);

						}

						///
						///
						if (equivalent.contains("\n")) {

							String[] arrOfStr = equivalent.split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = tableRowOne.getCell(2).getParagraphs().get(0);

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(arrOfStr[x1]);
								run123.addBreak();
								logger.info("rhs word start");
								logger.info(arrOfStr[x1]);
								logger.info("rhs word end");
							}
						} else {
							tableRowOne.getCell(2).setText(equivalent);

						}

						///

					}

				}
				if (scopecd.equals("SP")) {
					slno = slno + 1;

					if (itemid != previtemidper8) {
						slno = 1;
						if (count2 != 0) {
							paragraph = document.createParagraph();
							run = paragraph.createRun();
							run.setText("");
						}

						paragraph = document.createParagraph();
						run = paragraph.createRun();
						run.setBold(true);
						run.setText(itemname);

						run.setFontFamily("Cambria (Headings)");
						run.setFontSize(11);
						table = document.createTable();
						table.setCellMargins(0, 20, 0, 20);
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);
						setTableAlign(table, ParagraphAlignment.CENTER);// Creating
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(3000));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1200));

						// table
						// only once
						count2 = 1;
						tablerow34 = table.getRow(0);
					}
					if (tablerow34 != null) {

						///
						XWPFParagraph paragraph2345 = tablerow34.getCell(0).getParagraphs().get(0);
						paragraph2345.setIndentationLeft(100);
						paragraph2345.setIndentationRight(100);
						paragraph2345.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run1234 = paragraph2345.createRun();
						run1234.setFontSize(11);
						// run1234.setFontFamily("Cambria (Headings)");
						run1234.setText("Sl.no");
						run1234.setBold(true);
						tablerow34.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
						/////
						tablerow34.addNewTableCell();
						XWPFParagraph paragraph23451 = tablerow34.getCell(1).getParagraphs().get(0);
						paragraph23451.setIndentationLeft(100);
						paragraph23451.setIndentationRight(100);
						paragraph23451.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run12341 = paragraph23451.createRun();
						run12341.setFontSize(11);
						// run12341.setFontFamily("Cambria (Headings)");
						run12341.setText("Description");
						run12341.setBold(true);
						tablerow34.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
						tablerow34.addNewTableCell();
						XWPFParagraph paragraph23452 = tablerow34.getCell(2).getParagraphs().get(0);
						paragraph23452.setIndentationLeft(100);
						paragraph23452.setIndentationRight(100);
						paragraph23452.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run12342 = paragraph23452.createRun();
						run12342.setFontSize(11);
						// run12342.setFontFamily("Cambria (Headings)");
						run12342.setText("Quantity");
						run12342.setBold(true);
						tablerow34.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);

						// tablerow34.addNewTableCell().setText("Description");
						// tablerow34.addNewTableCell().setText("Quantity");
						// tablerow34.getCell(0).setColor("808080");
						// tablerow34.getCell(1).setColor("808080");
						// tablerow34.getCell(2).setColor("808080");

						XWPFTableRow tableRowOne = table.createRow();
						tableRowOne.getCell(0).removeParagraph(0);
						tableRowOne.getCell(0).setText(String.valueOf(slno));

						///
						if (description.contains("\n")) {
							tableRowOne.getCell(1).removeParagraph(0);

							String[] arrOfStr = description.split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = tableRowOne.getCell(1).getParagraphs().get(0);
								paragraph21.setSpacingAfter(0);
								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(arrOfStr[x1]);
								run123.addBreak();
								logger.info("rhs word start");
								logger.info(arrOfStr[x1]);
								logger.info("rhs word end");
							}
						} else {
							tableRowOne.getCell(1).removeParagraph(0);

							tableRowOne.getCell(1).setText(description);

						}
						///
						tableRowOne.getCell(2).removeParagraph(0);

						tableRowOne.getCell(2).setText(quanity);

						tablerow34 = null;
					} else {
						XWPFTableRow tableRowOne = table.createRow();
						tableRowOne.getCell(0).removeParagraph(0);

						tableRowOne.getCell(0).setText(String.valueOf(slno));
						///
						if (description.contains("\n")) {
							tableRowOne.getCell(1).removeParagraph(0);

							String[] arrOfStr = description.split("\n", 20);
							for (int x1 = 0; x1 < arrOfStr.length; x1++) {
								XWPFParagraph paragraph21 = tableRowOne.getCell(1).getParagraphs().get(0);
								// paragraph21.setSpacingAfter(0);
								// paragraph21.setSpacingBefore(0);

								// System.out.println(table.getNumberOfRows());
								// paragraph21.setAlignment(ParagraphAlignment.CENTER);
								XWPFRun run123 = paragraph21.createRun();
								run123.setFontSize(11);
								// run123.setFontFamily("Cambria (Headings)");
								run123.setText(arrOfStr[x1]);
								run123.addBreak();
								logger.info("rhs word start");
								logger.info(arrOfStr[x1]);
								logger.info("rhs word end");
							}
						} else {
							tableRowOne.getCell(1).removeParagraph(0);

							tableRowOne.getCell(1).setText(description);

						}
						///
						tableRowOne.getCell(2).removeParagraph(0);

						tableRowOne.getCell(2).setText(quanity);

					}
				}
				if (per8 + 1 == reportBean.getWordData().size()) {
					if (countercheck > 0) {
						if (reportBean.getWordData().get(per8).getRemarks() != null) {
							if (!reportBean.getWordData().get(per8).getRemarks().equalsIgnoreCase("NULL")
									&& !reportBean.getWordData().get(per8).getScopeCd().equalsIgnoreCase("SS")) {
								if (reportBean.getWordData().get(per8).getRemarks().contains("\n")) {

									String[] arrOfStr = reportBean.getWordData().get(per8).getRemarks().split("\n", 20);
									for (int x1 = 0; x1 < arrOfStr.length; x1++) {
										XWPFParagraph paragraph21 = document.createParagraph();

										XWPFRun run123 = paragraph21.createRun();
										run123.setFontSize(11);
										run123.setText(arrOfStr[x1]);

									}
								} else {
									XWPFParagraph paragraph21 = document.createParagraph();

									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									run123.setText(reportBean.getWordData().get(per8).getRemarks());
								}
							}
						}
						if (reportBean.getWordData().get(per8).getNote() != null) {
							if (!reportBean.getWordData().get(per8).getNote().equalsIgnoreCase("NULL")
									&& !reportBean.getWordData().get(per8).getScopeCd().equalsIgnoreCase("TP")) {
								if (reportBean.getWordData().get(per8).getNote().contains("\n")) {

									String[] arrOfStr = reportBean.getWordData().get(per8).getNote().split("\n", 20);
									for (int x1 = 0; x1 < arrOfStr.length; x1++) {
										XWPFParagraph paragraph21 = document.createParagraph();

										XWPFRun run123 = paragraph21.createRun();
										run123.setFontSize(11);
										run123.setText(arrOfStr[x1]);

									}
								} else {
									XWPFParagraph paragraph21 = document.createParagraph();

									XWPFRun run123 = paragraph21.createRun();
									run123.setFontSize(11);
									run123.setText(reportBean.getWordData().get(per8).getNote());
								}
							}
						}
					}

				}
			}
			previtemidper8 = itemid;
			prevscopenm = scopenm;
			prevnote = note;
		}

		// ATTACHMENT

		int per9 = 0;
		int previtemidper9 = 0;
	
		String prevscopename = null;
		String previnformation = null;
		String prevremarks = null;
		String prevscopecode = null;
		String prevtechremarks = null;
		int countercheckper9 = 0;
		for (per9 = per8; per9 < reportBean.getWordData().size(); per9++) {
			int itemid = 0;
			int count9 = 0;
			int counter56per9= 0;
			int count10 = 0;
			int counter56per10= 0;
			XWPFTableRow tableRowOne1 = null;
			if (per9 == QuotationDaoImpl.size21)
				break;

			String scopename = reportBean.getWordData().get(per9).getScopeNm();
			String scopecode = reportBean.getWordData().get(per9).getScopeCd();
			String techremarks = reportBean.getWordData().get(per9).getRemarks();
			String information = reportBean.getWordData().get(per9).getInformation();

			String remarks = reportBean.getWordData().get(per9).getRemarks();
			int slnum = reportBean.getWordData().get(per9).getSlNum();

			int ssid= reportBean.getWordData().get(per9).getSsId();
			String filename = reportBean.getWordData().get(per9).getFileName();

			if (!scopename.equals(prevscopename)) {
				
				paragraph = document.createParagraph();
				run = paragraph.createRun();
				run.setText("");
				
				paragraph = document.createParagraph();
				run = paragraph.createRun();
				run.setText("");
				
				paragraph = document.createParagraph();
				run = paragraph.createRun();
				run.setText(scopename);
				run.setBold(true);
				run.setFontFamily("Cambria (Headings)");
				run.setFontSize(12);

			}
if (!information.equals(previnformation)) {
	paragraph = document.createParagraph();
				run = paragraph.createRun();
				
				run.setText(information);
//				run.setBold(true);

				run.setFontFamily("Cambria (Headings)");
				run.setFontSize(12);

			}
if (!remarks.equals(prevremarks)) {
	paragraph = document.createParagraph();
				run = paragraph.createRun();
				
				run.setText("Remarks:	"+remarks);
//				run.setBold(true);

				run.setFontFamily("Cambria (Headings)");
				run.setFontSize(12);

			}
				if (scopecode.equals("TD")) {
					if (!scopename.equals(prevscopename)) {

					if (counter56per9 == 0) {
						if (count9 != 0) {

							paragraph = document.createParagraph();
							run = paragraph.createRun();
							run.setText("");
						}

						run.setFontFamily("Cambria (Headings)");
						run.setFontSize(11);
						
						table = document.createTable();
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(2500));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(5000));

						setTableAlign(table, ParagraphAlignment.CENTER);// Creating
						count9 = 1;
						tableRowOne1 = table.getRow(0);
					}
					}
					if (tableRowOne1 != null) {

						XWPFParagraph paragraph2345212 = tableRowOne1.getCell(0).getParagraphs().get(0);
						paragraph2345212.setIndentationLeft(100);
						paragraph2345212.setIndentationRight(100);
						paragraph2345212.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run1234212 = paragraph2345212.createRun();
						run1234212.setFontSize(11);
						run1234212.setText("SL.No.");
						run1234212.setBold(true);
						tableRowOne1.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
						tableRowOne1.addNewTableCell();
						XWPFParagraph paragraph234521 = tableRowOne1.getCell(1).getParagraphs().get(0);
						paragraph234521.setIndentationLeft(100);
						paragraph234521.setIndentationRight(100);
						paragraph234521.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run123421 = paragraph234521.createRun();
						run123421.setFontSize(11);
						run123421.setText("Filename");
						run123421.setBold(true);
						tableRowOne1.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);

						
						XWPFTableRow tableRowOne = table.createRow();
						tableRowOne.getCell(0).setText(String.valueOf(slnum));
						tableRowOne.getCell(1).setText(filename);
						
						tableRowOne1 = null;
					} else {
						XWPFTableRow tableRowOne = table.createRow();
						
						tableRowOne.getCell(0).setText(String.valueOf(slnum));
						tableRowOne.getCell(1).setText(filename);
						 
						

					}
					
					
					counter56per9= counter56per9 + 1;

					

				}
		

				
				if (scopecode.equals("CD")) {
					if (!scopename.equals(prevscopename)) {

					if (counter56per10 == 0) {
						if (count10 != 0) {

							paragraph = document.createParagraph();
							run = paragraph.createRun();
							run.setText("");
						}

						run.setFontFamily("Cambria (Headings)");
						run.setFontSize(11);
						
						table = document.createTable();
						CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
						type1.setType(STTblLayoutType.FIXED);
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(2500));
						table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(5000));

						setTableAlign(table, ParagraphAlignment.CENTER);// Creating
						count10 = 1;
						tableRowOne1 = table.getRow(0);
					}
					}
					if (tableRowOne1 != null) {

						XWPFParagraph paragraph2345212 = tableRowOne1.getCell(0).getParagraphs().get(0);
						paragraph2345212.setIndentationLeft(100);
						paragraph2345212.setIndentationRight(100);
						paragraph2345212.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run1234212 = paragraph2345212.createRun();
						run1234212.setFontSize(11);
						run1234212.setText("SL.No.");
						run1234212.setBold(true);
						tableRowOne1.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
						tableRowOne1.addNewTableCell();
						XWPFParagraph paragraph234521 = tableRowOne1.getCell(1).getParagraphs().get(0);
						paragraph234521.setIndentationLeft(100);
						paragraph234521.setIndentationRight(100);
						paragraph234521.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run123421 = paragraph234521.createRun();
						run123421.setFontSize(11);
						run123421.setText("Filename");
						run123421.setBold(true);
						tableRowOne1.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);

						
						XWPFTableRow tableRowOne = table.createRow();
						tableRowOne.getCell(0).setText(String.valueOf(slnum));
						tableRowOne.getCell(1).setText(filename);
						
						tableRowOne1 = null;
					} else {
						XWPFTableRow tableRowOne = table.createRow();
						
						tableRowOne.getCell(0).setText(String.valueOf(slnum));
						tableRowOne.getCell(1).setText(filename);
						 
						

					}
					
					counter56per10= counter56per10 + 1;
				}
				
				
				
				prevscopename = scopename;
				previnformation = information;
				prevremarks = remarks;
		}
					
			
			
	}

	private static void addCustomHeadingStyle(XWPFDocument document, String strStyleId, int headingLevel) {

		CTStyle ctStyle = CTStyle.Factory.newInstance();
		ctStyle.setStyleId(strStyleId);

		CTString styleName = CTString.Factory.newInstance();
		styleName.setVal(strStyleId);
		ctStyle.setName(styleName);

		CTDecimalNumber indentNumber = CTDecimalNumber.Factory.newInstance();
		indentNumber.setVal(BigInteger.valueOf(headingLevel));

		// lower number > style is more prominent in the formats bar
		ctStyle.setUiPriority(indentNumber);

		CTOnOff onoffnull = CTOnOff.Factory.newInstance();
		ctStyle.setUnhideWhenUsed(onoffnull);

		// style shows up in the formats bar
		ctStyle.setQFormat(onoffnull);

		// style defines a heading of the given level
		CTPPr ppr = CTPPr.Factory.newInstance();
		ppr.setOutlineLvl(indentNumber);
		ctStyle.setPPr(ppr);

		XWPFStyle style = new XWPFStyle(ctStyle);

		// is a null op if already defined
		XWPFStyles styles = document.createStyles();

		style.setType(STStyleType.PARAGRAPH);
		styles.addStyle(style);

	}

	static void mergeCellHorizontally(XWPFTable table, int row, int fromCol, int toCol) {
		for (int colIndex = fromCol; colIndex <= toCol; colIndex++) {
			XWPFTableCell cell = table.getRow(row).getCell(colIndex);
			CTHMerge hmerge = CTHMerge.Factory.newInstance();
			if (colIndex == fromCol) {
				// The first merged cell is set with RESTART merge value
				hmerge.setVal(STMerge.RESTART);
			} else {
				// Cells which join (merge) the first one, are set with CONTINUE
				hmerge.setVal(STMerge.CONTINUE);
				// and the content should be removed
				for (int i = cell.getParagraphs().size(); i > 0; i--) {
					cell.removeParagraph(0);
				}
				cell.addParagraph();
			}
			// Try getting the TcPr. Not simply setting an new one every time.
			CTTcPr tcPr = cell.getCTTc().getTcPr();
			if (tcPr != null) {
				tcPr.setHMerge(hmerge);
			} else {
				// only set an new TcPr if there is not one already
				tcPr = CTTcPr.Factory.newInstance();
				tcPr.setHMerge(hmerge);
				cell.getCTTc().setTcPr(tcPr);
			}
		}
	}

	public static void setTableAlign(XWPFTable table, ParagraphAlignment align) {
		CTTblPr tblPr = table.getCTTbl().getTblPr();
		CTJc jc = (tblPr.isSetJc() ? tblPr.getJc() : tblPr.addNewJc());
		STJc.Enum en = STJc.Enum.forInt(align.getValue());
		jc.setVal(en);
	}

}
