/**
 * @author mike802
 * 
 * product of - ???
 * brand-aware
 * 2017
 */
package core;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import balogging.BALoggerUtil;
import highscores.HighScores;
import highscores.IBoardOutline;
import highscores.NameInput;
import highscores.hsProperties;
import utils.Tools;
import utils.UtilsScreenRoot;

public class Screen extends UtilsScreenRoot implements IBoardOutline{
	
	boolean linearRemove = true;
	boolean stopFlag = false;
	
	private final String PRODUCT_NAME = "pattern_game";
	private String userDir;
	
	public Screen(String usrDir){
		userDir = usrDir;
		output = new DecimalFormat(OUTPUT_FORMAT);
	}
	
	private void create(){
		screenPage = new JFrame(HEADER);
		screenPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String imageDir = properties.getImageDir();
		Image imageIcon = Toolkit.getDefaultToolkit().getImage(imageDir + File.separator + "company.png");
		screenPage.setIconImage(imageIcon);
		screenPage.setResizable(false);
		screenPage.setPreferredSize(new Dimension(GAME_WIDTH + 5, totalHeight + 64));
		screenPage.setLocation(150, 50);
		utils = new Tools();
		
		handler = new ButtonHandlerScreen(this, utils);
		gameArea = new JDesktopPane();
		gameArea.setBounds(0, 0, GAME_WIDTH, totalHeight);
		
		JLabel backgroundImage = new JLabel();
		backgroundImage.setIcon(new ImageIcon(properties.getGameBackground()));
		backgroundImage.setBounds(0, 0, 850, 900);
		
		
		JLabel title = new JLabel();
		ImageIcon logo = new ImageIcon(properties.getTitle());
		title.setIcon(logo);
		title.setBounds(titleX, 10, TITLE_WIDTH, TITLE_HEIGHT);
		
		start = new JButton("start");
		start.setBounds(startX, startY, START_BUTTON_WIDTH, START_BUTTON_HEIGHT);
		start.addActionListener(handler);
		
		stop = new JButton("stop");
		stop.setBounds(stopX, stopY, STOP_BUTTON_WIDTH, STOP_BUTTON_HEIGHT);
		stop.addActionListener(handler);
		
		help = new JButton("help");
		help.setBounds(helpX, helpY, HELP_BUTTON_WIDTH, HELP_BUTTON_HEIGHT);
		help.addActionListener(handler);
		help.setEnabled(false);
		
		JLabel levelLabel = new JLabel("level");
		levelLabel.setBounds(levelLabelX, levelLabelY, LEVEL_LABEL_WIDTH, LEVEL_LABEL_HEIGHT);
		JLabel scoreLabel = new JLabel("score");
		scoreLabel.setBounds(scoreLabelX, scoreLabelY, SCORE_LABEL_WIDTH, SCORE_LABEL_HEIGHT);
		JLabel timeLabel = new JLabel("time");
		timeLabel.setBounds(timeLabelX, timeLabelY, TIME_LABEL_WIDTH, TIME_LABEL_HEIGHT);
		
		level = new JTextField("1");
		level.setEditable(false);
		level.setBounds(levelDisplayX, levelDisplayY, LEVEL_DISPLAY_WIDTH, LEVEL_DISPLAY_HEIGHT);
		
		score = new JTextField("0");
		score.setEditable(false);
		score.setBounds(scoreDisplayX, scoreDisplayY, SCORE_DISPLAY_WIDTH, SCORE_DISPLAY_HEIGHT);
		
		time = new JTextField("--");
		time.setEditable(false);
		time.setBounds(timeDisplayX, timeDisplayY, TIME_DISPLAY_WIDTH, TIME_DISPLAY_HEIGHT);
				
		gameArea.add(title);
		gameArea.add(start);
		gameArea.add(stop);
		gameArea.add(help);
		gameArea.add(levelLabel);
		gameArea.add(level);
		gameArea.add(scoreLabel);
		gameArea.add(score);
		gameArea.add(timeLabel);
		gameArea.add(time);
		
		displayBlocks(properties);
		gameArea.add(backgroundImage);
		gameArea.moveToBack(backgroundImage);
		
		disableBlocks();
		stop.setEnabled(false);
		
		screenPage.add(gameArea);
		screenPage.pack();
		screenPage.setVisible(true);
	}
	
	public synchronized void doMove() throws NumberFormatException, IOException{
		if(stopFlag) {
			animationRunning = false;
			stopAction();
		}
		if(started){
			doUpdateTime();
		}
		if(remove) {
			doRemove();
		}
		if(replace) {
			synchronized(this) {
				if(replaceCounter == 0) {
					replaceFinished(true);
					successfulIteration = doShift();				
					removeCounter = REPLACE_LOOP;
					if(!successfulIteration) {
						connected = new ArrayList<Integer>();
						replace = false;
						animationRunning = false;
						updateLevel();
					}
				}else {
					replaceCounter--;
				}
			}
		}
	}
	
	private synchronized void doUpdateTime() throws IOException {
		Long currentTime = System.currentTimeMillis();
		double difference = currentTime - lastTime;
		double current = difference *.001;
		double timeLeft = Double.parseDouble(time.getText());
		timeLeft -= current;
		time.setText(output.format(timeLeft));
		lastTime = currentTime;
		if(timeLeft <= 0){
			JOptionPane.showMessageDialog(screenPage, 
					"GAME OVER",
					"time expired!",
					JOptionPane.INFORMATION_MESSAGE,
					new ImageIcon(properties.getImageDir() + File.separator + "company.png"));
			
			nameInput = new NameInput(properties.getRoot(), this, PRODUCT_NAME, userDir);
			int currentScore = Integer.parseInt(score.getText());
			stopAction();
			nameInput.setDescending();
			nameInput.init(currentScore);
			gameArea.add(nameInput);
			gameArea.moveToFront(nameInput);
			disable();
		}
	}
	
