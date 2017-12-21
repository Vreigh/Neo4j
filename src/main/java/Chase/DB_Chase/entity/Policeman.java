package Chase.DB_Chase.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.*;

import Chase.DB_Chase.relationship.Suspects;

@NodeEntity
public class Policeman{
	@GraphId private Long id;
	
	private String name;
	private int badgeId;
	
	@Relationship(type = "FRIENDS", direction = Relationship.UNDIRECTED)
	private Set<Policeman> friends = new HashSet<Policeman>();
	
	@Relationship(type = "SUSPECTS", direction = Relationship.OUTGOING)
	private Set<Suspects> suspects = new HashSet<Suspects>();
	
	@Relationship(type = "IMPOSED_BY", direction = Relationship.INCOMING)
	private Set<Penalty> penalties = new HashSet<Penalty>();
	
	public Policeman(String name, int badgeId) {
		this.name = name;
		this.badgeId = badgeId;
	}
	
	public Policeman() {}
	
	public String getName() {
		return name;
	}
	
	public void addFriend(Policeman other) {
		friends.add(other);
	}
	
	public Suspects addSuspect(Criminal c, int scale) {
		Suspects suspect = new Suspects(this, c, scale);
		suspects.add(suspect);
		return suspect;
	}
}
