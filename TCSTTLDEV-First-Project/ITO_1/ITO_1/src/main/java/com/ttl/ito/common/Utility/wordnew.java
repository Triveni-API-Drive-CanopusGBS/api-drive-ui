//package com.ttl.ito.common.Utility;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.lang.reflect.Field;
//import java.math.BigInteger;
//import java.util.Calendar;
//import java.util.List;
//
//import javax.swing.GroupLayout.Alignment;
//import javax.swing.text.TableView.TableRow;
//
//import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
//import org.apache.poi.xwpf.usermodel.*;
//import org.apache.xmlbeans.XmlCursor;
//import org.apache.poi.util.Units;
//import org.apache.poi.wp.usermodel.HeaderFooterType;
//
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;
//import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
//import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColor;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblLayoutType;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextAlignment;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTxbxContent;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtrRef;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHyperlink;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPicture;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.STStyleType;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblLayoutType;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTextAlignment;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUnderline;
//import org.w3c.dom.Node;
//
//import com.microsoft.schemas.office.word.STWrapType;
//import com.microsoft.schemas.vml.CTGroup;
//import com.microsoft.schemas.vml.CTShape;
//
//public class wordnew {
//
// public static void main(String[] args) throws Exception {
//		final String IMAGE1 = "C:\\Users\\Public\\Pictures\\TTL_Brand.png";//server
//
//  XWPFDocument document= new XWPFDocument();
//  XWPFParagraph paragraphHeader = document.createParagraph();
//	XWPFRun runHeader = paragraphHeader.createRun();
//
//  CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
//  
//	sectPr.addNewTitlePg();
//	XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(document, sectPr);
//  XWPFHeader header1 = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);
//  paragraphHeader = header1.createParagraph();
//  paragraphHeader.setAlignment(ParagraphAlignment.RIGHT);
//  runHeader = paragraphHeader.createRun();
//  runHeader.addPicture(new FileInputStream(IMAGE1), Document.PICTURE_TYPE_PNG, "TTL_Brand", Units.toEMU(80), Units.toEMU(40));
//  //paragraphHeader.setBorderBottom(Borders.SINGLE);
//  //create first footer for section 2 - first created as first footer for the document
//  XWPFFooter footer = document.createFooter(HeaderFooterType.FIRST); 
//  //making it HeaderFooterType.FIRST first to be able creating one more footer later
//  //will changing this later to HeaderFooterType.DEFAULT
//
//  
//  XWPFParagraph paragraph = footer.getParagraphArray(0);
//  if (paragraph == null) paragraph = footer.createParagraph();
//  paragraph.setAlignment(ParagraphAlignment.CENTER);
//  paragraph.setAlignment(ParagraphAlignment.CENTER);
//  
//  XWPFRun run = paragraph.createRun();
//// // XWPFParagraph pn = document.createParagraph();
////
//  run.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\footer.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\footer.png", Units.toEMU(700), Units.toEMU(140));
////  
//  
// 
//  //  paragraph = footer.createParagraph();
////  XmlCursor cursor = paragraph.getCTP().newCursor();
////  XWPFTable table = footer.insertNewTbl(cursor);
////  XWPFTableRow row = table.getRow(0); if (row == null) row = table.createRow();
////  int twipsPerInch =  1440;
////  table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(6 * twipsPerInch));
////   XWPFTableCell cell = row.getCell(0); if (cell == null) cell = row.createCell();
////   CTTblWidth tblWidth = cell.getCTTc().addNewTcPr().addNewTcW();
////   tblWidth.setW(BigInteger.valueOf(2 * twipsPerInch));
////   tblWidth.setType(STTblWidth.DXA);
////   paragraph = cell.getParagraphs().get(0);
//    
//    
//   
//  // run = paragraph.createRun();
//  // run.setText("Header Table Cell ");
//   
//   //XWPFParagraph pk1=document.createParagraph();
////   XWPFHyperlinkRun hyperlinkrun1;
////   hyperlinkrun1 = createHyperlinkRun((XWPFParagraph)paragraph, "mailto:me@example.com");
////   hyperlinkrun1.setText("me@example.com");
////   hyperlinkrun1.setColor("0000FF");
////   hyperlinkrun1.setUnderline(UnderlinePatterns.SINGLE);
//  // cell.getParagraphs().get(0).addRun(hyperlinkrun1);
//  CTDrawing drawing;
//  CTAnchor anchor;
//  drawing = run.getCTR().getDrawingArray(0);
//
//  anchor = getAnchorWithGraphic(drawing, "C:\\Users\\Public\\Pictures\\footer.png", true /*behind text*/);
//
//  drawing.setAnchorArray(new CTAnchor[]{anchor});
//  drawing.removeInline(0);
//  //run = paragraph.createRun();
////  XWPFParagraph pw1 = footer.createParagraph();
////  XWPFRun rw1 = pw1.createRun();
////  
////  
////  
////
////  rw1.setText("TRIVENI TURBINE LIMITED");
////  rw1.addBreak();
////  rw1.setText("12-A, Peenya Industrial Area, Phase -1, Bengaluru, Karnataka, 560058");
////  rw1.addBreak();
////  rw1.setText("Tel: +91-80-2216 4000 Fax: +91-80-2216 4100");
////  rw1.setBold(true);
////  XWPFParagraph pw = footer.createParagraph();
////  XWPFParagraph ps = document.createParagraph();
////  XWPFParagraph pk1=document.createParagraph();
//  XWPFParagraph paragraphfooter = footer.createParagraph();
//  paragraphfooter.setAlignment(ParagraphAlignment.RIGHT);
//  XWPFHyperlinkRun hyperlinkrun11;
//  hyperlinkrun11 = createHyperlinkRun(paragraphfooter, "https://www.google.com");
//  hyperlinkrun11.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\global.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(10), Units.toEMU(8));
//  hyperlinkrun11.setText("www.trivenitubines.com");
//  hyperlinkrun11.setColor("ff8243");
//  hyperlinkrun11.setFontSize(12);
//  
//  hyperlinkrun11.addBreak();
//  
////  CTSectPr sectPr = document.getDocument().getBody().getSectPr();
////  if (sectPr == null) sectPr = document.getDocument().getBody().addNewSectPr();
////  CTPageMar pageMar = sectPr.getPgMar();
////  if (pageMar == null) pageMar = sectPr.addNewPgMar();
////  pageMar.setLeft(BigInteger.valueOf(720)); //720 TWentieths of an Inch Point (Twips) = 720/20 = 36 pt = 36/72 = 0.5"
////  pageMar.setRight(BigInteger.valueOf(720));
////  pageMar.setTop(BigInteger.valueOf(1440)); //1440 Twips = 1440/20 = 72 pt = 72/72 = 1"
////  pageMar.setBottom(BigInteger.valueOf(1440));
////
////  pageMar.setHeader(BigInteger.valueOf(908)); //45.4 pt * 20 = 908 = 45.4 pt header from top
////  pageMar.setFooter(BigInteger.valueOf(568)); //28.4 pt * 20 = 568 = 28.4 pt footer from bottom
//      
// // hyperlinkrun.setColor("0000FF");
//  ///hyperlinkrun.setUnderline(UnderlinePatterns.SINGLE);
//
//  
//  //  run.setText("Page ");
////  paragraph.getCTP().addNewFldSimple().setInstr("PAGE \\* ROMAN MERGEFORMAT");
////  run = paragraph.createRun();  
////  run.setText(" of ");
////  paragraph.getCTP().addNewFldSimple().setInstr("NUMPAGES \\* ROMAN MERGEFORMAT");
//
//  //create second footer for section 3 == last section in document
//  footer = document.createFooter(HeaderFooterType.DEFAULT);
//
//  paragraph = footer.getParagraphArray(0);
//  if (paragraph == null) paragraph = footer.createParagraph();
//  paragraph.setAlignment(ParagraphAlignment.RIGHT);
//
//  run = paragraph.createRun();  
//  run.setText("Page ");
//  paragraph.getCTP().addNewFldSimple().setInstr("PAGE \\* ARABIC MERGEFORMAT");
//  run = paragraph.createRun();  
//  run.setText(" of ");
//  paragraph.getCTP().addNewFldSimple().setInstr("NUMPAGES \\* ARABIC MERGEFORMAT");
//
//  //create document content.
//
//  //section 1
//
//
//  //paragraph with section setting for section above
//  XWPFParagraph paragraphrowz=document.createParagraph();
// 	XWPFRun runrowz=paragraphrowz.createRun();
// 	runrowz.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\cover.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(650), Units.toEMU(730));
//
// 	 //paragraph with section setting for section above
// 	  CTDrawing drawing1;
// 	  CTAnchor anchor1;
// 	  drawing1 = runrowz.getCTR().getDrawingArray(0);
//
// 	  anchor1 = getAnchorWithGraphic(drawing1, "C:\\Users\\Public\\Pictures\\cover.png", true /*behind text*/);
//
// 	  drawing1.setAnchorArray(new CTAnchor[]{anchor1});
// 	  drawing1.removeInline(0);
// 	
//
// 	  CTGroup ctGroupz = CTGroup.Factory.newInstance();
//
// 	  CTShape ctShapez = ctGroupz.addNewShape();
// 	  ctShapez.addNewWrap().setType(STWrapType.SQUARE);
// 	  ctShapez.setStyle("position:absolute;mso-position-horizontal:left;margin-top:500pt;width:80pt;height:150pt");
// 	  CTTxbxContent ctTxbxContentz = ctShapez.addNewTextbox().addNewTxbxContent();
// 	 
// 	  Node ctGroupNodez = ctGroupz.getDomNode(); 
// 	  CTPicture ctPicturez = CTPicture.Factory.parse(ctGroupNodez);
// 	 runrowz=paragraphrowz.createRun();
// 	paragraphrowz.setIndentationLeft(900);
// 	runrowz.addBreak();
// 	runrowz.addBreak();
// 	runrowz.addBreak();
// 	int year = Calendar.getInstance().get(Calendar.YEAR);
// 	int yearPlus=year+1;
// 	String yearString=Integer.toString(year)+" - "+Integer.toString(yearPlus);
// 	runrowz.setFontFamily("Calibri");
// 	runrowz.setText("TECHNO-");
// 	runrowz.addBreak();
// 	runrowz.setText("COMMERCIAL PROPOSAL");
// 	runrowz.addBreak();
// 	runrowz.setText(yearString);
//
// 	runrowz.setFontSize(30);
// 	 paragraphrowz.setAlignment(ParagraphAlignment.LEFT);
// 	  CTR cTRz = runrowz.getCTR();
// 	  cTRz.addNewPict();
// 	  cTRz.setPictArray(0, ctPicturez);
//
// 	  
//
//  paragraph = document.createParagraph();
// 
//  CTSectPr ctSectPr = paragraph.getCTP().addNewPPr().addNewSectPr();
//for(int y=0;y<1;y++)
//{
//	  if (ctSectPr == null) ctSectPr = document.getDocument().getBody().addNewSectPr();
//	  CTPageSz pageSz = ctSectPr.addNewPgSz(); // paper format letter
//	  pageSz.setW(BigInteger.valueOf(12240)); //12240 Twips = 12240/20 = 612 pt = 612/72 = 8.5"
//	  pageSz.setH(BigInteger.valueOf(15840)); //15840 Twips = 15840/20 = 792 pt = 792/72 = 11"
//	  CTPageMar pageMar = ctSectPr.getPgMar();
//	  if (pageMar == null) pageMar = ctSectPr.addNewPgMar();
//	  pageMar.setLeft(BigInteger.valueOf(0)); //720 TWentieths of an Inch Point (Twips) = 720/20 = 36 pt = 36/72 = 0.5"
//	  pageMar.setRight(BigInteger.valueOf(0));
//	  pageMar.setTop(BigInteger.valueOf(0));
//	  pageMar.setBottom(BigInteger.valueOf(0));
//	  pageMar.setFooter(BigInteger.valueOf(0));
//	  pageMar.setHeader(BigInteger.valueOf(0));
//	  pageMar.setGutter(BigInteger.valueOf(0));
//	 }
//  //section 2
//  XWPFParagraph pk=document.createParagraph();
//  XWPFRun rk;
//  
//  CTGroup ctGroup1 = CTGroup.Factory.newInstance();
//
//  CTShape ctShape = ctGroup1.addNewShape();
//  ctShape.addNewWrap().setType(STWrapType.SQUARE);
//  ctShape.setStyle("position:absolute;mso-position-horizontal:left;margin-top:680pt;width:300pt;height:150pt");
//  CTTxbxContent ctTxbxContent = ctShape.addNewTextbox().addNewTxbxContent();
//  ctTxbxContent.addNewP().addNewR().addNewT().setStringValue("TRIVENI TURBINE LIMITED12-A, "
//  		+ "Peenya Industrial Area, Phase -1, Bengaluru, "
//  		+ "Karnataka, 560058Tel: +91-80-2216 4000 "
//  		+ "Fax: +91-80-2216 4100 ➤");
//
//  Node ctGroupNode = ctGroup1.getDomNode(); 
//  CTPicture ctPicture = CTPicture.Factory.parse(ctGroupNode);
//  rk=pk.createRun();
//  pk.setIndentationLeft(720);
//  pk.setAlignment(ParagraphAlignment.RIGHT);
//  rk.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\TTL_Brand.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(50), Units.toEMU(50));
//  CTR cTR = rk.getCTR();
//  cTR.addNewPict();
//  cTR.setPictArray(0, ctPicture);
//
//  
//  
//  XWPFParagraph paragraph1;
//  XWPFRun run1;  
//  for(int h=0;h<1;h++)
//	{
//	XWPFTable table = null;
//	XWPFTableRow tableRowOne1 = null;
//
//	table = document.createTable();
//	CTTblLayoutType type = table.getCTTbl().getTblPr().addNewTblLayout();
//	type.setType(STTblLayoutType.FIXED);
//	
//	CTTblPr tblpro = table.getCTTbl().getTblPr();
//
//	CTTblBorders borders = tblpro.addNewTblBorders();
//	borders.addNewBottom().setVal(STBorder.NONE);
//	borders.addNewLeft().setVal(STBorder.NONE);
//	borders.addNewRight().setVal(STBorder.NONE);
//	borders.addNewTop().setVal(STBorder.NONE);
//	borders.addNewInsideH().setVal(STBorder.NONE);
//	borders.addNewInsideV().setVal(STBorder.NONE);
//	table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(720));
//
//	table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(500));
//	table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(3500));
//	table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(100));
//	table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(500));
//	table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(3500));
//	setTableAlign(table, ParagraphAlignment.LEFT);// Creating
//	tableRowOne1 = table.getRow(0);
//	XWPFParagraph paragraph12;
//	XWPFRun run12;
//	tableRowOne1.addNewTableCell();
//
//	paragraph12 = tableRowOne1.getCell(1).getParagraphs().get(0);
//
//	paragraph12.setAlignment(ParagraphAlignment.LEFT);
//	 run12 = paragraph12.createRun();
//	  run12.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\Untitled.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(10), Units.toEMU(10));
//
//	tableRowOne1.addNewTableCell();
//	 paragraph12 = tableRowOne1.getCell(2).getParagraphs().get(0);
//
//	paragraph12.setAlignment(ParagraphAlignment.LEFT);
//	 run12 = paragraph12.createRun();
//	run12.setFontSize(9);
//	run12.setFontFamily("Trebuchet MS");
//			  run12.setText("One of the world’s leading manufacturer of industrial steam turbine, having a installation base of over 5,000 steam turbines in 75+ countries totalling to 13 GW.");
//
//		tableRowOne1.addNewTableCell();
//
//				tableRowOne1.addNewTableCell();
//
//		paragraph12 = tableRowOne1.getCell(4).getParagraphs().get(0);
//
//	paragraph12.setAlignment(ParagraphAlignment.LEFT);
//	 run12 = paragraph12.createRun();
//	  run12.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\Untitled.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(10), Units.toEMU(10));
//
//		tableRowOne1.addNewTableCell();
//	 paragraph12 = tableRowOne1.getCell(5).getParagraphs().get(0);
//
//	paragraph12.setAlignment(ParagraphAlignment.LEFT);
//	 run12 = paragraph12.createRun();
//	run12.setFontSize(9);
//	run12.setFontFamily("Trebuchet MS");
//		  run12.setText("Two state-of-the-art production facilities located in Bengaluru, India having capacity to manufacture more than 200 steam turbines a year and equipped with a fleet of 4 Axis / 5 Axis machines to carry out manufacturing, assembling and testing under one roof.");
//	
//		  for(int k=0;k<5;k++)
//		  {
//			XWPFTableRow tableRowOne = table.createRow();
//			XWPFParagraph paragraphrow;
//			XWPFRun runrow;
//			XWPFRun runrow2;
//
//					XWPFParagraph paragraphrow1;
//			XWPFRun runrow1;
//
//			  paragraphrow = tableRowOne.getCell(1).getParagraphs().get(0);
//
//			  paragraphrow.setAlignment(ParagraphAlignment.LEFT);
//				 runrow = paragraphrow.createRun();
//				 runrow.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\Untitled.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(10), Units.toEMU(10));
//
//				
//
//		
//		  paragraphrow = tableRowOne.getCell(2).getParagraphs().get(0);
//
//		  paragraphrow.setAlignment(ParagraphAlignment.LEFT);
//			 runrow = paragraphrow.createRun();
//			runrow.setFontSize(9);
//			runrow.setFontFamily("Trebuchet MS");
//			if(k==0)
//			{
//				runrow.setText("Catering to 20+ diverse industries like Steel, Cement, Sugar, Distillery, Pulp & Paper, Textiles, Chemical, Petrochemical, Fertilizers, Petroleum Reﬁneries, Food Processing, District heating, MSW IPP, Biomass IPP etc.");
//
//
//			}
//			if(k==1)
//			{
//	runrow.setText("Offering includes steam turbines conforming to API 611 and API 612 standards.");
//			}
//
//			if(k==2)
//			{
//	runrow.setText("Round the clock to support to our turbine fleet of installations across the globe.");
//			}
//
//			
//					if(k==3)
//			{
//	runrow.setText("Life time support for spares and services to maximize the return on investment.");
//			}
//					if(k==4)
//			{
//	runrow.setText("AS 9100D-2016, ISO 9001:2015 (QMS), ISO 14001:2015 (EMS) & ISO45001:2018 (OHSAS) certiﬁedccompany, meeting industry best");
//			}
//
//			tableRowOne.getCell(3).setText(" ");
//if(k!=4)
//{
//				 		  paragraphrow = tableRowOne.getCell(4).getParagraphs().get(0);
//
//				 		 paragraphrow.setAlignment(ParagraphAlignment.LEFT);
//				 runrow2 = paragraphrow.createRun();
//				 runrow2.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\Untitled.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(10), Units.toEMU(10));
//
//}
//				  paragraphrow1 = tableRowOne.getCell(5).getParagraphs().get(0);
//
//				  paragraphrow1.setAlignment(ParagraphAlignment.LEFT);
//			 runrow1 = paragraphrow1.createRun();
//			runrow1.setFontSize(10);
//			runrow1.setFontFamily("Trebuchet MS");
//			
//			if(k==0)
//			{
//			    runrow1.setText("One of the few companies in the world having balancing and test facilities with 2 SCHENK Vacuum Tunnelling for High Speed Balancing of rotors and Live Steam Boilers with more than 20 Steam Mechanical Run Test Beds including load simulation.");
//
//
//			}
//			if(k==1)
//			{
//			    runrow1.setText("Every turbine is put through Mechanical Steam Run Test (at factory, before dispatch) at Full speed to test vibration, safety trip etc.");
//			}
//
//			if(k==2)
//			{
//			    runrow1.setText("Service centres available in close proximity to installation with a quick response time during emergency.");
//			}
//
//			
//					if(k==3)
//			{
//			    runrow1.setText("Strong association with world-renowned universities and design houses such as Indian Institute of Science (IISc), Indian Institute of Technology (IIT), University of Cambridge in United Kingdom, Polimi in Italy, Impact Technologies (Lockheed Martin) in USA, and Concepts NREC in USA.");
//			}
//					if(k==4)
//			{
//			    runrow1.setText("");
//			}
//
//		  }
//	}
//  // the body content
//   
////  XWPFParagraph paragraph11;
////  paragraph11=document.createParagraph();  
////  XWPFRun run11=paragraph11.createRun();
////
////  
////  
////  run11.setFontSize(11);
////  run11.setText("");
////  run11.addBreak();
////  run11.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\Untitled.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(10), Units.toEMU(10));
////  run11.setText("   Catering to 20+ diverse industries like Steel, Cement, Sugar, Distillery, Pulp & Paper, Textiles, Chemical, Petrochemical, Fertilizers, Petroleum Reﬁneries, Food Processing, District heating, MSW IPP, Biomass IPP etc.");
////  run11.addBreak();
////  run11.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\Untitled.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(10), Units.toEMU(10));
////  run11.setText("   Offering includes steam turbines conforming to API 611 and API 612 standards.");
////  run11.addBreak();
////  run11.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\Untitled.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(10), Units.toEMU(10));
////  run11.setText("   Round the clock to support to our turbine fleet of installations across the globe.");
////  run11.addBreak();
////  run11.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\Untitled.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(10), Units.toEMU(10));
////  run11.setText("   Service centres available in close proximity to installation with a quick response time during emergency.");
////  run11.addBreak();
////  run11.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\Untitled.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(10), Units.toEMU(10));
////  run11.setText("   Life time support for spares and services to maximize the return on investment.");
////  run11.addBreak();
////  run11.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\Untitled.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(10), Units.toEMU(10));
////  run11.setText("   AS 9100D-2016, ISO 9001:2015 (QMS), ISO 14001:2015 (EMS) & ISO 45001:2018 (OHSAS) certiﬁed company, meeting industry best practices and international standards.");
////  run11.addBreak();
////  run11.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\Untitled.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(10), Units.toEMU(10));
////  run11.setText("   Two state-of-the-art production facilities located in Bengaluru, India having capacity to manufacture more than 200 steam turbines a year and equipped with a fleet of 4 Axis / 5 Axis machines to carry out manufacturing, assembling and testing under one roof.");
////  run11.addBreak();
////  run11.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\Untitled.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(10), Units.toEMU(10));
////  run11.setText("   One of the few companies in the world having balancing and test facilities with 2 SCHENK Vacuum Tunnelling for High Speed Balancing of rotors and Live Steam Boilers with more than 20 Steam Mechanical Run Test Beds including load simulation.");
////  run11.addBreak();
////  run11.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\Untitled.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(10), Units.toEMU(10));
////  run11.setText("   Every turbine is put through Mechanical Steam Run Test (at factory, before dispatch) at Full speed to test vibration, safety trip etc.");
////  run11.addBreak();
////  run11.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\Untitled.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(10), Units.toEMU(10));
////  run11.setText("   Strong association with world-renowned universities and design houses such as Indian Institute of Science (IISc), Indian Institute of Technology (IIT), University of Cambridge in United Kingdom, Polimi in Italy, Impact Technologies (Lockheed Martin) in USA, and Concepts NREC in USA.");
//
//
//  XWPFParagraph pk1=document.createParagraph();
//  XWPFRun rk1=pk1.createRun();
//  rk1.addBreak(BreakType.PAGE);
//
//  CTGroup ctGroup11 = CTGroup.Factory.newInstance();
//
//  CTShape ctShape1 = ctGroup11.addNewShape();
//  ctShape1.addNewWrap().setType(STWrapType.SQUARE);
//  ctShape1.setStyle("position:absolute;mso-position-horizontal:left;margin-top:600pt;width:100pt;height:150pt");
//  CTTxbxContent ctTxbxContent1 = ctShape1.addNewTextbox().addNewTxbxContent();
// 
//  Node ctGroupNode1 = ctGroup11.getDomNode(); 
//  CTPicture ctPicture1 = CTPicture.Factory.parse(ctGroupNode1);
//  rk1=pk1.createRun();
//  pk1.setAlignment(ParagraphAlignment.RIGHT);
//  rk1.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\TTL_Brand.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(50), Units.toEMU(50));
//  CTR cTR1 = rk1.getCTR();
//  cTR1.addNewPict();
//  cTR1.setPictArray(0, ctPicture1);
//  paragraph1 = document.createParagraph();
//  paragraph1.setIndentationLeft(720);
//  run1=paragraph1.createRun();
//
//  run1.setText("QUALITY POLICY");
//  run1.setFontSize(18);
//  
//  
//  run1=paragraph1.createRun();
//  run1.setFontSize(12);
//  run1.setColor("FFA500");
//  run1.setText("TRIVENI TURBINE LIMITED IS COMMITED TO PROVIDE PRODUCTS AND AFTER- MARKET SERVICES OF CONSISTENT QUALITY THAT MEETS THE REQUIREMENTS AND EXCEED THE EXPECTATIONS OF GLOBAL CUSTOMERS AND ACHIEVE COM- PETITIVE EDGE THROUGH:");
//  run1.addBreak();
//  run1.addBreak();
//  run1=paragraph1.createRun();
//  run1.setFontSize(12);
//
//  run1.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\Untitled.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(10), Units.toEMU(10));
//
//  run1.setText("   Sound quality management system ");
//  run1.addBreak();
//  run1.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\Untitled.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(10), Units.toEMU(10));
//
//  run1.setText("   Active participation of all the employees");
//  run1.addBreak();
//  run1.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\Untitled.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(10), Units.toEMU(10));
//
//  run1.setText("   Continual improvement in all areas of business");
//  run1.addBreak(); 
//  run1.addBreak();
//  run1=paragraph1.createRun();
//  run1.setBold(true);
//  run1.setFontSize(18);
//  run1.setText("ENVIRONMENT AND OCCUPATION HEALTH & SAFETY (EOHS) POLICY");
//  run1.addBreak(); 
//  run1.addBreak();
//
//  run1=paragraph1.createRun();
//  run1.setFontSize(12);
//  run1.setColor("FFA500");
//  run1.setText("WE AT TRIVENI TURBINE LIMITED MANUFACTURING STEAM TURBINES & RELATED PRODUCTS INCLUDING THE ACCESSORIES ARE COMMITTED TO:");
//  run1.addBreak(); 
//  run1.addBreak();
//
//  run1=paragraph1.createRun();
//  run1.setFontSize(12);
//  
//	XWPFTable table = null;
//	XWPFTableRow tableRowOne1 = null;
//
//	table = document.createTable();
//	CTTblLayoutType type = table.getCTTbl().getTblPr().addNewTblLayout();
//	type.setType(STTblLayoutType.FIXED);
//	
//	CTTblPr tblpro = table.getCTTbl().getTblPr();
//
//	CTTblBorders borders = tblpro.addNewTblBorders();
//	borders.addNewBottom().setVal(STBorder.NONE);
//	borders.addNewLeft().setVal(STBorder.NONE);
//	borders.addNewRight().setVal(STBorder.NONE);
//	borders.addNewTop().setVal(STBorder.NONE);
//	borders.addNewInsideH().setVal(STBorder.NONE);
//	borders.addNewInsideV().setVal(STBorder.NONE);
//	table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(720));
//	table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(500));
//	table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(8000));
//	setTableAlign(table, ParagraphAlignment.LEFT);// Creating
//	tableRowOne1 = table.getRow(0);
//	tableRowOne1.addNewTableCell();
//
//	XWPFParagraph paragraph12 = tableRowOne1.getCell(1).getParagraphs().get(0);
//
//	paragraph12.setAlignment(ParagraphAlignment.LEFT);
//	XWPFRun run12 = paragraph12.createRun();
//	  run12.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\Untitled.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(10), Units.toEMU(10));
//
//	tableRowOne1.addNewTableCell();
//	XWPFParagraph paragraph121 = tableRowOne1.getCell(2).getParagraphs().get(0);
//
//	paragraph121.setAlignment(ParagraphAlignment.LEFT);
//	XWPFRun run121 = paragraph121.createRun();
//	run121.setFontSize(12);
//	run121.setFontFamily("Trebuchet MS");
//	  run121.setText("Produce the product and provide services to enhance the customer satisfaction by ensuring protection of environment and prevention of pollution; by reducing the waste, conservation of resources and creation of safe working environment to prevent injury and ill health.");
//	
//	  /////
//	  for(int k=0;k<4;k++)
//	  {
//		XWPFTableRow tableRowOne = table.createRow();
//		XWPFParagraph paragraphrow;
//		XWPFRun runrow;
//
//		  paragraphrow = tableRowOne.getCell(1).getParagraphs().get(0);
//
//			paragraph121.setAlignment(ParagraphAlignment.LEFT);
//			 runrow = paragraphrow.createRun();
//			 runrow.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\Untitled.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(10), Units.toEMU(10));
//
//			
//
//	
//	  paragraphrow = tableRowOne.getCell(2).getParagraphs().get(0);
//
//		paragraph121.setAlignment(ParagraphAlignment.LEFT);
//		 runrow = paragraphrow.createRun();
//		runrow.setFontSize(12);
//		runrow.setFontFamily("Trebuchet MS");
//		if(k==0)
//		{
//			runrow.setText("Comply with all applicable legal and other requirements with relation to EOHS.");
//
//		}
//		if(k==1)
//		{
//			runrow.setText("Establish appropriate objectives in respect of EOHS to facilitate continual improvement.");
//
//		}
//
//		if(k==2)
//		{
//			runrow.setText("Establish appropriate objectives in respect of EOHS to facilitate continual improvement.");
//
//		}
//
//		if(k==3)
//		{
//			runrow.setText("Persons working on behalf of the organization shall be provided with necessary competency requirement in respect of EOHS with the intent that they are made aware of their individual obligations.");
//
//		}
//
//	  }
//	  XWPFParagraph pk11=document.createParagraph();
//	  XWPFRun rk11=pk11.createRun();
//	  rk11.addBreak(BreakType.PAGE);
//
//	  CTGroup ctGroup111 = CTGroup.Factory.newInstance();
//
//	  CTShape ctShape11 = ctGroup111.addNewShape();
//	  ctShape11.addNewWrap().setType(STWrapType.SQUARE);
//	  ctShape11.setStyle("position:absolute;mso-position-horizontal:left;margin-top:600pt;width:300pt;height:50pt");
//	  CTTxbxContent ctTxbxContent11 = ctShape11.addNewTextbox().addNewTxbxContent();
//	 
//	  Node ctGroupNode11 = ctGroup111.getDomNode(); 
//	  CTPicture ctPicture11 = CTPicture.Factory.parse(ctGroupNode11);
//	  rk11=pk11.createRun();
//	  pk11.setAlignment(ParagraphAlignment.RIGHT);
//	  rk11.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\TTL_Brand.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(50), Units.toEMU(50));
//	  CTR cTR11 = rk11.getCTR();
//	  cTR11.addNewPict();
//	  cTR11.setPictArray(0, ctPicture11);
//
//	  XWPFParagraph paragraphimage=document.createParagraph();
//	  XWPFRun runimage; 
//	  paragraphimage.setIndentationLeft(720);
//	  runimage= paragraphimage.createRun();
//	// // XWPFParagraph pn = document.createParagraph();
//	//
//	  runimage.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\image1.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(500), Units.toEMU(200));
//	//  
//	  runimage.addBreak();
//	  runimage= paragraphimage.createRun();
//	  paragraphimage.setAlignment(ParagraphAlignment.CENTER);
//runimage.setText("Unit - II, Sompura, Bangalore, India");
//	  /////
//	  
//		  table = null;
//			tableRowOne1 = null;
//
//			table = document.createTable();
//			CTTblLayoutType type1 = table.getCTTbl().getTblPr().addNewTblLayout();
//			type1.setType(STTblLayoutType.FIXED);
//			
//			CTTblPr tblpro1 = table.getCTTbl().getTblPr();
//
//			CTTblBorders borders1 = tblpro1.addNewTblBorders();
//			borders1.addNewBottom().setVal(STBorder.NONE);
//			borders1.addNewLeft().setVal(STBorder.NONE);
//			borders1.addNewRight().setVal(STBorder.NONE);
//			borders1.addNewTop().setVal(STBorder.NONE);
//			borders1.addNewInsideH().setVal(STBorder.NONE);
//			borders1.addNewInsideV().setVal(STBorder.NONE);
//			table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));
//
//			table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
//			table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));
//
//			table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
//			
//			setTableAlign(table, ParagraphAlignment.LEFT);// Creating
//			tableRowOne1 = table.getRow(0);
//			XWPFParagraph paragraph1211;
//			XWPFRun run1211;
//			
//			tableRowOne1.addNewTableCell();
//
//			paragraph1211 = tableRowOne1.getCell(1).getParagraphs().get(0);
//
//			paragraph1211.setAlignment(ParagraphAlignment.LEFT);
//			 run1211 = paragraph1211.createRun();
//			  run1211.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\image2.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(200), Units.toEMU(200));
//			  run1211.addBreak();
//			  run1211.setText("    High speed vacuum balancing machine");
//			tableRowOne1.addNewTableCell();	
//			tableRowOne1.addNewTableCell();
//
//			 paragraph1211 = tableRowOne1.getCell(3).getParagraphs().get(0);
//
//			paragraph1211.setAlignment(ParagraphAlignment.LEFT);
//			 run1211 = paragraph1211.createRun();
//			  run1211.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\image3.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(200), Units.toEMU(200));
//			  run1211.addBreak();
//			  run1211.setText(" Fleet of 4 axis & 5 axis CNC machining centre");
//			
//	  
//	  
//	  
//	  
//	  
//	  
////  run1.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\Untitled.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(10), Units.toEMU(10));
////
////  run1.setText("Produce the product and provide services to enhance the customer satisfaction by ensuring protection of environment and prevention of pollution; by reducing the waste, conservation of resources and creation of safe working environment to prevent injury and ill health.");
////run1.addBreak();
////run1.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\Untitled.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(10), Units.toEMU(10));
////
////run1.setText("Comply with all applicable legal and other requirements with relation to EOHS.");
////run1.addBreak();
////run1.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\Untitled.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(10), Units.toEMU(10));
////
////run1.setText("Establish appropriate objectives in respect of EOHS to facilitate continual improvement.");
////run1.addBreak();
////run1.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\Untitled.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(10), Units.toEMU(10));
////
////run1.setText("Communicate the policy effectively to all personnel working under the control of the organization including relevant interested parties such as Sub contractors, Suppliers, Transporters and Customers.");
////run1.addBreak();
////run1.addPicture(new FileInputStream("C:\\Users\\Public\\Pictures\\Untitled.png"), XWPFDocument.PICTURE_TYPE_PNG, "C:\\Users\\Public\\Pictures\\TTL_Brand.png", Units.toEMU(10), Units.toEMU(10));
////
////run1.setText("Persons working on behalf of the organization shall be provided with necessary competency requirement in respect of EOHS with the intent that they are made aware of their individual obligations.");
//			 
//  //paragraph with section setting for section above
//  paragraph1 = document.createParagraph();
//  CTSectPr ctSectPrSect2 = paragraph1.getCTP().addNewPPr().addNewSectPr(); //we need this later
//  if (ctSectPrSect2 == null) ctSectPrSect2 = document.getDocument().getBody().addNewSectPr();
//  CTPageSz pageSz = ctSectPrSect2.addNewPgSz(); // paper format letter
//  pageSz.setW(BigInteger.valueOf(12240)); //12240 Twips = 12240/20 = 612 pt = 612/72 = 8.5"
//  pageSz.setH(BigInteger.valueOf(15840)); //15840 Twips = 15840/20 = 792 pt = 792/72 = 11"
//  CTPageMar pageMar = ctSectPrSect2.getPgMar();
//  if (pageMar == null) pageMar = ctSectPrSect2.addNewPgMar();
//  pageMar.setLeft(BigInteger.valueOf(0)); //720 TWentieths of an Inch Point (Twips) = 720/20 = 36 pt = 36/72 = 0.5"
//  pageMar.setRight(BigInteger.valueOf(720));
//  pageMar.setTop(BigInteger.valueOf(0));
//  pageMar.setBottom(BigInteger.valueOf(0));
//  pageMar.setFooter(BigInteger.valueOf(1500));
//  pageMar.setHeader(BigInteger.valueOf(0));
//  pageMar.setGutter(BigInteger.valueOf(0));
//  
//  //section 3 
// 
//////////// static text
//  
//  //////
//  //section setting for section above == last section in document
//  CTDocument1 ctDocument = document.getDocument();
//  CTBody ctBody = ctDocument.getBody();
//  CTSectPr ctSectPrLastSect = ctBody.getSectPr(); //there must be a SectPr already because of the footer settings above
//  if (ctSectPrLastSect == null) ctSectPrLastSect = document.getDocument().getBody().addNewSectPr();
//  CTPageSz pageSz1 = ctSectPrLastSect.addNewPgSz(); // paper format letter
//  pageSz1.setW(BigInteger.valueOf(12240)); //12240 Twips = 12240/20 = 612 pt = 612/72 = 8.5"
//  pageSz1.setH(BigInteger.valueOf(15840)); //15840 Twips = 15840/20 = 792 pt = 792/72 = 11"
//  CTPageMar pageMar1 = ctSectPrLastSect.getPgMar();
//  if (pageMar1 == null) pageMar1 = ctSectPrLastSect.addNewPgMar();
//  pageMar1.setLeft(BigInteger.valueOf(720)); //720 TWentieths of an Inch Point (Twips) = 720/20 = 36 pt = 36/72 = 0.5"
//  pageMar1.setRight(BigInteger.valueOf(720));
//  pageMar1.setTop(BigInteger.valueOf(0));
//  pageMar1.setBottom(BigInteger.valueOf(0));
//  pageMar1.setFooter(BigInteger.valueOf(0));
//  pageMar1.setHeader(BigInteger.valueOf(0));
//  pageMar1.setGutter(BigInteger.valueOf(0));
// 
//  //get footer reference of first footer and move this to be footer reference for section 2
//  CTHdrFtrRef ctHdrFtrRef = ctSectPrLastSect.getFooterReferenceArray(0);
//  ctHdrFtrRef.setType(STHdrFtr.DEFAULT); //change this from STHdrFtr.FIRST to STHdrFtr.DEFAULT
//  CTHdrFtrRef[] ctHdrFtrRefs = new CTHdrFtrRef[]{ctHdrFtrRef};
//  ctSectPrSect2.setFooterReferenceArray(ctHdrFtrRefs);
//  ctSectPrLastSect.removeFooterReference(0);
//
//  //unset "there is a title page" for the whole document because we have a section for the title (cover)
//  ctSectPrLastSect.unsetTitlePg();
// 
// 
// 
//
// 
// 
////  Node ctGroupNode = ctGroup1.getDomNode(); 
////  CTPicture ctPicture = CTPicture.Factory.parse(ctGroupNode);
////  run11=paragraph1.createRun();  
////  CTR cTR = run11.getCTR();
////  cTR.addNewPict();
////  cTR.setPictArray(0, ctPicture);
// 
//   
//  document.write(new FileOutputStream("D:\\Basavesh\\TTL_Brand1.docx"));
//  
//
// }
// private static CTAnchor getAnchorWithGraphic(CTDrawing drawing /*inline drawing*/ , 
//		  String drawingDescr, boolean behind) throws Exception {
//
//		  CTGraphicalObject graphicalobject = drawing.getInlineArray(0).getGraphic();
//		  long width = drawing.getInlineArray(0).getExtent().getCx();
//		  long height = drawing.getInlineArray(0).getExtent().getCy();
//
//		  String anchorXML = 
//		   "<wp:anchor xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" "
//		  +"simplePos=\"0\" relativeHeight=\"0\" behindDoc=\""+((behind)?1:0)+"\" locked=\"0\" layoutInCell=\"1\" allowOverlap=\"1\">"
//		  +"<wp:simplePos x=\"0\" y=\"0\"/>"
//		  +"<wp:positionH relativeFrom=\"column\"><wp:posOffset>0</wp:posOffset></wp:positionH>"
//		  +"<wp:positionV relativeFrom=\"paragraph\"><wp:posOffset>0</wp:posOffset></wp:positionV>"
//		  +"<wp:extent cx=\""+width+"\" cy=\""+height+"\"/>"
//		  +"<wp:effectExtent l=\"0\" t=\"0\" r=\"0\" b=\"0\"/><wp:wrapNone/>"
//		  +"<wp:docPr id=\"1\" name=\"Drawing 0\" descr=\""+drawingDescr+"\"/><wp:cNvGraphicFramePr/>"
//		  +"</wp:anchor>";
//
//		  drawing = CTDrawing.Factory.parse(anchorXML);
//		  CTAnchor anchor = drawing.getAnchorArray(0);
//		  anchor.setGraphic(graphicalobject);
//		  return anchor;  
//		 }
// static XWPFHyperlinkRun createHyperlinkRun(XWPFParagraph paragraph, String uri) throws Exception {
//	  String rId =paragraph.getPart().getPackagePart().addExternalRelationship(uri, XWPFRelation.HYPERLINK.getRelation()).getId();
//
//	  CTHyperlink cthyperLink=paragraph.getCTP().addNewHyperlink();
//	  cthyperLink.setId(rId);
//	  cthyperLink.addNewR();
//	  return new XWPFHyperlinkRun(
//	    cthyperLink,
//	    cthyperLink.getRArray(0),
//	    paragraph
//	   );
//	 }
// public static void appendExternalHyperlink(String url, String text, XWPFRun run){
//
//	    //Add the link as External relationship
//	    String id=run.getDocument().getPackagePart().addExternalRelationship(url, XWPFRelation.HYPERLINK.getRelation()).getId();
//
//	    //Append the link and bind it to the relationship
//	    CTHyperlink cLink=run.getParagraph().getCTP().addNewHyperlink();
//	    cLink.setId(id);
//
//	    //Create the linked text
//	    CTText ctText=CTText.Factory.newInstance();
//	    ctText.setStringValue(text);
//	    CTR ctr=CTR.Factory.newInstance();
//	    ctr.setTArray(new CTText[]{ctText});
//
//	     //Create the formatting
//	     CTRPr rpr = ctr.addNewRPr();
//	     CTColor colour = CTColor.Factory.newInstance();
//	     colour.setVal("0000FF");
//	     rpr.setColor(colour);
//	     CTRPr rpr1 = ctr.addNewRPr();
//	     rpr1.addNewU().setVal(STUnderline.SINGLE);
//
//	    //Insert the linked text into the link
//	    cLink.setRArray(new CTR[]{ctr});
//	}
// private static XWPFStyle createNamedStyle(XWPFStyles styles, STStyleType.Enum styleType, String styleId) {
//	  if (styles == null || styleId == null) return null;
//	  XWPFStyle style = styles.getStyle(styleId);
//	  if (style == null) {
//	   CTStyle ctStyle = CTStyle.Factory.newInstance();
//	   ctStyle.addNewName().setVal(styleId);
//	   ctStyle.setCustomStyle(STOnOff.TRUE);
//	   style = new XWPFStyle(ctStyle, styles);
//	   style.setType(styleType);
//	   style.setStyleId(styleId);
//	   styles.addStyle(style);
//	  }
//	  return style;
//	 }
//
//	 private static void applyTextAlignment(XWPFStyle style, TextAlignment value) throws Exception {
//	  if (style == null || value == null) return;
//
//	  Field _ctStyles = XWPFStyles.class.getDeclaredField("ctStyles");
//	  _ctStyles.setAccessible(true);
//	  CTStyles ctStyles = (CTStyles)_ctStyles.get(style.getStyles());
//
//	  for (CTStyle ctStyle : ctStyles.getStyleList()) {
//	   if (ctStyle.getStyleId().equals(style.getStyleId())) {
//	    CTPPr ppr = ctStyle.getPPr(); 
//	    if (ppr == null) ppr = ctStyle.addNewPPr();
//	    CTTextAlignment ctTextAlignment = ppr.getTextAlignment(); 
//	    if (ctTextAlignment == null) ctTextAlignment = ppr.addNewTextAlignment();
//	    if (value == TextAlignment.AUTO) {
//	     ctTextAlignment.setVal(STTextAlignment.AUTO);
//	    } else if (value == TextAlignment.BASELINE) {
//	     ctTextAlignment.setVal(STTextAlignment.BASELINE);
//	    } else if (value == TextAlignment.BOTTOM) {
//	     ctTextAlignment.setVal(STTextAlignment.BOTTOM);
//	    } else if (value == TextAlignment.CENTER) {
//	     ctTextAlignment.setVal(STTextAlignment.CENTER);
//	    } else if (value == TextAlignment.TOP) {
//	     ctTextAlignment.setVal(STTextAlignment.TOP);
//	    }
//	    style.setStyle(ctStyle);
//	   }
//	  }
//	 }
//
//	 private static void applyJustification(XWPFStyle style, ParagraphAlignment value) throws Exception {
//	  if (style == null || value == null) return;
//
//	  Field _ctStyles = XWPFStyles.class.getDeclaredField("ctStyles");
//	  _ctStyles.setAccessible(true);
//	  CTStyles ctStyles = (CTStyles)_ctStyles.get(style.getStyles());
//
//	  for (CTStyle ctStyle : ctStyles.getStyleList()) {
//	   if (ctStyle.getStyleId().equals(style.getStyleId())) {
//	    CTPPr ppr = ctStyle.getPPr(); if (ppr == null) ppr = ctStyle.addNewPPr();
//	    CTJc jc = ppr.getJc(); if (jc == null) jc = ppr.addNewJc();
//	    if (value == ParagraphAlignment.BOTH) {
//	     jc.setVal(STJc.BOTH);
//	    } else if (value == ParagraphAlignment.CENTER) {
//	     jc.setVal(STJc.CENTER);
//	    } else if (value == ParagraphAlignment.DISTRIBUTE) {
//	     jc.setVal(STJc.DISTRIBUTE);
//	    } else if (value == ParagraphAlignment.HIGH_KASHIDA) {
//	     jc.setVal(STJc.HIGH_KASHIDA);
//	    } else if (value == ParagraphAlignment.LEFT) {
//	     jc.setVal(STJc.LEFT);
//	    } else if (value == ParagraphAlignment.LOW_KASHIDA) {
//	     jc.setVal(STJc.LOW_KASHIDA);
//	    } else if (value == ParagraphAlignment.MEDIUM_KASHIDA) {
//	     jc.setVal(STJc.MEDIUM_KASHIDA);
//	    } else if (value == ParagraphAlignment.NUM_TAB) {
//	     jc.setVal(STJc.NUM_TAB);
//	    } else if (value == ParagraphAlignment.RIGHT) {
//	     jc.setVal(STJc.RIGHT);
//	    } else if (value == ParagraphAlignment.THAI_DISTRIBUTE) {
//	     jc.setVal(STJc.THAI_DISTRIBUTE);
//	    }
//	    style.setStyle(ctStyle);
//	   }
//	  }
//	 }
//		public static void setTableAlign(XWPFTable table, ParagraphAlignment align) {
//			CTTblPr tblPr = table.getCTTbl().getTblPr();
//			CTJc jc = (tblPr.isSetJc() ? tblPr.getJc() : tblPr.addNewJc());
//			STJc.Enum en = STJc.Enum.forInt(align.getValue());
//			jc.setVal(en);
//		}
//}
//	
//	



