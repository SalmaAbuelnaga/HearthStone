package game.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import game.view.Start;
import game.view.View;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;

public class StartControl implements ActionListener{
	private Start view;
	private Controller controller;
	private JButton selectedHero;
	private int done1;
	private Hero player1;
	private Hero player2;
	
	public StartControl() {
		player1 = null;
		player2 = null;
		done1 = 0;
		view = new Start();
		JButton b1 = new JButton();
		b1.setText("Mage");
		b1.addActionListener(this);
		b1.setHorizontalTextPosition(JButton.CENTER);//https://stackoverflow.com/questions/2713480/is-it-possible-to-put-text-on-top-of-a-image-in-a-button
		b1.setVerticalTextPosition(JButton.CENTER);
		view.getStart().add(b1);
		b1.setIcon(new ImageIcon("images/Mage.jpg"));//https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.metabomb.net%2Fhearthstone%2Fdeck-guides%2Fbeginner-mage-deck-list-guide-2017&psig=AOvVaw0KUO6f1PZiuqfdmOu52mT2&ust=1587506596049000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCNC144eB-OgCFQAAAAAdAAAAABAD
		JButton b2 = new JButton();
		b2.setText("Priest");
		b2.addActionListener(this);
		b2.setHorizontalTextPosition(JButton.CENTER);
		b2.setVerticalTextPosition(JButton.CENTER);
		view.getStart().add(b2);
		b2.setIcon(new ImageIcon("images/Priest.jpg"));//https://www.google.com/url?sa=i&url=https%3A%2F%2Fplayhearthstone.com%2Fen-us%2Fheroes%2Fpriest&psig=AOvVaw1Nc7aCtmTdRRcTsFyVWHXU&ust=1587504364985000&source=images&cd=vfe&ved=0CAIQjRxqGAoTCKjr0OD49-gCFQAAAAAdAAAAABCjAQ
		JButton b3 = new JButton();
		b3.setText("Hunter");
		b3.addActionListener(this);
		b3.setHorizontalTextPosition(JButton.CENTER);
		b3.setVerticalTextPosition(JButton.CENTER);
		view.getStart().add(b3);
		b3.setIcon(new ImageIcon("images/Hunter.jpg"));//https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.hearthpwn.com%2Fdecks%2F942739-meta-breaker-death-knight-face-hunter&psig=AOvVaw1gytsknDnCMJIhSpcIofvW&ust=1587506669266000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCLio-KyB-OgCFQAAAAAdAAAAABAf
		JButton b4 = new JButton();
		b4.setText("Paladin");
		b4.addActionListener(this);
		b4.setHorizontalTextPosition(JButton.CENTER);
		b4.setVerticalTextPosition(JButton.CENTER);
		view.getStart().add(b4);
		b4.setIcon(new ImageIcon("images/Paladin.jpg"));//https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3DqV_N4HNxu6Y&psig=AOvVaw2aZpho0TnFbbImjw3weH6z&ust=1587506757493000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCOj13tSB-OgCFQAAAAAdAAAAABAD
		JButton b5 = new JButton();
		b5.setText("Warlock");
		b5.addActionListener(this);
		view.getStart().add(b5);
		b5.setHorizontalTextPosition(JButton.CENTER);
		b5.setVerticalTextPosition(JButton.CENTER);
		b5.setIcon(new ImageIcon("images/Warlock.jpg"));//https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.hearthpwn.com%2Fdecks%2F691185-kabal-control-warlock&psig=AOvVaw01GWIQ8N4VlTD7PZAJIKzw&ust=1587506561025000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCMCFjPeA-OgCFQAAAAAdAAAAABAD
		JButton done = new JButton();
		done.setText("Done");
		done.addActionListener(this);
		view.getStart().add(done);
		view.revalidate();
		view.repaint();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) (e.getSource());
		if(b.getActionCommand().equals("Done") && done1 ==0 && selectedHero!= null) {
			player1 = whichHero(selectedHero);
			done1++;
			selectedHero = null;
			view.getText().setText("Player 2, please choose a hero");
			view.revalidate();
			view.repaint();
	}
		else if (b.getActionCommand().equals("Done") && done1==1 && selectedHero!= null) {
			player2 = whichHero(selectedHero);
			view.dispose();
			controller = new Controller(player1, player2);
			
		}
		else if (!b.getActionCommand().equals("Done")) {
				if (selectedHero == null) {
					selectedHero = b;
			}
				else {
					if (b == selectedHero) {
						selectedHero = null;
		}
					else
						selectedHero = b;
				}
		
		}
		
	}
	public Hero whichHero(JButton b) {
		Hero h = null;
		if(b.getText().equals("Mage"))
			try {
				h = new Mage();
			} catch (IOException | CloneNotSupportedException e3) {
				e3.printStackTrace();
			}
		if(b.getText().equals("Paladin"))
			try {
				h = new Paladin();
			} catch (IOException | CloneNotSupportedException e2) {
				e2.printStackTrace();
			}
		if(b.getText().equals("Priest"))
			try {
				h = new Priest();
			} catch (IOException | CloneNotSupportedException e1) {
				e1.printStackTrace();
			}
		if(b.getText().equals("Warlock"))
			try {
				h = new Warlock();
			} catch (IOException | CloneNotSupportedException e1) {
				e1.printStackTrace();
			}
		else if(b.getText().equals("Hunter"))
			try {
				h = new Hunter();
			} catch (IOException | CloneNotSupportedException e) {
				e.printStackTrace();
			}
		return h;
	}

	public Hero getPlayer1() {
		return player1;
	}
	public Hero getPlayer2() {
		return player2;
	}
	public static void main(String[]args) {
		new StartControl();
	}

}
