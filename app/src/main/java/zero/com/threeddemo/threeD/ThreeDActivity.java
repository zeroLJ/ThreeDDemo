package zero.com.threeddemo.threeD;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import zero.com.threeddemo.ObjUtils;
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
                if (mOpenGLView.getRenderer().count > 224) {
                    mOpenGLView.getRenderer().count = 0;
                }
            }
        }, 150, 150);
        set();
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

    public void one(View view) {mOpenGLView.getRenderer().setSelectId(1);
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

    public void five(View view) {
        mOpenGLView.getRenderer().setSelectId(5);
    }

    
    private void set(){
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("step","1");
        map.put("SKUID", "78017338");
        map.put("zone", "原点");
        map.put("scheme", "方案一");
        map.put("num","75");
        map.put("lengthNum","5");
        map.put("widthNum","5");
        map.put("heightNum","3");
        map.put("SKULength","1.05");
        map.put("SKUWidth","0.85");
        map.put("SKUHeight","1.42");
        list.add(map);
        map = new HashMap<>();
        map.put("step","2");
        map.put("SKUID", "78017340");
        map.put("zone", "第1次空间3");
        map.put("scheme", "方案四");
        map.put("num","33");
        map.put("lengthNum","1");
        map.put("widthNum","3");
        map.put("heightNum","11");
        map.put("SKULength","0.45");
        map.put("SKUWidth","0.43");
        map.put("SKUHeight","1.45");
        list.add(map);
        map = new HashMap<>();
        map.put("step","3");
        map.put("SKUID", "78017344");
        map.put("zone", "第1次空间1");
        map.put("scheme", "方案四");
        map.put("num","110");
        map.put("lengthNum","14");
        map.put("widthNum","4");
        map.put("heightNum","2");
        map.put("SKULength","0.28");
        map.put("SKUWidth","0.28");
        map.put("SKUHeight","0.95");
        list.add(map);
        map = new HashMap<>();
        map.put("step","4");
        map.put("SKUID", "78017340");
        map.put("zone", "第3次空间3");
        map.put("scheme", "方案四");
        map.put("num","6");
        map.put("lengthNum","3");
        map.put("widthNum","2");
        map.put("heightNum","1");
        map.put("SKULength","0.45");
        map.put("SKUWidth","0.43");
        map.put("SKUHeight","1.45");
        list.add(map);


        for (int i = 0; i< list.size(); i++){
            map = list.get(i);
            String scheme = ObjUtils.objToStr(map.get("scheme"));
            String SKULength = ObjUtils.objToStr(map.get("SKULength"));
            String SKUWidth = ObjUtils.objToStr(map.get("SKUWidth"));
            String SKUHeight = ObjUtils.objToStr(map.get("SKUHeight"));
            if (scheme.endsWith("二")){
                map.put("SKULength", SKUWidth);
                map.put("SKUWidth", SKULength);
                map.put("SKUHeight", SKUHeight);
            }else if (scheme.endsWith("三")){
                map.put("SKULength", SKUHeight);
                map.put("SKUWidth", SKUWidth);
                map.put("SKUHeight", SKULength);
            }else if (scheme.endsWith("四")){
                map.put("SKULength", SKUWidth);
                map.put("SKUWidth", SKUHeight);
                map.put("SKUHeight", SKULength);
            }
        }
        mOpenGLView.getRenderer().setList(list);
    }
}
