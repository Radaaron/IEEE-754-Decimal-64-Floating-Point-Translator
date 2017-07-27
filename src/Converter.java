
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
		case 'a': return "1010";
		case 'b': return "1011";
		case 'c': return "1100";
		case 'd': return "1101";
		case 'e': return "1110";
		case 'f': return "1111";
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
	
	public String DPDToPackedBCD(String in) {
		// a = [0123456789]
		int offset = 0;
		String ans = "", a, per10;
		for(int i = 0; i < (in.length() / 10); i++) {
			// partitions in string to 10 bits
			a = "";
			for(int j = offset; j < (offset + 10); j++) {
				a = a + in.charAt(j);
			}
			
			per10 = "abcdefghijkm"; // reset
			// first, copy lsb of each nibble to ans
			per10 = per10.replace('d', a.charAt(2));
			per10 = per10.replace('h', a.charAt(5));
			per10 = per10.replace('m', a.charAt(9));
			
			// check the set bit for any major nibbles
			if(a.charAt(6) == '1') {
				// check if there are two or more major nibbles
				if(a.charAt(7) == '1' && a.charAt(8) == '1') {
					// two or more major nibbles, get location of minor nibble
					switch(String.valueOf(a.charAt(3)) + String.valueOf(a.charAt(4))){
					case "00":{
						// minor nibble is first nibble
						per10 = per10.replace('a', '1');
						per10 = per10.replace('b', '0');
						per10 = per10.replace('c', '0');
						per10 = per10.replace('e', '1');
						per10 = per10.replace('f', '0');
						per10 = per10.replace('g', '0');
						per10 = per10.replace('i', '0');
						per10 = per10.replace('j', a.charAt(0));
						per10 = per10.replace('k', a.charAt(1));
						
					}
					case "01":{
						// minor nibble is second nibble
						per10 = per10.replace('a', '1');
						per10 = per10.replace('b', '0');
						per10 = per10.replace('c', '0');
						per10 = per10.replace('e', '0');
						per10 = per10.replace('f', a.charAt(0));
						per10 = per10.replace('g', a.charAt(1));
						per10 = per10.replace('i', '1');
						per10 = per10.replace('j', '0');
						per10 = per10.replace('k', '0');
					}
					case "10":{
						// minor nibble is third nibble
						per10 = per10.replace('a', '0');
						per10 = per10.replace('b', a.charAt(0));
						per10 = per10.replace('c', a.charAt(1));
						per10 = per10.replace('e', '1');
						per10 = per10.replace('f', '0');
						per10 = per10.replace('g', '0');
						per10 = per10.replace('i', '1');
						per10 = per10.replace('j', '0');
						per10 = per10.replace('k', '0');
					}
					case "11":{
						// all major
						per10 = per10.replace('a', '1');
						per10 = per10.replace('b', '0');
						per10 = per10.replace('c', '0');
						per10 = per10.replace('e', '1');
						per10 = per10.replace('f', '0');
						per10 = per10.replace('g', '0');
						per10 = per10.replace('i', '1');
						per10 = per10.replace('j', '0');
						per10 = per10.replace('k', '0');
					}
					}
				} else {
					// only one major nibble, get location of major nibble
					switch(String.valueOf(a.charAt(7)) + String.valueOf(a.charAt(8))){
					case "00":{
						// major nibble is first nibble
						per10 = per10.replace('a', '0');
						per10 = per10.replace('b', a.charAt(0));
						per10 = per10.replace('c', a.charAt(1));
						per10 = per10.replace('e', '0');
						per10 = per10.replace('f', a.charAt(3));
						per10 = per10.replace('g', a.charAt(4));
						per10 = per10.replace('i', '1');
						per10 = per10.replace('j', '0');
						per10 = per10.replace('k', '0');
					}
					case "01":{
						// major nibble is second nibble
						per10 = per10.replace('a', '0');
						per10 = per10.replace('b', a.charAt(0));
						per10 = per10.replace('c', a.charAt(1));
						per10 = per10.replace('e', '1');
						per10 = per10.replace('f', '0');
						per10 = per10.replace('g', '0');
						per10 = per10.replace('i', '0');
						per10 = per10.replace('j', a.charAt(3));
						per10 = per10.replace('k', a.charAt(4));
					}
					case "10":{
						// major nibble is third nibble
						per10 = per10.replace('a', '1');
						per10 = per10.replace('b', '0');
						per10 = per10.replace('c', '0');
						per10 = per10.replace('e', '0');
						per10 = per10.replace('f', a.charAt(3));
						per10 = per10.replace('g', a.charAt(4));
						per10 = per10.replace('i', '0');
						per10 = per10.replace('j', a.charAt(0));
						per10 = per10.replace('k', a.charAt(1));
					}
					}
				}
			} else {
				// no major nibbles, copy all
				per10 = per10.replace('a', '0');
				per10 = per10.replace('b', a.charAt(0));
				per10 = per10.replace('c', a.charAt(1));
				per10 = per10.replace('e', '0');
				per10 = per10.replace('f', a.charAt(3));
				per10 = per10.replace('g', a.charAt(4));
				per10 = per10.replace('i', '0');
				per10 = per10.replace('j', a.charAt(7));
				per10 = per10.replace('k', a.charAt(8));
			}
			ans = ans + per10;
			offset += 10;
		}
		return ans;
	}
	
	public String packedBCDToDecimal(String a) {
		// check if it is packed BCD
		if(a.length() % 4 != 0) {
			return null;
		}
		int offset = 0;
		String ans = "", temp = "";
		for(int i = 0; i < (a.length() / 4); i++) {
			// per 4 bits
			temp = (String.valueOf(a.charAt(offset)) + String.valueOf(a.charAt(offset + 1)) + String.valueOf(a.charAt(offset + 2)) + String.valueOf(a.charAt(offset + 3)));
			ans = ans.concat(Integer.toString(this.binaryToDecimal(temp)));
			offset += 4;
		}
		return ans;
	}
}
