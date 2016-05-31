package model;

import java.util.*;

public class Spiel {
	
	private Spiel(String spielername1, String spielername2)
	{
		//Spieler hinzufügen
		Spieler = new ArrayList<Spieler>();
		
		Spieler spieler1 = new Spieler(spielername1,'x');
		Spieler spieler2 = new Spieler(spielername2, 'o');
		Spieler.add(spieler1);
		Spieler.add(spieler2);
		
		//Der erste Spieler beginnt
		AktuellerSpieler = spieler1;
		
		//Datum des Spielbeginns setzen
		Beginn = new Date();
		
		//Marker initialisieren
		Markers = new ArrayList<Marker>();
	}
	
	private static Spiel spiel;
	
	public static Spiel GetSpiel()
	{
		if(spiel == null)
		{
			spiel = new Spiel("Spieler 1", "Spieler 2");
		}
		return spiel;
	}
	
	//Ermitteln des nächsten Spielers
	public void NaechsterSpieler()
	{
		for(Spieler naechsterSpieler : Spieler)
		{
			if(naechsterSpieler != AktuellerSpieler)
			{
				AktuellerSpieler = naechsterSpieler;
				break;
			}
		}
	}
	
	private List<Spieler> Spieler;
	private Date Beginn;
	private List<Marker> Markers; 
	
	//Spieler, der an der Reihe ist
	public Spieler AktuellerSpieler;

	//Erzeugt neuen Marker im Spiel
	public void AddMarker(int x, int y) {
		Markers.add(new Marker(AktuellerSpieler,x,y));
	}
}
