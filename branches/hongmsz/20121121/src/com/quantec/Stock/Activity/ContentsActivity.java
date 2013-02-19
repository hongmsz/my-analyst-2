package com.quantec.Stock.Activity;


import com.quantec.Stock.MyApp;
import com.quantec.Stock.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;

public class ContentsActivity extends Activity {

	MyApp GV;
    private LinearLayout contentsContatiner;
	private static final float D_width = 800f;
	private static final float D_height = 480f;
    
    Color bgColor;
    
    testView test;
    Intent Cmenu1, Cmenu2, Cmenu3, Cmenu4, Cmenu5, Cmenu6;     
    float x1, y1;
    int tmp_sel = 0;
    

	int width, height;
	float c_width, c_height;
    
	int w_gap, w_gap9, w_gap11;
    
    static final int MAX_VIEW_DEPTH = 3;
    
    AlertDialog.Builder bld;
/*    
    private String mSub = "자세히 보기";
    private String mPre = "돌아가기";
    
    private String menu1 = "수익성";
    private String menu11 = "  1. EPS";
    private String menu12 = "  2. EPS & 매출액";
    private String menu13 = "  3. 매출액 & 영업이익 & 순이익";
    private String menu14 = "  4. 매출액영업이익률 & 매출액순이익률";
    private String menu15 = "  5. 영업외손익률";
    private String menu2 = "안전성";
    private String menu21 = "  1. 부채비율 & 유동비율";
    private String menu22 = "  2. 차입금 & 차입금비중";
    private String menu23 = "  3. 이자보상배율";
    private String menu24 = "  4. 자기자본비율";
    private String menu3 = "배당성";
    private String menu31 = "  1. 유보율";
    private String menu32 = "  2. 배당금 & 시가배당률";
    private String menu33 = "  3. 배당성향 & 시가배당률";
    private String menu4 = "성장성";
    private String menu41 = "  1. ROE & PBR";
    private String menu42 = "  2. 총자산 증가율";
    private String menu43 = "  3. ROA & ROE";
    private String menu5 = "효율성";
    private String menu51 = "  1. 총자산 회전율";
    private String menu52 = "  2. 매출채권 회전율";
    private String menu53 = "  3. 재고자산 회전율";
    private String menu6 = "가치평가";
    private String menu61 = "  1. 주가순자산비율(PBR)";
    private String menu62 = "  2. 주당순자산(BPS)";
*/    
	public void onCreate(Bundle savedInstanceState) {
		GV = (MyApp) getApplication();
		
		GV.setDisplay(((WindowManager)this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay());
		
		
		
		width = GV.getDisplay().getWidth();
		height= GV.getDisplay().getHeight();
		
		c_width = (float)width/D_width;
		c_height = (float)height/D_height;
		
		w_gap = MyApp.getDisplay().getHeight()/8;
		w_gap9 = MyApp.getDisplay().getHeight()/9;
		w_gap11 = MyApp.getDisplay().getHeight()/11;
		
		super.onCreate(savedInstanceState); 
		
		Cmenu1 = new Intent(this, Menu_One.class);
		Cmenu2 = new Intent(this, Menu_Two.class);
		Cmenu3 = new Intent(this, Menu_Three.class);
		Cmenu4 = new Intent(this, Menu_Four.class);
		Cmenu5 = new Intent(this, Menu_Five.class);
		Cmenu6 = new Intent(this, Menu_Six.class);
		
		bld = new AlertDialog.Builder(this);
		
		LinearLayout layout = new LinearLayout(this);
		layout.setBackgroundColor(bgColor.WHITE);
		
		createContentsContainer();
        layout.addView(contentsContatiner);
        
        setContentView(layout);

	}
	
	private void createContentsContainer(){
    	
    	contentsContatiner = new LinearLayout(this);
    	
    	test = new testView(this);
    	
    	contentsContatiner.addView(test);
//    	test.animate(test);
    	
    	test.setOnTouchListener(new OnTouchListener(){
        	 public boolean onTouch(View arg0, MotionEvent event) {            
        		 if (event.getAction() == MotionEvent.ACTION_DOWN) {
        			 x1=event.getX();
                	 y1=event.getY();
        			 
        			 if(x1 > MyApp.getDisplay().getWidth()*0.07 && x1 < MyApp.getDisplay().getWidth()*0.46){
            			 if(y1 > MyApp.getDisplay().getHeight()*0.13 && y1 < MyApp.getDisplay().getHeight()*0.36){
            				 tmp_sel = 1;
            			 }else if(y1 > MyApp.getDisplay().getHeight()*0.41 && y1 < MyApp.getDisplay().getHeight()*0.64){
            				 tmp_sel = 3;
            			 }else if(y1 > MyApp.getDisplay().getHeight()*0.69 && y1 < MyApp.getDisplay().getHeight()*0.92){
            				 tmp_sel = 5;
            			 } 
            		 }else if(x1 > MyApp.getDisplay().getWidth()*0.55 && x1 < MyApp.getDisplay().getWidth()*0.93){
            			 if(y1 > MyApp.getDisplay().getHeight()*0.13 && y1 < MyApp.getDisplay().getHeight()*0.36){
            				 tmp_sel = 2;
            			 }else if(y1 > MyApp.getDisplay().getHeight()*0.41 && y1 < MyApp.getDisplay().getHeight()*0.64){
            				 tmp_sel = 4;
            			 }else if(y1 > MyApp.getDisplay().getHeight()*0.69 && y1 < MyApp.getDisplay().getHeight()*0.92){
            				 tmp_sel = 6;
            			 } 
            		 }
    				 test.invalidate();
        			 
                 }else if (event.getAction() == MotionEvent.ACTION_UP) {
                	 tmp_sel = 0;
                	 x1=event.getX();
                	 y1=event.getY();
//MyApp.getDisplay().getWidth()-nSearch.getWidth()-35, 25
                	 if(GV.getSR()==0){
                		 if(x1 > 33 && x1 < MyApp.getDisplay().getWidth()-35 && y1 > 25 && y1 < 25+60*c_height){           		  
                    		 finish();
                    	 }
 
                		 if(x1 > MyApp.getDisplay().getWidth()*0.07 && x1 < MyApp.getDisplay().getWidth()*0.46){
                			 if(y1 > MyApp.getDisplay().getHeight()*0.13 && y1 < MyApp.getDisplay().getHeight()*0.36){
                				 GV.setSR(1);
                			 }else if(y1 > MyApp.getDisplay().getHeight()*0.41 && y1 < MyApp.getDisplay().getHeight()*0.64){
                				 GV.setSR(3);
                			 }else if(y1 > MyApp.getDisplay().getHeight()*0.69 && y1 < MyApp.getDisplay().getHeight()*0.92){
                				 GV.setSR(5);
                			 } 
                		 }else if(x1 > MyApp.getDisplay().getWidth()*0.55 && x1 < MyApp.getDisplay().getWidth()*0.93){
                			 if(y1 > MyApp.getDisplay().getHeight()*0.13 && y1 < MyApp.getDisplay().getHeight()*0.36){
                				 GV.setSR(2);
                			 }else if(y1 > MyApp.getDisplay().getHeight()*0.41 && y1 < MyApp.getDisplay().getHeight()*0.64){
                				 GV.setSR(4);
                			 }else if(y1 > MyApp.getDisplay().getHeight()*0.69 && y1 < MyApp.getDisplay().getHeight()*0.92){
                				 GV.setSR(6);
                			 } 
                		 }
        				 test.invalidate();
	         		  }else if(GV.getSR()==1){ 
	         			  if(y1 < MyApp.getDisplay().getHeight()*0.41 && y1 > MyApp.getDisplay().getHeight()*0.32){
		           			  GV.setSRM(1);
		           			  GV.setSRV(0);
		           			  startActivity(Cmenu1);
		           			  finish();
		           		  }else if(y1 < MyApp.getDisplay().getHeight()*0.5 && y1 > MyApp.getDisplay().getHeight()*0.41){
		           			  GV.setSRM(1);
		           			  GV.setSRV(1);
		           			  startActivity(Cmenu1);
		           			  finish();
		           		  }else if(y1 < MyApp.getDisplay().getHeight()*0.6 && y1 > MyApp.getDisplay().getHeight()*0.5){
		           			  GV.setSRM(1);
		           			  GV.setSRV(2);
		           			  startActivity(Cmenu1);
		           			  finish();
		           		  }else if(y1 < MyApp.getDisplay().getHeight()*0.69 && y1 > MyApp.getDisplay().getHeight()*0.6){
		          			  GV.setSRM(1);
		           			  GV.setSRV(3);
		           			  startActivity(Cmenu1);
		           			  finish();
		           		  }else if(y1 < MyApp.getDisplay().getHeight()*0.78 && y1 > MyApp.getDisplay().getHeight()*0.69){
		           			  GV.setSRM(1);
		           			  GV.setSRV(4);
		           			  startActivity(Cmenu1);
		           			  finish();
		           		  }else if(y1 > MyApp.getDisplay().getHeight()*0.87 && y1 < MyApp.getDisplay().getHeight()*0.92){
		           			  if(x1 > MyApp.getDisplay().getWidth()*0.66 && x1 < MyApp.getDisplay().getWidth()*0.94){
		           				GV.setSR(0);
		           				test.invalidate();
		           			  }
		           		  }
	         		  }else if(GV.getSR()==2){ 
	         			 if(y1 < MyApp.getDisplay().getHeight()*0.41 && y1 > MyApp.getDisplay().getHeight()*0.32){
		           			  GV.setSRM(2);
		           			  GV.setSRV(0);
		           			  startActivity(Cmenu2);
		           			  finish();
	         			}else if(y1 < MyApp.getDisplay().getHeight()*0.5 && y1 > MyApp.getDisplay().getHeight()*0.41){
		           			  GV.setSRM(2);
		           			  GV.setSRV(1);
		           			  startActivity(Cmenu2);
		           			  finish();
	         			}else if(y1 < MyApp.getDisplay().getHeight()*0.6 && y1 > MyApp.getDisplay().getHeight()*0.5){
		           			  GV.setSRM(2);
		           			  GV.setSRV(2);
		           			  startActivity(Cmenu2);
		           			  finish();
	         			}else if(y1 < MyApp.getDisplay().getHeight()*0.69 && y1 > MyApp.getDisplay().getHeight()*0.6){
		          			  GV.setSRM(2);
		           			  GV.setSRV(3);
		           			  startActivity(Cmenu2);
		           			  finish();
	         			}else if(y1 > MyApp.getDisplay().getHeight()*0.87 && y1 < MyApp.getDisplay().getHeight()*0.92){
		           			  if(x1 > MyApp.getDisplay().getWidth()*0.66 && x1 < MyApp.getDisplay().getWidth()*0.94){
		           				GV.setSR(0);
		           				test.invalidate();
		           			  }
		           		  }
	         		  }else if(GV.getSR()==3){
	         			 if(y1 < MyApp.getDisplay().getHeight()*0.41 && y1 > MyApp.getDisplay().getHeight()*0.32){
		           			  GV.setSRM(3);
		           			  GV.setSRV(0);
		           			  startActivity(Cmenu3);
		           			  finish();
	         			}else if(y1 < MyApp.getDisplay().getHeight()*0.5 && y1 > MyApp.getDisplay().getHeight()*0.41){
		           			  GV.setSRM(3);
		           			  GV.setSRV(1);
		           			  startActivity(Cmenu3);
		           			  finish();
	         			}else if(y1 < MyApp.getDisplay().getHeight()*0.6 && y1 > MyApp.getDisplay().getHeight()*0.5){
		           			  GV.setSRM(3);
		           			  GV.setSRV(2);
		           			  startActivity(Cmenu3);
		           			  finish();
	         			}else if(y1 > MyApp.getDisplay().getHeight()*0.87 && y1 < MyApp.getDisplay().getHeight()*0.92){
		           			  if(x1 > MyApp.getDisplay().getWidth()*0.66 && x1 < MyApp.getDisplay().getWidth()*0.94){
		           				GV.setSR(0);
		           				test.invalidate();
		           			  }
		           		  }
	         		  }else if(GV.getSR()==4){ 
	         			if(y1 < MyApp.getDisplay().getHeight()*0.41 && y1 > MyApp.getDisplay().getHeight()*0.32){
		           			GV.setSRM(4);
		           			GV.setSRV(0);
		           			startActivity(Cmenu4);
		           			finish();
	         			}else if(y1 < MyApp.getDisplay().getHeight()*0.5 && y1 > MyApp.getDisplay().getHeight()*0.41){
		           			GV.setSRM(4);
		           			GV.setSRV(1);
		           			startActivity(Cmenu4);
		           			finish();
	         			}else if(y1 < MyApp.getDisplay().getHeight()*0.6 && y1 > MyApp.getDisplay().getHeight()*0.5){
		           			GV.setSRM(4);
		           			GV.setSRV(2);
		           			startActivity(Cmenu4);
		           			finish();
	         			}else if(y1 > MyApp.getDisplay().getHeight()*0.87 && y1 < MyApp.getDisplay().getHeight()*0.92){
		           			  if(x1 > MyApp.getDisplay().getWidth()*0.66 && x1 < MyApp.getDisplay().getWidth()*0.94){
		           				GV.setSR(0);
		           				test.invalidate();
		           			  }
		           		  }
	         		  }else if(GV.getSR()==5){ 
	         			 if(y1 < MyApp.getDisplay().getHeight()*0.41 && y1 > MyApp.getDisplay().getHeight()*0.32){
		           			  GV.setSRM(5);
		           			  GV.setSRV(0);
		           			  startActivity(Cmenu5);
		           			  finish();
	         			}else if(y1 < MyApp.getDisplay().getHeight()*0.5 && y1 > MyApp.getDisplay().getHeight()*0.41){
		           			  GV.setSRM(5);
		           			  GV.setSRV(1);
		           			  startActivity(Cmenu5);
		           			  finish();
	         			}else if(y1 < MyApp.getDisplay().getHeight()*0.6 && y1 > MyApp.getDisplay().getHeight()*0.5){
		           			  GV.setSRM(5);
		           			  GV.setSRV(2);
		           			  startActivity(Cmenu5);
		           			  finish();
	         			}else if(y1 > MyApp.getDisplay().getHeight()*0.87 && y1 < MyApp.getDisplay().getHeight()*0.92){
		           			  if(x1 > MyApp.getDisplay().getWidth()*0.66 && x1 < MyApp.getDisplay().getWidth()*0.94){
		           				GV.setSR(0);
		           				test.invalidate();
		           			  }
		           		  }
	         		  }else if(GV.getSR()==6){ 
	         			 if(y1 < MyApp.getDisplay().getHeight()*0.41 && y1 > MyApp.getDisplay().getHeight()*0.32){
		           			  GV.setSRM(6);
		           			  GV.setSRV(0);
		           			  startActivity(Cmenu6);
		           			  finish();
	         			}else if(y1 < MyApp.getDisplay().getHeight()*0.5 && y1 > MyApp.getDisplay().getHeight()*0.41){
		           			  GV.setSRM(6);
		           			  GV.setSRV(1);
		           			  startActivity(Cmenu6);
		           			  finish();
	         			}else if(y1 > MyApp.getDisplay().getHeight()*0.87 && y1 < MyApp.getDisplay().getHeight()*0.92){
		           			  if(x1 > MyApp.getDisplay().getWidth()*0.66 && x1 < MyApp.getDisplay().getWidth()*0.94){
		           				GV.setSR(0);
		           				test.invalidate();
		           			  }
		           		  }
	         		  }
                 }
 				return true;
        }});
    	
    }
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(event.getAction() == KeyEvent.ACTION_DOWN){
			if(keyCode == KeyEvent.KEYCODE_BACK){

				if(GV.getSR()==0){
					finish();
					return true;
				}else{
					GV.setSR(0);
					test.invalidate();
				}
			}
		}
		return false;
	}

