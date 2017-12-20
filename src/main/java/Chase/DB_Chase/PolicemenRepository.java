package Chase.DB_Chase;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import Chase.DB_Chase.entity.Policeman;

public interface PolicemenRepository extends Neo4jRepository<Policeman, Long>{
	//@Query("MATCH (n:Policeman) WHERE n.name = {name} RETURN n")
	//Policeman findByName(@Param("name") String name);
	
	Policeman findByName(String name); // wbudowane wyszukiwanie po atrybutach
	
	@Query("MATCH (p:Policeman)-[r]-(other) WHERE p.id = {id} RETURN r")
	Iterable<Object> getAllRelationships(@Param("id") int id);
	
}
