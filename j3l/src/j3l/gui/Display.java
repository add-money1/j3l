package j3l.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Display extends JFrame {
	
	private static final long serialVersionUID = 7427520380823051029L;
	
	private boolean ready = false;
	private JPanel display_panel;
	
	public Display(String title) {
		super(title);
		
		SwingUtilities.invokeLater(() -> {
			initiliazeDisplay();
		});
		while(!ready) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void initiliazeDisplay() {
		double screen_width = getToolkit().getScreenSize().getWidth();
		double screen_height = getToolkit().getScreenSize().getHeight();

		setSize((int) (screen_width * 0.8d), (int) (screen_height * 0.8d));
		setLocation((int) ((screen_width - getWidth()) / 2), (int) ((screen_height - getHeight()) / 2));
		setResizable(true);
		setUndecorated(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		display_panel = new JPanel();
		display_panel.setLayout(new BorderLayout());
		add(display_panel, BorderLayout.CENTER);
		
		ready = true;
	}
	
	public void setDisplayPanel(JPanel panel) {
		if(!ready) {
			while(!ready) {
				try {
					Thread.sleep(50);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		SwingUtilities.invokeLater(() -> {
			display_panel.removeAll();
			display_panel.add(panel, BorderLayout.CENTER);
			display_panel.updateUI();
		});
		
	}
}
