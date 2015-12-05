package j3l.gui;

import java.awt.BorderLayout;

import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import j3l.util.interfaces.Nameable;

public class TabbedDisplay extends Display {

	private static final long serialVersionUID = -3933371592192364054L;
	
	private JTabbedPane tabbed_pane;

	public TabbedDisplay(String title) {
		super(title);

		JPanel display_panel = new JPanel();
		display_panel.setLayout(new BorderLayout());
		tabbed_pane = new JTabbedPane();
		display_panel.add(tabbed_pane, BorderLayout.CENTER);
		setDisplayPanel(display_panel);
	}
	
	public void tab(JPanel panel, Nameable name, Icon icon) {
		tabbed_pane.insertTab(name.getName(), icon, panel, name.getName(), tabbed_pane.getTabCount());
		tabbed_pane.setSelectedIndex(tabbed_pane.getTabCount()-1);
	}
	
	public void tab(JPanel panel, Nameable name) {
		tab(panel, name, null);
	}
	
	public void untab(JPanel panel) {
		for(int a=0,n=tabbed_pane.getTabCount();a<n;a++) {
			if(panel == tabbed_pane.getComponentAt(a)) {
				tabbed_pane.removeTabAt(a);
				return;
			}
		}
	}
	
	public void untabAll() {
		tabbed_pane.removeAll();
	}

}
