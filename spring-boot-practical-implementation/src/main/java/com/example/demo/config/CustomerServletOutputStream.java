package com.example.demo.config;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;

public class CustomerServletOutputStream extends ServletOutputStream {

	private GZIPOutputStream gzipOutputStream;

	public CustomerServletOutputStream(GZIPOutputStream gzipOutputStream) {
		this.gzipOutputStream = gzipOutputStream;
	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public void setWriteListener(WriteListener listener) {

	}

	@Override
	public void write(int b) throws IOException {
		this.gzipOutputStream.write(b);
	}

}
