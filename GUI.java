import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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

    //--------------rezisable---------------//
        public boolean canrezize = false;
    //--------------------------------------//


    private String currentPage = "Klasse1";
    
    //Swing components
    private int preis = 5;
    private int money = 50;
    private JFrame frame;
    private JLabel desc;
    private JPanel panel;
    private JButton button;
    private ButtonGroup auswahlgruppe = new ButtonGroup();
    private ButtonGroup zeitgruppe = new ButtonGroup();
    private JPanel alignRadio;
    private JPanel alignTimes;

    //colors
    private Color cconfirm = new Color(157, 255, 156);
    private Color cnotready = new Color(255, 116, 82);
    private Color cerror = new Color(89, 40, 40);
    


    public GUI() {
        //creates Window        
        frame = new JFrame();
        
        button = new JButton("Jetzt Kaufen");
        button.addActionListener(new Kauf());
        button.setBackground(cnotready);

        JButton ClassOne = new JButton("Ticket Klasse 1");
        ClassOne.addActionListener(new TriggerClassOne());

        JButton ClassTwo = new JButton("Ticket Klasse 2");
        ClassTwo.addActionListener(new TriggerClassTwo());

        JButton ClassThree = new JButton("Ticket Klasse 3");
        ClassThree.addActionListener(new TriggerClassThree());

        JButton ClassFour = new JButton("Langzeittickets");
        ClassFour.addActionListener(new TriggerClassTimed());

        //desription text
        desc = new JLabel("<html>Bitte wählen sie eine Klasse<br/>und Art aus.<br/>Hello world<html/>");
        //desc.setPreferredSize(new Dimension(220, 400));


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
            alignRadio = new JPanel();
            JRadioButton one = new JRadioButton("Einzelticket");
            JRadioButton two = new JRadioButton("4er Ticket");
            JRadioButton three = new JRadioButton("Gruppen Ticket (6 Personen)");
            JRadioButton four = new JRadioButton("<html>Familienticket<br/>(2 Erwachsene, 1 Kind)<html/>");

            JRadioButton day = new JRadioButton("Tagesticket", true);
            JRadioButton week = new JRadioButton("Wochenticket");
            JRadioButton month = new JRadioButton("Monatsticket");
            JRadioButton year = new JRadioButton("Jahresticket");

            
            //auswahlgruppe = new ButtonGroup();
            auswahlgruppe.add(one);
            auswahlgruppe.add(two);
            auswahlgruppe.add(three);
            auswahlgruppe.add(four);

            one.setSelected(true);
            one.setActionCommand("einzel");
            two.setActionCommand("vier");
            three.setActionCommand("gruppe");
            four.setActionCommand("family");

            one.addChangeListener(new NormalChanged());
            two.addChangeListener(new NormalChanged());
            three.addChangeListener(new NormalChanged());
            four.addChangeListener(new NormalChanged());
            

            zeitgruppe.add(day);
            zeitgruppe.add(week);
            zeitgruppe.add(month);
            zeitgruppe.add(year);

            alignTimes = new JPanel();
            alignTimes.setLayout(new GridLayout(5, 1));
            alignTimes.add(day);
            alignTimes.add(week);
            alignTimes.add(month);
            alignTimes.add(year);



            day.addChangeListener(new TimedFocus());
            week.addChangeListener(new TimedFocus());
            month.addChangeListener(new TimedFocus());
            year.addChangeListener(new TimedFocus());

            day.setActionCommand("day");
            week.setActionCommand("week");
            month.setActionCommand("month");
            year.setActionCommand("year");

            alignRadio.setLayout(new GridLayout(5, 1));
            alignRadio.add(one);
            alignRadio.add(two);
            alignRadio.add(three);
            alignRadio.add(four);

        leftPanel.add(alignRadio, BorderLayout.WEST);
        leftPanel.add(alignTimes, BorderLayout.WEST);
        alignTimes.setVisible(false);

        //Top Bar
        JPanel topPanel = new JPanel();
        JPanel alignTop = new JPanel();
        alignTop.add(ClassOne);
        alignTop.add(ClassTwo);
        alignTop.add(ClassThree);
        alignTop.add(ClassFour);
        Color lightBlue = new Color(54, 168, 255);
        topPanel.setBackground(lightBlue);
        alignTop.setBackground(lightBlue);
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

    public boolean confirm(String type, String preis){

        int preisint = Integer.valueOf(preis);
        if(money > preisint) {
            
            
            int confirm;
            confirm = JOptionPane.showOptionDialog(frame, "Sie sind dabei ein " + type + "ticket für " + preis + "€ zu kaufen" + "\nFortfahren?","Einkauf",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE, null, 
                new String[]{"Ja", "Nein"}, "B");

                if(confirm == JOptionPane.YES_OPTION) {
                    money = money - preisint;
                    System.out.println("Benutzer kauft ein "+ type + "ticket für " + preis + "€.");
                    System.out.println("Geld verbleibt: " + money + "€");
                    return true;
                }
                else {
                    System.out.println("Benutzer bricht den Kauf eines "+ type + "tickets für " + preis + "€ ab.");
                    return false;
                }
        }
        else{
            JOptionPane.showMessageDialog(frame, "Fehler bei der Transaktion", "Fehler", JOptionPane.INFORMATION_MESSAGE);
        }
        return false;
        
    }

    
    //Button Trigger

    //Kauf
    class Kauf implements java.awt.event.ActionListener{
        public void actionPerformed(ActionEvent e){
            String pre;
            pre = "0";
            String typ;
            typ = "error";
                
                switch(currentPage){
                    case "Klasse1":
                        switch(auswahlgruppe.getSelection().getActionCommand()){
                            case "einzel":
                                pre = "4";
                                typ = "Einzel";
                                break;
                            case "vier":
                            typ = "Vierer";
                            pre = "15";
                                                            break;
                            case "gruppe":
                            typ = "Gruppen";
                            pre = "20";
                                break;
                            case "family":
                            typ = "Familien";
                            pre = "10";
                                break;
                        }
                        break;
                    case "Klasse2":
                        switch(auswahlgruppe.getSelection().getActionCommand()){
                            case "einzel":
                            pre = "6";
                            typ = "Einzel";
                                break;
                            case "vier":
                            typ = "Vierer";
                            pre = "25";
                                break;
                            case "gruppe":
                            typ = "Gruppen";
                            pre = "30";
                                break;
                            case "family":
                            typ = "Familien";
                            pre = "20";
                                break;
                        
                        }
                        break;
                    case "Klasse3":
                        switch(auswahlgruppe.getSelection().getActionCommand()){ 
                            case "einzel":
                            pre = "10";
                            typ = "Einzel";
                                break;
                            case "vier":
                            pre = "40";
                            typ = "Vierer";
                                break;
                            case "gruppe":
                            typ = "Gruppen";
                            pre = "50";
                                break;
                            case "family":
                            typ = "Familien";
                            pre = "30";
                                                            break;
                            
                            
                            }
                            break;
                    case "KlasseTimed":
                            switch(zeitgruppe.getSelection().getActionCommand()){
                                case "day":
                                    typ = "Tages";
                                    pre = "15";
                                case "week":
                                    typ = "Wochen";
                                    pre = "25";
                                break;
                                case "month":
                                    typ = "Monats";
                                    pre = "40";
                                break;
                                case "year":
                                    typ = "Jahres";
                                    pre = "60";

                                break;
                            }
                            break;
                            
                        
            }
        
        if(confirm(typ, pre) == true) {
            System.out.println("Gekauft");
            JOptionPane.showMessageDialog(frame, "Vielen Dank für ihren Einkauf \n Ihr Ticket wird gedruckt.");
        }
        else {
            System.out.println("Nicht gekauft");
            JOptionPane.showMessageDialog(frame, "Ihr einkauf wurde abgebrochen");
        }
    }
}

    //Klasse 1
    class TriggerClassOne implements ActionListener {
        public void actionPerformed(ActionEvent e){
            currentPage = "Klasse1";
            button.setBackground(cconfirm);
            ShowPanel(panel);
            alignRadio.setVisible(true);
            alignTimes.setVisible(false);
            desc.setText("<html><h1>Klasse 1 Ticket</h1><br/>4 Stationen Fahren<br/>Preis 4€<br/>Gültig ab Fahrtantritt<br/> <br/>Mit dem Klasse 1 Ticket<br/>ganz einfach und günsitg<br/>bis zu 4 Stationen Fahren.<br/>Als Einzelticket, Gruppenticket,<br/>4er Ticket und Familienticket<br/>erhältich.<html/>");
        }

    }

    //Klasse 2
    class TriggerClassTwo implements java.awt.event.ActionListener {
        public void actionPerformed(ActionEvent e){
            currentPage = "Klasse2";
            button.setBackground(cconfirm);
            ShowPanel(panel);
            alignRadio.setVisible(true);
            alignTimes.setVisible(false);
            desc.setText("<html><h1>Klasse 2 Ticket</h1><br/>Bis zu 8 Stationen Fahren<br/>Preis 6,50€<br/>Gültig ab Fahrtantritt<br/> <br/>Mit dem Klasse 2 Ticket<br/>ganz einfach und günsitg<br/>bis zu 8 Stationen Fahren.<br/>Als Einzelticket, Gruppenticket,<br/>4er Ticket und Familienticket<br/>erhältich.<html/>");
        }

    }

    //Klasse 3
    class TriggerClassThree implements java.awt.event.ActionListener {
        public void actionPerformed(ActionEvent e){
            currentPage = "Klasse3";
            button.setBackground(cconfirm);
            ShowPanel(panel);
            alignRadio.setVisible(true);
            alignTimes.setVisible(false);
            desc.setText("<html><h1>Klasse 3 Ticket</h1><br/>Bis zu 15 Stationen Fahren<br/>Preis 9,99€<br/>Gültig ab Fahrtantritt<br/> <br/>Mit dem Klasse 3 Ticket<br/>ganz einfach und günsitg<br/>bis zu 15 Stationen Fahren.<br/>Als Einzelticket, Gruppenticket,<br/>4er Ticket und Familienticket<br/>erhältich.<html/>");
        }

    }

    //Langzeitticket
    class TriggerClassTimed implements java.awt.event.ActionListener {
        public void actionPerformed(ActionEvent e){
            currentPage = "KlasseTimed";
            button.setBackground(cconfirm);
            ShowPanel(panel);
            desc.setText("<html><h1>Langzeittickets</h1><br/>Bitte auswählen<html/>");
            alignRadio.setVisible(false);
            alignTimes.setVisible(true);

            switch(zeitgruppe.getSelection().getActionCommand()){
                case "day":
                    desc.setText("<html><h1>Tagesticket</h1><br/>Einen ganzen Tag Gültig<br/>Preis 14,99€<br/>Gültig ab Kauf<br/> <br/>Mit dem Tagesticket fahren sie<br/>ganz einfach und günsitg<br/>den ganzen tag lang.<html/>");
                    break;
                case "week":
                    desc.setText("<html><h1>Wochenticket</h1><br/>Eine ganze Woche Gültig<br/>Preis 24,99€<br/>Gültig ab Kauf<br/> <br/>Mit dem Wochenticket fahren sie<br/>ganz einfach und günsitg<br/>die ganze Woche lang.<html/>");
                    break;
                case "month":
                    desc.setText("<html><h1>Monatsticket</h1><br/>Eine ganze Woche Gültig<br/>Preis 24,99€<br/>Gültig ab Kauf<br/> <br/>Mit dem Wochenticket fahren sie<br/>ganz einfach und günsitg<br/>die ganze Woche lang.<html/>");
                    break;
                case "year":
                    desc.setText("<html><h1>Jahresticket</h1><br/>Eine ganze Woche Gültig<br/>Preis 24,99€<br/>Gültig ab Kauf<br/> <br/>Mit dem Wochenticket fahren sie<br/>ganz einfach und günsitg<br/>die ganze Woche lang.<html/>");
                    break;
            }


        }

    }

    //Refresh Label
    class TimedFocus implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
            // TODO Auto-generated method stub
            switch(zeitgruppe.getSelection().getActionCommand()){
                case "day":
                    desc.setText("<html><h1>Tagesticket</h1><br/>Einen ganzen Tag Gültig<br/>Preis 14,99€<br/>Gültig ab Kauf<br/> <br/>Mit dem Tagesticket fahren sie<br/>ganz einfach und günsitg<br/>den ganzen tag lang.<html/>");
                    break;
                case "week":
                    desc.setText("<html><h1>Wochenticket</h1><br/>Eine ganze Woche Gültig<br/>Preis 24,99€<br/>Gültig ab Kauf<br/> <br/>Mit dem Wochenticket fahren sie<br/>ganz einfach und günsitg<br/>die ganze Woche lang.<html/>");
                    break;
                case "month":
                    desc.setText("<html><h1>Monatsticket</h1><br/>Einen ganzen Monat Gültig<br/>Preis 39,99€<br/>Gültig ab Kauf<br/> <br/>Mit dem Monatsticket fahren sie<br/>ganz einfach und günsitg<br/>den ganzen Monat lang.<html/>");
                    break;
                case "year":
                    desc.setText("<html><h1>Jahresticket</h1><br/>Ein ganzes Jahr Gültig<br/>Preis 59,99€<br/>Gültig ab Kauf<br/> <br/>Mit dem Jahresticket fahren sie<br/>ganz einfach und günsitg<br/>ein ganzes Jahr lang.<html/>");
                    break;
            }
        }

    }

     class NormalChanged implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e){
            switch(currentPage){
                case "Klasse1":
                    switch(auswahlgruppe.getSelection().getActionCommand()){
                        case "einzel":
                            desc.setText("<html><h1>Klasse 1 Ticket</h1><br/>4 Stationen Fahren<br/>Preis 4€<br/>Gültig ab Fahrtantritt<br/> <br/>Mit dem Klasse 1 Ticket<br/>ganz einfach und günsitg<br/>bis zu 4 Stationen Fahren.<br/>Als Einzelticket, Gruppenticket,<br/>4er Ticket und Familienticket<br/>erhältich.<html/>");
                            break;
                        case "vier":
                            desc.setText("<html><h1>Klasse 1 Ticket</h1><br/>4 Stationen Fahren<br/>Preis 15€<br/>Gültig ab Fahrtantritt<br/> <br/>Mit dem Klasse 1 Ticket<br/>ganz einfach und günsitg<br/>bis zu 4 Stationen Fahren.<br/>Als Einzelticket, Gruppenticket,<br/>4er Ticket und Familienticket<br/>erhältich.<html/>");
                            break;
                        case "gruppe":
                            desc.setText("<html><h1>Klasse 1 Ticket</h1><br/>4 Stationen Fahren<br/>Preis 20€<br/>Gültig ab Fahrtantritt<br/> <br/>Mit dem Klasse 1 Ticket<br/>ganz einfach und günsitg<br/>bis zu 4 Stationen Fahren.<br/>Als Einzelticket, Gruppenticket,<br/>4er Ticket und Familienticket<br/>erhältich.<html/>");
                            break;
                        case "family":
                            desc.setText("<html><h1>Klasse 1 Ticket</h1><br/>4 Stationen Fahren<br/>Preis 10€<br/>Gültig ab Fahrtantritt<br/> <br/>Mit dem Klasse 1 Ticket<br/>ganz einfach und günsitg<br/>bis zu 4 Stationen Fahren.<br/>Als Einzelticket, Gruppenticket,<br/>4er Ticket und Familienticket<br/>erhältich.<html/>");
                            break;
                    }
                    break;
                case "Klasse2":
                    switch(auswahlgruppe.getSelection().getActionCommand()){
                        case "einzel":
                            desc.setText("<html><h1>Klasse 2 Ticket</h1><br/>Bis zu 8 Stationen Fahren<br/>Preis 6,50€<br/>Gültig ab Fahrtantritt<br/> <br/>Mit dem Klasse 2 Ticket<br/>ganz einfach und günsitg<br/>bis zu 8 Stationen Fahren.<br/>Als Einzelticket, Gruppenticket,<br/>4er Ticket und Familienticket<br/>erhältich.<html/>"); 
                            break;
                        case "vier":
                            desc.setText("<html><h1>Klasse 2 Ticket</h1><br/>Bis zu 8 Stationen Fahren<br/>Preis 24,99€<br/>Gültig ab Fahrtantritt<br/> <br/>Mit dem Klasse 2 Ticket<br/>ganz einfach und günsitg<br/>bis zu 8 Stationen Fahren.<br/>Als Einzelticket, Gruppenticket,<br/>4er Ticket und Familienticket<br/>erhältich.<html/>");
                            break;
                        case "gruppe":
                            desc.setText("<html><h1>Klasse 2 Ticket</h1><br/>Bis zu 8 Stationen Fahren<br/>Preis 30€<br/>Gültig ab Fahrtantritt<br/> <br/>Mit dem Klasse 2 Ticket<br/>ganz einfach und günsitg<br/>bis zu 8 Stationen Fahren.<br/>Als Einzelticket, Gruppenticket,<br/>4er Ticket und Familienticket<br/>erhältich.<html/>");
                            break;
                        case "family":
                            desc.setText("<html><h1>Klasse 2 Ticket</h1><br/>Bis zu 8 Stationen Fahren<br/>Preis 20€<br/>Gültig ab Fahrtantritt<br/> <br/>Mit dem Klasse 2 Ticket<br/>ganz einfach und günsitg<br/>bis zu 8 Stationen Fahren.<br/>Als Einzelticket, Gruppenticket,<br/>4er Ticket und Familienticket<br/>erhältich.<html/>");
                            break;
                    
                    }
                    break;
                case "Klasse3":
                    switch(auswahlgruppe.getSelection().getActionCommand()){ 
                        case "einzel":
                            desc.setText("<html><h1>Klasse 3 Ticket</h1><br/>Bis zu 15 Stationen Fahren<br/>Preis 9,99€<br/>Gültig ab Fahrtantritt<br/> <br/>Mit dem Klasse 3 Ticket<br/>ganz einfach und günsitg<br/>bis zu 15 Stationen Fahren.<br/>Als Einzelticket, Gruppenticket,<br/>4er Ticket und Familienticket<br/>erhältich.<html/>");
                            break;
                        case "vier":
                            desc.setText("<html><h1>Klasse 3 Ticket</h1><br/>Bis zu 15 Stationen Fahren<br/>Preis 39,99€<br/>Gültig ab Fahrtantritt<br/> <br/>Mit dem Klasse 3 Ticket<br/>ganz einfach und günsitg<br/>bis zu 15 Stationen Fahren.<br/>Als Einzelticket, Gruppenticket,<br/>4er Ticket und Familienticket<br/>erhältich.<html/>");
                            break;
                        case "gruppe":
                            desc.setText("<html><h1>Klasse 3 Ticket</h1><br/>Bis zu 15 Stationen Fahren<br/>Preis 50€<br/>Gültig ab Fahrtantritt<br/> <br/>Mit dem Klasse 3 Ticket<br/>ganz einfach und günsitg<br/>bis zu 15 Stationen Fahren.<br/>Als Einzelticket, Gruppenticket,<br/>4er Ticket und Familienticket<br/>erhältich.<html/>");
                            break;
                        case "family":
                            desc.setText("<html><h1>Klasse 3 Ticket</h1><br/>Bis zu 15 Stationen Fahren<br/>Preis 30€<br/>Gültig ab Fahrtantritt<br/> <br/>Mit dem Klasse 3 Ticket<br/>ganz einfach und günsitg<br/>bis zu 15 Stationen Fahren.<br/>Als Einzelticket, Gruppenticket,<br/>4er Ticket und Familienticket<br/>erhältich.<html/>");
                            break;
                        
                        
                        }
                        break;
                        
                    }
        }
    }


    //Funktionen zur vereinfachung

    public void RemovePanel(JPanel panel) {

        panel.setVisible(false);

    }

    public void ShowPanel(JPanel panel) {

        panel.setVisible(true);

    }

    //Start Funktion
    public void Automat(){

    }


}


