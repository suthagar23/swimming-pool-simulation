/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swimming_competition;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author sutha
 */
public class Save_Competiton_Details extends Convert {
    

    private   ArrayList<String> Lane_watchers;
    private   ArrayList<String> Time_Keepers;
    private   ArrayList<String> Lines;
    TimeZone tz = TimeZone.getTimeZone("UTC");
    SimpleDateFormat df = new SimpleDateFormat("mm:ss.SSS");
    
    public Save_Competiton_Details()
    {
        Lines=new ArrayList<>();
        
        Lane_watchers=Open_Save_Files.Read_Input_File("Lane_watchers.txt");
        Time_Keepers=Open_Save_Files.Read_Input_File("Time_Keepers.txt");
    }
    
    public void Save_Competition_Detils(ArrayList<Swimmers> Swimmer,ArrayList<Swimmers> Ordered_Swimmer,
            ArrayList<Judge> judges,String[] settings)
    {
        int count=0; String[] items;
        Lines.clear();
        Lines.add("\t SWIMMING COMPETITION ");
        Lines.add("");
        Lines.add("\t Selection Gender : " + settings[0]+"");
        Lines.add("\t Selection Stroke : " + settings[1]+"");
        Lines.add("\t Selection Swim Length : " + settings[2]+"\n");
        Lines.add("\t Start Time : " + Ordered_Swimmer.get(0).get_start_time()+"");
        Lines.add("");
        Lines.add("\t Judges Details ");
        for(Judge judge:judges)
        {
            count++;
            Lines.add("\t\t "+count+". "+judge.get_name()+" ("+judge.get_gender()+") ");
        }
        Lines.add("");
        Lines.add("\t Lane Watchers Details ");
        count=0;
        for(String data:Lane_watchers)
        {   count++;
            items=data.split(","); 
            Lines.add("\t\t "+count+". "+items[1].charAt(0)+"."+items[0]+" ");
            if(count==Swimmer.size()){ break; }
        }
        Lines.add("");
        Lines.add("\t Time Keepers Details ");

        count=0;
        for(String data:Time_Keepers)
        {
            count++;
            items=data.split(",");
            System.out.println(Arrays.asList(items));
            Lines.add("\t\t "+count+". "+items[1].charAt(0)+"."+items[0]+" ");
            if(count==Swimmer.size()){ break; }
        }
        Lines.add("");
        Lines.add("\t Swimmer Details ");
        count=0;
        for(Swimmers swimmer:Ordered_Swimmer)
        {
            count++;
            Lines.add("\t Name : "+swimmer.get_name()+" ");
            Lines.add("\t\t Win Place : "+count+" ");
            Lines.add("\t\t Body Condion : "+swimmer.get_Body_Condition()+" ");
            Lines.add("\t\t Lane ID: "+swimmer.get_swimlane().get_Lane_Id()+"");
            Lines.add("\t\t Swam Time : "+convert_to_time(swimmer.get_Swam_time())+" s");
            Lines.add("\t\t Avg.Speed : "+Float.parseFloat(String.format("%.2f",((float)(swimmer.get_total_length()*10)/(float)swimmer.get_Swam_time())))+" m/s ");
            Lines.add(" ");
        }
        
        Open_Save_Files.Save_Output(Lines, "games\\"+Swimmer.get(0).get_start_time().getTime()+".txt");
    }
    
    @Override
    public String convert_to_time(Long millis)
    {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return (formatter.format(calendar.getTime())); 
    }
}

abstract  class Convert{
    public abstract String convert_to_time(Long millisec);
}