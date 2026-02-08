/**
 * @author mike802
 * 
 * product of - ???
 * 2017
 */
import core.Mover;
import core.Properties;
import core.Screen;

public class driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length != 2){
			System.out.println("USEAGE: java driver <current dir> <user dir>");
			System.exit(0);
		}
		Properties properties = new Properties(args[0]);
		Screen screen = new Screen(args[1]);
		screen.init(properties);
		properties.setScreen(screen);
		Mover mover = new Mover(properties);
		Thread thread = new Thread(mover);
		thread.start();
	}
}
