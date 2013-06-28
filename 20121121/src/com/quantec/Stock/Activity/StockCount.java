package com.quantec.Stock.Activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class StockCount {
	int begin_sq;
	int last_sw;
	
	public StockCount getSDur(){
		
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
}
