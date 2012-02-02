package sm.droid.pocketscope;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class View_PieChart extends View 
{
	public static final int WAIT = 0;
	public static final int IS_READY_TO_DRAW = 1;
	public static final int IS_DRAW = 2;
	private static final float START_INC = 30;
	private Paint mBagpaints = new Paint();
	private Paint mLinePaints = new Paint();

	private int mWidth;
	private int mHeight;
	private int mGapTop;
	private int mGapBottm;
	private int mBgcolor;
	private int mGapleft;
	private int mGapright;
	private int mState = WAIT;
	private float mStart;
	private float mSweep;
	private int mMaxConnection;
	private List<PieDetailsItem> mdataArray;

	public View_PieChart(Context context) {
		super(context);
		Log.w(" single cons ", " single cons");
	}

	public View_PieChart(Context context, AttributeSet attr) {
		super(context, attr);
		Log.w(" double cons ", " double cons");
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (mState != IS_READY_TO_DRAW) {
			return;
		}
		canvas.drawColor(mBgcolor);
		mBagpaints.setAntiAlias(true);
		mBagpaints.setStyle(Paint.Style.FILL);
		mBagpaints.setColor(0x88FF0000);
		mBagpaints.setStrokeWidth(0.0f);
		mLinePaints.setAntiAlias(true);
		mLinePaints.setColor(0xff000000);
		mLinePaints.setStrokeWidth(3.0f);
		mLinePaints.setStyle(Paint.Style.STROKE);
		RectF mOvals = new RectF(mGapleft, mGapTop, mWidth - mGapright, mHeight
				- mGapBottm);
		mStart = START_INC;
		PieDetailsItem item;
		for (int i = 0; i < mdataArray.size(); i++) {
			item = (PieDetailsItem) mdataArray.get(i);
			mBagpaints.setColor(item.color);
			mSweep = (float) 360* ((float) item.count / (float) mMaxConnection);
			canvas.drawArc(mOvals, mStart, mSweep, true, mBagpaints);
			canvas.drawArc(mOvals, mStart, mSweep, true, mLinePaints);
			mStart = mStart + mSweep;
		}

		mState = IS_DRAW;
	}

	public void setGeometry(int width, int height, int gapleft, int gapright,
			int gaptop, int gapbottom, int overlayid) {

		mWidth = width;
		mHeight = height;
		mGapleft = gapleft;
		mGapright = gapright;
		mGapBottm = gapbottom;
		mGapTop = gaptop;

	}

	public void setSkinparams(int bgcolor) {
		Log.w(" Set bg color  : ", bgcolor + "");
		mBgcolor = bgcolor;
	}

	public void setData(List<PieDetailsItem> data, int maxconnection) {
		mdataArray = data;
		mMaxConnection = maxconnection;
		Log.w(" Max Connection  ", maxconnection + " " + "  Adataarray :"
				+ data.toString());
		mState = IS_READY_TO_DRAW;
	}

	public void setState(int state) {
		mState = state;
	}

	public int getColorValues(int index) {
		if (mdataArray == null) {
			return 0;
		}

		else if (index < 0)
			return ((PieDetailsItem) mdataArray.get(0)).color;
		else if (index > mdataArray.size())
			return ((PieDetailsItem) mdataArray.get(mdataArray.size() - 1)).color;
		else
			return ((PieDetailsItem) mdataArray.get(mdataArray.size() - 1)).color;

	}

}
