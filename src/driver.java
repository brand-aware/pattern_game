/**
 * @author mike802
 * 
 * product of - ???
 * 2017
 */
import com.pattern_game.core.Mover;
import com.pattern_game.core.Properties;
import com.pattern_game.core.Screen;

public class driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String currentDir = System.getProperty("user.dir");
		Properties properties = new Properties(currentDir);
		Screen screen = new Screen();
		screen.init(properties);
		properties.setScreen(screen);
		Mover mover = new Mover(properties);
		Thread thread = new Thread(mover);
		thread.start();
	}
}
