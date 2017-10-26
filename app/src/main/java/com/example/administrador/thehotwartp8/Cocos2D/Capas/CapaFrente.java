package com.example.administrador.thehotwartp8.Cocos2D.Capas;

import android.util.Log;
import android.view.MotionEvent;

import com.example.administrador.thehotwartp8.Models.Bala;
import com.example.administrador.thehotwartp8.Models.SoldadoEnemigo;
import com.example.administrador.thehotwartp8.Models.SoldadoPropio;

import org.cocos2d.actions.instant.CallFuncN;
import org.cocos2d.actions.interval.IntervalAction;
import org.cocos2d.actions.interval.MoveBy;
import org.cocos2d.actions.interval.MoveTo;
import org.cocos2d.actions.interval.Sequence;
import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.CocosNode;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.types.CCSize;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Martin on 24/10/2017.
 */

public class CapaFrente extends Layer {
    CCSize PantallaDelDispositivo;
    ArrayList<Bala> ArrayBalasActivas;
    SoldadoEnemigo Murica;

    public CapaFrente(CCSize PantallaDelDispositivo) {
        this.PantallaDelDispositivo = PantallaDelDispositivo;
        this.setIsTouchEnabled(true);
        Murica = new SoldadoEnemigo();
        ArrayBalasActivas = new ArrayList<Bala>();
        PonerObjetoPosInicial(SoldadoPropio.ElSoldado.Imagen);

        PonerObjetoPosInicial(Murica.Imagen);
        while(InterseccionEntreSprites(SoldadoPropio.ElSoldado.Imagen, Murica.Imagen)){
            PonerObjetoPosInicial(Murica.Imagen);
        }
        super.schedule("DetectarColisiones", 0.01f);

    }
    @Override
    public boolean ccTouchesBegan(MotionEvent event) {
        Bala LaBala = SoldadoPropio.ElSoldado.CalcularDisparo(event.getX(),PantallaDelDispositivo.getHeight() - event.getY(), PantallaDelDispositivo);

        IntervalAction Disparo;

        MoveTo Mover = MoveTo.action(LaBala.Duracion, LaBala.PosXFinal, LaBala.PosYFinal);
        CallFuncN RemoverDeCapa = CallFuncN.action(this, "RemoverDeCapa");

        Disparo = Sequence.actions(Mover, RemoverDeCapa);

        super.addChild(LaBala.Imagen);
        ArrayBalasActivas.add(LaBala);
        LaBala.Imagen.runAction(Disparo);
        ArrayBalasActivas.remove(LaBala);
        LaBala = null;
        return true;
    }

    public void DetectarColisiones(float UltLlamada) {
        //en un futuro tambien habria un for each recorriendo una lista de enemigos
        if (ArrayBalasActivas.size() > 0) {
            Log.d("Colision", "Apa");

            for (Bala CarlitosBala: ArrayBalasActivas){
                if (InterseccionEntreSprites(CarlitosBala.Imagen, Murica.Imagen)){
                    super.removeChild(CarlitosBala.Imagen, true);

                    ArrayBalasActivas.remove(CarlitosBala);
                    CarlitosBala = null;
                }
            }
        }

    }

    public void RemoverDeCapa(CocosNode ObjetoLlamador){
        super.removeChild(ObjetoLlamador, true);
    }

    public boolean ccTouchesMoved(MotionEvent event) {

        return true;
    }
    public boolean ccTouchesEnded(MotionEvent event) {

        return true;
    }

    public float[] PonerObjetoPosInicial(Sprite Objeto){
        float[] Poss = new float[2];
        Random GeneradorDeAzar = new Random();
        int PosX, PosY;

        PosX = GeneradorDeAzar.nextInt((int) (PantallaDelDispositivo.width));
        PosY = GeneradorDeAzar.nextInt((int)(PantallaDelDispositivo.height));
        Objeto.setPosition(PosX, PosY);

        Poss[0] = PosX;
        Poss[1] = PosY;

        super.addChild(Objeto);
        return Poss;
    }

