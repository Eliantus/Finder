/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;

/**
 * @author Eliantus De MICHEL
 *
 */
public class HintField extends JTextField {
	
	private HintField me =this;
	private int i=0;
	
	public HintField(String a) {
		super(a);
		this.setForeground(Color.gray);
		
		this.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		    	me.setForeground(Color.BLACK);
		        if(i==0) {
		        	me.setText("");
		        	i+=1;
		        }
		    }

		    public void focusLost(FocusEvent e) {
		       if(me.getText().length()==0) {
		    	   me.setText(a);
		    	   i=0;
		    	   me.setForeground(Color.gray);
		       }
		       else {
		    	   me.setForeground(Color.BLACK);
		       }
		       
		    }
		});	
	}

}
