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
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;

public class Hint extends Activity {
	
	MyApp GV;

	float version = MyApp.getVER();
	
	private LinearLayout contentsContatiner;
    
    testView test;
	
	Paint pnt = new Paint();
	
	int width, height;
	float x1, x2, y1, y2;
	int menu_num=0;
	String ch_op;
	OptD OptRest = null;
	
	int view=0;
	int lit;
	
	Dialog dialog;
	
	int qt_count = 201101;
	int tmp_count, dr;
	Intent Ctest;

    StockD tmp_stock = null;
    
    String tmp;
	
	Paint B_24 = new Paint();
    
	public void onCreate(Bundle savedInstanceState) {
		
		GV = (MyApp) getApplication();
		
		GV.setDisplay(((WindowManager)this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay());
		
		Ctest = new Intent(this, OptView.class);
		
		width = GV.getDisplay().getWidth();
		height = GV.getDisplay().getHeight();
		
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
                	x2=event.getX();
                	y2=event.getY();
                	
            		if(y1 > 150 && y1 < 190){
            			dialog = ProgressDialog.show(Hint.this, "", "데이터 정렬 중(EPS 증가율 기준)..", true, true);
            			new Thread(new Runnable() {
            		        
            		        public void run() {
            		        	OptRest = getOptValue("m11", 201102, 201101, 20120702, 10);
                    			GV.setOptD(OptRest);
                    			dialog.dismiss();
        						startActivity(Ctest);
            		        }
            		    }).start();
//    		        	view = 1;
//            			test.invalidate();
            		}else if(y1 > 200 && y1 < 240){
            			dialog = ProgressDialog.show(Hint.this, "", "데이터 정렬 중(매출액 증가율 기준)..", true, true);
            			new Thread(new Runnable() {
            		        
            		        public void run() {
            		        	OptRest = getOptValue("m12", 201102, 201101, 20120702, 10);
                    			GV.setOptD(OptRest);
                    			dialog.dismiss();
        						startActivity(Ctest);
            		        }
            		    }).start();
            			
            		}else if(y1 > 250 && y1 < 290){
            			dialog = ProgressDialog.show(Hint.this, "", "데이터 정렬 중(영업이익 증가율 기준)..", true, true);
            			new Thread(new Runnable() {
            		        
            		        public void run() {
                    			OptRest = getOptValue("m13", 201102, 201101, 20120702, 10);
                    			GV.setOptD(OptRest);
                    			dialog.dismiss();
        						startActivity(Ctest);
            		        }
            		    }).start();
            		}
/*
                	if(view == 1){
						GV.setSRV(0);
						GV.setSRM(0);
                		if(y1 > 330 && y1 < 370 && OptRest.m_Jcode[0] != null){
                			dialog = ProgressDialog.show(Hint.this, "", OptRest.m_Kname[0]+" 정보 불러오는  중..", true, true);
                			new Thread(new Runnable() {
                		        
                		        public void run() {
                		        	tmp = OptRest.m_Jcode[0];
                        			tmp_stock = getSValue(tmp); // 20111024 수정
                        			GV.setStockD(tmp_stock);
                        			tmp_stock = getSValueA(tmp);
                        			tmp_stock = getWSValue(tmp);
                        			GV.setSR(0);
                        			dialog.dismiss();
            						startActivity(Ctest);
                		        }
                		    }).start();
                		}else if(y1 > 370 && y1 < 410 && OptRest.m_Jcode[1] != null){
                			dialog = ProgressDialog.show(Hint.this, "", OptRest.m_Kname[1]+" 정보 불러오는  중..", true, true);
                			new Thread(new Runnable() {
                		        
                		        public void run() {
                		        	tmp = OptRest.m_Jcode[1];
                        			tmp_stock = getSValue(tmp); // 20111024 수정
                        			GV.setStockD(tmp_stock);
                        			tmp_stock = getSValueA(tmp);
                        			tmp_stock = getWSValue(tmp);
                        			GV.setSR(0);
                        			dialog.dismiss();
            						startActivity(Ctest);
                		        }
                		    }).start();
                		}else if(y1 > 410 && y1 < 450 && OptRest.m_Jcode[2] != null){
                			dialog = ProgressDialog.show(Hint.this, "", OptRest.m_Kname[2]+" 정보 불러오는  중..", true, true);
                			new Thread(new Runnable() {
                		        
                		        public void run() {
                		        	tmp = OptRest.m_Jcode[2];
                        			tmp_stock = getSValue(tmp); // 20111024 수정
                        			GV.setStockD(tmp_stock);
                        			tmp_stock = getSValueA(tmp);
                        			tmp_stock = getWSValue(tmp);
                        			GV.setSR(0);
                        			dialog.dismiss();
            						startActivity(Ctest);
                		        }
                		    }).start();
                		}else if(y1 > 450 && y1 < 490 && OptRest.m_Jcode[3] != null){
                			dialog = ProgressDialog.show(Hint.this, "", OptRest.m_Kname[3]+" 정보 불러오는  중..", true, true);
                			new Thread(new Runnable() {
                		        
                		        public void run() {
                		        	tmp = OptRest.m_Jcode[3];
                        			tmp_stock = getSValue(tmp); // 20111024 수정
                        			GV.setStockD(tmp_stock);
                        			tmp_stock = getSValueA(tmp);
                        			tmp_stock = getWSValue(tmp);
                        			GV.setSR(0);
                        			dialog.dismiss();
            						startActivity(Ctest);
                		        }
                		    }).start();
                		}else if(y1 > 490 && y1 < 530 && OptRest.m_Jcode[4] != null){
                			dialog = ProgressDialog.show(Hint.this, "", OptRest.m_Kname[4]+" 정보 불러오는  중..", true, true);
                			new Thread(new Runnable() {
                		        
                		        public void run() {
                		        	tmp = OptRest.m_Jcode[4];
                        			tmp_stock = getSValue(tmp); // 20111024 수정
                        			GV.setStockD(tmp_stock);
                        			tmp_stock = getSValueA(tmp);
                        			tmp_stock = getWSValue(tmp);
                        			GV.setSR(0);
                        			dialog.dismiss();
            						startActivity(Ctest);
                		        }
                		    }).start();
                		}else if(y1 > 530 && y1 < 570 && OptRest.m_Jcode[5] != null){
                			dialog = ProgressDialog.show(Hint.this, "", OptRest.m_Kname[5]+" 정보 불러오는  중..", true, true);
                			new Thread(new Runnable() {
                		        
                		        public void run() {
                		        	tmp = OptRest.m_Jcode[5];
                        			tmp_stock = getSValue(tmp); // 20111024 수정
                        			GV.setStockD(tmp_stock);
                        			tmp_stock = getSValueA(tmp);
                        			tmp_stock = getWSValue(tmp);
                        			GV.setSR(0);
                        			dialog.dismiss();
            						startActivity(Ctest);
                		        }
                		    }).start();
                		}else if(y1 > 570 && y1 < 610 && OptRest.m_Jcode[6] != null){
                			dialog = ProgressDialog.show(Hint.this, "", OptRest.m_Kname[6]+" 정보 불러오는  중..", true, true);
                			new Thread(new Runnable() {
                		        
                		        public void run() {
                		        	tmp = OptRest.m_Jcode[6];
                        			tmp_stock = getSValue(tmp); // 20111024 수정
                        			GV.setStockD(tmp_stock);
                        			tmp_stock = getSValueA(tmp);
                        			tmp_stock = getWSValue(tmp);
                        			GV.setSR(0);
                        			dialog.dismiss();
            						startActivity(Ctest);
                		        }
                		    }).start();
                		}else if(y1 > 610 && y1 < 650 && OptRest.m_Jcode[7] != null){
                			dialog = ProgressDialog.show(Hint.this, "", OptRest.m_Kname[7]+" 정보 불러오는  중..", true, true);
                			new Thread(new Runnable() {
                		        
                		        public void run() {
                		        	tmp = OptRest.m_Jcode[7];
                        			tmp_stock = getSValue(tmp); // 20111024 수정
                        			GV.setStockD(tmp_stock);
                        			tmp_stock = getSValueA(tmp);
                        			tmp_stock = getWSValue(tmp);
                        			GV.setSR(0);
                        			dialog.dismiss();
            						startActivity(Ctest);
                		        }
                		    }).start();
                		}else if(y1 > 650 && y1 < 690 && OptRest.m_Jcode[8] != null){
                			dialog = ProgressDialog.show(Hint.this, "", OptRest.m_Kname[8]+" 정보 불러오는  중..", true, true);
                			new Thread(new Runnable() {
                		        
                		        public void run() {
                		        	tmp = OptRest.m_Jcode[8];
                        			tmp_stock = getSValue(tmp); // 20111024 수정
                        			GV.setStockD(tmp_stock);
                        			tmp_stock = getSValueA(tmp);
                        			tmp_stock = getWSValue(tmp);
                        			GV.setSR(0);
                        			dialog.dismiss();
            						startActivity(Ctest);
                		        }
                		    }).start();
                		}else if(y1 > 690 && y1 < 730 && OptRest.m_Jcode[9] != null){
                			dialog = ProgressDialog.show(Hint.this, "", OptRest.m_Kname[9]+" 정보 불러오는  중..", true, true);
                			new Thread(new Runnable() {
                		        
                		        public void run() {
                		        	tmp = OptRest.m_Jcode[9];
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
                		
                		test.invalidate();
                	}
                	
//*/
            	}
                
 				return true;
        }});
    	
    }
	
