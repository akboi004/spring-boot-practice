package com.example.demo.config;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPOutputStream;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

public class CustomHttpServletResponseWrapper extends HttpServletResponseWrapper {

	private GZIPOutputStream gzipOutputStream;
	private ServletOutputStream servletOutputStream;
	private PrintWriter printWriter;

	public CustomHttpServletResponseWrapper(HttpServletResponse response) throws IOException {
		super(response);
		this.gzipOutputStream = new GZIPOutputStream(response.getOutputStream());
	}

	@Override
	public ServletOutputStream getOutputStream() {
		if (this.servletOutputStream == null) {
			this.servletOutputStream = new CustomerServletOutputStream(this.gzipOutputStream);
		}
		return this.servletOutputStream;
	}

	@Override
	public PrintWriter getWriter() throws UnsupportedEncodingException {
		if (this.printWriter == null) {
			this.printWriter = new PrintWriter(new OutputStreamWriter(this.gzipOutputStream, getCharacterEncoding()));
		}
		return this.printWriter;
	}

	@Override
	public void flushBuffer() throws IOException {
		if (this.printWriter != null) {
			this.printWriter.flush();
		}
		if (this.servletOutputStream != null) {
			this.servletOutputStream.flush();
		}
		this.gzipOutputStream.flush();
	}

	public void close() throws IOException {
		this.gzipOutputStream.close();
	}

}
