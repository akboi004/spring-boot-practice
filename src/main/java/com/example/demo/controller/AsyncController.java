package com.example.demo.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.serviceImpl.AsyncService;

@RestController
public class AsyncController {

	@Autowired
	AsyncService asyncService;

	@GetMapping("/asyncCall")
	public String startAsyncTasks() throws InterruptedException, ExecutionException, TimeoutException {
		long startTime = System.currentTimeMillis();

		CompletableFuture<String> task1 = asyncService.processTask1();
		CompletableFuture<String> task2 = asyncService.processTask2();
		CompletableFuture<String> task3 = asyncService.processTask3();

		CompletableFuture.allOf(task1, task2, task3).get(5, TimeUnit.SECONDS);
		long endTime = System.currentTimeMillis();
		System.out.println("Total Execution Time: " + (endTime - startTime) + "ms");
		return task1.get() + " | " + task2.get() + " | " + task3.get();
	}
}
