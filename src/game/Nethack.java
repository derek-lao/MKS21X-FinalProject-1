import com.googlecode.lanterna.*;
import com.googlecode.lanterna.input.*;
import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.terminal.ansi.*;
import com.googlecode.lanterna.TextColor.*;
import com.googlecode.lanterna.Symbols.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.io.IOException;
import java.util.Random;

public class Nethack {

public static void main(String[] args) throws IOException {
        Maze maze = new Maze();
        ExtendedTerminal terminal = new UnixTerminal();
        terminal.enterPrivateMode();
        terminal.setCursorVisible(false);


        for (int i = 15; i < 26; i++) {
        	new Wall(12, i, maze);
        }

	// game crashes if border is overreached,
	// this draws in border walls
	for (int x = 0; x < 100; x++){
		for (int y = 0; y < 30; y++){
			if (x == 0 || x == 99){
				new Wall(x, y, maze);
			}
			if (y == 0 || y == 29){
				new Wall(x, y, maze);
			}
		}
	}

        Player p = new Player(10, 10, maze);

        boolean running = true;

	while (running){
		terminal.setCursorPosition(0, 0);
		terminal.clearScreen();
		maze.printMaze(terminal);

		// ask for the keyStroke once, then feed into all the "feeder" functions
		KeyStroke key = terminal.readInput();
		char c = key.getCharacter();
		if (c == 'q'){
			terminal.exitPrivateMode();
			System.exit(0);
		}
		p.moveViaInput(c);
	}
}
}