	private void doRemove() {
		synchronized(this) {
			if(linearRemove) {
				while(!doneRemove()) {
					changeReplaced();
				}
				if(removeCounter == 0) {
					remove = false;
					removeCounter = REMOVE_LOOP;
					replaceFinished();
					replace = true;
				}else {
					removeCounter--;
				}
			}
			else {
				if(removeCounter > 0) {
					if(removeCounter == 1) {
						successfulIteration = changeReplaced();
					}
					successfulIteration = false;
					removeCounter--;
				}else {
					if(!successfulIteration) {
						remove = false;
						replaceFinished();
						replace = true;
					}else {
						removeCounter = REMOVE_LOOP;
					}
				}
			}
		}
	}
	
	public void updateScore(){
		//System.out.println("updateScore:");
		int previousScore = Integer.parseInt(score.getText());
		int newScore = connected.size();
		if(newScore >= 11){
			score.setText("" + (previousScore + newScore + 14));
		}else if(newScore >= 9){
			score.setText("" + (previousScore + newScore + 9));
		}else if(newScore >= 7){
			score.setText("" + (previousScore + newScore + 5));
		}else if(newScore >= 5){
			score.setText("" + (previousScore + newScore + 2));
		}else{
			score.setText("" + (previousScore + newScore));
		}
		
	}
	
	public void updateLevel(){
		int levelCount = Integer.parseInt(level.getText());
		int levelStat = 0;
		while(levelCount > 0){
			levelCount --;
			levelStat = levelCount * goalInterval;
		}
			
		levelCount = Integer.parseInt(level.getText());
		int levelCap = (scoreInterval * levelCount) + levelStat;
		if(Integer.parseInt(score.getText()) > levelCap){
			levelCount = Integer.parseInt(level.getText());
			levelCount++;
			level.setText(levelCount + "");
			String timeText = time.getText();
			while(timeText.compareTo("") == 0){
				timeText = time.getText();
			}
			double previousTime = Double.parseDouble(timeText);
			timeBonus -= ((levelCount - 1) * levelDecay);
			if(timeBonus < levelMin){
				timeBonus = levelMin;
			}
			previousTime += timeBonus;
			time.setText(output.format(previousTime));
			disableBlocks();
			enableBlocks();
		}
	}
	
	// Game API
	public ArrayList<JButton> getBlocks(){
		return blocks;
	}
	public ArrayList<Integer> getConnected(){
		return connected;
	}
	public ArrayList<Integer> getBlockLabels(){
		return blockLabels;
	}
	public int getConnectedMin(){
		return CONNECTED_MIN;
	}
	public void setConnected(ArrayList<Integer> cnctd){
		connected = cnctd;
	}
	// end game API
	
	public void startAnimation() {
		configAnimation();
		remove = true;
	}
	public boolean getAnimationRunning() {
		return animationRunning;
	}
	
	public void startAction(){
		balogger.startTimer();
		lastTime = System.currentTimeMillis();
		start.setEnabled(false);
		stop.setEnabled(true);
		time.setText(roundLength + "");
		score.setText("0");
		level.setText("1");
		started = true;
		help.setEnabled(true);
		enableBlocks();
	}
	
	public synchronized void stopAction() throws IOException{
		balogger.stopTimer();
		balogger.logScore("", score.getText());
		started = false;
		remove = false;
		replace = false;
		stop.setEnabled(false);
		start.setEnabled(true);
		score.setText("--");
		time.setText("--");
		level.setText("--");
		help.setEnabled(false);
		disableBlocks();
		stopFlag = false;
	}
	
	public synchronized void setStop() {
		stopFlag = true;
	}
	
	public void randomize(){
		String timeText = time.getText();
		while(timeText.compareTo("") == 0){
			timeText = time.getText();
		}
		double previousTime = Double.parseDouble(timeText);
		previousTime -= penalty;
		time.setText(output.format(previousTime));
		unhighlight();
		for(int x = 0; x < blocks.size(); x++){
			String path = getBlock(x);
			ImageIcon blockLabel = new ImageIcon(path);
			blocks.get(x).setIcon(blockLabel);
		}
	}
	
	public void init(Properties p){
		if(p == null){
			System.exit(0);
		}else{
			properties = p;
		}
		
		if(screenPage == null){
			balogger = new BALoggerUtil(properties.getRoot(), PRODUCT_NAME, userDir);
			create();
		}
	}

	@Override
	public void init() {
		init(properties);
	}

	@Override
	public void enable() {
		start.setEnabled(true);
		
	}
	private void disable(){
		start.setEnabled(false);
	}

	@Override
	public void initHighScores(hsProperties p) {
		HighScores highScore = new HighScores(this);
		try {
			highScore.init(p);
			gameArea.add(highScore);
			gameArea.moveToFront(highScore);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initHighScores(String name, String rank, int score, hsProperties properties) {
		HighScores highScore = new HighScores(this);
		try {
			highScore.init(name, rank, score, properties);
			gameArea.add(highScore);
			gameArea.moveToFront(highScore);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public int getFrameHeight() {
		return totalHeight;
	}

	@Override
	public int getFrameWidth() {
		return GAME_WIDTH;
	}
}
