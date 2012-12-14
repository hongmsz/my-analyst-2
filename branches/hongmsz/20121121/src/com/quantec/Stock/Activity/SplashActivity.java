package com.quantec.Stock.Activity;

import com.quantec.Stock.MyApp;
import com.quantec.Stock.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

public class SplashActivity extends Activity {
	MyApp GV;
	
	int count = 0;
	
	int SI;	
	String tmp = null;
	
	Boolean err = false;
	
	private static final float D_width = 480f;
	private static final float D_height = 800f;
	int width, height;
	float c_width, c_height;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		GV = (MyApp) getApplication();
		
		width = GV.getDisplay().getWidth();
		height= GV.getDisplay().getHeight();
		
		c_width = (float)width/D_width;
		c_height = (float)height/D_height;
        
		LinearLayout layout = new LinearLayout(this);
        setContentView(layout);
		layout.setBackgroundColor(Color.BLACK);
        
        
		testView test = new testView(this);
        layout.addView(test, new LinearLayout.LayoutParams(MyApp.getDisplay().getWidth(),MyApp.getDisplay().getHeight()));
        test.animate(test);
        
/*        
        test.setOnTouchListener(new OnTouchListener(){
         	 public boolean onTouch(View arg0, MotionEvent event) {             
                  if (event.getAction() == MotionEvent.ACTION_DOWN) {
                 	 finish();
                  }
  				return true;
         }});
//*/
//*		
        Handler handler = new Handler () {
			public void handleMessage(Message msg) {
				finish();
			}
		};
	       
		handler.sendEmptyMessageDelayed(0, 2000);
//*/
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(event.getAction() == KeyEvent.ACTION_DOWN){
			if(keyCode == KeyEvent.KEYCODE_BACK){

			}
		}
		return false;
	}
	
	public class testView extends View 
    {
		Paint pnt = new Paint();
		

		Bitmap map001 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.b_logo);
		Bitmap map002 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.loading02);
		
    	
    	int thr = 0;
    	float rLoad;
    	
    	public testView(Context context)
    	{
    		super(context);
    	}
    	public testView(Context context, AttributeSet attrs) {
    		super(context, attrs);
    	}
    	public void onDraw(Canvas canvas){
    		
    		pnt.setColor(Color.WHITE);
    		
    		int t_height;
			
    		if(GV.getDisplay().getWidth() > 700)
				t_height = GV.getDisplay().getHeight()+96;
			else
				t_height = GV.getDisplay().getHeight();
    		
    		Rect src = new Rect(0,0,map001.getWidth(),map001.getHeight());
    		Rect dst = new Rect(0,0,MyApp.getDisplay().getWidth(),t_height);
    		
    		canvas.drawBitmap(map001, src, dst, pnt);

			canvas.rotate(rLoad, (float)(MyApp.getDisplay().getWidth()/2), (float)(MyApp.getDisplay().getHeight()*0.83));
			canvas.drawBitmap(map002, (float)(MyApp.getDisplay().getWidth()/2-25*c_width),(float)(MyApp.getDisplay().getHeight()*0.83-25*c_height), pnt);
		}
    	
    	public void animate(final testView tmp) {
    		tmp.thr = 1;
    		
    		new Thread(new Runnable() {
    			  
    			  public void run() {
    			     while(tmp.thr == 1){ 
    			    	 count++;
    			    	 if(count>23)
    			    		 count=0;
    			    	 
    			    	 rLoad = (float)count * 15;
    			    	 
    					tmp.postInvalidate();
    					try {
    						Thread.sleep(100);
    					} catch (InterruptedException e) {
    						e.printStackTrace();
    					}
    			     }
    			  }
    		}).start();
    	}
    }
}
