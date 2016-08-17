package com.company;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;

public class Ex13_2 {
// hejhej hej
// hejhej hej
// hej hej
// hej hej
    private static Terminal terminal;

    public static void main(String[] args) throws InterruptedException {
        Player player = new Player(10, 10);
        Enemy enemy = new Enemy(5,5);

        terminal = TerminalFacade.createTerminal(System.in,
                System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();

        boolean isGameOn = true;

        while(isGameOn){
            terminal.clearScreen();

            UpdateScreen(player, enemy, terminal);
            MovePlayer(player, terminal);
            isGameOn = GameLogic(player, enemy);
            UpdateScreen(player, enemy, terminal);

        }
        terminal.clearScreen();
        printString();


    }

    private static boolean GameLogic(Player player, Enemy enemy) {

        if (player.x == enemy.x && player.y == enemy.y) {
            return false;
        }

       else if (Math.abs(player.x - enemy.x) > Math.abs(player.y - enemy.y)) {
            if (player.x > enemy.x)
                enemy.x = enemy.x + 1;
            else
                enemy.x = enemy.x - 1;
        }
        else  {
            if (player.y > enemy.y)
                enemy.y = enemy.y + 1;
            else
                enemy.y = enemy.y - 1;
        }
        return (player.x != enemy.x || player.y != enemy.y);
    }



    private static void UpdateScreen(Player player, Enemy enemy, Terminal terminal) throws InterruptedException {
        terminal.moveCursor(player.x,player.y);
        terminal.putCharacter('\u263a');
        terminal.moveCursor(enemy.x,enemy.y);
        terminal.putCharacter('X');
        terminal.moveCursor(0,0);

    }

    private static void MovePlayer(Player player, Terminal terminal) throws InterruptedException {
        //Wait for a key to be pressed
        Key key;
        do{
            Thread.sleep(5);
            key = terminal.readInput();
        }
        while(key == null);

        switch(key.getCharacter()+ " " + key.getKind()) {
            case "R ArrowRight":
                if(player.x<20)
               player.x= player.x+1;
                break;
            case "L ArrowLeft":
                if(player.x>0)
                player.x = player.x-1;
                break;
            case "U ArrowUp":
                if(player.y>0)
                player.y=player.y-1;
                break;
            case "D ArrowDown":
                if(player.y<20)
                player.y=player.y+1;

        }


    }

    public static void printString() {
        String gameOver = "Game Over!";
        int counter = 0;
        int x = 9;
        int y = 10;

        while(counter < gameOver.length()) {
            terminal.moveCursor(x++, y);
            terminal.putCharacter(gameOver.charAt(counter++));
            terminal.moveCursor(0,0);

        }

    }
}