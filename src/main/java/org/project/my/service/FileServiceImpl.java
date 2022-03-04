package org.project.my.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class FileServiceImpl implements FileService{
	@Autowired
	private String saveDir;
	
	@Override
	public String fileUpLoad(MultipartFile file) throws Exception {
		String originalFilename = file.getOriginalFilename();
		if(originalFilename.equals("")) {
			return "";
		}
		String filename = System.currentTimeMillis()+"_"+originalFilename;
		File f = new File(saveDir, filename);
		file.transferTo(f);
		return filename;
	}

}
