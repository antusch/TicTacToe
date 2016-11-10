import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class TicTacToeGUI extends JFrame {
	
	private Container pane;
	private String currentPlayer;
	private JButton [] [] board;
	private char result;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem quit;
	private JMenuItem newGame;
	private int size;
	private int toWin;

	
	public TicTacToeGUI() {
		super();
		pane = getContentPane();
		size = 20;
		toWin = 5;
		pane.setLayout(new GridLayout(size,size));
		setTitle("Pierwszy program Antka!");
		setSize(650,650);
		// setResizable(false);;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		currentPlayer = "x";
		board = new JButton[size] [size];
		result = 'N';
		initializeBoard();
		initializeMenuBar();
		
	
		
		
		
	}
	private void initializeMenuBar(){
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		newGame = new JMenuItem("New Game");
		newGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resetBoard();
				
				
			}
		});
		quit = new JMenuItem("Quit");
		quit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				System.exit(0);
				
			}
		});
		menu.add(newGame);
		menu.add(quit);
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
	}
	private void resetBoard(){
		currentPlayer = "x";
		result = 'N';
		if(currentPlayer == "x")
			setTitle("Ruch CZARNEGO gnoma!");
		else
			setTitle("Ruch BIA£EGO gnoma!");
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				board[i][j].setBackground(Color.GRAY);
				board[i][j].setText("");
			}
		}
		
	}
	
//	private void checkWinner()
	
	private void initializeBoard(){
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				JButton btn = new JButton();
				btn.setFont(new Font(Font.SANS_SERIF,0,0));
				board[i][j] = btn;
				board[i][j].setBackground(Color.GRAY);
				btn.addActionListener(new ActionListener() {
					
				
					public void actionPerformed(ActionEvent e) {
						if(((JButton)e.getSource()).getText().equals("") &&
						// equals = testing if valid move
						result == 'N'){
							if(currentPlayer=="o"){
								btn.setBackground(Color.BLACK);
							}
							else
								btn.setBackground(Color.WHITE);
							btn.setText(currentPlayer);
							result = getResult();
							checkEnd();
							if(currentPlayer == "x")
								setTitle("Ruch CZARNEGO gnoma!");
							else
								setTitle("Ruch BIA£EGO gnoma!");
							togglePlayer();
						}
						
						
					}
				});
				pane.add(btn);
				
			}
		}
	}
	
	private void checkEnd() {
		if(result != 'N'){
			if(result == 'D') {
				JOptionPane.showMessageDialog(null, "DRAW");
				
			}
			else {
				if(currentPlayer=="o"){
					JOptionPane.showMessageDialog(null, "Wygra³ CZARNY gnom!");
				}
				else
					JOptionPane.showMessageDialog(null, "Wygra³ BIA£Y gnom!");
			}
			resetBoard();
			currentPlayer = "o";
		}
		
			
	}
	
	private void togglePlayer(){
//		return currentPlayer.equals("x")? "o" : "x";
		if (currentPlayer.equals("x"))
			currentPlayer = "o";
		else
			currentPlayer = "x";
			
	}
	
	/**
	 * W - win
	 * N - nothing
	 * D - draw
	 * @return
	 */
	
	private char getResult(){ 
		// POZIOMO
		int counter;
		for(int i = 0; i < size; i++) {
			
			counter = 0;
			for(int j = 0; j < size; j++) {
				if(board[i][j].getText().equals(currentPlayer)){
					counter = counter + 1;
				}else if(counter >= toWin)
					return 'W';
				else
					counter = 0;
			}
		}
		
		// PIONOWO
		for(int i = 0; i < size; i++) {
			
			counter = 0;
			
			for(int j = 0; j < size; j++) {
				if(board[j][i].getText().equals(currentPlayer)){
					counter = counter + 1;
				}else if(counter >= toWin)
					return 'W';
				else
					counter = 0;
			}
			
		}
		
		// UKOŒNIE
		int counter1 = 0, counter2 = 0, counter3 = 0, counter4 = 0;

		for(int j = 0; j < size; j++) {
			counter1 = 0;
			counter2 = 0;
			counter3 = 0;
			counter4 = 0;
			
			// W JEDN¥ STRONÊ
			for(int i = 0; i < size - j; i++) {
				if(board[i+j][i].getText().equals(currentPlayer)){
					counter1 = counter1 + 1;
				} else if(counter1 >= toWin)
					return 'W';
				else
					counter1 = 0;
								
				if(board[i][i+j].getText().equals(currentPlayer)){
					counter2 = counter2 + 1;
				} else if(counter2 >= toWin)
					return 'W';
				else
					counter2 = 0;
				
				// W DRUG¥ STRONÊ
				
				if(board[i+j][size - 1 - i].getText().equals(currentPlayer)){
					counter3 = counter3 + 1;
				}  else if(counter3 >= toWin)
					return 'W';
				else
					counter3 = 0;
				
				if(board[i][size - 1 - i - j].getText().equals(currentPlayer)){
					counter4 = counter4 + 1;
				}  else if(counter4 >= toWin)
					return 'W';
				else
					counter4 = 0;
			}
		}
		
		if(result == 'N'){
			int p = 0;
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
					if(!board[i][j].getText().equals("")){
						
						p = p + 1;
					}
				}
			}
			if(p >= size*size) {
//				JOptionPane.showMessageDialog(null, "DRAW");
//				resetBoard();
				return 'D';
			}
		}
		return 'N';
	}
	
	

}
