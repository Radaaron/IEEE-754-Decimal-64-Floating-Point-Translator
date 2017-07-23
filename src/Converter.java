
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
	
	public String hexDigitToBinary(char ch) {
		switch(ch) {
		case '0': return "0000";
		case '1': return "0001";
		case '2': return "0010";
		case '3': return "0011";
		case '4': return "0100";
		case '5': return "0101";
		case '6': return "0110";
		case '7': return "0111";
		case '8': return "1000";
		case '9': return "1001";
		case 'A': return "1010";
		case 'B': return "1011";
		case 'C': return "1100";
		case 'D': return "1101";
		case 'E': return "1110";
		case 'F': return "1111";
		default: return null;
		}
	}
	
	public String hexToBinary(String a) {
		String temp = "";
		for(int i = 0; i < a.length(); i++) {
			temp = temp.concat(this.hexDigitToBinary(a.charAt(i)));
		}
		return temp;
	}
}
