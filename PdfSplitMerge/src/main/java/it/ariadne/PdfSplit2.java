package it.ariadne;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfSplit2 {

	public static void split (String pdfFile, String excelFile, String month, String year, String folderPath) throws Exception{
		
		File myPdf = new File(pdfFile);
		
		try {
			PDDocument document = PDDocument.load(myPdf);
			Splitter splitter = new Splitter();
			PDFTextStripper pdfStripper = new PDFTextStripper();
			PDFMergerUtility merger = new PDFMergerUtility();
			List<PDDocument> splittedDocuments = splitter.split(document);
			
			Map<String,String> mappa = ReadExcel.readXLSFile(excelFile);
			
			PDDocument firstDoc = null;
			
			for (String n: mappa.keySet()) {
			
				String pass = mappa.get(n);
				n = n.toUpperCase();

				firstDoc = null;
				
				int theres = 0;
				int i = 0;
				
			
				for (PDDocument p: splittedDocuments) {
				    
					String allText = pdfStripper.getText(p);
					
					if(allText.indexOf(n) > 0) {
						
						if(theres==0) {
							firstDoc = p;
							theres++;
						}else {
							merger.appendDocument(firstDoc, p);
						}	
						
					}
					
					i++;
					
					if(i == splittedDocuments.size() && firstDoc != null) {
						n = n.toLowerCase();
						String nomeDipendente = n.replaceAll(" ", "_");
						
//						AccessPermission accessPermission = new AccessPermission();
//						StandardProtectionPolicy spp = new StandardProtectionPolicy(pass,pass,accessPermission);
//						spp.setEncryptionKeyLength(128);
//						spp.setPermissions(accessPermission);
//						
//						firstDoc.protect(spp);
						
//						String zipName = folderPath + "/cedolino" + nomeDipendente + "_" + month + "_" + year + ".zip";
//						String newPath = folderPath + "/cedolino_" + nomeDipendente + "_" + month + "_" + year + ".pdf";
						String inputFile = folderPath + "/cedolino_" + nomeDipendente + "_" + month + "_" + year + ".pdf";
						Path path = Paths.get(inputFile);
						
						File f = new File(folderPath + "/cedolino_" + nomeDipendente + "_" + month + "_" + year + ".pdf");
						firstDoc.save(f);
						
						String compressedFile = folderPath + "/cedolino_" + nomeDipendente + "_" + month + "_" + year + ".zip";
						
						Zip.compress(f, compressedFile, pass);
						
						Files.delete(path);
						
						break;
					}
				}
			}
			document.close();
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
}
