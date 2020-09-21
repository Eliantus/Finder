/**
 * 
 */
package controler;

import java.awt.Desktop;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;
import model.*;
import view.*;

/**
 * @author Eliantus De MICHEL
 *
 */
public class Controler implements ActionListener {
	
	private Window w;
	private Dbase Base;
	private int rec;
	private double lon,lat;
	
	public Controler (Dbase b) {
		w = new Window();
		Base = new Dbase(b.getLink());
		w.getSearch().addActionListener(this);
		w.getNext().addActionListener(this);
		w.getPrevious().addActionListener(this);
		w.getView().addActionListener(this);
		
	}
	
	public Window getWindow(){
		return this.w;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==w.getSearch()) {
			String t=w.getInput().getText();
			w.getScreen().setText("");
			
			if(!t.equals(w.getHint())) {
				w.setVille(t);
			}
			else {
				w.setVille(new String());			
			}
			
			if(w.getVille().length()!=0 && (w.getVille().contains("a")||w.getVille().contains("e")||w.getVille().contains("i")||w.getVille().contains("o")||w.getVille().contains("u")||w.getVille().contains("y"))) {
					this.Find();
			}
		}
		
		if(e.getSource()==w.getNext()) {
			
			if(rec<this.Base.getnb()-1) {
				String pop,reg;
				this.rec += 1;
				int[] tab = this.Base.getTab();
				this.w.getInfo().setText("  We found "+Base.getnb()+" records for this search.  (record : "+(rec+1)+"/"+this.Base.getnb()+")  ");
				this.w.getInfo().setVisible(true);

				lat= Double.valueOf(this.Base.GetBase().get(tab[rec])[2]).doubleValue();
				lon= Double.valueOf(this.Base.GetBase().get(tab[rec])[3]).doubleValue();
								
				if(this.Base.GetBase().get(tab[rec])[9].equals("0")) {
					pop="No data available";
				}
				else {
					pop=this.Base.GetBase().get(tab[rec])[9];
				}
				
				if(this.Base.GetBase().get(tab[rec])[7].equals("0")) {
					reg="No data available";
				}
				else {
					reg=this.Base.GetBase().get(tab[rec])[7];
				}
				
				w.getScreen().setText("<html><table style=\\\"color: #02385E;\\\">"
						+ "<tr>"
						+ "<td><b>City</b></td>"+"<td>:</td>"+"<td>"+this.Base.GetBase().get(tab[rec])[0]+"</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Latitude</b></td>"+"<td>:</td>"+"<td>"+this.Base.GetBase().get(tab[rec])[2]+"</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Longitude</b></td>"+"<td>:</td>"+"<td>"+this.Base.GetBase().get(tab[rec])[3]+"</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Population</b></td>"+"<td>:</td>"+"<td>"+pop+" <i><b>(en 2019)</b></i></td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Region</b></td>"+"<td>:</td>"+"<td>"+reg+"</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Country</b></td>"+"<td>:</td>"+"<td>"+this.Base.GetBase().get(tab[rec])[4]+"</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Country ISO3</b></td>"+"<td>:</td>"+"<td>"+this.Base.GetBase().get(tab[rec])[6]+"</td>"
						+ "</tr>"
						+ "</table></html>");
			
			}
			
		}
		
		if(e.getSource()==w.getPrevious()) {
			if(rec>0) {
				String pop,reg;
				this.rec -= 1;
				int[] tab = this.Base.getTab();
				this.w.getInfo().setText("  We found "+Base.getnb()+" records for this search.  (record : "+(rec+1)+"/"+this.Base.getnb()+")  ");
				this.w.getInfo().setVisible(true);
				

				lat= Double.valueOf(this.Base.GetBase().get(tab[rec])[2]).doubleValue();
				lon= Double.valueOf(this.Base.GetBase().get(tab[rec])[3]).doubleValue();
				
				
				if(this.Base.GetBase().get(tab[rec])[9].equals("0")) {
					pop="No data available";
				}
				else {
					pop=this.Base.GetBase().get(tab[rec])[9];
				}
				
				if(this.Base.GetBase().get(tab[rec])[7].equals("0")) {
					reg="No data available";
				}
				else {
					reg=this.Base.GetBase().get(tab[rec])[7];
				}
				
				w.getScreen().setText("<html><table style=\\\"color: #02385E;\\\">"
						+ "<tr>"
						+ "<td><b>City</b></td>"+"<td>:</td>"+"<td>"+this.Base.GetBase().get(tab[rec])[0]+"</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Latitude</b></td>"+"<td>:</td>"+"<td>"+this.Base.GetBase().get(tab[rec])[2]+"</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Longitude</b></td>"+"<td>:</td>"+"<td>"+this.Base.GetBase().get(tab[rec])[3]+"</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Population</b></td>"+"<td>:</td>"+"<td>"+pop+" <i><b>(en 2019)</b></i></td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Region</b></td>"+"<td>:</td>"+"<td>"+reg+"</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Country</b></td>"+"<td>:</td>"+"<td>"+this.Base.GetBase().get(tab[rec])[4]+"</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Country ISO3</b></td>"+"<td>:</td>"+"<td>"+this.Base.GetBase().get(tab[rec])[6]+"</td>"
						+ "</tr>"
						+ "</table></html>");
			
			}
			
		}
		
