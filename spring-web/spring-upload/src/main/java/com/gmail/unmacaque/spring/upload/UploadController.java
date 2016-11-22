package com.gmail.unmacaque.spring.upload;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

	private final Path tempDirectory;

	public UploadController() throws IOException {
		Set<PosixFilePermission> permissionSet = PosixFilePermissions.fromString("rwxr-xr-x");
		FileAttribute<?> fileAttribute = PosixFilePermissions.asFileAttribute(permissionSet);
		tempDirectory = Files.createTempDirectory("upload", fileAttribute);
	}

	@GetMapping
	public String getUpload() {
		return "index";
	}

	@PostMapping
	public String postUpload(@RequestParam("aFile") MultipartFile file, ModelMap modelMap) {
		if (file.isEmpty()) {
			modelMap.addAttribute("error", "File is empty");
			return "index";
		}
		try {
			byte[] bytes = file.getBytes();
			FileOutputStream fileOutputStream = new FileOutputStream(new File(tempDirectory.toFile(), file.getOriginalFilename()));
			BufferedOutputStream stream = new BufferedOutputStream(fileOutputStream);
			stream.write(bytes);
			stream.close();
			modelMap.addAttribute("filename", file.getOriginalFilename());
			modelMap.addAttribute("filesize", file.getSize());
			modelMap.addAttribute("filetype", file.getContentType());
		} catch (Exception e) {
			modelMap.addAttribute("error", e.getMessage());
		}
		return "index";
	}

	@GetMapping(value = "/download", produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE })
	@ResponseBody
	public FileSystemResource download(@RequestParam("filename") String filename, HttpServletResponse response) {
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		return new FileSystemResource(tempDirectory + "/" + filename);
	}
}
