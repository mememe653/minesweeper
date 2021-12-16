package com.mememe653.minesweeper;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Window extends JPanel {
	
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	
	public static final int NUM_MINES = 50;
	
	public Window() {
		initWindow();
		initCells();
	}

	private void initWindow() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new GridLayout(HEIGHT / Cell.WIDTH, WIDTH / Cell.WIDTH));
	}
	
	private void initCells() {
		// Place mines randomly
		int row[] = new int[HEIGHT / Cell.WIDTH];
		int col[] = new int[WIDTH / Cell.WIDTH];
		
		int i = 0;
		while (i < row.length) {
			int candidateRow = (int) (Math.random() * HEIGHT / Cell.WIDTH);
			int candidateCol = (int) (Math.random() * WIDTH / Cell.WIDTH);
			
			for (int j = 0; j < row.length; j++) {
				if (row[j] == candidateRow && col[j] == candidateCol) {
					continue;
				}
			}
			
			row[i] = candidateRow;
			col[i] = candidateCol;
			i++;
		}
		
		// Initialise cells
		for (i = 0; i < WIDTH / Cell.WIDTH; i++) {
			for (int j = 0; j < HEIGHT / Cell.WIDTH; j++) {
				boolean hasMine = false;
				for (int k = 0; k < row.length; k++) {
					if (row[k] == j && col[k] == i) {
						hasMine = true;
					}
				}
				add(new Cell(j, i, hasMine));
			}
		}
	}
}
