package sensor.shohag.com;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SensorsActivity extends Activity implements SensorEventListener {
    /** Called when the activity is first created. */
	SensorManager sm;
	List<Sensor> s;
	TextView textview;
	TextView proxview;
	TextView accview;
	TextView liview;
	Sensor proxSensor;
	Sensor accSensor;
	Sensor liSensor;
	Button button;
	Button btn2,btn3,btn4;
	MediaPlayer mp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textview = (TextView)findViewById(R.id.textView1);
        mp = MediaPlayer.create(this,R.raw.iphn);
        mp.start();
       button = (Button)findViewById(R.id.button1);
       btn2 = (Button)findViewById(R.id.button2);
       //btn3 = (Button)findViewById(R.id.button3);
       btn4 = (Button)findViewById(R.id.button4);
       button.setOnClickListener(new View.OnClickListener() {
           public void onClick(View view) {
               Intent i = new Intent(getApplicationContext(),CallService.class);
               startService(i);
              
           }
       });

       
       btn4.setOnClickListener(new View.OnClickListener() {
           public void onClick(View view) {
               mp.stop();
              
           }
       });
       
       btn2.setOnClickListener(new View.OnClickListener() {
           public void onClick(View view) {
        	   Intent i = new Intent(getApplicationContext(),CallService.class);
               stopService(i);
              
           }
       });
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        s = sm.getSensorList(Sensor.TYPE_ALL);
        
        for(Sensor ss:s)
        {textview.append("\n\n"+ss.getName());}
        
        proxSensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        proxview = (TextView)findViewById(R.id.proxtextView);
        sm.registerListener(this, proxSensor, SensorManager.SENSOR_DELAY_NORMAL);
        accSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_NORMAL);
        accview = (TextView)findViewById(R.id.acctextView);
        liSensor = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        sm.registerListener(this, liSensor, SensorManager.SENSOR_DELAY_NORMAL);
        liview = (TextView)findViewById(R.id.litextView);
        

       
    }
    
    

	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		if(event.sensor.getType() == Sensor.TYPE_PROXIMITY){
		proxview.setText("\n\n"+"PROXIMITY"+"\n"+String.valueOf(event.values[0]));
		
		
		}
		if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
			
		accview.setText("ACCELEROMETER"+"\n"+"X: "+event.values[0]+"\nY: "+event.values[1]+"\nZ: "+event.values[2]);
		
		double p = event.values[0];
		if(p<0.0)
		{mp.pause();}
		else{
			if(mp.isPlaying())
			{
				
			}
			else
			{mp.start();}
		}
		
	}
		if(event.sensor.getType() == Sensor.TYPE_LIGHT){
			
			liview.setText("LIGHT"+"\n"+String.valueOf(event.values[0]));
			
		}
}
}