package Chase.DB_Chase;

import java.util.Map;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import Chase.DB_Chase.entity.Penalty;

public interface PenaltyRepository extends Neo4jRepository<Penalty, Long>{
	Penalty findByPenaltyId(int penaltyId);
	
	@Query("MATCH p=shortestPath( (p1:Penalty)-[*..6]-(p2:Penalty)) WHERE p1.penaltyId = {id1} AND p2.penaltyId = {id2} RETURN p")
	Iterable<Map<String, Object>> getPathBetweenPenalties(@Param("id1") int id1, @Param("id2") int id2);
}
