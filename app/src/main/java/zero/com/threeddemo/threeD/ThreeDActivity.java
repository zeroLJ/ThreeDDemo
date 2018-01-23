package zero.com.threeddemo.threeD;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

import zero.com.threeddemo.R;

public class ThreeDActivity extends AppCompatActivity {
    private OpenGLView mOpenGLView;

    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_d);
        mOpenGLView = new OpenGLView(this);
        linearLayout = findViewById(R.id.layout);
        linearLayout.addView(mOpenGLView);

        mOpenGLView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mOpenGLView.getRenderer().setColor( event.getX()/(event.getX()+100),event.getY()/(event.getY()+100),event.getX()/(event.getX()+100));
                return false;
            }
        });
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mOpenGLView.getRenderer().count++;
                if (mOpenGLView.getRenderer().count>64){
                    mOpenGLView.getRenderer().count = 0;
                }
            }
        },150,150);
    }
}
