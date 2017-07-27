import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		Converter converter = new Converter();
		Translator translator = new Translator();
		System.out.println(translator.translate("2220000000000025"));
	}
}
