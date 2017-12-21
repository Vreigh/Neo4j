package Chase.DB_Chase;

import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import Chase.DB_Chase.entity.Criminal;
import Chase.DB_Chase.entity.Penalty;
import Chase.DB_Chase.entity.Policeman;
import Chase.DB_Chase.generator.DBGenerator;

@SpringBootApplication
@EnableNeo4jRepositories
public class Application 
{
	private final static Logger log = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) throws Exception {
		
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	CommandLineRunner demo(PolicemenRepository pr, CriminalRepository cr, PenaltyRepository penr) {
		return args -> {
			// Generator
			
			/*DBGenerator dbGenerator = new DBGenerator();
			dbGenerator.generate(pr, cr, penr);
			
			Policeman p1 = new Policeman("Janek Taranek", 1234);
			Policeman p2 = new Policeman("Damian Dąb", 4321);
			
			p1.addFriend(p2);
			
			pr.save(p1);
			pr.save(p2);
			
			ok
			*/
			
			// Wbudowane metody repozytoriów
			Policeman p3 = pr.findByName("Jerzy Pomsta");
			System.out.println(p3.getName());
			
			Criminal c = cr.findByName("Jan Przekręt");
			System.out.println(c.getName());
			
			Penalty pen = penr.findByPenaltyId(6836);
			System.out.println(pen.getPenaltyId());
			
			// ok
			
			// Pobieranie wszystkich relacji
			Iterable<Map<String, Object>> relations = pr.getAllRelationships("Jerzy Pomsta");
			Iterator itr1 = relations.iterator();
			System.out.println("\n \n \n \n");
			while(itr1.hasNext()) {
				Map<String, Object> r = (Map<String, Object>) itr1.next();
				System.out.println(r.toString());
			}
			
			
			// Przykładowe wyszukiwanie ścieżek
			System.out.println("\n \n \n \n");
			Object path1 = pr.getPathToCriminal("Jerzy Pomsta", "Jan Przekręt");
			System.out.println("Path1: \n" + path1.toString());
			System.out.println("\n \n \n \n");
			
			System.out.println("\n \n \n \n");
			Object path2 = penr.getPathBetweenPenalties(7085, 9332);
			System.out.println("Path2: \n" + path2.toString());
			System.out.println("\n \n \n \n");
			
			// ok
			
		};
	}

}
