package com.mememe653.minesweeper;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Window extends JPanel {
	
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	
	public static final int NUM_MINES = 50;
	
	private Cell cells[][] = new Cell[HEIGHT / Cell.WIDTH][WIDTH / Cell.WIDTH];
	
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
		int mineRow[] = new int[NUM_MINES];
		int mineCol[] = new int[NUM_MINES];
		
		int i = 0;
		while (i < NUM_MINES) {
			int candidateRow = (int) (Math.random() * HEIGHT / Cell.WIDTH);
			int candidateCol = (int) (Math.random() * WIDTH / Cell.WIDTH);
			
			for (int j = 0; j < mineRow.length; j++) {
				if (mineRow[j] == candidateRow && mineCol[j] == candidateCol) {
					continue;
				}
			}
			
			mineRow[i] = candidateRow;
			mineCol[i] = candidateCol;
			i++;
		}
		
		// Initialise cells
		for (i = 0; i < WIDTH / Cell.WIDTH; i++) {
			for (int j = 0; j < HEIGHT / Cell.WIDTH; j++) {
				boolean hasMine = false;
				for (int k = 0; k < mineRow.length; k++) {
					if (mineRow[k] == j && mineCol[k] == i) {
						hasMine = true;
					}
				}
				Cell cell = new Cell(j, i, hasMine);
				add(cell);
				cells[j][i] = cell;
			}
		}
		
		// Set neighbours
		for (i = 0; i < WIDTH / Cell.WIDTH; i++) {
			for (int j = 0; j < HEIGHT / Cell.WIDTH; j++) {
				Cell neighbours[] = new Cell[8];
				int row = j - 1;
				int col = i - 1;
				if (row >= 0 && col >= 0) {
					neighbours[0] = cells[row][col];
				}
				row = j - 1;
				col = i;
				if (row >= 0) {
					neighbours[1] = cells[row][col];
				}
				row = j - 1;
				col = i + 1;
				if (row >= 0 && col < WIDTH / Cell.WIDTH) {
					neighbours[2] = cells[row][col];
				}
				row = j;
				col = i + 1;
				if (col < WIDTH / Cell.WIDTH) {
					neighbours[3] = cells[row][col];
				}
				row = j + 1;
				col = i + 1;
				if (row < HEIGHT / Cell.WIDTH && col < WIDTH / Cell.WIDTH) {
					neighbours[4] = cells[row][col];
				}
				row = j + 1;
				col = i;
				if (row < HEIGHT / Cell.WIDTH) {
					neighbours[5] = cells[row][col];
				}
				row = j + 1;
				col = i - 1;
				if (row < HEIGHT / Cell.WIDTH && col >= 0) {
					neighbours[6] = cells[row][col];
				}
				row = j;
				col = i - 1;
				if (col >= 0) {
					neighbours[7] = cells[row][col];
				}
				
				cells[j][i].setNeighbours(neighbours);
			}
		}
	}
}
