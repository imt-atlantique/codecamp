package sm.droid.pocketscope;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class ExpensesActivity extends Activity {
	public static ArrayList<Category> categories = new ArrayList<Category>();
	private EditText text;
	public static String category = null;
	public static int totalExpenses = 0;

	// private Spinner spinner;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expensesmain);
		text = (EditText) findViewById(R.id.editText1);
		this.createCategoriesArray();

		ImageButton vocal = (ImageButton) findViewById(R.id.imageButton1);
		vocal.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						VoiceRecognitionDemo.class);
				startActivityForResult(myIntent, 0);
			}

		});

		Spinner spinner = (Spinner) findViewById(R.id.categoriesspinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.categories, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		category = null;
		spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

		// category = MyOnItemSelectedListener.myCategory;
		// category = spinner.getSelectedItem().toString();
		// text.setText(spinner.getSelectedItem().toString());
		// text.setText(category);
	}

	public void createCategoriesArray() {
		categories.add(new Category("Groceries", 0));
		categories.add(new Category("Entertainment", 0));
		categories.add(new Category("Shopping", 0));
		categories.add(new Category("Transportation", 0));
		categories.add(new Category("Trips", 0));
		categories.add(new Category("Others", 0));
	}

	public void myClickHandler(View view) {
		if (text.getText().length() == 0) {
			Toast.makeText(this, "Please enter a valid number",
					Toast.LENGTH_LONG).show();
			return;
		}
		int editTextValue = Integer.parseInt(text.getText().toString());
		// int value = 0;
		totalExpenses = totalExpenses + editTextValue;
		for (int i = 0; i < categories.size(); i++)
			if (category.equals(categories.get(i).name)) {
				categories.get(i).value = categories.get(i).value
						+ editTextValue;
				text.setText("");
			}

		// text.setText(category);
	}
}
