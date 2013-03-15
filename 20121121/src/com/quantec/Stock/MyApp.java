package com.quantec.Stock;

import com.quantec.Stock.Activity.OptD;
import com.quantec.Stock.Activity.StockCount;
import com.quantec.Stock.Activity.StockD;
import com.quantec.Stock.Activity.digitD;

import android.app.Application;
import android.view.Display;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MyApp extends Application{
    private static Display display2;
    private static StockD dataStockR;
    private static StockCount dataStockC;
    private static OptD optR;
//    private static digitD digitR;
    private static int duration = 40;
    private static String tmp;
    private static float version = 2.035f;
    
    private static int show_result = 0;
    private static int show_result_menu = 0;
    private static int show_result_view = 0;
    
    private static Boolean connect = false;  

    public static Boolean getConnect(){
    	return connect;
    }
    
    public void setConnect(Boolean con){
    	this.connect = con;
    }
    
    public static int getSR(){
    	return show_result;
    }
    
    public static float getVER(){
    	return version;
    }
    
    public void setSR(int m_sr){
    	this.show_result = m_sr;
    }
    
    public static int getSRM(){
    	return show_result_menu;
    }
    
    public void setSRM(int m_srm){
    	this.show_result_menu = m_srm;
    }
    
    public static int getSRV(){
    	return show_result_view;
    }
    
    public void setSRV(int m_srv){
    	this.show_result_view = m_srv;
    }
    
    public static String getGlobalString()
    {
      return tmp;
    }

    public void setGlobalString(String globalString)
    {
      this.tmp = globalString;
    }
    
    public static int getDuration(){
    	return duration;
    }
    
    public void setDuration(int m_duration){
    	this.duration = m_duration;
    }
	
    public static Display getDisplay(){
    	return display2;
    }
    
    public void setDisplay(Display m_display){
    	this.display2 = m_display;
    }
    
    public static StockD getStockD(){
    	return dataStockR;
    }
    
    public void setStockD(StockD m_stock){
    	this.dataStockR = m_stock;
    }
    
    public static OptD getOptD(){
    	return optR;
    }
    
    public void setOptD(OptD m_stock){
    	this.optR = m_stock;
    }
    
    public static StockCount getStockCount(){
    	return dataStockC;
    }
    
    public void setStockCount(StockCount m_stock){
    	this.dataStockC = m_stock;
    }
/*    
    public static digitD getdigitD(){
    	return digitR;
    }
    
    public void setdigitD(digitD m_digit){
    	this.digitR = m_digit;
    }
//*/
//	float x1, y1, x2, y2;
}
