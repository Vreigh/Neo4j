package Chase.DB_Chase;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

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
			pr.save(p2);*/
			
			Iterable<Object> relations = pr.getAllRelationships(310);
			if(relations != null) {
				System.out.println("Znaleziono relacje");
				Iterator itr = relations.iterator();
				while(itr.hasNext()) {
					Object relation = itr.next();
					System.out.println("Jest relacja!");
					System.out.println(relation.toString());
				}
			}else System.out.println("Nie ma relacji!");
			
		};
	}

}
