/*
 * 우량 자산으로 쌓이는가?
 */
package com.quantec.Stock.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;

import com.quantec.Stock.MyApp;
import com.quantec.Stock.R;

public class Menu_Three extends Activity{
	MyApp GV;
	
	private LinearLayout contentsContatiner;
	
	private static final int MAX_VIEW_DEPTH = 3;
	private static final float D_width = 800f;
	private static final float D_height = 480f;
	int title_position = 80;
	private static final int digit=5;
	int BEGIN_YEAR;
    
    testView test;
    
    Intent Cmenu1, Cmenu2, Cmenu3, Cmenu4, Cmenu5, Cmenu6;
    
    float x1, y1, x2, y2;
    int tmp_v;
    int duration;
    
	float b_x = 25;
	float c_y;
	float l_x = 16;
	float c_x;
	float r_c = 4;
	float t_i;
	
	long tmp_max, tmp_min;
	float f_max, f_min;
	int width, height;
	float c_width, c_height;
    
    int sub_menu = 0;
    int opt_dur=1;
    int Y_count;
    int view_val = -1;
    int digit_count=0; 
    long tmp_dig;
    int fix_dis = 0;
    
    float[] f_slong;				// 주가 변동 추세
    float[] f_sshort;				// 주가 변동 추세

    float f_stmp;					// 주가 변동 추세
    int macd=0;					// 주가 변동 추세
    int dur2;
    int dur_s = 5, dur_l=20;
    
    digitD tmp_D = new digitD();
    
    AlertDialog.Builder bld;
    
    String mTitle = "배당성";
    
    String exp1 = "[유보율]\n" +
    		"유보율 = 잉여금(자본잉여금 + 이익 잉여금) / 자본금 * 100\n" +
    		"(80%이상 양호, 40%이하 불량)\n" +
    		" * 기업이 스스로 얼마만큼의 자금을 동원 할 수 있는지를 측정하는 지표\n\n" +
    		"[지표분석방법]\n" +
    		"* 유보율이 높을수록 불황에 대한 기업의 적응력이 높다고 볼 수 있고, 또한 무상증자의 가능성을 측정하는 유용한 지표로도 이용됨\n\n" +
    		"[투자포인트]\n" +
    		"* 지속적으로 성장하는 회사의 경우에는 배당금을 받는것 보다 사내에 자금을 축적시켜 미래 수익을 극대화 시키는 것을 주주들은 선호하게 됨. 유보율이 높은 경우 무상증자의 가능성이 높음";
    String exp2 = "[배당금 & 시가배당률]\n" +
    		"시가배당률 =  배당금 총액 / 시가총액 * 100\n" +
    		" * 배당금이 배당기준일 주가의 몇%인가를 나타낸것";
    String exp3 = "[배당성향 & 시가배당률]\n" +
    		"배당성향 = 배당금 총액 / 당기순이익 * 100\n" +
    		"(40% 이하 양호  80% 이상 불량)\n" +
    		" * 당기순이익에 대한 현금배당액의 비율로서 배당지급률, 사외분배율이라고도 한다\n\n" +
    		"[지표분석방법]\n" +
    		"* 배당성향이 높아지면 배당금이 많아져서 재무구조 악화요인으로 작용되며, 배당성향이 낮아 질수록 사내 유보율이 높아져 무상증자의 가능성이 높아 진다.\n\n" +
    		"[투자포인트]\n" +
    		"* 성장업종인 경우 사내유보를 강화하여 배당성향이 낮아지고 성숙업종은 반대현상이 발생한다. 그러므로 배당성향 자체보다는 추세를 파악하는것이 중요하다.";
    
