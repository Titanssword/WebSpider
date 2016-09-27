package com.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.test.Project2AllStock.HttpDownloadUtility;


/*
 * DownLoadFile from the Internet using Java
 * 
 * Written by CJ
 * 
 * 2016-9-27
 * 
 */
public class DownLoadFileUsingJava {
	public static class HttpDownloadUtility {
	    private static final int BUFFER_SIZE = 4096;
	 
	    
	    public static void downloadFile(String fileURL, String saveDir,String fileName)
	            throws IOException {
	        URL url = new URL(fileURL);
	        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
	        int responseCode = httpConn.getResponseCode();
	 
	        // always check HTTP response code first
	        if (responseCode == HttpURLConnection.HTTP_OK) {
	            //String fileName = "";
	            String disposition = httpConn.getHeaderField("Content-Disposition");
	            String contentType = httpConn.getContentType();
	            int contentLength = httpConn.getContentLength();
	         
	            System.out.println("Content-Type = " + contentType);
	            System.out.println("Content-Disposition = " + disposition);
	            System.out.println("Content-Length = " + contentLength);
	            System.out.println("fileName = " + fileName);
	 
	            // opens input stream from the HTTP connection
	            InputStream inputStream = httpConn.getInputStream();
	            String saveFilePath = saveDir + File.separator + fileName;
	             
	            // opens an output stream to save into file
	            FileOutputStream outputStream = new FileOutputStream(saveFilePath);
	 
	            int bytesRead = -1;
	            byte[] buffer = new byte[BUFFER_SIZE];
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	                outputStream.write(buffer, 0, bytesRead);
	            }
	 
	            outputStream.close();
	            inputStream.close();
	 
	            System.out.println("File downloaded");
	        } else {
	            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
	        }
	        httpConn.disconnect();
	    }
	}
	public static void main(String[] args) {
		
	    		String fileURL = "http://apache.fayea.com//httpd/mod_fcgid/mod_fcgid-2.3.9.tar.gz";
	    		String saveDir = "D:/Download2";
	    		String filename="mod_fcgid-2.3.9.tar.gz";
	    		try {
	                HttpDownloadUtility.downloadFile(fileURL, saveDir,filename);
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	    		
	    	}
	    
	   
	   
}

