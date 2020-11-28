// Author: Aatma Patel

// Wednesday November 18 2020

// Brick Breaker Game

package brickBreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Navigate {

	public int nav [][], blockWidth, blockHeight;

	public Navigate (int row, int col) {

		nav = new int [row][col];

		for (int p = 0; p < nav.length; p++) {

			for (int m = 0; m < nav[0].length; m++) {

				nav[p][m] = 1;

			}
		}

		blockWidth = 540/col;

		blockHeight = 150/row;

	}

	public void draw(Graphics2D msm) {

		for (int p = 0; p < nav.length; p++) {

			for (int m = 0; m < nav[0].length; m++) {

				if (nav [p][m] > 0) {

					Color winterBlue = new Color(46, 134, 193);

					msm.setColor(winterBlue);

					msm.fillRect(m * blockWidth + 80, p * blockHeight + 50, blockWidth, blockHeight);

					msm.setStroke(new BasicStroke(3));

					Color babyBlue = new Color(214, 234, 248);

					msm.setColor(babyBlue);

					msm.drawRect(m * blockWidth + 80, p * blockHeight + 50, blockWidth, blockHeight);

				}
			}
		}

	}

	public void setBlockValue (int value, int row, int col) {

		nav[row][col] = value;



	}

}
