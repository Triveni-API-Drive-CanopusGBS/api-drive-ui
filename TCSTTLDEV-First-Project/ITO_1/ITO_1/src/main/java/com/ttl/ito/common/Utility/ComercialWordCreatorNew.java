

package com.ttl.ito.common.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;

import com.ttl.ito.business.beans.QuotationForm;
import com.ttl.ito.business.beans.ReportBean;
import com.ttl.ito.business.daoImpl.QuotationDaoImpl;

public class ComercialWordCreatorNew {

	@Autowired

	private static Logger logger = Logger.getLogger(ComercialWordCreatorNew.class);

	public static void addComercialWordContentNew(XWPFDocument document, QuotationForm quotationForm,
			ReportBean reportBean) throws InvalidFormatException, IOException {
	
		XWPFParagraph paragraph = null;
		XWPFRun run= null;
		XWPFParagraph paragraphheading = null;
		XWPFRun runheading= null;
		XWPFRun run1 =null;
		
		XWPFTable table=null;
		XWPFTableRow tablerow=null;
		XWPFTable tabledef=null;
		XWPFTableRow tablerowdef=null;
		String offertype=null;
		int counter=1;
		
		if(reportBean.getComercialWordDataNew().get(0).getOfferType().equalsIgnoreCase("DM"))
		{
			offertype="DOMESTIC";
		}
		else
		{
			offertype="OFFSHORE";

		}
		XWPFParagraph p1 = document.createParagraph();
		p1.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r1 = p1.createRun();

		r1.setText("SECTION-IIB" + " (" + offertype + ")");
		r1.setFontSize(13);
		r1.setFontFamily("Trebuchet MS");

		r1.setBold(true);
		r1.setUnderline(UnderlinePatterns.THICK);
		paragraph = document.createParagraph();
		paragraph.setBorderBottom(Borders.DOUBLE);
		paragraph.setBorderLeft(Borders.DOUBLE);
		paragraph.setBorderRight(Borders.DOUBLE);
		paragraph.setBorderTop(Borders.DOUBLE);
		paragraph.setIndentationLeft(200);
		paragraph.setAlignment(ParagraphAlignment.LEFT);
		paragraph.setSpacingBeforeLines(1);
		run = paragraph.createRun();
		run1 = paragraph.createRun();
		run.setText("Definitions:");
		run.setFontSize(12);
		run.setFontFamily("Trebuchet MS");

		run.addBreak();
		
		run.setBold(true);
		run1.setText(reportBean.getComercialWordDataNew().get(0).getNote());
		run1.setFontFamily("Trebuchet MS");

		run.addBreak();

//		tabledef=document.createTable();
//		tabledef.setCellMargins(100, 0, 100, 100);
//		tablerowdef = tabledef.getRow(0);
//		if(tablerowdef!=null)
//		{
//			 XWPFParagraph paragraph1 = tablerowdef.getCell(0).getParagraphs().get(0);
//				paragraph1.setIndentationLeft(100);
//				paragraph1.setIndentationRight(100);
//				paragraph1.setAlignment(ParagraphAlignment.CENTER);
//				XWPFRun run11 = paragraph1.createRun();
//				run11.setFontSize(11);
//				run11.setFontFamily("Trebuchet MS");
//				run11.setText("Definitions:");
//				run11.addBreak();
//				run11.setText(reportBean.getComercialWordDataNew().get(0).getNote());
//
//		}
//		XWPFParagraph ptemp = document.createParagraph();
//        XWPFRun runtemp= ptemp.createRun();
		XWPFParagraph p2 = document.createParagraph();
		p2.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r2 = p2.createRun();

		r2.setText("SECTION-IIB");
		r2.setFontSize(13);
		r2.setBold(true);
		r2.setUnderline(UnderlinePatterns.THICK);
		r2.setFontFamily("Trebuchet MS");
		
		XWPFParagraph p3 = document.createParagraph();
		p3.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r21 = p3.createRun();

		r21.setText("GENERAL TERMS & CONDITIONS " + "("+ offertype +")");
		r21.setFontSize(13);
		r21.setBold(true);
		r21.setUnderline(UnderlinePatterns.THICK);
		r21.setFontFamily("Trebuchet MS");



logger.info(reportBean.getComercialWordDataNew().size());
		int i = 0;
		for (i = 0; i < reportBean.getComercialWordDataNew().size(); i++) {
			logger.info("1");

			if (i == QuotationDaoImpl.sizeB)
				break;
			logger.info("2");

			String sectionCd = reportBean.getComercialWordDataNew().get(i).getSectionCd();
			int itemId = reportBean.getComercialWordDataNew().get(i).getItemId();
			String itemName = reportBean.getComercialWordDataNew().get(i).getItemName();
			int subItemId = reportBean.getComercialWordDataNew().get(i).getSubItemId();
			String subItemName = reportBean.getComercialWordDataNew().get(i).getSubItemName();
			String subItemTypeCd = reportBean.getComercialWordDataNew().get(i).getSubItemTypeCd();
			String subItemTypeName = reportBean.getComercialWordDataNew().get(i).getSubItemTypeName();
			String fixedText1 = reportBean.getComercialWordDataNew().get(i).getFixedText1();
			String fixedText2 = reportBean.getComercialWordDataNew().get(i).getFixedText2();
			String fixedText3 = reportBean.getComercialWordDataNew().get(i).getFixedText3();
			int editFlag = reportBean.getComercialWordDataNew().get(i).getEditFlag();
			int newColFlag = reportBean.getComercialWordDataNew().get(i).getNewColValFlag();
			String note = reportBean.getComercialWordDataNew().get(i).getNote();
			String offferType = reportBean.getComercialWordDataNew().get(i).getOfferType();
			
			logger.info("21");
            if(sectionCd.equalsIgnoreCase("B"))
            {
			            
			if(!itemName.equalsIgnoreCase("Definitions"))
			{
				paragraph=document.createParagraph();
				run=paragraph.createRun();

			run.setText(String.valueOf(counter)+ "  " +itemName);
			run.setBold(true);
			run.setFontSize(13);
			run.setFontFamily("Trebuchet MS");	
			counter=counter+1;
			}
			logger.info("31");
			logger.info(itemName);


			if(itemName.equalsIgnoreCase("Price"))
			{
				logger.info("3");

				if(fixedText1!=null && fixedText2!=null)
				{
				 String temptext=fixedText1+ " "+fixedText2+" "+fixedText3;
				 if (temptext.contains("\n")) {
						String[] arrOfStr = temptext.split("\n", 200);
						for (int x1 = 0; x1 < arrOfStr.length; x1++) {
							XWPFParagraph paragraph21 = document.createParagraph();

						
							XWPFRun run123 = paragraph21.createRun();
							run123.setFontSize(11);
							 run123.setFontFamily("Trebuchet MS");
							run123.setText(arrOfStr[x1]);
														

													}
					} else {
						paragraph = document.createParagraph();
						run = paragraph.createRun();
						run.setText(temptext);
						 run.setFontFamily("Trebuchet MS");
						run.setFontSize(11);

					}
				}
				else
				{
					if (fixedText3.contains("\n")) {
						String[] arrOfStr = fixedText3.split("\n", 200);
						for (int x1 = 0; x1 < arrOfStr.length; x1++) {
							XWPFParagraph paragraph21 = document.createParagraph();

						
							XWPFRun run123 = paragraph21.createRun();
							run123.setFontSize(11);
							 run123.setFontFamily("Trebuchet MS");
							run123.setText(arrOfStr[x1]);

													}
					} else {
						paragraph = document.createParagraph();
						run = paragraph.createRun();
						run.setText(fixedText3);
						run.setFontFamily("Trebuchet MS");
						run.setFontSize(11);

					}

				}
							}
			else if(itemName.equalsIgnoreCase("Termination"))
			{
				logger.info("4");

				String[] temparray = fixedText2.split("\n", 20);
				logger.info(temparray.length);
				for(int j=0;j<temparray.length;j++)
				{
					logger.info(temparray[j]);
	
				}

				if (fixedText1.contains("\n")) {
					String[] arrOfStr = fixedText1.split("\n", 200);
					for (int x1 = 0; x1 < arrOfStr.length; x1++) {
						XWPFParagraph paragraph21 = document.createParagraph();

					
						XWPFRun run123 = paragraph21.createRun();
						run123.setFontSize(11);
						run123.setFontFamily("Trebuchet MS");
						run123.setText(arrOfStr[x1]);

												}
				} else {
					paragraph = document.createParagraph();
					run = paragraph.createRun();
					run.setText(fixedText1);
					run.setFontFamily("Trebuchet MS");
					run.setFontSize(11);

				}
				/////for table in termination
				table=document.createTable();
				table.setCellMargins(100, 0, 100, 100);
				tablerow = table.getRow(0);
				if(tablerow!=null)
				{
					 XWPFParagraph paragraph1 = tablerow.getCell(0).getParagraphs().get(0);
						paragraph1.setIndentationLeft(100);
						paragraph1.setIndentationRight(100);
						paragraph1.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run11 = paragraph1.createRun();
						run11.setFontSize(11);
						run11.setFontFamily("Trebuchet MS");
						run11.setText("Contract Termination within");
						run11.setBold(true);
						tablerow.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
						/////
						tablerow.addNewTableCell();
						XWPFParagraph paragraph2 = tablerow.getCell(1).getParagraphs().get(0);
						paragraph2.setIndentationLeft(100);
						paragraph2.setIndentationRight(100);
						paragraph2.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run2 = paragraph2.createRun();
						run2.setFontSize(11);
						run2.setFontFamily("Trebuchet MS");
						run2.setText("Within 15 days");
						run2.setBold(true);
						tablerow.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
						
						
						tablerow.addNewTableCell();
						XWPFParagraph paragraph3 = tablerow.getCell(2).getParagraphs().get(0);
						paragraph3.setIndentationLeft(100);
						paragraph3.setIndentationRight(100);
						paragraph3.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run3 = paragraph3.createRun();
						run3.setFontSize(11);
						run3.setFontFamily("Trebuchet MS");
						run3.setText("Between 15 to 30 days");
						run3.setBold(true);
						tablerow.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);

						
						tablerow.addNewTableCell();
						XWPFParagraph paragraph4 = tablerow.getCell(3).getParagraphs().get(0);
						paragraph4.setIndentationLeft(100);
						paragraph4.setIndentationRight(100);
						paragraph4.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run4 = paragraph4.createRun();
						run4.setFontSize(11);
						run4.setFontFamily("Trebuchet MS");
						run4.setText("Between 1 to 2 months");
						run4.setBold(true);
						tablerow.getCell(3).setVerticalAlignment(XWPFVertAlign.CENTER);

						
						tablerow.addNewTableCell();
						XWPFParagraph paragraph5 = tablerow.getCell(4).getParagraphs().get(0);
						paragraph5.setIndentationLeft(100);
						paragraph5.setIndentationRight(100);
						paragraph5.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run5 = paragraph5.createRun();
						run5.setFontSize(11);
						run5.setFontFamily("Trebuchet MS");
						run5.setText("Between 2 to 4 months");
						run5.setBold(true);
						tablerow.getCell(4).setVerticalAlignment(XWPFVertAlign.CENTER);
						
						
						tablerow.addNewTableCell();
						XWPFParagraph paragraph6 = tablerow.getCell(5).getParagraphs().get(0);
						paragraph6.setIndentationLeft(100);
						paragraph6.setIndentationRight(100);
						paragraph6.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run6 = paragraph6.createRun();
						run6.setFontSize(11);
						run6.setFontFamily("Trebuchet MS");
						run6.setText("Between 4 to 5 months");
						run6.setBold(true);
						tablerow.getCell(5).setVerticalAlignment(XWPFVertAlign.CENTER);
						
												tablerow.addNewTableCell();
						XWPFParagraph paragraph7 = tablerow.getCell(6).getParagraphs().get(0);
						paragraph7.setIndentationLeft(100);
						paragraph7.setIndentationRight(100);
						paragraph7.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run7 = paragraph7.createRun();
						run7.setFontSize(11);
						run7.setFontFamily("Trebuchet MS");
						run7.setText("More than 5 months");
						run7.setBold(true);
						tablerow.getCell(6).setVerticalAlignment(XWPFVertAlign.CENTER);

					tablerow=null;
					
					
				}
				
				XWPFTableRow tableRowOne = table.createRow();
				 XWPFParagraph paragraph1a = tableRowOne.getCell(0).getParagraphs().get(0);
					paragraph1a.setIndentationLeft(100);
					paragraph1a.setIndentationRight(100);
					paragraph1a.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun run1a = paragraph1a.createRun();
					run1a.setFontSize(11);
					run1a.setFontFamily("Trebuchet MS");
					run1a.setText("Compensation to Seller 10%");
					run1a.setBold(true);
					tableRowOne.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);

					String[] arrOfStr = fixedText2.split(",");
					for (int x1 = 0; x1 < arrOfStr.length; x1++) {
						XWPFParagraph paragraph2a = tableRowOne.getCell(x1+1).getParagraphs().get(0);
						paragraph2a.setIndentationLeft(100);
						paragraph2a.setIndentationRight(100);
						paragraph2a.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run2a = paragraph2a.createRun();
						run2a.setFontSize(11);
						run2a.setFontFamily("Trebuchet MS");
						run2a.setText(arrOfStr[x1]);
						tableRowOne.getCell(x1+1).setVerticalAlignment(XWPFVertAlign.CENTER);

					}					
									/////
										
					
//					XWPFParagraph paragraph3a = tableRowOne.getCell(2).getParagraphs().get(0);
//					paragraph3a.setIndentationLeft(100);
//					paragraph3a.setIndentationRight(100);
//					paragraph3a.setAlignment(ParagraphAlignment.CENTER);
//					XWPFRun run3a = paragraph3a.createRun();
//					run3a.setFontSize(11);
//					run3a.setFontFamily("Trebuchet MS");
//					run3a.setText("Between 15 to 30 days");
//					run3a.setBold(true);
//					tableRowOne.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
//
//					
//					XWPFParagraph paragraph4a = tableRowOne.getCell(3).getParagraphs().get(0);
//					paragraph4a.setIndentationLeft(100);
//					paragraph4a.setIndentationRight(100);
//					paragraph4a.setAlignment(ParagraphAlignment.CENTER);
//					XWPFRun run4a = paragraph4a.createRun();
//					run4a.setFontSize(11);
//					run4a.setFontFamily("Trebuchet MS");
//					run4a.setText("Between 1 to 2 months");
//					run4a.setBold(true);
//					tableRowOne.getCell(3).setVerticalAlignment(XWPFVertAlign.CENTER);
//
//					
//					XWPFParagraph paragraph5a = tableRowOne.getCell(4).getParagraphs().get(0);
//					paragraph5a.setIndentationLeft(100);
//					paragraph5a.setIndentationRight(100);
//					paragraph5a.setAlignment(ParagraphAlignment.CENTER);
//					XWPFRun run5a = paragraph5a.createRun();
//					run5a.setFontSize(11);
//					run5a.setFontFamily("Trebuchet MS");
//					run5a.setText("Between 2 to 4 months");
//					run5a.setBold(true);
//					tableRowOne.getCell(4).setVerticalAlignment(XWPFVertAlign.CENTER);
//					
//					
//					XWPFParagraph paragraph6a = tableRowOne.getCell(5).getParagraphs().get(0);
//					paragraph6a.setIndentationLeft(100);
//					paragraph6a.setIndentationRight(100);
//					paragraph6a.setAlignment(ParagraphAlignment.CENTER);
//					XWPFRun run6a = paragraph6a.createRun();
//					run6a.setFontSize(11);
//				    run6a.setFontFamily("Trebuchet MS");
//					run6a.setText("Between 4 to 5 months");
//					run6a.setBold(true);
//					tableRowOne.getCell(5).setVerticalAlignment(XWPFVertAlign.CENTER);
//					
//					XWPFParagraph paragraph7a = tableRowOne.getCell(6).getParagraphs().get(0);
//					paragraph7a.setIndentationLeft(100);
//					paragraph7a.setIndentationRight(100);
//					paragraph7a.setAlignment(ParagraphAlignment.CENTER);
//					XWPFRun run7a = paragraph7a.createRun();
//					run7a.setFontSize(11);
//					run7a.setFontFamily("Trebuchet MS");
//					run7a.setText("More than 5 months");
//					run7a.setBold(true);
//					tableRowOne.getCell(6).setVerticalAlignment(XWPFVertAlign.CENTER);
//					
					XWPFParagraph pa=document.createParagraph();
					XWPFRun ru=pa.createRun();
					
					if (fixedText3.contains("\n")) {
						String[] arrOfStr1 = fixedText3.split("\n", 200);
						for (int x1 = 0; x1 < arrOfStr1.length; x1++) {
							XWPFParagraph paragraph21 = document.createParagraph();

						
							XWPFRun run123 = paragraph21.createRun();
							run123.setFontSize(11);
							run123.setFontFamily("Trebuchet MS");
							run123.setText(arrOfStr1[x1]);

						}
					} else {
						paragraph = document.createParagraph();
						run = paragraph.createRun();
						run.setText(fixedText3);
						run.setFontFamily("Trebuchet MS");
						run.setFontSize(11);

					}


			}
			else if(!itemName.equalsIgnoreCase("Definitions"))
			{
				if (fixedText3.contains("\n")) {
					String[] arrOfStr = fixedText3.split("\n", 200);
					for (int x1 = 0; x1 < arrOfStr.length; x1++) {
						XWPFParagraph paragraph21 = document.createParagraph();

					
						XWPFRun run123 = paragraph21.createRun();
						run123.setFontSize(11);
						run123.setFontFamily("Trebuchet MS");
						run123.setText(arrOfStr[x1]);
						
												}
				} else  {
					paragraph = document.createParagraph();
					run = paragraph.createRun();
					run.setText(fixedText3);
					run.setFontFamily("Trebuchet MS");
					run.setFontSize(11);

				}

			}

			
			

		}
	}
	}
}

