/*
 * ���� �� ���� ȸ���ΰ�?
 */

package com.quantec.Stock.Activity;

import com.quantec.Stock.MyApp;
import com.quantec.Stock.R;

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
import android.graphics.Typeface;
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

public class Menu_One extends Activity {
	
	MyApp GV;
	
	private LinearLayout contentsContatiner;
	
	private static final int MAX_VIEW_DEPTH = 5;
	private static final float D_width = 800f;
	private static final float D_height = 480f;
	int title_position = 80;
	int digit=5;
	int BEGIN_YEAR;
	
	Intent Cmenu1, Cmenu2, Cmenu3, Cmenu4, Cmenu5, Cmenu6;
    
    testView test;
    
    float x1, y1, x2, y2;
    int tmp_v;
    int duration;
    
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
	float[] f_sort3;
	
	float[] f_slong;				// �ְ� ���� �߼�
	float[] f_sshort;				// �ְ� ���� �߼�
	
	float f_stmp;					// �ְ� ���� �߼�
	int macd=0;					// �ְ� ���� �߼�
	int dur2;
	int dur_s = 5, dur_l=20;

	digitD tmp_D = new digitD();

//	long[] tmp_002;
    //////////////////////////////////////////////
    
    int fix_dis = 0;
    AlertDialog.Builder bld;
    String mTitle = "���ͼ�";
    
