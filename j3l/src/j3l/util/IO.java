package j3l.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public final class IO {
	
	private final static String[] FUN = new String[] {
		"42", "Oh, and DFTBA", "Houston, we have a problem.", "Wibbly-wobbly, timy-wimy.",
		"What does the fox say?", "Life? Don't tell me about life.", "Geronimo!", "Allons-y!",
		"Don't blink!", "General Overblick findet den Major Spoiler sehr Captain Obvious.",
		"Watt is love? Baby don't Hertz me, don't Hertz me. No Morse."
	};
	
	private final static Random RANDOM = new Random(42 ^ System.currentTimeMillis());
	
	private final static BufferedReader input_reader = new BufferedReader(new InputStreamReader(System.in));

	public static String readUntilBreakLine(String output) throws IOException {
		System.out.print(output);
		
		String in = "";
		
		int next = input_reader.read();
		while(next != '\n' && next != '\r') {
			in += (char)next;
			next = input_reader.read();
		}
		input_reader.read();
		
		return in;
	}
	
	public static ReadInObject readAndParse(String output_message, String type) {
		Exception thrown_exception = null;
		
		String input_string = "";
		try {
			input_string = IO.readUntilBreakLine(output_message);
		}
		catch(Exception e) {
			thrown_exception = e;
		}
		return new ReadInObject(input_string, type, thrown_exception);
	}
	
	public static void printFun() {
		System.out.println(FUN[(int)(RANDOM.nextFloat()*FUN.length)]);
	}
	
}
