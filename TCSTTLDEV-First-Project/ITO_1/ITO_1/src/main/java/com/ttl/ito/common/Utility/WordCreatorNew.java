//
//package com.ttl.ito.common.Utility;
//
//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.apache.poi.xwpf.usermodel.Borders;
//import org.apache.poi.xwpf.usermodel.BreakType;
//import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
//import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.apache.poi.xwpf.usermodel.XWPFRun;
//import org.apache.poi.xwpf.usermodel.XWPFStyle;
//import org.apache.poi.xwpf.usermodel.XWPFStyles;
//import org.apache.poi.xwpf.usermodel.XWPFTable;
//import org.apache.poi.xwpf.usermodel.XWPFTableRow;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSimpleField;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.STStyleType;
//import org.springframework.stereotype.Component;
//
//import com.ttl.ito.business.beans.QuotationForm;
//import com.ttl.ito.business.beans.ReportBean;
//import com.ttl.ito.business.daoImpl.QuotationDaoImpl;
//
//@Component
//public class WordCreator {
//	private static Logger logger = Logger.getLogger(WordCreator.class);
//
//	public static void addWordContent(XWPFDocument document, QuotationForm quotationForm, ReportBean reportBean) {
//
//		List<ReportBean> data = new ArrayList<>();
//		data = reportBean.getWordData();
//
//		XWPFParagraph p1 = document.createParagraph();
//		p1.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r1 = p1.createRun();
//		XWPFRun r111 = p1.createRun();
//		XWPFRun r1111 = p1.createRun();
//		XWPFRun r112 = p1.createRun();
//
//		r1.setText("Reference No: ");
//		r1.setFontFamily("Calibri(Body)");
//		r1.setFontSize(11);
//		r111.setText(reportBean.getOpportunitySeqNum());
//		r111.setFontFamily("Calibri(Body)");
//		r111.setFontSize(11);
////		r111.setColor("ff0000");
//		r111.setText("                                                                           ");
//		r1111.setText("Date: ");
//		r1111.setFontFamily("Calibri(Body)");
//		r1111.setFontSize(11);
//		r112.setText(reportBean.getDate());
//		r112.setFontFamily("Calibri(Body)");
//		r112.setFontSize(11);
////		r112.setColor("ff0000");
//		r112.addBreak();
//		r112.addBreak();
//
//		XWPFParagraph p2 = document.createParagraph();
//		p2.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r2 = p2.createRun();
//		XWPFRun r222 = p2.createRun();
//		r2.setText("Purchaser:");
//		r2.setFontFamily("Calibri(Body)");
//		r2.setFontSize(11);
//
//		r222.setText(reportBean.getOppName());
////		r222.setColor("ff0000");
//		r222.setFontFamily("Calibri(Body)");
//		r222.setFontSize(11);
//
//		XWPFParagraph p3 = document.createParagraph();
//		p3.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r3 = p3.createRun();
//		XWPFRun r333 = p3.createRun();
//		r3.setText("Project Name:");
//		r3.setFontFamily("Calibri(Body)");
//		r3.setFontSize(11);
//		String TurbineCode = null;
//		TurbineCode = reportBean.getTurbineCode();
//
//		r333.setText(reportBean.getAccountName());
////		r333.setColor("ff0000");
//		r333.setFontFamily("Calibri(Body)");
//		r333.setFontSize(11);
//		r333.addBreak();
//		r333.addBreak();
//
//		XWPFParagraph p5 = document.createParagraph();
//		p5.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r5 = p5.createRun();
//		XWPFRun r13 = p5.createRun();
//		r5.setText("Kind Attention:");
//		r5.setBold(true);
//		r5.setFontFamily("Calibri(Body)");
//		r5.setFontSize(11);
//		r13.setText(reportBean.getCustContactPersonName());
//		r13.setFontFamily("Calibri(Body)");
//		r13.setFontSize(11);
////		r13.setColor("ff0000");
//		r13.addBreak();
//
//		XWPFParagraph p7 = document.createParagraph();
//		p7.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r7 = p7.createRun();
//		XWPFRun r17 = p7.createRun();
//		r7.setText("Sub:");
//		r7.setFontFamily("Calibri(Body)");
//		r7.setFontSize(11);
//		r7.setBold(true);
//		r17.setText(reportBean.getSubject());
//		r17.setFontFamily("Calibri(Body)");
//		r17.setFontSize(11);
//		r17.setUnderline(UnderlinePatterns.THICK);
//
//		XWPFParagraph p8 = document.createParagraph();
//		p8.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r8 = p8.createRun();
//		r8.setText("Dear sir,");
//		r8.setFontFamily("Calibri(Body)");
//		r8.setFontSize(11);
//		r8.addBreak();
//
//		XWPFParagraph p9 = document.createParagraph();
//		p9.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r9 = p9.createRun();
//		r9.setText("We thank you for your enquiry. Further to the same, we are pleased to submit herewith our techno-commercial offer for your kind perusal.");
//		r9.setFontSize(11);
//		r9.setFontFamily("Calibri(Body)");
//		r9.addBreak();
//
//		XWPFParagraph p10 = document.createParagraph();
//		p10.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r10 = p10.createRun();
//		r10.setText("We hope you will find the same in line with your requirements. However, should there be any clarifications, please feel free to get in touch with us. ");
//		r10.setFontFamily("Calibri(Body)");
//		r10.setFontSize(11);
//		r10.addBreak();
//
//		XWPFParagraph p11 = document.createParagraph();
//		p11.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r11 = p11.createRun();
//		r11.setText("Thanking you and assuring you of our best attention at all times.");
//		r11.setFontFamily("Calibri(Body)");
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
//		r12.setFontFamily("Calibri(Body)");
//		r12.setFontSize(11);
//		r14.addBreak();
//		r14.addBreak();
//		r14.addBreak();
//		r14.addBreak();
//		r14.addBreak();
//		r14.addBreak();
//		r14.addBreak();
//		r14.addBreak();
//
//		XWPFParagraph p21 = document.createParagraph();
//		p21.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r21 = p21.createRun();
//		r21.setText("Dear Customer, ");
//		r21.setFontFamily("Calibri(Body)");
//		r21.setFontSize(11);
//		r21.addBreak();
//
//		XWPFParagraph p22 = document.createParagraph();
//		p22.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r22 = p22.createRun();
//		r22.setText(
//				"Triveni Turbines is an AS 9100D (Based on and including ISO 9001:2008) and ISO 14001:2004 certified company.  As a commitment to environment we have laid out our environment policy which will be practiced during our operations, manufacturing and other areas of operation.   ");
//		r22.setFontFamily("Calibri(Body)");
//		r22.setFontSize(11);
//		r22.addBreak();
//
//		XWPFParagraph p23 = document.createParagraph();
//		p23.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r23 = p23.createRun();
//		r23.setText("All our products will confine to broad guidelines laid out by our EOHS policy. ");
//		r23.setFontFamily("Calibri(Body)");
//		r23.setFontSize(11);
//		r23.addBreak();
//
//		XWPFParagraph p24 = document.createParagraph();
//		p24.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r24 = p24.createRun();
//		r24.setText("For TRIVENI TURBINE LTD., ");
//		r24.setFontFamily("Calibri(Body)");
//		r24.setFontSize(11);
//		r24.addBreak();
//
//		XWPFParagraph p25 = document.createParagraph();
//		p25.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r25 = p25.createRun();
//		r25.setText("Authorized Signatory ");
//		r25.setFontFamily("Calibri(Body)");
//		r25.setFontSize(11);
//		r25.addBreak();
//		r25.addBreak();
//
//		XWPFParagraph p26 = document.createParagraph();
//		p26.setAlignment(ParagraphAlignment.CENTER);
//		XWPFRun r26 = p26.createRun();
//		r26.setText("ENVIRONMENT AND OCCUPATION HEALTH & SAFETY (EOHS) POLICY  ");
//		r26.setBold(true);
//		r26.setFontFamily("Calibri(Body)");
//		r26.setUnderline(UnderlinePatterns.THICK);
//		r26.setFontSize(11);
//
//		XWPFParagraph p27 = document.createParagraph();
//		p27.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r27 = p27.createRun();
//		r27.setText("We at Triveni Turbine Limited manufacturing Steam Turbines and related products including the accessories are committed to:");
//		r27.setFontFamily("Calibri(Body)");
//		r27.setFontSize(11);
//		r27.addBreak();
//
//		XWPFParagraph p28 = document.createParagraph();
//		p28.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r28 = p28.createRun();
//		r28.setText(
//				"•	Produce the product and provide services to enhance the Customer satisfaction by ensuring prevention of pollution, reduction of waste, conservation of resources and creating safe working environment by ensuring prevention of injury and ill health");
//		r28.setFontFamily("Calibri(Body)");
//		r28.setFontSize(11);
//		r28.addBreak();
//
//		XWPFParagraph p29 = document.createParagraph();
//		p29.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r29 = p29.createRun();
//		r29.setText("•	Comply will all applicable legal and other requirements with relation to EOHS.");
//		r29.setFontFamily("Calibri(Body)");
//		r29.setFontSize(11);
//		r29.addBreak();
//
//		XWPFParagraph p30 = document.createParagraph();
//		p30.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r30 = p30.createRun();
//		r30.setText("•	Establish appropriate objectives in respect of EOHS to facilate the continual improvement.");
//		r30.setFontFamily("Calibri(Body)");
//		r30.setFontSize(11);
//		r30.addBreak();
//
//		XWPFParagraph p31 = document.createParagraph();
//		p31.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r31 = p31.createRun();
//		r31.setText(
//				"•	Communicate the policy effectively to all persons working under the control of the organization including relevant interested parties such as Sub contractors, Suppliers, Transporters and Customers.");
//		r31.setFontFamily("Calibri(Body)");
//		r31.setFontSize(11);
//		r31.addBreak();
//
//		XWPFParagraph p32 = document.createParagraph();
//		p32.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r32 = p32.createRun();
//		r32.setText(
//				"•	Persons working on behalf of the organization shall be provided with necessary competency in respect of EOHS with the intent that they are made aware of their individual obligations.");
//		r32.setFontFamily("Calibri(Body)");
//		r32.setFontSize(11);
//		r32.addBreak();
//
//		XWPFParagraph p33 = document.createParagraph();
//		p33.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r33 = p33.createRun();
//		r33.setText("EXECUTIVE DIRECTOR");
//		r33.setFontFamily("Calibri(Body)");
//		r33.setFontSize(11);
//		r33.addBreak();
//		r33.addBreak();
//		r33.addBreak();
//
//		XWPFParagraph p34 = document.createParagraph();
//		p34.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r34 = p34.createRun();
//		r34.setText("Why Triveni ");
//		r34.setBold(true);
//		r34.setFontFamily("Calibri(Body)");
//		r34.setUnderline(UnderlinePatterns.THICK);
//		r34.setFontSize(11);
//		r34.addBreak();
//
//		XWPFParagraph p35 = document.createParagraph();
//		p35.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r35 = p35.createRun();
//		r35.setText("*	Most preferred supplier, with a market share of over 60% in India in the range up to 30MW");
//		r35.setFontFamily("Calibri(Body)");
//		r35.setFontSize(11);
//		r35.addBreak();
//
//		XWPFParagraph p36 = document.createParagraph();
//		p36.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r36 = p36.createRun();
//		r36.setText("*	Over 3200 installations across the globe in over 70 countries in all industry segments");
//		r36.setFontFamily("Calibri(Body)");
//		r36.setFontSize(11);
//		r36.addBreak();
//
//		XWPFParagraph p37 = document.createParagraph();
//		p37.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r37 = p37.createRun();
//		r37.setText("*	Over 500 turbines in process co-generation plants and nearly 100 turbines in biomass based IPPs");
//		r37.setFontFamily("Calibri(Body)");
//		r37.setFontSize(11);
//		r37.addBreak();
//
//		XWPFParagraph p38 = document.createParagraph();
//		p38.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r38 = p38.createRun();
//		r38.setText("*	Industry Bench Mark Delivery time. Helps our customers commission their power plants faster");
//		r38.setFontFamily("Calibri(Body)");
//		r38.setFontSize(11);
//		r38.addBreak();
//
//		XWPFParagraph p39 = document.createParagraph();
//		p39.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r39 = p39.createRun();
//		r39.setText("	* Best in class manufacturing plant in Bangalore with fleet of 4 Axis / 5 Axis machine. Manufacturing capacity of 150 turbines per year");
//		r39.setFontFamily("Calibri(Body)");
//		r39.setFontSize(11);
//		r39.addBreak();
//
//		XWPFParagraph p40 = document.createParagraph();
//		p40.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r40 = p40.createRun();
//		r40.setText("* Every turbine is put through a Mechanical Steam Run Test (at factory, before dispatch) at Full speed to test vibration, safety trip etc.");
//		r40.setFontFamily("Calibri(Body)");
//		r40.setFontSize(11);
//		r40.addBreak();
//
//		XWPFParagraph p41 = document.createParagraph();
//		p41.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r41 = p41.createRun();
//		r41.setText("*	SCHENK High Speed Vacuum Tunnel balancing for precise dynamic balancing of large rotors. Ensures operation reliability");
//		r41.setFontFamily("Calibri(Body)");
//		r41.setFontSize(11);
//		r41.addBreak();
//
//		XWPFParagraph p42 = document.createParagraph();
//		p42.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r42 = p42.createRun();
//		r42.setText("*	24 x 7 Service centers closer to our customers - 13 service offices across India with over 150 service personnel. We respond within 8 hours of an emergency call");
//		r42.setFontFamily("Calibri(Body)");
//		r42.setFontSize(11);
//		r42.addBreak();
//
//		XWPFParagraph p43 = document.createParagraph();
//		p43.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r43 = p43.createRun();
//		r43.setText("* Life time support for spares and services to maximize the return on investment. ");
//		r43.setFontFamily("Calibri(Body)");
//		r43.setFontSize(11);
//		r43.addBreak();
//
//		XWPFParagraph p44 = document.createParagraph();
//		p44.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r44 = p44.createRun();
//		r44.setText("*	Refurbishing solutions offered up to 150 MW for any make of turbine to enhance efficiency, availability and reliability.");
//		r44.setFontFamily("Calibri(Body)");
//		r44.setFontSize(11);
//		r44.addBreak();
//
//		XWPFParagraph p45 = document.createParagraph();
//		p45.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r45 = p45.createRun();
//		r45.setText(
//				"*A decade old Research and Development department for continuous improvement of existing models and development of new models.  Associated with renowned design houses from USA – Impact Technologies LLC and Concepts NREC. ");
//		r45.setFontFamily("Calibri(Body)");
//		r45.setFontSize(11);
//		r45.addBreak();
//
//		XWPFParagraph paragraph1 = document.createParagraph();
//		XWPFRun run1 = paragraph1.createRun();
//		// run1.addBreak(BreakType.PAGE);
//		//// paragraph1.setBorderTop(Borders.SINGLE);
//
//		addCustomHeadingStyle(document, "heading 1", 1);
//		addCustomHeadingStyle(document, "heading 2", 2);
//
//		// the body content
//		XWPFParagraph paragraph = document.createParagraph();
//		XWPFRun run = paragraph.createRun();
//
//		// Print table of contents
//
//		int i = 0;
//		int chapter = 0;
//		String chapterindent = "";
//		String prevcategoryname = null;
//
//		XWPFParagraph p123 = document.createParagraph();
//		p123.setAlignment(ParagraphAlignment.CENTER);
//		XWPFRun r123 = p123.createRun();
//		r123.setText("INDEX ");
//		r123.setBold(true);
//		r123.setFontFamily("Calibri(Body)");
//		r123.setUnderline(UnderlinePatterns.THICK);
//		r123.setFontSize(11);
//
//		for (i = 0; i < reportBean.getWordData().size(); i++) {
//
//			if (i == QuotationDaoImpl.size)
//				break;
//
//			String categoryname = reportBean.getWordData().get(i).getCategoryName();
//
//			String subcategorytemp = reportBean.getWordData().get(i).getSubCategoryName();
//
//			if (!categoryname.equals(prevcategoryname)) {
//				paragraph = document.createParagraph();
//				run = paragraph.createRun();
//
//				chapter = chapter + 1;
//				chapterindent = chapter + ".";
//				run.setText(chapterindent);
//				run.setText("         ");
//				run.setText(categoryname);
//				run.setFontFamily("Calibri(Body)");
//				run.setFontSize(11);
//				paragraph.setStyle("heading 1");
//			}
//
//			if (subcategorytemp != null) // Compare two strings
//			{
//				paragraph = document.createParagraph();
//				run = paragraph.createRun();
//				run.setText("              ");
//				run.setText(reportBean.getWordData().get(i).getSubCategoryName());
//				run.setFontFamily("Calibri(Body)");
//				run.setFontSize(11);
//				paragraph.setStyle("heading 2");
//			}
//
//			prevcategoryname = categoryname;
//		}
//		run.addBreak();
//		run.addBreak();
//		run.addBreak();
//		run.addBreak();
//		run.addBreak();
//		run.addBreak();
//		run.addBreak();
//		run.addBreak();
//		run.addBreak();
//		run.addBreak();
//		run.addBreak();
//		run.addBreak();
//		run.addBreak();
//		run.addBreak();
//		run.addBreak();
//		run.addBreak();
//
//		// To print scope of supply(chapter 1)
//		String prevcategoryname1 = null;
//		String prevsubcategoryname = null;
//		String previtemtemp= null;
//		int k = 0;
//
//		for (k = i; k < reportBean.getWordData().size(); k++) {
//
//			if (k == QuotationDaoImpl.size1)
//				break;
//
//			String categoryname = reportBean.getWordData().get(k).getCategoryName();
//			String subcategoryname = reportBean.getWordData().get(k).getSubCategoryName();
//			String itemtemp = reportBean.getWordData().get(k).getItemName();
//			String subCatName = reportBean.getWordData().get(k).getSubCatName();
//			// to print category name
//			if (!categoryname.equals(prevcategoryname1)) {
//				paragraph = document.createParagraph();
//				run = paragraph.createRun();
//				run.setText(categoryname);
//				run.setBold(true);
//				run.setFontFamily("Calibri(Body)");
//				run.setFontSize(11);
//				paragraph.setStyle("heading 1");
//			}
//
//			// to print subcategory name
//			if (!subcategoryname.equals(prevsubcategoryname)) {
//				paragraph = document.createParagraph();
//				run = paragraph.createRun();
//				run.setText("           ");
//				run.setText(subcategoryname);
//				run.setBold(true);
//				run.setFontFamily("Calibri(Body)");
//				run.setFontSize(11);
//				paragraph.setStyle("heading 1");
//			}
//
//			// to print Item name
//			if (!itemtemp.equals(previtemtemp)) {
//				paragraph = document.createParagraph();
//				run = paragraph.createRun();
//				run.setText("           ");
//				run.setText(reportBean.getWordData().get(k).getItemName());
//				run.setFontFamily("Calibri(Body)");
//				run.setFontSize(11);
//
//			}
//			if (itemtemp.equals("A.9  Mechanical Auxiliaries")) {
//				paragraph = document.createParagraph();
//				run1 = paragraph.createRun();
//				run1.setText("                      ");
//				run1.setText(subCatName);
//				
//				run1.setFontFamily("Calibri(Body)");
//				run1.setFontSize(11);
//			}
//			
//			if (itemtemp.equals("A.10  Mechanical Extended Scope")) {
//				paragraph = document.createParagraph();
//				XWPFRun r99 = paragraph.createRun();
//				r99 = paragraph.createRun();
//				r99.setText("                      ");
//				r99.setText(subCatName);
//				
//				r99.setFontFamily("Calibri(Body)");
//				r99.setFontSize(11);
//			}
//			
//			prevcategoryname1 = categoryname;
//			prevsubcategoryname = subcategoryname;
//			previtemtemp = itemtemp;
//			
//			
//		}
//
//		// To print the technical details / technical specifications(chapter 2)
//
//		int j = 0;
//		String prevcategoryname2 = null;
//		String prevsubcategoryname1 = null;
//		String previtemname = null;
//		String prevsubitemname = null;
//		String prevsubitemtypename = null;
//		String previtemcode = null;
//		String prevtechremarks = null;
//		String prevfixedtext1 = null;
//		String prevfixedtext5 = null;
//		XWPFTable table = null;
//
//		for (j = k; j < reportBean.getWordData().size(); j++) {
//
//			String categoryname = null;
//			String subcategoryname = null;
//			String itemcode = null;
//			String colValCd = null;
//			String techremarks = null;
//			String Category = null;
//			String colName = null;
//			String FixedText1 = null;
//			String FixedText2 = null;
//			String FixedText3 = null;
//			String FixedText4 = null;
//			String FixedText5 = null;
//			String VarianttypeName = null;
//			int numberOfRows = 0;
//			
//			int iData = 0;
//			// get row
//			XWPFTableRow tableRowOne1 = null;
//
//			if (j == QuotationDaoImpl.size2)
//				break;
//
//			categoryname = reportBean.getWordData().get(j).getCategoryName();
//			subcategoryname = reportBean.getWordData().get(j).getSubCategoryName();
//			itemcode = reportBean.getWordData().get(j).getItemCode();
//			techremarks = reportBean.getWordData().get(j).getTechRemarks();
//			VarianttypeName = reportBean.getWordData().get(j).getVarientTypeName();
//			Category = reportBean.getWordData().get(j).getCategory();
//			FixedText1 = reportBean.getWordData().get(j).getFixedText1();
//			FixedText2 = reportBean.getWordData().get(j).getFixedText2();
//			FixedText3 = reportBean.getWordData().get(j).getFixedText3();
//			FixedText4 = reportBean.getWordData().get(j).getFixedText4();
//			FixedText5 = reportBean.getWordData().get(j).getFixedText5();
//			colValCd = reportBean.getWordData().get(j).getColValCd();
//			numberOfRows = reportBean.getWordData().get(j).getNumberOfRows();
//			
//			// This is to print the technical remarks at the end of the item
//			if (!itemcode.equals(previtemcode) && itemcode != null) {
//				if (techremarks != null) {
//					if (!techremarks.equals(prevtechremarks)) {
//						paragraph = document.createParagraph();
//						run = paragraph.createRun();
//
//						run.setText(prevtechremarks);
//						run.addBreak();
//						run.addBreak();
//
//						run.setFontFamily("Calibri(Body)");
//						run.setFontSize(11);
//					} // if (techremarks != null)
//				} // if (techremarks != null)
//				if (prevfixedtext5 != null) {
//					// This is to print safety devices for steam turbine model
//					// only
//					if (previtemcode.equals("STEM_MD")) {
//						paragraph = document.createParagraph();
//						run1 = paragraph.createRun();
//
//						run.setText("				");
//						run1.setText(" A.1.1 Safety Devices ");
//						run1.setText("                      ");
//						run1.setBold(true);
//						run1.setFontFamily("Calibri(Body)");
//						run1.setFontSize(11);
//
//						paragraph = document.createParagraph();
//						run = paragraph.createRun();
//						run.setText("				");
//						run.setText("-Over speed trip:  Eccentric spring loaded oil dump valve");
//						run.setFontFamily("Calibri(Body)");
//						run.setFontSize(11);
//
//						paragraph = document.createParagraph();
//						XWPFRun run7 = paragraph.createRun();
//						run7 = paragraph.createRun();
//						run7.setText("				");
//						run7.setText("-Low lube oil pressure trip: Spring loaded spool valve combined with LP trip");
//						run7.setFontFamily("Calibri(Body)");
//						run7.setFontSize(11);
//
//						paragraph = document.createParagraph();
//						XWPFRun run2 = paragraph.createRun();
//						run2.setText("				");
//						run2.setText("-Low control oil pressure trip: Spring loaded spool valve combined with LP trip");
//						run2.setFontFamily("Calibri(Body)");
//						run2.setFontSize(11);
//
//						paragraph = document.createParagraph();
//						XWPFRun run3 = paragraph.createRun();
//						run3.setText("				");
//						run3.setText("-High thrust wear trip: Through vibration monitoring system	");
//						run3.setFontFamily("Calibri(Body)");
//						run3.setFontSize(11);
//
//						paragraph = document.createParagraph();
//						XWPFRun run4 = paragraph.createRun();
//						run4.setText("				");
//						run4.setText("-High exhaust pressure trip: Through pressure switch and solenoid trip");
//						run4.setFontFamily("Calibri(Body)");
//						run4.setFontSize(11);
//
//						paragraph = document.createParagraph();
//						XWPFRun run5 = paragraph.createRun();
//						run5.setText("				");
//						run5.setText("-Hand trip : Block & dump valve in trip oil line ");
//						run5.setFontFamily("Calibri(Body)");
//						run5.setFontSize(11);
//
//						paragraph = document.createParagraph();
//						XWPFRun run8 = paragraph.createRun();
//						run8.setText("				");
//						run8.setText("Operation:  Local hand lever ");
//						run8.setFontFamily("Calibri(Body)");
//						run8.setFontSize(11);
//
//						paragraph = document.createParagraph();
//						XWPFRun run6 = paragraph.createRun();
//						run6.setText("				");
//						run6.setText("-Solenoid trip:  Solenoid operated block & dump valve ");
//						run6.setFontFamily("Calibri(Body)");
//						run6.setFontSize(11);
//
//						paragraph = document.createParagraph();
//						XWPFRun run9 = paragraph.createRun();
//						run9.setText("				");
//						run9.setText("Operation:  De-energise to open & trip. ");
//						run9.setFontFamily("Calibri(Body)");
//						run9.setFontSize(11);
//
//						iData = 1; // To know that safety devices is printed
//					}
//				} // if (prevfixedtext5 != null)
//
//			} // if (!itemcode.equals(previtemcode) && itemcode != null)
//
//			String itemtemp = null;
//			String subitemname = null;
//			String subitemtypename = null;
//
//			itemtemp = reportBean.getWordData().get(j).getItemName();
//			subitemname = reportBean.getWordData().get(j).getSubItemName();
//			subitemtypename = reportBean.getWordData().get(j).getSubItemTypeName();
//			colName = reportBean.getWordData().get(j).getColNm();
//			// to print category name
//			if (!categoryname.equals(prevcategoryname2)) {
//				paragraph = document.createParagraph();
//				run = paragraph.createRun();
//				run.setText(categoryname);
//				run.setBold(true);
//				run.setFontFamily("Calibri(Body)");
//				run.setFontSize(11);
//				paragraph.setStyle("heading 1");
//			}
//
//			// to print subcategory name
//			if (!subcategoryname.equals(prevsubcategoryname1)) {
//				paragraph = document.createParagraph();
//				run = paragraph.createRun();
//				run.setText("           ");
//				run.setText(subcategoryname);
//				run.setBold(true);
//				run.setFontFamily("Calibri(Body)");
//				run.setFontSize(11);
//				paragraph.setStyle("heading 1");
//
//			}
//
//			// to print item name only once
//			if (!itemtemp.equals(previtemname)) {
//				paragraph = document.createParagraph();
//				run = paragraph.createRun();
////				run.setText("                       ");
//				run.setText(reportBean.getWordData().get(j).getItemName());
//				run.setBold(true);
//				run.setFontFamily("Calibri(Body)");
//				run.setFontSize(11);
//				
//				// To print the static text of gland sealing system
//				if (itemcode.equals("GLAND_SYS")) {
//					paragraph = document.createParagraph();
//					run1 = paragraph.createRun();
//					run1.setText(colValCd);
//					run1.setText(",  ");
//					run1.setText(FixedText1);
//					run1.setFontFamily("Calibri(Body)");
//					run1.setFontSize(11);
//				}
//
//				// To print the static text of steam turbines back pressure
//				if (TurbineCode != null && TurbineCode.equals("BP")){
//					if ((FixedText1 != null) && ((itemcode.equals("STEM_MD")) && (!itemcode.equals("GLAND_SYS")))) {
//						if (!FixedText1.equals(prevfixedtext1)) {
//							paragraph = document.createParagraph();
//							run = paragraph.createRun();
//							run.setText("          ");
//							if (!FixedText1.equals("NULL")&&(FixedText1 != null))
//								run.setText(FixedText1);
//								run.setText(",  ");
//							if (!FixedText2.equals("NULL")&&(FixedText2 != null)){
//
//								run.setText(FixedText2);
//								run.setText(",  ");
//							}
//
//							if (!FixedText3.equals("NULL")&&(FixedText3 != null)) {
//
//								run.setText(FixedText3);
//								run.setText(",  ");
//							}
//							if (!VarianttypeName.equals("NULL")&&(VarianttypeName != null)) {
//								run.setText(VarianttypeName);
//								run.setText(",  ");
//							}
//							if (!FixedText4.equals("NULL")&&(FixedText4 != null)) {
//								run.setText(FixedText4);
//								run.setText(",  ");
//							}
//							if (!FixedText5.equals("NULL")&&(FixedText5 != null)){
//								if (!itemcode.equals("STEM_MD"))
//									run.setText(FixedText5);
//							}
//
//							run.setFontFamily("Calibri(Body)");
//							run.setFontSize(11);
//
//						}
//					}
//				}
//				if (TurbineCode != null && TurbineCode.equals("CD")){
//					if ((FixedText1 != null) && ((itemcode.equals("STEM_MD")) && (!itemcode.equals("GLAND_SYS")))) {
//						if (!FixedText1.equals(prevfixedtext1)) {
//							paragraph = document.createParagraph();
//							run = paragraph.createRun();
//							run.setText("          ");
//							if (!FixedText1.equals("NULL")&&(FixedText1 != null))
//								run.setText(FixedText1);
//								run.setText(",  ");
//							if (!FixedText2.equals("NULL")&&(FixedText2 != null)){
//
//								run.setText(FixedText2);
//								run.setText(",  ");
//							}
//
//							if (!FixedText3.equals("NULL")&&(FixedText3 != null)){
//
//								run.setText(FixedText3);
//								run.setText(",  ");
//							}
//							if (!VarianttypeName.equals("NULL")&&(VarianttypeName != null)) {
//								run.setText(VarianttypeName);
//								run.setText(",  ");
//							}
//							if (!FixedText4.equals("NULL")&&(FixedText4 != null)) {
//								run.setText(FixedText4);
//								run.setText(",  ");
//							}
//							if (!FixedText5.equals("NULL")&&(FixedText5 != null)){
//								if (!itemcode.equals("STEM_MD"))
//									run.setText(FixedText5);
//							}
//
//							run.setFontFamily("Calibri(Body)");
//							run.setFontSize(11);
//
//						}
//					}
//				}
//
//
//				// To print the static text of lubeoil control system
//
//				if (Category != null && (Category.equals("A") || Category.equals("B")||Category.equals("C")||Category.equals("D"))) {
//					if ((FixedText1 != null) && ((itemcode.equals("LUB_OIL")) && (!itemcode.equals("GLAND_SYS")))) {
//						if (!FixedText1.equals(prevfixedtext1)) {
//							paragraph = document.createParagraph();
//							run = paragraph.createRun();
//							run.setText("          ");
//
//							if (!FixedText1.equals("NULL")&&FixedText1 != null)
//								run.setText(FixedText1);
//								
//							if (!FixedText2.equals("NULL")&&FixedText2 != null)
//								run.setText(FixedText2);
//								
//							if (!FixedText3.equals("NULL")&&(FixedText3 != null)) {
//								
//								run.setText(FixedText3);
//								run.setText(",  ");
//
//							}
//
//							if (!FixedText4.equals("NULL")&&(FixedText4 != null))
//								run.setText(FixedText4);
//								run.setText(",  ");
//								if (!FixedText5.equals("NULL")&&(FixedText5 != null)){
//								if (!itemcode.equals("STEM_MD") && !itemcode.equals("GLAND_SYS"))
//									paragraph = document.createParagraph();
//									run = paragraph.createRun();
//									
//									if (subitemname == null && subitemtypename == null && colName == null) {
//										if (colValCd != null)
//										{
////											paragraph = document.createParagraph();
////											run = paragraph.createRun();
//											run.setText(colValCd);
//											run.setText(",  ");
//										}
//
//									}
//
//									run.setText(FixedText5);
//
//								paragraph = document.createParagraph();
//								run = paragraph.createRun();
//								run.setText("				");
//								run.setText("-Main Oil Pump (MOP)");
//								run.setFontFamily("Calibri(Body)");
//								run.setFontSize(11);
//
//								paragraph = document.createParagraph();
//								XWPFRun run7 = paragraph.createRun();
//								run7 = paragraph.createRun();
//								run7.setText("				");
//								run7.setText("-A.C. Motor driven auxiliary oil pump (AOP) for flooding the bearings during start-up and wired through a pressure switch for automatic cut-in and cut-out in the event of main oil pump not supplying required quantity of oil.");
//								run7.setFontFamily("Calibri(Body)");
//								run7.setFontSize(11);
//
//								paragraph = document.createParagraph();
//								XWPFRun run2 = paragraph.createRun();
//								run2.setText("				");
//								run2.setText("-AC Motor driven two Auxiliary Control Oil Pump (1 working + 1 stand-by) to supply oil to Control system.");
//								run2.setFontFamily("Calibri(Body)");
//								run2.setFontSize(11);
//
//								paragraph = document.createParagraph();
//								XWPFRun run3 = paragraph.createRun();
//								run3.setText("				");
//								run3.setText("-D. C. Motor driven Emergency lube Oil Pump (EOP) with auto cut-in & cut-out facility	");
//								run3.setFontFamily("Calibri(Body)");
//								run3.setFontSize(11);
//
//								paragraph = document.createParagraph();
//								XWPFRun run4 = paragraph.createRun();
//								run4.setText("				");
//								run4.setText("-Suction strainers for MOP, AOP & EOP");
//								run4.setFontFamily("Calibri(Body)");
//								run4.setFontSize(11);
//
//								paragraph = document.createParagraph();
//								XWPFRun run5 = paragraph.createRun();
//								run5.setText("				");
//								run5.setText("-Pressure relief valves for discharge of MOP, AOP, ACOP and lube oil line ");
//								run5.setFontFamily("Calibri(Body)");
//								run5.setFontSize(11);
//
//								paragraph = document.createParagraph();
//								XWPFRun run8 = paragraph.createRun();
//								run8.setText("				");
//								run8.setText("-Oil coolers (1 working + 1 standby) with on-line changeover arrangement ");
//								run8.setFontFamily("Calibri(Body)");
//								run8.setFontSize(11);
//
//								paragraph = document.createParagraph();
//								XWPFRun run6 = paragraph.createRun();
//								run6.setText("				");
//								run6.setText("-Oil filters (1 working + 1 standby) with on-line change-over arrangement");
//								run6.setFontFamily("Calibri(Body)");
//								run6.setFontSize(11);
//
//								paragraph = document.createParagraph();
//								XWPFRun run9 = paragraph.createRun();
//								run9.setText("				");
//								run9.setText("-AC motor driven oil mist separator mounted on oil tank");
//								run9.setFontFamily("Calibri(Body)");
//								run9.setFontSize(11);
//
//								paragraph = document.createParagraph();
//								XWPFRun run10 = paragraph.createRun();
//								run10.setText("				");
//								run10.setText("-Pressure regulating valve to achieve desired lube oil pressure");
//								run10.setFontFamily("Calibri(Body)");
//								run10.setFontSize(11);
//
//								paragraph = document.createParagraph();
//								XWPFRun run11 = paragraph.createRun();
//								run11.setText("				");
//								run11.setText("-Set of lube oil piping within the limits of the turbine base frame and sufficient to meet the requirement of the ordered equipment. ");
//								run11.setFontFamily("Calibri(Body)");
//								run11.setFontSize(11);
//
//							}
//
//							run.setFontFamily("Calibri(Body)");
//							run.setFontSize(11);
//
//						}
//					}
//
//				}
//				
//				if (itemcode.equals("COND_SYST")) {
//					paragraph = document.createParagraph();
//					run1 = paragraph.createRun();
//					
//					run1.setText("Condensing system comprising of:");
//					
//					run1.setFontFamily("Calibri(Body)");
//					run1.setFontSize(11);
//				}
//				
//				table = document.createTable();
//				setTableAlign(table, ParagraphAlignment.LEFT);// Creating table only once				
//				tableRowOne1 = table.getRow(0);
//
//			}
//
//			// to print sub item name
//			// To print the sub item name only once
//			if(subitemname!=null){
//			if (!subitemname.equals(prevsubitemname)) {
//				paragraph = document.createParagraph();
//				run = paragraph.createRun();
////				run.setText("          ");
//				run.setText(reportBean.getWordData().get(j).getSubItemName());
//				run.setText(":");
//				run.setBold(true);
//				run.setFontFamily("Calibri(Body)");
//				run.setFontSize(11);
//				
//				boolean isFound = false;
//				isFound = subitemname.contains("Bearings");					
//				if(isFound == false)
//				{	
//					
//					table = document.createTable();
//					setTableAlign(table, ParagraphAlignment.LEFT);
//					tableRowOne1 = table.getRow(0);
//					
//				}
//				
//				
//				if (subitemname.equals("ACCESSORIES")) {
//					paragraph = document.createParagraph();
//					run1 = paragraph.createRun();
//					run1.setText("-	Rupture disc for condenser protection.");
//					run1.addBreak();
//					run1.addBreak();
//					run1.setText("-	Dry air/vapor line within specified battery limit.");
//					run1.setFontFamily("Calibri(Body)");
//					run1.setFontSize(11);
//				}
//
//					
//					
//				
//				
//			}
//
//			// To print the lhs and rhs of sub item
//			if (subitemname != null && subitemtypename == null && !subitemname.equals("ACCESSORIES")) // Compare two strings
//		{
//			String colunmname = reportBean.getWordData().get(j).getColNm();
//			
//			String colValCode = reportBean.getWordData().get(j).getColValCd();
//
//			if (colunmname != null && colValCode != null) {
//
//				if(	tableRowOne1 != null)
//				{
//					tableRowOne1.getCell(0).setText(colunmname);
//					tableRowOne1.addNewTableCell().setText(":");
//					tableRowOne1.addNewTableCell().setText(colValCode);
//					tableRowOne1 = null;						
//				}
//				
//				else
//				{
//					XWPFTableRow tableRowOne = table.createRow();
//					tableRowOne.getCell(0).setText(colunmname);
//					tableRowOne.getCell(1).setText(":");
//					tableRowOne.getCell(2).setText(colValCode);
//					
//				}
//				run.setFontFamily("Calibri(Body)");
//				run.setFontSize(11);
//			}
//				// To print technical remarks of the sub itemq
//
//				if (j + 1 == reportBean.getWordData().size()) {
//					paragraph = document.createParagraph();
//					run = paragraph.createRun();
////					run.setText("                      ");
//					run.setText(techremarks);
//					run.addBreak();
//					run.addBreak();
//					run.setFontFamily("Calibri(Body)");
//					run.setFontSize(11);
//				}
//			}
//			} // if (subitemname != null)
//			
//			if(subitemtypename!=null)
//			if (!subitemtypename.equals(prevsubitemtypename)) {
//			paragraph = document.createParagraph();
//			run = paragraph.createRun();
////			run.setText("                       ");
//			run.setText(reportBean.getWordData().get(j).getSubItemTypeName());
////			run.setBold(true);
//			run.setFontFamily("Calibri(Body)");
//			run.setFontSize(11);
//			
//			table = document.createTable();
//			setTableAlign(table, ParagraphAlignment.LEFT);// Creating table only once				
//			tableRowOne1 = table.getRow(0);
//
//		}
//		
//			if (subitemtypename != null)// Compare two strings
//		{
//			String colunmname = reportBean.getWordData().get(j).getColNm();
//
//			String colValCode = reportBean.getWordData().get(j).getColValCd();
//
//			// We should not print if lhs is null
//			if (colunmname != null && colValCode != null) {
//
//				if(	tableRowOne1 != null)
//				{
//					tableRowOne1.getCell(0).setText(colunmname);
//					tableRowOne1.addNewTableCell().setText(":");
//					tableRowOne1.addNewTableCell().setText(colValCode);
//					tableRowOne1 = null;						
//				}
//				
//				else
//				{
//					XWPFTableRow tableRowOne = table.createRow();
//					tableRowOne.getCell(0).setText(colunmname);
//					tableRowOne.getCell(1).setText(":");
//					tableRowOne.getCell(2).setText(colValCode);
//					
//				}
//				run.setFontFamily("Calibri(Body)");
//				run.setFontSize(11);
//			}
//		
//			
//				// To print the technical remark of sub item type
//
//				if (j + 1 == reportBean.getWordData().size()) {
//					paragraph = document.createParagraph();
//					run = paragraph.createRun();
////					run.setText("                      ");
//					run.setText(techremarks);
//					run.addBreak();
//					run.addBreak();
//					run.setFontFamily("Calibri(Body)");
//					run.setFontSize(11);
//
//				}
//
//			}
//				// This is to check that only item lhs and rhs should print of item
//			if (itemtemp != null && subitemname == null) // Compare two strings
//			{
//				String colunmname = reportBean.getWordData().get(j).getColNm();
//
//				String colValCode = reportBean.getWordData().get(j).getColValCd();
//
//				// We should not print if lhs is null
//				if (colunmname != null && colValCode != null) {
//
//					if(	tableRowOne1 != null)
//					{
//						tableRowOne1.getCell(0).setText(colunmname);
//						tableRowOne1.addNewTableCell().setText(":");
//						tableRowOne1.addNewTableCell().setText(colValCode);
//						tableRowOne1 = null;						
//					}
//					
//					else
//					{
//						XWPFTableRow tableRowOne = table.createRow();
//						tableRowOne.getCell(0).setText(colunmname);
//						tableRowOne.getCell(1).setText(":");
//						tableRowOne.getCell(2).setText(colValCode);
//						
//					}
//					run.setFontFamily("Calibri(Body)");
//					run.setFontSize(11);
//				}
//
//				// This is to print the technical remarks of item at the end
//				// after
//				// lhs and rhs
//
//				if (j + 1 == reportBean.getWordData().size()) {
//					paragraph = document.createParagraph();
//					run = paragraph.createRun();
//					run.setText(techremarks);
//					run.addBreak();
//					run.addBreak();
//					run.setFontFamily("Calibri(Body)");
//					run.setFontSize(11);
//				}
//
//			}
//
//			prevcategoryname2 = categoryname;
//			prevsubcategoryname1 = subcategoryname;
//			previtemname = itemtemp;
//			prevsubitemname = subitemname;
//			prevsubitemtypename = subitemtypename;
//			previtemcode = itemcode;
//			prevtechremarks = techremarks;
//			prevfixedtext1 = FixedText1;
//			prevfixedtext5 = FixedText5;
//
//		}
//		
//		// To print the technical details / technical specifications(chapter 2)(new result set)
//		
//
//				int m = 0;
//				String prevcategoryaux = null;
//				String prevsubcategoryaux = null;
//				String previtemnameaux = null;
//				String prevsubitemnameaux = null;
//				String previtemcodeaux = null;
//				String prevtechremarksaux = null;
//				
//
//				for (m = j; m < reportBean.getWordData().size(); m++) 
//
//				{		
//				
//					String categorynameaux = null;
//					String subcategorynameaux = null;
//					int itemidaux = 0;
//					String itemnameaux = null;
//					String itemcodeaux = null;
//					String subitemcodeaux = null;
//					String subitemnameaux = null;
//					
//					int qunatityaux = 0;
//					String techremarksaux = null;
//					String techcommentsaux = null;
//					int numberofsubitems = 0;
//					
//					
//					if (m == QuotationDaoImpl.size3)
//						break;
//					
//					
//					categorynameaux = reportBean.getWordData().get(m).getCategoryName();
//					subcategorynameaux = reportBean.getWordData().get(m).getSubCategoryName();
//					itemidaux = reportBean.getWordData().get(m).getItemId();
//					itemnameaux = reportBean.getWordData().get(m).getItemName();
//					itemcodeaux = reportBean.getWordData().get(m).getItemCode();
//					subitemcodeaux = reportBean.getWordData().get(m).getSubItemCode();
//					subitemnameaux = reportBean.getWordData().get(m).getSubItemName();
//					
//					
//					techremarksaux = reportBean.getWordData().get(m).getTechRemarks();
//					techcommentsaux = reportBean.getWordData().get(m).getTechComments();
//					numberofsubitems = reportBean.getWordData().get(m).getNumberOfSubItems();
//					
//					// This is to print the technical remarks at the end of the item
//					if (!itemcodeaux.equals(previtemcodeaux) && itemcodeaux != null) 
//					{
//						if (techremarksaux != null) 
//						{
//							if (!techremarksaux.equals(prevtechremarksaux)) 
//							{
//								paragraph = document.createParagraph();
//								run = paragraph.createRun();
//								run.setText(prevtechremarksaux);
//								run.addBreak();
//								run.addBreak();
//								run.setFontFamily("Calibri(Body)");
//								run.setFontSize(11);
//							}//if (!techremarksaux.equals(prevtechremarksaux)) 
//						}// if (techremarksaux != null) 
//					}//(!itemcodeaux.equals(previtemcodeaux) && itemcodeaux != null) 
//
//
//					itemnameaux = reportBean.getWordData().get(m).getItemName();
//					subitemnameaux = reportBean.getWordData().get(m).getSubItemName();
//					
//					
//					// to print item name only once
//					if (!itemnameaux.equals(previtemnameaux)) 
//					{
//						paragraph = document.createParagraph();
//						run = paragraph.createRun();
//						run.setText(reportBean.getWordData().get(m).getItemName());
//						run.setBold(true);
//						run.setFontFamily("Calibri(Body)");
//					
//
//					}//if (!itemnameaux.equals(previtemnameaux)) 
//
//					// To print the sub item name only once
//					if(subitemnameaux!=null)
//					{
//						if (!subitemnameaux.equals(prevsubitemnameaux))
//						{
//							paragraph = document.createParagraph();
//							run = paragraph.createRun();
//							run.setText(reportBean.getWordData().get(m).getSubItemName());
//							run.setText(":");
//							run.setBold(true);
//							run.setFontFamily("Calibri(Body)");
//							run.setFontSize(11);
//						} //if (!subitemnameaux.equals(prevsubitemnameaux))
//					
//					// To print the lhs and rhs of sub item
//					if (subitemnameaux != null ) // Compare two strings
//					{
//						String colunmname = reportBean.getWordData().get(m).getColNm();
//						String colValCode = reportBean.getWordData().get(m).getColValCd();
//						
//						if (colunmname != null && colValCode != null) 
//						{
//
//							if (subitemcodeaux.equals("FFRV")&& colunmname.equals("FFRV SIZE") )
//							{
//								
//
//										paragraph = document.createParagraph();
//										run = paragraph.createRun();
//										run.setText(colunmname);
//										run.setText(":");
//										run.setText(colValCode);
//										run.setText(",");
//										run.setText("Quantity");
//										run.setText(":");
//										run.setText( String.valueOf(qunatityaux));
//										run.setText("(Loose Supply)");
//										run.setFontFamily("Calibri(Body)");
//										run.setFontSize(11);
//									}
//							
//							if (subitemcodeaux.equals("NRV")&& colunmname.equals("Size NB"))
//							{
//								paragraph = document.createParagraph();
//										run = paragraph.createRun();
//										run.setText(colunmname);
//										run.setText(":");
//										run.setText(colValCode);
//										run.setText(",");
//										run.setText("Quantity");
//										run.setText(":");
//										run.setText( String.valueOf(qunatityaux));
//										run.setText("(Loose Supply)");
//										run.setFontFamily("Calibri(Body)");
//										run.setFontSize(11);
//									}
//							if (subitemcodeaux.equals("QCNRV") && colunmname.equals("Size"))
//							{
//										paragraph = document.createParagraph();
//										run = paragraph.createRun();
//										run.setText(colunmname);
//										run.setText(":");
//										run.setText(colValCode);
//										run.setText(",");
//										run.setText("Quantity");
//										run.setText(":");
//										run.setText( String.valueOf(qunatityaux));
//										run.setText("(Loose Supply)");
//										run.setFontFamily("Calibri(Body)");
//										run.setFontSize(11);
//									}
//							
//							
//							if (subitemcodeaux.equals("ISV")  && colunmname.equals("Size NB"))
//							{
//								
//										paragraph = document.createParagraph();
//										run = paragraph.createRun();
//										run.setText(colunmname);
//										run.setText(":");
//										run.setText(colValCode);
//										run.setText(",");
//										run.setText("Quantity");
//										run.setText(":");
//										run.setText( String.valueOf(qunatityaux));
//										run.setText("(Loose Supply)");
//										run.setFontFamily("Calibri(Body)");
//										run.setFontSize(11);
//									}
//							
//							if (subitemcodeaux.equals("OIL_CENTRIFUGE")  && colunmname.equals("Capacity") )
//							{
//										paragraph = document.createParagraph();
//										run = paragraph.createRun();
//										run.setText(colunmname);
//										run.setFontFamily("Calibri(Body)");
//										run.setFontSize(11);
//									}
//							
//							if (subitemcodeaux.equals("OV_TANK")  && colunmname.equals("Capacity"))
//							{
//										paragraph = document.createParagraph();
//										run = paragraph.createRun();
//										run.setText(colunmname);
//										run.setFontFamily("Calibri(Body)");
//										run.setFontSize(11);
//								}
//							if (subitemcodeaux.equals("FLOW_NOZZLE")  && colunmname.equals("Size"))
//							{
//										paragraph = document.createParagraph();
//										run = paragraph.createRun();
//										run.setText(colunmname);
//										run.setText(":");
//										run.setText(colValCode);
//										run.setText(",");
//										run.setText("Quantity");
//										run.setText(":");
//										run.setText( String.valueOf(qunatityaux));
//										run.setFontFamily("Calibri(Body)");
//										run.setFontSize(11);
//									}
//							if (subitemcodeaux.equals("FLOW_ORIFICE")  && colunmname.equals("Size"))
//							{
//										paragraph = document.createParagraph();
//										run = paragraph.createRun();
//										run.setText(colunmname);
//										run.setText(":");
//										run.setText(colValCode);
//										run.setText(",");
//										run.setText("Quantity");
//										run.setText(":");
//										run.setText( String.valueOf(qunatityaux));
//										run.setText("(Loose Supply)");
//										run.setFontFamily("Calibri(Body)");
//										run.setFontSize(11);
//									}
//							
//							if (subitemcodeaux.equals("MET_EXP_BEL") && colunmname.equals("Size"))
//							{
//								
//										paragraph = document.createParagraph();
//										run = paragraph.createRun();
//										run.setText(colunmname);
//										run.setFontFamily("Calibri(Body)");
//										run.setFontSize(11);
//									}
//							
//							if (subitemcodeaux.equals("ATR_VAL"))
//							{
//								paragraph = document.createParagraph();
//								run = paragraph.createRun();
//								run.setText(subitemcodeaux);
//								run.setBold(true);
//								run.setFontFamily("Calibri(Body)");
//								run.setFontSize(11);
//							}
//							if (subitemcodeaux.equals("VACC_BVL"))
//							{
//								paragraph = document.createParagraph();
//								run = paragraph.createRun();
//								run.setText(subitemcodeaux);
//								run.setBold(true);
//								run.setFontFamily("Calibri(Body)");
//								run.setFontSize(11);
//							}
//					
//						
//						
//						// To print technical remarks of the sub itemq
//
//						if (m + 1 == reportBean.getWordData().size()) 
//						{
//							paragraph = document.createParagraph();
//							run = paragraph.createRun();
//							run.setText(techremarksaux);
//							run.addBreak();
//							run.addBreak();
//							run.setFontFamily("Calibri(Body)");
//							run.setFontSize(11);
//						}//if (m + 1 == reportBean.getWordData().size()) 
//							
//					} //if (subitemnameaux != null ) 
//					
//				} // if (subitemnameaux != null)
//					
//			}	
//					previtemnameaux = itemnameaux;
//					prevsubitemnameaux = subitemnameaux;
//					previtemcodeaux = itemcodeaux;
//					prevtechremarksaux = techremarksaux;
//				
//				}//for loop
//				
//				// To print the technical details / technical specifications(chapter 2)(new result set 10)
//				
//
//				int n = 0;
//				String prevcategoryextscp = null;
//				String prevsubcategoryextscp = null;
//				String previtemnameextscp = null;
//				String prevsubitemnameextscp = null;
//				String previtemcodeextscp = null;
//				String prevtechremarksextscp = null;
//				
//
//				for (n = m; n < reportBean.getWordData().size(); n++) 
//
//				{		
//				
//					String categorynameextscp = null;
//					String subcategorynameextscp = null;
//					int itemidextscp= 0;
//					String itemnameextscp = null;
//					String itemcodeextscp = null;
//					String subitemcodeextscp = null;
//					String subitemnameextscp= null;
//					
//					int qunatityextscp = 0;
//					String techremarksextscp = null;
//					String techcommentsextscp = null;
//					int numberofsubitemsextscp = 0;
//					
//					
//					if (n == QuotationDaoImpl.size4)
//						break;
//					
//					
//					categorynameextscp = reportBean.getWordData().get(n).getCategoryName();
//					subcategorynameextscp= reportBean.getWordData().get(n).getSubCategoryName();
//					itemidextscp = reportBean.getWordData().get(n).getItemId();
//					itemnameextscp = reportBean.getWordData().get(n).getItemName();
//					itemcodeextscp = reportBean.getWordData().get(n).getItemCode();
//					subitemcodeextscp = reportBean.getWordData().get(n).getSubItemCode();
//					subitemnameextscp = reportBean.getWordData().get(n).getSubItemName();
//					
//					qunatityextscp = reportBean.getWordData().get(n).getQuantityAux();
//					techremarksextscp = reportBean.getWordData().get(n).getTechRemarks();
//					techcommentsextscp = reportBean.getWordData().get(n).getTechComments();
//					numberofsubitemsextscp = reportBean.getWordData().get(n).getNumberOfSubItems();
//					
//					// This is to print the technical remarks at the end of the item
//					if (!itemcodeextscp.equals(previtemcodeextscp) && itemcodeextscp != null) 
//					{
//						if (techremarksextscp != null) 
//						{
//							if (!techremarksextscp.equals(prevtechremarksextscp)) 
//							{
//								paragraph = document.createParagraph();
//								run = paragraph.createRun();
//								run.setText(prevtechremarksextscp);
//								run.addBreak();
//								run.addBreak();
//								run.setFontFamily("Calibri(Body)");
//								run.setFontSize(11);
//							}//if (!techremarkextscp.equals(prevtechremarksextscp)) 
//						}// if (techremarksextscp != null) 
//					}//(!itemcodeextscp.equals(previtemcodeextscp) && itemcodeextscp != null) 
//
//
//					itemnameextscp = reportBean.getWordData().get(n).getItemName();
//					subitemnameextscp = reportBean.getWordData().get(n).getSubItemName();
//					
//					
//					// to print item name only once
//					if (!itemnameextscp.equals(previtemnameextscp)) 
//					{
//						paragraph = document.createParagraph();
//						run = paragraph.createRun();
//						run.setText(reportBean.getWordData().get(n).getItemName());
//						run.setBold(true);
//						run.setFontFamily("Calibri(Body)");
//					
//
//					}//if (!itemnameextscp.equals(previtemnameextscp)) 
//
//					// To print the sub item name only once
//					if(subitemnameextscp!=null)
//					{
//						if (!subitemnameextscp.equals(prevsubitemnameextscp))
//						{
//							paragraph = document.createParagraph();
//							run = paragraph.createRun();
//							run.setText(reportBean.getWordData().get(n).getSubItemName());
//							run.setText(":");
//							run.setBold(true);
//							run.setFontFamily("Calibri(Body)");
//							run.setFontSize(11);
//						} //if (!subitemnameextscp.equals(prevsubitemnameextscp))
//					
//					// To print the lhs and rhs of sub item
//					if (subitemnameextscp != null ) // Compare two strings
//					{
//						String colunmname = reportBean.getWordData().get(n).getColNm();
//						String colValCode = reportBean.getWordData().get(n).getColValCd();
//						
//						if (colunmname != null && colValCode != null) 
//						{
//										paragraph = document.createParagraph();
//										run = paragraph.createRun();
//										run.setText(colunmname);
//										run.setText(":");
//										run.setText(colValCode);
//										run.setFontFamily("Calibri(Body)");
//										run.setFontSize(11);
//						}
//
//						// To print technical remarks of the sub itemq
//
//						if (n + 1 == reportBean.getWordData().size()) 
//						{
//							paragraph = document.createParagraph();
//							run = paragraph.createRun();
//							run.setText(techremarksextscp);
//							run.addBreak();
//							run.addBreak();
//							run.setFontFamily("Calibri(Body)");
//							run.setFontSize(11);
//						}//if (n + 1 == reportBean.getWordData().size()) 
//							
//					} //if (subitemnameextscp != null ) 
//					
//				} // if (subitemnameextscp != null)
//					
//			
//					previtemnameextscp = itemnameextscp;
//					prevsubitemnameextscp = subitemnameextscp;
//					previtemcodeextscp = itemcodeextscp;
//					prevtechremarksextscp = techremarksextscp;
//				
//				}//for loop
//				
//						
//
//				}
//				
//
//	private static void addCustomHeadingStyle(XWPFDocument document, String strStyleId, int headingLevel) {
//
//		CTStyle ctStyle = CTStyle.Factory.newInstance();
//		ctStyle.setStyleId(strStyleId);
//
//		CTString styleName = CTString.Factory.newInstance();
//		styleName.setVal(strStyleId);
//		ctStyle.setName(styleName);
//
//		CTDecimalNumber indentNumber = CTDecimalNumber.Factory.newInstance();
//		indentNumber.setVal(BigInteger.valueOf(headingLevel));
//
//		// lower number > style is more prominent in the formats bar
//		ctStyle.setUiPriority(indentNumber);
//
//		CTOnOff onoffnull = CTOnOff.Factory.newInstance();
//		ctStyle.setUnhideWhenUsed(onoffnull);
//
//		// style shows up in the formats bar
//		ctStyle.setQFormat(onoffnull);
//
//		// style defines a heading of the given level
//		CTPPr ppr = CTPPr.Factory.newInstance();
//		ppr.setOutlineLvl(indentNumber);
//		ctStyle.setPPr(ppr);
//
//		XWPFStyle style = new XWPFStyle(ctStyle);
//
//		// is a null op if already defined
//		XWPFStyles styles = document.createStyles();
//
//		style.setType(STStyleType.PARAGRAPH);
//		styles.addStyle(style);
//
//	}
//	 public static void setTableAlign(XWPFTable table,ParagraphAlignment align) {
//		    CTTblPr tblPr = table.getCTTbl().getTblPr();
//		    CTJc jc = (tblPr.isSetJc() ? tblPr.getJc() : tblPr.addNewJc());
//		    STJc.Enum en = STJc.Enum.forInt(align.getValue());
//		    jc.setVal(en);
//		}
//	
//}
