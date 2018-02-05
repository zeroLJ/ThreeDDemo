package zero.com.threeddemo.threeD;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import zero.com.threeddemo.ObjUtils;

public class OpenGLRenderer implements GLSurfaceView.Renderer {
    List<Map<String, String>> list = new ArrayList<>();
    public void setList(List<Map<String, String>> list){
         this.list = list;
    }
    float width = 0.25f;
    float height = 0.25f;
    float depth = 0.25f;
    int count = 0;
    Cube cube ;
    CubeColor cubeColor;
    CubePicture cubePicture;
    CubePicture cubePictureAdd;
    Context context;
    CubeLine cubeLine;
    public OpenGLRenderer(Context context){
        this.context = context;
    }

    private int selectId = 0;
    public void setSelectId(int id){
        this.selectId = id;
    }
    float x = 0, y = 0;
    public void setRotatef(float x, float y){
        if (x > 360) {
            x = x%360;
        }
        if (x < 0) {
            x = x%360 + 360;
        }
//        if (y > 360) {
//            y = y%360 - 360;
//        }
//        if (y < 0) {
//            y = y%360 + 360;
//        }

        //角度转弧度
        y = (float) (2*Math.PI*y/360);
        this.x = x;
        this.y = y;
        Log.d("ljl","x:"+x+" y:" + y);

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

        //清除屏幕和深度缓存
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT |
                GL10.GL_DEPTH_BUFFER_BIT);
        //重置当前的模型观察矩阵
        gl.glLoadIdentity();
        //设置绘制颜色
        gl.glClearColor(cr, cg, cb, 0f);


        GLU.gluLookAt(gl,0f,(float)(Math.sin(y)*5 ),(float)(Math.cos(y)*5),
                0f,0f,0f,
                0, 1, 0);


//        x = (float) (2*Math.PI*x/360);
//        y = (float) (2*Math.PI*y/360);
//        GLU.gluLookAt(gl,(float)(Math.cos(y)*4* Math.sin(x)),
//                (float)(Math.sin(y)*4),
//                (float)(Math.cos(y)*4* Math.cos(x)),
//                0.5f,0.5f,0.5f,
//                0, 1, 0);
        //往z轴负方向平移4个单位（这个很重要，不然看不到绘图效果）
//        gl.glTranslatef(0, 0, -4);


        //以x轴为中心顺时针旋转45度
        gl.glRotatef(45f, -1f, 0f, 0f);
        //以z轴为中心顺时针旋转45度（右手原则）
        gl.glRotatef(45f, 0f, 0f, -1f);
        // 缩放指定倍数
//        gl.glScalef(0.5f, 0.5f, 0.5f);

        gl.glRotatef(x, 0f, 0f, 1f);

        //保存当前矩阵
        gl.glPushMatrix();

