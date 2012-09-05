package com.quantec.Stock.Activity;

public class makeDigit {

	public digitD Fdigit(float max, float min){
		String v_max1, v_min1;
		digitD tmp_D = new digitD();
		
		v_max1 = (int)max+"";
		v_min1 = (int)min+"";

		if(v_max1.length()>9){
			max = max/1000000000l;
			min = min/1000000000l;
			v_max1 = String.format("%.2f", Float.valueOf(max));
			v_min1 = String.format("%.2f", Float.valueOf(min));
			tmp_D.uWon = " (십억원)";
			tmp_D.dCount = 9;
		}else if(v_max1.length()>8){
			max = max/100000000;
			min = min/100000000;
			v_max1 = String.format("%.2f", Float.valueOf(max));
			v_min1 = String.format("%.2f", Float.valueOf(min));
			tmp_D.uWon = " (억원)";
			tmp_D.dCount = 8;
		}else if(v_max1.length()>3){
			max = max/1000;
			min = min/1000;
			v_max1 = String.format("%.2f", Float.valueOf(max));
			v_min1 = String.format("%.2f", Float.valueOf(min));
			tmp_D.uWon = " (천원)";
			tmp_D.dCount = 3;
		}else{
			v_max1 = String.format("%.2f", Float.valueOf(max));
			v_min1 = String.format("%.2f", Float.valueOf(min));
			tmp_D.uWon = " (원)";
			tmp_D.dCount = 0;
		}

		tmp_D.v1 = String.format("%.2f", Float.valueOf(min+(max-min)/5));
		tmp_D.v2 = String.format("%.2f", Float.valueOf(min+(max-min)*2/5));
		tmp_D.v3 = String.format("%.2f", Float.valueOf(min+(max-min)*3/5));
		tmp_D.v4 = String.format("%.2f", Float.valueOf(min+(max-min)*4/5));
		

		tmp_D.max = v_max1;
		tmp_D.min = v_min1;
		
		return tmp_D; 
	}
	
	public digitD Ldigit(long max, long min, int digit){
		String v_max1, v_min1;
		digitD tmp_D = new digitD();

		v_max1 = max+"";
		v_min1 = min+"";

		if(v_max1.length()-digit>0){
			switch(v_max1.length()-digit){
			case 1:
				max = max/10;
				min = min/10;
				v_max1 = max+"";
				v_min1 = min+"";
				tmp_D.uWon = " (십원)";
				tmp_D.dCount = 1;
				break;
			case 2:
				max = max/100;
				min = min/100;
				v_max1 = max+"";
				v_min1 = min+"";
				tmp_D.uWon = " (백원)";
				tmp_D.dCount = 2;
				break;
			case 3:
				max = max/1000;
				min = min/1000;
				v_max1 = max+"";
				v_min1 = min+"";
				tmp_D.uWon = " (천원)";
				tmp_D.dCount = 3;
				break;
			case 4:
				max = max/10000;
				min = min/10000;
				v_max1 = max+"";
				v_min1 = min+"";
				tmp_D.uWon = " (만원)";
				tmp_D.dCount = 4;
				break;
			case 5:
				max = max/100000;
				min = min/100000;
				v_max1 = max+"";
				v_min1 = min+"";
				tmp_D.uWon = " (십만원)";
				tmp_D.dCount = 5;
				break;
			case 6:
				max = max/1000000;
				min = min/1000000;
				v_max1 = max+"";
				v_min1 = min+"";
				tmp_D.uWon = " (백만원)";
				tmp_D.dCount = 6;
				break;
			case 7:
				max = max/10000000l;
				min = min/10000000l;
				v_max1 = max+"";
				v_min1 = min+"";
				tmp_D.uWon = " (천만원)";
				tmp_D.dCount = 7;
				break;
			case 8:
				max = max/100000000l;
				min = min/100000000l;
				v_max1 = max+"";
				v_min1 = min+"";
				tmp_D.uWon = " (억원)";
				tmp_D.dCount = 8;
				break;
			case 9:
				max = max/1000000000l;
				min = min/1000000000l;
				v_max1 = max+"";
				v_min1 = min+"";
				tmp_D.uWon = " (십억원)";
				tmp_D.dCount = 9;
				break;
			case 10:
				max = max/10000000000l;
				min = min/10000000000l;
				v_max1 = max+"";
				v_min1 = min+"";
				tmp_D.uWon = " (백억원)";
				tmp_D.dCount = 10;
				break;
			}
		}else{
			tmp_D.uWon = " (원)";
		}
		
		tmp_D.v1 = (int)(min+(max-min)/5)+"";
		tmp_D.v2 = (int)(min+(max-min)*2/5)+"";
		tmp_D.v3 = (int)(min+(max-min)*3/5)+"";
		tmp_D.v4 = (int)(min+(max-min)*4/5)+"";

		tmp_D.max = v_max1;
		tmp_D.min = v_min1;
		
		return tmp_D;
	}
}
