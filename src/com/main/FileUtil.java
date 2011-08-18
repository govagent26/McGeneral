package com.main;

import java.nio.channels.FileChannel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The <b>FileUtil</b> class contains various file utilities.
 */
public class FileUtil {

	/**
	 * The {@link #copyDirectory(File, File)} method is called to copy entire
	 * directories from one location to another.
	 *
	 * @param inDir the source directory name
	 * @param outDir the target directory name
	 */
    public static void copyDirectory (File inDir, File outDir) throws IOException {
    	File[] files = inDir.listFiles();
    	
    	if (files == null) {
    		throw new IOException("Failed to list contents of " + inDir);
    	}
    	if (outDir.exists()) {
    		if (outDir.isDirectory() == false) {
    			throw new IOException("Destination '" + outDir + "' exists but is not a directory");
    		}
    	}
    	else if (outDir.mkdirs() == false) {
    		throw new IOException("Destination '" + outDir + "' directory cannot be created");
    	}
    	if (outDir.canWrite() == false) {
    		throw new IOException("Destination '" + outDir + "' cannot be written to");
    	}
    	for (File file : files) {
    		File copiedFile = new File(outDir, file.getName());
    		if (file.isDirectory()) {
    			copyDirectory(file, copiedFile);
    		}
    		else {
    			copyFile(file, copiedFile);
    		}
    	}
    	outDir.setLastModified(inDir.lastModified());
    }
    
	/**
	 * The {@link #copyFile(File, File)} method is called to copy entire files from
	 * from location to another.
	 *
	 * @param inFile the source file name
	 * @param outFile the target file name
	 * @return true, on success
	 */
    public static boolean copyFile(File inFile, File outFile) {
        if (!inFile.exists()) {
            return false;
        }
        FileChannel in = null;
        FileChannel out = null;

        try {
            in = new FileInputStream(inFile).getChannel();
            out = new FileOutputStream(outFile).getChannel();

            long pos = 0;
            long size = in.size();

            while (pos < size) {
                 pos += in.transferTo(pos, 10*1024*1024, out);
            }
        } catch (IOException ioe) {
            return false;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException ioe) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * The {@link FileUtil#writeFile(File, String)} method is called to write a message
     * to a file.
     * 
     * @param file where to write the message to
     * @param message what is written to the file
     */
    public static void writeFile(File file, String message) {
    	try {
    	    BufferedWriter out = new BufferedWriter(new FileWriter(file));
    	    out.write(message);
    	    out.close();
    	} catch (IOException e) {
    		
    	}
    	//TODO fix this method
    }
}