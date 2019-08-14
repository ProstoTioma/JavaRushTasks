package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.Random;

public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private static final int GOAL = 28;
    private Snake snake;
    private int turnDelay;
    private Apple apple;
    private boolean isGameStopped;
    private int score;

    @Override
    public void initialize()
    {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void drawScene() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellValueEx(x, y, Color.AQUA, "");
            }
        }
        snake.draw(this);
        apple.draw(this);
    }
    private void createGame() {
        isGameStopped = false;
        score = 0;
        setScore(score);
        turnDelay = 300;
        snake = new Snake(7, 7);
        createNewApple();
        drawScene();
        setTurnTimer(turnDelay);
    }

    @Override
    public void onTurn(int step) {
        if(!apple.isAlive) {
            createNewApple();
            score += 5;
            setScore(score);
            turnDelay -= 10;
            setTurnTimer(turnDelay);
        }
        snake.move(apple);
        if(!snake.isAlive) gameOver();
        if(snake.getLength() > GOAL) win();
        drawScene();
    }

    private void gameOver() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.SILVER, "GAME OVER", Color.DARKRED, 100);
    }

    private void win() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.SILVER, "YOU WIN!", Color.DARKBLUE, 100);
    }

    @Override
    public void onKeyPress(Key key) {
        if(key == Key.SPACE && isGameStopped) createGame();
        if(key == Key.DOWN) snake.setDirection(Direction.DOWN);
        else if(key == Key.UP) snake.setDirection(Direction.UP);
        else if(key == Key.LEFT) snake.setDirection(Direction.LEFT);
        else if(key == Key.RIGHT) snake.setDirection(Direction.RIGHT);
    }

    private void createNewApple() {
        do {
            apple = new Apple(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
        } while (snake.checkCollision(apple));
    }
}
