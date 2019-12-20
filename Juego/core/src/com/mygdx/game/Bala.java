package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Bala extends Thread {

    private Enemigo enemigo;


    public Bala(Enemigo enemigo) {
        this.enemigo = enemigo;
    }

    @Override
    public void run() {

        try
        {
            while(enemigo.isVisible()) {
                Thread.sleep(1500);
                enemigo.balas.add(new BalasEnemigas(enemigo.getX() + (enemigo.getWidth() / 2), enemigo.getY()));
            }
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }



    }


}
