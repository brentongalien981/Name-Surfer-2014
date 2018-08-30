import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class NameSurferEntryPrototype {
	private String nameEntry;
	private String name;
	private int decadeRanks[];
	
	public NameSurferEntryPrototype(String line) {
		nameEntry = line;
		decadeRanks = new int[11];
		setDecadeRanks();
		
	}
	
	private void setDecadeRanks() {
		int nameEnd = nameEntry.indexOf(" ") + 1;
		String rankLine = nameEntry.substring(nameEnd);
		
		StringTokenizer st = new StringTokenizer(rankLine);
		for(int x=0; st.hasMoreTokens(); x++) {
			decadeRanks[x] = Integer.parseInt(st.nextToken());
		}		
	}
	
	public String getName() {
		int nameEnd = nameEntry.indexOf(" ");
		return nameEntry.substring(0, nameEnd);
	}
	
	public int[] getRank() {
		return decadeRanks;
	}
	
	public String toString() {
		return nameEntry;
	}
}
