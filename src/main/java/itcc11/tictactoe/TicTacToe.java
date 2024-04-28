/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package itcc11.tictactoe;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

/**
 * Francis King C. Uyguangco
 * Tic Tac Toe is fun!
*/

public class TicTacToe {
    private Frame frame;
    private Panel panel;
    private Button[][] btn = new Button[3][3];
    private Button clicked, reset;
    private Label title;
    private boolean xTurn = true;
    private String[][] board = new String[3][3];
    private int counter = 0;
    private Font TitleFont = new Font("Times New Roman",Font.BOLD,20);
    private Font font = new Font("Arial",Font.BOLD,15);
    TicTacToe()
    {
        frame = new Frame("Activity 5");
        frame.setSize(265,310);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        panel = new Panel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#EAEEE9"));
        
        title = new Label("TIC TAC TOE");
        title.setBounds(60,10,150,25);
        title.setFont(TitleFont);
        panel.add(title);
        
        Buttons();
        Board();
        
        frame.addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) 
            {
                System.exit(0);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
    
    private void Buttons()
    {
        ActionListener Action = new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                counter++;
                clicked = (Button) e.getSource();
                int x = (clicked.getX() - 50) / 50;
                int y = (clicked.getY() - 50) / 50;
                
                if (board[x][y] == null) 
                {
                    if (xTurn) 
                    {
                        clicked.setLabel("X");
                        clicked.setBackground(Color.decode("#E0FFFF"));
                        clicked.setEnabled(false);
                        board[x][y] = "X";
                    } 
                    else 
                    {
                        clicked.setLabel("O");
                        clicked.setBackground(Color.decode("#AAF0D1"));
                        clicked.setEnabled(false);
                        board[x][y] = "O";
                    }
                    xTurn = !xTurn;
                    if (checkResult()) 
                    {
                        String[] option = {"New Game", "Exit"};
                        int selection = JOptionPane.showOptionDialog(frame, "          Player " + (xTurn ? "O" : "X") + " wins!", "Result", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
                        if(selection == 0)
                        {
                            Reset();
                        }
                        else
                        {
                            System.exit(0);
                        }
                    }
                    else if (counter == 9)
                    {
                        String[] option = {"Play Again", "Exit"};
                        int selection = JOptionPane.showOptionDialog(frame, "   It is a Draw! Play Again?", "Result", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
                        if(selection == 0)
                        {
                            Reset();
                        }
                        else
                        {
                            System.exit(0);
                        }
                    }
                }
            }
        };
        
        for(int x = 0; x < 3; x++)
        {
            for(int y = 0; y < 3; y++)
            {
                btn[x][y] = new Button("");
                btn[x][y].setBounds((x * 50) + 50,(y * 50) + 50,50,50);
                btn[x][y].setBackground(null);
                btn[x][y].setFont(font);
                panel.add(btn[x][y]);
                btn[x][y].addActionListener(Action);
            }
        }
        
        reset = new Button("Reset");
        reset.setBounds(75,225,100,25);
        panel.add(reset);
        reset.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                Reset();
            }
        });
    }
    
    private void Board()
    {
        for (int x = 0; x < 3; x++) 
        {
            for (int y = 0; y < 3; y++) 
            {
                board[x][y] = null;
            }
        }
    }
    
    private void Reset() 
    {
        Board();
        counter = 0;
        xTurn = true;
        for (int x = 0; x < 3; x++) 
        {
            for (int y = 0; y < 3; y++) 
            {
                btn[x][y].setLabel(board[x][y] == null ? "" : board[x][y]);
                btn[x][y].setBackground(null);
                btn[x][y].setEnabled(true);
            }
        }
    }
    
    private boolean checkResult() 
    {
        for (int x = 0; x < 3; x++) 
        {
            if (board[x][0] != null && board[x][0].equals(board[x][1]) && board[x][0].equals(board[x][2])) 
            {
                return true;
            }
        }
        for (int y = 0; y < 3; y++) 
        {
            if (board[0][y] != null && board[0][y].equals(board[1][y]) && board[0][y].equals(board[2][y])) 
            {
                return true;
            }
        }
        if (board[0][0] != null && board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2])) 
        {
            return true;
        }
        else if (board[0][2] != null && board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0])) 
        {
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) 
    {
        EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
		try 
                {
                    new TicTacToe();
		} 
                catch (Exception e) 
                {
                    e.printStackTrace();
		}
            }
        });
    }
}