    boolean InterseccionEntreSprites (Sprite Sprite1, Sprite Sprite2) {
        boolean Devolver;
        Devolver=false;
        int Sprite1Izquierda, Sprite1Derecha, Sprite1Abajo, Sprite1Arriba;
        int Sprite2Izquierda, Sprite2Derecha, Sprite2Abajo, Sprite2Arriba;
        Sprite1Izquierda=(int) (Sprite1.getPositionX() - Sprite1.getWidth()/2);
        Sprite1Derecha=(int) (Sprite1.getPositionX() + Sprite1.getWidth()/2);
        Sprite1Abajo=(int) (Sprite1.getPositionY() - Sprite1.getHeight()/2);
        Sprite1Arriba=(int) (Sprite1.getPositionY() + Sprite1.getHeight()/2);
        Sprite2Izquierda=(int) (Sprite2.getPositionX() - Sprite2.getWidth()/2);
        Sprite2Derecha=(int) (Sprite2.getPositionX() + Sprite2.getWidth()/2);
        Sprite2Abajo=(int) (Sprite2.getPositionY() - Sprite2.getHeight()/2);
        Sprite2Arriba=(int) (Sprite2.getPositionY() + Sprite2.getHeight()/2);
        //Borde izq y borde inf de Sprite 1 está dentro de Sprite 2
        if (EstaEntre(Sprite1Izquierda, Sprite2Izquierda, Sprite2Derecha) &&
                EstaEntre(Sprite1Abajo, Sprite2Abajo, Sprite2Arriba)) {
            Log.d("Interseccion", "1");
            Devolver=true;
        }
//Borde izq y borde sup de Sprite 1 está dentro de Sprite 2
        if (EstaEntre(Sprite1Izquierda, Sprite2Izquierda, Sprite2Derecha) &&
                EstaEntre(Sprite1Arriba, Sprite2Abajo, Sprite2Arriba)) {
            Log.d("Interseccion", "2");
            Devolver=true;
        }
//Borde der y borde sup de Sprite 1 está dentro de Sprite 2
        if (EstaEntre(Sprite1Derecha, Sprite2Izquierda, Sprite2Derecha) &&
                EstaEntre(Sprite1Arriba, Sprite2Abajo, Sprite2Arriba)) {
            Log.d("Interseccion", "3");
            Devolver=true;
        }
//Borde der y borde inf de Sprite 1 está dentro de Sprite 2
        if (EstaEntre(Sprite1Derecha, Sprite2Izquierda, Sprite2Derecha) &&
                EstaEntre(Sprite1Abajo, Sprite2Abajo, Sprite2Arriba)) {
            Log.d("Interseccion", "4");
            Devolver=true;
        }
//Borde izq y borde inf de Sprite 2 está dentro de Sprite 1
        if (EstaEntre(Sprite2Izquierda, Sprite1Izquierda, Sprite1Derecha) &&
                EstaEntre(Sprite2Abajo, Sprite1Abajo, Sprite1Arriba)) {
            Log.d("Interseccion", "5");
            Devolver=true;
        }

//Borde izq y borde sup de Sprite 1 está dentro de Sprite 1
        if (EstaEntre(Sprite2Izquierda, Sprite1Izquierda, Sprite1Derecha) &&
                EstaEntre(Sprite2Arriba, Sprite1Abajo, Sprite1Arriba)) {
            Log.d("Interseccion", "6");
            Devolver=true;
        }
//Borde der y borde sup de Sprite 2 está dentro de Sprite 1
        if (EstaEntre(Sprite2Derecha, Sprite1Izquierda, Sprite1Derecha) &&
                EstaEntre(Sprite2Arriba, Sprite1Abajo, Sprite1Arriba)) {
            Log.d("Interseccion", "7");
            Devolver=true;
        }
//Borde der y borde inf de Sprite 2 está dentro de Sprite 1
        if (EstaEntre(Sprite2Derecha, Sprite1Izquierda, Sprite1Derecha) &&
                EstaEntre(Sprite2Abajo, Sprite1Abajo, Sprite1Arriba)) {
            Log.d("Interseccion", "8");
            Devolver=true;
        }
        return Devolver;
    }

    boolean EstaEntre(int NumeroAComparar,int NumeroMenor, int NumeroMayor){
        boolean Devolver;

        if(NumeroMenor > NumeroMayor){
            int Auxiliar;
            Auxiliar = NumeroMayor;
            NumeroMayor = NumeroMenor;
            NumeroMenor = Auxiliar;
        }
        if(NumeroAComparar >= NumeroMenor && NumeroAComparar <= NumeroMayor){
            Devolver = true;
        }else{
            Devolver = false;
        }
        return Devolver;
    }


}