    String exp1 = "[�б� EPS]\n" +
    		"* 1�ִ� ������ �� â���Ͽ����ĸ� ��Ÿ���� ��ǥ\n\n" +
    		"[��ǥ�м����]\n" +
    		"* �Ϲ������� EPS�� ���ٴ°��� �׸�ŭ �濵������ ��ȣ�ϴٴ� ���̸�, ��翩�µ� �����Ƿ� �ְ��� �������� ������ ��ģ��.\n\n" +
    		"[��������Ʈ]\n" +
    		"* ������ �����ϰ� ������ �ְ��� Ⱦ���ϰų� ������ �϶��ϴ� ��� ��� �ż� ��ȸ.";
    String exp2 = "[�б� EPS & �����]\n" +
    		"* 1�ִ� ������ �� â���Ͽ����Ŀ� ���� �б��� ������� ��Ÿ���� ��ǥ\n\n" +
    		"[��ǥ�м����]\n" +
    		"* �� ������ ���Ⱑ ����� �ϸ� ����� ������ ������� ���� ���� �ִٴ� ���� �ǹ�\n\n" +
    		"[��������Ʈ]\n" +
    		"* ���Ⱓ ��Ʈ�� ����� �ϴ� ������ ���� ���� ����";
    String exp3 = "[����� & �������� & ������]\n" +
    		"* ����� ��� ������ ��Ÿ���� ��ǥ.\n\n" +
    		"[��������Ʈ]\n" +
    		"* ���Ⱓ ��Ʈ�� ������ϴ� ������ ���� ���� ����";
    String exp4 = "[�������ͷ� & �����ͷ�]\n" +
    		"�������ͷ� = �������� / ����� * 100\n" +
    		"(20% �̻� ��ȣ, 10% ���� �ҷ�)\n" +
    		" * ����� �ֵ� ����Ȱ���� ���� ������ �м��ϱ� ���� ��ǥ\n" +
    		"�����ͷ� = ������ /  ����� * 100\n" +
    		"(5% �̻� ��ȣ, 2% ���� �ҷ�)\n" +
    		" * ��ǰ�̳� ��ǰ�� �������� ���ͷ��� �����ϴ� ��ǥ\n\n" +
    		"[��ǥ�м����]\n" +
    		"* �������ͷ��� ȸ�簡 �������� �󸶳� ���� ������ ������� �����ִ� ���� �߿��� ���ͼ� ��ǥ �� �ϳ�\n" +
    		"* �����ͷ��� �����ϸ� ���ֿ��� ���ư��� ������ Ŀ���Ƿ� ���ڸŷµ��� ������\n\n" +
    		"[��������Ʈ]\n" +
    		"* �Ϲ������� �߰� ����縦 ���� �Ǹ� �ϴ� ��ȭ�а����� ��� ����׿��� �����ϴ� �ǸŰ��������� ���� ������ �������ͷ��� ������, �Һ��ڵ鿡�� ���� �Ǹ��ϴ� ����,ȭ��ǰ, ���������� �Ǹ� ������ ���⶧���� �������ͷ��� ����\n" +
    		"* �Ϲ������� �����ͷ��� ���ٴ� ���� ȸ���� �������� ���ٴ� ���� ���ϳ� �� ������ �����ڻ� ó�е��� �������� ������ �߻����� ���� ���� ��� ����\n";
    String exp5 = "[�����ܼ��ͷ�]\n" +
    		"�����ܼ��ͷ� = (�����ܼ��� - �����ܺ��) / ����� * 100\n" +
    		"(10%�̻� ��ȣ, 5%���� �ҷ�)\n" +
    		" * ����� �ֵ� ����Ȱ������ ����� ���� �̿��� ������ ��Ÿ���� ��ǥ\n\n" +
    		"[��ǥ�м����]\n" +
    		"* ����� �ֵ� ����Ȱ�� �Ӹ� �ƴ϶� �繫 Ȱ������ �߻��� �濵������ ���ÿ� �м� �� �� �ִ� ��ǥ\n\n" +
    		"[��������Ʈ]\n" +
    		"* �����ܼ��������� �Ͻ����� ��찡 ����. ���� �����ܼ��� �߻� ������ Ȯ���ϸ�, �̷��� �ܺλ�Ȳ�� �ٲ� ��� ū ���� ������ ����� ������ �ְ� ����� ũ�� ��Ÿ�� �� �ִ�.";
	public void onCreate(Bundle savedInstanceState) {
		
		GV = (MyApp) getApplication();
		
		GV.setDisplay(((WindowManager)this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay());
		
		width = GV.getDisplay().getWidth();
		height= GV.getDisplay().getHeight();
		
		c_width = (float)width/D_width;
		c_height = (float)height/D_height;
		
		dur2 = (GV.getStockCount().last_sw/100 - ((GV.getStockCount().begin_sq/100)*100 + (GV.getStockCount().begin_sq%100 + 1)*3) - 1)*4 + GV.getStockCount().last_sw%100; 
		
		title_position = height/6;
		
		BEGIN_YEAR = (GV.getStockD().m_quarter[0])/100 - 9;
		
		super.onCreate(savedInstanceState); 
		
		Cmenu1 = new Intent(this, ContentsActivity.class);
		
//		Cmenu1 = new Intent(this, Menu_One.class);
		Cmenu2 = new Intent(this, Menu_Two.class);
		Cmenu3 = new Intent(this, Menu_Three.class);
		Cmenu4 = new Intent(this, Menu_Four.class);
		Cmenu5 = new Intent(this, Menu_Five.class);
		Cmenu6 = new Intent(this, Menu_Six.class);
		
		bld = new AlertDialog.Builder(this);
		
		posX = new float[(GV.getDuration()+1)];
		posY = new float[(GV.getDuration()+1)];
		posY_t1 = new float[GV.getDuration()+1];
		posY_t2 = new float[GV.getDuration()+1];
		
		
		c_y = height *0.842f;
		
		b_x = width*0.03125f;
		l_x = width*0.02f;
		c_x = b_x + width*0.01f;
		t_i = height*(-0.0525f);
		
		tmp_sort = new long[(GV.getDuration()+1)];
		tmp_sort2 = new long[(GV.getDuration()+1)];
		tmp_origin = new long[(GV.getDuration()+1)];
		tmp_origin2 = new long[(GV.getDuration()+1)];
		tmp_sort3 = new long[(GV.getDuration()+1)];
		tmp_origin3 = new long[(GV.getDuration()+1)];
		
		tmp_sort_sv = new int[(GV.getDuration()+1)*12];
		tmp_origin_sv = new int[(GV.getDuration()+1)*12];
		
		posX_sv = new float[(GV.getDuration()+1)*12];
		posY_sv = new float[(GV.getDuration()+1)*12];
		f_slong = new float[(GV.getDuration()+1)*12];
		f_sshort = new float[(GV.getDuration()+1)*12];
		
		f_sort = new float[GV.getDuration()];
		f_origin = new float[GV.getDuration()];
		f_sort2 = new float[GV.getDuration()];
		f_origin2 = new float[GV.getDuration()];
		f_sort3 = new float[GV.getDuration()];
		
//		tmp_002 = new long[GV.getDuration()];
		
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
//*// �ְ� ���� �߼�						
						else if( x1 < (float) (MyApp.getDisplay().getWidth()*0.80)+width*0.0625f && x1 > (float) (MyApp.getDisplay().getWidth()*0.80) && y1 > title_position-height*0.09f && y1 < title_position+height*0.01f){
//							GV.setStockD(null);
							if(macd==0)
								macd=1;
							else
								macd=0;
						}
//*///�ְ� ���� �߼�						
						else if( y1 > height*0.24f && y1 < height*0.327f && x1 > width*0.075f && x1 < width*0.231f){ //���� ��Ʈ���� ��ư ��ġ
							view_val = -1;
							if(GV.getSRV() == 0) bld.setMessage(exp1);
							else if(GV.getSRV() == 1) bld.setMessage(exp2);
							else if(GV.getSRV() == 2) bld.setMessage(exp3);
							else if(GV.getSRV() == 3) bld.setMessage(exp4);
							else if(GV.getSRV() == 4) bld.setMessage(exp5);
							bld.setPositiveButton("�ݱ�", new DialogInterface.OnClickListener() {
								
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
										view_val = (int) ((float)(x1-width*0.075f /*  */+((float)(width-width*0.15f)/(float)((duration+1)*12-1)*dur2) )*39.0f/(float)(GV.getDisplay().getWidth()-width*0.1875f));
									}
								}else if(opt_dur == 1){
									if(x1 >= width*0.075f && x1 <= GV.getDisplay().getWidth()-width*0.075f){
										view_val = (int) ((float)(x1-width*0.075f/*  */+((float)(width-width*0.15f)/(float)((duration+1)*12-1)*dur2))*19.0f/(float)(GV.getDisplay().getWidth()-width*0.1875f));
									}
								}else if(opt_dur == 2){
									if(x1 >= width*0.075f && x1 <= GV.getDisplay().getWidth()-width*0.075f){
										view_val = (int) ((float)(x1-width*0.075f/*  */+((float)(width-width*0.15f)/(float)((duration+1)*12-1)*dur2))*11.0f/(float)(GV.getDisplay().getWidth()-width*0.1875f));
									}
								}
							}
						}
					}else if(sub_menu==1){
						view_val = -1;
						
						if(y1 > title_position-20*c_height && y1 < title_position+20*c_height){
							GV.setSRV(0);
							sub_menu = 0;
						}else if(y1 > title_position+30*c_height && y1 < title_position+70*c_height){
							GV.setSRV(1);
							sub_menu = 0;
						}else if(y1 > title_position+80*c_height && y1 < title_position+120*c_height){
							GV.setSRV(2);
							sub_menu = 0;
						}else if(y1 > title_position+130*c_height && y1 < title_position+170*c_height){
							GV.setSRV(3);
							sub_menu = 0;
						}else if(y1 > title_position+180*c_height && y1 < title_position+220*c_height){
							GV.setSRV(4);
							sub_menu = 0;
						}else{
							sub_menu=0;
						}
					}else{
						view_val = -1;
						
						if(y1 > title_position+30*c_height && y1 < title_position+70*c_height){
							GV.setSRM(2);
		           			GV.setSRV(0);
		           			sub_menu=0;
		           			startActivity(Cmenu2);
		           			finish();
						}else if(y1 > title_position+80*c_height && y1 < title_position+120*c_height){
							GV.setSRM(3);
		           			GV.setSRV(0);
		           			sub_menu=0;
		           			startActivity(Cmenu3);
		           			finish();
						}else if(y1 > title_position+130*c_height && y1 < title_position+170*c_height){
							GV.setSRM(4);
		           			GV.setSRV(0);
		           			sub_menu=0;
		           			startActivity(Cmenu4);
		           			finish();
						}else if(y1 > title_position+180*c_height && y1 < title_position+220*c_height){
							GV.setSRM(5);
		           			GV.setSRV(0);
		           			sub_menu=0;
		           			startActivity(Cmenu5);
		           			finish();
						}else if(y1 > title_position+230*c_height && y1 < title_position+270*c_height){
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
		
		Bitmap map012 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.m1);
		Bitmap map013 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.sm1);
		Bitmap map014 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.mm1);

		Bitmap map015 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.search_ss); // �ְ� ���� �߼�
		

		Bitmap c01 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cloud_up);
		Bitmap c02 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cloud_mid);
		Bitmap c03 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cloud_down);
		
		Bitmap ci01 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cloud_i_up);
		Bitmap ci02 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cloud_i_mid);
		Bitmap ci03 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cloud_i_down);

    	public testView(Context context)
    	{
    		super(context);
    	}
    	public testView(Context context, AttributeSet attrs) {
    		super(context, attrs);
    	}
    	public void onDraw(Canvas canvas){
    		
    		String v_max1,v_min1,v1,v2,v3,v4; 
    		
    		String uPer= " (%)", uWon2 = "", uWon1 = "";
    		
    		duration = GV.getDuration();
    		width = GV.getDisplay().getWidth();
    		height = GV.getDisplay().getHeight();
    		
    		_Drawable.getPaint().setColor(0xffAAAAAA);
    		_Drawable2.getPaint().setColor(0xff0071d1);
    		
    		QuickSort sorter = new QuickSort();
    		makeDigit mD = new makeDigit();

    		pnt.setColor(0xffA90000);//red
    		pnt.setTextSize(18*c_height);
    		W_24.setColor(Color.WHITE);
    		W_24.setTextSize(24*c_height);
    		pnt3.setColor(0xff0071d1);//blue
    		pnt3.setTextSize(18*c_height);
    		pnt3.setStrokeWidth(3);	// �� ���� ���� 
    		pnt4.setColor(0xff079200);//green
    		pnt4.setTextSize(18*c_height);
    		pnt4.setStrokeWidth(3);	// �� ���� ���� 
    		pnt5.setColor(0xffFFC600);//yellow
    		pnt5.setTextSize(18*c_height);
    		pnt5.setStrokeWidth(3);	// �� ���� ����
    		B_18.setColor(Color.BLACK);
    		B_18.setTextSize(18*c_height);
    		W_18.setColor(Color.WHITE);
    		W_18.setTextSize(18*c_height);
    		B_24.setColor(Color.WHITE);//BLACK);
    		B_24.setTextSize(24*c_height);
    		

    		stock_C1.setColor(0xff079200);//yellow
    		stock_C1.setStrokeWidth(3);	// �� ���� ����
    		stock_C2.setColor(0xffFFC600);//yellow
    		stock_C2.setStrokeWidth(3);	// �� ���� ����
    		
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
    		
    		
    		if(GV.getSRM() == 1){
    			
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
//    				canvas.drawText("[ ", 45, title_position-40, B_24); 
    				if(GV.getStockD().m_Kname.length() > 8){
    					canvas.drawText(GV.getStockD().m_Kname, 0, 8, 50*c_width, title_position-40*c_height, W_24);
    					canvas.drawText("...", width*0.28f, title_position-height*0.08f, W_24);
    				}else{
    					canvas.drawText(GV.getStockD().m_Kname, 50*c_width, title_position-40*c_height, W_24);
//    					canvas.drawText(" - "+mTitle, 54 + (GV.getStockD().m_Kname.length())*20, title_position-40, B_24);
    				}
    				canvas.drawText(mTitle, 34*c_width, title_position, W_24);
    				canvas.drawBitmap(map003, MyApp.getDisplay().getWidth()*0.90f, title_position-50*c_height, pnt);
    				canvas.drawBitmap(map015, MyApp.getDisplay().getWidth()*0.80f, title_position-50*c_height, pnt);
    				
    				for(int dx = 0; dx< (duration+1)*12; dx++)
						posX_sv[dx] = (float)(width-width*0.15f)/(float)((duration+1)*12-1)*(float)dx + width*0.075f;
	    			
	    			for(int dx = 0; dx< duration; dx++){
						posX[dx] = (float)(width-width*0.15f)/(float)(duration)*dx + width*0.075f  /* */ - (float)(width-width*0.15f)/(float)((duration+1)*12-1)*dur2;
	    			}
	    			for(int dx = 0; dx< duration; dx++){
						if(GV.getStockD().m_quarter[duration - dx-1]%4 == 1 && posX[dx] > width*0.067f){
							canvas.drawText(Y_count+"", posX[dx], height*0.77f+15*c_height, W_18);
							Y_count++;
							
							canvas.drawLine(posX[dx], height*0.23f, posX[dx], height*(float)0.77, _Drawable.getPaint());
						}
	    			}
    			}
    			
    			if(opt_dur == 0){
	    			canvas.drawBitmap(map004, GV.getDisplay().getWidth()-190*c_width, GV.getDisplay().getHeight()-90*c_height, pnt);
	    			canvas.drawBitmap(map007, GV.getDisplay().getWidth()-145*c_width, GV.getDisplay().getHeight()-90*c_height, pnt);
	    			canvas.drawBitmap(map011, GV.getDisplay().getWidth()-100*c_width, GV.getDisplay().getHeight()-90*c_height, pnt);
    			}else if(opt_dur == 1){
	    			canvas.drawBitmap(map004, GV.getDisplay().getWidth()-190*c_width, GV.getDisplay().getHeight()-90*c_height, pnt);
	    			canvas.drawBitmap(map010, GV.getDisplay().getWidth()-145*c_width, GV.getDisplay().getHeight()-90*c_height, pnt);
	    			canvas.drawBitmap(map008, GV.getDisplay().getWidth()-100*c_width, GV.getDisplay().getHeight()-90*c_height, pnt);
    			}else if(opt_dur == 2){
	    			canvas.drawBitmap(map009, GV.getDisplay().getWidth()-190*c_width, GV.getDisplay().getHeight()-90*c_height, pnt);
	    			canvas.drawBitmap(map007, GV.getDisplay().getWidth()-145*c_width, GV.getDisplay().getHeight()-90*c_height, pnt);
	    			canvas.drawBitmap(map008, GV.getDisplay().getWidth()-100*c_width, GV.getDisplay().getHeight()-90*c_height, pnt);
    			}
				if(GV.getSRV() == 0){
////////////////////////////////////////////view 1 begin		
/*					
					float[] f_sort = new float[(duration+1)];
			    	float[] f_origin = new float[(duration+1)];
			    	float[] f_sort2 = new float[(duration+1)];
*/			    	
			    	
					canvas.drawText("�б� EPS", 214*c_width, title_position, W_24);
					

		    		pnt.setStrokeWidth(3); 
					canvas.drawLine(b_x, c_y - t_i, b_x+l_x, c_y - t_i, pnt);
					canvas.drawCircle(c_x, c_y - t_i, r_c, pnt);
					canvas.drawCircle(c_x, c_y - t_i, r_c -1, W_24);
//					canvas.drawText("�ְ�"+ uWon1, c_x + 10, c_y - t_i + 10, B_24);
					
					canvas.drawLine(b_x, c_y, b_x+l_x, c_y, pnt3);
					canvas.drawCircle(c_x, c_y, r_c, pnt3);
					canvas.drawCircle(c_x, c_y, r_c -1, W_24);
//					canvas.drawText("EPS ����"+uWon1, c_x + 10, c_y + 10, B_24);
			    	
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
						if(dx < duration-1 && GV.getStockD().m_m11[dx+1]==0 && GV.getStockD().m_quarter[dx]%4 == 0)
							f_sort[dx] = f_origin[dx] = 0.0f;
						else
							f_sort[dx] = f_origin[dx] = (float)(GV.getStockD().m_m11[dx]);
					}

					// �ְ�		 
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

//*// �ְ� ���� �߼�					
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
//*/// �ְ� ���� �߼�
					
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
						
						canvas.drawText("�ְ�"+ uWon1 +" ��", c_x + 10, c_y - t_i + 10, W_24);
												
						canvas.drawText(v_max1, 5, height*0.26f, W_18);
						canvas.drawText(v4, 5, height*0.35f, W_18);
						canvas.drawText(v3, 5, height*0.45f, W_18);
						canvas.drawText(v2, 5, height*0.56f, W_18);
						canvas.drawText(v1, 5, height*0.66f, W_18);
						canvas.drawText(v_min1, 5, height*0.77f, W_18);
					}
					
