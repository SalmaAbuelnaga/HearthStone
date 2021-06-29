package game.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import engine.Game;
import engine.GameListener;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import game.view.GameOverView;
import game.view.Start;
import game.view.View;
import model.cards.Card;
import model.cards.minions.Minion;
import model.cards.spells.AOESpell;
import model.cards.spells.DivineSpirit;
import model.cards.spells.FieldSpell;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.KillCommand;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.Polymorph;
import model.cards.spells.Pyroblast;
import model.cards.spells.SealOfChampions;
import model.cards.spells.ShadowWordDeath;
import model.cards.spells.SiphonSoul;
import model.cards.spells.Spell;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;

public class Controller implements GameListener, ActionListener{
	private Game model;
	private View view;
	private ArrayList<JButton> curHeroHand;
	private ArrayList<JButton> curHeroField;
	private ArrayList<JButton> oppHeroField;
	private ArrayList<JButton> oppHeroHand;
	private boolean flag;
	private JButton selectedButton;
	private Hero player1;
	private Hero player2;
	public Controller(Hero player1, Hero player2) {
		this.player1= player1;
		this.player2= player2;
		try {
			model = new Game(player1, player2);
		} catch (FullHandException | CloneNotSupportedException e) {
			e.printStackTrace();
		}
		model.setListener(this);
		view = new View();
		flag = false; 
		if (!player1.equals(model.getCurrentHero())) {
			flag = false;
			view.getPlayer2Hand().setVisible(true);
			view.getPlayer1Hand().setVisible(false);
		}
		else {
			flag = true;
			view.getPlayer1Hand().setVisible(true);
			view.getPlayer2Hand().setVisible(false);
		}
		
		curHeroHand = new ArrayList<JButton>();
		curHeroField = new ArrayList<JButton>();
		oppHeroField = new ArrayList<JButton>();
		oppHeroHand = new ArrayList<JButton>();
		heroesIn();
		String z="";
		String s="";
		String x="";
		String y="";
		for(int i=0;i< model.getOpponent().getHand().size();i++) {
			z = "";
			JButton b = new JButton();
			Card c = model.getOpponent().getHand().get(i);
			if (model.getOpponent().getHand().get(i) instanceof Minion) {
				Minion a = (Minion) c;
				if(a.isTaunt()&& a.isDivine()&& !(a.isSleeping())) {
 					 z+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
   				 "<br>" + "Taunt" + "<br>" + "Divine"+"<br>"  +"Charge"+ "</html>"; 
 				}
 				else if(a.isDivine()&& a.isTaunt()) {
 					 z+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
 		    				 "<br>" + "Taunt" + "<br>"+ "Divine"+ "</html>"; 
 				}
 				else if (a.isTaunt()&& !(a.isSleeping())) {
 					 z+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
 		    				 "<br>" + "Taunt"+ "<br>"  +"Charge"+ "</html>"; 
 				}
 				else if(a.isDivine() && !(a.isSleeping())) {
 					 z+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
     				 "<br>" + "Divine"+ "<br>"  +"Charge"+ "</html>";
 					 }
 				else if (!a.isSleeping()) {
 					 z+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
 		    				 "<br>" +"Charge"+ "</html>";
 				}
 				else if(a.isDivine()) {
 					 z+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
 		    				 "<br>" + "Divine"+ "</html>";
 				}
 				else if (a.isTaunt()) {
 					 z+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
 		    				 "<br>" + "Taunt"+ "</html>"; 
 				}
 				else
 					z+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
   				  "</html>"; 
				}
  				
   			else {
   				z+= "<html>" + "Name: "+c.getName() + "<br>" + "ManaCost: "+c.getManaCost()+ "<br>" + "Rarity: "+ c.getRarity() +"</html>";
   			}
			//reference: https://www.roseindia.net/java/example/java/swing/MultilineLabelButton.shtml

			b.setText(z);
			oppHeroHand.add(b);
			if (flag)
				view.getPlayer2Hand().add(b);
			else
				view.getPlayer1Hand().add(b);
			view.revalidate();
			view.repaint();
			}
		for(int i=0;i< model.getOpponent().getField().size();i++) {
			y = "";
			JButton b = new JButton();
			Card c = model.getOpponent().getField().get(i);
			if (model.getOpponent().getField().get(i) instanceof Minion) {
				Minion a = (Minion) c;
				if(a.isTaunt()&& a.isDivine()&& a.isSleeping()) {
  					 y+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
    				 "<br>" + "Taunt" + "<br>" + "Divine"+"<br>"  +"Sleeping"+ "</html>"; 
  				}
  				else if(a.isDivine()&& a.isTaunt()) {
  					 y+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
  		    				 "<br>" + "Taunt" + "<br>"+ "Divine"+ "</html>"; 
  				}
  				else if (a.isTaunt()&& a.isSleeping()) {
  					 y+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
  		    				 "<br>" + "Taunt"+ "<br>"  +"Sleeping"+ "</html>"; 
  				}
  				else if(a.isDivine() && a.isSleeping()) {
  					 y+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
      				 "<br>" + "Divine"+ "<br>"  +"Sleeping"+ "</html>";
  					 }
  				else if (a.isSleeping()) {
  					 y+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
  		    				 "<br>" +"Sleeping"+ "</html>";
  				}
  				else if(a.isDivine()) {
  					 y+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
  		    				 "<br>" + "Divine"+ "</html>";
  				}
  				else if (a.isTaunt()) {
  					 y+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
  		    				 "<br>" + "Taunt"+ "</html>"; 
  				}
  				else
  					y+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
    				  "</html>"; 
  			}
			b.setText(y);
			b.addActionListener(this);
			oppHeroField.add(b);
			if (flag)
				view.getPlayer2Field().add(b);
			else 
				view.getPlayer1Field().add(b);
			view.revalidate();
			view.repaint();
			
		}
		for(int i=0;i< model.getCurrentHero().getField().size();i++) {
			x = "";
			JButton b = new JButton();
			Card c = model.getCurrentHero().getField().get(i);
			if (model.getCurrentHero().getField().get(i) instanceof Minion) {
				Minion a = (Minion) c;
				if(a.isTaunt()&& a.isDivine()&& a.isSleeping()) {
  					 x+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
    				 "<br>" + "Taunt" + "<br>" + "Divine"+"<br>"  +"Sleeping"+ "</html>"; 
  				}
  				else if(a.isDivine()&& a.isTaunt()) {
  					 x+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
  		    				 "<br>" + "Taunt" + "<br>"+ "Divine"+ "</html>"; 
  				}
  				else if (a.isTaunt()&& a.isSleeping()) {
  					 x+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
  		    				 "<br>" + "Taunt"+ "<br>"  +"Sleeping"+ "</html>"; 
  				}
  				else if(a.isDivine() && a.isSleeping()) {
  					 x+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
      				 "<br>" + "Divine"+ "<br>"  +"Sleeping"+ "</html>";
  					 }
  				else if (a.isSleeping()) {
  					 x+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
  		    				 "<br>" +"Sleeping"+ "</html>";
  				}
  				else if(a.isDivine()) {
  					 x+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
  		    				 "<br>" + "Divine"+ "</html>";
  				}
  				else if (a.isTaunt()) {
  					 x+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
  		    				 "<br>" + "Taunt"+ "</html>"; 
  				}
  				else
  					x+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
    				  "</html>"; 

			}
			
			b.setText(x);
			b.addActionListener(this);
			curHeroField.add(b);
			if (flag)
				view.getPlayer1Field().add(b);
			else
				view.getPlayer2Field().add(b);
			view.revalidate();
			view.repaint();
		}
		
		for(int i=0;i< model.getCurrentHero().getHand().size();i++) {
			s = "";
			JButton b = new JButton();
			Card c = model.getCurrentHero().getHand().get(i);
			if (model.getCurrentHero().getHand().get(i) instanceof Minion) {
				Minion a = (Minion) c;
				if(a.isTaunt()&& a.isDivine()&& !(a.isSleeping())) {
					 s+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
  				 "<br>" + "Taunt" + "<br>" + "Divine"+"<br>"  +"Charge"+ "</html>"; 
				}
				else if(a.isDivine()&& a.isTaunt()) {
					 s+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
		    				 "<br>" + "Taunt" + "<br>"+ "Divine"+ "</html>"; 
				}
				else if (a.isTaunt()&& !(a.isSleeping())) {
					 s+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
		    				 "<br>" + "Taunt"+ "<br>"  +"Charge"+ "</html>"; 
				}
				else if(a.isDivine() && !(a.isSleeping())) {
					 s+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
    				 "<br>" + "Divine"+ "<br>"  +"Charge"+ "</html>";
					 }
				else if (!a.isSleeping()) {
					 s+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
		    				 "<br>" +"Charge"+ "</html>";
				}
				else if(a.isDivine()) {
					 s+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
		    				 "<br>" + "Divine"+ "</html>";
				}
				else if (a.isTaunt()) {
					 s+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
		    				 "<br>" + "Taunt"+ "</html>"; 
				}
				else
					s+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
  				  "</html>"; 
 			}
  			else {
  				s+= "<html>" + "Name: "+c.getName() + "<br>" + "ManaCost: "+c.getManaCost()+ "<br>" + "Rarity: "+ c.getRarity() +"</html>";
  			}
			b.setText(s);
			b.addActionListener(this);
			curHeroHand.add(b);
			if (flag)
				view.getPlayer1Hand().add(b);
			else
				view.getPlayer2Hand().add(b);
			view.revalidate();
			view.repaint();		
		}
	
		JButton play = new JButton();
		play.setText("Player 2");
		play.addActionListener(this);
		view.getHeroButtons().add(play);
		JButton end = new JButton();
		end.setText("End Turn");
		end.addActionListener(this);
		view.getHeroButtons().add(end);
		JButton minion = new JButton();
		minion.setText("Play Minion");
		minion.addActionListener(this);
		view.getHeroButtons().add(minion);
		JButton HeroP = new JButton();
		HeroP.setText("Use Hero Power");
		HeroP.addActionListener(this);
		view.getHeroButtons().add(HeroP);
		JButton draw = new JButton();
		draw.setText("Draw Card");
		draw.addActionListener(this);
		view.getHeroButtons().add(draw);
		JButton hero = new JButton();
		hero.setText("Player 1");
		hero.addActionListener(this);
		view.getHeroButtons().add(hero);
		view.revalidate();
		view.repaint();
		view.setVisible(true);
		}
		
		
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) (e.getSource());
		view.getErrors().setText("");
	    if (b.getActionCommand().equals("End Turn")) {
	    	try {
	    		view.getErrors().setText("");
	    		if(flag) {
		    		view.getPlayer1Hand().setVisible(false);
		    		view.getPlayer2Hand().setVisible(true);
	    		}
		    	else {
		    		view.getPlayer1Hand().setVisible(true);
		    		view.getPlayer2Hand().setVisible(false);
		    		}
	    		model.endTurn();
	    		flag = model.getCurrentHero().equals(player1);
	    		heroesIn();
	    		oppHeroHand = curHeroHand;
	    		curHeroHand.removeAll(curHeroHand);
	    		ArrayList<JButton> t = curHeroField;
	    		curHeroField = oppHeroField;
	    		oppHeroField = t;
	    		updateHand();
	    		view.revalidate();
	    		view.repaint();
	    		updateFields();
	    		
	    	} catch (FullHandException | CloneNotSupportedException e1) {
	    		view.getErrors().setText(e1.getMessage());
				e1.printStackTrace();
				view.revalidate();
				view.repaint();
				b= null;
			}
		}
	    else if(b.getActionCommand().equals("Draw Card")) {
	    	try {
	    		view.getErrors().setText("");
				model.getCurrentHero().drawCard();
				updateHand();
				updateFields();
				heroesIn();
	    		view.revalidate();
	    		view.repaint();
			} catch (FullHandException | CloneNotSupportedException e1) {
				view.getErrors().setText(e1.getMessage());
				e1.printStackTrace();
				view.revalidate();
				view.repaint();
				b = null;
			}
	    	
	    }
	    else if(b.getActionCommand().equals("Play Minion")&& selectedButton!=null) {
	    	if(curHeroHand.contains(selectedButton)) {
	    		view.getErrors().setText("");
	    		if (isMinion(getIndex(selectedButton,curHeroHand))) {
					JPanel p;
					JPanel s;
					if(flag) {
						p = view.getPlayer1Hand();
						s = view.getPlayer1Field();
					}
					else {
						p = view.getPlayer2Hand();
						s = view.getPlayer2Field();
					}
					try {
						model.getCurrentHero().playMinion((Minion)model.getCurrentHero().getHand().get(getIndex(selectedButton, curHeroHand)));
						s.add(curHeroHand.get(getIndex(selectedButton, curHeroHand)));
						curHeroField.add(curHeroHand.get(getIndex(selectedButton, curHeroHand)));
						p.remove(curHeroHand.get(getIndex(selectedButton, curHeroHand)));
						curHeroHand.remove(curHeroHand.get(getIndex(selectedButton, curHeroHand)));
						selectedButton = null;
						heroesIn();
						updateFields();
						updateHand();
						view.getErrors().setText("");
						view.revalidate();
						view.repaint();
					} catch (NotYourTurnException | NotEnoughManaException | FullFieldException e1) {
						// TODO Auto-generated catch block
						view.getErrors().setText(e1.getMessage());
						e1.printStackTrace();
						view.revalidate();
						view.repaint();
						b = null;
					}
				}
	    		
	    	}
	    		
	    }
		else if(b.getActionCommand().equals("Use Hero Power")) {
			if(model.getCurrentHero() instanceof Mage || model.getCurrentHero() instanceof Priest) {
				selectedButton = b;
				}
			else {
				try {
					view.getErrors().setText("");
					model.getCurrentHero().useHeroPower();
					updateFields();
					updateHand();
					heroesIn();
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
						| FullHandException | FullFieldException | CloneNotSupportedException e1) {
					view.getErrors().setText(e1.getMessage());
					e1.printStackTrace();
					view.revalidate();
					view.repaint();
					b = null;
				}
			}
			heroesIn();
		}
		else if( curHeroHand.contains(b)) {	//Minion in current hero field, attacker minion
				Spell c;
				if(curHeroHand.contains(b)&& !isMinion(getIndex(b,curHeroHand))) {
					c = (Spell) model.getCurrentHero().getHand().get(getIndex(b,curHeroHand));
					if(c instanceof AOESpell) {
						AOESpell a =(AOESpell)c;
						try {
							view.getErrors().setText("");
							model.getCurrentHero().castSpell(a, model.getOpponent().getField());
							view.repaint();
							view.revalidate();
							updateFields();
							updateHand();
							heroesIn();
						} catch (NotYourTurnException | NotEnoughManaException e1) {
							view.getErrors().setText(e1.getMessage());
							e1.printStackTrace();
							view.revalidate();
							view.repaint();
							b = null;
						}
						}
					else if(c instanceof FieldSpell) {
						FieldSpell f = (FieldSpell)c;
						try {
							view.getErrors().setText("");
							model.getCurrentHero().castSpell(f);
							view.repaint();
							view.revalidate();
							updateFields();
							updateHand();
							heroesIn();
						} catch (NotYourTurnException | NotEnoughManaException e1) {
							view.getErrors().setText(e1.getMessage());
							e1.printStackTrace();
							view.revalidate();
							view.repaint();
							b = null;
						}
					}
					else 
						selectedButton = b;
				}
		
				else 
						selectedButton = b;
				
			}
		else if((oppHeroField.contains(b) || curHeroField.contains(b)) ) {// Minion in opponent field, target minion
					if(curHeroField.contains(b)&& selectedButton == null)
						selectedButton = b;
					else {
						Minion tar;
						if (oppHeroField.contains(b))
							tar =(Minion) model.getOpponent().getField().get(getIndex(b, oppHeroField));
						else 
							tar =(Minion) model.getCurrentHero().getField().get(getIndex(b, curHeroField));
						if (curHeroField.contains(selectedButton)) { //minion exists in current field, attacker minion
							Minion attacker = (Minion)model.getCurrentHero().getField().get(getIndex(selectedButton, curHeroField));
								try {
									view.getErrors().setText("");
									model.getCurrentHero().attackWithMinion(attacker, tar);
									updateFields();
									selectedButton=null;
									updateHand();
									heroesIn();
							
						} 		catch (CannotAttackException | NotYourTurnException | TauntBypassException
										| InvalidTargetException | NotSummonedException e1) {
									view.getErrors().setText(e1.getMessage());
									e1.printStackTrace();
									view.revalidate();
									view.repaint();
									b = null;
						}
								
					heroesIn();
					view.revalidate();
					view.repaint();
					}
						
					
					else if(curHeroHand.contains(selectedButton)&& !isMinion(getIndex(selectedButton, curHeroHand))) {
						Spell s = (Spell) model.getCurrentHero().getHand().get(getIndex(selectedButton,curHeroHand));
						if(s instanceof MinionTargetSpell ) { 
							try {
								view.getErrors().setText("");
								MinionTargetSpell m = (MinionTargetSpell)s;
								model.getCurrentHero().castSpell(m, tar);
								updateFields();
								selectedButton = null;
								updateHand();
								heroesIn();
							} catch (NotYourTurnException | NotEnoughManaException | InvalidTargetException e1) {
								view.getErrors().setText(e1.getMessage());
								e1.printStackTrace();
								view.revalidate();
								view.repaint();
								b = null;
							}
						}
						else if (s instanceof LeechingSpell){
							LeechingSpell l = (LeechingSpell)s;
							try {
								view.getErrors().setText("");
								model.getCurrentHero().castSpell(l, tar);
								updateFields();
								selectedButton = null;
								updateHand();
								heroesIn();
							} catch (NotYourTurnException | NotEnoughManaException e1) {
								view.getErrors().setText(e1.getMessage());
								e1.printStackTrace();
								view.revalidate();
								view.repaint();
								b = null;
							}	
						}
					}
				   else if(selectedButton.getText().equals("Use Hero Power")) {
					  if(model.getCurrentHero() instanceof Mage ) {
						  Mage m = (Mage) model.getCurrentHero();
						  try {
							view.getErrors().setText("");
							m.useHeroPower(tar);
							updateFields();
							updateHand();
							heroesIn();
							selectedButton = null;
						  } catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
								| FullHandException | FullFieldException | CloneNotSupportedException e1) {
							  view.getErrors().setText(e1.getMessage());
								e1.printStackTrace();
								view.revalidate();
								view.repaint();
								b = null;
						 }
					 }
					 else if(model.getCurrentHero() instanceof Priest) {
						Priest p = (Priest) model.getCurrentHero();
						try {
							view.getErrors().setText("");
							p.useHeroPower(tar);
							updateFields();
							selectedButton = null;
							updateHand();
							heroesIn();
						} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
								| FullHandException | FullFieldException | CloneNotSupportedException e1) {
							view.getErrors().setText(e1.getMessage());
							e1.printStackTrace();
							view.revalidate();
							view.repaint();
							b = null;
						}
					}
					
				}
					}
			}
			else if((b.getActionCommand().equals("Player 2")||b.getActionCommand().equals("Player 1"))&& selectedButton!=null) {
			
				Hero tar;
				
				if(flag) {
					if(b.getActionCommand().equals("Player 1")) {
						tar = model.getCurrentHero();
					}
					else
						tar = model.getOpponent();
				}
				else {
					if(b.getActionCommand().equals("Player 1"))
						tar = model.getOpponent();
					else
						tar = model.getCurrentHero();
				}
				if(curHeroField.contains(selectedButton)) {
					Minion attacker = (Minion)model.getCurrentHero().getField().get(getIndex(selectedButton, curHeroField));
					try {
						view.getErrors().setText("");
						model.getCurrentHero().attackWithMinion(attacker, tar);
						updateFields();
						selectedButton = null;
						updateHand();
						heroesIn();
					} catch (CannotAttackException | NotYourTurnException | TauntBypassException | NotSummonedException
							| InvalidTargetException e1) {
						view.getErrors().setText(e1.getMessage());
						e1.printStackTrace();
						view.revalidate();
						view.repaint();
						b = null;
					}
				}
				else if(curHeroHand.contains(selectedButton)&& !isMinion(getIndex(selectedButton, curHeroHand))) {
					HeroTargetSpell c = (HeroTargetSpell)model.getCurrentHero().getHand().get(getIndex(selectedButton, curHeroHand));
					if(c instanceof KillCommand || c instanceof Pyroblast) {
						try {
							view.getErrors().setText("");
							model.getCurrentHero().castSpell(c,tar);
							updateHand();
							updateFields();
							heroesIn();
							selectedButton = null;
						} catch (NotYourTurnException | NotEnoughManaException e1) {
							view.getErrors().setText(e1.getMessage());
							e1.printStackTrace();
							view.revalidate();
							view.repaint();
							b = null; 
						}
					}
				}
				else if(selectedButton.getText().equals("Use Hero Power")) {
					if(model.getCurrentHero() instanceof Mage ) {
						Mage m = (Mage) model.getCurrentHero();
						try {
							view.getErrors().setText("");
							m.useHeroPower(tar);
							heroesIn();
							updateHand();
							updateFields();
							selectedButton = null;
						} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
								| FullHandException | FullFieldException | CloneNotSupportedException e1) {
							view.getErrors().setText(e1.getMessage());
							e1.printStackTrace();
							view.revalidate();
							view.repaint();
							b = null;
						}
					}
					else if(model.getCurrentHero() instanceof Priest) {
						Priest p = (Priest) model.getCurrentHero();
						try {
							view.getErrors().setText("");
							p.useHeroPower(tar);
							heroesIn();
							updateHand();
							updateFields();
							selectedButton= null;
						} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
								| FullHandException | FullFieldException | CloneNotSupportedException e1) {
							view.getErrors().setText(e1.getMessage());
							e1.printStackTrace();
							view.revalidate();
							view.repaint();
							b = null;
						}
					}
					
				}
					
			}
			
		
		}
	

	public int getIndex(JButton b, ArrayList<JButton> a) {
		int i;
		for(i=0; i<a.size();i++) {
			if (b==a.get(i)) 
				return i;		
	}
		return 0;
		}
	
	public boolean isMinion(int index) {
		if(model.getCurrentHero().getHand().get(index) instanceof Minion)
			return true;
		return false;
	}
	
	@Override
	public void onGameOver() {
		String h = ""; 
		if (player1.getCurrentHP()==0) {
				h = "Player 1";
		}
		else 
			h = "Player 2";
		view.dispose();
		GameOverView view3 = new GameOverView(h);
		view3.setVisible(true);
			
		
	}
		
	
	public void updateHand() {
		curHeroHand.removeAll(curHeroHand);
		String s;
		if (flag) {
			view.getPlayer1Hand().removeAll();
		}
		else
			view.getPlayer2Hand().removeAll();
		for(int i=0;i< model.getCurrentHero().getHand().size();i++) {
			s = "";
			JButton b = new JButton();
			Card c = model.getCurrentHero().getHand().get(i);
			if (model.getCurrentHero().getHand().get(i) instanceof Minion) {
				Minion a = (Minion) c;
				if(a.isTaunt()&& a.isDivine()&& !(a.isSleeping())) {
					 s+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
  				 "<br>" + "Taunt" + "<br>" + "Divine"+"<br>"  +"Charge"+ "</html>"; 
				}
				else if(a.isDivine()&& a.isTaunt()) {
					 s+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
		    				 "<br>" + "Taunt" + "<br>"+ "Divine"+ "</html>"; 
				}
				else if (a.isTaunt()&& !(a.isSleeping())) {
					 s+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
		    				 "<br>" + "Taunt"+ "<br>"  +"Charge"+ "</html>"; 
				}
				else if(a.isDivine() && !(a.isSleeping())) {
					 s+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
    				 "<br>" + "Divine"+ "<br>"  +"Charge"+ "</html>";
					 }
				else if (!a.isSleeping()) {
					 s+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
		    				 "<br>" +"Charge"+ "</html>";
				}
				else if(a.isDivine()) {
					 s+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
		    				 "<br>" + "Divine"+ "</html>";
				}
				else if (a.isTaunt()) {
					 s+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
		    				 "<br>" + "Taunt"+ "</html>"; 
				}
				else
					s+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
  				  "</html>";  
  			}
   			else {
   				s+= "<html>" + "Name: "+c.getName() + "<br>" + "ManaCost: "+c.getManaCost()+ "<br>" + "Rarity: "+ c.getRarity() +"</html>";
   			}
			b.setText(s);
			b.addActionListener(this);
			curHeroHand.add(b);
			if (flag)
				view.getPlayer1Hand().add(b);
			else
				view.getPlayer2Hand().add(b);	
		}
		view.revalidate();
		view.repaint();
	}
	public void updateFields() {
		view.getPlayer1Field().removeAll();
		view.getPlayer2Field().removeAll();
		curHeroField.removeAll(curHeroField);
		oppHeroField.removeAll(oppHeroField);
		for(int i=0;i< model.getCurrentHero().getField().size();i++) {
			String x = "";
			JButton b = new JButton();
			Card c = model.getCurrentHero().getField().get(i);
			if (model.getCurrentHero().getField().get(i) instanceof Minion) {
				Minion a = (Minion) c;
				if(a.isTaunt()&& a.isDivine()&& a.isSleeping()) {
					 x+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
 				 "<br>" + "Taunt" + "<br>" + "Divine"+"<br>"  +"Sleeping"+ "</html>"; 
				}
				else if(a.isDivine()&& a.isTaunt()) {
					 x+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
		    				 "<br>" + "Taunt" + "<br>"+ "Divine"+ "</html>"; 
				}
				else if (a.isTaunt()&& a.isSleeping()) {
					 x+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
		    				 "<br>" + "Taunt"+ "<br>"  +"Sleeping"+ "</html>"; 
				}
				else if(a.isDivine() && a.isSleeping()) {
					 x+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
   				 "<br>" + "Divine"+ "<br>"  +"Sleeping"+ "</html>";
					 }
				else if (a.isSleeping()) {
					 x+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
		    				 "<br>" +"Sleeping"+ "</html>";
				}
				else if(a.isDivine()) {
					 x+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
		    				 "<br>" + "Divine"+ "</html>";
				}
				else if (a.isTaunt()) {
					 x+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
		    				 "<br>" + "Taunt"+ "</html>"; 
				}
				else
					x+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
 				  "</html>"; 
			}
			b.setText(x);
			b.addActionListener(this);
			curHeroField.add(b);
			if (flag)
				view.getPlayer1Field().add(b);
			else
				view.getPlayer2Field().add(b);
			view.revalidate();
			view.repaint();
		}
		for(int i=0;i< model.getOpponent().getField().size();i++) {
			String y = "";
			JButton b = new JButton();
			Card c = model.getOpponent().getField().get(i);
			if (model.getOpponent().getField().get(i) instanceof Minion) {
				Minion a = (Minion) c;
				if(a.isTaunt()&& a.isDivine()&& a.isSleeping()) {
					 y+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
  				 "<br>" + "Taunt" + "<br>" + "Divine"+"<br>"  +"Sleeping"+ "</html>"; 
				}
				else if(a.isDivine()&& a.isTaunt()) {
					 y+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
		    				 "<br>" + "Taunt" + "<br>"+ "Divine"+ "</html>"; 
				}
				else if (a.isTaunt()&& a.isSleeping()) {
					 y+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
		    				 "<br>" + "Taunt"+ "<br>"  +"Sleeping"+ "</html>"; 
				}
				else if(a.isDivine() && a.isSleeping()) {
					 y+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
    				 "<br>" + "Divine"+ "<br>"  +"Sleeping"+ "</html>";
					 }
				else if (a.isSleeping()) {
					 y+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
		    				 "<br>" +"Sleeping"+ "</html>";
				}
				else if(a.isDivine()) {
					 y+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
		    				 "<br>" + "Divine"+ "</html>";
				}
				else if (a.isTaunt()) {
					 y+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
		    				 "<br>" + "Taunt"+ "</html>"; 
				}
				else
					y+="<html>" + "Name: "+a.getName() + "<br>" + "ManaCost: "+a.getManaCost()+ "<br>" + "Rarity: "+ a.getRarity() +"<br>" + "Current Hp: "+ a.getCurrentHP()+"<br>"+ "Attack: " +a.getAttack()+
  				  "</html>"; 
			}
			b.setText(y);
			b.addActionListener(this);
			oppHeroField.add(b);
			if (flag)
				view.getPlayer2Field().add(b);
			else 
				view.getPlayer1Field().add(b);
			view.revalidate();
			view.repaint();
			
		}
	}
	public void heroesIn() {
		String h = "Name: ";
		h+= model.getCurrentHero().getName()+"\n";
		h+= "Current Hp: "+ model.getCurrentHero().getCurrentHP()+"\n";
		h+= "Current ManaCrystals: "+ model.getCurrentHero().getCurrentManaCrystals()+"\n";
		h+= "Total ManaCrystals: "+ model.getCurrentHero().getTotalManaCrystals()+"\n";
		h+= "Cards in deck: "+ model.getCurrentHero().getDeck().size()+"\n";
		String v = "Name: ";
		v+= model.getOpponent().getName()+"\n";
		v+= "Current Hp: "+ model.getOpponent().getCurrentHP()+"\n";
		v+= "Current ManaCrystals: "+ model.getOpponent().getCurrentManaCrystals()+"\n";
		v+= "Total ManaCrystals: "+ model.getOpponent().getTotalManaCrystals()+"\n";
		v+= "Cards in deck: "+ model.getOpponent().getDeck().size()+"\n";
		v+= "Cards in hand: "+ model.getOpponent().getHand().size();
		view.getHeroesInfo().setText("Current Hero Info"+"\n"+h+"\n"+"Opponent Hero Info "+"\n"+v);
		
	}
	
	public static void main(String[]args) {
	}
}

