package com.example.demo.serviceImpl;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

	@Async("asyncExecutor")
	public CompletableFuture<String> processTask1() throws InterruptedException {
		long startTime = System.currentTimeMillis();
		try {
			Thread.sleep(3000);
			return CompletableFuture.completedFuture("Task 1 completed");
		} finally {
			long endTime = System.currentTimeMillis();
			System.out.println("Task 1 Execution Time: " + (endTime - startTime) + "ms");
		}

	}

	@Async("asyncExecutor")
	public CompletableFuture<String> processTask2() throws InterruptedException {
		long startTime = System.currentTimeMillis();
		try {
			Thread.sleep(2000);
			return CompletableFuture.completedFuture("Task 2 completed");
		} finally {
			long endTime = System.currentTimeMillis();
			System.out.println("Task 2 Execution Time: " + (endTime - startTime) + "ms");
		}
	}

	@Async("asyncExecutor")
	public CompletableFuture<String> processTask3() throws InterruptedException {
		long startTime = System.currentTimeMillis();
		try {
			Thread.sleep(1000);
			return CompletableFuture.completedFuture("Task 3 completed");
		} finally {
			long endTime = System.currentTimeMillis();
			System.out.println("Task 3 Execution Time: " + (endTime - startTime) + "ms");
		}
	}
}
