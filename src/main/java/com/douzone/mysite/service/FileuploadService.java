package com.douzone.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileuploadService {
	private static final String SAVE_PATH="D:/upload";
	private static final String URL="/images";//저장은 자기 source 위치에 하는 것이 아니다.
	
	// 확인 할것.========================================================================//
	public String restore(MultipartFile file) {
		// TODO Auto-generated method stub
		String url = "";
		try {
		
			if (file.isEmpty()) {
				return url;
			}

			String originFileName = file.getOriginalFilename();
			String extName = originFileName.substring(originFileName.lastIndexOf(".")+1);
			String savefileName = generateSavafileName(extName);
			long filesize = file.getSize();

			System.out.println(originFileName);
			System.out.println(extName);
			System.out.println(savefileName);

			byte[] filedata = file.getBytes();
			OutputStream out=new FileOutputStream(SAVE_PATH+"/"+savefileName);
			out.write(filedata);
			out.close();
			
			url=URL+"/"+savefileName;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			new RuntimeException("upload fail");
		}

		return url;
	}

	private String generateSavafileName(String path) {
		String fileName = "";
		Calendar calender = Calendar.getInstance();

		fileName += calender.get(Calendar.YEAR);
		fileName += calender.get(Calendar.MONTH);
		fileName += calender.get(Calendar.DATE);
		fileName += calender.get(Calendar.HOUR);
		fileName += calender.get(Calendar.MINUTE);
		fileName += calender.get(Calendar.SECOND);
		fileName += calender.get(Calendar.MILLISECOND);
		fileName += ("." + path);

		return fileName;

	}

}