//*/					
					// EPS		 
					sorter.sort(f_sort, duration);
					
					f_max = f_sort[duration-1];
					f_min = f_sort[0];
					
					for(int dy = 0; dy<duration ; dy++){
						posY[dy] = height*(float)0.75 -(float)(f_origin[duration - dy -1]-f_min)/(float)(f_max-f_min)*height*(float)0.5;
					}
					 
//					if(macd==0){	// �ְ� ���� �߼�
						for(int dr = 0; dr < duration-1; dr++){
							if(posX[dr] > width*0.067f)
								canvas.drawLine(posX[dr], posY[dr], posX[dr+1], posY[dr+1], pnt3);
						}
	//*					
						if(dis_ori == 1 || dis_ori == 3){
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
							
							//canvas.drawText("EPS ���� (%) ��", c_x + 10, c_y + 10, B_24);
							canvas.drawText("�б� EPS"+ uWon2 +" ��", c_x + 10, c_y + 10, B_24);
											
							canvas.drawText(v_max1, width-2 - v_max1.length()*11*c_height, height*0.26f, W_18);
							canvas.drawText(v4, width-2 - v_max1.length()*11*c_height, height*0.35f, W_18);
							canvas.drawText(v3, width-2 - v_max1.length()*11*c_height, height*0.45f, W_18);
							canvas.drawText(v2, width-2- v_max1.length()*11*c_height, height*0.56f, W_18);
							canvas.drawText(v1, width-2- v_max1.length()*11*c_height, height*0.66f, W_18);
							canvas.drawText(v_min1, width-2 - v_max1.length()*11*c_height, height*0.77f, W_18);
						
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
						if(view_val >= 0 && view_val < 15){
							canvas.drawBitmap(c01, posX[view_val], posY[view_val]-c01.getHeight(), pnt);
	//						canvas.drawLine(posX[view_val]+7, posY[view_val]-c01.getHeight()/2, posX[view_val]+27, posY[view_val]-c01.getHeight()/2, pnt3);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin[duration-1-view_val]/tmp_dig_f)), posX[view_val]+7*c_height, posY[view_val]-c01.getHeight()/2, B_18);
						}else if(view_val>=15 && view_val < duration){
							canvas.drawBitmap(ci01, posX[view_val]-ci01.getWidth(), posY[view_val]-ci01.getHeight(), pnt);
	//						canvas.drawLine(posX[view_val]+7, posY[view_val]-c01.getHeight()/2, posX[view_val]+27, posY[view_val]-c01.getHeight()/2, pnt3);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin[duration-1-view_val]/tmp_dig_f)), posX[view_val]-ci01.getWidth()+7*c_height, posY[view_val]-c01.getHeight()/2, B_18);
						}
//					}	// �ְ� ���� �߼�
				}else if(GV.getSRV() == 1){
////////////////////////////////////////////view 2 begin
					canvas.drawText("�б� EPS & �����", 214*c_width, title_position, W_24);

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
						if(dx < duration-1 && GV.getStockD().m_m12[dx+1]==0 && GV.getStockD().m_quarter[dx]%4 == 0)
							tmp_sort[dx] = tmp_origin[dx] = 0;
						else
							tmp_sort[dx] = tmp_origin[dx] = GV.getStockD().m_m12[dx];

						if(dx < duration-1 && GV.getStockD().m_m11[dx+1]==0 && GV.getStockD().m_quarter[dx]%4 == 0)
							f_sort[dx] = f_origin[dx] = 0.0f;
						else
							f_sort[dx] = f_origin[dx] = (float)(GV.getStockD().m_m11[dx]);
					}
