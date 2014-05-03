package edu.vanderbilt.isis.demo;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

public class MainActivity extends Activity {
	TextView temp;
	GraphView graph;
	
	public void updateMeasurements(View view){
		TextView tempLabel;
		tempLabel = (TextView) findViewById(R.id.tempLabel);
		tempLabel.setText("Button has been pressed");
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run(){
				MainActivity.this.runOnUiThread(new Runnable(){
					@Override
					public void run(){
						updateTemp();
					}
				});
			}
		}, 0, 1000);
	}
	
	public void updateTemp(){
		//Update the temp
		temp = (TextView) findViewById(R.id.temp);
		temp.setText("" + (Math.random()*100));
	}

	public void updateGraph(){
		//graph.
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Initialize Graph
        graph = new LineGraphView(this, "Electrocardiogram");
        GraphViewSeries series = new GraphViewSeries(new GraphViewData[] {
        		new GraphViewData(1, 2.0d)
        		, new GraphViewData(2, 1.5d)
        		, new GraphViewData(3, 2.5d)
        		, new GraphViewData(4, 1.0d)
        });
        graph.addSeries(series);
        
        //Add to Layout
        //LinearLayout layout = (LinearLayout)findViewById(R.id.graph1);
        //layout.addView(graph);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
