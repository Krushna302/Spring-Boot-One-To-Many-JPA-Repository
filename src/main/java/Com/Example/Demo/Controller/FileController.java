package Com.Example.Demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import Com.Example.Demo.Service.FileServiceInter;

@RestController
public class FileController {
	
	
	
	
	
	@Autowired
	private FileServiceInter fileServiceInter;
	
	
	@GetMapping(value="/upload")
	public  String   uploadFile(@RequestPart MultipartFile file) {
		
		System.out.println("Check File Name :- "+file.getName());
		
		System.out.println("Check File Data :- "+file.getOriginalFilename());
		
		fileServiceInter.uploadFile(file);
		
		
		return "File Upload";
		

	}
	
	
	
	
	
	
	
	

}
