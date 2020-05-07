/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package swimming_competition;

import javax.swing.JLabel;

/**
 *
 * @author Suthagar 140611L
 */
public class SwimLanes {
    
    private int SwimLane_length_;
    private String SwimLane_Id_;
    private boolean enable;
    private Swimmers Lane_Swimmer_;
    
    private JLabel Lane_perfection;
    private JLabel Lane_loop_count;
    private JLabel Swimimng_time;
    private JLabel Swimmer_image;
    private JLabel touch_pad_image;
    private JLabel start_finish_label;
    private JLabel rank_label;
    
    SwimLanes(String id,JLabel Lane_perfection,JLabel Lane_loop_count,JLabel Swimimng_time,
            JLabel Swimmer_image,JLabel touch_pad_image,JLabel start_finish_label,JLabel rank_label,boolean enable)
    {
        SwimLane_length_=1050;
        SwimLane_Id_=id;
        this.enable=enable;
        this.Lane_perfection=Lane_perfection;
        this.Lane_loop_count=Lane_loop_count;      
        this.Swimimng_time=Swimimng_time;
        this.Swimmer_image=Swimmer_image;
        this.touch_pad_image=touch_pad_image;
        this.start_finish_label=start_finish_label;
        this.rank_label=rank_label;
    }

    public void set_enable(boolean enable)
    {
        this.enable=enable;
    }
    
    public boolean get_enable()
    {
        return enable;
    }
    
    public JLabel get_rank_label()
    {
        return rank_label;
    }  
    
    public JLabel get_start_finish_label()
    {
        return start_finish_label;
    }  
        
    public JLabel get_touch_pad_image()
    {
        return touch_pad_image;
    }  
    
    public JLabel get_Swimmer_image()
    {
        return Swimmer_image;
    }
    
    public JLabel get_Swimimng_time()
    {
        return Swimimng_time;
    }
    
    public JLabel get_Lane_loop_count()
    {
        return Lane_loop_count;
    }
    
    public JLabel get_lane_perfection()
    {
        return Lane_perfection;
    }
    
    public void set_Lane_Swimmer(Swimmers Swimmer_obj)
    {
        Lane_Swimmer_=Swimmer_obj;
    }
    
    public Swimmers get_Lane_Swimmer()
    {
        return Lane_Swimmer_;
    }
    
    public String get_Lane_Id()
    {
        return SwimLane_Id_;
    }
    
    public float get_Lane_Length()
    {
        return SwimLane_length_;
    }
}
