/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swimming_competition;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.JLabel;
/**
 *
 * @author sutha
 */
public class Swimming_Pool extends javax.swing.JFrame {

    /**
     * Creates new form Swimming_Pool
     */
    ArrayList<Swimmers> swimmers;     // swimmers objects
    ArrayList<SwimLanes> swimlanes;      // lane objects
    ArrayList<Judge> judges;            // judge objects
    ArrayList<JLabel> Spectors_label;
    ArrayList<JLabel> Scoreboard_label;
     
    Objects_Creation obj;   
    Score_Board score_board;
    Save_Competiton_Details save_details;
    private final Message_Box msg_box;
    private boolean Swimming_in_progress;
    private  String[] competition_settings;
    
    ImageIcon finish_image = new ImageIcon(getClass().getResource("finish1.png"));
    ImageIcon start_image = new ImageIcon(getClass().getResource("start1.png"));
    
    public Swimming_Pool() {
        initComponents();
        msg_box=new Message_Box();
        obj=new Objects_Creation();
        score_board=new Score_Board();
        
        swimlanes=obj.create_lane_objects(this);
        Swimming_in_progress=false;
        Strat_labels(true);
        Start_changes();
        
    }
    
    private void Strat_labels(boolean start)
    {
        if(start==true)
        {
            jLabel14.setIcon(start_image);
            jLabel15.setIcon(start_image);
            jLabel16.setIcon(start_image);
            jLabel17.setIcon(start_image);
            jLabel18.setIcon(start_image);
        }
        else
        {
            jLabel14.setIcon(finish_image);
            jLabel15.setIcon(finish_image);
            jLabel16.setIcon(finish_image);
            jLabel17.setIcon(finish_image);
            jLabel18.setIcon(finish_image);
        }   
    }
    
    private void Start_changes()
    {
        jLabel8.setText("0"); jLabel24.setText("0"); jLabel26.setText("0"); jLabel28.setText("0"); jLabel30.setText("0");
        jLabel22.setText("00.00"); jLabel33.setText("00.00"); jLabel20.setText("00.00"); jLabel31.setText("00.00"); jLabel32.setText("00.00");
        
        jLabel2.setBackground(Color.red); jLabel3.setBackground(Color.red); jLabel5.setBackground(Color.red);
        jLabel4.setBackground(Color.red); jLabel13.setBackground(Color.red);
        
        jLabel8.setBackground(Color.white); jLabel24.setBackground(Color.white); jLabel26.setBackground(Color.white);
        jLabel28.setBackground(Color.white); jLabel30.setBackground(Color.white);
        
        jLabel21.setBackground(Color.white); jLabel23.setBackground(Color.white); jLabel25.setBackground(Color.white);
        jLabel27.setBackground(Color.white); jLabel29.setBackground(Color.white);
        
        initilize_spectors_label();
        empty_spectors_labels(Spectors_label);
        Score_Board_wroks();
    }
    
    private void Score_Board_wroks()
    {
        Scoreboard_label=new ArrayList<>();
        Scoreboard_label.add(jLabel51); Scoreboard_label.add(jLabel52);
        Scoreboard_label.add(jLabel53); Scoreboard_label.add(jLabel56);
        Scoreboard_label.add(jLabel59); Scoreboard_label.add(jLabel60);
        
        score_board.set_label(Scoreboard_label);
       // jPanel6.setVisible(false);
    }
    
    public void show_score_board()
    {
        
        score_board.Show_Details(judges.get(3).Order_swimmers(swimmers));
    }
    
    public void set_primary_values(ArrayList<Swimmers> swimmer,ArrayList<Judge> judges,String[] settings)
    {
        this.swimmers=swimmer;
        this.judges=judges;
        this.competition_settings=settings;
        Change_envireoments();
        Add_Lanes_to_Swimmers();
        Add_Staus_to_Judges();
    }
    
