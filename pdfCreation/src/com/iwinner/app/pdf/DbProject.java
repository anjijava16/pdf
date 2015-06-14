/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iwinner.app.pdf;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 * 
 * @author nishad
 */
public class DbProject {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
        // TODO code application logic here
        PDDocument pd;
        ArrayList<Student> database = new ArrayList<Student>();
        try {
            File input = new File("E:\\Tech_Learn\\ImpApplications_Source\\student-InfoSystem-master\\fe2012.pdf");
            pd = PDDocument.load(input);
            
            PDFTextStripper stripper = new PDFTextStripper();
            StringBuilder sb = new StringBuilder();
            
            stripper.setStartPage(1);
            stripper.setEndPage(4);
            sb.append(stripper.getText(pd));
            
            Pattern p = Pattern.compile("(\\w)(\\d{9})(.*?)((\\d+)/(\\d{4}))",Pattern.DOTALL);
            
            Matcher m = p.matcher(sb);
            
            while(m.find()) {
                //System.out.println(m.group());
                //System.out.println("\n----\n");
                Student s = new Student();
                String student_block, tmp_name;
                
                student_block = m.group();
                
                // Regex for roll number
                Pattern p2 = Pattern.compile("\\w\\d{9}",Pattern.DOTALL);   
                Matcher m2 = p2.matcher(student_block);
                m2.find();
                s.roll = m2.group().trim();
                //System.out.println(s.roll);
                
                // Regex for finding name
                p2 = Pattern.compile("\\s{3}(.*?)\\s{2}",Pattern.DOTALL);   
                m2 = p2.matcher(student_block);
                m2.find();
                
                tmp_name = m2.group();
                
                // Strip leading and trailing whitespace
                s.name = tmp_name.trim();
                
                // Regex for iterating over individual subjects
                p2 = Pattern.compile("\\d{6}\\s\\w(.*?)\\s[P|F]\\s",Pattern.DOTALL);
                m2 = p2.matcher(student_block);
                
                while(m2.find()) {
                    String tmp;
                    tmp = m2.group().trim();                   
                    //System.out.println(tmp);
                    
                    // Get subject name
                    Pattern p3 = Pattern.compile("[A-Z](.*?)(PP|PR|TW)\\s{2}");
                    Matcher m3 = p3.matcher(tmp);
                    m3.find();
                    String sub_name = m3.group().trim();
                    
                    //System.out.println("---" + sub_name);
                    
                    // Remove PP/PR/TW from sub_name
                    StringBuilder sb_tmp;
                    sb_tmp = new StringBuilder(sub_name);
                    sb_tmp.deleteCharAt(sub_name.length()-1);
                    sb_tmp.deleteCharAt(sub_name.length()-2);
                    
                    sub_name = sb_tmp.toString().trim();
                    
                    // Final subject name
                    //System.out.println("---" + sub_name);
                    
                    s.subjects.add(sub_name);
                    
                }
                
                // Add to student db
                database.add(s);
                
                //System.out.println("\n-------\n");
            }
            
            for (Student s : database) {
                System.out.println("Name : " + s.name + "\n" + s.roll);
                for (Iterator it = s.subjects.iterator(); it.hasNext();) {
                    Object a = it.next();
                    System.out.println("--> " + a);
                }
                System.out.println("\n");
            }
            
            pd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
