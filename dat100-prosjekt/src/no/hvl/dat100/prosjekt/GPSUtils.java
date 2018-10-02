package no.hvl.dat100.prosjekt;

import static java.lang.Math.*;

public class GPSUtils {

	public GPSUtils() {
	
	}
	
	// konverter sekunder til string på formen hh:mm:ss
	public static String printTime(int secs) {
		
		String timestr = "";
		String TIMESEP = ":";
		
		// TODO
		// OPPGAVE - START
	    int hours = secs / 3600;
	    int remainder = secs - hours * 3600;
	    int mins = remainder / 60;
	    remainder = remainder - mins * 60;
	    int sec = remainder;

	    timestr = hours + TIMESEP + mins + TIMESEP + sec;	
		// OPPGAVE - SLUTT
		
		return timestr;
	}
	
	// beregn maximum av en tabell av doubles med minnst et element
	public static double findMax(double[] da) {

		double max = da[0];

		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}

		return max;
	}

	// beregn minimum av en tabell av doubles med minnst et element
	public static double findMin(double[] da) {

		// fjern = "0.0" når metoden implementeres for ikke få forkert minimum
		double min = da[0]; 

		// TODO
		// OPPGAVE - START
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		// OPPGAVE - SLUT
		return min;
	}

	
	private static int R = 6371000; // jordens radius
	
	// Beregn avstand mellom to gps punkter ved bruk av Haversine formlen
	public static double distance(double latitude1, double longitude1, double latitude2, double longitude2) {

		double a,c,d; // fjern = 1.0
		
		// TODO:
		// OPPGAVE - START
		double deltaLat = toRadians(latitude2 - latitude1);
		double deltaLong = toRadians(longitude2 - longitude1);
		double R = 637100;
		a = Math.pow((sin (deltaLat / 2)), 2) + cos (toRadians(latitude1)) * cos (toRadians(latitude2)) * Math.pow((sin (deltaLong / 2)), 2);
		c = 2 * atan2(sqrt(a), sqrt(1 - a));
		d = R * c;
		// OPPGAVE - SLUTT

		return d;
	}
	
	// beregn gjennomsnits hastighet i km/t mellom to gps punkter
	public static double speed(int secs, double latitude1, double longitude1, double latitude2, double longitude2) {

		double speed = 0.0;

		// TODO:
		// OPPGAVE - START
		double d = distance(latitude1, longitude1, latitude2, longitude2);
		speed = (d / secs) * 3.6;
		// OPPGAVE - SLUTT

		return speed;
	}
	
	private static int TEXTWIDTH = 10;
	
	// konverter double til string med 2 decimaler og streng lengde 10
	// eks. 1.346 konverteres til "      1.35" og enhet til slutt
	// Hint: se på String.format metoden
	
	public static String printDouble(double d) {
		
		String str = "";
		
		// TODO
		// OPPGAVE - START
		String round = String.format("%.2f", d);
		str = String.format("%1$"+ TEXTWIDTH + "s", round);
		// OPPGAVE - SLUTT
		
		return str;
	}
}
