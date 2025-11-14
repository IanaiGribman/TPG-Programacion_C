package util;

import java.util.Random;

public class Util {
	private static Random random = new Random();
	
	public static void tiempoMuerto(int base, int extra) throws InterruptedException {
		Thread.sleep(base + random.nextInt(extra));
	}
	public static void tiempoMuerto(int tiempo) throws InterruptedException{
		tiempoMuerto(tiempo,0);
	}

	public static void tiempoMuerto() throws InterruptedException{
		tiempoMuerto(1000,1000);
	}
	
	public static int numeroAleatorio(int base, int max) {
		return base + random.nextInt(max-base);
	}
	
}
