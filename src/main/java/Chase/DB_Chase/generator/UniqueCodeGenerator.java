package Chase.DB_Chase.generator;

import java.util.HashSet;
import java.util.Random;

public class UniqueCodeGenerator {
	private HashSet<Integer> set = new HashSet<>();
	private int bot;
	private int top;
	private Random rn = new Random(System.currentTimeMillis());
	
	public UniqueCodeGenerator(int bot, int top) {
		this.bot = bot;
		this.top = top;
	}
	
	public int get() {
		while(true) {
			int ran = rn.nextInt(top - bot) + bot;
			if(!set.contains(ran)) {
				set.add(ran);
				return ran;
			}
		}
	}
	

}
