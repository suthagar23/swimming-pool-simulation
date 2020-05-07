/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package swimming_competition;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Random;
import java.util.TimeZone;
import javax.swing.ImageIcon;
import java.util.Observer;
/**
 *
 * @author Sutha
 */
public class Swimmers extends Person implements Observer{

    private String body_condition_;
    private long Swam_time_=0;
    private int total_length;
    private int Swam_length_=0;
    private int win_place_;
    private int swimmer_main_index_;
    private int swimming_speed_;
    private boolean forward_swim=true;
    private int loop_count=0;
    private int total_loops_;
    private Swimming_Pool swimming_pool;
    private SwimLanes swimlane;
 
    
    ImageIcon backward_swim_image;
    ImageIcon forward_swim_image;
    ImageIcon backward_swim_hand_image;
    ImageIcon forward_swim_hand_image;
    
    ImageIcon finish_image = new ImageIcon(getClass().getResource("finish1.png"));
    ImageIcon start_image = new ImageIcon(getClass().getResource("start1.png"));
    
   // DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    TimeZone tz = TimeZone.getTimeZone("UTC");
    SimpleDateFormat df = new SimpleDateFormat("mm:ss.SSS");
    Date date;
    Date start_time;
    
    private Spectators spectors;
    
    public Swimmers(String name, int age, String gender,String country,int total_loops,String body_condition) {
        super(name, age, gender, "Swimmer",country);
        this.total_loops_=total_loops;
        Random random = new Random();
        swimming_speed_ = random.nextInt(25-20)+20;                 // speed range 20 - 25
        this.body_condition_=body_condition;
        //System.out.println(get_name()+" speed : "+swimming_speed_);
        spectors=new Spectators();
        spectors.addObserver(this);
    }
    
    public void reset()
    {
        Swam_time_=0;
        total_length=0;
        Swam_length_=0;
        forward_swim=true;
        loop_count=0;
    }
    
    public void initilize_Icons()
    {
        if(get_gender().equals("Male"))
        {
         backward_swim_image = new ImageIcon(getClass().getResource("5.png"));
         forward_swim_image = new ImageIcon(getClass().getResource("3.png"));
         backward_swim_hand_image = new ImageIcon(getClass().getResource("6.png"));
         forward_swim_hand_image = new ImageIcon(getClass().getResource("4.png"));
         swimlane.get_Swimmer_image().setIcon(new ImageIcon(getClass().getResource("waiting1.png")));
        }
        else
        {
         backward_swim_image = new ImageIcon(getClass().getResource("fe_5.png"));
         forward_swim_image = new ImageIcon(getClass().getResource("fe_3.png"));
         backward_swim_hand_image = new ImageIcon(getClass().getResource("fe_6.png"));
         forward_swim_hand_image = new ImageIcon(getClass().getResource("fe_4.png"));
         swimlane.get_Swimmer_image().setIcon(new ImageIcon(getClass().getResource("fe_waiting.png")));
        }
    }
    
    
    public void set_details(String body_condition, int swimmer_main_index)
    {
        this.body_condition_=body_condition;
        this.swimmer_main_index_=swimmer_main_index;
     //   this.swimming_speed_=swimming_speed;
    }
    
    public void set_swimlane(SwimLanes lane)
    {
        this.swimlane=lane;
    }
    
    public SwimLanes get_swimlane()
    {
        return this.swimlane;
    }
    
    public String get_Body_Condition()
    {
        return this.body_condition_;
    }
    
    public int get_swimmer_main_index()
    {
        return this.swimmer_main_index_;
    }
    
    public void set_winning_place(int place)
    {
        this.win_place_=place;
    }

    public int get_swimming_strokes_speed()
    {
        return swimming_speed_;
    }  
    
    public void set_swimming_speed_(int speed)
    {
        swimming_speed_=speed;
    }
    
    public int get_Swam_length()
    {
        return Swam_length_;
    }

    public long get_Swam_time()
    {
        return Swam_time_;
    }
    
    public int get_total_length()
    {
        return total_length;
    }
    
    public Date get_start_time()
    {
        return date;
    }
     
    public void set_swimming_pool(Swimming_Pool pool,Date start)
    {
        this.swimming_pool=pool;
        start_time=start;
        spectors.add_spectors_label(swimming_pool.get_Spectors_Label());
    }
    
    public void Start_individual_swimming(){
        
        class SwimmerThread extends Thread{
            public void run() {
                Run_Swim_loops();
                Swim_finish();
            }
            public synchronized void Swim_finish(){
                swimming_pool.enble_start_button();
            }
        }
        SwimmerThread swimmerthread=new SwimmerThread();
        swimmerthread.start();
        swimmerthread.setName(get_name());          // set swimmer name
    }
    
