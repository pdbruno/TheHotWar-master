package com.example.administrador.thehotwartp8.Models;

import android.util.Log;

import org.cocos2d.actions.interval.MoveBy;
import org.cocos2d.actions.interval.MoveTo;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.types.CCSize;

import java.util.ArrayList;

/**
 * Created by Administrador on 19/9/2017.
 */

public class Soldado {
    public int Vida;
    public Sprite Imagen;
    public int DañoPorBala;
    public ArrayList<Bala> ArrayBalasActivas;

    public Bala CalcularDisparo(float PosXDisparo, float PosYDisparo, CCSize PantallaDelDispositivo) {
        Log.d("Diparo soldado","Se ejecuto el despiaro");
        float PosX = Imagen.getPositionX();
        float PosY = Imagen.getPositionY();

        float[] Coord = CalcularLineal(PosXDisparo, PosYDisparo, PantallaDelDispositivo);

        Bala LaBala = new Bala();
        LaBala.PosicionarComienzo(PosX, PosY);

        float Hipotenusa = (float) Math.sqrt(Math.pow(Coord[0] - PosX, 2) + Math.pow(Coord[1] - PosY, 2));


        LaBala.PosXFinal = Coord[0];
        LaBala.PosYFinal = Coord[1];
        LaBala.Duracion = Hipotenusa / 1000;
        return LaBala;
    }

    private float[] CalcularLineal(float PosXDisparo, float PosYDisparo, CCSize PantallaDelDispositivo){
        float PosXPer = Imagen.getPositionX();
        float PosYPer = Imagen.getPositionY();
        float deltaX = PosXDisparo - PosXPer;
        float deltaY = PosYDisparo - PosYPer;
        float M = deltaY/deltaX;

        int i = 1;
        float PosibleY;
        float PosibleX;

        while (true){
            i++;
            PosibleY = M * (deltaX * 10);//ACA SE DEBE USAR LA I PARA ASEGURAR EL BORRADO DE LA BALA DE LA CAPA, PERO NO ANDA
            PosibleX = deltaX * 10;//ACA SE DEBE USAR LA I PARA ASEGURAR EL BORRADO DE LA BALA DE LA CAPA, PERO NO ANDA

            if (PosibleY > PantallaDelDispositivo.getHeight() || PosibleY < 0){
                break;
            }
            if (PosibleX > PantallaDelDispositivo.getWidth() || PosibleX < 0){
                break;
            }
        }

        float[] Coord = new float[2];
        Coord[0] = PosibleX;
        Coord[1] = PosibleY;

        return Coord;
    }

    public void BajarVida(Soldado Victimario){
        this.Vida -= Victimario.DañoPorBala;
    }
    public boolean VerificarVida(){
        if(Vida < 0){
            return true;
        }else{
            return false;
        }
    }

}
