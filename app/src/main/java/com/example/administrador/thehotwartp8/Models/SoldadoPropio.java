package com.example.administrador.thehotwartp8.Models;

import org.cocos2d.nodes.Sprite;

import java.util.ArrayList;

/**
 * Created by Administrador on 19/9/2017.
 */

public class SoldadoPropio extends Soldado {

    public static SoldadoPropio ElSoldado;

    public SoldadoPropio(){
        ArrayBalasActivas = new ArrayList<Bala>();
        DañoPorBala = 10;
        Vida = 100;
        Imagen = Sprite.sprite("Stalin.png");
    }
}
