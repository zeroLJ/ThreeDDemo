package zero.com.threeddemo.threeD;

import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

/**
 * 立方体
 */
public class Cube extends Mesh {
    public Cube(GL10 gl, float width, float height, float depth) {
        super(gl);
        width /= 2;
        height /= 2;
        depth /= 2;
        float vertices[] = {
                -width, -height, -depth, // 0
                width, -height, -depth, // 1
                width, height, -depth, // 2
                -width, height, -depth, // 3
                -width, -height, depth, // 4
                width, -height, depth, // 5
                width, height, depth, // 6
                -width, height, depth, // 7
        };
//        short indices[] = {
//                0, 4, 5, 0, 5, 1,
//                1, 5, 6, 1, 6, 2,
//                2, 6, 7, 2, 7, 3,
//                3, 7, 4, 3, 4, 0,
//                4, 7, 6, 4, 6, 5,
//                3, 0, 1, 3, 1, 2,
//        };

//        float[] texCoords = {
//                0,1,1,1,1,0,1,0,0,0,0,1, //前面
//                0,1,0,0,1,0,1,0,1,1,0,1, //后面
//                0,1,1,1,1,0,1,0,0,0,0,1, //上面
//                0,1,0,0,1,0,1,0,1,1,0,1, //下面
//                0,1,0,0,1,0,1,0,1,1,0,1, //左面
//                0,1,1,1,1,0,1,0,0,0,0,1, //右面  左右兑换了
//        };
//        setTextureCoordinates(texCoords);
//        short indices[] = {
//                4, 5, 6, 6, 7, 4,
//                0, 3, 2, 2, 1, 0,
//                7, 6, 2, 2, 3, 7,
//                4, 0, 1, 1, 5, 4,
//                4, 7, 3, 3, 0, 4,
//                5, 1, 2, 2, 6, 5,
//        };
        short indices[] = {
                4, 5, 6, 4, 6, 7,
                0, 3, 2, 0, 2, 1,
                7, 6, 2, 7, 2, 3,
                4, 0, 1, 4, 1, 5,
                4, 7, 3, 4, 3, 0,
                5, 1, 2, 5, 2, 6,
        };
//        float[] texCoords = {
//                0,1,1,1,1,0,0,1,1,0,0,0, //前面
//                0,1,0,0,1,0,0,1,1,0,1,1, //后面
//                0,1,1,1,1,0,0,1,1,0,0,0, //上面
//                0,1,0,0,1,0,0,1,1,0,1,1, //下面
//                0,1,0,0,1,0,0,1,1,0,1,1, //左面
//                0,1,1,1,1,0,0,1,1,0,0,0, //右面  左右兑换了
//        };
//        float[] texCoords = {
//                0,1,1,1,1,0,0,0, //前面
//                0,1,0,0,1,0,1,1, //后面
//                0,1,1,1,1,0,0,0, //上面
//                0,1,0,0,1,0,1,1, //下面
//                0,1,0,0,1,0,1,1, //左面
//                0,1,1,1,1,0,0,0, //右面  左右兑换了
//        };
//        setTextureCoordinates(texCoords);
        setIndices(indices);
        setVertices(vertices);


    }

}