	public void onCreate(Bundle savedInstanceState) {
		
		GV = (MyApp) getApplication();
		
		GV.setDisplay(((WindowManager)this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay());
		BEGIN_YEAR = (GV.getStockD().m_quarter[0])/100 - 9;
		
		super.onCreate(savedInstanceState); 
		
		Cmenu1 = new Intent(this, ContentsActivity.class);
		
//		Cmenu1 = new Intent(this, Menu_One.class);
		Cmenu2 = new Intent(this, Menu_One.class);
		Cmenu3 = new Intent(this, Menu_Two.class);
		Cmenu4 = new Intent(this, Menu_Four.class);
		Cmenu5 = new Intent(this, Menu_Five.class);
		Cmenu6 = new Intent(this, Menu_Six.class);
		
		width = GV.getDisplay().getWidth();
		height= GV.getDisplay().getHeight();
		c_width = (float)width/D_width;
		c_height = (float)height/D_height;
		
		dur2 = (GV.getStockCount().last_sw/100 - ((GV.getStockCount().begin_sq/100)*100 + (GV.getStockCount().begin_sq%100 + 1)*3) - 1)*4 + GV.getStockCount().last_sw%100;
		
		title_position = height/6;
		
		f_slong = new float[(GV.getDuration()+1)*12];
		f_sshort = new float[(GV.getDuration()+1)*12];
		
		c_y = height *0.842f;
		
		b_x = width*0.03125f;
		l_x = width*0.02f;
		c_x = b_x + width*0.01f;
		t_i = height*(-0.0525f);
		
		bld = new AlertDialog.Builder(this);
		
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
//    	test.animate(test);
    	
    	test.setOnTouchListener(new OnTouchListener(){
    		public boolean onTouch(View arg0, MotionEvent event) {               
        		if (event.getAction() == MotionEvent.ACTION_DOWN) {
        			x1=event.getX();
        			y1=event.getY();
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
					y2=event.getY();
					if(sub_menu == 0){
						if( x1 > width*0.025f && x1 < width*0.235f && y1 > title_position-height*0.04f && y1 < title_position+height*0.04f){
							sub_menu = 2;
						}
						
						if( x1 > width*0.25f && x1 < width*0.68f && y1 > title_position-height*0.04f && y1 < title_position+height*0.04f){
							sub_menu = 1;
						}
						
						if( x1 < (float) (MyApp.getDisplay().getWidth()*0.90)+width*0.0625f && x1 > (float) (MyApp.getDisplay().getWidth()*0.90) && y1 > title_position-height*0.09f && y1 < title_position+height*0.01f){
//							GV.setStockD(null);
							finish();
						}
						//*// 주가 변동 추세						
						else if( x1 < (float) (MyApp.getDisplay().getWidth()*0.80)+width*0.0625f && x1 > (float) (MyApp.getDisplay().getWidth()*0.80) && y1 > title_position-height*0.09f && y1 < title_position+height*0.01f){
//							GV.setStockD(null);
							if(macd==0)
								macd=1;
							else
								macd=0;
						}
//*///주가 변동 추세		
						else if( y1 > height*0.24f && y1 < height*0.327f && x1 > width*0.075f && x1 < width*0.231f){ //가로 차트설명 버튼 위치
							view_val = -1;
							if(GV.getSRV() == 0) bld.setMessage(exp1);
							else if(GV.getSRV() == 1) bld.setMessage(exp2);
							else if(GV.getSRV() == 2) bld.setMessage(exp3);
							bld.setPositiveButton("닫기", new DialogInterface.OnClickListener() {
								
								public void onClick(DialogInterface dialog, int which) {
								}
							});
							bld.show();
						}else if( y1 > GV.getDisplay().getHeight()-height*0.1875f && y1 < GV.getDisplay().getHeight()-height*0.09375f){
							if(x1 > GV.getDisplay().getWidth()-width*0.125f && x1 < GV.getDisplay().getWidth()-width*0.05f){
								// 10	
								opt_dur = 0;
							}else if(x1 > GV.getDisplay().getWidth()-width*0.18125f && x1 < GV.getDisplay().getWidth()-width*0.125f){
								// 5
								opt_dur = 1;
							}else if(x1 > GV.getDisplay().getWidth()-width*0.2375f && x1 < GV.getDisplay().getWidth()-width*0.18125f){
								// 3
								opt_dur = 2;
							}
							view_val = -1;
						}else{
							view_val = -1;
							
							if((y1-y2)>7){
								tmp_v = GV.getSRV();
								tmp_v++;
								GV.setSRV(tmp_v);
								if(tmp_v > MAX_VIEW_DEPTH-1)
									GV.setSRV(0);
							}else if((y2-y1)>7){
								tmp_v = GV.getSRV();
								tmp_v--;
								GV.setSRV(tmp_v);
								if(tmp_v < 0)
									GV.setSRV(MAX_VIEW_DEPTH-1);
							}else if( y1 <= GV.getDisplay().getHeight()*0.8f && y1 >= GV.getDisplay().getHeight()*0.25f){
								if(opt_dur == 0){
									if(x1 >= width*0.075f && x1 <= GV.getDisplay().getWidth()-width*0.075f){
										view_val = (int) ((float)(x1-width*0.075f/*  */+((float)(width-width*0.15f)/(float)((duration+1)*12-1)*dur2) )*39.0f/(float)(GV.getDisplay().getWidth()-width*0.1875f));
									}
								}else if(opt_dur == 1){
									if(x1 >= width*0.075f && x1 <= GV.getDisplay().getWidth()-width*0.075f){
										view_val = (int) ((float)(x1-width*0.075f/*  */+((float)(width-width*0.15f)/(float)((duration+1)*12-1)*dur2) )*19.0f/(float)(GV.getDisplay().getWidth()-width*0.1875f));
									}
								}else if(opt_dur == 2){
									if(x1 >= width*0.075f && x1 <= GV.getDisplay().getWidth()-width*0.075f){
										view_val = (int) ((float)(x1-width*0.075f/*  */+((float)(width-width*0.15f)/(float)((duration+1)*12-1)*dur2) )*11.0f/(float)(GV.getDisplay().getWidth()-width*0.1875f));
									}
								}
							}
						}
					}else if(sub_menu==1){
						view_val = -1;
						
						if(y1 > title_position-height*0.042f && y1 < title_position+height*0.042f){
							GV.setSRV(0);
							sub_menu = 0;
						}else if(y1 > title_position+height*0.0625f && y1 < title_position+height*0.1458f){
							GV.setSRV(1);
							sub_menu = 0;
						}else if(y1 > title_position+height*0.167f && y1 < title_position+height*0.25f){
							GV.setSRV(2);
							sub_menu = 0;
						}else{
							sub_menu=0;
						}
					}else{
						view_val = -1;
						
						if(y1 > title_position+height*0.0625f && y1 < title_position+height*0.1458f){
							GV.setSRM(1);
		           			GV.setSRV(0);
		           			sub_menu=0;
		           			startActivity(Cmenu2);
		           			finish();
						}else if(y1 > title_position+height*0.167f && y1 < title_position+height*0.25f){
							GV.setSRM(2);
		           			GV.setSRV(0);
		           			sub_menu=0;
		           			startActivity(Cmenu3);
		           			finish();
						}else if(y1 > title_position+height*0.271f && y1 < title_position+height*0.354f){
							GV.setSRM(4);
		           			GV.setSRV(0);
		           			sub_menu=0;
		           			startActivity(Cmenu4);
		           			finish();
						}else if(y1 > title_position+height*0.375f && y1 < title_position+height*0.458f){
							GV.setSRM(5);
		           			GV.setSRV(0);
		           			sub_menu=0;
		           			startActivity(Cmenu5);
		           			finish();
						}else if(y1 > title_position+height*0.4792f && y1 < title_position+height*0.5625f){
							GV.setSRM(6);
		           			GV.setSRV(0);
		           			sub_menu=0;
		           			startActivity(Cmenu6);
		           			finish();
						}else{
							sub_menu=0;
						}
					}
					test.invalidate();
                }
 				return true;
        }});
    	
    }

	public class testView extends View {
		int thr = 0;

		Bitmap map001 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.h_bg);
		Bitmap map003 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.seach_l);
		Bitmap map006 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.help);
		Bitmap map004 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.off_03);
		Bitmap map007 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.off_05);
		Bitmap map008 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.off_10);
		Bitmap map009 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.on_03);
		Bitmap map010 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.on_05);
		Bitmap map011 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.on_10);
		Bitmap map012 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.m3);
		Bitmap map013 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.sm3);
		Bitmap map014 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.mm3);

		Bitmap map015 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.search_ss); // 주가 변동 추세

		Bitmap c01 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cloud_up);
		Bitmap c02 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cloud_mid);
		Bitmap c03 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cloud_down);
		
		Bitmap ci01 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cloud_i_up);
		Bitmap ci02 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cloud_i_mid);
		Bitmap ci03 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cloud_i_down);
		

    	ShapeDrawable _Drawable = new ShapeDrawable(new OvalShape());
    	ShapeDrawable _Drawable2 = new ShapeDrawable(new OvalShape());
    	
		float[] posX = new float[GV.getDuration()+1];
		float[] tmp_posX = new float[GV.getDuration()+1];
    	float[] posY = new float[GV.getDuration()+1];
    	float[] posY_t1 = new float[GV.getDuration()+1];


    	
    	long[] tmp_sort = new long[GV.getDuration()+1];
//    	int[] tmp_sort2 = new int[GV.getDuration()];
    	long[] tmp_origin = new long[GV.getDuration()+1];
