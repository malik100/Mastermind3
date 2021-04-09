import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Mastermind3 extends JFrame
	{
		public static void main( String[] args )
		{
			// Maak een frame
			JFrame frame = new Mastermind3();
			frame.setSize( 1000, 1250 );
			frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			frame.setTitle( "Mastermind" );
			JPanel paneel = new Paneel();
			frame.setContentPane( paneel );
			frame.setVisible( true );
		}
	}

class Paneel extends JPanel
	{	
		private Color kleurBord, kleurBol;
		private Timer geraden;
	    private int rij, kolom, tellerJuistePlek, tellerAanwezig;
	    private Color [][] arrayKleurPion, arrayKleurPion2;
	    private static Color[] colorArray = {Color.red, Color.orange, Color.yellow, Color.magenta};
	    private boolean aan;
	    
		 public Paneel()
		 {
			 // kleur rgb waarde geven.
			 kleurBord = new Color(210,105,20);
			 kleurBol = new Color(229,165,116);
			 arrayKleurPion = new Color [12][4];
			 arrayKleurPion2 = new Color [12][4];
			 geraden = new Timer( 500, new TimerHandler() );
			 // pionnen standaard achtergrond kleur geven.
			 for (int rij = 0; rij < 12; rij++)
			 {
				 for (int kolom = 0; kolom < 4; kolom++)
				 {
					 arrayKleurPion[rij][kolom] = kleurBol;
					 arrayKleurPion2[rij][kolom] = kleurBol;
				 }
			 }
			 
			 rij = 0;
			 kolom = 0;
			 tellerJuistePlek = tellerAanwezig = 0;
			 aan = true;
			 // knoppen maken
			 createButton("geel");
			 createButton("rood");
			 createButton("oranje");
			 createButton("blauw");
			 createButton("groen");
			 createButton("paars");
		 }		
		 	 
		 public void paintComponent( Graphics g )
		 {
			 super.paintComponent( g );
			 g.setColor(kleurBord);
			 g.fillRect(30, 30, 700, 950);
			 
			 for (int rij = 0; rij < 12; rij++)
			 {
				 for (int kolom = 0; kolom < 4; kolom++)
				 {
					 g.setColor(arrayKleurPion[rij][kolom]);
					 // grote bolletjes
					 g.fillOval(50 + kolom * 75, 50 + rij * 75, 50, 50);
					 
					 g.setColor(arrayKleurPion2[rij][kolom]);
					 // kleine bolletjes
					 g.fillOval(400 + kolom * 50, 60 + rij * 75, 25, 25);
					 
					 g.setColor(colorArray[kolom]);
					 // ballen die moeten geraden worden
					 g.fillOval(650 + kolom * 50, 60 + rij * 75, 25, 25);
				 }
			 }
			 
			 
			}
		 // createButton methode maakt button en koppelt met Handler
		 public void createButton(String naam) {
			 JButton knop;
			 knop = new JButton(naam);
			 add(knop);
			 knop.addActionListener(this::actionPerformed);
			 
			 // achtergrond kleur bepalen
			 switch(naam) {
			 case "geel" :
				 knop.setBackground(Color.yellow);
				 break;
			 case "rood" :
				 knop.setBackground(Color.red);
				 break;
			 case "oranje" :
				 knop.setBackground(Color.orange);
				 break;
			 case "blauw" :
				 knop.setBackground(Color.blue);
				 break;
			 case "groen" :
				 knop.setBackground(Color.green);
				 break;
			 case "paars" :
				 knop.setBackground(Color.magenta);
				 break;
			 }
		 }
		 
		 public void actionPerformed(ActionEvent e) {
			 String naam;
			 naam = e.getActionCommand();
			 // kijken welke button is ingedrukt en uitvoeren code die erbij hoort.
			 geraden.stop();
			 switch(naam) {
			 case "geel" :
				 arrayKleurPion[rij][kolom] = Color.yellow;
				 break;
			 case "rood" :
				 arrayKleurPion[rij][kolom] = Color.red;
				 break;
			 case "oranje" :
				 arrayKleurPion[rij][kolom] = Color.orange;
				 break;
			 case "blauw" :
				 arrayKleurPion[rij][kolom] = Color.blue;
				 break;
			 case "groen" :
				 arrayKleurPion[rij][kolom] = Color.green;
				 break;
			 case "paars" :
				 arrayKleurPion[rij][kolom] = Color.magenta;
				 break;
			 }
			 
			 repaint();
			 kolom++;
			 if( kolom == 4)
			 {
				 // Controleren voor de kleuren
				 for (int i = 0; i < 4; i++)
				 {
					 for (int j = 0; j < 4; j++)
					 {
						 // op de juiste plaats
						 if (arrayKleurPion[rij][i] == colorArray[j] && i == j)
						 {
							 tellerJuistePlek++;
						 }
						 else if (arrayKleurPion[rij][i] == colorArray[j] && i != j)
						 {
							 tellerAanwezig++;
						 }
					 }
				 }
				 if (tellerJuistePlek == 4)
				 {
					 geraden.start();
				 }
				 int index = 0;
				 while (tellerJuistePlek > 0)
				 {
					arrayKleurPion2[rij][index] = Color.white;
					tellerJuistePlek--;
					index++;
				 }
				 while (tellerAanwezig > 0)
				 {
					arrayKleurPion2[rij][index] = Color.black;
					tellerAanwezig--;
					index++;
				 }
			
				 kolom = 0;
				 rij++;
			 }
		 }
		 
		 class TimerHandler implements ActionListener{
			 public void actionPerformed( ActionEvent e ) {
				 if ( aan == true)
				 {
					 for (int i = 0; i < 4; i++)
					 {
							 arrayKleurPion[rij - 1][i]  = kleurBol;
					 }
					 repaint();
					 aan = false;
				 }
				 else
				 {
					 for (int i = 0; i < 4; i++)
					 {
							 arrayKleurPion[rij - 1][i]  = colorArray[i];
					 }
					 repaint();
					 aan = true;
				 }
			 }
		 }
			 
	 
	}