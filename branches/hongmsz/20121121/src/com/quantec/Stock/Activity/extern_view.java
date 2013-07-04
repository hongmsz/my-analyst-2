package com.quantec.Stock.Activity;

import com.quantec.Stock.MyApp;
import com.quantec.Stock.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

public class extern_view extends View {
	MyApp GV;
	
	int thr = 0;
		
///////////////////////////////////////////////////////////
	String mTitle, sub_title;
	
	int title_position = 80;
	int digit=5;
	int BEGIN_YEAR;
	
	int duration;
    int[] qt_tmp = new int[43];
    int tmp_y, tmp_q;
    
    int sub_menu = 0;
    int opt_dur=1;
    int Y_count;
    int view_val = -1;
    int digit_count=0; 
    long tmp_dig=0l, tmp_dig2=0l;
    float tmp_dig_f = 1f, tmp_dig_f2=1f;
    
    //////////////////////////////////////////////
    ShapeDrawable _Drawable = new ShapeDrawable(new OvalShape());
	ShapeDrawable _Drawable2 = new ShapeDrawable(new OvalShape());
	
	float[] posX;
	float[] posY;
	float[] posY_t1;
	float[] posY_t2;
	float[] posX_sv;
	float[] posY_sv;
	
	float b_x;
	float c_y;
	float l_x;
	float c_x;
	float r_c = 4;
	float t_i;

	long tmp_max, tmp_min;
	float f_max, f_min;
	int width, height;
	float c_width, c_height;
	
	long[] tmp_sort;
	long[] tmp_sort2;
	long[] tmp_sort3;
	long[] tmp_origin;
	long[] tmp_origin2;
	long[] tmp_origin3;
	
	int[] tmp_sort_sv;
	int[] tmp_origin_sv;
	
	Paint pnt = new Paint();
	Paint W_24 = new Paint();
	Paint pnt3 = new Paint();
	Paint pnt4 = new Paint();
	Paint pnt5 = new Paint();
	Paint pnt6 = new Paint();
	Paint B_18 = new Paint();
	Paint W_18 = new Paint();
	Paint B_24 = new Paint();
	
	Paint stock_C1 = new Paint();
	Paint stock_C2 = new Paint();
	

	float[] f_sort;
	float[] f_origin;
	float[] f_sort2;
	float[] f_origin2;
	
	float[] f_slong;				// 주가 변동 추세
	float[] f_sshort;				// 주가 변동 추세
	
	float f_stmp;					// 주가 변동 추세
	int macd=0;					// 주가 변동 추세
	int dur2;
	int dur_s = 5, dur_l=20;

	digitD tmp_D = new digitD();	
	
//////////////////////////////////////////
	
