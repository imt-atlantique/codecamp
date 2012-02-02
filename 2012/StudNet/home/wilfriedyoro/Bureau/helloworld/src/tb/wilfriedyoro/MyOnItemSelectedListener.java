package tb.wilfriedyoro;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class MyOnItemSelectedListener  implements OnItemSelectedListener{
	 public void onItemSelected(AdapterView<?> parent,
		        View view, int pos, long id) {
		      Toast.makeText(parent.getContext(), "You're looking for " +
		          parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
		    }

		    public void onNothingSelected(AdapterView parent) {
		      // Do nothing.
		    }


}
