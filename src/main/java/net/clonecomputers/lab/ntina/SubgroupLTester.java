package net.clonecomputers.lab.ntina;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

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
		int power = 1;
		File outputFile = new File("data.csv");
		int a = 0;
		while(outputFile.exists()) {
			outputFile = new File(“data-“ + ++a + ".csv");
		}
		CSVPrinter p = null;
		try {
			p = new CSVPrinter(new BufferedWriter(new FileWriter(outputFile)), CSVFormat.RFC4180.withRecordSeparator('\n'));
		} catch (IOException e) {
			System.err.println("Exception opening writing to " + outputFile);
			e.printStackTrace();
			return;
		}
		try {
		while (running) {
			if (tuple >= 1 << power) {
				p.println();
				System.out.println(power);
				++power;
			}
			long lValue = getLValue(tuple);
			p.print(lValue);
			if (tuple == Long.MAX_VALUE) {
				System.out.println("Stopping due to long overflow");
				break;
			}
			++tuple;
		}
		p.flush();
		} catch (IOException e) {
			System.err.println("Exception writing data to file");
			e.printStackTrace();
		} finally {
			try {
				p.close();
			} catch (IOException e) {
				System.err.println("Exception closing CSVPrinter!!!");
				e.printStackTrace();
			}
		}
	}
	
	public static long getLValue(long tuple) throws IllegalArgumentException {
		if (tuple <= 0) throw new IllegalArgumentException("Tuple must be a positive integer");
		long value = 0;
		int power = 0;
		int sign = 1;
		while (tuple > 0) {
			if (tuple % 2 != 0) {
				value += sign << power;
				sign *= -1;
			}
			tuple = tuple >> 1;
			++power;
		}
		return value;
	}

}
