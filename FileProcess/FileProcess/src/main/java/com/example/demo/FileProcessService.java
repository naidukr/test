package com.example.demo;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service
public class FileProcessService {
	
	public static List<String> removedFiles = new ArrayList<String>();
	
	public List<String> getRemovedFilesList(List<String> filePathList, String searchString){
		List<Path> matchedList = new ArrayList<Path>();
		for(String filePath : filePathList) {
			System.out.println("File Path: "+ filePath );
			Path path = Paths.get(filePath);
			try (Stream<Path> walk = Files.walk(path)) {
		        matchedList = walk.filter(file ->
		         								file.getFileName().toString().contains(searchString)
		         								|| file.getFileName().toString().equals(searchString.substring(searchString.length()-3)))
		        								.collect(Collectors.toList());
		       
		        matchedList.forEach(file ->{
									        if(file.toFile().isDirectory()) {
										        deleteDirectory(file.toFile());
										        System.out.println("Directory Name Deleted: "+ file.toFile().getAbsolutePath());
										        removedFiles.add("Directory Name Deleted: "+ file.toFile().getAbsolutePath());
									        }else {
										        file.toFile().delete();
										        System.out.println("File Name Deleted: "+ file.toFile().getAbsolutePath());
										        removedFiles.add("File Name Deleted: "+ file.toFile().getAbsolutePath());
									        }
	        							});
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return removedFiles;
	}
	
	
	public static void deleteDirectory(File file)
	{
        for (File subfile : file.listFiles()) {
            if (subfile.isDirectory()) {
                deleteDirectory(subfile);
            }
            subfile.delete();
        }
        file.delete();
	}
	

}
