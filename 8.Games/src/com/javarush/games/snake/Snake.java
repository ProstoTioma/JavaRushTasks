package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Snake {
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    private int x, y;
    private List<GameObject> snakeParts = new ArrayList<>();
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;


    public Snake(int x, int y) {
        this.x = x;
        this.y = y;
        GameObject gameObject1 = new GameObject(x, y);
        GameObject gameObject2 = new GameObject(x + 1, y);
        GameObject gameObject3 = new GameObject(x + 2, y);
        Collections.addAll(snakeParts, gameObject1, gameObject2, gameObject3);
    }

    public void setDirection(Direction direction) {
        boolean isOver = false;
        if (direction == Direction.LEFT && snakeParts.get(0).y == snakeParts.get(1).y) isOver = true;
        if (direction == Direction.RIGHT && snakeParts.get(0).y == snakeParts.get(1).y) isOver = true;
        if (direction == Direction.UP && snakeParts.get(0).x == snakeParts.get(1).x) isOver = true;
        if (direction == Direction.DOWN && snakeParts.get(0).x == snakeParts.get(1).x) isOver = true;
        if (!isOver) {
            if (this.direction == Direction.UP && direction != Direction.DOWN)
                this.direction = direction;
            if (this.direction == Direction.DOWN && direction != Direction.UP)
                this.direction = direction;
            if (this.direction == Direction.LEFT && direction != Direction.RIGHT)
                this.direction = direction;
            if (this.direction == Direction.RIGHT && direction != Direction.LEFT)
                this.direction = direction;
        }
    }

    public void move(Apple apple) {
        boolean remove = true;
        GameObject newHead = createNewHead();
        if (!checkCollision(newHead)) {
            if (newHead.x >= SnakeGame.WIDTH || newHead.y >= SnakeGame.HEIGHT || newHead.x < 0 || newHead.y < 0) {
                isAlive = false;
            } else if (newHead.x == apple.x && newHead.y == apple.y) {
                apple.isAlive = false;
                remove = false;
            }
            snakeParts.add(0, newHead);

            if (remove)
                removeTail();
        } else isAlive = false;
    }

    public int getLength() {
        return snakeParts.size();
    }

    public void draw(Game game) {

        if (!isAlive) {
            for (int i = 0; i < snakeParts.size(); i++)
                if (i == 0) {
                    game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, HEAD_SIGN, Color.RED, 75);
                } else
                    game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, Color.RED, 75);

        } else {
            for (int i = 0; i < snakeParts.size(); i++)
                if (i == 0) {
                    game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, HEAD_SIGN, Color.GREEN, 75);
                } else
                    game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, Color.GREEN, 75);
        }
    }

    public GameObject createNewHead() {
        int headX = snakeParts.get(0).x;
        int headY = snakeParts.get(0).y;
        if (direction == Direction.LEFT) return new GameObject(headX - 1, headY);
        else if (direction == Direction.RIGHT) return new GameObject(headX + 1, headY);
        else if (direction == Direction.DOWN) return new GameObject(headX, headY + 1);
        else if (direction == Direction.UP) return new GameObject(headX, headY - 1);
        return null;
    }

    public void removeTail() {
        snakeParts.remove(snakeParts.get(snakeParts.size() - 1));
    }

    public boolean checkCollision(GameObject gameObject) {
        for (GameObject gameObject1 : snakeParts)
            if (gameObject.x == gameObject1.x && gameObject.y == gameObject1.y) return true;
        return false;
    }
}
