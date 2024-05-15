//package com.ttl.ito.common.Utility;
//
//
//
//import java.io.FileOutputStream;
//import java.io.FileInputStream;
//import java.io.IOException;
//
//import org.apache.poi.util.Units;
//
//import org.apache.poi.xwpf.usermodel.*;
//
//import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSimpleField;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.STStyleType;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabJc;
//
//import java.math.BigInteger;
//
//public class tpt {
//	private static final String IMAGE1 = "C:\\Users\\Public\\Pictures\\Sample Pictures\\TTL_Brand.png";
//
//	public static void main(String[] args) throws Exception {
//
//		XWPFDocument doc = new XWPFDocument();
//		
//		XWPFParagraph paragraph1 = doc.createParagraph();
//		XWPFRun run1 = paragraph1.createRun();
//		run1.addBreak(BreakType.PAGE);
//		paragraph1.setBorderTop(Borders.SINGLE);
//
//		doc.createTOC();
//		addCustomHeadingStyle(doc, "heading 1", 1);
//		addCustomHeadingStyle(doc, "heading 1", 2);
//		addCustomHeadingStyle(doc, "heading 1", 3);
//		addCustomHeadingStyle(doc, "heading 1", 4);
//		addCustomHeadingStyle(doc, "heading 1", 5);
//		addCustomHeadingStyle(doc, "heading 1", 6);
//		addCustomHeadingStyle(doc, "heading 1", 7);
//		addCustomHeadingStyle(doc, "heading 1", 8);
//		addCustomHeadingStyle(doc, "heading 1", 9);
//		addCustomHeadingStyle(doc, "heading 1", 10);
//		
//
//		// the body content
//		XWPFParagraph paragraph = doc.createParagraph();
//		XWPFRun run = paragraph.createRun();
//
//		// create 
//		CTP ctP = paragraph.getCTP();
//		CTSimpleField toc = ctP.addNewFldSimple();
//		toc.setInstr("TOC \\h");
//		toc.setDirty(STOnOff.TRUE);
//
//		paragraph = doc.createParagraph();
//		run = paragraph.createRun();
//		run.addBreak(BreakType.PAGE);
//		paragraph.setBorderTop(Borders.SINGLE);
//		run.setText("1    Scope of Supply");
//		paragraph.setStyle("heading 1");
//
//		paragraph = doc.createParagraph();
//		run = paragraph.createRun();
//		run.addBreak(BreakType.PAGE);
//		paragraph.setBorderTop(Borders.SINGLE);
//		run.setText("2    Technical Details/ Technical Specifications");
//		paragraph.setStyle("heading 1");
//
//		paragraph = doc.createParagraph();
//		run = paragraph.createRun();
//		run.addBreak(BreakType.PAGE);
//		paragraph.setBorderTop(Borders.SINGLE);
//		run.setText("3    Performance/ Heat & Mass Balance Diagram (Attaching external files shall be provided as  a pdf files)");
//		paragraph.setStyle("heading 1");
//
//		paragraph = doc.createParagraph();
//		run = paragraph.createRun();
//		run.addBreak(BreakType.PAGE);
//		paragraph.setBorderTop(Borders.SINGLE);
//		run.setText("4    Scope of Spares");
//		paragraph.setStyle("heading 1");
//
//		paragraph = doc.createParagraph();
//		run = paragraph.createRun();
//		run.addBreak(BreakType.PAGE);
//		paragraph.setBorderTop(Borders.SINGLE);
//		run.setText("5    Quality Assurance/ Control plan");
//		paragraph.setStyle("heading 1");
//
//		paragraph = doc.createParagraph();
//		run = paragraph.createRun();
//		run.addBreak(BreakType.PAGE);
//		paragraph.setBorderTop(Borders.SINGLE);
//		run.setText("6    Terminal Points");
//		paragraph.setStyle("heading 1");
//
//		paragraph = doc.createParagraph();
//		run = paragraph.createRun();
//		run.addBreak(BreakType.PAGE);
//		paragraph.setBorderTop(Borders.SINGLE);
//		run.setText("7    Exclusion List");
//		paragraph.setStyle("heading 1");
//
//		paragraph = doc.createParagraph();
//		run = paragraph.createRun();
//		run.addBreak(BreakType.PAGE);
//		paragraph.setBorderTop(Borders.SINGLE);
//		run.setText("8    Sub-supplier List");
//		paragraph.setStyle("heading 1");
//
//		paragraph = doc.createParagraph();
//		run = paragraph.createRun();
//		run.addBreak(BreakType.PAGE);
//		paragraph.setBorderTop(Borders.SINGLE);
//		run.setText("9    Tender Drawings (Attaching external files shall be provided as  a pdf files)");
//		paragraph.setStyle("heading 1");
//
//		paragraph = doc.createParagraph();
//		run = paragraph.createRun();
//		run.addBreak(BreakType.PAGE);
//		paragraph.setBorderTop(Borders.SINGLE);
//		run.setText("10    Clarifications/ Deviations (Attaching external files shall be provided as  a pdf files)");
//		paragraph.setStyle("heading 1");
//		
//		
//		// create first page header
//
//		CTSectPr sectPr = doc.getDocument().getBody().addNewSectPr();
//		sectPr.addNewTitlePg();
//		XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(doc, sectPr);
//		XWPFHeader header = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.FIRST);
//		paragraph = header.createParagraph();
//		paragraph.setAlignment(ParagraphAlignment.RIGHT);
//
//		run = paragraph.createRun();
//		run.addPicture(new FileInputStream(IMAGE1), Document.PICTURE_TYPE_PNG, "TTL_Brand", Units.toEMU(100), Units.toEMU(50));
//
//		// create default page header
//		XWPFHeader header1 = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);
//		paragraph = header1.createParagraph();
//		paragraph.setAlignment(ParagraphAlignment.RIGHT);
//		run = paragraph.createRun();
//		run.addPicture(new FileInputStream(IMAGE1), Document.PICTURE_TYPE_PNG, "TTL_Brand", Units.toEMU(100), Units.toEMU(50));
//
//		CTTabStop tabStop = paragraph.getCTP().getPPr().addNewTabs().addNewTab();
//		tabStop.setVal(STTabJc.RIGHT);
//		int twipsPerInch = 1440;
//		tabStop.setPos(BigInteger.valueOf(6 * twipsPerInch));
//
//		// create first page footer
//		XWPFFooter footer = headerFooterPolicy.createFooter(XWPFHeaderFooterPolicy.FIRST);
//		paragraph = footer.createParagraph();
//		paragraph.setAlignment(ParagraphAlignment.CENTER);
//		run = paragraph.createRun();
//		run.setText("TRIVENI TURBINE LIMITED ");
//		run.setBold(true);
//		run.setFontFamily("Arial");
//		run.setFontSize(12);
//		paragraph = footer.createParagraph();
//		paragraph.setAlignment(ParagraphAlignment.CENTER);
//		run = paragraph.createRun();
//		run.setText(
//				"12-A, Peenya Industrial Area, Bangalore 560 058, India. Tel: +91-80-2216 4000 Fax: +91-80-2216 4100 Website: www.triveniturbines.com  Regd. Off: A-44, Hosiery Complex, Phase-II Extn., NOIDA - 201 305, Uttar Pradesh CIN: L29110UP1995PLC041834");
//		run.setFontFamily("Arial");
//		run.setFontSize(8);
//
//		// create footer default page
//		XWPFFooter footer1 = headerFooterPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT);
//		paragraph = footer1.createParagraph();
//		paragraph.setAlignment(ParagraphAlignment.RIGHT);
//		run = paragraph.createRun();
//		run.setText("Page ");
//		paragraph.getCTP().addNewFldSimple().setInstr("PAGE \\* MERGEFORMAT");
//		run = paragraph.createRun();
//		run.setText(" of ");
//		paragraph.getCTP().addNewFldSimple().setInstr("NUMPAGES \\* MERGEFORMAT");
//
//		doc.write(new FileOutputStream("D:\\megha\\technicalProposal.docx"));
//		System.out.println("Word Document is Created Successfully !!!");
//
//	}
//
//	private static void addCustomHeadingStyle(XWPFDocument docxDocument, String strStyleId, int headingLevel) {
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
//		XWPFStyles styles = docxDocument.createStyles();
//
//		style.setType(STStyleType.PARAGRAPH);
//		styles.addStyle(style);
//
//	}
//
//}
//
//
//
