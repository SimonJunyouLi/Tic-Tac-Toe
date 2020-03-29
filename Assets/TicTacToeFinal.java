import java.util.*;
import java.awt.*;
import java.applet.*;
import javax.swing.*;

// blank spot is 0. ai is 1, and the user is 2
/* the board looks like this
 * 0 1 2
 * 3 4 5
 * 6 7 8
 */
public class TicTacToeFinal extends Applet {
	Image offScreen, board, cross, circle, loss, victory, draw;
	Graphics offG;
	AudioClip Musicwin;
	AudioClip Musiclost;
	int turn = 1;
	int x, y;
	int press;

	int [] tic = new int [9];

	boolean win = false;
	boolean nowinner = true;
	boolean start = true;
	boolean restartstate = false;

	public void init() {


		offScreen = createImage(1261,710);
		offG = offScreen.getGraphics();

		board = getImage(getCodeBase(), "TicTacBoard.jpg");
		cross = getImage(getCodeBase(), "XO Cross.png");
		circle = getImage(getCodeBase(), "XO Circle.png");
		victory = getImage(getCodeBase(), "Victory.png");
		loss = getImage(getCodeBase(), "Loss.png");
		draw = getImage(getCodeBase(), "Draw.png");
		Musicwin = getAudioClip(getCodeBase(), "Youwin.wav");
		Musiclost = getAudioClip(getCodeBase(), "Youlost.wav");
		MediaTracker tracker = new MediaTracker(this);  //track loading of pics
		tracker.addImage(board, 0);
		tracker.addImage(cross, 0);
		tracker.addImage(circle, 0);

		//Wait for pictures to complete loading
		while(tracker.checkAll(true) != true){ }
		//Check if trouble loading pics
		if (tracker.isErrorAny()){
			JOptionPane.showMessageDialog(null, "Trouble loading pictures.");
		}

		offG.drawImage(board, 0, 0, this);
		offG.drawImage(cross, 635-630, 390-400, this);

		tic[4] = 1;
	}



	public void paint(Graphics g){
		g.drawImage(offScreen, 0, 0, this);
	}

	// Set/Reset the board back to all empty values.
	public void initializeBoard() {
		for (int k = 0; k < 9; k++) {
			tic [k] = 0;
			tic [4] = 1;
		}
	}

	public boolean mouseDown(Event evt, int x, int y){
		restart();

		screen();

	if(start){
		if(nowinner){
			if(turn%2 == 1){
				if(x>281 && x<524 && y>69 && y<277){
					press = 0;
				}
				else if(x>524 && x<753 && y>69 && y<277){
					press = 1;
				}
				else if(x>753 && x<949 && y>69 && y<277){
					press = 2;
				}
				else if(x>281 && x<524 && y>277 && y<490){
					press = 3;
				}
				else if(x>524 && x<753 && y>277 && y<490){
					press = 4;
				}
				else if(x>753 && x<949 && y>277 && y<490){
					press = 5;
				}
				else if(x>281 && x<524 && y>490 && y<640){
					press = 6;
				}
				else if(x>524 && x<753 && y>490 && y<640){
					press = 7;
				}
				else if(x>753 && x<949 && y>490 && y<640){
					press = 8;
				}

				if(tic[press] != 1 && tic[press] != 2){

					tic[press] = 2;

					if(press == 0){
						offG.drawImage(circle, 390-630, 165-400, this);
						turn++;
					}
					else if(press == 1){
						offG.drawImage(circle, 630-630, 165-400, this);
						turn++;
					}
					else if(press == 2){
						offG.drawImage(circle, 845-630, 160-400, this);
						turn++;
					}
					else if(press == 3){
						offG.drawImage(circle, 400-630, 395-400, this);
						turn++;
					}
					else if(press == 4){
						offG.drawImage(circle, 635-630, 390-400, this);
						turn++;
					}
					else if(press == 5){
						offG.drawImage(circle, 855-630, 385-400, this);
						turn++;
					}
					else if(press == 6){
						offG.drawImage(circle, 425-630, 587-400, this);
						turn++;
					}
					else if(press == 7){
						offG.drawImage(circle, 645-630, 578-400, this);
						turn++;
					}
					else if(press == 8){
						offG.drawImage(circle, 860-630, 570-400, this);
						turn++;
					}
				}
			}


			if(turn%2 == 0){
				moves();
			}
		}
	}

			if(!start){
				start = true;
			}

			windecision();
			repaint();

			return true;

	}

