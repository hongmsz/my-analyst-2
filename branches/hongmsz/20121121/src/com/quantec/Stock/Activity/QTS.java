package com.quantec.Stock.Activity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.quantec.Stock.MyApp;
import com.quantec.Stock.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class QTS extends Activity {
	
	
	URL aURL;
	String adImgUrl1 = "http://hongmsz.raonnet.com/AdImg/adImg1.png";;
	String adImgUrl2 = "http://hongmsz.raonnet.com/AdImg/adImg2.png";;
	String adImgUrl3 = "http://hongmsz.raonnet.com/AdImg/adImg3.png";;
	
	private boolean isTwoClickBack = false;
	
	int count = 0;
	String str ="해당 데이터가 없습니다. 다시 입력해주십시오";
	Intent Ctest, CHint;
	
	testView test;
	adView setAd;
	
	float x1, y1, x2, y2;
	int sub_menu;
	private static final float D_width = 480f;
	private static final float D_height = 800f;
	int width, height;
	float c_width, c_height;
	OptD OptRest = null;
	int lit;
	
	float menu_pos;
	float menu_dist;
	float menu_text_dist;
	
	Intent Cmenu1, Cmenu2, Cmenu3, Cmenu4, Cmenu5, Cmenu6; 
    private LinearLayout textGetContatiner;
    private LinearLayout logoContatiner;
    private LinearLayout adContatiner;
    
//    private EditText et;
    private Button _btGet, _btGet2;
    private AutoCompleteTextView et;
    ArrayAdapter adapter;
    
    MyApp GV;
    int thr = 0;
    
    float version = MyApp.getVER();
    float vc = version;
    
    int qt_count = 201101;
	int tmp_count, dr;
	
	int begin_q;// = 201201;	//옵션 메뉴 생성
	int end_q;						//옵션 메뉴 생성
	int this_w;// = 20120702;	//옵션 메뉴 생성
	int sub_menu1 = -1;	//옵션 메뉴 생성
	int sub_menu2 = -1;	//옵션 메뉴 생성
	int dur2;
	
    Color bgColor;
    Paint G_24 = new Paint();
	Paint W_30 = new Paint();
    Paint B_30 = new Paint();
    
    StockD tmp_stock = null;
    StockCount tmp_stock_count=null;
    String tmp;
    Dialog dialog;
    String op_out =String.format("%.3f", Float.valueOf(version));
    Reader rd = null;
    
    AlertDialog.Builder bld;
    AlertDialog.Builder end2;
    AlertDialog.Builder update;
	
    /** Called when the activity is first created. */
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        startActivity(new Intent(this, SplashActivity.class));
        
        GV = (MyApp) getApplication();
        GV.setDisplay(((WindowManager) this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay());
        
        width = GV.getDisplay().getWidth();
		height= GV.getDisplay().getHeight();
		
		c_width = (float)width/D_width;
		c_height = (float)height/D_height;
		
		menu_pos = 0;
 		menu_dist = GV.getDisplay().getHeight()*0.075f;
 		menu_text_dist = GV.getDisplay().getHeight()*0.01f;
        
		Ctest = new Intent(this, ContentsActivity.class);
        CHint = new Intent(this, OptView.class);
        
        Cmenu1 = new Intent(this, Menu_One.class);
		Cmenu2 = new Intent(this, Menu_Two.class);
		Cmenu3 = new Intent(this, Menu_Three.class);
		Cmenu4 = new Intent(this, Menu_Four.class);
		Cmenu5 = new Intent(this, Menu_Five.class);
		Cmenu6 = new Intent(this, Menu_Six.class);

		sub_menu = 0;
		
        update = new AlertDialog.Builder(this);
        
        createLogoContainer();
        createTextGetContainer();
        createAdContainer();
        
        LinearLayout layout = new LinearLayout(this);
        
        layout.setOrientation(LinearLayout.VERTICAL);
//        layout.setBackgroundColor(bgColor.WHITE);
        
        layout.addView(logoContatiner);
        layout.addView(textGetContatiner);
        layout.addView(adContatiner);
        
        setContentView(layout);
    }
    
    private void createTextGetContainer(){
    	textGetContatiner = new LinearLayout(this);
    	int t_height = GV.getDisplay().getHeight()/8;
    	int t_width = GV.getDisplay().getWidth()/6;
    	
    	BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(R.drawable.test_bg_draw);
    	Bitmap bitmapIMG = drawable.getBitmap();
    	
    	Drawable dBit = new BitmapDrawable(bitmapIMG);
    	
    	textGetContatiner.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, t_height));
    	textGetContatiner.setGravity(Gravity.CENTER);
    	textGetContatiner.setBackgroundColor(bgColor.BLACK);
    	textGetContatiner.setBackgroundDrawable(dBit);
    	textGetContatiner.setOrientation(LinearLayout.HORIZONTAL);
 
//    	test = new testView(this);
    	
    	
//    	et = new EditText(this);
    	et = new AutoCompleteTextView(this);
    	et.setBackgroundResource(R.drawable.etstyle);
    	et.setSingleLine(true);
    	et.setBackgroundColor(Color.WHITE);
    	et.setWidth((int)(GV.getDisplay().getWidth()-t_width*2));
    	et.setTextColor(Color.BLACK);
    	if(width>480){
    		et.setHeight((int)(GV.getDisplay().getHeight()*0.06f));
    		et.setTextSize(15);
    	}else{
    		et.setHeight((int)(GV.getDisplay().getHeight()*0.07f));
    		et.setTextSize(13);
    	}
    	et.setText("종목 명 또는 종목 코드");
    	et.setOnClickListener(on_ETinit);
    	et.setOnKeyListener(on_kETinit);
    	
    	adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, NSTOCKS);
        et.setAdapter(adapter);
    	
//        textGetContatiner.addView(test, new LinearLayout.LayoutParams(480, 50));
//    	test.animate(test);

    	textGetContatiner.addView(et);    	
    	
    	
    	_btGet2 = new Button(this);
    	_btGet2.setWidth((int)(t_width));
    	_btGet2.setHeight(t_width*4/3);
    	_btGet2.setText("");
    	_btGet2.setBackgroundResource(R.drawable.s_icon_b);
//    	_btGet2.setOnClickListener(on_Init);
    	
    	_btGet = new Button(this);
    	_btGet.setWidth(GV.getDisplay().getWidth()/9);
    	_btGet.setHeight(GV.getDisplay().getWidth()*2/9);
    	_btGet.setText("");
    	_btGet.setBackgroundResource(R.drawable.s_icon_bs);
    	_btGet.setOnClickListener(on_Init);

    	textGetContatiner.addView(_btGet2);
    	textGetContatiner.addView(_btGet);
 //layout.addView(test, new LinearLayout.LayoutParams(480, 800));   	
    	
    }
    
    private void createLogoContainer(){
    	logoContatiner = new LinearLayout(this);
    	int t_height = GV.getDisplay().getHeight()/8;
    	logoContatiner.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, t_height));
    	logoContatiner.setBackgroundColor(bgColor.BLACK);
    	   	
    	test = new testView(this);
    	
        logoContatiner.addView(test);
        
        test.setOnTouchListener(new OnTouchListener(){
    		public boolean onTouch(View arg0, MotionEvent event) {
    			if (event.getAction() == MotionEvent.ACTION_DOWN) {
    				
                }    			
    			if (event.getAction() == MotionEvent.ACTION_UP) {

        			x1=event.getX();
        			y1=event.getY();
//    				sub_menu = 0;
//    				setAd.invalidate();
    			}
    			return true;
    	}});
