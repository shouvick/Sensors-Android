package sensor.shohag.com;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class CallService extends Service implements SensorEventListener{
	/** Called when the activity is first created. */
	Sensor proxSensor;
	Sensor accSensor;
	Sensor liSensor;
	SensorManager sm;
	AudioManager am;
	MediaPlayer mp;
	@Override
    public void onCreate(){
        super.onCreate();
       // mp = MediaPlayer.create(this,R.raw.iphn);
       
        Toast.makeText(getApplicationContext(),"Service Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
    	
    	Toast.makeText(getApplicationContext(),"Service Started", Toast.LENGTH_SHORT).show();
    	//mp.start();
    	sm = (SensorManager) getSystemService(SENSOR_SERVICE);
    	liSensor = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
    	accSensor= sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    	proxSensor= sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);

    	sm.registerListener(this, liSensor, SensorManager.SENSOR_DELAY_NORMAL);
    	sm.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_NORMAL);
    	sm.registerListener(this, proxSensor, SensorManager.SENSOR_DELAY_NORMAL);

        am = (AudioManager) getSystemService(AUDIO_SERVICE);

        return START_STICKY;
    }


@Override
public IBinder onBind(Intent arg0) {
	// TODO Auto-generated method stub
	return null;
}


public void onAccuracyChanged(Sensor arg0, int arg1) {
	// TODO Auto-generated method stub
	
}


public void onSensorChanged(SensorEvent event) {
	// TODO Auto-generated method stub
	 if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
         
         if(event.values[0]<=0.0){
             am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
         }
         else {
             am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
         }
     }
}
public void onDestroy(){
	
    super.onDestroy();
    sm.unregisterListener(this);
    //mp.stop();
    
    Toast.makeText(getApplicationContext(),"Service Stop", Toast.LENGTH_SHORT).show();
}

}
