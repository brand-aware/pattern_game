/**
 * @author mike802
 * 
 * product of - ???
 * 2017
 */
package core;

public class ConfigScreen {
	
	//gameplay settings
	protected int roundLength = 60;
	protected int timeBonus = 45;
	protected int levelDecay = 3;
	protected int levelMin = 5;
	protected int scoreInterval = 60;
	protected int goalInterval = 5;
	protected int penalty = 5;
	
	//animation settings
	protected boolean started = false;
	protected boolean remove = false;
	protected boolean replace = false;
	protected boolean animationRunning = false;
	protected int removeCounter = -1;
	protected int replaceCounter = -1;
	protected int shiftCounter = 0;
	protected int REMOVE_LOOP = 1;
	protected int REPLACE_LOOP = 7;
	protected boolean successfulIteration = false;
	protected String OUTPUT_FORMAT = ".##";
	
	//display settings
	protected final String HEADER = "pattern_game";
	
	protected int totalHeight = 0;
	protected int titleOffset = 0;
	
	protected int titleX = 0;
	
	protected int startX = 0;
	protected int startY = 0;
	
	protected int stopX = 0;
	protected int stopY = 0;
	
	protected int helpX = 0;
	protected int helpY = 0;
	
	protected int levelDisplayX = 0;
	protected int levelDisplayY = 0;
	protected int levelLabelX = 0;
	protected int levelLabelY = 0;
	
	protected int scoreDisplayX = 0;
	protected int scoreDisplayY = 0;
	protected int scoreLabelX = 0;
	protected int scoreLabelY = 0;
	
	protected int timeDisplayX = 0;
	protected int timeDisplayY = 0;
	protected int timeLabelX = 0;
	protected int timeLabelY = 0;
	
	
	protected final int GAME_WIDTH = 599;
	protected final int GAME_HEIGHT = 509;
	
	protected final int TITLE_WIDTH = 387;
	protected final int TITLE_HEIGHT = 87;
	
	protected final int START_BUTTON_WIDTH = 100;
	protected final int START_BUTTON_HEIGHT = 30;
	
	protected final int STOP_BUTTON_WIDTH = 100;
	protected final int STOP_BUTTON_HEIGHT = 30;
	
	protected final int HELP_BUTTON_WIDTH = 100;
	protected final int HELP_BUTTON_HEIGHT = 30;
	
	protected final int LEVEL_DISPLAY_WIDTH = 60;
	protected final int LEVEL_DISPLAY_HEIGHT = 30;
	protected final int LEVEL_LABEL_WIDTH = 60;
	protected final int LEVEL_LABEL_HEIGHT = 30;
	
	protected final int SCORE_DISPLAY_WIDTH = 60;
	protected final int SCORE_DISPLAY_HEIGHT = 30;
	protected final int SCORE_LABEL_WIDTH = 60;
	protected final int SCORE_LABEL_HEIGHT = 30;
	
	protected final int TIME_DISPLAY_WIDTH = 60;
	protected final int TIME_DISPLAY_HEIGHT = 30;
	protected final int TIME_LABEL_WIDTH = 60;
	protected final int TIME_LABEL_HEIGHT = 30;
	
	protected final int WIDTH = 10;
	protected final int HEIGHT = 10;
	
	protected final int BACKGROUND_HEIGHT = 50;
	protected final int BACKGROUND_WIDTH = 56;
	
	protected final int BUTTON_HEIGHT = 42;
	protected final int BUTTON_WIDTH = 47;
	
	
	protected final int CONNECTED_MIN = 3;
	protected final int UNIQUE_BLOCKS = 6;
	
	public ConfigScreen(){
		totalHeight = GAME_HEIGHT + TITLE_HEIGHT 
				+ HELP_BUTTON_HEIGHT + TIME_DISPLAY_HEIGHT + 10 + 10;
		
		titleX = (GAME_WIDTH / 2) - (TITLE_WIDTH / 2);
		
		int buttonsX = (START_BUTTON_WIDTH + STOP_BUTTON_WIDTH 
				+ HELP_BUTTON_WIDTH + 15) / 2;
		int buttonsY = TITLE_HEIGHT + 15;
		
		startX = GAME_WIDTH / 2 - buttonsX;
		startY = buttonsY;
		stopX = startX + START_BUTTON_WIDTH + 5;
		stopY = buttonsY;
		helpX = stopX + STOP_BUTTON_WIDTH + 5;
		helpY = buttonsY;
		
		int displayX = (LEVEL_DISPLAY_WIDTH + SCORE_DISPLAY_WIDTH 
				+ TIME_DISPLAY_WIDTH + LEVEL_LABEL_WIDTH 
				+ SCORE_LABEL_WIDTH + TIME_LABEL_WIDTH + 30) / 2;
		int displayY = TITLE_HEIGHT + HELP_BUTTON_HEIGHT + 30;
		
		levelLabelX  = GAME_WIDTH / 2 - displayX;
		levelLabelY = displayY;
		levelDisplayX = levelLabelX + LEVEL_LABEL_WIDTH + 5;
		levelDisplayY = displayY;
		scoreLabelX = levelDisplayX + LEVEL_DISPLAY_WIDTH + 5;
		scoreLabelY = displayY;
		scoreDisplayX = scoreLabelX + SCORE_LABEL_WIDTH + 5;
		scoreDisplayY = displayY;
		timeLabelX = scoreDisplayX + SCORE_DISPLAY_WIDTH + 5;
		timeLabelY = displayY;
		timeDisplayX = timeLabelX + TIME_LABEL_WIDTH + 5;
		timeDisplayY = displayY;
		
		titleOffset = displayY + TIME_DISPLAY_HEIGHT + 15;
	}
	
}
