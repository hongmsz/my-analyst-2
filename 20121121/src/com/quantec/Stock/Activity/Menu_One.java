/*
 * 돈을 잘 버는 회사인가?
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
    
	extern_view test;
    
    //////////////////////////////////////////////
    
    float x1, y1, x2, y2;
    int tmp_v;
    int duration;
    
//	long[] tmp_002;
    //////////////////////////////////////////////
    
    int fix_dis = 0;
    AlertDialog.Builder bld;
//    String mTitle = "수익성";
    
    String exp1 = "[분기 EPS]\n" +
    		"* 1주당 이익을 얼마 창출하였느냐를 나타내는 지표\n\n" +
    		"[지표분석방법]\n" +
    		"* 일반적으로 EPS가 높다는것은 그만큼 경영실적이 양호하다는 뜻이며, 배당여력도 많으므로 주가에 긍정적인 영향을 미친다.\n\n" +
    		"[투자포인트]\n" +
    		"* 이익은 증가하고 있으나 주가는 횡보하거나 오히려 하락하는 경우 장기 매수 기회.";
    String exp2 = "[분기 EPS & 매출액]\n" +
    		"* 1주당 이익을 얼마 창출하였느냐와 현재 분기의 매출액을 나타내는 지표\n\n" +
    		"[지표분석방법]\n" +
    		"* 두 지수의 기울기가 우상향 하면 매출과 순이익 성장률이 높아 지고 있다는 것을 의미\n\n" +
    		"[투자포인트]\n" +
    		"* 상당기간 차트가 우상향 하는 기조를 가진 종목에 투자";
    String exp3 = "[매출액 & 영업이익 & 순이익]\n" +
    		"* 기업의 사업 실적을 나타내는 지표.\n\n" +
    		"[투자포인트]\n" +
    		"* 상당기간 차트가 우상향하는 기조를 가진 종목에 투자";
    String exp4 = "[영업이익률 & 순이익률]\n" +
    		"영업이익률 = 영업이익 / 매출액 * 100\n" +
    		"(20% 이상 양호, 10% 이하 불량)\n" +
    		" * 기업의 주된 영업활동에 대한 성과를 분석하기 위한 지표\n" +
    		"순이익률 = 순이익 /  매출액 * 100\n" +
    		"(5% 이상 양호, 2% 이하 불량)\n" +
    		" * 제품이나 상품의 최종적인 수익력을 측정하는 지표\n\n" +
    		"[지표분석방법]\n" +
    		"* 영업이익률은 회사가 본업에서 얼마나 많은 이익을 남기는지 보여주는 가장 중요한 수익성 지표 중 하나\n" +
    		"* 순이익률이 증가하면 주주에게 돌아가는 이익이 커지므로 투자매력도가 높아짐\n\n" +
    		"[투자포인트]\n" +
    		"* 일반적으로 중간 산업재를 생산 판매 하는 중화학공업의 경우 매출액에서 차지하는 판매관리비율이 낮기 때문에 영업이익률이 높으나, 소비자들에게 직접 판매하는 제약,화장품, 제조업등은 판매 관리비가 높기때문에 영업이익률이 낮음\n" +
    		"* 일반적으로 순이익률이 높다는 것은 회사의 마진율이 높다는 것을 뜻하나 그 이유가 투자자산 처분등의 비경상적인 이익의 발생으로 인한 것일 경우 주의\n";
    String exp5 = "[영업외손익률]\n" +
    		"영업외손익률 = (영업외수익 - 영업외비용) / 매출액 * 100\n" +
    		"(10%이상 양호, 5%이하 불량)\n" +
    		" * 기업의 주된 영업활동에서 생기는 수익 이외의 수익을 나타내는 지표\n\n" +
    		"[지표분석방법]\n" +
    		"* 기업의 주된 영업활동 뿐만 아니라 재무 활동에서 발생한 경영성과를 동시에 분석 할 수 있는 지표\n\n" +
    		"[투자포인트]\n" +
    		"* 영업외손익증가는 일시적인 경우가 많다. 따라서 영업외손익 발생 이유를 확인하면, 이러한 외부상황이 바뀔 경우 큰 폭의 순이익 상승이 가능해 주가 상승이 크게 나타날 수 있다.";
	public void onCreate(Bundle savedInstanceState) {
		
		GV = (MyApp) getApplication();
		
		GV.setDisplay(((WindowManager)this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay());
		
		test = new extern_view(this);
		

		test.t_sub_title[0] = "분기 EPS";
		test.t_sub_title[1] = "분기 EPS & 매출액";
		test.t_sub_title[2] = "매출액&영업이익&순이익";
		test.t_sub_title[3] = "영업이익률 & 순이익률";
		test.t_sub_title[4] = "영업외손익률";
		
				
		test.width = GV.getDisplay().getWidth();
		test.height= GV.getDisplay().getHeight();
		
		test.c_width = (float)test.width/D_width;
		test.c_height = (float)test.height/D_height;
		
//		dur2 = (GV.getStockCount().last_sw/100 - ((GV.getStockCount().begin_sq/100)*100 + (GV.getStockCount().begin_sq%100 + 1)*3) - 1)*4 + GV.getStockCount().last_sw%100; 
		
		if(GV.getStockCount().last_sw/10000 == GV.getStockCount().begin_sq/100){
			test.dur2 = ((GV.getStockCount().last_sw/100)%100 - ((GV.getStockCount().begin_sq%100 + 1)*3) - 1)*4 + GV.getStockCount().last_sw%100; // (begin_q/100)*100  // this_w/100
		}else{
			test.dur2 = ((GV.getStockCount().last_sw/100)%100 - ((GV.getStockCount().begin_sq%100 + 1)*3) + (GV.getStockCount().last_sw/10000 - GV.getStockCount().begin_sq/100)*12 - 1)*4 + GV.getStockCount().last_sw%100; // (begin_q/100)*100  // this_w/100
		}
		
		test.title_position = test.height/6;
		
		test.BEGIN_YEAR = (GV.getStockD().m_quarter[0])/100 - 9;
		
		super.onCreate(savedInstanceState); 
		
		Cmenu1 = new Intent(this, ContentsActivity.class);
		
//		Cmenu1 = new Intent(this, Menu_One.class);
		Cmenu2 = new Intent(this, Menu_Two.class);
		Cmenu3 = new Intent(this, Menu_Three.class);
		Cmenu4 = new Intent(this, Menu_Four.class);
		Cmenu5 = new Intent(this, Menu_Five.class);
		Cmenu6 = new Intent(this, Menu_Six.class);
		

		test.map012 = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.m1);
		test.map013 = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.sm1);
		test.map014 = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.mm1);
		
		test.Icon_m = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.p);
		
		bld = new AlertDialog.Builder(this);
		
		test.VIEW_DEPTH = MAX_VIEW_DEPTH;
		
		test.posX = new float[(GV.getDuration()+1)];
		test.posY = new float[(GV.getDuration()+1)];
		test.posY_t1 = new float[GV.getDuration()+1];
		test.posY_t2 = new float[GV.getDuration()+1];
		
		
		test.c_y = test.height *0.842f;
		
		test.b_x = test.width*0.03125f;
		test.l_x = test.width*0.02f;
		test.c_x = test.b_x + test.width*0.01f;
		test.t_i = test.height*(-0.0525f);
		
		test.tmp_sort = new long[(GV.getDuration()+1)];
		test.tmp_sort2 = new long[(GV.getDuration()+1)];
		test.tmp_origin = new long[(GV.getDuration()+1)];
		test.tmp_origin2 = new long[(GV.getDuration()+1)];
		test.tmp_sort3 = new long[(GV.getDuration()+1)];
		test.tmp_origin3 = new long[(GV.getDuration()+1)];
				
		test.tmp_sort_sv = new int[(GV.getDuration()+1)*12];
		test.tmp_origin_sv = new int[(GV.getDuration()+1)*12];
		
		test.posX_sv = new float[(GV.getDuration()+1)*12];
		test.posY_sv = new float[(GV.getDuration()+1)*12];
		test.f_slong = new float[(GV.getDuration()+1)*12];
		test.f_sshort = new float[(GV.getDuration()+1)*12];
		
		test.f_sort = new float[GV.getDuration()];
		test.f_origin = new float[GV.getDuration()];
		test.f_sort2 = new float[GV.getDuration()];
		test.f_origin2 = new float[GV.getDuration()];
		
//		tmp_002 = new long[GV.getDuration()];
		
		LinearLayout layout = new LinearLayout(this);
		layout.setBackgroundColor(Color.BLACK);
		
		createContentsContainer();       
		layout.addView(contentsContatiner);
        
        setContentView(layout);

	}
	
	private void createContentsContainer(){
    	
    	contentsContatiner = new LinearLayout(this);
    	
    	contentsContatiner.addView(test);
    	
    	test.setOnTouchListener(new OnTouchListener(){
        	public boolean onTouch(View arg0, MotionEvent event) {               
        		if (event.getAction() == MotionEvent.ACTION_DOWN) {
        			x1=event.getX();
        			y1=event.getY();
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
					y2=event.getY();
					if(test.sub_menu == 0){
						if( x1 > test.width*0.025f && x1 < test.width*0.235f && y1 > title_position-test.height*0.04f && y1 < title_position+test.height*0.04f){
							test.sub_menu = 2;
						}
						
						if( x1 > test.width*0.25f && x1 < test.width*0.68f && y1 > title_position-test.height*0.04f && y1 < title_position+test.height*0.04f){
							test.sub_menu = 1;
						}
						
						if( x1 < (float) (MyApp.getDisplay().getWidth()*0.90)+test.width*0.0625f && x1 > (float) (MyApp.getDisplay().getWidth()*0.90) && y1 > title_position-test.height*0.09f && y1 < title_position+test.height*0.01f){
//							GV.setStockD(null);
							finish();
						}
//*// 주가 변동 추세						
						else if( x1 < (float) (MyApp.getDisplay().getWidth()*0.80)+test.width*0.0625f && x1 > (float) (MyApp.getDisplay().getWidth()*0.80) && y1 > title_position-test.height*0.09f && y1 < title_position+test.height*0.01f){
//							GV.setStockD(null);
							if(test.macd==0)
								test.macd=1;
							else
								test.macd=0;
						}
