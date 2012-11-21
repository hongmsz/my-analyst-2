package com.quantec.Stock.Activity;

import org.xmlpull.v1.XmlPullParser;

public class ReceiveParsing {
	StockD dataStock;
	
	int count_m11 = 0;
	
	public ReceiveParsing(StockD t_dS){
		dataStock = t_dS;

		dataStock.m_quarter = new int[43];
		dataStock.m_m11 = new int[43];
		dataStock.m_m12 = new long[43];
		dataStock.m_m13 = new long[43];
		dataStock.m_m14 = new long[43];
		dataStock.m_m15 = new long[43];
		dataStock.m_m16 = new long[43];
		dataStock.m_m17 = new float[43];
		dataStock.m_m18 = new float[43];
		dataStock.m_m21 = new long[43];
		dataStock.m_m22 = new long[43];
		dataStock.m_m23 = new float[43];
		dataStock.m_m24 = new long[43];
		dataStock.m_m25 = new long[43];
		dataStock.m_m26 = new long[43];
		dataStock.m_m27 = new long[43];
		dataStock.m_m28 = new float[43];
		dataStock.m_m31 = new long[43];
		dataStock.m_m32 = new long[43];
		dataStock.m_m33 = new long[43];
		dataStock.m_m34 = new long[43];
		dataStock.m_m35 = new long[43];
		dataStock.m_m41 = new float[43];
		dataStock.m_m43 = new float[43];
		dataStock.m_m44 = new long[43];
		dataStock.m_m45 = new long[43];
		
		for(int i = 0; i<43; i++){
			dataStock.m_m35[i] = dataStock.m_m34[i] = dataStock.m_m45[i] = dataStock.m_m44[i] = dataStock.m_m33[i] = dataStock.m_m32[i] = dataStock.m_m31[i] = dataStock.m_m27[i] = dataStock.m_m26[i] = dataStock.m_m25[i] = dataStock.m_m24[i] = dataStock.m_m22[i] = dataStock.m_m21[i] = dataStock.m_m16[i] = dataStock.m_m15[i] = dataStock.m_m14[i] = dataStock.m_m13[i] = dataStock.m_m12[i] = 10;
			dataStock.m_m17[i] = dataStock.m_m18[i] = dataStock.m_m43[i] = dataStock.m_m41[i] = dataStock.m_m28[i] = dataStock.m_m23[i] = 10.0f;
			dataStock.m_m11[i] = 10;
			dataStock.m_quarter[i] = 1;
		}
	}		

	void proceed(XmlPullParser ReceiveStream){
		
		boolean bevent_information = false;
		
		boolean bm11_information = false;
		boolean bm12_information = false;
		boolean bm13_information = false;
		boolean bm14_information = false;
		boolean bm15_information = false;
		boolean bm16_information = false;
		boolean bm17_information = false;
		boolean bm18_information = false;
		boolean bm21_information = false;
		boolean bm22_information = false;
		boolean bm23_information = false;
		boolean bm24_information = false;
		boolean bm25_information = false;
		boolean bm26_information = false;
		boolean bm27_information = false;
		boolean bm28_information = false;
		boolean bm31_information = false;
		boolean bm32_information = false;
		boolean bm33_information = false;
		boolean bm34_information = false;
		boolean bm35_information = false;
		boolean bm41_information = false;
		boolean bm43_information = false;
		boolean bm44_information = false;
		boolean bm45_information = false;

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
					}else if (sTag.equals("m11_information")){
						bm11_information = true;
					}else if (sTag.equals("m12_information")){
						bm12_information = true;
					}else if (sTag.equals("m13_information")){
						bm13_information = true;
					}else if (sTag.equals("m14_information")){
						bm14_information = true;
					}else if (sTag.equals("m15_information")){
						bm15_information = true;
					}else if (sTag.equals("m16_information")){
						bm16_information = true;
					}else if (sTag.equals("m17_information")){
						bm17_information = true;
					}else if (sTag.equals("m18_information")){
						bm18_information = true;
					}else if (sTag.equals("m21_information")){
						bm21_information = true;
					}else if (sTag.equals("m22_information")){
						bm22_information = true;
					}else if (sTag.equals("m23_information")){
						bm23_information = true;
					}else if (sTag.equals("m24_information")){
						bm24_information = true;
					}else if (sTag.equals("m25_information")){
						bm25_information = true;
					}else if (sTag.equals("m26_information")){
						bm26_information = true;
					}else if (sTag.equals("m27_information")){
						bm27_information = true;
					}else if (sTag.equals("m28_information")){
						bm28_information = true;
					}else if (sTag.equals("m31_information")){
						bm31_information = true;
					}else if (sTag.equals("m32_information")){
						bm32_information = true;
					}else if (sTag.equals("m33_information")){
						bm33_information = true;
					}else if (sTag.equals("m34_information")){
						bm34_information = true;
					}else if (sTag.equals("m35_information")){
						bm35_information = true;
					}else if (sTag.equals("m41_information")){
						bm41_information = true;
					}else if (sTag.equals("m43_information")){
						bm43_information = true;
					}else if (sTag.equals("m44_information")){
						bm44_information = true;
					}else if (sTag.equals("m45_information")){
						bm45_information = true;
					}
					
