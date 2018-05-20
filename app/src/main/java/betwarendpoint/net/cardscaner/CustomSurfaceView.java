package betwarendpoint.net.cardscaner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CustomSurfaceView extends SurfaceView {

    private final Paint paint;
    private final SurfaceHolder mHolder;
    private final Context context;

    public CustomSurfaceView(Context context) {
        this(context,null);
       // super(context);
    }

    public CustomSurfaceView(Context context, AttributeSet attrs) {
             //   this(context);
        this(context, attrs,0);

    }

    public CustomSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public CustomSurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mHolder = getHolder();
        mHolder.setFormat(PixelFormat.TRANSPARENT);
        this.context = context;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
    }

    private int mRatioWidth = 0;
    private int mRatioHeight = 0;

    /**
     * Sets the aspect ratio for this view. The size of the view will be measured based on the ratio
     * calculated from the parameters. Note that the actual sizes of parameters don't matter, that
     * is, calling setAspectRatio(2, 3) and setAspectRatio(4, 6) make the same result.
     *
     * @param width  Relative horizontal size
     * @param height Relative vertical size
     */
    public void setAspectRatio(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Size cannot be negative.");
        }
        mRatioWidth = width;
        mRatioHeight = height;
        requestLayout();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (0 == mRatioWidth || 0 == mRatioHeight) {
            setMeasuredDimension(width, height);
        } else {
            if (width < height * mRatioWidth / mRatioHeight) {
                setMeasuredDimension(width, width * mRatioHeight / mRatioWidth);
            } else {
                setMeasuredDimension(height * mRatioWidth / mRatioHeight, height);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            invalidate();
//            if (mHolder.getSurface().isValid()) {
//                final Canvas canvas = mHolder.lockCanvas();
//                Log.d("touch", "touchRecieved by camera");
//                if (canvas != null) {
//                    Log.d("touch", "touchRecieved CANVAS STILL Not Null");
//                    canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
//                    canvas.drawColor(Color.TRANSPARENT);
//                    canvas.drawCircle(event.getX(), event.getY(), 100, paint);
//                    mHolder.unlockCanvasAndPost(canvas);
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            Canvas canvas1 = mHolder.lockCanvas();
//                            if(canvas1 !=null){
//                                canvas1.drawColor(0, PorterDuff.Mode.CLEAR);
//                                mHolder.unlockCanvasAndPost(canvas1);
//                            }
//
//                        }
//                    }, 1000);
//
//                }
//                   // mHolder.unlockCanvasAndPost(canvas);
//
//
//            }
//        }
//
//
//        return false;
//    }
}
