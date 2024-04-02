package com.kh.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy{

	// 원본 파일을 전달 받아서 파일 명 수정 작업 후 수정된 파일을 반환 시켜주는 메소드
	@Override
	public File rename(File originFile) {
		// 원본파일명("sampleImg.jpg")
		String originName = originFile.getName();
//		넘어온 원본파일의 파일 명 꺼냄
		
		// 수정파일명("2024040215213200012345.jpg")
		// 파일 업로드 시간(년월일시분초) + 랜덤 5자리 + 원본확장자(jpg)
		
		// 1. 파일 업로드 시간
		String currentTime = new SimpleDateFormat("yyyyDDddHHmmss").format(new Date());
		
		// 2. 5자리 랜덤값
		int ranNum = (int)(Math.random()*90000 + 10000);
		
		// 3. 원본파일확장자
		String ext = originName.substring(originName.lastIndexOf("."));
//				indexof해도 되는데 
				
		// 모두 하나로
		String changeName = currentTime  + ranNum + ext;
		
		File changeFile = new File(originFile.getParent(), changeName);
//		오리진 파일이 있는 부모 폴더, 즉 파일이 존재하는 폴더
		
		return changeFile;
	}
	
	
}
