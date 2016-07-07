package j3l.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * <p>utility-class for {@link java.io.Reader reader} and {@link java.io.InputStream input streams}</p>
 * 
 * @since JDK 1.8
 * @version 2016.06.10_0
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
	 * @throws IOException
	 */
	public static char[] readComplete(Reader reader, char[] buffer) throws IOException {
		int actually_read = 0;
		int buffer_size = buffer.length;
		int current_read_in_bytes;
		while( actually_read < buffer_size ) {
			current_read_in_bytes = reader.read(buffer, actually_read, (buffer_size - actually_read));
			if( current_read_in_bytes < 0 ) {
				throw new IOException("Reached EOF while reading!");
			}
			actually_read += current_read_in_bytes;
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
	 * @throws IOException
	 */
	public static byte[] readComplete(InputStream input_stream, byte[] buffer) throws IOException {
		int actually_read = 0;
		int buffer_size = buffer.length;
		int current_read_in_bytes;
		while( actually_read < buffer_size ) {
			current_read_in_bytes = input_stream.read(buffer, actually_read, (buffer_size - actually_read));
			if( current_read_in_bytes < 0 ) {
				throw new IOException("Reached EOF while reading!");
			}
			actually_read += current_read_in_bytes;
		}
		return buffer;
	}
	
}
