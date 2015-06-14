package com.iwinner.jdbc.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.iwinner.jdbc.utils.DbUtils;

public class PDFGenerator {

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			String filepath = request.getParameter("path");
			String tablename = request.getParameter("tablename");
			Document document = new Document(PageSize.A4.rotate());
			PdfWriter.getInstance(document, new FileOutputStream(filepath
					+ "\\" + tablename + ".pdf"));
			Connection conn = DbUtils.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from " + tablename);
			ResultSetMetaData rsm = rs.getMetaData();
			document.open();
			Paragraph p = new Paragraph();
			Font f = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD,
					BaseColor.DARK_GRAY);
			p.setFont(f);
			p.setSpacingBefore(10.0f);
			p.add("LIF STYPEn\n");
			p.add("Hyderabad )\n\n");
			p.add("\n\n\n");
			p.setAlignment(1);
			document.add(p);
			PdfPTable table = new PdfPTable(rsm.getColumnCount());
			float[] width = { 10.0f, 10.0f, 4.0f, 8.0f, 8.0f, 8.0f, 6.0f, 6.0f,
					6.0f, 6.0f, .0f, 6.0f, 6.0f, 6.0f, 6.0f, 8.0f, 8.0f };
			table.setTotalWidth(width);
			int i = 1;
			table.setHeaderRows(1);
			document.setMargins(2.0f, 2.0f, 15.0f, 15.0f);
			table.addCell("Ename");
			table.addCell("Desi.");
			table.addCell("Attn.");
			table.addCell("Basic");
			table.addCell("SA");
			table.addCell("TotBa.");
			table.addCell("HRA");
			table.addCell("MA");
			table.addCell("Conv.");
			table.addCell("PA");
			table.addCell("Gross");
			table.addCell("EPF@\n12%");
			table.addCell("EPF@\n13.61%");
			table.addCell("WC");
			table.addCell("Adv.");
			table.addCell("Net");
			table.addCell("CTC");
			while (rs.next()) {
				for (i = 1; i <= rsm.getColumnCount(); i++) {
					table.addCell(rs.getString(i));
				}
			}
			document.add(table);
			document.addAuthor("Vengicx");
			document.close();
		} catch (FileNotFoundException ex) {
		} catch (DocumentException ex) {
		} catch (SQLException ex) {

		}
	}
}
