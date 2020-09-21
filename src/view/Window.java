/**
 * 
 */
package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import model.Dbase;


/**
 * @author Eliantus De MICHEL
 *
 */
public class Window extends JFrame{
	
	private HintField input;
	private JLabel screen,info;
	private JButton search,previous,next,view;
	private String ville, hint="Enter a city name...";
	
	
	public Window() {
		
		//Create Panes
		JPanel pane1 = new JPanel();
		//pane1.setOpaque(false);
		pane1.setBackground(Color.LIGHT_GRAY);
		
		JPanel pane2 = new JPanel();
		pane2.setOpaque(false);
		JPanel pane3 = new JPanel();
		pane3.setOpaque(false);
		JPanel pane4 = new JPanel();
		pane4.setOpaque(false);
		
		//Custom input
		input = new HintField(hint);
		input.setPreferredSize(new Dimension(250,28));
		Font f1 = new Font("Cambria",Font.PLAIN, 15);
		input.setFont(f1);
		input.setMargin(new Insets(0, 10, 0, 0));
		pane1.add(input);
		
		//Custom Search
		search = new JButton("<html><span style=\"color: #02385E;\">Search</span></html>");
		pane1.add(search);
		
		//Custom info
		info = new JLabel("Trouver");
		Font f2 = new Font("cambria",Font.BOLD, 15);
		info.setFont(f2);
		info.setVisible(false);
		pane2.add(info);
		
		
		//Custom Previous, Next and view
		previous = new JButton("prev.");
		previous.setVisible(false);
		next = new JButton("next");
		next.setVisible(false);
		view = new JButton("View city on Map");
		view.setVisible(false);
		
		//Custom screen
		screen = new JLabel();
		Font f = new Font("Courier",Font.PLAIN, 18);
		screen.setFont(f);
		pane3.add(screen);
		
		pane4.setLayout(new BorderLayout());
		pane4.add(pane2,BorderLayout.NORTH);
		pane4.add(pane3,BorderLayout.CENTER);
		
		//Set Layout
		this.setLayout(new BorderLayout());
		this.setContentPane(new JLabel(new ImageIcon(Main.class.getResource("/carte.png"))));
		this.setLayout(new BorderLayout());
		this.add(pane1,BorderLayout.NORTH);
		this.add(pane4,BorderLayout.CENTER);
		this.add(view,BorderLayout.SOUTH);
		this.add(next,BorderLayout.EAST);
		this.add(previous,BorderLayout.WEST);
		
		//Custom Frame
		this.setPreferredSize(new Dimension(700,390));
		this.pack();
		screen.requestFocusInWindow();;
		this.setTitle("Quick City Finder English version");
		ImageIcon img = new ImageIcon(Main.class.getResource("/logo.png"));
		this.setIconImage(img.getImage());
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.getRootPane().setDefaultButton(search);
		
	}
	
	public void setVille(String a) {
		this.ville = a;
	}
	
	public HintField getInput() {
		return this.input;
	}
	
	public JLabel getScreen() {
		return this.screen;
	}
	
	public JButton getSearch() {
		return this.search;
	}
	
	public JButton getView() {
		return this.view;
	}
	
	public JButton getPrevious() {
		return this.previous;
	}
	
	public JButton getNext() {
		return this.next;
	}
	
	public String getVille() {
		return this.ville;
	}
	
	public String getHint() {
		return this.hint;
	}
	
	public JLabel getInfo() {
		return this.info;
	}
	

}
