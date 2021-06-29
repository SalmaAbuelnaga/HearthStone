package game.view;

import java.awt.BorderLayout;

import javax.swing.*;

public class GameOverView extends JFrame{
	private JTextArea text;
	public GameOverView(String x) {
		setBounds(0,0,1500,850);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Game Over");
		text = new JTextArea();
		text.setEditable(false);
		add(text,BorderLayout.CENTER);
		String s =  "GAME OVER. " + x +" HAS WON!";
		text.setText(s);
		this.revalidate();
		this.repaint();
	}
}
