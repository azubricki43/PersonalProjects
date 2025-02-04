import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener{

    //create random to determine which players goes first
    Random random = new Random();
    //create JFrame to display the game
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_true;
    
    //create constructor
    TicTacToe(){

        //intializaing JFrame - main container for GUI
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        //initialized title JPanel - visible area of JFrame, where components are added
        //needs JFrame to be visible
        textfield.setBackground(Color.BLACK);
        textfield.setForeground(Color.PINK);
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        //add textfield to title panel, add title panel to our JFrame
        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0, 800, 100);

        //add button panel next
        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(Color.PINK);

        //initializing each button in JButton array
        for(int i = 0; i < 9; i++){
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        //decides whos turn comes first
        firstTurn();
    }

    public void actionPerformed(ActionEvent e){

        for(int i = 0; i < 9; i++){
            if(e.getSource()==buttons[i]){
                if(player1_true){
                    if(buttons[i].getText()==""){
                        buttons[i].setForeground(Color.MAGENTA);
                        buttons[i].setText("X");
                        player1_true = false;
                        textfield.setText("O turn");
                        check();
                    }
                }
                else{
                    if(buttons[i].getText()==""){
                        buttons[i].setForeground(Color.CYAN);
                        buttons[i].setText("O");
                        player1_true = true;
                        textfield.setText("X turn");
                        check();
                    }
                }
            }
        }

    }

    public void firstTurn(){

        //delay so title can be displayed
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(random.nextInt(2)==0){
            player1_true = true;
            textfield.setText("X turn");
        }
        else{
            player1_true = false;
            textfield.setText("O Turn");
        }
    }

    //check to see if winning conditions
    public void check(){
        //check X win conditions
        
        //check horizontal
        for(int i = 0; i <= 6; i += 3){
            if((buttons[i].getText()=="X") && (buttons[i+1].getText()=="X") && (buttons[i+2].getText() == "X")){
                xWins(i, i+1, i+2);
            }
        }
        //check vertical
        for(int i = 0; i < 3; i++){
            if((buttons[i].getText()=="X") && (buttons[i+3].getText()=="X") && (buttons[i+6].getText()=="X")){
                xWins(i, i+3, i+6);
            }
        }

        //check diagonal
        if((buttons[0].getText()=="X") && (buttons[4].getText()=="X") && (buttons[8].getText()=="X")){
            xWins(0,4,8);
        }
        if((buttons[2].getText()=="X") && (buttons[4].getText()=="X") && (buttons[6].getText()=="X")){
            xWins(2,4,8);
        }

        //check O win conditions
        //check horizontal
        for(int i = 0; i <= 6; i += 3){
            if((buttons[i].getText()=="O") && (buttons[i+1].getText()=="O") && (buttons[i+2].getText() == "O")){
                oWins(i, i+1, i+2);
            }
        }
        //check vertical
        for(int i = 0; i < 3; i++){
            if((buttons[i].getText()=="O") && (buttons[i+3].getText()=="O") && (buttons[i+6].getText()=="O")){
                oWins(i, i+3, i+6);
            }
        }

        //check diagonal
        if((buttons[0].getText()=="O") && (buttons[4].getText()=="O") && (buttons[8].getText()=="O")){
            oWins(0,4,8);
        }
        if((buttons[2].getText()=="O") && (buttons[4].getText()=="O") && (buttons[6].getText()=="O")){
            oWins(2,4,6);
        }
    }

    //method for if either wins
    public void xWins(int a, int b, int c){
        //indicate x has won
        buttons[a].setBackground(Color.YELLOW);
        buttons[b].setBackground(Color.YELLOW);
        buttons[c].setBackground(Color.YELLOW);

        //disable all buttons
        for(int i = 0; i < 9; i++){
            buttons[i].setEnabled(false);
        }
        textfield.setText("X wins!");
        
    }

    public void oWins(int a, int b, int c){
        buttons[a].setBackground(Color.YELLOW);
        buttons[b].setBackground(Color.YELLOW);
        buttons[c].setBackground(Color.YELLOW);

        //disable all buttons
        for(int i = 0; i < 9; i++){
            buttons[i].setEnabled(false);
        }
        textfield.setText("O wins!");
    }


}