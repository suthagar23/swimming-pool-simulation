/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swimming_competition;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.JLabel;

/**
 *
 * @author Sutha
 */
public class Judge extends Person
{
    private String desination_;
    private JLabel status_label;
    private ArrayList<Swimmers> ordered_swimmers;
    public Judge(String name, int age, String gender,String country) {
        super(name, age, gender, "Judge",country);
        ordered_swimmers=new ArrayList<>();
    }
    
    public void set_status_label(JLabel status)
    {
        this.status_label=status;
    }
    
    public JLabel get_satus_label()
    {
        return status_label;
    }
    
    public void set_desination(String desination){
        desination_=desination;
    }
    
    public boolean check_swimmer_body_status(ArrayList<Swimmers> swimmers)
    {
        for(Swimmers swimmer:swimmers)
        {
            if(swimmer.get_Body_Condition() == null ? ("good") != null : !swimmer.get_Body_Condition().equals("good"))
            {
                return false;
            }
        }
        return true;
    }
    
    public void Start_whistle(ArrayList<Swimmers> swimmers, Swimming_Pool pool)
    {
        start_competition(swimmers,pool);
    }
    
    private void start_competition(ArrayList<Swimmers> swimmers, Swimming_Pool pool)
    {
        for(Swimmers swimmer:swimmers)
        {
            swimmer.set_swimming_pool(pool,new Date());
            swimmer.Start_individual_swimming();
        }
    }
    
    public void Start_Watching(String text)
    {
        status_label.setText(text);
    }
    

    public ArrayList<Swimmers> Order_swimmers(ArrayList<Swimmers> swimmers)
    {
        float[][] order_index=new float[swimmers.size()][2];
        for(int i=0;i<swimmers.size();i++)
        {
            order_index[i][0]=swimmers.get(i).get_Swam_time();
          //  System.out.println(order_index[i][0]);
            order_index[i][1]=i;
        }
        java.util.Arrays.sort(order_index, new java.util.Comparator<float[]>() {
            public int compare(float[] a, float[] b) 
            {
                return Double.compare(b[0], a[0]);
            }
            });
        
        ordered_swimmers.clear();
        for(int i=0;i<order_index.length;i++)
        {
            ordered_swimmers.add(swimmers.get((int)order_index[order_index.length-i-1][1]));
        }
  
        return ordered_swimmers;
    }
}
