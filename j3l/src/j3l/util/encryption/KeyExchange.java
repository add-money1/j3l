package j3l.util.encryption;

import java.math.BigInteger;

public class KeyExchange {
	
	private final static BigInteger TWO = new BigInteger(new byte[]{2});

	private final int key_length;
	private final BigInteger secret;
	
	public KeyExchange(int key_length) throws Exception {
		if(key_length < 1) throw new Exception("The key_length is not allowed to be <= 1!");
		else {
			this.key_length = key_length;
			if(key_length % 8 != 0) {
				key_length += 8 - key_length % 8;
			}
			this.secret = Generator.getRandomBigInteger(key_length / 8);
		}		
	}
	
	public BigInteger[] createGenerator() {
		BigInteger[] share = new BigInteger[2];
		boolean generate_again = false;
		
		share[0] = Generator.getRandomBigInteger(key_length / 8).abs().nextProbablePrime();		
		share[1] = Generator.getRandomBigInteger(key_length / 8).mod(share[0]);
		
		if(share[1].compareTo(TWO) < 0) {
			generate_again = true;
		}
		else {
			if(share[1].compareTo(share[0].subtract(TWO)) > 0) {
				generate_again = true;
			}
		}
		
		if(generate_again) return createGenerator();
		else return share;
	}
	
	public BigInteger getExchangedKey(BigInteger other_shared_key, BigInteger p) {
		return other_shared_key.modPow(secret, p);
	}
	
	public BigInteger getSharedKey(BigInteger g, BigInteger p) {
		return g.modPow(secret, p);
	}
}
