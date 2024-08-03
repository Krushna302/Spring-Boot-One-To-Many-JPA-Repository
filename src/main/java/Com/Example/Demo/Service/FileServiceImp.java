package Com.Example.Demo.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Com.Example.Demo.Entity.FileData;
import Com.Example.Demo.Repository.FileRepository;

@Service
public class FileServiceImp implements FileServiceInter {

	
	
	@Autowired
	private FileRepository fileRepository;
	
	
	
	
	@Override
	public void uploadFile(MultipartFile file) {
		
		
		FileData filedata = new FileData();    // get access that way using outside try block

		String fileUploadingDri1 ="C:\\Test\\filedemo\\";
		
		String fileUploadingDri2 ="C:\\Test\\fileHandling\\";
		
		
		
		try {
			
		fileUploadOnServerPath(file.getBytes(),file.getOriginalFilename(),fileUploadingDri1);

		fileUploadOnServerPath(file.getBytes(),file.getOriginalFilename(),fileUploadingDri2);
		
	//	fileUploadOnServerPath(file.getBytes(),file.getOriginalFilename());  // declare file upload method
		
		
		filedata.setFileName(file.getOriginalFilename());
		
		filedata.setFileType(file.getContentType());
		
		filedata.setFileSize(file.getSize());
		
			filedata.setFileData(file.getBytes());
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		
		
	//	fileRepository.save(filedata);
		
	}
	
	
	private String fileUploadOnServerPath(byte[] filedata ,String filename ) {
		
		
		int i =filename.lastIndexOf(".");
		
		String ext = filename.substring(i);   // begging index store 
		
		//Path path =Paths.get("C:\\Demo\\public.jpg");  // single file use

		
	//	Path path =Paths.get("C:\\Demo\\public"+ext);// / multiple choose file send code ex:- txt,jpg,png, multiple 
		
	//	Path path =Paths.get("C:"+File.separator+"Test"+File.separator +"public"+ext); // 
		
		Path path =Paths.get("C:\\Test\\fileHandling\\demo" + ext);
		
		
		try {
			
			Files.write(path, filedata);
			
			
		} catch (IOException e) {
			
			
			e.printStackTrace();
		}
		
		
		
		return"File writing done on server";
		
	}

	
	private String fileUploadOnServerPath(byte[] filedata ,String filename ,String directory){
		
		int i =filename.lastIndexOf(".");
		
		String ext=filename.substring(i);
		
	
		Path path =Paths.get(directory + "Testt" + ext);
		
		try {
			Files.write(path, filedata);
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return"File writing done on server";

		
		
	}
	
	
	

}
