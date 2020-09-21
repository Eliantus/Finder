/**
 * 
 */
package view;

import controler.*;
import model.*;

/**
 * @author Eliantus De MICHEL
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Dbase base = new Dbase("/Base.txt");
		Controler c = new Controler(base);
		c.getWindow().setVisible(true);
		
		

	}

}