//*///주가 변동 추세						
						else if( y1 > test.height*0.24f && y1 < test.height*0.327f && x1 > test.width*0.075f && x1 < test.width*0.231f){ //가로 차트설명 버튼 위치
							test.view_val = -1;
							switch(GV.getSRV()){
							case  0:
								bld.setMessage(exp1);
								break;
							case 1:
								bld.setMessage(exp2);
								break;
							case 2:
								bld.setMessage(exp3);
								break;
							case 3:
								bld.setMessage(exp4);
								break;
							case 4:
								bld.setMessage(exp5);
								break;
							}
							
							bld.setPositiveButton("닫기", new DialogInterface.OnClickListener() {
								
								public void onClick(DialogInterface dialog, int which) {
								}
							});
							bld.show();
						}else if( y1 > GV.getDisplay().getHeight()-test.height*0.1875f && y1 < GV.getDisplay().getHeight()-test.height*0.09375f){
							if(x1 > GV.getDisplay().getWidth()-test.width*0.125f && x1 < GV.getDisplay().getWidth()-test.width*0.05f){
								// 10	
								test.opt_dur = 0;
							}else if(x1 > GV.getDisplay().getWidth()-test.width*0.18125f && x1 < GV.getDisplay().getWidth()-test.width*0.125f){
								// 5
								test.opt_dur = 1;
							}else if(x1 > GV.getDisplay().getWidth()-test.width*0.2375f && x1 < GV.getDisplay().getWidth()-test.width*0.18125f){
								// 3
								test.opt_dur = 2;
							}
							test.view_val = -1;
						}else{
							test.view_val = -1;
							
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
								switch(test.opt_dur){
								case 0:
									if(x1 >= test.width*0.075f && x1 <= GV.getDisplay().getWidth()-test.width*0.075f){
										test.view_val = (int) ((float)(x1-test.width*0.075f /*  */+((float)(test.width-test.width*0.15f)/(float)((test.duration+1)*12-1)*test.dur2) )*39.0f/(float)(GV.getDisplay().getWidth()-test.width*0.1875f));
									}
									break;
								case 1:
									if(x1 >= test.width*0.075f && x1 <= GV.getDisplay().getWidth()-test.width*0.075f){
										test.view_val = (int) ((float)(x1-test.width*0.075f/*  */+((float)(test.width-test.width*0.15f)/(float)((test.duration+1)*12-1)*test.dur2))*19.0f/(float)(GV.getDisplay().getWidth()-test.width*0.1875f));
									}
									break;
								case 2:
									if(x1 >= test.width*0.075f && x1 <= GV.getDisplay().getWidth()-test.width*0.075f){
										test.view_val = (int) ((float)(x1-test.width*0.075f/*  */+((float)(test.width-test.width*0.15f)/(float)((test.duration+1)*12-1)*test.dur2))*11.0f/(float)(GV.getDisplay().getWidth()-test.width*0.1875f));
									}
									break;
								}
							}
						}
					}else if(test.sub_menu==1){
						test.view_val = -1;
						
						if(y1 > title_position-20*test.c_height && y1 < title_position+20*test.c_height){
							GV.setSRV(0);
							test.sub_menu = 0;
						}else if(y1 > title_position+30*test.c_height && y1 < title_position+70*test.c_height){
							GV.setSRV(1);
							test.sub_menu = 0;
						}else if(y1 > title_position+80*test.c_height && y1 < title_position+120*test.c_height){
							GV.setSRV(2);
							test.sub_menu = 0;
						}else if(y1 > title_position+130*test.c_height && y1 < title_position+170*test.c_height){
							GV.setSRV(3);
							test.sub_menu = 0;
						}else if(y1 > title_position+180*test.c_height && y1 < title_position+220*test.c_height){
							GV.setSRV(4);
							test.sub_menu = 0;
						}else{
							test.sub_menu=0;
						}
					}else{
						test.view_val = -1;
						
						if(y1 > title_position+30*test.c_height && y1 < title_position+70*test.c_height){
							GV.setSRM(2);
		           			GV.setSRV(0);
		           			test.sub_menu=0;
		           			startActivity(Cmenu2);
		           			finish();
						}else if(y1 > title_position+80*test.c_height && y1 < title_position+120*test.c_height){
							GV.setSRM(3);
		           			GV.setSRV(0);
		           			test.sub_menu=0;
		           			startActivity(Cmenu3);
		           			finish();
						}else if(y1 > title_position+130*test.c_height && y1 < title_position+170*test.c_height){
							GV.setSRM(4);
		           			GV.setSRV(0);
		           			test.sub_menu=0;
		           			startActivity(Cmenu4);
		           			finish();
						}else if(y1 > title_position+180*test.c_height && y1 < title_position+220*test.c_height){
							GV.setSRM(5);
		           			GV.setSRV(0);
		           			test.sub_menu=0;
		           			startActivity(Cmenu5);
		           			finish();
						}else if(y1 > title_position+230*test.c_height && y1 < title_position+270*test.c_height){
							GV.setSRM(6);
		           			GV.setSRV(0);
		           			test.sub_menu=0;
		           			startActivity(Cmenu6);
		           			finish();
						}else{
							test.sub_menu=0;
						}
					}
					test.invalidate();
                }
 				return true;
        }});
    }
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(event.getAction() == KeyEvent.ACTION_DOWN){
			if(keyCode == KeyEvent.KEYCODE_BACK){
				GV.setSR(0);
				GV.setSRM(0);
				GV.setSRV(0);
				test.sub_menu = 0;
			    fix_dis = 0;
//			    Cmenu1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(Cmenu1);
				finish();
			}
		}
		return false;
	}
}
