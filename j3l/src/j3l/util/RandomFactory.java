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
		return new Random(
				Instant.now().toEpochMilli()
				^ ( (long)(new Object()).hashCode() << 32 )
				^ (new Object()).hashCode()
		);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static Random createRandom(Random random) {
		long seed = (
				Instant.now().toEpochMilli()
				^ ( (long)(new Object()).hashCode() << 32 )
				^ (new Object()).hashCode()
		);
		for(int a=0,n=random.nextInt(100);a<n;a++) {
			seed ^= random.nextLong();
		}
		return new Random(seed);
	}

}
