/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swimming_competition;

import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author sutha
 */

class Swimming_Pool_Model{
    
    ArrayList<Swimmers> swimmers;       // swimmers objects
    ArrayList<SwimLanes> swimlanes;     // lane objects
    ArrayList<Judge> judges;            // judge objects
    ArrayList<JLabel> Spectors_label;
    ArrayList<JLabel> Scoreboard_label;    
    
    public void set_Scoreboard_label(ArrayList<JLabel> Scoreboard_label)
    {
        this.Scoreboard_label=Scoreboard_label;
    }
    public ArrayList<JLabel> get_Scoreboard_label()
    {
        return this.Scoreboard_label;
    }
    
    public void set_Spectors_label(ArrayList<JLabel> Spectors_label)
    {
        this.Spectors_label=Spectors_label;
    }
    public ArrayList<JLabel> get_Spectors_label()
    {
        return this.Spectors_label;
    }
    
    public void set_judges(ArrayList<Judge> judges)
    {
        this.judges=judges;
    }
    public ArrayList<Judge> get_judges()
    {
        return this.judges;
    }
    
    public void set_swimlanes(ArrayList<SwimLanes> swimlanes)
    {
        this.swimlanes=swimlanes;
    }
    public ArrayList<SwimLanes> get_swimlanes()
    {
        return this.swimlanes;
    }
    
    public void set_swimmers(ArrayList<Swimmers> swimmer)
    {
        this.swimmers=swimmer;
    }
    public ArrayList<Swimmers> get_swimmers()
    {
        return this.swimmers;
    }
    
}

class Swimming_Pool_View{
    
    Swimming_Pool pool;
    public void Run_Swimming_Pool()
    {
        pool=new Swimming_Pool();
        pool.setVisible(true);
    }
    
    public void set_primary_values(ArrayList<Swimmers> swimmer,ArrayList<Judge> judges,String[] settings)
    {
        pool.set_primary_values(swimmer,judges, settings);
    }
    
}

class Swimming_Pool_Controller{
    
    private Swimming_Pool_View view;
    private Swimming_Pool_Model model;
    
    public Swimming_Pool_Controller(Swimming_Pool_View view,Swimming_Pool_Model model)
    {
        this.view=view;
        this.model=model;
    }
    
    public void set_primary_values(ArrayList<Swimmers> swimmer,ArrayList<Judge> judges,String[] settings)
    {
        view.set_primary_values(swimmer,judges, settings);
    }
    
    public void Run_Swimming_Pool()
    {
        view.Run_Swimming_Pool();
    }
    
    public void Add_Lanes_to_Swimmers()
    {
        int index=0;
        for(Swimmers swimmer:model.get_swimmers())
        {
            swimmer.set_swimlane(model.get_swimlanes().get(index));
            swimmer.initilize_Icons();
            model.get_swimlanes().get(index).set_enable(true);
            index++;
        }
        for(int i=index;i<5;i++)
        {
            model.get_swimlanes().get(index).set_enable(false);
        }
    }
    
}
