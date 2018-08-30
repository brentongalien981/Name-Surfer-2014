import java.util.StringTokenizer;

public class TokenizerSample {
	public static void main(String args[]) {
		String s = "i will continue the legacy of the STEM people";
		StringTokenizer st = new StringTokenizer(s);
		
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
	}
}
