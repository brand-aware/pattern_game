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
	private String[] shapes;
	private String background;
	private String backgroundMatching;
	private String removedImg;
	private String title;
	private String highScores;
	private String cover;
	private String gameBackground;
	
	public Properties(){
		
		shapes = new String[]{
				"shape1.png",
				"shape2.png",
				"shape3.png",
				"shape4.png",
				"shape5.png",
				"shape6.png"
		};
		
		background = "background.png";
		backgroundMatching = "background_matching.png";
		title = "logo.png";
		highScores = "high_scores";
		cover = "cover.png";
		gameBackground = "gameBackground.png";
		removedImg = "removed.png";
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
	public void setScreen(Screen s){
		screen = s;
	}
	public Screen getScreen(){
		return screen;
	}
}
