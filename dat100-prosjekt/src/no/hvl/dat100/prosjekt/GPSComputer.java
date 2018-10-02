package no.hvl.dat100.prosjekt;

public class GPSComputer {
	
	public GPSComputer(GPSData gpsdata) {

		GPSDataConverter converter = new GPSDataConverter(gpsdata);
		converter.convert();

		this.times = converter.times;
		this.latitudes = converter.latitudes;
		this.longitudes = converter.longitudes;
		this.elevations = converter.elevations;
	}

	// tabeller for GPS datapunkter
	public int[] times;
	public double[] speeds;
	public double[] latitudes;
	public double[] longitudes;
	public double[] elevations;
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO
		// OPPGAVE - START
		int n = times.length;
		for (int i = 0; i < n - 1; i++ ) {
			double dis = GPSUtils.distance(latitudes[i], longitudes[i], latitudes[i+1], longitudes[i+1]);
			distance = distance + dis;
		}
		// Hint: bruk distance-metoden fra GPSUtils.
		
		// OPPGAVE - SLUTT

		return distance;
	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		// TODO
		// OPPGAVE - START
		int n = elevations.length;
		for (int i = 0; i < n - 1; i++) {
			if (elevations[i] > 0) {
				elevation = elevation + elevations[i];
			}
		}
		// OPPGAVE - SLUTT
		return elevation;
	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {
		
		
		// TODO 
		// OPPGAVE START
		return times[times.length - 1] - times[0];
		// OPPGAVE SLUTT
		
	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene
	public double[] speeds() {

		double[] speeds = new double[times.length-1];
		
		// TODO
		// OPPGAVE - START
		int n = times.length;
		for (int i = 0; i < n - 1; i++) {
			int secs = times[i+1] - times [i];
			speeds[i] = GPSUtils.speed(secs, latitudes[i], longitudes[i], latitudes[i+1], longitudes[i+1]);
		}

		// OPPGAVE - SLUTT
		return speeds;
	}

	// beregn maximum hastighet km/t
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		// TODO
		// OPPGAVE - START
		maxspeed = GPSUtils.findMax(speeds()) * 3.6;
		// OPPGAVE - SLUTT
		
		return maxspeed;
	}
	
	// beregn gjennomsnittshasitiget for hele turen km/t
	public double averageSpeed() {

		double average = 0;
		
		// TODO
		// OPPGAVE - START
		average = totalDistance() / totalTime();
		// OPPGAVE - SLUTT
		
		return average;
	}


	// conversion factor kph (km/t) to miles per hour (mph)
	public static double MS = 0.62;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal = 0;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;

		// TODO
		// OPPGAVE START
		if (speedmph < 10) {
			met = 4.0;
		}
		else if (speedmph < 12) {
			met = 6.0;
		}
		else if (speedmph < 14) {
			met = 8.0;
		}
		else if (speedmph < 16) {
			met = 10.0;
		}
		else if (speedmph < 20) {
			met = 12.0;
		}
		else {
			met = 16.0;
		}
		
		// Energy Expended (kcal) = MET x Body Weight (kg) x Time (h)
		kcal = met * weight * secs / 3600;
		// OPPGAVE SLUTT
		
		return kcal;
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO
		// OPPGAVE - START 
		int n = times.length;
		for (int i = 0; i < n; i ++) {
			totalkcal = totalkcal + kcal(weight, times[i], speeds[i]);
		}
		// Hint: hent hastigheter i speeds tabellen og tider i timestabellen
		// disse er definer i toppen av klassen og lese automatisk inn
		
		// OPPGAVE - SLUTT
		
		return totalkcal;
	}
	
	private static double WEIGHT = 80.0;
	
	// skriv ut statistikk for turen
	public void print() {
		
		// TODO
		// OPPGAVE - START
		System.out.println("GPS datafile: " + "medium");
        System.out.println("Total Time     :" + "   " + totalTime());
        System.out.println("Total distance :" + "      " + totalDistance() + " km");
        System.out.println("Total elevation:" + "     " + totalElevation() + " m");
        System.out.println("Max speed      :" + "      " + maxSpeed() + " km/t");
        System.out.println("Average speed  :" + "      " + speeds() + " km/t");
        System.out.println("Energy         :" + "     " + totalKcal(WEIGHT) + " kcal");
		// OPPGAVE - SLUT
	}
	
	// ekstraoppgaver
	public void climbs() {
		
	}
	
	public void maxClimb() {
		
	}
}
