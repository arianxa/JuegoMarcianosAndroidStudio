package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BalasEnemigas {

    private int x, y, speedX;
    private Texture texture;
    private boolean visible;
    private int width;
    private int height;
    private Colision colision;

    public BalasEnemigas(int startX, int startY){
        this.x = startX;
        this.y = startY;
        this.texture = new Texture("rectangle.png");
        this.width = (this.getTexture().getWidth());
        this.height = (this.getTexture().getHeight());
        this.speedX = 7;
        this.visible = true;
        this.colision = new Colision(x, y, width, height);
    }

    public void update(){
        y = y + speedX;
        if (y > Gdx.graphics.getHeight()){
            visible = false;
        }
        colision.move(x,y);
    }
    public void updateE(){
        y = y - speedX;
        if (y < 0){
            visible = false;
        }
        colision.move(x,y);
    }

    public Texture getTexture() {
        return texture;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeedX() {
        return speedX;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Colision getColision()
    {
        return colision;
    }
}
