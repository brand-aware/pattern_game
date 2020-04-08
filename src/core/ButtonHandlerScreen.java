/**
 * @author mike802
 * 
 * product of - ???
 * 2017
 */
package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;

import utils.Tools;

public class ButtonHandlerScreen implements ActionListener{

	private Screen screen;
	private Tools tools;
	
	public ButtonHandlerScreen(Screen game, Tools tls){
		screen = game;
		tools = tls;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String text = "";
		JButton button = (JButton)event.getSource();
		text = button.getText();
		
		if(text.compareTo("start") == 0){
			screen.startAction();
		}else if(text.compareTo("stop") == 0){
			if(screen.getAnimationRunning()) {
				screen.setStop();
			}else {
				try {
					screen.stopAction();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else if(text.compareTo("help") == 0){
			screen.randomize();		
		}else{
			
			synchronized(this) {
				boolean found = false;
				ArrayList<JButton> blocks = screen.getBlocks();
				int size = blocks.size();
				for(int x = 0; x < size; x++){
					JButton block = blocks.get(x);
					if(event.getSource() == block){
						found = true;
						
						if(screen.highlighted(x)){
							if(!screen.getAnimationRunning()) {
								//System.out.println("highlighted - removing - " + x);
								screen.updateScore();
								screen.unhighlight();
								//screen.removeHighlighted();
								screen.startAnimation();
								//screen.updateLevel();
							}
						}else{
							if(!screen.getAnimationRunning()) {
								ArrayList<Integer> connected = screen.getConnected();
								if(connected != null){
									screen.unhighlight();
									screen.setConnected(null);
								}
								connected = new ArrayList<Integer>();
								tools.startMove(x);
								ArrayList<Integer> blockLabels = screen.getBlockLabels();
								tools.findConnected(blockLabels, x);
								connected = tools.getConnected();
								int minimum = screen.getConnectedMin();
								int connectedSize = connected.size();
								if(connectedSize >= minimum){
									screen.setConnected(connected);
									screen.highlightConnected();
								}
							}
						}
					}
					if(!found){
						//try {
						//	throw new Exception("???");
						//} catch (Exception e) {
						//	e.printStackTrace();
						//}
					}
				}
			}
			
			
		}
	}
}