/*					sorter.sort(f_sort3, duration);
					sorter.sort(tmp_002, duration);
					
					for(int dx = 0; dx< duration; dx++){
						if(f_sort3[duration-1] == 0)
							f_sort[dx] = f_origin[dx] = 0;
						else
							f_sort[dx] = f_origin[dx] = f_origin[dx]/f_sort3[duration-1];
						
						if(tmp_002[duration-1] == 0 || (dx < duration-1 && GV.getStockD().m_m12[dx+1]==0 && GV.getStockD().m_quarter[dx]%4 == 0))
							f_sort2[dx] = f_origin2[dx] = 0;
						else
							f_sort2[dx] = f_origin2[dx] = (float)(GV.getStockD().m_m12[dx])/(float)(tmp_002[duration-1]);
					}
*/					
					canvas.drawLine(b_x, c_y - t_i, b_x+l_x, c_y - t_i, pnt4);
					canvas.drawCircle(c_x, c_y - t_i, r_c, pnt4);
					canvas.drawCircle(c_x, c_y - t_i, r_c -1, W_24);
//					canvas.drawText("����(���� ����)"+uPer, c_x + 10, c_y - t_i + 10, B_24);
					
					canvas.drawLine(b_x, c_y, b_x+l_x, c_y, pnt3);
					canvas.drawCircle(c_x, c_y, r_c, pnt3);
					canvas.drawCircle(c_x, c_y, r_c -1, W_24);