        switch (selectId){
            case 1:
                cube.draw(gl);
//                gl.glScalef(0.2f,0.2f,0.2f);
//                float cartonLength = 6/2;
//                float cartonWidth = 4.5f/2;
//                float cartonHeight = 5/2;
//                gl.glTranslatef(-cartonLength, -cartonWidth, -cartonHeight);
//                gl.glPushMatrix();
//                int num2 = 0;
//                B:for (int z = 0; z < list.size(); z++){
//                    Map<String, String> map = list.get(z);
//                    gl.glPushMatrix();
//                    t(gl, z);
//                    gl.glPushMatrix();
//                    float SKULength = ObjUtils.objToFloat(map.get("SKULength"));
//                    float SKUWidth =ObjUtils.objToFloat(map.get("SKUWidth"));
//                    float SKUHeight = ObjUtils.objToFloat(map.get("SKUHeight"));
//                    CubeColor2 cube =  new CubeColor2(gl, SKULength, SKUWidth, SKUHeight);
//                    A:for (int i = 0 ; i < ObjUtils.objToInt(map.get("heightNum")); i++){
//                        for (int j = 0 ; j <  ObjUtils.objToInt(map.get("widthNum")); j++){
//                            for (int k = 0 ; k <  ObjUtils.objToInt(map.get("lengthNum")); k++){
////                            cubeColor.draw(gl);
//                                cube.draw(gl);
//                                num2++;
//                                if (num2 == count){
//                                    gl.glPopMatrix();
//                                    gl.glPopMatrix();
//                                    break B;
//                                }
//                                gl.glPopMatrix();
//                                gl.glPushMatrix();
//                                gl.glTranslatef(SKULength*(k+1),SKUWidth * j,SKUHeight * i);
//                            }
//                            //恢复到上一次保存的矩阵
//                            gl.glPopMatrix();
//                            gl.glPushMatrix();
//                            gl.glTranslatef(0, SKUWidth * (j+1), SKUHeight * i);
//                        }
//                        gl.glPopMatrix();
//                        gl.glPushMatrix();
//                        gl.glTranslatef(0,0,SKUHeight * (i+1));
//                    }
//                    gl.glPopMatrix();
//                    gl.glPopMatrix();
//                }
//                gl.glPopMatrix();
                break;
            case 2:
                new CubeColor(gl,1,1,1).draw(gl);
//                int num3 = 0;
//                gl.glScalef(0.2f,0.2f,0.2f);
//                float cartonLength2 = 6/2;
//                float cartonWidth2 = 4.5f/2;
//                float cartonHeight2 = 5/2;
//                gl.glTranslatef(-cartonLength2, -cartonWidth2, -cartonHeight2);
//                gl.glPushMatrix();
//                for (int z = 0; z < list.size(); z++){
//                    Map<String, String> map = list.get(z);
//                    gl.glPushMatrix();
//                    t(gl, z);
//                    gl.glPushMatrix();
//                    float SKULength = ObjUtils.objToFloat(map.get("SKULength"));
//                    float SKUWidth =ObjUtils.objToFloat(map.get("SKUWidth"));
//                    float SKUHeight = ObjUtils.objToFloat(map.get("SKUHeight"));
//                    CubeColor2 cube =  new CubeColor2(gl, SKULength, SKUWidth, SKUHeight);
//                    for (int i = 0 ; i < ObjUtils.objToInt(map.get("heightNum")); i++){
//                        for (int j = 0 ; j <  ObjUtils.objToInt(map.get("widthNum")); j++){
//                            for (int k = 0 ; k <  ObjUtils.objToInt(map.get("lengthNum")); k++){
////                            cubeColor.draw(gl);
//                                num3++;
//                                cube.draw(gl);
//                                gl.glPopMatrix();
//                                gl.glPushMatrix();
//                                gl.glTranslatef(SKULength*(k+1),SKUWidth * j,SKUHeight * i);
//                            }
//                            //恢复到上一次保存的矩阵
//                            gl.glPopMatrix();
//                            gl.glPushMatrix();
//                            gl.glTranslatef(0, SKUWidth * (j+1), SKUHeight * i);
//                        }
//                        gl.glPopMatrix();
//                        gl.glPushMatrix();
//                        gl.glTranslatef(0,0,SKUHeight * (i+1));
//                    }
//                    gl.glPopMatrix();
//                    gl.glPopMatrix();
//                }
//                gl.glPopMatrix();
//                new CubeBlend(gl,7f,5.5f,6f).draw(gl);
                break;
            case 3:
                cubeLine.draw(gl);
                break;
            case 4:
                cubePicture.draw(gl);
                break;
            case 5:
                gl.glTranslatef((float) (-2 * width+width*0.5), (float) (-2*height + height*0.5), (float) (-2*depth + depth*0.5));
                gl.glPushMatrix();
                int num = 0;
                A:for (int i = 0 ; i < 4; i++){
                    for (int j = 0 ; j < 4; j++){
                        for (int k = 0 ; k < 4; k++){
//                            cubeColor.draw(gl);
                            cubePictureAdd.draw(gl);
                            num++;
                            if (num == count){
                                break A;
                            }
                            gl.glPopMatrix();
                            gl.glPushMatrix();
                            gl.glTranslatef(width*(k+1),height * j,depth * i);
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
                gl.glPopMatrix();
                break;
        }


//        gl.glPopMatrix();
        gl.glPopMatrix();

//        gl.glFlush();
//
//        gl.glFinish();
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

        cubePicture = new CubePicture(gl,context,1,1,1);
        cubePictureAdd = new CubePicture(gl,context,width,height,depth);
        cube = new Cube(gl,1,1,1);
        cube.setContext(context);

        cubeColor = new CubeColor(gl,width,height,depth);
        cubeLine = new CubeLine(gl,1,1,1);
    }

    private float cr = 0, cg = 0, cb = 0;

    //设置背景色
    public void setColor(float r, float g, float b) {
        cr = r;
        cg = g;
        cb = b;
    }


    private void t(GL10 gl, int position){
//        gl.glTranslatef(ObjUtils.mutiply(a, ObjUtils.objToFloat(list.get(0).get("SKULength"))), 0, 0);
        String zone = ObjUtils.objToStr(list.get(position).get("zone"));
        if (!zone.equals("原点")){
            int per = ObjUtils.objToInt(zone.substring(1,2)) - 1;
            t(gl, per);
            int a = ObjUtils.objToInt(list.get(per).get("lengthNum"));
            int b = ObjUtils.objToInt(list.get(per).get("widthNum"));
            int c = ObjUtils.objToInt(list.get(per).get("heightNum"));
            if (zone.endsWith("3")){
                gl.glTranslatef(ObjUtils.mutiply(a, ObjUtils.objToFloat(list.get(per).get("SKULength"))), 0, 0);
            }else if (zone.endsWith("2")){
                gl.glTranslatef(0, ObjUtils.mutiply(b,  ObjUtils.objToFloat(list.get(per).get("SKUWidth"))), 0);
            }else if (zone.endsWith("1")){
//                Log.d("ljl","z:"+c+"*"+ObjUtils.objToFloat(list.get(0).get("SKUHeight"))+"="+ ObjUtils.mutiply(c,  ObjUtils.objToFloat(list.get(0).get("SKUHeight"))) );
                gl.glTranslatef(0, 0, ObjUtils.mutiply(c, ObjUtils.objToFloat(list.get(per).get("SKUHeight"))));
            }
        }
    }
}