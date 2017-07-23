
public class Driver {

	public static void main(String[] args) {
		Converter converter = new Converter();
		System.out.println(converter.packedBCDToDecimal(converter.DPDToPackedBCD("1011011110")));
	}
}
