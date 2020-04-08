/**
 * @author mike802
 * 
 * product of - ???
 * 2017
 */
package core;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import balogging.BALoggerUtil;
import highscores.NameInput;
import utils.Tools;

public class CommonScreen extends ConfigScreen{

	protected JFrame screenPage = null;
	protected Tools utils;
	protected BALoggerUtil balogger;
	protected Properties properties;
	protected ButtonHandlerScreen handler;
	protected JDesktopPane gameArea;
	
	protected JButton start, stop, help;
	protected JTextField level, score, time;
	
	protected ArrayList<Integer> blockLabels = new ArrayList<Integer>();
	protected ArrayList<Integer> connected = null;
	protected ArrayList<JButton> blocks = new ArrayList<JButton>();
	protected ArrayList<JLabel> allBackgrounds = new ArrayList<JLabel>();
	protected int connectedSize = -1;
	protected ArrayList<Integer> origConn;
	protected ConcurrentHashMap<Integer, Integer> modified;
	//<column, row>
	//protected ConcurrentHashMap<Integer, Integer> lower;
	
	protected Long lastTime;
	protected DecimalFormat output;
	protected NameInput nameInput = null;
}
