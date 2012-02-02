package sm.droid.pocketscope;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RessourcesActivity extends Activity {
	private EditText text;
	public static int totalIncomes = 0;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.resourcesmain);
		text = (EditText) findViewById(R.id.editText1);

	}

	// This method is called at button click because we assigned the name to the
	// "On Click property" of the button
	public void addIncome(View view) {
		// int accountTotal = 0;
		int accountNoType = 0;
		int accountCash = 0;
		int accountBank = 0;
		switch (view.getId()) {
		case R.id.button1:
			RadioButton noTypeButton = (RadioButton) findViewById(R.id.radio0);
			RadioButton cashButton = (RadioButton) findViewById(R.id.radio1);
			RadioButton bankButton = (RadioButton) findViewById(R.id.radio2);
			if (text.getText().length() == 0) {
				Toast.makeText(this, "Please enter a valid number",
						Toast.LENGTH_LONG).show();
				return;
			}

			int inputValue = Integer.parseInt(text.getText().toString());
			totalIncomes = totalIncomes + inputValue;
			text.setText("");
			if (noTypeButton.isChecked()) {
				accountNoType = accountNoType + inputValue;
				// accountTotal = accountTotal + inputValue;

			} else if (cashButton.isChecked()) {
				accountCash = accountNoType + inputValue;
				// accountTotal = accountTotal + inputValue;
			} else if (bankButton.isChecked()) {
				accountBank = accountBank + inputValue;
				// accountTotal = accountTotal + inputValue;
			}
			break;
		// return accountTotal;
		}
	}
	// Converts to EURO
	// private int In(float fmg) {
	// return (fmg / 15000);
	// }

	// Converts to FMG

}