//    	test.animate(test);
    }
    
    private void createAdContainer(){
    	adContatiner = new LinearLayout(this);
    	final int t_height = GV.getDisplay().getHeight()/16*11;
    	adContatiner.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, t_height));
    	
    	setAd = new adView(this);
    	
    	adContatiner.addView(setAd);

    	tmp_stock_count = getSDur();
    	
    	begin_q = tmp_stock_count.begin_sq;
    	this_w = tmp_stock_count.last_sw;
		
		GV.setStockCount(tmp_stock_count);

    	if(begin_q%100 == 1){
    		end_q = (begin_q/100 - 1)*100 + 4;
    	}else
    		end_q = begin_q - 1;
    	
    	if(begin_q%100 == 4)
    		begin_q = (begin_q/100);
    	
    	if(end_q%100 == 4)
    		end_q = (end_q/100);
    	
    	setAd.setOnTouchListener(new OnTouchListener(){
    		public boolean onTouch(View arg0, MotionEvent event) {
    			if (event.getAction() == MotionEvent.ACTION_DOWN) {
        			x1=event.getX();
        			y1=event.getY();
                }
    			
    			if (event.getAction() == MotionEvent.ACTION_UP) {
//    				y2=event.getY();
    				if(sub_menu == 0){
    					if(x1 > 0 && x1 < (float)width && y1 > menu_pos && y1 < menu_pos+menu_dist){
    						sub_menu=1;
    					}
    				}else if(sub_menu==1 || sub_menu==2){
    					if(x1<(float)width*4/9 && y1 > menu_pos+menu_dist && y1 < menu_pos+menu_dist*2){
    						sub_menu=2;
    						sub_menu1 = -1;
    						sub_menu2 = 0;
                		}else if(x1<(float)width*4/9 && y1 > menu_pos+menu_dist*2 && y1 < menu_pos+menu_dist*3){
                			sub_menu=2;
    						sub_menu1 = -1;
                			sub_menu2 = 1;
                		}else if(x1<(float)width*4/9 && y1 > menu_pos+menu_dist*3 && y1 < menu_pos+menu_dist*4){
                			sub_menu=2;
    						sub_menu1 = -1;
                			sub_menu2 = 2;
                		}else if(x1<(float)width*4/9){
        					sub_menu=0;
    						sub_menu1 = -1;
        					sub_menu2 = -1;
                		}
    				}
    				
    				if(sub_menu==2){
    					if(x1>(float)width*4/9 && y1 > menu_pos+menu_dist && y1 < menu_pos+menu_dist*2){
    						sub_menu1=0;
    						if(sub_menu2==0){
    							dialog = ProgressDialog.show(QTS.this, "", "데이터 정렬 중(EPS 증가율 기준)..\n전체 보기", true, true);
    							new Thread(new Runnable() {
    								
    								public void run() {
    									OptRest = getOptValue("m11", begin_q, end_q, this_w, 10l);
    									OptRest.dType = 1;
    									GV.setOptD(OptRest);
    									dialog.dismiss();
    									startActivity(CHint);
    		        					sub_menu=0;
    		        					sub_menu1 = -1;
    		        					sub_menu2 = -1;
    								}
    							}).start();
    						}else if(sub_menu2==1){
    							dialog = ProgressDialog.show(QTS.this, "", "데이터 정렬 중(매출액 증가율 기준)..\n전체 보기", true, true);
    							new Thread(new Runnable() {
    						        
    						        public void run() {
    						        	OptRest = getOptValue("m12", begin_q, end_q, this_w, 10l);
    						        	OptRest.dType = 2;
    					    			GV.setOptD(OptRest);
    					    			dialog.dismiss();
    									startActivity(CHint);
    		        					sub_menu=0;
    		        					sub_menu1 = -1;
    		        					sub_menu2 = -1;
    						        }
    						    }).start();
    						}else if(sub_menu2==2){
    							dialog = ProgressDialog.show(QTS.this, "", "데이터 정렬 중(영업이익 증가율 기준)..\n전체 보기", true, true);
    							new Thread(new Runnable() {
    						        
    						        public void run() {
    					    			OptRest = getOptValue("m13", begin_q, end_q, this_w, 10l);
    						        	OptRest.dType = 3;
    					    			GV.setOptD(OptRest);
    					    			dialog.dismiss();
    									startActivity(CHint);
    		        					sub_menu=0;
    		        					sub_menu1 = -1;
    		        					sub_menu2 = -1;
    						        }
    						    }).start();
    						}
                		}else if(x1>(float)width*4/9 && y1 > menu_pos+menu_dist*2 && y1 < menu_pos+menu_dist*3){
                			sub_menu1=1;
                			if(sub_menu2==0){
    							dialog = ProgressDialog.show(QTS.this, "", "데이터 정렬 중(EPS 증가율 기준)..\n시가 총액 5백억 이상", true, true);
    							new Thread(new Runnable() {
    								
    								public void run() {
    									OptRest = getOptValue("m11", begin_q, end_q, this_w, 50000000000l);
    									OptRest.dType = 1;
    									GV.setOptD(OptRest);
    									dialog.dismiss();
    									startActivity(CHint);
    		        					sub_menu=0;
    		        					sub_menu1 = -1;
    		        					sub_menu2 = -1;
    								}
    							}).start();
    						}else if(sub_menu2==1){
    							dialog = ProgressDialog.show(QTS.this, "", "데이터 정렬 중(매출액 증가율 기준)..\n시가 총액 5백억 이상", true, true);
    							new Thread(new Runnable() {
    						        
    						        public void run() {
    						        	OptRest = getOptValue("m12", begin_q, end_q, this_w, 50000000000l);
    						        	OptRest.dType = 2;
    					    			GV.setOptD(OptRest);
    					    			dialog.dismiss();
    									startActivity(CHint);
    		        					sub_menu=0;
    		        					sub_menu1 = -1;
    		        					sub_menu2 = -1;
    						        }
    						    }).start();
    						}else if(sub_menu2==2){
    							dialog = ProgressDialog.show(QTS.this, "", "데이터 정렬 중(영업이익 증가율 기준)..\n시가 총액 5백억 이상", true, true);
    							new Thread(new Runnable() {
    						        
    						        public void run() {
    					    			OptRest = getOptValue("m13", begin_q, end_q, this_w, 50000000000l);
    						        	OptRest.dType = 3;
    					    			GV.setOptD(OptRest);
    					    			dialog.dismiss();
    									startActivity(CHint);
    		        					sub_menu=0;
    		        					sub_menu1 = -1;
    		        					sub_menu2 = -1;
    						        }
    						    }).start();
    						}
                		}else if(x1>(float)width*4/9 && y1 > menu_pos+menu_dist*3 && y1 < menu_pos+menu_dist*4){
                			sub_menu1=2;
                			if(sub_menu2==0){
                				dialog = ProgressDialog.show(QTS.this, "", "데이터 정렬 중(EPS 증가율 기준)..\n시가 총액 1천억 이상", true, true);
                				new Thread(new Runnable() {
                					
                					public void run() {
                						OptRest = getOptValue("m11", begin_q, end_q, this_w, 100000000000l);
                						OptRest.dType = 1;
                						GV.setOptD(OptRest);
                						dialog.dismiss();
                						startActivity(CHint);
                    					sub_menu=0;
                    					sub_menu1 = -1;
                    					sub_menu2 = -1;
                					}
                				}).start();
                			}else if(sub_menu2==1){
                				dialog = ProgressDialog.show(QTS.this, "", "데이터 정렬 중(매출액 증가율 기준)..\n시가 총액 1천억 이상", true, true);
                				new Thread(new Runnable() {
                			        
                			        public void run() {
                			        	OptRest = getOptValue("m12", begin_q, end_q, this_w, 100000000000l);
                			        	OptRest.dType = 2;
                						GV.setOptD(OptRest);
                						dialog.dismiss();
                						startActivity(CHint);
                    					sub_menu=0;
                    					sub_menu1 = -1;
                    					sub_menu2 = -1;
                			        }
                			    }).start();
                			}else if(sub_menu2==2){
                				dialog = ProgressDialog.show(QTS.this, "", "데이터 정렬 중(영업이익 증가율 기준)..\n시가 총액 1천억 이상", true, true);
                				new Thread(new Runnable() {
                			        
                			        public void run() {
                						OptRest = getOptValue("m13", begin_q, end_q, this_w, 100000000000l);
                			        	OptRest.dType = 3;
                						GV.setOptD(OptRest);
                						dialog.dismiss();
                						startActivity(CHint);
                    					sub_menu=0;
                    					sub_menu1 = -1;
                    					sub_menu2 = -1;
                			        }
                			    }).start();
                			}
                		}else if(x1>(float)width*4/9 && y1 > menu_pos+menu_dist*4 && y1 < menu_pos+menu_dist*5){
                			sub_menu1=3;
                			if(sub_menu2==0){
                				dialog = ProgressDialog.show(QTS.this, "", "데이터 정렬 중(EPS 증가율 기준)..\n시가 총액 2천억 이상", true, true);
                				new Thread(new Runnable() {
                					
                					public void run() {
                						OptRest = getOptValue("m11", begin_q, end_q, this_w, 200000000000l);
                						OptRest.dType = 1;
                						GV.setOptD(OptRest);
                						dialog.dismiss();
                						startActivity(CHint);
                    					sub_menu=0;
                    					sub_menu1 = -1;
                    					sub_menu2 = -1;
                					}
                				}).start();
                			}else if(sub_menu2==1){
                				dialog = ProgressDialog.show(QTS.this, "", "데이터 정렬 중(매출액 증가율 기준)..\n시가 총액 2천억 이상", true, true);
                				new Thread(new Runnable() {
                			        
                			        public void run() {
                			        	OptRest = getOptValue("m12", begin_q, end_q, this_w, 200000000000l);
                			        	OptRest.dType = 2;
                						GV.setOptD(OptRest);
                						dialog.dismiss();
                						startActivity(CHint);
                    					sub_menu=0;
                    					sub_menu1 = -1;
                    					sub_menu2 = -1;
                			        }
                			    }).start();
                			}else if(sub_menu2==2){
                				dialog = ProgressDialog.show(QTS.this, "", "데이터 정렬 중(영업이익 증가율 기준)..\n시가 총액 2천억 이상", true, true);
                				new Thread(new Runnable() {
                			        
                			        public void run() {
                						OptRest = getOptValue("m13", begin_q, end_q, this_w, 200000000000l);
                			        	OptRest.dType = 3;
                						GV.setOptD(OptRest);
                						dialog.dismiss();
                						startActivity(CHint);
                    					sub_menu=0;
                    					sub_menu1 = -1;
                    					sub_menu2 = -1;
                			        }
                			    }).start();
                			}
                		}else if(x1>(float)width*4/9 && y1 > menu_pos+menu_dist*5 && y1 < menu_pos+menu_dist*6){
                			sub_menu1=4;
                			if(sub_menu2==0){
                				dialog = ProgressDialog.show(QTS.this, "", "데이터 정렬 중(EPS 증가율 기준)..\n시가 총액 5천억 이상", true, true);
                				new Thread(new Runnable() {
                					
                					public void run() {
                						OptRest = getOptValue("m11", begin_q, end_q, this_w, 500000000000l);
                						OptRest.dType = 1;
                						GV.setOptD(OptRest);
                						dialog.dismiss();
                						startActivity(CHint);
                    					sub_menu=0;
                    					sub_menu1 = -1;
                    					sub_menu2 = -1;
                					}
                				}).start();
                			}else if(sub_menu2==1){
                				dialog = ProgressDialog.show(QTS.this, "", "데이터 정렬 중(매출액 증가율 기준)..\n시가 총액 5천억 이상", true, true);
                				new Thread(new Runnable() {
                			        
                			        public void run() {
                			        	OptRest = getOptValue("m12", begin_q, end_q, this_w, 500000000000l);
                			        	OptRest.dType = 2;
                						GV.setOptD(OptRest);
                						dialog.dismiss();
                						startActivity(CHint);
                    					sub_menu=0;
                    					sub_menu1 = -1;
                    					sub_menu2 = -1;
                			        }
                			    }).start();
                			}else if(sub_menu2==2){
                				dialog = ProgressDialog.show(QTS.this, "", "데이터 정렬 중(영업이익 증가율 기준)..\n시가 총액 5천억 이상", true, true);
                				new Thread(new Runnable() {
                			        
                			        public void run() {
                						OptRest = getOptValue("m13", begin_q, end_q, this_w, 500000000000l);
                			        	OptRest.dType = 3;
                						GV.setOptD(OptRest);
                						dialog.dismiss();
                						startActivity(CHint);
                    					sub_menu=0;
                    					sub_menu1 = -1;
                    					sub_menu2 = -1;
                			        }
                			    }).start();
                			}
                		}else if(x1>(float)width*4/9 && y1 > menu_pos+menu_dist*6 && y1 < menu_pos+menu_dist*7){
                			sub_menu1=5;
                			if(sub_menu2==0){
                				dialog = ProgressDialog.show(QTS.this, "", "데이터 정렬 중(EPS 증가율 기준)..\n시가 총액 1조 이상", true, true);
                				new Thread(new Runnable() {
                					
                					public void run() {
                						OptRest = getOptValue("m11", begin_q, end_q, this_w, 1000000000000l);
                						OptRest.dType = 1;
                						GV.setOptD(OptRest);
                						dialog.dismiss();
                						startActivity(CHint);
                    					sub_menu=0;
                    					sub_menu1 = -1;
                    					sub_menu2 = -1;
                					}
                				}).start();
                			}else if(sub_menu2==1){
                				dialog = ProgressDialog.show(QTS.this, "", "데이터 정렬 중(매출액 증가율 기준)..\n시가 총액 1조 이상", true, true);
                				new Thread(new Runnable() {
                			        
                			        public void run() {
                			        	OptRest = getOptValue("m12", begin_q, end_q, this_w, 1000000000000l);
                			        	OptRest.dType = 2;
                						GV.setOptD(OptRest);
                						dialog.dismiss();
                						startActivity(CHint);
                    					sub_menu=0;
                    					sub_menu1 = -1;
                    					sub_menu2 = -1;
                			        }
                			    }).start();
                			}else if(sub_menu2==2){
                				dialog = ProgressDialog.show(QTS.this, "", "데이터 정렬 중(영업이익 증가율 기준)..\n시가 총액 1조 이상", true, true);
                				new Thread(new Runnable() {
                			        
                			        public void run() {
                						OptRest = getOptValue("m13", begin_q, end_q, this_w, 1000000000000l);
                			        	OptRest.dType = 3;
                						GV.setOptD(OptRest);
                						dialog.dismiss();
                						startActivity(CHint);
                    					sub_menu=0;
                    					sub_menu1 = -1;
                    					sub_menu2 = -1;
                			        }
                			    }).start();
                			}
                		}else if(x1>(float)width*4/9){
        					sub_menu=0;
        					sub_menu1 = -1;
        					sub_menu2 = -1;
                		}
    				}
    				
    				setAd.invalidate();
    			}
    			return true;
    	}});
