package j3l.certificate;

/**
 * <p>base class for authentification-certificate</p>
 * 
 * @see {@link j3l.certificate.PublicCertificate}
 * @see {@link j3l.certificate.PrivateCertificate}
 * 
 * @since JDK 1.8
 * @version 2015.08.31_0
 * @author Johannes B. Latzel
 */
public abstract class Certificate {

	
	/**
	 * <p>publicly shared identification of the certificate</p>
	 */
	private final long public_id;
	
	
	/**
	 * <p>sets the {@link #public_id}</p>
	 *
	 * @param public_id {@link #public_id}
	 */
	protected Certificate(long public_id) {
		this.public_id = public_id;
	}
	
	
	/**
	 * <p>getter for {@link #public_id}</p>
	 *
	 * @return {@link #public_id}
	 */
	public final long getPublicID() {
		return public_id;
	}
	
}
