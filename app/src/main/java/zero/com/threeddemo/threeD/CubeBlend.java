package zero.com.threeddemo.threeD;

import javax.microedition.khronos.opengles.GL10;

/**
 * 带颜色半透明的立方体
 */

public class CubeBlend extends Mesh {
    public CubeBlend(GL10 gl, float width, float height, float depth) {
        super(gl);
        //顶点坐标
        float vertices[] = {0, 0, 0, // 0
                width,0, 0, // 1
                width, height, 0, // 2
                0, height, 0, // 3
                0, 0, depth, // 4
                width, 0, depth, // 5
                width, height, depth, // 6
                0, height, depth, // 7
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

//        setColor(0.6f,0.4f,0.3f,0);

        setColor(0.81250f,0.61328f,0.41406f,0.5f);

        setBlendable(true);

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