	public class testView extends View {
    	int thr = 0;
    	
    	Paint pnt = new Paint();
    	Paint pnt2 = new Paint();
    	
    	Bitmap vBG = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.bg_list_view);
    	Bitmap nSearch = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.search_i);
		Bitmap sBG1 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.list_sel_1);
		Bitmap sBG2 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.list_sel_2);
		Bitmap sBG3 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.list_sel_3);
		Bitmap sBG4 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.list_sel_4);
		Bitmap sBG5 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.list_sel_5);
		Bitmap sBG6 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.list_sel_6);
		Bitmap vName = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.kname);
    	
    	
    	public testView(Context context)
    	{
    		super(context);
    	}
    	public testView(Context context, AttributeSet attrs) {
    		super(context, attrs);
    	}
    	public void onDraw(Canvas canvas){
    		    		
    		pnt.setColor(Color.BLACK);
    		if(width>480)
    			pnt.setTextSize(35);
    		else
    			pnt.setTextSize(25);
    		
    		pnt2.setColor(Color.WHITE);
    		
    		pnt.setAntiAlias(true);
    		pnt2.setAntiAlias(true);
    		

    		Bitmap mBG1, mBG2, mBG3, mBG4, mBG5, mBG6;

    		
    		Rect src = new Rect(0,0,vBG.getWidth(),vBG.getHeight());
    		Rect dst = new Rect(0,0,MyApp.getDisplay().getWidth(),MyApp.getDisplay().getHeight());

    		if(MyApp.getSR() == 0){
    			canvas.drawRect(0, 0, MyApp.getDisplay().getWidth(), MyApp.getDisplay().getHeight(),pnt);
        		canvas.drawBitmap(nSearch, MyApp.getDisplay().getWidth()-nSearch.getWidth()-35, 25, pnt);
    			canvas.drawBitmap(vBG, src, dst, pnt);
//        		canvas.drawBitmap(vBG, 0, 0, pnt);
        		switch(tmp_sel){
	        		case 1:
	        			canvas.drawBitmap(sBG1, src, dst, pnt);
	        			break;
	        		case 2:
	        			canvas.drawBitmap(sBG2, src, dst, pnt);
	        			break;
	        		case  3:
	        			canvas.drawBitmap(sBG3, src, dst, pnt);
	        			break;
	        		case  4:
	        			canvas.drawBitmap(sBG4, src, dst, pnt);
	        			break;
	        		case  5:
	        			canvas.drawBitmap(sBG5, src, dst, pnt);
	        			break;
	        		case  6:
	        			canvas.drawBitmap(sBG6, src, dst, pnt);
	        			break;
        		}
    		}else if(MyApp.getSR() == 1){
    			mBG1 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.menu_view_1);
    			canvas.drawBitmap(mBG1, src, dst, pnt);
//    			canvas.drawBitmap(mBG1, 0, 0, pnt);
//				canvas.drawText(menu11, 50, 275, pnt2);
//				canvas.drawText(menu12, 50, 335, pnt2);
//				canvas.drawText(menu13, 50, 395, pnt2);
//				canvas.drawText(menu14, 50, 455, pnt2);
//				canvas.drawText(menu15, 50, 515, pnt2);
    		}else if(MyApp.getSR() == 2){
    			mBG2 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.menu_view_2);
    			canvas.drawBitmap(mBG2, src, dst, pnt);
