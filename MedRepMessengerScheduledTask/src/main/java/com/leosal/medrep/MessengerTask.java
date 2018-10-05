package com.leosal.medrep;

import java.io.File;

import org.springframework.boot.CommandLineRunner;

public class MessengerTask implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		
		if(args.length > 2) {
			String fileName = args[1];
			String fileExtension = args[2];
			
			File f = new File("D:\\1\\" + fileName + "." + fileExtension);
			
			f.createNewFile();
		}
		System.out.println("MessengerTask executed...");
		
	}

}
