package sm.droid.pocketscope;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class HelloTabWidget extends TabActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, SettingsActivity.class);
		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost
				.newTabSpec("settings")
				.setIndicator("Settings",
						res.getDrawable(R.drawable.ic_tab_settings))
				.setContent(intent);
		tabHost.addTab(spec);

		// Do the same for the other tabs
		intent = new Intent().setClass(this, RessourcesActivity.class);
		spec = tabHost
				.newTabSpec("ressources")
				.setIndicator("Ressources",
						res.getDrawable(R.drawable.ic_tab_resources))
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, ExpensesActivity.class);
		spec = tabHost
				.newTabSpec("expenses")
				.setIndicator("Expenses",
						res.getDrawable(R.drawable.ic_tab_expenses))
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, CategoriesActivity.class);
		spec = tabHost
				.newTabSpec("reports")
				.setIndicator("Reports",
						res.getDrawable(R.drawable.ic_tab_reports))
				.setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(2);
	}
}