		if(e.getSource()==w.getView()) {
			
			String lien ="https://www.google.com/maps/search/"+lat+","+lon; 
			try {
				
			    Desktop.getDesktop().browse(new URL(lien).toURI());
			} catch (Exception a) {}
		}
			
				
	}
	
	public void Find() {
		this.Base.Search(w.getVille());
		
		if(this.Base.getnb()==0) {
			this.w.getInfo().setVisible(false);
			this.w.getNext().setVisible(false);
			this.w.getPrevious().setVisible(false);
			this.w.getView().setVisible(false);
			rec=-1;
			JOptionPane.showMessageDialog(null,
					"Sorry!  We can not find this city name in our database!\nEnsure that the name is correct and written in English!",
					"Information", JOptionPane.INFORMATION_MESSAGE);
		}
		
		else {
			
			String pop,reg;
			rec = this.Base.getTab()[0];
			w.getView().setVisible(true);
			
			if(this.Base.getnb()==1) {
				this.w.getInfo().setText("  We found 1 record for this search.  ");
				this.w.getInfo().setVisible(true);
				lat= Double.valueOf(this.Base.GetBase().get(rec)[2]).doubleValue();
				lon= Double.valueOf(this.Base.GetBase().get(rec)[3]).doubleValue();
				this.w.getNext().setVisible(false);
				this.w.getPrevious().setVisible(false);
				
				if(this.Base.GetBase().get(rec)[9].equals("0")) {
					pop="No data available";
				}
				else {
					pop=this.Base.GetBase().get(rec)[9];
				}
				
				if(this.Base.GetBase().get(rec)[7].equals("0")) {
					reg="No data available";
				}
				else {
					reg=this.Base.GetBase().get(rec)[7];
				}
				
				w.getScreen().setText("<html><table style=\\\"color: #02385E;\\\">"
						+ "<tr>"
						+ "<td><b>City</b></td>"+"<td>:</td>"+"<td>"+this.Base.GetBase().get(rec)[0]+"</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Latitude</b></td>"+"<td>:</td>"+"<td>"+this.Base.GetBase().get(rec)[2]+"</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Longitude</b></td>"+"<td>:</td>"+"<td>"+this.Base.GetBase().get(rec)[3]+"</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Population</b></td>"+"<td>:</td>"+"<td>"+pop+" <i><b>(en 2019)</b></i></td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Region</b></td>"+"<td>:</td>"+"<td>"+reg+"</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Country</b></td>"+"<td>:</td>"+"<td>"+this.Base.GetBase().get(rec)[4]+"</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Country ISO3</b></td>"+"<td>:</td>"+"<td>"+this.Base.GetBase().get(rec)[6]+"</td>"
						+ "</tr>"
						+ "</table></html>");
			
			}
			
			if(this.Base.getnb()>1) {
				this.rec = 0;
				int[] tab = this.Base.getTab();
				this.w.getInfo().setText("  We found "+Base.getnb()+" records for this search.  (record : "+(rec+1)+"/"+this.Base.getnb()+")  ");
				this.w.getInfo().setVisible(true);
				this.w.getNext().setVisible(true);
				this.w.getPrevious().setVisible(true);
				
				lat= Double.valueOf(this.Base.GetBase().get(tab[rec])[2]).doubleValue();
				lon= Double.valueOf(this.Base.GetBase().get(tab[rec])[3]).doubleValue();
				
				if(this.Base.GetBase().get(tab[rec])[9].equals("0")) {
					pop="No data available";
				}
				else {
					pop=this.Base.GetBase().get(tab[rec])[9];
				}
				
				if(this.Base.GetBase().get(tab[rec])[7].equals("0")) {
					reg="No data available";
				}
				else {
					reg=this.Base.GetBase().get(tab[rec])[7];
				}
				
				w.getScreen().setText("<html><table style=\\\"color: #02385E;\\\">"
						+ "<tr>"
						+ "<td><b>City</b></td>"+"<td>:</td>"+"<td>"+this.Base.GetBase().get(tab[rec])[0]+"</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Latitude</b></td>"+"<td>:</td>"+"<td>"+this.Base.GetBase().get(tab[rec])[2]+"</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Longitude</b></td>"+"<td>:</td>"+"<td>"+this.Base.GetBase().get(tab[rec])[3]+"</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Population</b></td>"+"<td>:</td>"+"<td>"+pop+" <i><b>(en 2019)</b></i></td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Region</b></td>"+"<td>:</td>"+"<td>"+reg+"</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Country</b></td>"+"<td>:</td>"+"<td>"+this.Base.GetBase().get(tab[rec])[4]+"</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Country ISO3</b></td>"+"<td>:</td>"+"<td>"+this.Base.GetBase().get(tab[rec])[6]+"</td>"
						+ "</tr>"
						+ "</table></html>");
			
			}
		}
	}
}
