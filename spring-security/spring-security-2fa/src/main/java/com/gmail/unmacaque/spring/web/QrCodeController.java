package com.gmail.unmacaque.spring.web;

import com.gmail.unmacaque.spring.util.QrCodeUtil;
import com.google.zxing.WriterException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.OutputStream;

@RestController
public class QrCodeController {
	@GetMapping(value = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
	public void qrcode(@RequestParam(name = "t", defaultValue = "Spring") String text, OutputStream os)
			throws IOException, WriterException {
		QrCodeUtil.writeToStream(os, text);
	}
}
