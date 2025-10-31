package com.modefinsever.kafka.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Stream;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import org.apache.catalina.util.URLEncoder;

public class SendHTTPSMS  {

//	public HashMap<String, Object> sendSMS(HashMap<String, Object> inputMap) throws Exception {
//		HashMap<String, Object> outputMap = new HashMap<>(inputMap);
//
//		String serviceURL = PropertyUtil.getProperty("SMSURL");
//		String recipient = String.valueOf(inputMap.get("MobileNumber"));
//		String message = String.valueOf(inputMap.get("Message"));
//
//		// Remove prefix if any
//		if (recipient.contains("-")) {
//			recipient = recipient.split("-")[1];
//		}
//
//		// Encode message
//		message = URLEncoder.encode(message, "UTF-8");
//
//		// UAT Allowed Mobile Check
//		String allowedMobiles = PropertyUtil.getProperty("SMSUATALLOWEDMOBILES");
//		boolean allowedStatus = Stream.of(allowedMobiles.split(",")).anyMatch(m -> m.equals(recipient));
//
//		// Generate 4-digit random number for message ID (if needed)
//		int randomNumber = new Random().nextInt(9999);
//		String randomId = String.format("%04d", randomNumber);
//
//		System.out.println("Recipient: " + recipient + ", Allowed: " + allowedStatus + ", RandomId: " + randomId);
//
//		// Build final URL
//		String url = serviceURL + "?destination=" + recipient + "&message=" + message;
//
//		try {
//			int responseCode = sendJsonGETRequest(url);
//
//			if (responseCode != 200) {
//				outputMap.put("ErrorMessage", "Failed to deliver SMS.");
//			} else {
//				outputMap.put("ResultMessage", "SMS Message Delivered at " + java.time.LocalDateTime.now());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			outputMap.put("ErrorMessage", "Failed to deliver SMS: " + e.getMessage());
//		}
//
//		return outputMap;
//	}
//
//	public static int sendJsonGETRequest(String url) throws Exception {
//		String channelID = PropertyUtil.getProperty("CHANNELID");
//		String apiKey = PropertyUtil.getProperty("SMSAPIKey");
//		MFSecurityUtil securityUtil = new MFSecurityUtil();
//		apiKey = securityUtil.decryptRegular(apiKey);
//
//		int connectionTimeout = PropertyUtil.getPropertyAsInt("CBSCONNECTIONTIMEOUT");
//		int readTimeout = PropertyUtil.getPropertyAsInt("CBSREADTIMEOUT");
//
//		URL obj = new URL(url);
//		HttpsURLConnection conn = (HttpsURLConnection) obj.openConnection();
//
//		conn.setSSLSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault());
//		conn.setAllowUserInteraction(true);
//		conn.setRequestMethod("GET");
//		conn.setRequestProperty("CHANNEL_ID", channelID);
//		conn.setRequestProperty("APIKey", apiKey);
//		conn.setConnectTimeout(connectionTimeout);
//		conn.setReadTimeout(readTimeout);
//		conn.setDoInput(true);
//		conn.setDoOutput(true);
//		conn.setUseCaches(false);
//
//		System.out.println("Sending GET request to URL: " + url);
//
//		int responseCode = conn.getResponseCode();
//		System.out.println("GET Response Code: " + responseCode);
//
//		try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
//			StringBuilder response = new StringBuilder();
//			br.lines().forEach(line -> response.append(line).append('\n'));
//			System.out.println(response);
//		}
//
//		return responseCode;
//	}
//
//	public static void main(String[] args) throws Exception {
//		HashMap<String, Object> input = new HashMap<>();
//		input.put("MobileNumber", "9876543210");
//		input.put("Message", "Hello, this is a test SMS.");
//
//		SendHTTPSMS sender = new SendHTTPSMS();
//		HashMap<String, Object> result = sender.sendSMS(input);
//		System.out.println(result);
//	}
}
