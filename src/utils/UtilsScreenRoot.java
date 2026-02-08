/**
 * @author mike802
 * 
 * product of - ???
 * 2017
 */
package utils;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import core.Properties;

public class UtilsScreenRoot extends UtilsScreenAnimation{
	
	protected void displayBlocks(Properties p){
		JLabel background;
		JButton button;
		int counter = 0;
		properties = p;
		for(int x = 0; x < (WIDTH * HEIGHT); x++){
			//System.out.print(x + ",");
			background = new JLabel();
			String path = properties.getBackground();
			ImageIcon icon = new ImageIcon(path);
			background.setIcon(icon);
			background.setOpaque(true);
			
			int xcoord = (BACKGROUND_WIDTH * (x % WIDTH) + (x % WIDTH)) + 15;
			int ycoord = (BACKGROUND_HEIGHT * counter) + counter;
			background.setBounds(xcoord, ycoord + titleOffset, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
			gameArea.add(background);
			allBackgrounds.add(background);
			
			button = new JButton();
			path = getBlock();
			icon = new ImageIcon(path);
			button.setIcon(icon);
			button.setOpaque(true);
			
			int xcoord_button = xcoord + ((BACKGROUND_WIDTH - BUTTON_WIDTH) / 2);
			int ycoord_button = ycoord + ((BACKGROUND_HEIGHT - BUTTON_HEIGHT) / 2);
			button.setBounds(xcoord_button, ycoord_button + titleOffset, BUTTON_WIDTH, BUTTON_HEIGHT);
			button.addActionListener(handler);
			
			gameArea.add(button);
			gameArea.moveToFront(button);
			blocks.add(button);
						
			if(x % WIDTH == 9){
				counter++;
				//System.out.println();
			}
		}
	}
}
