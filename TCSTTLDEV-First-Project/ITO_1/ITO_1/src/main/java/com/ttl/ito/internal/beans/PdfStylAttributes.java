package com.ttl.ito.internal.beans;

import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;

@Component
public class PdfStylAttributes {
	 public final static Font SMALL_BOLD = new Font(Font.FontFamily.TIMES_ROMAN, 7,
	            Font.BOLD);
	    public final static Font NORMAL_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 7,
	            Font.NORMAL);
	    
	    public final static Font NORMAL_FONT1 = new Font(Font.FontFamily.TIMES_ROMAN, 11,
	            Font.NORMAL);
	    
	    public final static Font NORMAL_FONT1_BOLD = new Font(Font.FontFamily.TIMES_ROMAN, 11,
	            Font.BOLD);
	    
	    
	    public final static Font NORMAL_FONT1_SML = new Font(Font.FontFamily.TIMES_ROMAN, 8,
	            Font.NORMAL);
	    
	    public final static Font BIG_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 8,
	            Font.NORMAL);
	    
	    public final static Font NORMAL_BOLD = new Font(Font.FontFamily.TIMES_ROMAN, 8,
	            Font.BOLD);
	    public final static Font BOLD_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 9,
	            Font.BOLD);
	    
	    public final static Font BLUE_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 9,
	            Font.BOLD, BaseColor.BLUE);
	    
	    public final static Font RED_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 9,
	            Font.BOLD, BaseColor.RED);
	    
	    public final static Font OVERWRITE = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD, BaseColor.RED);
	    
		public static final Font ALIGN_LEFT = new Font(Font.FontFamily.TIMES_ROMAN, 7,
	            Font.NORMAL);
}
