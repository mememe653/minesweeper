package com.mememe653.minesweeper;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Minesweeper extends JFrame {

	public Minesweeper() {
		initUI();
	}
	
	private void initUI() {
		add(new Window());
		setResizable(false);
		pack();
		setTitle("Minesweeper");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String args[]) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new Minesweeper();
			frame.setVisible(true);
		});
	}
}
