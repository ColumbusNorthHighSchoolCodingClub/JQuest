package game;

import java.io.IOException;

public class StationOne extends TemplateStation {

	public StationOne(String stationName) throws IOException {
		super(stationName);
	}

	public static void main(String...args) throws IOException {
		var st = new StationOne("StationOne");

	}

}
