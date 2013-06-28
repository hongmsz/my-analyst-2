package com.quantec.Stock.Activity;

import java.io.InputStream;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.quantec.Stock.MyApp;

public class StockD {
	String m_Jcode=null;
	String m_Kname=null;
	
	int[] v_stock;
	int[] v_week;
	
	int[] m_quarter;
	
	int[] m_m11;
	long[] m_m12;
	long[] m_m13;
	long[] m_m14;
	long[] m_m15;
	long[] m_m16;
	float[] m_m17;
	float[] m_m18;
	long[] m_m21;
	long[] m_m22;
	float[] m_m23;
	long[] m_m24;
	long[] m_m25;
	long[] m_m26;
	long[] m_m27;
	float[] m_m28;
	long[] m_m31;
	long[] m_m32;
	long[] m_m33;
	long[] m_m34;
	long[] m_m35;
	float[] m_m41;
	float[] m_m43;
	long[] m_m44;
	long[] m_m45;
	
	public StockD getSValue(String S_Code){
    	
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
	
	public StockD getSValueA(String S_Code){
    	
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
	
	public StockD getWSValue(String S_Code, int tmp_count, int qt_count, int dr, int this_w, int begin_oq){
		StockD dataStock = MyApp.getStockD();
		int dur2;
       
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
				
				if(this_w/10000 == begin_oq/100){
					dur2 = ((this_w/100)%100 - ((begin_oq%100 + 1)*3) - 1)*4 + this_w%100; // (begin_q/100)*100  // this_w/100
				}else{
					dur2 = ((this_w/100)%100 - ((begin_oq%100 + 1)*3) + (this_w/10000 - begin_oq/100)*12 - 1)*4 + this_w%100; // (begin_q/100)*100  // this_w/100
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
}
