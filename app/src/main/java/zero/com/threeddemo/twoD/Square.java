package zero.com.threeddemo.twoD;

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

public class Square {
    // Our vertices.
    private float vertices[] = {
            -1.0f, 1.0f, 0.0f,  // 0, Top Left
            -1.0f, -1.0f, 0.0f,  // 1, Bottom Left
            1.0f, -1.0f, 0.0f,  // 2, Bottom Right
            1.0f, 1.0f, 0.0f,  // 3, Top Right
    };
    // The order we like to connect them.
    private short[] indices = {0, 1, 2, 0, 2, 3};
    // Our vertex buffer.
    public FloatBuffer vertexBuffer;
    // Our index buffer.
    private ShortBuffer indexBuffer;

    private Context context;
    public void setContext(Context context){
        this.context = context;
    }
    public Square() {
        // a float is 4 bytes, therefore we
        // multiply the number if
        // vertices with 4.
        ByteBuffer vbb
                = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
        // short is 2 bytes, therefore we multiply
        //the number if
        // vertices with 2.
        ByteBuffer ibb
                = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);
    }

    /**
     * This function draws our square on screen.
     *
     * @param gl
     */
    public void draw(GL10 gl) {
        // Counter-clockwise winding.
        gl.glFrontFace(GL10.GL_CCW);
        // Enable face culling.
        gl.glEnable(GL10.GL_CULL_FACE);
        // What faces to remove with the face culling.
        gl.glCullFace(GL10.GL_BACK);
        // Enabled the vertices buffer for writing
        //and to be used during
        // rendering.
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // Specifies the location and data format of
        //an array of vertex
        // coordinates to use when rendering.
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0,
                vertexBuffer);
        if (mTextureBuffer != null){
            // 启用贴图坐标数组数据
            gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);   // ①
            gl.glEnable(GL10.GL_TEXTURE_2D);
            gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
            loadTexture(gl);
            gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);  // ②
            gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);  // ③
        }
        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length,
                GL10.GL_UNSIGNED_SHORT, indexBuffer);
        // Disable the vertices buffer.
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        // Disable the use of textures.
        gl.glDisable(GL10.GL_TEXTURE_2D);
        // Disable face culling.
        gl.glDisable(GL10.GL_CULL_FACE);
    }

    protected FloatBuffer mTextureBuffer;
    /**
     * 设置贴图坐标
     * @param textureCoords
     */
    protected void setTextureCoordinates(float[] textureCoords) {
        // float is 4 bytes, therefore we multiply the number if
        // vertices with 4.
        ByteBuffer byteBuf = ByteBuffer.allocateDirect(
                textureCoords.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        mTextureBuffer = byteBuf.asFloatBuffer();
        mTextureBuffer.put(textureCoords);
        mTextureBuffer.position(0);
    }
    int texture;
    private void loadTexture(GL10 gl) {
        Bitmap bitmap = null;
        try {
            // 加载位图
            bitmap = BitmapFactory.decodeResource(context.getResources(),
                    R.mipmap.ic_launcher);
            int[] textures = new int[1];
            // 指定生成N个纹理（第一个参数指定生成一个纹理）
            // textures数组将负责存储所有纹理的代号
            gl.glGenTextures(1, textures, 0);
//            // 获取textures纹理数组中的第一个纹理
            texture = textures[0];
            // 通知OpenGL将texture纹理绑定到GL10.GL_TEXTURE_2D目标中
            gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
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
        } finally {
            // 生成纹理之后，回收位图
            if (bitmap != null)
                bitmap.recycle();
        }
    }
}