package j3l.util.array;


/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.11.04_0
 * @author Johannes B. Latzel
 */
public final class ArrayTool {
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static <T> void transferValues(T[] left, T[] right) {
		ArrayTool.transferValues(left, right, 0, right.length);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static <T> void transferValues(T[] left, T[] right, int left_offset) {
		ArrayTool.transferValues(left, right, left_offset, right.length);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static <T> void transferValues(T[] left, T[] right, int left_offset, int length) {
		ArrayTool.transferValues(left, right, left_offset, 0, length);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static <T> void transferValues(T[] left, T[] right, int left_offset, int right_offset, int length) {
		
		if( left == null ) {
			throw new IllegalArgumentException("The left must not be equal to null!");
		}
		
		if( right == null ) {
			throw new IllegalArgumentException("The right must not be equal to null!");
		}
		
		if( left_offset < 0 || right_offset < 0 || length < 0 ) {
			throw new IndexOutOfBoundsException("The offsets and length must not be less than 0!");
		}
		
		if( left_offset + length  > left.length ) {
			throw new IndexOutOfBoundsException("The left_offset + length must not succeed the length of the left!");
		}
		
		if( right_offset + length > right.length ) {
			throw new IndexOutOfBoundsException("The right_offset + length must not succeed the length of the right!");
		}
		
		for(int a=left_offset,n=left_offset+length;a<n;a++) {
			left[a] = right[ a - left_offset + right_offset ];
		}
		
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static void transferValues(byte[] left, byte[] right) {
		ArrayTool.transferValues(left, right, 0, right.length);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static void transferValues(byte[] left, byte[] right, int left_offset) {
		ArrayTool.transferValues(left, right, left_offset, right.length);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static void transferValues(byte[] left, byte[] right, int left_offset, int length) {
		ArrayTool.transferValues(left, right, left_offset, 0, length);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static void transferValues(byte[] left, byte[] right, int left_offset, int right_offset, int length) {
		
		if( left == null ) {
			throw new IllegalArgumentException("The left must not be equal to null!");
		}
		
		if( right == null ) {
			throw new IllegalArgumentException("The right must not be equal to null!");
		}
		
		if( left_offset < 0 || right_offset < 0 || length < 0 ) {
			throw new IndexOutOfBoundsException("The offsets and length must not be less than 0!");
		}
		
		if( left_offset + length  > left.length ) {
			throw new IndexOutOfBoundsException("The left_offset + length must not succeed the length of the left!");
		}
		
		if( right_offset + length > right.length ) {
			throw new IndexOutOfBoundsException("The right_offset + length must not succeed the length of the right!");
		}	
		
		for(int a=left_offset,n=left_offset+length;a<n;a++) {
			left[a] = right[ a - left_offset + right_offset ];
		}
		
	}
	
}