//					canvas.drawText("EPS ����"+uPer, c_x + 10, c_y + 10, B_24);

					// �ְ�		 
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
//*// �ְ� ���� �߼�					
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
//*/// �ְ� ���� �߼�
					
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
						uWon2 = tmp_D.uWon;
						digit_count = tmp_D.dCount;
						v_max1 = tmp_D.max;
						v_min1 = tmp_D.min;
						v1 = tmp_D.v1;
						v2 = tmp_D.v2;
						v3 = tmp_D.v3;
						v4 = tmp_D.v4;
						
						//canvas.drawText("EPS ���� (%) ��", c_x + 10, c_y + 10, B_24);
						canvas.drawText("�б� EPS"+ uWon2 +" ��", c_x + 10, c_y + 10, B_24);
					
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
					if(view_val > 0){
						tmp_dig_f2 = 1f;
						for(int xx = 0; xx < digit_count; xx++){
							tmp_dig_f2 *=10f;
						}
					}
					
					// �����
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
						uWon1 = tmp_D.uWon;
						digit_count = tmp_D.dCount;
						v_max1 = tmp_D.max;
						v_min1 = tmp_D.min;
						v1 = tmp_D.v1;
						v2 = tmp_D.v2;
						v3 = tmp_D.v3;
						v4 = tmp_D.v4;
						
						canvas.drawText("�����"+ uWon1 +" ��", c_x + 10, c_y - t_i + 10, B_24);
												
						canvas.drawText(v_max1, width-2 - v_max1.length()*11*c_height, height*0.26f, W_18);
						canvas.drawText(v4, width-2 - v_max1.length()*11*c_height, height*0.35f, W_18);
						canvas.drawText(v3, width-2 - v_max1.length()*11*c_height, height*0.45f, W_18);
						canvas.drawText(v2, width-2- v_max1.length()*11*c_height, height*0.56f, W_18);
						canvas.drawText(v1, width-2- v_max1.length()*11*c_height, height*0.66f, W_18);
						canvas.drawText(v_min1, width-2 - v_max1.length()*11*c_height, height*0.77f, W_18);
					
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
					
					if(view_val >= 0 && view_val < 15){
						if(posY[view_val]>posY_t1[view_val]){
							canvas.drawBitmap(c01, posX[view_val], posY_t1[view_val]-c01.getHeight(), pnt);
//							canvas.drawLine(posX[view_val]+7, posY[view_val]-c01.getHeight()/2, posX[view_val]+27, posY[view_val]-c01.getHeight()/2, pnt3);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin[duration-1-view_val]/tmp_dig_f2)), posX[view_val]+7*c_height, posY_t1[view_val]-c01.getHeight()/2, B_18);
							
							canvas.drawBitmap(c02, posX[view_val], posY[view_val]-c02.getHeight()/2, pnt);
//							canvas.drawLine(posX[view_val]+17, posY[view_val]+7,posX[view_val]+37, posY[view_val]+7, pnt4);
							canvas.drawText(Long.toString(tmp_origin[duration-1-view_val]/tmp_dig), posX[view_val]+17*c_height, posY[view_val]+7, B_18);
						}else{
							canvas.drawBitmap(c01, posX[view_val], posY[view_val]-c01.getHeight(), pnt);
//							canvas.drawLine(posX[view_val]+7, posY[view_val]-c01.getHeight()/2, posX[view_val]+27, posY[view_val]-c01.getHeight()/2, pnt4);
							canvas.drawText(Long.toString(tmp_origin[duration-1-view_val]/tmp_dig), posX[view_val]+7*c_height, posY[view_val]-c01.getHeight()/2, B_18);
							
							canvas.drawBitmap(c02, posX[view_val], posY_t1[view_val]-c02.getHeight()/2, pnt);
//							canvas.drawLine(posX[view_val]+17, posY[view_val]+7,posX[view_val]+37, posY[view_val]+7, pnt3);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin[duration-1-view_val]/tmp_dig_f2)), posX[view_val]+17*c_height, posY_t1[view_val]+7, B_18);
						}
					}else if(view_val>=15 && view_val < duration){
						if(posY[view_val]>posY_t1[view_val]){
							canvas.drawBitmap(ci01, posX[view_val]-ci01.getWidth(), posY_t1[view_val]-ci01.getHeight(), pnt);
//							canvas.drawLine(posX[view_val]-ci01.getWidth()+5, posY_t1[view_val]-ci01.getHeight()/2,posX[view_val]-ci01.getWidth()+25, posY_t1[view_val]-ci01.getHeight()/2, pnt3);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin[duration-1-view_val]/tmp_dig_f2)), posX[view_val]-ci01.getWidth()+5, posY_t1[view_val]-ci01.getHeight()/2, B_18);
							
							canvas.drawBitmap(ci02, posX[view_val]-ci02.getWidth(), posY[view_val]-ci02.getHeight()/2, pnt);
//							canvas.drawLine(posX[view_val]-ci02.getWidth()+5, posY[view_val]+7,posX[view_val]-ci02.getWidth()+25, posY[view_val]+7, pnt4);
							canvas.drawText(Long.toString(tmp_origin[duration-1-view_val]/tmp_dig), posX[view_val]-ci02.getWidth()+5, posY[view_val]+7, B_18);
						}else{
							canvas.drawBitmap(ci01, posX[view_val]-ci01.getWidth(), posY[view_val]-ci01.getHeight(), pnt);
//							canvas.drawLine(posX[view_val]-ci01.getWidth()+5, posY[view_val]-ci01.getHeight()/2,posX[view_val]-ci01.getWidth()+25, posY[view_val]-ci01.getHeight()/2, pnt4);
							canvas.drawText(Long.toString(tmp_origin[duration-1-view_val]/tmp_dig), posX[view_val]-ci01.getWidth()+5, posY[view_val]-ci01.getHeight()/2, B_18);
							
							canvas.drawBitmap(ci02, posX[view_val]-ci02.getWidth(), posY_t1[view_val]-ci02.getHeight()/2, pnt);
//							canvas.drawLine(posX[view_val]-ci02.getWidth()+5, posY_t1[view_val]+7,posX[view_val]-ci02.getWidth()+25, posY_t1[view_val]+7, pnt3);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin[duration-1-view_val]/tmp_dig_f2)), posX[view_val]-ci02.getWidth()+5, posY_t1[view_val]+7, B_18);
						}
					}
					
				}else if(GV.getSRV() == 2){
////////////////////////////////////////////view 3 begin
//					duration = GV.getDuration()-3;
					
//					canvas.drawText("[ " + GV.getStockD().m_Kname + " ]", 20, 70, W_24);
					canvas.drawText("�����&��������&������", 214*c_width, title_position, W_24);

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
					
					canvas.drawLine(b_x, c_y - (t_i*2), b_x+l_x, c_y - (t_i*2), pnt4);
					canvas.drawCircle(c_x, c_y - (t_i*2), r_c, pnt4);
					canvas.drawCircle(c_x, c_y - (t_i*2), r_c -1, W_24);
//					canvas.drawText("���� ����"+uWon1, c_x + 10, c_y - (t_i*2) + 10, B_24);
					
					canvas.drawLine(b_x, c_y - t_i, b_x+l_x, c_y - t_i, pnt3);
					canvas.drawCircle(c_x, c_y - t_i, r_c, pnt3);
					canvas.drawCircle(c_x, c_y - t_i, r_c -1, W_24);
//					canvas.drawText("���� ����"+uWon2, c_x + 10, c_y - t_i + 10, B_24);
					
					canvas.drawLine(b_x, c_y, b_x+l_x, c_y, pnt5);
					canvas.drawCircle(c_x, c_y, r_c, pnt5);
					canvas.drawCircle(c_x, c_y, r_c -1, W_24);
//					canvas.drawText("������"+uWon2, c_x + 10, c_y + 10, B_24);
					
					// �ְ�		 
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
//*// �ְ� ���� �߼�					
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
//*/// �ְ� ���� �߼�
					
					// �����
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
						uWon1 = tmp_D.uWon;
						digit_count = tmp_D.dCount;
						v_max1 = tmp_D.max;
						v_min1 = tmp_D.min;
						v1 = tmp_D.v1;
						v2 = tmp_D.v2;
						v3 = tmp_D.v3;
						v4 = tmp_D.v4;
						
						canvas.drawText("�����"+uWon1 + " ��", c_x + 10, c_y - (t_i*2) + 10, B_24);
						
						canvas.drawText(v_max1, 5, height*0.26f, W_18);
						canvas.drawText(v4, 5, height*0.35f, W_18);
						canvas.drawText(v3, 5, height*0.45f, W_18);
						canvas.drawText(v2, 5, height*0.56f, W_18);
						canvas.drawText(v1, 5, height*0.66f, W_18);
						canvas.drawText(v_min1, 5, height*0.77f, W_18);
					
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
					// ��������
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
							canvas.drawLine(posX[dr], posY[dr], posX[dr+1], posY[dr+1], pnt3);
					
					
					if(dis_ori == 1 || dis_ori == 3)
						for(int dr = 0; dr < duration; dr++)
							if(posX[dr] > width*0.067f){
							canvas.drawCircle(posX[dr], posY[dr], 4, pnt3);
							canvas.drawCircle(posX[dr], posY[dr], 3, W_24);
						}
					
					
					// ������
					//*   		 
//					sorter.sort(tmp_sort3, duration);
					
//					tmp_max = tmp_sort3[duration-1];
//					tmp_min = tmp_sort3[0];
					
					for(int dy = 0; dy<duration ; dy++){
						posY[dy] = height*(float)0.75 -(float)(tmp_origin3[duration - dy -1]-tmp_min)/(float)(tmp_max-tmp_min)*height*(float)0.5;
					}
					//*/
					for(int dr = 0; dr < duration-1; dr++)
						if(posX[dr] > width*0.067f)
							canvas.drawLine(posX[dr], posY[dr], posX[dr+1], posY[dr+1], pnt5);
					
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
						
						canvas.drawText("���� ����"+uWon2+" ��", c_x + 10, c_y - t_i + 10, B_24);
						canvas.drawText("������"+uWon2+" ��", c_x + 10, c_y + 10, B_24);
						
						canvas.drawText(v_max1, width-2 - v_max1.length()*11*c_height, height*0.26f, W_18);
						canvas.drawText(v4, width-2 - v_max1.length()*11*c_height, height*0.35f, W_18);
						canvas.drawText(v3, width-2 - v_max1.length()*11*c_height, height*0.45f, W_18);
						canvas.drawText(v2, width-2- v_max1.length()*11*c_height, height*0.56f, W_18);
						canvas.drawText(v1, width-2- v_max1.length()*11*c_height, height*0.66f, W_18);
						canvas.drawText(v_min1, width-2 - v_max1.length()*11*c_height, height*0.77f, W_18);
					
						for(int dr = 0; dr < duration; dr++)
							if(posX[dr] > width*0.067f){
							canvas.drawCircle(posX[dr], posY[dr], 4, pnt5);
							canvas.drawCircle(posX[dr], posY[dr], 3, W_24);
						}
					}
					
					if(view_val > 0){
						tmp_dig2 = 1l;
						for(int xx = 0; xx < digit_count; xx++){
							tmp_dig2 *=10l;
						}
					}
					
					if(view_val >= 0 && view_val < 15){
						if(posY[view_val]>posY_t1[view_val]){
							if(posY_t1[view_val]>posY_t2[view_val]){
								canvas.drawBitmap(c01, posX[view_val], posY_t2[view_val]-c01.getHeight(), pnt);
								canvas.drawText(Long.toString(tmp_origin2[duration-1-view_val]/tmp_dig2), posX[view_val]+7*c_height, posY_t2[view_val]-c01.getHeight()/2, B_18);
								
								canvas.drawBitmap(c02, posX[view_val], posY_t1[view_val]-c02.getHeight()/2, pnt);
								canvas.drawText(Long.toString(tmp_origin[duration-1-view_val]/tmp_dig), posX[view_val]+17*c_height, posY_t1[view_val]+7, B_18);
								
								canvas.drawBitmap(c03, posX[view_val], posY[view_val], pnt);
								canvas.drawText(Long.toString(tmp_origin3[duration-1-view_val]/tmp_dig2), posX[view_val]+7*c_height, posY[view_val]+c03.getHeight()-3, B_18);
							}else{
								if(posY[view_val]>posY_t2[view_val]){
									canvas.drawBitmap(c01, posX[view_val], posY_t1[view_val]-c01.getHeight(), pnt);
									canvas.drawText(Long.toString(tmp_origin[duration-1-view_val]/tmp_dig), posX[view_val]+7*c_height, posY_t1[view_val]-c01.getHeight()/2, B_18);
									
									canvas.drawBitmap(c02, posX[view_val], posY_t2[view_val]-c02.getHeight()/2, pnt);
									canvas.drawText(Long.toString(tmp_origin2[duration-1-view_val]/tmp_dig2), posX[view_val]+17*c_height, posY_t2[view_val]+7, B_18);
									
									canvas.drawBitmap(c03, posX[view_val], posY[view_val], pnt);
									canvas.drawText(Long.toString(tmp_origin3[duration-1-view_val]/tmp_dig2), posX[view_val]+7*c_height, posY[view_val]+c03.getHeight()-3, B_18);
								}else{
									canvas.drawBitmap(c01, posX[view_val], posY_t1[view_val]-c01.getHeight(), pnt);
									canvas.drawText(Long.toString(tmp_origin[duration-1-view_val]/tmp_dig), posX[view_val]+7*c_height, posY_t1[view_val]-c01.getHeight()/2, B_18);
									
									canvas.drawBitmap(c02, posX[view_val], posY[view_val]-c02.getHeight()/2, pnt);
									canvas.drawText(Long.toString(tmp_origin3[duration-1-view_val]/tmp_dig2), posX[view_val]+17*c_height, posY[view_val]+7, B_18);
									
									canvas.drawBitmap(c03, posX[view_val], posY_t2[view_val], pnt);
									canvas.drawText(Long.toString(tmp_origin2[duration-1-view_val]/tmp_dig2), posX[view_val]+7*c_height, posY_t2[view_val]+c03.getHeight()-3, B_18);
								}
							}
						}else{
							if(posY[view_val]>posY_t2[view_val]){
								canvas.drawBitmap(c01, posX[view_val], posY_t2[view_val]-c01.getHeight(), pnt);
								canvas.drawText(Long.toString(tmp_origin2[duration-1-view_val]/tmp_dig2), posX[view_val]+7*c_height, posY_t2[view_val]-c01.getHeight()/2, B_18);
								
								canvas.drawBitmap(c02, posX[view_val], posY[view_val]-c02.getHeight()/2, pnt);
								canvas.drawText(Long.toString(tmp_origin3[duration-1-view_val]/tmp_dig2), posX[view_val]+17*c_height, posY[view_val]+7, B_18);
								
								canvas.drawBitmap(c03, posX[view_val], posY_t1[view_val], pnt);
								canvas.drawText(Long.toString(tmp_origin[duration-1-view_val]/tmp_dig), posX[view_val]+7*c_height, posY_t1[view_val]+c03.getHeight()-3, B_18);
							}else{
								if(posY_t1[view_val]>posY_t2[view_val]){
									canvas.drawBitmap(c01, posX[view_val], posY[view_val]-c01.getHeight(), pnt);
									canvas.drawText(Long.toString(tmp_origin3[duration-1-view_val]/tmp_dig2), posX[view_val]+7*c_height, posY[view_val]-c01.getHeight()/2, B_18);
									
									canvas.drawBitmap(c02, posX[view_val], posY_t2[view_val]-c02.getHeight()/2, pnt);
									canvas.drawText(Long.toString(tmp_origin2[duration-1-view_val]/tmp_dig2), posX[view_val]+17*c_height, posY_t2[view_val]+7, B_18);
									
									canvas.drawBitmap(c03, posX[view_val], posY_t1[view_val], pnt);
									canvas.drawText(Long.toString(tmp_origin[duration-1-view_val]/tmp_dig), posX[view_val]+7*c_height, posY_t1[view_val]+c03.getHeight()-3, B_18);
								}else{
									canvas.drawBitmap(c01, posX[view_val], posY[view_val]-c01.getHeight(), pnt);
									canvas.drawText(Long.toString(tmp_origin3[duration-1-view_val]/tmp_dig2), posX[view_val]+7*c_height, posY[view_val]-c01.getHeight()/2, B_18);
									
									canvas.drawBitmap(c02, posX[view_val], posY_t1[view_val]-c02.getHeight()/2, pnt);
									canvas.drawText(Long.toString(tmp_origin[duration-1-view_val]/tmp_dig), posX[view_val]+17*c_height, posY_t1[view_val]+7, B_18);
									
									canvas.drawBitmap(c03, posX[view_val], posY_t2[view_val], pnt);
									canvas.drawText(Long.toString(tmp_origin2[duration-1-view_val]/tmp_dig2), posX[view_val]+7*c_height, posY_t2[view_val]+c03.getHeight()-3, B_18);
								}
							}
						}
					}else if(view_val>=15 && view_val < duration){
						if(posY[view_val]>posY_t1[view_val]){
							if(posY_t1[view_val]>posY_t2[view_val]){
								canvas.drawBitmap(ci01, posX[view_val]-ci01.getWidth(), posY_t2[view_val]-ci01.getHeight(), pnt);
								canvas.drawText(Long.toString(tmp_origin2[duration-1-view_val]/tmp_dig2), posX[view_val]-ci01.getWidth()+5, posY_t2[view_val]-ci01.getHeight()/2, B_18);
								
								canvas.drawBitmap(ci02, posX[view_val]-ci02.getWidth(), posY_t1[view_val]-ci02.getHeight()/2, pnt);
								canvas.drawText(Long.toString(tmp_origin[duration-1-view_val]/tmp_dig), posX[view_val]-ci02.getWidth()+5, posY_t1[view_val]+7, B_18);
								
								canvas.drawBitmap(ci03, posX[view_val]-ci03.getWidth(), posY[view_val], pnt);
								canvas.drawText(Long.toString(tmp_origin3[duration-1-view_val]/tmp_dig2), posX[view_val]-ci03.getWidth()+5, posY[view_val]+ci03.getHeight()-3, B_18);
							}else{
								if(posY[view_val]>posY_t2[view_val]){
									canvas.drawBitmap(ci01, posX[view_val]-ci01.getWidth(), posY_t1[view_val]-ci01.getHeight(), pnt);
									canvas.drawText(Long.toString(tmp_origin[duration-1-view_val]/tmp_dig), posX[view_val]-ci01.getWidth()+5, posY_t1[view_val]-ci01.getHeight()/2, B_18);
									
									canvas.drawBitmap(ci02, posX[view_val]-ci02.getWidth(), posY_t2[view_val]-ci02.getHeight()/2, pnt);
									canvas.drawText(Long.toString(tmp_origin2[duration-1-view_val]/tmp_dig2), posX[view_val]-ci02.getWidth()+5, posY_t2[view_val]+7, B_18);
									
									canvas.drawBitmap(ci03, posX[view_val]-ci03.getWidth(), posY[view_val], pnt);
									canvas.drawText(Long.toString(tmp_origin3[duration-1-view_val]/tmp_dig2), posX[view_val]-ci03.getWidth()+5, posY[view_val]+ci03.getHeight()-3, B_18);
								}else{
									canvas.drawBitmap(ci01, posX[view_val]-ci01.getWidth(), posY_t1[view_val]-ci01.getHeight(), pnt);
									canvas.drawText(Long.toString(tmp_origin[duration-1-view_val]/tmp_dig), posX[view_val]-ci01.getWidth()+5, posY_t1[view_val]-ci01.getHeight()/2, B_18);
									
									canvas.drawBitmap(ci02, posX[view_val]-ci02.getWidth(), posY[view_val]-ci02.getHeight()/2, pnt);
									canvas.drawText(Long.toString(tmp_origin3[duration-1-view_val]/tmp_dig2), posX[view_val]-ci02.getWidth()+5, posY[view_val]+7, B_18);
									
									canvas.drawBitmap(ci03, posX[view_val]-ci03.getWidth(), posY_t2[view_val], pnt);
									canvas.drawText(Long.toString(tmp_origin2[duration-1-view_val]/tmp_dig2), posX[view_val]-ci03.getWidth()+5, posY_t2[view_val]+ci03.getHeight()-3, B_18);
								}
							}
						}else{
							if(posY[view_val]>posY_t2[view_val]){
								canvas.drawBitmap(ci01, posX[view_val]-ci01.getWidth(), posY_t2[view_val]-ci01.getHeight(), pnt);
								canvas.drawText(Long.toString(tmp_origin2[duration-1-view_val]/tmp_dig2), posX[view_val]-ci01.getWidth()+5, posY_t2[view_val]-ci01.getHeight()/2, B_18);
								
								canvas.drawBitmap(ci02, posX[view_val]-ci02.getWidth(), posY[view_val]-ci02.getHeight()/2, pnt);
								canvas.drawText(Long.toString(tmp_origin3[duration-1-view_val]/tmp_dig2), posX[view_val]-ci02.getWidth()+5, posY[view_val]+7, B_18);
								
								canvas.drawBitmap(ci03, posX[view_val]-ci03.getWidth(), posY_t1[view_val], pnt);
								canvas.drawText(Long.toString(tmp_origin[duration-1-view_val]/tmp_dig), posX[view_val]-ci03.getWidth()+5, posY_t1[view_val]+ci03.getHeight()-3, B_18);
							}else{
								if(posY_t1[view_val]>posY_t2[view_val]){
									canvas.drawBitmap(ci01, posX[view_val]-ci01.getWidth(), posY[view_val]-ci01.getHeight(), pnt);
									canvas.drawText(Long.toString(tmp_origin3[duration-1-view_val]/tmp_dig2), posX[view_val]-ci01.getWidth()+5, posY[view_val]-ci01.getHeight()/2, B_18);
									
									canvas.drawBitmap(ci02, posX[view_val]-ci02.getWidth(), posY_t2[view_val]-ci02.getHeight()/2, pnt);
									canvas.drawText(Long.toString(tmp_origin2[duration-1-view_val]/tmp_dig2), posX[view_val]-ci02.getWidth()+5, posY_t2[view_val]+7, B_18);
									
									canvas.drawBitmap(ci03, posX[view_val]-ci03.getWidth(), posY_t1[view_val], pnt);
									canvas.drawText(Long.toString(tmp_origin[duration-1-view_val]/tmp_dig), posX[view_val]-ci03.getWidth()+5, posY_t1[view_val]+ci03.getHeight()-3, B_18);
								}else{
									canvas.drawBitmap(ci01, posX[view_val]-ci01.getWidth(), posY[view_val]-ci01.getHeight(), pnt);
									canvas.drawText(Long.toString(tmp_origin3[duration-1-view_val]/tmp_dig2), posX[view_val]-ci01.getWidth()+5, posY[view_val]-ci01.getHeight()/2, B_18);
									
									canvas.drawBitmap(ci02, posX[view_val]-ci02.getWidth(), posY_t1[view_val]-ci02.getHeight()/2, pnt);
									canvas.drawText(Long.toString(tmp_origin[duration-1-view_val]/tmp_dig), posX[view_val]-ci02.getWidth()+5, posY_t1[view_val]+7, B_18);
									
									canvas.drawBitmap(ci03, posX[view_val]-ci03.getWidth(), posY_t2[view_val], pnt);
									canvas.drawText(Long.toString(tmp_origin2[duration-1-view_val]/tmp_dig2), posX[view_val]-ci03.getWidth()+5, posY_t2[view_val]+ci03.getHeight()-3, B_18);
								}
							}
						}
					}
				}else if(GV.getSRV() == 3){
//////////////////////////////////////////////view 4 begin
/*					
					float[] f_sort = new float[duration];
			    	float[] f_sort2 = new float[duration];
			    	float[] f_origin = new float[duration];
			    	float[] f_origin2 = new float[duration];
*/					
//					canvas.drawText("[ " + GV.getStockD().m_Kname + " ]", 20, 70, W_24);
					canvas.drawText("�������ͷ� & �����ͷ�", 214*c_width, title_position, W_24);

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
						if(dx < duration-1 && GV.getStockD().m_m17[dx+1]==0 && GV.getStockD().m_quarter[dx]%4 == 0)
							f_sort[dx] = f_origin[dx] = 0;
						else
							f_sort[dx] = f_origin[dx] = GV.getStockD().m_m17[dx];
						
						if(dx < duration-1 && GV.getStockD().m_m18[dx+1]==0 && GV.getStockD().m_quarter[dx]%4 == 0)
							f_sort2[dx] = f_origin2[dx] = 0;
						else
							f_sort2[dx] = f_origin2[dx] = GV.getStockD().m_m18[dx];
					}
					
					canvas.drawLine(b_x, c_y - t_i, b_x+l_x, c_y - t_i, pnt3);
					canvas.drawCircle(c_x, c_y - t_i, r_c, pnt3);
					canvas.drawCircle(c_x, c_y - t_i, r_c -1, W_24);
