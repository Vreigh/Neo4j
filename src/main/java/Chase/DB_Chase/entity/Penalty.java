package Chase.DB_Chase.entity;

import java.util.Date;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Penalty {
	@GraphId private Long id;
	
	private String description;
	private Date date;
	private int amount;
	private int penaltyId;
	
	@Relationship(type = "IMPOSED_BY", direction = Relationship.OUTGOING)
	private Policeman punisher;
	
	@Relationship(type = "IMPOSED_TO", direction = Relationship.OUTGOING)
	private Criminal punished;
	
	public Penalty() {}
	
	public Penalty(String description, Date date, int amount, Policeman punisher, Criminal punished) {
		this.description = description;
		this.date = date;
		this.amount = amount;
		this.punisher = punisher;
		this.punished = punished;
		this.penaltyId = 25 + (int)(Math.random() * 10000);
	}
	
	public int getPenaltyId() {
		return penaltyId;
	}

}
