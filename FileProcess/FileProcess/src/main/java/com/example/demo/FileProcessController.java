package com.example.demo;

import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileProcessController {
	
	@Autowired
	FileProcessService fileProcessService;
	
	@RequestMapping("fileprocess/filepath/{filePath}/appId/{appId}")  
	public List<String> processFileDirectory(@PathVariable("filePath") String filePath, @PathVariable("appId") String appid)  
	{  
		List<String> deletedFiles = fileProcessService.getRemovedFilesList(Arrays.asList("C:\\Users\\mvita\\"+filePath), appid);
		return deletedFiles;  
	}
	

}