//					canvas.drawText("�������ͷ�"+uPer+" ��", c_x + 10, c_y - t_i + 10, B_24);
					
					canvas.drawLine(b_x, c_y, b_x+l_x, c_y, pnt5);
					canvas.drawCircle(c_x, c_y, r_c, pnt5);
					canvas.drawCircle(c_x, c_y, r_c -1, W_24);
//					canvas.drawText("�����ͷ�"+uPer+" ��", c_x + 10, c_y + 10, B_24);
					
					// �ְ�		 
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
//*// �ְ� ���� �߼�					
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
//*/// �ְ� ���� �߼�
					// ���� ���� o1/o3
					//*   		
					sorter.sort(f_sort, duration);
					
					f_max = f_sort[duration-1];
					f_min = f_sort[0];
					
					for(int dy = 0; dy<duration ; dy++){
						posY_t1[dy] = posY[dy] = height*(float)0.75 -(f_origin[duration - dy -1]-f_min)/(f_max-f_min)*height*(float)0.5;
					}
					
					//*/
					for(int dr = 0; dr < duration-1; dr++)
						if(posX[dr] > width*0.067f)
							canvas.drawLine(posX[dr], posY[dr], posX[dr+1], posY[dr+1], pnt3);
					
					if(dis_ori == 1 || dis_ori == 3){
/*
						v_max1 = String.format("%.2f", Float.valueOf(f_max));//(int)(f_max)+"";
						v_min1 = String.format("%.2f", Float.valueOf(f_min));//(int)(f_min)+"";
						
						v1 = String.format("%.2f", Float.valueOf(f_min+(f_max-f_min)/5));//(int)(f_min+(f_max-f_min)/5)+"";
						v2 = String.format("%.2f", Float.valueOf(f_min+(f_max-f_min)*2/5));//(int)(f_min+(f_max-f_min)*2/5)+"";
						v3 = String.format("%.2f", Float.valueOf(f_min+(f_max-f_min)*3/5));//(int)(f_min+(f_max-f_min)*3/5)+"";
						v4 = String.format("%.2f", Float.valueOf(f_min+(f_max-f_min)*4/5));//(int)(f_min+(f_max-f_min)*4/5)+"";
//*/
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
						
						canvas.drawText("�������ͷ�"+uPer+" ��", c_x + 10, c_y - t_i + 10, B_24);
						
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
					}else
						canvas.drawText("�������ͷ�", c_x + 10, c_y - t_i + 10, B_24);
					
					// �� ���� o2/o3
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
							canvas.drawLine(posX[dr], posY[dr], posX[dr+1], posY[dr+1], pnt5);
					
					if(dis_ori == 1 || dis_ori == 3){
/*						
						v_max1 = String.format("%.2f", Float.valueOf(f_max));//(int)(f_max)+"";
						v_min1 = String.format("%.2f", Float.valueOf(f_min));//(int)(f_min)+"";

						v1 = String.format("%.2f", Float.valueOf(f_min+(f_max-f_min)/5));//(int)(f_min+(f_max-f_min)/5)+"";
						v2 = String.format("%.2f", Float.valueOf(f_min+(f_max-f_min)*2/5));//(int)(f_min+(f_max-f_min)*2/5)+"";
						v3 = String.format("%.2f", Float.valueOf(f_min+(f_max-f_min)*3/5));//(int)(f_min+(f_max-f_min)*3/5)+"";
						v4 = String.format("%.2f", Float.valueOf(f_min+(f_max-f_min)*4/5));//(int)(f_min+(f_max-f_min)*4/5)+"";
//*/
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
						
						canvas.drawText("�����ͷ�"+uPer+" ��", c_x + 10, c_y + 10, B_24);
						
						canvas.drawText(v_max1, width-2 - v_max1.length()*11*c_height, height*0.26f, W_18);
						canvas.drawText(v4, width-2 - v_max1.length()*11*c_height, height*0.35f, W_18);
						canvas.drawText(v3, width-2 - v_max1.length()*11*c_height, height*0.45f, W_18);
						canvas.drawText(v2, width-2- v_max1.length()*11*c_height, height*0.56f, W_18);
						canvas.drawText(v1, width-2- v_max1.length()*11*c_height, height*0.66f, W_18);
						canvas.drawText(v_min1, width-2 - v_max1.length()*11*c_height, height*0.77f, W_18);
					
						for(int dr = 0; dr < duration; dr++)
							if(posX[dr] > width*0.067f){
							canvas.drawCircle(posX[dr], posY[dr], 4, pnt5);
							canvas.drawCircle(posX[dr], posY[dr], 3, W_24);
						}
					}
					
					if(view_val >= 0 && view_val < 15){
						if(posY[view_val]<posY_t1[view_val]){
							canvas.drawBitmap(c01, posX[view_val], posY[view_val]-c01.getHeight(), pnt);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin2[duration-1-view_val])), posX[view_val]+7*c_height, posY[view_val]-c01.getHeight()/2, B_18);
	
							canvas.drawBitmap(c02, posX[view_val], posY_t1[view_val]-c02.getHeight()/2, pnt);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin[duration-1-view_val])), posX[view_val]+17*c_height, posY_t1[view_val]+7, B_18);
						}else{
							canvas.drawBitmap(c01, posX[view_val], posY_t1[view_val]-c01.getHeight(), pnt);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin[duration-1-view_val])), posX[view_val]+7*c_height, posY_t1[view_val]-c01.getHeight()/2, B_18);
							
							canvas.drawBitmap(c02, posX[view_val], posY[view_val]-c02.getHeight()/2, pnt);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin2[duration-1-view_val])), posX[view_val]+17*c_height, posY[view_val]+7, B_18);
						}
					}else if(view_val>=15 && view_val < duration){
						if(posY[view_val]<posY_t1[view_val]){
							canvas.drawBitmap(ci01, posX[view_val]-ci01.getWidth(), posY[view_val]-c01.getHeight(), pnt);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin2[duration-1-view_val])), posX[view_val]-ci01.getWidth()+5, posY[view_val]-ci01.getHeight()/2, B_18);
							
							canvas.drawBitmap(ci02, posX[view_val]-ci02.getWidth(), posY_t1[view_val]-ci02.getHeight()/2, pnt);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin[duration-1-view_val])), posX[view_val]-ci02.getWidth()+5, posY_t1[view_val]+7, B_18);
						}else{
							canvas.drawBitmap(ci01, posX[view_val]-ci01.getWidth(), posY_t1[view_val]-ci01.getHeight(), pnt);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin[duration-1-view_val])), posX[view_val]-ci01.getWidth()+5, posY_t1[view_val]-ci01.getHeight()/2, B_18);
							
							canvas.drawBitmap(ci02, posX[view_val]-ci02.getWidth(), posY[view_val]-ci02.getHeight()/2, pnt);
							canvas.drawText(String.format("%.2f", Float.valueOf(f_origin2[duration-1-view_val])), posX[view_val]-ci02.getWidth()+5, posY[view_val]+7, B_18);
						}
					}

				}else if(GV.getSRV() == 4){
//////////////////////////////////////////////view 5 begin
//					canvas.drawText("[ " + GV.getStockD().m_Kname + " ]", 20, 70, W_24);
					canvas.drawText("�����ܼ��ͷ�", 214*c_width, title_position, W_24);
/*					
					float[] f_sort = new float[duration];
			    	float[] f_origin = new float[duration];
*/
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
						if(GV.getStockD().m_m12[dx] == 0)
							f_sort[dx] = f_origin[dx] = 0;
						else
							f_sort[dx] = f_origin[dx] = (float)(GV.getStockD().m_m15[dx]-GV.getStockD().m_m16[dx])/(float)(GV.getStockD().m_m12[dx]);
					}
					
					canvas.drawLine(b_x, c_y, b_x+l_x, c_y, pnt3);
					canvas.drawCircle(c_x, c_y, r_c, pnt3);
					canvas.drawCircle(c_x, c_y, r_c -1, W_24);
