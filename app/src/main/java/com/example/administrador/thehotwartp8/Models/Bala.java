package com.example.administrador.thehotwartp8.Models;

import org.cocos2d.actions.interval.MoveTo;
import org.cocos2d.nodes.Sprite;

import java.util.ArrayList;

/**
 * Created by 42077426 on 26/10/2017.
 */

public class Bala {
    public Sprite Imagen;
    public float PosXFinal;
    public float PosYFinal;
    public float Duracion;

    public Bala (){
        Imagen = Sprite.sprite("Disparo.png");
    }

    public void PosicionarComienzo(float PosX, float PosY){
        Imagen.setPosition(PosX,PosY);
    }



}
