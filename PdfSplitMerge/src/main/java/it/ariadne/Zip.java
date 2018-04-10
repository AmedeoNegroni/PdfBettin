package it.ariadne;

import java.io.File;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class Zip {

	 public static void compress(File inputFile,String compressedFile, String password) {
		 try {
		  ZipFile zipFile = new ZipFile(compressedFile);
		  ZipParameters parameters = new ZipParameters();

		  // COMP_DEFLATE is for compression
		         // COMp_STORE no compression
		  parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		  // DEFLATE_LEVEL_ULTRA = maximum compression
		  // DEFLATE_LEVEL_MAXIMUM
		  // DEFLATE_LEVEL_NORMAL = normal compression
		  // DEFLATE_LEVEL_FAST
		  // DEFLATE_LEVEL_FASTEST = fastest compression
		  parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_ULTRA);
		  parameters.setEncryptFiles(true);
		  parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
		  parameters.setPassword(password);
		  

		  // file compressed
		  zipFile.addFile(inputFile, parameters);
		  
		  //File outputFileH = new File(compressedFile);

		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		 }
	
}
