package com.mememe653.minesweeper;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class Cell extends JPanel {
	
	public static final int WIDTH = 20;
	
	private final int row;
	private final int col;
	private boolean uncovered = false;
	
	private ImageIcon facingDownII = new ImageIcon("src/com/mememe653/resources/facingDown.png");
	private ImageIcon uncoveredII = new ImageIcon("src/com/mememe653/resources/0.png");
	
	private MouseListener mouseListener = new MouseListener();
	
	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
		
		Image img = facingDownII.getImage().getScaledInstance(WIDTH, WIDTH, Image.SCALE_DEFAULT);
		facingDownII = new ImageIcon(img, facingDownII.getDescription());
		
		img = uncoveredII.getImage().getScaledInstance(WIDTH, WIDTH, Image.SCALE_DEFAULT);
		uncoveredII = new ImageIcon(img, uncoveredII.getDescription());
		
		addMouseListener(mouseListener);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (uncovered) {
			Image img = uncoveredII.getImage();
			g.drawImage(img, 0, 0, this);
		} else {
			Image img = facingDownII.getImage();
			g.drawImage(img, 0, 0, this);
		}
	}
	
	private class MouseListener extends MouseInputAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			
			uncovered = true;
			repaint();
		}
	}
}
