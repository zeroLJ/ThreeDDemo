package zero.com.threeddemo.threeD;

import javax.microedition.khronos.opengles.GL10;


/**
 * Created by ljl on 2018/2/1.
 * 只有边框的立方体（左下角为零点）
 */

public class CubeLine extends Mesh {
    public CubeLine(GL10 gl, float width, float height, float depth) {
        super(gl);
//        //顶点坐标
//        float vertices[] = {0, 0, 0, // 0
//                width,0, 0, // 1
//                width, height, 0, // 2
//                0, height, 0, // 3
//                0, 0, depth, // 4
//                width, 0, depth, // 5
//                width, height, depth, // 6
//                0, height, depth, // 7
//        };
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
        setColor(0.66015f,0.39453f,0.25781f,0.6f);
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
        setBlendable(true);
    }
}
