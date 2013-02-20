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
	String str ="�ش� �����Ͱ� �����ϴ�. �ٽ� �Է����ֽʽÿ�";
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
	
	int begin_q;// = 201201;	//�ɼ� �޴� ����
	int end_q;						//�ɼ� �޴� ����
	int this_w;// = 20120702;	//�ɼ� �޴� ����
	int sub_menu1 = -1;	//�ɼ� �޴� ����
	int sub_menu2 = -1;	//�ɼ� �޴� ����
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
    	et.setText("���� �� �Ǵ� ���� �ڵ�");
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
    							dialog = ProgressDialog.show(QTS.this, "", "������ ���� ��(EPS ������ ����)..\n��ü ����", true, true);
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
    							dialog = ProgressDialog.show(QTS.this, "", "������ ���� ��(����� ������ ����)..\n��ü ����", true, true);
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
    							dialog = ProgressDialog.show(QTS.this, "", "������ ���� ��(�������� ������ ����)..\n��ü ����", true, true);
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
    							dialog = ProgressDialog.show(QTS.this, "", "������ ���� ��(EPS ������ ����)..\n�ð� �Ѿ� 5��� �̻�", true, true);
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
    							dialog = ProgressDialog.show(QTS.this, "", "������ ���� ��(����� ������ ����)..\n�ð� �Ѿ� 5��� �̻�", true, true);
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
    							dialog = ProgressDialog.show(QTS.this, "", "������ ���� ��(�������� ������ ����)..\n�ð� �Ѿ� 5��� �̻�", true, true);
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
                				dialog = ProgressDialog.show(QTS.this, "", "������ ���� ��(EPS ������ ����)..\n�ð� �Ѿ� 1õ�� �̻�", true, true);
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
                				dialog = ProgressDialog.show(QTS.this, "", "������ ���� ��(����� ������ ����)..\n�ð� �Ѿ� 1õ�� �̻�", true, true);
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
                				dialog = ProgressDialog.show(QTS.this, "", "������ ���� ��(�������� ������ ����)..\n�ð� �Ѿ� 1õ�� �̻�", true, true);
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
                				dialog = ProgressDialog.show(QTS.this, "", "������ ���� ��(EPS ������ ����)..\n�ð� �Ѿ� 2õ�� �̻�", true, true);
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
                				dialog = ProgressDialog.show(QTS.this, "", "������ ���� ��(����� ������ ����)..\n�ð� �Ѿ� 2õ�� �̻�", true, true);
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
                				dialog = ProgressDialog.show(QTS.this, "", "������ ���� ��(�������� ������ ����)..\n�ð� �Ѿ� 2õ�� �̻�", true, true);
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
                				dialog = ProgressDialog.show(QTS.this, "", "������ ���� ��(EPS ������ ����)..\n�ð� �Ѿ� 5õ�� �̻�", true, true);
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
                				dialog = ProgressDialog.show(QTS.this, "", "������ ���� ��(����� ������ ����)..\n�ð� �Ѿ� 5õ�� �̻�", true, true);
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
                				dialog = ProgressDialog.show(QTS.this, "", "������ ���� ��(�������� ������ ����)..\n�ð� �Ѿ� 5õ�� �̻�", true, true);
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
                				dialog = ProgressDialog.show(QTS.this, "", "������ ���� ��(EPS ������ ����)..\n�ð� �Ѿ� 1�� �̻�", true, true);
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
                				dialog = ProgressDialog.show(QTS.this, "", "������ ���� ��(����� ������ ����)..\n�ð� �Ѿ� 1�� �̻�", true, true);
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
                				dialog = ProgressDialog.show(QTS.this, "", "������ ���� ��(�������� ������ ����)..\n�ð� �Ѿ� 1�� �̻�", true, true);
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
			
			dialog = ProgressDialog.show(QTS.this, "", MyApp.getGlobalString()+"\n���� �ҷ�����  ��..", true, true);
			new Thread(new Runnable() {
		        
		        public void run() {
//		        	tmp_stock = getSValue(MyApp.getGlobalString());// 20111024 ����
		        	char c2 = MyApp.getGlobalString().charAt(1);// 20111024 ����
		        	
//		        	if(tmp_stock == null){// 20111024 ����
		        	if( (c2 < '0' || c2 > '9')){// 20111024 ����
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
						tmp_stock = getSValue(MyApp.getGlobalString()); // 20111024 ����
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
				
				dialog = ProgressDialog.show(QTS.this, "", "�ҷ����� ��..", true, true);
				new Thread(new Runnable() {
			        
			        public void run() {
//			        	tmp_stock = getSValue(MyApp.getGlobalString());// 20111024 ����
			        	char c = MyApp.getGlobalString().charAt(1);// 20111024 ����
			        	
//			        	if(tmp_stock == null){// 20111024 ����
			        	if( c < '0' || c > '9'){// 20111024 ����
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
							tmp_stock = getSValue(MyApp.getGlobalString()); // 20111024 ����
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
				// �ش� ������ url�� �����Ѵ�.
				
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
				
				// �ش� ������ url�� �����Ѵ�.
				
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
//    		eUrl = urlString.substring(urlString.lastIndexOf("/")+1, urlString.length()); // �ѱ۰� ������ ������ �κ�
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
/*				���� �ɼ� 1(�ι� Ŭ��)
				if(!isTwoClickBack){
					Toast.makeText(this, "'�ڷ�'��ư�� �ѹ��� Ŭ�� �Ͻø� ����˴ϴ�." , Toast.LENGTH_SHORT).show();
					CntTimer timer = new CntTimer(2000, 1);
					timer.start();
				}else{
					finish();
					return true;
				} 
//*/
//*				���� �ɼ� 2 (���� Ȯ�� â)
				end2.setMessage("My Analyst��\n�����Ͻðڽ��ϱ�?");
				end2.setPositiveButton("��", new DialogInterface.OnClickListener() {
					
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
				end2.setNegativeButton("�ƴϿ�", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				end2.show();
//*/
			}else if(keyCode == KeyEvent.KEYCODE_MENU){
				getVC();
				bld = new AlertDialog.Builder(QTS.this);
				bld.setMessage("My Analyst ����: "+String.format("%.3f", Float.valueOf(version))+"\n\n" +
						"�� ���α׷��� �ֽ� ������ ���� �ڷ�� Ȱ��� �� �ִ� ������ �پ��� ��Ʈ�� �������� ǥ���ϰ�, " +
						"������ ��Ʈ�� ���� ������ �߰������ν�, �����ϰ��� �ϴ� ȸ���� �繫 ���¸� �ľ��� �� �ֵ��� �մϴ�.\n\n" +
						"[���α׷� ��� ���]\n" +
						"�Ʒ��� [���� ����] ��ư�� ������ �ش� �������� �̵��մϴ�.\n\n" +
						"�� [�ǰ� ����] ��ư�� �̿��Ͽ� �ǰ��� ���� �ֽø�, " +
						"���� �� ���� ������ �ݿ���� �ϰڽ��ϴ�.\n\n" +
						"���ۻ� ����\n\n" +
						"Quantec. co., ltd\n" +
						"http://quantec.co.kr");
				bld.setPositiveButton("�ݱ�", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
					}
				});
			
				bld.setNeutralButton("���� ����", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://quantec.co.kr/intro_mobile.html")));
					}
				});
				bld.setNegativeButton("�ǰ� ����", new DialogInterface.OnClickListener() {
					
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
    	    canvas.drawText("�췮 ���� Ž����", (float)Icon_t2.getWidth()+5f, menu_pos+menu_dist/2+menu_text_dist, W_30);
    	    
    	    if(sub_menu > 0){
//    	    	canvas.drawRect((float)0, menu_pos+menu_dist, (float)width*4/9, menu_pos+menu_dist*4, W_30);
    	    	for(int mp=0; mp<5; mp++){
    	    		canvas.drawBitmap(Icon_t3, 0, menu_pos+menu_dist*(mp+1), pnt2);
    	    		canvas.drawBitmap(Icon_soff, (float)width*4/9-Icon_soff.getWidth()-5f, menu_pos+menu_dist*(mp+1), pnt);
    	    	}
				canvas.drawText("EPS ������ ����", 	15f, menu_pos+menu_text_dist+menu_dist*3/2, W_30);
				canvas.drawText("����� ����", 		15f, menu_pos+menu_text_dist+menu_dist*5/2, W_30);
				canvas.drawText("�������� ����", 		15f, menu_pos+menu_text_dist+menu_dist*7/2, W_30);
				////////////////////////////////////////////// 2013.02.20 �޴� �߰�
				canvas.drawText("�ӽ� �޴� 1", 		15f, menu_pos+menu_text_dist+menu_dist*9/2, W_30);
				canvas.drawText("�ӽ� �޴� 2", 		15f, menu_pos+menu_text_dist+menu_dist*11/2, W_30);
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
    				////////////////////////////////////////////// 2013.02.20 �޴� �߰�
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
				canvas.drawText("��ü ����", 				(float)width*4/9+Pnter.getWidth()+15f, menu_pos+menu_text_dist+menu_dist*3/2, W_30);
				canvas.drawText("�ð��Ѿ� 5��� �̻�", (float)width*4/9+Pnter.getWidth()+15f, menu_pos+menu_text_dist+menu_dist*5/2, W_30);
				canvas.drawText("�ð��Ѿ� 1õ�� �̻�", (float)width*4/9+Pnter.getWidth()+15f, menu_pos+menu_text_dist+menu_dist*7/2, W_30);
				canvas.drawText("�ð��Ѿ� 2õ�� �̻�", (float)width*4/9+Pnter.getWidth()+15f, menu_pos+menu_text_dist+menu_dist*9/2, W_30);
				canvas.drawText("�ð��Ѿ� 5õ�� �̻�", (float)width*4/9+Pnter.getWidth()+15f, menu_pos+menu_text_dist+menu_dist*11/2, W_30);
				canvas.drawText("�ð��Ѿ� 1�� �̻�", (float)width*4/9+Pnter.getWidth()+15f, menu_pos+menu_text_dist+menu_dist*13/2, W_30);
				
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
//				2011. 10. 15 ���� �� �׽�Ʈ    		
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
    			update.setTitle("�� ���� "+String.format("%.3f", Float.valueOf(version))+" ������Ʈ ����");
	    		update.setMessage("�̹� ������Ʈ�� ���� ������ �̷���� �κ��� �Ʒ��� �����ϴ�.\n\n" +
	    				"1. �ְ� ���� �ҷ����� ���� �ذ�\n" +
	    				"  - �Ϻ� ���� �˻��� �ְ� ������ ����� �������� ���ϴ� ���� �ذ�.");
	    		update.setPositiveButton("�ݱ�", new DialogInterface.OnClickListener() {
					
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
//			2011. 10. 15 ���� �� �׽�Ʈ    	
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
//		menu.add(0, 2, 1, "���� ����");
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
    	"3������Ż�׷����Ѱ���", "3������Ż", "��������ġ", "3H", "�￡���ڸ���", "3S", "�����", "���¹̵��", "��������", "����", "����ĳ��Ż", "��������", "���������", "�����þ�Ŀ�´����̼���", "�����þ�����", "�źϼ�4ȣ��������ȸ��", "�źϼ�4ȣ", "�źϼ�5ȣ��������ȸ��", "�źϼ�5ȣ", "�źϼ�6ȣ��������ȸ��", "�źϼ�6ȣ", "�źϼ�7ȣ��������ȸ��", "�źϼ�7ȣ", "�Ǽ�ȭ�а���", "�Ǽ�ȭ��", "���Ӻ�", "��������", "�泲���", "�泲��ƿ", "�泲������", "�泲����", "���", "�浿���ð���", "�浿����", "�浿����", "�浿����", "���", "���", "���ξ���", "��������", "��â���", "���Ǽ����", "���Ǽ�", "�������", "�������", "����ݵ�ü�ý���", "����ݵ�ü", "������", "����ſ�����", "����ƿ�", "�������", "�������", "���������", "����ũ���", "��", "��糪�����������ڱ�����ε�������ȸ��", "��糪������", "���긴����������", "���긴������", "������", "��������", "����", "��������", "������", "���ֽż���", "�����Ǿ�", "��������", "���񰳹������ڱ�����ε�������ȸ��", "������", "��������", "��������Ƽ�����μ�����", "����KTB����", "������ũ", "����ȭ��", "����", "����", "����������", "������", "�������ؿ�", "��������", "���������", "������ǰ����", "������ǰ", "��������Ʈ���ڸ���", "��������Ʈ��", "�׷����ȭ��", "�׸��������", "�׸����غ���", "�ص���ƿ", "�ص���ȭ", "��ȭ����", "�۷ι���������ũ����Ƽ��", "�۷ι�������", "�۷ν���", "�ݰ�����", "�ݰ�ö��", "�ݺ�", "�ݻ�", "�ݼ���ũ", "�ݾ�", "��ȣ���", "��ȣ����ȭ��", "��ȣ����", "��ȣ����", "��ȣ���ձ���", "��ȣ����", "��ȣŸ�̾�", "��ȭ�ǿ�����", "�⸢����", "����ڷ���", "�������", "����ڵ���", "�����", "�߼ұ������", "�������", "�����ѳ���", "����ż���", "���뿣��", "����ķ��", "����Ʈ�δн�", "���󿥾ص�", "������", "���̺�", "���̽��������", "�������", "�����˹̴�", "����", "��������", "��������", "����ȭ��", "�׽���ũ", "�׿�����", "�׿�����", "�׿����������", "�׿��������ͳ�", "�׿�Ƽ��", "�׿���", "�׿�����", "�׿��ǵ���Ƽ", "���߷�", "���н�", "���н��ż���", "�����ξ���Ƽ", "�ؼ�", "�ؼ�Ÿ�̾�", "�ؼ���ũ", "�ؽ���", "�ؽ�����ũ���", "�ؽ�����ũ", "�ؽ���", "�ؽ�Ʈ����", "�ؽ�ƮĨ", "�ݿ��̺�", "���Ǿ�", "�������Ʈ", "���Ȧ����", "�����", "�����Ȧ����", "���", "���Ȧ����", "���ȸ����γ����̿�", "�����̿�", "�����ڷ���", "�����÷�", "������ũ", "������", "������", "�������̵����۷��̼�", "�������̵�", "��������", "�ɷ�����", "��Ʈ�������۴�", "�ٳ���", "�ٳ�", "�ٹɸ�Ƽ�̵��", "�ٻ��Ʈ����", "�ٽ���", "�ٿ���", "�ٿ쵥��Ÿ", "�ٿ��ý�", "������", "����Ŀ�´����̼�", "����", "��ũȣ��", "��������", "�������", "�����", "�뱳", "�뱸���", "�뱸��ȭ��", "�뱹", "�������", "���GDS", "�뵿����", "�뵿�ݼ�", "�뵿���", "�뵿��ƿ", "�뵿����", "�������", "�븲���", "�븲����", "�븲���", "�븲�����", "�븲B&Co", "�������������", "�����������", "���������", "���", "���Ȧ����", "�뼺�̻���������", "�뼺�̻���", "�뼺���", "�뼺������", "�뼺����", "�뼺â������", "�뼺â��", "�뼺������", "�뼺�յ�����", "�뼺Ȧ����", "����������", "�������", "������Ǳ׷ξ����ı���μ�����", "������Ǳ׷ξ�����", "���Ƽ����", "���۷ι�", "���ݼ�", "����������", "�����������", "�������", "�뿵����", "���Ǽ�", "������ں�ǰ", "����ǰ", "������ͳ��ų�", "��������ؾ�", "�������", "������Ǳ׸��ڸ��Ʊ���μ�����ȸ��", "������ǽ���", "����ڵ����Ǹ�", "������Ǹ�", "���", "�������", "�������", "����̵��", "������", "�������", "�������", "���ȭ��", "�����ż���", "����������", "����ȭ��", "���ֻ��", "�����������", "��������", "��â", "��â����", "��â��Ż", "���ѵ��ð���", "���Ѱ���", "���Ѵ���", "���ѹ��̿�", "���ѹ���", "���ѻ�����", "���ѻ���", "���Ѿ�ǰ����", "���Ѿ�ǰ", "������ȭ����", "������ȭ", "����������", "��������", "��������", "��������", "��������", "�������ջ��", "�������", "�����װ�", "�����ؿ�", "����ȭ��", "����", "��ȣ���̿�", "��ȣ�Ǿؽ�", "��ȣ�Ǿؾ�", "��ȭ����", "���������", "��ü����", "�������̸�Ż", "����", "������", "���ڳ�Ƽ��", "����ġ���ͽ�", "��ȭ�����Ͼ", "�������", "�����Ǿ�", "�����˾ؿ���", "��������", "��������", "������������", "����S&C", "�����ռ�", "����", "���漱��", "����Ʊ׷�", "���ΰǼ�", "ȭ����ũ���", "���ζ�����", "���ηκ�", "������ö", "��������", "����Ƽ�������ޱ���μ�����", "����Ƽ�������޽���", "����������", "����ȭ���ػ���", "����ȭ��", "���ξ�������", "����CNI", "���Ͼ�10ȣ��������ȸ��", "���Ͼ�10ȣ", "���Ͼ�11ȣ��������ȸ��", "���Ͼ�11ȣ", "���Ͼ�12ȣ��������ȸ��", "���Ͼ�12ȣ", "���Ͼ�13ȣ��������ȸ��", "���Ͼ�13ȣ", "���Ͼ�14ȣ��������ȸ��", "���Ͼ�14ȣ", "���Ͼ�2ȣ��������ȸ��", "���Ͼ�2ȣ", "���Ͼ�3ȣ��������ȸ��", "���Ͼ�3ȣ", "���Ͼ�4ȣ��������ȸ��", "���Ͼ�4ȣ", "���Ͼ�5ȣ��������ȸ��", "���Ͼ�5ȣ", "����", "��������", "����������", "����Ȧ����", "����ȭ��", "���ŰǼ�", "���ƿ�����", "���ƿ���", "���ƿ�", "��������", "��������", "����Ÿ�̾����", "����Ÿ�̾�", "����ȭ��", "���������", "����", "���簭ö", "����Ǽ����", "����Ǽ�", "�����ӿ��", "������", "�������", "�������", "���繰����", "���繰��", "���������Ǳ���μ�����", "����������", "���������", "�������", "����ø�Ʈ", "����ý�����", "���翡����", "�����̿���", "�������ձ�������", "������������", "����ö��", "�����ڷ���", "�����ǿ�����", "����", "����", "��������", "�����ݼ�", "�������", "��������", "�����ý�����", "����F&B", "���ϱݼ�", "���ϱ⿬", "���Ϲ���", "���ϰ���Ʈ", "���Ϻ�Ʈ", "���ϻ��", "��������", "����ö��", "�����Ǽ�", "���������", "��ȭ��ǰ", "��ȭȦ����", "�λ�", "�λ�Ǽ�", "�λ꿣��", "�λ��������ھ�", "�λ��߰���", "�οû��", "�ο��߰���", "������ڸ���", "�巡���ö���", "�帲��", "����̿����ڸ���", "����̿���", "���÷�����ũ", "���÷�����", "�𾾿�", "�����", "����̵�", "����̾�", "��ؼ�", "�𿡽�", "�𿡽�����", "������", "�𿣿���", "�𿥾�", "���", "�����", "������۷ι�", "���̿�Ƽ", "��������", "����Ż�Ƹ���", "�����д뼺", "�����п���", "��������", "�����ؽý��۽�", "�����ؽý���", "����Ʋ�����Ϻ�", "����Ʋ����", "�����̵�ؾ���", "�����̶�", "����ũ�����", "���Ǿ�", "����", "�߷���", "���̺��÷���", "�������غ�", "���ض�", "����ι�", "����ĸ����", "������", "�θ���", "�ο��������θ�Ʈ", "�ο�", "�ο�����", "��ü�ý�����", "����Ʈ����", "�Ե���������", "�Ե��̵���", "�Ե��ﰭ", "�Ե����غ���", "�Ե�����", "�Ե�����", "�Ե�ĥ������", "�Ե�ĥ��", "��ེ", "��̸���ũ��", "�纸", "��Ʈ�δ�", "��տ�����Ȧ����", "��տ�����", "������", "�������", "���뽺", "��������", "����Ʈ", "����", "��Ȩ", "����Ʈ", "����Ŀ", "����Ŀ�´�", "���̴ٽ�_Ŀ������", "���̽���", "����ũ�����ؼַ��", "����ũ�����ؼ�", "��ũ����", "����", "��ȣ����", "���Ͽ���", "��������", "��Ŀ��", "�������ѱ�������������ȸ��", "������������", "�̷����¸ʽ��ƽþ��۽��Ⱥε��������ȣ����ȸ��", "�ʽ�����Ƽ1", "�̷����¸ʽ�����Ʃ��Ƽ��Ʈ���ֽ�ȥ��������ȸ����ȣ", "�ʽ���Ʈ��1", "�ް����͵�", "�޵��彺", "�޵�����Ʈ", "�޵����е��Ƽ", "�޵�����", "�޸�����������", "�޸������ձ�������", "�޸�����������", "�޸���ȭ���ػ���", "�޸���ȭ��", "��Ÿ���̿��޵�", "���Ľ�", "������", "�����", "���", "��ǰ��ī����", "�𳪸���", "�𳪹�", "��������Ʈ��ũ", "�������", "�𸰽�", "����ϸ���", "���̽�", "�������", "�����", "�����", "������", "����������", "�����Ǿ���", "����P&P", "����������", "����SP", "����", "��������", "����ö��", "�̵���÷���", "�̷�������", "�̷����", "�̷�������1ȣ����μ�����", "�̷����½���1ȣ", "�̷���������", "�̷�����", "�̷����۴�", "�̸���", "�̼�������ũ", "�̽�������", "�̿����", "�̿������Ƽ�ɹ�Į", "�̿�������", "�̿�ȭ��", "��������", "��â��������", "��â����", "�ٴٷ�3ȣ��������ȸ��", "�ٴٷ�3ȣ", "�ٸ���", "�ٸ��հ�����", "�ٸ�����", "���̳ؽ�", "���̷θ޵�", "���̿��Ͼ�", "���̿�����", "���̿��޵左", "���̿�����Ʈ", "���̿������̽�", "���̿��彺��", "����", "�渲", "���ݼ�", "�鱤���", "�鱤����", "���Ƽ�ؿ���", "���T&A", "���", "�����Ǿ�", "���OPC", "��ũ�̽ʿ�", "���߾���", "����ǿ�", "���������Ͽ콺", "�������Ͽ콺", "��Ʈ������1", "����", "����Ǽ�", "����Ƽ����", "����", "���ɸ޵�ӽ�", "��������", "����׿�����", "����׿�", "�����Ŀ���", "���ؾ���", "�α���ǰ", "�α�����", "�α�ö��", "�α�ǻ�Ľ�Ÿ�����μ�����", "�α�ǻ�Ľ�Ÿ���", "�λ굵�ð���", "�λ갡��", "�λ��������", "�λ����", "�λ���", "�λ��ְ�", "�ν�Ÿ", "�����", "�긮����", "�������ؾ�", "�����", "�����", "������̿�Ƽ", "��غ񼺿�", "�񿡽���Ȧ����", "�񿡽���", "����ġ", "����ġ����", "��Ƽ", "�������", "�����μ�", "�����νý�", "��������ũ", "��Ʈ�νý�", "��Ʈ��ǻ��", "��Ƽ���������", "��Ƽ������", "��ַ�", "����", "���׷�", "��������", "�����븲", "�������", "��������", "������ǥ", "�꼺�Ǿؾ�", "�ﰭ����Ƽ", "�ﱤ����", "�︢����", "�︳��ǰ", "�������", "�ﺸ���佺", "�ﺸ���", "�ﺸ�̿���", "�ﺸ����", "�ﺻ��������", "������", "�Ｚ����", "�Ｚ����", "�Ｚ������", "�Ｚ����", "�Ｚ�����Ͼ", "�Ｚ����", "�Ｚ����", "�Ｚ����ȭ��", "�Ｚ�������", "�Ｚ����", "�Ｚ�߰���", "�Ｚ����", "�Ｚ���ǻ�", "�Ｚī��", "�Ｚ��ũ��", "�Ｚȭ���ػ���", "�Ｚȭ��", "�ＺSDI", "��ƾ˹̴�", "�������", "����", "����ǰ", "��翣��", "����ƽ��", "������ؽ�", "������", "�￵����", "�￵����", "�￵�̿���", "�￵���ڰ���", "�￵����", "�￵����", "�￵Ȧ����", "�￵ȭ�а���", "�￵ȭ��", "����̿���", "�������", "�����ũ", "���;Ǳ�", "����THK", "����", "���ϱ������", "��������", "��������", "��������", "����", "�������ص�", "��������", "��õ������", "��õ��", "��õ��������", "����ö��", "��ȣ", "��ȣ����", "��ȭ��Ʈ����", "��ȭ�հ�", "��ȭ����", "��ȭ���ڰ���", "��ȭ����", "��ȭ�ܵ�������", "��ȭ�ܵ���", "��ȭ����Ʈ����", "��ȭ����Ʈ", "��ȯ���", "��ȯ���", "��", "��ź극��ũ", "����̵���", "���������ũ", "���δн�", "���п����Ƽ��", "��ǥ��ǰ", "����", "����", "�������̿����̾�", "�������̿�", "����Ƽ����", "����T&D", "����", "���ﵵ�ð���", "���ﰡ��", "����ݵ�ü", "�����ǰ����", "�����ǰ", "����ſ�������", "����ſ���", "�������", "�����ȣ��������", "������������", "�����������", "��������", "����", "��������", "����", "��ȣ����", "��ȭ�������", "����İ��", "����Ǽ�", "����", "��������Ʈ", "��������", "����", "��������", "��â���", "������ũ", "��������", "�����̿���", "��������", "����ȭ��", "���ž�ȸ", "����", "��������", "������ũ��", "����������", "���������������Ѱ���", "������������", "�����Ǽ�", "����������", "��â�������", "��â������", "��ȣ����", "��������", "������", "����", "�����������", "��������", "������", "����", "��������", "�������̾�", "���ƺ���ƿ", "��������", "����Ư����", "����Ȧ����", "����۷ι�", "������ũ", "����޵�Į", "��������", "����������", "��������", "���̺������̾ؾ�", "���̺���I&C", "��������", "����", "��������", "����Ƽ����", "���ڴн�", "����", "������", "��Ʈ����", "��Ʈ��������", "�Ҹ��ٴ�", "����Ʈ�ƽ�", "����Ʈ����", "�տ���", "�ְ���̿��޵�Į", "�ְ���̿�", "�ֶ�þ�", "�ַθ���������", "�ֺ���", "�ֺ�", "�ۿ����", "�����߰���", "����", "�ѱ������������", "��������", "������", "���ڻ������", "��������", "���߽�", "��ī�̴���", "����Ƽ��ī�̶�����", "��ī�̶�����", "��Ÿ�÷���", "���۽��̾�", "��ƿ�ö��", "������", "����", "��ȭ��ǰ�Ǽ�", "��ȭ���", "�ð���ũ", "�ñ׳�ƽ��", "�ó��彺", "�ó��彺�׸���ũ", "�Ŵ������", "�ŵ�����", "�Ŷ󱳿�", "�Ŷ���", "�Ŷ󿡽���", "�Źλ�ȣ��������", "�Ź���������", "�ż���Ÿ��ũ", "�ż��ֶ󿡳���", "�ż���������", "�ż��̿���", "�ż����", "�ż���", "�ż�����̾ؾ�", "�ż���_I&C", "�ż���Ǽ�", "�ż������ͳ��ų�", "�ż���Ǫ��", "�ž翣���Ͼ", "�ž�", "�ſ����ڷ�", "�ſ�����", "�ſ�", "�ſ�", "�ſ����հ���", "���ϰǾ�", "���ϻ��", "��������", "��â����", "����", "��ǳ����", "��ǳ����", "����", "������1ȣ����μ�����", "���ѽ���1ȣ", "���ѱ�������ȸ��", "��������", "��ȭ�Ǿ�", "��ȭ������", "����", "�Ǹ��ܿ���", "�Ǹ���ȭ��", "����", "���÷�������", "�ֹ��Ʈ���̱׷�", "�ֹ��Ʈ����", "�ֿ�Ǽ�", "�ֿ��Ƽ����", "�ֿ��ȸ����", "�ֿ��ȸ", "�ֿ��������", "�ֿ��ڵ���", "�ֿ���", "�������", "���Ʈ", "��Ʈ������", "����̿���", "��ٳ�", "����ũ", "�����ǽý���", "���׳��������", "������", "����������Ϲ��", "��������", "���غ���", "���ؿ�����ũ�����", "���ؿ���", "�����������ͳ��ų�", "������������", "����", "��Ƽ�����̿�", "��Ƽ��", "�ư�������۴�", "�ư������۴�", "�Ƴ��н�", "�Ƴ�����", "�Ƴ��������", "�Ʒθ�����Ʈ", "�Ƹ���_K100EW", "�Ƹ���_KOSPI50", "�Ƹ���_KRX100EW", "�Ƹ�����ũ�����", "�Ƹ���", "�Ƹ��۽���", "�Ƹ��۽��ȱ׷�", "�Ƹ�G", "�Ƹ���", "�ƹ̳������", "�ƹ���", "�ƺ�Ÿ", "�ƺ�������", "�Ƽ��ƽø�Ʈ", "�Ƽ�������", "�Ƽ�����", "�Ƽ�����������", "�ƽþ��۽���10ȣ��������ȸ��", "�ƽþ�10ȣ", "�ƽþ��۽���11ȣ��������ȸ��", "�ƽþ�11ȣ", "�ƽþ��۽���12ȣ��������ȸ��", "�ƽþ�12ȣ", "�ƽþ��۽���13ȣ��������ȸ��", "�ƽþ�13ȣ", "�ƽþ��۽���14ȣ��������ȸ��", "�ƽþ�14ȣ", "�ƽþ��۽���15ȣ��������ȸ��", "�ƽþ�15ȣ", "�ƽþ��۽���1ȣ��������ȸ��", "�ƽþ�1ȣ", "�ƽþ��۽���2ȣ��������ȸ��", "�ƽþ�2ȣ", "�ƽþ��۽���3ȣ��������ȸ��", "�ƽþ�3ȣ", "�ƽþ��۽���4ȣ��������ȸ��", "�ƽþ�4ȣ", "�ƽþ��۽���5ȣ��������ȸ��", "�ƽþ�5ȣ", "�ƽþ��۽���6ȣ��������ȸ��", "�ƽþ�6ȣ", "�ƽþ��۽���7ȣ��������ȸ��", "�ƽþ�7ȣ", "�ƽþ��۽���8ȣ��������ȸ��", "�ƽþ�8ȣ", "�ƽþ��۽���9ȣ��������ȸ��", "�ƽþ�9ȣ", "�ƽþƳ��װ�", "��������Ÿ", "�ƽþƹ̵��Ȧ����", "���̳ݽ���", "���̵�Ȧ����", "���̵𿡽�", "���̵�", "���̸���", "���̸����ڸ���", "���̽����̼�", "���̾���", "���̾�������", "���̾�����", "���̾ؾ���ũ�����", "���̾ؾ�", "���̿�������", "���̿�����Ŀ�ӽ�", "���̿�", "���̿��ý���", "���������", "����������Ʈ", "����ũ����Ʈ", "�����عݵ�ü", "���ν�", "���ν����ؿ�", "���ν�M&M", "����ĳ��Ż", "�����ش���������", "������WB", "��ť�عݵ�ü���", "��ť��", "��ť�Ƚ�", "��Ʈ�󽺺񿢽�", "��Ʈ��BX", "��Ʈ������", "��Ƽ��", "�����������", "�ȱ���ǰ", "��ö��������", "�˵���", "�˾ؿ����̿�", "�˾ؿ����", "�˿�����", "�˿�������", "�˿�����", "�ſ���������ο���1ȣ����μ�����", "���潺����", "����Ĩ��", "�ְ�������", "�ְ���ȭ", "���������Ʈ", "��Ʈ", "�ؾ�Ŀ�´�", "������", "���ݵ�ü", "��︲��Ʈ����", "��︲_��Ʈ", "��︲���ý�", "��︲�������", "��︲����", "�������ַ����", "�������ַ��", "������ũ", "���ؽ�", "����ڽ�", "�����", "����Ʈ������", "���ӽ��۽���", "��������", "������ũ��", "�����ݽý���", "������", "������ý���", "������", "��������̾ؼַθ�帲����μ�����", "��������̾ؼַθ�", "������", "��������", "�������̸��ҽ�", "�����ش����", "�����ؿ�����", "����������", "�������̿�Ƽ", "��������Ƽ", "������������", "����������������", "��������", "������", "�������������θ�Ʈ", "������", "������", "������Ƽ����", "�����ڳ�", "������", "�������ĸ�", "����Ƽ��������", "����Ƽ����", "����Ƽ��", "����Ƽť��", "����������", "��������", "������ũ", "�����������Ͼ", "������ũ", "���̵�Ĩ��", "���̸��", "���̺�����", "���̽�������", "���̽�����Ʈ�ʽ�", "���̽�ħ��", "���̽���ũ�����", "���̽���ũ", "���̽�������", "���̾ؾ����̿�Ȧ����", "���̿���Ƽ����", "���̿���", "���̿�����ũ��", "����ġ��ÿ���", "����ġ��Ƽ", "����ġ����", "������ũ�ַ��", "������", "����Ƽ���κ���Ʈ��Ʈ", "����Ƽ���κ���Ʈ", "����������ũ�����", "�������̱۷ι�", "��������", "���ڿ�����Ȧ����", "���ڿ�����", "������Ʈ�νý���", "��������", "�����ö�ƽ", "��������������", "��������", "��������Ƽ", "����Ƽ�̾���", "���ǹ븮", "�����̿���", "��ť����", "������Ʈ", "��������Ʈ", "���˵�", "����������", "����Ƽ", "������", "�����̹��̿�", "���ͱ��", "���ؿ��̵�", "���ڽ�", "��Ƽ�Ǿ�", "��������", "��������ũ", "����Ƽ", "���񼼹���", "���ؾ���", "���ؿ���", "�����ǾƳʽ�", "��������", "����Ƽ��Ũ", "��Ƽ����", "������", "����ó����", "���񼺻�", "��������", "��������", "������������", "����������", "������Ʈ����", "���غ���", "��.��.��", "���Ǿ�", "�����̾�Ƽ", "�����������", "���հ���������Ѱ���", "���հ���", "��������", "����ȭ��", "���űݼӰ���", "���űݼ�", "�������", "��������", "��������Ȧ����", "��������Ƽ��", "������ǰ����", "������ǰ", "����������", "��ǳ", "��ǳ����", "��ǳ����", "��ȭ�ݼ�", "����ö��", "�������۴�", "����", "������", "�����̽ʻ�", "����24", "������", "����", "���ð�����", "������", "���ѱ�", "���ζ����", "���ζ�", "������Ż����", "������Ʈ���̿�", "�ؽ���", "������Ʈ����", "������", "������", "�������̿�", "����������Ƽ", "��������", "������", "���������ö�Ʈ", "����غ���", "����", "���º��̽�", "�¼��ڷ���", "�ȴϽý���", "�ȴ���", "�������", "��Ʈ����", "��Ƽ�ý�", "���̵�¶���", "���̺�ε�", "���̼�", "���̿���", "�������Ŀ�", "��������", "���佺�ڸ���", "�ϸ����ͳ��ų�Ȧ����", "�ϸ�", "�ѱ���ȯ����", "��ȯ����", "������", "����BM", "���ö��", "�������۴�", "�츮��������", "�츮����", "�츮���", "�츮�������", "�츮��", "�츮��������", "�츮������", "�츮���", "�츮����μ�����1ȣ", "�츮����1ȣ", "�츮��Ƽ����", "�츮��������", "�츮��������", "�츮���̳���", "�츲���", "�켺���", "�켺���̺�", "�켺���̾ؾ�", "�켺I&C", "���AMS", "��Ű���", "��Žý���", "�������", "�������Ѵ�", "�����Ϸ�Ʈ�δн�", "�����Ϸ�Ʈ��", "����", "���������", "����������", "��Ʈ��Ǽ�", "������ũ��", "����������", "�����ɹ�Į", "�����ڿ���", "����Ȧ����", "����", "����", "��������", "���;����ǿ���", "����IPS", "����Ư��", "��ǳ", "��ǳ����", "������", "��������Ʈ�𺧷Ӹ�Ʈ", "��������Ʈ", "���̺��Ϸ�Ʈ�δн�", "���̺��Ϸ�Ʈ��", "������Ʈ���Ѱ���", "������Ʈ", "�����̵彺Ÿ��", "�����̵�", "�����긴��", "����Ų����", "����Ųȭ��ǰ", "��ũ��", "����", "�����", "���н�", "���ٽ�", "�����̵忣�����θ�Ʈ", "�����̵�", "�����������", "����Ʈ", "������ũ��", "������", "����", "�ѱ�������Ƽ������", "������Ƽ������", "���ϴ���", "���ϵ�", "���ϸ𾾾ؾ�", "���ϼ�", "���Ͻ�", "���Ͽ�", "���Ͽ½�ƿ", "������", "��������Ʈ", "����ũ", "�����׽�Ʈ", "������", "������ũ", "���񺧷Ͻ�", "������", "�����ɾ�", "��������", "������������", "�������", "����Ƽ������", "����", "�����̵�", "�����̿�����", "DK�����̿�", "�����̿�", "��������", "������", "��������", "��������", "���Ͽ��ý�", "�����޵�Į", "�������", "�����κ�", "������ũ", "������������", "���Ѿ���", "��ȭ����", "����ȭ��", "�̰ǻ��", "�̰�âȣ", "�̱����", "�̱��轺", "�̱ۺ�", "�̱�_��", "�̱۷��ť��Ƽ", "�̳ʽ���", "�̳뼿", "�̳���̾��", "�̳���̾�", "�̳�Ĩ��ũ�����", "�̳�Ĩ", "�̳콺", "�̴Ͻý�", "�̴���", "�̵�", "�̶�����", "�̷���", "�̷��", "�̷�������", "�̸�Ʈ", "�̹�������ũ�����", "�̹�����", "�̻��Ʈ����", "�̼�������", "�̼���Ÿ�ý�", "�̼�ȭ��", "�̽�Ÿ��", "�̽�Ʈ����Ʈ", "�̽�Ʈ�ƽþƽ��������ͳ��ųθ���Ƽ��", "�̽�Ʈ�ƽþƽ�����", "�̾������ڷ���", "�̾�����", "�̿�����", "�̿�������ũ�����", "�̿�����", "�̿��ڸ���", "�̿���", "�̿�����", "�̿���ũ�н�", "�̿�������", "�̿���", "������", "�������̿��ý���", "�������̿�", "���ڸ����ڱ�����ε�������ȸ��", "���ڸ��Ƹ���", "��ť�����ڷ�", "��ũ������", "����ũ�Ǽ�", "��Ʈ���̵�1ȣ����μ�����", "��Ʈ���̵�1ȣ����", "��Ʈ���̵�����", "��ǻ��", "��ȭ����", "��ȭ���", "��ȭ�������", "��ȭ����", "�ε���", "�λ갡", "�μ��̿�Ƽ", "�μ�����", "�ν�����Ʈ", "�������÷���", "�������÷�", "������Ʈ�ѽ�", "��õ���ð���", "��ť����ũ", "��ž��", "���ͷ���", "���Ϳ�", "������ũ", "�����÷���", "�����Ͽ콺", "�����÷���", "��Ʈ�й��̿���ũ�����", "��Ʈ�й��̿�", "����", "��������", "������ũ", "�����Ǿ�", "���������", "���ǳ���ũ", "���Ǵ�Ʈ�ｺ�ɾ�", "��ȭ����", "�ϰ�������", "�ϵ�����", "�ϼ��Ǽ�", "�ϼ��ž�", "�ϽŹ��̿����̽�", "�ϽŹ��̿�", "�ϽŹ���", "�Ͻż���", "�Ͼ�", "�Ͼ��ǰ", "�����Ǿ�", "������ũ", "�������̾Ƹ��", "�������̾�", "�������÷���", "��������", "������Ƽ������", "����������", "��������", "����Ȧ����", "��ũ��ũ", "�ڿ���ȯ��", "�ڿ�", "��������", "��Ƽ����", "��ȭ����", "�߸���ũ", "�翵�ַ���", "����", "��������", "�ѱ����ı�����", "���ı�����", "�������̿�����", "�������ý�", "���ʽý�����", "���ؽ�", "����", "������", "���̴�����", "���콺", "���̺��̿�", "���̾��ɹ�Į", "���̾����ý���", "���̾���", "���̿���������", "���̿�����", "���̿�����", "���̿�Ƽ", "���̿���", "��������Ʈ��", "����Ƽ", "���ϱ�ȹ", "���ϸ���", "���Ϲ��̿�", "���Ͼ�ǰ", "���Ͽ�������", "���Ͽ���", "������������", "������������", "��������", "����â��", "������ũ", "������ũ�뽺", "��������", "��Ʈ��", "���齺&ī��", "���齺", "��������Ʈ", "��������", "�������̿�����", "����ILI", "����", "������ȭ", "��������", "��������", "���̸ƽ�", "���Ͼ˹̴�", "����", "���ٴ�", "���ٴ���̿�", "���������", "�ּ������Ͼ", "�ֿ���ũ", "�߱����������Ѱ���", "�߱���", "�߱���ǰ�������Ѱ���", "�߱���ǰ����", "�߱������������Ѱ���", "�߱���������", "�߱������ڿ����Ѱ���", "�߱������ڿ�", "�߾ӰǼ�", "�߾ӹ�ſ�����", "�߾ӹ��", "�߾ӿ��ʺ�", "�߾ӿ���", "�����̹��̿�", "�����̺��", "���ص�����", "���ؿ���Ƽ", "��������", "�������ν�Ʈ���Ʈ", "�������ν�Ʈ��", "������", "������", "������", "������", "�ѱ������������", "�����������", "����", "����", "����", "���ι�ȿ", "����Ʈ����", "�����̿���", "����Ƽ�̾�", "������", "��������", "���������췹ź", "��������", "����Ȧ����", "����ȭ��", "������", "������������", "�����̿��ص������", "�����̿���", "���̳��׷���Ʈ��Ÿ���ͳ��ųθ���Ƽ��", "���̳��׷���Ʈ", "���̳�ŷ���̿���Ȧ��������Ƽ��", "���̳�ŷ", "���̳��Ͽ���������Ŭ�����Ѱ���", "���̳��Ͽ���", "�������Ͼ", "����������", "����ũ�۷ι�", "����ũ", "â�ؿ������", "ó���ؾ�", "õ�ϰ��", "û�㷯��", "û�����", "ûȣ�ĳ�", "ü�ý�", "�ʷϹ�̵��", "�ʷϹ�", "ī��", "ī����", "ĵ��̵��", "ķ�ý�", "������", "���̵�̵��", "���̵�", "���̺�۷ι���Ÿ���Ӿؾ۽�����μ�����", "���̺���Ӿؾ۽�����", "���̺񹰻�", "���̺���ũ���", "���̺�Ƽ", "���̾�����", "���̾���", "���̾��ǵ�", "���̾��̾�", "���̾��̿�����", "���̾���Ƽ", "���̾���2ȣ����������Ź�����ε�������ȸ��", "���̾���2ȣ", "���̾����۴�", "���̿�������", "���̿������ҽ�", "���̿�����", "���̿�����", "���̿�������", "���̿���Ƽ�ؾ���", "���̿���Ƽ", "���̿���", "���̿�", "���̿�������", "���̿�����", "����Ƽ��", "����Ƽ��", "������", "�����ǿ���", "�����ǿ���ũ", "�������ɹ�Į", "������Ƽ��", "������Ƽ", "��Ʈ�δн�", "�ڴб۷θ�", "�ڴ���", "�ڵ����Ĺ���", "�ڵ𿡽�", "�ڶ��Ȧ����", "�ڷ�", "�ڷ���", "�ڸ����۽���01ȣ��������ȸ��", "�ڸ���01ȣ", "�ڸ����۽���02ȣ��������ȸ��", "�ڸ���02ȣ", "�ڸ����۽���03ȣ��������ȸ��", "�ڸ���03ȣ", "�ڸ����۽���04ȣ��������ȸ��", "�ڸ���04ȣ", "�ڸ����۽���05ȣ��������ȸ��", "�ڸ���05ȣ", "�ڸ����۽���06ȣ��������ȸ��", "�ڸ���06ȣ", "�ڸ����۽���07ȣ��������ȸ��", "�ڸ���07ȣ", "�ڸ���2000", "�ڸ��Ƴ�ȭ��ǰ", "�ڸ��Ƴ�", "�ڸ��ƺ���ũ", "�ڸ��ƽ�ŰƮ", "�ڸ��ƿ�����", "�ڸ��ȸ��纸��", "�ڸ��ȸ�", "�ڸƽ�", "�ڸ޷�", "�ڹ���", "�ڹ���", "�ڽ��ƽ�", "�ڽ���ż���", "�ڽ���ȭ��", "�ڽ��ؽý�", "�ھƷ���", "�ھƽ���", "�ھƽ�", "�ھƿ����ؾ���", "�ھ�ũ�ν�", "�ڿ���", "�ڿ���", "�ڿ��հǼ�", "�ڿ��ջ������", "�ڿ��վ��̳�", "�ڿ����δ���Ʈ��", "�ڿ����δ�", "�ڿ����ö�ƽ", "�ڿ��ý���", "�ڿ�", "������Ȧ�������Ѱ���", "������Ȧ����", "������", "��į", "����", "��ũ����15ȣ������������ε�������ȸ��", "��ũ��15ȣ", "��ũ����8ȣ��Ź�����ε�������ȸ��", "��ũ��8ȣ", "����", "������", "���ؽý���", "ť����", "ť����", "ť��Ȧ����", "ť���", "ť�ؿ���", "ť��������", "ťĳ��Ż��Ʈ�ʽ�", "ťĳ��Ż", "ũ�������", "ũ����", "ũ�ι�������", "ũ�����", "ũ����Ż����ͽ�", "ũ����Ż", "ũ���ػ��̾�", "Ŭ��Ÿ", "Ŭ���", "Ű����۷ι�", "Ű����1ȣ����μ�����", "Ű����1ȣ", "Ű������", "Ű�̽�Ʈ", "ž�ݼ�", "ž�����Ͼ", "�°���", "�°�ȭ��", "�±�", "�±����", "�¸��������", "�¸�����", "�»꿤�õ�", "�¾�ݼӰ���", "�¾�ݼ�", "�¾����", "�¾���", "�¿��Ǽ�", "�¿�", "�¿�����", "��â�ķν�", "����繰��", "���������", "�ͺ���ũ", "�׶󸮼ҽ�", "�׶��", "�׶������ؽ�", "�׽�", "��ũ�뼼����", "�ؼ���Ʈ��", "�ڷ�Ĩ��", "�ڷ��ʵ�", "���ڿ���", "���", "������Ȧ����", "��Ż����Ʈ��ũ", "��Ż����Ʈ", "���ʵ�", "����", "�������Ʈ", "������", "Ʈ���̽�", "Ư���Ǽ�", "Ƽ����ũ�����", "Ƽ��", "Ƽ��ε嵵�����Ϲ��", "Ƽ������", "Ƽ��������", "Ƽ������", "Ƽ����ġ��", "Ƽ������", "Ƽ�̾�����", "Ƽ�����ɹ�Į", "Ƽ�÷���", "Ƽ�Ǿ�", "Ƽ�Ǿ��۷ι�", "����", "��ũ����", "�ĳ���", "�Ķ���̽�", "�Ķ���̽����", "�Ķ���", "�ķ�", "�ĺ���", "�ļ���", "�Ŀ�_K100", "�Ŀ�������", "�Ŀ�����", "���ε�ؾ�", "���ε�����", "������ũ�н�", "��ĵ���Ǿ�", "��ĵOPC", "��Ʈ��", "�ʽ������̿�", "�ʽ���", "�ʽ��丮�ѳ�", "�ҿ������θ�Ʈ", "�۽���", "�۽ý�", "�������ڸ���", "���", "��ȭ���", "��ȭ����", "��ȭȦ����", "����Ż", "����Ƽ�ؾ�", "�����ھ��̾�Ƽ", "������_ICT", "�����ڰ���", "�����ڿ���", "����������", "����Ʈ����", "��������", "�����÷���", "Ǫ����", "Ǫ�����", "Ǫ����ȣ��������", "Ǫ����������", "Ǯ����Ȧ����", "ǳ��", "ǳ��", "ǳ����������", "ǳ������", "ǳ�����", "ǳ��", "ǳ��Ȧ����", "�����Ľ�Ʈ", "������", "���ҽ��Ƽ", "��������", "������", "������", "�÷�Ƽ��", "�÷�����", "�ǵ�����", "�Ǿ���Ʈ", "�Ǿ���", "�ǿ�������", "�ǿ�������", "�ǿ�������", "�ǿ�����", "�ǿ�����", "�ǿ�����", "�����̸�Ż", "����������", "�ʷ轺", "�ʸ�ũ", "��������", "�ϳ��׸�����μ�����ȸ��", "�ϳ��׸�����", "�ϳ���������", "�ϳ����񿡽��Ϲ��������ؿ��ڿ���������ȸ����1ȣ", "�ϳ�����1ȣ", "�ϳ����񿡽��Ϲ��������ؿ��ڿ���������ȸ����2ȣ", "�ϳ�����2ȣ", "�ϳ�����ũ��", "�ϳ�����", "�ϸ�", "�ϸ�Ȧ����", "���̰�����2ȣ��������ȸ��", "���̰��2ȣ", "���̴н��ݵ�ü", "���̴н�", "���̷��ڸ���", "���̸�Ʈ", "���̼Ҵ�", "���̽�ƿ", "���̽�", "������1ȣ����μ�����", "������1ȣ����", "��������", "����Ʈ�о�������", "����Ʈ��", "����Ʈ����", "����ƮȦ����", "����", "�ѱ�", "�ѱ�����", "�ѱ���������", "�ѱ����߱���", "�ѱ�����Ƽ����", "�ѱ�����TV", "�ѱ�����", "�ѱ����ڱ�������", "�ѱ���������", "�ѱ������", "�ѱ���ȭ", "�ѱ����ڰ���", "�ѱ�����", "�ѱ����̹�����", "�ѱ���������", "�ѱ�����", "�ѱ�����", "�ѱ�������", "�ѱ����ڽż���1ȣ����μ�����ȸ��", "�ѱ�����1ȣ", "�ѱ�������̵�ƽþ������Ư���ڻ�1ȣ����ȸ��", "�ѱ�����Ư��", "�ѱ����ݻ��", "�ѱ�����", "�ѱ���������", "�ѱ�����", "�ѱ���Ʈ���ʿ����������ؿ��ڿ���������ȸ��", "�ѱ�����151", "�ѱ��ڿ����ڰ���", "�ѱ���������", "�ѱ����°���", "�ѱ�����", "�ѱ����ڱ���", "�ѱ���������", "�ѱ�����Ȧ����", "�ѱ����б��", "�ѱ���������", "�ѱ��������", "�ѱ�����", "�ѱ����ձ��", "�ѱ�����ĳ��Ż", "�ѱ��ְ�", "�ѱ���ö������", "�ѱ���ö��", "�ѱ�ö��", "�ѱ�ü�ΰ���", "�ѱ�ī��", "�ѱ�ĳ��Ż", "�ѱ���ǻ��", "�ѱ��ݸ�", "�ѱ�ť��", "�ѱ�Ÿ�̾�", "�ѱ�������Ź", "�ѱ�Ʈ�δн�", "�ѱ�Ư������", "�ѱ���Ű��", "�ѱ�����������", "�ѱ�������", "�ѱ��װ����ֻ��", "�ѱ��װ�����", "�ѱ�ȭ��ǰ", "�ѱ�ȭ��ǰ����", "�ѱ۰���ǻ��", "�ѳ�Ʈ", "�ѳ�ȭ��", "�ѵ���ǰ", "�Ѷ�Ǽ�", "�Ѷ����", "�Ѷ���̿�����", "�Ѷ�IMS", "�Ѹ�â������", "�Ѹ�â��", "�ѹ̱۷ι������繫��", "�ѹ̱۷ι�", "�ѹ̹ݵ�ü", "�ѹ̾�ǰ", "�ѹ�Ȧ����", "Ƽ��ε��Ѻ����", "�Ѻ����", "�Ѻ�����Ʈ", "�ѻ�", "�Ѽ�", "�Ѽ����", "�Ѽ�������", "�Ѽ��Ǿ�", "�Ѽ�����24Ȧ����", "�Ѽ���Ƽť��", "�Ѽ�����", "�Ѽ��ɹ�Į", "�Ѽ���ũ�н�", "�Ѽ�Ȩ����", "�Ѽ�CSN", "�Ѽ��ǿ�����", "�Ѽ�PNS", "�ѽ����̿��޵�", "�ѽŰ���", "�ѽű�����", "�ѽű��", "�Ѿ������", "�Ѿ��̿���", "�Ѿ�����", "�ѿù��̿��ĸ�", "�ѿ�κ�ƽ��", "���ͽ�������", "���ϰǼ�", "���ϴ�������", "���ϴ���", "���ϻ��", "���Ͻø�Ʈ", "������ȭ", "����ö��", "����ȭ�а���", "����ȭ��", "�ѱ����±��", "�������", "�����������", "�������", "����KPS", "����", "�����߰���", "�����߰���Ȧ����", "�����Ǿؾ�", "�����ؿ�", "�����ؿ�Ȧ����", "��â", "��â���", "��â����", "���ؿ����Ͼ", "����", "���뵥��Ÿ", "��ȭ", "��ȭ���غ���", "��ȭ����", "��ȭ�ɹ�Į", "��ȭŸ�ӿ���", "��ȭ�������̸�����1ȣ����μ�����", "��ȭSV����1ȣ", "�ش��Ŀ�����", "�ؼ����", "�ೲ�ڱ�", "��޽�Ȧ����", "�콺��", "����Ǽ�", "����׸�Ǫ��", "����۷κ�", "�������Ż��ũ", "�������Ż��", "������", "�����������", "�����ȭ��", "����������ƿ", "����������", "������", "�������ջ��", "������", "�����", "����ø�Ʈ", "�������Ƽ", "�����ǰ", "���뿡��ġ����", "���뿤��������", "���뿤������", "��������", "����������", "�����������", "������ö", "�����߰���", "��������", "����帲���Դ�����μ�����", "�������ǽ���1ȣ", "�����ڵ���", "������", "�������", "�����Ǿؾ�", "�������̽���", "�����ػ�ȭ�纸��", "�����ػ�", "����Ȩ����", "����EP", "������", "��������", "����", "ȣ������ȭ��", "ȣ������", "ȣ�̵忬����", "ȣ�ڽŶ�", "Ȩ��Ÿ", "Ȩĳ��Ʈ", "ȭ��", "ȭ�����", "ȭ�¾˾ؿ���", "ȭ���δ���Ʈ��", "ȭ���δ�", "ȭ��", "ȭ������", "ȭ����ũ", "ȭ����", "ȭ�Ͼ�ǰ", "ȭ��", "ȭõ���", "ȭõ���", "ȭǳ��������ȸ��", "ȭǳ����_KDR", "ȯ������", "Ȳ�ݿ���Ƽ", "ȿ��", "ȿ�����غ�", "ȿ������Ƽ����", "ȿ��ITX", "�ĳʽ�", "�ļ�", "�ִн�����", "�ִн�Ŀ�´����̼���", "�ִн���", "�ٶ��ڸ���", "�޴ϵ���ũ�����", "�޴ϵ�", "�޸ƽ�", "�޸ƽ�Ȧ����", "�޸����ڸ���", "�޹��̷�", "�޺���", "�޽�ƿ", "�޿½�", "���ͽ�", "�ﱸ����", "�ﱹ", "�ﱹȭ���ػ���", "�ﱹȭ��", "����ؿ�", "�����հ����繫��", "��", "����è�Ǿ���1ȣ����μ�����", "����è�Ǿ���1ȣ", "�����佺��", "���̵���ͽ�", "AD���ͽ�", "�������̿���", "AJS", "�����ǽý���", "AP�ý���", "BS��������", "��Ƽ�ؾ���", "BT&I", "BYC", "���ؿ����ڻ����", "C&S�ڻ����", "CJ", "�����̾�������", "CJ_CGV", "�������̾ؿ�", "CJ_E&M", "CJ��Ǫ��", "�����̿�����", "CJ������", "CJ��������", "�����������ÿ���", "CJ�����ÿ���", "���ؿ���ġ", "CNH", "������", "CS", "CSȦ����", "��Ƽ��Ƽ", "CT&T", "��������", "CU����", "DGB��������", "�𿥿���", "DMS", "�𿡽�����", "DS����", "�𿡽�������", "DSR����", "E1", "EG", "�̿�����������", "EMLSI", "�̿�������", "EMW", "F&F", "FIRST_��Ÿ�췮", "������", "G��R", "������", "G����", "GIANT_�������׷�", "�׷����ڸ��Ʒ���", "GKL", "GREAT_GREEN", "GREAT_SRI", "GS", "GS�Ǽ�", "�������۷ι�", "GS�۷ι�", "GSȨ����", "�۷ι����Ĵٵ���ũ�����", "GST", "��Ƽ��Ƽ", "GT&T", "����ġ������ġ�۷ι����ҽ�", "H&H", "HIT_���", "HIT_����", "����ġ������������", "HMC��������", "����ġ�˿���", "HRS", "���̺�����", "IB������", "���̺����̿�������Ʈ�������̱���μ�����1ȣ", "IBKS����1ȣ", "���̿���ġť", "IHQ", "���̿���", "iMBC", "���̿�������ũ���", "ISC", "����Ƽ������ť��Ƽ", "ITX��ť��Ƽ", "���̾��������θ�Ʈ", "JCE", "���̿�������", "JS����", "���̴������߿ܽž�", "JW�߿ܽž�", "JW�߿�����", "���̴�����Ȧ����", "JWȦ����", "���̿����ǿ������θ�Ʈ", "JYP_Ent.", "KB��������", "KB����", "���̺����ý�", "KB����ý�", "���̾��׸�Ȧ����", "KC�׸�Ȧ����", "���̾���Ʈ��", "KC��Ʈ��", "���̾���", "KCC", "���̾����Ǽ�", "KCC�Ǽ�", "���̾�����", "KCI", "���̾�Ƽ��", "KCTC", "���̾������", "KCW", "�����̾�", "KEC", "KG�ɹ�Į", "��������", "KGP", "KH����", "KINDEX_����ä", "KINDEX_�Ｚ�׷�EW", "KINDEX_�Ｚ�׷�SW", "KINDEX_�ڽ��ڽ�Ÿ", "KINDEX_�ݴ����д�", "KINDEX_F15", "KINDEX200", "KISCOȦ����", "��������������", "KJ������", "KMH", "���̿���", "KNN", "KODEX_200", "KODEX_�Ǽ�", "KODEX_��弱��", "KODEX_��������", "KODEX_����ä", "KODEX_��������", "KODEX_�ݵ�ü", "KODEX_����", "KODEX_�Ｚ�׷�", "KODEX_�Һ���", "KODEX_������ȭ��", "KODEX_���", "KODEX_������", "KODEX_����", "KODEX_�ι���", "KODEX_�ڵ���", "KODEX_����", "KODEX_����", "KODEX_ö��", "KODEX_�ἱ��", "KODEX_�¾籤", "KODEX_Brazil", "KODEX_China_H", "KODEX_Japan", "KOSEF_200", "KOSEF_����", "KOSEF_����ä", "KOSEF_�ܱ��ڱ�", "KOSEF_�̱��޷�����", "KOSEF_�̴޷��ι���", "KOSEF_���Ĩ", "KOSEF_�ι���", "KOSEF_���ä", "KOSEF_�ݴ���Ż����", "KOSEF_Banks", "KOSEF_IT", "KOSEF_KRX100", "KPX�׸��ɹ�Į", "�����ǿ������������̾�", "KPX�������", "KPX�ɹ�Į", "KPXȦ����", "KPXȭ���ɹ�Į", "���̿��������ؿ�", "KSS�ؿ�", "KStar_5��׷���", "KStar_����ä", "KStar_��������", "KStar_������", "KStar_�췮����", "KStar_�췮ȸ��ä", "KStar_�ڽ��ڿ���Ʈ", "����Ƽ", "KT", "����Ƽ����", "KT&G", "KT����", "����Ƽ���긶��", "KT���긶��", "����Ƽ����������", "KTB��������", "����Ƽ������", "KTcs", "����Ƽ������", "KTH", "LG", "�������÷���", "LG���÷���", "LG���", "LG�������", "LG��Ȱ�ǰ�", "LG���÷���", "LG�̳���", "LG����", "LG�м�", "�����Ͽ�ý�", "LG�Ͽ�ý�", "LGȭ��", "�����������غ���", "LIG���غ���", "�����������̵���", "LIG���̵���", "LS", "LS��Ʈ����", "LS��Ʈ������ȯ��ȯ", "LS����", "MDS��ũ�����", "MDS��ũ", "MH��ź��", "�������Ʈ����", "NCB��Ʈ����", "NH��������", "������ġ��", "NHN", "NI��ƿ", "NICEȦ����", "NICE", "NICE�ſ�������", "NICE������", "���Ǿ�", "NPC", "��������", "OCI", "�������̸�Ƽ������", "OCI��Ƽ������", "�ǿ�ǳ��", "PNǳ��", "������", "POSCO", "PREX_�׿����", "PREX_LG�׷�&", "����������������", "S&K������", "S&T���", "S&T���ͽ�", "S&T�߰���", "S&TȦ����", "S&TC", "��������̱۷ι��κ���Ʈ��Ʈ", "SBI�۷ι�", "����������κ���Ʈ��Ʈ", "SBI�κ���Ʈ��Ʈ", "SBS", "�����񿡽��̵��Ȧ����", "SBS�̵��Ȧ����", "�����񿡽����������", "SBS���������", "������", "SDN", "����������", "SG&G", "���������蹰��", "SG���蹰��", "�������泲����", "SG�泲����", "����������", "SGA", "SH������ȭ��", "SIMPAC", "���Ѹ�Ż����", "SIMPAC_METALLOY", "�������̿�", "SJM", "�������̿�Ȧ����", "SJMȦ����", "SK", "�������̾��ؾ�", "SK_C&C", "SK����", "SK��Ʈ����", "�������̺�ε���", "SK��ε���", "SK�̳뺣�̼�", "SK����", "��������Ŀ�´����̼���", "SK����", "SK�ɹ�Į", "SK�ڷ���", "�������̾�", "SKC", "SKC�ֹͽ�", "SKC_�ֹͽ�", "���׸���", "SMEC", "����������ġ", "SNH", "SOil", "������������", "SSCP", "����Ƽ�����ݵ�ü���", "STS�ݵ�ü", "STX", "STX��Ż", "STX����", "STX�����ؾ�", "STX�ҿ���", "Ƽ��������", "TCC����", "TIGER_200", "TIGER_��ġ��", "TIGER_�Ǽ����", "TIGER_��ä3", "TIGER_�׸�", "TIGER_�ݼӼ���", "TIGER_����", "TIGER_��������", "TIGER_������100", "TIGER_��깰����", "TIGER_��ƾ", "TIGER_��������", "TIGER_�̵�ĸ", "TIGER_�̵�����", "TIGER_�ݵ�ü", "TIGER_�긯��", "TIGER_���Ĩ30", "TIGER_�Ｚ�׷�", "TIGER_������ȭ��", "TIGER_��������", "TIGER_����", "TIGER_�ι���", "TIGER_�ι�����ä3Y", "TIGER_�ڵ���&����", "TIGER_����&���̿�", "TIGER_�������", "TIGER_���̳�", "TIGER_ö������", "TIGER_�ڽ���������", "TIGER_�ʼ��Һ���", "TIGER_�������׷�", "TIGER_IT", "TIGER_KRX100", "TIGER_LG�׷�", "TIGER_S&P500����", "Ƽ���̵̹��", "TJ�̵��", "Ƽ�Ǿ���īƮ�δн�", "TPC", "TREX_200", "TREX_�߼�����ġ", "Ƽ��������ý�", "TSC_��ý�", "�������������ͳ��ų�", "VGX����", "������", "WISCOM", "���̺񿥽û����", "YBM�û����", "���̾������ڸ���", "YNK�ڸ���", "����Ƽ��", "�������������θ�Ʈ", "YTN", "YG����"
    };
  
}