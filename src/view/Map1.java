/**
 * 
 */
package view;



import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


/**
 * @author Eliantus De MICHEL
 *
 */
public class Map1 {
	
	private String city;
	private final JFrame frame;
	
	public Map1(String a) {
		city = a;
		
		frame=new JFrame();
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		final JFXPanel fxpanel=new JFXPanel();
		frame.add(fxpanel);

		Platform.runLater(new Runnable() {
		@Override
		public void run()
		    {
		    WebEngine engine;
		    WebView wv=new WebView();
		    engine=wv.getEngine();
		    fxpanel.setScene(new Scene(wv));
		    engine.load("https://nominatim.openstreetmap.org/ui/search.html?q="+city);
		    }
		    });
		
		frame.setTitle("Search for "+city+" on OpenStreetMap");
		ImageIcon img = new ImageIcon(Main.class.getResource("/logo.png"));
		frame.setIconImage(img.getImage());
		
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public String getcity() {
		return city;
	}
	
	public void setcity(String a) {
		city=a;
	}

}
