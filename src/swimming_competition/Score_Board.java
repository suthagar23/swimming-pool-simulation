/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swimming_competition;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author sutha
 */
public class Score_Board {
    
    private ArrayList<JLabel> Score_board_labels;
    
    public Score_Board() { /** Score_Board constructor **/ }
    
    public void Watch_the_rank_changes() { /** returns the changes of ranks **/ }
    
    public void set_label(ArrayList<JLabel> score_board_labels)
    {
        this.Score_board_labels=score_board_labels;
    }
    
    public void Show_Details(ArrayList<Swimmers> ordered_swimmers)
    {
        float speed;
        if(ordered_swimmers.size()>=3)
        {
            for(int i=0;i<3;i++)
            {
               // if(Score_board_labels.get(2*i).getText() == null ? ordered_swimmers.get(i).get_name() != null : !Score_board_labels.get(2*i).getText().equals(ordered_swimmers.get(i).get_name())){
                Score_board_labels.get(2*i).setText(ordered_swimmers.get(i).get_name());
                speed=Float.parseFloat(String.format("%.2f",((float)(ordered_swimmers.get(i).get_total_length()*10)/(float)ordered_swimmers.get(i).get_Swam_time())));
                Score_board_labels.get(2*i+1).setText(Float.toString(speed)+" m/s");
            }//}
        }
        else
        {
            for(int i=0;i<ordered_swimmers.size();i++)
            {
              //  if(Score_board_labels.get(2*i).getText() == null ? ordered_swimmers.get(i).get_name() != null : !Score_board_labels.get(2*i).getText().equals(ordered_swimmers.get(i).get_name())){
                Score_board_labels.get(2*i).setText(ordered_swimmers.get(i).get_name());
                speed=Float.parseFloat(String.format("%.2f",((float)(ordered_swimmers.get(i).get_total_length()*10)/(float)ordered_swimmers.get(i).get_Swam_time())));
                Score_board_labels.get(2*i+1).setText(Float.toString(speed)+" m/s");
            }
        }
    }
    
    public void set_ranks_to_line(ArrayList<Swimmers> ordered_swimmers)
    {
        int index=1;
        for(Swimmers swimmer:ordered_swimmers)
        {
            swimmer.get_swimlane().get_rank_label().setIcon(new ImageIcon(getClass().getResource(index+""+index+".png")));
            swimmer.get_swimlane().get_rank_label().setText(swimmer.get_name());
            swimmer.get_swimlane().get_rank_label().setVisible(true);
            index++;
        }
    }
    
}
