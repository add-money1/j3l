package j3l.util.encryption;


import java.math.BigInteger;
import java.util.Random;

public final class Generator {

	private static Random RANDOM = new Random(System.currentTimeMillis());
	
	public static synchronized BigInteger getRandomBigInteger(int amount_bytes) {
		Generator.RANDOM = new Random(System.currentTimeMillis() ^ RANDOM.nextLong());
		byte[] bytes = new byte[amount_bytes];
		for(int a=0;a<amount_bytes;a++) {
			bytes[a] = (byte)(RANDOM.nextInt() & 0xff);
			bytes[a] ^= (RANDOM.nextBoolean()) ? 1 : -1;
		}		
		return new BigInteger(bytes);
	}
	
}
