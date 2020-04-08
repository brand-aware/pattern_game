/**
 * @author mike802
 * 
 * product of - ???
 * 2017
 */
package utils;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import core.CommonScreen;

public class UtilsScreenActions extends CommonScreen{
	
	public void highlightConnected(){
		//System.out.println("HIGHLIGHT CONNECTED");
		ImageIcon icon;
		int size = connected.size();
		for(int x = 0; x < size; x++){
			int index = connected.get(x);
			//System.out.println(index);
			String backgroundMatching = properties.getBackgroundMatching();
			icon = new ImageIcon(backgroundMatching);
			JLabel background = allBackgrounds.get(index);
			background.setIcon(icon);
		}
	}
	
	public boolean highlighted(int index){
		if(connected != null){
			int size = connected.size();
			for(int x = 0; x < size; x++){
				int position = connected.get(x);
				if(position == index){
					return true;
				}
			}
		}
		return false;
	}
	
	public void unhighlight(){
		ImageIcon icon;
		if(connected != null){
			int size = connected.size();
			for(int x = 0; x < size; x++){
				int index = connected.get(x);
				String backgroundPath = properties.getBackground();
				icon = new ImageIcon(backgroundPath);
				JLabel background = allBackgrounds.get(index);
				background.setIcon(icon);
			}
		}
	}
	
	/*public synchronized void removeHighlighted(){
		//System.out.println("REMOVEHIGHLIGHTED");
		for(int x = 0; x < connected.size(); x++) {
			//System.out.print(connected.get(x) + ",");
		}
		//System.out.println();
		int size = connected.size();
		for(int x = 0; x < size; x++){
			if(connected.get(x) - 10 >= 0){
				//System.out.println("removing starting at: " + connected.get(x));
				shiftColumnDown(connected.get(x), x);
			}else{
				addOne(connected.get(x));
			}
		}
		connected = new ArrayList<Integer>();
	}*/
	
	protected synchronized void shiftColumnDown(int index, int pointer){
		//System.out.println("SHIFTING COLUMN AT: " + index);
		if(index - WIDTH > 0){
			//System.out.println("fixConnected");
			fixConnected(index, pointer);
		}
		
		while(index - WIDTH >= 0){
			int position = index - WIDTH;
			//System.out.println("moving: " + position + ", to: " + index);
			int label = blockLabels.get(position);
			blockLabels.set(index, label);
			ImageIcon icon = new ImageIcon(properties.getShape(label));
			blocks.get(index).setIcon(icon);
			ImageIcon background = new ImageIcon(properties.getBackground());
			allBackgrounds.get(index).setIcon(background);
			gameArea.moveToFront(allBackgrounds.get(index));
			gameArea.moveToFront(blocks.get(index));
			
			index -= WIDTH;
		}
		//addOne(index += 10);
		addOne(index % WIDTH);
	}
	
	/**
	 * when a given column (index) is shifted down by one, all affected
	 * blocks in the connected internal data structure are maintained
	 * as an original match by having their coordinates shifted to their
	 * new location.  thus, while original matches may not longer be 
	 * "connected", they are properly dealt with.
	 * 
	 * @param index
	 */
	private void fixConnected(int index, int pointer){
		while(index - WIDTH >= 0){
			int top = index - WIDTH;
			if(isConnected(top, pointer)){
				int position = connectedIndex(top, pointer);
				if(position >= pointer) {
					//System.out.println("connected: " + position + ". " + top + " to " + index);
					connected.set(position, index);
					//origConn.set(position, index);
					//connected.set(position, top);
				}
			}
			index -= WIDTH;
		}
	}
	
	/**
	 * determines if the given index is located within the connected
	 * internal data structure.  given index is starting row id.  method
	 * is intended to be used in a column search. pointer is the current
	 * position in the connected data structure (to prevent duplicate removal)
	 * 
	 * @param int index
	 * @param int pointer
	 * @return boolean found
	 */
	private boolean isConnected(int index, int pointer){
		for(int x = pointer; x < connected.size(); x++){
			if(connected.get(x) == index){
				return true;
			}
		}
		return false;
	}
	
	private int connectedIndex(int index, int pointer){
		int position = 0;
		for(int x = pointer; x < connected.size(); x++){
			if(connected.get(x) == index){
				return x;
			}
		}
		return position;
	}
	
	/**
	 * Adds exactly one (random) block at the top of the screen for the column
	 * identified by the given index.
	 * 
	 * @param int index
	 */
	protected void addOne(int index){
		//System.out.println("addOne to: " + index);
		String block = getBlock(index);
		ImageIcon icon = new ImageIcon(block);
		blocks.get(index).setIcon(icon);
		ImageIcon background = new ImageIcon(properties.getBackground());
		allBackgrounds.get(index).setIcon(background);
		gameArea.moveToFront(allBackgrounds.get(index));
		gameArea.moveToFront(blocks.get(index));
	}
	

	protected String getBlock(){
		double random = Math.random();
		int index = (int) (random * UNIQUE_BLOCKS);
		blockLabels.add(index);
		return properties.getShape(index);
	}
	
	protected String getBlock(int xcord){
		double random = Math.random();
		int index = (int) (random * UNIQUE_BLOCKS);
		blockLabels.set(xcord, index);
		return properties.getShape(index);
	}
	
	protected void disableBlocks(){
		unhighlight();
		for(int x = 0; x < blocks.size(); x++){
			blocks.get(x).setEnabled(false);
			ImageIcon cover = new ImageIcon(properties.getCover());
			blocks.get(x).setIcon(cover);
			ImageIcon background = new ImageIcon(properties.getBackground());
			allBackgrounds.get(x).setIcon(background);
			gameArea.moveToFront(allBackgrounds.get(x));
			gameArea.moveToFront(blocks.get(x));
		}
		connected = null;
	}
	
	protected void enableBlocks(){
		for(int x = 0; x < blocks.size(); x++){
			blocks.get(x).setEnabled(true);
			String path = getBlock(x);
			ImageIcon blockLabel = new ImageIcon(path);
			blocks.get(x).setIcon(blockLabel);
		}
	}
}
