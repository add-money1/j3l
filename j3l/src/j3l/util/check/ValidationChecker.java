package j3l.util.check;


/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.12.05_0
 * @author Johannes B. Latzel
 */
public final class ValidationChecker {
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static <T extends IValidate> T checkForValidation(T validate) {
		if( !ArgumentChecker.checkForNull(validate, "validate").isValid() ) {
			throw new SecurityException("The instance \"" + validate.toString() + "\" is not valid!");
		}
		else {
			return validate;
		}
	}
	
}
