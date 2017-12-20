package Chase.DB_Chase;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import Chase.DB_Chase.entity.Penalty;

public interface PenaltyRepository extends Neo4jRepository<Penalty, Long>{
	Penalty findByPenaltyId(int penaltyId);
}