	// Restart
	public void restart () {
		if(restartstate){
			turn = 1;
			initializeBoard();
			win = false;
			nowinner = true;
			restartstate = false;
			offG.drawImage(board, 0, 0, this);
			offG.drawImage(cross, 635-630, 390-400, this);
			tic[4] = 1;
			press = -1;
			start = false;
			repaint();
		}
	}

	// Determines the winner
	public void windecision () {
	//	boolean winstate = true;

		//diagnol
		if (tic[0] == tic[4] && tic[4] == tic[8] && tic[0] != 0) {
	        win = true;
	        nowinner = false;
	    }
		//diagnol
	    else if(tic[2] == tic[4] && tic[4] == tic[6] && tic[2] != 0){
	    	win = true;
	    	nowinner = false;
	    }

		//row1
	    else if (tic[0] == tic[1] && tic[1] == tic[2] && tic[0] != 0) {
	        win = true;
	        nowinner = false;
	    }
		//row2
	    else if (tic[3] == tic[4] && tic[4] == tic[5] && tic[3] != 0) {
	        win = true;
	        nowinner = false;
        }
		//row3
	    else if (tic[6] == tic[7] && tic[7] == tic[8] && tic[6] != 0) {
	        win = true;
	        nowinner = false;
        }
        //column1
        else if (tic[0] == tic[3] && tic[3] == tic[6] && tic[6] != 0) {
	        win = true;
	        nowinner = false;
        }
        //column2
        else if (tic[1] == tic[4] && tic[4] == tic[7] && tic[1] != 0) {
	        win = true;
	        nowinner = false;
        }
        //column3
		else if (tic[2] == tic[5] && tic[5] == tic[8] && tic[2] != 0) {
	        win = true;
	        nowinner = false;
        }
	}

	public void screen(){
		if(win){
			if(turn%2 == 1){
				offG.drawImage(loss, 0, 0, this);
				repaint();
				Musiclost.play();
				restartstate = true;
			}
			else if(turn%2 == 0){
				offG.drawImage(victory, 0, 0, this);
				repaint();
				Musicwin.play();
				restartstate = true;
			}
		}

		else if(nowinner && turn == 9){
			offG.drawImage(draw, 0, 0, this);
			repaint();
			restartstate = true;
		}
	}

	//you get the position of the mouse and then determine which grid it is, and then turn that grid to 2 if it is empty

