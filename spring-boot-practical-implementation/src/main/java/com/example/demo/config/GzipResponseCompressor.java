package com.example.demo.config;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "/pattern-1", "/*", "/api/greet/*" })
public class GzipResponseCompressor implements Filter {

	@Override
	public void init(FilterConfig filterConfig) {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		if (acceptIfGzip(httpRequest)) {
			CustomHttpServletResponseWrapper responseWrapper = new CustomHttpServletResponseWrapper(httpResponse);
			responseWrapper.setHeader("Content-Encoding", "gzip");
			chain.doFilter(request, responseWrapper);
			responseWrapper.close();
		} else {
			chain.doFilter(request, response);
		}
	}

	private boolean acceptIfGzip(HttpServletRequest httpRequest) {
		String acceptEncoding = httpRequest.getHeader("Accept-Encoding");
		return acceptEncoding != null && acceptEncoding.contains("gzip");
	}

	@Override
	public void destroy() {

	}

}
