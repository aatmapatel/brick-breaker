// Author: Aatma Patel

// Wednesday November 18 2020

// Brick Breaker Game

package brickBreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

	private boolean play = false;

	private int score = 0, totalBlocks = 21, delay = 8, player1 = 310, ballposX = 120,

			ballposY = 350, ball1dir = -1, ball2dir = -2;

	private Timer clock;

	private Navigate nav;

	public Gameplay() {

		nav = new Navigate(3, 7);

		addKeyListener(this);

		setFocusable(true);

		setFocusTraversalKeysEnabled(false);

		clock = new Timer(delay, this);

		clock.start();
	}

	public void paint(Graphics psm) {

		// adding the background of the brick breaker game

		Color babyBlue = new Color(214, 234, 248);

		psm.setColor(babyBlue);

		psm.fillRect(1, 1, 692, 592);

		// drawing map

		nav.draw((Graphics2D) psm);

		// game borders

		Color pastalBlue = new Color(133, 193, 233);

		Color skyBlue = new Color(93, 173, 226);

		Color seaBlue = new Color(52, 152, 219);

		psm.setColor(pastalBlue);

		psm.fillRect(0, 0, 3, 592);

		psm.fillRect(0, 0, 692, 3);

		psm.fillRect(691, 0, 3, 592);

		// score bar

		psm.setColor(skyBlue);

		psm.setFont(new Font("Serif", Font.BOLD, 25));

		psm.drawString("" + score, 590, 30);

		// board for ball

		psm.setColor(skyBlue);

		psm.fillRect(player1, 550, 100, 8);

		// ball

		psm.setColor(seaBlue);

		psm.fillOval(ballposX, ballposY, 20, 20);

		if (totalBlocks <= 0) {

			play = false;

			ball1dir = 0;

			ball2dir = 0;

			psm.setColor(seaBlue);

			psm.setFont(new Font("Serif", Font.BOLD, 30));

			psm.drawString("You Win! Score: " + score, 193, 300);

			psm.setFont(new Font("Serif", Font.BOLD, 20));

			psm.drawString("Press Enter to Play Again", 230, 350);

		}

		if (ballposY > 570) {

			play = false;

			ball1dir = 0;

			ball2dir = 0;

			psm.setColor(seaBlue);

			psm.setFont(new Font("Serif", Font.BOLD, 30));

			psm.drawString("Game Over!" + " Score: " + score, 193, 300);

			psm.setFont(new Font("Serif", Font.BOLD, 20));

			psm.drawString("Press Enter to Play Again", 230, 350);

		}

		psm.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		clock.restart();

		if (play) {

			if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(player1, 550, 100, 8))) {

				ball2dir = -ball2dir;

			}

			for (int p = 0; p < nav.nav.length; p++) {

				for (int m = 0; m < nav.nav[0].length; m++) {

					if (nav.nav[p][m] > 0) {

						int blockX = m * nav.blockWidth + 80;

						int blockY = p * nav.blockHeight + 50;

						int blockWidth = nav.blockWidth;

						int blockHeight = nav.blockHeight;

						Rectangle rec = new Rectangle(blockX, blockY, blockWidth, blockHeight);

						Rectangle ballRec = new Rectangle(ballposX, ballposY, 20, 20);

						Rectangle blockRec = rec;

						if (ballRec.intersects(blockRec)) {

							nav.setBlockValue(0, p, m);

							totalBlocks -= 1;

							score += 5;

							if (ballposX + 19 <= blockRec.x || ballposX + 1 >= blockRec.x + blockRec.width) {

								ball1dir = -ball1dir;

							} else {

								ball2dir = -ball2dir;

							}

							break;
						}

					}

				}

			}

			ballposX += ball1dir;

			ballposY += ball2dir;

			if (ballposX < 0) {

				ball1dir = -ball1dir;

			}

			if (ballposY < 0) {

				ball2dir = -ball2dir;

			}

			if (ballposX > 670) {

				ball1dir = -ball1dir;

			}
		}

		repaint();

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			if (player1 >= 600) {

				player1 = 600;

			} else {

				moveRight();
			}

		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			if (player1 < 10) {

				player1 = 10;

			} else {

				moveLeft();
			}

		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!play) {

				play = true;

				ballposX = 120;

				ballposY = 350;

				ball1dir = -1;

				ball2dir = -2;

				player1 = 310;

				score = 0;

				totalBlocks = 21;

				nav = new Navigate(3, 7);

				repaint();

			}
		}

	}

	public void moveRight() {

		play = true;

		player1 += 20;

	}

	public void moveLeft() {

		play = true;

		player1 -= 20;

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