//					canvas.drawText("�����ܼ���"+uPer, c_x + 10, c_y + 10, B_24);
					
					// �ְ�		 
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
//*// �ְ� ���� �߼�					
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
//*/// �ְ� ���� �߼�
					// �����ܼ���
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
						
/*						v_max1 = String.format("%.2f", Float.valueOf(f_max*100));
						v_min1 = String.format("%.2f", Float.valueOf(f_min*100));
						
						v1 = String.format("%.2f", Float.valueOf(f_min*100+(f_max-f_min)*100/5));
						v2 = String.format("%.2f", Float.valueOf(f_min*100+(f_max-f_min)*100*2/5));
						v3 = String.format("%.2f", Float.valueOf(f_min*100+(f_max-f_min)*100*3/5));
						v4 = String.format("%.2f", Float.valueOf(f_min*100+(f_max-f_min)*100*4/5));
//*/
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
						
						canvas.drawText("�����ܼ��� (%)", c_x + 10, c_y + 10, B_24);
					
						for(int dr = 0; dr < duration; dr++)
							if(posX[dr] > width*0.067f){
							canvas.drawCircle(posX[dr], posY[dr], 4, pnt3);
							canvas.drawCircle(posX[dr], posY[dr], 3, W_24);
						}
					}
					
					if(view_val >= 0 && view_val < 15){
						canvas.drawBitmap(c01, posX[view_val], posY[view_val]-c01.getHeight(), pnt);
						canvas.drawText(String.format("%.2f", Float.valueOf(f_origin[duration-1-view_val]*100.0f)), posX[view_val]+7*c_height, posY[view_val]-c01.getHeight()/2, B_18);
					}else if(view_val>=15 && view_val < duration){
						canvas.drawBitmap(ci01, posX[view_val]-ci01.getWidth(), posY[view_val]-ci01.getHeight(), pnt);
						canvas.drawText(String.format("%.2f", Float.valueOf(f_origin[duration-1-view_val]*100.0f)), posX[view_val]-ci01.getWidth()+7, posY[view_val]-c01.getHeight()/2, B_18);
					}
				}
				
    			canvas.drawBitmap(map006, 60*c_width, 115*c_height, pnt);
