package net.clonecomputers.lab.ntina;

import java.io.IOException;

public class SubgroupLTester implements Runnable {
	
	private volatile boolean running = true;

	public static void main(String[] args) throws IOException {
		SubgroupLTester subgroupLTester = new SubgroupLTester();
		Thread runner = new Thread(subgroupLTester);
		runner.start();
		System.in.read();
		subgroupLTester.running = false;
	}

	@Override
	public void run() {
		long tuple = 1;
		while (running) {
			//TODO do stuff here
			if (tuple == Long.MAX_VALUE) break;
			++tuple;
		}
	}
	
	public long getLValue(long tuple) {
		long value = 0;
		int power = 0;
		while (tuple > 0) {
			if (tuple % 2 != 0) {
				value += 1L << power;
			}
			tuple = tuple >> 1;
			++power;
		}
		return value;
	}

}
