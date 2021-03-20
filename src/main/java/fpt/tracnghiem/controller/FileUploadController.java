package fpt.tracnghiem.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import fpt.tracnghiem.service.CauHoiService;
import fpt.tracnghiem.service.ExcelService;

@Controller
public class FileUploadController {
	@Autowired
	CauHoiService cauHoiService;
	@Autowired
	ExcelService excelService;
	
	public static String uploadDirectory = System.getProperty("user.dir") +"\\src\\main\\resources\\static\\excel\\";
	@RequestMapping("/addQuestionExcel/{idDe}")
	public String UploadPage(Model model,@PathVariable int idDe) {
		model.addAttribute("idDe",idDe);
		return "/creator/question/uploadview";
	}
	
	@RequestMapping("/upload/{idDe}")
	public String upload(@PathVariable(name = "idDe") Integer idDe,Model model, @RequestParam("files") MultipartFile[] files) throws EncryptedDocumentException, InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
		new File(uploadDirectory).mkdir();
		StringBuilder fileNames = new StringBuilder();
		for(MultipartFile file: files) {
			System.out.println(file.getOriginalFilename());
			model.addAttribute("path", file.getOriginalFilename()); 
			Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
			fileNames.append(file.getOriginalFilename());
			try {
				Files.write(fileNameAndPath, file.getBytes() );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				//xử lý lỗi nếu thêm không thành công
				Workbook workbook = WorkbookFactory.create(new File(uploadDirectory+file.getOriginalFilename()));
				excelService.upDuLieu(workbook,idDe);
			}catch (Exception e) {
				model.addAttribute("msg", "Thêm câu hỏi không thành công, lỗi:" +e.toString()); 
				model.addAttribute("idDe", idDe);
				return "/creator/question/uploadstatusview";
			}
			
			
		}
		model.addAttribute("msg", "Thêm câu hỏi thành công"); 
		model.addAttribute("idDe", idDe);
		return "/creator/question/uploadstatusview";
		
	}

	
	
}
