package com.quantec.Stock.Activity;

import org.xmlpull.v1.XmlPullParser;

public class StockValueParsing {
	StockD dataStock;
	
	int count_v = 0;
	int count_q = 0;
	
	public StockValueParsing(StockD t_dS){
		dataStock = t_dS;
		
		dataStock.v_stock = new int[650];
		dataStock.v_week = new int[650];
		for(int i = 0; i<650; i++)
			dataStock.v_stock[i] = dataStock.v_week[i] = -1;
	}		

	void proceed(XmlPullParser ReceiveStream){
		
		boolean bstock_information = false;

		try {
			String sTag;
			int eventType = ReceiveStream.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				// Wait(10);
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					break;

				case XmlPullParser.END_DOCUMENT:
					break;

				case XmlPullParser.START_TAG:
					sTag = ReceiveStream.getName();
					
					if (sTag.equals("w_information")){
						bstock_information = true;
					}
					
					if (bstock_information == true){
						if (sTag.equals("amount")) {
							String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.v_stock[count_v] = Integer.parseInt(sValue);
						}
						else if (sTag.equals("cweek")) {
							String sValue = ReceiveStream.getAttributeValue(0);
							dataStock.v_week[count_v] = Integer.parseInt(sValue);
							count_v++;
						}
					}
					break;

				case XmlPullParser.END_TAG:
					sTag = ReceiveStream.getName();

					if (sTag.equals("w_information")) {
						bstock_information = false;
					}
					break;

				case XmlPullParser.TEXT:
					break;
				}

				eventType = ReceiveStream.next();
			}
		} catch (Exception e) {

		}		 
	}
}
