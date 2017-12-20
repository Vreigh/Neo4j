package Chase.DB_Chase.generator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import Chase.DB_Chase.CriminalRepository;
import Chase.DB_Chase.PenaltyRepository;
import Chase.DB_Chase.PolicemenRepository;
import Chase.DB_Chase.entity.Criminal;
import Chase.DB_Chase.entity.Penalty;
import Chase.DB_Chase.entity.Policeman;

public class DBGenerator {
	static final int N = 5;
	
	private Random rn = new Random(System.currentTimeMillis());
	
	public void generate(PolicemenRepository pr, CriminalRepository cr, PenaltyRepository penr) {
		pr.deleteAll();
		cr.deleteAll();
		penr.deleteAll();
		
		ArrayList<Policeman> policemen = new ArrayList<Policeman>();
		ArrayList<Criminal> criminals = new ArrayList<Criminal>();
		
		policemen.add(new Policeman("Grzegorz Pościg", 1353));
		policemen.add(new Policeman("Marcin Sprawiedliwy", 3234));
		policemen.add(new Policeman("Jerzy Pomsta", 5732));
		policemen.add(new Policeman("Filip Prawo", 2453));
		policemen.add(new Policeman("Stefan Bezpieczeństwo", 7571));
		
		criminals.add(new Criminal("Jan Przekręt", "Łysy"));
		criminals.add(new Criminal("Mariusz Występek", "Młody"));
		criminals.add(new Criminal("Łukasz Zło", "Szlugi"));
		criminals.add(new Criminal("Mateusz Kradzież", "Duży"));
		criminals.add(new Criminal("Damian Łapówka", "Spryciarz"));
		
		pr.save(policemen);
		cr.save(criminals);
		
		
		
		// Losowe tworzenie relacji przyjaźni
		for(int i=0; i<N; i++) {
			int friendsN = rn.nextInt(N);
			for(int j=0; j<friendsN; j++) {
				int f = rn.nextInt(N);
				if(f == i) continue; // nie przyjaznimy sie z samym soba
				policemen.get(i).addFriend(policemen.get(f));
				policemen.get(f).addFriend(policemen.get(i));
			}
		}
		
		pr.save(policemen);
		
		// Przykładowe tworzenie relacji kolaboracji
		
		criminals.get(0).addCollaborator(criminals.get(2), true, true, new GregorianCalendar(1997, Calendar.DECEMBER, 13).getTime());
		criminals.get(2).addCollaborator(criminals.get(0), false, false, new GregorianCalendar(1997, Calendar.DECEMBER, 11).getTime());
		
		criminals.get(1).addCollaborator(criminals.get(4), false, true, new GregorianCalendar(2001, Calendar.JUNE, 17).getTime());
		criminals.get(4).addCollaborator(criminals.get(1), true, false, new GregorianCalendar(2001, Calendar.JUNE, 17).getTime());
		
		criminals.get(0).addCollaborator(criminals.get(3), true, true, new GregorianCalendar(2012, Calendar.MAY, 13).getTime());
		criminals.get(3).addCollaborator(criminals.get(0), false, false, new GregorianCalendar(2012, Calendar.JULY, 13).getTime());
		
		criminals.get(2).addCollaborator(criminals.get(4), false, false, new GregorianCalendar(2015, Calendar.APRIL, 13).getTime());
		criminals.get(4).addCollaborator(criminals.get(2), true, true, new GregorianCalendar(2016, Calendar.APRIL, 13).getTime());
		
		criminals.get(1).addCollaborator(criminals.get(0), true, false, new GregorianCalendar(2013, Calendar.AUGUST, 13).getTime());
		criminals.get(0).addCollaborator(criminals.get(1), true, true, new GregorianCalendar(2013, Calendar.AUGUST, 17).getTime());
		
		criminals.get(3).addCollaborator(criminals.get(4), false, false, new GregorianCalendar(2015, Calendar.JANUARY, 13).getTime());
		criminals.get(4).addCollaborator(criminals.get(3), true, true, new GregorianCalendar(2015, Calendar.MAY, 13).getTime());
		
		cr.save(criminals);
		
		// Przykładowe tworzenie kar (metoda tworząca karę od razu tworzy jej relacje)
		for(int i=0; i<15; i++) {
			Policeman randomPoliceman = policemen.get(rn.nextInt(N));
			Criminal randomCriminal = criminals.get(rn.nextInt(N));
			Penalty penalty = new Penalty("" + i, getRandomDate(), rn.nextInt(1000), randomPoliceman, randomCriminal);
			penr.save(penalty);
		}
		
		
		// Losowe tworzenie relacji podejrzenia
		for(int i=0; i<N; i++) {
			int suspectsN = rn.nextInt(N / 2 + 1); // ilu do podejrzenia
			Set<Integer> criminalsToSuspect = new HashSet<Integer>(); 
			for(int j=0; j< suspectsN; j++) {
				criminalsToSuspect.add(rn.nextInt(N)); // przygotowujemy zestaw kryminalistow dopodejrzenia bez powtorzen
			}
			for(Integer j : criminalsToSuspect) { 
				policemen.get(i).addSuspect(criminals.get(j), rn.nextInt(10) + 1); // podejrzewamy (bez powtorzen)
			}
			pr.save(policemen.get(i));
		}
		

		
	}
	
	private Date getRandomDate() {
		GregorianCalendar gc = new GregorianCalendar();
		
        gc.set(gc.YEAR, 1980 + rn.nextInt(50));
        gc.set(gc.DAY_OF_YEAR, 1 + rn.nextInt(gc.getActualMaximum(gc.DAY_OF_YEAR)));
        
        return gc.getTime();
	}
}
