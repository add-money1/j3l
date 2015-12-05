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

import j3l.util.interfaces.Nameable;
import j3l.util.transform.TransformValue;

/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.08.30_0
 * @author Johannes B. Latzel
 */
public final class PrivateCertificate extends Certificate implements Nameable {
	
	
	private final long private_id;
	
	private final String name;
	
	private final long issue_date;
	
	private final long expiration_date;
	
	private ByteBuffer private_data;
	
	private long checksum = 0;
	

	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 * @throws IllegalArgumentException
	 */
	public PrivateCertificate(long public_id, long private_id, String name, long issue_date, long expiration_date) {
		super(public_id);
		
		if(name.length() <= 0) {
			throw new IllegalArgumentException("The length of name must be greater than 0!");
		}

		this.private_id = private_id;
		this.name = name;
		this.issue_date = issue_date;
		this.expiration_date = expiration_date;
		
		createData();
		
	}
	
	public PublicCertificate encrypt(SecretKey secret_key, IvParameterSpec iv_spec) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		
		Cipher encryption_cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		encryption_cipher.init(Cipher.ENCRYPT_MODE, secret_key, iv_spec);
				
		return new PublicCertificate(getPublicID(), encryption_cipher.doFinal(private_data.array()));
		
	}
	
	
	private void createData() {

		// 64 bit + 64 bit + 64 bit + 32 bit = 28 Byte
		ByteBuffer temp_buffer = ByteBuffer.allocate(28 + (getName().length() * 2));
		temp_buffer.put(TransformValue.toByteArray(getPrivateID()));
		temp_buffer.put(TransformValue.toByteArray(getIssueDate()));
		temp_buffer.put(TransformValue.toByteArray(getExpirationDate()));
		temp_buffer.put(TransformValue.toByteArray(getName().length()));
		temp_buffer.put(TransformValue.toByteArray(getName()));
		
		
		if(private_data != null) {
			private_data.clear();
		}
		else {
			//temp_buffer + 64 bit checksum
			private_data = ByteBuffer.allocate(temp_buffer.capacity() + 8);
		}		
		
		
		CRC32 crc_32 = new CRC32();
		crc_32.update(TransformValue.toByteArray(getPublicID()));
		crc_32.update(temp_buffer);
		checksum = crc_32.getValue();
		
		
		private_data.put(TransformValue.toByteArray(checksum));
		private_data.put(temp_buffer.array());
		
	}

	
	/**
	 * <p>getter for {@link #private_id}</p>
	 *
	 * @return {@link #private_id}
	 */
	public long getPrivateID() {
		return private_id;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see j3l.util.Nameable#getName()
	 */
	@Override public String getName() {
		return name;
	}
	
	
	/**
	 * <p>getter for {@link #issue_date}</p>
	 *
	 * @return {@link #issue_date}
	 */
	public long getIssueDate() {
		return issue_date;
	}
	
	
	/**
	 * <p>getter for {@link #expiration_date}</p>
	 *
	 * @return {@link #expiration_date}
	 */
	public long getExpirationDate() {
		return expiration_date;
	}
	
	
	/**
	 * <p>getter for {@link #checksum}</p>
	 *
	 * @return {@link #checksum}
	 */
	public long getCheckSum() {
		return checksum;
	}

}