					if (bevent_information == true){
						if (sTag.equals("Jcode")) {
							String sValue = ReceiveStream.getAttributeValue(0);
							dataStock.m_Jcode = sValue;
						} else if (sTag.equals("Kname")) {
							String sValue = ReceiveStream.getAttributeValue(0);
							dataStock.m_Kname=sValue;
						}
					}else if(count_m11<43){
						if (bm11_information == true){
							if (sTag.equals("quarter")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_quarter[count_m11] = Integer.parseInt(sValue);
							}else if (sTag.equals("amount")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_m11[count_m11] = Integer.parseInt(sValue);
	//							count_m11++;
							}
						}else if (bm12_information == true){
							if (sTag.equals("amount")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_m12[count_m11] = Long.parseLong(sValue);//Integer.parseInt(sValue)/1000;
	//							count_m12++;
							}
						}else if (bm13_information == true){
							if (sTag.equals("amount")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_m13[count_m11] = Long.parseLong(sValue);//Integer.parseInt(sValue)/1000;
	//							count_m13++;
							}
						}else if (bm14_information == true){
							if (sTag.equals("amount")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_m14[count_m11] = Long.parseLong(sValue);//Integer.parseInt(sValue)/1000;
	//							count_m14++;
							}
						}else if (bm15_information == true){
							if (sTag.equals("amount")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_m15[count_m11] = Long.parseLong(sValue);//Integer.parseInt(sValue)/1000;
	//							count_m15++;
							}
						}else if (bm16_information == true){
							if (sTag.equals("amount")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_m16[count_m11] = Long.parseLong(sValue);//Integer.parseInt(sValue)/1000;
	//							count_m16++;
							}
						}else if (bm17_information == true){
							if (sTag.equals("amount")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_m17[count_m11] = Float.parseFloat(sValue);
	//							count_m17++;
							}
						}else if (bm18_information == true){
							if (sTag.equals("amount")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_m18[count_m11] = Float.parseFloat(sValue);
	//							count_m18++;
							}
						}else if (bm21_information == true){
							if (sTag.equals("amount")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_m21[count_m11] = Long.parseLong(sValue);//Integer.parseInt(sValue)/1000;
	//							count_m21++;
							}
						}else if (bm22_information == true){
							if (sTag.equals("amount")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_m22[count_m11] = Long.parseLong(sValue);//Integer.parseInt(sValue)/1000;
	//							count_m22++;
							}
						}else if (bm23_information == true){
							if (sTag.equals("amount")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_m23[count_m11] = Float.parseFloat(sValue);
		//						count_m23++;
							}
						}else if (bm24_information == true){
							if (sTag.equals("amount")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_m24[count_m11] = Long.parseLong(sValue);//Integer.parseInt(sValue)/1000;
	//							count_m24++;
							}
						}else if (bm25_information == true){
							if (sTag.equals("amount")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_m25[count_m11] = Long.parseLong(sValue);//Integer.parseInt(sValue)/1000;
		//						count_m25++;
							}
						}else if (bm26_information == true){
							if (sTag.equals("amount")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_m26[count_m11] = Long.parseLong(sValue);//Integer.parseInt(sValue)/1000;
	//							count_m26++;
							}
						}else if (bm27_information == true){
							if (sTag.equals("amount")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_m27[count_m11] = Long.parseLong(sValue);//Integer.parseInt(sValue)/1000;
	//							count_m27++;
							}
						}else if (bm28_information == true){
							if (sTag.equals("amount")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_m28[count_m11] = Float.parseFloat(sValue);
	//							count_m28++;
							}
						}else if (bm31_information == true){
							if (sTag.equals("amount")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_m31[count_m11] = Long.parseLong(sValue);//Integer.parseInt(sValue)/1000;
	//							count_m31++;
							}
						}else if (bm32_information == true){
							if (sTag.equals("amount")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_m32[count_m11] = Long.parseLong(sValue);//Integer.parseInt(sValue)/1000;
	//							count_m32++;
								count_m11++;
							}
						}else if (bm33_information == true){
							if (sTag.equals("amount")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_m33[count_m11] = Long.parseLong(sValue);//Integer.parseInt(sValue)/1000;
	//							count_m33++;
							}
						}else if (bm34_information == true){
							if (sTag.equals("amount")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_m34[count_m11] = Long.parseLong(sValue);
	//							count_m34++;
							}
						}else if (bm35_information == true){
							if (sTag.equals("amount")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_m35[count_m11] = Long.parseLong(sValue);//Integer.parseInt(sValue)/1000;
		//						count_m35++;
							}
						}else if (bm41_information == true){
							if (sTag.equals("amount")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_m41[count_m11] = Float.parseFloat(sValue);
	//							count_m41++;
							}
						}else if (bm43_information == true){
							if (sTag.equals("amount")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_m43[count_m11] = Float.parseFloat(sValue);
		//						count_m43++;
							}
						}else if (bm44_information == true){
							if (sTag.equals("amount")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_m44[count_m11] = Long.parseLong(sValue);//Integer.parseInt(sValue)/1000;
	//							count_m44++;
							}
						}else if (bm45_information == true){
							if (sTag.equals("amount")) {
								String sValue = ReceiveStream.getAttributeValue(0);
								dataStock.m_m45[count_m11] = Long.parseLong(sValue);//Integer.parseInt(sValue)/1000;
		//						count_m45++;
							}
						}
					}
					break;

