package com.example.demo.serviceImpl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.errorHandling.RemoteServiceNotAvailableException;
import com.example.demo.service.BackendAdapter;

@Service
public class BackendAdapterImpl implements BackendAdapter {

	@Override
	public String getBackendResponse(String param1, String param2) {
		System.out.println("Attempting to call remote service...");
		if (Math.random() < 0.7) {
			throw new RemoteServiceNotAvailableException("Remote API Failed");
		}
		return "Success: Response from remote service";
	}

	@Override
	public String getBackendResponseFallBack(RemoteServiceNotAvailableException e, String param1, String param2) {
		System.out.println("Fallback method called after retries failed: " + e.getMessage());
		return "Fallback response: Remote service is currently unavailable.";
	}

}
