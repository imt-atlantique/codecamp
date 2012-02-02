package sm.droid.pocketscope;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class CategoriesActivity extends Activity {
	List<PieDetailsItem> piedata = new ArrayList<PieDetailsItem>(0);
	int items[] = { 0, 0, 0, 0, 0, 0 };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categoriesmain);

		Button overviewbutton = (Button) findViewById(R.id.button1);
		overviewbutton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						OverviewActivity.class);// creates an “Intent” to start
												// another Activity
				startActivityForResult(myIntent, 0);
			}

		});

		PieDetailsItem item;
		int maxCount = 0;
		int itemCount = 0;
		createArray();
		int colors[] = { -11391617, -5874946, -8547479, -14670564, -15773098,
				-10856236 };
		String itemslabel[] = { " vauesr ur 100", " vauesr ur 200",
				" vauesr ur 300", " vauesr ur 400", " vauesr ur 500",
				" vauesr ur 600" };
		for (int i = 0; i < items.length; i++) {
			itemCount = items[i];
			item = new PieDetailsItem();
			item.count = itemCount;
			item.label = itemslabel[i];
			item.color = colors[i];
			piedata.add(item);
			maxCount = maxCount + itemCount;
		}

		/*
		 * Toolkit toolkit = Toolkit.getDefaultToolkit(); Dimension dim =
		 * toolkit.getScreenSize(); int screenWidth = dim.width; // int
		 * screenHeight = dim.height;
		 */

		// int size = (int) 0.8 * screenWidth;
		int size = 155;
		int BgColor = 0x000000;
		Bitmap mBaggroundImage = Bitmap.createBitmap(size, size,
				Bitmap.Config.ARGB_8888);
		View_PieChart piechart = new View_PieChart(this);
		piechart.setLayoutParams(new LayoutParams(size, size));
		piechart.setGeometry(size, size, 2, 2, 2, 2, 2130837504);
		piechart.setSkinparams(BgColor);
		piechart.setData(piedata, maxCount);
		piechart.invalidate();
		piechart.draw(new Canvas(mBaggroundImage));
		piechart = null;
		ImageView mImageView = new ImageView(this);
		mImageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		mImageView.setBackgroundColor(BgColor);
		mImageView.setImageBitmap(mBaggroundImage);
		RelativeLayout finalLayout = (RelativeLayout) findViewById(R.id.pie_container);
		finalLayout.addView(mImageView);
	}

	public void createArray() {

		for (int i = 0; i < ExpensesActivity.categories.size(); i++) {
			items[i] = (int) ExpensesActivity.categories.get(i).value;

		}
		// return items;
	}
}