    private void Change_envireoments()
    {
        if(swimmers.size()==1)
        {jLabel33.setText("N/A"); jLabel20.setText("N/A"); jLabel31.setText("N/A"); jLabel32.setText("N/A");
        jLabel10.setVisible(false); jLabel9.setVisible(false); jLabel11.setVisible(false); jLabel12.setVisible(false);}
        else if(swimmers.size()==2)
        { jLabel20.setText("N/A"); jLabel31.setText("N/A"); jLabel32.setText("N/A"); 
        jLabel9.setVisible(false); jLabel11.setVisible(false); jLabel12.setVisible(false);}
        else if(swimmers.size()==3)
        { jLabel31.setText("N/A"); jLabel32.setText("N/A"); 
        jLabel11.setVisible(false); jLabel12.setVisible(false);}
        else if(swimmers.size()==4)
        { jLabel32.setText("N/A"); 
        jLabel12.setVisible(false);}
        
        jLabel57.setVisible(false); jLabel57.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        jLabel58.setVisible(false); jLabel58.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        jLabel61.setVisible(false); jLabel61.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        jLabel62.setVisible(false); jLabel62.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        jLabel63.setVisible(false); jLabel63.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }
    
    public JLabel[] get_JLABELS(int index)
    {
        if(index==0)
        {return new JLabel[]{jLabel21,jLabel8,jLabel22,jLabel6,jLabel2,jLabel14,jLabel57}; }
        else if(index==1)
        { return new JLabel[]{jLabel23,jLabel24,jLabel33,jLabel10,jLabel3,jLabel15,jLabel58};}
        else if(index==2)
        { return new JLabel[]{jLabel25,jLabel26,jLabel20,jLabel9,jLabel5,jLabel16,jLabel61};}
        else if(index==3)
        { return new JLabel[]{jLabel27,jLabel28,jLabel31,jLabel11,jLabel4,jLabel17,jLabel62};}
        else
        { return new JLabel[]{jLabel29,jLabel30,jLabel32,jLabel12,jLabel13,jLabel18,jLabel63};}
    }
    
    
    private void Add_Lanes_to_Swimmers()
    {
        int index=0;
        for(Swimmers swimmer:swimmers)
        {
            swimmer.set_swimlane(swimlanes.get(index));
            swimmer.initilize_Icons();
            swimlanes.get(index).set_enable(true);
            index++;
        }
        for(int i=index;i<5;i++)
        {
            swimlanes.get(index).set_enable(false);
        }
    }
    
    public void Add_Staus_to_Judges()
    {
        int index=0;
        for(Judge judge:judges)
        {
            if(index==0)
            {judge.set_status_label(jLabel45);}
            else if(index==1)
            {judge.set_status_label(jLabel46);}
            else if(index==2)
            {judge.set_status_label(jLabel47);}
            else if(index==3)
            {judge.set_status_label(jLabel44);}
            index++;
            judge.Start_Watching(judge.get_name());
        }
    }
    
    private void initilize_spectors_label()
    {
        Spectors_label=new ArrayList<>();
        Spectors_label.add(jLabel34); Spectors_label.add(jLabel35);
        Spectors_label.add(jLabel36); Spectors_label.add(jLabel37);
        Spectors_label.add(jLabel40); Spectors_label.add(jLabel41);
        Spectors_label.add(jLabel42); Spectors_label.add(jLabel43);
    }
    
    public ArrayList<JLabel> get_Spectors_Label()
    {
        return Spectors_label;
    }
    
