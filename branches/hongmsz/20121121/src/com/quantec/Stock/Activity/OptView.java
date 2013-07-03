package com.quantec.Stock.Activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.quantec.Stock.MyApp;
import com.quantec.Stock.R;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class OptView extends Activity{
	
	MyApp GV;
	private LinearLayout contentsContatiner;
	testView test;
	
	Dialog dialog;
	String tmp;
	StockD tmp_stock = null;
	OptD OptRest = null;

	private static final float D_width = 480f;
	
	Intent Ctest, CHint;
	int begin_q = 201201;		//옵션 메뉴 생성
	int end_q;						//옵션 메뉴 생성
	int this_w = 20120702;		//옵션 메뉴 생성
	int tmp_k;		//옵션 메뉴 생성
    int tmp_i;
	
	int view=0;
	int lit;
	int view_page_num=0;
	
	float menu_pos;
	float menu_dist;
	float menu_text_dist;
	float c_width, c_height;
	
	int width, height;
	float x1, x2, y1, y2;
	
	int qt_count = 201101;
	int tmp_count, dr;
	
	int go_thread=0;
    int tmp_position_cnt;
	
	Paint B_24 = new Paint();
	Paint W_24 = new Paint();
	
	public void onCreate(Bundle savedInstanceState) {
		
		GV = (MyApp) getApplication();
		GV.setDisplay(((WindowManager)this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay());
		
		width = GV.getDisplay().getWidth();
		height = GV.getDisplay().getHeight();
		
		menu_pos = 70;
 		menu_dist = GV.getDisplay().getHeight()*0.084f;
 		menu_text_dist = GV.getDisplay().getHeight()*0.01f;

		c_width = (float)width/D_width;
		
		Ctest = new Intent(this, ContentsActivity.class);
        CHint = new Intent(this, OptView.class);
		super.onCreate(savedInstanceState); 

		LinearLayout layout = new LinearLayout(this);
		layout.setBackgroundColor(Color.BLACK);
		
		createContentsContainer();       
		layout.addView(contentsContatiner);
	    
	    setContentView(layout);
	}
	
private void createContentsContainer(){
    	
    	contentsContatiner = new LinearLayout(this);
    	
    	test = new testView(this);
    	
    	contentsContatiner.addView(test);
    	test.setOnTouchListener(new OnTouchListener(){
        	public boolean onTouch(View arg0, MotionEvent event) {               
        		if (event.getAction() == MotionEvent.ACTION_DOWN) {
        			x1=event.getX();
        			y1=event.getY();
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                	go_thread = 0;
                	x2=event.getX();
                	y2=event.getY();
            	
					GV.setSRV(0);
					GV.setSRM(0);
					
				if((x1>0 && x1 < width/2f-50f && y1 > menu_pos+menu_dist*10 && view_page_num-9>0)||( y2-y1> 40 && view_page_num-9>0)){
        			view_page_num = view_page_num-10;
        		}
				
				if((x1>width/2f+50f && x1 < width && y1 > menu_pos+menu_dist*10 && view_page_num+10<GV.getOptD().dNum)||(y1-y2>40 && view_page_num+10<GV.getOptD().dNum)){
        			view_page_num = view_page_num+10;
        		}
				
/*				for(tmp_k=0; tmp_k<10;tmp_k++){
					if(y1 > (float)(60+(tmp_k*(height-110)/11)) && y1 < (float)(60+((tmp_k+1)*(height-110)/11)) && (view_page_num+tmp_k)<GV.getOptD().dNum){
						dialog = ProgressDialog.show(OptView.this, "", GV.getOptD().m_Kname[view_page_num+tmp_k]+" 정보 불러오는  중..", true, true);
						new Thread(new Runnable() {
							
							public void run() {
								tmp = GV.getOptD().m_Jcode[view_page_num+tmp_k];
								tmp_stock = getSValue(tmp); // 20111024 수정
								GV.setStockD(tmp_stock);
								tmp_stock = getSValueA(tmp);
								tmp_stock = getWSValue(tmp);
								GV.setSR(0);
								dialog.dismiss();
								startActivity(Ctest);
							}
						}).start();
					}
				}
//*/                
//*					
				if(Math.abs(y2-y1)<40){
					go_thread = 0;
					for(tmp_position_cnt=0; tmp_position_cnt<10; tmp_position_cnt++)
						if(y1 > menu_pos+menu_dist*tmp_position_cnt && y1 < menu_pos+menu_dist*(tmp_position_cnt+1) && view_page_num+tmp_position_cnt<GV.getOptD().dNum){
							dialog = ProgressDialog.show(OptView.this, "", GV.getOptD().m_Kname[view_page_num+tmp_position_cnt]+" 정보 불러오는  중..", true, true);
							tmp = GV.getOptD().m_Jcode[view_page_num+tmp_position_cnt];
							go_thread = 1;
						}
					
					if(go_thread==1)
						new Thread(new Runnable() {
					        
					        public void run() {
					        	qt_count = 201101;
				    			tmp_stock = getSValue(tmp); // 20111024 수정
				    			GV.setStockD(tmp_stock);
				    			tmp_stock = getSValueA(tmp);
				    			tmp_stock = getWSValue(tmp);
				    			GV.setSR(0);
				    			dialog.dismiss();
								startActivity(Ctest);
					        }
					    }).start();
				}
	            		
//*/            		           		
            		test.invalidate();
            	}
                
 				return true;
        }});
    	
    }
	
	public class testView extends View {
    	int thr = 0;

		Bitmap Icon_m = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.vb_logo);
		Bitmap Bg_m = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.s_menu_m);
	    Bitmap Icon_soff = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ch_us);
	    Bitmap Nav_c = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.nav_c);
	    Bitmap Nav_R = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.nav_r);
	    Bitmap Nav_L = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.nav_l);
	    
    	public testView(Context context)
    	{
    		super(context);
    	}
    	public testView(Context context, AttributeSet attrs) {
    		super(context, attrs);
    	}
    	public void onDraw(Canvas canvas){
    		B_24.setColor(Color.BLACK);
    		if(width>480)
    			B_24.setTextSize(30);
    		else
    			B_24.setTextSize(20);
    		
    		B_24.setAntiAlias(true);
    		B_24.setStrokeWidth(3);	// 선 굵기 조정 
    		
    		W_24.setColor(Color.WHITE);
    		if(width>480)
    			W_24.setTextSize(30);
    		else
    			W_24.setTextSize(20);
    		W_24.setAntiAlias(true);
    		
    		
    		canvas.drawBitmap(Icon_m, 0, 10, B_24);
    		switch(GV.getOptD().dType){
    			case 1:
    				canvas.drawText("- EPS 증가율 기준", width*5/9, 50, W_24);
    				break;
    			case 2:
    				canvas.drawText("- 매출액 증가율 기준", width*5/9, 50, W_24);
    				break;
    			case 3:
    				canvas.drawText("- 영업이익 증가율 기준", width*5/9, 50, W_24);
    				break;
    		}
    			
//			canvas.drawText("[검색 결과]", 20, 20, B_24);
    		for(int k = 0 ; k<10; k++)
    			if(view_page_num+k < GV.getOptD().dNum){
    				canvas.drawBitmap(Bg_m, 0, menu_pos+menu_dist*k, B_24);
    				canvas.drawBitmap(Icon_soff, (float)width-Icon_soff.getWidth()-5f, menu_pos*1.1f+menu_dist*k, W_24);
    			}
    		for(int j = 0 ; j<10; j++){
//    			canvas.drawRect((float)0,(float)(60+(k*(height-110)/11)),width,(float)(60+((k+1)*(height-110)/11)),W_24);
    			if(view_page_num+j < GV.getOptD().dNum){
    				canvas.drawText(view_page_num+j + 1 + ". "+GV.getOptD().m_Kname[view_page_num+j], 30, menu_pos+menu_text_dist+menu_dist*(2*j+1)/2, W_24);
//    				canvas.drawText(">", width*8/9, menu_pos+menu_text_dist+menu_dist*(2*j+1)/2, W_24);
    			}
//    			canvas.drawLine((float)0,(float)(60+((k+1)*(height-110)/11)),width,(float)(60+((k+1)*(height-110)/11)),B_24);
    		}
    		
    		canvas.drawBitmap(Nav_c, 0, menu_pos+menu_dist*10, B_24);
    		
    		if(view_page_num-9 > 0){
    			canvas.drawBitmap(Nav_L, 0, menu_pos+menu_dist*10, B_24);
/*    			String tmp_001 = " < 이전 페이지";
    			String tmp_002 = "    ["+(view_page_num-9)+" ~ "+(view_page_num)+"]";
    			canvas.drawText(tmp_001, 20, height-50, W_24);
    			canvas.drawText(tmp_002, 20, height-20, W_24);
 */   		}
    		
    		if(view_page_num+11 < GV.getOptD().dNum){
    			canvas.drawBitmap(Nav_R, width-Nav_R.getWidth(), menu_pos+menu_dist*10, B_24);
/*    			String tmp_003 = "다음 페이지 > ";
    			String tmp_004 = "["+(view_page_num+11)+" ~ "+(view_page_num+20)+"] ";
    			canvas.drawText(tmp_003, width-tmp_003.length()*20-20, height-50, W_24);
    			canvas.drawText(tmp_004, width-tmp_004.length()*15-20, height-20, W_24);
*/    		}
/*    		
    		canvas.drawText("시가총액 기준", 20, 650, B_24);
    		canvas.drawText("전체 보기", 20, 680, B_24);
    		canvas.drawText("500억 이상",20+GV.getDisplay().getWidth()/5, 680, B_24);
    		canvas.drawText("1000억 이상",20+GV.getDisplay().getWidth()/5*2, 680, B_24);
    		canvas.drawText("2000억 이상", 20+GV.getDisplay().getWidth()/5*3, 680, B_24);
    		canvas.drawText("5000억 이상", 20+GV.getDisplay().getWidth()/5*4, 680, B_24);
//*/    		
    	}
    }
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(event.getAction() == KeyEvent.ACTION_DOWN){
			if(keyCode == KeyEvent.KEYCODE_BACK){
				finish();
			}
		}
		return false;
	}
	
	private StockD getSValue(String S_Code){
    	
        StockD dataStock = new StockD();
       
    	if(S_Code == null){
    		return null;
    	}else {
            dataStock.m_Jcode = S_Code;
    //*    	
	        XmlPullParserFactory factory = null;
			
			try{
				factory = XmlPullParserFactory.newInstance();
	
				factory.setNamespaceAware(true);
				XmlPullParser xpp = null;
	
				xpp = factory.newPullParser();
	
				String connectUrl = "http://quantec.co.kr/Stock_data_final.html?id=" + dataStock.m_Jcode;
				// 해당 지역의 url을 설정한다.
				
				URL UrlgetStockD = null;
				UrlgetStockD = new URL(connectUrl);
	
				InputStream in;
				in = UrlgetStockD.openStream();
	
				xpp.setInput(in, null);
				
				ReceiveParsing getParse = new ReceiveParsing(dataStock);
	
				getParse.proceed(xpp);
	    	}catch(Exception ex){
	    		ex.printStackTrace();
	    		return null;
			}	
	    	return dataStock;
		}
//*/
    }
	
	
    private StockD getWSValue(String S_Code){
		StockD dataStock = MyApp.getStockD();
       
    	if(S_Code == null){
    		return null;
    	}else {
    //*    	
	        XmlPullParserFactory factory = null;
			
			try{
				tmp_count = dataStock.m_quarter[0];
				qt_count = tmp_count - qt_count;
				
				dr = (qt_count%100 + (qt_count/100)*4)*12;
				
				factory = XmlPullParserFactory.newInstance();
	
				factory.setNamespaceAware(true);
				XmlPullParser xpp = null;
	
				xpp = factory.newPullParser();
	
				String connectUrl = "http://quantec.co.kr/W_Stock_val.html?id=" + S_Code + "&dur="+dr;
				
				URL UrlgetStockD = null;
				UrlgetStockD = new URL(connectUrl);
	
				InputStream in;
				in = UrlgetStockD.openStream();
	
				xpp.setInput(in, "UTF-8");
				
				StockValueParsing getParse = new StockValueParsing(dataStock);
	
				getParse.proceed(xpp);
	    	}catch(Exception ex){
	    		ex.printStackTrace();
	    		return null;
			}	
	    	return dataStock;
		}
//*/
    }
    
    private StockD getSValueA(String S_Code){
    	
        StockD dataStock = MyApp.getStockD();
       
    	if(S_Code == null){
    		return null;
    	}else {
    //*    	
	        XmlPullParserFactory factory = null;
			
			try{
				factory = XmlPullParserFactory.newInstance();
	
				factory.setNamespaceAware(true);
				XmlPullParser xpp = null;
	
				xpp = factory.newPullParser();
	
				String connectUrl = "http://quantec.co.kr/Stock_data_annual.html?id=" + dataStock.m_Jcode;
				
				// 해당 지역의 url을 설정한다.
				
				URL UrlgetStockD = null;
				UrlgetStockD = new URL(connectUrl);
	
				InputStream in;
				in = UrlgetStockD.openStream();
	
				xpp.setInput(in, null);
				
				ReceiveParsingA getParse = new ReceiveParsingA(dataStock);
	
				getParse.proceed(xpp);
	    	}catch(Exception ex){
	    		ex.printStackTrace();
	    		return null;
			}	
	    	return dataStock;
		}
//*/
    }
    
