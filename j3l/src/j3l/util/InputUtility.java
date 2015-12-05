package j3l.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * <p>utility-class for {@link java.io.Reader reader} and {@link java.io.InputStream input streams}</p>
 * 
 * @since JDK 1.8
 * @version 2015.08.29_0
 * @author Johannes B. Latzel
 */
public final class InputUtility {

	
	/**
	 * <p>reads characters into the buffer as long as the buffer is completely filled</p>
	 * <p>notice: 
	 * 	<li>proceeds as long as the buffer is not completely filled or an exception is thrown</li>
	 * 	<li>returns the same object which is passed by the parameter buffer</li>
	 * </p>
	 *
	 * @param reader any reader
	 * @param buffer the buffer in which the characters are read into
	 * @return the buffer
	 * @throws IOException  occurs in reader.read(....)
	 */
	public static char[] readComplete(Reader reader, char[] buffer) throws IOException {
		
		int actually_read = 0;
		int buffer_size = buffer.length;
		
		
		while( actually_read < buffer_size ) {
			actually_read += reader.read( buffer, actually_read, (buffer_size - actually_read) );
		}
		
		
		return buffer;
	}
	
	/**
	 * <p>reads bytes into the buffer as long as the buffer is completely filled</p>
	 * <p>notice: 
	 * 	<li>proceeds as long as the buffer is not completely filled or an exception is thrown</li>
	 * 	<li>returns the same object which is passed by the parameter buffer</li>
	 * </p>
	 *
	 * @param input_stream any input stream
	 * @param buffer the buffer in which the bytes are read into
	 * @return the buffer
	 * @throws IOException occurs in input_stream.read(....)
	 */
	public static byte[] readComplete(InputStream input_stream, byte[] buffer) throws IOException {
		
		int actually_read = 0;
		int buffer_size = buffer.length;
		
		
		while( actually_read < buffer_size ) {
			actually_read += input_stream.read( buffer, actually_read, (buffer_size - actually_read) );
		}
		
		
		return buffer;
	}
	
}
