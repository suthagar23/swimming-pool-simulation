/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swimming_competition;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import javax.swing.JLabel;

/**
 *
 * @author sutha
 */
public class Spectators extends Observable {
    
    private int swim_length;
    private String[] spectors_msg;
    private ArrayList<JLabel> Spectors_label;
    int count;
    public Spectators()
    {
        spectors_msg=new String[4];
        spectors_msg[0]="Ohoo!!!";
        spectors_msg[1]="Shit..! Lost";
        spectors_msg[2]="Woww!..";
        spectors_msg[3]="curry up!!!";     
        count=0;
    }
    
    public void add_spectors_label(ArrayList<JLabel> spectors_label)
    {
        this.Spectors_label=spectors_label;
    }
    
    public void changed_swim_length(int value) {
        if(swim_length!=(value)) 
        {
             swim_length = value;
             setChanged();
             notifyObservers(value);
        }
   }
    
    public void Spectors_Action(Object arg,Swimming_Pool pool,String Name)
    {
        count++;
        if(count>=20)
        {
            Random random = new Random();
            pool.empty_spectors_labels(Spectors_label);
            Spectors_label.get(random.nextInt(8-0)).setText(spectors_msg[random.nextInt(4-0)]);
            Spectors_label.get(random.nextInt(8-0)).setText(Name+" " +spectors_msg[random.nextInt(4-0)]);
            Spectors_label.get(random.nextInt(8-0)).setText(spectors_msg[random.nextInt(4-0)]);
            count=0;
        }
    }
}
