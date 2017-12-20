package Chase.DB_Chase.relationship;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import Chase.DB_Chase.entity.Criminal;
import Chase.DB_Chase.entity.Policeman;

@RelationshipEntity(type="SUSPECTS")
public class Suspects {
	@GraphId private Long id;
	
	@StartNode private  Policeman policeman;
	@EndNode private Criminal criminal;
	
	private int scale;
	
	public Suspects() {}
	
	public Suspects(Policeman policeman, Criminal criminal, int scale) {
		this.policeman = policeman;
		this.criminal = criminal;
		this.scale = scale;
	}
	
	@Override 
	public boolean equals(Object o){
	    Suspects other = (Suspects) o;
	    return ((other.policeman == policeman) && (other.criminal == criminal) && (other.scale == scale));
	}
	
	@Override
    public int hashCode() {
        int result = 10;
        result = 31 * result + policeman.hashCode();
        result = 31 * result + criminal.hashCode();
        result += scale;
        return result;
    }

}
