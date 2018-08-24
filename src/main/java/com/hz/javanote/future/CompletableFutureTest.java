package com.hz.javanote.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CompletableFutureTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.out.println(getPriceAsync("apple").get());
	}

	public static Future<Double> getPriceAsync(String product) {
		CompletableFuture<Double> futurePrice = new CompletableFuture<>();
		new Thread(() -> {
			double price = calculatePrice(product);
			futurePrice.complete(price);
		}).start();
		return futurePrice;
	}

	public static double calculatePrice(String product) {
		double ret = 0.0;
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		switch (product) {
		case "apple":
			ret = 1.8;
			break;
		case "pear":
			ret = 2.5;
			break;
		}

		return ret;
	}
}
