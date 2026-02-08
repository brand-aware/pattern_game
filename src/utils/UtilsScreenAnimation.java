package utils;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class UtilsScreenAnimation extends UtilsScreenActions{
	
	protected void configAnimation() {
		
		replaceCounter = REPLACE_LOOP;
		removeCounter = REMOVE_LOOP;
		connectedSize = connected.size();
		shiftCounter = 0;
		
		animationRunning = true;
	}	
	
	public synchronized boolean changeReplaced() {
		if(connectedSize > 0) {
			ImageIcon icon;
			if(connected != null){
				int index = connected.get(connectedSize - 1);
				String removedPath = properties.getRemovedImg();
				icon = new ImageIcon(removedPath);
				JLabel background = allBackgrounds.get(index);
				background.setIcon(icon);
				gameArea.moveToFront(background);
			}
			
			connectedSize--;
			return true;
		}
		return false;
		
	}
	
	public synchronized void replaceFinished() {
		connectedSize = connected.size();
		origConn = connected;
	}
	
	public synchronized void replaceFinished(boolean started) {
		//System.out.print("replaceFinished: " + shiftCounter);
		if(shiftCounter < connected.size()) {
			ImageIcon icon, icon2;
			if(connected != null){
				int index = origConn.get(shiftCounter);
				//System.out.println(", index: " + index);
				String cover = properties.getCover();
				String backgroundPath = properties.getBackground();
				icon = new ImageIcon(cover);
				icon2 = new ImageIcon(backgroundPath);
				JLabel background = allBackgrounds.get(index);
				JButton block = blocks.get(index);
				block.setIcon(icon);
				background.setIcon(icon2);
				gameArea.moveToFront(background);
				gameArea.moveToFront(block);
			}
		}
		
	}
	
	protected synchronized boolean doShift() {
		if(shiftCounter < connected.size()) {
			if(connected.get(shiftCounter) - 10 >= 0){
				//System.out.println("removing starting at: " + connected.get(x));
				shiftColumnDown(connected.get(shiftCounter), shiftCounter);
			}else{
				addOne(connected.get(shiftCounter));
			}
			
			shiftCounter++;
			return true;
		}else {
			animationRunning = false;
			return false;
		}
	}
	
	protected boolean getAnimationRunning() {
		return animationRunning;
	}
	protected boolean doneRemove() {
		return !(connectedSize > 0);
	}

}
