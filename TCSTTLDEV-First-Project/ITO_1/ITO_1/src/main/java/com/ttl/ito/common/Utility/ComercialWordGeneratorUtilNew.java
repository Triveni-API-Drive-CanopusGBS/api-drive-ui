
package com.ttl.ito.common.Utility;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;

import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;
//import org.hibernate.validator.internal.util.logging.Log_.logger;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSimpleField;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STStyleType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabJc;

import com.ttl.ito.business.beans.QuotationForm;
import com.ttl.ito.business.beans.ReportBean;

import java.math.BigInteger;

public class ComercialWordGeneratorUtilNew {

	private static final String IMAGE1 = "C:\\Users\\Public\\Pictures\\TTL_Brand.png";//server
	
	public static XWPFDocument generateComercialWordNew(ReportBean reportBean, XWPFDocument document,QuotationForm quotationForm) throws IOException, InvalidFormatException {

		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();

		// create first page header

		CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
		sectPr.addNewTitlePg();
		XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(document, sectPr);
		XWPFHeader header = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.FIRST);
		paragraph = header.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.RIGHT);
		paragraph.setBorderBottom(Borders.SINGLE);

		run = paragraph.createRun();
		run.addPicture(new FileInputStream(IMAGE1), Document.PICTURE_TYPE_PNG, "TTL_Brand", Units.toEMU(100), Units.toEMU(50));

		// create default page header`
		XWPFHeader header1 = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);
		paragraph = header1.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.RIGHT);
		run = paragraph.createRun();
		run.addPicture(new FileInputStream(IMAGE1), Document.PICTURE_TYPE_PNG, "TTL_Brand", Units.toEMU(100), Units.toEMU(50));
		paragraph.setBorderBottom(Borders.SINGLE);
	

		CTTabStop tabStop = paragraph.getCTP().getPPr().addNewTabs().addNewTab();
		tabStop.setVal(STTabJc.RIGHT);
		int twipsPerInch = 1440;
		tabStop.setPos(BigInteger.valueOf(6 * twipsPerInch));

		// create footer default page
		XWPFFooter footer1 = headerFooterPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT);
		paragraph = footer1.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.RIGHT);
		paragraph.setBorderBottom(Borders.SINGLE);
		run = paragraph.createRun();
		
		run.setText("Page ");
		paragraph.getCTP().addNewFldSimple().setInstr("PAGE \\* MERGEFORMAT");
		run = paragraph.createRun();
		run.setText(" of ");
		paragraph.getCTP().addNewFldSimple().setInstr("NUMPAGES \\* MERGEFORMAT");
		
		ComercialWordCreatorNew.addComercialWordContentNew(document,quotationForm,reportBean); 

		return document;

	}

	

}
