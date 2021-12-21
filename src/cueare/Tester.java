package cueare;

import java.awt.Rectangle;

import javax.swing.UIManager;

public class Tester {

	public static void main(String[] args) throws Exception {
		try {

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	MappingUtil u = new MappingUtil();

//	var b = u.encode("abcdefg");
//
//	for(var i : b) {
//		System.out.println(Arrays.toString(i));
//	}
//	
	
	u.encodeAndDisplay();
	

	
	Rectangle r = new Rectangle();
	
	
	}

}
