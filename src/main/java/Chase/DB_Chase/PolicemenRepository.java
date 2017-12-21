package Chase.DB_Chase;

import java.util.Map;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import Chase.DB_Chase.entity.Criminal;
import Chase.DB_Chase.entity.Policeman;

public interface PolicemenRepository extends Neo4jRepository<Policeman, Long>{
	
	Policeman findByName(String name); // wbudowane wyszukiwanie po atrybutach
	
	@Query("MATCH (p:Policeman)-[r]-(other) WHERE p.name = {name} RETURN r")
	Iterable<Map<String, Object>> getAllRelationships(@Param("name") String name);
	
	
	@Query("MATCH p=shortestPath( (p1:Policeman)-[*..6]-(c1:Criminal)) WHERE p1.name = {pname} AND c1.name = {cname} RETURN p")
	Iterable<Map<String, Object>> getPathToCriminal(@Param("pname") String pname, @Param("cname") String cname);
	
}
