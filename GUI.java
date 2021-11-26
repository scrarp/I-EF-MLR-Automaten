import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/* NOTES
 - Monatsticket
 - Verschiedene Langzeittickets

*/
/* ************************ *
 * @CREDITS                 *
 * @author Jaro Kaulmann    *
 * @version 2021.11.26      *
 * ************************ */ 
public class GUI implements java.awt.event.ActionListener {

    //rezizable
    public boolean canrezize = false;
    //-------

    private int preis = 5;
    private JFrame frame;
    private JLabel desc;
    private JPanel panel;
    private ButtonGroup auswahlgruppe = new ButtonGroup();
    


    public GUI() {
        //creates Window        
        frame = new JFrame();
        
        JButton button = new JButton("Jetzt Kaufen");
        button.addActionListener(new Kauf());

        JButton ClassOne = new JButton("Ticket Klasse 1");
        ClassOne.addActionListener(new TriggerClassOne());

        JButton ClassTwo = new JButton("Ticket Klasse 2");
        ClassTwo.addActionListener(new TriggerClassTwo());

        desc = new JLabel("Bitte wählen sie \n" + "eine Klasse und Art aus.");


        //Beschreibung
        JPanel rightTextPanel = new JPanel();
        rightTextPanel.add(desc);
        rightTextPanel.setPreferredSize(new Dimension(220, 400));


        //Auswahl
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(220, 400));
        JLabel auswahl = new JLabel("Bitte wählen sie eine aus:");
        leftPanel.add(auswahl);
        
            //Radio Buttons
            JPanel alignRadio = new JPanel();
            JRadioButton one = new JRadioButton("Einzelticket");
            JRadioButton two = new JRadioButton("4er Ticket");
            JRadioButton three = new JRadioButton("Gruppen Ticket (6 Personen)");
            //auswahlgruppe = new ButtonGroup();
            auswahlgruppe.add(one);
            auswahlgruppe.add(two);
            auswahlgruppe.add(three);

            one.setSelected(true);
            one.setActionCommand("einzel");
            two.setActionCommand("4");
            three.setActionCommand("gruppe");

            alignRadio.setLayout(new GridLayout(5, 1));
            alignRadio.add(one);
            alignRadio.add(two);
            alignRadio.add(three);

        leftPanel.add(alignRadio, BorderLayout.WEST);

        //Top Bar
        JPanel topPanel = new JPanel();
        JPanel alignTop = new JPanel();
        alignTop.add(ClassOne);
        alignTop.add(ClassTwo);
        topPanel.setBackground(Color.LIGHT_GRAY);
        alignTop.setBackground(Color.LIGHT_GRAY);
        topPanel.add(alignTop, BorderLayout.CENTER);
        topPanel.setPreferredSize(new Dimension(800, 50));



        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);


        frame.setPreferredSize(new Dimension(850, 400));;
        frame.add(panel, BorderLayout.CENTER);
        frame.add(topPanel, BorderLayout.PAGE_START);
        frame.add(rightTextPanel, BorderLayout.EAST);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Ticketautomat");
        frame.setResizable(canrezize);
        //frame.setSize(1000, 1000);
        frame.pack();
        frame.setVisible(true);
        
    }
    
    public static void main(String[] args) {
        //main methode for running
        new GUI();

    }

    public void RemovePanel(JPanel panel) {

        panel.setVisible(false);

    }

    public void ShowPanel(JPanel panel) {

        panel.setVisible(true);

    }
//Jaro
    @Override
    public void actionPerformed(ActionEvent e) {
        //action when button pressed
        //count++;

        if(auswahlgruppe.getSelection().getActionCommand() == "einzel"){
            RemovePanel(panel);
        }

        //label.setText("Number of clicks:" + count);
    }

    
    //Button Trigger

    //Kauf
    class Kauf implements java.awt.event.ActionListener{
        public void actionPerformed(ActionEvent e){
            if(auswahlgruppe.getSelection().getActionCommand() == "einzel"){
                RemovePanel(panel); 
            }
        }
    }

    //Klasse 1
    class TriggerClassOne implements ActionListener {
        public void actionPerformed(ActionEvent e){
            
        }

    }

    //Klasse 2
    class TriggerClassTwo implements java.awt.event.ActionListener {
        public void actionPerformed(ActionEvent e){
            ShowPanel(panel);
            
        }

    }


    }
