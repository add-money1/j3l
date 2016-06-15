package j3l.util;

import java.io.IOException;

/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2016.06.15_0
 * @author Johannes B. Latzel
 */
public interface DataTable<T extends Indexable> {
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	void saveEntry(T t) throws IOException;
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	void deleteEntry(T t) throws IOException;
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	long getAvailableIndex();
	
}
