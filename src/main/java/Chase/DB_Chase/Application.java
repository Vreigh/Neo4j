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
			/*DBGenerator dbGenerator = new DBGenerator();
			dbGenerator.generate(pr, cr, penr);
			
			Policeman p1 = new Policeman("Janek Taranek", 1234);
			Policeman p2 = new Policeman("Damian Dąb", 4321);
			
			p1.addFriend(p2);
			
			pr.save(p1);
			pr.save(p2);
			
			*/
			
			Policeman p3 = pr.findByName("Jerzy Pomsta");
			Criminal c = cr.findByName("Jan Przekręt"); // działa
			
			Iterable<Map<String, Object>> relations = pr.getAllRelationships("Jerzy Pomsta");
			Iterator itr1 = relations.iterator();
			System.out.println("\n \n \n \n \n \n \n");
			while(itr1.hasNext()) {
				Map<String, Object> r = (Map<String, Object>) itr1.next();
				System.out.println(r.toString());
			}
			System.out.println("\n \n \n \n \n \n \n");
			
			
			Iterable<Map<String, Object>> path = pr.getPathToCriminal("Jerzy Pomsta", "Jan Przekręt");
			Iterator itr = path.iterator();
			System.out.println("\n \n \n \n \n \n \n");
			while(itr.hasNext()) {
				Map<String, Object> r = (Map<String, Object>) itr.next();
				System.out.println(r.toString());
			}
			System.out.println("\n \n \n \n \n \n \n");
			
			
			
		};
	}

}