    private synchronized void Run_Swim_loops()
    {
                if(total_loops_==1)
                {increase_swam_length(); }
                else if(total_loops_==2)
                { increase_swam_length();
                    decrease_swam_length();}
                else if(total_loops_==3)
                { increase_swam_length();
                    decrease_swam_length();
                    increase_swam_length();}
                else if(total_loops_==4)
                { for(int i=0;i<2;i++) {
                    increase_swam_length();
                    decrease_swam_length();}}
                else if(total_loops_==5)
                { for(int i=0;i<2;i++) {
                    increase_swam_length();
                    decrease_swam_length();}
                increase_swam_length();}
                else if(total_loops_==6)
                { for(int i=0;i<3;i++) {
                    increase_swam_length();
                    decrease_swam_length();}}
    }
    
    int blink_count=0,random_int=0;
    public void increase_swam_length()
    {
        loop_count+=1;
        swimlane.get_Lane_loop_count().setText(Integer.toString(loop_count));
        swimlane.get_start_finish_label().setBackground(Color.green);
        swimlane.get_Swimmer_image().setIcon(forward_swim_image);
        while (Swam_length_ <= 1000) 
        {
            Random random = new Random();
            random_int=random.nextInt(swimming_speed_);
            Swam_length_ += random_int;
            total_length+=random_int;
            spectors.changed_swim_length(Swam_length_);
            date=new Date();
            swimlane.get_Swimimng_time().setText(convert_to_time((date.getTime()-start_time.getTime())));
        //    System.out.println(super.get_name()+ ": " + Swam_length_);
          //  swimming_pool.show_score_board();
            blink_count++;
            if(blink_count<=7)
            { swimlane.get_lane_perfection().setBackground(Color.white); swimlane.get_Swimmer_image().setIcon(forward_swim_image);}
            else if(blink_count>=7 && blink_count<=14)
            { swimlane.get_lane_perfection().setBackground(Color.green); swimlane.get_Swimmer_image().setIcon(forward_swim_hand_image);}
            else {blink_count=0; }

            try {Thread.sleep(50);} 
            catch (InterruptedException e) {}
        }
        
        swimlane.get_touch_pad_image().setBackground(Color.green);
        if(total_loops_-1==loop_count)
        {   // 2 loop only, 2nd loop is finish , work for <---
            swimlane.get_start_finish_label().setIcon(finish_image);
            swimlane.get_start_finish_label().setBackground(Color.red);
        }
        else if(total_loops_-3==loop_count)
        {   // 4 loop only, 2nd  loop , work for <--
            swimlane.get_start_finish_label().setIcon(swimlane.get_touch_pad_image().getIcon());
            swimlane.get_start_finish_label().setBackground(Color.red);
        }
        else if(total_loops_-5==loop_count)
        {   // 6 loop only, 2nd  loop , work for <--
            swimlane.get_start_finish_label().setIcon(swimlane.get_touch_pad_image().getIcon());
            swimlane.get_start_finish_label().setBackground(Color.red);
        }
        
    }
    
    public void decrease_swam_length()
    {
        loop_count+=1;
        swimlane.get_Lane_loop_count().setText(Integer.toString(loop_count));
        swimlane.get_Swimmer_image().setIcon(backward_swim_image);
        while (Swam_length_ >0) 
        {
            Random random = new Random();
            random_int=random.nextInt(swimming_speed_);
            Swam_length_ -= random_int;
            total_length+=random_int;
            spectors.changed_swim_length(Swam_length_);
            date=new Date();
            swimlane.get_Swimimng_time().setText(convert_to_time((date.getTime()-start_time.getTime())));
           // swimming_pool.show_score_board();
          // System.out.println(super.get_name()+ ": " + Swam_length_);
            blink_count++;
            if(blink_count<=7)
            { swimlane.get_lane_perfection().setBackground(Color.white); swimlane.get_Swimmer_image().setIcon(backward_swim_image);}
            else if(blink_count>=7 && blink_count<=14)
            { swimlane.get_lane_perfection().setBackground(Color.green); swimlane.get_Swimmer_image().setIcon(backward_swim_hand_image);}
            else {blink_count=0; }
            
            try {Thread.sleep(50);} 
            catch (InterruptedException e) {}
        }
        
        if(loop_count==total_loops_)
        {   // 2 loop only, <-- finished
            swimlane.get_Swimmer_image().setIcon(backward_swim_image);
            swimlane.get_start_finish_label().setBackground(Color.green);
            swimlane.get_lane_perfection().setBackground(Color.green);
            swimming_pool.empty_spectors_labels(swimming_pool.get_Spectors_Label());
          //  swimming_pool.enble_start_button();
            
         //   swimming_pool.show_score_board();
        }
        else if(total_loops_-2==loop_count)
        {
            swimlane.get_start_finish_label().setBackground(Color.green);
            swimlane.get_touch_pad_image().setBackground(Color.red);
        }
        else if(total_loops_-4==loop_count)
        {
            swimlane.get_start_finish_label().setBackground(Color.green);
            swimlane.get_touch_pad_image().setBackground(Color.red);
        }
    }
    
    private String convert_to_time(long millis)
    {
        Swam_time_=millis;
        df.setTimeZone(tz);
        return df.format(new Date(millis));
    }

    @Override
    public void update(Observable o, Object arg) {
           spectors.Spectors_Action(arg,swimming_pool,get_name());
    }
}
