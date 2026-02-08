/**
 * @author mike802
 * 
 * product of - ???
 * 2017
 */
package core;

import java.io.File;

public class Properties {
	
	private Screen screen;
	private String rootDir;
	private String[] shapes;
	private String background;
	private String backgroundMatching;
	private String removedImg;
	private String title;
	private String highScores;
	private String cover;
	private String gameBackground;
	
	public Properties(String root){
		rootDir = root;
		
		shapes = new String[]{
				rootDir + File.separator + "img" + File.separator + "shape1.png",
				rootDir + File.separator + "img" + File.separator + "shape2.png",
				rootDir + File.separator + "img" + File.separator + "shape3.png",
				rootDir + File.separator + "img" + File.separator + "shape4.png",
				rootDir + File.separator + "img" + File.separator + "shape5.png",
				rootDir + File.separator + "img" + File.separator + "shape6.png"
		};
		
		background = rootDir + File.separator + "img" + File.separator + "background.png";
		backgroundMatching = rootDir + File.separator + "img" + File.separator + "background_matching.png";
		title = rootDir + File.separator + "img" + File.separator + "logo.png";
		highScores = rootDir + File.separator + "high_scores";
		cover = rootDir + File.separator + "img" + File.separator + "cover.png";
		gameBackground = rootDir + File.separator + "img" + File.separator + "gameBackground.png";
		removedImg = rootDir + File.separator + "img" + File.separator + "removed.png";
	}
	
	public String getRoot(){
		return rootDir;
	}
	public String getShape(int index){
		return shapes[index];
	}
	public String getGameBackground(){
		return gameBackground;
	}
	public String getBackground(){
		return background;
	}
	public String getBackgroundMatching(){
		return backgroundMatching;
	}
	public String getRemovedImg() {
		return removedImg;
	}
	public String getTitle(){
		return title;
	}
	public String getHighScores(){
		return highScores;
	}
	public String getCover(){
		return cover;
	}
	public String getImageDir(){
		return rootDir + File.separator + "img";
	}
	public void setScreen(Screen s){
		screen = s;
	}
	public Screen getScreen(){
		return screen;
	}
}
