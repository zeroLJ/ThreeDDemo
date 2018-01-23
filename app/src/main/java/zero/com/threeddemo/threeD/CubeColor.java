package zero.com.threeddemo.threeD;

/**
 * 带颜色带边框的立方体
 */

public class CubeColor extends Mesh {
    public CubeColor(float width, float height, float depth) {
        width /= 2;
        height /= 2;
        depth /= 2;
        //顶点坐标
        float vertices[] = {-width, -height, -depth, // 0
                width, -height, -depth, // 1
                width, height, -depth, // 2
                -width, height, -depth, // 3
                -width, -height, depth, // 4
                width, -height, depth, // 5
                width, height, depth, // 6
                -width, height, depth, // 7
        };
        //设置顶点坐标
        setVertices(vertices);
        //此处为绘制顺序
        //每个数字代表vertices的第几个坐标，每3个数字一组绘制三角形，通过绘制多个三角形达到立方体的效果
        short indices[] = {0, 4, 5,
                0, 5, 1,
                1, 5, 6,
                1, 6, 2,
                2, 6, 7,
                2, 7, 3,
                3, 7, 4,
                3, 4, 0,
                4, 7, 6,
                4, 6, 5,
                3, 0, 1,
                3, 1, 2,};
        //设置绘制顺序
        setIndices(indices);

        setColor(0.6f,0.4f,0.3f,0);
//        setColors(new float[]{
//                1f, 0f, 0f, 1f, // vertex 0 red
//                0f, 1f, 0f, 1f, // vertex 1 green
//                0f, 0f, 1f, 1f, // vertex 2 blue
//                1f, 0f, 1f, 1f, // vertex 3 magenta
//                1f, 0f, 0f, 1f, // vertex 0 red
//                0f, 1f, 0f, 1f, // vertex 1 green
//                0f, 0f, 1f, 1f, // vertex 2 blue
//                1f, 0f, 1f, 1f, // vertex 3 magenta
//        });

        short lines[] = {
                0, 1,
                1, 2,
                2, 3,
                3, 0,
                4, 5,
                5, 6,
                6, 7,
                7, 4,
                0, 4,
                1, 5,
                3, 7,
                2, 6
        };
        setLines(lines);

    }
}
