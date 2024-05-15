package com.ttl.ito.common.Utility;

import java.io.IOException;
import java.math.BigInteger;

import java.util.ArrayList;
import java.util.HashMap;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.ttl.ito.business.beans.AddOnComponent;
import com.ttl.ito.business.beans.CustomerDetails;
import com.ttl.ito.business.beans.DBOBean;
import com.ttl.ito.business.beans.ErectionCommissionBean;
import com.ttl.ito.business.beans.OtherCostsBean;
import com.ttl.ito.business.beans.PackageBean;
import com.ttl.ito.business.beans.QuotationForm;
import com.ttl.ito.business.beans.ReportBean;
import com.ttl.ito.business.beans.SaveBasicDetails;
import com.ttl.ito.business.beans.ScopeOfSupply;
import com.ttl.ito.business.beans.TransportationDetailsBean;
import com.ttl.ito.business.daoImpl.QuotationDaoImpl;
import com.ttl.ito.internal.beans.ItoConstants;
import com.ttl.ito.internal.beans.PdfStylAttributes;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STStyleType;
import org.springframework.stereotype.Component;

@Component
public class PDFCreator {
	public static final String FONT = "C:/Users/tcsadmin1/FreeSans.ttf";

	private static Logger logger = Logger.getLogger(PDFCreator.class);

	private final static String[] HEADER_ARRAY = { "Sl.No.", "Category", "Resource (text)", "Qty", "Total Value",
			"Remarks" };
	private final static String[] HEADER_ARRAY_ADDON = { "Sl.No.", "Category", "Resource (text)", "Total ItemCost",
			"Base Cost", "Discount", "LHS", "RHS", "AddOnCost", "Remarks" };

	public void addCostSheetContent(Document document, PdfPTable table, ReportBean reportBean)
			throws DocumentException {

		Paragraph paragraph = new Paragraph();
		paragraph.setFont(PdfStylAttributes.NORMAL_FONT);
		createCostSheetTable(paragraph, table, reportBean);
		document.add(paragraph);

	}

	private void createCostSheetTable(Paragraph paragraph, PdfPTable table, ReportBean reportBean)
			throws BadElementException {

		addHeaderInTable(HEADER_ARRAY, table);
		List<ReportBean> data = new ArrayList<>();
		data = reportBean.getReportData();
		int count = 1;
		int cellcount = 1;
		int rowspan = 0;
		// displaying turbine Cost

		List<String> scopeCodes = new ArrayList<>();
		for (int i = 0; i < data.size(); i++) {
			scopeCodes.add(data.get(i).getScopeCode());
		}

		String firstInd = data.get(0).getScopeCode();

		for (int i = 0; i < data.size(); i++) {
			addToTable(table, String.valueOf(count) + ".");

			if (i == 0)
				rowspan = scopeCodes.lastIndexOf(data.get(i).getScopeCode());

			if ((i > 0) && (data.get(i).getScopeCode().equalsIgnoreCase(firstInd))) {
				cellcount = cellcount + 1;
			} else if ((i > 0) && (!data.get(i).getScopeCode().equalsIgnoreCase(firstInd))) {
				cellcount = 1;
				rowspan = (scopeCodes.lastIndexOf(data.get(i).getScopeCode()) - (scopeCodes.lastIndexOf(firstInd)) - 1);
				firstInd = data.get(i).getScopeCode();
			}

			logger.info("** cellcount **" + cellcount + "**scope code**" + data.get(i).getScopeCode() + "**rowspan**"
					+ rowspan);

			if (cellcount == 1) {

				PdfPCell cell1 = new PdfPCell();
				cell1.addElement(new Phrase(data.get(i).getScopeCode(), PdfStylAttributes.NORMAL_FONT));
				cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell1.setRowspan(rowspan + 1);
				table.addCell(cell1);
			}
			// addToTable(table,data.getScopeCode());

			String word8 = data.get(i).getItemName();
			if (word8.contains("#")) {
				String result = word8.replace("#", "");
				logger.info(result);
				addToTableBold(table, result);
			}

			else {
				addToTable(table, data.get(i).getItemName());
			}

			if ((data.get(i).getQuantity()) == 0) {
				addToTable(table, "");
			} else {
				addToTable(table, String.valueOf(data.get(i).getQuantity()));
			}

			if (data.get(i).isOverwrittenPriceFlag()) {

				String word = data.get(i).getCost();
				logger.info(word);

				if ((data.get(i).getCost()) != null)

				{

					if (word.contains(".")) {
						// 1,23,79,910.00
						String result = word.replace(".", "");
						logger.info(result);// 1,23,79,91000
						String result1 = result.trim();
						logger.info(result1);// 1,23,79,91000
						String result2 = result1.replaceAll(".$", "");
						logger.info(result2);
						String result3 = result2.replaceAll(".$", "");
						logger.info(result3);// 1,23,79,9100
						if (result3.contains("#")) {
							String result4 = result3.replace("#", "");
							logger.info(result4);
							addToTableredcolor(table, result4);

						}

						else {
							addToTableredcolor(table, result3);
						}
					}

				} else {
					addToTableredcolor(table, "");
				}

			}

			else {
				String word = data.get(i).getCost();
				logger.info(word);
				if ((data.get(i).getCost()) != null) {

					if (word.contains(".")) {
						String result = word.replace(".", "");
						logger.info(result);
						String result1 = result.trim();
						logger.info(result1);
						String result2 = result1.replaceAll(".$", "");
						logger.info(result2);
						String result3 = result2.replaceAll(".$", "");
						logger.info(result3);

						if (result3.contains("#")) {
							String result4 = result3.replace("#", "");
							logger.info(result4);
							addToTableBold(table, result4);
						}

						else {

							addToTable(table, result3);
						}

					}
				} else {
					addToTable(table, "");
				}

			}
			addToTable(table, data.get(i).getRemarks());

			count++;

		}

	}

	/**
	 * Helper methods start here
	 * 
	 * @param quotationForm
	 **/
	public void addCostSheetBody(Document document, String title, ReportBean reportBean) throws DocumentException {

		Paragraph preface = new Paragraph();
		addEmptyLine(preface, 1);

		preface.add(new Paragraph("Quotation Number - " + reportBean.getQuotNum(), PdfStylAttributes.BLUE_FONT));
		preface.setAlignment(Element.ALIGN_LEFT);

		preface.add(new Paragraph("Quotation Status - " + reportBean.getQuotStatus(), PdfStylAttributes.BLUE_FONT));
		preface.setAlignment(Element.ALIGN_LEFT);

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100);
		table.setHeaderRows(0);
		// addHeaderInTable(HEADER_ARRAY, table);

		PdfPCell cell = new PdfPCell();
		cell.addElement(new Phrase("Customer Name	 ", PdfStylAttributes.BOLD_FONT));
		cell.setColspan(2);
		table.addCell(cell);

		String word = reportBean.getOppName();
		if (word.contains(reportBean.getOpportunitySeqNum())) {
			String result = word.replace(reportBean.getOpportunitySeqNum(), "");
			String result1 = result.trim();
			String result2 = result1.replaceAll(".$", "");
			PdfPCell cell1 = new PdfPCell();
			cell1.addElement(new Phrase(result2, PdfStylAttributes.BOLD_FONT));
			table.addCell(cell1);
		}

		PdfPCell cell2a = new PdfPCell();
		cell2a.addElement(new Phrase("Date", PdfStylAttributes.SMALL_BOLD));

		table.addCell(cell2a);

		PdfPCell cell3a = new PdfPCell();
		cell3a.addElement(new Phrase(reportBean.getDate(), PdfStylAttributes.SMALL_BOLD));
		cell3a.setColspan(2);
		table.addCell(cell3a);

		PdfPCell cell2 = new PdfPCell();
		cell2.addElement(new Phrase("End User Name		 ", PdfStylAttributes.SMALL_BOLD));
		cell2.setColspan(2);
		table.addCell(cell2);

		PdfPCell cell3 = new PdfPCell();
		cell3.addElement(new Phrase(reportBean.getEndUserName(), PdfStylAttributes.SMALL_BOLD));
		table.addCell(cell3);

		PdfPCell cell2aa = new PdfPCell();
		cell2aa.addElement(new Phrase("Revision No.", PdfStylAttributes.SMALL_BOLD));

		table.addCell(cell2aa);

		PdfPCell cell3aa = new PdfPCell();
		cell3aa.addElement(new Phrase(reportBean.getRevNum(), PdfStylAttributes.SMALL_BOLD));
		cell3aa.setColspan(2);
		table.addCell(cell3aa);

		// type
		PdfPCell cell22 = new PdfPCell();
		cell22.addElement(new Phrase("Type	 ", PdfStylAttributes.SMALL_BOLD));
		cell22.setColspan(2);
		table.addCell(cell22);

		PdfPCell cell32 = new PdfPCell();
		cell32.addElement(new Phrase(reportBean.getType(), PdfStylAttributes.SMALL_BOLD));
		table.addCell(cell32);

		PdfPCell cell2aaa = new PdfPCell();
		cell2aaa.addElement(new Phrase("", PdfStylAttributes.SMALL_BOLD));
		table.addCell(cell2aaa);

		PdfPCell cell3aaa = new PdfPCell();
		cell3aaa.addElement(new Phrase("", PdfStylAttributes.SMALL_BOLD));
		cell3aaa.setColspan(2);
		table.addCell(cell3aaa);

		// frame

		PdfPCell cell20 = new PdfPCell();
		cell20.addElement(new Phrase("Frame	 ", PdfStylAttributes.SMALL_BOLD));
		cell20.setColspan(2);
		table.addCell(cell20);

		PdfPCell cell30 = new PdfPCell();
		cell30.addElement(new Phrase(reportBean.getFrameName(), PdfStylAttributes.SMALL_BOLD));
		table.addCell(cell30);

		// empty date
		PdfPCell cell2aaaa = new PdfPCell();
		cell2aaaa.addElement(new Phrase("", PdfStylAttributes.SMALL_BOLD));
		table.addCell(cell2aaaa);

		PdfPCell cell3aaaa = new PdfPCell();
		cell3aaaa.addElement(new Phrase("", PdfStylAttributes.SMALL_BOLD));
		cell3aaaa.setColspan(2);
		table.addCell(cell3aaaa);

		// Turbine/ Generator MW
		PdfPCell cell23 = new PdfPCell();
		cell23.addElement(new Phrase("Turbine/ Generator MW 	 ", PdfStylAttributes.SMALL_BOLD));
		cell23.setColspan(2);
		table.addCell(cell23);

		PdfPCell cell37 = new PdfPCell();
		cell37.addElement(new Phrase(reportBean.getTurbGeneratorMw(), PdfStylAttributes.SMALL_BOLD));
		table.addCell(cell37);

		// empty date
		PdfPCell cell2aaaaa = new PdfPCell();
		cell2aaaaa.addElement(new Phrase("", PdfStylAttributes.SMALL_BOLD));
		table.addCell(cell2aaaaa);

		PdfPCell cell3aaaaa = new PdfPCell();
		cell3aaaaa.addElement(new Phrase("", PdfStylAttributes.SMALL_BOLD));
		cell3aaaaa.setColspan(2);
		table.addCell(cell3aaaaa);

		// Order date / LOI date
		PdfPCell cell25 = new PdfPCell();
		cell25.addElement(new Phrase("Order date / LOI date	", PdfStylAttributes.SMALL_BOLD));
		cell25.setColspan(2);
		table.addCell(cell25);

		PdfPCell cell35 = new PdfPCell();
		cell35.addElement(new Phrase("", PdfStylAttributes.SMALL_BOLD));
		table.addCell(cell35);

		// empty date
		PdfPCell cell2aaaaaa = new PdfPCell();
		cell2aaaaaa.addElement(new Phrase("", PdfStylAttributes.SMALL_BOLD));
		table.addCell(cell2aaaaaa);

		PdfPCell cell3aaaaaa = new PdfPCell();
		cell3aaaaaa.addElement(new Phrase("", PdfStylAttributes.SMALL_BOLD));
		cell3aaaaaa.setColspan(2);
		table.addCell(cell3aaaaaa);
		// Delievery / Commissioing
		PdfPCell cell26 = new PdfPCell();
		cell26.addElement(new Phrase("Delievery / Commissioing	", PdfStylAttributes.SMALL_BOLD));
		cell26.setColspan(2);
		table.addCell(cell26);

		PdfPCell cell36 = new PdfPCell();
		cell36.addElement(new Phrase("", PdfStylAttributes.SMALL_BOLD));
		table.addCell(cell36);
		// empty date
		PdfPCell cell2aaaaaaa = new PdfPCell();
		cell2aaaaaaa.addElement(new Phrase("", PdfStylAttributes.SMALL_BOLD));
		table.addCell(cell2aaaaaaa);

		PdfPCell cell3aaaaaaa = new PdfPCell();
		cell3aaaaaaa.addElement(new Phrase("", PdfStylAttributes.SMALL_BOLD));
		cell3aaaaaaa.setColspan(2);
		table.addCell(cell3aaaaaaa);

		PdfPCell cell6 = new PdfPCell();
		cell6.addElement(new Phrase("Base Planning Object	 ", PdfStylAttributes.NORMAL_BOLD));
		cell6.setColspan(6);
		table.addCell(cell6);

		addCostSheetContent(document, table, reportBean);
		// float[] colWidths = new float[] { 5f, 25f, 40f, 10f, 20f };
		float[] colWidths = new float[] { 5f, 15f, 40f, 10f, 15f, 10f };
		table.setWidths(colWidths);

