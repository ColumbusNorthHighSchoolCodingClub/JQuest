package game;

import java.io.IOException;
import java.util.Scanner;

public class StationOne extends TemplateStation {

	public StationOne(String stationName) throws IOException {
		super(stationName);
	}

	public static void main(String... args) throws IOException, InterruptedException {
		var st = new StationOne("StationOne");

		Thread.sleep(3000);

		Scanner s = new Scanner(System.in);
		System.out.println("Please enter your player ID");
		st.authenticatePlayer(s.next());

	}

}
