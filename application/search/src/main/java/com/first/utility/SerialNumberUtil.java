package com.first.utility;

public class SerialNumberUtil {
	
	/***
	 * 
	 * @param markNum  标识数字  1.转账 ZZ 2.充值  CZ 3.预购 YG 4.退单 TD 5.冻结 DJ 6.结算  8.拨付BF
	 * @return  流水号  
	 */
	public static String getSerialNumber(int  markNum){
	
	    String SN=System.nanoTime()+"";
		switch (markNum) {
		case 1:
			 SN="ZZ"+SN;
			break;
		case 2:
			 SN="CZ"+SN;
			break;
		case 3:
			 SN="YG"+SN;
			break;
		case 4:
			 SN="TD"+SN;
			break;
		case 5:
			 SN="DJ"+SN;
			 break;
		case 6:
			 SN="JS"+SN;
			 break;
		case 7:
			SN="XF"+SN;
			break;
		case 8:
			SN="BF"+SN;
			break;
		default:
			 SN="WZ0000000000000";
		}
		
		
		return SN;
	}

}
