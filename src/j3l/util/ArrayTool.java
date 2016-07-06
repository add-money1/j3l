package j3l.util;


/**
 * <p>transfers from right to left</p>
 * 
 * @since JDK 1.8
 * @version 2016.05.29_0
 * @author Johannes B. Latzel
 */
public final class ArrayTool {
	
	
	
	
	/*
	 * 
	 * Unterscheidung in SafeArrayTool und ArrayTool (keine checks auf null und boundaries)
	 * namenswahl von left und right in swap üebrdenken
	 * 
	 */
	
	
	
	/**
	 * <p>swaps the elements at the specified indices</p>
	 *
	 * @param array
	 * @param left_index index of the left element
	 * @param right_index index of the right element
	 */
	public static <T> void swap(T[] array, int left_index, int right_index) {
		T temp = array[left_index];
		array[left_index] = array[right_index];
		array[right_index] = temp;
	}
	
	
	/**
	 * <p>transfers from right to left</p>
	 *
	 * @param
	 * @return
	 */
	public static <T> T[] transferValues(T[] left, T[] right) {
		return ArrayTool.transferValues(left, right, 0, right.length);
	}
	
	
	/**
	 * <p>transfers from right to left</p>
	 *
	 * @param
	 * @return
	 */
	public static <T> T[] transferValues(T[] left, T[] right, int left_offset) {
		return ArrayTool.transferValues(left, right, left_offset, right.length);
	}
	
	
	/**
	 * <p>transfers from right to left</p>
	 *
	 * @param
	 * @return
	 */
	public static <T> T[] transferValues(T[] left, T[] right, int left_offset, int length) {
		return ArrayTool.transferValues(left, right, left_offset, 0, length);
	}
	
	
	/**
	 * <p>transfers from right to left</p>
	 *
	 * @param
	 * @return
	 */
	public static <T> T[] transferValues(T[] left, T[] right, int left_offset, int right_offset, int length) {
		
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
		
		return left;
	}
	
	
	/**
	 * <p>transfers from right to left</p>
	 *
	 * @param
	 * @return
	 */
	public static byte[] transferValues(byte[] left, byte[] right) {
		return ArrayTool.transferValues(left, right, 0, right.length);
	}
	
	
	/**
	 * <p>transfers from right to left</p>
	 *
	 * @param
	 * @return
	 */
	public static byte[] transferValues(byte[] left, byte[] right, int left_offset) {
		return ArrayTool.transferValues(left, right, left_offset, right.length);
	}
	
	
	/**
	 * <p>transfers from right to left</p>
	 *
	 * @param
	 * @return
	 */
	public static byte[] transferValues(byte[] left, byte[] right, int left_offset, int length) {
		return ArrayTool.transferValues(left, right, left_offset, 0, length);
	}
	
	
	/**
	 * <p>transfers from right to left</p>
	 *
	 * @param
	 * @return
	 */
	public static byte[] transferValues(byte[] left, byte[] right, int left_offset, int right_offset, int length) {
		
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
		
		return left;
		
	}
	
}
