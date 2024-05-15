package com.ttl.ito.common.Utility;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.IOException;
import java.net.MalformedURLException;

public class headerFooter extends PdfPageEventHelper {

    private PdfTemplate t;
    private PdfTemplate t1;
    private PdfTemplate t2;
    private PdfTemplate t3;
    private PdfTemplate t4;
    
    private Image total;

    public void onOpenDocument(PdfWriter writer, Document document) {
        t = writer.getDirectContent().createTemplate(30,16);
        try {
            total = Image.getInstance(t);
          //  total.setRole(PdfName.ARTIFACT);
        } catch (DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        addHeader(writer);
        try {
			addFooter(writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
  


    public void addEmptyRows(PdfPTable table) {
    		PdfPCell cell = new PdfPCell();
    		cell = new PdfPCell(new Phrase("\n"));
    		cell.setColspan(4);
    		cell.setBorder(Rectangle.BOTTOM);
    		table.addCell(cell);
    	}
    private void addHeader(PdfWriter writer){
        PdfPTable header = new PdfPTable(2);
        try {
            // set defaults
            header.setWidths(new int[]{2, 24});
            header.setTotalWidth(527);
            header.setLockedWidth(true);
            header.getDefaultCell().setFixedHeight(40);
            header.getDefaultCell().setBorder(Rectangle.BOTTOM);
            header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
            header.addCell(new Phrase("     ", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));


            // add image
            Image logo = Image.getInstance(("C:/Users/tcsadmin1/TTL_Brand.png"));
            header.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
           header.addCell(logo);
          //  PdfContentByte cb = writer.getDirectContent();      

            /*
              Some code to place some text in the header
            */

           // Image imgSoc = Image.getInstance("C:\\...\\Logo.jpg");
           // logo.scaleToFit(100,100);
           // logo.setAbsolutePosition(490, 800);

          //  cb.addImage(logo);
          //  addEmptyRows(header);
            // add text
//            PdfPCell text = new PdfPCell();
//            text.setPaddingBottom(15);
//            text.setPaddingLeft(10);
//            text.setBorder(Rectangle.BOTTOM);
//            text.setBorderColor(BaseColor.LIGHT_GRAY);
           // text.addElement(new Phrase("iText PDF Header Footer Example", new Font(Font.FontFamily.TIMES_NEW_ROMAN, 12)));
           // text.addElement(new Phrase("http://memorynotfound.com", new Font(Font.FontFamily.TIMES_NEW_ROMAN, 8)));
//            header.addCell(text);

            // write content
            header.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
        } catch(DocumentException de) {
            throw new ExceptionConverter(de);
        } catch (MalformedURLException e) {
            throw new ExceptionConverter(e);
        } catch (IOException e) {
            throw new ExceptionConverter(e);
        }
    }

    private void addFooter(PdfWriter writer) throws IOException{
    	
    	if(writer.getPageNumber()!= 1)	{
    		
    	
    	
        PdfPTable footer = new PdfPTable(3);
        try {
            // set defaults
            footer.setWidths(new int[]{24, 2, 1});
            footer.setTotalWidth(527);
            footer.setLockedWidth(true);
            footer.getDefaultCell().setFixedHeight(40);
            footer.getDefaultCell().setBorder(Rectangle.TOP);
            footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

            // add copyright
          footer.addCell(new Phrase("     ", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));

            // add current page count
           
//            if(writer.getCurrentPageNumber()==1)
//            {
//            	
//            	//BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
////            	String foobar = "Foobar Film Festival";
//            
//            	            	for(int i=0;i<4;i++)
//            	{
//            		if(i==0)
//            		{
//            			//Font times = new Font(bf, 8);
////                        
////                    	PdfContentByte canvas = writer.getDirectContent();
//
//            		//	Phrase phrase = new Phrase("TRIVENITURBINE", times);
//                        ///	ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase, 200, 50, 0);
//                        ///	ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase, 200, 536, 0);
//                        	//ColumnText.showTextAligned(t, Element.ALIGN_CENTER, phrase, 300, 50, 0);
//                        	 ColumnText.showTextAligned(t1, Element.ALIGN_RIGHT,
//                                     new Phrase(("TRIVENITURBINE"), new Font(Font.FontFamily.TIMES_ROMAN, 8)),
//                                     300, 50, 0);
//                        	 
//            		}
//            		if(i==1)
//            		{
////            			Font times = new Font(bf, 4);
//                        
//                    	//PdfContentByte canvas = writer.getDirectContent();
//
//            			//Phrase phrase = new Phrase("12-A, Peenya Industrial Area, Bangalore 560 058, India. Tel: +91-80-2216 4000 Fax: +91-80-2216 4100 Website: www.triveniturbines.com", times);
//                        ///	ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase, 200, 50, 0);
//                        ///	ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase, 200, 536, 0);
//                        //	ColumnText.showTextAligned(t, Element.ALIGN_LEFT, phrase, 180, 40, 0);
//                        	 ColumnText.showTextAligned(t2, Element.ALIGN_RIGHT,
//                                     new Phrase(("12-A, Peenya Industrial Area, Bangalore 560 058, India. Tel: +91-80-2216 4000 Fax: +91-80-2216 4100 Website: www.triveniturbines.com"), new Font(Font.FontFamily.TIMES_ROMAN, 8)),
//                                     180, 40, 0);
//            		}
//            		if(i==2)
//            		{
//            			//Font times = new Font(bf, 4);
//                        
//                    	//PdfContentByte canvas = writer.getDirectContent();
//
//            			//Phrase phrase = new Phrase("Regd. Off: A-44, Hosiery Complex, Phase-II Extn., NOIDA - 201 305, Uttar Pradesh", times);
//                        ///	ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase, 200, 50, 0);
//                        ///	ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase, 200, 536, 0);
//                        	//ColumnText.showTextAligned(t, Element.ALIGN_CENTER, phrase, 280, 35, 0);
//                        	 ColumnText.showTextAligned(t3, Element.ALIGN_RIGHT,
//                                     new Phrase(("Regd. Off: A-44, Hosiery Complex, Phase-II Extn., NOIDA - 201 305, Uttar Pradesh"), new Font(Font.FontFamily.TIMES_ROMAN, 8)),
//                                     280, 35, 0);
//            		}
//            		if(i==3)
//            		{
//            			//Font times = new Font(bf, 4);
//                        
//                    	//PdfContentByte canvas = writer.getDirectContent();
//
//            		//	Phrase phrase = new Phrase("CIN: L29110UP1995PLC041834", times);
//                        ///	ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase, 200, 50, 0);
//                        ///	ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase, 200, 536, 0);
//                        	//ColumnText.showTextAligned(t, Element.ALIGN_CENTER, phrase, 300, 30, 0);
//                        	 ColumnText.showTextAligned(t4, Element.ALIGN_RIGHT,
//                                     new Phrase(("CIN: L29110UP1995PLC041834"), new Font(Font.FontFamily.TIMES_ROMAN, 8)),
//                                     300, 30, 0);
//            		}
//            		
//            	}
            
            	
            ///	ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase, 200, 464, 30);
            ///	ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase, 200, 428, -30);
           

            
//            }
//            else
//            {
            	System.out.println("check");

            	 footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            	 footer.addCell(new Phrase(String.format("Page %d", writer.getPageNumber()), new Font(Font.FontFamily.TIMES_ROMAN, 8)));

                 // add placeholder for total page count
                 PdfPCell totalPageCount = new PdfPCell(total);
                 totalPageCount.setBorder(Rectangle.TOP);
                 totalPageCount.setBorderColor(BaseColor.LIGHT_GRAY);
                 footer.addCell(totalPageCount);
             

//           }
            
//            }
          // PdfContentByte canvas = writer.getDirectContent();
         //   canvas.beginMarkedContentSequence(PdfName.ARTIFARCT);
            footer.writeSelectedRows(0, -1, 34, 50,  writer.getDirectContent());
            
           
        //    canvas.endMarkedContentSequence();
            // write page
        
                  } catch(DocumentException de) {
            throw new ExceptionConverter(de);
                  }
        }
    	
    }

    public void onCloseDocument(PdfWriter writer, Document document) {
    	if(writer.getPageNumber()!=1)
    	{
    		
    	
        int totalLength = String.valueOf(writer.getPageNumber()).length();
        int totalWidth = totalLength * 5;
        ColumnText.showTextAligned(t, Element.ALIGN_RIGHT,
                new Phrase(String.valueOf(writer.getPageNumber()), new Font(Font.FontFamily.TIMES_ROMAN, 8)),
                totalWidth, document.bottom()-20, 0);
    	}
    	
      
    }
}
