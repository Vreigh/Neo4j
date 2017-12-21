package Chase.DB_Chase.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import Chase.DB_Chase.relationship.Collaborates;

@NodeEntity
public class Criminal{
	@GraphId private Long id;
	
	private String name;
	private String codeName;
	
	@Relationship(type = "COLLABORATED_WITH", direction = Relationship.OUTGOING)
	private Set<Collaborates> collaborators = new HashSet<Collaborates>();
	
	@Relationship(type = "IMPOSED_TO", direction = Relationship.INCOMING)
	private Set<Penalty> penalties = new HashSet<Penalty>();
	
	
	public Criminal() {
		// wymagany pusty konstruktor
	}
	
	public Criminal(String name, String codeName) {
		this.name = name;
		this.codeName = codeName;
	}
	
	public Collaborates addCollaborator(Criminal other, boolean kc, boolean kw, Date lastHeard) {
		Collaborates collaboration = new Collaborates(this, other, kc, kw, lastHeard);
		collaborators.add(collaboration);
		return collaboration;
	}
	
	public String getName() {
		return name;
	}
}