	Bitmap map001 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.h_bg);
	Bitmap map003 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.seach_l);
	Bitmap map006 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.help);
	Bitmap map004 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.off_03);
	Bitmap map007 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.off_05);
	Bitmap map008 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.off_10);
	Bitmap map009 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.on_03);
	Bitmap map010 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.on_05);
	Bitmap map011 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.on_10);
	Bitmap map012, map013, map014;
	
	Bitmap map015 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.search_ss); // 주가 변동 추세
	

	Bitmap c01 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cloud_up);
	Bitmap c02 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cloud_mid);
	Bitmap c03 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cloud_down);
	
	Bitmap ci01 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cloud_i_up);
	Bitmap ci02 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cloud_i_mid);
	Bitmap ci03 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cloud_i_down);
	
	Bitmap Icon_m = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.p);

	public extern_view(Context context)
	{
		super(context);
	}
	public extern_view(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void drawText_left(Canvas canvas, digitD tmp, Paint Cr){
		canvas.drawText(tmp.max, 5, height*0.26f, Cr);
		canvas.drawText(tmp.v4, 5, height*0.35f, Cr);
		canvas.drawText(tmp.v3, 5, height*0.45f, Cr);
		canvas.drawText(tmp.v2, 5, height*0.56f, Cr);
		canvas.drawText(tmp.v1, 5, height*0.66f, Cr);
		canvas.drawText(tmp.min, 5, height*0.77f, Cr);
	}
	
	public void drawText_right(Canvas canvas, digitD tmp, Paint Cr){
		canvas.drawText(tmp.max, width-2 - tmp.max.length()*11*c_height, height*0.26f, Cr);
		canvas.drawText(tmp.v4, width-2 - tmp.max.length()*11*c_height, height*0.35f, Cr);
		canvas.drawText(tmp.v3, width-2 - tmp.max.length()*11*c_height, height*0.45f, Cr);
		canvas.drawText(tmp.v2, width-2- tmp.max.length()*11*c_height, height*0.56f, Cr);
		canvas.drawText(tmp.v1, width-2- tmp.max.length()*11*c_height, height*0.66f, Cr);
		canvas.drawText(tmp.min, width-2 - tmp.max.length()*11*c_height, height*0.77f, Cr);
	}
	
	public void drawText_top(Canvas canvas, String tmp, Paint Cr){
		canvas.drawText(tmp, c_x + 10, c_y - (t_i*2) + 10, Cr);
	}
	
	public void drawText_mid(Canvas canvas, String tmp, Paint Cr){
		canvas.drawText(tmp, c_x + 10, c_y - t_i + 10, Cr);
	}
	
	public void drawText_btm(Canvas canvas, String tmp, Paint Cr){
		canvas.drawText(tmp, c_x + 10, c_y + 10, Cr);
	}
	
	public String convert_n2s(float[] tmp, int dur, float dig){
		String tmp_s;
		tmp_s = String.format("%.2f", Float.valueOf(tmp[dur-1-view_val]/dig));
		return tmp_s;
	}
	
	public String convert_n2s(long[] tmp,  int dur, long dig){
		String tmp_s;
		tmp_s = Long.toString(tmp[dur-1-view_val]/dig);
		return tmp_s;
	}
	
	public String convert_n2s(float[] tmp, int dur){
		String tmp_s;
		tmp_s = String.format("%.2f", Float.valueOf(tmp[dur-1-view_val]));
		return tmp_s;
	}
	
	public String convert_n2s(long[] tmp,  int dur){
		String tmp_s;
		tmp_s = Long.toString(tmp[dur-1-view_val]);
		return tmp_s;
	}
	
	public void drawvalue_1(Canvas canvas, String tmp_s,  int dur, float[] x, float[] y, Paint Cr){
		if(view_val >= 0 && view_val < 15){
			canvas.drawBitmap(c01, x[view_val], y[view_val]-c01.getHeight(), pnt);
			canvas.drawText(tmp_s, x[view_val]+7*c_height, y[view_val]-c01.getHeight()/2, Cr);
		}else if(view_val>=15 && view_val < dur){
			canvas.drawBitmap(ci01, x[view_val]-ci01.getWidth(), y[view_val]-ci01.getHeight(), pnt);
			canvas.drawText(tmp_s, x[view_val]-ci01.getWidth()+7*c_height, y[view_val]-c01.getHeight()/2, Cr);
		}
	}
	
	public void drawvalue_2(Canvas canvas, String tmp_s, String tmp_s2, int dur, float[] x, float[] y, float[] y2, Paint Cr){
		if(view_val >= 0 && view_val < 15){
			if(y[view_val]>y2[view_val]){
				canvas.drawBitmap(c01, x[view_val], y2[view_val]-c01.getHeight(), pnt);
				canvas.drawText(tmp_s, x[view_val]+7*c_height, y2[view_val]-c01.getHeight()/2, Cr);
				
				canvas.drawBitmap(c02, x[view_val], y[view_val]-c02.getHeight()/2, pnt);
				canvas.drawText(tmp_s2, x[view_val]+17*c_height, y[view_val]+7, Cr);
			}else{
				canvas.drawBitmap(c01, x[view_val], y[view_val]-c01.getHeight(), pnt);
				canvas.drawText(tmp_s2, x[view_val]+7*c_height, y[view_val]-c01.getHeight()/2, Cr);
				
				canvas.drawBitmap(c02, x[view_val], y2[view_val]-c02.getHeight()/2, pnt);
				canvas.drawText(tmp_s, x[view_val]+17*c_height, y2[view_val]+7, Cr);
			}
		}else if(view_val>=15 && view_val < dur){
			if(y[view_val]>y2[view_val]){
				canvas.drawBitmap(ci01, x[view_val]-ci01.getWidth(), y2[view_val]-ci01.getHeight(), pnt);
				canvas.drawText(tmp_s, x[view_val]-ci01.getWidth()+5, y2[view_val]-ci01.getHeight()/2, Cr);
				
				canvas.drawBitmap(ci02, x[view_val]-ci02.getWidth(), y[view_val]-ci02.getHeight()/2, pnt);
				canvas.drawText(tmp_s2, x[view_val]-ci02.getWidth()+5, y[view_val]+7, Cr);
			}else{
				canvas.drawBitmap(ci01, x[view_val]-ci01.getWidth(), y[view_val]-ci01.getHeight(), pnt);
				canvas.drawText(tmp_s2, x[view_val]-ci01.getWidth()+5, y[view_val]-ci01.getHeight()/2, Cr);
				
				canvas.drawBitmap(ci02, x[view_val]-ci02.getWidth(), y2[view_val]-ci02.getHeight()/2, pnt);
				canvas.drawText(tmp_s, x[view_val]-ci02.getWidth()+5, y2[view_val]+7, Cr);
			}
		}
	}
	
	public void drawvalue_3(Canvas canvas, String tmp_s, String tmp_s2, String tmp_s3, int dur, float[] x, float[] y, float[] y2, float[] y3, Paint Cr){
		if(view_val >= 0 && view_val < 15){
			if(y[view_val]>y2[view_val]){
				if(y2[view_val]>y3[view_val]){
					canvas.drawBitmap(c01, x[view_val], y3[view_val]-c01.getHeight(), pnt);
					canvas.drawText(tmp_s, x[view_val]+7*c_height, y3[view_val]-c01.getHeight()/2, Cr);
					
					canvas.drawBitmap(c02, x[view_val], y2[view_val]-c02.getHeight()/2, pnt);
					canvas.drawText(tmp_s2, x[view_val]+17*c_height, y2[view_val]+7, Cr);
					
					canvas.drawBitmap(c03, x[view_val], y[view_val], pnt);
					canvas.drawText(tmp_s3, x[view_val]+7*c_height, y[view_val]+c03.getHeight()-3, Cr);
				}else{
					if(y[view_val]>y3[view_val]){
						canvas.drawBitmap(c01, x[view_val], y2[view_val]-c01.getHeight(), pnt);
						canvas.drawText(tmp_s2, x[view_val]+7*c_height, y2[view_val]-c01.getHeight()/2, Cr);
						
						canvas.drawBitmap(c02, x[view_val], y3[view_val]-c02.getHeight()/2, pnt);
						canvas.drawText(tmp_s, x[view_val]+17*c_height, y3[view_val]+7, Cr);
						
						canvas.drawBitmap(c03, x[view_val], y[view_val], pnt);
						canvas.drawText(tmp_s3, x[view_val]+7*c_height, y[view_val]+c03.getHeight()-3, Cr);
					}else{
						canvas.drawBitmap(c01, x[view_val], y2[view_val]-c01.getHeight(), pnt);
						canvas.drawText(tmp_s2, x[view_val]+7*c_height, y2[view_val]-c01.getHeight()/2, Cr);
						
						canvas.drawBitmap(c02, x[view_val], y[view_val]-c02.getHeight()/2, pnt);
						canvas.drawText(tmp_s3, x[view_val]+17*c_height, y[view_val]+7, Cr);
						
						canvas.drawBitmap(c03, x[view_val], y3[view_val], pnt);
						canvas.drawText(tmp_s, x[view_val]+7*c_height, y3[view_val]+c03.getHeight()-3, Cr);
					}
				}
			}else{
				if(y[view_val]>y3[view_val]){
					canvas.drawBitmap(c01, x[view_val], y3[view_val]-c01.getHeight(), pnt);
					canvas.drawText(tmp_s, x[view_val]+7*c_height, y3[view_val]-c01.getHeight()/2, Cr);
					
					canvas.drawBitmap(c02, x[view_val], y[view_val]-c02.getHeight()/2, pnt);
					canvas.drawText(tmp_s3, x[view_val]+17*c_height, y[view_val]+7, Cr);
					
					canvas.drawBitmap(c03, x[view_val], y2[view_val], pnt);
					canvas.drawText(tmp_s2, x[view_val]+7*c_height, y2[view_val]+c03.getHeight()-3, Cr);
				}else{
					if(y2[view_val]>y3[view_val]){
						canvas.drawBitmap(c01, x[view_val], y[view_val]-c01.getHeight(), pnt);
						canvas.drawText(tmp_s3, x[view_val]+7*c_height, y[view_val]-c01.getHeight()/2, Cr);
						
						canvas.drawBitmap(c02, x[view_val], y3[view_val]-c02.getHeight()/2, pnt);
						canvas.drawText(tmp_s, x[view_val]+17*c_height, y3[view_val]+7, Cr);
						
						canvas.drawBitmap(c03, x[view_val], y2[view_val], pnt);
						canvas.drawText(tmp_s2, x[view_val]+7*c_height, y2[view_val]+c03.getHeight()-3, Cr);
					}else{
						canvas.drawBitmap(c01, x[view_val], y[view_val]-c01.getHeight(), pnt);
						canvas.drawText(tmp_s3, x[view_val]+7*c_height, y[view_val]-c01.getHeight()/2, Cr);
						
						canvas.drawBitmap(c02, x[view_val], y2[view_val]-c02.getHeight()/2, pnt);
						canvas.drawText(tmp_s2, x[view_val]+17*c_height, y2[view_val]+7, Cr);
						
						canvas.drawBitmap(c03, x[view_val], y3[view_val], pnt);
						canvas.drawText(tmp_s, x[view_val]+7*c_height, y3[view_val]+c03.getHeight()-3, Cr);
					}
				}
			}
		}else if(view_val>=15 && view_val < dur){
			if(y[view_val]>y2[view_val]){
				if(y2[view_val]>y3[view_val]){
					canvas.drawBitmap(ci01, x[view_val]-ci01.getWidth(), y3[view_val]-ci01.getHeight(), pnt);
					canvas.drawText(tmp_s, x[view_val]-ci01.getWidth()+5, y3[view_val]-ci01.getHeight()/2, Cr);
					
					canvas.drawBitmap(ci02, x[view_val]-ci02.getWidth(), y2[view_val]-ci02.getHeight()/2, pnt);
					canvas.drawText(tmp_s2, x[view_val]-ci02.getWidth()+5, y2[view_val]+7, Cr);
					
					canvas.drawBitmap(ci03, x[view_val]-ci03.getWidth(), y[view_val], pnt);
					canvas.drawText(tmp_s3, x[view_val]-ci03.getWidth()+5, y[view_val]+ci03.getHeight()-3, Cr);
				}else{
					if(y[view_val]>y3[view_val]){
						canvas.drawBitmap(ci01, x[view_val]-ci01.getWidth(), y2[view_val]-ci01.getHeight(), pnt);
						canvas.drawText(tmp_s2, x[view_val]-ci01.getWidth()+5, y2[view_val]-ci01.getHeight()/2, Cr);
						
						canvas.drawBitmap(ci02, x[view_val]-ci02.getWidth(), y3[view_val]-ci02.getHeight()/2, pnt);
						canvas.drawText(tmp_s, x[view_val]-ci02.getWidth()+5, y3[view_val]+7, Cr);
						
						canvas.drawBitmap(ci03, x[view_val]-ci03.getWidth(), y[view_val], pnt);
						canvas.drawText(tmp_s3, x[view_val]-ci03.getWidth()+5, y[view_val]+ci03.getHeight()-3, Cr);
					}else{
						canvas.drawBitmap(ci01, x[view_val]-ci01.getWidth(), y2[view_val]-ci01.getHeight(), pnt);
						canvas.drawText(tmp_s2, x[view_val]-ci01.getWidth()+5, y2[view_val]-ci01.getHeight()/2, Cr);
						
						canvas.drawBitmap(ci02, x[view_val]-ci02.getWidth(), y[view_val]-ci02.getHeight()/2, pnt);
						canvas.drawText(tmp_s3, x[view_val]-ci02.getWidth()+5, y[view_val]+7, Cr);
						
						canvas.drawBitmap(ci03, x[view_val]-ci03.getWidth(), y3[view_val], pnt);
						canvas.drawText(tmp_s, x[view_val]-ci03.getWidth()+5, y3[view_val]+ci03.getHeight()-3, Cr);
					}
				}
			}else{
				if(y[view_val]>y3[view_val]){
					canvas.drawBitmap(ci01, x[view_val]-ci01.getWidth(), y3[view_val]-ci01.getHeight(), pnt);
					canvas.drawText(tmp_s, x[view_val]-ci01.getWidth()+5, y3[view_val]-ci01.getHeight()/2, Cr);
					
					canvas.drawBitmap(ci02, x[view_val]-ci02.getWidth(), y[view_val]-ci02.getHeight()/2, pnt);
					canvas.drawText(tmp_s3, x[view_val]-ci02.getWidth()+5, y[view_val]+7, Cr);
					
					canvas.drawBitmap(ci03, x[view_val]-ci03.getWidth(), y2[view_val], pnt);
					canvas.drawText(tmp_s2, x[view_val]-ci03.getWidth()+5, y2[view_val]+ci03.getHeight()-3, Cr);
				}else{
					if(y2[view_val]>y3[view_val]){
						canvas.drawBitmap(ci01, x[view_val]-ci01.getWidth(), y[view_val]-ci01.getHeight(), pnt);
						canvas.drawText(tmp_s3, x[view_val]-ci01.getWidth()+5, y[view_val]-ci01.getHeight()/2, Cr);
						
						canvas.drawBitmap(ci02, x[view_val]-ci02.getWidth(), y3[view_val]-ci02.getHeight()/2, pnt);
						canvas.drawText(tmp_s, x[view_val]-ci02.getWidth()+5, y3[view_val]+7, Cr);
						
						canvas.drawBitmap(ci03, x[view_val]-ci03.getWidth(), y2[view_val], pnt);
						canvas.drawText(tmp_s2, x[view_val]-ci03.getWidth()+5, y2[view_val]+ci03.getHeight()-3, Cr);
					}else{
						canvas.drawBitmap(ci01, x[view_val]-ci01.getWidth(), y[view_val]-ci01.getHeight(), pnt);
						canvas.drawText(tmp_s3, x[view_val]-ci01.getWidth()+5, y[view_val]-ci01.getHeight()/2, Cr);
						
						canvas.drawBitmap(ci02, x[view_val]-ci02.getWidth(), y2[view_val]-ci02.getHeight()/2, pnt);
						canvas.drawText(tmp_s2, x[view_val]-ci02.getWidth()+5, y2[view_val]+7, Cr);
						
						canvas.drawBitmap(ci03, x[view_val]-ci03.getWidth(), y3[view_val], pnt);
						canvas.drawText(tmp_s, x[view_val]-ci03.getWidth()+5, y3[view_val]+ci03.getHeight()-3, Cr);
					}
				}
			}
		}
	}
	
	public void onDraw(Canvas canvas){
		
		String uPer= " (%)";
		
		duration = GV.getDuration();
		width = GV.getDisplay().getWidth();
		height = GV.getDisplay().getHeight();
		
		_Drawable.getPaint().setColor(0xffAAAAAA);
		_Drawable2.getPaint().setColor(0xff0071d1);
		
		QuickSort sorter = new QuickSort();
		makeDigit mD = new makeDigit();

		pnt.setColor(0xffA90000);//red
		pnt.setTextSize(18*c_height);
		pnt.setStrokeWidth(3); 
		W_24.setColor(Color.WHITE);
		W_24.setTextSize(24*c_height);
		pnt3.setColor(0xff0071d1);//blue
		pnt3.setTextSize(18*c_height);
		pnt3.setStrokeWidth(3);	// 선 굵기 조정 
		pnt4.setColor(0xff079200);//green
		pnt4.setTextSize(18*c_height);
		pnt4.setStrokeWidth(3);	// 선 굵기 조정 
		pnt5.setColor(0xffFFC600);//yellow
		pnt5.setTextSize(18*c_height);
		pnt5.setStrokeWidth(3);	// 선 굵기 조정
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
		pnt5.setAntiAlias(true);
		B_18.setAntiAlias(true);
		W_18.setAntiAlias(true);
		B_24.setAntiAlias(true);
		
		Rect src = new Rect(0,0,map001.getWidth(),map001.getHeight());
		Rect dst = new Rect(0,0,MyApp.getDisplay().getWidth(),MyApp.getDisplay().getHeight());
		
		switch(GV.getSRM()){
		case 1:
			map012 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.m1);
			map013 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.sm1);
			map014 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.mm1);
			
			int dis_ori = 1;  
			if(dis_ori == 1){
				

    			if(opt_dur == 0){
    				duration = 40;
    				Y_count = BEGIN_YEAR;
    			}else if(opt_dur==1){
    				duration = 20;
    				Y_count = BEGIN_YEAR+5;
    			}else if(opt_dur==2){
    				duration = 12;
    				Y_count = BEGIN_YEAR+7;
    			}
				
				canvas.drawBitmap(map001, src, dst, pnt);
				canvas.drawBitmap(map012, 20*c_width, title_position-58*c_height, pnt);
				canvas.drawBitmap(map014, 20*c_width, title_position-29*c_height, pnt);
				canvas.drawBitmap(map013, 200*c_width, title_position-29*c_height, pnt);
//				canvas.drawText("[ ", 45, title_position-40, B_24); 
				if(GV.getStockD().m_Kname.length() > 8){
					canvas.drawText(GV.getStockD().m_Kname, 0, 8, 50*c_width, title_position-40*c_height, W_24);
					canvas.drawText("...", width*0.28f, title_position-height*0.08f, W_24);
				}else{
					canvas.drawText(GV.getStockD().m_Kname, 50*c_width, title_position-40*c_height, W_24);
//					canvas.drawText(" - "+mTitle, 54 + (GV.getStockD().m_Kname.length())*20, title_position-40, B_24);
				}
				canvas.drawText(mTitle, 34*c_width, title_position, W_24);
				canvas.drawBitmap(map003, MyApp.getDisplay().getWidth()*0.90f, title_position-50*c_height, pnt);
				canvas.drawBitmap(map015, MyApp.getDisplay().getWidth()*0.80f, title_position-50*c_height, pnt);
				
				for(int dx = 0; dx< (duration+1)*12; dx++)
					posX_sv[dx] = (float)(width-width*0.15f)/(float)((duration+1)*12-1)*(float)dx + width*0.075f;
    			
    			for(int dx = 0; dx< duration; dx++){
					posX[dx] = (float)(width-width*0.15f)/(float)(duration)*dx + width*0.075f  /* */ - (float)(width-width*0.15f)/(float)((duration+1)*12-1)*dur2;
    			}
    				    			
//////////////////////////////////////////////////////
//연도/ 분기 계산	    			
//    			tmp_y = GV.getStockD().m_quarter[0]/100;
//    			tmp_q = GV.getStockD().m_quarter[0]%4;
    			int tmp_dx = 0, tmp_qx;

    			for(int dx = 0; dx< duration; dx++){
    				 if(GV.getStockD().m_quarter[dx] == 1){
    					 tmp_qx = tmp_q - (dx-tmp_dx + 4-tmp_q)%4;
    					 if(tmp_qx < 1)
    						tmp_qx += 4;
    					 
    					qt_tmp[dx] = (tmp_y - (dx-tmp_dx+4-tmp_q)/4)*100 + tmp_qx;
    					
    				 }else{
    					qt_tmp[dx] = GV.getStockD().m_quarter[dx];
    					tmp_y = GV.getStockD().m_quarter[dx]/100;
    		    		tmp_q = GV.getStockD().m_quarter[dx]%4;
    		    		if(tmp_q == 0) tmp_q = 4;
    		    		tmp_dx = dx-1;
    				 }
    			}
    			
//////////////////////////////////////////////////////    			
    			for(int dx = 0; dx< duration; dx++){
    				if(qt_tmp[duration - dx-1]%4 == 1 && posX[dx] > width*0.067f){
						canvas.drawText(Y_count+"", posX[dx], height*0.77f+15*c_height, W_18);
						Y_count++;
						
						canvas.drawLine(posX[dx], height*0.23f, posX[dx], height*(float)0.77, _Drawable.getPaint());
					}
    			}
			}
			
			switch(opt_dur){
    			case 0:
	    			canvas.drawBitmap(map004, GV.getDisplay().getWidth()-190*c_width, GV.getDisplay().getHeight()-90*c_height, pnt);
	    			canvas.drawBitmap(map007, GV.getDisplay().getWidth()-145*c_width, GV.getDisplay().getHeight()-90*c_height, pnt);
	    			canvas.drawBitmap(map011, GV.getDisplay().getWidth()-100*c_width, GV.getDisplay().getHeight()-90*c_height, pnt);
	    			for(int dx = 0; dx< (duration+1)*12; dx++){
						if(GV.getStockD().v_stock[dx] == -1 && dx > 0){
							tmp_sort_sv[dx] = tmp_sort_sv[dx-1]; 
							tmp_origin_sv[dx] = GV.getStockD().v_stock[dx];
						}else
							tmp_sort_sv[dx] = tmp_origin_sv[dx] = GV.getStockD().v_stock[dx];
					}
	    			break;
    			case 1:
	    			canvas.drawBitmap(map004, GV.getDisplay().getWidth()-190*c_width, GV.getDisplay().getHeight()-90*c_height, pnt);
	    			canvas.drawBitmap(map010, GV.getDisplay().getWidth()-145*c_width, GV.getDisplay().getHeight()-90*c_height, pnt);
	    			canvas.drawBitmap(map008, GV.getDisplay().getWidth()-100*c_width, GV.getDisplay().getHeight()-90*c_height, pnt);
	    			for(int dx = 0; dx< (duration+1)*12; dx++){
						if(GV.getStockD().v_stock[dx+240] == -1 && dx > 0){
							tmp_sort_sv[dx] = tmp_sort_sv[dx-1]; 
							tmp_origin_sv[dx] = GV.getStockD().v_stock[dx+240];
						}else
							tmp_sort_sv[dx] = tmp_origin_sv[dx] = GV.getStockD().v_stock[dx+240];
					}
	    			break;
    			case 2:
	    			canvas.drawBitmap(map009, GV.getDisplay().getWidth()-190*c_width, GV.getDisplay().getHeight()-90*c_height, pnt);
	    			canvas.drawBitmap(map007, GV.getDisplay().getWidth()-145*c_width, GV.getDisplay().getHeight()-90*c_height, pnt);
	    			canvas.drawBitmap(map008, GV.getDisplay().getWidth()-100*c_width, GV.getDisplay().getHeight()-90*c_height, pnt);
	    			for(int dx = 0; dx< (duration+1)*12; dx++){
						if(GV.getStockD().v_stock[dx+336] == -1 && dx > 0){
							tmp_sort_sv[dx] = tmp_sort_sv[dx-1]; 
							tmp_origin_sv[dx] = GV.getStockD().v_stock[dx+336];
						}else
							tmp_sort_sv[dx] = tmp_origin_sv[dx] = GV.getStockD().v_stock[dx+336];
					}
	    			break;
			}
			
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

			// 주가 변동 추세					
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
					if(tmp_origin_sv[dr+1] != -1  && posX_sv[dr] > width*0.067f)
						canvas.drawLine(posX_sv[dr], f_sshort[dr], posX_sv[dr+1], f_sshort[dr+1], stock_C1);
				}
				for(int dr = dur_l-1; dr < (duration+1)*12-1; dr++){
					if(tmp_origin_sv[dr+1] != -1  && posX_sv[dr] > width*0.067f)
						canvas.drawLine(posX_sv[dr], f_slong[dr], posX_sv[dr+1], f_slong[dr+1], stock_C2);
				}
			}
			// 주가 변동 추세
			
			switch(GV.getSRV()){
			case 0:
				sub_title = "분기 EPS";
				
				canvas.drawLine(b_x, c_y - t_i, b_x+l_x, c_y - t_i, pnt);
				canvas.drawCircle(c_x, c_y - t_i, r_c, pnt);
				canvas.drawCircle(c_x, c_y - t_i, r_c -1, W_24);
								
		    	for(int dx = 0; dx< duration; dx++){
					if(dx < duration-1 && GV.getStockD().m_m11[dx+1]==0 && GV.getStockD().m_quarter[dx]%4 == 0)
						f_sort[dx] = f_origin[dx] = 0.0f;
					else
						f_sort[dx] = f_origin[dx] = (float)(GV.getStockD().m_m11[dx]);
				}
		    	break;
			case 1:
				sub_title = "분기 EPS & 매출액";
				
				canvas.drawLine(b_x, c_y - t_i, b_x+l_x, c_y - t_i, pnt4);
				canvas.drawCircle(c_x, c_y - t_i, r_c, pnt4);
				canvas.drawCircle(c_x, c_y - t_i, r_c -1, W_24);
				
				for(int dx = 0; dx< duration; dx++){
					if(dx < duration-1 && GV.getStockD().m_m12[dx+1]==0 && GV.getStockD().m_quarter[dx]%4 == 0)
						tmp_sort[dx] = tmp_origin[dx] = 0;
					else
						tmp_sort[dx] = tmp_origin[dx] = GV.getStockD().m_m12[dx];
					
					if(dx < duration-1 && GV.getStockD().m_m11[dx+1]==0 && GV.getStockD().m_quarter[dx]%4 == 0)
						f_sort[dx] = f_origin[dx] = 0.0f;
					else
						f_sort[dx] = f_origin[dx] = (float)(GV.getStockD().m_m11[dx]);
				}
		    	break;
			case 2:
				sub_title = "매출액&영업이익&순이익";
				
				canvas.drawLine(b_x, c_y - (t_i*2), b_x+l_x, c_y - (t_i*2), pnt4);
				canvas.drawCircle(c_x, c_y - (t_i*2), r_c, pnt4);
				canvas.drawCircle(c_x, c_y - (t_i*2), r_c -1, W_24);

				canvas.drawLine(b_x, c_y - t_i, b_x+l_x, c_y - t_i, pnt5);
				canvas.drawCircle(c_x, c_y - t_i, r_c, pnt5);
				canvas.drawCircle(c_x, c_y - t_i, r_c -1, W_24);
				
				for(int dx = 0; dx< duration; dx++){
					if(dx < duration-1 && GV.getStockD().m_m12[dx+1]==0 && GV.getStockD().m_quarter[dx]%4 == 0)
						tmp_sort[dx] = tmp_origin[dx] = 0;
					else
						tmp_sort[dx] = tmp_origin[dx] = GV.getStockD().m_m12[dx];
					
					if(dx < duration-1 && GV.getStockD().m_m13[dx+1]==0 && GV.getStockD().m_quarter[dx]%4 == 0)
						tmp_sort2[dx] = tmp_origin2[dx] = 0;
					else
						tmp_sort2[dx] = tmp_origin2[dx] = GV.getStockD().m_m13[dx];
					
					if(dx < duration-1 && GV.getStockD().m_m14[dx+1]==0 && GV.getStockD().m_quarter[dx]%4 == 0)
						tmp_sort3[dx] = tmp_origin3[dx] = 0;
					else
						tmp_sort3[dx] = tmp_origin3[dx] = GV.getStockD().m_m14[dx];
				}
		    	break;
			case 3:
				sub_title = "영업이익률 & 순이익률";
				
				canvas.drawLine(b_x, c_y - t_i, b_x+l_x, c_y - t_i, pnt5);
				canvas.drawCircle(c_x, c_y - t_i, r_c, pnt5);
				canvas.drawCircle(c_x, c_y - t_i, r_c -1, W_24);
				
				for(int dx = 0; dx< duration; dx++){
					if(dx < duration-1 && GV.getStockD().m_m17[dx+1]==0 && GV.getStockD().m_quarter[dx]%4 == 0)
						f_sort[dx] = f_origin[dx] = 0;
					else
						f_sort[dx] = f_origin[dx] = GV.getStockD().m_m17[dx];
					
					if(dx < duration-1 && GV.getStockD().m_m18[dx+1]==0 && GV.getStockD().m_quarter[dx]%4 == 0)
						f_sort2[dx] = f_origin2[dx] = 0;
					else
						f_sort2[dx] = f_origin2[dx] = GV.getStockD().m_m18[dx];
				}
		    	break;
			case 4:
				sub_title = "영업외손익률";
				
				for(int dx = 0; dx< duration; dx++){
					if(GV.getStockD().m_m12[dx] == 0)
						f_sort[dx] = f_origin[dx] = 0;
					else
						f_sort[dx] = f_origin[dx] = (float)(GV.getStockD().m_m15[dx]-GV.getStockD().m_m16[dx])/(float)(GV.getStockD().m_m12[dx]);
				}
				break;
	    	}
			
			canvas.drawText(sub_title, 214*c_width, title_position, W_24);
			
			canvas.drawLine(b_x, c_y, b_x+l_x, c_y, pnt3);
			canvas.drawCircle(c_x, c_y, r_c, pnt3);
			canvas.drawCircle(c_x, c_y, r_c -1, W_24);
			
			switch(GV.getSRV()){
			case 0:
////////////////////////////////////////////view 1 begin		
					
				if(dis_ori == 1 || dis_ori == 3){
					tmp_D = null;
					tmp_D = mD.Ldigit(tmp_max, tmp_min, digit);
					
					drawText_mid(canvas, "주가"+ tmp_D.uWon +" ←", W_24);
					drawText_left(canvas, tmp_D, W_18);
				}
//*/					
				// EPS		 
				sorter.sort(f_sort, duration);
				
				f_max = f_sort[duration-1];
				f_min = f_sort[0];
				
				for(int dy = 0; dy<duration ; dy++){
					posY[dy] = height*(float)0.75 -(float)(f_origin[duration - dy -1]-f_min)/(float)(f_max-f_min)*height*(float)0.5;
				}
			 
				for(int dr = 0; dr < duration-1; dr++){
					if(posX[dr] > width*0.067f)
						canvas.drawLine(posX[dr], posY[dr], posX[dr+1], posY[dr+1], pnt3);
				}
//*					
				if(dis_ori == 1 || dis_ori == 3){
					tmp_D = null;
					tmp_D = mD.Fdigit(f_max, f_min);
					digit_count = tmp_D.dCount;
					
					drawText_btm(canvas, "분기 EPS"+ tmp_D.uWon +" →", B_24);
					drawText_right(canvas, tmp_D, W_18);
				
					for(int dr = 0; dr < duration; dr++){
						if(posX[dr] > width*0.067f){
							canvas.drawCircle(posX[dr], posY[dr], 4, pnt3);
							canvas.drawCircle(posX[dr], posY[dr], 3, W_24);
						}
					}
				}
				if(view_val > 0){
					tmp_dig_f = 1f;
					for(int xx = 0; xx < digit_count; xx++){
						tmp_dig_f *=10f;
					}
				}
//*			
				if(view_val > 0) 
					drawvalue_1(canvas, 
						convert_n2s(f_origin, duration, tmp_dig_f), 
						duration, posX, posY, B_18);
//*/

				break;
			case 1:
////////////////////////////////////////////view 2 begin
								
				// EPS
				//*   		 
				sorter.sort(f_sort, duration);
				
				f_max = f_sort[duration-1];
				f_min = f_sort[0];
				
				for(int dy = 0; dy<duration ; dy++){
					posY_t1[dy] = posY[dy] = height*(float)0.75 -(float)(f_origin[duration - dy -1]-f_min)/(float)(f_max-f_min)*height*(float)0.5;
				}
				//*/
				for(int dr = 0; dr < duration-1; dr++)
					if(posX[dr] > width*0.067f)
						canvas.drawLine(posX[dr], posY[dr], posX[dr+1], posY[dr+1], pnt3);
				
				if(dis_ori == 1 || dis_ori == 3){
					tmp_D = null;
					tmp_D = mD.Fdigit(f_max, f_min);
					digit_count = tmp_D.dCount;
					
					drawText_btm(canvas, "분기 EPS"+ tmp_D.uWon +" ←", B_24);
					drawText_left(canvas, tmp_D, W_18);
				
					for(int dr = 0; dr < duration; dr++)
						if(posX[dr] > width*0.067f){
						canvas.drawCircle(posX[dr], posY[dr], 4, pnt3);
						canvas.drawCircle(posX[dr], posY[dr], 3, W_24);
					}
				}
				if(view_val > 0){
					tmp_dig_f2 = 1f;
					for(int xx = 0; xx < digit_count; xx++){
						tmp_dig_f2 *=10f;
					}
				}
				
				// 매출액
				//*   		 
				sorter.sort(tmp_sort, duration);
				
				tmp_max = tmp_sort[duration-1];
				tmp_min = tmp_sort[0];
				
				for(int dy = 0; dy<duration ; dy++){
					posY[dy] = height*(float)0.75 -(float)(tmp_origin[duration - dy -1]-tmp_min)/(float)(tmp_max-tmp_min)*height*(float)0.5;
				}
				//*/
				for(int dr = 0; dr < duration-1; dr++)
					if(posX[dr] > width*0.067f)
						canvas.drawLine(posX[dr], posY[dr], posX[dr+1], posY[dr+1], pnt4);
				
				if(dis_ori == 1 || dis_ori == 3){
					tmp_D = null;
					tmp_D = mD.Ldigit(tmp_max, tmp_min, digit);
					digit_count = tmp_D.dCount;
					
					drawText_mid(canvas, "매출액"+ tmp_D.uWon +" →", B_24);
					drawText_right(canvas, tmp_D, W_18);
				
					for(int dr = 0; dr < duration; dr++)
						if(posX[dr] > width*0.067f){
						canvas.drawCircle(posX[dr], posY[dr], 4, pnt4);
						canvas.drawCircle(posX[dr], posY[dr], 3, W_24);
					}
				}
				
				if(view_val > 0){
					tmp_dig = 1l;
					for(int xx = 0; xx < digit_count; xx++){
						tmp_dig *=10l;
					}			
				}
				
				if(view_val > 0)
					drawvalue_2(canvas, 
						convert_n2s(f_origin, duration, tmp_dig_f2), convert_n2s(tmp_origin, duration, tmp_dig), 
						duration, posX, posY, posY_t1, B_18);
		
				break;
			case 2:
////////////////////////////////////////////view 3 begin
				
				// 매출액
				//*   		 
				sorter.sort(tmp_sort, duration);
				
				tmp_max = tmp_sort[duration-1];
				tmp_min = tmp_sort[0];
				
				for(int dy = 0; dy<duration ; dy++){
					posY_t1[dy] = posY[dy] = height*(float)0.75 -(float)(tmp_origin[duration - dy -1]-tmp_min)/(float)(tmp_max-tmp_min)*height*(float)0.5;
				}
				//*/
				for(int dr = 0; dr < duration-1; dr++)
					if(posX[dr] > width*0.067f)
						canvas.drawLine(posX[dr], posY[dr], posX[dr+1], posY[dr+1], pnt4);
				
				if(dis_ori == 1 || dis_ori == 3){
					tmp_D = null;
					tmp_D = mD.Ldigit(tmp_max, tmp_min, digit);
					digit_count = tmp_D.dCount;
					
					drawText_top(canvas, "매출액"+tmp_D.uWon + " ←", B_24);
					drawText_left(canvas, tmp_D, W_18);
				
					for(int dr = 0; dr < duration; dr++)
						if(posX[dr] > width*0.067f){
						canvas.drawCircle(posX[dr], posY[dr], 4, pnt4);
						canvas.drawCircle(posX[dr], posY[dr], 3, W_24);
					}
				}
				
				if(view_val > 0){
					tmp_dig = 1l;
					for(int xx = 0; xx < digit_count; xx++){
						tmp_dig *=10l;
					}
				}
				// 영업이익
				//*   	
				tmp_min = 9999;
				tmp_max = 0;
				
				sorter.sort(tmp_sort2, duration);
				sorter.sort(tmp_sort3, duration);
				
				tmp_max = Math.max(tmp_sort3[duration-1], tmp_sort2[duration-1]);
				tmp_min = Math.min(tmp_sort3[0], tmp_sort2[0]) ;
				
				for(int dy = 0; dy<duration ; dy++){
					posY_t2[dy] = posY[dy] = height*(float)0.75 -(float)(tmp_origin2[duration - dy -1]-tmp_min)/(float)(tmp_max-tmp_min)*height*(float)0.5;
				}
				//*/
				for(int dr = 0; dr < duration-1; dr++)
					if(posX[dr] > width*0.067f)
						canvas.drawLine(posX[dr], posY[dr], posX[dr+1], posY[dr+1], pnt5);
				
				
				if(dis_ori == 1 || dis_ori == 3)
					for(int dr = 0; dr < duration; dr++)
						if(posX[dr] > width*0.067f){
						canvas.drawCircle(posX[dr], posY[dr], 4, pnt5);
						canvas.drawCircle(posX[dr], posY[dr], 3, W_24);
					}
				
				
				// 순이익
				//*   		 
//				sorter.sort(tmp_sort3, duration);
				
//				tmp_max = tmp_sort3[duration-1];
//				tmp_min = tmp_sort3[0];
				
				for(int dy = 0; dy<duration ; dy++){
					posY[dy] = height*(float)0.75 -(float)(tmp_origin3[duration - dy -1]-tmp_min)/(float)(tmp_max-tmp_min)*height*(float)0.5;
				}
				//*/
				for(int dr = 0; dr < duration-1; dr++)
					if(posX[dr] > width*0.067f)
						canvas.drawLine(posX[dr], posY[dr], posX[dr+1], posY[dr+1], pnt3);
				
				if(dis_ori == 1 || dis_ori == 3){
					tmp_D = null;
					tmp_D = mD.Ldigit(tmp_max, tmp_min, digit);
					digit_count = tmp_D.dCount;
					
					drawText_mid(canvas, "영업 이익"+tmp_D.uWon+" →", B_24);
					drawText_btm(canvas, "순이익"+tmp_D.uWon+" →", B_24);
					drawText_right(canvas, tmp_D, W_18);
				
					for(int dr = 0; dr < duration; dr++)
						if(posX[dr] > width*0.067f){
						canvas.drawCircle(posX[dr], posY[dr], 4, pnt3);
						canvas.drawCircle(posX[dr], posY[dr], 3, W_24);
					}
				}
				
				if(view_val > 0){
					tmp_dig2 = 1l;
					for(int xx = 0; xx < digit_count; xx++){
						tmp_dig2 *=10l;
					}
				}
				
				if(view_val > 0)
					drawvalue_3(canvas, 
						convert_n2s(tmp_origin2, duration, tmp_dig2), convert_n2s(tmp_origin, duration, tmp_dig), convert_n2s(tmp_origin3, duration, tmp_dig2),
						duration, posX, posY, posY_t1, posY_t2, B_18);
			
				break;
			case 3:
//////////////////////////////////////////////view 4 begin
				
				// 영업 이익 o1/o3
				sorter.sort(f_sort, duration);
				
				f_max = f_sort[duration-1];
				f_min = f_sort[0];
				
				for(int dy = 0; dy<duration ; dy++){
					posY_t1[dy] = posY[dy] = height*(float)0.75 -(f_origin[duration - dy -1]-f_min)/(f_max-f_min)*height*(float)0.5;
				}
				
				for(int dr = 0; dr < duration-1; dr++)
					if(posX[dr] > width*0.067f)
						canvas.drawLine(posX[dr], posY[dr], posX[dr+1], posY[dr+1], pnt5);
				
				if(dis_ori == 1 || dis_ori == 3){
					tmp_D = null;
					tmp_D = mD.Fdigit(f_max, f_min);	
					
					drawText_mid(canvas, "영업이익률"+uPer+" ←", B_24);
					drawText_left(canvas, tmp_D, W_18);
				
					for(int dr = 0; dr < duration; dr++)
						if(posX[dr] > width*0.067f){
						canvas.drawCircle(posX[dr], posY[dr], 4, pnt5);
						canvas.drawCircle(posX[dr], posY[dr], 3, W_24);
					}
				}else
					drawText_mid(canvas, "영업이익률"+uPer+" ←", B_24);
				
				// 순 이익 o2/o3
				//*	    	 
				sorter.sort(f_sort2, duration);
				
				f_max = f_sort2[duration-1];
				f_min = f_sort2[0];
				
				for(int dy = 0; dy<duration ; dy++){
					posY[dy] = height*(float)0.75 -(f_origin2[duration - dy -1] - f_min)/(f_max-f_min)*height*(float)0.5;
				}
				//*/
				for(int dr = 0; dr < duration-1; dr++)
					if(posX[dr] > width*0.067f)
						canvas.drawLine(posX[dr], posY[dr], posX[dr+1], posY[dr+1], pnt3);
				
				if(dis_ori == 1 || dis_ori == 3){
					tmp_D = null;
					tmp_D = mD.Fdigit(f_max, f_min);
					
					drawText_btm(canvas, "순이익률"+uPer+" →", B_24);
					drawText_right(canvas, tmp_D, W_18);
				
					for(int dr = 0; dr < duration; dr++)
						if(posX[dr] > width*0.067f){
						canvas.drawCircle(posX[dr], posY[dr], 4, pnt3);
						canvas.drawCircle(posX[dr], posY[dr], 3, W_24);
					}
				}
				
				if(view_val > 0)
					drawvalue_2(canvas, 
						convert_n2s(f_origin2, duration), convert_n2s(f_origin, duration),
						duration, posX, posY, posY_t1, B_18);
				
				break;
			case 4:
//////////////////////////////////////////////view 5 begin
				
				// 영업외손익
				//*   		 
				sorter.sort(f_sort, duration);
				
				f_max = f_sort[duration-1];
				f_min = f_sort[0];
				
				for(int dy = 0; dy<duration ; dy++){
					posY[dy] = height*(float)0.75 -(f_origin[duration - dy -1] - f_min)/(f_max-f_min)*height*(float)0.5;
				}	
				//*/
				for(int dr = 0; dr < duration-1; dr++)
					if(posX[dr] > width*0.067f)
						canvas.drawLine(posX[dr], posY[dr], posX[dr+1], posY[dr+1], pnt3);
				
				if(dis_ori == 1 || dis_ori == 3){
					tmp_D = null;
					tmp_D = mD.Fdigit(f_max*100f, f_min*100f);
					digit_count = tmp_D.dCount;
					
					drawText_btm(canvas, "영업외손익 (%)", B_24);
					drawText_left(canvas, tmp_D, W_18);
									
					for(int dr = 0; dr < duration; dr++)
						if(posX[dr] > width*0.067f){
						canvas.drawCircle(posX[dr], posY[dr], 4, pnt3);
						canvas.drawCircle(posX[dr], posY[dr], 3, W_24);
					}
				}
				
				if(view_val > 0)
					drawvalue_1(canvas, 
						convert_n2s(f_origin, duration), 
						duration, posX, posY, B_18);
				
				break;
			}
			
			canvas.drawBitmap(map006, 60*c_width, 115*c_height, pnt);
