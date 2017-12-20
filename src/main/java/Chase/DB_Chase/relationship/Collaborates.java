package Chase.DB_Chase.relationship;

import java.util.Date;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import Chase.DB_Chase.entity.Criminal;

@RelationshipEntity(type="COLLABORATED_WITH")
public class Collaborates {
	@GraphId private Long id;
	
	@StartNode private Criminal c1;
	@EndNode private Criminal c2;
	
	private boolean knowsCrimes;
	private boolean knowsWhereabouts;
	private Date lastHeard;
	
	public Collaborates() {}
	
	public Collaborates(Criminal c1, Criminal c2, boolean kc, boolean kw, Date lastHeard) {
		this.c1 = c1;
		this.c2 = c2;
		knowsCrimes = kc;
		knowsWhereabouts = kw;
		this.lastHeard = lastHeard;
	}
}