	public void moves() {

		boolean movestate = true;

		// turn is for Marshall to determine who's turn it is to make the move
		// aipic is the image of the ai's symbol

		//tic 1 & 2
		if (tic[0] == 2 && tic[1] == 2 && tic[2] == 0 && movestate){
			offG.drawImage(cross, 845-630, 160-400, this);
			tic[2] = 1;
			movestate = false;
			turn++;
		}
		//tic 2 & 3
		if (tic[1] == 2 && tic[2] == 2 && tic[0] == 0 && movestate){
			offG.drawImage(cross, 390-630, 165-400, this);
			tic[0] = 1;
			movestate = false;
			turn++;
		}

		//tic 1 & 3
		if (tic[0] == 2 && tic[2] == 2 && tic[1] == 0 && movestate){
			offG.drawImage(cross, 630-630, 165-400, this);
			tic[1] = 1;
			movestate = false;
			turn++;
		}

		//tic 7 & 8
		if (tic[6] == 2 && tic[7] == 2 && tic[8] == 0 && movestate){
			offG.drawImage(cross, 860-630, 570-400, this);
			tic[8] = 1;
		    movestate = false;
			turn++;
		}
		//tic 8 & 9
		if (tic[7] == 2 && tic[8] == 2 && tic[6] == 0 && movestate){
			offG.drawImage(cross, 425-630, 587-400, this);
			tic[6] = 1;
			movestate = false;
			turn++;
		}
		// tic 7 & 9
		if (tic[6] == 2 && tic[8] == 2 && tic[7] == 0 && movestate){
			offG.drawImage(cross, 645-630, 578-400, this);
			tic[7] = 1;
			movestate = false;
			turn++;
		}
		//tic 1 & 4
		if (tic[0] == 2 && tic[3] == 2 && tic[6] == 0 && movestate){
			offG.drawImage(cross, 425-630, 587-400, this);
			tic[6] = 1;
		    movestate = false;
		    turn++;
		}
		//tic 4 & 7
		if (tic[3] == 2 && tic[6] == 2 && tic[0] == 0 && movestate){
			offG.drawImage(cross, 390-630, 165-400, this);
			tic[0] = 1;
			movestate = false;
			turn++;
		}

		// tic 1 & 7
		if (tic[0] == 2 && tic[6] == 2 && tic[3] == 0 && movestate){
			offG.drawImage(cross, 400-630, 395-400, this);
			tic[3] = 1;
			movestate = false;
			turn++;
		}

		//tic 3 & 6
		if (tic[2] == 2 && tic[5] == 2 && tic[8] == 0 && movestate){
			offG.drawImage(cross, 860-630, 570-400, this);
			tic[8] = 1;
			movestate = false;
			turn++;
		}
		//tic 6 & 9
		if (tic[5] == 2 && tic[8] == 2 && tic[2] == 0 && movestate){
			offG.drawImage(cross, 845-630, 160-400, this);
			tic[2] = 1;
			movestate = false;
			turn++;
		}

		// tic 3 & 9
		if (tic[2] == 2 && tic[8] == 2 && tic[5] == 0 && movestate){
			offG.drawImage(cross, 855-630, 385-400, this);
			tic[5] = 1;
			movestate = false;
			turn++;
		}
		//tic 2 & 4
		if (tic[1] == 2 && tic[3] == 2 && tic[0] == 0 && movestate){
			offG.drawImage(cross, 390-630, 165-400, this);
			tic[0] = 1;
			movestate = false;
			turn++;
		}
		//tic 2 & 6
		if (tic[1] == 2 && tic[5] == 2 && tic[2] == 0 && movestate){
			offG.drawImage(cross, 845-630, 160-400, this);
			tic[2] = 1;
			movestate = false;
			turn++;
		}
		//tic 4 & 8
		if (tic[3] == 2 && tic[7] == 2 && tic[6] == 0 && movestate){
			offG.drawImage(cross, 425-630, 587-400, this);
			tic[6] = 1;
			movestate = false;
			turn++;
		}
		//tic 6 & 8
		if (tic[5] == 2 && tic[7] == 2 && tic[8] == 0 && movestate){
			offG.drawImage(cross, 860-630, 570-400, this);
			tic[8] = 1;
			movestate = false;
			turn++;
		}

		if (tic[0] == 0 && movestate){
			offG.drawImage(cross, 390-630, 165-400, this);
			tic[0] = 1;
			movestate = false;
			turn++;
		}

		if (tic[2] == 0 && movestate){
			offG.drawImage(cross, 845-630, 160-400, this);
			tic[2] = 1;
			movestate = false;
			turn++;
		}

		if (tic[6] == 0 && movestate){
			offG.drawImage(cross, 425-630, 587-400, this);
			tic[6] = 1;
			movestate = false;
			turn++;
		}

		if (tic[8] == 0 && movestate){
			offG.drawImage(cross, 860-630, 570-400, this);
			tic[8] = 1;
			movestate = false;
			turn++;
		}

		if (tic[1] == 0 && movestate){
			offG.drawImage(cross, 630-630, 165-400, this);
			tic[1] = 1;
			movestate = false;
			turn++;
		}

		if (tic[3] == 0 && movestate){
			offG.drawImage(cross, 400-630, 395-400, this);
			tic[3] = 1;
			movestate = false;
			turn++;
		}

		if (tic[5] == 0 && movestate){
			offG.drawImage(cross, 855-630, 385-400, this);
			tic[5] = 1;
			movestate = false;
			turn++;
		}

		if (tic[7] == 0 && movestate){
			offG.drawImage(cross, 645-630, 578-400, this);
			tic[7] = 1;
			movestate = false;
			turn++;
		}
	}
}
