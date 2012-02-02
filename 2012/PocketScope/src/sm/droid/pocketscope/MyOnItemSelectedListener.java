package sm.droid.pocketscope;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class MyOnItemSelectedListener implements OnItemSelectedListener {

	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		/*
		 * Toast.makeText(parent.getContext(), "The planet is " +
		 * parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
		 */

		ExpensesActivity.category = parent.getItemAtPosition(pos).toString();
	}

	public void onNothingSelected(AdapterView parent) {
		// Do nothing.
	}

}