//    			canvas.drawBitmap(mBG2, 0, 0, pnt);
//				canvas.drawText(menu21, 50, 275, pnt2);
//				canvas.drawText(menu22, 50, 335, pnt2);
//				canvas.drawText(menu23, 50, 395, pnt2);
//				canvas.drawText(menu24, 50, 455, pnt2);
    		}else if(MyApp.getSR() == 3){
    			mBG3 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.menu_view_3);
    			canvas.drawBitmap(mBG3, src, dst, pnt);
//    			canvas.drawBitmap(mBG3, 0, 0, pnt);
//				canvas.drawText(menu31, 50, 275, pnt2);
//				canvas.drawText(menu32, 50, 335, pnt2);
//				canvas.drawText(menu33, 50, 395, pnt2);
    		}else if(MyApp.getSR() == 4){
    			mBG4 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.menu_view_4);
    			canvas.drawBitmap(mBG4, src, dst, pnt);
//    			canvas.drawBitmap(mBG4, 0, 0, pnt);
//				canvas.drawText(menu41, 50, 275, pnt2);
//				canvas.drawText(menu42, 50, 335, pnt2);
//				canvas.drawText(menu43, 50,395, pnt2);
    		}else if(MyApp.getSR() == 5){
    			mBG5 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.menu_view_5);
    			canvas.drawBitmap(mBG5, src, dst, pnt);
