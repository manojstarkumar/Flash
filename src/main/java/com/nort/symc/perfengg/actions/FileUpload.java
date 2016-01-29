package com.nort.symc.perfengg.actions;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;
import com.nort.symc.perfengg.utils.Commons;
import com.nort.symc.perfengg.utils.Constants;

public class FileUpload {
	
	public String uploadNewFile(MultipartFile file) {
		String name ="data.csv";
        String dataFolder = Commons.generateRandomString(16);
        
        //generate a random folder name and upload the file there
        File dir = new File(Constants.repoLocation + File.separator + dataFolder);
        if (!dir.exists())
            dir.mkdirs();
        
         // Create the file on server
        File serverFile = new File(dir.getAbsolutePath()
                + File.separator + name);
        
		if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                //return random folder
                return dataFolder;
            } catch (Exception e) {
            	 //log e
            	return "Fail";
            }
        } else {
        	//since no data file copy the default file to the new location and return it
        	// this is to ensure that we can delete the folder after is copied to the jenkins workspace
        	File defaultFile = new File(Constants.repoLocation + File.separator + Constants.defaultDataFolder + File.separator + name);
        	try {
				Files.copy(defaultFile, serverFile);
				return dataFolder;
			} catch (Exception e) {
				//log e
				return "Fail";
			}
        }
	}

}
