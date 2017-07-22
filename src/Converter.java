
public class Converter {
	// handles the conversion of DPD->decimal, binary->decimal
	
	public int binaryToDecimal(String a) {
		int ans = 0; int add = 1;
		for(int i = 0; i < a.length(); i++) {
			if(a.charAt((a.length()-i-1)) == '1') {
				ans += add;
			}
			add = add * 2;
		}
		return ans;
	}
}
