package com.gmail.unmacaque.spring.security.mfa.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Dimension;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class QrCodeUtil {
	public static void writeToStream(OutputStream stream, String text) throws WriterException, IOException {
		final var hints = new HashMap<EncodeHintType, Object>();
		final var ecl = ErrorCorrectionLevel.L;
		hints.put(EncodeHintType.ERROR_CORRECTION, ecl);
		hints.put(EncodeHintType.MARGIN, 0);
		writeToStream(stream, text, hints);
	}

	public static void writeToStream(OutputStream stream, String text, Map<EncodeHintType, Object> hints)
			throws WriterException, IOException {
		writeToStream(stream, text, hints, new Dimension(200, 200));
	}

	public static void writeToStream(OutputStream stream, String text, Map<EncodeHintType, Object> hints, Dimension dimension)
			throws WriterException, IOException {
		writeToStream(stream, text, hints, dimension, "png");
	}

	public static void writeToStream(OutputStream stream, String text, Map<EncodeHintType, Object> hints, Dimension dimension,
									 String format) throws WriterException, IOException {
		final var writer = new QRCodeWriter();
		final var matrix = writer.encode(text, BarcodeFormat.QR_CODE, dimension.getHeight(), dimension.getWidth(), hints);
		MatrixToImageWriter.writeToStream(matrix, format, stream);
	}
}
