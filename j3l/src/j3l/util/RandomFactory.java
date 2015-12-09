package j3l.util;

import java.time.Instant;
import java.util.Random;

/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.12.04_0
 * @author Johannes B. Latzel
 */
public final class RandomFactory {
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static Random createRandom() {
		return RandomFactory.createRandom(null);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static Random createRandom(Random random) {
		return RandomFactory.createRandom(random, 100);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static Random createRandom(Random random, int number_of_rounds) {
		long seed = (
				Instant.now().toEpochMilli()
				^ ( (long)(new Object()).hashCode() << 32 )
				^ (new Object()).hashCode()
		);
		if( random != null ) {
			for(int a=0,n=random.nextInt(number_of_rounds);a<n;a++) {
				seed ^= random.nextLong();
			}
		}
		return new Random(seed);
	}

}
