package controler;


import java.awt.event.*;
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int[] tab = this.Base.getTab();
		
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
			else {
				if(w.getVille().length()!=0) {
					JOptionPane.showMessageDialog(null,
							"Sorry! The city name is incorrect!",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		if(e.getSource()==w.getNext()) {
			
			if(rec<this.Base.getnb()-1) {
				this.rec += 1;
				this.w.getInfo().setText("  We found "+Base.getnb()+" records for this search.  (record : "+(rec+1)+"/"+this.Base.getnb()+")  ");
				this.w.getInfo().setVisible(true);
				Result();
			}
			
		}
		
		if(e.getSource()==w.getPrevious()) {
			if(rec>0) {
				this.rec -= 1;
				this.w.getInfo().setText("  We found "+Base.getnb()+" records for this search.  (record : "+(rec+1)+"/"+this.Base.getnb()+")  ");
				this.w.getInfo().setVisible(true);
				Result();
			}
			
		}
		
		if(e.getSource()==w.getView()) {
			
			String region=new String();
			if(this.Base.GetBase().get(tab[rec])[7].equals("0"))
				region=", ";
			else 
				region=", "+this.Base.GetBase().get(tab[rec])[7]+", ";
			
			
			String title = "View of "+this.w.getVille()+region+this.Base.GetBase().get(tab[rec])[4]; 
			Map m = new Map(lat,lon,title);
			m.getFrame().setVisible(true);
			
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
			
			rec =  0;			
			
			w.getView().setVisible(true);
			
			if(this.Base.getnb()==1) {
				
				this.w.getInfo().setText("  We found 1 record for this search.  ");
				this.w.getInfo().setVisible(true);
				this.w.getNext().setVisible(false);
				this.w.getPrevious().setVisible(false);
				Result();			
			
			}
			
			if(this.Base.getnb()>1) {
				
				this.w.getInfo().setText("  We found "+Base.getnb()+" records for this search.  (record : "+(rec+1)+"/"+this.Base.getnb()+")  ");
				this.w.getInfo().setVisible(true);
				this.w.getNext().setVisible(true);
				this.w.getPrevious().setVisible(true);
				Result();
			}
		}
	}
	
	public void Result() {
		String pop,reg;
		int[] tab = this.Base.getTab();
				
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
