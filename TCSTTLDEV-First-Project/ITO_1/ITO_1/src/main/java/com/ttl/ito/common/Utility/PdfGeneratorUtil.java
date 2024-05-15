package com.ttl.ito.common.Utility;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.ttl.ito.business.beans.QuotationForm;
import com.ttl.ito.business.beans.ReportBean;

@Component
public class PdfGeneratorUtil {
	@Autowired
	private PDFCreator pdfCreator;
	
	private  Logger logger = Logger.getLogger(PDFCreator.class);
	
	 private static final String TITLE = "CostSheet";
	    
	    public Document  generatePdf (ReportBean reportBean, Document document,PdfWriter writer) {  
	    	logger.info("generatePdf Start");
	    	
	        try {
	        	
	            PdfHeaderFooter event = new PdfHeaderFooter();
	            event.setHeader("CostSheet");
	            writer.setPageEvent(event);
	            document.open();
	        
	            pdfCreator.addCostSheetBody(document, TITLE,reportBean); 
	          
	            logger.info("generatePdf End");
	        }catch (Exception e) {
	            e.printStackTrace();
	            logger.error("Exception : "+e);
	        }
			return document;
	    }
	    
	    
	    
	    public Document  generateAddOnPdf (ReportBean reportBean, Document document,PdfWriter writer) {  
	    	logger.info("generateAddOnPdf Start");
	    	
	        try {
	        	
	            PdfHeaderFooter event = new PdfHeaderFooter();
	            event.setHeader(" AddOn CostSheet");
	            writer.setPageEvent(event);
	            document.open();
	        
	            pdfCreator.addAddOnCostSheetBody(document, TITLE,reportBean); 
	          
	            logger.info("generateAddOnPdf End");
	        }catch (Exception e) {
	            e.printStackTrace();
	            logger.error("Exception : "+e);
	        }
			return document;
	    }
	    
	    
	    
	    public Document  quotExportPdfDet(QuotationForm quotationForm, Document document,PdfWriter writer) {  
	    	
	        try {
	        	
	            PdfHeaderFooter event = new PdfHeaderFooter();
	            event.setHeader("Input Report");
	            writer.setPageEvent(event);
	            document.open();
	        
	            pdfCreator.addCostSheetBodyDet(document, TITLE,quotationForm); 
	          
	        }catch (Exception e) {
	            e.printStackTrace();
	            logger.error("Exception : "+e);
	        }
			return document;
	    }
	    
	    public Document  generateTechReport (ReportBean reportBean, Document document,PdfWriter writer) {  
	    	logger.info("generateTechReport Start");
	    	
	        try {
	        	
	        	headerFooter event = new headerFooter(); 
	            //event.setHeader("TechReport");
	            writer.setPageEvent(event);
	            document.open();
	        
	            pdfCreator.addTechnicalReportBodyDet(document, TITLE,reportBean); 
	          
	            logger.info("generateTechReport End");
	        }catch (Exception e) {
	            e.printStackTrace();
	            logger.error("Exception : "+e);
	        }
			return document;
	    }
	    
	   
}
