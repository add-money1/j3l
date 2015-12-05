package j3l.util.stream;

import java.util.stream.Stream;


/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.12.03_0
 * @author Johannes B. Latzel
 */
public interface IStream<T> {
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	Stream<T> getStream(StreamMode stream_mode);
	
}
