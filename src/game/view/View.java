package game.view;

import java.awt.*;
import javax.swing.*;

public class View extends JFrame{
	private JPanel player1Hand;
	private JPanel player1Field;
	private JPanel player2Field;
	private JPanel player2Hand;
	private JPanel heroButtons;
	private JPanel game;
	private JTextArea heroesInfo;
	private JTextArea errors;
	private JPanel text;
	public View() {
		this.setBounds(0, 0, 1500, 850);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Hearthstone");
		

		
		text = new JPanel();
		text.setPreferredSize(new Dimension(200,850));
		text.setLayout(new BorderLayout());
		add(text, BorderLayout.EAST);
		
		
		heroesInfo = new JTextArea();
		heroesInfo.setPreferredSize(new Dimension(text.getWidth(),450));
		heroesInfo.setEditable(false);
		heroesInfo.setLineWrap(true);
		text.add(heroesInfo, BorderLayout.NORTH);
		heroesInfo.setText("Heroes info");
		
		errors = new JTextArea();
		errors.setPreferredSize(new Dimension(text.getWidth(),400));
		errors.setEditable(false);
		errors.setLineWrap(true);
		text.add(errors);
		errors.setText("");
		
		game = new JPanel();
		game.setSize(new Dimension(1100,850));
		game.setLayout(new GridLayout(4,1));
		this.add(game, BorderLayout.CENTER);
		
		player2Hand = new JPanel();
		player2Hand.setSize(new Dimension(1100,200));
		player2Hand.setLayout(new GridLayout(1,10));
		game.add(player2Hand);
		
		player2Field = new JPanel();
		player2Field.setSize(new Dimension(1100,200));
		player2Field.setLayout(new GridLayout(1,7));
		game.add(player2Field);
		
		player1Field = new JPanel();
		player1Field.setSize(new Dimension(1100,200));
		player1Field.setLayout(new GridLayout(1,7));
		game.add(player1Field);
		
		player1Hand = new JPanel();
		player1Hand.setSize(new Dimension(1100,250));
		player1Hand.setLayout(new GridLayout(1,10));
		game.add(player1Hand);
		
		heroButtons = new JPanel();
		heroButtons.setSize(new Dimension(200,850));
		heroButtons.setLayout(new GridLayout(6,1));
		add(heroButtons, BorderLayout.WEST);
		
		
		
		
		
		this.revalidate();
		this.repaint();
		this.setVisible(true);
		}
	
	public JPanel getGame() {
		return game;
	}


	public JTextArea getHeroesInfo() {
		return heroesInfo;
	}

	public JPanel getPlayer1Hand() {
		return player1Hand;
	}

	public JPanel getPlayer1Field() {
		return player1Field;
	}

	public JPanel getPlayer2Field() {
		return player2Field;
	}

	public JPanel getPlayer2Hand() {
		return player2Hand;
	}

	public JPanel getHeroButtons() {
		return heroButtons;
	}
	

	public JTextArea getErrors() {
		return errors;
	}

	public static void main(String[] args) {
		View view = new View();
		
	}

}
