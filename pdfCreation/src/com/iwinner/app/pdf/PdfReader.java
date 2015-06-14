package com.iwinner.app.pdf;

import java.io.File;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PdfReader {
	public static void main(String[] args) {
        // TODO code application logic here
        PDDocument pd;
        ArrayList<Student> database = new ArrayList<Student>();
        try {
            File input = new File("E:\\Tech_Learn\\ImpApplications_Source\\student-InfoSystem-master\\fe2012.pdf");
        	//File input = new File("C:\\Users\\vl\\Desktop\\api_design.pdf");
            //C:\\Users\\vl\\Desktop\\api_design.pdf
            pd = PDDocument.load(input);
            
            PDFTextStripper stripper = new PDFTextStripper();
            StringBuilder sb = new StringBuilder();
            stripper.setStartPage(1);
            stripper.setEndPage(2);
            sb.append(stripper.getText(pd));
            System.out.println(sb);
        }catch(Exception e){
        	
        }
	}
}