//    	int[] tmp_origin2 = new int[GV.getDuration()];
//    	int[] tmp_sort3 = new int[GV.getDuration()];
//    	int[] tmp_origin3 = new int[GV.getDuration()];
    	
    	int[] tmp_sort_sv = new int[(GV.getDuration()+1)*12];
    	int[] tmp_origin_sv = new int[(GV.getDuration()+1)*12];
    	
    	float[] posX_sv = new float[(GV.getDuration()+1)*12];
    	float[] posY_sv = new float[(GV.getDuration()+1)*12];
    	
    	Paint pnt = new Paint();
    	Paint W_24 = new Paint();
    	Paint pnt3 = new Paint();
    	Paint pnt4 = new Paint();
    	Paint B_18 = new Paint();
    	Paint W_18 = new Paint();
    	Paint B_24 = new Paint();

    	Paint stock_C1 = new Paint();
    	Paint stock_C2 = new Paint();

    	public testView(Context context)
    	{
    		super(context);
    	}
    	public testView(Context context, AttributeSet attrs) {
    		super(context, attrs);
    	}
    	public void onDraw(Canvas canvas){
    		
    		String v_max1,v_min1, v1,v2,v3,v4,uWon1,uWon2,uPer; 
    		
    		duration = GV.getDuration();
    		width = GV.getDisplay().getWidth();
    		height = GV.getDisplay().getHeight();
    		
    		_Drawable.getPaint().setColor(0xffAAAAAA);
    		_Drawable2.getPaint().setColor(0xffFFC600);
    		
    		QuickSort sorter = new QuickSort();
    		makeDigit mD = new makeDigit();

    		pnt.setColor(0xffA90000);//red
    		pnt.setTextSize(18*c_height);
    		W_24.setColor(Color.WHITE);
    		W_24.setTextSize(24*c_height);
    		pnt3.setColor(0xff0071d1);//blue
    		pnt3.setTextSize(18*c_height);
    		pnt3.setStrokeWidth(3);	// 선 굵기 조정 
    		pnt4.setColor(0xff079200);//green
    		pnt4.setTextSize(18*c_height);
    		pnt4.setStrokeWidth(3);	// 선 굵기 조정 
    		B_18.setColor(Color.BLACK);
    		B_18.setTextSize(18*c_height);
    		W_18.setColor(Color.WHITE);
    		W_18.setTextSize(18*c_height);
    		B_24.setColor(Color.WHITE);//BLACK);
    		B_24.setTextSize(24*c_height);
    		
    		stock_C1.setColor(0xff079200);//yellow
    		stock_C1.setStrokeWidth(3);	// 선 굵기 조정
    		stock_C2.setColor(0xffFFC600);//yellow
    		stock_C2.setStrokeWidth(3);	// 선 굵기 조정
    		
    		
    		pnt.setAntiAlias(true);
    		W_24.setAntiAlias(true);
    		pnt3.setAntiAlias(true);
    		pnt4.setAntiAlias(true);
    		B_18.setAntiAlias(true);
    		W_18.setAntiAlias(true);
    		B_24.setAntiAlias(true);
    		
    		Rect src = new Rect(0,0,map001.getWidth(),map001.getHeight());
    		Rect dst = new Rect(0,0,MyApp.getDisplay().getWidth(),MyApp.getDisplay().getHeight());
    		
    		if(GV.getSRM() == 3){
    			int dis_ori = 1;  
    			if(dis_ori == 1){

    				if(opt_dur == 0){
	    				duration = 40;
	    				Y_count = BEGIN_YEAR;
    				}else if(opt_dur==1){
	    				duration = 20;
	    				Y_count = BEGIN_YEAR+5;
	    			}
	    			else if(opt_dur==2){
	    				duration = 12;
	    				Y_count = BEGIN_YEAR+7;
	    			}
    				
    				canvas.drawBitmap(map001, src, dst, pnt);
    				canvas.drawBitmap(map012, width*0.025f, title_position-height*0.12f, pnt);
    				canvas.drawBitmap(map014, width*0.025f, title_position-height*0.06f, pnt);
    				canvas.drawBitmap(map013, width*0.25f, title_position-height*0.06f, pnt);
//    				canvas.drawText("[ ", 45, title_position-40, B_24); 
    				if(GV.getStockD().m_Kname.length() > 8){
    					canvas.drawText(GV.getStockD().m_Kname, 0, 8, width*0.0625f, title_position-height*0.08f, W_24);
    					canvas.drawText("...", width*0.28f, title_position-height*0.08f, W_24);
    				}else{
    					canvas.drawText(GV.getStockD().m_Kname, width*0.0625f, title_position-height*0.08f, W_24);
//    					canvas.drawText(" - "+mTitle, 54 + (GV.getStockD().m_Kname.length())*20, title_position-40, B_24);
    				}
    				canvas.drawText(mTitle, width*0.0425f, title_position, W_24);
    				canvas.drawBitmap(map003, (float) (MyApp.getDisplay().getWidth()*0.90), title_position-height*0.104f, pnt);
    				canvas.drawBitmap(map015, MyApp.getDisplay().getWidth()*0.80f, title_position-50*c_height, pnt);
    				
    				for(int dx = 0; dx< (duration+1)*12; dx++)
						posX_sv[dx] = (float)(width-width*0.15f)/(float)((duration+1)*12-1)*(float)dx + width*0.075f;
	    			
	    			for(int dx = 0; dx< duration; dx++){
						posX[dx] = (float)(width-width*0.15f)/(float)(duration)*dx + width*0.075f /* */ - (float)(width-width*0.15f)/(float)((duration+1)*12-1)*dur2;;
	    			}
	    			for(int dx = 0; dx< duration; dx++){
						if(GV.getStockD().m_quarter[duration - dx-1]%4 == 1 && posX[dx] > width*0.067f){
							canvas.drawText(Y_count+"", posX[dx], height*(float)0.77+height*0.03125f, W_18);
							Y_count++;
							
							canvas.drawLine(posX[dx], height*(float)0.23, posX[dx], height*(float)0.77, _Drawable.getPaint());
						}
	    			}
    			}
    			
    			if(opt_dur == 0){
	    			canvas.drawBitmap(map004, GV.getDisplay().getWidth()-width*0.2375f, GV.getDisplay().getHeight()-height*0.1875f, pnt);
	    			canvas.drawBitmap(map007, GV.getDisplay().getWidth()-width*0.18125f, GV.getDisplay().getHeight()-height*0.1875f, pnt);
	    			canvas.drawBitmap(map011, GV.getDisplay().getWidth()-width*0.125f, GV.getDisplay().getHeight()-height*0.1875f, pnt);
    			}else if(opt_dur == 1){
	    			canvas.drawBitmap(map004, GV.getDisplay().getWidth()-width*0.2375f, GV.getDisplay().getHeight()-height*0.1875f, pnt);
	    			canvas.drawBitmap(map010, GV.getDisplay().getWidth()-width*0.18125f, GV.getDisplay().getHeight()-height*0.1875f, pnt);
	    			canvas.drawBitmap(map008, GV.getDisplay().getWidth()-width*0.125f, GV.getDisplay().getHeight()-height*0.1875f, pnt);
    			}else if(opt_dur == 2){
	    			canvas.drawBitmap(map009, GV.getDisplay().getWidth()-width*0.2375f, GV.getDisplay().getHeight()-height*0.1875f, pnt);
	    			canvas.drawBitmap(map007, GV.getDisplay().getWidth()-width*0.18125f, GV.getDisplay().getHeight()-height*0.1875f, pnt);
	    			canvas.drawBitmap(map008, GV.getDisplay().getWidth()-width*0.125f, GV.getDisplay().getHeight()-height*0.1875f, pnt);
    			}
				if(GV.getSRV() == 0){
////////////////////////////////////////////view 1 begin										
					float[] f_sort = new float[duration];
			    	float[] f_origin = new float[duration];
					
					canvas.drawText("유보율", width*0.2675f, title_position, W_24);

					if(opt_dur == 0){
						for(int dx = 0; dx< (duration+1)*12; dx++){
							if(GV.getStockD().v_stock[dx] == -1 && dx > 0){
								tmp_sort_sv[dx] = tmp_sort_sv[dx-1]; 
								tmp_origin_sv[dx] = GV.getStockD().v_stock[dx];
							}else
								tmp_sort_sv[dx] = tmp_origin_sv[dx] = GV.getStockD().v_stock[dx];
						}
					}else if(opt_dur == 1){
						for(int dx = 0; dx< (duration+1)*12; dx++){
							if(GV.getStockD().v_stock[dx+240] == -1 && dx > 0){
								tmp_sort_sv[dx] = tmp_sort_sv[dx-1]; 
								tmp_origin_sv[dx] = GV.getStockD().v_stock[dx+240];
							}else
								tmp_sort_sv[dx] = tmp_origin_sv[dx] = GV.getStockD().v_stock[dx+240];
						}
					}else if(opt_dur == 2){
						for(int dx = 0; dx< (duration+1)*12; dx++){
							if(GV.getStockD().v_stock[dx+336] == -1 && dx > 0){
								tmp_sort_sv[dx] = tmp_sort_sv[dx-1]; 
								tmp_origin_sv[dx] = GV.getStockD().v_stock[dx+336];
							}else
								tmp_sort_sv[dx] = tmp_origin_sv[dx] = GV.getStockD().v_stock[dx+336];
						}
					}
					
					for(int dx = 0; dx< duration; dx++){
						if(GV.getStockD().m_m33[dx] == 0)
							f_sort[dx] = f_origin[dx] = 0;
						else
							f_sort[dx]=f_origin[dx] = (float)(GV.getStockD().m_m31[dx]+GV.getStockD().m_m32[dx])/(float)GV.getStockD().m_m33[dx];
					}
					
					canvas.drawLine(b_x, c_y , b_x+l_x, c_y , pnt3);
					canvas.drawCircle(c_x, c_y , r_c, pnt3);
					canvas.drawCircle(c_x, c_y , r_c -1, W_24);
//					canvas.drawText("유보율", c_x + 10, c_y  + 10, B_24);

					// 주가		 
					sorter.sort(tmp_sort_sv, (duration+1)*12);
					
					tmp_max = tmp_sort_sv[(duration+1)*12-1];
					tmp_min = tmp_sort_sv[0];
					
					for(int dy = 0; dy<(duration+1)*12 ; dy++){
						posY_sv[dy] = height*(float)0.75 -(float)(tmp_origin_sv[dy]-tmp_min)/(float)(tmp_max-tmp_min)*height*(float)0.5;
					}
					 
					pnt.setStrokeWidth(1);
					for(int dr = 0; dr < (duration+1)*12-1; dr++){
						if(tmp_origin_sv[dr+1] != -1 && posX_sv[dr] > width*0.067f)
							canvas.drawLine(posX_sv[dr], posY_sv[dr], posX_sv[dr+1], posY_sv[dr+1], pnt);
					}
//*// 주가 변동 추세					
					for(int dy = dur_s-1; dy<(duration+1)*12 ; dy++){
						f_stmp = 0;
						for(int durt=0; durt<dur_s; durt++){
							f_stmp = f_stmp + posY_sv[dy-durt];  
						}
						f_sshort[dy]=f_stmp/dur_s;
					}
					
					for(int dy = dur_l-1; dy<(duration+1)*12 ; dy++){
						f_stmp = 0;
						for(int durt=0; durt<dur_l; durt++){
							f_stmp = f_stmp + posY_sv[dy-durt];  
						}
						f_slong[dy]=f_stmp/dur_l;
					}
					
					if(macd==1){
						for(int dr = dur_s-1; dr < (duration+1)*12-1; dr++){
							if(tmp_origin_sv[dr+1] != -1 && posX_sv[dr] > width*0.067f)
								canvas.drawLine(posX_sv[dr], f_sshort[dr], posX_sv[dr+1], f_sshort[dr+1], stock_C1);
						}
						for(int dr = dur_l-1; dr < (duration+1)*12-1; dr++){
							if(tmp_origin_sv[dr+1] != -1  && posX_sv[dr] > width*0.067f)
								canvas.drawLine(posX_sv[dr], f_slong[dr], posX_sv[dr+1], f_slong[dr+1], stock_C2);
						}
					}
//*/// 주가 변동 추세
					// 유보율
					//*   		 
					sorter.sort(f_sort, duration);
					
					f_max = f_sort[duration-1];
					f_min = f_sort[0];
					
					for(int dy = 0; dy<duration ; dy++){
						posY[dy] = height*(float)0.75 -(float)(f_origin[duration - dy -1]-f_min)/(float)(f_max-f_min)*height*(float)0.5;
					}
					//*/
					for(int dr = 0; dr < duration-1; dr++){
						 if(posX[dr] > width*0.067f)
							 canvas.drawLine(posX[dr], posY[dr], posX[dr+1], posY[dr+1], pnt3);
					}
										
					if(dis_ori == 1 || dis_ori == 3){
						canvas.drawText("유보율 (%)", c_x + 10, c_y  + 10, B_24);
						
						tmp_D = null;
						tmp_D = mD.Fdigit(f_max, f_min);
						uWon2 = tmp_D.uWon;
						digit_count = tmp_D.dCount;
						v_max1 = tmp_D.max;
						v_min1 = tmp_D.min;
						v1 = tmp_D.v1;
						v2 = tmp_D.v2;
						v3 = tmp_D.v3;
						v4 = tmp_D.v4;
						
						canvas.drawText(v_max1, 5, height*0.26f, W_18);
						canvas.drawText(v4, 5, height*0.35f, W_18);
						canvas.drawText(v3, 5, height*0.45f, W_18);
						canvas.drawText(v2, 5, height*0.56f, W_18);
						canvas.drawText(v1, 5, height*0.66f, W_18);
						canvas.drawText(v_min1, 5, height*0.77f, W_18);
					
						for(int dr = 0; dr < duration; dr++)
							if(posX[dr] > width*0.067f){
								canvas.drawCircle(posX[dr], posY[dr], 4, pnt3);
								canvas.drawCircle(posX[dr], posY[dr], 3, W_24);
						}
					}

					if(view_val >= 0 && view_val < 15){
						canvas.drawBitmap(c01, posX[view_val], posY[view_val]-c01.getHeight(), pnt);
						canvas.drawText(String.format("%.2f", Float.valueOf(f_origin[duration-1-view_val])), posX[view_val]+7*c_height, posY[view_val]-c01.getHeight()/2, B_18);
					}else if(view_val>=15 && view_val < duration){
						canvas.drawBitmap(ci01, posX[view_val]-ci01.getWidth(), posY[view_val]-ci01.getHeight(), pnt);
						canvas.drawText(String.format("%.2f", Float.valueOf(f_origin[duration-1-view_val])), posX[view_val]-ci01.getWidth()+5, posY[view_val]-c01.getHeight()/2, B_18);
					}
				}else if(GV.getSRV() == 1){
//////////////////////////////////////////////view 2 begin
					float[] f_sort = new float[duration];
			    	float[] f_origin = new float[duration];
			    	long[] m34_tmp = new long[duration];
			    	long[] m35_tmp = new long[duration];
			    	int[] vs_tmp = new int[duration];
					
					canvas.drawText("배당금 & 시가배당률", width*0.2675f, title_position, W_24);

					if(opt_dur == 0){
						for(int dx = 0; dx< (duration+1)*12; dx++){
							if(GV.getStockD().v_stock[dx] == -1 && dx > 0){
								tmp_sort_sv[dx] = tmp_sort_sv[dx-1]; 
								tmp_origin_sv[dx] = GV.getStockD().v_stock[dx];
							}else
								tmp_sort_sv[dx] = tmp_origin_sv[dx] = GV.getStockD().v_stock[dx];
						}
					}else if(opt_dur == 1){
						for(int dx = 0; dx< (duration+1)*12; dx++){
							if(GV.getStockD().v_stock[dx+240] == -1 && dx > 0){
								tmp_sort_sv[dx] = tmp_sort_sv[dx-1]; 
								tmp_origin_sv[dx] = GV.getStockD().v_stock[dx+240];
							}else
								tmp_sort_sv[dx] = tmp_origin_sv[dx] = GV.getStockD().v_stock[dx+240];
						}
					}else if(opt_dur == 2){
						for(int dx = 0; dx< (duration+1)*12; dx++){
							if(GV.getStockD().v_stock[dx+336] == -1 && dx > 0){
								tmp_sort_sv[dx] = tmp_sort_sv[dx-1]; 
								tmp_origin_sv[dx] = GV.getStockD().v_stock[dx+336];
							}else
								tmp_sort_sv[dx] = tmp_origin_sv[dx] = GV.getStockD().v_stock[dx+336];
						}
					}
					
					int tmp_count = 0;
					for(int dx = 0; dx< duration; dx++){
						if(GV.getStockD().m_quarter[dx]%4 == 0){
							m34_tmp[tmp_count] = GV.getStockD().m_m34[dx];
							m35_tmp[tmp_count] = GV.getStockD().m_m35[dx];
							vs_tmp[tmp_count] = tmp_origin_sv[(duration - dx)*12];
							tmp_count++;
						}
					}
					
					for(int dx = 0; dx< tmp_count; dx++){
						tmp_sort[dx] = tmp_origin[dx] = m34_tmp[dx];
						
						if(m35_tmp[dx] == 0)
							f_sort[dx] = f_origin[dx] = 0;
						else
							f_sort[dx]=f_origin[dx] = (float)(m34_tmp[dx])/(float)(m35_tmp[dx]*vs_tmp[dx]);
					}
/*					
					for(int dx = 0; dx< duration; dx++){
						tmp_sort[dx]=tmp_origin[dx] = GV.getStockD().m_m34[dx];
						
						if(GV.getStockD().m_m35[dx] == 0)
							f_sort[dx] = f_origin[dx] = 0;
						else
							f_sort[dx]=f_origin[dx] = (float)(GV.getStockD().m_m34[dx])/(float)(GV.getStockD().m_m35[dx]*GV.getStockD().v_stock[(duration - dx -1)*12]);
					}
//*/					
					canvas.drawLine(b_x, c_y - t_i, b_x+l_x, c_y - t_i, pnt4);
					canvas.drawCircle(c_x, c_y - t_i, r_c, pnt4);
					canvas.drawCircle(c_x, c_y - t_i, r_c -1, W_24);
//					canvas.drawText("배당금", c_x + 10, c_y - t_i + 10, B_24);
					
					canvas.drawLine(b_x, c_y, b_x+l_x, c_y, pnt3);
					canvas.drawCircle(c_x, c_y, r_c, pnt3);
					canvas.drawCircle(c_x, c_y, r_c -1, W_24);
//					canvas.drawText("시가배당률", c_x + 10, c_y + 10, B_24);
					

					// 주가		 
					sorter.sort(tmp_sort_sv, (duration+1)*12);
					
					tmp_max = tmp_sort_sv[(duration+1)*12-1];
					tmp_min = tmp_sort_sv[0];
					
					for(int dy = 0; dy<(duration+1)*12 ; dy++){
						posY_sv[dy] = height*(float)0.75 -(float)(tmp_origin_sv[dy]-tmp_min)/(float)(tmp_max-tmp_min)*height*(float)0.5;
					}
					 
					pnt.setStrokeWidth(1);
					for(int dr = 0; dr < (duration+1)*12-1; dr++){
						if(tmp_origin_sv[dr+1] != -1  && posX_sv[dr] > width*0.067f)
							canvas.drawLine(posX_sv[dr], posY_sv[dr], posX_sv[dr+1], posY_sv[dr+1], pnt);
					}
//*// 주가 변동 추세					
					for(int dy = dur_s-1; dy<(duration+1)*12 ; dy++){
						f_stmp = 0;
						for(int durt=0; durt<dur_s; durt++){
							f_stmp = f_stmp + posY_sv[dy-durt];  
						}
						f_sshort[dy]=f_stmp/dur_s;
					}
					
					for(int dy = dur_l-1; dy<(duration+1)*12 ; dy++){
						f_stmp = 0;
						for(int durt=0; durt<dur_l; durt++){
							f_stmp = f_stmp + posY_sv[dy-durt];  
						}
						f_slong[dy]=f_stmp/dur_l;
					}
					
					if(macd==1){
						for(int dr = dur_s-1; dr < (duration+1)*12-1; dr++){
							if(tmp_origin_sv[dr+1] != -1 && posX_sv[dr] > width*0.067f)
								canvas.drawLine(posX_sv[dr], f_sshort[dr], posX_sv[dr+1], f_sshort[dr+1], stock_C1);
						}
						for(int dr = dur_l-1; dr < (duration+1)*12-1; dr++){
							if(tmp_origin_sv[dr+1] != -1 && posX_sv[dr] > width*0.067f)
								canvas.drawLine(posX_sv[dr], f_slong[dr], posX_sv[dr+1], f_slong[dr+1], stock_C2);
						}
					}
//*/// 주가 변동 추세
					// 배당금
					//*  
					
					sorter.sort(tmp_sort, tmp_count);
					
					tmp_max = tmp_sort[tmp_count-1];
					tmp_min = tmp_sort[0];
					
					for(int dy = 0; dy<tmp_count; dy++){
						if(tmp_max-tmp_min == 0)
							posY_t1[dy] = posY[dy] = height*(float)0.75;
						else
							posY_t1[dy] = posY[dy] = height*(float)0.75 -(float)(tmp_origin[tmp_count - dy -1]-tmp_min)/(float)(tmp_max-tmp_min)*height*(float)0.5;
					}
//*					
					int tmp_count_posX=0;
					for(int dx = 0; dx< duration; dx++)
						if(GV.getStockD().m_quarter[duration-dx-1]%4 == 0){
							tmp_posX[tmp_count_posX] = posX[dx];
							tmp_count_posX++;
						}
//*/					
//*
					for(int dr = 0; dr < tmp_count-1; dr++)
						if(tmp_posX[dr] > width*0.067f)
							canvas.drawLine(tmp_posX[dr], posY[dr], tmp_posX[dr+1], posY[dr+1], pnt4);
					
					if(dis_ori == 1 || dis_ori == 3){
						tmp_D = null;
						tmp_D = mD.Ldigit(tmp_max, tmp_min, digit);								
						uWon1 = tmp_D.uWon;
						digit_count = tmp_D.dCount;
						v_max1 = tmp_D.max;
						v_min1 = tmp_D.min;
						v1 = tmp_D.v1;
						v2 = tmp_D.v2;
						v3 = tmp_D.v3;
						v4 = tmp_D.v4;
						
						canvas.drawText("배당금"+uWon1 + " ←", c_x + 10, c_y - t_i + 10, B_24);
												
						canvas.drawText(v_max1, 5, height*0.26f, W_18);
						canvas.drawText(v4, 5, height*0.35f, W_18);
						canvas.drawText(v3, 5, height*0.45f, W_18);
						canvas.drawText(v2, 5, height*0.56f, W_18);
						canvas.drawText(v1, 5, height*0.66f, W_18);
						canvas.drawText(v_min1, 5, height*0.77f, W_18);
					
						for(int dr = 0; dr < tmp_count; dr++)
							if( tmp_posX[dr] > width*0.067f){
								canvas.drawCircle(tmp_posX[dr], posY[dr], 4, pnt4);
								canvas.drawCircle(tmp_posX[dr], posY[dr], 3, W_24);
							}
					}
					if(view_val > 0){
						tmp_dig = 1l;
						for(int xx = 0; xx < digit_count; xx++){
							tmp_dig *=10l;
						}
					}
					
					// 시가배당률
					sorter.sort(f_sort, tmp_count);
					
					f_max = f_sort[tmp_count-1];
					f_min = f_sort[0];
					
					for(int dy = 0; dy<tmp_count; dy++){
						if(f_max - f_min == 0)
							posY[dy] = height*(float)0.75;
						else
							posY[dy] = height*(float)0.75 -(float)(f_origin[tmp_count - dy -1]-f_min)/(float)(f_max-f_min)*height*(float)0.5;
					}
				
//*
					for(int dr = 0; dr < tmp_count-1; dr++)
						if( tmp_posX[dr] > width*0.067f)
							canvas.drawLine(tmp_posX[dr], posY[dr], tmp_posX[dr+1], posY[dr+1], pnt3);
										
					if(dis_ori == 1 || dis_ori == 3){
						canvas.drawText("시가배당률 (%) →", c_x + 10, c_y + 10, B_24);

						tmp_D = null;
						tmp_D = mD.Fdigit(f_max*100f, f_min*100f);
						uWon2 = tmp_D.uWon;
						digit_count = tmp_D.dCount;
						v_max1 = tmp_D.max;
						v_min1 = tmp_D.min;
						v1 = tmp_D.v1;
						v2 = tmp_D.v2;
						v3 = tmp_D.v3;
						v4 = tmp_D.v4;
						
						canvas.drawText(v_max1, width-2 - v_max1.length()*11*c_height, height*0.26f, W_18);
						canvas.drawText(v4, width-2 - v_max1.length()*11*c_height, height*0.35f, W_18);
						canvas.drawText(v3, width-2 - v_max1.length()*11*c_height, height*0.45f, W_18);
						canvas.drawText(v2, width-2- v_max1.length()*11*c_height, height*0.56f, W_18);
						canvas.drawText(v1, width-2- v_max1.length()*11*c_height, height*0.66f, W_18);
						canvas.drawText(v_min1, width-2 - v_max1.length()*11*c_height, height*0.77f, W_18);
					
						for(int dr = 0; dr < tmp_count; dr++)
							if( tmp_posX[dr] > width*0.067f){
								canvas.drawCircle(tmp_posX[dr], posY[dr], 4, pnt3);
								canvas.drawCircle(tmp_posX[dr], posY[dr], 3, W_24);
							}
					}
					
					if(view_val >= 0 && view_val < 15){
						if(posY[view_val/4]<posY_t1[view_val/4]){
							canvas.drawBitmap(c01, tmp_posX[view_val/4], posY[view_val/4]-c01.getHeight(), pnt);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin[tmp_count-1-view_val/4]*100.0f)), tmp_posX[view_val/4]+7*c_height, posY[view_val/4]-c01.getHeight()/2, B_18);

							canvas.drawBitmap(c02, tmp_posX[view_val/4], posY_t1[view_val/4]-c02.getHeight()/2, pnt);
							canvas.drawText(Long.toString(tmp_origin[tmp_count-1-view_val/4]/tmp_dig), tmp_posX[view_val/4]+17*c_height, posY_t1[view_val/4]+7, B_18);
						}else{
							canvas.drawBitmap(c01, tmp_posX[view_val/4], posY_t1[view_val/4]-c01.getHeight(), pnt);
							canvas.drawText(Long.toString(tmp_origin[tmp_count-1-view_val/4]/tmp_dig), tmp_posX[view_val/4]+7*c_height, posY_t1[view_val/4]-c01.getHeight()/2, B_18);

							canvas.drawBitmap(c02, tmp_posX[view_val/4], posY[view_val/4]-c02.getHeight()/2, pnt);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin[tmp_count-1-view_val/4]*100.0f)), tmp_posX[view_val/4]+17*c_height, posY[view_val/4]+7, B_18);
						}
					}else if(view_val>=15 && view_val < duration){
						if(posY[view_val/4]<posY_t1[view_val/4]){
							canvas.drawBitmap(ci01, tmp_posX[view_val/4]-ci01.getWidth(), posY[view_val/4]-c01.getHeight(), pnt);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin[tmp_count-1-view_val/4]*100.0f)), tmp_posX[view_val/4]-ci01.getWidth()+5, posY[view_val/4]-ci01.getHeight()/2, B_18);

							canvas.drawBitmap(ci02, tmp_posX[view_val/4]-ci02.getWidth(), posY_t1[view_val/4]-ci02.getHeight()/2, pnt);
							canvas.drawText(Long.toString(tmp_origin[tmp_count-1-view_val/4]/tmp_dig), tmp_posX[view_val/4]-ci02.getWidth()+5, posY_t1[view_val/4]+7, B_18);
						}else{
							canvas.drawBitmap(ci01, tmp_posX[view_val/4]-ci01.getWidth(), posY_t1[view_val/4]-ci01.getHeight(), pnt);
							canvas.drawText(Long.toString(tmp_origin[tmp_count-1-view_val/4]/tmp_dig), tmp_posX[view_val/4]-ci01.getWidth()+5, posY_t1[view_val/4]-ci01.getHeight()/2, B_18);

							canvas.drawBitmap(ci02, tmp_posX[view_val/4]-ci02.getWidth(), posY[view_val/4]-ci02.getHeight()/2, pnt);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin[tmp_count-1-view_val/4]*100.0f)), tmp_posX[view_val/4]-ci02.getWidth()+5, posY[view_val/4]+7, B_18);
						}
					}
				}else if(GV.getSRV() == 2){
//////////////////////////////////////////////view 3 begin
					float[] f_sort = new float[duration];
			    	float[] f_origin = new float[duration];
					float[] f_sort2 = new float[duration];
			    	float[] f_origin2 = new float[duration];
			    	long[] m14_tmp = new long[duration];
			    	long[] m34_tmp = new long[duration];
			    	long[] m35_tmp = new long[duration];
			    	int[] vs_tmp = new int[duration];
					
					canvas.drawText("배당성향 & 시가배당률", width*0.2675f, title_position, W_24);

					if(opt_dur == 0){
						for(int dx = 0; dx< (duration+1)*12; dx++){
							if(GV.getStockD().v_stock[dx] == -1 && dx > 0){
								tmp_sort_sv[dx] = tmp_sort_sv[dx-1]; 
								tmp_origin_sv[dx] = GV.getStockD().v_stock[dx];
							}else
								tmp_sort_sv[dx] = tmp_origin_sv[dx] = GV.getStockD().v_stock[dx];
						}
					}else if(opt_dur == 1){
						for(int dx = 0; dx< (duration+1)*12; dx++){
							if(GV.getStockD().v_stock[dx+240] == -1 && dx > 0){
								tmp_sort_sv[dx] = tmp_sort_sv[dx-1]; 
								tmp_origin_sv[dx] = GV.getStockD().v_stock[dx+240];
							}else
								tmp_sort_sv[dx] = tmp_origin_sv[dx] = GV.getStockD().v_stock[dx+240];
						}
					}else if(opt_dur == 2){
						for(int dx = 0; dx< (duration+1)*12; dx++){
							if(GV.getStockD().v_stock[dx+336] == -1 && dx > 0){
								tmp_sort_sv[dx] = tmp_sort_sv[dx-1]; 
								tmp_origin_sv[dx] = GV.getStockD().v_stock[dx+336];
							}else
								tmp_sort_sv[dx] = tmp_origin_sv[dx] = GV.getStockD().v_stock[dx+336];
						}
					}
					
					int tmp_count = 0;
					for(int dx = 0; dx< duration; dx++){
						if(GV.getStockD().m_quarter[dx]%4 == 0){
							m14_tmp[tmp_count] = GV.getStockD().m_m14[dx];
							m34_tmp[tmp_count] = GV.getStockD().m_m34[dx];
							m35_tmp[tmp_count] = GV.getStockD().m_m35[dx];
							vs_tmp[tmp_count] = tmp_origin_sv[(duration - dx)*12];
							tmp_count++;
						}
					}
					
					
					for(int dx = 0; dx< tmp_count; dx++){
						if(m14_tmp[dx] == 0)
							f_sort[dx] = f_origin[dx] = 0;
						else
							f_sort[dx]=f_origin[dx] = (float)(m34_tmp[dx])/(float)(m14_tmp[dx]);
						
						if(m35_tmp[dx] == 0)
							f_sort2[dx] = f_origin2[dx] = 0;
						else
							f_sort2[dx]=f_origin2[dx] = (float)(m34_tmp[dx])/(float)(m35_tmp[dx]*vs_tmp[dx]);
					}
					canvas.drawLine(b_x, c_y - t_i, b_x+l_x, c_y - t_i, pnt4);
					canvas.drawCircle(c_x, c_y - t_i, r_c, pnt4);
					canvas.drawCircle(c_x, c_y - t_i, r_c -1, W_24);
//					canvas.drawText("배당성향", c_x + 10, c_y - t_i + 10, B_24);
					
					canvas.drawLine(b_x, c_y, b_x+l_x, c_y, pnt3);
					canvas.drawCircle(c_x, c_y, r_c, pnt3);
					canvas.drawCircle(c_x, c_y, r_c -1, W_24);
//					canvas.drawText("시가배당률", c_x + 10, c_y + 10, B_24);
					
					// 주가		 
					sorter.sort(tmp_sort_sv, (duration+1)*12);
					
					tmp_max = tmp_sort_sv[(duration+1)*12-1];
					tmp_min = tmp_sort_sv[0];
					
					for(int dy = 0; dy<(duration+1)*12 ; dy++){
						posY_sv[dy] = height*(float)0.75 -(float)(tmp_origin_sv[dy]-tmp_min)/(float)(tmp_max-tmp_min)*height*(float)0.5;
					}
					 
					pnt.setStrokeWidth(1);
					for(int dr = 0; dr < (duration+1)*12-1; dr++){
						if(tmp_origin_sv[dr+1] != -1 && posX_sv[dr] > width*0.067f)
							canvas.drawLine(posX_sv[dr], posY_sv[dr], posX_sv[dr+1], posY_sv[dr+1], pnt);
					}
//*// 주가 변동 추세					
					for(int dy = dur_s-1; dy<(duration+1)*12 ; dy++){
						f_stmp = 0;
						for(int durt=0; durt<dur_s; durt++){
							f_stmp = f_stmp + posY_sv[dy-durt];  
						}
						f_sshort[dy]=f_stmp/dur_s;
					}
					
					for(int dy = dur_l-1; dy<(duration+1)*12 ; dy++){
						f_stmp = 0;
						for(int durt=0; durt<dur_l; durt++){
							f_stmp = f_stmp + posY_sv[dy-durt];  
						}
						f_slong[dy]=f_stmp/dur_l;
					}
					
					if(macd==1){
						for(int dr = dur_s-1; dr < (duration+1)*12-1; dr++){
							if(tmp_origin_sv[dr+1] != -1 && posX_sv[dr] > width*0.067f)
								canvas.drawLine(posX_sv[dr], f_sshort[dr], posX_sv[dr+1], f_sshort[dr+1], stock_C1);
						}
						for(int dr = dur_l-1; dr < (duration+1)*12-1; dr++){
							if(tmp_origin_sv[dr+1] != -1 && posX_sv[dr] > width*0.067f)
								canvas.drawLine(posX_sv[dr], f_slong[dr], posX_sv[dr+1], f_slong[dr+1], stock_C2);
						}
					}
//*/// 주가 변동 추세
					// 배당성향
					//*   		 
					sorter.sort(f_sort, tmp_count);
					
					f_max = f_sort[tmp_count-1];
					f_min = f_sort[0];
					
					for(int dy = 0; dy<tmp_count; dy++){
						if(f_max - f_min == 0)
							posY_t1[dy] = posY[dy] = height*(float)0.75;
						else
							posY_t1[dy] = posY[dy] = height*(float)0.75 -(float)(f_origin[tmp_count - dy -1]-f_min)/(float)(f_max-f_min)*height*(float)0.5;
					}
//*					
					int tmp_count_posX=0;
					for(int dx = 0; dx< duration; dx++)
						if(GV.getStockD().m_quarter[duration-dx-1]%4 == 0){
							tmp_posX[tmp_count_posX] = posX[dx];
							tmp_count_posX++;
						}
//*/			
//*
					for(int dr = 0; dr < tmp_count-1; dr++)
						if( tmp_posX[dr] > width*0.067f)
							canvas.drawLine(tmp_posX[dr], posY[dr], tmp_posX[dr+1], posY[dr+1], pnt4);
										
					if(dis_ori == 1 || dis_ori == 3){
						canvas.drawText("배당성향 (%) ←", c_x + 10, c_y - t_i + 10, B_24);
						
						tmp_D = null;
						tmp_D = mD.Fdigit(f_max*100f, f_min*100f);
						uWon2 = tmp_D.uWon;
						digit_count = tmp_D.dCount;
						v_max1 = tmp_D.max;
						v_min1 = tmp_D.min;
						v1 = tmp_D.v1;
						v2 = tmp_D.v2;
						v3 = tmp_D.v3;
						v4 = tmp_D.v4;
						
						canvas.drawText(v_max1, 5, height*0.26f, W_18);
						canvas.drawText(v4, 5, height*0.35f, W_18);
						canvas.drawText(v3, 5, height*0.45f, W_18);
						canvas.drawText(v2, 5, height*0.56f, W_18);
						canvas.drawText(v1, 5, height*0.66f, W_18);
						canvas.drawText(v_min1, 5, height*0.77f, W_18);
					
						for(int dr = 0; dr < tmp_count; dr++)
						if( tmp_posX[dr] > width*0.067f){
							canvas.drawCircle(tmp_posX[dr], posY[dr], 4, pnt4);
							canvas.drawCircle(tmp_posX[dr], posY[dr], 3, W_24);
						}
					}
					
					// 시가배당률
					sorter.sort(f_sort2, tmp_count);
					
					f_max = f_sort2[tmp_count-1];
					f_min = f_sort2[0];
					
					for(int dy = 0; dy<tmp_count ; dy++){
						if(f_max-f_min == 0)
							posY[dy] = height*(float)0.75;
						else
							posY[dy] = height*(float)0.75 -(float)(f_origin2[tmp_count - dy -1]-f_min)/(float)(f_max-f_min)*height*(float)0.5;
					}
					//*/
					for(int dr = 0; dr < tmp_count-1; dr++)
						if( tmp_posX[dr] > width*0.067f)
							canvas.drawLine(tmp_posX[dr], posY[dr], tmp_posX[dr+1], posY[dr+1], pnt3);
										
					if(dis_ori == 1 || dis_ori == 3){
						canvas.drawText("시가배당률 (%) →", c_x + 10, c_y + 10, B_24);

						tmp_D = null;
						tmp_D = mD.Fdigit(f_max*100f, f_min*100f);
						uWon2 = tmp_D.uWon;
						digit_count = tmp_D.dCount;
						v_max1 = tmp_D.max;
						v_min1 = tmp_D.min;
						v1 = tmp_D.v1;
						v2 = tmp_D.v2;
						v3 = tmp_D.v3;
						v4 = tmp_D.v4;
						
						canvas.drawText(v_max1, width-2 - v_max1.length()*11*c_height, height*0.26f, W_18);
						canvas.drawText(v4, width-2 - v_max1.length()*11*c_height, height*0.35f, W_18);
						canvas.drawText(v3, width-2 - v_max1.length()*11*c_height, height*0.45f, W_18);
						canvas.drawText(v2, width-2- v_max1.length()*11*c_height, height*0.56f, W_18);
						canvas.drawText(v1, width-2- v_max1.length()*11*c_height, height*0.66f, W_18);
						canvas.drawText(v_min1, width-2 - v_max1.length()*11*c_height, height*0.77f, W_18);
					
						for(int dr = 0; dr < tmp_count; dr++)
						if( tmp_posX[dr] > width*0.067f){
							canvas.drawCircle(tmp_posX[dr], posY[dr], 4, pnt3);
							canvas.drawCircle(tmp_posX[dr], posY[dr], 3, W_24);
						}
					}

					if(view_val >= 0 && view_val < 15){
						if(posY[view_val/4]<posY_t1[view_val/4]){
							canvas.drawBitmap(c01, tmp_posX[view_val/4], posY[view_val/4]-c01.getHeight(), pnt);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin2[tmp_count-1-view_val/4]*100.0f)), tmp_posX[view_val/4]+7*c_height, posY[view_val/4]-c01.getHeight()/2, B_18);

							canvas.drawBitmap(c02, tmp_posX[view_val/4], posY_t1[view_val/4]-c02.getHeight()/2, pnt);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin[tmp_count-1-view_val/4]*100.0f)), tmp_posX[view_val/4]+17*c_height, posY_t1[view_val/4]+7, B_18);
						}else{
							canvas.drawBitmap(c01, tmp_posX[view_val/4], posY_t1[view_val/4]-c01.getHeight(), pnt);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin[tmp_count-1-view_val/4]*100.0f)), tmp_posX[view_val/4]+7*c_height, posY_t1[view_val/4]-c01.getHeight()/2, B_18);

							canvas.drawBitmap(c02, tmp_posX[view_val/4], posY[view_val/4]-c02.getHeight()/2, pnt);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin2[tmp_count-1-view_val/4]*100.0f)), tmp_posX[view_val/4]+17*c_height, posY[view_val/4]+7, B_18);
						}
					}else if(view_val>=15 && view_val < duration){
						if(posY[view_val/4]<posY_t1[view_val/4]){
							canvas.drawBitmap(ci01, tmp_posX[view_val/4]-ci01.getWidth(), posY[view_val/4]-c01.getHeight(), pnt);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin2[tmp_count-1-view_val/4]*100.0f)), tmp_posX[view_val/4]-ci01.getWidth()+5, posY[view_val/4]-ci01.getHeight()/2, B_18);

							canvas.drawBitmap(ci02, tmp_posX[view_val/4]-ci02.getWidth(), posY_t1[view_val/4]-ci02.getHeight()/2, pnt);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin[tmp_count-1-view_val/4]*100.0f)), tmp_posX[view_val/4]-ci02.getWidth()+5, posY_t1[view_val/4]+7, B_18);
						}else{
							canvas.drawBitmap(ci01, tmp_posX[view_val/4]-ci01.getWidth(), posY_t1[view_val/4]-ci01.getHeight(), pnt);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin[tmp_count-1-view_val/4]*100.0f)), tmp_posX[view_val/4]-ci01.getWidth()+5, posY_t1[view_val/4]-ci01.getHeight()/2, B_18);

							canvas.drawBitmap(ci02, tmp_posX[view_val/4]-ci02.getWidth(), posY[view_val/4]-ci02.getHeight()/2, pnt);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin2[tmp_count-1-view_val/4]*100.0f)), tmp_posX[view_val/4]-ci02.getWidth()+5, posY[view_val/4]+7, B_18);
						}
					}
				}
				canvas.drawBitmap(map006, width*0.075f, height*0.24f, pnt);
