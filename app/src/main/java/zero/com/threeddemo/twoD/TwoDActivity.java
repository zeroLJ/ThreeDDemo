package zero.com.threeddemo.twoD;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

import zero.com.threeddemo.R;

public class TwoDActivity extends AppCompatActivity {
    private OpenGLView mOpenGLView;

    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // (NEW)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // (NEW)
        setContentView(R.layout.activity_two_d);

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
//        setContentView(mOpenGLView);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mOpenGLView.getRenderer().angle++;
            }
        },30,30);
    }

    public void one(View view) {
        mOpenGLView.getRenderer().setSelectId(1);
    }

    public void two(View view) {
        mOpenGLView.getRenderer().setSelectId(2);
    }

    public void three(View view) {
        mOpenGLView.getRenderer().setSelectId(3);
    }

    public void four(View view) {
        mOpenGLView.getRenderer().setSelectId(4);
    }
}
