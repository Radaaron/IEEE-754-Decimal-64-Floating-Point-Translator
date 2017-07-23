
public class Converter {
	// handles the conversion of binary->decimal, hex->binary, DPD->PackedBCD, PackedBCD->decimal
	
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
	
	public String DPDToPackedBCD(String a) {
		// a = [0123456789]
		String ans = "abcdefghijkm"; // abcd efgh ijkm
		// first, copy lsb of each nibble to ans
		ans = ans.replace('d', a.charAt(2));
		ans = ans.replace('h', a.charAt(5));
		ans = ans.replace('m', a.charAt(9));
		
		// check the set bit for any major nibbles
		if(a.charAt(6) == '1') {
			// check if there are two or more major nibbles
			if(a.charAt(7) == '1' && a.charAt(8) == '1') {
				// two or more major nibbles, get location of minor nibble
				switch(String.valueOf(a.charAt(3)) + String.valueOf(a.charAt(4))){
				case "00":{
					// minor nibble is first nibble
					ans = ans.replace('a', '1');
					ans = ans.replace('b', '0');
					ans = ans.replace('c', '0');
					ans = ans.replace('e', '1');
					ans = ans.replace('f', '0');
					ans = ans.replace('g', '0');
					ans = ans.replace('i', '0');
					ans = ans.replace('j', a.charAt(0));
					ans = ans.replace('k', a.charAt(1));
					return ans;
					
				}
				case "01":{
					// minor nibble is second nibble
					ans = ans.replace('a', '1');
					ans = ans.replace('b', '0');
					ans = ans.replace('c', '0');
					ans = ans.replace('e', '0');
					ans = ans.replace('f', a.charAt(0));
					ans = ans.replace('g', a.charAt(1));
					ans = ans.replace('i', '1');
					ans = ans.replace('j', '0');
					ans = ans.replace('k', '0');
					return ans;
				}
				case "10":{
					// minor nibble is third nibble
					ans = ans.replace('a', '0');
					ans = ans.replace('b', a.charAt(0));
					ans = ans.replace('c', a.charAt(1));
					ans = ans.replace('e', '1');
					ans = ans.replace('f', '0');
					ans = ans.replace('g', '0');
					ans = ans.replace('i', '1');
					ans = ans.replace('j', '0');
					ans = ans.replace('k', '0');
					return ans;
				}
				case "11":{
					// all major
					ans = ans.replace('a', '1');
					ans = ans.replace('b', '0');
					ans = ans.replace('c', '0');
					ans = ans.replace('e', '1');
					ans = ans.replace('f', '0');
					ans = ans.replace('g', '0');
					ans = ans.replace('i', '1');
					ans = ans.replace('j', '0');
					ans = ans.replace('k', '0');
					return ans;
				}
				default: {
					System.out.println("TWO OR MORE MAJOR NIBBLE ERROR");
					return null;
				}
				}
			} else {
				// only one major nibble, get location of major nibble
				switch(String.valueOf(a.charAt(7)) + String.valueOf(a.charAt(8))){
				case "00":{
					// major nibble is first nibble
					ans = ans.replace('a', '0');
					ans = ans.replace('b', a.charAt(0));
					ans = ans.replace('c', a.charAt(1));
					ans = ans.replace('e', '0');
					ans = ans.replace('f', a.charAt(3));
					ans = ans.replace('g', a.charAt(4));
					ans = ans.replace('i', '1');
					ans = ans.replace('j', '0');
					ans = ans.replace('k', '0');
					return ans;
				}
				case "01":{
					// major nibble is second nibble
					ans = ans.replace('a', '0');
					ans = ans.replace('b', a.charAt(0));
					ans = ans.replace('c', a.charAt(1));
					ans = ans.replace('e', '1');
					ans = ans.replace('f', '0');
					ans = ans.replace('g', '0');
					ans = ans.replace('i', '0');
					ans = ans.replace('j', a.charAt(3));
					ans = ans.replace('k', a.charAt(4));
					return ans;
				}
				case "10":{
					// major nibble is third nibble
					ans = ans.replace('a', '1');
					ans = ans.replace('b', '0');
					ans = ans.replace('c', '0');
					ans = ans.replace('e', '0');
					ans = ans.replace('f', a.charAt(3));
					ans = ans.replace('g', a.charAt(4));
					ans = ans.replace('i', '0');
					ans = ans.replace('j', a.charAt(0));
					ans = ans.replace('k', a.charAt(1));
					return ans;
				}
				default: {
					System.out.println("ONLY ONE MAJOR NIBBLE ERROR");
					return null;
				}
				}
			}
		} else {
			// no major nibbles, copy all
			ans = ans.replace('a', '0');
			ans = ans.replace('b', a.charAt(0));
			ans = ans.replace('c', a.charAt(1));
			ans = ans.replace('e', '0');
			ans = ans.replace('f', a.charAt(3));
			ans = ans.replace('g', a.charAt(4));
			ans = ans.replace('i', '0');
			ans = ans.replace('j', a.charAt(6));
			ans = ans.replace('k', a.charAt(7));
			return ans;
		}
	}
	
	public String packedBCDToDecimal(String a) {
		int offset = 0;
		String ans = "", temp = "";
		for(int i = 0; i < 3; i++) {
			temp = (String.valueOf(a.charAt(offset)) + String.valueOf(a.charAt(offset + 1)) + String.valueOf(a.charAt(offset + 2)) + String.valueOf(a.charAt(offset + 3)));
			ans = ans.concat(Integer.toString(this.binaryToDecimal(temp)));
			offset += 4;
		}
		return ans;
	}
}
