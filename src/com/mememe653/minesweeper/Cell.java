package com.mememe653.minesweeper;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

public class Cell extends JPanel {
	
	public static final int WIDTH = 20;
	
	private final int row;
	private final int col;
	private boolean uncovered = false;
	private boolean flagged = false;
	private final boolean hasMine;
	private int mineCount;
	
	private Cell neighbours[] = new Cell[8];
	
	private List<ImageIcon> imageIcons = List.of(new ImageIcon("src/com/mememe653/resources/0.png"),
												 new ImageIcon("src/com/mememe653/resources/1.png"),
												 new ImageIcon("src/com/mememe653/resources/2.png"),
												 new ImageIcon("src/com/mememe653/resources/3.png"),
												 new ImageIcon("src/com/mememe653/resources/4.png"),
												 new ImageIcon("src/com/mememe653/resources/5.png"),
												 new ImageIcon("src/com/mememe653/resources/6.png"),
												 new ImageIcon("src/com/mememe653/resources/7.png"),
												 new ImageIcon("src/com/mememe653/resources/8.png"));
	
	private ImageIcon bombII = new ImageIcon("src/com/mememe653/resources/bomb.png");
	private ImageIcon facingDownII = new ImageIcon("src/com/mememe653/resources/facingDown.png");
	private ImageIcon flaggedII = new ImageIcon("src/com/mememe653/resources/flagged.png");
	
	private MouseListener mouseListener = new MouseListener();
	
	public Cell(int row, int col, boolean hasMine) {
		this.row = row;
		this.col = col;
		this.hasMine = hasMine;
		
		Image img = facingDownII.getImage().getScaledInstance(WIDTH, WIDTH, Image.SCALE_DEFAULT);
		facingDownII = new ImageIcon(img, facingDownII.getDescription());
		
		if (hasMine) {
			img = bombII.getImage().getScaledInstance(WIDTH, WIDTH, Image.SCALE_DEFAULT);
			bombII = new ImageIcon(img, bombII.getDescription());
		}
		
		addMouseListener(mouseListener);
	}
	
	public void setNeighbours(Cell neighbours[]) {
		this.neighbours = neighbours;
		updateMineCount();
	}
	
	private void updateMineCount() {
		mineCount = 0;
		for (Cell neighbour : neighbours) {
			if (neighbour != null && neighbour.hasMine()) {
				mineCount++;
			}
		}
	}
	
	public boolean hasMine() {
		return hasMine;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (uncovered && hasMine) {
			Image img = bombII.getImage();
			g.drawImage(img, 0, 0, this);
		} else if (uncovered && !hasMine) {
			Image img = imageIcons.get(mineCount).getImage().getScaledInstance(WIDTH, WIDTH, Image.SCALE_DEFAULT);
			ImageIcon ii = new ImageIcon(img, imageIcons.get(mineCount).getDescription());
			g.drawImage(ii.getImage(), 0, 0, this);
		} else if (!uncovered && flagged) {
			Image img = flaggedII.getImage().getScaledInstance(WIDTH, WIDTH, Image.SCALE_DEFAULT);
			ImageIcon ii = new ImageIcon(img, flaggedII.getDescription());
			g.drawImage(ii.getImage(), 0, 0, this);
		} else {
			Image img = facingDownII.getImage();
			g.drawImage(img, 0, 0, this);
		}
	}
	
	private class MouseListener extends MouseInputAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			
			if (SwingUtilities.isLeftMouseButton(e)) {
				uncovered = true;
			} else if (SwingUtilities.isRightMouseButton(e)) {
				flagged = !flagged;
			}
			repaint();
		}
	}
}
