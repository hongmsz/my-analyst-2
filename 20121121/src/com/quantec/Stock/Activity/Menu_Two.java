/*
 * 안전한 회사인가?
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

public class Menu_Two extends Activity{
	MyApp GV;
	
	private LinearLayout contentsContatiner;
	
	private static final int MAX_VIEW_DEPTH = 4;
	private static final float D_width = 800f;
	private static final float D_height = 480f;
	int title_position = 80;
	private static final int digit=5;
	int BEGIN_YEAR;
    
//    testView test;
	extern_view test;
    
    Intent Cmenu1, Cmenu2, Cmenu3, Cmenu4, Cmenu5, Cmenu6;
    
    float x1, y1, x2, y2;
    int tmp_v;
    int duration;
    
    int fix_dis = 0;
    
    AlertDialog.Builder bld;
    
    String exp1 = "[부채비율 & 유동 비율]\n" +
    		"부채비율 =  (유동부채 + 비유동부채) / 자기자본 * 100\n" +
    		"(200%이하 양호, 400% 이상 불량)\n" +
    		" * 부채비율은 타인자본의 의존도를 표시하며, 경영분석에서 기업의 건전성의 정도를 나타내는 지표\n" +
    		"유동비율 = 유동자산 / 유동부채 * 100\n" +
    		"(150% 이상 양호, 100% 이하 불량)\n" +
    		" * 유동비율은 단기간 내에 갚아야 하는 부채를 단기간 내에 현금화가 가능한 자산으로 상환할 수 있는지 여부를 측정하는 지표\n\n" +
    		"[지표분석방법]\n" +
    		"* 부채비율이 높을수록 재무구조가 불건전하므로 지불 능력의 문제가 생김\n" +
    		"* 유동비율은 기업이 보유하는 지급 능력, 또는 그 신용능력을 판단하기 위하여 쓰이는 것으로 그 비율이 클수록 그만큼 기업의 재무유동성은 크다고 할 수 있음\n\n" +
    		"[투자포인트]\n" +
    		"* 부채비율은 일반적으로 100% 이하를 표준비율로 보고 있으나 경영자 입장에서는 1년이내에 상환해야 하는 단기채무에 대한 변제의 압박을 받지 않는 한 200%까지는 양호한 수준이라고 할 수 있음\n" +
    		"* 유동비율이 높을수록 회사의 지불 능력은 커지나 필요이상으로 카다는 것은 이부분만큼을 다른곳에 투자하여 수익을 올릴수 있는 기회를 상실하고 있다는 것을 의미함";
    String exp2 = "[차입금 & 차입금 비중]\n" +
    		"차입금 의존도 = (단기차입금 + 장기차입금) / 총자산 * 100\n" +
    		"(30%이하 양호, 60% 이상 불량)\n" +
    		" * 총자본 중에서 이자를 지급하기로 약정하고 조달한 차입금의 비중을 나타내는 지표\n\n" +
    		"[지표분석방법]\n" +
    		"* 차입금의존도가 높은 기업일수록 금융비용에 대한 부담이 가중되어 수익성이 저하되고, 따라서 안정성도 낮아지게 된다\n\n" +
    		"[투자포인트]\n" +
    		"* 부채 중에서도 실제 이자 비용을  지출해야 하는 차입금의 규모를 가지고 재무적 안정성을 따지는 것이 더 합리적일수 있는데 차입금 의존도는 총 차입금(사채와 유동성 장기차입금 포함)을 이용 하므로 부채비율보다 더 중요하다 할수 있음";
    String exp3 = "[이자보상배율]\n" +
    		"이자보상배율 = 영업이익 /  이자비용\n" +
    		"(2배이상 양호, 1배미만 불량)\n" +
    		" * 한해 영업이익으로 이자비용을 지불하고 남는지를 알아보는 지표. \n\n" +
    		"[지표분석방법]\n" +
    		"* 기업의 채무상환 능력을 나타내는 지표로써, 이자보상배율이 1이면 영업활동으로번 돈으로 이자를 지불하고 나면 남는 돈이 없다는 의미\n\n" +
    		"[투자포인트]\n" +
    		"* 이자보상배율이 1보다 크다는 것은 영업 활동으로 번 돈이 금융비용을 지불하고 남는다는 의미이며, 1 미만이면 영업활동에서 창출한 이익으로 금융비용조차 지불할 수 없기 때문에 잠재적 부실기업으로 볼 수 있음";
    String exp4 = "[자기자본비율]\n" +
    		"자기자본 비율 = 자기자본 / 총자산 * 100\n" +
    		"(30% 이상 양호  20% 이하 불량)\n" +
    		" * 자기자본이 차지하는 비중을 나타내는 지표로서 기업 의무구조의 건전성을 나타내는 지표\n\n" +
    		"[지표분석방법]\n" +
    		"* 자기자본은 금융비용을 부담하지 않는 자금으로써 자기자본 비율이 높을수록 기업의 안정성이 높아진다고 할 수 있음\n\n" +
    		"[투자포인트]\n" +
    		"* 일반적인 표준 비율은 50% 이상으로 보고 있음";
    
	public void onCreate(Bundle savedInstanceState) {
		
		GV = (MyApp) getApplication();
		
		GV.setDisplay(((WindowManager)this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay());
		
		test = new extern_view(this);
		
		test.t_sub_title[0]="부채비율 & 유동비율";
		test.t_sub_title[1]="차입금 & 차입금 비중";
		test.t_sub_title[2]="이자보상배율";
		test.t_sub_title[3]="자기자본비율";
					
		
		test.VIEW_DEPTH = MAX_VIEW_DEPTH;
		
		test.map012 = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.m2);
		test.map013 = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.sm2);
		test.map014 = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.mm2);
		
		test.Icon_m = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.s);
					
		
		test.width = GV.getDisplay().getWidth();
		test.height= GV.getDisplay().getHeight();
		
		test.c_width = (float)test.width/D_width;
		test.c_height = (float)test.height/D_height;		

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
		Cmenu2 = new Intent(this, Menu_One.class);
		Cmenu3 = new Intent(this, Menu_Three.class);
		Cmenu4 = new Intent(this, Menu_Four.class);
		Cmenu5 = new Intent(this, Menu_Five.class);
		Cmenu6 = new Intent(this, Menu_Six.class);
				
		bld = new AlertDialog.Builder(this);
		
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
		
		LinearLayout layout = new LinearLayout(this);
		layout.setBackgroundColor(Color.BLACK);
		
		createContentsContainer();
        layout.addView(contentsContatiner);
        
        setContentView(layout);

	}
	
	private void createContentsContainer(){
    	
    	contentsContatiner = new LinearLayout(this);
    	
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
							case 0:
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
						
						if(y1 > title_position-test.height*0.042f && y1 < title_position+test.height*0.042f){
							GV.setSRV(0);
							test.sub_menu = 0;
						}else if(y1 > title_position+test.height*0.0625f && y1 < title_position+test.height*0.1458f){
							GV.setSRV(1);
							test.sub_menu = 0;
						}else if(y1 > title_position+test.height*0.167f && y1 < title_position+test.height*0.25f){
							GV.setSRV(2);
							test.sub_menu = 0;
						}else if(y1 > title_position+test.height*0.271f && y1 < title_position+test.height*0.354f){
							GV.setSRV(3);
							test.sub_menu = 0;
						}else{
							test.sub_menu=0;
						}
					}else{
						test.view_val = -1;
						
						if(y1 > title_position+test.height*0.0625f && y1 < title_position+test.height*0.1458f){
							GV.setSRM(1);
		           			GV.setSRV(0);
		           			test.sub_menu=0;
		           			startActivity(Cmenu2);
		           			finish();
						}else if(y1 > title_position+test.height*0.167f && y1 < title_position+test.height*0.25f){
							GV.setSRM(3);
		           			GV.setSRV(0);
		           			test.sub_menu=0;
		           			startActivity(Cmenu3);
		           			finish();
						}else if(y1 > title_position+test.height*0.271f && y1 < title_position+test.height*0.354f){
							GV.setSRM(4);
		           			GV.setSRV(0);
		           			test.sub_menu=0;
		           			startActivity(Cmenu4);
		           			finish();
						}else if(y1 > title_position+test.height*0.375f && y1 < title_position+test.height*0.458f){
							GV.setSRM(5);
		           			GV.setSRV(0);
		           			test.sub_menu=0;
		           			startActivity(Cmenu5);
		           			finish();
						}else if(y1 > title_position+test.height*0.4792f && y1 < title_position+test.height*0.5625f){
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
				startActivity(Cmenu1);
				finish();
			}
		}
		return false;
	}
}