//        setAd.animate(setAd);
    }
    
    private View.OnClickListener on_Init = new View.OnClickListener() {
		
		public void onClick(View v) {
			
			tmp = et.getText().toString();
			GV.setGlobalString(tmp);
			tmp_stock = null;
			GV.setStockD(tmp_stock);
			
			dialog = ProgressDialog.show(QTS.this, "", MyApp.getGlobalString()+"\n정보 불러오는  중..", true, true);
			new Thread(new Runnable() {
		        
		        public void run() {
//		        	tmp_stock = getSValue(MyApp.getGlobalString());// 20111024 수정
		        	char c2 = MyApp.getGlobalString().charAt(1);// 20111024 수정
		        	
//		        	if(tmp_stock == null){// 20111024 수정
		        	if( (c2 < '0' || c2 > '9')){// 20111024 수정
						tmp = getPageSource(MyApp.getGlobalString());
						if(tmp == null){
							dialog.dismiss();
							runOnUiThread(new Runnable() {
			                    public void run() {
			                    	Toast.makeText(QTS.this, str, Toast.LENGTH_LONG).show();
			                    	et.setText("");
			                    }
			                });
						}else{				
							GV.setGlobalString(tmp);
							tmp_stock = getSValue(MyApp.getGlobalString());
							if(tmp_stock != null){
								GV.setStockD(tmp_stock);
								tmp_stock = getSValueA(MyApp.getGlobalString());
								tmp_stock = getWSValue(MyApp.getGlobalString());
								GV.setStockD(tmp_stock);
								
								if(MyApp.getSRM() == 0){
									GV.setSR(0);
									GV.setSRV(0);
									GV.setSRM(0);
									startActivity(Ctest);
								}else
									onShowMenu();
								
								dialog.dismiss();
							}else{
								dialog.dismiss();
								runOnUiThread(new Runnable() {
				                    public void run() {
				                    	Toast.makeText(QTS.this, str, Toast.LENGTH_LONG).show();
				                    	et.setText("");
				                    }
				                });
							}
						}	
					}else{
						tmp_stock = getSValue(MyApp.getGlobalString()); // 20111024 수정
						GV.setStockD(tmp_stock);
						tmp_stock = getSValueA(MyApp.getGlobalString());
						tmp_stock = getWSValue(MyApp.getGlobalString());
						GV.setStockD(tmp_stock);
					
						if(MyApp.getSRM() == 0){
							GV.setSR(0);
							GV.setSRV(0);
							GV.setSRM(0);
							startActivity(Ctest);
						}else
							onShowMenu();
						
						dialog.dismiss();
					}
		        }
		    }).start();
	        
			
		}
	};
	
	private View.OnClickListener on_ETinit = new View.OnClickListener() {
		
		public void onClick(View v) {
				et.setText("");
		}
	};
	
	private View.OnKeyListener on_kETinit = new View.OnKeyListener() {
		
		
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			
			if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
				tmp = et.getText().toString();
				GV.setGlobalString(tmp);
				tmp_stock = null;
				GV.setStockD(tmp_stock);
				
				dialog = ProgressDialog.show(QTS.this, "", "불러오는 중..", true, true);
				new Thread(new Runnable() {
			        
			        public void run() {
//			        	tmp_stock = getSValue(MyApp.getGlobalString());// 20111024 수정
			        	char c = MyApp.getGlobalString().charAt(1);// 20111024 수정
			        	
//			        	if(tmp_stock == null){// 20111024 수정
			        	if( c < '0' || c > '9'){// 20111024 수정
							tmp = getPageSource(MyApp.getGlobalString());
							if(tmp == null){
								dialog.dismiss();
								runOnUiThread(new Runnable() {
				                    public void run() {
				                    	Toast.makeText(QTS.this, str, Toast.LENGTH_LONG).show();
				                    	et.setText("");
				                    }
				                });
							}else{				
								GV.setGlobalString(tmp);
								tmp_stock = getSValue(MyApp.getGlobalString());
								if(tmp_stock != null){
									GV.setStockD(tmp_stock);
									tmp_stock = getSValueA(MyApp.getGlobalString());
									tmp_stock = getWSValue(MyApp.getGlobalString());
									GV.setStockD(tmp_stock);
									if(MyApp.getSRM() == 0){
										GV.setSR(0);
										GV.setSRV(0);
										GV.setSRM(0);
										startActivity(Ctest);
									}else
										onShowMenu();
									
									dialog.dismiss();
								}else{
									dialog.dismiss();
									runOnUiThread(new Runnable() {
					                    public void run() {
					                    	Toast.makeText(QTS.this, str, Toast.LENGTH_LONG).show();
					                    	et.setText("");
					                    }
					                });
								}
							}	
						}else{
							tmp_stock = getSValue(MyApp.getGlobalString()); // 20111024 수정
							GV.setStockD(tmp_stock);
							tmp_stock = getSValueA(MyApp.getGlobalString());
							tmp_stock = getWSValue(MyApp.getGlobalString());
							GV.setStockD(tmp_stock);
							if(MyApp.getSRM() == 0){
								GV.setSR(0);
								GV.setSRV(0);
								GV.setSRM(0);
								startActivity(Ctest);
							}else
								onShowMenu();
							
							dialog.dismiss();
						}
			        }
			    }).start();
			}
			
			return false;
		}
	};
	
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
    
    private String getPageSource( String nameString ){
    	String retVal = "";
    	
    	String resVal="";
 //   	String eUrl="";
    	String urlString;
    	
    	int SI, EI;
    	
    	try {
    		
//    		String sUrl="";
    		
//    		sUrl = urlString.substring(0, urlString.lastIndexOf("/")+1);
//    		eUrl = urlString.substring(urlString.lastIndexOf("/")+1, urlString.length()); // 한글과 공백을 포함한 부분
//    		eUrl = URLEncoder.encode(nameString,"UTF-8").replace("+", "%20");
//    		urlString = "http://search.naver.com/search.naver?where=nexearch&sm=ies_hty&query=" + eUrl;
//    		urlString = "http://hongmsz.raonnet.com/datacode/kname.xml";
    		urlString = "http://quantec.co.kr/datacode/kname.xml";
    		
    		
    		URL url = new URL( urlString );
    		URLConnection connection = url.openConnection();
    		InputStream is = connection.getInputStream();
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
/*    	
    	EI = retVal.indexOf(MyApp.getGlobalString());
		SI = retVal.indexOf("<span>(", EI);
		if(SI > 0){
			EI = retVal.indexOf(")</span>", SI);
			SI= SI+7;
//*/		
		EI = retVal.indexOf(nameString);
		SI = retVal.indexOf("<Jcode>", EI);
		if(SI > 0){
			EI = retVal.indexOf("</Jcode>", SI);
			SI= SI+7;
			resVal = retVal.substring(SI, EI);
			retVal = "";
			retVal = resVal;
			
			return retVal;
		}else
			return null;
    }
    
    
    private StockD getWSValue(String S_Code){
		StockD dataStock = MyApp.getStockD();
       
    	if(S_Code == null){
    		return null;
    	}else {
    //*    	
	        XmlPullParserFactory factory = null;
			
			try{
				qt_count = 201101;
				tmp_count = dataStock.m_quarter[0];
				qt_count = tmp_count - qt_count;
				
				dr = (qt_count%100 + (qt_count/100)*4)*12-1;
				
				if(this_w/10000 == begin_q/100){
					dur2 = ((this_w/100)%100 - ((begin_q%100 + 1)*3) - 1)*4 + this_w%100; // (begin_q/100)*100  // this_w/100
				}else{
					dur2 = ((this_w/100)%100 - ((begin_q%100 + 1)*3) + (this_w/10000 - begin_q/100)*12 - 1)*4 + this_w%100; // (begin_q/100)*100  // this_w/100
				}
				
				dr = dr + dur2;
				
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

	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(event.getAction() == KeyEvent.ACTION_DOWN){
			if(keyCode == KeyEvent.KEYCODE_BACK){
		        end2 = new AlertDialog.Builder(QTS.this);
/*				종료 옵션 1(두번 클릭)
				if(!isTwoClickBack){
					Toast.makeText(this, "'뒤로'버튼을 한번더 클릭 하시면 종료됩니다." , Toast.LENGTH_SHORT).show();
					CntTimer timer = new CntTimer(2000, 1);
					timer.start();
				}else{
					finish();
					return true;
				} 
//*/
//*				종료 옵션 2 (종료 확인 창)
				end2.setMessage("My Analyst를\n종료하시겠습니까?");
				end2.setPositiveButton("예", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						FileInputStream in;
						GV.setSRM(0);
						try{
			            	in = openFileInput("op1.txt");
//			            	rd = new InputStreamReader(in);
			            	 byte[] data = new byte[in.available()];
			            	op_out = "";
			            	while(in.read(data) != -1){
			                	in.close();
			            		op_out = new String(data);
			    			}
			            }catch(FileNotFoundException e){
			            	
			            }catch (IOException e) {
			    			e.printStackTrace();
			    		}
			    		
			    		if(op_out.equals("1")){
			    			String ch_op = String.format("%.3f", Float.valueOf(version));
							try{
								FileOutputStream out = openFileOutput("op1.txt",Context.MODE_WORLD_READABLE);
					               out.write(ch_op.getBytes());
					               out.close();
							}
							catch (IOException ioe){
								System.out.print("Can't Write");
						}
			    		}
						finish();
					}
				});
				end2.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				end2.show();
//*/
			}else if(keyCode == KeyEvent.KEYCODE_MENU){
				getVC();
				bld = new AlertDialog.Builder(QTS.this);
				bld.setMessage("My Analyst 버전: "+String.format("%.3f", Float.valueOf(version))+"\n\n" +
						"본 프로그램은 주식 투자의 참고 자료로 활용될 수 있는 정보를 다양한 차트의 형식으로 표시하고, " +
						"각각의 차트에 대한 설명을 추가함으로써, 투자하고자 하는 회사의 재무 상태를 파악할 수 있도록 합니다.\n\n" +
						"[프로그램 사용 방법]\n" +
						"아래의 [설명 보기] 버튼을 누르면 해당 페이지로 이동합니다.\n\n" +
						"※ [의견 쓰기] 버튼을 이용하여 의견을 남겨 주시면, " +
						"검토 후 차기 버전에 반영토록 하겠습니다.\n\n" +
						"제작사 정보\n\n" +
						"Quantec. co., ltd\n" +
						"http://quantec.co.kr");
				bld.setPositiveButton("닫기", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
					}
				});
			
				bld.setNeutralButton("설명 보기", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://quantec.co.kr/intro_mobile.html")));
					}
				});
				bld.setNegativeButton("의견 쓰기", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://quantec.co.kr/bbs/zboard.php?id=bug_report")));
					}
				});
				bld.show();
			}
		}
		return false;
	}
	
	private void getVC(){
    	String retVal = "";
    	
    	String resVal="";
    	String urlString;
    	
    	int SI, EI;
    	
    	try {
    		urlString = "http://quantec.co.kr/version_ch/vc.xml";
    		
    		URL url = new URL( urlString );
    		URLConnection connection = url.openConnection();
    		InputStream is = connection.getInputStream();
    		BufferedReader br = new BufferedReader( new InputStreamReader( is, "UTF-8" ), 8192 );

    		char recv[] = new char[ 8192 ];
    		while( br.read( recv ) > 0 )
    			retVal += new String( recv );
    		br.close();
    		is.close();
    	} catch (MalformedURLException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
		SI = retVal.indexOf("<vc>");
		if(SI > 0){
			EI = retVal.indexOf("</vc>", SI);
			SI= SI+4;
			resVal = retVal.substring(SI, EI);
			retVal = "";
			retVal = resVal+"f";
			
			vc = Float.parseFloat(retVal);
		}else
			vc = version;
    }
	
	class CntTimer extends CountDownTimer{
		public CntTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			isTwoClickBack = true;
		}
		
		public void onFinish() {
			// TODO Auto-generated method stub
			isTwoClickBack = false;
		}
		
		public void onTick(long millisUntilFinished) {
			// TODO Auto-generated method stub
			Log.i("Test"," isTwoClickBack " + isTwoClickBack);
		}
	}
    
	public void onShowMenu(){
		if(GV.getSRM() == 1)
			startActivity(Cmenu1);
		else if(GV.getSRM() == 2)
			startActivity(Cmenu2);
		else if(GV.getSRM() == 3)
			startActivity(Cmenu3);
		else if(GV.getSRM() == 4)
			startActivity(Cmenu4);
		else if(GV.getSRM() == 5)
			startActivity(Cmenu5);
		else if(GV.getSRM() == 6)
			startActivity(Cmenu6);
	}
    
	public class testView extends View {
    	int thr = 0;
		Bitmap vLogo = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.vb_logo);
    	
    	Paint pnt = new Paint();
    	
    	public testView(Context context)
    	{
    		super(context);
    	}
    	public testView(Context context, AttributeSet attrs) {
    		super(context, attrs);
    	}
    	public void onDraw(Canvas canvas){
    		pnt.setColor(Color.BLACK);
    		
    		canvas.drawBitmap(vLogo, MyApp.getDisplay().getWidth()/2-vLogo.getWidth()/2, 25, pnt);
    		
    	}
         
    	public void animate(final testView tmp) {
    		tmp.thr = 1;
     		
    		new Thread(new Runnable() {
    			
    			public void run() {
    				while(tmp.thr == 1){ 
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
	
	public class adView extends View {
    	int thr = 0;
    	
    	Paint pnt = new Paint();
    	Paint pnt2 = new Paint();
    	URL Imgurl;
    	
    	String t_adImg;
    	
    	static final int MAX_AD_NUM = 3;
    	
    	Bitmap vLogo = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.b_bg);    	

//	    Bitmap Icon_m = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.hint_m);
	    Bitmap Icon_t1 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.s_icon_3);
	    Bitmap Icon_t2 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.s_icon_4);
	    Bitmap Icon_t3 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.s_menu_s);
	    Bitmap Icon_t4 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.d_menu);
//	    Bitmap Icon_t4 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.s_menu_l);
	    Bitmap Icon_soff = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ch_us);
	    Bitmap Icon_son = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ch_s);
	    Bitmap M_ss = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.m_select_s);
	    Bitmap M_sl = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.m_select_l);
	    Bitmap Pnter = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.pnt1);
	    
    	
    	int ad_num = 1;
    	
