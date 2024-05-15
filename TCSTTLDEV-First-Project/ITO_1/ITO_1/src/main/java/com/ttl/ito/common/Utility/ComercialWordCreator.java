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
import org.springframework.beans.factory.annotation.Autowired;

import com.ttl.ito.business.beans.QuotationForm;
import com.ttl.ito.business.beans.ReportBean;
import com.ttl.ito.business.daoImpl.QuotationDaoImpl;

public class ComercialWordCreator {

	@Autowired

	private static Logger logger = Logger.getLogger(ComercialWordCreator.class);

	public static void addComercialWordContent(XWPFDocument document, QuotationForm quotationForm,
			ReportBean reportBean) throws InvalidFormatException, IOException {

		XWPFParagraph paragraph1 = null;
		XWPFRun run1 = null;
		XWPFParagraph paragraphtable = null;
		XWPFRun runtable = null;
		XWPFParagraph paragraph = null;
		XWPFRun run = null;
		String offertype = null;
		List<ReportBean> data = new ArrayList<>();
		data = reportBean.getWordData();

		XWPFParagraph p1 = document.createParagraph();
		p1.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r1 = p1.createRun();

		r1.setText("SECTION-II ");
		r1.setFontFamily("Trebuchet MS");
		if (reportBean.getComercialWordData().get(0).getOfferType().equalsIgnoreCase("DM")) {
			offertype = "DOMESTIC";
		} else {
			offertype = "OFFSHORE";

		}
		r1.setText("		" + "(" + offertype + ")");
		r1.setFontSize(11);
		r1.setFontFamily("Trebuchet MS");
		r1.setBold(true);

		// to print itemname only once

		int i = 0;

		String prevItemName = null;
		String prevSubItemName = null;
		String prevSubItemTypeName = null;
		String prevFixedText3 = null;

		// XWPFParagraph p123 = document.createParagraph();
		// p123.setAlignment(ParagraphAlignment.LEFT);

		paragraph = document.createParagraph();
		paragraph.setBorderBottom(Borders.DOUBLE);
		paragraph.setBorderLeft(Borders.DOUBLE);
		paragraph.setBorderRight(Borders.DOUBLE);
		paragraph.setBorderTop(Borders.DOUBLE);
		paragraph.setIndentationLeft(200);
		run = paragraph.createRun();
		run1 = paragraph.createRun();
		run.setText("Definitions");
		run.addBreak();
		run.setBold(true);
		run.setFontFamily("Trebuchet MS");
		run1.setText(reportBean.getComercialWordData().get(0).getNote());
		run1.setFontFamily("Trebuchet MS");
		run.addBreak();

		XWPFParagraph p2 = document.createParagraph();
		p2.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r2 = p2.createRun();

		r2.setText("SECTION-IIA-Special Conditions ");
		r2.setFontSize(11);
		r2.setFontFamily("Trebuchet MS");
		r2.setBold(true);
		r2.setUnderline(UnderlinePatterns.THICK);
		for (i = 0; i < reportBean.getComercialWordData().size(); i++) {

			if (i == QuotationDaoImpl.sizeA)
				break;

			String sectionCd = reportBean.getComercialWordData().get(i).getSectionCd();
			int itemId = reportBean.getComercialWordData().get(i).getItemId();
			String itemName = reportBean.getComercialWordData().get(i).getItemName();
			int subItemId = reportBean.getComercialWordData().get(i).getSubItemId();
			String subItemName = reportBean.getComercialWordData().get(i).getSubItemName();
			String subItemTypeCd = reportBean.getComercialWordData().get(i).getSubItemTypeCd();
			String subItemTypeName = reportBean.getComercialWordData().get(i).getSubItemTypeName();
			String fixedText1 = reportBean.getComercialWordData().get(i).getFixedText1();
			String fixedText2 = reportBean.getComercialWordData().get(i).getFixedText2();
			String fixedText3 = reportBean.getComercialWordData().get(i).getFixedText3();
			int editFlag = reportBean.getComercialWordData().get(i).getEditFlag();
			int newColFlag = reportBean.getComercialWordData().get(i).getNewColValFlag();
			String note = reportBean.getComercialWordData().get(i).getNote();
			String offerType = reportBean.getComercialWordData().get(i).getOfferType();

			if (!itemName.equals(prevItemName) && (!itemName.equalsIgnoreCase("Definitions"))) {

				paragraph = document.createParagraph();
				run = paragraph.createRun();
				run.setText(itemName);
				run.setFontFamily("Trebuchet MS");
				run.setFontSize(11);
				run.setBold(true);

			}

			if ((subItemName != null) && (!subItemName.equals(prevSubItemName))) {
				paragraph = document.createParagraph();
				run = paragraph.createRun();
				run.setText("		");
				run.setText(subItemName);
				run.setFontFamily("Trebuchet MS");
				run.setBold(true);

			}
			if ((subItemTypeName != null) && (!subItemTypeName.equals(prevSubItemTypeName))) {
				paragraph = document.createParagraph();
				run = paragraph.createRun();
				run.setText("		");
				run.setText(subItemTypeName);
				run.setFontFamily("Trebuchet MS");
				run.setBold(true);

			}
			if ((fixedText3 != null) && (!fixedText3.equals(prevFixedText3))) {
				
				if ((fixedText1 != null) && (fixedText2 != null)) {
					XWPFParagraph paragraph11 = document.createParagraph();
					XWPFRun run11 = paragraph11.createRun();

					run11.setText(fixedText1);
					run11.setText(fixedText2);
					run11.setFontFamily("Trebuchet MS");
					run11.setFontSize(11);
				}
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

			prevItemName = itemName;
			prevSubItemName = subItemName;
			prevSubItemTypeName = subItemTypeName;
			prevFixedText3 = fixedText3;
		}

	}
}
