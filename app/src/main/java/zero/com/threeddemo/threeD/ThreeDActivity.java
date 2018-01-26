package zero.com.threeddemo.threeD;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

import zero.com.threeddemo.R;

public class ThreeDActivity extends AppCompatActivity {
    private OpenGLView mOpenGLView;
    private GestureDetector gestureDetector;
    private LinearLayout linearLayout;
    private float x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_d);
        mOpenGLView = new OpenGLView(this);
        linearLayout = findViewById(R.id.layout);
        linearLayout.addView(mOpenGLView);
        gestureDetector = new GestureDetector(this, onGestureListener);
        mOpenGLView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mOpenGLView.getRenderer().setColor(event.getX() / (event.getX() + 100), event.getY() / (event.getY() + 100), event.getX() / (event.getX() + 100));
//                mOpenGLView.getRenderer().setColor(0.91015f,0.91015f,0.91015f);
                return false;
            }
        });
//        mOpenGLView.getRenderer().count = 64;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mOpenGLView.getRenderer().count++;
                if (mOpenGLView.getRenderer().count > 64) {
                    mOpenGLView.getRenderer().count = 0;
                }
            }
        }, 150, 150);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private GestureDetector.OnGestureListener onGestureListener = new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            x = x + (motionEvent1.getX() - motionEvent.getX())/80;
            y = y + (motionEvent1.getY() - motionEvent.getY())/80;
            if (y > 30) y = 30;
            if (y< -80) y = -80;
            mOpenGLView.getRenderer().setRotatef(x, y);
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }
    };

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
