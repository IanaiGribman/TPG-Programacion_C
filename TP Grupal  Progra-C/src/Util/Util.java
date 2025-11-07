package Util;

import java.util.Random;

public class Util {
	private static Random random = new Random();
	
	public static void tiempoMuerto() throws InterruptedException{
		Thread.sleep(1000 + random.nextInt(2000));
	}
	
	
}
