package com.mememe653.minesweeper;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Window extends JPanel {
	
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	
	public Window() {
		initWindow();
	}

	private void initWindow() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new GridLayout(HEIGHT / Cell.WIDTH, WIDTH / Cell.WIDTH));
		for (int i = 0; i < WIDTH / Cell.WIDTH; i++) {
			for (int j = 0; j < HEIGHT / Cell.WIDTH; j++) {
				add(new Cell(j, i));
			}
		}
	}
}
