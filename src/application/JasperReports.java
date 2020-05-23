package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;


public class JasperReports {

	public void topTen(List<BookReport1> list) throws JRException, IOException {
		
		BasicConfigurator.configure();
		String file = "topten.pdf";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("BookDataSource", list);
		JasperReport report = (JasperReport) JRLoader.loadObjectFromFile("F:\\CSED\\Java Projects\\DB Project\\TopSellingBooks.jasper");
		JasperPrint jasperprint = JasperFillManager.fillReport(report,parameters ,new JREmptyDataSource());
		JasperExportManager.exportReportToPdfFile(jasperprint, file);
		
	}
	
	public void totalSales(List<BookReport1> list) throws JRException, IOException {
		BasicConfigurator.configure();
		String file = "totalSales.pdf";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("BookDataSource", list);
		JasperReport report = (JasperReport) JRLoader.loadObjectFromFile("F:\\CSED\\Java Projects\\DB Project\\TotalSales.jasper");
		JasperPrint jasperprint = JasperFillManager.fillReport(report,parameters ,new JREmptyDataSource());
		JasperExportManager.exportReportToPdfFile(jasperprint, file);
	}
	
	public void topPurchasing(List<UserReport> list) throws JRException, IOException {
		BasicConfigurator.configure();
		String file = "top5Users.pdf";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("BookDataSource", list);
		JasperReport report = (JasperReport) JRLoader.loadObjectFromFile("F:\\CSED\\Java Projects\\DB Project\\Purchases.jasper");
		JasperPrint jasperprint = JasperFillManager.fillReport(report,parameters ,new JREmptyDataSource());
		JasperExportManager.exportReportToPdfFile(jasperprint, file);
	}
}