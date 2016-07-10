package j3l.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;


/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2016.07.10_0
 * @author Johannes B. Latzel
 */
public class ConfigurationManager implements IReadConfiguration {
	
	
	/**
	 * <p></p>
	 */
	private final Hashtable<String, String> configuration_table;
	
	
	/**
	 * <p></p>
	 */
	private final File configuration_file;
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public ConfigurationManager(File configuration_file) {
		Checker.checkForExistence(configuration_file, "configuration_file");
		this.configuration_file = configuration_file;
		configuration_table = new Hashtable<>();
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public File getConfigurationFile() {
		return configuration_file;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public void addElement(String name, String value) {
		Checker.checkForEmptyString(name, "name");
		Checker.checkForNull(value, "value");
		configuration_table.put(name, value);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public void removeElement(String name) {
		Checker.checkForEmptyString(name, "name");
		configuration_table.remove(name);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public void setElement(String name, String value) {
		addElement(name, value);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public void loadConfiguration() throws IOException {
		
		int line_number = 0;
		String line = null;
		
		try ( BufferedReader reader = new BufferedReader(new FileReader(configuration_file)) ) {
			while( (line = reader.readLine()) != null ) {
				evaluateLine(line);
				line_number++;
			}
		}
		catch( IOException e ) {
			throw new IOException("Failed to load the configuration at line " + line_number + 
					" in file \"" + configuration_file.getAbsolutePath() + "\"!", e);
		}
		
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public void saveConfiguration() throws IOException {
		
		int line_number = 0;
		Set<String> keys = configuration_table.keySet();
		StringBuilder string_buffer = new StringBuilder();
		
		
		try ( RandomAccessFile output_stream = new RandomAccessFile(configuration_file, "rw") ) {
			output_stream.setLength(0);
		}
		catch( IOException e ) {
			throw new IOException("Failed to reset the size of the configuration in file \""
					+ configuration_file.getAbsolutePath() + "\"!", e);
		}
		
		
		try ( BufferedWriter writer = new BufferedWriter(new FileWriter(configuration_file)) ) {
			for( String key : keys ) {
				writer.write(createLine(string_buffer, key).toString());
				writer.newLine();
				line_number++;
			}
		}
		catch( IOException e ) {
			throw new IOException("Failed to save the configuration at line " + line_number + 
					" in file \"" + configuration_file.getAbsolutePath() + "\"!", e);
		}
		
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	private void evaluateLine(String line) {
		
		Checker.checkForEmptyString(line, "line");
		String[] split_string = line.trim().split("=");
		Checker.checkForNull(split_string, "split_string");
		
		if( split_string.length != 2 ) {
			throw new RuntimeException("The line contains the wrong number of \"=\"-signs! It must be exactly 1 and is: "
					+ (split_string.length - 1) );
		}
		
		String name = split_string[0].trim();
		String value = split_string[1].trim();
		
		try {
			addElement(name, value);
		}
		catch( Exception e ) {
			throw new RuntimeException("Failed to read the line!", e);
		}
		
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	private StringBuilder createLine(StringBuilder string_buffer, String name) {
		
		Checker.checkForNull(string_buffer, "string_buffer");
		Checker.checkForEmptyString(name, "name");
		string_buffer.setLength(0);
		
		String value = configuration_table.get(name);
		Checker.checkForNull(value, "value");		
		string_buffer.ensureCapacity( name.length() + value.length() + 3 );
		
		string_buffer.append(name);
		string_buffer.append(" = ");
		string_buffer.append(value);
		
		string_buffer.trimToSize();
		return string_buffer;
		
	}
	
	
	/* (non-Javadoc)
	 * @see j3l.configuration.IReadConfiguration#getValue(java.lang.String)
	 */
	@Override public String getValue(String name) {
		String temp_value = configuration_table.get(name);
		if( temp_value != null ) {
			return temp_value.trim();
		}
		return "";
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see j3l.configuration.IReadConfiguration#getConfiguration()
	 */
	@Override public HashMap<String, String> getConfiguration() {
		HashMap<String, String> configuration_map = new HashMap<>();
		configuration_table.forEach((key, value) -> configuration_map.put(key, value));
		return configuration_map;
	}
	
}
