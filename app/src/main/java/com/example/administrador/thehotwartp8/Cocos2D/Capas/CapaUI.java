package com.example.administrador.thehotwartp8.Cocos2D.Capas;

import android.util.Log;

import com.example.administrador.thehotwartp8.Cocos2D.clsJuego;
import com.example.administrador.thehotwartp8.Models.SoldadoPropio;

import org.cocos2d.actions.interval.MoveBy;
import org.cocos2d.layers.Layer;
import org.cocos2d.menus.Menu;
import org.cocos2d.menus.MenuItemImage;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.types.CCSize;

/**
 * Created by Martin on 24/10/2017.
 */

public class CapaUI extends Layer {
    CCSize PantallaDelDispositivo;

    public CapaUI(CCSize PantallaDelDispositivo){
        this.PantallaDelDispositivo = PantallaDelDispositivo;

        float[] Posiciones = CalcularPosicionesBotonesMoverse();

        MenuItemImage BotonIzquierda, BotonDerecha, BotonArriba, BotonAbajo;

        BotonIzquierda=PonerBotonMover(Posiciones[0] - 100,Posiciones[1],"PresionarIzquierda");
        BotonAbajo=PonerBotonMover(Posiciones[0],Posiciones[1] + 100,"PresionarAbajo");
        BotonArriba=PonerBotonMover(Posiciones[0],Posiciones[1] - 100,"PresionarArriba");
        BotonDerecha=PonerBotonMover(Posiciones[0] + 100,Posiciones[1],"PresionarDerecha");

        Menu MenuDeBotones;

        MenuDeBotones=Menu.menu(BotonAbajo, BotonArriba, BotonDerecha, BotonIzquierda);
        MenuDeBotones.setPosition(0, 0);
        super.addChild(MenuDeBotones);

        //no anda esta funcion PonerCorazonEsquina();
    }
    private float[] CalcularPosicionesBotonesMoverse() {
        float[] Posiciones = new float[2];
        Posiciones[0] = PantallaDelDispositivo.getHeight()/(float)4;
        Posiciones[1] = PantallaDelDispositivo.getWidth()/(float)5;
        return Posiciones;
    }
    public MenuItemImage PonerBotonMover(float PosX,float PosY,String NombreMetodoPresionar){
        MenuItemImage BotonMovimiento;
        Log.d("Boton presionado","Se creo el boton --- " + NombreMetodoPresionar);
        BotonMovimiento = MenuItemImage.item("BotonMover.png","BotonMoverPresionado.png", this, NombreMetodoPresionar);
        BotonMovimiento.scale((float)0.25);
        BotonMovimiento.setPosition(PosX,PosY);
        return  BotonMovimiento;
    }
    public void PonerCorazonEsquina(){
        Sprite Corazon;
        Corazon = Sprite.sprite("Corazon.png");
        Corazon.scale((float)0.25);
        Corazon.setPosition(PantallaDelDispositivo.getHeight()/(float)-10,PantallaDelDispositivo.getWidth()/(float)-10);
        super.addChild(Corazon);
    }
    public void PresionarArriba(){
        Log.d("Boton presionado","Se presiono el boton arr");
        SoldadoPropio.ElSoldado.Imagen.runAction(MoveBy.action(1,0,-150));
    }
    public void PresionarAbajo(){
        Log.d("Boton presionado","Se presiono el boton abj");
        SoldadoPropio.ElSoldado.Imagen.runAction(MoveBy.action(1,0,150));
    }
    public void PresionarIzquierda(){
        Log.d("Boton presionado","Se presiono el boton izq");
        SoldadoPropio.ElSoldado.Imagen.runAction(MoveBy.action(1,-150,0));
    }
    public void PresionarDerecha(){
        Log.d("Boton presionado","Se presiono el boton der");
        SoldadoPropio.ElSoldado.Imagen.runAction(MoveBy.action(1,150,0));
    }


}