//    	Bitmap i_Ad1 = getAdImage1();
//		Bitmap i_Ad2 = getAdImage2();
//		Bitmap i_Ad3 = getAdImage3();
    	
    	public adView(Context context)
    	{
    		super(context);
    	}
    	public adView(Context context, AttributeSet attrs) {
    		super(context, attrs);
    	}
    	public void onDraw(Canvas canvas){
    		pnt.setColor(Color.BLACK);
    		pnt2.setAlpha(200);
    		
   			
   			int t_height;
    		
   			if(GV.getDisplay().getWidth() > 700)
   				t_height =GV.getDisplay().getHeight()*11/16+96;
   			else
   				t_height = GV.getDisplay().getHeight()*11/16;
    		
       		Rect src = new Rect(0,0,vLogo.getWidth(),vLogo.getHeight());
       		Rect dst = new Rect(0,0,MyApp.getDisplay().getWidth(),t_height);
        	
    		canvas.drawBitmap(vLogo, src, dst, pnt);
//       		canvas.drawBitmap(vLogo, 0, 0, pnt);
    		
    		W_30.setColor(Color.WHITE);
    		if(width>480)
    			W_30.setTextSize(30);
    		else
    			W_30.setTextSize(20);
    	    W_30.setAntiAlias(true);
    	    
    	    B_30.setColor(Color.BLACK);
    	    if(width>480)
    	    	B_30.setTextSize(30);
    	    else
    	    	B_30.setTextSize(20);
    	    B_30.setAntiAlias(true);
    	    
    	    G_24.setColor(0xff777777);
    	    G_24.setAlpha(50);

    	    Rect src2 = new Rect(0,0,Icon_t4.getWidth(),Icon_t4.getHeight());
     		Rect dst2 = new Rect(width*4/9, (int)(menu_pos+menu_dist), width, (int)(menu_pos+menu_dist*7));
        	    	    
    	    canvas.drawBitmap(Icon_t1, 0, 0, pnt2);
    	    canvas.drawBitmap(Icon_t2, 0, 0, pnt);
    	    
//    	    canvas.drawRect((float)0, menu_pos, (float)width, menu_pos+menu_dist, W_30);
//    	    canvas.drawBitmap(Icon_m, (float)width-Icon_m.getWidth()-5, menu_pos+(menu_dist-Icon_m.getHeight())/2, pnt);
    	    canvas.drawText("우량 종목 탐색기", (float)Icon_t2.getWidth()+5f, menu_pos+menu_dist/2+menu_text_dist, W_30);
    	    
    	    if(sub_menu > 0){
//    	    	canvas.drawRect((float)0, menu_pos+menu_dist, (float)width*4/9, menu_pos+menu_dist*4, W_30);
    	    	for(int mp=0; mp<5; mp++){
    	    		canvas.drawBitmap(Icon_t3, 0, menu_pos+menu_dist*(mp+1), pnt2);
    	    		canvas.drawBitmap(Icon_soff, (float)width*4/9-Icon_soff.getWidth()-5f, menu_pos+menu_dist*(mp+1), pnt);
    	    	}
				canvas.drawText("EPS 증가율 기준", 	15f, menu_pos+menu_text_dist+menu_dist*3/2, W_30);
				canvas.drawText("매출액 기준", 		15f, menu_pos+menu_text_dist+menu_dist*5/2, W_30);
				canvas.drawText("영업이익 기준", 		15f, menu_pos+menu_text_dist+menu_dist*7/2, W_30);
				////////////////////////////////////////////// 2013.02.20 메뉴 추가
				canvas.drawText("임시 메뉴 1", 		15f, menu_pos+menu_text_dist+menu_dist*9/2, W_30);
				canvas.drawText("임시 메뉴 2", 		15f, menu_pos+menu_text_dist+menu_dist*11/2, W_30);
    	    }
    	    if(sub_menu == 2){
    	    	switch(sub_menu2){
	    	    	case 0:
//	    	    		canvas.drawRect(0, menu_pos+menu_dist, (float)width*4/9, menu_pos+menu_dist*2, G_24);
//		    	    	canvas.drawText(">", (float)width*4/9-20, menu_pos+menu_text_dist+menu_dist*3/2, B_30);
	    	    		canvas.drawBitmap(M_ss, 0, menu_pos+menu_dist, pnt);
	    	    		canvas.drawBitmap(Icon_son, (float)width*4/9-Icon_son.getWidth()-5f, menu_pos+menu_dist, pnt);
	    	    		canvas.drawBitmap(Pnter, (float)width*4/9-3f, menu_pos+menu_dist, pnt2);
						break;
	    	    	case 1:
//	    	    		canvas.drawRect(0, menu_pos+menu_dist*2, (float)width*4/9, menu_pos+menu_dist*3, G_24);
//	    	    		canvas.drawText(">", (float)width*4/9-20, menu_pos+menu_text_dist+menu_dist*5/2, B_30);
	    	    		canvas.drawBitmap(M_ss, 0, menu_pos+menu_dist*2, pnt);
	    	    		canvas.drawBitmap(Icon_son, (float)width*4/9-Icon_son.getWidth()-5f, menu_pos+menu_dist*2, pnt);
	    	    		canvas.drawBitmap(Pnter, (float)width*4/9-3f, menu_pos+menu_dist*2, pnt2);
						break;
	    	    	case 2:
//	    	    		canvas.drawRect((float)0, menu_pos+menu_dist*3, (float)width*4/9, menu_pos+menu_dist*4, G_24);
//	    	    		canvas.drawText(">", (float)width*4/9-20, menu_pos+menu_text_dist+menu_dist*7/2, B_30);
	    	    		canvas.drawBitmap(M_ss, 0, menu_pos+menu_dist*3, pnt);
	    	    		canvas.drawBitmap(Icon_son, (float)width*4/9-Icon_son.getWidth()-5f, menu_pos+menu_dist*3, pnt);
	    	    		canvas.drawBitmap(Pnter, (float)width*4/9-3f, menu_pos+menu_dist*3, pnt2);
	    	    		break;
    				////////////////////////////////////////////// 2013.02.20 메뉴 추가
	    	    	case 3:
//	    	    		canvas.drawRect(0, menu_pos+menu_dist*2, (float)width*4/9, menu_pos+menu_dist*3, G_24);
//	    	    		canvas.drawText(">", (float)width*4/9-20, menu_pos+menu_text_dist+menu_dist*5/2, B_30);
	    	    		canvas.drawBitmap(M_ss, 0, menu_pos+menu_dist*4, pnt);
	    	    		canvas.drawBitmap(Icon_son, (float)width*4/9-Icon_son.getWidth()-5f, menu_pos+menu_dist*4, pnt);
	    	    		canvas.drawBitmap(Pnter, (float)width*4/9-3f, menu_pos+menu_dist*4, pnt2);
						break;
	    	    	case 4:
//	    	    		canvas.drawRect((float)0, menu_pos+menu_dist*3, (float)width*4/9, menu_pos+menu_dist*4, G_24);
//	    	    		canvas.drawText(">", (float)width*4/9-20, menu_pos+menu_text_dist+menu_dist*7/2, B_30);
	    	    		canvas.drawBitmap(M_ss, 0, menu_pos+menu_dist*5, pnt);
	    	    		canvas.drawBitmap(Icon_son, (float)width*4/9-Icon_son.getWidth()-5f, menu_pos+menu_dist*5, pnt);
	    	    		canvas.drawBitmap(Pnter, (float)width*4/9-3f, menu_pos+menu_dist*5, pnt2);
	    	    		break;
    	    	}
    	    	
//    	    	canvas.drawRect((float)width*4/9, menu_pos+menu_dist, (float)width-2, menu_pos+menu_dist*6, W_30);
//    	    	canvas.drawBitmap(Icon_t4, (float)width*4/9, menu_pos+menu_dist, pnt2);
    	    	canvas.drawBitmap(Icon_t4, src2, dst2, pnt2);
    	    	for(int mp2=0; mp2<6; mp2++){
//    	    		canvas.drawBitmap(Icon_t4, (float)width*4/9, menu_pos+menu_dist*(mp2+1), pnt2);
    	    		canvas.drawBitmap(Icon_soff, (float)width-Icon_soff.getWidth()-5f, menu_pos+menu_dist*(mp2+1), pnt);
    	    	}
				canvas.drawText("전체 보기", 				(float)width*4/9+Pnter.getWidth()+15f, menu_pos+menu_text_dist+menu_dist*3/2, W_30);
				canvas.drawText("시가총액 5백억 이상", (float)width*4/9+Pnter.getWidth()+15f, menu_pos+menu_text_dist+menu_dist*5/2, W_30);
				canvas.drawText("시가총액 1천억 이상", (float)width*4/9+Pnter.getWidth()+15f, menu_pos+menu_text_dist+menu_dist*7/2, W_30);
				canvas.drawText("시가총액 2천억 이상", (float)width*4/9+Pnter.getWidth()+15f, menu_pos+menu_text_dist+menu_dist*9/2, W_30);
				canvas.drawText("시가총액 5천억 이상", (float)width*4/9+Pnter.getWidth()+15f, menu_pos+menu_text_dist+menu_dist*11/2, W_30);
				canvas.drawText("시가총액 1조 이상", (float)width*4/9+Pnter.getWidth()+15f, menu_pos+menu_text_dist+menu_dist*13/2, W_30);
				
				switch(sub_menu1){
	    	    	case 0:
//	    	    		canvas.drawRect((float)width*4/9, menu_pos+menu_dist, (float)width-2, menu_pos+menu_dist*2, G_24);
//		    	    	canvas.drawText("<", (float)width-20, menu_pos+menu_text_dist+menu_dist*3/2, B_30);
	    	    		canvas.drawBitmap(M_sl, (float)width*4/9, menu_pos+menu_dist, pnt);
	    	    		canvas.drawBitmap(Icon_son, (float)width-Icon_son.getWidth()-5f, menu_pos+menu_dist, pnt);
						break;
	    	    	case 1:
//	    	    		canvas.drawRect((float)width*4/9, menu_pos+menu_dist*2, (float)width-2, menu_pos+menu_dist*3, G_24);
//	    	    		canvas.drawText("<", (float)width-20, menu_pos+menu_text_dist+menu_dist*5/2, B_30);
	    	    		canvas.drawBitmap(M_sl, (float)width*4/9, menu_pos+menu_dist*2, pnt);
	    	    		canvas.drawBitmap(Icon_son, (float)width-Icon_son.getWidth()-5f, menu_pos+menu_dist*2, pnt);
						break;
	    	    	case 2:
//	    	    		canvas.drawRect((float)width*4/9, menu_pos+menu_dist*3, (float)width-2, menu_pos+menu_dist*4, G_24);
//	    	    		canvas.drawText("<", (float)width-20, menu_pos+menu_text_dist+menu_dist*7/2, B_30);
	    	    		canvas.drawBitmap(M_sl, (float)width*4/9, menu_pos+menu_dist*3, pnt);
	    	    		canvas.drawBitmap(Icon_son, (float)width-Icon_son.getWidth()-5f, menu_pos+menu_dist*3, pnt);
	    	    		break;
	    	    	case 3:
//	    	    		canvas.drawRect((float)width*4/9, menu_pos+menu_dist*4, (float)width-2, menu_pos+menu_dist*5, G_24);
//	    	    		canvas.drawText("<", (float)width-20, menu_pos+menu_text_dist+menu_dist*9/2, B_30);
	    	    		canvas.drawBitmap(M_sl, (float)width*4/9, menu_pos+menu_dist*4, pnt);
	    	    		canvas.drawBitmap(Icon_son, (float)width-Icon_son.getWidth()-5f, menu_pos+menu_dist*4, pnt);
	    	    		break;
	    	    	case 4:
//	    	    		canvas.drawRect((float)width*4/9, menu_pos+menu_dist*5, (float)width-2, menu_pos+menu_dist*6, G_24);
//	    	    		canvas.drawText("<", (float)width-20, menu_pos+menu_text_dist+menu_dist*11/2, B_30);
	    	    		canvas.drawBitmap(M_sl, (float)width*4/9, menu_pos+menu_dist*5, pnt);
	    	    		canvas.drawBitmap(Icon_son, (float)width-Icon_son.getWidth()-5f, menu_pos+menu_dist*5, pnt);
	    	    		break;
	    	    	case 5:
//	    	    		canvas.drawRect((float)width*4/9, menu_pos+menu_dist*5, (float)width-2, menu_pos+menu_dist*6, G_24);
//	    	    		canvas.drawText("<", (float)width-20, menu_pos+menu_text_dist+menu_dist*11/2, B_30);
	    	    		canvas.drawBitmap(M_sl, (float)width*4/9, menu_pos+menu_dist*6, pnt);
	    	    		canvas.drawBitmap(Icon_son, (float)width-Icon_son.getWidth()-5f, menu_pos+menu_dist*6, pnt);
	    	    		break;
    	    	}
    	    }
    	    
//    	    Bitmap Icon_t1 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.s_icon_3);
//   	    Bitmap Icon_t2 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.s_icon_4);
    	    
//    	    canvas.drawBitmap(Icon_t1, (float)width-Icon_t1.getWidth()-Icon_t2.getWidth()-width/36, 0, pnt);
//    	    canvas.drawBitmap(Icon_t2, (float)width-Icon_t2.getWidth()-width/36, 0, pnt);
///////////////////////////////////////////////////////////////////////////
//				2011. 10. 15 수정 및 테스트    		
/////////////////////////////////////////////////////////////////////////
    		FileInputStream in;
            FileOutputStream out;
            
            try{
            	in = openFileInput("op1.txt");
//            	rd = new InputStreamReader(in);
            	 byte[] data = new byte[in.available()];
            	op_out = "";
            	while(in.read(data) != -1){
                	in.close();
            		op_out = new String(data);
    			}
            }catch(FileNotFoundException e){
            	try{
            		out = openFileOutput("op1.txt",Context.MODE_WORLD_READABLE);
	               out.write(op_out.getBytes());
	               out.close();
    			}
    			catch (IOException ioe){
    				System.out.print("Can't Write");
    			}
            }catch (IOException e) {
    			e.printStackTrace();
    		}
    		
//    		if(op_out.equals(String.format("%.3f", Float.valueOf(version)))){
            if(Float.valueOf(op_out).floatValue() <= Float.valueOf(version)){
    			update.setTitle("※ 버전 "+String.format("%.3f", Float.valueOf(version))+" 업데이트 내용");
	    		update.setMessage("이번 업데이트를 통해 수정이 이루어진 부분은 아래와 같습니다.\n\n" +
	    				"1. 주가 정보 불러오기 오류 해결\n" +
	    				"  - 일부 종목 검색시 주가 정보를 제대로 가져오지 못하는 문제 해결.");
	    		update.setPositiveButton("닫기", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
//*						
						String ch_op = String.format("%.3f", Float.valueOf(version+0.005f));
						try{
							FileOutputStream out = openFileOutput("op1.txt",Context.MODE_WORLD_READABLE);
				               out.write(ch_op.getBytes());
				               out.close();
						}
						catch (IOException ioe){
							System.out.print("Can't Write");
						} //*/
					}
				});
				update.show();
    		}
/////////////////////////////////////////////////////////////////////////
//			2011. 10. 15 수정 및 테스트    	
/////////////////////////////////////////////////////////////////////////
    		
