
public class Translator {
	// handles the translation of decimal64 to decimal notation
	
	String sign, combo, expCont, mantissa, significand, ePrime;
	boolean infinity, nan;
	Converter converter;
	int exponent;
	
	public Translator() {
		sign  = new String();
		combo = new String();
		expCont = new String();
		mantissa = new String();
		significand = new String();
		ePrime = new String();
		infinity = nan = false;
		converter = new Converter();
	}
	
	public String translate(String in) {
		// converts to binary if input is hex
		if(in.length() != 16 && in.length() != 64) {
			return null;
		}
		if(in.length() == 16) {
			in = converter.hexToBinary(in);
		}

		// splits the input into IEEE decimal64 format partitions
		if(in.charAt(0) == '1') {
			sign = "-";
		} else {
			sign = "+";
		}
		for(int i = 1; i < 6; i++) {
			combo = combo + in.charAt(i);
		}
		for(int i = 6; i < 14; i++) {
			expCont = expCont + in.charAt(i);
		}
		for(int i = 14; i < 64; i++) {
			mantissa = mantissa + in.charAt(i);
		}
		decodeComboAndExpCont();
		normalizeMantissa();
		if(infinity) {
			return sign + "infinity";
		} else if(nan) {
			return "NaN";
		}
		return sign + mantissa + "x10^" + exponent;
	}
	
	public void decodeComboAndExpCont() {
		// first, decodes combo bits to get significand and first two bits of e'
		// check first two bits of combo
		if(combo.charAt(0) == '1' && combo.charAt(1) == '1') {
			// first two bits are 11, first check special cases
			if(combo.charAt(2) == '1' && combo.charAt(3) == '1') {
				// special case
				switch(combo.charAt(4)) {
				case '0': infinity = true;
				case '1': nan = true;
				}
			} else {
				// not special case, significand is just 8 or 9 (1000 or 1001)
				significand = "100";
				// get first two bits of e'
				for(int i = 2; i < 4; i++) {
					ePrime = ePrime + combo.charAt(i);
				}
				// get last bit of significand
				significand = significand + combo.charAt(4);
			}
		} else {
			// first two bits are not 11, significand starts with 0
			significand = "0";
			// get first two bits of e'
			for(int i = 0; i < 2; i++) {
				ePrime = ePrime + combo.charAt(i);
			}
			// get last 3 bits of significand
			for(int i = 2; i < 5; i++) {
				significand = significand + combo.charAt(i);
			}
		}
		
		// second, combine first two bits of e' and expCont to get full e'
		ePrime = ePrime + expCont;
		// then calculate exponent using bias 398
		exponent = converter.binaryToDecimal(ePrime);
		exponent = exponent - 398;
	}
	
	public void normalizeMantissa() {
		// convert mantissa from DPD to packed BCD
		mantissa = converter.DPDToPackedBCD(mantissa);
		// append to significand
		mantissa = significand + mantissa;
		// convert to decimal
		mantissa = converter.packedBCDToDecimal(mantissa);
	}
}
