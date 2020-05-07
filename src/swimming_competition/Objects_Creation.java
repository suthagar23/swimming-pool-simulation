/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swimming_competition;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author sutha
 */
public class Objects_Creation {
    
    ArrayList<Swimmers> swimmers;
    ArrayList<Judge> judge;
    ArrayList<SwimLanes> swimlanes;
    public Objects_Creation()
    {
         swimmers=new ArrayList();
         judge=new ArrayList();
         swimlanes=new ArrayList();
    }
    
    public ArrayList<Swimmers> Swimmer_Object_Creation(ArrayList<String> Swimmers_List,ArrayList<String> selected_List,int[] competition_settings)
    {
        swimmers.clear();
        int index=0;
        String data[];
        for(String item:selected_List)
        {
            Swimmers swimmer_obj;
            data=Swimmers_List.get(Integer.parseInt(selected_List.get(index))).split(",");
            System.out.println(Arrays.asList(data));
            if(data[3].equals("0"))
            {
                swimmer_obj=new Swimmers((data[1].charAt(0)+"."+data[0]),Integer.parseInt(data[2]),"Male",data[4],(2*competition_settings[2]+2),"good");
            }
            else
            {
                swimmer_obj=new Swimmers((data[1].charAt(0)+"."+data[0]),Integer.parseInt(data[2]),"Female",data[4],(2*competition_settings[2]+2),"good");
            }
            swimmers.add(swimmer_obj);
            index++;
        }
        return swimmers;
    }
    
    public ArrayList<Judge> Judge_Creation(ArrayList<String> Judge_List)
    {
        judge.clear();
        int index=0;
        String data[];
        for(String item:Judge_List)
        {
            Judge Judge_obj;
            data=item.split(",");
            if(data[3].equals("0"))
            {
                Judge_obj=new Judge((data[1].charAt(0)+"."+data[0]),Integer.parseInt(data[2]),"Male",data[4]);
            }
            else
            {
                Judge_obj=new Judge((data[1].charAt(0)+"."+data[0]),Integer.parseInt(data[2]),"Female",data[4]);
            }
            judge.add(Judge_obj);
            index++;
        }
        return judge;
    }
    
    public ArrayList<SwimLanes> create_lane_objects(Swimming_Pool pool)
    {
        swimlanes.clear();
        SwimLanes lane;
        for(int index=0;index<5;index++)
        {
                lane=new SwimLanes(Integer.toString(index),pool.get_JLABELS(index)[0],pool.get_JLABELS(index)[1],pool.get_JLABELS(index)[2], 
                pool.get_JLABELS(index)[3],pool.get_JLABELS(index)[4],pool.get_JLABELS(index)[5],pool.get_JLABELS(index)[6],true);
                swimlanes.add(lane);
        }
         return swimlanes;
    }
    
    public void create_supporting_staff_object(ArrayList<String> staff_List)
    {
        String data[];
        for(String item:staff_List)
        {
            data=staff_List.get(0).split(",");
            Person staff=new Supporting_staff(data[0],Integer.parseInt(data[2]),data[3],"Staff","");  
        }
    }
}
