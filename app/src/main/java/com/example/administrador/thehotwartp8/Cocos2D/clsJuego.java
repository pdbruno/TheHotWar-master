package com.example.administrador.thehotwartp8.Cocos2D;

import android.util.Log;
import android.view.MotionEvent;

import com.example.administrador.thehotwartp8.Cocos2D.Capas.CapaBackground;
import com.example.administrador.thehotwartp8.Cocos2D.Capas.CapaFrente;
import com.example.administrador.thehotwartp8.Cocos2D.Capas.CapaUI;
import com.example.administrador.thehotwartp8.Models.SoldadoPropio;

import org.cocos2d.actions.instant.CallFuncN;
import org.cocos2d.actions.interval.IntervalAction;
import org.cocos2d.actions.interval.MoveBy;
import org.cocos2d.actions.interval.MoveTo;
import org.cocos2d.actions.interval.ScaleBy;
import org.cocos2d.actions.interval.Sequence;
import org.cocos2d.layers.Layer;
import org.cocos2d.menus.Menu;
import org.cocos2d.menus.MenuItemImage;
import org.cocos2d.nodes.CocosNode;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCSize;

import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * Created by Administrador on 19/9/2017.
 */

public class clsJuego {
    CCGLSurfaceView _VistaDelJuego;
    public CCSize PantallaDelDispositivo;
    public Sprite Objeto;
    public Sprite Objeto2;
    Sprite SpriteTocado;
    Sprite DummySprite;

    public clsJuego(CCGLSurfaceView VistaDelJuego){
        Log.d("APA", "comienza el constructor");
        _VistaDelJuego = VistaDelJuego;
    }

    public void ComenzarJuego(){
        Log.d("APA", "comienza el juego");
        Director.sharedDirector().attachInView(_VistaDelJuego);
        PantallaDelDispositivo = Director.sharedDirector().displaySize();
        SoldadoPropio.ElSoldado = new SoldadoPropio();
        Director.sharedDirector().runWithScene(EscenaDelJuego());
    }

    private Scene EscenaDelJuego(){
        Scene EscenaADevolver;
        EscenaADevolver = Scene.node();
        CapaFrente miCapaFrente;
        CapaBackground miCapaBackground;
        CapaUI miCapaUI;
        miCapaFrente = new CapaFrente(PantallaDelDispositivo);
        miCapaBackground = new CapaBackground(PantallaDelDispositivo);
        miCapaUI = new CapaUI(PantallaDelDispositivo);
        EscenaADevolver.addChild(miCapaUI, 20);

        EscenaADevolver.addChild(miCapaFrente, 10);
        EscenaADevolver.addChild(miCapaBackground,-10);
        return EscenaADevolver;
    }





}