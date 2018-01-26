package zero.com.threeddemo.threeD;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

import zero.com.threeddemo.R;

/**
 * Created by jj on 2018/1/19.
 */

/**
 * 带贴图的立方体
 */
public class CubePictureSix extends Mesh {
    private Bitmap bitmap;
    private Bitmap bitmap2;
    public CubePictureSix(GL10 gl,Context context, float width, float height, float depth) {
        super(gl);
        width /= 2;
        height /= 2;
        depth /= 2;
        float x = width, y = height, z = depth;
//        float[] vertices = {
//                //正前面
//                -x,-y,z, //2       0
//                x,-y,z,  //1       1
//                x,y,z,   //0       2
//                -x,y,z,  //3       3
//                //正后面
//                -x,-y,-z, //6      4
//                -x,y,-z,  //7      5
//                x,y,-z,   //4      6
//                x,-y,-z,  //5      7
//                //上面
//                -x,y,z,   //3      8
//                x,y,z,    //0      9
//                x,y,-z,   //4      10
//                -x,y,-z,  //7      11
//                //下面
//                -x,-y,z,  //2      12
//                -x,-y,-z,   //6    13
//                x,-y,-z, //5       14
//                x,-y,z,  //1       15
//                //左面
//                -x,-y,-z,   //6    16
//                -x,-y,z,  //2      17
//                -x,y,z,  //3       18
//                -x,y,-z,  //7      19
//                //右面
//                x,-y,-z, //5       20
//                x,y,-z, //4        21
//                x,y,z,//0          22
//                x,-y,z,  //1       23
//        };


        float[] vertices = {
                //正前面
                -x,-y,z, //2       0
                x,-y,z,  //1       1
                x,y,z,   //0       2
                -x,y,z,  //3       3
                //正后面
                -x,-y,-z, //6      4
                -x,y,-z,  //7      5
                x,y,-z,   //4      6
                x,-y,-z,  //5      7
                //上面
                -x,y,z,   //3      8
                x,y,z,    //0      9
                x,y,-z,   //4      10
                -x,y,-z,  //7      11
                //下面
                -x,-y,z,  //2      12
                -x,-y,-z,   //6    13
                x,-y,-z, //5       14
                x,-y,z,  //1       15
                //左面

                -x,-y,z,  //2      17
                -x,y,z,  //3       18
                -x,y,-z,  //7      19
                -x,-y,-z,   //6    16
                //右面
                x,-y,z,  //1       23
                x,-y,-z, //5       20
                x,y,-z, //4        21
                x,y,z,//0          22

        };

//        float[] texCoords = {
//                0,y,x,y,x,0,0,0, //前面
//                0,y,0,0,x,0,x,y, //后面
//                0,z,x,z,x,0,0,0, //上面
//                0,0,0,z,x,z,x,0, //下面
//                0,y,z,y,z,0,0,0, //左面
//                z,y,z,0,0,0,0,y, //右面  左右兑换了
//        };

        float[] texCoords = {
                0,1,1,1,1,0,0,0, //前面
                0,1,0,0,1,0,1,1, //后面
                0,1,1,1,1,0,0,0, //上面
                0,0,0,1,1,1,1,0, //下面
//                0,1,0,0,1,0,1,1, //下面
                0,1,1,1,1,0,0,0, //左面
//                0,1,0,0,1,0,1,1, //左面
                1,1,1,0,0,0,0,1, //右面  左右兑换了
//                0,1,1,1,1,0,0,0, //右面  左右兑换了
        };

        setVertices(vertices);
        setTextureCoordinates(texCoords,null);
        bitmap = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ic_launcher);
        bitmap2 = BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.ic_launcher);
        setColor(0.6f,0.4f,0.3f,0);
    }

    @Override
    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CCW);
        // Enable face culling.
        gl.glEnable(GL10.GL_CULL_FACE);
        // What faces to remove with the face culling.
        gl.glCullFace(GL10.GL_BACK);
        // 三角形索引数据
        //因为glDrawElements用的模式为GL10.GL_TRIANGLE_STRIP
        //所以三角形是连在一起的，0，1，3，2代表顶点013组成的三角形和132组成的三角形真好组成一个面
        ByteBuffer indices1 = ByteBuffer.wrap(new byte[]{//前面
                0,1,3,2,
        });

        ByteBuffer indices2 = ByteBuffer.wrap(new byte[]{//后面
                4,5,7,6,
        });

        ByteBuffer indices3 = ByteBuffer.wrap(new byte[]{//上面
                8,9,11,10,
        });

        ByteBuffer indices4 = ByteBuffer.wrap(new byte[]{//下面
                12,13,15,14,
        });

        ByteBuffer indices5 = ByteBuffer.wrap(new byte[]{//左面
                16,17,19,18,
        });

        ByteBuffer indices6 = ByteBuffer.wrap(new byte[]{//右面
                20,21,23,22,
        });

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //纹理的使用与开启颜色渲染一样，需要开启纹理功能
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
        gl.glEnable(GL10.GL_TEXTURE_2D);

        // 设置绘制颜色
        gl.glColor4f(rgba[0], rgba[1], rgba[2], rgba[3]);

        // Smooth color
        if (colorBuffer != null) {
            // Enable the color array buffer to be
            //used during rendering.
            //打开顶点颜色开关并设置颜色，相邻顶点颜色渐变
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
        }