				case XmlPullParser.END_TAG:
					sTag = ReceiveStream.getName();

					if (sTag.equals("event_information")) {
						bevent_information = false;
					}else if (sTag.equals("m11_information")){
						bm11_information = false;
					}else if (sTag.equals("m12_information")){
						bm12_information = false;
					}else if (sTag.equals("m13_information")){
						bm13_information = false;
					}else if (sTag.equals("m14_information")){
						bm14_information = false;
					}else if (sTag.equals("m15_information")){
						bm15_information = false;
					}else if (sTag.equals("m16_information")){
						bm16_information = false;
					}else if (sTag.equals("m17_information")){
						bm17_information = false;
					}else if (sTag.equals("m18_information")){
						bm18_information = false;
					}else if (sTag.equals("m21_information")){
						bm21_information = false;
					}else if (sTag.equals("m22_information")){
						bm22_information = false;
					}else if (sTag.equals("m23_information")){
						bm23_information = false;
					}else if (sTag.equals("m24_information")){
						bm24_information = false;
					}else if (sTag.equals("m25_information")){
						bm25_information = false;
					}else if (sTag.equals("m26_information")){
						bm26_information = false;
					}else if (sTag.equals("m27_information")){
						bm27_information = false;
					}else if (sTag.equals("m28_information")){
						bm28_information = false;
					}else if (sTag.equals("m31_information")){
						bm31_information = false;
					}else if (sTag.equals("m32_information")){
						bm32_information = false;
					}else if (sTag.equals("m33_information")){
						bm33_information = false;
					}else if (sTag.equals("m34_information")){
						bm34_information = false;
					}else if (sTag.equals("m35_information")){
						bm35_information = false;
					}else if (sTag.equals("m41_information")){
						bm41_information = false;
					}else if (sTag.equals("m43_information")){
						bm43_information = false;
					}else if (sTag.equals("m44_information")){
						bm44_information = false;
					}else if (sTag.equals("m45_information")){
						bm45_information = false;
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
