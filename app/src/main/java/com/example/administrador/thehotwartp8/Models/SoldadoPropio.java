package com.example.administrador.thehotwartp8.Models;

import org.cocos2d.nodes.Sprite;

/**
 * Created by Administrador on 19/9/2017.
 */

public class SoldadoPropio extends Soldado {

    public static SoldadoPropio ElSoldado;

    public SoldadoPropio(){
        DañoPorBala = 10;
        Vida = 100;
        Imagen = Sprite.sprite("Stalin.png");
    }
}