// ad 1
//    		canvas.drawRect(0, MyApp.getDisplay().getHeight()-150-250, MyApp.getDisplay().getWidth(), MyApp.getDisplay().getHeight()-100-250, pnt);
//    		canvas.drawBitmap(i_Ad1, MyApp.getDisplay().getWidth()-i_Ad1.getWidth(), MyApp.getDisplay().getHeight()-150-250, pnt);
// ad 2    		
//    		canvas.drawRect(0, MyApp.getDisplay().getHeight()-200-250, MyApp.getDisplay().getWidth(), MyApp.getDisplay().getHeight()-150-250, pnt);
//    		canvas.drawBitmap(i_Ad2, MyApp.getDisplay().getWidth()-i_Ad2.getWidth(), MyApp.getDisplay().getHeight()-200-250, pnt);
// ad 3    		
//    		canvas.drawRect(0, MyApp.getDisplay().getHeight()-150-250, MyApp.getDisplay().getWidth(), MyApp.getDisplay().getHeight()-100-250, pnt);
//    		canvas.drawBitmap(i_Ad3, MyApp.getDisplay().getWidth()-i_Ad3.getWidth(), MyApp.getDisplay().getHeight()-150-250, pnt);
    		
    	}
/*         
    	public void animate(final adView tmp) {
    		tmp.thr = 1;
     		
    		new Thread(new Runnable() {
    			
    			public void run() {
    				while(tmp.thr == 1){ 
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
//*/    	
    }

	public Bitmap getAdImage1() {

	    try {
	    	aURL = new URL( adImgUrl1 );
	        final URLConnection conn = aURL.openConnection();
	        conn.connect();
	        final BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
	        final Bitmap bm = BitmapFactory.decodeStream(bis);
	        bis.close();
	        return bm;
	    } catch (IOException e) {
		
	    }
	    return null;
	}
	
	public Bitmap getAdImage2() {

	    try {
	    	aURL = new URL( adImgUrl2 );
	        final URLConnection conn = aURL.openConnection();
	        conn.connect();
	        final BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
	        final Bitmap bm = BitmapFactory.decodeStream(bis);
	        bis.close();
	        return bm;
	    } catch (IOException e) {
		
	    }
	    return null;
	}
	
	public Bitmap getAdImage3() {

	    try {
	    	aURL = new URL( adImgUrl3 );
	        final URLConnection conn = aURL.openConnection();
	        conn.connect();
	        final BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
	        final Bitmap bm = BitmapFactory.decodeStream(bis);
	        bis.close();
	        return bm;
	    } catch (IOException e) {
		
	    }
	    return null;
	}
	
	private StockCount getSDur(){
		
		StockCount tmpSC = new StockCount();
		
		String retVal = "";
    	String resVal;
    	String urlString;
    	int SI, EI;
    	
		try {
			urlString = "http://quantec.co.kr/dur_check.html";
			
			URL url = new URL( urlString );
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader( new InputStreamReader( is, "UTF-8" ), 8192 );

			char recv[] = new char[ 8192 ];
			while( br.read( recv ) > 0 )
				retVal += new String( recv );
			br.close();
			is.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		resVal = "";
		SI = retVal.indexOf("<quar data=");
		EI = retVal.indexOf("/>", SI);
		SI = SI + 12;
		EI = EI - 1;
		resVal = retVal.substring(SI, EI);
		
		tmpSC.begin_sq = Integer.valueOf(resVal).intValue();
		
		resVal = "";
		SI = retVal.indexOf("<week data=", EI);
		EI = retVal.indexOf("/>", SI);
		SI = SI+12;
		EI = EI -1;
		resVal = retVal.substring(SI, EI);
		
		tmpSC.last_sw = Integer.valueOf(resVal).intValue();
		
		return tmpSC;		
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
    		
    		Orest.dNum = lit;
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
	
/*	
	public boolean onCreateOptionsMenu(Menu menu){	
		super.onCreateOptionsMenu(menu);
		menu.add(0, 1, 0, "My Analyst v1.215");
//		menu.add(0, 2, 1, "건의 사항");
//		menu.add(0, 3, 2, "clear");
		
		return true;
	}
	
	public boolean onOptionItemSelected(MenuItem item){
		
		switch(item.getItemId()){
		case 1:
	
			return true;
		case 2:
//			startActivity(Cmenu1);
//			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://quantec.co.kr/bbs/zboard.php?id=bug_report")));
			return true;
		}
		
		return false;
	}
	
*/	
    static final String[] NSTOCKS = new String[] {
    	"3노드디지탈그룹유한공사", "3노드디지탈", "쓰리에이치", "3H", "삼에스코리아", "3S", "가비아", "가온미디어", "가온전선", "가희", "강남캐피탈", "강원랜드", "강원비앤이", "갤럭시아커뮤니케이션즈", "갤럭시아컴즈", "거북선4호선박투자회사", "거북선4호", "거북선5호선박투자회사", "거북선5호", "거북선6호선박투자회사", "거북선6호", "거북선7호선박투자회사", "거북선7호", "건설화학공업", "건설화학", "게임빌", "게임하이", "경남기업", "경남스틸", "경남에너지", "경남제약", "경농", "경동도시가스", "경동가스", "경동나비엔", "경동제약", "경방", "경봉", "경인양행", "경인전자", "경창산업", "계룡건설산업", "계룡건설", "계양전기", "고려개발", "고려반도체시스템", "고려반도체", "고려산업", "고려신용정보", "고려아연", "고려제강", "고려제약", "고려포리머", "고영테크놀러지", "고영", "골든나래개발전문자기관리부동산투자회사", "골든나래리츠", "골든브릿지투자증권", "골든브릿지증권", "골프존", "광동제약", "광림", "광명전기", "광전자", "광주신세계", "광진실업", "광진윈텍", "광희개발전문자기관리부동산투자회사", "광희리츠", "교보증권", "교보케이티비기업인수목적", "교보KTB스팩", "구영테크", "국도화학", "국동", "국보", "국보디자인", "국순당", "국영지앤엠", "국일제지", "국제디와이", "국제약품공업", "국제약품", "국제엘렉트릭코리아", "국제엘렉트릭", "그랜드백화점", "그린기술투자", "그린손해보험", "극동스틸", "극동유화", "근화제약", "글로벌에스엠테크리미티드", "글로벌에스엠", "글로스텍", "금강공업", "금강철강", "금비", "금산", "금성테크", "금양", "금호산업", "금호석유화학", "금호석유", "금호전기", "금호종합금융", "금호종금", "금호타이어", "금화피에스시", "기륭전자", "기산텔레콤", "기신정기", "기아자동차", "기아차", "중소기업은행", "기업은행", "깨끗한나라", "나노신소재", "나노엔텍", "나노캠텍", "나노트로닉스", "나라엠앤디", "나우콤", "나이벡", "나이스정보통신", "남광토건", "남선알미늄", "남성", "남양유업", "남영비비안", "남해화학", "네스테크", "네오엠텔", "네오위즈", "네오위즈게임즈", "네오위즈인터넷", "네오티스", "네오팜", "네오퍼플", "네오피델리티", "네추럴", "네패스", "네패스신소재", "네프로아이티", "넥센", "넥센타이어", "넥센테크", "넥스지", "넥스콘테크놀러지", "넥스콘테크", "넥스턴", "넥스트아이", "넥스트칩", "넷웨이브", "넷피아", "노루페인트", "노루홀딩스", "녹십자", "녹십자홀딩스", "농심", "농심홀딩스", "농업회사법인농우바이오", "농우바이오", "누리텔레콤", "누리플랜", "뉴로테크", "뉴보텍", "뉴인텍", "뉴프라이드코퍼레이션", "뉴프라이드", "뉴프렉스", "능률교육", "니트젠앤컴퍼니", "다나와", "다날", "다믈멀티미디어", "다산네트웍스", "다스텍", "다우기술", "다우데이타", "다원시스", "다윈텍", "다음커뮤니케이션", "다음", "다크호스", "다함이텍", "대경기계기술", "대경기계", "대교", "대구방송", "대구백화점", "대국", "대덕전자", "대덕GDS", "대동공업", "대동금속", "대동기어", "대동스틸", "대동전자", "대륙제관", "대림산업", "대림제지", "대림통상", "대림비앤코", "대림B&Co", "대명엔터프라이즈", "대백저축은행", "대봉엘에스", "대상", "대상홀딩스", "대성미생물연구소", "대성미생물", "대성산업", "대성에너지", "대성엘텍", "대성창업투자", "대성창투", "대성파인텍", "대성합동지주", "대성홀딩스", "대신정보통신", "대신증권", "대신증권그로쓰알파기업인수목적", "대신증권그로쓰스팩", "대아티아이", "대양글로벌", "대양금속", "대양전기공업", "대양제지공업", "대양제지", "대영포장", "대우건설", "대우전자부품", "대우부품", "대우인터내셔널", "대우조선해양", "대우증권", "대우증권그린코리아기업인수목적회사", "대우증권스팩", "대우자동차판매", "대우차판매", "대웅", "대웅제약", "대원강업", "대원미디어", "대원산업", "대원전선", "대원제약", "대원화성", "대유신소재", "대유에이텍", "대정화금", "대주산업", "대주전자재료", "대진디엠피", "대창", "대창단조", "대창메탈", "대한도시가스", "대한가스", "대한뉴팜", "대한바이오", "대한방직", "대한생명보험", "대한생명", "대한약품공업", "대한약품", "대한유화공업", "대한유화", "대한은박지", "대한전선", "대한제강", "대한제당", "대한제분", "대한종합상사", "대한통운", "대한항공", "대한해운", "대한화섬", "대현", "대호에이엘", "대호피앤시", "대호피앤씨", "대화제약", "더존비즈온", "더체인지", "덕산하이메탈", "덕성", "덕양산업", "데코네티션", "도이치모터스", "도화엔지니어링", "동국산업", "동국실업", "동국알앤에스", "동국제강", "동국제약", "동국에스엔씨", "동국S&C", "동남합성", "동방", "동방선기", "동방아그로", "동부건설", "화우테크놀러지", "동부라이텍", "동부로봇", "동부제철", "동부증권", "동부티에스블랙펄기업인수목적", "동부티에스블랙펄스팩", "동부하이텍", "동부화재해상보험", "동부화재", "동부씨엔아이", "동부CNI", "동북아10호선박투자회사", "동북아10호", "동북아11호선박투자회사", "동북아11호", "동북아12호선박투자회사", "동북아12호", "동북아13호선박투자회사", "동북아13호", "동북아14호선박투자회사", "동북아14호", "동북아2호선박투자회사", "동북아2호", "동북아3호선박투자회사", "동북아3호", "동북아4호선박투자회사", "동북아4호", "동북아5호선박투자회사", "동북아5호", "동서", "동성제약", "동성하이켐", "동성홀딩스", "동성화학", "동신건설", "동아에스텍", "동아엘텍", "동아원", "동아제약", "동아지질", "동아타이어공업", "동아타이어", "동아화성", "동양메이저", "동양", "동양강철", "동양건설산업", "동양건설", "동양고속운수", "동양고속", "동양기전", "동양매직", "동양물산기업", "동양물산", "동양밸류오션기업인수목적", "동양밸류스팩", "동양생명보험", "동양생명", "동양시멘트", "동양시스템즈", "동양에스텍", "동양이엔피", "동양종합금융증권", "동양종금증권", "동양철관", "동양텔레콤", "동양피엔에프", "동우", "동원", "동원개발", "동원금속", "동원산업", "동원수산", "동원시스템즈", "동원F&B", "동일금속", "동일기연", "동일방직", "동일고무벨트", "동일벨트", "동일산업", "동일제지", "동일철강", "동진건설", "동진쎄미켐", "동화약품", "동화홀딩스", "두산", "두산건설", "두산엔진", "두산인프라코어", "두산중공업", "두올산업", "두원중공업", "듀오백코리아", "드래곤플라이", "드림텍", "디브이에스코리아", "디브이에스", "디스플레이테크", "디스플레이텍", "디씨엠", "디아이", "디아이디", "디아이씨", "디앤샵", "디에스", "디에스케이", "디에이피", "디엔에프", "디엠씨", "디오", "디오텍", "디웍스글로벌", "디이엔티", "디지아이", "디지탈아리아", "디지털대성", "디지털오션", "디지털텍", "디지텍시스템스", "디지텍시스템", "디지틀조선일보", "디지틀조선", "디케이디앤아이", "디케이락", "디테크놀로지", "디피씨", "딜리", "뜨레봄", "라이브플렉스", "라이프앤비", "락앤락", "레드로버", "레드캡투어", "레이젠", "로만손", "로엔엔터테인먼트", "로엔", "로엔케이", "로체시스템즈", "로케트전기", "롯데관광개발", "롯데미도파", "롯데삼강", "롯데손해보험", "롯데쇼핑", "롯데제과", "롯데칠성음료", "롯데칠성", "루멘스", "루미마이크로", "루보", "루트로닉", "룩손에너지홀딩스", "룩손에너지", "르네코", "리노공업", "리노스", "리드코프", "리바트", "리켐", "리홈", "링네트", "마니커", "마음커뮤니", "마이다스_커버드콜", "마이스코", "마이크로컨텍솔루션", "마이크로컨텍솔", "마크로젠", "만도", "만호제강", "매일옥션", "매일유업", "매커스", "맥쿼리한국인프라투융자회사", "맥쿼리인프라", "미래에셋맵스아시아퍼시픽부동산공모일호투자회사", "맵스리얼티1", "미래에셋맵스오퍼튜니티베트남주식혼합형투자회사일호", "맵스베트남1", "메가스터디", "메디톡스", "메디포스트", "메디프론디비티", "메디프론", "메리츠금융지주", "메리츠종합금융증권", "메리츠종금증권", "메리츠화재해상보험", "메리츠화재", "메타바이오메드", "멜파스", "명문제약", "명보기업", "명신", "명품아카데미", "모나리자", "모나미", "모두투어네트워크", "모두투어", "모린스", "모바일리더", "모베이스", "모빌리언스", "모아텍", "모토닉", "모헨즈", "무림페이퍼", "무림피앤피", "무림P&P", "무림에스피", "무림SP", "무학", "무한투자", "문배철강", "미디어플렉스", "미래나노텍", "미래산업", "미래에셋제1호기업인수목적", "미래에셋스팩1호", "미래에셋증권", "미래엠텍", "미래컴퍼니", "미리넷", "미성포리테크", "미스터피자", "미원상사", "미원스페셜티케미칼", "미원에스씨", "미원화학", "미주제강", "미창석유공업", "미창석유", "바다로3호선박투자회사", "바다로3호", "바른손", "바른손게임즈", "바른전자", "바이넥스", "바이로메드", "바이오니아", "바이오랜드", "바이오메드랩", "바이오스마트", "바이오스페이스", "바이오톡스텍", "바텍", "방림", "배명금속", "백광산업", "백광소재", "백금티앤에이", "백금T&A", "백산", "백산오피씨", "백산OPC", "뱅크이십오", "버추얼텍", "범양건영", "더베이직하우스", "베이직하우스", "베트남개발1", "벽산", "벽산건설", "보광티에스", "보락", "보령메디앙스", "보령제약", "보루네오가구", "보루네오", "보성파워텍", "보해양조", "부광약품", "부국증권", "부국철강", "부국퓨쳐스타즈기업인수목적", "부국퓨쳐스타즈스팩", "부산도시가스", "부산가스", "부산방직공업", "부산방직", "부산산업", "부산주공", "부스타", "뷰웍스", "브리지텍", "블루젬디앤씨", "블루콤", "비상교육", "비아이이엠티", "비앤비성원", "비에스이홀딩스", "비에스이", "비에이치", "비에이치아이", "비엠티", "비즈아이", "비츠로셀", "비츠로시스", "비츠로테크", "비트로시스", "비트컴퓨터", "비티씨정보통신", "비티씨정보", "빅솔론", "빅텍", "빙그레", "빛과전자", "사조대림", "사조산업", "사조오양", "사조해표", "산성피앤씨", "삼강엠앤티", "삼광유리", "삼륭물산", "삼립식품", "삼목정공", "삼보모토스", "삼보산업", "삼보이엔씨", "삼보판지", "삼본정밀전자", "삼부토건", "삼성공조", "삼성물산", "삼성생명보험", "삼성생명", "삼성엔지니어링", "삼성전기", "삼성전자", "삼성정밀화학", "삼성제약공업", "삼성제약", "삼성중공업", "삼성증권", "삼성출판사", "삼성카드", "삼성테크윈", "삼성화재해상보험", "삼성화재", "삼성SDI", "삼아알미늄", "삼아제약", "삼양사", "삼양식품", "삼양엔텍", "삼양옵틱스", "삼양제넥스", "삼양통상", "삼영무역", "삼영엠텍", "삼영이엔씨", "삼영전자공업", "삼영전자", "삼영필텍", "삼영홀딩스", "삼영화학공업", "삼영화학", "삼우이엠씨", "삼원강재", "삼원테크", "삼익악기", "삼익THK", "삼일", "삼일기업공사", "삼일제약", "삼정펄프", "삼지전자", "삼진", "삼진엘앤디", "삼진제약", "삼천당제약", "삼천리", "삼천리자전거", "삼현철강", "삼호", "삼호개발", "삼화네트웍스", "삼화왕관", "삼화전기", "삼화전자공업", "삼화전자", "삼화콘덴서공업", "삼화콘덴서", "삼화페인트공업", "삼화페인트", "삼환기업", "삼환까뮤", "상보", "상신브레이크", "상신이디피", "상아프론테크", "새로닉스", "새론오토모티브", "샘표식품", "샤인", "서광", "서린바이오사이언스", "서린바이오", "서부티엔디", "서부T&D", "서산", "서울도시가스", "서울가스", "서울반도체", "서울식품공업", "서울식품", "서울신용평가정보", "서울신용평가", "서울옥션", "서울상호저축은행", "서울저축은행", "서울전자통신", "서울제약", "서원", "서원인텍", "서한", "서호전기", "서화정보통신", "서흥캅셀", "서희건설", "선광", "선도소프트", "선도전기", "선진", "선진지주", "선창산업", "선팩테크", "성광벤드", "성도이엔지", "성문전자", "성보화학", "성신양회", "성안", "성우전자", "성우테크론", "성우하이텍", "성융광전투자유한공사", "성융광전투자", "성지건설", "성진지오텍", "성창기업지주", "성창에어텍", "성호전자", "세계투어", "세기상사", "세동", "세명전기공업", "세명전기", "세미텍", "세방", "세방전지", "세보엠이씨", "세아베스틸", "세아제강", "세아특수강", "세아홀딩스", "세우글로벌", "세우테크", "세운메디칼", "세원물산", "세원셀론텍", "세원정공", "세이브존아이앤씨", "세이브존I&C", "세종공업", "세중", "세진전자", "세진티에스", "세코닉스", "세하", "셀레네", "셀트리온", "셀트리온제약", "소리바다", "소프트맥스", "소프트포럼", "손오공", "솔고바이오메디칼", "솔고바이오", "솔라시아", "솔로몬저축은행", "솔보텍", "솔본", "송원산업", "수산중공업", "수성", "한국수출포장공업", "수출포장", "쉘라인", "슈넬생명과학", "슈프리마", "스멕스", "스카이뉴팜", "케이티스카이라이프", "스카이라이프", "스타플렉스", "스템싸이언스", "스틸플라워", "스페코", "승일", "승화명품건설", "승화산업", "시공테크", "시그네틱스", "시노펙스", "시노펙스그린테크", "신대양제지", "신도리코", "신라교역", "신라섬유", "신라에스지", "신민상호저축은행", "신민저축은행", "신성델타테크", "신성솔라에너지", "신성에프에이", "신성이엔지", "신성통상", "신세계", "신세계아이앤씨", "신세계_I&C", "신세계건설", "신세계인터내셔날", "신세계푸드", "신양엔지니어링", "신양", "신영와코루", "신영증권", "신우", "신원", "신원종합개발", "신일건업", "신일산업", "신일제약", "신창전기", "신텍", "신풍제약", "신풍제지", "신한", "신한제1호기업인수목적", "신한스팩1호", "신한금융지주회사", "신한지주", "신화실업", "신화인터텍", "신흥", "실리콘웍스", "실리콘화일", "심텍", "심플렉스인터", "쌍방울트라이그룹", "쌍방울트라이", "쌍용건설", "쌍용머티리얼", "쌍용양회공업", "쌍용양회", "쌍용정보통신", "쌍용자동차", "쌍용차", "써니전자", "쎄니트", "쎄트렉아이", "쎌바이오텍", "쏜다넷", "쏠리테크", "쓰리피시스템", "씨그널정보통신", "씨모텍", "씨씨에스충북방송", "씨씨에스", "씨앤비텍", "씨앤에스테크놀로지", "씨앤에스", "씨앤케이인터내셔널", "씨앤케이인터", "씨젠", "씨티씨바이오", "씨티엘", "아가방앤컴퍼니", "아가방컴퍼니", "아나패스", "아남전자", "아남정보기술", "아로마소프트", "아리랑_K100EW", "아리랑_KOSPI50", "아리랑_KRX100EW", "아리온테크놀로지", "아리온", "아모레퍼시픽", "아모레퍼시픽그룹", "아모레G", "아모텍", "아미노로직스", "아바코", "아비스타", "아비코전자", "아세아시멘트", "아세아제지", "아세아텍", "아세아페이퍼텍", "아시아퍼시픽10호선박투자회사", "아시아10호", "아시아퍼시픽11호선박투자회사", "아시아11호", "아시아퍼시픽12호선박투자회사", "아시아12호", "아시아퍼시픽13호선박투자회사", "아시아13호", "아시아퍼시픽14호선박투자회사", "아시아14호", "아시아퍼시픽15호선박투자회사", "아시아15호", "아시아퍼시픽1호선박투자회사", "아시아1호", "아시아퍼시픽2호선박투자회사", "아시아2호", "아시아퍼시픽3호선박투자회사", "아시아3호", "아시아퍼시픽4호선박투자회사", "아시아4호", "아시아퍼시픽5호선박투자회사", "아시아5호", "아시아퍼시픽6호선박투자회사", "아시아6호", "아시아퍼시픽7호선박투자회사", "아시아7호", "아시아퍼시픽8호선박투자회사", "아시아8호", "아시아퍼시픽9호선박투자회사", "아시아9호", "아시아나항공", "유진데이타", "아시아미디어홀딩스", "아이넷스쿨", "아이디스홀딩스", "아이디에스", "아이디엔", "아이리버", "아이마켓코리아", "아이스테이션", "아이씨디", "아이씨에너텍", "아이씨케이", "아이앤씨테크놀로지", "아이앤씨", "아이에스동서", "아이에스이커머스", "아이엠", "아이오시스템", "아이즈비전", "아이컴포넌트", "아이크래프트", "아이텍반도체", "아인스", "아인스엠앤엠", "아인스M&M", "아주캐피탈", "아즈텍더블유비이", "아즈텍WB", "아큐텍반도체기술", "아큐텍", "아큐픽스", "아트라스비엑스", "아트라스BX", "아트원제지", "아티스", "아하정보통신", "안국약품", "안철수연구소", "알덱스", "알앤엘바이오", "알앤엘삼미", "알에스넷", "알에프세미", "알에프텍", "신영해피투모로우제1호기업인수목적", "알톤스포츠", "알파칩스", "애강리메텍", "애경유화", "액토즈소프트", "액트", "앤알커뮤니", "양지사", "어보브반도체", "어울림네트웍스", "어울림_네트", "어울림엘시스", "어울림정보기술", "어울림정보", "에너지솔루션즈", "에너지솔루션", "에너토크", "에넥스", "에듀박스", "에듀언스", "에리트베이직", "에머슨퍼시픽", "에버다임", "에버테크노", "에스넷시스템", "에스넷", "에스디시스템", "에스맥", "에스비아이앤솔로몬드림기업인수목적", "에스비아이앤솔로몬스", "에스비엠", "에스씨디", "에스아이리소스", "에스앤더블류", "에스앤에스텍", "에스에너지", "에스에이엠티", "에스에이티", "에스에프에이", "에스엔유프리시젼", "에스엔유", "에스엘", "에스엠엔터테인먼트", "에스엠", "에스원", "에스이티아이", "에스코넥", "에스텍", "에스텍파마", "에스티씨라이프", "에스티아이", "에스티오", "에스티큐브", "에스폴리텍", "에스피지", "에쎈테크", "에쓰씨엔지니어링", "에어파크", "에이디칩스", "에이모션", "에이블씨엔씨", "에이스디지텍", "에이스앤파트너스", "에이스침대", "에이스테크놀로지", "에이스테크", "에이스하이텍", "에이앤씨바이오홀딩스", "에이에스티젯텍", "에이엔피", "에이원마이크로", "에이치디시에스", "에이치앤티", "에이치엘비", "에이테크솔루션", "에이텍", "에이티넘인베스트먼트", "에이티넘인베스트", "에이프로테크놀로지", "에임하이글로벌", "에임하이", "에코에너지홀딩스", "에코에너지", "에코페트로시스템", "에코프로", "에코플라스틱", "에프씨비투웰브", "에프알텍", "에프에스티", "에프티이앤이", "에피밸리", "엑사이엔씨", "엑큐리스", "엔스퍼트", "엔씨소프트", "엔알디", "엔에스브이", "엔엔티", "엔케이", "엔케이바이오", "엔터기술", "엔텍엘이디", "엔텔스", "엔티피아", "엔피케이", "엔하이테크", "엘디티", "엘비세미콘", "엘앤씨피", "엘앤에프", "엘앤피아너스", "엘엠에스", "엘오티베큠", "엘티에스", "엠게임", "엠벤처투자", "엠비성산", "엠씨전자", "엠에스씨", "엠에스오토텍", "엠케이전자", "엠케이트렌드", "엠텍비젼", "엠.피.씨", "엠피씨", "연우이앤티", "연이정보통신", "연합과기공고유한공사", "연합과기", "영남제분", "영보화학", "영신금속공업", "영신금속", "영우통신", "영원무역", "영원무역홀딩스", "영인프런티어", "영진약품공업", "영진약품", "영진인프라", "영풍", "영풍정밀", "영풍제지", "영화금속", "영흥철강", "예당컴퍼니", "예당", "예림당", "예스이십사", "예스24", "예스코", "오공", "오늘과내일", "오디텍", "오뚜기", "오로라월드", "오로라", "오리엔탈정공", "오리엔트바이오", "넥스텍", "오리엔트정공", "오리온", "오리콤", "오상자이엘", "오성엘에스티", "오스코텍", "오스템", "오스템임플란트", "오즈앤블레싱", "오텍", "오픈베이스", "온세텔레콤", "옴니시스템", "옴니텔", "옵토매직", "옵트론텍", "옵티시스", "와이디온라인", "와이비로드", "와이솔", "와이엔텍", "와이즈파워", "와이지원", "와토스코리아", "완리인터내셔널홀딩스", "완리", "한국외환은행", "외환은행", "용현비엠", "용현BM", "우경철강", "우노앤컴퍼니", "우리금융지주", "우리금융", "우리기술", "우리기술투자", "우리넷", "우리들생명과학", "우리들제약", "우리산업", "우리기업인수목적1호", "우리스팩1호", "우리이티아이", "우리조명지주", "우리투자증권", "우리파이낸셜", "우림기계", "우성사료", "우성아이비", "우성아이앤씨", "우성I&C", "우수AMS", "우신공업", "우신시스템", "우원개발", "우전앤한단", "우주일렉트로닉스", "우주일렉트로", "우진", "우진비앤지", "우진세렉스", "울트라건설", "웅진씽크빅", "웅진에너지", "웅진케미칼", "웅진코웨이", "웅진홀딩스", "원림", "원익", "원익쿼츠", "원익아이피에스", "원익IPS", "원일특강", "원풍", "원풍물산", "월덱스", "웨스테이트디벨롭먼트", "웨스테이트", "웨이브일렉트로닉스", "웨이브일렉트로", "웨이포트유한공사", "웨이포트", "웰메이드스타엠", "웰메이드", "웰스브릿지", "웰스킨씨앤", "웰스킨화장품", "웰크론", "웹젠", "위노바", "위닉스", "위다스", "위메이드엔터테인먼트", "위메이드", "위즈정보기술", "위지트", "윈스테크넷", "윈포넷", "윌비스", "한국유나이티드제약", "유나이티드제약", "유니더스", "유니드", "유니모씨앤씨", "유니셈", "유니슨", "유니온", "유니온스틸", "유니켐", "유니퀘스트", "유니크", "유니테스트", "유니텍", "유라테크", "유비벨록스", "유비컴", "유비케어", "유비쿼스", "유비프리시젼", "유성기업", "유성티엔에스", "유신", "유아이디", "유아이에너지", "DK유아이엘", "유아이엘", "유양디앤유", "유엔젤", "유원컴텍", "유유제약", "유일엔시스", "유정메디칼", "유진기업", "유진로봇", "유진테크", "유진투자증권", "유한양행", "유화증권", "율촌화학", "이건산업", "이건창호", "이구산업", "이그잭스", "이글벳", "이글_벳", "이글루시큐리티", "이너스텍", "이노셀", "이노와이어리스", "이노와이어", "이노칩테크놀로지", "이노칩", "이녹스", "이니시스", "이니텍", "이디", "이라이콤", "이랜텍", "이루온", "이룸지엔지", "이마트", "이미지스테크놀로지", "이미지스", "이상네트웍스", "이수앱지스", "이수페타시스", "이수화학", "이스타코", "이스트소프트", "이스트아시아스포츠인터내셔널리미티드", "이스트아시아스포츠", "이씨에스텔레콤", "이씨에스", "이엔쓰리", "이엔에프테크놀로지", "이엘케이", "이엠코리아", "이엠텍", "이연제약", "이오테크닉스", "이원컴포텍", "이월드", "이젠텍", "이지바이오시스템", "이지바이오", "이코리아자기관리부동산투자회사", "이코리아리츠", "이큐스앤자루", "이크레더블", "이테크건설", "이트레이드1호기업인수목적", "이트레이드1호스팩", "이트레이드증권", "이퓨쳐", "이화공영", "이화산업", "이화전기공업", "이화전기", "인디에프", "인산가", "인선이엔티", "인성정보", "인스프리트", "인지디스플레이", "인지디스플레", "인지컨트롤스", "인천도시가스", "인큐브테크", "인탑스", "인터로조", "인터엠", "인터파크", "인터플렉스", "인터하우스", "인텍플러스", "인트론바이오테크놀로지", "인트론바이오", "인팩", "인포바인", "인포뱅크", "인포피아", "인프라웨어", "인피노테크", "인피니트헬스케어", "인화정공", "일경산업개발", "일동제약", "일성건설", "일성신약", "일신바이오베이스", "일신바이오", "일신방직", "일신석재", "일야", "일양약품", "일정실업", "일지테크", "일진다이아몬드", "일진다이아", "일진디스플레이", "일진디스플", "일진머티리얼즈", "일진에너지", "일진전기", "일진홀딩스", "잉크테크", "자연과환경", "자원", "자유투어", "자티전자", "자화전자", "잘만테크", "재영솔루텍", "전방", "전북은행", "한국전파기지국", "전파기지국", "정상제이엘에스", "정원엔시스", "제너시스템즈", "제넥신", "제닉", "제룡산업", "제미니투자", "제우스", "제이브이엠", "제이씨케미칼", "제이씨현시스템", "제이씨현", "제이엔케이히터", "제이엠생명", "제이엠아이", "제이엠티", "제이웨이", "제이콘텐트리", "제이티", "제일기획", "제일모직", "제일바이오", "제일약품", "제일연마공업", "제일연마", "제일저축은행", "제일제강공업", "제일제강", "제일창투", "제일테크", "제일테크노스", "제주은행", "젠트로", "젬백스&카엘", "젬백스", "조광페인트", "조광피혁", "조광아이엘아이", "조광ILI", "조비", "조선내화", "조선선재", "조아제약", "조이맥스", "조일알미늄", "조흥", "종근당", "종근당바이오", "좋은사람들", "주성엔지니어링", "주연테크", "중국고섬공고유한공사", "중국고섬", "중국식품포장유한공사", "중국식품포장", "중국엔진집단유한공사", "중국엔진집단", "중국원양자원유한공사", "중국원양자원", "중앙건설", "중앙백신연구소", "중앙백신", "중앙에너비스", "중앙오션", "지아이바이오", "지아이블루", "지앤디윈텍", "지앤에스티", "지에스이", "지에스인스트루먼트", "지에스인스트루", "지엔뷰", "지엔코", "지엠지", "지엠피", "한국지역난방공사", "지역난방공사", "지코", "진도", "진로", "진로발효", "진매트릭스", "진바이오텍", "진성티이씨", "진양산업", "진양제약", "진양폴리우레탄", "진양폴리", "진양홀딩스", "진양화학", "진흥기업", "진흥저축은행", "차바이오앤디오스텍", "차바이오앤", "차이나그레이트스타인터내셔널리미티드", "차이나그레이트", "차이나킹하이웨이홀딩스리미티드", "차이나킹", "차이나하오란리사이클링유한공사", "차이나하오란", "참엔지니어링", "참좋은레져", "참테크글로벌", "참테크", "창해에너지어링", "처음앤씨", "천일고속", "청담러닝", "청보산업", "청호컴넷", "체시스", "초록뱀미디어", "초록뱀", "카스", "카프로", "캔들미디어", "캠시스", "컴투스", "케이디미디어", "케이디씨", "케이비글로벌스타게임앤앱스기업인수목적", "케이비게임앤앱스스팩", "케이비물산", "케이비테크놀러지", "케이비티", "케이씨에스", "케이씨텍", "케이씨피드", "케이아이씨", "케이아이엔엑스", "케이아이티", "케이알제2호개발전문위탁관리부동산투자회사", "케이알제2호", "케이앤컴퍼니", "케이에스씨비", "케이에스리소스", "케이에스알", "케이에스피", "케이엔더블유", "케이엔디티앤아이", "케이엔디티", "케이엘넷", "케이엠", "케이엠더블유", "케이엠컬쳐", "케이티롤", "케이티스", "케이프", "케이피에프", "케이피엠테크", "케이피케미칼", "케이피티유", "케이피티", "켐트로닉스", "코닉글로리", "코다코", "코데즈컴바인", "코디에스", "코라오홀딩스", "코렌", "코렌텍", "코리아퍼시픽01호선박투자회사", "코리아01호", "코리아퍼시픽02호선박투자회사", "코리아02호", "코리아퍼시픽03호선박투자회사", "코리아03호", "코리아퍼시픽04호선박투자회사", "코리아04호", "코리아퍼시픽05호선박투자회사", "코리아05호", "코리아퍼시픽06호선박투자회사", "코리아06호", "코리아퍼시픽07호선박투자회사", "코리아07호", "코리아2000", "코리아나화장품", "코리아나", "코리아본뱅크", "코리아써키트", "코리아에스이", "코리안리재보험", "코리안리", "코맥스", "코메론", "코미코", "코미팜", "코스맥스", "코스모신소재", "코스모화학", "코스텍시스", "코아로직", "코아스웰", "코아스", "코아에스앤아이", "코아크로스", "코엔텍", "코오롱", "코오롱건설", "코오롱생명과학", "코오롱아이넷", "코오롱인더스트리", "코오롱인더", "코오롱플라스틱", "코원시스템", "코원", "코웰이홀딩스유한공사", "코웰이홀딩스", "코위버", "코캄", "코콤", "코크렙제15호기업구조조정부동산투자회사", "코크렙15호", "코크렙제8호위탁관리부동산투자회사", "코크렙8호", "코텍", "코프라", "콤텍시스템", "큐렉소", "큐로컴", "큐로홀딩스", "큐리어스", "큐앤에스", "큐에스아이", "큐캐피탈파트너스", "큐캐피탈", "크라운제과", "크레듀", "크로바하이텍", "크루셜텍", "크리스탈지노믹스", "크리스탈", "크린앤사이언스", "클라스타", "클루넷", "키스톤글로벌", "키움제1호기업인수목적", "키움스팩1호", "키움증권", "키이스트", "탑금속", "탑엔지니어링", "태경산업", "태경화학", "태광", "태광산업", "태림포장공업", "태림포장", "태산엘시디", "태양금속공업", "태양금속", "태양기전", "태양산업", "태영건설", "태웅", "태원물산", "태창파로스", "태평양물산", "태평양제약", "터보테크", "테라리소스", "테라움", "테라젠이텍스", "테스", "테크노세미켐", "텍셀네트컴", "텔레칩스", "텔레필드", "텔코웨어", "토비스", "토자이홀딩스", "토탈소프트뱅크", "토탈소프트", "토필드", "톱텍", "투비소프트", "투에버", "트레이스", "특수건설", "티모테크놀로지", "티모", "티브로드도봉강북방송", "티씨케이", "티에스엠텍", "티에스이", "티에이치엔", "티엘아이", "티이씨앤코", "티케이케미칼", "티플랙스", "티피씨", "티피씨글로벌", "팀스", "팅크웨어", "파나진", "파라다이스", "파라다이스산업", "파라텍", "파루", "파브코", "파세코", "파워_K100", "파워로직스", "파워벨리", "파인디앤씨", "파인디지털", "파인테크닉스", "파캔오피씨", "파캔OPC", "파트론", "팜스웰바이오", "팜스코", "팜스토리한냉", "팬엔터테인먼트", "퍼스텍", "퍼시스", "페이퍼코리아", "평산", "평화산업", "평화정공", "평화홀딩스", "포메탈", "포비스티앤씨", "포스코아이씨티", "포스코_ICT", "포스코강판", "포스코엠텍", "포스코켐텍", "포인트아이", "폴리비전", "폴리플러스", "푸드웰", "푸른기술", "푸른상호저축은행", "푸른저축은행", "풀무원홀딩스", "풍강", "풍국", "풍국주정공업", "풍국주정", "풍림산업", "풍산", "풍산홀딩스", "프럼파스트", "프로텍", "프롬써어티", "프리엠스", "프리진", "프린톤", "플랜티넷", "플렉스컴", "피델릭스", "피씨디렉트", "피앤텔", "피에스앤지", "피에스엠씨", "피에스케이", "피에스텍", "피엘에이", "피엘케이", "피제이메탈", "피제이전자", "필룩스", "필링크", "필코전자", "하나그린기업인수목적회사", "하나그린스팩", "하나금융지주", "하나유비에스암바토비니켈해외자원개발투자회사제1호", "하나니켈1호", "하나유비에스암바토비니켈해외자원개발투자회사제2호", "하나니켈2호", "하나마이크론", "하나투어", "하림", "하림홀딩스", "하이골드오션2호선박투자회사", "하이골드2호", "하이닉스반도체", "하이닉스", "하이록코리아", "하이마트", "하이소닉", "하이스틸", "하이쎌", "하이제1호기업인수목적", "하이제1호스팩", "하이텍팜", "하이트론씨스템즈", "하이트론", "하이트맥주", "하이트홀딩스", "하츠", "한광", "한국가구", "한국가스공사", "한국개발금융", "한국경제티브이", "한국경제TV", "한국공항", "한국투자금융지주", "한국금융지주", "한국기업평가", "한국내화", "한국단자공업", "한국단자", "한국사이버결제", "한국석유공업", "한국석유", "한국선재", "한국쉘석유", "한국투자신성장1호기업인수목적회사", "한국스팩1호", "한국월드와이드아시아태평양특별자산1호투자회사", "한국아태특별", "한국알콜산업", "한국알콜", "한국유리공업", "한국유리", "한국베트남십오의일유전해외자원개발투자회사", "한국유전151", "한국자원투자개발", "한국저축은행", "한국전력공사", "한국전력", "한국전자금융", "한국전자인증", "한국전자홀딩스", "한국정밀기계", "한국정보공학", "한국정보통신", "한국제지", "한국종합기술", "한국종합캐피탈", "한국주강", "한국주철관공업", "한국주철관", "한국철강", "한국체인공업", "한국카본", "한국캐피탈", "한국컴퓨터", "한국콜마", "한국큐빅", "한국타이어", "한국토지신탁", "한국트로닉스", "한국특수형강", "한국팩키지", "한국프랜지공업", "한국프랜지", "한국항공우주산업", "한국항공우주", "한국화장품", "한국화장품제조", "한글과컴퓨터", "한네트", "한농화성", "한독약품", "한라건설", "한라공조", "한라아이엠에스", "한라IMS", "한림창업투자", "한림창투", "한미글로벌건축사사무소", "한미글로벌", "한미반도체", "한미약품", "한미홀딩스", "티브로드한빛방송", "한빛방송", "한빛소프트", "한샘", "한섬", "한성기업", "한성엘컴텍", "한세실업", "한세예스24홀딩스", "한솔인티큐브", "한솔제지", "한솔케미칼", "한솔테크닉스", "한솔홈데코", "한솔CSN", "한솔피엔에스", "한솔PNS", "한스바이오메드", "한신공영", "한신기계공업", "한신기계", "한양디지텍", "한양이엔지", "한양증권", "한올바이오파마", "한울로보틱스", "한익스프레스", "한일건설", "한일단조공업", "한일단조", "한일사료", "한일시멘트", "한일이화", "한일철강", "한일화학공업", "한일화학", "한국전력기술", "한전기술", "한전산업개발", "한전산업", "한전KPS", "한진", "한진중공업", "한진중공업홀딩스", "한진피앤씨", "한진해운", "한진해운홀딩스", "한창", "한창산업", "한창제지", "한텍엔지니어링", "한텍", "한통데이타", "한화", "한화손해보험", "한화증권", "한화케미칼", "한화타임월드", "한화에스브이명장제1호기업인수목적", "한화SV스팩1호", "해덕파워웨이", "해성산업", "행남자기", "허메스홀딩스", "헤스본", "현대건설", "현대그린푸드", "현대글로비스", "현대디지탈테크", "현대디지탈텍", "현대모비스", "현대미포조선", "현대백화점", "현대비앤지스틸", "현대산업개발", "현대산업", "현대종합상사", "현대상사", "현대상선", "현대시멘트", "현대아이티", "현대약품", "현대에이치씨엔", "현대엘리베이터", "현대엘리베이", "현대위아", "현대인프라", "현대정보기술", "현대제철", "현대중공업", "현대증권", "현대드림투게더기업인수목적", "현대증권스팩1호", "현대자동차", "현대차", "현대통신", "현대피앤씨", "현대하이스코", "현대해상화재보험", "현대해상", "현대홈쇼핑", "현대EP", "현우산업", "현진소재", "혜인", "호남석유화학", "호남석유", "호이드연구소", "호텔신라", "홈센타", "홈캐스트", "화성", "화성산업", "화승알앤에이", "화승인더스트리", "화승인더", "화신", "화신정공", "화신테크", "화인텍", "화일약품", "화진", "화천기계", "화천기공", "화풍집단지주회사", "화풍집단_KDR", "환인제약", "황금에스티", "효성", "효성오앤비", "효성아이티엑스", "효성ITX", "후너스", "후성", "휘닉스소재", "휘닉스커뮤니케이션즈", "휘닉스컴", "휠라코리아", "휴니드테크놀러지스", "휴니드", "휴맥스", "휴맥스홀딩스", "휴먼텍코리아", "휴바이론", "휴비츠", "휴스틸", "휴온스", "휴켐스", "흥구석유", "흥국", "흥국화재해상보험", "흥국화재", "흥아해운", "희림종합건축사사무소", "희림", "히든챔피언제1호기업인수목적", "히든챔피언스팩1호", "히스토스템", "에이디모터스", "AD모터스", "에이제이에스", "AJS", "에이피시스템", "AP시스템", "BS금융지주", "비티앤아이", "BT&I", "BYC", "씨앤에스자산관리", "C&S자산관리", "CJ", "씨제이씨지브이", "CJ_CGV", "씨제이이앤엠", "CJ_E&M", "CJ씨푸드", "씨제이오쇼핑", "CJ오쇼핑", "CJ제일제당", "씨제이프레시웨이", "CJ프레시웨이", "씨앤에이치", "CNH", "씨에스", "CS", "CS홀딩스", "씨티앤티", "CT&T", "씨유전자", "CU전자", "DGB금융지주", "디엠에스", "DMS", "디에스제강", "DS제강", "디에스알제강", "DSR제강", "E1", "EG", "이엠엘에스아이", "EMLSI", "이엠따블유", "EMW", "F&F", "FIRST_스타우량", "지투알", "GⅡR", "지러닝", "G러닝", "GIANT_현대차그룹", "그랜드코리아레저", "GKL", "GREAT_GREEN", "GREAT_SRI", "GS", "GS건설", "지에스글로벌", "GS글로벌", "GS홈쇼핑", "글로벌스탠다드테크놀로지", "GST", "지티앤티", "GT&T", "에이치엔에이치글로벌리소스", "H&H", "HIT_골드", "HIT_보험", "에이치엠씨투자증권", "HMC투자증권", "에이치알에스", "HRS", "아이비스포츠", "IB스포츠", "아이비케이에스스마트에스엠이기업인수목적1호", "IBKS스팩1호", "아이에이치큐", "IHQ", "아이엠비씨", "iMBC", "아이에스시테크놀러지", "ISC", "아이티엑스시큐리티", "ITX시큐리티", "제이씨엔터테인먼트", "JCE", "제이에스전선", "JS전선", "제이더블유중외신약", "JW중외신약", "JW중외제약", "제이더블유홀딩스", "JW홀딩스", "제이와이피엔터테인먼트", "JYP_Ent.", "KB금융지주", "KB금융", "케이비오토시스", "KB오토시스", "케이씨그린홀딩스", "KC그린홀딩스", "케이씨코트렐", "KC코트렐", "케이씨씨", "KCC", "케이씨씨건설", "KCC건설", "케이씨아이", "KCI", "케이씨티시", "KCTC", "케이씨더블류", "KCW", "케이이씨", "KEC", "KG케미칼", "케이지피", "KGP", "KH바텍", "KINDEX_국고채", "KINDEX_삼성그룹EW", "KINDEX_삼성그룹SW", "KINDEX_코스닥스타", "KINDEX_펀더멘털대", "KINDEX_F15", "KINDEX200", "KISCO홀딩스", "케이제이프리텍", "KJ프리텍", "KMH", "케이엔엔", "KNN", "KODEX_200", "KODEX_건설", "KODEX_골드선물", "KODEX_구리선물", "KODEX_국고채", "KODEX_레버리지", "KODEX_반도체", "KODEX_보험", "KODEX_삼성그룹", "KODEX_소비재", "KODEX_에너지화학", "KODEX_운송", "KODEX_은선물", "KODEX_은행", "KODEX_인버스", "KODEX_자동차", "KODEX_조선", "KODEX_증권", "KODEX_철강", "KODEX_콩선물", "KODEX_태양광", "KODEX_Brazil", "KODEX_China_H", "KODEX_Japan", "KOSEF_200", "KOSEF_고배당", "KOSEF_국고채", "KOSEF_단기자금", "KOSEF_미국달러선물", "KOSEF_미달러인버스", "KOSEF_블루칩", "KOSEF_인버스", "KOSEF_통안채", "KOSEF_펀더멘탈대형", "KOSEF_Banks", "KOSEF_IT", "KOSEF_KRX100", "KPX그린케미칼", "케이피엑스라이프사이언스", "KPX생명과학", "KPX케미칼", "KPX홀딩스", "KPX화인케미칼", "케이에스에스해운", "KSS해운", "KStar_5대그룹주", "KStar_국고채", "KStar_레버리지", "KStar_수출주", "KStar_우량업종", "KStar_우량회사채", "KStar_코스닥엘리트", "케이티", "KT", "케이티앤지", "KT&G", "KT뮤직", "케이티서브마린", "KT서브마린", "케이티비투자증권", "KTB투자증권", "케이티씨에스", "KTcs", "케이티하이텔", "KTH", "LG", "엘지디스플레이", "LG디스플레이", "LG상사", "LG생명과학", "LG생활건강", "LG유플러스", "LG이노텍", "LG전자", "LG패션", "엘지하우시스", "LG하우시스", "LG화학", "엘아이지손해보험", "LIG손해보험", "엘아이지에이디피", "LIG에이디피", "LS", "LS네트웍스", "LS네트웍스전환상환", "LS산전", "MDS테크놀로지", "MDS테크", "MH에탄올", "엔씨비네트웍스", "NCB네트웍스", "NH투자증권", "엔에이치엔", "NHN", "NI스틸", "NICE홀딩스", "NICE", "NICE신용평가정보", "NICE평가정보", "엔피씨", "NPC", "오씨아이", "OCI", "오씨아이머티리얼즈", "OCI머티리얼즈", "피엔풍년", "PN풍년", "포스코", "POSCO", "PREX_네오밸류", "PREX_LG그룹&", "에스엔케이폴리텍", "S&K폴리텍", "S&T대우", "S&T모터스", "S&T중공업", "S&T홀딩스", "S&TC", "에스비아이글로벌인베스트먼트", "SBI글로벌", "에스비아이인베스트먼트", "SBI인베스트먼트", "SBS", "에스비에스미디어홀딩스", "SBS미디어홀딩스", "에스비에스콘텐츠허브", "SBS콘텐츠허브", "에스디엔", "SDN", "에스지엔지", "SG&G", "에스지세계물산", "SG세계물산", "에스지충남방적", "SG충남방적", "에스지에이", "SGA", "SH에너지화학", "SIMPAC", "심팩메탈로이", "SIMPAC_METALLOY", "에스제이엠", "SJM", "에스제이엠홀딩스", "SJM홀딩스", "SK", "에스케이씨앤씨", "SK_C&C", "SK가스", "SK네트웍스", "에스케이브로드밴드", "SK브로드밴드", "SK이노베이션", "SK증권", "에스케이커뮤니케이션즈", "SK컴즈", "SK케미칼", "SK텔레콤", "에스케이씨", "SKC", "SKC솔믹스", "SKC_솔믹스", "뉴그리드", "SMEC", "에스엔에이치", "SNH", "SOil", "에스에스씨피", "SSCP", "에스티에스반도체통신", "STS반도체", "STX", "STX메탈", "STX엔진", "STX조선해양", "STX팬오션", "티씨씨동양", "TCC동양", "TIGER_200", "TIGER_가치주", "TIGER_건설기계", "TIGER_국채3", "TIGER_그린", "TIGER_금속선물", "TIGER_금융", "TIGER_금은선물", "TIGER_나스닥100", "TIGER_농산물선물", "TIGER_라틴", "TIGER_레버리지", "TIGER_미드캡", "TIGER_미디어통신", "TIGER_반도체", "TIGER_브릭스", "TIGER_블루칩30", "TIGER_삼성그룹", "TIGER_에너지화학", "TIGER_원유선물", "TIGER_은행", "TIGER_인버스", "TIGER_인버스국채3Y", "TIGER_자동차&유통", "TIGER_제약&바이오", "TIGER_조선운송", "TIGER_차이나", "TIGER_철강소재", "TIGER_코스닥프리미", "TIGER_필수소비재", "TIGER_현대차그룹", "TIGER_IT", "TIGER_KRX100", "TIGER_LG그룹", "TIGER_S&P500선물", "티제이미디어", "TJ미디어", "티피씨메카트로닉스", "TPC", "TREX_200", "TREX_중소형가치", "티에스씨멤시스", "TSC_멤시스", "브이지엑스인터내셔널", "VGX인터", "위스컴", "WISCOM", "와이비엠시사닷컴", "YBM시사닷컴", "와이앤케이코리아", "YNK코리아", "와이티엔", "와이지엔터테인먼트", "YTN", "YG엔터"
    };
  
}