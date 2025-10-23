package com.modefinsever.kafka.utils;

public class SendHTTPSMS  {

//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public HashMap sendSMS(HashMap inputMap) throws Exception {
//		HashMap<String, Object> outputMap = new HashMap<String, Object>(inputMap);
////		String serviceURL = PropertyUtil.getProperty("SMSURL");
////		String messsageid = getMapValue(inputMap, "MessageId");
//
//		int randomNumber = (new Random()).nextInt(9999);
//		String formattedRandomNumber4 = StringUtils.trimLeadingSize(randomNumber + "", 4);
////		messsageid = formattedRandomNumber4 + messsageid;
////		messsageid = StringUtils.trimLeadingSize(messsageid, 8);
//
//		String recipient = getMapValue(inputMap, "MobileNumber");
//		System.out.println("recipient :" + recipient);
//		if (recipient.contains("-")) {
//			String[] corMobile = recipient.split("-");
//			recipient = corMobile[1];
//		}
//
//		System.out.println("After recipient :::::" + recipient);
//		String message = getMapValue(inputMap, "Message");
//
//		message = URLEncoder.encode(message, "UTF-8");
//
//		// UAT Code Start
//		String allowedMobiles = PropertyUtil.getProperty("SMSUATALLOWEDMOBILES");
//		String allowedlists[] = allowedMobiles.split(",");
//		boolean allowedStatus = false;
//		for (int i = 0; i < allowedlists.length; i++) {
//
//			String mobile = allowedlists[i];
//			if (mobile.equals(recipient)) {
//				allowedStatus = true;
//			}
//		}
//
//		System.out.println("After allowedStatus :::::" + allowedStatus);
//		
///*		if (allowedStatus) {*/
//			// UAT Code End
//			String url = serviceURL + "?destination=" + recipient + "&message=" + message;
//
//			try {
//
//				int responseCode = sendJsonGETRequest(url);
//
//				String errorMessage;
//
//				if (responseCode != 200) {
//					errorMessage = "Failed to deliver SMS.";
//
//					outputMap.put("ErrorMessage", errorMessage);
//					return outputMap;
//				}
//
//				outputMap.put("ResultMessage", "SMS Message Delivered. Sent at" + new java.util.Date());
//
//			} catch (Exception exception) {
//				exception.printStackTrace();
//				outputMap.put("ErrorMessage", "Failed to deliver SMS");
//				return outputMap;
//			}
//			// UAT Code Start
//		/*} else {
//			outputMap.put("ResultMessage", "SMS Message Delivered. Sent at" + new java.util.Date());
//		}*/
//		// UAT Code End
//		return outputMap;
//	}
//
//	public static void main(String args[]) {
//		System.out.println("" + StringUtils.trimLeadingSize("1051", 8));
//		System.out.println("" + StringUtils.trimLeadingMaxSize("1051", 7, "1"));
//		int randomNumber = (new Random()).nextInt(9999);
//		String formattedRandomNumber4 = StringUtils.trimLeadingSize(randomNumber + "", 4);
//		System.out.println("" + formattedRandomNumber4);
//		String messageid = "3";
//		messageid = formattedRandomNumber4 + messageid;
//		System.out.println("***" + messageid);
//		messageid = StringUtils.trimLeadingSize(messageid, 8);
//
//		System.out.println("messageid" + messageid);
//
//	}
//
//	@SuppressWarnings({})
//	public static int sendJsonGETRequest(String url) throws Exception {
//
//		String channelID = PropertyUtil.getProperty("CHANNELID");
//		String apiKey = PropertyUtil.getProperty("SMSAPIKey");
//		 MFSecurityUtil secutill=new MFSecurityUtil();
//		 apiKey =secutill.decryptRegular(apiKey);
//		System.out.println("URL isapiKey::::::" + apiKey);
//		int connectionTimeOut = PropertyUtil.getPropertyAsInt("CBSCONNECTIONTIMEOUT");
//		int readTimeOut = PropertyUtil.getPropertyAsInt("CBSREADTIMEOUT");
//		URL obj = new URL(url);
//		HttpsURLConnection conn = (HttpsURLConnection) obj.openConnection();
//		SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
//		conn.setSSLSocketFactory(socketFactory);
//		conn.setAllowUserInteraction(true);
//		conn.setRequestMethod("GET");
//		conn.setRequestProperty("CHANNEL_ID", channelID);
//		conn.setRequestProperty("APIKey", apiKey);
//		conn.setConnectTimeout(connectionTimeOut);
//		conn.setReadTimeout(readTimeOut);
//		conn.setDoInput(true);
//		conn.setDoOutput(true);
//		conn.setUseCaches(false);
//		StringBuilder response = new StringBuilder();
//		System.out.println("URL is=" + url);
//		int responseCode = conn.getResponseCode();
//		System.out.println("GET Response Code :: " + responseCode);
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//		String line;
//		while ((line = br.readLine()) != null) {
//			response.append(line);
//			response.append('\n');
//		}
//		br.close();
//		System.out.println(response.toString());
//
//		return responseCode;
//	}

}
