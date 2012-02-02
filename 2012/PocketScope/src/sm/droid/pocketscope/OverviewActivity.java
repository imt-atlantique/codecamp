package sm.droid.pocketscope;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class OverviewActivity extends Activity {
	private EditText text1, text2, text3;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.overviewmain);

		text1 = (EditText) findViewById(R.id.editText1);
		int spentValue = ExpensesActivity.totalExpenses;
		text1.setText(String.valueOf(spentValue));

		text2 = (EditText) findViewById(R.id.editText2);
		int startValue = RessourcesActivity.totalIncomes;
		text2.setText(String.valueOf(startValue));

		text3 = (EditText) findViewById(R.id.editText3);
		int restValue = startValue - spentValue;
		text3.setText(String.valueOf(restValue));
	}
}
