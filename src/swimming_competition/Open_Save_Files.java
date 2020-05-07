/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swimming_competition;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author sutha
 */
public class Open_Save_Files {
    
public static ArrayList<String> Read_Input_File(String path) 
    {
        ArrayList<String> inputData=new ArrayList<String>();
        String currentline="";
        System.out.println("");
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(path));       // initilize filereader to read file  
            while ((currentline=br.readLine()) != null) 
            {
                inputData.add(currentline);
              //  System.out.println(currentline);
            }
            return inputData;
        }
        catch(IOException | NumberFormatException FileNotFoundException)
        {
            return inputData;
        }               
        
    }
    
    public static boolean Save_Output(ArrayList<String> inputData,String path) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
            for (String inputData1 : inputData) {
                writer.write(inputData1);
                writer.newLine();
            }
            writer.close();
            return true;
        }
        catch(Exception IOException)
        { return false;}
    }     
}
