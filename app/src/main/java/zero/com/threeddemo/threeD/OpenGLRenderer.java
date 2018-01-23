package zero.com.threeddemo.threeD;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLRenderer implements GLSurfaceView.Renderer {
    float width = 0.2f;
    float height = 0.25f;
    float depth = 0.3f;
    int count = 0;
    Plane plane = new Plane();
    Cube cube = new Cube(1,1,1);
    Group group = new Group();
    CubeColor cubeColor = new CubeColor(width,height,depth);
    CubePicture cubePicture;
    Context context;
    public OpenGLRenderer(Context context){
        this.context = context;
    }

    /**
     *  定义实际的绘图操作
     *  OpenGl ES提供了两类方法来绘制一个空间几何图形
     *  void glDrawArrays(int mode, int first, int count)使用VetexBuffer来绘制，顶点的顺序由VetexBuffer中的顺序指定
     *  void glDrawElements(int mode, int count, int type, Buffer indices)可以重新定义顶点的顺序，顶点的顺序由indices指定
     * @param gl
     */
    @Override
    public void onDrawFrame(GL10 gl) {

        //清楚屏幕和深度缓存
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT |
                GL10.GL_DEPTH_BUFFER_BIT);
        //重置当前的模型观察矩阵
        gl.glLoadIdentity();
        //前四个参数用于确定窗口的大小，而后面两个参数分别是在场景中所能绘制深度的起点和终点(z轴坐标，暂时只发现对深度有影响，无法控制窗口大小)
        gl.glFrustumf(-3f,3f,-1f,1f,-7,-5);
        //设置绘制颜色
        gl.glClearColor(cr, cg, cb, 0f);
        //往z轴负方向平移4个单位（这个很重要，不然看不到绘图效果）
        gl.glTranslatef(0, 0, -4);
        gl.glTranslatef(-0.5f, -0.5f, 0);
        //以x轴为中心顺时针旋转45度／
        gl.glRotatef(45f, -1f, 0f, 0f);
        //以z轴为中心顺时针旋转45度（右手原则）
        gl.glRotatef(45f, 0f, 0f, -1f);
        // 缩放指定倍数／／
//        gl.glScalef(0.5f, 0.5f, 0.5f);
        //保存当前矩阵
        gl.glPushMatrix();
//
//        gl.glTranslatef(2.5f,0,0);
//        cube.draw(gl);
//        cube.setTexture(texture);

//        cubePicture.draw(gl);

        cubeColor.setContext(context);
        int num = 0;
        A:for (int i = 0 ; i < 4; i++){
            for (int j = 0 ; j < 4; j++){
                for (int k = 0 ; k < 4; k++){
                    cubeColor.draw(gl);
//                    cubePicture.draw(gl);
                    num++;
                    if (num == count){
                        break A;
                    }
                    gl.glTranslatef(0.2f,0,0);
                }
                //恢复到上一次保存的矩阵
                gl.glPopMatrix();
                gl.glPushMatrix();
                gl.glTranslatef(0, height * (j+1), depth * i);
            }
            gl.glPopMatrix();
            gl.glPushMatrix();
            gl.glTranslatef(0,0,depth * (i+1));
        }


        gl.glFinish();
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

        //设置OpenGl场景的大小
        gl.glViewport(0, 0, width, height);


        /**
         *以下代码必须在此处执行才能绘制图像出来
         */
        //设置投影矩阵（指明接下来的代码将影响投影矩阵，投影矩阵负责为场景增加透视度）
        gl.glMatrixMode(GL10.GL_PROJECTION);
        //重置投影据矩阵
        gl.glLoadIdentity();
        // Calculate the aspect ratio of the window
        GLU.gluPerspective(gl, 45.0f,
                (float) width / (float) height,
                0.1f, 100.0f);
        // 选择模型观察举证（指明接下来的代码将影响模型观察矩阵）
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        //重置模型观察矩阵
        gl.glLoadIdentity();
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
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FOG_HINT);

//        cubePicture = new CubePicture(context,1,1,1);
        cubePicture = new CubePicture(context,width,height,depth);
    }

    private float cr = 0, cg = 0, cb = 0;

    //设置背景色
    public void setColor(float r, float g, float b) {
        cr = r;
        cg = g;
        cb = b;
    }
}