package com.excel.shyam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class InvoiceController {

	@Autowired
	IFileUploaderService fileService;

	@Autowired
	IExcelDataService excelService;

	@Autowired
	InvoiceRepository repo;

	@GetMapping("/")
	public String index() {
		return "Upload Page";
	}

	@PostMapping("/uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		fileService.uploadFile(file);
		redirectAttributes.addFlashAttribute("message",
				"You have successfully uploaded '" + file.getOriginalFilename() + "' !");
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}

	@GetMapping("/saveData")
	public String saveExcelData(Model model) {
List<Invoice> excelDataAsList=excelService.getExcelDataAsList();
int noOfRecords=excelService.saveExcelData(excelDataAsList);
model.addAttribute("noOfRecords",noOfRecords);
return "Success";
	}

}