//				canvas.drawText("��Ʈ ���� ����", width - 200, height-20, pnt2);
    			
			}
    		Bitmap Icon_m = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.p);
    		
    		if(sub_menu == 1){
    			canvas.drawRect(200*c_width, title_position-29*c_height, width-20*c_width, title_position+270*c_height, _Drawable2.getPaint());
    			canvas.drawText("�б� EPS", 214*c_width, title_position, W_24);
    			canvas.drawText("�б� EPS & �����", 214*c_width, title_position+50*c_height, W_24);
    			canvas.drawText("����� & �������� & ������", 214*c_width, title_position+100*c_height, W_24);
    			canvas.drawText("�������ͷ� & �����ͷ�", 214*c_width, title_position+150*c_height, W_24);
    			canvas.drawText("�����ܼ��ͷ�", 214*c_width, title_position+200*c_height, W_24);
    			canvas.drawBitmap(Icon_m, width-20*c_width-Icon_m.getWidth(), title_position+270*c_height-Icon_m.getHeight(), pnt);
    		}
    		
    		if(sub_menu == 2){
    			canvas.drawRect(20*c_width, title_position+13*c_height, 190*c_width, title_position+270*c_height, B_24);
    			canvas.drawRect(21*c_width, title_position+13*c_height, 189*c_width, title_position+269*c_height, W_24);
//    			_Drawable2.getPaint().setColor(0xff0071d1);
//    			canvas.drawText("���ͼ�", 34, title_position+50, _Drawable2.getPaint());
    			_Drawable2.getPaint().setColor(0xff079200);
    			_Drawable2.getPaint().setTextSize(24*c_height);
    			canvas.drawText("������", 34*c_width, title_position+50*c_height, _Drawable2.getPaint());
    			_Drawable2.getPaint().setColor(0xffFFC600);
    			canvas.drawText("��缺", 34*c_width, title_position+100*c_height, _Drawable2.getPaint());
    			_Drawable2.getPaint().setColor(0xffA90000);
    			canvas.drawText("���强", 34*c_width, title_position+150*c_height, _Drawable2.getPaint());
    			_Drawable2.getPaint().setColor(0xff5B00A9);
    			canvas.drawText("ȿ����", 34*c_width, title_position+200*c_height, _Drawable2.getPaint());
    			_Drawable2.getPaint().setColor(0xffA4A4A4);
    			canvas.drawText("��ġ��", 34*c_width, title_position+250*c_height, _Drawable2.getPaint());
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
//			    Cmenu1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(Cmenu1);
				finish();
			}
		}
		return false;
	}
}
