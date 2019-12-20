package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.ListIterator;

public class MyGdxGame extends ApplicationAdapter implements Runnable,InputProcessor {
	private SpriteBatch batch;
	private Texture img;
	private Texture fondo;
	private int x;
	private int y;


	private int SCREEN_WIDHT;
	private int SCREEN_HEIGHT;
    private Sprite pium;
    private Texture dibujoDisparo;
	private Sprite rectangle;
    private Sound sonidoBalaJugador;
    private Sound sonidoBalaEnemiga;
    private Music sonidoGameOVER;
    private Music MusicaFondo;
    private Sprite dibujoJugador;
    private Texture dibujoEnemigo;
    private int disparoY;
    private int disparoX;
    private ArrayList<BalasEnemigas> disparos;
    private ArrayList<BalasEnemigas> disparosE;
    private ArrayList<Enemigo> enemigos;
    private Jugador jugador;
    private BitmapFont TipoLetraSalut;
    private BitmapFont TipoLetraVidas;
    private BitmapFont TipoLetraGameOVER;
    private String TextoSalut;
    private String TextoVidas;
    private String TextoGameOver;







	@Override
	public void create () {

        Gdx.input.setInputProcessor(this);

        SCREEN_WIDHT = Gdx.graphics.getWidth();
        SCREEN_HEIGHT = Gdx.graphics.getHeight();

        batch = new SpriteBatch();

        img = new Texture("f.png");
     //   fondo = new Texture("fondo6.jpg");
        dibujoDisparo = new Texture("b.png");
        dibujoEnemigo = new Texture("p.png");
        sonidoBalaJugador = Gdx.audio.newSound(Gdx.files.internal("pium.wav"));
        sonidoBalaEnemiga = Gdx.audio.newSound(Gdx.files.internal("Explosion.wav"));
        sonidoGameOVER = Gdx.audio.newMusic(Gdx.files.internal("GAMEOVER.wav"));
        MusicaFondo=Gdx.audio.newMusic(Gdx.files.internal("MUSICA.wav"));
        MusicaFondo.setLooping(true);

       // sonidoGameOVER.setLooping(true);
        pium = new Sprite(dibujoDisparo, 0, 0, dibujoDisparo.getWidth(), dibujoDisparo.getHeight());
        jugador = new Jugador();
        dibujoJugador = new Sprite(jugador.getTexture(), 0, 0, jugador.getTexture().getWidth(), jugador.getTexture().getHeight());
        dibujoJugador.setX(jugador.getX());
        dibujoJugador.setY(jugador.getY());
        disparoX = (int)dibujoJugador.getX() + (int)(dibujoJugador.getWidth() / 2) - (int)(pium.getWidth()/2);
        disparoY = (int)dibujoJugador.getY()+ (int)dibujoJugador.getHeight();
        y = (int)dibujoJugador.getY()+ (int)dibujoJugador.getHeight();
        x = (int)dibujoJugador.getX() + (int)(dibujoJugador.getWidth() / 2) - (int)(pium.getWidth()/2);
        disparos = new ArrayList<BalasEnemigas>();
        disparosE = new ArrayList<BalasEnemigas>();
        enemigos = new ArrayList<Enemigo>();

        TipoLetraSalut = new BitmapFont();
        TextoSalut = "SALUD: ";
         TipoLetraVidas = new BitmapFont();
        TextoVidas = "VIDAS: ";
        TipoLetraGameOVER = new BitmapFont();
        TextoGameOver = "GAME OVER!!";

        Thread t = new Thread(this);
        t.start();

	}

	@Override
	public void render () {
        Gdx.gl.glClearColor(0, 0.5F, 0.5F, 0.2F);//Elegir el color  alpha es transparencia
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);//color solido

        batch.begin();
     //   batch.draw(fondo, 0, 0);


        TipoLetraSalut.getData().setScale(3);// Tamañi texto
        TipoLetraSalut.setColor(Color.SKY);//color
        TipoLetraSalut.draw(batch, TextoSalut + jugador.getSalut() + "  ||  " + TextoVidas + jugador.getvidas(), 10,35);

        TipoLetraGameOVER.getData().setScale(10);
        TipoLetraGameOVER.setColor(Color.SCARLET);

