package j3l.util;


/**
 * <p>transforms primitive types to Strings, byte[], and char[], and vice versa</p>
 * <p>
 * 	e.g.:
 * 		<p>
 * 			int i = 0xff00ff00;<br>
 * 			String s = TransformValue.toString(i);<br>
 * 			int j = TransformValue.toInteger(s);<br>
 * 		</p>
 * 		<p>
 * 			result: i == j is true!
 * 		</p>
 * </p>
 * 
 * @since JDK 1.8
 * @version 2015.12.12_0
 * @author Johannes B. Latzel
 */
@Deprecated public final class TransformValue {	
	
	
	/**
	 * <p>wrapper for {@link j3l.util.TransformValue#toCharArray(int)}</p>
	 *
	 * @param i the 32 bit signed integer value
	 * @return {@link j3l.util.TransformValue#toCharArray(int) String.valueOf( TransformValue.toCharArray(i) )}
	 */
	public static String toString(int i) {
		
		return String.valueOf( TransformValue.toCharArray(i) );
		
	}
	
	
	/**
	 * <p>transforms an int to a character-array whose elements are bitwise identical to the int</p>
	 * <p>note: the array will always have a length of 2</p>
	 *
	 * @param i the 32 bit signed integer value
	 * @return bitwise identical array of length 2
	 */
	public static char[] toCharArray(int i) {
		
		return new char[] {
				(char)(i >> 16),
				(char)(0x0000ffff & i)
		};
		
	}
	
	
	/**
	 * <p>transforms an int to a byte-array whose elements are bitwise identical to the int</p>
	 * <p>note: the array will always have a length of 4</p>
	 *
	 * @param l the 32 bit signed integer value
	 * @return bitwise identical array of length 4
	 */
	public static byte[] toByteArray(int i) {
		
		return new byte[] {
				(byte)( (i >> 24) - 128 ),
				(byte)( ((i >> 16) & 0xff) - 128 ),
				(byte)( ((i >> 8) & 0xff) - 128 ),
				(byte)( (i & 0xff) - 128 )
		};
		
	}
	
	
	/**
	 * <p>wrapper for {@link j3l.util.TransformValue#toInteger(char[])}</p>
	 *
	 * @param s the String must have a length of 2
	 * @return {@link j3l.util.TransformValue#toInteger(char[]) TransformValue.toInteger(s.toCharArray())}
	 * @throws IllegalArgumentException
	 */
	public static int toInteger(String s) {
		
		return TransformValue.toInteger(s.toCharArray());
		
	}
	
	
	/**
	 * <p>transforms an character-array of two elements to an int which is bitwise identical to the elements</p>
	 *
	 * @param c must be a character-array of length 2
	 * @return the 32 bit signed integral value which is bitwise identical to the character-array's elements
	 * @throws IllegalArgumentException when c == null
	 * @throws IllegalArgumentException when c.length != 4
	 */
	public static int toInteger(char[] c) {
		
		if( c == null ) {
			throw new IllegalArgumentException("The c must not be equal to null!");
		}
		else if(c.length != 2) {
			throw new IllegalArgumentException("The length of the array must be equal to 2!");
		}	
		
		return (c[0] << 16) + c[1];
		
	}
	
	
	/**
	 * <p>transforms an byte-array of 4 elements to an int which is bitwise identical to the elements</p>
	 *
	 * @param b must be a byte-array of length 4
	 * @return the 32 bit signed integral value which is bitwise identical to the byte-array's elements
	 * @throws IllegalArgumentException when b == null
	 * @throws IllegalArgumentException when b.length != 4
	 */
	public static int toInteger(byte[] b) {
		
		if( b == null ) {
			throw new IllegalArgumentException("The b must not be equal to null!");
		}
		else if( b.length != 4 ) {
			throw new IllegalArgumentException("The length of the array must be equal to 4!");
		}	
		
		return ( (b[0] + 128) << 24) + ( (b[1] + 128) << 16) + ( (b[2] + 128) << 8) + (b[3] + 128);
		
	}	
	
	
	/**
	 * <p>wrapper for {@link j3l.util.TransformValue#toCharArray(long)}</p>
	 *
	 * @param l the 64 bit signed integer value
	 * @return {@link j3l.util.TransformValue#toCharArray(long) String.valueOf( TransformValue.toCharArray(l) )}
	 */
	public static String toString(long l) {
		
		return String.valueOf( TransformValue.toCharArray(l) );
		
	}
	
	
	/**
	 * <p>transforms a long to a character-array whose elements are bitwise identical to the long</p>
	 * <p>note: the array will always have a length of 4</p>
	 *
	 * @param l the 64 bit signed integer value
	 * @return bitwise identical array of length 4
	 */
	public static char[] toCharArray(long l) {
		
		return new char[] {
				(char)(l >> 48),
				(char)( (l >> 32) & 0xffff ),
				(char)( (l >> 16) & 0xffff ),
				(char)( l & 0xffff )
		};
		
	}
	
	
	/**
	 * <p>transforms a long to a byte-array whose elements are bitwise identical to the long</p>
	 * <p>note: the array will always have a length of 8</p>
	 *
	 * @param l the 64 bit signed integer value
	 * @return bitwise identical array of length 8
	 */
	public static byte[] toByteArray(long l) {
		
		return new byte[] {
				(byte)( (l >> 56) - 128 ),
				(byte)( ((l >> 48) & 0xff) - 128 ),
				(byte)( ((l >> 40) & 0xff) - 128 ),
				(byte)( ((l >> 32) & 0xff) - 128 ),
				(byte)( ((l >> 24) & 0xff) - 128 ),
				(byte)( ((l >> 16) & 0xff) - 128 ),
				(byte)( ((l >> 8) & 0xff) - 128 ),
				(byte)( (l & 0xff) - 128 )
		};
		
	}
	
	
	/**
	 * <p>wrapper for {@link j3l.util.TransformValue#toLong(char[])}</p>
	 *
	 * @param s the String must have a length of 4
	 * @return {@link j3l.util.TransformValue#toLong(char[]) TransformValue.toLong(s.toCharArray())}
	 * @throws IllegalArgumentException
	 */
	public static long toLong(String s) {
		
		return TransformValue.toLong(s.toCharArray());
		
	}
	
	
	/**
	 * <p>transforms an character-array of 4 elements to a long which is bitwise identical to the elements</p>
	 *
	 * @param c must be a character-array of length 4
	 * @return the 64 bit signed integral value which is bitwise identical to the character-array's elements
	 * @throws IllegalArgumentException when c == null
	 * @throws IllegalArgumentException when c.length != 4
	 */
	public static long toLong(char[] c) {
		
		if( c == null ) {
			throw new IllegalArgumentException("The c must not be equal to null!");
		}
		else if( c.length != 4 ) {
			throw new IllegalArgumentException("The length of the array must be equal to 4!");
		}	
		
		return ((long)c[0] << 48) + ((long)c[1] << 32) + ((long)c[2] << 16) + c[3];
		
	}
	
	
	/**
	 * <p>transforms an byte-array of 8 elements to a long which is bitwise identical to the elements</p>
	 *
	 * @param b must be a byte-array of length 8
	 * @return the 64 bit signed integral value which is bitwise identical to the byte-array's elements
	 * @throws IllegalArgumentException when b == null
	 * @throws IllegalArgumentException when b.length != 8
	 */
	public static long toLong(byte[] b) {
		
		if( b == null ) {
			throw new IllegalArgumentException("The b must not be equal to null!");
		}
		else if( b.length != 8 ) {
			throw new IllegalArgumentException("The length of the array must be equal to 8!");
		}	
		
		return ( (long)(b[0] + 128) << 56) + ( (long)(b[1] + 128) << 48) + ( (long)(b[2] + 128) << 40) + ( (long)(b[3] + 128) << 32) + 
				( (long)(b[4] + 128) << 24) + ( (long)(b[5] + 128) << 16) + ( (long)(b[6] + 128) << 8) + (b[7] + 128);
		
	}
	
	
	/**
	 * <p>transforms a String to a bitwise identical byte[]</p>
	 * <p>note: the array will always have a length of s.length() * 2</p>
	 *
	 * @param s the String
	 * @return a byte[] bitwise identical to the String s
	 * @throws IllegalArgumentException when s == null
	 */
	public static byte[] toByteArray(String s) {
		
		if( s == null ) {
			throw new IllegalArgumentException("The s must not be equal to null!");
		}
		else if( s.length() == 0 ) {
			return new byte[0];
		}
		
		
		byte[] result = new byte[s.length() * 2];
		
		char[] chars = s.toCharArray();
		
		
		for(int a=0,n=result.length;a<n;a++) {
			result[a] = (byte)( (((chars[a/2]) & (255 << (8 * (a % 2)))) >> (8 * (a % 2))) - 128 );
		}
		
		
		return result;
	}
	
	
	/**
	 * <p>transforms a byte array of even length to a bitwise identical string</p>
	 *
	 * @param b the byte array of even length
	 * @return the bitwise identical String
	 * @throws IllegalArgumentException when b == 0
	 * @throws IllegalArgumentException when the length of the byte array is odd
	 */
	public static String toString(byte[] b) {
		
		if( b == null ) {
			throw new IllegalArgumentException("The b must not be equal to null!");
		}		
		
		
		int n = b.length;
		
		if( n == 0 ) {
			return "";
		}
				
		if( n % 2 != 0) {
			throw new IllegalArgumentException("The length of the byte array must be even!");
		}
		
		
		char[] chars = new char[n / 2];
		
		for(int a=0;a<n;a+=2) {
			chars[a/2] = (char)(((b[a+1] + 128) << 8) + (b[a] + 128));
		}
		
		
		StringBuilder result = new StringBuilder("");
		
		for(int a=0,m=chars.length;a<m;a++) {
			result.append(chars[a]);
		}
		
		
		return result.toString();
		
	}	
	
	
	/**
	 * <p>wrapper for {@link j3l.util.TransformValue#toCharArray(int, char[])}</p>
	 *
	 * @param i the 32 bit signed integer value
	 * @param buffer a character buffer of size two (will be overwritten)
	 * @throws IllegalArgumentException when buffer == null
	 * @throws IllegalArgumentException when buffer.length != 2
	 * @return a String representing the int
	 */
	public static String toString(int i, char[] buffer) {
		TransformValue.toCharArray(i, buffer);
		return String.valueOf( buffer );
	}
	
	
	/**
	 * <p>writes an int into the buffer (a character-array whose elements are bitwise identical to the int)</p>
	 * <p>note: the buffer must always have a length of 2</p>
	 *
	 * @param i the 32 bit signed integer value
	 * @param buffer a character buffer of size 2 (will be overwritten)
	 * @throws IllegalArgumentException when buffer == null
	 * @throws IllegalArgumentException when buffer.length != 2
	 */
	public static void toCharArray(int i, char[] buffer) {
		
		if( buffer == null ) {
			throw new IllegalArgumentException("The buffer must not be equal to null!");
		}
		else if( buffer.length != 2 ) {
			throw new IllegalArgumentException("The length of the buffer must be equal to 2!");
		}
		
		buffer[0] = (char)(i >> 16);
		buffer[1] = (char)(0x0000ffff & i);
		
	}
	
	
	/**
	 * <p>writes an int into a buffer (a byte-array whose elements are bitwise identical to the int)</p>
	 * <p>note: the buffer must always have a length of 4</p>
	 *
	 * @param l the 32 bit signed integer value
	 * @param buffer a byte buffer of size 4 (will be overwritten)
	 * @throws IllegalArgumentException when buffer == null
	 * @throws IllegalArgumentException when buffer.length != 4
	 */
	public static void toByteArray(int i, byte[] buffer) {
		
		if( buffer == null ) {
			throw new IllegalArgumentException("The buffer must not be equal to null!");
		}
		else if( buffer.length != 4 ) {
			throw new IllegalArgumentException("The length of the buffer must be equal to 4!");
		}
		

		buffer[0] = (byte)( (i >> 24) - 128 );
		buffer[1] = (byte)( ((i >> 16) & 0xff) - 128 );
		buffer[2] = (byte)( ((i >> 8) & 0xff) - 128 );
		buffer[3] = (byte)( (i & 0xff) - 128 );
		
	}	
	
	
	/**
	 * <p>wrapper for {@link j3l.util.TransformValue#toCharArray(long, char[])}</p>
	 *
	 * @param l the 64 bit signed integer value
	 * @param buffer a char buffer of size 4 (will be overwritten)
	 * @throws IllegalArgumentException when buffer == null
	 * @throws IllegalArgumentException when buffer.length != 4
	 * @return a String representing the long
	 */
	public static String toString(long l, char[] buffer) {
		
		TransformValue.toCharArray(l, buffer);
		return String.valueOf( buffer );
		
	}
	
	
	/**
	 * <p>writes a long into the buffer (a character-array whose elements are bitwise identical to the long)</p>
	 * <p>note: the buffer must always have a length of 4</p>
	 *
	 * @param l the 64 bit signed integer value
	 * @param buffer a char buffer of size 4 (will be overwritten)
	 * @throws IllegalArgumentException when buffer == null
	 * @throws IllegalArgumentException when buffer.length != 4
	 */
	public static void toCharArray(long l, char[] buffer) {
		
		if( buffer == null ) {
			throw new IllegalArgumentException("The buffer must not be equal to null!");
		}
		else if( buffer.length != 4 ) {
			throw new IllegalArgumentException("The length of the buffer must be equal to 4!");
		}
		

		buffer[0] = (char)(l >> 48);
		buffer[1] = (char)( (l >> 32) & 0xffff );
		buffer[2] = (char)( (l >> 16) & 0xffff );
		buffer[3] = (char)( l & 0xffff );
		
	}
	
	
	/**
	 * <p>writes a long into the buffer (a byte-array whose elements are bitwise identical to the long)</p>
	 * <p>note: the buffer must always have a length of 8</p>
	 *
	 * @param l the 64 bit signed integer value
	 * @param buffer a byte buffer of size 8 (will be overwritten)
	 * @throws IllegalArgumentException when buffer == null
	 * @throws IllegalArgumentException when buffer.length != 8
	 */
	public static void toByteArray(long l, byte[] buffer) {
		
		if( buffer == null ) {
			throw new IllegalArgumentException("The buffer must not be equal to null!");
		}
		else if( buffer.length != 8 ) {
			throw new IllegalArgumentException("The length of the buffer must be equal to 8!");
		}
		
		
		buffer[0] = (byte)( (l >> 56) - 128 );
		buffer[1] = (byte)( ((l >> 48) & 0xff) - 128 );
		buffer[2] = (byte)( ((l >> 40) & 0xff) - 128 );
		buffer[3] = (byte)( ((l >> 32) & 0xff) - 128 );
		buffer[4] = (byte)( ((l >> 24) & 0xff) - 128 );
		buffer[5] = (byte)( ((l >> 16) & 0xff) - 128 );
		buffer[6] = (byte)( ((l >> 8) & 0xff) - 128 );
		buffer[7] = (byte)( (l & 0xff) - 128 );
		
	}
	
	
	/**
	 * <p>writes a String into the buffer (a bitwise identical byte-array)</p>
	 * <p>note: the buffer must always have a length of s.length() * 2</p>
	 * 
	 * @param s the String
	 * @param buffer a byte buffer of size s.length() * 2 (will be overwritten)
	 * @throws IllegalArgumentException when buffer == null
	 * @throws IllegalArgumentException when buffer.length != 8 
	 */
	public static void toByteArray(String s, byte[] buffer) {
		
		if( buffer == null ) {
			throw new IllegalArgumentException("The buffer must not be equal to null!");
		}
		else if( buffer.length != s.length() * 2 ) {
			throw new IllegalArgumentException("The length of the buffer must be equal to s.length() * 2!");
		}
		else if( s.length() == 0 ) {
			return;
		}
		
		
		char[] chars = s.toCharArray();		
		
		for(int a=0,n=buffer.length;a<n;a++) {
			buffer[a] = (byte)( (((chars[a/2]) & (255 << (8 * (a % 2)))) >> (8 * (a % 2))) - 128 );
		}
		
	}
	
	
	/**
	 * <p>writes a byte array of even length into a buffer (a bitwise identical character-array)
	 * and returns the {@link java.lang.String String} corresponding to the character-array</p>
	 * <p>note: the buffer must always have a length equal to the half of b.length</p>
	 *
	 * @param b the byte array of even length
	 * @param buffer a char buffer with a length equal to the half of b.length (will be overwritten)
	 * @return the bitwise identical String
	 * @throws IllegalArgumentException when buffer == null
	 * @throws IllegalArgumentException when buffer.length * 2 != b.length
	 * @throws IllegalArgumentException when the length of the byte array is odd
	 */
	public static String toString(byte[] b, char[] buffer) {
		
		if( buffer == null ) {
			throw new IllegalArgumentException("The buffer must not be equal to null!");
		}
		else if( buffer.length * 2 != b.length ) {
			throw new IllegalArgumentException("The length of the buffer must be equal to the half of b.length!");
		}
		else if( b.length == 0 ) {
			return "";
		}
		
		
		int n = b.length;		
		
		if( n % 2 != 0) {
			throw new IllegalArgumentException("The length of the byte array must be even!");
		}
		
		
		char[] chars = new char[n / 2];
		
		for(int a=0;a<n;a+=2) {
			chars[a/2] = (char)(((b[a+1] + 128) << 8) + (b[a] + 128));
		}
		
		
		StringBuilder result = new StringBuilder("");
		
		for(int a=0,m=chars.length;a<m;a++) {
			result.append(chars[a]);
		}
		
		
		return result.toString();
		
	}
	

}
