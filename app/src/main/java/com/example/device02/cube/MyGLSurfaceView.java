package com.example.device02.cube;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.KeyEvent;
import android.view.MotionEvent;

import java.util.Vector;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/*
 * Custom GL view by extending GLSurfaceView so as
 * to override event handlers such as onKeyUp(), onTouchEvent()
 */
public class MyGLSurfaceView extends GLSurfaceView {
    MyGLRenderer renderer;    // Custom GL Renderer

    // For touch event
    private final float TOUCH_SCALE_FACTOR = 180.0f / 320.0f;
    private float previousX;
    private float previousY;
    private float verticalAngle;
    private float horizontalAngle;

    // Constructor - Allocate and set the renderer
    public MyGLSurfaceView(Context context) {
        super(context);
        renderer = new MyGLRenderer(context);
        this.setRenderer(renderer);
        // Request focus, otherwise key/button won't react
        this.requestFocus();
        this.setFocusableInTouchMode(true);
    }

    // Handler for key event
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent evt) {
        switch(keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:   // Decrease Y-rotational speed
                renderer.speedY -= 0.1f;
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:  // Increase Y-rotational speed
                renderer.speedY += 0.1f;
                break;
            case KeyEvent.KEYCODE_DPAD_UP:     // Decrease X-rotational speed
                renderer.speedX -= 0.1f;
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:   // Increase X-rotational speed
                renderer.speedX += 0.1f;
                break;
            case KeyEvent.KEYCODE_A:           // Zoom out (decrease z)
                renderer.z -= 0.2f;
                break;
            case KeyEvent.KEYCODE_Z:           // Zoom in (increase z)
                renderer.z += 0.2f;
                break;
        }
        return true;  // Event handled
    }

    // Handler for touch event
    @Override
    public boolean onTouchEvent(final MotionEvent evt) {

        float currentX = evt.getX();
        float currentY = evt.getY();

        int width  = this.getResources().getDisplayMetrics().widthPixels;
        int height = this.getResources().getDisplayMetrics().heightPixels;

        //rotation
        if(currentX>width/2)
        {
            this.horizontalAngle = (width / 4 - currentX);
            this.verticalAngle = (height / 2 - currentY);
            Vector3 direction = new Vector3((float)(cos(verticalAngle) * sin(horizontalAngle)), (float)(sin(verticalAngle)),(float)(cos(verticalAngle) * cos(horizontalAngle)));
            Vector3 right = new Vector3((float)(sin(horizontalAngle - 3.14f/2.0f)), (float)(0),(float)(    cos(horizontalAngle - 3.14f/2.0f)));
            Vector3 up = Vector3.cross(right,direction);

            float deltaX, deltaY;
        switch (evt.getAction()) {
            case MotionEvent.ACTION_MOVE:
                // Modify rotational angles according to movement
                deltaX = currentX - previousX;
                deltaY = currentY - previousY;
                renderer.angleX += deltaY * TOUCH_SCALE_FACTOR;
                renderer.angleY += deltaX * TOUCH_SCALE_FACTOR;
        }
    }
    //translation
    else
        {
            float step=0.2f;

            Vector3 position = new Vector3();
            position.setX(renderer.eyeX);
            position.setY(renderer.eyeY);
            position.setZ(renderer.eyeZ);

            Vector3 look = new Vector3();
            look.setX(renderer.centerX);
            look.setY(renderer.centerY);
            look.setZ(renderer.centerZ);

            Vector3 diff = Vector3.sub(look,position);

            if(currentY<previousY) {renderer.z += step;
                //Vector3 diffPosition = Vector3.scale(diff,step);renderer.eyeX+=diffPosition.getX();renderer.eyeY+=diffPosition.getY();renderer.eyeZ+=diffPosition.getZ();
            }
            else {renderer.z -= step;
                //Vector3 diffPosition = Vector3.scale(diff,-step);renderer.eyeX+=diffPosition.getX();renderer.eyeY+=diffPosition.getY();renderer.eyeZ+=diffPosition.getZ();
            }

        }


        // Save current x, y
        previousX = currentX;
        previousY = currentY;
        return true;  // Event handled
    }
}