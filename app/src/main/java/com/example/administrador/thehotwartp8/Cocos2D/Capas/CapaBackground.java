package com.example.administrador.thehotwartp8.Cocos2D.Capas;

import org.cocos2d.actions.interval.ScaleBy;
import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.types.CCSize;

/**
 * Created by Martin on 24/10/2017.
 */

public class CapaBackground extends Layer {
    Sprite ImagenFondo;
    CCSize PantallaDelDispositivo;
    public CapaBackground(CCSize PantallaDelDispositivo){
        this.PantallaDelDispositivo = PantallaDelDispositivo;
        PonerFondo();
    }
    private void PonerFondo(){
        ImagenFondo = Sprite.sprite("Background.jpg");
        ImagenFondo.setPosition(PantallaDelDispositivo.width/2,PantallaDelDispositivo.height/2);

        Float FactorX,FactorY;
        FactorX = PantallaDelDispositivo.width / ImagenFondo.getWidth();
        FactorY = PantallaDelDispositivo.height / ImagenFondo.getHeight();

        ImagenFondo.runAction(ScaleBy.action(0.1f,FactorX,FactorY));

        super.addChild(ImagenFondo);
    }
}