        if(jugador.isVisible()) {//ESTO SIRVE PARA QUE CUANDO SALGA EL GAMEOVER SE QUITE LA NAVE JUGADOR
            dibujoJugador.draw(batch);
            MusicaFondo.play();
        }

// PARA CONTROLAR donde sale la bala jugador
        disparoX = (int) dibujoJugador.getX() + (int) (dibujoJugador.getWidth() ) - (int) (pium.getWidth() ); //podria ser /2 los 2 withh
        disparoY = (int) dibujoJugador.getY() + (int) dibujoJugador.getHeight();

        ArrayList<Enemigo> EliminarEnemigos = new ArrayList<Enemigo>();
        ArrayList<BalasEnemigas> EliminarBalas;
//---------------Control para que salgan los enemigos y sus balas
        for(Enemigo e : enemigos)
        {
            EliminarBalas = new ArrayList<BalasEnemigas>();
            if (e.isVisible()) {
                Sprite bala = new Sprite(dibujoEnemigo, 0, 0, e.getTexture().getWidth(), e.getTexture().getHeight());
                e.update();
                bala.setX(e.getX());
                bala.setY(e.getY());
                bala.draw(batch);
                if(!e.getBalas().isEmpty())
                {
                    for (BalasEnemigas balaEnem : e.getBalas())
                    {

                        if (balaEnem.isVisible())
                        {
                            Sprite balaEnemiga = new Sprite(balaEnem.getTexture(), 0, 0, balaEnem.getTexture().getWidth(), balaEnem.getTexture().getHeight());
                            balaEnem.updateE();
                            balaEnemiga.setX(balaEnem.getX());
                            balaEnemiga.setY(balaEnem.getY());
                            batch.draw(dibujoDisparo,balaEnem.getX(),balaEnem.getY());

                            if (balaEnem.getColision().collidesWith(jugador.getColision()))
                            {
                                EliminarBalas.add(balaEnem);
                                jugador.setSalut(jugador.getSalut() - 1);
                                jugador.calcularVidas();
                                if (jugador.getSalut() <= 0)
                                {
                                    jugador.setVisible(false);
                                }
                            }

                        } else
                        {
                            EliminarBalas.add(balaEnem);
                        }
                    }
                    ArrayList<BalasEnemigas> balasEnemigas = e.getBalas();
                    balasEnemigas.removeAll(EliminarBalas);
                }

            } else {
                EliminarEnemigos.add(e);
            }

        }
        ArrayList<BalasEnemigas> EliminarBala = new ArrayList<BalasEnemigas>();
        for(BalasEnemigas b : disparos) {
            if (b.isVisible()) {
                Sprite bullet = new Sprite(b.getTexture(), 0, 0, b.getTexture().getWidth(), b.getTexture().getHeight());
                b.update();
                bullet.setX(b.getX());
                bullet.setY(b.getY());

                bullet.draw(batch);
            } else {
                EliminarBala.add(b);
            }
        }


//------------Control de colisiones de balas

        ListIterator<BalasEnemigas> itB2 = disparos.listIterator();

        while (itB2.hasNext()) {
            BalasEnemigas b1 = itB2.next();
            {
                ListIterator<Enemigo> itE = enemigos.listIterator();

                while (itE.hasNext()) {
                    Enemigo e1 = itE.next();
                    if (b1.getColision().collidesWith(e1.getColision())) {
                        EliminarBala.add(b1);
                        e1.setvides(e1.getvides() - 1);
                        sonidoBalaEnemiga.play();
                        if(e1.getvides() <= 0) {
                            EliminarEnemigos.add(e1);
                            sonidoBalaEnemiga.play();

                        }
                    }
                }
            }
        }
        for(Enemigo e : EliminarEnemigos)
        {
            disparosE.addAll(e.getBalas());
        }
//------------Control de balas de enemigos eliminados

