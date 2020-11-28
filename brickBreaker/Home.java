// Author: Aatma Patel

// Wednesday November 18 2020

// Brick Breaker Game

package brickBreaker;

import javax.swing.JFrame;

public class Home {

	public static void main(String[] args) {

		JFrame obj = new JFrame();

		Gameplay gamePlay = new Gameplay();

		obj.setBounds(10,10, 700, 600);

		obj.setTitle("Ball");

		obj.setResizable(false);

		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		obj.add(gamePlay);

		obj.setVisible(true);


	}

}
