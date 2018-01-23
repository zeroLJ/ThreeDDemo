package zero.com.threeddemo.twoD;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class OpenGLView extends GLSurfaceView {

    private OpenGLRenderer mRenderer;

    public OpenGLView(Context context) {

       super(context);

       mRenderer = new OpenGLRenderer();

       setRenderer(mRenderer);

       // TODO Auto-generated constructor stub

    }

   public OpenGLRenderer getRenderer(){
        return mRenderer;
   }
}