//        int[] tex_id = new int[6];
//        gl.glGenTextures(6,tex_id,0);  //一次性生成几个纹理，放到数组中，但是这时纹理和图片还没结合在一起

        IntBuffer textureBuffer = IntBuffer.allocate(6);
        gl.glGenTextures(6, textureBuffer);  //一次性生成几个纹理，放到数组中，但是这时纹理和图片还没结合在一起
        int[] tex_id = textureBuffer.array();
        for(int i = 0; i < 6; i++)
        {
            gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);  // ②
            //将纹理绑定到目标GL10.GL_TEXTURE_2D上
            gl.glBindTexture(GL10.GL_TEXTURE_2D, tex_id[i]);
            //为目标纹理GL10.GL_TEXTURE_2D放上图片
            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0,bitmap, 0);
            // 设置纹理被缩小（距离视点很远时被缩小）时的滤波方式
            gl.glTexParameterf(GL10.GL_TEXTURE_2D,
                    GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
            // 设置纹理被放大（距离视点很近时被方法）时的滤波方式
            gl.glTexParameterf(GL10.GL_TEXTURE_2D,
                    GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
            //设置目标纹理属性
            gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
            gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
        }



        //设置正方体 各顶点 数据和贴图坐标数组，第一个参数都是代表几个元素设定一个顶点，顶点是三个xyz，贴图是两个xy(因为是二维贴图
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, verticesBuffer);                //注意：这个地方的第二个参数是关于坐标数据类型的，根据顶点数据和纹理数据坐标的数据类型而改变，如果是FL_FLOAT的话数据类型就是float的，以前我写的OpenGL版本都是float的
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);


//         绘制立方体并绑定纹理
        gl.glBindTexture(GL10.GL_TEXTURE_2D, tex_id[0]);
        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, 4,  GL10.GL_UNSIGNED_BYTE, indices1);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, tex_id[1]);
        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, 4,  GL10.GL_UNSIGNED_BYTE, indices2);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, tex_id[2]);
        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, 4,  GL10.GL_UNSIGNED_BYTE, indices3);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, tex_id[3]);
        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, 4,  GL10.GL_UNSIGNED_BYTE, indices4);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, tex_id[4]);
        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, 4,  GL10.GL_UNSIGNED_BYTE, indices5);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, tex_id[5]);
        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, 4,  GL10.GL_UNSIGNED_BYTE, indices6);

//        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, 24,  GL10.GL_UNSIGNED_BYTE, ByteBuffer.wrap(new byte[]{
//                0,1,3,2, 4,5,7,6, 8,9,11,10, 12,13,15,14,  16,17,19,18, 20,21,23,22
//        }));

        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
        gl.glDisable(GL10.GL_TEXTURE_2D);
        //关闭顶点颜色开关
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        // Disable face culling.
        gl.glDisable(GL10.GL_CULL_FACE);
    }
}
