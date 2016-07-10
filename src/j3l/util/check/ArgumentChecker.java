package j3l.util.check;

import java.io.File;

import j3l.util.IClose;


/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2016.07.10_0
 * @author Johannes B. Latzel
 */
public final class ArgumentChecker {
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static <T> T checkForNull(T object) {
		return ArgumentChecker.<T>checkForNull(object, "object");
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
	public final static int checkForBoundaries(int value, int min_value, int max_value) {
		return ArgumentChecker.checkForBoundaries(value, min_value, max_value, Integer.toString(value));
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
		return ArgumentChecker.checkForBoundaries(value, min_value, max_value, Long.toString(value));
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
		return ArgumentChecker.checkForBoundaries(value, min_value, max_value, Float.toString(value));
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
		return ArgumentChecker.checkForBoundaries(value, min_value, max_value, Double.toString(value));
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
		return ArgumentChecker.checkForExistence(file, "file");
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static File checkForExistence(File file, String name_of_the_file) {
		ArgumentChecker.checkForNull(file, name_of_the_file);
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
		ArgumentChecker.checkForNull(value, "value");
		ArgumentChecker.checkForNull(name_of_the_value, "name_of_the_value");
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
		return ArgumentChecker.checkForValidation(validate, "validate");
	}


	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static <T extends IValidate> T checkForValidation(T validate, String name) {
		if( !ArgumentChecker.checkForNull(validate, name).isValid() ) {
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
	
}
