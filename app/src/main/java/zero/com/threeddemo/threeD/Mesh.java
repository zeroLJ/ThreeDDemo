package zero.com.threeddemo.threeD;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import zero.com.threeddemo.R;


/**
 *3D图形基类
 setVertices 允许子类重新定义顶点坐标。
 setIndices 允许子类重新定义顶点的顺序。
 setColor /setColors 允许子类重新定义颜色。
 setTextureCoordinates 允许子类重新定义纹理
 setLines 允许子类重新定义边框
 x,y,z 定义了平移变换的参数。
 rx,ry,rz 定义旋转变换的参数。
 */

public class Mesh {
    // 顶点坐标数组.
    protected FloatBuffer verticesBuffer = null;
    // 顶点绘制顺序数组.
    protected ShortBuffer indicesBuffer = null;
    // indicesBuffer长度
    private int numOfIndices = -1;
    // 给图形边框添加线条
    private ShortBuffer lineBuffer = null;
    // 需要连线的顶点数量
    private int numOfLines = -1;
    // Flat Color
    protected float[] rgba
            = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
    // Smooth Colors
    protected FloatBuffer colorBuffer = null;
    // 平移量
    public float x = 0;
    public float y = 0;
    public float z = 0;
    // 旋转量
    public float rx = 0;
    public float ry = 0;
    public float rz = 0;

    Context context;
    private GL10 gl;
    public Mesh(GL10 gl){
        this.gl = gl;
    }
    public void setContext(Context context){
        this.context = context;
    }
    public void setTexture(int texture){
        this.texture = texture;
    }
    public void draw(GL10 gl) {
//         Counter-clockwise winding.
        gl.glFrontFace(GL10.GL_CCW);
        // Enable face culling.
        gl.glEnable(GL10.GL_CULL_FACE);
        // What faces to remove with the face culling.
        gl.glCullFace(GL10.GL_BACK);
        // Enabled the vertices buffer for writing and
        //to be used during
        // rendering.
        // Specifies the location and data format
        //of an array of vertex
        // coordinates to use when rendering.
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        /**
         * 设置图形顶点，
         * size：坐标纬数，
         * type：坐标数据类型，
         * stride：每个相邻顶点之间在数组中的间隔（字节数）,缺省为0，表示顶点存储之间无间隔
         * pointer：存储顶点的数组
         */
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, verticesBuffer);

        if (mTextureBuffer != null){
            // 启用贴图坐标数组数据
            gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);   // ①
            // 启用2D纹理贴图
            gl.glEnable(GL10.GL_TEXTURE_2D);
            gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
//            loadTexture(gl);
//            // 通知OpenGL将texture纹理绑定到GL10.GL_TEXTURE_2D目标中
//            gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
            // 设置纹理被缩小（距离视点很远时被缩小）时的滤波方式
            gl.glTexParameterf(GL10.GL_TEXTURE_2D,
                    GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
            // 设置纹理被放大（距离视点很近时被方法）时的滤波方式
            gl.glTexParameterf(GL10.GL_TEXTURE_2D,
                    GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
            // 设置在横向、纵向上都是平铺纹理
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
                    GL10.GL_REPEAT);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
                    GL10.GL_REPEAT);
            // 加载位图生成纹理
            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
            gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);  // ②
            // 通知OpenGL将texture纹理绑定到GL10.GL_TEXTURE_2D目标中
            gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);  // ③
        }


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

        //平移
        gl.glTranslatef(x, y, z);
        //旋转
        gl.glRotatef(rx, 1, 0, 0);
        gl.glRotatef(ry, 0, 1, 0);
        gl.glRotatef(rz, 0, 0, 1);

        // Point out the where the color buffer is.绘制图形
//        GL10.GL_TRIANGLE_STRIP
        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, numOfIndices,
                GL10.GL_UNSIGNED_SHORT, indicesBuffer);

        if (lineBuffer != null) {
            //以黑色绘制边框
            gl.glColor4f(0f, 0f, 0f, 0f);
            gl.glDrawElements(GL10.GL_LINES, numOfLines, GL10.GL_UNSIGNED_SHORT, lineBuffer);
        }




        //关闭顶点颜色开关
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        //关闭顶点开关
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        // 关闭纹理贴图相关
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
        gl.glDisable(GL10.GL_TEXTURE_2D);

        // Disable face culling.
        gl.glDisable(GL10.GL_CULL_FACE);

    }

    /**
     * 设置顶点坐标
     * @param vertices
     */
    protected void setVertices(float[] vertices) {
        // a float is 4 bytes, therefore
        //we multiply the number if
        // vertices with 4.
        ByteBuffer vbb
                = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        verticesBuffer = vbb.asFloatBuffer();
        verticesBuffer.put(vertices);
        verticesBuffer.position(0);
    }

    /**
     * 设置绘制顺序
     * @param indices
     */
    protected void setIndices(short[] indices) {
        // short is 2 bytes, therefore we multiply
        //the number if
        // vertices with 2.
        ByteBuffer ibb
                = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indicesBuffer = ibb.asShortBuffer();
        indicesBuffer.put(indices);
        indicesBuffer.position(0);
        numOfIndices = indices.length;
    }

    /**
     * 设置边框线条，每两点为一条线。
     * @param points 必须为偶数
     */
    protected void setLines(short[] points) {
        // short is 2 bytes, therefore we multiply
        //the number if
        // vertices with 2.
        ByteBuffer ibb
                = ByteBuffer.allocateDirect(points.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        lineBuffer = ibb.asShortBuffer();
        lineBuffer.put(points);
        lineBuffer.position(0);
        numOfLines = points.length;
    }


    /**
     *  设置整个颜色
     * @param red
     * @param green
     * @param blue
     * @param alpha
     */
    protected void setColor(float red, float green,
                            float blue, float alpha) {
        // Setting the flat color.
        rgba[0] = red;
        rgba[1] = green;
        rgba[2] = blue;
        rgba[3] = alpha;
    }


    /**
     * 为每个顶点设置颜色，顶点之间颜色渐变，每四个float为一个点的颜色
     * @param colors
     */
    protected void setColors(float[] colors) {
        // float has 4 bytes.
        ByteBuffer cbb
                = ByteBuffer.allocateDirect(colors.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        colorBuffer = cbb.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);
    }

    // Our UV texture buffer.

    protected FloatBuffer mTextureBuffer;
    private  Bitmap bitmap;
    private  int[] textures = new int[1];
    public int texture;
    /**
     * 设置纹理坐标&&纹理图片
     * @param textureCoords
     */
    protected void setTextureCoordinates(float[] textureCoords , Bitmap bitmap) {
        // float is 4 bytes, therefore we multiply the number if
        // vertices with 4.
        ByteBuffer byteBuf = ByteBuffer.allocateDirect(
                textureCoords.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        mTextureBuffer = byteBuf.asFloatBuffer();
        mTextureBuffer.put(textureCoords);
        mTextureBuffer.position(0);
        this.bitmap = bitmap;

        //创建时获取纹理id（不能在循环过程调用，否则会造成内存溢出，程序崩掉）
        textures = new int[1];
        // 指定生成N个纹理（第一个参数指定生成一个纹理）
        // textures数组将负责存储所有纹理的代号
        gl.glGenTextures(1, textures, 0);
//            // 获取textures纹理数组中的第一个纹理
        texture = textures[0];
    }
}  