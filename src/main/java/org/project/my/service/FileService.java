package org.project.my.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	String fileUpLoad(MultipartFile file) throws Exception;
}
