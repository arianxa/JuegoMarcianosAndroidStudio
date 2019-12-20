package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class Colision {
    float x, y;
    int width,height;
    private int SCREEN_WIDTH = Gdx.graphics.getWidth();
    private int SCREEN_HEIGHT = Gdx.graphics.getHeight();

    public Colision (float x, float y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    public boolean collidesWith(Colision rect)
    {
        return x < rect.x + rect.width && y < rect.y + rect.height && x + width > rect.x && y + height > rect.y;
    }

    //para saber si colisiones con la pantalla
    public boolean ColisionDerecha()
    {
        return SCREEN_WIDTH <= this.x + this.width;
    }
    public boolean ColisionIzquierda()
    {
        return 0 >= this.x;
    }
    public boolean ColisionArriba()
    {
        return SCREEN_HEIGHT <= this.y + this.height;
    }
    public boolean ColisionAbajo()
    {
        return 0 >= this.y;
    }
}

