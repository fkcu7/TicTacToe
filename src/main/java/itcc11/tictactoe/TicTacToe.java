/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package itcc11.tictactoe;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * Francis King C. Uyguangco
 * Tic Tac Toe is fun!
*/

public class TicTacToe {
    private JFrame frame;
    private JPanel panel;
    private JButton btn, reset;
    private JLabel title;
    private boolean xTurn = true;
    private String[][] board = new String[3][3];
    private int counter = 0;
    private Border normal = BorderFactory.createLineBorder(Color.decode("#666362"), 1);
    private Font TitleFont = new Font("Times New Roman",Font.BOLD,20);
    private Font font = new Font("Arial",Font.BOLD,15);
    TicTacToe()
    {
        frame = new JFrame("Activity 5");
        frame.setSize(265,310);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        panel = new JPanel();
        frame.setContentPane(panel);
	panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(null);
        panel.setBackground(Color.decode("#EAEEE9"));
        
        Buttons();
        Board();
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
                btn = (JButton) e.getSource();
                int x = (btn.getX() - 50) / 50;
                int y = (btn.getY() - 50) / 50;
                
                if (board[x][y] == null) 
                {
                    if (xTurn) 
                    {
                        btn.setText("X");
                        btn.setBackground(Color.decode("#E0FFFF"));
                        btn.setEnabled(false);
                        board[x][y] = "X";
                    } 
                    else 
                    {
                        btn.setText("O");
                        btn.setBackground(Color.decode("#AAF0D1"));
                        btn.setEnabled(false);
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
                        String[] option = {"New Game", "Exit"};
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
                btn = new JButton("");
                btn.setBounds((x * 50) + 50,(y * 50) + 50,50,50);
                btn.setBackground(null);
                btn.setBorder(normal);
                btn.setFont(font);
                panel.add(btn);
                btn.addActionListener(Action);
            }
        }
        
        reset = new JButton("Reset");
        reset.setBounds(75,225,100,25);
        panel.add(reset);
        reset.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                Reset();
            }
        });
        
        title = new JLabel("TIC TAC TOE");
        title.setBounds(62,10,150,25);
        title.setFont(TitleFont);
        panel.add(title);
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
        for (int x = 0; x < 9; x++) 
        {
            btn = (JButton) panel.getComponent(x);
            btn.setBackground(null);
            btn.setBorder(normal);
            btn.setFont(font);
            btn.setEnabled(true);
            btn.setText("");
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