		preface.add(table);
		document.add(preface);
		document.newPage();
		document.newPage();

	}

	// AddOn CostSheet

	public void addAddOnCostSheetContent(Document document, PdfPTable table, ReportBean reportBean)
			throws DocumentException, IOException {

		Paragraph paragraph = new Paragraph();
		paragraph.setFont(PdfStylAttributes.NORMAL_FONT);
		createAddOnCostSheetTable(paragraph, table, reportBean);
		document.add(paragraph);

	}

	private void createAddOnCostSheetTable(Paragraph paragraph, PdfPTable table, ReportBean reportBean)
			throws DocumentException, IOException {

		addHeaderInTableAddOn(HEADER_ARRAY_ADDON, table);
		List<ReportBean> data = new ArrayList<>();
		data = reportBean.getAddOnReportData();
		int count = 1;
		int cellcount = 1;
		int rowspan = 0;
		String previtemname = null;
		String previtemcostnew = null;
		String prevbasiccostnew = null;
		String prevdiscountper = null;

		// displaying turbine Cost
		List<String> slNo = new ArrayList<>();
		List<String> scopeCodes = new ArrayList<>();
		List<String> itemDesc = new ArrayList<>();
		List<String> totalItemCost = new ArrayList<>();
		List<String> addOnCost = new ArrayList<>();
		List<String> basicCost = new ArrayList<>();
		List<String> discount = new ArrayList<>();
		List<Integer> colId = new ArrayList<>();
		for (int i = 0; i < data.size(); i++) {
			slNo.add(data.get(i).getSlNo());
			scopeCodes.add(data.get(i).getScopeCode());
			itemDesc.add(data.get(i).getItemDesc());
			totalItemCost.add(data.get(i).getItemCostNew());
			addOnCost.add(data.get(i).getAddOnCostNew());
			basicCost.add(data.get(i).getBasicCostNew());
			discount.add(data.get(i).getDiscountPer());
			colId.add(data.get(i).getColId());
		}
		String zeroInd = data.get(0).getSlNo();
		String firstInd = data.get(0).getScopeCode();
		String secondInd = data.get(0).getItemDesc();
		String thirdInd = data.get(0).getItemCostNew();
		String fourthInd = data.get(0).getBasicCostNew();
		String fifthInd = data.get(0).getDiscountPer();

		for (int i = 0; i < data.size(); i++) {
			addToTable(table, String.valueOf(count) + ".");

			// // set sl no.
			//
			// if (i == 0)
			// rowspan = slNo.lastIndexOf(data.get(i).getSlNo());
			//
			// if ((i > 0) && (data.get(i).getSlNo().equalsIgnoreCase(zeroInd)))
			// {
			// cellcount = cellcount + 1;
			// } else if ((i > 0) &&
			// (!data.get(i).getSlNo().equalsIgnoreCase(zeroInd))) {
			// cellcount = 1;
			// rowspan = (slNo.lastIndexOf(data.get(i).getSlNo()) -
			// (slNo.lastIndexOf(zeroInd)) - 1);
			// zeroInd = data.get(i).getSlNo();
			// }
			//
			// logger.info(
			// "** cellcount **" + cellcount + "**scope code**" +
			// data.get(i).getSlNo() + "**rowspan**" + rowspan);
			//
			// if (cellcount == 1) {
			//
			// PdfPCell cell1 = new PdfPCell();
			// cell1.addElement(new Phrase(data.get(i).getSlNo(),
			// PdfStylAttributes.NORMAL_FONT));
			// cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			// cell1.setRowspan(rowspan + 1);
			// table.addCell(cell1);
			// }

			if (i == 0)
				rowspan = scopeCodes.lastIndexOf(data.get(i).getScopeCode());

			if ((i > 0) && (data.get(i).getScopeCode().equalsIgnoreCase(firstInd))) {
				cellcount = cellcount + 1;
			} else if ((i > 0) && (!data.get(i).getScopeCode().equalsIgnoreCase(firstInd))) {
				cellcount = 1;
				rowspan = (scopeCodes.lastIndexOf(data.get(i).getScopeCode()) - (scopeCodes.lastIndexOf(firstInd)) - 1);
				firstInd = data.get(i).getScopeCode();
			}

			logger.info("** cellcount **" + cellcount + "**scope code**" + data.get(i).getScopeCode() + "**rowspan**"
					+ rowspan);

			if (cellcount == 1) {

				PdfPCell cell1 = new PdfPCell();
				cell1.addElement(new Phrase(data.get(i).getScopeCode(), PdfStylAttributes.NORMAL_FONT));
				cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell1.setRowspan(rowspan + 1);
				table.addCell(cell1);
			}

			// itemdesc
			if (i == 0)
				rowspan = itemDesc.lastIndexOf(data.get(i).getItemDesc());

			if ((i > 0) && (data.get(i).getItemDesc().equalsIgnoreCase(secondInd))) {
				cellcount = cellcount + 1;
			} else if ((i > 0) && (!data.get(i).getItemDesc().equalsIgnoreCase(secondInd))) {
				cellcount = 1;
				rowspan = (itemDesc.lastIndexOf(data.get(i).getItemDesc()) - (itemDesc.lastIndexOf(secondInd)) - 1);
				secondInd = data.get(i).getItemDesc();
			}

			logger.info("** cellcount **" + cellcount + "**scope code**" + data.get(i).getItemDesc() + "**rowspan**"
					+ rowspan);

			if (cellcount == 1) {

				String word8 = data.get(i).getItemDesc();
				if (word8.contains("#")) {
					String result = word8.replace("#", "");
					logger.info(result);
					PdfPCell cell1 = new PdfPCell();
					cell1.addElement(new Phrase(result, PdfStylAttributes.NORMAL_BOLD));
					cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell1.setRowspan(rowspan + 1);
					table.addCell(cell1);
				}

				else {
					PdfPCell cell1 = new PdfPCell();
					cell1.addElement(new Phrase(data.get(i).getItemDesc(), PdfStylAttributes.NORMAL_FONT));
					cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell1.setRowspan(rowspan + 1);
					table.addCell(cell1);
				}

			}

			// total item cost

			if (data.get(i).isItemOverwrittenPriceFlag()) {

				if (i > 0) {
					

					if (!(data.get(i).getItemDesc().equalsIgnoreCase(data.get(i - 1).getItemDesc()))) {
						String word = data.get(i).getItemCostNew();
						if ((data.get(i).getItemCostNew()) != null)
						{
							if (word.contains(".")) 
							{
								String result = word.replace(".", "");
								logger.info(result);
								String result1 = result.trim();
								logger.info(result1);
								String result2 = result1.replaceAll(".$", "");
								logger.info(result2);
								String result3 = result2.replaceAll(".$", "");
								logger.info(result3);

								if (result3.contains("#")) 
								{
									String result4 = result3.replace("#", "");
									logger.info(result4);
									PdfPCell cell1 = new PdfPCell();
									cell1.addElement(new Phrase(result4, PdfStylAttributes.OVERWRITE));
									cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
									table.addCell(cell1);

								}

								else 
								{
									PdfPCell cell1 = new PdfPCell();
									cell1.addElement(new Phrase(result3, PdfStylAttributes.OVERWRITE));
									cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
									table.addCell(cell1);
								}

							}

						}

						else 
						{
							PdfPCell cell1 = new PdfPCell();
							cell1.addElement(new Phrase("", PdfStylAttributes.OVERWRITE));
							cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
							table.addCell(cell1);
						}
					} 
					
					else 
					{
						PdfPCell cell1 = new PdfPCell();
						cell1.addElement(new Phrase("", PdfStylAttributes.OVERWRITE));
						cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
						table.addCell(cell1);
					}

				} // i>0(first if)
			
				else {
					String word = data.get(i).getItemCostNew();
					if (word != null) {
						if (word.contains(".")) {
							String result = word.replace(".", "");
							logger.info(result);
							String result1 = result.trim();
							logger.info(result1);
							String result2 = result1.replaceAll(".$", "");
							logger.info(result2);
							String result3 = result2.replaceAll(".$", "");
							logger.info(result3);

							if (result3.contains("#")) {
								String result4 = result3.replace("#", "");
								logger.info(result4);
								PdfPCell cell1 = new PdfPCell();
								cell1.addElement(new Phrase(result4, PdfStylAttributes.OVERWRITE));
								cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
								table.addCell(cell1);

							}

							else {
								PdfPCell cell1 = new PdfPCell();
								cell1.addElement(new Phrase(result3, PdfStylAttributes.OVERWRITE));
								cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
								table.addCell(cell1);
							}

						}
					} else {
						PdfPCell cell1 = new PdfPCell();
						cell1.addElement(new Phrase("", PdfStylAttributes.OVERWRITE));
						cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
						table.addCell(cell1);
					}
				}
			}
			else{
				if (i > 0) {
					

					if (!(data.get(i).getItemDesc().equalsIgnoreCase(data.get(i - 1).getItemDesc()))) {
						String word = data.get(i).getItemCostNew();
						if ((data.get(i).getItemCostNew()) != null)
						{
							if (word.contains(".")) 
							{
								String result = word.replace(".", "");
								logger.info(result);
								String result1 = result.trim();
								logger.info(result1);
								String result2 = result1.replaceAll(".$", "");
								logger.info(result2);
								String result3 = result2.replaceAll(".$", "");
								logger.info(result3);

								if (result3.contains("#")) 
								{
									String result4 = result3.replace("#", "");
									logger.info(result4);
									PdfPCell cell1 = new PdfPCell();
									cell1.addElement(new Phrase(result4, PdfStylAttributes.NORMAL_BOLD));
									cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
									table.addCell(cell1);

								}

								else 
								{
									PdfPCell cell1 = new PdfPCell();
									cell1.addElement(new Phrase(result3, PdfStylAttributes.NORMAL_FONT));
									cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
									table.addCell(cell1);
								}

							}

						}

						else 
						{
							PdfPCell cell1 = new PdfPCell();
							cell1.addElement(new Phrase("", PdfStylAttributes.NORMAL_FONT));
							cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
							table.addCell(cell1);
						}
					} 
					
					else 
					{
						PdfPCell cell1 = new PdfPCell();
						cell1.addElement(new Phrase("", PdfStylAttributes.NORMAL_FONT));
						cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
						table.addCell(cell1);
					}

				} // i>0(first if)
			
				else {
					String word = data.get(i).getItemCostNew();
					if (word != null) {
						if (word.contains(".")) {
							String result = word.replace(".", "");
							logger.info(result);
							String result1 = result.trim();
							logger.info(result1);
							String result2 = result1.replaceAll(".$", "");
							logger.info(result2);
							String result3 = result2.replaceAll(".$", "");
							logger.info(result3);

							if (result3.contains("#")) {
								String result4 = result3.replace("#", "");
								logger.info(result4);
								PdfPCell cell1 = new PdfPCell();
								cell1.addElement(new Phrase(result4, PdfStylAttributes.NORMAL_BOLD));
								cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
								table.addCell(cell1);

							}

							else {
								PdfPCell cell1 = new PdfPCell();
								cell1.addElement(new Phrase(result3, PdfStylAttributes.NORMAL_FONT));
								cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
								table.addCell(cell1);
							}

						}
					} else {
						PdfPCell cell1 = new PdfPCell();
						cell1.addElement(new Phrase("", PdfStylAttributes.NORMAL_FONT));
						cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
						table.addCell(cell1);
					}
				}
			}
			// Basic cost

			if (i > 0) {

				if (!(data.get(i).getItemDesc().equalsIgnoreCase(data.get(i - 1).getItemDesc()))) {
					String word = data.get(i).getBasicCostNew();
					if (data.get(i).getBasicCostNew() != null) {
						if (word.contains(".")) {
							String result = word.replace(".", "");
							logger.info(result);
							String result1 = result.trim();
							logger.info(result1);
							String result2 = result1.replaceAll(".$", "");
							logger.info(result2);
							String result3 = result2.replaceAll(".$", "");
							logger.info(result3);
							PdfPCell cell1 = new PdfPCell();
							cell1.addElement(new Phrase(result3, PdfStylAttributes.NORMAL_FONT));
							cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
							table.addCell(cell1);
						}

					} else {
						PdfPCell cell1 = new PdfPCell();
						cell1.addElement(new Phrase("", PdfStylAttributes.NORMAL_FONT));
						cell1.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell1);
					}
				} else {
					PdfPCell cell1 = new PdfPCell();
					cell1.addElement(new Phrase("", PdfStylAttributes.NORMAL_FONT));
					cell1.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell1);
				}

			} else {
				String word = data.get(i).getBasicCostNew();
				if (data.get(i).getBasicCostNew() != null) {
					if (word.contains(".")) {
						String result = word.replace(".", "");
						logger.info(result);
						String result1 = result.trim();
						logger.info(result1);
						String result2 = result1.replaceAll(".$", "");
						logger.info(result2);
						String result3 = result2.replaceAll(".$", "");
						logger.info(result3);

						PdfPCell cell1 = new PdfPCell();
						cell1.addElement(new Phrase(result3, PdfStylAttributes.NORMAL_FONT));
						cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
						table.addCell(cell1);
					}
				} else {
					PdfPCell cell1 = new PdfPCell();
					cell1.addElement(new Phrase("", PdfStylAttributes.NORMAL_FONT));
					cell1.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell1);
				}

			}

			// discount

			if (i > 0) {

				if (!(data.get(i).getItemDesc().equalsIgnoreCase(data.get(i - 1).getItemDesc()))) {
					if (data.get(i).getDiscountPer() != null) {

						PdfPCell cell1 = new PdfPCell();
						cell1.addElement(new Phrase(data.get(i).getDiscountPer(), PdfStylAttributes.NORMAL_FONT));
						cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
						table.addCell(cell1);
					} else {
						PdfPCell cell1 = new PdfPCell();
						cell1.addElement(new Phrase("", PdfStylAttributes.NORMAL_FONT));
						cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
						table.addCell(cell1);
					}
				} else {

					PdfPCell cell1 = new PdfPCell();
					cell1.addElement(new Phrase("", PdfStylAttributes.NORMAL_FONT));
					cell1.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell1);
				}

			} else {
				if (data.get(i).getDiscountPer() != null) {
					PdfPCell cell1 = new PdfPCell();
					cell1.addElement(new Phrase(data.get(i).getDiscountPer(), PdfStylAttributes.NORMAL_FONT));
					cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(cell1);
				} else {
					PdfPCell cell1 = new PdfPCell();
					cell1.addElement(new Phrase("", PdfStylAttributes.NORMAL_FONT));
					cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(cell1);
				}
			}

			addToTable(table, data.get(i).getColNm());

			if ((data.get(i).getColNm()) != null && (data.get(i).getColNm()).equals("Vector Surge Protection")) {
				String TEXT = "\u2202\u019F";

				BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
				Font f = new Font(bf, 8);
				Paragraph p = new Paragraph(TEXT, f);
				addToTableParagraph(table, p);
			} else {
				addToTable(table, data.get(i).getColValCd());
			}

			if ((data.get(i).getColId()) == 0) {

				String word = data.get(i).getAddOnCostNew();
				if (data.get(i).getAddOnCostNew() != null) {
					if (word.contains(".")) {
						String result = word.replace(".", "");
						logger.info(result);
						String result1 = result.trim();
						logger.info(result1);
						String result2 = result1.replaceAll(".$", "");
						logger.info(result2);
						String result3 = result2.replaceAll(".$", "");
						logger.info(result3);
						addToTable(table, result3);

					}

				} else {
					addToTable(table, "");
				}

			} else {
				String word = data.get(i).getRhsCostNew();
				if(data.get(i).isAddonOverwrittenPriceFlag()){
				if (data.get(i).getRhsCostNew() != null) {
					if (word.contains(".")) {
						String result = word.replace(".", "");
						logger.info(result);
						String result1 = result.trim();
						logger.info(result1);
						String result2 = result1.replaceAll(".$", "");
						logger.info(result2);
						String result3 = result2.replaceAll(".$", "");
						logger.info(result3);
						addToTableredcolor(table, result3);

					}

				} else {
					addToTable(table, "");
				}
				}
				//no colour
				else{
					if (data.get(i).getRhsCostNew() != null) {
						if (word.contains(".")) {
							String result = word.replace(".", "");
							logger.info(result);
							String result1 = result.trim();
							logger.info(result1);
							String result2 = result1.replaceAll(".$", "");
							logger.info(result2);
							String result3 = result2.replaceAll(".$", "");
							logger.info(result3);
							addToTable(table, result3);

						}

					} else {
						addToTable(table, "");
					}
				}
			}//end 
			addToTable(table, data.get(i).getRhsComrComments());

			count++;
		}

	}

	/**
	 * Helper methods start here
	 * 
	 * @param quotationForm
	 * @throws IOException
	 **/
	public void addAddOnCostSheetBody(Document document, String title, ReportBean reportBean)
			throws DocumentException, IOException {

		Paragraph p = new Paragraph();

		Paragraph preface = new Paragraph();
		addEmptyLine(preface, 1);

		// String TEXT ="\u2202\u019F";
		//
		// BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H,
		// BaseFont.EMBEDDED);
		// Font f = new Font(bf, 12);
		// p = new Paragraph(TEXT, f);

		preface.add(new Paragraph("Quotation Number - " + reportBean.getQuotNum(), PdfStylAttributes.BLUE_FONT));
		preface.setAlignment(Element.ALIGN_LEFT);
		logger.info("Quotation Number - " + reportBean.getQuotNum());
		preface.add(new Paragraph("Quotation Status - " + reportBean.getQuotStatus(), PdfStylAttributes.BLUE_FONT));
		preface.setAlignment(Element.ALIGN_LEFT);
		logger.info("Quotation Status - " + reportBean.getQuotStatus());
		PdfPTable table = new PdfPTable(10);
		table.setWidthPercentage(100);
		table.setHeaderRows(0);

		PdfPCell cell = new PdfPCell();
		cell.addElement(new Phrase("Customer Name	 ", PdfStylAttributes.SMALL_BOLD));
		cell.setColspan(3);
		table.addCell(cell);

		String word = reportBean.getOppName();
		if (word.contains(reportBean.getOpportunitySeqNum())) {
			String result = word.replace(reportBean.getOpportunitySeqNum(), "");
			String result1 = result.trim();
			String result2 = result1.replaceAll(".$", "");
			PdfPCell cell1 = new PdfPCell();
			cell1.addElement(new Phrase(result2, PdfStylAttributes.SMALL_BOLD));
			cell1.setColspan(4);
			table.addCell(cell1);
		}

		table.addCell(new Phrase("Date  ", PdfStylAttributes.SMALL_BOLD));
		PdfPCell cell1a = new PdfPCell();
		cell1a.addElement(new Phrase(reportBean.getDate(), PdfStylAttributes.SMALL_BOLD));
		cell1a.setColspan(2);
		table.addCell(cell1a);
		// table.addCell(new Phrase(reportBean.getDate(),
		// PdfStylAttributes.SMALL_BOLD));
		// table.setColspan(3);

		PdfPCell cell2 = new PdfPCell();
		cell2.addElement(new Phrase("End User Name		 ", PdfStylAttributes.SMALL_BOLD));
		cell2.setColspan(3);
		table.addCell(cell2);

		PdfPCell cell3 = new PdfPCell();
		cell3.addElement(new Phrase(reportBean.getEndUserName(), PdfStylAttributes.SMALL_BOLD));
		cell3.setColspan(4);
		table.addCell(cell3);

		table.addCell(new Phrase("Revision No.	 ", PdfStylAttributes.SMALL_BOLD));

		PdfPCell cell1b = new PdfPCell();
		cell1b.addElement(new Phrase(reportBean.getRevNum(), PdfStylAttributes.SMALL_BOLD));
		cell1b.setColspan(2);
		table.addCell(cell1b);

		// type
		PdfPCell cell22 = new PdfPCell();
		cell22.addElement(new Phrase("Type	 ", PdfStylAttributes.SMALL_BOLD));
		cell22.setColspan(3);
		table.addCell(cell22);

		PdfPCell cell32 = new PdfPCell();
		cell32.addElement(new Phrase(reportBean.getType(), PdfStylAttributes.SMALL_BOLD));
		cell32.setColspan(4);
		table.addCell(cell32);

		// empty date
		table.addCell(new Phrase(" ", PdfStylAttributes.SMALL_BOLD));
		PdfPCell cell1c = new PdfPCell();
		cell1c.addElement(new Phrase("", PdfStylAttributes.SMALL_BOLD));
		cell1c.setColspan(2);
		table.addCell(cell1c);

		// frame

		PdfPCell cell20 = new PdfPCell();
		cell20.addElement(new Phrase("Frame	 ", PdfStylAttributes.SMALL_BOLD));
		cell20.setColspan(3);
		table.addCell(cell20);

		PdfPCell cell30 = new PdfPCell();
		cell30.addElement(new Phrase(reportBean.getFrameName(), PdfStylAttributes.SMALL_BOLD));
		cell30.setColspan(4);
		table.addCell(cell30);

		// empty date
		table.addCell(new Phrase(" ", PdfStylAttributes.SMALL_BOLD));

		PdfPCell cell1d = new PdfPCell();
		cell1d.addElement(new Phrase("", PdfStylAttributes.SMALL_BOLD));
		cell1d.setColspan(2);
		table.addCell(cell1d);
		// table.addCell(new Phrase(" ", PdfStylAttributes.SMALL_BOLD));

		// Turbine/ Generator MW
		PdfPCell cell23 = new PdfPCell();
		cell23.addElement(new Phrase("Turbine/ Generator MW 	 ", PdfStylAttributes.SMALL_BOLD));
		cell23.setColspan(3);
		table.addCell(cell23);

		PdfPCell cell37 = new PdfPCell();
		cell37.addElement(new Phrase(reportBean.getTurbGeneratorMw(), PdfStylAttributes.SMALL_BOLD));
		cell37.setColspan(4);
		table.addCell(cell37);

		// empty date
		table.addCell(new Phrase(" ", PdfStylAttributes.SMALL_BOLD));
		PdfPCell cell1e = new PdfPCell();
		cell1e.addElement(new Phrase("", PdfStylAttributes.SMALL_BOLD));
		cell1e.setColspan(2);
		table.addCell(cell1e);
		// table.addCell(new Phrase(" ", PdfStylAttributes.SMALL_BOLD));

		// Order date / LOI date
		PdfPCell cell25 = new PdfPCell();
		cell25.addElement(new Phrase("Order date / LOI date	", PdfStylAttributes.SMALL_BOLD));
		cell25.setColspan(3);
		table.addCell(cell25);

		PdfPCell cell35 = new PdfPCell();
		cell35.addElement(new Phrase("", PdfStylAttributes.SMALL_BOLD));
		cell35.setColspan(4);
		table.addCell(cell35);

		// empty date
		table.addCell(new Phrase(" ", PdfStylAttributes.SMALL_BOLD));
		PdfPCell cell1f = new PdfPCell();
		cell1f.addElement(new Phrase("", PdfStylAttributes.SMALL_BOLD));
		cell1f.setColspan(2);
		table.addCell(cell1f);
		// table.addCell(new Phrase(" ", PdfStylAttributes.SMALL_BOLD));

		// Delievery / Commissioing
		PdfPCell cell26 = new PdfPCell();
		cell26.addElement(new Phrase("Delievery / Commissioing	", PdfStylAttributes.SMALL_BOLD));
		cell26.setColspan(3);
		table.addCell(cell26);

		PdfPCell cell36 = new PdfPCell();
		cell36.addElement(new Phrase("", PdfStylAttributes.SMALL_BOLD));
		cell36.setColspan(4);
		table.addCell(cell36);

		// empty date
		table.addCell(new Phrase(" ", PdfStylAttributes.SMALL_BOLD));
		PdfPCell cell1g = new PdfPCell();
		cell1g.addElement(new Phrase("", PdfStylAttributes.SMALL_BOLD));
		cell1g.setColspan(2);
		table.addCell(cell1g);
		// table.addCell(new Phrase(" ", PdfStylAttributes.SMALL_BOLD));

		PdfPCell cell6 = new PdfPCell();
		cell6.addElement(new Phrase("Base Planning Object	 ", PdfStylAttributes.NORMAL_BOLD));
		cell6.setColspan(10);
		table.addCell(cell6);

		addAddOnCostSheetContent(document, table, reportBean);// 10
		// float[] colWidths = new float[] { 5f, 25f, 40f, 10f, 20f };
		float[] colWidths = new float[] { 5f, 13f, 12f, 10f, 10f, 10f, 10f, 10f, 10f, 10f };
		table.setWidths(colWidths);

		preface.add(table);
		document.add(preface);
		// document.add(p);
		document.newPage();
		document.newPage();

	}

	// Detailed report starts
	public void addCostSheetContentDet(Document document, PdfPTable table, QuotationForm quotationForm)
			throws DocumentException {

		Paragraph paragraph = new Paragraph();
		paragraph.setFont(PdfStylAttributes.NORMAL_FONT);
		createCostSheetTableDet(paragraph, table, quotationForm);
		document.add(paragraph);

	}

	private void createCostSheetTableDet(Paragraph paragraph, PdfPTable table, QuotationForm quotationForm)
			throws BadElementException {

		logger.info("createCostSheetTable Start");

		/*
		 * // List<AddOnComponent> addOnCompList = new
		 * ArrayList<AddOnComponent>(); // List<TurbineDetails> costSheetList =
		 * new ArrayList<TurbineDetails>(); // List<TurbineDetails> dboMechList
		 * = new ArrayList<TurbineDetails>();
		 * 
		 * F2FForm onelineBomExcel = new F2FForm(); onelineBomExcel =
		 * quotationForm.getOneLineBomExcel(); addOnCompList =
		 * onelineBomExcel.getAddOnsList(); costSheetList =
		 * onelineBomExcel.getCostSheetList(); dboMechList =
		 * onelineBomExcel.getDboMechList();
		 */

	}

	/**
	 * Helper methods start here
	 * 
	 * @param quotationForm
	 **/
	public void addCostSheetBodyDet(Document document, String title, QuotationForm quotationForm)
			throws DocumentException {
		try {
			SaveBasicDetails saveBasicDetails = new SaveBasicDetails();
			CustomerDetails customerDetails = new CustomerDetails();
			saveBasicDetails = quotationForm.getSaveBasicDetails();
			customerDetails = quotationForm.getCustomerDetailsForm();

			Paragraph preface = new Paragraph();
			addEmptyLine(preface, 1);

			preface.add(new Paragraph("Quotation Number : " + quotationForm.getSaveBasicDetails().getQuotNumber(),
					PdfStylAttributes.BLUE_FONT));
			preface.setAlignment(Element.ALIGN_LEFT);

			preface.add(new Paragraph("Quotation Status : " + quotationForm.getSaveBasicDetails().getStatusName(),
					PdfStylAttributes.BLUE_FONT));
			preface.setAlignment(Element.ALIGN_LEFT);

			preface.add(new Paragraph("Revision Number : " + quotationForm.getSaveBasicDetails().getRevNum(),
					PdfStylAttributes.BLUE_FONT));
			preface.setAlignment(Element.ALIGN_LEFT);

			addEmptyLine(preface, 1);
			Paragraph prefaceUser = new Paragraph();
			addEmptyLine(prefaceUser, 1);

			// displaying user Details
			prefaceUser = new Paragraph("User Information", PdfStylAttributes.NORMAL_BOLD);

			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100);
			table.setHeaderRows(0);

			// addHeaderInTable(HEADER_ARRAY, table);

			addToTableHeaderDet(table, "Employee Id	: ");
			addToTableValDet(table, saveBasicDetails.getCreatorEmpId());

			addToTableHeaderDet(table, "Department : ");
			addToTableValDet(table, saveBasicDetails.getCreatorDeptName());

			addToTableHeaderDet(table, "Name(Owner/Creater) : ");
			addToTableValDet(table, saveBasicDetails.getCreatedBy());

			addToTableHeaderDet(table, "Contact Number :	 ");
			addToTableValDet(table, saveBasicDetails.getCreatorPhoneNumber());

			addToTableHeaderDet(table, "Email :	 ");
			addToTableValDet(table, saveBasicDetails.getCreatorEmail());

			addToTableHeaderDet(table, "");
			addToTableValDet(table, "");

			addEmptyRows(table);

			// user details end

			// customer Details starts

			addToTableHeaderVal(table, "Customer Information");

			addEmptyRowsWithoutBorder(table);

			addToTableHeaderDet(table, "Oppurtunity Sequence Number	: ");
			addToTableValDet(table, saveBasicDetails.getOpportunitySeqNum());

			addToTableHeaderDet(table, "");
			addToTableValDet(table, "");

			addToTableHeaderVal(table, "Customer Details");

			addToTableHeaderDet(table, "Account Name	: ");
			addToTableValDet(table, quotationForm.getSaveBasicDetails().getCustName());

			addToTableHeaderDet(table, "Opportunity Name	: ");
			addToTableValDet(table, quotationForm.getSaveBasicDetails().getOppName());

			addToTableHeaderDet(table, "Customer Type	: ");
			addToTableValDet(table, quotationForm.getSaveBasicDetails().getCustType());

			addToTableHeaderDet(table, "Customer Status	: ");
			addToTableValDet(table, quotationForm.getSaveBasicDetails().getCustStatus());

			addToTableHeaderDet(table, "Contact Person Name	: ");
			addToTableValDet(table, quotationForm.getSaveBasicDetails().getCustContactPersonName());

			addToTableHeaderDet(table, "Email	: ");
			addToTableValDet(table, quotationForm.getSaveBasicDetails().getCustEmailId());

			addToTableHeaderDet(table, "Contact Number	: ");
			addToTableValDet(table, quotationForm.getSaveBasicDetails().getCustContactNumber());

			addToTableHeaderDet(table, "Address	: ");
			addToTableValDet(table, quotationForm.getSaveBasicDetails().getCustAddress());

			addToTableHeaderDet(table, "");
			addToTableValDet(table, "");

			addToTableHeaderVal(table, "End User Details");

			if (quotationForm.getSaveBasicDetails().getEndUserName() != null) {
				addToTableHeaderDet(table, "EndUser Name	: ");
				addToTableValDet(table, quotationForm.getSaveBasicDetails().getEndUserName());

				addToTableHeaderDet(table, "Address	: ");
				addToTableValDet(table, quotationForm.getSaveBasicDetails().getEndUserAddress());

			} else {
				addToTableHeaderDet(table, "Customer Name	: ");
				addToTableValDet(table, quotationForm.getSaveBasicDetails().getCustName());

				addToTableHeaderDet(table, "Customer Type	: ");
				addToTableValDet(table, quotationForm.getSaveBasicDetails().getCustType());

				addToTableHeaderDet(table, "Customer Status	: ");
				addToTableValDet(table, quotationForm.getSaveBasicDetails().getCustStatus());

				addToTableHeaderDet(table, "Contact Person Name	: ");
				addToTableValDet(table, quotationForm.getSaveBasicDetails().getCustContactPersonName());

				addToTableHeaderDet(table, "Email	: ");
				addToTableValDet(table, quotationForm.getSaveBasicDetails().getCustEmailId());

				addToTableHeaderDet(table, "Contact Number	: ");
				addToTableValDet(table, quotationForm.getSaveBasicDetails().getCustContactNumber());

				addToTableHeaderDet(table, "Address	: ");
				addToTableValDet(table, quotationForm.getSaveBasicDetails().getCustAddress());

				addToTableHeaderDet(table, "");
				addToTableValDet(table, "");

			}

			if (customerDetails.getEndUserName() != null) {

				PdfPCell cell3 = new PdfPCell();
				cell3.addElement(new Phrase("Consultant Details", PdfStylAttributes.NORMAL_BOLD));
				cell3.setBorder(Rectangle.NO_BORDER);
				cell3.setColspan(2);
				table.addCell(cell3);

				addToTableHeaderDet(table, "Consultant Name	: ");
				addToTableValDet(table, quotationForm.getSaveBasicDetails().getConsultantName());

				addToTableHeaderDet(table, " Consultant Address	: ");
				addToTableValDet(table, quotationForm.getSaveBasicDetails().getConsultantAddress());

			}
			addEmptyRows(table);

			// customer Details ends

			// customer Requirements starts
			addToTableHeaderVal(table, "Customer Requirements");

			addEmptyRowsWithoutBorder(table);

			addToTableHeaderDet(table, "Type Of Blade	: ");
			addToTableValDet(table, quotationForm.getSaveBasicDetails().getTypOfBlade());

			addToTableHeaderDet(table, "Turbine Type	: ");
			addToTableValDet(table, quotationForm.getSaveBasicDetails().getTypeOfTurbine());

			addToTableHeaderDet(table, "Bleed Type	: ");
			addToTableValDet(table, quotationForm.getSaveBasicDetails().getBleedType());

			addToTableHeaderDet(table, "Type Of Varient	: ");
			addToTableValDet(table, quotationForm.getSaveBasicDetails().getTypeOfVarientNm());

			addToTableHeaderDet(table, "Frame	: ");
			addToTableValDet(table, quotationForm.getSaveBasicDetails().getFrameName());

			addToTableHeaderDet(table, "Capacity	: ");
			addToTableValDet(table, Float.toString(quotationForm.getSaveBasicDetails().getCapacity()));

			addToTableHeaderDet(table, "Orientation Type	: ");
			addToTableValDet(table, quotationForm.getSaveBasicDetails().getOrientationType());

			addToTableHeaderDet(table, "Region : ");
			addToTableValDet(table, quotationForm.getSaveBasicDetails().getRegion());

			addEmptyRows(table);
			// customer Requirements ends

			// scope of supply Starts

			addToTableHeaderVal(table, "Scope of Supply ");

			addEmptyRowsWithoutBorder(table);

			for (ScopeOfSupply scopes : quotationForm.getScopeOfSupplyList()) {

				PdfPCell cell6 = new PdfPCell();
				cell6.addElement(new Phrase(scopes.getScopeName(), PdfStylAttributes.NORMAL_FONT));
				cell6.setColspan(4);
				cell6.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell6);
			}
			addEmptyRows(table);

			// scope of supply ends

			// genin Starts

			addToTableHeaderVal(table, "General Input ");

			addEmptyRowsWithoutBorder(table);

			for (DBOBean genin : quotationForm.getSavedList()) {

				if(genin.getGenType().equals("INPUT"))
				{
				PdfPCell cell6 = new PdfPCell();
				cell6.addElement(new Phrase(genin.getGenInNm() + "			:			" + genin.getCategoryValCode(),
						PdfStylAttributes.NORMAL_FONT));
				cell6.setColspan(4);
				cell6.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell6);
				}
				else{
					PdfPCell cell6 = new PdfPCell();
					cell6.addElement(new Phrase(genin.getGenInNm() + "			:			" + genin.getCategoryValName(),
							PdfStylAttributes.NORMAL_FONT));
					cell6.setColspan(4);
					cell6.setBorder(Rectangle.NO_BORDER);
					table.addCell(cell6);
				}
			}
			for (DBOBean geninspl : quotationForm.getSaveEleFilterList()) {

				if(geninspl.getGenType().equals("INPUT"))
				{
				PdfPCell cell6 = new PdfPCell();
				cell6.addElement(new Phrase(geninspl.getItemName() + "			:			" + geninspl.getColValCd(),PdfStylAttributes.NORMAL_FONT));
				cell6.setColspan(4);
				cell6.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell6);
				}
				else{
					
					if((geninspl.getFilterCd().equals("DT_GRID")))
					{
					PdfPCell cell6 = new PdfPCell();
					cell6.addElement(new Phrase(geninspl.getItemName() + "			:			" + geninspl.getColValNm()+"		"+geninspl.getSubColValNm(),PdfStylAttributes.NORMAL_FONT));
					cell6.setColspan(4);
					cell6.setBorder(Rectangle.NO_BORDER);
					table.addCell(cell6);
					}
					else{
						PdfPCell cell6 = new PdfPCell();
						cell6.addElement(new Phrase(geninspl.getItemName() + "			:			" + geninspl.getColValNm(),PdfStylAttributes.NORMAL_FONT));
						cell6.setColspan(4);
						cell6.setBorder(Rectangle.NO_BORDER);
						table.addCell(cell6);
					}
				}
			}
			addEmptyRows(table);

			// genin ends

