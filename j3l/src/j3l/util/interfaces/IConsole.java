package j3l.util.interfaces;

/**
 * <p>interface for console interactions</p>
 * 
 * @since JDK 1.8
 * @version 2015.08.10_0
 * @author Johannes B. Latzel
 */
public interface IConsole {
	
	
	/**
	 * <p>prints a string of characters onto the output of the console and appends an '\n'</p>
	 *
	 * @param message the string
	 */
	public void println(String message);
	
	
	/**
	 * <p>prints a string of characters onto the output of the console</p>
	 *
	 * @param message the string
	 */
	public void print(String message);
	
	
	/**
	 * <p>prints a single character onto the output of the console</p>
	 *
	 * @param message the character
	 */
	public void print(char message);
	
	
	/**
	 * <p>prints an empty line onto the output of the console</p>
	 */
	public void println();
	
	
	/**
	 * <p>reads an waits until a character has been read from the input</p>
	 *
	 * @return the character
	 */
	public char read();
	
	
	/**
	 * <p>reads in a stream of characters until an '\n' has been detected</p>
	 *
	 * @return the string of read characters (the '\n' will be omitted)
	 */
	public String readln();

	
	/**
	 * <p>clears the console from any printed messages</p>
	 */
	public void clear();
	
	
	/**
	 * <p>if true prevents the given input from being printed onto the output of the console, false otherwise</p>
	 *
	 * @param hide if true prevents the given input from being printed onto the output of the console, flase otherwise
	 */
	public void hideOutput(boolean hide);
	
	
	/**
	 * <p>waits for any key to be pressed and closes the console afterwards</p>
	 */
	public void closeOnKeyStroke();
	
	
	/**
	 * <p>closes the console</p>
	 */
	public void close();

}
