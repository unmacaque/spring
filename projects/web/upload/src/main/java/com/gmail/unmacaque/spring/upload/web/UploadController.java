package com.gmail.unmacaque.spring.upload.web;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermissions;

@Controller
@RequestMapping("/")
public class UploadController {

	private final Path tempDirectory;

	public UploadController() throws IOException {
		final var permissionSet = PosixFilePermissions.fromString("rwxr-xr-x");
		final var fileAttribute = PosixFilePermissions.asFileAttribute(permissionSet);
		tempDirectory = Files.createTempDirectory("upload", fileAttribute);
	}

	@GetMapping
	public String getUpload() {
		return "index";
	}

	@PostMapping
	public String postUpload(@RequestPart("aFile") MultipartFile file, ModelMap modelMap) {
		if (file.isEmpty()) {
			modelMap.addAttribute("error", "File is empty");
			return "index";
		}
		final String fileName = StringUtils.hasText(file.getOriginalFilename()) ? file.getOriginalFilename() : "newFile";
		final var destFile = new File(tempDirectory.toFile(), fileName).toPath();
		try {
			Files.write(destFile, file.getBytes());
			modelMap.addAttribute("filename", file.getOriginalFilename());
			modelMap.addAttribute("filesize", file.getSize());
			modelMap.addAttribute("filetype", file.getContentType());
		} catch (IOException e) {
			modelMap.addAttribute("error", e.getMessage());
		}
		return "index";
	}

	@GetMapping(value = "/download", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
	@ResponseBody
	public FileSystemResource download(@RequestParam("filename") String filename, HttpServletResponse response) {
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		return new FileSystemResource(tempDirectory + "/" + filename);
	}
}
