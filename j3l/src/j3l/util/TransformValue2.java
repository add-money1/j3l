package j3l.util;

/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2016.06.07_0
 * @author Johannes B. Latzel
 */
public final class TransformValue2 {
		
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static short toShort(byte[] buffer) {
		return (short)(
			((buffer[0] << 8) & 0xff00)
			+ (buffer[1] & 0xff)
		);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static int toInteger(byte[] buffer) {
		return (
			((buffer[0] << 24) & 0xff000000)
			+ ((buffer[1] << 16) & 0x00ff0000)
			+ ((buffer[2] << 8) & 0x0000ff00)
			+ (buffer[3] & 0x000000ff)
		);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static long toLong(byte[] buffer) {
		return (
			(((long)(buffer[0]) << 56) & 0xff00000000000000L)
			+ (((long)(buffer[1]) << 48) & 0x00ff000000000000L)
			+ (((long)(buffer[2]) << 40) & 0x0000ff0000000000L)
			+ (((long)(buffer[3]) << 32) & 0x000000ff00000000L)
			+ (((long)(buffer[4]) << 24) & 0x00000000ff000000L)
			+ (((long)(buffer[5]) << 16) & 0x0000000000ff0000L)
			+ (((long)(buffer[6]) << 8) & 0x000000000000ff00L)
			+ (buffer[7] & 0x00000000000000ffL)
		);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static byte[] toByteArray(short s) {
		return new byte[] {
			(byte)(s >> 8),
			(byte)s
		};
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static byte[] toByteArray(short s, byte[] buffer) {
		buffer[0] = (byte)(s >> 8);
		buffer[1] = (byte)s;
		return buffer;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static byte[] toByteArray(int i) {
		return new byte[] {
			(byte)(i >> 24),
			(byte)(i >> 16),
			(byte)(i >> 8),
			(byte)i
		};
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static byte[] toByteArray(int i, byte[] buffer) {
		buffer[0] = (byte)(i >> 24) ;
		buffer[1] = (byte)(i >> 16) ;
		buffer[2] = (byte)(i >> 8) ;
		buffer[3] = (byte)i ;
		return buffer;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static byte[] toByteArray(long l) {
		return new byte[] {
			(byte)(l >> 56),
			(byte)(l >> 48),
			(byte)(l >> 40),
			(byte)(l >> 32),
			(byte)(l >> 24),
			(byte)(l >> 16),
			(byte)(l >> 8),
			(byte)l
		};
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static byte[] toByteArray(long l, byte[] buffer) {
		buffer[0] = (byte)(l >> 56);
		buffer[1] = (byte)(l >> 48);
		buffer[2] = (byte)(l >> 40);
		buffer[3] = (byte)(l >> 32);
		buffer[4] = (byte)(l >> 24);
		buffer[5] = (byte)(l >> 16);
		buffer[6] = (byte)(l >> 8);
		buffer[7] = (byte)l ;
		return buffer;
	}
	
}
