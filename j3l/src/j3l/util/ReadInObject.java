package j3l.util;

public final class ReadInObject {

	private Object data;
	private final String input;
	private final String type;
	private final Exception thrown_exception;
	
	public ReadInObject(String input, String type, Exception thrown_exception) {
		this.thrown_exception = thrown_exception;
		this.input = input;
		this.type = type;

		if(isCastable()) {
			
			switch(type) {			
				case "int":
					try {
						data = Integer.parseInt(input);
					}
					catch(NumberFormatException e) {
						thrown_exception = e;
					}
					break;
					
				case "Integer":
					try {
						data = (Integer)(Integer.parseInt(input));
					}
					catch(NumberFormatException e) {
						thrown_exception = e;
					}
					break;
					
				case "double":
					try {
						data = Double.parseDouble(input);
					}
					catch(NumberFormatException e) {
						thrown_exception = e;
					}
					break;
				
				case "Double":
					try {
						data = (Double)(Double.parseDouble(input));
					}
					catch(NumberFormatException e) {
						thrown_exception = e;
					}
					break;
					
				case "String":
					data = input;
					break;
					
				case "Object":
					data = (Object)input;
					break;
					
				default:
					data = null;
					break;			
			}
		}
		else {
			data = null;
		}
	}
	
	private boolean isCastable() {
		return !hasThrownException() && hasType() && hasInput();
	}
	
	public boolean isSafe() {
		return isCastable() && hasData();
	}
	
	public boolean hasData() {
		return data != null;
	}
	
	public Object getData() {
		return data;
	}
	
	public boolean hasThrownException() {
		return thrown_exception != null;
	}
	
	public Exception getThrownException() {
		return thrown_exception;
	}
	
	public boolean hasType() {
		return type != null && !type.equals("");
	}
	
	public Class<?> getType() {
		try {
			return Class.forName(type);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean hasInput() {
		return input != null && !input.equals("");
	}
	
	public String getInput() {
		return input;
	}
}