private OptD getOptValue(String Index, int Opt1, int Opt2, int Opt3, int Opt4){
    	
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
    		//Integer.valueOf(str).intValue();
    		SI = retVal.indexOf("<dNum data=");
    		EI = retVal.indexOf("/>", SI);
    		if(SI > 0){
//    			EI = retVal.indexOf("/>", SI);
//    			SI= SI+7;
    			SI = SI + 12;
    			EI = EI - 1;
    			resVal = retVal.substring(SI, EI);
//    			retVal = "";
//   			retVal = resVal;
    			
//    			return retVal;
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

	public class testView extends View {
    	int thr = 0;

    	public testView(Context context)
    	{
    		super(context);
    	}
    	public testView(Context context, AttributeSet attrs) {
    		super(context, attrs);
    	}
    	public void onDraw(Canvas canvas){
    		B_24.setColor(Color.WHITE);
    		B_24.setTextSize(24);
    		B_24.setAntiAlias(true);
    		
    		canvas.drawText("[옵션 선택]", 20, 120, B_24);
    		canvas.drawText("EPS 증가율 기준 정렬", 20, 170, B_24);
    		canvas.drawText("매출액 증가율 기준 정렬", 20, 220, B_24);
    		canvas.drawText("영업 이익 증가율 기준 정렬", 20, 270, B_24);
/*    		
    		if(lit > 10)
				lit = 10;
    		
    		if(view == 1){
    			canvas.drawText("[검색 결과]", 20, 320, B_24);
    			
	    		for(int k = 0 ; k< lit; k++){
	    			canvas.drawText(OptRest.m_Kname[k], 20, 350+(k*40), B_24);
	    		}
    		}
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
	
//				String connectUrl = "http://hongmsz.raonnet.com/Stock_data_final/" + dataStock.m_Jcode + ".xml";
				String connectUrl = "http://quantec.co.kr/Stock_data_final.html?id=" + dataStock.m_Jcode;
//				String connectUrl = "http://quantec.co.kr/Stock_data_final/" + dataStock.m_Jcode + ".xml";
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
	
//				String connectUrl = "http://quantec.co.kr/W_Stock_val/V" + S_Code + ".xml";
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
	
//				String connectUrl = "http://hongmsz.raonnet.com/Stock_data_final/" + dataStock.m_Jcode + ".xml";
//				String connectUrl = "http://quantec.co.kr/Stock_data_annual/A" + dataStock.m_Jcode + ".xml";
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
}
