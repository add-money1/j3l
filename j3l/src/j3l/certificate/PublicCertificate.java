package j3l.certificate; 

import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import j3l.exception.ChecksumException;
import j3l.util.transform.TransformValue;

/**
 * <p>AES-128 encypted certificate - can be transferred into a {@link j3l.certificate.PrivateCertificate}</p>
 * 
 * @since JDK 1.8
 * @version 2015.09.01_0
 * @author Johannes B. Latzel
 */
public final class PublicCertificate extends Certificate {

	
	/**
	 * <p>encrypted part of the certificate - contains the properties of this certificate</p>
	 */
	private final byte[] encrypted_data;
	
	
	/**
	 * <p>calls super(public_id) and saves the {@link #encrypted_data encrypted data}</p>
	 * <p>may only be called by {@link j3l.certificate.PrivateCertificate#encrypt(SecretKey, IvParameterSpec)} -
	 * any other way may result in wibbly-wobbly-data</p>
	 *
	 * @param public_id the public identification
	 * @param encrypted_data initializer for {@link #encrypted_data}
	 */
	protected PublicCertificate(long public_id, byte[] encrypted_data) {
		
		super(public_id);
		
		this.encrypted_data = encrypted_data;

	}
	

	/**
	 * <p>decrypts the encrypted part of the certificate and returns a {@link gloabl.certificate.PrivateCertificate} with the properties
	 * derived by the encrypted part of this certificate</p>
	 *
	 * @param secret_key the key used for the encryption in {@link j3l.certificate.PrivateCertificate#encrypt(SecretKey, IvParameterSpec)}
	 * @param iv_spec the initialization vector used for the encryption in {@link j3l.certificate.PrivateCertificate#encrypt(SecretKey, IvParameterSpec)}
	 * @return {@link gloabl.certificate.PrivateCertificate} with publicly visible properties derived by {@link #encrypted_data}
	 */
	public PrivateCertificate decrypt(SecretKey secret_key, IvParameterSpec iv_spec) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, ChecksumException, InvalidAlgorithmParameterException {
		
		
		Cipher encryption_cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		encryption_cipher.init(Cipher.DECRYPT_MODE, secret_key, iv_spec);
		
		
		byte[] decrypted_data = encryption_cipher.doFinal(encrypted_data);
		ByteBuffer data = ByteBuffer.allocate(decrypted_data.length);
		data.put(decrypted_data);
		data.rewind();
		

		//read raw fix-lenghted data
		byte[] raw_checksum = new byte[8];
		byte[] raw_private_id = new byte[8];
		byte[] raw_issue_date = new byte[8];
		byte[] raw_expiration_date = new byte[8];
		byte[] raw_name_length = new byte[4];
		data.get(raw_checksum).get(raw_private_id).get(raw_issue_date).get(raw_expiration_date).get(raw_name_length);
		
		//read check_sum
		long checksum = TransformValue.toLong(raw_checksum);
		
		//read fix-lenghted data
		long private_id = TransformValue.toLong(raw_private_id);
		long issue_date = TransformValue.toLong(raw_issue_date);
		long expiration_date = TransformValue.toLong(raw_expiration_date);
		int name_length = TransformValue.toInteger(raw_name_length);
		
		//read name
		byte[] raw_name = new byte[name_length];
		data.get(raw_name);
		String name = TransformValue.toString(raw_name);
		
		
		if(checkSum(private_id, issue_date, expiration_date, name_length, name) != checksum) {
			throw new ChecksumException("The checksum of this certificate is not equal to the checksum of the read data!");
		}
		
		
		return new PrivateCertificate(getPublicID(), private_id, name, issue_date, expiration_date);
	}
	
	
	/**
	 * <p>provides the checksum for the decrypted properties - uses {@link java.util.zip.CRC32}</p>
	 *
	 * @param private_id {@link j3l.certificate.PrivateCertificate#getPrivateID()}
	 * @param issue_date {@link j3l.certificate.PrivateCertificate#getIssueDate()}
	 * @param expiration_date {@link j3l.certificate.PrivateCertificate#getExpirationDate()}
	 * @param name_length the length of {@link j3l.certificate.PrivateCertificate#getName()} 
	 * @param private_id {@link j3l.certificate.PrivateCertificate#getPrivateID()}
	 * @param name {@link j3l.certificate.PrivateCertificate#getPrivateID()}
	 * 
	 * @return a 64 bit checksum
	 */
	private long checkSum(long private_id, long issue_date, long expiration_date, int name_length, String name) {

		// 64 bit + 64 bit + 64 bit + 32 bit = 28 Byte
		ByteBuffer temp_buffer = ByteBuffer.allocate(28 + (name.length() * 2));
		temp_buffer.put(TransformValue.toByteArray(private_id));
		temp_buffer.put(TransformValue.toByteArray(issue_date));
		temp_buffer.put(TransformValue.toByteArray(expiration_date));
		temp_buffer.put(TransformValue.toByteArray(name_length));
		temp_buffer.put(TransformValue.toByteArray(name));
		
		//calculate CRC32 checksum
		CRC32 crc_32 = new CRC32();
		crc_32.update(TransformValue.toByteArray(getPublicID()));
		crc_32.update(temp_buffer);
		return crc_32.getValue();
		
	}
	
}
