package game.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;


import javax.swing.*;

public class Start extends JFrame{
	private JPanel start;
	private JTextArea text;
	//private JTextArea chosen;
	public Start() {
		this.setTitle("Welcome");
		this.setBounds(0, 0, 1500, 850);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		start = new JPanel();
		start.setSize(new Dimension(600,this.getHeight()));
		start.setLayout(new GridLayout(3,3));
		this.add(start, BorderLayout.CENTER);
		text = new JTextArea();
		text.setPreferredSize(new Dimension(this.getWidth(),100));
		text.setAlignmentX(CENTER_ALIGNMENT);
		text.setEditable(false);
		text.setText("Player 1, please choose a hero");
		this.add(text, BorderLayout.NORTH);
		//chosen = new JTextArea();
		//chosen.setPreferredSize(new Dimension(this.getWidth(),100));
		//chosen.setEditable(false);
		//chosen.setText("your chosen hero is");
		//this.add(chosen, BorderLayout.SOUTH);
		
		this.revalidate();
		this.repaint();
		this.setVisible(true);	
	}
	
	
	public JPanel getStart() {
		return start;
	}


	public JTextArea getText() {
		return text;
	}
	

	public static void main(String[]args) {
		Start view = new Start();
	}
	

}