//			// turbine details SAP starts
//
//			addToTableHeaderVal(table, "Turbine Details SAP ");
//
//			addEmptyRowsWithoutBorder(table);
//
//			addToTableHeaderDet(table, "Varient Code :	 ");
//			addToTableValDet(table, quotationForm.getLatestCProjectData().getVariantCode());
//
//			addToTableHeaderDet(table, "C-Number :");
//			addToTableValDet(table, quotationForm.getLatestCProjectData().getcNum());
//
//			addToTableHeaderDet(table, "Turbine Material Cost :");
//			addToTableValDetStr(table, quotationForm.getLatestCProjectData().getTurbineMaterialCost());
//
//			addToTableHeaderDet(table, "Shop Conversion Cost :");
//			addToTableValDetStr(table, quotationForm.getLatestCProjectData().getShopCoverCost());
//
//			addToTableHeaderDet(table, "Sub-Contracting Cost :");
//			addToTableValDetStr(table, quotationForm.getLatestCProjectData().getSubContrCost());
//
//			addToTableHeaderDet(table, "Total Cost :");
//			addToTableValDetStr(table, quotationForm.getLatestCProjectData().getTotal());
//
//			addEmptyRowsWithoutBorder(table);
//
//			int count = 1;
//			for (int i = 0; i < quotationForm.getSelectedQuestionAnswerSet().size(); i++) {
//
//				PdfPCell cell8 = new PdfPCell();
//				cell8.addElement(new Phrase(
//						"Q" + (count++) + " " + quotationForm.getSelectedQuestionAnswerSet().get(i).getQuestionDesc(),
//						PdfStylAttributes.NORMAL_FONT));
//				cell8.setBorder(Rectangle.NO_BORDER);
//				table.addCell(cell8);
//
//				PdfPCell cell9 = new PdfPCell();
//				cell9.addElement(new Phrase(quotationForm.getSelectedQuestionAnswerSet().get(i).getAnswerDesc(),
//						PdfStylAttributes.NORMAL_FONT));
//				cell9.setBorder(Rectangle.NO_BORDER);
//				table.addCell(cell9);
//			}
//			addEmptyRows(table);
//
//			// turbine details SAP ends
//
//			// turbine details Excel Starts
//
//			addToTableHeaderVal(table, "Turbine Details Excel ");
//
//			addEmptyRowsWithoutBorder(table);
//
//			addToTableHeaderDet(table, "Turbine Material Cost : ");
//			addToTableValDetStr(table, quotationForm.getF2fExcel().getTurbineMaterialCost());
//
//			addToTableHeaderDet(table, "SubContracting Cost :");
//			addToTableValDetStr(table, quotationForm.getF2fExcel().getSubContrCost());
//
//			addToTableHeaderDet(table, "Shop Conversion Cost :");
//			addToTableValDetStr(table, quotationForm.getF2fExcel().getShopCoverCost());
//
//			addToTableHeaderDet(table, "Total Cost :");
//			addToTableValDetStr(table, quotationForm.getF2fExcel().getTotalF2FCost());
//
//			if (quotationForm.getF2fExcel().isOverwrittenPriceFlg()) {
//				addToTableHeaderDet(table, "Overwritten Cost :");
//				addToTableValDetStr(table, quotationForm.getF2fExcel().getOverwrittenPrice());
//			} else {
//
//				addToTableHeaderDet(table, "");
//				addToTableValDet(table, "");
//			}
//			addToTableHeaderDet(table, "");
//			addToTableValDet(table, "");
//			addEmptyRows(table);

			// turbine details Excel Ends

			// // f2f details starts
			//
			// if (!quotationForm.getF2fGeneralList().isEmpty()) {
			// List<DBOBean> f2fBean = new ArrayList<DBOBean>();
			// f2fBean = quotationForm.getF2fGeneralList();
			// Map<String, DBOBean> f2fMap1 = new HashedMap();
			// Map<String, DBOBean> f2fMap2 = new HashedMap();
			//
			// for (DBOBean f2f1 : f2fBean) {
			// f2fMap1.put(f2f1.getItemName(), f2f1);
			// }
			//
			// for (DBOBean f2f2 : f2fBean) {
			// f2fMap2.put(f2f2.getSubItemName(), f2f2);
			// }
			//
			// addToTableHeaderVal(table, "Flange To Flange Items details");
			// addEmptyRowsWithoutBorder(table);
			//
			// addToTableValueBoldSmall(table, "Total F2F Cost :");
			// addToTableValueBoldSmall(table, f2fBean.get(0).getTotalPrice());
			//
			// if (f2fBean.get(0).isOverwrittenPriceFlag()) {
			// addToTableValueBoldSmall(table, "Overwritten Cost :");
			// addToTableValueBoldSmall(table,
			// f2fBean.get(0).getOverwrittenPrice());
			// } else {
			//
			// addToTableHeaderDet(table, "");
			// addToTableValDet(table, "");
			// }
			// addEmptyRowsWithoutBorder(table);
			//
			// addToTableValueBold(table, "F2F Item");
			// addToTableValueBold(table, "Item Cost");
			// addToTableValueBold(table, "Basic Cost");
			// addToTableValueBold(table, "AddOn Cost");
			//
			// for (DBOBean f2fBeans1 : f2fMap1.values()) {
			//
			// addToTableValueBold(table, f2fBeans1.getItemName());
			// addToTableValueBig(table, f2fBeans1.getItemCost());
			// addToTableValueBig(table, f2fBeans1.getBasicCost());
			// addToTableValueBig(table, f2fBeans1.getAddOnCost());
			// for (DBOBean f2fitem1 : quotationForm.getF2fGeneralList()) {
			//
			// if
			// (f2fBeans1.getItemName().equalsIgnoreCase(f2fitem1.getItemName())
			// && f2fitem1.getSubItemName() == null) {
			// if (f2fitem1.isDefaultVal()) {
			// addToTableValue(table, f2fitem1.getColNm());
			// addToTableValueBold(table, ":");
			// addToTableValue(table, f2fitem1.getColValCd());
			// if (f2fitem1.getRhsCost() != 0) {
			// addToTableValDetStr(table, f2fitem1.getRhsCost());
			//
			// } else {
			// addToTableValue(table, "");
			// }
			//
			// }
			// }
			//
			// }
			//
			// }
			//
			// for (DBOBean f2fBeans2 : f2fMap2.values()) {
			// addToTableValueBold(table, f2fBeans2.getSubItemName());
			// addToTableValue(table, "");
			// addToTableValue(table, "");
			// addToTableValue(table, "");
			//
			// for (DBOBean f2fsub1 : quotationForm.getF2fGeneralList()) {
			//
			// if (f2fsub1.getSubItemName() != null
			// &&
			// f2fsub1.getSubItemName().equalsIgnoreCase(f2fBeans2.getSubItemName()))
			// {
			// if (f2fsub1.isDefaultVal()) {
			// addToTableValue(table, f2fsub1.getColNm());
			// addToTableValueBold(table, ":");
			// addToTableValue(table, f2fsub1.getColValCd());
			// if (f2fsub1.getRhsCost() != 0) {
			// addToTableValDetStr(table, f2fsub1.getRhsCost());
			// } else {
			// addToTableValue(table, "");
			// }
			//
			// }
			//
			// }
			//
			// }
			// }
			//
			// addEmptyRows(table);
			//
			// }
			// // f2f details ends
			//
			// // mechanical details starts
			//
			// if (!quotationForm.getMechGeneralList().isEmpty()) {
			// List<DBOBean> mechBean = new ArrayList<DBOBean>();
			// mechBean = quotationForm.getMechGeneralList();
			// Map<String, DBOBean> mechMap1 = new HashedMap();
			// Map<String, DBOBean> mechMap2 = new HashedMap();
			//
			// for (DBOBean mech1 : mechBean) {
			// mechMap1.put(mech1.getItemName(), mech1);
			// }
			//
			// for (DBOBean mech2 : mechBean) {
			// mechMap2.put(mech2.getSubItemName(), mech2);
			// }
			//
			// addToTableHeaderVal(table, "Mechanical Items details");
			// addEmptyRowsWithoutBorder(table);
			//
			// addToTableValueBoldSmall(table, "Total Mechanical Cost :");
			// addToTableValueBoldSmall(table, mechBean.get(0).getTotalPrice());
			//
			// if (mechBean.get(0).isOverwrittenPriceFlag()) {
			// addToTableValueBoldSmall(table, "Overwritten Cost :");
			// addToTableValueBoldSmall(table,
			// mechBean.get(0).getOverwrittenPrice());
			// } else {
			//
			// addToTableHeaderDet(table, "");
			// addToTableValDet(table, "");
			// }
			// addEmptyRowsWithoutBorder(table);
			//
			// addToTableValueBold(table, "Mechanical Item");
			// addToTableValueBold(table, "Item Cost");
			// addToTableValueBold(table, "Basic Cost");
			// addToTableValueBold(table, "AddOn Cost");
			//
			// for (DBOBean mechBeans1 : mechMap1.values()) {
			//
			// addToTableValueBold(table, mechBeans1.getItemName());
			// addToTableValueBig(table, mechBeans1.getItemCost());
			// addToTableValueBig(table, mechBeans1.getBasicCost());
			// addToTableValueBig(table, mechBeans1.getAddOnCost());
			// for (DBOBean mechitem1 : quotationForm.getMechGeneralList()) {
			// if
			// (mechBeans1.getItemName().equalsIgnoreCase(mechitem1.getItemName())
			// && mechitem1.getSubItemName() == null) {
			// if (mechitem1.isDefaultVal()) {
			// addToTableValue(table, mechitem1.getColNm());
			// addToTableValueBold(table, ":");
			// addToTableValue(table, mechitem1.getColValCd());
			// if (mechitem1.getRhsCost() != 0) {
			// addToTableValDetStr(table, mechitem1.getRhsCost());
			// } else {
			// addToTableValue(table, "");
			// }
			// }
			// }
			//
			// }
			//
			// }
			//
			// for (DBOBean mechBeans2 : mechMap2.values()) {
			// addToTableValueBold(table, mechBeans2.getSubItemName());
			// addToTableValue(table, "");
			// addToTableValue(table, "");
			// addToTableValue(table, "");
			//
			// for (DBOBean mechsub1 : quotationForm.getMechGeneralList()) {
			//
			// if (mechsub1.getSubItemName() != null
			// &&
			// mechsub1.getSubItemName().equalsIgnoreCase(mechBeans2.getSubItemName()))
			// {
			// if (mechsub1.isDefaultVal()) {
			// addToTableValue(table, mechsub1.getColNm());
			// addToTableValueBold(table, ":");
			// addToTableValue(table, mechsub1.getColValCd());
			// if (mechsub1.getRhsCost() != 0) {
			// addToTableValDetStr(table, mechsub1.getRhsCost());
			// } else {
			// addToTableValue(table, "");
			// }
			// // addToTableValue(table,
			// // elesub1.getRhsColTechcomments());
			// }
			//
			// }
			//
			// }
			// }
			//
			// addEmptyRows(table);
			//
			// }
			// // mechanical details ends
			//
			// // mechanical Aux details starts
			//
			// if (!quotationForm.getMechAuxGeneralList().isEmpty()) {
			// List<DBOBean> mechAuxBean = new ArrayList<DBOBean>();
			// mechAuxBean = quotationForm.getMechAuxGeneralList();
			// Map<String, DBOBean> mechAuxMap1 = new HashedMap();
			// Map<String, DBOBean> mechAuxMap2 = new HashedMap();
			//
			// for (DBOBean mechAux1 : mechAuxBean) {
			// mechAuxMap1.put(mechAux1.getItemName(), mechAux1);
			// }
			//
			// for (DBOBean mechAux2 : mechAuxBean) {
			// mechAuxMap2.put(mechAux2.getSubItemName(), mechAux2);
			// }
			//
			// addToTableHeaderVal(table, "Mechanical Aux Items details");
			// addEmptyRowsWithoutBorder(table);
			//
			// addToTableValueBoldSmall(table, "Total Mechanical Cost :");
			// addToTableValueBoldSmall(table,
			// mechAuxBean.get(0).getTotalPrice());
			//
			// if (mechAuxBean.get(0).isOverwrittenPriceFlag()) {
			// addToTableValueBoldSmall(table, "Overwritten Cost :");
			// addToTableValueBoldSmall(table,
			// mechAuxBean.get(0).getOverwrittenPrice());
			// } else {
			//
			// addToTableHeaderDet(table, "");
			// addToTableValDet(table, "");
			// }
			// addEmptyRowsWithoutBorder(table);
			//
			// addToTableValueBold(table, "Mechanical Aux Item");
			// addToTableValueBold(table, "Item Cost");
			// addToTableValueBold(table, "Basic Cost");
			// addToTableValueBold(table, "AddOn Cost");
			//
			// for (DBOBean mechAuxBeans1 : mechAuxMap1.values()) {
			//
			// addToTableValueBold(table, mechAuxBeans1.getItemName());
			// addToTableValueBig(table, mechAuxBeans1.getItemCost());
			// addToTableValueBig(table, mechAuxBeans1.getBasicCost());
			// addToTableValueBig(table, mechAuxBeans1.getAddOnCost());
			// for (DBOBean mechAuxitem1 :
			// quotationForm.getMechAuxGeneralList()) {
			// if
			// (mechAuxBeans1.getItemName().equalsIgnoreCase(mechAuxitem1.getItemName())
			// && mechAuxitem1.getSubItemName() == null) {
			// if (mechAuxitem1.isDefaultVal()) {
			// addToTableValue(table, mechAuxitem1.getColNm());
			// addToTableValueBold(table, ":");
			// addToTableValue(table, mechAuxitem1.getColValCd());
			// if (mechAuxitem1.getRhsCost() != 0) {
			// addToTableValDetStr(table, mechAuxitem1.getRhsCost());
			// } else {
			// addToTableValue(table, "");
			// }
			// }
			//
			// }
			//
			// }
			//
			// }
			//
			// for (DBOBean mechAuxBeans2 : mechAuxMap2.values()) {
			// addToTableValueBold(table, mechAuxBeans2.getSubItemName());
			// addToTableValue(table, "");
			// addToTableValue(table, "");
			// addToTableValue(table, "");
			//
			// for (DBOBean mechAuxsub1 : quotationForm.getMechAuxGeneralList())
			// {
			//
			// if (mechAuxsub1.getSubItemName() != null
			// &&
			// mechAuxsub1.getSubItemName().equalsIgnoreCase(mechAuxBeans2.getSubItemName()))
			// {
			// if (mechAuxsub1.isDefaultVal()) {
			// addToTableValue(table, mechAuxsub1.getColNm());
			// addToTableValueBold(table, ":");
			// addToTableValue(table, mechAuxsub1.getColValCd());
			// if (mechAuxsub1.getRhsCost() != 0) {
			// addToTableValDetStr(table, mechAuxsub1.getRhsCost());
			// } else {
			// addToTableValue(table, "");
			// }
			// // addToTableValue(table,
			// // elesub1.getRhsColTechcomments());
			// }
			//
			// }
			//
			// }
			// }
			//
			// addEmptyRows(table);
			//
			// }
			// // mechanical details ends
			//
			// // mech extended scope starts
			//
			// if (!quotationForm.getMechExtScpList().isEmpty()) {
			// List<DBOBean> mechExtBean = new ArrayList<DBOBean>();
			// mechExtBean = quotationForm.getMechExtScpList();
			// Map<String, DBOBean> mechExtMap1 = new HashedMap();
			// Map<String, DBOBean> mechExtMap2 = new HashedMap();
			//
			// for (DBOBean mechExt1 : mechExtBean) {
			// mechExtMap1.put(mechExt1.getItemName(), mechExt1);
			// }
			//
			// addToTableHeaderVal(table, "Mechanical Extended Scope Items
			// details");
			// addEmptyRowsWithoutBorder(table);
			//
			// addToTableValueBoldSmall(table, "Total Mechanical Extended Scope
			// Cost :");
			// addToTableValueBoldSmall(table,
			// mechExtBean.get(0).getTotalExtCost());
			//
			// addEmptyRowsWithoutBorder(table);
			//
			// addToTableValueBold(table, "Mechanical Extended Scope Item");
			// addToTableValueBold(table, "");
			// addToTableValueBold(table, "Basic Cost");
			// addToTableValueBold(table, "");
			//
			// for (DBOBean mechExtBeans1 : mechExtMap1.values()) {
			//
			// addToTableValueBold(table, mechExtBeans1.getItemName());
			// addToTableValue(table, "");
			// addToTableValDetStr(table, mechExtBeans1.getExtScopeCost());
			// addToTableValue(table, "");
			//
			// }
			// addToTableValueBold(table, "Mechanical Extended Scope Item");
			// addToTableValueBold(table, "AddOn Cost");
			// addToTableValueBold(table, "Remarks");
			// addToTableValueBold(table, "");
			//
			// for (DBOBean mechExtsub1 : quotationForm.getMechExtScpList()) {
			// if (mechExtsub1.isDefaultVal()) {
			// addToTableValue(table, mechExtsub1.getColNm() + " - " +
			// mechExtsub1.getColValCd());
			// addToTableValDetStr(table, mechExtsub1.getExtScopeRhsCost());
			// addToTableValue(table, mechExtsub1.getRhsColTechcomments());
			// addToTableValue(table, "");
			// }
			//
			// }
			//
			// addEmptyRows(table);
			//
			// }
			// // mech extended scope ends
			//
			// // // Electrical details starts
			//
			// if (!quotationForm.getEleGeneralList().isEmpty()) {
			// List<DBOBean> eleBean = new ArrayList<DBOBean>();
			// eleBean = quotationForm.getEleGeneralList();
			// Map<String, DBOBean> eleMap1 = new HashedMap();
			// Map<String, DBOBean> eleMap2 = new HashedMap();
			// Map<String, DBOBean> eleMap3 = new HashedMap();
			//
			// for (DBOBean ele1 : eleBean) {
			// eleMap1.put(ele1.getItemName(), ele1);
			// }
			//
			// for (DBOBean ele2 : eleBean) {
			// eleMap2.put(ele2.getDesItemName(), ele2);
			// }
			//
			// for (DBOBean ele3 : eleBean) {
			// eleMap3.put(ele3.getDesSubItemName(), ele3);
			// }
			// addToTableHeaderVal(table, "Electrical Items details");
			// addEmptyRowsWithoutBorder(table);
			//
			// addToTableValueBoldSmall(table, "Total Electrical Cost :");
			// addToTableValueBoldSmall(table, eleBean.get(0).getTotalPrice());
			//
			// if (eleBean.get(0).isOverwrittenPriceFlag()) {
			// addToTableValueBoldSmall(table, "Overwritten Cost :");
			// addToTableValueBoldSmall(table,
			// eleBean.get(0).getOverwrittenPrice());
			// } else {
			//
			// addToTableHeaderDet(table, "");
			// addToTableValDet(table, "");
			// }
			//
			// if (eleBean.get(0).isOverwrittenPriceFlag()) {
			// addToTableValueBoldSmall(table, "Overwritten Cost :");
			// addToTableValueBoldSmall(table,
			// eleBean.get(0).getOverwrittenPrice());
			// } else {
			//
			// addToTableHeaderDet(table, "");
			// addToTableValDet(table, "");
			// }
			//
			// addEmptyRowsWithoutBorder(table);
			//
			// addToTableValueBold(table, "Electrical Item");
			// addToTableValueBold(table, "Item Cost");
			// addToTableValueBold(table, "Basic Cost");
			// addToTableValueBold(table, "AddOn Cost");
			//
			// for (DBOBean eleBeans1 : eleMap1.values()) {
			//
			// addToTableValueBold(table, eleBeans1.getItemName());
			// addToTableValueBig(table, eleBeans1.getItemCost());
			// addToTableValueBig(table, eleBeans1.getBasicCost());
			// if (eleBeans1.getItemApproxCostFlag() == 2) {
			// addToTableValueBoldRedNew(table, eleBeans1.getItemErrMessage());
			//
			// } else if (eleBeans1.getItemApproxCostFlag() != 2)
			// addToTableValueBigRed(table, eleBeans1.getAddOnCost());
			//
			// for (DBOBean eleBeans2 : eleMap2.values()) {
			//
			// int counter = 0;
			// for (DBOBean elesub2 : quotationForm.getEleGeneralList()) {
			//
			// if (elesub2.getDesItemName() != null
			// &&
			// elesub2.getDesItemName().equalsIgnoreCase(eleBeans2.getDesItemName())
			// &&
			// eleBeans1.getItemName().equalsIgnoreCase(elesub2.getItemName()))
			// {
			// counter = 1;
			// }
			// }
			// if (counter == 1) {
			//
			// addToTableValueBold(table, eleBeans2.getDesItemName());
			// addToTableValue(table, "");
			// addToTableValue(table, "");
			// addToTableValue(table, "");
			// }
			// System.out.println(eleMap3);
			// for (DBOBean elesub1 : quotationForm.getEleGeneralList()) {
			//
			// if (elesub1.getDesItemName() != null && eleBeans2.getDesItemId()
			// == elesub1.getDesItemId()
			// &&
			// eleBeans1.getItemName().equalsIgnoreCase(elesub1.getItemName()))
			// {
			//
			// addToTableValue(table, elesub1.getDesColOrderId() + " " +
			// elesub1.getColNm());
			// addToTableValueBold(table, ":");
			// addToTableValue(table, elesub1.getColValCd());
			// if (elesub1.getRhsCost() != 0) {
			// if (elesub1.getApproxCostFlag() == 1) {
			// addToTableValDetStrRed(table, elesub1.getRhsCost());
			// } else {
			//
			// addToTableValDetStr(table, elesub1.getRhsCost());
			//
			// }
			//
			// } else {
			// addToTableValue(table, "");
			// }
			//
			// }
			//
			// }
			//
			// for (DBOBean eleBeans3 : eleMap3.values()) {
			//
			// if (eleBeans2.getDesItemId() == eleBeans3.getDesItemId()
			// && eleBeans3.getItemId() == eleBeans1.getItemId()) {
			// if (eleBeans3.getDesSubItemName() == null ||
			// eleBeans3.getDesSubItemName() == "") {
			// addToTableValueBold(table, "");
			// addToTableValue(table, "");
			// addToTableValue(table, "");
			// addToTableValue(table, "");
			// for (DBOBean elesub1 : quotationForm.getEleGeneralList()) {
			//
			// if (elesub1.getDesItemName() != null
			// && eleBeans3.getDesSubItemId() == elesub1.getDesSubItemId()
			// && eleBeans2.getDesItemId() == elesub1.getDesItemId()
			// &&
			// eleBeans1.getItemName().equalsIgnoreCase(elesub1.getItemName()))
			// {
			//
			// addToTableValue(table,
			// elesub1.getDesColOrderId() + " " + elesub1.getColNm());
			// addToTableValueBold(table, ":");
			// addToTableValue(table, elesub1.getColValCd());
			// if (elesub1.getRhsCost() != 0) {
			// if (elesub1.getApproxCostFlag() == 0) {
			// addToTableValDetStrRed(table, elesub1.getRhsCost());
			// } else {
			//
			// addToTableValDetStr(table, elesub1.getRhsCost());
			//
			// }
			//
			// } else {
			// addToTableValue(table, "");
			// }
			//
			// }
			//
			// }
			// } else {
			// addToTableValueBold(table,
			// eleBeans3.getDesSubItemOrderId() + " " +
			// eleBeans3.getDesSubItemName());
			// addToTableValue(table, "");
			// addToTableValue(table, "");
			// addToTableValue(table, "");
			// for (DBOBean elesub1 : quotationForm.getEleGeneralList()) {
			//
			// if (elesub1.getDesItemName() != null
			// && eleBeans3.getDesSubItemId() == elesub1.getDesSubItemId()
			// && eleBeans2.getDesItemId() == elesub1.getDesItemId()
			// &&
			// eleBeans1.getItemName().equalsIgnoreCase(elesub1.getItemName()))
			// {
			//
			// addToTableValue(table,
			// elesub1.getDesColOrderId() + " " + elesub1.getColNm());
			// addToTableValueBold(table, ":");
			// addToTableValue(table, elesub1.getColValCd());
			// if (elesub1.getRhsCost() != 0) {
			// if (elesub1.getApproxCostFlag() == 1) {
			// addToTableValDetStrRed(table, elesub1.getRhsCost());
			// } else {
			//
			// addToTableValDetStr(table, elesub1.getRhsCost());
			//
			// }
			//
			// } else {
			// addToTableValue(table, "");
			// }
			//
			// }
			//
			// }
			// }
			//
			// }
			// }
			//
			// }
			//
			// }
			// addEmptyRows(table);
			//
			// }
			// // // electrical details ends
			// //
			// //
			//
			// // electrical others details starts
			//
			// if (!quotationForm.getEleOtherList().isEmpty()) {
			// List<DBOBean> eleOthBean = new ArrayList<DBOBean>();
			// eleOthBean = quotationForm.getEleOtherList();
			// Map<String, DBOBean> eleOthMap1 = new HashedMap();
			//
			// for (DBOBean eleOth1 : eleOthBean) {
			// eleOthMap1.put(eleOth1.getItemName(), eleOth1);
			// }
			//
			// addToTableHeaderVal(table, "Electrical Others Items details");
			// addEmptyRowsWithoutBorder(table);
			//
			// addToTableValueBoldSmall(table, "Total Electrical Others Items
			// Cost :");
			// addToTableValueBoldSmall(table,
			// eleOthBean.get(0).getTotalPrice());
			//
			// addEmptyRowsWithoutBorder(table);
			//
			// addToTableValueBold(table, "Electrical Other Item");
			// addToTableValueBold(table, "");
			// addToTableValueBold(table, "Basic Cost");
			// addToTableValueBold(table, "");
			//
			// for (DBOBean eleOthBeans1 : eleOthMap1.values()) {
			//
			// addToTableValueBold(table, eleOthBeans1.getItemName());
			// addToTableValue(table, "");
			// addToTableValDetStr(table, eleOthBeans1.getBasicCost());
			// addToTableValue(table, "");
			//
			// }
			// addToTableValueBold(table, "Electrical Other Item");
			// addToTableValueBold(table, "AddOn Cost");
			// addToTableValueBold(table, "Remarks");
			// addToTableValueBold(table, "");
			//
			// for (DBOBean eleOthsub1 : quotationForm.getEleOtherList()) {
			//
			// addToTableValue(table, eleOthsub1.getColNm() + " - " +
			// eleOthsub1.getColValCd());
			// addToTableValDetStr(table, eleOthsub1.getRhsCost());
			// addToTableValue(table, eleOthsub1.getRhsColTechcomments());
			// addToTableValue(table, "");
			//
			// }
			//
			// addEmptyRows(table);
			//
			// }
			//
			// // electrical others details ends
			////
			//// // electrical sub item others details starts
			////
			//// if (!quotationForm.getEleSubList().isEmpty()) {
			//// List<DBOBean> eleSubBean = new ArrayList<DBOBean>();
			//// eleSubBean = quotationForm.getEleSubList();
			//// Map<String, DBOBean> eleSubMap1 = new HashedMap();
			//// Map<String, DBOBean> eleSubMap2 = new HashedMap();
			////
			//// for (DBOBean eleSub1 : eleSubBean) {
			//// eleSubMap1.put(eleSub1.getItemName(), eleSub1);
			//// }
			////
			//// for (DBOBean eleSub2 : eleSubBean) {
			//// eleSubMap2.put(eleSub2.getSubItemName(), eleSub2);
			//// }
			////
			//// addToTableHeaderVal(table, "Electrical SubItem details");
			//// addEmptyRowsWithoutBorder(table);
			////
			//// addToTableValueBoldSmall(table, "Total ElectricalSub Item Cost
			// :");
			//// addToTableValueBoldSmall(table,
			// eleSubBean.get(0).getTotalPrice());
			////
			//// if (eleSubBean.get(0).isOverwrittenPriceFlag()) {
			//// addToTableValueBoldSmall(table, "Overwritten Cost :");
			//// addToTableValueBoldSmall(table,
			// eleSubBean.get(0).getOverwrittenPrice());
			//// } else {
			////
			//// addToTableHeaderDet(table, "");
			//// addToTableValDet(table, "");
			//// }
			//// addEmptyRowsWithoutBorder(table);
			////
			//// addToTableValueBold(table, "ElectricalSub Item");
			//// addToTableValueBold(table, "Item Cost");
			//// addToTableValueBold(table, "Basic Cost");
			//// addToTableValueBold(table, "AddOn Cost");
			////
			//// for (DBOBean eleSubBeans1 : eleSubMap1.values()) {
			////
			//// addToTableValueBold(table, eleSubBeans1.getItemName());
			//// addToTableValueBig(table, eleSubBeans1.getItemCost());
			//// addToTableValueBig(table, eleSubBeans1.getBasicCost());
			//// addToTableValueBig(table, eleSubBeans1.getAddOnCost());
			//// for (DBOBean eleSubitem1 : quotationForm.getEleSubList()) {
			//// if
			// (eleSubBeans1.getItemName().equalsIgnoreCase(eleSubitem1.getItemName())
			//// && eleSubitem1.getSubItemName() == null) {
			////
			//// addToTableValue(table, eleSubitem1.getColNm());
			//// addToTableValueBold(table, ":");
			//// addToTableValue(table, eleSubitem1.getColValCd());
			//// if (eleSubitem1.getRhsCost() != 0) {
			//// addToTableValDetStr(table, eleSubitem1.getRhsCost());
			//// } else {
			//// addToTableValue(table, "");
			//// }
			////
			//// }
			////
			//// }
			////
			//// }
			////
			//// for (DBOBean eleSubBeans2 : eleSubMap2.values()) {
			//// addToTableValueBold(table, eleSubBeans2.getSubItemName());
			//// addToTableValue(table, "");
			//// addToTableValue(table, "");
			//// addToTableValue(table, "");
			////
			//// for (DBOBean elesub1 : quotationForm.getEleSubList()) {
			////
			//// if (elesub1.getSubItemName() != null
			//// &&
			// elesub1.getSubItemName().equalsIgnoreCase(eleSubBeans2.getSubItemName()))
			// {
			////
			//// addToTableValue(table, elesub1.getColNm());
			//// addToTableValueBold(table, ":");
			//// addToTableValue(table, elesub1.getColValCd());
			//// if (elesub1.getRhsCost() != 0) {
			//// addToTableValDetStr(table, elesub1.getRhsCost());
			//// } else {
			//// addToTableValue(table, "");
			//// }
			//// // addToTableValue(table,
			//// // elesub1.getRhsColTechcomments());
			////
			//// }
			////
			//// }
			//// }
			////
			//// addEmptyRows(table);
			////
			//// }
			//// // electrical sub details ends
			////
			//// // extended scope start
			////
			//// if (!quotationForm.getEleExtScopeList().isEmpty()) {
			//// List<DBOBean> eleExtBean = new ArrayList<DBOBean>();
			//// eleExtBean = quotationForm.getEleExtScopeList();
			//// Map<String, DBOBean> eleExtMap1 = new HashedMap();
			////
			//// for (DBOBean eleExt1 : eleExtBean) {
			//// eleExtMap1.put(eleExt1.getItemName(), eleExt1);
			//// }
			////
			//// addToTableHeaderVal(table, "Electrical ExtendedScope Items
			// details");
			//// addEmptyRowsWithoutBorder(table);
			////
			//// addToTableValueBoldSmall(table, "Electrical ExtendedScope Cost
			// :");
			//// addToTableValueBoldSmall(table,
			// eleExtBean.get(0).getTotalPrice());
			////
			//// if (eleExtBean.get(0).isOverwrittenPriceFlag()) {
			//// addToTableValueBoldSmall(table, "Overwritten Cost :");
			//// addToTableValueBoldSmall(table,
			// eleExtBean.get(0).getOverwrittenPrice());
			//// } else {
			////
			//// addToTableHeaderDet(table, "");
			//// addToTableValDet(table, "");
			//// }
			//// addEmptyRowsWithoutBorder(table);
			////
			//// addToTableValueBold(table, "ExtendedScope Item");
			//// addToTableValueBold(table, "Item Cost");
			//// addToTableValueBold(table, "Basic Cost");
			//// addToTableValueBold(table, "AddOn Cost");
			////
			//// for (DBOBean eleExtBeans1 : eleExtMap1.values()) {
			////
			//// addToTableValueBold(table, eleExtBeans1.getItemName());
			//// addToTableValueBig(table, eleExtBeans1.getItemCost());
			//// addToTableValueBig(table, eleExtBeans1.getBasicCost());
			//// addToTableValueBig(table, eleExtBeans1.getAddOnCost());
			//// for (DBOBean eleExtitem1 : quotationForm.getEleExtScopeList())
			// {
			////
			//// addToTableValue(table, eleExtitem1.getColNm());
			//// addToTableValueBold(table, ":");
			//// addToTableValue(table, eleExtitem1.getColValCd());
			//// if (eleExtitem1.getRhsCost() != 0) {
			//// addToTableValDetStr(table, eleExtitem1.getRhsCost());
			////
			//// } else {
			//// addToTableValue(table, "");
			//// }
			////
			//// }
			////
			//// }
			////
			//// addEmptyRows(table);
			////
			//// }
			//// // extended scope start
			////
			//// // extended scope ends
			////
			//// if (!quotationForm.getEleCIExtScopeList().isEmpty()) {
			//// List<DBOBean> eleCIExtBean = new ArrayList<DBOBean>();
			//// eleCIExtBean = quotationForm.getEleCIExtScopeList();
			//// Map<String, DBOBean> eleCIExtMap1 = new HashedMap();
			////
			//// for (DBOBean eleCIExt1 : eleCIExtBean) {
			//// eleCIExtMap1.put(eleCIExt1.getItemName(), eleCIExt1);
			//// }
			////
			//// addToTableHeaderVal(table, "CI ExtendedScope Items details");
			//// addEmptyRowsWithoutBorder(table);
			////
			//// addToTableValueBoldSmall(table, "CI ExtendedScope Cost :");
			//// addToTableValueBoldSmall(table,
			// eleCIExtBean.get(0).getTotalPrice());
			////
			//// if (eleCIExtBean.get(0).isOverwrittenPriceFlag()) {
			//// addToTableValueBoldSmall(table, "Overwritten Cost :");
			//// addToTableValueBoldSmall(table,
			// eleCIExtBean.get(0).getOverwrittenPrice());
			//// } else {
			////
			//// addToTableHeaderDet(table, "");
			//// addToTableValDet(table, "");
			//// }
			//// addEmptyRowsWithoutBorder(table);
			////
			//// addToTableValueBold(table, "ExtendedScope Item");
			//// addToTableValueBold(table, "Item Cost");
			//// addToTableValueBold(table, "Basic Cost");
			//// addToTableValueBold(table, "AddOn Cost");
			////
			//// for (DBOBean eleCIExtBeans1 : eleCIExtMap1.values()) {
			////
			//// addToTableValueBold(table, eleCIExtBeans1.getItemName());
			//// addToTableValueBig(table, eleCIExtBeans1.getItemCost());
			//// addToTableValueBig(table, eleCIExtBeans1.getBasicCost());
			//// addToTableValueBig(table, eleCIExtBeans1.getAddOnCost());
			//// for (DBOBean eleCIExtitem1 :
			// quotationForm.getEleCIExtScopeList()) {
			////
			//// addToTableValue(table, eleCIExtitem1.getColNm());
			//// addToTableValueBold(table, ":");
			//// addToTableValue(table, eleCIExtitem1.getColValCd());
			//// if (eleCIExtitem1.getRhsCost() != 0) {
			//// addToTableValDetStr(table, eleCIExtitem1.getRhsCost());
			////
			//// } else {
			//// addToTableValue(table, "");
			//// }
			////
			//// }
			////
			//// }
			////
			//// addEmptyRows(table);
			////
			//// }
			// // CIextended scope ends
			//
			// // instrumentation resultset
			//
			// // // Electrical details starts
			// //
			// // if (!quotationForm.getEleGeneralList().isEmpty()) {
			// // List<DBOBean> eleBean = new ArrayList<DBOBean>();
			// // eleBean = quotationForm.getEleGeneralList();
			// // Map<String, DBOBean> eleMap1 = new HashedMap();
			// // Map<String, DBOBean> eleMap2 = new HashedMap();
			// // Map<String, DBOBean> eleMap3 = new HashedMap();
			// //
			// // for (DBOBean ele1 : eleBean) {
			// // eleMap1.put(ele1.getItemName(), ele1);
			// // }
			// //
			// // for (DBOBean ele2 : eleBean) {
			// // eleMap2.put(ele2.getDesItemName(), ele2);
			// // }
			// //
			// // for (DBOBean ele3 : eleBean) {
			// // eleMap3.put(ele3.getDesSubItemName(), ele3);
			// // }
			// //
			// //
			// // addToTableHeaderVal(table, "Electrical Items details");
			// // addEmptyRowsWithoutBorder(table);
			// //
			// // addToTableValueBoldSmall(table, "Total Electrical Cost :");
			// // addToTableValueBoldSmall(table,
			// eleBean.get(0).getTotalPrice());
			// //
			// // if (eleBean.get(0).isOverwrittenPriceFlag()) {
			// // addToTableValueBoldSmall(table, "Overwritten Cost :");
			// // addToTableValueBoldSmall(table,
			// // eleBean.get(0).getOverwrittenPrice());
			// // } else {
			// //
			// // addToTableHeaderDet(table, "");
			// // addToTableValDet(table, "");
			// // }
			// // addEmptyRowsWithoutBorder(table);
			// //
			// // addToTableValueBold(table, "Electrical Item");
			// // addToTableValueBold(table, "Item Cost");
			// // addToTableValueBold(table, "Basic Cost");
			// // addToTableValueBold(table, "AddOn Cost");
			// //
			// // for (DBOBean eleBeans1 : eleMap1.values()) {
			// //
			// // addToTableValueBold(table, eleBeans1.getItemName());
			// // addToTableValueBig(table, eleBeans1.getItemCost());
			// // addToTableValueBig(table, eleBeans1.getBasicCost());
			// // if (eleBeans1.isItemApproxCostFlag()) {
			// // addToTableValueBigRed(table, eleBeans1.getAddOnCost());
			// // }
			// // addToTableValueBig(table, eleBeans1.getAddOnCost());
			// //
			// // for (DBOBean eleBeans2 : eleMap2.values()) {
			// //
			// // int counter = 0;
			// // for (DBOBean elesub2 : quotationForm.getEleGeneralList()) {
			// //
			// // if (elesub2.getDesItemName() != null &&
			// //
			// elesub2.getDesItemName().equalsIgnoreCase(eleBeans2.getDesItemName())
			// // &&
			// //
			// eleBeans1.getItemName().equalsIgnoreCase(elesub2.getItemName()))
			// // {
			// // counter = 1;
			// // }
			// // }
			// // if (counter == 1) {
			// //
			// // addToTableValueBold(table, eleBeans2.getDesItemName());
			// // addToTableValue(table, "");
			// // addToTableValue(table, "");
			// // addToTableValue(table, "");
			// // }
			// // for (DBOBean eleBeans3 : eleMap3.values()) {
			// // if(eleBeans3.getDesSubItemName()==null)
			// // {
			// // for (DBOBean elesub1 : quotationForm.getEleGeneralList()) {
			// //
			// // if (elesub1.getDesItemName() != null &&
			// //
			// elesub1.getDesItemName().equalsIgnoreCase(eleBeans2.getDesItemName())
			// // &&
			// //
			// eleBeans1.getItemName().equalsIgnoreCase(elesub1.getItemName()))
			// // {
			// //
			// // addToTableValue(table, elesub1.getColNm());
			// // addToTableValueBold(table, ":");
			// // addToTableValue(table, elesub1.getColValCd());
			// // if (elesub1.getRhsCost() != 0) {
			// // if (elesub1.isApproxCostFlag()) {
			// // addToTableValDetStrRed(table, elesub1.getRhsCost());
			// // } else {
			// //
			// // addToTableValDetStr(table, elesub1.getRhsCost());
			// //
			// // }
			// //
			// // } else {
			// // addToTableValue(table, "");
			// // }
			// //
			// // }
			// //
			// // }
			// // }
			// // else
			// // {
			// // addToTableValueBold(table, eleBeans3.getDesSubItemName());
			// // addToTableValue(table, "");
			// // addToTableValue(table, "");
			// // addToTableValue(table, "");
			// //
			// if(eleBeans2.getDesItemName().equalsIgnoreCase(eleBeans3.getDesItemName()))
			// // {
			// // for (DBOBean elesub1 : quotationForm.getEleGeneralList()) {
			// //
			// // if (elesub1.getDesItemName() != null &&
			// //
			// elesub1.getDesItemName().equalsIgnoreCase(eleBeans2.getDesItemName())
			// // &&
			// //
			// eleBeans1.getItemName().equalsIgnoreCase(elesub1.getItemName()))
			// // {
			// //
			// // addToTableValue(table, elesub1.getColNm());
			// // addToTableValueBold(table, ":");
			// // addToTableValue(table, elesub1.getColValCd());
			// // if (elesub1.getRhsCost() != 0) {
			// // if (elesub1.isApproxCostFlag()) {
			// // addToTableValDetStrRed(table, elesub1.getRhsCost());
			// // } else {
			// //
			// // addToTableValDetStr(table, elesub1.getRhsCost());
			// //
			// // }
			// //
			// // } else {
			// // addToTableValue(table, "");
			// // }
			// //
			// // }
			// //
			// // }
			// // }
			// // }
			// //
			// // }
			// //
			// //// for (DBOBean eleBeans3 : eleMap3.values()) {
			// ////
			// //// int counter1 = 0;
			// //// for (DBOBean elesub3 : quotationForm.getEleGeneralList()) {
			// ////
			// //// if (elesub3.getDesSubItemName() != null &&
			// //
			// elesub3.getDesSubItemName().equalsIgnoreCase(eleBeans3.getDesSubItemName())
			// //// &&
			// //
			// eleBeans3.getDesItemName().equalsIgnoreCase(elesub3.getDesItemName()))
			// // {
			// //// counter1 = 1;
			// //// }
			// //// }
			// //// if (counter1 == 1) {
			// ////
			// //// addToTableValueBold(table, eleBeans3.getDesSubItemName());
			// //// addToTableValue(table, "");
			// //// addToTableValue(table, "");
			// //// addToTableValue(table, "");
			// //// }
			// ////
			// //// for (DBOBean elesub4 : quotationForm.getEleGeneralList()) {
			// ////
			// //// if (elesub4.getDesSubItemName() != null &&
			// //
			// elesub4.getDesSubItemName().equalsIgnoreCase(eleBeans3.getDesSubItemName())
			// //// &&
			// //
			// eleBeans3.getDesItemName().equalsIgnoreCase(elesub4.getDesItemName()))
			// // {
			// ////
			// //// addToTableValue(table, elesub4.getColNm());
			// //// addToTableValueBold(table, ":");
			// //// addToTableValue(table, elesub4.getColValCd());
			// //// if (elesub4.getRhsCost() != 0) {
			// //// if (elesub4.isApproxCostFlag()) {
			// //// addToTableValDetStrRed(table, elesub4.getRhsCost());
			// //// } else {
			// ////
			// //// addToTableValDetStr(table, elesub4.getRhsCost());
			// ////
			// //// }
			// ////
			// //// } else {
			// //// addToTableValue(table, "");
			// //// }
			// ////
			// //// }
			// ////
			// //// }
			// ////
			// //// }
			// //
			// //
			// // }
			// //
			// // }
			// // addEmptyRows(table);
			// //
			// // }
			// // // electrical details ends
			//
			// // Add On Starts
			// if (!quotationForm.getSubmittedAddOnList().isEmpty()) {
			// List<AddOnComponent> addOn = new ArrayList<AddOnComponent>();
			// addOn = quotationForm.getSubmittedAddOnList();
			// addToTableHeaderVal(table, "AddOn Components ");
			//
			// addEmptyRowsWithoutBorder(table);
			//
			// addToTableHeaderDet(table, "Total AddOn Cost : ");
			// addToTableValDetStr(table, (float) addOn.get(0).getAddOnTotal());
			//
			// if (addOn.get(0).getSelectedCostFlag() == 1) {
			// addToTableHeaderDet(table, "Overwritten Cost :");
			// addToTableValDet(table,
			// Float.toString(addOn.get(0).getSelectedCost()));
			// } else {
			// addToTableHeaderDet(table, "");
			// addToTableValDet(table, "");
			// }
			//
			// for (AddOnComponent addOns : addOn) {
			//
			// addToTableValue(table, addOns.getAddOnCompoName());
			// addToTableValue(table, Float.toString(addOns.getExcelCost()));
			//
			// }
			// if (addOn.size() % 2 != 0) {
			// addToTableHeaderDet(table, "");
			// addToTableValDet(table, "");
			// }
			//
			// addEmptyRows(table);
			//
			// List<AddOnComponent> addOn1 = new ArrayList<AddOnComponent>();
			// addOn1 = quotationForm.getQuotAddOnOthersList();
			// addToTableHeaderVal(table, "Add ON Others details");
			// addEmptyRowsWithoutBorder(table);
			//
			// addToTableHeaderDet(table, "Quantity");
			// addToTableHeaderDet(table, "Component Name");
			// addToTableHeaderDet(table, "Cost");
			// addToTableHeaderDet(table, "Remarks");
			//
			// for (AddOnComponent addOnOthBeans : addOn1) {
			// addToTableValue(table,
			// Float.toString(addOnOthBeans.getNewCompQty()));
			// addToTableValue(table, addOnOthBeans.getNewCompName());
			// addToTableValue(table,
			// Float.toString(addOnOthBeans.getNewCompPrice()));
			// addToTableValue(table, addOnOthBeans.getNewCompRemark());
			// }
			// addEmptyRows(table);
			// }
			//
			// // Add On Ends
			//
			// // // DBO mechanical details starts
			// // if (!quotationForm.getQuotDboMechList().isEmpty()) {
			// // List<DBOBean> mechBean = new ArrayList<DBOBean>();
			// // mechBean = quotationForm.getQuotDboMechList();
			// // Map<String, DBOBean> mechMap = new HashedMap();
			// //
			// // for (DBOBean mech1 : mechBean) {
			// // mechMap.put(mech1.getItemName(), mech1);
			// // }
			// //
			// // addToTableHeaderVal(table, "DBO Mechanical details");
			// // addEmptyRowsWithoutBorder(table);
			// //
			// // addToTableHeaderDet(table, "Total Cost :");
			// // addToTableValDetStr(table, mechBean.get(0).getTotalPrice());
			// //
			// // if (mechBean.get(0).isOverwrittenPriceFlag()) {
			// // addToTableHeaderDet(table, "Overwritten Cost :");
			// // addToTableValDet(table,
			// // Float.toString(mechBean.get(0).getOverwrittenPrice()));
			// // } else {
			// //
			// // addToTableHeaderDet(table, "");
			// // addToTableValDet(table, "");
			// // }
			// // addEmptyRowsWithoutBorder(table);
			// //
			// // addToTableHeaderDet(table, "Quantity-Component");
			// // addToTableHeaderDet(table, "Cost");
			// // addToTableHeaderDet(table, "Additional Cost");
			// // addToTableHeaderDet(table, "Remarks");
			// //
			// // for (DBOBean mechBeans : mechMap.values()) {
			// // PdfPCell cellA = new PdfPCell();
			// // cellA.addElement(new Phrase(mechBeans.getQuantity() + " - " +
			// // mechBeans.getItemName(), PdfStylAttributes.NORMAL_FONT));
			// // cellA.setBorder(Rectangle.NO_BORDER);
			// // table.addCell(cellA);
			// //
			// // addToTableValue(table, Float.toString(mechBeans.getPrice()));
			// // addToTableValue(table,
			// // Float.toString(mechBeans.getAdditionalCost()));
			// // addToTableValue(table, mechBeans.getCompRemarks());
			// // }
			// // // DBO mechanical details ends
			// //
			// // // DBO mechanical others details starts
			// // if (quotationForm.getQuotDboMechOthersList().size() == 0)
			// // addEmptyRows(table);
			// // }
			// //
			// // if (!quotationForm.getQuotDboMechOthersList().isEmpty()) {
			// // List<DBOBean> mechOthBean = new ArrayList<DBOBean>();
			// // mechOthBean = quotationForm.getQuotDboMechOthersList();
			// //
			// // addEmptyRowsWithoutBorder(table);
			// // addToTableHeaderVal(table, "DBO Mechanical Others details");
			// // addEmptyRowsWithoutBorder(table);
			// //
			// // if (quotationForm.getQuotDboMechList().size() == 0) {
			// // addToTableHeaderDet(table, "Total Cost :");
			// // addToTableValDetStr(table,
			// mechOthBean.get(0).getTotalPrice());
			// //
			// // if (mechOthBean.get(0).isOverwrittenPriceFlag()) {
			// // addToTableHeaderDet(table, "Overwritten Cost :");
			// // addToTableValDet(table,
			// // Float.toString(mechOthBean.get(0).getOverwrittenPrice()));
			// // } else {
			// //
			// // addToTableHeaderDet(table, "");
			// // addToTableValDet(table, "");
			// // }
			// //
			// // }
			// //
			// // addToTableHeaderDet(table, "Quantity");
			// // addToTableHeaderDet(table, "Component Name");
			// // addToTableHeaderDet(table, "Cost");
			// // addToTableHeaderDet(table, "Remarks");
			// //
			// // for (DBOBean mechOthBeans : mechOthBean) {
			// // addToTableValue(table,
			// // Float.toString(mechOthBeans.getOthQuantity()));
			// // addToTableValue(table, mechOthBeans.getOthCompName());
			// // addToTableValue(table,
			// // Float.toString(mechOthBeans.getOthPrice()));
			// // addToTableValue(table, mechOthBeans.getOthRemarks());
			// // }
			// // addEmptyRows(table);
			// // }
			// //
			// // // DBO mechanical others details ends
			//
			// // DBO Electrical details starts
			//
			// // if (!quotationForm.getQuotDboElectricalList().isEmpty()) {
			// // List<DBOBean> eleBean = new ArrayList<DBOBean>();
			// // eleBean = quotationForm.getQuotDboElectricalList();
			// // Map<String, DBOBean> eleMap = new HashedMap();
			// //
			// // for (DBOBean ele1 : eleBean) {
			// // eleMap.put(ele1.getItemName(), ele1);
			// // }
			// //
			// // addToTableHeaderVal(table, "DBO Electrical details");
			// // addEmptyRowsWithoutBorder(table);
			// //
			// // addToTableHeaderDet(table, "Total Cost :");
			// // addToTableValDetStr(table, eleBean.get(0).getTotalPrice());
			// //
			// // if (eleBean.get(0).isOverwrittenPriceFlag()) {
			// // addToTableHeaderDet(table, "Overwritten Cost :");
			// // addToTableValDet(table,
			// // Float.toString(eleBean.get(0).getOverwrittenPrice()));
			// // } else {
			// //
			// // addToTableHeaderDet(table, "");
			// // addToTableValDet(table, "");
			// // }
			// // addEmptyRowsWithoutBorder(table);
			// //
			// // addToTableHeaderDet(table, "Quantity");
			// // addToTableHeaderDet(table, "Component");
			// // addToTableHeaderDet(table, "Cost");
			// // addToTableHeaderDet(table, "Remarks");
			// //
			// // for (DBOBean eleBeans : eleMap.values()) {
			// // PdfPCell cellA = new PdfPCell();
			// // cellA.addElement(new
			// // Phrase(Float.toString(eleBeans.getQuantity()),
			// // PdfStylAttributes.NORMAL_FONT));
			// // cellA.setBorder(Rectangle.NO_BORDER);
			// // table.addCell(cellA);
			// //
			// // addToTableValue(table, eleBeans.getItemName());
			// // addToTableValue(table, Float.toString(eleBeans.getPrice()));
			// // addToTableValue(table, eleBeans.getCompRemarks());
			// //
			// // addEmptyRows(table);
			// //
			// // }
			// //
			// // // Dbo electrical details ends
			//
			// // Dbo electrical special addon details starts
			//
			// if (!quotationForm.getQuotDboEleSplAddOnList().isEmpty()) {
			// List<DBOBean> eleSplBean = new ArrayList<DBOBean>();
			// eleSplBean = quotationForm.getQuotDboEleSplAddOnList();
			// Map<String, DBOBean> eleSplMap = new HashMap();
			//
			// for (DBOBean ele2 : eleSplBean) {
			// eleSplMap.put(ele2.getItemName(), ele2);
			// }
			//
			// addToTableHeaderVal(table, "DBO Electrical Special Addon
			// details");
			// addEmptyRowsWithoutBorder(table);
			//
			// for (DBOBean eleSplBeans : eleSplMap.values()) {
			//
			// PdfPCell cellC = new PdfPCell();
			// cellC.addElement(new Phrase(eleSplBeans.getItemName(),
			// PdfStylAttributes.NORMAL_FONT));
			// cellC.setColspan(4);
			// cellC.setBorder(Rectangle.NO_BORDER);
			// table.addCell(cellC);
			//
			// addEmptyRows(table);
			//
			// addToTableHeaderDet(table, "Quantity");
			// addToTableValue(table,
			// Float.toString(eleSplBeans.getSplQuantity()));
			//
			// addToTableHeaderDet(table, "Component Name");
			// addToTableValue(table, eleSplBeans.getSplColNm());
			//
			// addToTableHeaderDet(table, "Cost");
			// addToTableValue(table, Float.toString(eleSplBeans.getSplCost()));
			//
			// addToTableHeaderDet(table, " ");
			// addToTableValue(table, "");
			//
			// }
			// addEmptyRows(table);
			//
			// }
			//
			// // Dbo electrical special addon details ends
			//
			// // Dbo electrical addon details starts
			//
			// if (!quotationForm.getDboEleAddOnList().isEmpty()) {
			// List<DBOBean> eleAddOnBean = new ArrayList<DBOBean>();
			// eleAddOnBean = quotationForm.getDboEleAddOnList();
			// Map<String, DBOBean> eleAddOnMap = new HashMap();
			//
			// for (DBOBean ele3 : eleAddOnBean) {
			// eleAddOnMap.put(ele3.getItemName(), ele3);
			// }
			//
			// addToTableHeaderVal(table, "DBO Electrical AddOn details");
			// addEmptyRowsWithoutBorder(table);
			//
			// for (DBOBean eleAddOnBeans : eleAddOnMap.values()) {
			//
			// PdfPCell cellC = new PdfPCell();
			// cellC.addElement(new Phrase(eleAddOnBeans.getItemName(),
			// PdfStylAttributes.NORMAL_FONT));
			// cellC.setColspan(4);
			// cellC.setBorder(Rectangle.NO_BORDER);
			// table.addCell(cellC);
			//
			// addEmptyRows(table);
			//
			// addToTableHeaderDet(table, "Quantity");
			// addToTableValue(table,
			// Float.toString(eleAddOnBeans.getAddOnQuantity()));
			//
			// addToTableHeaderDet(table, "Component Name");
			// addToTableValue(table, eleAddOnBeans.getAddOnCompNm());
			//
			// addToTableHeaderDet(table, "Cost");
			// addToTableValue(table,
			// Float.toString(eleAddOnBeans.getAddOnCost()));
			//
			// addToTableHeaderDet(table, " ");
			// addToTableValue(table, "");
			//
			// }
			// addEmptyRows(table);
			// }
			//
			// // Dbo electrical addon details ends
			//
			// // DBO Electrical others details Starts
			//
			// if (!quotationForm.getQuotDboEleOthersList().isEmpty()) {
			// List<DBOBean> eleOthBean = new ArrayList<DBOBean>();
			// eleOthBean = quotationForm.getQuotDboEleOthersList();
			//
			// addEmptyRowsWithoutBorder(table);
			// addToTableHeaderVal(table, "DBO Electrical Others details");
			// addEmptyRowsWithoutBorder(table);
			//
			// addToTableHeaderDet(table, "Quantity");
			// addToTableHeaderDet(table, "Component Name");
			// addToTableHeaderDet(table, "Cost");
			// addToTableHeaderDet(table, "Remarks ");
			//
			// for (DBOBean eleOthBeans : eleOthBean) {
			// addToTableValue(table,
			// Float.toString(eleOthBeans.getOthQuantity()));
			// addToTableValue(table, eleOthBeans.getOthCompName());
			// addToTableValue(table,
			// Float.toString(eleOthBeans.getOthPrice()));
			// addToTableValue(table, eleOthBeans.getOthRemarks());
			// }
			//
			// addEmptyRows(table);
			// }
			//
			// // DBO Electrical others details ends
			//
			// // // DBO Electrical additional instruments details Starts
			// //
			// // if (!quotationForm.getAddInstrList().isEmpty()) {
			// // List<DBOBean> eleAddInstrBean = new ArrayList<DBOBean>();
			// // eleAddInstrBean = quotationForm.getAddInstrList();
			// //
			// // addEmptyRowsWithoutBorder(table);
			// // addToTableHeaderVal(table, "DBO Electrical additional
			// instruments
			// // details");
			// // addEmptyRowsWithoutBorder(table);
			// //
			// //
			// // if(eleAddInstrBean.size()>1){
			// // for(int i=0;i<eleAddInstrBean.size()-1;i++){
			// // for(int k=i+1;k<eleAddInstrBean.size();k++){
			// //
			// if(eleAddInstrBean.get(i).getItemId()!=eleAddInstrBean.get(k).getItemId()){
			// // PdfPCell cellD = new PdfPCell();
			// // cellD.addElement(new
			// Phrase(eleAddInstrBean.get(i).getItemName(),
			// // PdfStylAttributes.NORMAL_FONT));
			// // cellD.setColspan(4);
			// // cellD.setBorder(Rectangle.NO_BORDER);
			// // table.addCell(cellD);
			// // }
			// // }
			// // }
			// // }else{
			// // PdfPCell cellD = new PdfPCell();
			// // cellD.addElement(new
			// Phrase(eleAddInstrBean.get(0).getItemName(),
			// // PdfStylAttributes.NORMAL_FONT));
			// // cellD.setColspan(4);
			// // cellD.setBorder(Rectangle.NO_BORDER);
			// // table.addCell(cellD);
			// // }
			// //
			// // addToTableHeaderDet(table, "Quantity");
			// // addToTableHeaderDet(table, "Component");
			// // addToTableHeaderDet(table, "Cost");
			// // addToTableHeaderDet(table, " ");
			// //
			// //
			// //
			// // for (DBOBean eleAddInstrBeans :eleAddInstrBean) {
			// // addToTableValue(table,
			// // Float.toString(eleAddInstrBeans.getQuantity()));
			// // addToTableValue(table, eleAddInstrBeans.getColNm());
			// // addToTableValue(table,
			// // Float.toString(eleAddInstrBeans.getAddInstrCost()));
			// // addToTableValue(table, "");
			// //
			// // }
			// // addEmptyRows(table);
			// // }
			// //
			// // // DBO Electrical additional instruments details ends
			//
			// // DBO Electrical additional instruments details starts
			//
			// if (!quotationForm.getAddInstrList().isEmpty()) {
			// List<DBOBean> eleAddInstrBean = new ArrayList<DBOBean>();
			// eleAddInstrBean = quotationForm.getAddInstrList();
			// Map<String, DBOBean> eleAddInstrMap = new HashedMap();
			//
			// for (DBOBean ele4 : eleAddInstrBean) {
			// eleAddInstrMap.put(ele4.getItemName(), ele4);
			// }
			//
			// addToTableHeaderVal(table, "DBO Electrical additional instruments
			// details");
			// addEmptyRowsWithoutBorder(table);
			//
			// addToTableHeaderDet(table, "Total Cost :");
			// addToTableValDetStr(table,
			// eleAddInstrBean.get(0).getTotalPrice());
			//
			// addEmptyRowsWithoutBorder(table);
			//
			// for (DBOBean eleAddInstrBeans : eleAddInstrMap.values()) {
			//
			// PdfPCell cellC = new PdfPCell();
			// cellC.addElement(new Phrase(eleAddInstrBeans.getItemName(),
			// PdfStylAttributes.NORMAL_FONT));
			// cellC.setColspan(4);
			// cellC.setBorder(Rectangle.NO_BORDER);
			// table.addCell(cellC);
			//
			// addEmptyRows(table);
			//
			// addToTableHeaderDet(table, "Component");
			// addToTableHeaderDet(table, "Quantity");
			// addToTableHeaderDet(table, "Cost");
			// addToTableHeaderDet(table, " ");
			//
			// for (DBOBean eleAddInstrBeans1 : eleAddInstrMap.values()) {
			//
			// PdfPCell cellD = new PdfPCell();
			// cellD.addElement(
			// new Phrase(eleAddInstrBeans1.getAddInstrCompNm(),
			// PdfStylAttributes.NORMAL_FONT));
			// cellD.setColspan(4);
			// cellD.setBorder(Rectangle.NO_BORDER);
			// table.addCell(cellD);
			// addToTableValue(table,
			// Float.toString(eleAddInstrBeans.getAddInstrQuantity()));
			// addToTableValue(table,
			// Float.toString(eleAddInstrBeans.getAddInstrCost()));
			// addToTableValue(table, " ");
			// }
			//
			// addEmptyRows(table);
			// }
			// }
			//
			// // Errection and Comm details Starts
			// if (!quotationForm.getErrectionCommList().isEmpty()) {
			// ErectionCommissionBean ecBean = new ErectionCommissionBean();
			//
			// ecBean = quotationForm.getErrectionCommList().get(0);
			//
			// addToTableHeaderVal(table, "Errection and Commisioning Details
			// ");
			//
			// addEmptyRowsWithoutBorder(table);
			//
			// addToTableHeaderDet(table, "Customer Type :");
			// addToTableValDet(table,
			// quotationForm.getSaveBasicDetails().getCustType());
			//
			// addToTableHeaderDet(table, "Type Of Charge :");
			// addToTableValDet(table, ecBean.getTypeOfCharge());
			//
			// if (ecBean.getLoadingId() > 0) {
			// addToTableHeaderDet(table, "Type Of Loading :");
			// addToTableValDet(table, ecBean.getLoadingName());
			// addToTableHeaderDet(table, "");
			// addToTableValDet(table, "");
			// }
			// if (ecBean.getLodgingId() > 0) {
			// addToTableHeaderDet(table, "Type Of Boarding and Lodging :");
			// addToTableValDet(table, ecBean.getLodgingName());
			// }
			// if ((ecBean.getLodgingId() > 0) &&
			// (saveBasicDetails.getCustCode().equalsIgnoreCase("EX"))) {
			// addToTableHeaderDet(table, "Number Of Man Days :");
			// addToTableValDet(table,
			// Integer.toString(ecBean.getNoOfManDays()));
			//
			// } else if (ecBean.getLodgingId() > 0) {
			// addToTableHeaderDet(table, "");
			// addToTableValDet(table, "");
			// }
			//
			// addToTableValueBoldSmall(table, "Total Cost :");
			// addToTableValueBoldSmall(table, ecBean.getPrice());
			//
			// if (ecBean.isOverwrittenPriceFlag()) {
			// addToTableValueBoldSmall(table, "Overwritten Cost :");
			// addToTableValueBoldSmall(table, ecBean.getOverwrittenPrice());
			// } else {
			//
			// addToTableHeaderDet(table, "");
			// addToTableValDet(table, "");
			//
			// }
			//
			// addEmptyRows(table);
			// }
			// // Errection and Comm details Ends
			//
			// // Transportation details starts
			// if (!quotationForm.getTransportationDetailList().isEmpty()) {
			// List<TransportationDetailsBean> transDet = new
			// ArrayList<TransportationDetailsBean>();
			// transDet = quotationForm.getTransportationDetailList();
			//
			// addToTableHeaderVal(table, "Transportation Details");
			// addEmptyRowsWithoutBorder(table);
			//
			// addToTableHeaderDet(table, "Customer Type :");
			// addToTableValDet(table,
			// quotationForm.getSaveBasicDetails().getCustType());
			//
			// addToTableHeaderDet(table, "Transportation Type :");
			// addToTableValDet(table, transDet.get(0).getTransType());
			//
			// addToTableValueBoldSmall(table, "Total Cost : ");
			// addToTableValueBoldSmall(table, transDet.get(0).getTotalPrice());
			//
			// if (transDet.get(0).getOverwrittenPriceFlag() == 1) {
			// addToTableValueBoldSmall(table, "Overwritten Cost :");
			// addToTableValueBoldSmall(table,
			// transDet.get(0).getOverwrittenPrice());
			// } else {
			//
			// addToTableHeaderDet(table, "");
			// addToTableValDet(table, "");
			// }
			// addEmptyRowsWithoutBorder(table);
			// if (!transDet.get(0).getTransType().equalsIgnoreCase("EX-WORKS"))
			// {
			// if (saveBasicDetails.getCustCode().equalsIgnoreCase("DM")) {
			//
			// addToTableHeaderDet(table, "Component");
			// addToTableHeaderDet(table, "From Place");
			// addToTableHeaderDet(table, "Distance(km)");
			// addToTableHeaderDet(table, "Cost");
			//
			// for (TransportationDetailsBean transDets : transDet) {
			//
			// addToTableValue(table, transDets.getCompoName());
			// addToTableValue(table, transDets.getFOBPlace());
			// addToTableValue(table, Float.toString(transDets.getDistance()));
			// addToTableValue(table, Float.toString(transDets.getCompPrice()));
			// }
			// } else if (saveBasicDetails.getCustCode().equalsIgnoreCase("EX"))
			// {
			// addToTableHeaderDet(table, "FOB Cost");
			// addToTableHeaderDet(table, "Country Name");
			// addToTableHeaderDet(table, "Port Name");
			// addToTableHeaderDet(table, "Cost");
			//
			// for (TransportationDetailsBean transDets : transDet) {
			//
			// addToTableValue(table, Float.toString(transDets.getFobPrice()));
			// addToTableValue(table, transDets.getCountryName());
			// addToTableValue(table, transDets.getPortName());
			// addToTableValue(table, Float.toString(transDets.getCompPrice()));
			// }
			// }
			// }
			// addEmptyRows(table);
			// }
			//
			// // Transportation details ends
			//
			// // Packaging details starts
			// if (!quotationForm.getPackageDetailsListData().isEmpty()) {
			// TransportationDetailsBean pkBean = new
			// TransportationDetailsBean();
			// pkBean = quotationForm.getPackageDetailsListData().get(0);
			// addToTableHeaderVal(table, "Packaging Details");
			// addEmptyRowsWithoutBorder(table);
			//
			// addToTableHeaderDet(table, "Customer Type :");
			// addToTableValDet(table,
			// quotationForm.getSaveBasicDetails().getCustType());
			//
			// addToTableHeaderDet(table, "Type Of Packaging :");
			// addToTableValDet(table, pkBean.getPackageTypeName());
			//
			// addToTableValueBoldSmall(table, "Total Cost :");
			// addToTableValueBoldSmall(table, pkBean.getPrice());
			//
			// if (pkBean.getOverwrittenPriceFlag() > 0) {
			// addToTableValueBoldSmall(table, "Overwritten Cost :");
			// addToTableValueBoldSmall(table, pkBean.getOverwrittenPrice());
			// } else {
			// addToTableHeaderDet(table, "");
			// addToTableValDet(table, "");
			// }
			// addEmptyRows(table);
			// }
			// // Packaging details ends
			//
			// // Variable cost details starts
			// if (!quotationForm.getVariableCostList().isEmpty()) {
			// OtherCostsBean varBean = new OtherCostsBean();
			// varBean = quotationForm.getVariableCostList().get(0);
			//
			// addToTableHeaderVal(table, "Variable cost details");
			// addEmptyRowsWithoutBorder(table);
			//
			// addToTableHeaderDet(table, "Total Order Price :");
			// addToTableValDetStr(table, varBean.getTotOrderCost());
			//
			// addToTableHeaderDet(table, "Contingency General :");
			// addToTableValDetStr(table, varBean.getContigencyGeneral());
			//
			// addToTableHeaderDet(table, "LD for Performance :");
			// addToTableValDetStr(table, varBean.getLdPerformance());
			//
			// addToTableHeaderDet(table, "LD for late delivery :");
			// addToTableValDetStr(table, varBean.getLdforLateDelivery());
			//
			// addToTableHeaderDet(table, "BG charges for ABG :");
			// addToTableValDetStr(table, varBean.getBankingCharges1());
			//
			// addToTableHeaderDet(table, "BG charges for PBG ");
			// addToTableValDetStr(table, varBean.getBankingCharges2());
			//
			// addToTableHeaderDet(table, "Warranty Cost :");
			// addToTableValDetStr(table, varBean.getWarrantyCost());
			//
			// addToTableHeaderDet(table, "Overhead On Total Sale Value :");
			// addToTableValDetStr(table, varBean.getOvrheadSaleCost());
			//
			// addToTableHeaderDet(table, "Insurance :");
			// addToTableValDetStr(table, varBean.getInsurance());
			//
			// addToTableHeaderDet(table, "Interest Charges : ");
			// addToTableValDetStr(table, varBean.getIntrestCharges());
			//
			// addToTableHeaderDet(table, "Travel Expenses :");
			// addToTableValDetStr(table, varBean.getTravelExpenses());
			//
			// addToTableHeaderDet(table, "Contingency for DBO : ");
			// addToTableValDetStr(table, varBean.getDboContigency());
			//
			// addToTableHeaderDet(table, "Contingency for Turbine :");
			// addToTableValDetStr(table, varBean.getTurbineContigency());
			//
			// addToTableHeaderDet(table, "Engineering Charges :");
			// addToTableValDetStr(table, varBean.getEngineCharges());
			//
			// addToTableHeaderDet(table, "Sales Expenses :");
			// addToTableValDetStr(table, varBean.getSalesExpenses());
			//
			// addToTableHeaderDet(table, "Agency Commision :");
			// addToTableValDetStr(table, varBean.getAgencyCommCharges());
			//
			// addToTableHeaderDet(table, "Others :");
			// addToTableValDetStr(table, varBean.getOthers());
			//
			// addToTableValueBoldSmall(table, "Total Variable Cost :");
			// addToTableValueBoldSmall(table, varBean.getTotalVariableCost());
			//
			// if (varBean.isVarNewFlag()) {
			// addToTableValueBoldSmall(table, "Overwritten Cost :");
			// addToTableValueBoldSmall(table, varBean.getVarNewCost());
			//
			// addToTableHeaderDet(table, "");
			// addToTableValDet(table, "");
			// }
			// addEmptyRows(table);
			// }
			// // Variable cost details ends
			//
			// // Spares cost details starts
			// if (!quotationForm.getSparesCostList().isEmpty()) {
			// OtherCostsBean sparesBean = new OtherCostsBean();
			// sparesBean = quotationForm.getSparesCostList().get(0);
			//
			// addToTableHeaderVal(table, "Spares cost details");
			// addEmptyRowsWithoutBorder(table);
			//
			// addToTableHeaderDet(table, "Spares Cost :");
			// addToTableValDetStr(table, sparesBean.getSparesCost());
			//
			// addToTableHeaderDet(table, "Profit Percent :");
			// addToTableValDetStr(table, sparesBean.getInpSparesProfit());
			//
			// addToTableHeaderDet(table, "Over Heads Of Spares Cost :");
			// addToTableValDetStr(table, sparesBean.getOvrheadSparesCost());
			//
			// addToTableHeaderDet(table, " Over Heads On Total Sale Value :");
			// addToTableValDetStr(table, sparesBean.getOvrheadTotSaleCost());
			//
			// addToTableHeaderDet(table, "Order Booking Price For Spares :");
			// addToTableValDetStr(table, sparesBean.getOrderBookingSpares());
			//
			// addToTableHeaderDet(table, "Net Profit :");
			// addToTableValDetStr(table, sparesBean.getSparesNetProfit());
			//
			// addToTableValueBoldSmall(table, "Total Spares Cost :");
			// addToTableValueBoldSmall(table, sparesBean.getTotalSparesCost());
			//
			// if (sparesBean.isSparesNewFlag()) {
			// addToTableValueBoldSmall(table, "Overwritten Cost :");
			// addToTableValueBoldSmall(table, sparesBean.getSparesNewCost());
			// } else {
			// addToTableHeaderDet(table, "");
			// addToTableValDet(table, "");
			// }
			// addEmptyRows(table);
			// }
			//
			// // Spares cost details ends
			//
			// // Project cost details starts
			// if (!quotationForm.getProjectCostList().isEmpty()) {
			// OtherCostsBean projBean = new OtherCostsBean();
			// projBean = quotationForm.getProjectCostList().get(0);
			//
			// addToTableHeaderVal(table, "Project cost details");
			// addEmptyRowsWithoutBorder(table);
			//
			// addToTableHeaderDet(table, "Supply Price :");
			// addToTableValDetStr(table, projBean.getProjSupply());
			//
			// addToTableHeaderDet(table, "Services Price :");
			// addToTableValDetStr(table, projBean.getProjServices());
			//
			// addToTableHeaderDet(table, "Supply Cost :");
			// addToTableValDetStr(table, projBean.getSupplyCost());
			//
			// addToTableHeaderDet(table, " Services Cost :");
			// addToTableValDetStr(table, projBean.getServiceCost());
			//
			// addToTableHeaderDet(table, "Transportation Price :");
			// addToTableValDetStr(table, projBean.getProjTransport());
			//
			// addToTableHeaderDet(table, "Transportation Cost :");
			// addToTableValDetStr(table, projBean.getTransCost());
			//
			// addToTableHeaderDetBold(table, "Oder Booking Price For Turbine
			// And Spares :",
			// PdfStylAttributes.NORMAL_BOLD);
			// addToTableValDetStr(table, projBean.getTurbineOrderBookCost());
			//
			// addToTableHeaderDetBold(table, "Net Profit :",
			// PdfStylAttributes.NORMAL_BOLD);
			// addToTableValDetStr(table, projBean.getTotOrderCostNetProfit());
			//
			// addToTableHeaderDetBold(table, "Percent Profit :",
			// PdfStylAttributes.NORMAL_BOLD);
			// addToTableValDetStr(table, projBean.getPercentProfit());
			//
			// addToTableValueBoldSmall(table, "Cost For Turbine And Spares :");
			// addToTableValueBoldSmall(table, projBean.getTotalProjectCost());
			//
			// if (projBean.isProjectNewFlag()) {
			// addToTableValueBoldSmall(table, "Overwritten Cost :");
			// addToTableValueBoldSmall(table, projBean.getProjectNewCost());
			//
			// addToTableHeaderDet(table, "");
			// addToTableValDet(table, "");
			// }
			// addEmptyRows(table);
			// }
			// // Project cost details ends

			float[] colWidths = new float[] { 30f, 20f, 30f, 20f };
			table.setWidths(colWidths);
			prefaceUser.add(table);

			document.add(preface);
			document.add(prefaceUser);

			document.newPage();
			document.newPage();

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg("Something went Wrong");
			logger.error("Exception :" + e);
		}
	}

	private void addToTableHeaderDetBold(PdfPTable table, String data, Font normalBold) {
		PdfPCell cell = new PdfPCell();
		cell.addElement(new Phrase(data, PdfStylAttributes.NORMAL_BOLD));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(1);
		table.addCell(cell);

	}

	// Detailed report ends

	// technical report start

	public void addTechnicalContentDet(Document document, PdfPTable table, ReportBean reportBean)
			throws DocumentException {

		Paragraph paragraph = new Paragraph();
		paragraph.setFont(PdfStylAttributes.NORMAL_FONT);
		createTechnicalContentTableDet(paragraph, table, reportBean);
		document.add(paragraph);

	}

	private void createTechnicalContentTableDet(Paragraph paragraph, PdfPTable table, ReportBean reportBean)
			throws BadElementException {

		logger.info("create Technical Report Start");

	}

	public void addTechnicalReportBodyDet(Document document, String title, ReportBean reportBean)
			throws DocumentException {
		try {

			Paragraph preface = new Paragraph();
			addEmptyLine(preface, 1);

			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(100);
			table.setHeaderRows(0);

			PdfPTable tablevms = new PdfPTable(6);
			tablevms.setWidthPercentage(100);
			tablevms.setHeaderRows(0);

			PdfPTable tablestg = new PdfPTable(5);
			tablestg.setWidthPercentage(100);
			tablestg.setHeaderRows(0);

			PdfPTable tableaddinstr = new PdfPTable(5);
			tableaddinstr.setWidthPercentage(100);
			tableaddinstr.setHeaderRows(0);

			addEmptyLine(preface, 1);
			Paragraph prefaceUser = new Paragraph();

			Paragraph paragraph0 = new Paragraph();
			paragraph0.setFont(PdfStylAttributes.NORMAL_FONT1);

			paragraph0.add(new Paragraph("Reference No:  " + reportBean.getOpportunitySeqNum()
					+ "																																																																																																																								"
					+ "Date: " + reportBean.getDate()));
			addEmptyLine(paragraph0, 1);

			Paragraph paragraph01 = new Paragraph();
			paragraph01.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph01.add(new Paragraph("Purchaser:  " + reportBean.getOppName()));
			addEmptyLine(paragraph01, 1);

			Paragraph paragraph02 = new Paragraph();
			paragraph02.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph02.add(new Paragraph("Project Name:  " + reportBean.getAccountName()));
			addEmptyLine(paragraph02, 1);

			String TurbineCode = null;
			TurbineCode = reportBean.getTurbineCode();

			Paragraph paragraph04 = new Paragraph();

			paragraph04.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			paragraph04.add(new Paragraph("Kind Attention:  " + reportBean.getCustContactPersonName()));
			addEmptyLine(paragraph04, 1);
			Paragraph paragraph05 = new Paragraph();

			paragraph05.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);

			paragraph05.add(new Paragraph("Sub:  " + reportBean.getSubject()));
			addEmptyLine(paragraph05, 1);

			Paragraph paragraph06 = new Paragraph();
			paragraph06.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph06.add(new Paragraph("Dear sir,"));
			addEmptyLine(paragraph06, 1);

			Paragraph paragraph07 = new Paragraph();
			paragraph07.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph07.add(new Paragraph(
					"We thank you for your enquiry. Further to the same, we are pleased to submit herewith our techno-commercial offer for your kind perusal."));
			addEmptyLine(paragraph07, 1);

			Paragraph paragraph08 = new Paragraph();
			paragraph08.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph08.add(new Paragraph(
					"We hope you will find the same in line with your requirements. However, should there be any clarifications, please feel free to get in touch with us. "));
			addEmptyLine(paragraph08, 1);
			Paragraph paragraph09 = new Paragraph();
			paragraph09.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph09.add(new Paragraph("Thanking you and assuring you of our best attention at all times."));
			addEmptyLine(paragraph09, 1);
			Paragraph paragraph10 = new Paragraph();
			paragraph10.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph10.add(new Paragraph("Yours truly,"));

			Paragraph paragraph100 = new Paragraph();
			paragraph100.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			paragraph100.add(new Paragraph("for TRIVENI TURBINE LTD."));
			addEmptyLine(paragraph100, 14);

			Paragraph paragraph101 = new Paragraph();

			paragraph101.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			paragraph101.setAlignment(Paragraph.ALIGN_CENTER);
			paragraph101.add(new Paragraph(" TRIVENI TURBINE LIMITED."));
			// addEmptyLine(paragraph100, 23);

			Paragraph paragraph102 = new Paragraph();
			paragraph102.setFont(PdfStylAttributes.NORMAL_FONT1_SML);
			paragraph102.setAlignment(Paragraph.ALIGN_CENTER);
			paragraph102.add(new Paragraph(
					" 12-A, Peenya Industrial Area, Bangalore 560 058, India. Tel: +91-80-2216 4000 Fax: +91-80-2216 4100 Website: www.triveniturbines.com"));

			Paragraph paragraph103 = new Paragraph();
			paragraph103.setFont(PdfStylAttributes.NORMAL_FONT1_SML);
			paragraph103.setAlignment(Paragraph.ALIGN_CENTER);
			paragraph103.add(
					new Paragraph(" Regd. Off: A-44, Hosiery Complex, Phase-II Extn., NOIDA - 201 305, Uttar Pradesh"));

			Paragraph paragraph104 = new Paragraph();
			paragraph104.setFont(PdfStylAttributes.NORMAL_FONT1_SML);
			paragraph104.setAlignment(Paragraph.ALIGN_CENTER);
			paragraph104.add(new Paragraph("CIN: L29110UP1995PLC041834"));

			Paragraph paragraph11 = new Paragraph();
			addEmptyLine(paragraph11, 1);
			paragraph11.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph11.add(new Paragraph("Dear Customer, "));
			addEmptyLine(paragraph11, 1);

			// TRIVENITURBINE
			// 12-A, Peenya Industrial Area, Bangalore 560 058, India. Tel:
			// +91-80-2216 4000 Fax: +91-80-2216 4100 Website:
			// www.triveniturbines.com
			// Regd. Off: A-44, Hosiery Complex, Phase-II Extn., NOIDA - 201
			// 305, Uttar Pradesh
			// CIN: L29110UP1995PLC041834

			Paragraph paragraph12 = new Paragraph();
			paragraph12.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph12.add(new Paragraph(
					"Triveni Turbines is an AS 9100D (Based on and including ISO 9001:2015 and ISO 14001:2015 And also OHSAS 18001:2007 certified company.  As a commitment to environment we have laid out our environment policy which will be practiced during our operations, manufacturing and other areas of operation.   "));
			addEmptyLine(paragraph12, 1);
			Paragraph paragraph13 = new Paragraph();
			paragraph13.setFont(PdfStylAttributes.NORMAL_FONT1);

			paragraph13.add(
					new Paragraph("All our products will confine to broad guidelines laid out by our EOHS policy. "));
			addEmptyLine(paragraph13, 1);
			Paragraph paragraph14 = new Paragraph();
			paragraph14.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph14.add(new Paragraph("For TRIVENI TURBINE LTD., "));
			addEmptyLine(paragraph14, 1);
			Paragraph paragraph15 = new Paragraph();
			paragraph15.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph15.add(new Paragraph("Authorized Signatory "));
			addEmptyLine(paragraph15, 1);
			Paragraph paragraph16 = new Paragraph();
			paragraph16.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			paragraph16.setAlignment(Paragraph.ALIGN_CENTER);
			paragraph16.add(new Paragraph("ENVIRONMENT AND OCCUPATION HEALTH & SAFETY (EOHS) POLICY  "));
			addEmptyLine(paragraph16, 1);
			Paragraph paragraph17 = new Paragraph();
			paragraph17.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph17.add(new Paragraph(
					"We at Triveni Turbine Limited manufacturing Steam Turbines and related products including the accessories are committed to:"));
			addEmptyLine(paragraph17, 1);
			Paragraph paragraph18 = new Paragraph();
			paragraph18.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph18.add(new Paragraph(
					"�	Produce the product and provide services to enhance the Customer satisfaction by ensuring prevention of pollution, reduction of waste, conservation of resources and creating safe working environment by ensuring prevention of injury and ill health"));
			addEmptyLine(paragraph18, 1);
			Paragraph paragraph19 = new Paragraph();
			paragraph19.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph19.add(new Paragraph(
					"�	Comply will all applicable legal and other requirements with relation to EOHS."));
			addEmptyLine(paragraph19, 1);
			Paragraph paragraph20 = new Paragraph();
			paragraph20.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph20.add(new Paragraph(
					"�	Establish appropriate objectives in respect of EOHS to facilate the continual improvement."));
			addEmptyLine(paragraph20, 1);
			Paragraph paragraph21 = new Paragraph();
			paragraph21.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph21.add(new Paragraph(
					"�	Communicate the policy effectively to all persons working under the control of the organization including relevant interested parties such as Sub contractors, Suppliers, Transporters and Customers."));
			addEmptyLine(paragraph21, 1);
			Paragraph paragraph22 = new Paragraph();
			paragraph22.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph22.add(new Paragraph(
					"�	Persons working on behalf of the organization shall be provided with necessary competency in respect of EOHS with the intent that they are made aware of their individual obligations."));
			addEmptyLine(paragraph22, 4);
			Paragraph paragraph23 = new Paragraph();
			paragraph23.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph23.add(new Paragraph("EXECUTIVE DIRECTOR"));
			addEmptyLine(paragraph23, 10);
			Paragraph paragraph24 = new Paragraph();
			paragraph24.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);

			paragraph24.add(new Paragraph("Why Triveni "));
			addEmptyLine(paragraph24, 1);
			Paragraph paragraph25 = new Paragraph();
			paragraph25.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph25.add(new Paragraph(
					"*	Most preferred supplier, with a market share of over 60% in India in the range up to 30MW"));
			addEmptyLine(paragraph25, 1);
			Paragraph paragraph26 = new Paragraph();
			paragraph26.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph26.add(new Paragraph(
					"*	Over 4000 installations across the globe in over 70 countries in all industry segments"));
			addEmptyLine(paragraph26, 1);
			Paragraph paragraph27 = new Paragraph();
			paragraph27.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph27.add(new Paragraph(
					"*	Over 500 turbines in process co-generation plants and nearly 100 turbines in biomass based IPPs"));
			addEmptyLine(paragraph27, 1);
			Paragraph paragraph28 = new Paragraph();
			paragraph28.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph28.add(new Paragraph(
					"*	Industry Bench Mark Delivery time. Helps our customers commission their power plants faster"));
			addEmptyLine(paragraph28, 1);
			Paragraph paragraph29 = new Paragraph();
			paragraph29.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph29.add(new Paragraph(
					"	* Best in class manufacturing plant in Bangalore with fleet of 4 Axis / 5 Axis machine. Manufacturing capacity of 200 turbines per year"));
			addEmptyLine(paragraph29, 1);
			Paragraph paragraph30 = new Paragraph();
			paragraph30.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph30.add(new Paragraph(
					"* Every turbine is put through a Mechanical Steam Run Test (at factory, before dispatch) at Full speed to test vibration, safety trip etc."));
			addEmptyLine(paragraph30, 1);
			Paragraph paragraph31 = new Paragraph();
			paragraph31.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph31.add(new Paragraph(
					"*	SCHENK High Speed Vacuum Tunnel balancing for precise dynamic balancing of large rotors. Ensures operation reliability"));
			addEmptyLine(paragraph31, 1);
			Paragraph paragraph32 = new Paragraph();
			paragraph32.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph32.add(new Paragraph(
					"*	24 x 7 Service centers closer to our customers - 13 service offices across India with over 150 service personnel. We respond within 8 hours of an emergency call"));
			addEmptyLine(paragraph32, 1);
			Paragraph paragraph33 = new Paragraph();
			paragraph33.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph33.add(new Paragraph(
					"* Life time support for spares and services to maximize the return on investment. "));
			addEmptyLine(paragraph33, 1);
			Paragraph paragraph34 = new Paragraph();
			paragraph34.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph34.add(new Paragraph(
					"*	Refurbishing solutions offered up to 150 MW for any make of turbine to enhance efficiency, availability and reliability."));
			addEmptyLine(paragraph34, 1);
			Paragraph paragraph35 = new Paragraph();
			paragraph35.setFont(PdfStylAttributes.NORMAL_FONT1);
			paragraph35.add(new Paragraph(
					"*A decade old Research and Development department for continuous improvement of existing models and development of new models. Association with world-renowned designhouses and academia - IISc., Cambridge,Polimi, Impact Tech. (Lockheed Martin),Concepts NREC, USA."));
			addEmptyLine(paragraph35, 12);
			// Print table of contents
			document.newPage();
			int i = 0;
			int chapter = 0;
			String chapterindent = "";
			String prevcategoryname = null;

			Paragraph paragraph = new Paragraph();
			Paragraph paragraph1 = new Paragraph();

			paragraph.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			paragraph.setAlignment(Paragraph.ALIGN_CENTER);
			paragraph.add(new Paragraph("INDEX"));
			addEmptyLine(paragraph1, 1);
			for (i = 0; i < reportBean.getWordData().size(); i++) {

				if (i == QuotationDaoImpl.size)
					break;

				String categoryname = reportBean.getWordData().get(i).getCategoryName();

				String subcategorytemp = reportBean.getWordData().get(i).getSubCategoryName();

				if (!categoryname.equals(prevcategoryname)) {

					chapter = chapter + 1;
					chapterindent = chapter + ".";

					paragraph1.setFont(PdfStylAttributes.NORMAL_FONT1);
					paragraph1.add(new Paragraph(chapterindent + "				" + categoryname));
					addEmptyLine(paragraph1, 1);
				}

				if (subcategorytemp != null) // Compare two strings
				{

					paragraph1.setFont(PdfStylAttributes.NORMAL_FONT1);
					paragraph1.add(new Paragraph("								" + subcategorytemp));
					addEmptyLine(paragraph1, 1);
				}

				prevcategoryname = categoryname;
			}

			// // To print scope of supply(chapter 1)
			// to print category name

			Paragraph paragraph2 = new Paragraph();
			// addEmptyLine(paragraph2, 12);
			paragraph2.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			paragraph2.add(new Paragraph("1					Scope Of Supply"));
			addEmptyLine(paragraph2, 1);
			String prevcategoryname1 = null;
			String prevsubcategoryname = null;
			String previtemtemp = null;
			int k = 0;

			for (k = i; k < reportBean.getWordData().size(); k++) {

				if (k == QuotationDaoImpl.size1)
					break;

				String categoryname = reportBean.getWordData().get(k).getCategoryName();
				String subcategoryname = reportBean.getWordData().get(k).getSubCategoryName();
				String itemtemp = reportBean.getWordData().get(k).getItemName();
				String subCatName = reportBean.getWordData().get(k).getSubCatName();

				// to print subcategory name

				if (!subcategoryname.equals(prevsubcategoryname)) {

					paragraph2.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
					paragraph2.add(new Paragraph("										" + subcategoryname));
					addEmptyLine(paragraph2, 1);
				}

				// to print Item name

				if (!itemtemp.equals(previtemtemp)) {

					paragraph2.setFont(PdfStylAttributes.NORMAL_FONT1);
					paragraph2.add(new Paragraph("										" + itemtemp));
					addEmptyLine(paragraph2, 1);
				}

				if (itemtemp.equals("										" + "A.9  Mechanical Auxiliaries")) {

					paragraph2.setFont(PdfStylAttributes.NORMAL_FONT1);
					paragraph2.add(new Paragraph(subCatName));
					addEmptyLine(paragraph2, 1);
				}

				if (itemtemp.equals("										" + "A.10  Mechanical Extended Scope")) {

					paragraph2.setFont(PdfStylAttributes.NORMAL_FONT1);
					paragraph2.add(new Paragraph(subCatName));
					addEmptyLine(paragraph2, 1);
				}

				// prevcategoryname1 = categoryname;
				prevsubcategoryname = subcategoryname;
				previtemtemp = itemtemp;

			}

			// f2f mech start
			int j = 0;
			String prevcategoryname2 = null;
			String prevsubcategoryname1 = null;
			String previtemname = null;
			String prevsubitemname = null;
			String prevsubitemtypename = null;
			String previtemcode = null;
			String prevtechremarks = null;
			String prevfixedtext1 = null;
			String prevfixedtext5 = null;

			Paragraph preface123 = new Paragraph();
			Paragraph preface1234 = new Paragraph();
			int countertemp = 0;
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
				String VarianttypeName = null;
				int numberOfRows = 0;

				int iData = 0;
				// get row
				XWPFTableRow tableRowOne1 = null;

				if (j == QuotationDaoImpl.size2)
					break;

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
				colValCd = reportBean.getWordData().get(j).getColValCd();
				numberOfRows = reportBean.getWordData().get(j).getNumberOfRows();

				// This is to print the technical remarks at the end of the item
				if (!itemcode.equals(previtemcode) && itemcode != null) {

					table = new PdfPTable(3);

					if (techremarks != null) {
						if (!techremarks.equals(prevtechremarks)) {
							addEmptyLine(preface123, 1);
							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph(prevtechremarks));

						} // if (techremarks != null)
					} // if (techremarks != null)
					if (prevfixedtext5 != null) {
						// This is to print safety devices for steam turbine
						// model
						// only
						if (previtemcode.equals("STEM_MD")) {

							preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
							preface123.add(new Paragraph(" A.1.1 Safety Devices "));
							addEmptyLine(preface123, 1);

							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph(
									"-			Over speed trip: Eccentric spring loaded oil dumpvalve"));

							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph(
									"-			Low lube oil pressure trip: Through pressure switch and solenoid trip"));

							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph(
									"-			High thrust wear & vibration: Through vibration monitoring system "));

							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph(
									"-			High exhaust pressure trip: Through pressure switch and solenoid trip"));

							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph("-			Hand trip : Block & dump valve in trip oil line"));

							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph("			  Operation: Local hand lever "));

							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(
									new Paragraph("-			Solenoid trip: Solenoid operated block & dump valve "));

							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph("			  Operation: De-energise to open & trip. "));

							iData = 1; // To know that safety devices is printed
						}
					} // if (prevfixedtext5 != null)

				} // if (!itemcode.equals(previtemcode) && itemcode != null)

				String itemtemp = null;
				String subitemname = null;
				String subitemtypename = null;

				itemtemp = reportBean.getWordData().get(j).getItemName();
				subitemname = reportBean.getWordData().get(j).getSubItemName();
				subitemtypename = reportBean.getWordData().get(j).getSubItemTypeName();
				colName = reportBean.getWordData().get(j).getColNm();

				// to print category name
				if (!categoryname.equals(prevcategoryname2)) {

					addEmptyLine(preface123, 1);
					preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
					preface123.add(new Paragraph((categoryname)));

				}

				// to print subcategory name
				if (!subcategoryname.equals(prevsubcategoryname1)) {

					addEmptyLine(preface123, 1);
					preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
					preface123.add(new Paragraph(subcategoryname));

				}

				// to print item name only once
				if (!itemtemp.equals(previtemname)) {

					addEmptyLine(preface123, 1);
					preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
					preface123.add(new Paragraph(reportBean.getWordData().get(j).getItemName()));

					// To print the static text of gland sealing system
					if (itemcode.equals("GLAND_SYS")) {
						preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
						preface123.add(new Paragraph(colValCd + "		" + FixedText1));

					}

					// To print the static text of steam turbines back pressure
					if (TurbineCode != null && TurbineCode.equals("BP")) {
						if ((FixedText1 != null) && ((itemcode.equals("STEM_MD")) && (!itemcode.equals("GLAND_SYS")))) {
							if (!FixedText1.equals(prevfixedtext1)) {

								if (!FixedText1.equals("NULL") && (FixedText1 != null))
									preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
								preface123.add(new Paragraph(FixedText1));

								if (!FixedText2.equals("NULL") && (FixedText2 != null)) {
									preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
									preface123.add(new Paragraph(FixedText2));

								}

								if (!FixedText3.equals("NULL") && (FixedText3 != null)) {
									preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
									preface123.add(new Paragraph(FixedText3));

								}
								if (!VarianttypeName.equals("NULL") && (VarianttypeName != null)) {
									preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
									preface123.add(new Paragraph(VarianttypeName));

								}
								if (!FixedText4.equals("NULL") && (FixedText4 != null)) {
									preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
									preface123.add(new Paragraph(FixedText4));

								}
								if (!FixedText5.equals("NULL") && (FixedText5 != null)) {
									if (!itemcode.equals("STEM_MD")) {
										preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
										preface123.add(new Paragraph(FixedText5));
									}

								}

							}
						}
					}
					if (TurbineCode != null && TurbineCode.equals("CD")) {
						if ((FixedText1 != null) && ((itemcode.equals("STEM_MD")) && (!itemcode.equals("GLAND_SYS")))) {
							if (!FixedText1.equals(prevfixedtext1)) {

								if (!FixedText1.equals("NULL") && (FixedText1 != null))
									preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
								preface123.add(new Paragraph(FixedText1));

								if (!FixedText2.equals("NULL") && (FixedText2 != null)) {
									preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
									preface123.add(new Paragraph(FixedText2));

								}

								if (!FixedText3.equals("NULL") && (FixedText3 != null)) {
									preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
									preface123.add(new Paragraph(FixedText3));

								}
								if (!VarianttypeName.equals("NULL") && (VarianttypeName != null)) {
									preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
									preface123.add(new Paragraph(VarianttypeName));

								}
								if (!FixedText4.equals("NULL") && (FixedText4 != null)) {
									preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
									preface123.add(new Paragraph(FixedText4));

								}
								if (!FixedText5.equals("NULL") && (FixedText5 != null)) {
									if (!itemcode.equals("STEM_MD")) {
										preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
										preface123.add(new Paragraph(FixedText5));
									}

								}

							}
						}
					}

					// To print the static text of lubeoil control system

					if (Category != null && (Category.equals("A") || Category.equals("B") || Category.equals("C")
							|| Category.equals("D"))) {
						if ((FixedText1 != null) && ((itemcode.equals("LUB_OIL")) && (!itemcode.equals("GLAND_SYS")))) {
							if (!FixedText1.equals(prevfixedtext1)) {

								if (!FixedText1.equals("NULL") && FixedText1 != null)
									preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
								preface123.add(new Paragraph(FixedText1));

								// if (!FixedText2.equals("NULL") && FixedText2
								// != null)
								// preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
								// preface123.add(new Paragraph(FixedText2));

								if (!FixedText3.equals("NULL") && (FixedText3 != null)) {
									preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
									preface123.add(new Paragraph(FixedText3));

								}

								if (!FixedText4.equals("NULL") && (FixedText4 != null))
									preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
								preface123.add(new Paragraph(FixedText4));

								if (!FixedText5.equals("NULL") && (FixedText5 != null)) {
									if (!itemcode.equals("STEM_MD") && !itemcode.equals("GLAND_SYS"))

										if (subitemname == null && subitemtypename == null && colName == null) {
											if (colValCd != null) {

												preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
												preface123.add(new Paragraph(colValCd));

											}

										}
									preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
									preface123.add(new Paragraph(FixedText5));

									preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
									preface123.add(new Paragraph("-			Main Oil Pump (MOP)"));

									preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
									preface123.add(new Paragraph(
											"-			A.C. Motor driven auxiliary oil pump (AOP) for flooding the bearings during start-up and wired through a pressure switch forautomatic cut-in and cut-out in the event of main oil pump not supplying required quantity of oil."));

									preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
									preface123.add(new Paragraph(
											"-			AC Motor driven two Auxiliary Control Oil Pump (1 working + 1 stand-by) to supply oil to Control system."));

									preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
									preface123.add(new Paragraph(
											"-			D. C. Motor driven Emergency lube Oil Pump (EOP)with auto cut-in & cut-out facility "));

									preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
									preface123.add(new Paragraph("-			Suction strainers for MOP, AOP & EOP"));

									preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
									preface123.add(new Paragraph(
											"-			Pressure relief valves for discharge of MOP, AOP,ACOP and lube oil line "));

									preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
									preface123.add(new Paragraph(
											"-			Oil coolers (1 working + 1 standby) with on-linechangeover arrangement "));

									preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
									preface123.add(new Paragraph(
											"-			Oil filters (1 working + 1 standby) with on-linechange-over arrangement"));

									preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
									preface123.add(new Paragraph(
											"-			AC motor driven oil vapour extractor mounted on oil tank"));

									// preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
									// preface123.add(new Paragraph("- Pressure
									// regulating valve to achieve desired
									// lubeoil pressure"));

									preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
									preface123.add(new Paragraph(
											"-			Set of lube oil piping within the limits of theturbine base frame and sufficient to meet the requirement of the	ordered equipment. "));
									addEmptyLine(preface123, 1);
								}

							}
						}

					}

					if (itemcode.equals("COND_SYST")) {

						preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
						preface123.add(new Paragraph("Condensing system comprising of:"));

					}

				}
				if (!itemcode.equals(previtemcode) && itemcode != null) {
					preface123.add(table);

				}

				// To print the sub item name only once
				if (subitemname != null) {
					if (!subitemname.equals(prevsubitemname)) {

						table = new PdfPTable(3);

						preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
						preface123.add(new Paragraph(reportBean.getWordData().get(j).getSubItemName()));

						boolean isFound = false;
						isFound = subitemname.contains("Bearings");
						if (isFound == false) {

						}

						if (subitemname.equals("ACCESSORIES")) {

							addEmptyLine(preface123, 1);
							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph("-			 Rupture disc for condenser protection."));
							preface123.add(
									new Paragraph("-			 Dry air/vapor line within specified battery limit."));

						}

					}

					// To print the lhs and rhs of sub item
					if (subitemname != null && subitemtypename == null && !subitemname.equals("ACCESSORIES")) {
						String colunmname = reportBean.getWordData().get(j).getColNm();

						String colValCode = reportBean.getWordData().get(j).getColValCd();

						if (colunmname != null && colValCode != null) {

							addToTableValuePdf(table, colunmname);
							addToTableValueBoldPdf(table, ":");
							addToTableValuePdf(table, colValCode);
							// addToTableValue(table, "");

						}
						if (subitemname != null) {
							if (!subitemname.equals(prevsubitemname)) {

								preface123.add(table);
							}
						}
						// To print technical remarks of the sub itemq

						if (j + 1 == reportBean.getWordData().size()) {

							addEmptyLine(preface123, 1);
							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph(techremarks));

						}
					}
				} // if (subitemname != null)

				if (subitemtypename != null)
					if (!subitemtypename.equals(prevsubitemtypename)) {

						table = new PdfPTable(3);
						addEmptyLine(preface123, 1);
						preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
						preface123.add(new Paragraph(reportBean.getWordData().get(j).getSubItemTypeName()));

					}

				if (subitemtypename != null)// Compare two strings
				{
					String colunmname = reportBean.getWordData().get(j).getColNm();

					String colValCode = reportBean.getWordData().get(j).getColValCd();

					// We should not print if lhs is null
					if (colunmname != null && colValCode != null) {

						addToTableValuePdf(table, colunmname);
						addToTableValueBoldPdf(table, ":");
						addToTableValuePdf(table, colValCode);
						// addToTableValue(table, "");

					}
					if (subitemtypename != null)
						if (!subitemtypename.equals(prevsubitemtypename)) {

							preface123.add(table);

						}
					// To print the technical remark of sub item type

					if (j + 1 == reportBean.getWordData().size()) {

						addEmptyLine(preface123, 1);
						preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
						preface123.add(new Paragraph(techremarks));

					}

				}
				// This is to check that only item lhs and rhs should print of
				// item
				if (itemtemp != null && subitemname == null) // Compare two
																// strings
				{
					String colunmname = reportBean.getWordData().get(j).getColNm();

					String colValCode = reportBean.getWordData().get(j).getColValCd();

					// We should not print if lhs is null
					if (colunmname != null && colValCode != null) {

						addToTableValuePdf(table, colunmname);
						addToTableValueBoldPdf(table, ":");
						addToTableValuePdf(table, colValCode);
						// addToTableValue(table, "");

					}

					// This is to print the technical remarks of item at the end
					// after
					// lhs and rhs

					if (j + 1 == reportBean.getWordData().size()) {

						addEmptyLine(preface123, 1);
						preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
						preface123.add(new Paragraph(techremarks));

					}

				}

				prevcategoryname2 = categoryname;
				prevsubcategoryname1 = subcategoryname;
				previtemname = itemtemp;
				prevsubitemname = subitemname;
				prevsubitemtypename = subitemtypename;
				previtemcode = itemcode;
				prevtechremarks = techremarks;
				prevfixedtext1 = FixedText1;
				prevfixedtext5 = FixedText5;
			}
			// TESTAUXSTART
			int m = 0;
			String prevcategoryaux = null;
			String prevsubcategoryaux = null;
			String previtemnameaux = null;
			String prevsubitemnameaux = null;
			String previtemcodeaux = null;
			String prevtechremarksaux = null;

			for (m = j; m < reportBean.getWordData().size(); m++)

			{

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

				// This is to print the technical remarks at the end of the item
				if (!itemcodeaux.equals(previtemcodeaux) && itemcodeaux != null) {
					if (techremarksaux != null) {
						if (!techremarksaux.equals(prevtechremarksaux)) {
							addEmptyLine(preface123, 1);
							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph(prevtechremarksaux));

						} // if (!techremarksaux.equals(prevtechremarksaux))
					} // if (techremarksaux != null)
				} // (!itemcodeaux.equals(previtemcodeaux) && itemcodeaux !=
					// null)

				itemnameaux = reportBean.getWordData().get(m).getItemName();
				subitemcodeaux = reportBean.getWordData().get(m).getSubItemCode();
				// to print item name only once
				if (!itemnameaux.equals(previtemnameaux)) {
					addEmptyLine(preface123, 1);
					preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
					addEmptyLine(preface123, 1);

					preface123.add(new Paragraph("																"
							+ reportBean.getWordData().get(m).getItemName()));

				} // if (!itemnameaux.equals(previtemnameaux))

				// To print the lhs and rhs of sub item
				if (subitemcodeaux != null) // Compare two strings
				{

					String colunmname = reportBean.getWordData().get(m).getColNm();
					String colValCode = reportBean.getWordData().get(m).getColValCd();

					if (colunmname != null && colValCode != null) {

						if (subitemcodeaux.equals("FFRV") && colunmname.equals("FFRV SIZE")) {

							addEmptyLine(preface123, 1);
							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph(colunmname + ":" + colValCode + "," + "Quantity" + ":"
									+ String.valueOf(qunatityaux) + "(Loose Supply)"));

						}

						if (subitemcodeaux.equals("NRV") && colunmname.equals("Size NB")) {
							addEmptyLine(preface123, 1);
							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph(colunmname + ":" + colValCode + "," + "Quantity" + ":"
									+ String.valueOf(qunatityaux) + "(Loose Supply)"));

						}
						if (subitemcodeaux.equals("QCNRV") && colunmname.equals("Size")) {
							addEmptyLine(preface123, 1);
							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph(colunmname + ":" + colValCode + "," + "Quantity" + ":"
									+ String.valueOf(qunatityaux) + "(Loose Supply)"));

						}

						if (subitemcodeaux.equals("ISV") && colunmname.equals("Size NB")) {

							addEmptyLine(preface123, 1);
							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph(colunmname + ":" + colValCode + "," + "Quantity" + ":"
									+ String.valueOf(qunatityaux) + "(Loose Supply)"));

						}

						if (subitemcodeaux.equals("OIL_CENTRIFUGE") && colunmname.equals("Capacity")) {
							addEmptyLine(preface123, 1);
							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph(colunmname));

						}

						if (subitemcodeaux.equals("OV_TANK") && colunmname.equals("Capacity")) {
							addEmptyLine(preface123, 1);
							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph(colunmname));

						}
						if (subitemcodeaux.equals("FLOW_NOZZLE") && colunmname.equals("Size")) {
							addEmptyLine(preface123, 1);
							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph(colunmname + ":" + colValCode + "," + "Quantity" + ":"
									+ String.valueOf(qunatityaux)));

						}
						if (subitemcodeaux.equals("FLOW_ORIFICE") && colunmname.equals("Size")) {
							addEmptyLine(preface123, 1);
							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph(colunmname + ":" + colValCode + "," + "Quantity" + ":"
									+ String.valueOf(qunatityaux) + "(Loose Supply)"));

						}

						if (subitemcodeaux.equals("MET_EXP_BEL") && colunmname.equals("Size")) {
							addEmptyLine(preface123, 1);
							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph(colunmname));

						}

						if (subitemcodeaux.equals("ATR_VAL")) {
							addEmptyLine(preface123, 1);
							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph(subitemcodeaux));

						}
						if (subitemcodeaux.equals("VACC_BVL")) {
							addEmptyLine(preface123, 1);
							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph(subitemcodeaux));

						}

						if (subitemcodeaux.equals("FILL_FLU")) {

							addEmptyLine(preface123, 1);
							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph(colunmname + ":" + colValCode));

						}

						// To print technical remarks of the sub itemq

						if (m + 1 == reportBean.getWordData().size()) {
							// paragraph = document.createParagraph();
							// run = paragraph.createRun();
							// run.setText(techremarksaux);
							// run.addBreak();
							// run.addBreak();
							// run.setFontFamily("Bookman Old Style");
							// run.setFontSize(10);
							addEmptyLine(preface123, 1);
							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph(techremarksaux));
						} // if (m + 1 == reportBean.getWordData().size())

					} // if (subitemnameaux != null )

				}
				previtemnameaux = itemnameaux;
				prevsubitemnameaux = subitemnameaux;
				previtemcodeaux = itemcodeaux;
				prevtechremarksaux = techremarksaux;

			} // for loop

			// //TESTAUXEND
			// //MSS
			// // To print the technical details / technical
			// specifications(chapter
			// // 2)(new result set 10)
			//
			int n = 0;
			String prevcategoryextscp = null;
			String prevsubcategoryextscp = null;
			String previtemnameextscp = null;
			String prevsubitemnameextscp = null;
			String previtemcodeextscp = null;
			String prevtechremarksextscp = null;

			for (n = m; n < reportBean.getWordData().size(); n++)

			{

				String categorynameextscp = null;
				String subcategorynameextscp = null;
				int itemidextscp = 0;
				String itemnameextscp = null;
				String itemcodeextscp = null;
				String subitemcodeextscp = null;
				String subitemnameextscp = null;

				int qunatityextscp = 0;
				String techremarksextscp = null;
				String techcommentsextscp = null;
				int numberofsubitemsextscp = 0;

				if (n == QuotationDaoImpl.size4)
					break;

				categorynameextscp = reportBean.getWordData().get(n).getCategoryName();
				subcategorynameextscp = reportBean.getWordData().get(n).getSubCategoryName();
				itemidextscp = reportBean.getWordData().get(n).getItemId();
				itemnameextscp = reportBean.getWordData().get(n).getItemName();
				itemcodeextscp = reportBean.getWordData().get(n).getItemCode();
				subitemcodeextscp = reportBean.getWordData().get(n).getSubItemCode();
				subitemnameextscp = reportBean.getWordData().get(n).getSubItemName();

				qunatityextscp = reportBean.getWordData().get(n).getQuantityAux();
				techremarksextscp = reportBean.getWordData().get(n).getTechRemarks();
				techcommentsextscp = reportBean.getWordData().get(n).getTechComments();
				numberofsubitemsextscp = reportBean.getWordData().get(n).getNumberOfSubItems();

				// This is to print the technical remarks at the end of the item
				if (!itemcodeextscp.equals(previtemcodeextscp) && itemcodeextscp != null) {
					if (techremarksextscp != null) {
						if (!techremarksextscp.equals(prevtechremarksextscp)) {
							// paragraph = document.createParagraph();
							// run = paragraph.createRun();
							// run.setText(prevtechremarksextscp);
							// run.addBreak();
							// run.addBreak();
							// run.setFontFamily("Bookman Old Style");
							// run.setFontSize(10);
							addEmptyLine(preface123, 1);
							preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
							preface123.add(new Paragraph(prevtechremarksextscp));
						} // if
							// (!techremarkextscp.equals(prevtechremarksextscp))
					} // if (techremarksextscp != null)
				} // (!itemcodeextscp.equals(previtemcodeextscp) &&
					// itemcodeextscp
					// != null)

				itemnameextscp = reportBean.getWordData().get(n).getItemName();
				subitemnameextscp = reportBean.getWordData().get(n).getSubItemName();

				// to print item name only once
				if (!itemnameextscp.equals(previtemnameextscp)) {

					addEmptyLine(preface123, 1);
					preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
					preface123.add(new Paragraph(reportBean.getWordData().get(n).getItemName()));

				} // if (!itemnameextscp.equals(previtemnameextscp))

				// To print the sub item name only once
				if (subitemnameextscp != null) {
					if (!subitemnameextscp.equals(prevsubitemnameextscp)) {

						addEmptyLine(preface123, 1);
						preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
						preface123.add(new Paragraph(reportBean.getWordData().get(n).getSubItemName() + ":"));

					} // if (!subitemnameextscp.equals(prevsubitemnameextscp))

					// To print the lhs and rhs of sub item
					if (subitemnameextscp != null) // Compare two strings
					{
						String colunmname = reportBean.getWordData().get(n).getColNm();
						String colValCode = reportBean.getWordData().get(n).getColValCd();

						if (colunmname != null && colValCode != null) {

							addEmptyLine(preface123, 1);
							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph(colunmname + ":" + colValCode));

						}

						// To print technical remarks of the sub itemq

						if (n + 1 == reportBean.getWordData().size()) {

							addEmptyLine(preface123, 1);
							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph(techremarksextscp));

						} // if (n + 1 == reportBean.getWordData().size())

					} // if (subitemnameextscp != null )

				} // if (subitemnameextscp != null)

				previtemnameextscp = itemnameextscp;
				prevsubitemnameextscp = subitemnameextscp;
				previtemcodeextscp = itemcodeextscp;
				prevtechremarksextscp = techremarksextscp;

			} // for loop
				// //MSE
				//
			int x = 0;
			String prevcategoryele = null;
			String prevsubcategoryele = null;
			String previtemnameele = null;
			String prevdesitemnameele = null;
			String prevheadertextele = null;
			String prevfootertextele = null;
			String prevdessubitemnameele = null;
			String previtemcodeele = null;
			String prevtechremarksele = null;
			int counter4 = 0;
			int count = 0;
			for (x = n; x < reportBean.getWordData().size(); x++)

			{

				String categorynameele = null;
				String subcategorynameele = null;
				int itemidele = 0;
				String itemnameele = null;
				String desitemnameele = null;
				String dessubitemnameele = null;
				String itemcodeele = null;
				String subitemcodeele = null;
				String subitemnameele = null;
				String subcolvalcodeele = null;
				int qunatityele = 0;
				String techremarksele = null;
				String techcommentsele = null;
				int numberofsubitemsele = 0;
				int orderidele = 0;
				int dessubitemorderidele = 0;
				String headertextele = null;
				String footertextele = null;

				String descolorderidele = null;

				if (x == QuotationDaoImpl.size5)

					break;

				categorynameele = reportBean.getWordData().get(x).getCategoryName();
				subcategorynameele = reportBean.getWordData().get(x).getSubCategoryName();
				itemidele = reportBean.getWordData().get(x).getItemId();
				itemnameele = reportBean.getWordData().get(x).getItemName();
				desitemnameele = reportBean.getWordData().get(x).getDesItemName();
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
				dessubitemnameele = reportBean.getWordData().get(x).getDesSubItemName();
				subcolvalcodeele = reportBean.getWordData().get(x).getSubColValCode();
				headertextele = reportBean.getWordData().get(x).getHeaderText();
				footertextele = reportBean.getWordData().get(x).getFooterText();

				// This is to print the technical remarks at the end of the item
				if (!itemcodeele.equals(previtemcodeele) && itemcodeele != null) {
					if (techremarksele != null) {
						if (!techremarksele.equals(prevtechremarksele)) {
							addEmptyLine(preface123, 1);
							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
							preface123.add(new Paragraph(prevtechremarksele));

						} // if (!techremarksele.equals(prevtechremarksele))
					} // if (techremarksele != null)
				} // if (!itemcodeele.equals(previtemcodeele) && itemcodeele !=
					// null)

				itemnameele = reportBean.getWordData().get(x).getItemName();
				desitemnameele = reportBean.getWordData().get(x).getDesItemName();

				// to print subcategory name
				if (!subcategorynameele.equals(prevsubcategoryele)) {
					addEmptyLine(preface123, 1);
					preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
					preface123.add(new Paragraph(subcategorynameele));

				}
				int counter = 0;
				// to print item name only once
				if (!itemnameele.equals(previtemnameele)) {
					counter4 = counter4 + 1;
					if (counter4 > 1) {
						if ((footertextele != null) && !footertextele.equals("NULL")) {
							if (!footertextele.equals(prevfootertextele)) {
								addEmptyLine(preface123, 1);
								preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
								preface123.add(new Paragraph(prevfootertextele));
							}
						}
					}

					addEmptyLine(preface123, 1);
					preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
					preface123.add(new Paragraph(itemnameele));

					table = new PdfPTable(3);
					counter = 1;
				} // if (!itemnameele.equals(previtemnameele))

				// To print the item name only once

				if (desitemnameele != null) {

					if (!desitemnameele.equals(prevdesitemnameele)) {
						count = 0;
						addEmptyLine(preface123, 1);
						preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
						preface123.add(new Paragraph(desitemnameele));

						if ((headertextele != null) && !headertextele.equals("NULL")) {
							if (!headertextele.equals(prevheadertextele)) {
								addEmptyLine(preface123, 1);
								preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
								preface123.add(new Paragraph(headertextele));
							}
						}
						table = new PdfPTable(3);

						counter = 1;
						if (desitemnameele.equalsIgnoreCase(
								"Multifunctional digital generator protection relay shall have following protections features")) {
							addToTableValueBoldPdf2(table, "SL.NO", 0);
							addToTableValueBoldPdf2(table, "Category", 1);
							addToTableValueBoldPdf2(table, "Description", 2);
						}

					} // if (!subitemnameele.equals(prevdesitemnameele))
					if (dessubitemnameele != null) {
						if (!dessubitemnameele.equals(prevdessubitemnameele)) {
							addEmptyLine(preface123, 1);
							preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
							preface123.add(new Paragraph(
									dessubitemorderidele + "." + "							" + dessubitemnameele));

							table = new PdfPTable(3);

							counter = 1;
							if (dessubitemnameele.equalsIgnoreCase(
									"Multifunctional digital generator protection relay shall have following protections features")) {
								addToTableValueBoldPdf2(table, "SL.NO", 0);
								addToTableValueBoldPdf2(table, "Category", 1);
								addToTableValueBoldPdf2(table, "Description", 2);
							}

						}
					}
					// To print the lhs and rhs of des item
					String colunmname = reportBean.getWordData().get(x).getColNm();
					String colValCode = reportBean.getWordData().get(x).getColValCd();

					if (desitemnameele != null && dessubitemnameele == null) // Compare
																				// two
																				// strings
					{

						if (colunmname != null && colValCode != null) {
							if (desitemnameele.equalsIgnoreCase(
									"Multifunctional digital generator protection relay shall have following protections features")) {
								counter = counter + 1;
								addToTableValuePdf2(table, descolorderidele, 0);
								addToTableValuePdf2(table, colunmname, 1);
								if (subcolvalcodeele != null && !(subcolvalcodeele.equals("NULL"))) {
									addToTableValuePdf2(table, subcolvalcodeele, 2);
								} else {
									addToTableValuePdf2(table, colValCode, 2);
								}

							} else {
								counter = counter + 1;
								if (!(colunmname.equals("Additional outgoing feeders ( Ampere rating & quantity )"))) {
									addToTableValuePdf(table, descolorderidele + " 					" + colunmname);
									addToTableValueBoldPdf(table, ":");
									if (subcolvalcodeele != null && !(subcolvalcodeele.equals("NULL"))) {
										addToTableValuePdf(table, subcolvalcodeele);
									} else {
										addToTableValuePdf(table, colValCode);
										if (colValCode.contains("\n")) {
											logger.info(colValCode);
										}
									}
								}

								if (colunmname.equals("Additional outgoing feeders ( Ampere rating & quantity )")
										&& count == 0) {
									count = 1;

									addToTableValuePdf(table, descolorderidele + " 					" + colunmname);
									addToTableValueBoldPdf(table, ":");
									if (subcolvalcodeele != null && !(subcolvalcodeele.equals("NULL"))) {
										addToTableValuePdf(table, subcolvalcodeele);
									} else {
										addToTableValuePdf(table, colValCode + "*" + qunatityele);
									}
								}

								else if (colunmname.equals("Additional outgoing feeders ( Ampere rating & quantity )")
										&& count != 0) {
									addToTableValuePdf(table, "");
									addToTableValueBoldPdf(table, "");
									if (subcolvalcodeele != null && !(subcolvalcodeele.equals("NULL"))) {
										addToTableValuePdf(table, subcolvalcodeele);
									} else {
										addToTableValuePdf(table, colValCode + "*" + qunatityele);
									}

								}
							}

							// addToTableValue(table, "");

						}
						if (desitemnameele != null && dessubitemnameele == null && counter == 2) // Compare
																									// two
																									// strings
						{
							preface123.add(table);
						}
					} else {

						if (desitemnameele != null) {
							if (dessubitemnameele.equalsIgnoreCase(
									"Multifunctional digital generator protection relay shall have following protections features")) {
								counter = counter + 1;

								addToTableValuePdf2(table, descolorderidele, 0);
								addToTableValuePdf2(table, colunmname, 1);
								if (subcolvalcodeele != null && !(subcolvalcodeele.equals("NULL"))) {
									addToTableValuePdf2(table, subcolvalcodeele, 2);
								} else {
									addToTableValuePdf2(table, colValCode, 2);
								}

							} else

							{
								counter = counter + 1;

								addToTableValuePdf(table, descolorderidele + " 					" + colunmname);
								addToTableValueBoldPdf(table, ":");
								if (subcolvalcodeele != null && !(subcolvalcodeele.equals("NULL"))) {
									addToTableValuePdf(table, subcolvalcodeele);
								} else {
									addToTableValuePdf(table, colValCode);

									logger.info("rhs start");
									logger.info(colValCode);
									logger.info("rhs end");

								}
							}

							// addToTableValue(table, "");
						}
						if (desitemnameele != null && dessubitemnameele != null && counter == 2) {
							preface123.add(table);
						}

					}

					// To print technical remarks of the sub itemq

					if (x + 1 == reportBean.getWordData().size()) {
						addEmptyLine(preface123, 1);
						preface123.setFont(PdfStylAttributes.NORMAL_FONT1);

						preface123.add(new Paragraph(techremarksele));

					} // if (x + 1 == reportBean.getWordData().size())

				} // if (desitemnameele != null)

				previtemnameele = itemnameele;
				prevdesitemnameele = desitemnameele;
				prevheadertextele = headertextele;
				prevfootertextele = footertextele;
				previtemcodeele = itemcodeele;
				prevtechremarksele = techremarksele;
				prevsubcategoryele = subcategorynameele;
				prevdessubitemnameele = dessubitemnameele;

				if (x + 1 == reportBean.getWordData().size()) {
					if ((footertextele != null) && !footertextele.equals("NULL")) {
						if (!footertextele.equals(prevfootertextele)) {
							addEmptyLine(preface123, 1);
							preface123.setFont(PdfStylAttributes.NORMAL_FONT1);

							preface123.add(new Paragraph(footertextele));
						}
					}
				}

			} // for loop

			// ELESTART

			// ELEEND

			//// vms start
			// int q= 0;
			//
			// String prevvmsnm = null;
			// String prevheadertextvms = null;
			// int prevaddprbflag = 2;
			// PdfPRow tableRowOne1vms = null;
			// int countervms=0;
			//
			// for (q=x;q<reportBean.getWordData().size();q++)
			//
			// {
			// logger.info("inside vms");
			// countervms=countervms+1;
			// int vmsitemid = 0;
			// String vmsnm = null;
			// String type = null;
			// int addprbflag = 0;
			// int orderby = 0;
			// String tagnm = null;
			// String equipment = null;
			// String application = null;
			// String location = null;
			// int quant = 0;
			// float cost = 0;
			// int newcolvalflag = 0;
			// float itemcost = 0;
			// String headertext = null;
			// String note = null;
			//
			//
			// if (q == QuotationDaoImpl.size7)
			// break;
			//
			//
			//
			// vmsitemid = reportBean.getWordData().get(q).getItemId();
			// vmsnm = reportBean.getWordData().get(q).getItemName();
			// type = reportBean.getWordData().get(q).getType();
			// addprbflag = reportBean.getWordData().get(q).getAddPrbFlag();
			// orderby = reportBean.getWordData().get(q).getOrderBy();
			// tagnm = reportBean.getWordData().get(q).getTagNum();
			// equipment = reportBean.getWordData().get(q).getEquipment();
			// application = reportBean.getWordData().get(q).getApplication();
			// location = reportBean.getWordData().get(q).getLocation();
			// quant = reportBean.getWordData().get(q).getQuantity();
			// cost = reportBean.getWordData().get(q).getCost2();
			// newcolvalflag =
			//// reportBean.getWordData().get(q).getNewColValFlag();
			// itemcost = reportBean.getWordData().get(q).getItemCost();
			// headertext = reportBean.getWordData().get(q).getHeaderText();
			// note = reportBean.getWordData().get(q).getNote();
			//
			// if (addprbflag!=prevaddprbflag) {
			//
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
			// preface123.add(new Paragraph(""));
			//
			//
			//
			// if(addprbflag==1)
			// {
			// preface123.add(new Paragraph("Additional Probes"));
			//
			// }
			//
			// else
			// {
			// preface123.add(new Paragraph(type));
			// }
			//
			//
			// if (headertext != null)
			// {
			// if (!headertext.equals(prevheadertextvms))
			// {
			//
			//
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
			// preface123.add(new
			//// Paragraph(reportBean.getWordData().get(q).getHeaderText()));
			//
			// }
			// }
			//
			//
			// tableRowOne1vms = tablevms.getRow(0);
			//
			//
			// }
			//
			//
			// //to do
			// tablevms.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			// tablevms.addCell("SL.NO.");
			// tablevms.addCell("Tag NO");
			// tablevms.addCell("Equipment");
			// tablevms.addCell("Application");
			// tablevms.addCell("Location");
			// tablevms.addCell("Quantity");
			// tablevms.setHeaderRows(1);
			// PdfPCell[] cells = tablevms.getRow(0).getCells();
			// for (int vmsj=0;vmsj<cells.length;vmsj++){
			// cells[vmsj].setBackgroundColor(BaseColor.GRAY);
			// }
			//
			// tablevms.addCell(String.valueOf(orderby+1));
			// tablevms.addCell(tagnm);
			// tablevms.addCell(equipment);
			// tablevms.addCell(application);
			// tablevms.addCell(location);
			// tablevms.addCell(String.valueOf(quant));
			//
			//
			//
			// prevaddprbflag = addprbflag;
			// prevvmsnm =vmsnm;
			// prevheadertextvms=headertext;
			//
			//
			// }
			//
			// //vms end
			//
			// //stg table start
			//
			// int y = 0;
			//
			// String previnstrnm = null;
			// String prevheadertext = null;
			//
			// PdfPRow tableRowOne1stg = null;
			// int counter=0;
			// int count5=0;
			// int count51=0;
			// String previtemnm34=null;
			// for (y = q; y < reportBean.getWordData().size(); y++)
			//
			// {
			// logger.info("inside stg instruments");
			//
			// counter=counter+1;
			// int id = 0;
			// String instrcd = null;
			// String instrnm = null;
			// String instrtypenm = null;
			// String instrdesc = null;
			// String instrmounting = null;
			// int qtylogic = 0;
			// int nooftable = 0;
			// String headertext = null;
			// String footertext = null;
			// int itemid = 0;
			// int addinstrid =0;
			// String addinstrcd = null;
			// String addinstrnm = null;
			// String itemnm=null;
			// int count34=0;
			//
			// if(y== QuotationDaoImpl.size6 && count51>0)
			// {
			// if(reportBean.getWordData().get(y-1).getNoOfTable()>1 &&
			//// counter>1)
			// {
			// int count2=0;
			//
			// for(int
			//// y1=1;y1<reportBean.getWordData().get(y-1).getNoOfTable();y1++)
			// {
			//// to do
			//// int d=document.getTables().size();
			// if(count2==0)
			// {
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			// preface123.add(new Paragraph(""));
			//
			//
			// }
			// count2=count2+1;
			//
			//
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			// preface123.add(new Paragraph(
			//// reportBean.getWordData().get(y-1).getInstrNm()+"("+(y1+1)+")"));
			//
			//
			// //To Do
			//// CTTbl ctTbl = CTTbl.Factory.newInstance(); // Create a new
			//// CTTbl for the new table
			//// ctTbl.set(table.getCTTbl()); // Copy the template table's CTTbl
			//// XWPFTable new_table = new XWPFTable(ctTbl, document); // Cre
			//// document.createTable();
			//// document.createParagraph();
			//// document.setTable(d, new_table);
			//
			// }
			// }
			//
			// }
			//
			// if (y == QuotationDaoImpl.size6)
			// break;
			// if(!reportBean.getWordData().get(y).getInstrNm().equals("Additional
			//// Instruments"))
			//
			// {
			// id = reportBean.getWordData().get(y).getId();
			// instrcd = reportBean.getWordData().get(y).getInstrCode();
			// instrnm = reportBean.getWordData().get(y).getInstrNm();
			// instrtypenm = reportBean.getWordData().get(y).getInstrTypeNm();
			// instrdesc = reportBean.getWordData().get(y).getInstrDesc();
			// instrmounting =
			//// reportBean.getWordData().get(y).getInstrMounting();
			// qtylogic = reportBean.getWordData().get(y).getQuantityLogic();
			// nooftable = reportBean.getWordData().get(y).getNoOfTable();
			// headertext = reportBean.getWordData().get(y).getHeaderText();
			// footertext = reportBean.getWordData().get(y).getFooterText();
			// itemid = reportBean.getWordData().get(y).getItemId();
			// addinstrid = reportBean.getWordData().get(y).getAddInstrId();
			// addinstrcd = reportBean.getWordData().get(y).getAddInstrCd();
			// addinstrnm = reportBean.getWordData().get(y).getAddInstrNm();
			// itemnm=reportBean.getWordData().get(y).getItemNm();
			//
			//
			// if (!itemnm.equals(previtemnm34))
			// {
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			// preface123.add(new Paragraph(""));
			//
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			// preface123.add(new Paragraph(itemnm));
			//
			//
			// }
			//
			//
			// // to print instr name only once
			//
			// if (!instrnm.equals(previnstrnm) ) {
			// if(reportBean.getWordData().get(y-1).getNoOfTable()>1 &&
			//// counter>1)
			// {
			// count34=1;
			// int count2=0;
			// for(int
			//// y1=1;y1<reportBean.getWordData().get(y-1).getNoOfTable();y1++)
			// {
			//
			// //to do
			// //int d=document.getTables().size();
			// if(count2==0)
			// {
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			// preface123.add(new Paragraph(""));
			// }
			// count2=count2+1;
			//
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			// preface123.add(new Paragraph(previnstrnm+"("+(y1+1)+")"));
			//
			// //Tp do
			//// CTTbl ctTbl = CTTbl.Factory.newInstance(); // Create a new
			//// CTTbl for the new table
			//// ctTbl.set(table.getCTTbl()); // Copy the template table's CTTbl
			//// XWPFTable new_table = new XWPFTable(ctTbl, document); // Cre
			//// document.createTable();
			//// document.createParagraph();
			//// document.setTable(d, new_table);
			//
			// }
			// }
			//
			// if(count51>0 && count34==0 )
			// {
			// count34=0;
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			// preface123.add(new Paragraph(""));
			// }
			//
			//
			//
			// count51=count51+1;
			//
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			// if(reportBean.getWordData().get(y).getNoOfTable()>1)
			// {
			// preface123.add(new
			//// Paragraph(reportBean.getWordData().get(y).getInstrNm()+"(1)"));
			//
			// }
			// else
			// {
			//
			// preface123.add(new
			//// Paragraph(reportBean.getWordData().get(y).getInstrNm()));
			//
			// }
			//
			// if (headertext != null)
			// {
			// if (!headertext.equals(prevheadertext))
			// {
			//
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
			// preface123.add(new
			//// Paragraph(reportBean.getWordData().get(y).getHeaderText()));
			//
			// }
			// }
			//
			// if(!instrnm.equals("Additional Instruments"))
			// {
			// //To-Do
			//
			// tableRowOne1stg = tablestg.getRow(0);
			// }
			//
			//
			//
			// }
			//
			//
			// // write to first row, first column
			// if(!instrnm.equals("Additional Instruments"))
			// {
			//
			// //to do
			//
			// tablestg.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			// tablestg.addCell("SL.NO.");
			// tablestg.addCell("INST. TYPE ");
			// tablestg.addCell("INSTRUMENT DESCRIPTION");
			// tablestg.addCell("INSTRUMENT MOUNTING");
			// tablestg.addCell("Quantity ( for 1oo1 Logic)");
			//
			// tablestg.setHeaderRows(1);
			// PdfPCell[] cells = tablestg.getRow(0).getCells();
			// for (int stg=0;stg<cells.length;stg++){
			// cells[stg].setBackgroundColor(BaseColor.GRAY);
			// }
			//
			// tablestg.addCell(String.valueOf(id));
			// tablestg.addCell(instrtypenm);
			// tablestg.addCell(instrdesc);
			// tablestg.addCell(instrmounting);
			// tablestg.addCell(String.valueOf(qtylogic));
			//
			// if (!instrnm.equals(previnstrnm)) {
			//
			// }
			//
			//
			// previnstrnm = instrnm;
			// prevheadertext = headertext;
			// previtemnm34=itemnm;
			// }
			// }
			// }
			//
			//
			//
			// //stg table end
			//
			// //add instr table start
			//
			//
			// int d= 0;
			//
			// String previtemnm = null;
			// int counteradd=0;
			// PdfPRow tableRowOneaddinstr = null;
			//
			// int count8=0;
			// int count4=0;
			// for (d=y;d<reportBean.getWordData().size();d++)
			//
			// {
			// if (d == QuotationDaoImpl.size12)
			// {
			//
			//
			// break;
			// }
			// if(reportBean.getWordData().get(d).getColId()!=0)
			// {
			//
			// count4=count4+1;
			// logger.info("inside addition instruments");
			// counteradd =counteradd+1;
			// int detquotid=0;
			// int itemid=0;
			// String itemnm=null;
			// int qty=0;
			// int colid=0;
			// String colnm=null;
			// String colvalcd=null;
			// String subcolvalcd=null;
			// int addonnewcolflag=0;
			// boolean rhsflag=false;
			// float rhscolqty=0;
			// int dessubitmeorderid=0;
			// float rhscost=0;
			// float addinstrcost=0;
			// float totaladdinstrcost=0;
			// boolean itemflag=false;
			// boolean othersflag=false;
			// boolean addinstrflag=false;
			//
			//
			// detquotid = reportBean.getWordData().get(d).getDetQuotId();
			// itemid = reportBean.getWordData().get(d).getItemId();
			// itemnm = reportBean.getWordData().get(d).getItemName();
			// qty = reportBean.getWordData().get(d).getQty();
			// colid = reportBean.getWordData().get(d).getColId();
			// colnm = reportBean.getWordData().get(d).getColNm();
			// colvalcd = reportBean.getWordData().get(d).getColValCd();
			// subcolvalcd = reportBean.getWordData().get(d).getSubColValCode();
			// addonnewcolflag =
			//// reportBean.getWordData().get(d).getAddOnNewColFlag();
			// rhsflag = reportBean.getWordData().get(d).isRhsFlag();
			// rhscolqty = reportBean.getWordData().get(d).getRhsColQuantity();
			// dessubitmeorderid =
			//// reportBean.getWordData().get(d).getDesSubItemOrderId();
			// rhscost = reportBean.getWordData().get(d).getRhsCost();
			// addinstrcost = reportBean.getWordData().get(d).getAddInstrCost();
			// totaladdinstrcost =
			//// reportBean.getWordData().get(d).getTotalAddInstrCost();
			// itemflag = reportBean.getWordData().get(d).isItemFlag();
			// othersflag = reportBean.getWordData().get(d).isOthersFlag();
			// addinstrflag = reportBean.getWordData().get(d).isAddInstrFlag();
			//
			// //to print addinstr name only once
			// if(count8==0)
			// {
			//
			//
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			// preface123.add(new Paragraph(""));
			//
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			// preface123.add(new Paragraph("Additional Instruments"));
			//
			//
			// }
			// if (!itemnm.equals(previtemnm))
			// {
			// if(count8>0)
			// {
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			// preface123.add(new Paragraph(""));
			// }
			// count8=count8+1;
			//
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			// preface123.add(new Paragraph(itemnm));
			//
			// //table = document.createTable();
			// tableRowOneaddinstr = table.getRow(0);
			//
			//
			//
			// }
			//
			// tableaddinstr.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			// tableaddinstr.addCell("SL.NO.");
			// tableaddinstr.addCell("NAME");
			// tableaddinstr.addCell("DESCRIPTION");
			// tableaddinstr.addCell("MOUNTING");
			// tableaddinstr.addCell("Quantity");
			//
			// tableaddinstr.setHeaderRows(1);
			// PdfPCell[] cells = tableaddinstr.getRow(0).getCells();
			// for (int addinstr=0;addinstr<cells.length;addinstr++){
			// cells[addinstr].setBackgroundColor(BaseColor.GRAY);
			// }
			// `
			// tableaddinstr.addCell(String.valueOf(colid));
			// tableaddinstr.addCell(colnm);
			// tableaddinstr.addCell(colvalcd);
			// tableaddinstr.addCell(subcolvalcd);
			// tableaddinstr.addCell(String.valueOf(rhscolqty));
			//
			//
			//
			// previtemnm = itemnm;
			//
			// }
			// }
			//
			//
			// //add instr table end
			//
			// //ele aux start
			//
			// //ele aux start
			//
			// int c = 0;
			// String prevcategoryele1 = null;
			// String prevsubcategoryele1 = null;
			// String previtemnameele1 = null;
			// String prevsubitemnameele1= null;
			// String prevdesitemnameele1 = null;
			// String prevdessubitemnameele1 = null;
			// String previtemcodeele1 = null;
			// String prevtechremarksele1= null;
			// String prevheadertextele1 = null;
			// String prevfootertextele1= null;
			// int counter41 = 0;
			// int count11 = 0;
			// int count7=0;
			// int count71=0;
			// for (c = d; c < reportBean.getWordData().size(); c++)
			//
			// {
			// logger.info("ele_aux");
			//
			// String categorynameele1 = null;
			// String subcategorynameele1 = null;
			// int itemidele1 = 0;
			// String itemnameele1= null;
			// String desitemnameele1 = null;
			// String dessubitemnameele1 = null;
			// String itemcodeele1 = null;
			// String subitemcodeele1 = null;
			// String subitemnameele1 = null;
			// int qunatityele1 = 0;
			// String techremarksele1 = null;
			// String techcommentsele1 = null;
			// int numberofsubitemsele1 = 0;
			// int orderidele1 = 0;
			// int dessubitemorderidele1 = 0;
			// String headertextele1 = null;
			// String footertextele1 = null;
			// String subColValCodeele1 = null;
			// String descolorderidele1 = null;
			// int linkFlag1 = 0;
			//
			// PdfPRow tableRowOneEleAux = null;
			//
			// if (c == QuotationDaoImpl.size8)
			//
			// break;
			//
			// categorynameele1 =
			//// reportBean.getWordData().get(c).getCategoryName();
			// subcategorynameele1 =
			//// reportBean.getWordData().get(c).getSubCategoryName();
			// itemidele1 = reportBean.getWordData().get(c).getItemId();
			// itemnameele1 = reportBean.getWordData().get(c).getSubItemName();
			// desitemnameele1 =
			//// reportBean.getWordData().get(c).getDesItemName();
			// dessubitemnameele1 =
			//// reportBean.getWordData().get(c).getDesSubItemName();
			// itemcodeele1 = reportBean.getWordData().get(c).getItemCode();
			// subitemcodeele1 =
			//// reportBean.getWordData().get(c).getSubItemCode();
			// subitemnameele1 =
			//// reportBean.getWordData().get(c).getSubItemTypeName();
			// qunatityele1 = reportBean.getWordData().get(c).getQuantityEle();
			// techremarksele1 =
			//// reportBean.getWordData().get(c).getTechRemarks();
			// techcommentsele1 =
			//// reportBean.getWordData().get(c).getTechComments();
			// numberofsubitemsele1 =
			//// reportBean.getWordData().get(c).getNumberOfSubItems();
			// orderidele1 = reportBean.getWordData().get(c).getOrderId();
			// dessubitemorderidele1 =
			//// reportBean.getWordData().get(c).getDesSubItemOrderId();
			// descolorderidele1 =
			//// reportBean.getWordData().get(c).getDesColOrderId();
			// subColValCodeele1 =
			//// reportBean.getWordData().get(c).getSubColValCode();
			// headertextele1 = reportBean.getWordData().get(c).getHeaderText();
			// footertextele1 = reportBean.getWordData().get(c).getFooterText();
			// linkFlag1 = reportBean.getWordData().get(c).getLinkFlag();
			// logger.info(reportBean.getWordData().get(c).getColValCd());
			// int counter31 = 0;
			//
			// int[] cols1= { 4896, 1872, 4032, 1728, 1440 };
			// //
			// // This is to print the technical remarks at the end of the item
			// if (!itemcodeele1.equals(previtemcodeele1) && itemcodeele1 !=
			//// null) {
			// if (techremarksele1 != null) {
			// if (!techremarksele1.equals(prevtechremarksele1)){
			//
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
			// preface123.add(new Paragraph(prevtechremarksele1);
			//
			// }
			// }
			// }
			//
			// itemnameele1 = reportBean.getWordData().get(c).getSubItemName();
			// desitemnameele1 =
			//// reportBean.getWordData().get(c).getDesItemName();
			//
			// // to print subcategory name
			// if (!subcategorynameele1.equals(prevsubcategoryele1)) {
			//
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
			// preface123.add(new Paragraph("");
			//
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			// preface123.add(new Paragraph(subcategorynameele1);
			//
			//
			// }
			//
			// // to print item name only once
			// if (!itemnameele1.equals(previtemnameele1)) {
			//
			// counter41 = counter41 + 1;
			// if (counter41 > 1) {
			// if ((prevfootertextele1 != null) &&
			//// !(prevfootertextele1.equals("NULL")) &&
			//// !(prevfootertextele1.equals("NULL:"))) {
			// if (!footertextele1.equals(prevfootertextele1)) {
			//
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
			// preface123.add(new Paragraph(prevfootertextele1);
			//
			// }
			// }
			// }
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			// preface123.add(new Paragraph(itemnameele1);
			//
			// }
			// if(subitemnameele1!=null)
			// {
			//
			// if (!subitemnameele1.equals(prevsubitemnameele1)) {
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			// preface123.add(new Paragraph(subitemnameele1);
			//
			//
			// }
			// }
			// if (desitemnameele1 != null) {
			// if (!desitemnameele1.equals(prevdesitemnameele1)) {
			// if(count7>0)
			// {
			//
			//
			// if(prevdesitemnameele1.equals("Design Data") &&
			//// reportBean.getWordData().get(c-1).getLinkFlag()==1 )
			// {
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			// preface123.add(new Paragraph(""));
			//
			// }
			// }
			// count7=count7+1;
			// count11 = 0;
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			// preface123.add(new Paragraph(desitemnameele1));
			//
			//
			// if ((headertextele1 != null) && !headertextele1.equals("NULL")) {
			// if (!headertextele1.equals(prevheadertextele1)) {
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
			// preface123.add(new Paragraph(headertextele1));
			//
			//
			// }
			// }
			// if(dessubitemnameele1==null)
			// {
			// table = document.createTable();
			//
			// CTTblLayoutType type =
			//// table.getCTTbl().getTblPr().addNewTblLayout();
			// type.setType(STTblLayoutType.FIXED);
			//
			// if (desitemnameele1.equalsIgnoreCase(
			// "Multifunctional digital generator protection relay shall have
			//// following protections features")) {
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
			// table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));
			// table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(5000));
			// table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
			// }
			// else if(desitemnameele1.equals("Design Data") && linkFlag1==1) {
			// CTTblLayoutType type1 =
			//// table.getCTTbl().getTblPr().addNewTblLayout();
			// type1.setType(STTblLayoutType.FIXED);
			// setTableAlign(table, ParagraphAlignment.CENTER);// Creating
			//
			// table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
			// table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
			// table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(2000));
			// table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));
			//
			// }
			// else {
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
			// table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
			// table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
			// table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
			// table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));
			//
			// }
			//
			// setTableAlign(table, ParagraphAlignment.LEFT);// Creating
			// // table
			// // only once
			// tableRowOneEleAux = table.getRow(0);
			// }
			// } // if (!subitemnameele.equals(prevdesitemnameele))
			// else
			// {
			// if (!itemnameele1.equals(previtemnameele1)) {
			//
			// if(prevdesitemnameele1.equals("Design Data") &&
			//// reportBean.getWordData().get(c-1).getLinkFlag()==1 )
			// {
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
			// preface123.add(new Paragraph(""));
			//
			// }
			// count11 = 0;
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			// preface123.add(new Paragraph(desitemnameele1));
			//
			//
			// if ((headertextele1 != null) && !headertextele1.equals("NULL")) {
			// if (!headertextele1.equals(prevheadertextele1)) {
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
			// preface123.add(new Paragraph(headertextele1));
			//
			// }
			// }
			// if(dessubitemnameele1==null)
			// {
			// table = document.createTable();
			//
			// CTTblLayoutType type =
			//// table.getCTTbl().getTblPr().addNewTblLayout();
			// type.setType(STTblLayoutType.FIXED);
			//
			// if (desitemnameele1.equalsIgnoreCase(
			// "Multifunctional digital generator protection relay shall have
			//// following protections features")) {
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
			// table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));
			// table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(5000));
			// table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
			//
			// }
			// else if(desitemnameele1.equals("Design Data") && linkFlag1==1) {
			// CTTblLayoutType type1 =
			//// table.getCTTbl().getTblPr().addNewTblLayout();
			// type1.setType(STTblLayoutType.FIXED);
			// setTableAlign(table, ParagraphAlignment.CENTER);
			// table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
			// table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
			// table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(2000));
			// table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));
			//
			// }
			// else {
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
			// table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
			// table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
			// table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
			// table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1500));
			//
			// }
			//
			// setTableAlign(table, ParagraphAlignment.LEFT);// Creating
			// // table
			// // only once
			// tableRowOneEleAux = table.getRow(0);
			//
			//
			//
			// }
			// }
			// }
			//
			// if (dessubitemnameele1 != null) {
			// if (!dessubitemnameele1.equals(prevdessubitemnameele1)) {
			// logger.info("3");
			//
			// if(count71>0)
			// {
			//
			// if(prevdessubitemnameele1!=null)
			// {
			//
			//
			// if(prevdessubitemnameele1.equals("Multifunctional digital
			//// generator protection relay shall have following protections
			//// features"))
			// {
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
			// preface123.add(new Paragraph(""));
			//
			// }
			// }
			// }
			// count71=count71+1;
			// counter31 = 1;
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			// preface123.add(new Paragraph(dessubitemorderidele1 + " " +
			//// dessubitemnameele1));
			//
			//
			// table = document.createTable();
			//
			//
			// CTTblLayoutType type =
			//// table.getCTTbl().getTblPr().addNewTblLayout();
			// type.setType(STTblLayoutType.FIXED);
			// if (dessubitemnameele1.equalsIgnoreCase(
			// "Multifunctional digital generator protection relay shall have
			//// following protections features")) {
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
			// table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));
			// table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(5000));
			// table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
			// } else {
			//
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
			// table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
			//
			// table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(4200));
			// table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3200));
			// table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1500));
			//
			// }
			//
			// setTableAlign(table, ParagraphAlignment.LEFT);
			// tableRowOneEleAux = table.getRow(0);
			//
			// }
			// }
			//
			//
			//
			// // To print the lhs and rhs of des item
			// String colunmname1 = reportBean.getWordData().get(c).getColNm();
			// String colValCode1 =
			//// reportBean.getWordData().get(c).getColValCd();
			//
			// if (desitemnameele1 != null && dessubitemnameele1 == null)
			// {
			//
			// if (colunmname1 != null && colValCode1 != null) {
			//
			// if (tableRowOneEleAux != null) {
			// if (desitemnameele1.equalsIgnoreCase(
			// "Multifunctional digital generator protection relay shall have
			//// following protections features")) {
			//
			//
			// tableRowOneEleAux.getCell(0).setText("SL.NO");
			// tableRowOneEleAux.addNewTableCell().setText("CATEGORY");
			// tableRowOneEleAux.addNewTableCell().setText("DESCRIPTION");
			// tableRowOneEleAux.getCell(0).setColor("808080");
			// tableRowOneEleAux.getCell(1).setColor("808080");
			// tableRowOneEleAux.getCell(2).setColor("808080");
			// tableRowOneEleAux = null;
			// run.setFontFamily("Calibri (Body)");
			// run.setFontSize(11);
			// XWPFTableRow tableRowOne = table.createRow();
			// tableRowOne.getCell(0).setText(descolorderidele1);
			// tableRowOne.getCell(1).setText(colunmname1);
			// if (subColValCodeele1 != null &&
			//// !(subColValCodeele1.equals("NULL"))) {
			// tableRowOne.getCell(2).setText(subColValCodeele1);
			// } else {
			// tableRowOne.getCell(2).setText(colValCode1);
			// }
			//
			// run.setFontFamily("Calibri (Body)");
			// run.setFontSize(11);
			//
			// }
			// else if(desitemnameele1.equals("Design Data") && linkFlag1==1) {
			// tableRowOneEleAux.getCell(0).setText("SL.NO");
			// tableRowOneEleAux.addNewTableCell().setText("SCOPE of supply and
			//// battery limits");
			// tableRowOneEleAux.addNewTableCell();
			// XWPFParagraph
			//// paragraph12=tableRowOneEleAux.getCell(2).getParagraphs().get(0);
			// paragraph12.setAlignment(ParagraphAlignment.CENTER);
			// XWPFRun run12 = paragraph12.createRun();
			// run12.setFontSize(11);
			// run12.setFontFamily("Calibri (Body)");
			// run12.setText("Engineering");
			// tableRowOneEleAux.addNewTableCell().setText("Supply");
			// tableRowOneEleAux.getCell(0).setColor("808080");
			// tableRowOneEleAux.getCell(1).setColor("808080");
			// tableRowOneEleAux.getCell(2).setColor("808080");
			// tableRowOneEleAux.getCell(3).setColor("808080");
			// tableRowOneEleAux = null;
			// run.setFontFamily("Calibri (Body)");
			// run.setFontSize(11);
			//
			//
			// XWPFTableRow tableRowOne = table.createRow();
			// tableRowOne.getCell(0).setText(descolorderidele1);
			//
			// tableRowOne.getCell(1).setText(colunmname1);
			// if(colValCode1.equalsIgnoreCase("Yes") ||
			//// colValCode1.equalsIgnoreCase("No") ){
			// tableRowOne.getCell(2).setText("YES");
			// tableRowOne.getCell(3).setText(colValCode1);
			// }
			// else
			// {
			// tableRowOne.getCell(2).setText(" ");
			// CTTc ctTc1 = tableRowOne.getCell(2).getCTTc();
			// CTTcPr tcPr1 = ctTc1.addNewTcPr();
			// CTHMerge hMerge1 = tcPr1.addNewHMerge();
			// hMerge1.setVal(STMerge.RESTART);
			// CTTcBorders tblBorders1 = tcPr1.addNewTcBorders();
			//
			// tblBorders1.addNewRight().setVal(STBorder.NIL);
			// tableRowOne.getCell(3).setText(colValCode1);
			// CTTc ctTc12 = tableRowOne.getCell(3).getCTTc();
			// CTTcPr tcPr12 = ctTc12.addNewTcPr();
			// CTHMerge hMerge12 = tcPr12.addNewHMerge();
			// hMerge12.setVal(STMerge.RESTART);
			//
			// CTTcBorders tblBorders12 = tcPr12.addNewTcBorders();
			//
			//
			// tblBorders12.addNewLeft().setVal(STBorder.NIL);
			//
			//
			// }
			//
			//
			// run.setFontFamily("Calibri (Body)");
			// run.setFontSize(11);
			//
			//
			//
			// }
			//
			//
			// else {
			// tableRowOneEleAux.getCell(0).setText(descolorderidele1);
			//
			// tableRowOneEleAux.addNewTableCell().setText(colunmname1);;
			// tableRowOneEleAux.addNewTableCell();
			// XWPFParagraph
			//// paragraph12=tableRowOneEleAux.getCell(2).getParagraphs().get(0);
			//
			// paragraph12.setAlignment(ParagraphAlignment.CENTER);
			// XWPFRun run12 = paragraph12.createRun();
			// run12.setFontSize(14);
			// run12.setFontFamily("Calibri (Body)");
			// run12.setText(":");
			// if (subColValCodeele1 != null &&
			//// !(subColValCodeele1.equals("NULL"))) {
			// tableRowOneEleAux.addNewTableCell().setText(subColValCodeele1);
			// } else {
			// tableRowOneEleAux.addNewTableCell().setText(colValCode1);
			// }
			//
			// tableRowOneEleAux = null;
			// run.setFontFamily("Calibri (Body)");
			// run.setFontSize(11);
			//
			// }
			//
			// }
			//
			// else {
			// if (desitemnameele1.equalsIgnoreCase(
			// "Multifunctional digital generator protection relay shall have
			//// following protections features"))
			// {
			//
			// XWPFTableRow tableRowOne = table.createRow();
			// tableRowOne.getCell(0).setText(descolorderidele1);
			// //
			//// tableRowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
			//
			// tableRowOne.getCell(1).setText(colunmname1);
			// if (subColValCodeele1!= null &&
			//// !(subColValCodeele1.equals("NULL"))) {
			// tableRowOne.getCell(2).setText(subColValCodeele1);
			// } else {
			// tableRowOne.getCell(2).setText(colValCode1);
			// }
			//
			// run.setFontFamily("Calibri (Body)");
			// run.setFontSize(11);
			//
			// }
			//
			// else if((colunmname1.equals("Additional outgoing feeders ( Ampere
			//// rating & quantity )"))&& count == 0){
			// count11 = 1;
			// XWPFTableRow tableRowOne = table.createRow();
			// tableRowOne.getCell(0).setText(descolorderidele1);
			//
			// tableRowOne.getCell(1).setText(colunmname1);
			//
			// XWPFParagraph
			//// paragraph12=tableRowOne.getCell(2).getParagraphs().get(0);
			//
			//
			// paragraph12.setAlignment(ParagraphAlignment.CENTER);
			// XWPFRun run12 = paragraph12.createRun();
			// run12.setFontSize(14);
			// run12.setFontFamily("Calibri (Body)");
			// run12.setText(":");
			// if (subColValCodeele1 != null &&
			//// !(subColValCodeele1.equals("NULL"))) {
			// tableRowOne.getCell(3).setText(subColValCodeele1);
			// } else {
			// tableRowOne.getCell(3).setText(colValCode1 +"*"+qunatityele1);
			// }
			//
			//
			// run.setFontFamily("Calibri (Body)");
			// run.setFontSize(11);
			//
			// }
			//
			// else if(colunmname1.equals("Additional outgoing feeders ( Ampere
			//// rating & quantity )")&& count11 != 0){
			// XWPFTableRow tableRowOne = table.createRow();
			// tableRowOne.getCell(0).setText("");
			//
			// tableRowOne.getCell(1).setText(" ");
			// tableRowOne.getCell(2).setText(" ");
			// if (subColValCodeele1 != null &&
			//// !(subColValCodeele1.equals("NULL"))) {
			// tableRowOne.getCell(3).setText(subColValCodeele1);
			// } else {
			// tableRowOne.getCell(3).setText(colValCode1 +"*"+qunatityele1);
			// }
			//
			//
			// run.setFontFamily("Calibri (Body)");
			// run.setFontSize(11);
			//
			// }
			//
			//
			// else if(desitemnameele1.equals("Design Data") && linkFlag1==1)
			// {
			//
			// XWPFTableRow tableRowOne = table.createRow();
			// tableRowOne.getCell(0).setText(descolorderidele1);
			// tableRowOne.getCell(1).setText(colunmname1);
			// tableRowOne.getCell(2).setText(" ");
			// CTTc ctTc1 = tableRowOne.getCell(2).getCTTc();
			// CTTcPr tcPr1 = ctTc1.addNewTcPr();
			// CTHMerge hMerge1 = tcPr1.addNewHMerge();
			// hMerge1.setVal(STMerge.RESTART);
			//
			// CTTcBorders tblBorders1 = tcPr1.addNewTcBorders();
			//
			//
			// tblBorders1.addNewRight().setVal(STBorder.NIL);
			// tableRowOne.getCell(3).setText(colValCode1);
			// CTTc ctTc12 = tableRowOne.getCell(3).getCTTc();
			// CTTcPr tcPr12 = ctTc12.addNewTcPr();
			// CTHMerge hMerge12 = tcPr12.addNewHMerge();
			// hMerge12.setVal(STMerge.RESTART);
			//
			// CTTcBorders tblBorders12 = tcPr12.addNewTcBorders();
			//
			//
			// tblBorders12.addNewLeft().setVal(STBorder.NIL);
			//
			//
			//
			//
			// }
			//
			//
			// else {
			//
			// XWPFTableRow tableRowOne = table.createRow();
			// tableRowOne.getCell(0).setText(descolorderidele1 );
			// tableRowOne.getCell(1).setText(colunmname1);
			//
			// XWPFParagraph
			//// paragraph12=tableRowOne.getCell(2).getParagraphs().get(0);
			//
			//
			// paragraph12.setAlignment(ParagraphAlignment.CENTER);
			// XWPFRun run12 = paragraph12.createRun();
			// run12.setFontSize(14);
			// run12.setFontFamily("Calibri (Body)");
			// run12.setText(":");
			// if (subColValCodeele1 != null &&
			//// !(subColValCodeele1.equals("NULL"))) {
			// tableRowOne.getCell(3).setText(subColValCodeele1);
			// } else {
			// if(colValCode1.contains("\n")){
			//
			// String[] arrOfStr = colValCode1.split("\n", 20);
			// for(int x1=0;x1<arrOfStr.length;x1++)
			// {
			// XWPFParagraph
			//// paragraph21=tableRowOne.getCell(3).getParagraphs().get(0);
			//
			// XWPFRun run123 = paragraph21.createRun();
			// run123.setFontSize(11);
			// run123.setFontFamily("Calibri (Body)");
			// run123.setText(arrOfStr[x1]);
			// run123.addBreak();
			// logger.info("rhs word start");
			// logger.info(arrOfStr[x1]);
			// logger.info("rhs word end");
			// }
			// }
			// else
			// {
			// tableRowOne.getCell(3).setText(colValCode1);
			//
			// }
			// }
			//
			// run.setFontFamily("Calibri (Body)");
			// run.setFontSize(11);
			//
			// }
			//
			// }
			//
			// }
			// } else {
			//
			// if (desitemnameele1 != null) {
			//
			// if (tableRowOneEleAux != null) {
			// if (dessubitemnameele1.equalsIgnoreCase(
			// "Multifunctional digital generator protection relay shall have
			//// following protections features")) {
			// tableRowOneEleAux.getCell(0).setText("SL.NO");
			// tableRowOneEleAux.addNewTableCell().setText("CATEGORY");
			// tableRowOneEleAux.addNewTableCell().setText("DESCRIPTION");
			// tableRowOneEleAux.getCell(0).setColor("808080");
			// tableRowOneEleAux.getCell(1).setColor("808080");
			// tableRowOneEleAux.getCell(2).setColor("808080");
			//
			// tableRowOneEleAux= null;
			// run.setFontFamily("Calibri (Body)");
			// run.setFontSize(11);
			// XWPFTableRow tableRowOne = table.createRow();
			// tableRowOne.getCell(0).setText(descolorderidele1);
			// tableRowOne.getCell(1).setText(colunmname1);
			// if (subColValCodeele1 != null &&
			//// !(subColValCodeele1.equals("NULL"))) {
			// tableRowOne.getCell(2).setText(subColValCodeele1);
			// } else {
			// tableRowOne.getCell(2).setText(colValCode1);
			// }
			//
			// run.setFontFamily("Calibri (Body)");
			// run.setFontSize(11);
			//
			// } else {
			// tableRowOneEleAux.getCell(0).setText(descolorderidele1);
			//
			// tableRowOneEleAux.addNewTableCell().setText(colunmname1);
			// tableRowOneEleAux.addNewTableCell();
			// XWPFParagraph
			//// paragraph12=tableRowOneEleAux.getCell(2).getParagraphs().get(0);
			//
			//
			// paragraph12.setAlignment(ParagraphAlignment.CENTER);
			// XWPFRun run12 = paragraph12.createRun();
			// run12.setFontSize(14);
			// run12.setFontFamily("Calibri (Body)");
			// run12.setText(":");
			// if (subColValCodeele1 != null &&
			//// !(subColValCodeele1.equals("NULL"))) {
			// tableRowOneEleAux.addNewTableCell().setText(subColValCodeele1);
			// } else {
			// tableRowOneEleAux.addNewTableCell().setText(colValCode1);
			// }
			//
			// tableRowOneEleAux = null;
			// run.setFontFamily("Calibri (Body)");
			// run.setFontSize(11);
			// }
			//
			// }
			//
			// else {
			// if (dessubitemnameele1.equalsIgnoreCase(
			// "Multifunctional digital generator protection relay shall have
			//// following protections features")) {
			//
			// XWPFTableRow tableRowOne = table.createRow();
			//
			// tableRowOne.getCell(0).setText(descolorderidele1);
			// tableRowOne.getCell(1).setText(colunmname1);
			//
			// if (subColValCodeele1 != null &&
			//// !(subColValCodeele1.equals("NULL"))) {
			// tableRowOne.getCell(2).setText(subColValCodeele1);
			// } else {
			// tableRowOne.getCell(2).setText(colValCode1);
			// }
			//
			// run.setFontFamily("Calibri (Body)");
			// run.setFontSize(11);
			//
			//
			//
			// }
			//
			// else {
			//
			// XWPFTableRow tableRowOne = table.createRow();
			//
			// tableRowOne.getCell(0).setText(descolorderidele1);
			// tableRowOne.getCell(1).setText(colunmname1);
			//
			// XWPFParagraph
			//// paragraph12=tableRowOne.getCell(2).getParagraphs().get(0);
			//
			//
			// paragraph12.setAlignment(ParagraphAlignment.CENTER);
			// XWPFRun run12 = paragraph12.createRun();
			// run12.setFontSize(14);
			// run12.setFontFamily("Calibri (Body)");
			// run12.setText(":");
			//
			// if (subColValCodeele1 != null &&
			//// !(subColValCodeele1.equals("NULL"))) {
			// tableRowOne.getCell(3).setText(subColValCodeele1);
			// } else {
			// tableRowOne.getCell(3).setText(colValCode1);
			// if(colValCode1.contains("\n")){
			// logger.info("rhs word start");
			// logger.info(colValCode1);
			// logger.info("rhs word end");
			//
			// }
			//
			// }
			//
			// run.setFontFamily("Calibri (Body)");
			// run.setFontSize(11);
			//
			// }
			//
			// }
			//
			// }
			//
			// }
			//
			// // To print technical remarks of the sub itemq
			//
			// if (c + 1 == reportBean.getWordData().size()) {
			// if ((techremarksele1 != null))
			// {
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
			// preface123.add(new Paragraph(techremarksele1));
			//
			//
			// }
			// }
			//
			// }
			//
			// previtemnameele1 = itemnameele1;
			// prevsubitemnameele1 = subitemnameele1;
			// prevdesitemnameele1 = desitemnameele1;
			// prevdessubitemnameele1 = dessubitemnameele1;
			// previtemcodeele1 = itemcodeele1;
			// prevtechremarksele1 = techremarksele1;
			// prevsubcategoryele1 = subcategorynameele1;
			// prevheadertextele1 = headertextele1;
			// prevfootertextele1 = footertextele1;
			//
			// if (c + 1 == reportBean.getWordData().size()) {
			// if ((footertextele1 != null) && !(footertextele1.equals("NULL"))
			//// && !(footertextele1.equals("NULL:"))) {
			//
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
			// preface123.add(new Paragraph(footertextele1));
			//
			//
			//
			// }
			// }
			//
			// } // for loop
			// //ele aux ends
			//
			//
			//
			//
			//
			// //ele aux ends
			//
			// //ele ext start
			//
			//
			// int h = 0;
			// String prevcategoryextscp1 = null;
			// String prevsubcategoryextscp1 = null;
			// String previtemnameextscp1 = null;
			// String prevsubitemnameextscp1 = null;
			// String previtemcodeextscp1 = null;
			// String prevtechremarksextscp1 = null;
			// String prevtechcommentsextscp1=null;
			// int count88=0;
			// for (h = c; h < reportBean.getWordData().size(); h++)
			//
			// {
			// logger.info("inside ele_ext");
			// String categorynameextscp1 = null;
			// String subcategorynameextscp1 = null;
			// int itemidextscp1 = 0;
			// String itemnameextscp1= null;
			// String itemcodeextscp1 = null;
			// String subitemcodeextscp1 = null;
			// String subitemnameextscp1 = null;
			// int qunatityextscp1 = 0;
			// String techremarksextscp1 = null;
			// String techcommentsextscp1 = null;
			// int numberofsubitemsextscp1= 0;
			//
			//
			// if (h == QuotationDaoImpl.size9 && count88>0)
			// {
			// if(reportBean.getWordData().get(h-1).getTechRemarks()!=null)
			// {
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
			// preface123.add(new
			//// Paragraph(reportBean.getWordData().get(h-1).getTechRemarks()));
			//
			// }
			// }
			// if (h == QuotationDaoImpl.size9)
			// {
			// break;
			// }
			//
			//
			// categorynameextscp1 =
			//// reportBean.getWordData().get(h).getCategoryName();
			// subcategorynameextscp1 =
			//// reportBean.getWordData().get(h).getSubCategoryName();
			// itemidextscp1 = reportBean.getWordData().get(h).getItemId();
			// itemnameextscp1 = reportBean.getWordData().get(h).getItemName();
			// itemcodeextscp1 = reportBean.getWordData().get(h).getItemCode();
			// subitemcodeextscp1 =
			//// reportBean.getWordData().get(h).getSubItemCode();
			// subitemnameextscp1 =
			//// reportBean.getWordData().get(h).getSubItemName();
			// qunatityextscp1 =
			//// reportBean.getWordData().get(h).getQuantityAux();
			// techremarksextscp1 =
			//// reportBean.getWordData().get(h).getTechRemarks();
			// techcommentsextscp1 =
			//// reportBean.getWordData().get(h).getTechComments();
			// numberofsubitemsextscp1 =
			//// reportBean.getWordData().get(h).getNumberOfSubItems();
			//
			// // This is to print the technical remarks at the end of the item
			// if ( itemcodeextscp1 != null) {
			// if (techcommentsextscp1 != null) {
			// if (!techcommentsextscp1.equals(prevtechcommentsextscp1)) {
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
			// preface123.add(new Paragraph(techcommentsextscp1));
			// }
			// }
			// }
			//
			// itemnameextscp1 = reportBean.getWordData().get(h).getItemName();
			// subitemnameextscp1 =
			//// reportBean.getWordData().get(h).getSubItemName();
			//
			// // to print item name only once
			// if (!itemnameextscp1.equals(previtemnameextscp1)) {
			//
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			// preface123.add(new
			//// Paragraph(reportBean.getWordData().get(h).getItemName()));
			//
			//
			//
			// }
			//
			// // To print the sub item name only once
			// if (subitemnameextscp1 != null) {
			// if (!subitemnameextscp1.equals(prevsubitemnameextscp1)) {
			// if(count88>0)
			// {
			// if(prevtechremarksextscp1!=null)
			// {
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
			// preface123.add(new Paragraph(prevtechremarksextscp1));
			//
			// }
			// }
			//
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			// preface123.add(new
			//// Paragraph(reportBean.getWordData().get(h).getSubItemName()));
			//
			//
			//
			//
			// // To print the lhs and rhs of sub item
			// if (subitemnameextscp1 != null)
			// {
			// String colunmname1 = reportBean.getWordData().get(h).getColNm();
			// String colValCode1 =
			//// reportBean.getWordData().get(h).getColValCd();
			//
			// if (colunmname1 != null && colValCode1 != null) {
			//
			// addToTableValuePdf(table, colunmname1);
			// addToTableValueBoldPdf(table, ":");
			// addToTableValuePdf(table, colValCode1);
			// }
			//
			//
			//
			// } // if (subitemnameextscp != null )
			//
			// } // if (subitemnameextscp != null)
			//
			// previtemnameextscp1 = itemnameextscp1;
			// prevsubitemnameextscp1 = subitemnameextscp1;
			// previtemcodeextscp1 = itemcodeextscp1;
			// prevtechremarksextscp1 = techremarksextscp1;
			// prevtechcommentsextscp1=techcommentsextscp1;
			// count88=count88+1;
			// }// for loop
			//
			// //ele ext ends
			//
			//
			// //ci aux starts
			//
			// //ci aux ends
			//
			// //ci ext starts
			//
			// int f = 0;
			// String prevcategoryextscp11 = null;
			// String prevsubcategoryextscp11 = null;
			// String previtemnameextscp11 = null;
			// String prevsubitemnameextscp11 = null;
			// String previtemcodeextscp11 = null;
			// String prevtechremarksextscp11 = null;
			// String prevtechcommentsextscp11=null;
			// int counr99=0;
			// for (f = e; f < reportBean.getWordData().size(); f++)
			//
			// {
			// logger.info("inside CI_EXT");
			// String categorynameextscp11 = null;
			// String subcategorynameextscp11 = null;
			// int itemidextscp11 = 0;
			// String itemnameextscp11= null;
			// String itemcodeextscp11 = null;
			// String subitemcodeextscp11 = null;
			// String subitemnameextscp11 = null;
			// int qunatityextscp11 = 0;
			// String techremarksextscp11 = null;
			// String techcommentsextscp11 = null;
			// int numberofsubitemsextscp11= 0;
			//
			// if (f == QuotationDaoImpl.size11 && counr99>0)
			// {
			// if(reportBean.getWordData().get(f-1).getTechRemarks()!=null)
			// {
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
			// preface123.add(new
			//// Paragraph(reportBean.getWordData().get(f-1).getTechRemarks()));
			//
			// }
			// }
			// if (f == QuotationDaoImpl.size11)
			// {
			// break;
			// }
			// categorynameextscp11 =
			//// reportBean.getWordData().get(f).getCategoryName();
			// subcategorynameextscp11 =
			//// reportBean.getWordData().get(f).getSubCategoryName();
			// itemidextscp11 = reportBean.getWordData().get(f).getItemId();
			// itemnameextscp11 = reportBean.getWordData().get(f).getItemName();
			// itemcodeextscp11 = reportBean.getWordData().get(f).getItemCode();
			// subitemcodeextscp11 =
			//// reportBean.getWordData().get(f).getSubItemCode();
			// subitemnameextscp11 =
			//// reportBean.getWordData().get(f).getSubItemName();
			// qunatityextscp11 =
			//// reportBean.getWordData().get(f).getQuantityAux();
			// techremarksextscp11 =
			//// reportBean.getWordData().get(f).getTechRemarks();
			// techcommentsextscp11 =
			//// reportBean.getWordData().get(f).getTechComments();
			// numberofsubitemsextscp11 =
			//// reportBean.getWordData().get(f).getNumberOfSubItems();
			//
			// // This is to print the technical remarks at the end of the item
			// if ( itemcodeextscp11 != null) {
			// if (techcommentsextscp11 != null) {
			// if (!techcommentsextscp11.equals(prevtechcommentsextscp11)) {
			//
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
			// preface123.add(new Paragraph(techcommentsextscp11));
			// }
			// }
			// }
			//
			// itemnameextscp11 = reportBean.getWordData().get(f).getItemName();
			// subitemnameextscp11 =
			//// reportBean.getWordData().get(f).getSubItemName();
			//
			// // to print item name only once
			// if (!itemnameextscp11.equals(previtemnameextscp11)) {
			//
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			// preface123.add(new
			//// Paragraph(reportBean.getWordData().get(f).getItemName()));
			//
			//
			// }
			//
			// // To print the sub item name only once
			// if (subitemnameextscp11 != null) {
			// if (!subitemnameextscp11.equals(prevsubitemnameextscp11)) {
			// if(prevsubitemnameextscp11!=null)
			// {
			// if(prevtechremarksextscp11!=null)
			// {
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1);
			// preface123.add(new Paragraph(prevtechremarksextscp11));
			//
			//
			// }
			// }
			//
			// addEmptyLine(preface123, 1);
			// preface123.setFont(PdfStylAttributes.NORMAL_FONT1_BOLD);
			// preface123.add(new
			//// Paragraph(reportBean.getWordData().get(f).getSubItemName()));
			//
			//
			//
			// // To print the lhs and rhs of sub item
			// if (subitemnameextscp11 != null) // Compare two strings
			// {
			// String colunmname11 = reportBean.getWordData().get(f).getColNm();
			// String colValCode11 =
			//// reportBean.getWordData().get(f).getColValCd();
			//
			// if (colunmname11 != null && colValCode11 != null) {
			//
			// addToTableValuePdf(table, colunmname11);
			// addToTableValueBoldPdf(table, ":");
			// addToTableValuePdf(table, colValCode11);
			// }
			//
			//
			// }
			//
			// }
			//
			// previtemnameextscp11 = itemnameextscp11;
			// prevsubitemnameextscp11 = subitemnameextscp11;
			// previtemcodeextscp11 = itemcodeextscp11;
			// prevtechremarksextscp11 = techremarksextscp11;
			// prevtechcommentsextscp11=techcommentsextscp11;
			// counr99=counr99+1;
			// }// for loop
			//
			// //ci ext ends
			//

			float[] colWidths = new float[] { 45f, 10f, 45f };
			table.setWidths(colWidths);
			// table.setSpacingBefore(10);

			document.add(preface);
			document.add(prefaceUser);

			document.add(paragraph0);
			document.add(paragraph01);

			document.add(paragraph02);

			document.add(paragraph04);

			document.add(paragraph05);

			document.add(paragraph06);
			document.add(paragraph07);
			document.add(paragraph08);
			document.add(paragraph09);
			document.add(paragraph10);
			document.add(paragraph100);
			document.add(paragraph101);
			document.add(paragraph102);
			document.add(paragraph103);
			document.add(paragraph104);
			document.add(paragraph11);
			document.add(paragraph12);
			document.add(paragraph13);
			document.add(paragraph14);
			document.add(paragraph15);
			document.add(paragraph16);
			document.add(paragraph17);
			document.add(paragraph18);
			document.add(paragraph19);
			document.add(paragraph20);
			document.add(paragraph21);
			document.add(paragraph22);
			document.add(paragraph23);
			document.add(paragraph24);
			document.add(paragraph25);
			document.add(paragraph26);
			document.add(paragraph27);
			document.add(paragraph28);
			document.add(paragraph29);
			document.add(paragraph30);
			document.add(paragraph31);
			document.add(paragraph32);
			document.add(paragraph33);
			document.add(paragraph34);
			document.add(paragraph35);
			document.add(paragraph);
			// preface123.add(table);
			document.add(paragraph1);
			document.newPage();
			document.add(paragraph2);
			document.newPage();
			document.add(preface123);
			document.add(tablevms);

			document.newPage();
			document.newPage();

		} catch (Exception e) {
			reportBean.setSuccessCode(-1);
			reportBean.setSuccessMsg("Something went Wrong");
			logger.error("Exception :" + e);
		}
	}
	// technical report ends

	public void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	public void addHeaderInTable(String[] headerArray, PdfPTable table) {

		PdfPCell c1 = null;
		for (String header : headerArray) {
			c1 = new PdfPCell(new Phrase(header, PdfStylAttributes.SMALL_BOLD));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
		}
		table.setHeaderRows(2);

	}

	public void addHeaderInTableAddOn(String[] headerArray, PdfPTable table) {

		PdfPCell c1 = null;
		for (String header : headerArray) {
			c1 = new PdfPCell(new Phrase(header, PdfStylAttributes.SMALL_BOLD));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
		}
		table.setHeaderRows(2);

	}

	public void addToTable(PdfPTable table, String data) {
		PdfPCell cell = null;
		cell = new PdfPCell(new Phrase(data, PdfStylAttributes.NORMAL_FONT));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
	}

	public void addToTableParagraph(PdfPTable table, Paragraph p) {
		PdfPCell cell = null;
		cell = new PdfPCell(new Phrase(p));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
	}

	public void addToTablePara(PdfPTable table, Paragraph p) {
		PdfPCell cell = null;
		cell = new PdfPCell(new Phrase(p));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
	}

	public void addToTableBold(PdfPTable table, String data) {
		PdfPCell cell = null;
		cell = new PdfPCell(new Phrase(data, PdfStylAttributes.NORMAL_BOLD));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
	}

	public void addToTableredcolor(PdfPTable table, String data) {
		PdfPCell cell = new PdfPCell();
		Paragraph p = new Paragraph();
		p = new Paragraph(data, PdfStylAttributes.OVERWRITE);
		p.setAlignment(Element.ALIGN_LEFT);
		cell.addElement(p);
		table.addCell(cell);
	}

	public void addToTableWithBold(PdfPTable table, String data) {
		PdfPCell cell = null;
		cell = new PdfPCell(new Phrase(data, PdfStylAttributes.SMALL_BOLD));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
	}

	public void addStylToTable(PdfPTable table, String data) {
		PdfPCell cell = new PdfPCell();
		Paragraph p = new Paragraph();
		p = new Paragraph(data, PdfStylAttributes.OVERWRITE);
		p.setAlignment(Element.ALIGN_RIGHT);
		cell.addElement(p);
		table.addCell(cell);
	}

	public void addStylToTableRed(PdfPTable table, String data) {
		PdfPCell cell = new PdfPCell();
		Paragraph p = new Paragraph();
		p = new Paragraph(data, PdfStylAttributes.OVERWRITE);
		p.setAlignment(Element.ALIGN_RIGHT);
		cell.addElement(p);
		table.addCell(cell);
	}

	public void addNormStylToTable(PdfPTable table, String data) {
		PdfPCell cell = new PdfPCell();
		Paragraph p = new Paragraph();
		p = new Paragraph(data, PdfStylAttributes.NORMAL_BOLD);
		p.setAlignment(Element.ALIGN_RIGHT);
		cell.addElement(p);
		table.addCell(cell);
	}

	public void addNormStylToTableAlign(PdfPTable table, String data) {
		PdfPCell cell = new PdfPCell();
		Paragraph p = new Paragraph();
		table.setWidthPercentage(100);
		p = new Paragraph(data, PdfStylAttributes.NORMAL_FONT);
		p.setAlignment(Element.ALIGN_LEFT);
		cell.addElement(p);
		cell.setMinimumHeight(90);
		table.addCell(cell);
	}

	public void addToTableValDet(PdfPTable table, String data) {

		PdfPCell cell = new PdfPCell();
		cell = new PdfPCell(new Phrase(data, PdfStylAttributes.NORMAL_FONT));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
	}

	public void addToTableValDetStr(PdfPTable table, Float data) {
		PdfPCell cell = new PdfPCell();
		cell = new PdfPCell(new Phrase(String.format("%.3f", data), PdfStylAttributes.NORMAL_FONT));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
	}

	public void addToTableValDetStrRed(PdfPTable table, Float data) {
		PdfPCell cell = new PdfPCell();
		cell = new PdfPCell(new Phrase(String.format("%.3f", data), PdfStylAttributes.OVERWRITE));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
	}

	public void addToTableValDetStr(PdfPTable table, String data) {
		PdfPCell cell = new PdfPCell();
		cell = new PdfPCell(new Phrase(String.format("%.3f", data), PdfStylAttributes.NORMAL_FONT));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
	}

	public void addToTableHeaderDet(PdfPTable table, String data) {
		PdfPCell cell = new PdfPCell();
		cell = new PdfPCell(new Phrase(data, PdfStylAttributes.NORMAL_FONT));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
	}

	public void addToTableHeaderDetAlign(PdfPTable table, String data) {
		PdfPCell cell = new PdfPCell();
		cell = new PdfPCell(new Phrase(data, PdfStylAttributes.NORMAL_FONT));
		cell = new PdfPCell(new Phrase(data, PdfStylAttributes.ALIGN_LEFT));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
	}

	public void addToTableValue(PdfPTable table, String data) {
		PdfPCell cellF = new PdfPCell();
		cellF.addElement(new Phrase(data, PdfStylAttributes.NORMAL_FONT));
		cellF.setBorder(Rectangle.NO_BORDER);
		table.addCell(cellF);
	}

	public void addToTableValuePdf(PdfPTable table, String data) {
		PdfPCell cellF = new PdfPCell();
		cellF.addElement(new Phrase(data, PdfStylAttributes.NORMAL_FONT1));
		cellF.setBorder(Rectangle.NO_BORDER);
		table.addCell(cellF);
	}

	public void addToTableValuePdf2(PdfPTable table, String data, Integer value) {
		PdfPCell cellF = new PdfPCell();
		cellF.addElement(new Phrase(data, PdfStylAttributes.NORMAL_FONT1));
		cellF.setBorder(Rectangle.BOX);
		table.addCell(cellF);
	}

	public void addToTableValueBig(PdfPTable table, String data) {
		PdfPCell cellF = new PdfPCell();
		cellF.addElement(new Phrase(data, PdfStylAttributes.BIG_FONT));
		cellF.setBorder(Rectangle.NO_BORDER);
		table.addCell(cellF);
	}

	public void addToTableValueBig(PdfPTable table, Float data) {
		PdfPCell cellF = new PdfPCell();
		cellF = new PdfPCell(new Phrase(String.format("%.3f", data), PdfStylAttributes.BIG_FONT));

		cellF.setBorder(Rectangle.NO_BORDER);
		table.addCell(cellF);
	}

	public void addToTableValueBigRed(PdfPTable table, Float data) {
		PdfPCell cellF = new PdfPCell();
		cellF = new PdfPCell(new Phrase(String.format("%.3f", data), PdfStylAttributes.OVERWRITE));

		cellF.setBorder(Rectangle.NO_BORDER);
		table.addCell(cellF);
	}

	public void addToTableValueBold(PdfPTable table, String data) {
		PdfPCell cellF = new PdfPCell();
		cellF.addElement(new Phrase(data, PdfStylAttributes.NORMAL_BOLD));
		cellF.setBorder(Rectangle.NO_BORDER);
		table.addCell(cellF);
	}

	public void addToTableValueBoldRedNew(PdfPTable table, String data) {
		PdfPCell cellF = new PdfPCell();
		// cellF.addElement(new Phrase(data, PdfStylAttributes.NORMAL_BOLD));
		cellF.addElement(new Phrase(data, PdfStylAttributes.RED_FONT));
		cellF.setBorder(Rectangle.NO_BORDER);
		table.addCell(cellF);
	}

	public void addToTableValueBoldPdf(PdfPTable table, String data) {
		PdfPCell cellF = new PdfPCell();
		Paragraph p = new Paragraph();
		// table.setWidthPercentage(100);
		p = new Paragraph(data, PdfStylAttributes.NORMAL_FONT1_BOLD);
		p.setAlignment(Element.ALIGN_CENTER);
		cellF.addElement(p);
		// cellF.addElement(new Phrase(data,
		// PdfStylAttributes.NORMAL_FONT1_BOLD));
		cellF.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellF.setBorder(Rectangle.NO_BORDER);
		table.addCell(cellF);
	}

	public void addToTableValueBoldPdf2(PdfPTable table, String data, Integer value) {
		PdfPCell cellF = new PdfPCell();
		// cellF.setBorderWidth(30);
		cellF.addElement(new Phrase(data, PdfStylAttributes.NORMAL_FONT1_BOLD));
		cellF.setBackgroundColor(BaseColor.GRAY);

		cellF.setBorder(Rectangle.BOX);

		table.addCell(cellF);
	}

	public void addToTableValueBoldPdf22(PdfPTable table1, String data, Integer value) {
		PdfPCell cellF = new PdfPCell();
		// cellF.setBorderWidth(30);
		cellF.addElement(new Phrase(data, PdfStylAttributes.NORMAL_FONT1_BOLD));
		cellF.setBackgroundColor(BaseColor.GRAY);

		cellF.setBorder(Rectangle.BOX);

		table1.addCell(cellF);
	}

	public void addToTableValueBoldSmall(PdfPTable table, Float data) {
		PdfPCell cell = new PdfPCell();
		cell = new PdfPCell(new Phrase(String.format("%.3f", data), PdfStylAttributes.SMALL_BOLD));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
	}

	public void addToTableValueBoldSmall(PdfPTable table, String data) {
		PdfPCell cellF = new PdfPCell();
		cellF.addElement(new Phrase(data, PdfStylAttributes.SMALL_BOLD));
		cellF.setBorder(Rectangle.NO_BORDER);
		table.addCell(cellF);
	}

	public void addToTableHeaderVal(PdfPTable table, String data) {
		PdfPCell cell = new PdfPCell();
		cell.addElement(new Phrase(data, PdfStylAttributes.NORMAL_BOLD));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(4);
		table.addCell(cell);
	}

	public void addToTableHeaderDetBold(PdfPTable table, String data) {
		PdfPCell cell = new PdfPCell();
		cell.addElement(new Phrase(data, PdfStylAttributes.NORMAL_BOLD));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(4);
		table.addCell(cell);
	}

	public void addEmptyRows(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell = new PdfPCell(new Phrase("\n"));
		cell.setColspan(4);
		cell.setBorder(Rectangle.BOTTOM);
		table.addCell(cell);
	}

	public void addEmptyRowsWithoutBorder(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell = new PdfPCell(new Phrase("\n"));
		cell.setColspan(4);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
	}

	public Paragraph getParagraph() {
		Paragraph paragraph = new Paragraph();
		paragraph.setFont(PdfStylAttributes.NORMAL_FONT);
		addEmptyLine(paragraph, 1);
		return paragraph;
	}

}