    public void empty_spectors_labels(ArrayList<JLabel> Spectors_label)
    {
        for(JLabel label:Spectors_label)
        {
            label.setText("");
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Swimming Pool");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/1.png"))); // NOI18N
        jLabel1.setOpaque(true);

        jLayeredPane1.setBackground(new java.awt.Color(102, 102, 102));
        jLayeredPane1.setOpaque(true);

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setLayout(null);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/waiting1.png"))); // NOI18N
        jLabel6.setText("jLabel2");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(10, 10, 110, 110);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/waiting1.png"))); // NOI18N
        jLabel10.setText("jLabel2");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(10, 120, 110, 110);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/waiting1.png"))); // NOI18N
        jLabel9.setText("jLabel2");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(10, 230, 110, 110);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/waiting1.png"))); // NOI18N
        jLabel12.setText("jLabel2");
        jPanel3.add(jLabel12);
        jLabel12.setBounds(10, 440, 110, 110);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/waiting1.png"))); // NOI18N
        jLabel11.setText("jLabel2");
        jPanel3.add(jLabel11);
        jLabel11.setBounds(10, 340, 110, 110);

        jLabel57.setFont(new java.awt.Font("Cambria Math", 1, 30)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/11.png"))); // NOI18N
        jLabel57.setText("WINNER");
        jPanel3.add(jLabel57);
        jLabel57.setBounds(740, 10, 380, 110);

        jLabel58.setFont(new java.awt.Font("Cambria Math", 1, 30)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/22.png"))); // NOI18N
        jLabel58.setText("WINNER");
        jPanel3.add(jLabel58);
        jLabel58.setBounds(740, 120, 380, 110);

        jLabel61.setFont(new java.awt.Font("Cambria Math", 1, 30)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/33.png"))); // NOI18N
        jLabel61.setText("WINNER");
        jPanel3.add(jLabel61);
        jLabel61.setBounds(740, 230, 380, 110);

        jLabel62.setFont(new java.awt.Font("Cambria Math", 1, 30)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/44.png"))); // NOI18N
        jLabel62.setText("WINNER");
        jPanel3.add(jLabel62);
        jLabel62.setBounds(740, 340, 380, 110);

        jLabel63.setFont(new java.awt.Font("Cambria Math", 1, 30)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/55.png"))); // NOI18N
        jLabel63.setText("WINNER");
        jPanel3.add(jLabel63);
        jLabel63.setBounds(740, 440, 380, 110);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/swimming_pool.png"))); // NOI18N
        jLabel7.setText("jLabel3");
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153), 3));
        jPanel3.add(jLabel7);
        jLabel7.setBounds(0, 0, 1130, 560);

        jLabel50.setText("jLabel50");
        jPanel3.add(jLabel50);
        jLabel50.setBounds(970, 40, 60, 70);

        jLabel2.setBackground(new java.awt.Color(255, 0, 51));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/turn1.png"))); // NOI18N
        jLabel2.setText("   ");
        jLabel2.setOpaque(true);

        jLabel3.setBackground(new java.awt.Color(255, 0, 51));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/turn1.png"))); // NOI18N
        jLabel3.setText("   ");
        jLabel3.setOpaque(true);

        jLabel4.setBackground(new java.awt.Color(255, 0, 51));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/turn1.png"))); // NOI18N
        jLabel4.setText("   ");
        jLabel4.setOpaque(true);

        jLabel5.setBackground(new java.awt.Color(255, 0, 51));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/turn1.png"))); // NOI18N
        jLabel5.setText("   ");
        jLabel5.setOpaque(true);

        jLabel13.setBackground(new java.awt.Color(255, 0, 51));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/turn1.png"))); // NOI18N
        jLabel13.setText("   ");
        jLabel13.setOpaque(true);

        jLabel14.setBackground(new java.awt.Color(255, 0, 51));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/start1.png"))); // NOI18N
        jLabel14.setText("   ");
        jLabel14.setOpaque(true);

        jLabel15.setBackground(new java.awt.Color(255, 0, 51));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/start1.png"))); // NOI18N
        jLabel15.setText("   ");
        jLabel15.setOpaque(true);

        jLabel16.setBackground(new java.awt.Color(255, 0, 51));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/start1.png"))); // NOI18N
        jLabel16.setText("   ");
        jLabel16.setOpaque(true);

        jLabel17.setBackground(new java.awt.Color(255, 0, 51));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/start1.png"))); // NOI18N
        jLabel17.setText("   ");
        jLabel17.setOpaque(true);

        jLabel18.setBackground(new java.awt.Color(255, 0, 51));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/start1.png"))); // NOI18N
        jLabel18.setText("   ");
        jLabel18.setOpaque(true);

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("0");
        jLabel8.setOpaque(true);

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("00:00");
        jLabel20.setOpaque(true);

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/lane1.png"))); // NOI18N
        jLabel21.setText("   ");
        jLabel21.setOpaque(true);

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("00:00");
        jLabel22.setOpaque(true);

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/lane1.png"))); // NOI18N
        jLabel23.setText("   ");
        jLabel23.setOpaque(true);

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("0");
        jLabel24.setOpaque(true);

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/lane1.png"))); // NOI18N
        jLabel25.setText("   ");
        jLabel25.setOpaque(true);

        jLabel26.setBackground(new java.awt.Color(255, 255, 255));
        jLabel26.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("0");
        jLabel26.setOpaque(true);

        jLabel27.setBackground(new java.awt.Color(255, 255, 255));
        jLabel27.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/lane1.png"))); // NOI18N
        jLabel27.setText("   ");
        jLabel27.setOpaque(true);

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("0");
        jLabel28.setOpaque(true);

        jLabel29.setBackground(new java.awt.Color(255, 255, 255));
        jLabel29.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/lane1.png"))); // NOI18N
        jLabel29.setText("   ");
        jLabel29.setOpaque(true);

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("0");
        jLabel30.setOpaque(true);

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("00:00");
        jLabel31.setOpaque(true);

        jLabel32.setBackground(new java.awt.Color(255, 255, 255));
        jLabel32.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("00:00");
        jLabel32.setOpaque(true);

        jLabel33.setBackground(new java.awt.Color(255, 255, 255));
        jLabel33.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("00:00");
        jLabel33.setOpaque(true);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(14, 14, 14)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jLayeredPane1.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel16, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel18, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel20, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel21, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel22, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel23, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel24, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel25, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel26, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel27, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel28, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel29, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel30, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel31, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel32, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel33, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel4.setLayout(null);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setText("JUDGES PANEL");
        jPanel4.add(jLabel19);
        jLabel19.setBounds(240, 10, 182, 22);

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swimming_competition/judges.png"))); // NOI18N
        jLabel38.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel4.add(jLabel38);
        jLabel38.setBounds(150, 30, 310, 90);

        jLabel44.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(102, 102, 102));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("WAITING");
        jPanel4.add(jLabel44);
        jLabel44.setBounds(380, 110, 70, 20);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\sutha.DESKTOP-QF7IRVN\\Desktop\\oop_swiming competition\\start2.png")); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel4.add(jButton1);
        jButton1.setBounds(10, 10, 117, 103);

        jLabel45.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(102, 102, 102));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Waiting");
        jPanel4.add(jLabel45);
        jLabel45.setBounds(150, 110, 70, 19);

        jLabel46.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(102, 102, 102));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("WAITING");
        jPanel4.add(jLabel46);
        jLabel46.setBounds(230, 110, 70, 19);

        jLabel47.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(102, 102, 102));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("WAITING");
        jPanel4.add(jLabel47);
        jLabel47.setBounds(300, 110, 70, 19);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel5.setLayout(null);

        jLabel34.setFont(new java.awt.Font("Cambria Math", 0, 20)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 0, 0));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel34.setText("Speed !!!");
        jPanel5.add(jLabel34);
        jLabel34.setBounds(220, 50, 150, 25);

        jLabel36.setFont(new java.awt.Font("Cambria Math", 0, 20)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 0, 0));
        jLabel36.setText("Speed !!!");
        jPanel5.add(jLabel36);
        jLabel36.setBounds(530, 20, 130, 25);

        jLabel37.setFont(new java.awt.Font("Cambria Math", 0, 20)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 0, 0));
        jLabel37.setText("Speed !!!");
        jPanel5.add(jLabel37);
        jLabel37.setBounds(650, 10, 160, 25);

        jLabel35.setFont(new java.awt.Font("Cambria Math", 0, 20)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 0, 0));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel35.setText("Speed !!!");
        jPanel5.add(jLabel35);
        jLabel35.setBounds(304, 10, 200, 25);

        jLabel40.setFont(new java.awt.Font("Cambria Math", 0, 20)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 0, 0));
        jLabel40.setText("Speed !!!");
        jPanel5.add(jLabel40);
        jLabel40.setBounds(750, 20, 140, 25);

        jLabel41.setFont(new java.awt.Font("Cambria Math", 0, 20)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 0, 0));
        jLabel41.setText("Speed !!!");
        jPanel5.add(jLabel41);
        jLabel41.setBounds(884, 20, 180, 25);

        jLabel42.setFont(new java.awt.Font("Cambria Math", 0, 20)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 0, 0));
        jLabel42.setText("Speed !!!");
        jPanel5.add(jLabel42);
        jLabel42.setBounds(1080, 20, 220, 25);

        jLabel43.setFont(new java.awt.Font("Cambria Math", 0, 20)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 0, 0));
        jLabel43.setText("Speed !!!");
        jPanel5.add(jLabel43);
        jLabel43.setBounds(1180, 60, 170, 25);

        jLabel39.setIcon(new javax.swing.ImageIcon("C:\\Users\\sutha.DESKTOP-QF7IRVN\\Desktop\\oop_swiming competition\\spe1.png")); // NOI18N
        jLabel39.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel5.add(jLabel39);
        jLabel39.setBounds(310, 0, 890, 120);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel6.setLayout(null);

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel48.setText("SCORE BOARD");
        jPanel6.add(jLabel48);
        jLabel48.setBounds(10, 10, 182, 22);

        jLabel51.setFont(new java.awt.Font("Cambria Math", 1, 24)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(0, 0, 102));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel51.setText("N/A");
        jPanel6.add(jLabel51);
        jLabel51.setBounds(90, 60, 150, 29);

        jLabel52.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(102, 102, 102));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel52.setText("        ");
        jPanel6.add(jLabel52);
        jLabel52.setBounds(90, 90, 150, 19);

        jLabel54.setIcon(new javax.swing.ImageIcon("C:\\Users\\sutha.DESKTOP-QF7IRVN\\Desktop\\oop_swiming competition\\11.png")); // NOI18N
        jLabel54.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel6.add(jLabel54);
        jLabel54.setBounds(10, 40, 230, 90);

        jLabel53.setFont(new java.awt.Font("Cambria Math", 1, 24)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(0, 0, 102));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel53.setText("N/A");
        jPanel6.add(jLabel53);
        jLabel53.setBounds(330, 60, 150, 29);

        jLabel56.setBackground(new java.awt.Color(255, 255, 255));
        jLabel56.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(102, 102, 102));
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel56.setText("        ");
        jLabel56.setOpaque(true);
        jPanel6.add(jLabel56);
        jLabel56.setBounds(330, 90, 140, 19);

        jLabel59.setFont(new java.awt.Font("Cambria Math", 1, 24)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(0, 0, 102));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel59.setText("N/A");
        jPanel6.add(jLabel59);
        jLabel59.setBounds(570, 60, 150, 29);

        jLabel60.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(102, 102, 102));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel60.setText("        ");
        jPanel6.add(jLabel60);
        jLabel60.setBounds(570, 90, 150, 19);

        jLabel49.setIcon(new javax.swing.ImageIcon("C:\\Users\\sutha.DESKTOP-QF7IRVN\\Desktop\\oop_swiming competition\\33.png")); // NOI18N
        jLabel49.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel6.add(jLabel49);
        jLabel49.setBounds(490, 40, 230, 90);

        jLabel55.setIcon(new javax.swing.ImageIcon("C:\\Users\\sutha.DESKTOP-QF7IRVN\\Desktop\\oop_swiming competition\\22.png")); // NOI18N
        jLabel55.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel6.add(jLabel55);
        jLabel55.setBounds(250, 40, 230, 90);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    boolean first_time=true;
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        if(first_time==false)
        {
            ReStart_competition();
        }
        else { first_time=false; }
        
        if(Swimming_in_progress==false)
        {
        if(judges.get(1).check_swimmer_body_status(swimmers)==true) // Support Judge
            {
                jButton1.setEnabled(false);
                Swimming_in_progress=true;
                judges.get(0).Start_whistle(swimmers, this);            // Main Judge
                Swimmer_Movements();
            }
            else
            {msg_box.errorBox("We can not start competition, there is a disqualified swimmer.", "Disqualified Swimmer", this);}
        }
        else
        {msg_box.errorBox("Competition is Running.", "Commpetition", this);}

    }//GEN-LAST:event_jButton1MouseClicked

    private void ReStart_competition()
    {
        count_times=0;
        Strat_labels(true);
        Start_changes();    
        Change_envireoments();
        for(Swimmers swimmer:swimmers)
        {
            swimmer.reset();
        }
    }
    
    int count_times=0;
    public void enble_start_button()
    {
        count_times++;
        if(count_times>=swimmers.size()){
            Swimming_in_progress=false;
             jButton1.setEnabled(true);
             displayTimer.stop();
             score_board.set_ranks_to_line(judges.get(3).Order_swimmers(swimmers));
             Save_Details();
        }
    }
    
    public void Save_Details()
    {
        save_details=new Save_Competiton_Details();
        save_details.Save_Competition_Detils(swimmers, judges.get(3).Order_swimmers(swimmers), judges,competition_settings);
    }
    
    
    Timer displayTimer ;
    private void Swimmer_Movements()
    {
                final int[][] coordinates=new int[5][2];
                coordinates[0][0]=jLabel6.getX(); coordinates[0][1]=jLabel6.getY(); 
                coordinates[1][0]=jLabel10.getX(); coordinates[1][1]=jLabel10.getY(); 
                coordinates[2][0]=jLabel9.getX(); coordinates[2][1]=jLabel9.getY(); 
                coordinates[3][0]=jLabel11.getX(); coordinates[3][1]=jLabel11.getY(); 
                coordinates[4][0]=jLabel12.getX(); coordinates[4][1]=jLabel12.getY(); 
               
                ActionListener listener;
                listener = new ActionListener(){

                    public void actionPerformed(ActionEvent event){
                        if(swimmers.size()==1){
                            jLabel6.setLocation(swimmers.get(0).get_Swam_length()+coordinates[0][0],coordinates[0][1]);
                        } 
                        else if(swimmers.size()==2){
                            jLabel6.setLocation(swimmers.get(0).get_Swam_length()+coordinates[0][0],coordinates[0][1]);
                            jLabel10.setLocation(swimmers.get(1).get_Swam_length()+coordinates[1][0],coordinates[1][1]);
                        } 
                        else if(swimmers.size()==3){
                            jLabel6.setLocation(swimmers.get(0).get_Swam_length()+coordinates[0][0],coordinates[0][1]);
                            jLabel10.setLocation(swimmers.get(1).get_Swam_length()+coordinates[1][0],coordinates[1][1]);
                            jLabel9.setLocation(swimmers.get(2).get_Swam_length()+coordinates[2][0],coordinates[2][1]);
                        } 
                        else if(swimmers.size()==4){
                            jLabel6.setLocation(swimmers.get(0).get_Swam_length()+coordinates[0][0],coordinates[0][1]);
                            jLabel10.setLocation(swimmers.get(1).get_Swam_length()+coordinates[1][0],coordinates[1][1]);
                            jLabel9.setLocation(swimmers.get(2).get_Swam_length()+coordinates[2][0],coordinates[2][1]);
                            jLabel11.setLocation(swimmers.get(3).get_Swam_length()+coordinates[3][0],coordinates[3][1]);
                        } 
                        else if(swimmers.size()==5){
                            jLabel6.setLocation(swimmers.get(0).get_Swam_length()+coordinates[0][0],coordinates[0][1]);
                            jLabel10.setLocation(swimmers.get(1).get_Swam_length()+coordinates[1][0],coordinates[1][1]);
                            jLabel9.setLocation(swimmers.get(2).get_Swam_length()+coordinates[2][0],coordinates[2][1]);
                            jLabel11.setLocation(swimmers.get(3).get_Swam_length()+coordinates[3][0],coordinates[3][1]);
                            jLabel12.setLocation(swimmers.get(4).get_Swam_length()+coordinates[4][0],coordinates[4][1]);
                        } 
                        show_score_board();
                    }
                };
                displayTimer = new Timer(100, listener);
                displayTimer.start();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Swimming_Pool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Swimming_Pool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Swimming_Pool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Swimming_Pool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Swimming_Pool().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables
}

