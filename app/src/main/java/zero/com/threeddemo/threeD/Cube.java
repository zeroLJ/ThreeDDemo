package zero.com.threeddemo.threeD;

/**
 * 立方体
 */
public class Cube extends Mesh {
    public Cube(float width, float height, float depth) {
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
        short indices[] = {
                0, 4, 5, 0, 5, 1,
                1, 5, 6, 1, 6, 2,
                2, 6, 7, 2, 7, 3,
                3, 7, 4, 3, 4, 0,
                4, 7, 6, 4, 6, 5,
                3, 0, 1, 3, 1, 2,
        };

//        float[] texCoords = {
//                0,1,1,1,1,0,0,1,1,0,0,0, //前面
//                0,1,0,0,1,0,0,1,1,1, //后面
//                0,1,1,1,1,0,0,0, //上面
//                0,0,0,1,1,1,1,0, //下面
//                0,1,1,1,1,0,0,0, //左面
//                1,1,1,0,0,0,0,1, //右面  左右兑换了
//        };
//        setTextureCoordinates(texCoords);
//        short indices[] = {
//                3, 2, 6, 3, 6, 7,
//                0, 4, 5, 0, 5, 6,
//                2, 6, 5, 2, 5, 1,
//                0, 4, 7, 0, 7, 3,
//                7, 4, 5, 7, 5, 6,
//                2, 6, 5, 2, 5, 1,
//        };
        setIndices(indices);
        setVertices(vertices);


    }

}