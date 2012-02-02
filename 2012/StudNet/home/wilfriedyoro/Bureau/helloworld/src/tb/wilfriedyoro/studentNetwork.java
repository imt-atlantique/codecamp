package tb.wilfriedyoro;

import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class studentNetwork extends MapActivity{
	
	LinearLayout linearLayout;
	MapView mapView;
	List<Overlay> mapOverlays;
	Drawable drawable;
	HelloItemizedOverlay itemizedOverlay;
	private int zoomLevel=15;
	
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main1);
    Button compte = (Button) findViewById(R.id.button1);
    Button loggin = (Button) findViewById(R.id.button2);
    Button search = (Button) findViewById(R.id.button3);
    search.setOnClickListener(new View.OnClickListener(){
    	public void onClick(View v){
    		 Intent myIntent = new Intent (v.getContext(),rechercherActivity.class);
    		 startActivityForResult(myIntent, 0);
    	 }
    });
    mapView = (MapView) findViewById(R.id.mapview);
    mapView.setBuiltInZoomControls(true);
    MapController mapCont =mapView.getController();
    mapOverlays = mapView.getOverlays();
    drawable = this.getResources().getDrawable(R.drawable.ic_launcher);
    itemizedOverlay = new HelloItemizedOverlay(drawable);
    mapCont.setCenter(new GeoPoint(48362180,-4570370));
     mapCont.setZoom(zoomLevel);
    GeoPoint point = new GeoPoint(48362180,-4570370);
    OverlayItem overlayitem = new OverlayItem(point, "", "");
    itemizedOverlay.addOverlay(overlayitem);
     mapOverlays.add(itemizedOverlay);
}
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
    //GeoPoint point = new GeoPoint(19240000,-99120000);
    //OverlayItem overlayitem = new OverlayItem(point, "", "");
   // itemizedoverlay.addOverlay(overlayitem);
    // mapOverlays.add(itemizedoverlay);
   // Button interiorAdvizer = (Button) findViewById(R.id.button1);
   // Button driverAssistance = (Button) findViewById(R.id.button2);
   // Button studentNetwork = (Button) findViewById(R.id.button3);
    // studentNetwork.setOnClickListener(new View.onClickListener(){
    	//public void onClick(View v){
    	//	 Intent myIntent = new Intent (v.getContext(),studentNetwork.class);
    	//	 startActivityforResult(myIntent, 0);
    	// }
   // });
  
    }