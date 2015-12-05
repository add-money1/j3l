package j3l.util.debug;

import java.io.PrintStream;
import java.util.Collection;


/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.11.08_0
 * @author Johannes B. Latzel
 */
public class PrintElements {
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static <T> void printAllElementsOf(T[] array) {
		
		if( array == null || array.length == 0 ) {
			return;
		}
		
		for(int a=0,n=array.length;a<n;a++) {
			System.out.println("element " + a +": " + array[a]);
		}
		
	}
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static <T> void printAllElementsOf(T[] array, PrintStream print_stream) {
		
		if( array == null || array.length == 0 ) {
			return;
		}
		
		for(int a=0,n=array.length;a<n;a++) {
			print_stream.println("element " + a +": " + array[a]);
		}
		
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static <T> void printAllElementsOf(Collection<T> collection) {
		
		if( collection == null || collection.size() == 0 ) {
			return;
		}
		
		PrintElements.printAllElementsOf(collection.toArray());
		
	}
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static <T> void printAllElementsOf(Collection<T> collection, PrintStream print_stream) {
		
		if( collection == null || collection.size() == 0 ) {
			return;
		}
		
		PrintElements.printAllElementsOf(collection.toArray(), print_stream);
		
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static void printAllElementsOf(byte[] array) {
		
		if( array == null || array.length == 0 ) {
			return;
		}
		
		for(int a=0,n=array.length;a<n;a++) {
			System.out.println("element " + a +": " + array[a]);
		}
		
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static void printAllElementsOf(byte[] array, PrintStream print_stream) {
		
		if( array == null || array.length == 0 ) {
			return;
		}
		
		for(int a=0,n=array.length;a<n;a++) {
			print_stream.println("element " + a +": " + array[a]);
		}
		
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static void printAllElementsOf(short[] array) {
		
		if( array == null || array.length == 0 ) {
			return;
		}
		
		for(int a=0,n=array.length;a<n;a++) {
			System.out.println("element " + a +": " + array[a]);
		}
		
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static void printAllElementsOf(short[] array, PrintStream print_stream) {
		
		if( array == null || array.length == 0 ) {
			return;
		}
		
		for(int a=0,n=array.length;a<n;a++) {
			print_stream.println("element " + a +": " + array[a]);
		}
		
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static void printAllElementsOf(char[] array) {
		
		if( array == null || array.length == 0 ) {
			return;
		}
		
		for(int a=0,n=array.length;a<n;a++) {
			System.out.println("element " + a +": " + array[a]);
		}
		
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static void printAllElementsOf(char[] array, PrintStream print_stream) {
		
		if( array == null || array.length == 0 ) {
			return;
		}
		
		for(int a=0,n=array.length;a<n;a++) {
			print_stream.println("element " + a +": " + array[a]);
		}
		
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static void printAllElementsOf(int[] array) {
		
		if( array == null || array.length == 0 ) {
			return;
		}
		
		for(int a=0,n=array.length;a<n;a++) {
			System.out.println("element " + a +": " + array[a]);
		}
		
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static void printAllElementsOf(int[] array, PrintStream print_stream) {
		
		if( array == null || array.length == 0 ) {
			return;
		}
		
		for(int a=0,n=array.length;a<n;a++) {
			print_stream.println("element " + a +": " + array[a]);
		}
		
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static void printAllElementsOf(long[] array) {
		
		if( array == null || array.length == 0 ) {
			return;
		}
		
		for(int a=0,n=array.length;a<n;a++) {
			System.out.println("element " + a +": " + array[a]);
		}
		
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static void printAllElementsOf(long[] array, PrintStream print_stream) {
		
		if( array == null || array.length == 0 ) {
			return;
		}
		
		for(int a=0,n=array.length;a<n;a++) {
			print_stream.println("element " + a +": " + array[a]);
		}
		
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static void printAllElementsOf(float[] array) {
		
		if( array == null || array.length == 0 ) {
			return;
		}
		
		for(int a=0,n=array.length;a<n;a++) {
			System.out.println("element " + a +": " + array[a]);
		}
		
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static void printAllElementsOf(float[] array, PrintStream print_stream) {
		
		if( array == null || array.length == 0 ) {
			return;
		}
		
		for(int a=0,n=array.length;a<n;a++) {
			print_stream.println("element " + a +": " + array[a]);
		}
		
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static void printAllElementsOf(double[] array) {
		
		if( array == null || array.length == 0 ) {
			return;
		}
		
		for(int a=0,n=array.length;a<n;a++) {
			System.out.println("element " + a +": " + array[a]);
		}
		
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static void printAllElementsOf(double[] array, PrintStream print_stream) {
		
		if( array == null || array.length == 0 ) {
			return;
		}
		
		for(int a=0,n=array.length;a<n;a++) {
			print_stream.println("element " + a +": " + array[a]);
		}
		
	}
	
	
}
