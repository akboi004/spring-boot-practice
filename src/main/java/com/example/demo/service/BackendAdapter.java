package com.example.demo.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import com.example.demo.errorHandling.RemoteServiceNotAvailableException;

public interface BackendAdapter {

	@Retryable(retryFor = RemoteServiceNotAvailableException.class, maxAttempts = 3, backoff = @Backoff(delay = 1000))
	public String getBackendResponse(String param1, String param2);

	@Recover
	public String getBackendResponseFallBack(RemoteServiceNotAvailableException e, String param1, String param2);

}
