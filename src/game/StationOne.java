package game;

import java.io.IOException;

public class StationOne extends TemplateStation {

	public StationOne(String stationName) throws IOException {
		super(stationName);
	}

	public static void main(String... args) throws IOException, InterruptedException {
		var st = new StationOne("StationOne");

		Thread.sleep(3000);

		st.authenticatePlayer("1");

	}

}
