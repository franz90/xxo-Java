package ui;
import model.*;
import java.awt.*;
import java.awt.event.*;

public class Spielbrett extends Frame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1477065363674591733L;
	private Spiel spiel;
	
	public Spielbrett()
	{
		//Dialog erstellen
		setSize(600,600);      // Fenstergröße einstellen  
	    addWindowListener(new SpielbrettWindowListener()); // EventListener für das Fenster hinzufügen
	                                                 // (notwendig, damit das Fenster geschlossen werden kann)
	    
	    setLayout(new GridLayout(3,3));		//Layout als Gridlayout 3x3 setzen

	    // Knopfe für XXO Spielbrett initialisieren
	    for(int x = 1; x <= 3; x++)
	    {
	    	for(int y = 1; y <= 3; y++)
		    {	    		
	    		Button knopf = new Button();
	    		System.out.println("Knopf x = " + x + " y = " + y + " wurde generiert");
	    		knopf.setActionCommand(String.format("%1$d;%2$d", x,y));
	    	
	    		knopf.setPreferredSize(new Dimension(100,100));
	    		knopf.addActionListener(new KnopfActionListener());
	    		add(knopf);
		    }
	    }

	    pack();
	    
	    spiel = Spiel.GetSpiel();
	    TitelSetzen();
	    
	    setVisible(true);                            // Fenster (inkl. Inhalt) sichtbar machen
	      
	}
	
	  private void TitelSetzen() 
	  {
	    	setTitle(spiel.AktuellerSpieler.Name + " ist an der reihe");  // Fenstertitel setzen
	}

	class SpielbrettWindowListener extends WindowAdapter
	  {
	    public void windowClosing(WindowEvent e)
	    {
	      e.getWindow().dispose();                   // Fenster "killen"
	      System.exit(0);                            // VM "killen" 
	    }           
	  }
	  
	  class KnopfActionListener implements ActionListener
	  {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	System.out.println("drücken");
	    	
	    	//Aktuell gedrückten Knopf ermitteln
	    	
	    	Button knopf = Button.class.cast(e.getSource());
	    	
	    	//ActionCommand enthält die Koordinaten des Buttons und muss in x und y konvertiert werden
	    	String[] parts = knopf.getActionCommand().split(";");
	    	int x = Integer.parseInt(parts[0]);
	    	int y = Integer.parseInt(parts[1]);
	    	
	    	System.out.println(knopf.getActionCommand());
	    		    	
	    	System.out.println(spiel.AktuellerSpieler.Name + " hat auf x = " + x + " y = " + y + " geklickt");
	    	
	    	spiel.AddMarker(x, y);
	    	
	    	//Setzt das Label
	    	knopf.setLabel(Character.toString(spiel.AktuellerSpieler.Symbol));
	    	
	    	spiel.NaechsterSpieler();
	    	TitelSetzen();
	    }           
	  }
}