        ArrayList<BalasEnemigas>EliminarBalasEnemigos = new ArrayList<BalasEnemigas>();
        for(BalasEnemigas bE : disparosE) {
            if (bE.isVisible()) {
                Sprite bulletE = new Sprite(bE.getTexture(), bE.getX(), bE.getY(), bE.getTexture().getWidth(), bE.getTexture().getHeight());
                bE.updateE();
                bulletE.setX(bE.getX());
                bulletE.setY(bE.getY());
                batch.draw(dibujoDisparo,bE.getX(),bE.getY());

                    if (bE.getColision().collidesWith(jugador.getColision())) {
                        EliminarBalasEnemigos.add(bE);
                        jugador.setSalut(jugador.getSalut() - 1);
                        jugador.calcularVidas();
                        if(jugador.getSalut() <= 0) {
                            jugador.setVisible(false);
                        }
                    }

            } else {
                EliminarBalasEnemigos.add(bE);
            }
        }
//-------------Control de choque entre jugador y enemigo
        ListIterator<Enemigo> itEn = enemigos.listIterator();

        while (itEn.hasNext()) {
            Enemigo e2 = itEn.next();
            if (jugador.getColision().collidesWith(e2.getColision())) {
                e2.setvides(0);
                if(e2.getvides() <= 0) {
                    EliminarEnemigos.add(e2);
                }
                jugador.setSalut(0);
                jugador.setvides(0);
                jugador.setVisible(false);
            }
        }

        if(jugador.isVisible()) {
            enemigos.removeAll(EliminarEnemigos);
            disparos.removeAll(EliminarBala);
            disparosE.removeAll(EliminarBalasEnemigos);
        }
        else
        {
            enemigos = new ArrayList<Enemigo>();
            disparos = new ArrayList<BalasEnemigas>();
            disparosE = new ArrayList<BalasEnemigas>();
        }
        if(!jugador.isVisible())
        {MusicaFondo.pause();
            GlyphLayout layout = new GlyphLayout(TipoLetraGameOVER, TextoGameOver);

            float fontX = layout.width;
            TipoLetraGameOVER.draw(batch, layout, (SCREEN_WIDHT /2)  - (fontX / 2),SCREEN_HEIGHT/2);
            sonidoGameOVER.play();



        }

        batch.end();






	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}




	@Override
	public boolean keyDown(int keycode)
	{
		int novaPos;

		switch(keycode)
		{
			case Input.Keys.DPAD_RIGHT:


                novaPos =(int) dibujoJugador.getX() + 10;
                if(novaPos <= SCREEN_WIDHT - dibujoJugador.getWidth())
                {
                    dibujoJugador.setX(novaPos);
                    jugador.setX(novaPos);
                    jugador.update();
                }

				break;

			case Input.Keys.DPAD_LEFT:
                novaPos = (int)dibujoJugador.getX() - 10;
                if(novaPos >= 0)
                {
                    dibujoJugador.setX(novaPos);
                    jugador.setX(novaPos);
                    jugador.update();
                }


				break;

			case Input.Keys.DPAD_UP:

				break;

			case Input.Keys.DPAD_DOWN:

				break;
			case Input.Keys.SPACE:

                    BalasEnemigas disparo = new BalasEnemigas( disparoX, disparoY);
                    disparos.add(disparo);
                    sonidoBalaJugador.play();

		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		return false;
	}

	@Override
	public boolean keyTyped(char character)
	{
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		screenY = SCREEN_HEIGHT - screenY;

		if(screenX > SCREEN_WIDHT - rectangle.getWidth() / 2)
		{
			screenX = SCREEN_WIDHT - (int) rectangle.getWidth() / 2;
		}

		if(screenX < rectangle.getWidth() / 2)
		{
			screenX = (int) rectangle.getWidth() / 2;
		}

		rectangle.setX(screenX - rectangle.getWidth() / 2);

		if(screenY > SCREEN_HEIGHT - rectangle.getHeight() / 2)
		{
			screenY = SCREEN_HEIGHT - (int) rectangle.getHeight() / 2;
		}

		if(screenY < rectangle.getHeight() / 2)
		{
			screenY = (int) rectangle.getHeight() / 2;
		}

		rectangle.setY(screenY - rectangle.getHeight() / 2);

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{
		return false;
	}

    @Override
    public void run()
    {boolean start=true;
        while(start) {
            try {

                //Para controlar cuantos enemigos salen
                if (enemigos.size() < 7) {

                    Enemigo e = new Enemigo(SCREEN_WIDHT / 2, (int) (SCREEN_HEIGHT / 1.2));
                    enemigos.add(e);
                    Bala b = new Bala(e);
                  b.start();

                    Thread.sleep(1000);//la velociadad a la que sale el alien
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
