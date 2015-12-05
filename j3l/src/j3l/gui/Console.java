package j3l.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;

import j3l.util.interfaces.IConsole;

public class Console extends JPanel implements KeyListener, IConsole {
	
	private static final long serialVersionUID = 445100966497073266L;
	
	private final Runnable on_close;
	
	private JScrollPane scroll_pane;
	private JTextArea console_output; 
	private Document console_output_document;
	
	private boolean hide_output = false, ready = false, closed = false;
	private StringBuilder read_in = new StringBuilder("");
	private final Object read_lock = new Object(), write_lock = new Object();
	
	public Console(Runnable on_close) {
		super();
		this.on_close = on_close;
		SwingUtilities.invokeLater(() -> {	
			initiliazePanel();
		});
		while(!ready) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override public String toString() {
		return "Console";
	}

	protected void initiliazePanel() {	
		synchronized(read_lock) {
			synchronized(write_lock) {
				setLayout(new BorderLayout());
				
				console_output = new JTextArea();
				console_output.setBackground(Color.BLACK);
				console_output.setForeground(Color.GREEN);
				console_output.setEditable(false);
				console_output.setLineWrap(true);
				console_output.addKeyListener(this);
			}
		}

		print("<< ");
		
		synchronized(read_lock) {
			synchronized(write_lock) {
				DefaultCaret default_caret = (DefaultCaret)console_output.getCaret();
				default_caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
				default_caret.setVisible(true);
				
				scroll_pane = new JScrollPane(console_output);
				add(scroll_pane, BorderLayout.CENTER);
				
				console_output_document = console_output.getDocument();
			}
		}	
		
		ready = true;
	}

	@Override public void keyTyped(KeyEvent e) {}

	@Override public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			println();
			read_in.append('\n');
		}
		else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			if(read_in.length() > 0) {
				read_in.setLength(read_in.length()-1);
				try {
					console_output_document.remove(console_output_document.getLength()-1,1);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			} 			
		} 
		else {
			char kc = e.getKeyChar();
			if(kc > 31 && kc < 256) {
				if(!hide_output) print(kc);
				read_in.append(kc);
			}
		}
	}

	@Override public void keyReleased(KeyEvent e) {}
	
	
	/**
	 * <p>prints a string of characters onto the output of the console and appends an '\n'</p>
	 *
	 * @param message the string
	 */
	@Override public void println(String message) {
		
		print(message);
		println();
		
	}
	
	
	/**
	 * <p>prints a string of characters onto the output of the console</p>
	 *
	 * @param msg the string
	 */
	@Override public void print(String msg) {
		
		if(msg.contains("\n")) {
			
			String[] result = msg.split("\n");
			
			for(int a=0,n=result.length-1;a<n;a++) {
				println(result[a]);
			}
			
			print(result[result.length-1]);
			
		} 
		else {
			
			synchronized(write_lock) {
				console_output.append(msg);
			}
			
		}
	}
	
	
	/**
	 * <p>prints a single character onto the output of the console</p>
	 *
	 * @param msg the character
	 */
	@Override public void print(char msg) {
		
		if(msg == '\n') {
			println();
		} 
		else {
			
			synchronized(write_lock) {
				console_output.append(""+msg);
			}
			
		}
	}
	
	
	/**
	 * <p>prints an empty line onto the output of the console</p>
	 */
	@Override public void println() {
		
		synchronized(write_lock) {
			console_output.append("\n");
		}
		
		print("<< ");	
		
	}
	
	
	/**
	 * <p>reads an waits until a character has been read from the input</p>
	 *
	 * @return the character
	 */
	@Override public char read() {
		
		synchronized(read_lock) {
			
			read_in.setLength(0);
			
			
			while(read_in.length() == 0  && !closed) {
				
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			
			if(closed) {
				return 0;
			}
			
			char copy = read_in.toString().charAt(0);
			read_in.setLength(0);
			
			return copy;
			
		}
		
	}
	
	
	/**
	 * <p>reads in a stream of characters until an '\n' has been detected</p>
	 *
	 * @return the string of read characters (the '\n' will be omitted)
	 */
	@Override public String readln() {
		
		synchronized(read_lock) {
			
			read_in.setLength(0);
			
			
			while(!read_in.toString().contains("\n") && !closed) {
				
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			
			if(closed) {
				return "";
			}
			
			String copy = read_in.toString().substring(0, read_in.length()-1);
			read_in.setLength(0);
			
			return copy;
		}	
		
	}

	
	/**
	 * <p>clears the console from any printed messages</p>
	 */
	@Override public void clear() {
		
		synchronized(read_lock) {
			
			synchronized(write_lock) {
				
				read_in.setLength(0);
				
				try {
					console_output_document.remove(0,console_output_document.getLength());
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	
	
	/**
	 * <p>if true prevents the given input from being printed onto the output of the console, false otherwise</p>
	 *
	 * @param hide if true prevents the given input from being printed onto the output of the console, flase otherwise
	 */
	@Override public void hideOutput(boolean hide) {
		hide_output = hide;
	}
	
	
	/**
	 * <p>waits for any key to be pressed and closes the console afterwards</p>
	 */
	@Override public void closeOnKeyStroke() {
		
		println();
		print("The Console is disposing after the next key stroke...");
		
		read();
		
		close();
		
	}
	
	
	/**
	 * <p>closes the console</p>
	 */
	@Override public void close() {
		
		on_close.run();
		closed = true;
		
	}
}