private OptD getOptValue(String Index, int Opt1, int Opt2, int Opt3, long Opt4){
    	
		OptD Orest = new OptD();
	       
		String retVal = "";
		String tmpVal = "";
    	String resVal="";
    	String urlString;
    	int SI, EI;
    	
    	if(Index == null){
    		return null;
    	}else {    		
    		try {
        		urlString = "http://quantec.co.kr/opt2_test.html?loc=" + Index + "&opt1=" + Opt1;
        		if(Opt2> 0){
					urlString = urlString + "&opt2=" + Opt2;
				}
				if(Opt3> 0){
					urlString = urlString + "&opt3=" + Opt3;
				}
				if(Opt4> 0){
					urlString = urlString + "&opt4=" + Opt4;
				}
        		URL url = new URL( urlString );
        		URLConnection connection = url.openConnection();
        		InputStream is = connection.getInputStream();
        		//BufferedReader br = new BufferedReader( new InputStreamReader( is, "UTF-8" ), 8192 );
        		BufferedReader br = new BufferedReader( new InputStreamReader( is, "UTF-8" ), 8192 );

        		char recv[] = new char[ 8192 ];
        		while( br.read( recv ) > 0 )
        			retVal += new String( recv );
        		br.close();
        		is.close();
        	} catch (MalformedURLException e) {
        		e.printStackTrace();
        		return null;
        	} catch (IOException e) {
        		e.printStackTrace();
        		return null;
        	}
    		
    		
    		tmpVal = retVal;
    		SI = retVal.indexOf("<dNum data=");
    		EI = retVal.indexOf("/>", SI);
    		if(SI > 0){
    			SI = SI + 12;
    			EI = EI - 1;
    			resVal = retVal.substring(SI, EI);
    		}else
    			return null;
    		
    		lit = Integer.valueOf(resVal).intValue();
    		
    		Orest.m_Jcode = new String[lit];
    		Orest.m_Kname = new String[lit];
    		
    		for(int i = 0; i < lit; i++){
    			SI = retVal.indexOf("<Jcode data=", EI);
    			EI = retVal.indexOf("/>", SI);
    			SI = SI+13;
    			EI = EI -1;
    			Orest.m_Jcode[i]= retVal.substring(SI, EI);
    			
    			SI = retVal.indexOf("<Kname data=", EI);
    			EI = retVal.indexOf("/>", SI);
    			SI = SI+13;
    			EI = EI -1;
    			Orest.m_Kname[i]= retVal.substring(SI, EI);
    		}
    		    		
    		return Orest;
		}
    }
}
