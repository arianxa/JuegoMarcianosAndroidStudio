package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Jugador {
    private int x, y;
    private Texture texture;
    private boolean visible;
    private int width;
    private int height;
    private Colision colision;
    private int vidas;
    private int salut;
    private final int MAX_VIDES = 3;
    private final int MAX_SALUT = 100;
    public static final int SCREEN_WIDTH = Gdx.graphics.getWidth();
    public static final int SCREEN_HEIGHT = Gdx.graphics.getHeight();

    public Jugador(){
        this.texture = new Texture("f.png");
        this.x = (SCREEN_WIDTH - texture.getWidth()) / 2;
        this.y = SCREEN_HEIGHT /10;
        this.vidas = MAX_VIDES;
        this.salut = MAX_SALUT;
        this.visible = true;
        this.width = this.getTexture().getWidth();
        this.height = this.getTexture().getHeight();
        this.colision = new Colision(x, y, width, height);
    }

    public void update(){

        colision.move(x,y);
    }

    public int getvidas() {
        return vidas;
    }

    public void setvides(int vidas) {
        this.vidas = vidas;
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


    public boolean isVisible() {
        return visible;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSalut() {
        return salut;
    }

    public void setSalut(int salut) {
        this.salut = salut;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public Colision getColision()
    {
        return colision;
    }
    public void calcularVidas()
    {
        if(this.getSalut() == 0)
        {
            if(this.getvidas() > 0)//cuando tu salud llegue a 0 si tienes mas de 0 vidas  se te resta una vida y vuelves a tener max sald
            {
                this.setSalut(MAX_SALUT);
                this.setvides(this.getvidas()-1);
            }
            else
            {
                this.setvides(0);
            }
        }
    }
}
