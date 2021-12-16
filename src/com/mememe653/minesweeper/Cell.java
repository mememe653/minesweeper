package com.mememe653.minesweeper;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Cell extends JPanel {
	
	public static final int WIDTH = 20;
	
	private final int row;
	private final int col;
	
	private ImageIcon ii = new ImageIcon("src/com/mememe653/resources/facingDown.png");

	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
		
		Image img = ii.getImage().getScaledInstance(WIDTH, WIDTH, Image.SCALE_DEFAULT);
		ii = new ImageIcon(img, ii.getDescription());
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Image img = ii.getImage();
		g.drawImage(img, 0, 0, this);
	}
}
