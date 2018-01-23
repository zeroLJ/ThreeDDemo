package zero.com.threeddemo.twoD;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by jj on 2018/1/18.
 */

public class FlatColoredSquare extends Square {
    public void draw(GL10 gl) {
        //设置颜色
        gl.glColor4f(0.5f, 0.5f, 1.0f, 1.0f);
        super.draw(gl);
    }
}
