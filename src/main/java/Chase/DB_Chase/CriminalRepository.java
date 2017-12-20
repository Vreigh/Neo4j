package Chase.DB_Chase;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import Chase.DB_Chase.entity.Criminal;

public interface CriminalRepository extends Neo4jRepository<Criminal, Long>{
	Criminal findByName(String name);
}