//			canvas.drawText("차트 설명 보기", width - 200, height-20, pnt2);
				
			if(sub_menu == 1){
				canvas.drawRect(200*c_width, title_position-29*c_height, width-20*c_width, title_position+270*c_height, _Drawable2.getPaint());
				canvas.drawText("분기 EPS", 214*c_width, title_position, W_24);
				canvas.drawText("분기 EPS & 매출액", 214*c_width, title_position+50*c_height, W_24);
				canvas.drawText("매출액 & 영업이익 & 순이익", 214*c_width, title_position+100*c_height, W_24);
				canvas.drawText("영업이익률 & 순이익률", 214*c_width, title_position+150*c_height, W_24);
				canvas.drawText("영업외손익률", 214*c_width, title_position+200*c_height, W_24);
				canvas.drawBitmap(Icon_m, width-20*c_width-Icon_m.getWidth(), title_position+270*c_height-Icon_m.getHeight(), pnt);
			}
			
			if(sub_menu == 2){
				canvas.drawRect(20*c_width, title_position+13*c_height, 190*c_width, title_position+270*c_height, B_24);
				canvas.drawRect(21*c_width, title_position+13*c_height, 189*c_width, title_position+269*c_height, W_24);
	//			_Drawable2.getPaint().setColor(0xff0071d1);
	//			canvas.drawText("수익성", 34, title_position+50, _Drawable2.getPaint());
				_Drawable2.getPaint().setColor(0xff079200);
				_Drawable2.getPaint().setTextSize(24*c_height);
				canvas.drawText("안전성", 34*c_width, title_position+50*c_height, _Drawable2.getPaint());
				_Drawable2.getPaint().setColor(0xffFFC600);
				canvas.drawText("배당성", 34*c_width, title_position+100*c_height, _Drawable2.getPaint());
				_Drawable2.getPaint().setColor(0xffA90000);
				canvas.drawText("성장성", 34*c_width, title_position+150*c_height, _Drawable2.getPaint());
				_Drawable2.getPaint().setColor(0xff5B00A9);
				canvas.drawText("효율성", 34*c_width, title_position+200*c_height, _Drawable2.getPaint());
				_Drawable2.getPaint().setColor(0xffA4A4A4);
				canvas.drawText("가치평가", 34*c_width, title_position+250*c_height, _Drawable2.getPaint());
	//			canvas.drawBitmap(Icon_m, width-20-Icon_m.getWidth(), title_position+270-Icon_m.getHeight(), pnt);
			}
			break;
		case 2:
			map012 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.m2);
			map013 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.sm2);
			map014 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.mm2);
			
			break;
		case 3:
			map012 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.m3);
			map013 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.sm3);
			map014 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.mm3);
			
			break;
		case 4:
			map012 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.m4);
			map013 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.sm4);
			map014 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.mm4);
			
			break;
		case 5:
			map012 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.m5);
			map013 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.sm5);
			map014 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.mm5);
			
			break;
		case 6:
			map012 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.m6);
			map013 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.submenu);
			map014 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.mm6);
			
			break;
		}
	}
}