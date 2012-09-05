package com.quantec.Stock.Activity;

import org.xmlpull.v1.XmlPullParser;

public class OptParser {
	OptD Orest;
	
	int count_m11 = 0;

	
	public OptParser(OptD rest){
		Orest = rest;

		for(int i = 0; i<40; i++){
			Orest.m_Kname[i] = Orest.m_Jcode[i] = null;
		}
	}		

	void proceed(XmlPullParser ReceiveStream){
		
		boolean bevent_information = false;
		
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
					// items.add(xpp.getAttributeValue(0));
					sTag = ReceiveStream.getName();
					
					if (sTag.equals("event_information")){
						bevent_information = true;
					}
					
					if (bevent_information == true){
						if (sTag.equals("Jcode")) {
							String sValue = ReceiveStream.getAttributeValue(0);
							Orest.m_Jcode[count_m11] = sValue;
						} else if (sTag.equals("Kname")) {
							String sValue = ReceiveStream.getAttributeValue(0);
							Orest.m_Kname[count_m11] = sValue;
						}
						count_m11++;
					}
					break;

				case XmlPullParser.END_TAG:
					sTag = ReceiveStream.getName();

					if (sTag.equals("event_information")) {
						bevent_information = false;
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
