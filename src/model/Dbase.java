/**
 * 
 */
package model;

import java.io.*;
import java.nio.charset.*;
import java.util.*;

import com.sun.tools.javac.Main;

/**
 * @author Eliantus de MICHEL
 *
 */
public class Dbase {
	
	private String link;
	private ArrayList<String[]> Base = new ArrayList<String[]>();
	private int nb;
	private int[] tab;
	
	public Dbase(String a) {
		link = a;
		this.Load();
		
	}
	
	public ArrayList<String[]> GetBase(){
		return Base;
	}
	
	
	public String getLink() {
		return this.link;
	}
	
	public void Load() {
		BufferedReader f = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(link),StandardCharsets.UTF_8));
				
		String line = "";
        String[] Temp;
     
			try {
				while((line = f.readLine()) != null) {
				   Temp = line.split(";");
				   Base.add(Temp);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
								
	}
	
	public int getnb() {
		return nb;
	}
	
	public int[] getTab() {
		return tab;
	}
	
	public void Search(String ville) {
		nb = 0;
				
		for(int j=0; j<Base.size(); j++) {
			
				if(Base.get(j)[0].toLowerCase().equals(ville.toLowerCase())||Base.get(j)[1].toLowerCase().equals(ville.toLowerCase())) {
					nb+=1;
				}
		}
		
		if(nb>0) {
			tab = new int [nb];
			int k=0;
			
			for(int j=0; j<Base.size(); j++) {
				
				if(Base.get(j)[0].toLowerCase().equals(ville.toLowerCase())||Base.get(j)[1].toLowerCase().equals(ville.toLowerCase())) {
					tab[k]=j;
					k+=1;
				}
			}
			
		}		
		
	}

}
