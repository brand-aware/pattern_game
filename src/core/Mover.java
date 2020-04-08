/**
 * @author mike802
 * 
 * product of - ???
 * 2017
 */
package core;

import java.io.IOException;

import core.Properties;

public class Mover implements Runnable{
	
	Properties properties;
	
	public Mover(Properties prop){
		properties = prop;
	}

	@Override
	public void run() {
		while(true){
			
			try {
				properties.getScreen().doMove();
			} catch (NumberFormatException | IOException e1) {
				e1.printStackTrace();
			}
			
			try {
				Thread.sleep(73);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
