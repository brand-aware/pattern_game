/**
 * @author mike802
 * 
 * product of - ???
 * 2017
 */
package core;

import java.net.URL;

public class Properties {
	
	private Screen screen;
	private String rootDir;
	private URL[] shapes;
	private URL background;
	private URL backgroundMatching;
	private URL removedImg;
	private URL title;
	private String highScores;
	private URL cover;
	private URL gameBackground;
	
	public Properties(String root){
		rootDir = root;
		
		shapes = new URL[]{
				getClass().getResource("/img/shape1.png"),
				getClass().getResource("/img/shape2.png"),
				getClass().getResource("/img/shape3.png"),
				getClass().getResource("/img/shape4.png"),
				getClass().getResource("/img/shape5.png"),
				getClass().getResource("/img/shape6.png")
		};
		
		background = getClass().getResource("/img/background.png");
		backgroundMatching = getClass().getResource("/img/background_matching.png");
		title = getClass().getResource("/img/logo.png");
			
		cover = getClass().getResource("/img/cover.png");
		gameBackground = getClass().getResource("/img/gameBackground.png");
		removedImg = getClass().getResource("/img/removed.png");
	}
	
	public String getRoot(){
		return rootDir;
	}
	public URL getShape(int index){
		return shapes[index];
	}
	public URL getGameBackground(){
		return gameBackground;
	}
	public URL getBackground(){
		return background;
	}
	public URL getBackgroundMatching(){
		return backgroundMatching;
	}
	public URL getRemovedImg() {
		return removedImg;
	}
	public URL getTitle(){
		return title;
	}
	public String getHighScores(){
		return highScores;
	}
	public URL getCover(){
		return cover;
	}
	public String getImageDir(){
		return "/img";
	}
	public void setScreen(Screen s){
		screen = s;
	}
	public Screen getScreen(){
		return screen;
	}
}
