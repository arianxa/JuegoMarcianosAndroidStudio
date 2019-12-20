package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Random;

public class Enemigo {
    private int x, y, speedX, speedY;
    private Texture texture;
    private boolean visible;
    private int width;
    private int height;
    private Colision colision;
    private int vides;
    private String direction;
    private String directionV;
    public ArrayList<BalasEnemigas> balas;

    public Enemigo(int startX, int startY){
        this.x = startX;
        this.y = startY;
        this.texture = new Texture("p.png");
        this.balas = new ArrayList<BalasEnemigas>();
        this.vides = 2;
        this.visible = true;
        this.width = this.getTexture().getWidth();
        this.height = this.getTexture().getHeight();
        this.colision = new Colision(x, y, width, height);
        Random randVX = new Random();// ES PARA QUE LAS NAVES ENEMIGAS VAYAN A DIF VELOCIDADES
        int vX = randVX.nextInt(3);
        this.speedX = vX + 1;
        int vY = randVX.nextInt(2);
        this.speedY = vY + 1;
        Random rand = new Random();
        int n = rand.nextInt(2);
        if(n == 0)
        {
            this.direction = "Right";
        }
        else
        {
            this.direction = "Left";
        }
        this.directionV = "Down";


    }

    public void update(){
        if(this.colision.ColisionAbajo())
        {
            this.directionV = "Up";
        }
        else if(this.colision.ColisionArriba())
        {
            this.directionV = "Down";
        }
        if(this.colision.ColisionDerecha())
        {
            this.direction = "Left";
        }
        else if(this.colision.ColisionIzquierda())
        {
            this.direction = "Right";
        }

        if(this.direction.equals("Left"))
        {
            x = x - speedX;
        }
        else if(this.direction.equals("Right"))
        {
            x = x + speedX;
        }
        if(this.directionV.equals("Up"))
        {
            y = y + speedY;
        }
        else if(this.directionV.equals("Down"))
        {
            y = y - speedY;
        }

        colision.move(x,y);
    }

    public int getvides() {
        return vides;
    }

    public void setvides(int vides) {
        this.vides = vides;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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

    public ArrayList<BalasEnemigas> getBalas()
    {
        return balas;
    }

    public void setBalas(ArrayList<BalasEnemigas> balas)
    {
        this.balas = balas;
    }

    public String getDirectionV() {
        return directionV;
    }

    public void setDirectionV(String directionV) {
        this.directionV = directionV;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public Colision getColision()
    {
        return colision;
    }
}
