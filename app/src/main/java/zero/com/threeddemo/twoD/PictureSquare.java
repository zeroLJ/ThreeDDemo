package zero.com.threeddemo.twoD;

/**
 * Created by UBP on 2018/1/25.
 */

public class PictureSquare extends Square{
    public PictureSquare(){
        //逆时针
        setTextureCoordinates(new float[]{
                0,0,
                0,1,
                1,1,
                1,0,
        });

    }
}