//				canvas.drawText("차트 설명 보기", width - 200, height-20, pnt2);
			}
    		Bitmap Icon_m = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.d);
    		if(sub_menu == 1){
    			canvas.drawRect(width*0.25f, title_position-height*0.06f, width-width*0.025f, title_position+height*0.5625f, _Drawable2.getPaint());
    			canvas.drawText("유보율", width*0.2675f, title_position, W_24);
    			canvas.drawText("배당금  & 시가배당률", width*0.2675f, title_position+height*0.104f, W_24);
    			canvas.drawText("배당성향 & 시가배당률", width*0.2675f, title_position+height*0.208f, W_24);
    			canvas.drawBitmap(Icon_m, width-width*0.025f-Icon_m.getWidth(), title_position+height*0.5625f-Icon_m.getHeight(), pnt);
    		}
    		if(sub_menu == 2){
    			canvas.drawRect(width*0.025f, title_position+height*0.0271f, width*0.2375f, title_position+height*0.5625f, B_24);
    			canvas.drawRect(width*0.025f+1, title_position+height*0.0271f, width*0.2375f-1, title_position+height*0.5625f-1, W_24);
//    			_Drawable2.getPaint().setColor(0xff0071d1);
//    			canvas.drawText("수익성", 34, title_position+50, _Drawable2.getPaint());
    			_Drawable2.getPaint().setColor(0xff0071d1);
    			_Drawable2.getPaint().setTextSize(24*c_height);
    			canvas.drawText("수익성", width*0.0425f, title_position+height*0.104f, _Drawable2.getPaint());
    			_Drawable2.getPaint().setColor(0xff079200);
    			canvas.drawText("안전성", width*0.0425f, title_position+height*0.208f, _Drawable2.getPaint());
    			_Drawable2.getPaint().setColor(0xffA90000);
    			canvas.drawText("성장성", width*0.0425f, title_position+height*0.312f, _Drawable2.getPaint());
    			_Drawable2.getPaint().setColor(0xff5B00A9);
    			canvas.drawText("효율성", width*0.0425f, title_position+height*0.416f, _Drawable2.getPaint());
    			_Drawable2.getPaint().setColor(0xffA4A4A4);
    			canvas.drawText("가치평가", width*0.0425f, title_position+height*0.52f, _Drawable2.getPaint());
//    			canvas.drawBitmap(Icon_m, width-20-Icon_m.getWidth(), title_position+270-Icon_m.getHeight(), pnt);
    		}
    	}
         
    	public void animate(final testView tmp) {
    		tmp.thr = 1;
     		
    		new Thread(new Runnable() {
    			
    			public void run() {
    				while(tmp.thr == 1){ 
    					tmp.postInvalidate();
    					try {
    						Thread.sleep(50);
    					} catch (InterruptedException e) {
    						e.printStackTrace();
    					}
    				}
    			}
    		}).start();
    	}
    }
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(event.getAction() == KeyEvent.ACTION_DOWN){
			if(keyCode == KeyEvent.KEYCODE_BACK){
				GV.setSR(0);
				GV.setSRM(0);
				GV.setSRV(0);
				sub_menu = 0;
			    fix_dis = 0;
				startActivity(Cmenu1);
				finish();
			}
		}
		return false;
	}
}
