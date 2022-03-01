package com.pos.print.server.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;

public class PrinterFiles {

	public PrinterFiles() {
		// TODO Auto-generated constructor stub
		
		
	}
	
	
	
	public  File[] dirListByDescendingDate(String url, final String preFijo, final String ext) {
	    File files[] = new File(url).listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.toUpperCase().startsWith(preFijo.toUpperCase()) && name.toUpperCase().endsWith(ext.toUpperCase());
			}
		});
	    if (files!=null){
	    	 Arrays.sort( files, new Comparator()
			    {
			      public int compare(final Object o1, final Object o2) {
			        return new Long(((File)o1).lastModified()).compareTo
			             (new Long(((File) o2).lastModified()));
			      }
			    }); 
	    }
	    return files;
	  }  
	
	
	 public static File[] dirListByAscendingDate(File folder) {
		    if (!folder.isDirectory()) {
		      return null;
		    }
		    File files[] = folder.listFiles();
		    Arrays.sort( files, new Comparator()
		    {
		      public int compare(final Object o1, final Object o2) {
		        return new Long(((File)o1).lastModified()).compareTo
		             (new Long(((File) o2).lastModified()));
		      }
		    }); 
		    return files;
		  }  
	 
	 public static File[] dirListByDescendingDate(File folder) {
		    if (!folder.isDirectory()) {
		      return null;
		    }
		    File files[] = folder.listFiles();
		    Arrays.sort( files, new Comparator()
		    {
		      public int compare(final Object o1, final Object o2) {
		        return new Long(((File)o2).lastModified()).compareTo
		             (new Long(((File) o1).lastModified()));
		      }
		    }); 
		    return files;
		  }  

}
