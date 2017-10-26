package com.example.administrador.thehotwartp8;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.administrador.thehotwartp8.Cocos2D.clsJuego;

import org.cocos2d.opengl.CCGLSurfaceView;

public class MainActivity extends Activity {
    CCGLSurfaceView VistaPrincipal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VistaPrincipal = new CCGLSurfaceView(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(VistaPrincipal);
    }
    @Override
    protected void onStart() {
        super.onStart();
        clsJuego miJuego;
        miJuego = new clsJuego(VistaPrincipal);
        miJuego.ComenzarJuego();
    }
}
