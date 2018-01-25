package zero.com.threeddemo.twoD;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLRenderer implements GLSurfaceView.Renderer {
    Square square = new Square();
    PictureSquare pictureSquare = new PictureSquare();
    FlatColoredSquare flatColoredSquare = new FlatColoredSquare();
    SmoothColoredSquare smoothColoredSquare = new SmoothColoredSquare();

    float angle = 0L;
    private float[] mTriangleArray = {
            0f, 1f, 0f,
            -1f, -1f, 0f,
            1f, -1f, 0f
    };

    private float vertices[] = {
            -1.0f, 1.0f, 0.0f,  // 0, Top Left
            -1.0f, -1.0f, 0.0f,  // 1, Bottom Left
            1.0f, -1.0f, 0.0f,  // 2, Bottom Right
            1.0f, 1.0f, 0.0f,  // 3, Top Right
    };

    private FloatBuffer mTriangleBuffer;

    private int selectId = 0;
    public void setSelectId(int id){
        this.selectId = id;
    }
    private Context context;
    public OpenGLRenderer(Context context){
        this.context = context;
    }

    /**
     *  定义实际的绘图操作
     * @param gl
     */
    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT |
                GL10.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glClearColor(cr, cg, cb, 0f);

        gl.glTranslatef(0, 0, -4);
        switch (selectId){
            case 1:
                //在进行坐标变换的一个好习惯是在变换前使用 glPushMatrix 保存当前矩阵，完成坐标变换操作后，再调用 glPopMatrix 恢复原先的矩阵设置。
                gl.glPushMatrix();
                gl.glScalef(0.5f, 0.5f, 0.5f);
                gl.glRotatef(45f, 0f, 0f, 1f);
                gl.glColor4f(1f, 1f, 1.0f, 1.0f);
                square.draw(gl); // ( NEW )
                gl.glPopMatrix();
                break;
            case 2:
                gl.glPushMatrix();
                gl.glScalef(0.5f, 0.5f, 0.5f);
                gl.glRotatef(45f, 0f, 0f, 1f);
//                gl.glTranslatef(1, 1, 0);
                flatColoredSquare.draw(gl); // ( NEW )
                gl.glPopMatrix();
                break;
            case 3:
                gl.glPushMatrix();
                gl.glScalef(0.5f, 0.5f, 0.5f);
                gl.glRotatef(angle, 0f, 0f, 1f);
                smoothColoredSquare.draw(gl); // ( NEW )
                gl.glPopMatrix();
                break;
            case 4:
                gl.glPushMatrix();
                gl.glScalef(0.5f, 0.5f, 0.5f);
//                gl.glRotatef(45f, 0f, 0f, 1f);
                pictureSquare.setContext(context);
                pictureSquare.draw(gl); // ( NEW )
                gl.glPopMatrix();
                break;
        }
        // Draw our square.
//        square.draw(gl); // ( NEW )

    }


    /**
     * 如果设备支持屏幕横向和纵向切换，这个方法将发生在方向切换时，此时可以重新设置绘制的纵横比例
     * @param gl
     * @param width
     * @param height
     */
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        // 设置输出屏幕大小

//       gl.glViewport(0, 0, width, height);

        // Sets the current view port to the new size.
        gl.glViewport(0, 0, width, height);// OpenGL docs.
        // Select the projection matrix
        gl.glMatrixMode(GL10.GL_PROJECTION);// OpenGL docs.
        // Reset the projection matrix
        gl.glLoadIdentity();// OpenGL docs.
        // Calculate the aspect ratio of the window
        GLU.gluPerspective(gl, 45.0f,
                (float) width / (float) height,
                0.1f, 100.0f);
        // Select the modelview matrix
        gl.glMatrixMode(GL10.GL_MODELVIEW);// OpenGL docs.
        // Reset the modelview matrix
        gl.glLoadIdentity();// OpenGL docs.
    }


    /**
     * 在这个方法中主要用来设置一些绘制时不常变化的参数，比如背景色等
     * @param gl
     * @param config
     */
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        // TODO Auto-generated method stub

        //启用阴影平滑
        gl.glShadeModel(GL10.GL_SMOOTH);
        //设置背景图颜色（范围0-1）
        gl.glClearColor(cr, cg, cb, 0f);
        //设置深度缓存
        gl.glClearDepthf(1.0f);
        //启用深度测试
        gl.glEnable(GL10.GL_DEPTH_TEST);
        //所作深度测试的类型
        gl.glDepthFunc(GL10.GL_LEQUAL);
        //告诉系统对透视进行修正
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);

    }

    private float cr = 0, cg = 0, cb = 0;

    public void setColor(float r, float g, float b) {

        cr = r;

        cg = g;

        cb = b;

    }

}