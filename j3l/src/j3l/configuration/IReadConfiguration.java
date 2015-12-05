package j3l.configuration;

import java.util.HashMap;

/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.12.03_0
 * @author Johannes B. Latzel
 */
public interface IReadConfiguration {
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	String getValue(String name);
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	HashMap<String, String> getConfiguration();
	
}
