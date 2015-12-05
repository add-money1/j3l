package j3l.util.encryption;

import j3l.util.Generator;

import java.math.BigInteger;

public final class RSA {
	
	private final static BigInteger TWO = new BigInteger(new byte[] {2});
	
	public static RSA gengerateRSA(int key_length) {
		BigInteger e, d, N;
		
		BigInteger p = Generator.getRandomBigInteger(key_length);
		p = p.abs().nextProbablePrime();
		
		BigInteger q = Generator.getRandomBigInteger(key_length);
		q = q.abs().nextProbablePrime();
		
		N = p.multiply(q);
		
		BigInteger phi_N = N.subtract(p).subtract(q).add(BigInteger.ONE);
		
		e = RSA.getE(phi_N, key_length);
		
		d = null;
		while(d == null) {
			try {
				d = e.modInverse(phi_N);
			}
			catch(ArithmeticException e1) {
				e = RSA.getE(phi_N, key_length);
			}
		}
		return new RSA(e,d,N);
	}
	
	public static BigInteger getE(BigInteger phi_N, int key_length) {
		BigInteger e = Generator.getRandomBigInteger(key_length);
		if(e.compareTo(RSA.TWO) < 0) {
			e = e.abs().add(RSA.TWO);
		}
		e = e.mod(phi_N);
		return e;
	}
	
	private final BigInteger private_key, public_key, rsa_module;
	
	public RSA(BigInteger public_key, BigInteger rsa_module) {
		this(null, public_key, rsa_module);
	}
	
	public RSA(BigInteger private_key, BigInteger public_key, BigInteger rsa_module) {
		this.public_key = public_key;
		this.private_key = private_key;
		this.rsa_module = rsa_module;
	}
	
	public byte[] decrypt(byte[] data) throws Exception {
		if(private_key == null) throw new Exception("This RSA-instance is not able to decrypt Data.\nPlease create an RSA-instance with a generated private_key");
		
		byte[] temp_data = new byte[data.length+1];
		temp_data[0] = 0;
		for(int a=1,n=temp_data.length;a<n;a++) {
			temp_data[a] = data[a - 1]; 
		}
		
		BigInteger crypt = new BigInteger(temp_data);
		temp_data = crypt.modPow(private_key, rsa_module).toByteArray();
		
		byte[] final_data = new byte[data.length];
		for(int a=0,n=final_data.length;a<n;a++) {
			final_data[a] = temp_data[a + 1];
		}
		
		return final_data;
	}
	
	public byte[] encrypt(byte[] data) {
		byte[] temp_data = new byte[data.length+1];
		temp_data[0] = 0;
		for(int a=1,n=temp_data.length;a<n;a++) {
			temp_data[a] = data[a - 1]; 
		}
		
		BigInteger crypt = new BigInteger(temp_data);
		temp_data = crypt.modPow(public_key, rsa_module).toByteArray();
		
		byte[] final_data = new byte[data.length];
		for(int a=0,n=final_data.length;a<n;a++) {
			final_data[a] = temp_data[a + 1];
		}
		
		return final_data;
	}
	
	public BigInteger getPublicKey() {
		return public_key;
	}
	
	public BigInteger getRSAModule() {
		return rsa_module;
	}
}