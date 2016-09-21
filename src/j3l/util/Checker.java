package j3l.util;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.function.Function;


/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2016.09.22_0
 * @author Johannes B. Latzel
 */
public final class Checker {
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static <T> T checkForNull(T object) {
		return Checker.<T>checkForNull(object, "object");
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static <T> T checkForNull(T object, String name_of_the_object) {
		if( object == null ) {
			throw new NullPointerException("The argument \"" + name_of_the_object + "\" must not be equal to null!");
		}
		return object;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static byte checkForBoundaries(byte value, byte min_value, byte max_value) {
		return Checker.checkForBoundaries(value, min_value, max_value, Integer.toString(value));
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static byte checkForBoundaries(byte value, byte min_value, byte max_value, String name_of_the_value) {
		if( value < min_value || value > max_value ) {
			throw new IndexOutOfBoundsException("The argument \"" + name_of_the_value + "\" must be in range of ["
					+ min_value + "|" + max_value + "] and is equal to " + value + " instead!");
		}
		return value;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static short checkForBoundaries(short value, short min_value, short max_value) {
		return Checker.checkForBoundaries(value, min_value, max_value, Integer.toString(value));
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static short checkForBoundaries(short value, short min_value, short max_value, String name_of_the_value) {
		if( value < min_value || value > max_value ) {
			throw new IndexOutOfBoundsException("The argument \"" + name_of_the_value + "\" must be in range of ["
					+ min_value + "|" + max_value + "] and is equal to " + value + " instead!");
		}
		return value;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static int checkForBoundaries(int value, int min_value, int max_value) {
		return Checker.checkForBoundaries(value, min_value, max_value, Integer.toString(value));
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static int checkForBoundaries(int value, int min_value, int max_value, String name_of_the_value) {
		if( value < min_value || value > max_value ) {
			throw new IndexOutOfBoundsException("The argument \"" + name_of_the_value + "\" must be in range of ["
					+ min_value + "|" + max_value + "] and is equal to " + value + " instead!");
		}
		return value;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static long checkForBoundaries(long value, long min_value, long max_value) {
		return Checker.checkForBoundaries(value, min_value, max_value, Long.toString(value));
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static long checkForBoundaries(long value, long min_value, long max_value, String name_of_the_value) {
		if( value < min_value || value > max_value ) {
			throw new IndexOutOfBoundsException("The argument \"" + name_of_the_value + "\" must be in range of ["
					+ min_value + "|" + max_value + "] and is equal to " + value + " instead!");
		}
		return value;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static float checkForBoundaries(float value, float min_value, float max_value) {
		return Checker.checkForBoundaries(value, min_value, max_value, Float.toString(value));
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static float checkForBoundaries(float value, float min_value, float max_value, String name_of_the_value) {
		if( value < min_value || value > max_value ) {
			throw new IndexOutOfBoundsException("The argument \"" + name_of_the_value + "\" must be in range of ["
					+ min_value + "|" + max_value + "] and is equal to " + value + " instead!");
		}
		return value;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static double checkForBoundaries(double value, double min_value, double max_value) {
		return Checker.checkForBoundaries(value, min_value, max_value, Double.toString(value));
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static double checkForBoundaries(double value, double min_value, double max_value, String name_of_the_value) {
		if( value < min_value || value > max_value ) {
			throw new IndexOutOfBoundsException("The argument \"" + name_of_the_value + "\" must be in range of ["
					+ min_value + "|" + max_value + "] and is equal to " + value + " instead!");
		}
		return value;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static File checkForExistence(File file) {
		return Checker.checkForExistence(file, "file");
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static File checkForExistence(File file, String name_of_the_file) {
		Checker.checkForNull(file, name_of_the_file);
		if( !file.isFile() ) {
			throw new IllegalArgumentException("The file \"" + name_of_the_file + "\" at \""
					+ file.getAbsolutePath() + "\" does not exists!");
		}
		return file;
	}


	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static String checkForEmptyString(String value) {
		return checkForEmptyString(value, "value");
	}


	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static String checkForEmptyString(String value, String name_of_the_value) {
		Checker.checkForNull(value, "value");
		Checker.checkForNull(name_of_the_value, "name_of_the_value");
		if( value.trim().length() == 0 ) {
			throw new IllegalArgumentException("The argument \"" + name_of_the_value + "\" must not be empty!");
		}
		return value;
	}


	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static <T extends IValidate> T checkForValidation(T validate) {
		return Checker.checkForValidation(validate, "validate");
	}


	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static <T extends IValidate> T checkForValidation(T validate, String name) {
		if( !Checker.checkForNull(validate, name).isValid() ) {
			throw new SecurityException("The instance \"" + validate.toString() + "\" is not valid!");
		}
		return validate;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static <T extends Exception> IClose<T> checkForOpen(IClose<T> close) {
		return checkForOpen(close, close.toString());
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static <T extends Exception> IClose<T> checkForOpen(IClose<T> close, String name_of_the_instance) {
		if( !close.isOpen() ) {
			throw new SecurityException("The " + name_of_the_instance + " is not open!");
		}
		return close;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static boolean checkAllElementsForEquality(byte[] buffer1, byte[] buffer2) {
		if( buffer1.length != buffer2.length ) {
			return false;
		}
		for(int a=0,n=buffer1.length;a<n;a++) {
			if( buffer1[a] != buffer2[a] ) {
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static boolean checkAllElementsForEquality(ByteBuffer buffer1, ByteBuffer buffer2) {
		if( buffer1.remaining() != buffer2.remaining() ) {
			return false;
		}
		for(int a=0,n=buffer1.remaining();a<n;a++) {
			if( buffer1.get() != buffer2.get() ) {
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static boolean checkAllElements(byte[] array, byte value) {
		
		for( byte b : array ) {
			if( b != value ) {
				return false;
			}
		}
		
		return true;
		
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static boolean checkAllElements(ByteBuffer buffer, byte value) {
		buffer.rewind();
		byte[] buffer_array = new byte[buffer.remaining()];
		buffer.get(buffer_array);
		return Checker.checkAllElements(buffer_array, value);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static boolean checkAllElementsForZero(byte[] array) {
		return Checker.checkAllElements(array, (byte)0);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static <T, R> boolean checkAllElements(T[] array, Function<T, R> function, T check_object) {
		
		for( T element : array ) {
			if( !function.apply(element).equals(check_object) ) {
				return false;
			}
		}
		
		return true;
		
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static <T> boolean checkAllElementsForNull(T[] array) {
		
		for( T element : array ) {
			if( element == null ) {
				return true;
			}
		}
		
		return true;
		
	}
	
}