//    			canvas.drawBitmap(mBG5, 0, 0, pnt);
//				canvas.drawText(menu51, 50, 275, pnt2);
//				canvas.drawText(menu52, 50, 335, pnt2);
//				canvas.drawText(menu53, 50, 395, pnt2);
    		}else if(MyApp.getSR() == 6){
    			mBG6 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.menu_view_6);
    			canvas.drawBitmap(mBG6, src, dst, pnt);
//    			canvas.drawBitmap(mBG6, 0, 0, pnt);
//				canvas.drawText(menu61, 50, 275, pnt2);
//				canvas.drawText(menu62, 50, 335, pnt2);
    		}
    		
    		canvas.drawBitmap(vName, 0, 25, pnt);
    		
    		if(GV.getStockD().m_Kname.length() > 10){
				canvas.drawText(GV.getStockD().m_Kname, 0, 10, 55*c_width, GV.getDisplay().getHeight()*0.01f+25+vName.getHeight()/2, pnt);
				canvas.drawText("...", 420*c_width, GV.getDisplay().getHeight()*0.01f+25+vName.getHeight()/2, pnt);
			}else{
				canvas.drawText(GV.getStockD().m_Kname, 55*c_width, GV.getDisplay().getHeight()*0.01f+25+vName.getHeight()/2, pnt);
			}
//			canvas.drawText(GV.getStockD().m_Kname, 55, 63, pnt);
    		
    	}
         
    	public void animate(final testView tmp) {
    		tmp.thr = 1;
     		
    		new Thread(new Runnable() {
    			
    			public void run() {
    				while(tmp.thr == 1){ 
    					tmp.postInvalidate();
    					try {
    						Thread.sleep(20);
    					} catch (InterruptedException e) {
    						e.printStackTrace();
    					}
    				}
    			}
    		}).start();
    	}
    }
}
