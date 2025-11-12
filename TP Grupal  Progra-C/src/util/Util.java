package util;

import java.util.Random;

public class Util {
	private static Random random = new Random();
	
	public static void tiempoMuerto() throws InterruptedException{
		Thread.sleep(1000 + random.nextInt(1000));
	}
	
	public static void tiempoMuerto(int tiempo) throws InterruptedException{
		Thread.sleep(tiempo);
	}
	
	public static int numeroAleatorio(int base, int max) {
		return base + random.nextInt(max-base);
	}
	
}
