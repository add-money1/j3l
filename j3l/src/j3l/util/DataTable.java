package j3l.util;

import java.io.IOException;
import java.util.Collection;

/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2016.06.16_0
 * @author Johannes B. Latzel
 */
public interface DataTable<T extends Indexable, R extends IBinaryData> {
	
	
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
	Collection<R> getAllEntries() throws IOException;
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	long getAvailableIndex() throws IOException;
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	void trim() throws IOException;
	
}
