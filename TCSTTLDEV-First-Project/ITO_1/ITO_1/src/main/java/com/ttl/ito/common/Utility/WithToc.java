//
//package com.ttl.ito.common.Utility;
//
//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import com.itextpdf.text.BadElementException;
//import com.itextpdf.text.Chunk;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.Rectangle;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.draw.LineSeparator;
//import com.itextpdf.text.pdf.draw.VerticalPositionMark;
//import com.ttl.ito.business.beans.AddOnComponent;
//import com.ttl.ito.business.beans.CustomerDetails;
//import com.ttl.ito.business.beans.DBOBean;
//import com.ttl.ito.business.beans.ErectionCommissionBean;
//import com.ttl.ito.business.beans.OtherCostsBean;
//import com.ttl.ito.business.beans.PackageBean;
//import com.ttl.ito.business.beans.QuotationForm;
//import com.ttl.ito.business.beans.ReportBean;
//import com.ttl.ito.business.beans.SaveBasicDetails;
//import com.ttl.ito.business.beans.ScopeOfSupply;
//import com.ttl.ito.business.beans.TransportationDetailsBean;
//import com.ttl.ito.internal.beans.ItoConstants;
//import com.ttl.ito.internal.beans.PdfStylAttributes;
//
//import org.apache.commons.collections.MultiMap;
//import org.apache.commons.collections.map.HashedMap;
//import org.apache.commons.collections.map.MultiValueMap;
//import org.apache.log4j.Logger;
//import org.apache.poi.xwpf.usermodel.Borders;
//import org.apache.poi.xwpf.usermodel.BreakType;
//import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.apache.poi.xwpf.usermodel.XWPFRun;
//import org.apache.poi.xwpf.usermodel.XWPFStyle;
//import org.apache.poi.xwpf.usermodel.XWPFStyles;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSimpleField;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.STStyleType;
//import org.springframework.stereotype.Component;
//
//@Component
//public class WithToc {
//	private static Logger logger = Logger.getLogger(WordCreator.class);
//
//	
//
//	public static void addWordContent(XWPFDocument document, QuotationForm quotationForm,ReportBean reportBean)  {
//		
//		
//		XWPFParagraph p1 = document.createParagraph();
//		p1.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r1 = p1.createRun();
//		r1.setFontFamily("Arial");
//		r1.setText(quotationForm.getSaveBasicDetails().getOpportunitySeqNum());
//		p1.setAlignment(ParagraphAlignment.RIGHT);
//		r1.setText(quotationForm.getDate());
//		r1.addBreak();
//		
//	
//		
//		
//		XWPFParagraph p3 = document.createParagraph();
//		p3.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r3 = p3.createRun();
//		r3.setFontFamily("Arial");
//		r3.setText(quotationForm.getSaveBasicDetails().getOppName());
//		r3.addBreak();
//		r3.addBreak();
//		
//		
//		
//		XWPFParagraph p4 = document.createParagraph();
//		p4.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r4 = p4.createRun();
//		r4.setFontFamily("Arial");
//		r4.setText(quotationForm.getSaveBasicDetails().getAccountName());
//		r4.addBreak();
//		
//		
//		XWPFParagraph p5 = document.createParagraph();
//		p5.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r5= p5.createRun();
//		r5.setText("Kind Attention");
//		r5.setBold(true);
//		r5.setFontFamily("Arial");
//		r5.setText(quotationForm.getSaveBasicDetails().getCustName());
//		r5.addBreak();
//		
//		
//		XWPFParagraph p6 = document.createParagraph();
//		p6.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r6= p6.createRun();
//		r6.setFontFamily("Arial");
//		r6.setText(quotationForm.getSaveBasicDetails().getCustContactPersonName());
//		
//		
//		XWPFParagraph p7 = document.createParagraph();
//		p7.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r7= p7.createRun();
//		r7.setText("Sub:");
//		r7.setBold(true);
//		r7.setFontFamily("Arial");
//		r7.setText(quotationForm.getSubject());
//		r7.addBreak();
//		
//	
//
//		XWPFParagraph p8= document.createParagraph();
//		p8.setAlignment(ParagraphAlignment.LEFT);
//		r1.setText(quotationForm.getFixedText1());
//		r1.setText(quotationForm.getFixedText2());
//		r1.setText(quotationForm.getFixedText3());
//		r1.setText(quotationForm.getFixedText4());
//		
//		XWPFParagraph paragraph1 = document.createParagraph();
//		XWPFRun run1 = paragraph1.createRun();
//		run1.addBreak(BreakType.PAGE);
//		paragraph1.setBorderTop(Borders.SINGLE);
//		
//		document.createTOC();
//		addCustomHeadingStyle(document, "heading 1", 1);
//		addCustomHeadingStyle(document, "heading 1", 2);
//		addCustomHeadingStyle(document, "heading 1", 3);
//		addCustomHeadingStyle(document, "heading 1", 4);
//		addCustomHeadingStyle(document, "heading 1", 5);
//		addCustomHeadingStyle(document, "heading 1", 6);
//		addCustomHeadingStyle(document, "heading 1", 7);
//		addCustomHeadingStyle(document, "heading 1", 8);
//		addCustomHeadingStyle(document, "heading 1", 9);
//		addCustomHeadingStyle(document, "heading 1", 10);
//		
//		// the body content
//		XWPFParagraph paragraph = document.createParagraph();
//		XWPFRun run = paragraph.createRun();
//
//		// create 
//		CTP ctP = paragraph.getCTP();
//		CTSimpleField toc = ctP.addNewFldSimple();
//		toc.setInstr("TOC \\h");
//		toc.setDirty(STOnOff.TRUE);
//
//		paragraph = document.createParagraph();
//		run = paragraph.createRun();
//		run.addBreak(BreakType.PAGE);
//		paragraph.setBorderTop(Borders.SINGLE);
//		run.setText("1    Scope of Supply");
//		paragraph.setStyle("heading 1");
//
//		paragraph = document.createParagraph();
//		run = paragraph.createRun();
//		run.addBreak(BreakType.PAGE);
//		paragraph.setBorderTop(Borders.SINGLE);
//		run.setText("2    Technical Details/ Technical Specifications");
//		paragraph.setStyle("heading 1");
//
//		paragraph = document.createParagraph();
//		run = paragraph.createRun();
//		run.addBreak(BreakType.PAGE);
//		paragraph.setBorderTop(Borders.SINGLE);
//		run.setText("3    Performance/ Heat & Mass Balance Diagram (Attaching external files shall be provided as  a pdf files)");
//		paragraph.setStyle("heading 1");
//
//		paragraph = document.createParagraph();
//		run = paragraph.createRun();
//		run.addBreak(BreakType.PAGE);
//		paragraph.setBorderTop(Borders.SINGLE);
//		run.setText("4    Scope of Spares");
//		paragraph.setStyle("heading 1");
//
//		paragraph = document.createParagraph();
//		run = paragraph.createRun();
//		run.addBreak(BreakType.PAGE);
//		paragraph.setBorderTop(Borders.SINGLE);
//		run.setText("5    Quality Assurance/ Control plan");
//		paragraph.setStyle("heading 1");
//
//		paragraph = document.createParagraph();
//		run = paragraph.createRun();
//		run.addBreak(BreakType.PAGE);
//		paragraph.setBorderTop(Borders.SINGLE);
//		run.setText("6    Terminal Points");
//		paragraph.setStyle("heading 1");
//
//		paragraph = document.createParagraph();
//		run = paragraph.createRun();
//		run.addBreak(BreakType.PAGE);
//		paragraph.setBorderTop(Borders.SINGLE);
//		run.setText("7    Exclusion List");
//		paragraph.setStyle("heading 1");
//
//		paragraph = document.createParagraph();
//		run = paragraph.createRun();
//		run.addBreak(BreakType.PAGE);
//		paragraph.setBorderTop(Borders.SINGLE);
//		run.setText("8    Sub-supplier List");
//		paragraph.setStyle("heading 1");
//
//		paragraph = document.createParagraph();
//		run = paragraph.createRun();
//		run.addBreak(BreakType.PAGE);
//		paragraph.setBorderTop(Borders.SINGLE);
//		run.setText("9    Tender Drawings (Attaching external files shall be provided as  a pdf files)");
//		paragraph.setStyle("heading 1");
//
//		paragraph = document.createParagraph();
//		run = paragraph.createRun();
//		run.addBreak(BreakType.PAGE);
//		paragraph.setBorderTop(Borders.SINGLE);
//		run.setText("10    Clarifications/ Deviations (Attaching external files shall be provided as  a pdf files)");
//		paragraph.setStyle("heading 1");
//	}
//		
//		
//		
//		private static void addCustomHeadingStyle(XWPFDocument document, String strStyleId, int headingLevel) {
//
//			CTStyle ctStyle = CTStyle.Factory.newInstance();
//			ctStyle.setStyleId(strStyleId);
//
//			CTString styleName = CTString.Factory.newInstance();
//			styleName.setVal(strStyleId);
//			ctStyle.setName(styleName);
//
//			CTDecimalNumber indentNumber = CTDecimalNumber.Factory.newInstance();
//			indentNumber.setVal(BigInteger.valueOf(headingLevel));
//
//			// lower number > style is more prominent in the formats bar
//			ctStyle.setUiPriority(indentNumber);
//
//			CTOnOff onoffnull = CTOnOff.Factory.newInstance();
//			ctStyle.setUnhideWhenUsed(onoffnull);
//
//			// style shows up in the formats bar
//			ctStyle.setQFormat(onoffnull);
//	 
//			// style defines a heading of the given level
//			CTPPr ppr = CTPPr.Factory.newInstance();
//			ppr.setOutlineLvl(indentNumber);
//			ctStyle.setPPr(ppr);
//
//			XWPFStyle style = new XWPFStyle(ctStyle);
//
//			// is a null op if already defined
//			XWPFStyles styles = document.createStyles();
//
//			style.setType(STStyleType.PARAGRAPH);
//			styles.addStyle(style);
//
//		}
//		
//
//		
//		
//
//		
//}
//
//
//
//	
//
