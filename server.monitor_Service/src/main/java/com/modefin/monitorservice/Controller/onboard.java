package com.modefin.monitorservice.Controller;

import java.net.http.HttpResponse;
import java.util.HashMap;

import org.apache.http.HttpStatus;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import com.netflix.spectator.impl.Config;

public class onboard {
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//		public HashMap<String, Object> onboard(HashMap inputMap) {
//			JSONObject resultObject = new JSONObject();
//			String msg = null;
//			try {
//				LogUtils.println("External Integration Data Parameters:" + resultObject);
//				/* Prepare data object */
//				JSONObject dataObject = prepareDataObject(inputMap);
//	
//				/* Update Branch code */
//				AccountUtil account = new AccountUtil();
//				dataObject = account.getAccountBranchCode(dataObject, StringUtils.getMapValue(inputMap, "ServiceCode"));
//				resultObject.put("data", dataObject);
//	
//				/* Prepare config object */
//				Config config = prepareConfig(inputMap);
//	
//				HashMap<String, Object> transformDataMap;
//				String actionId = StringUtils.getMapValue(inputMap, "TemplateActionId");
//				LogUtils.println("Mongod DB ActionId:" + actionId);
//				transformDataMap = transformUserRequestData("", "", resultObject, actionId, config);
//				String userPayload = transformDataMap.get("payLoad").toString();
//	
//				/* Call middleware */
//				HttpResponse httpResponse = fetchMiddleware(config, userPayload);
//	
//				LogUtils.println("Responsestatus:" + httpResponse.getStatus());
//				LogUtils.println("ResponseBody:" + httpResponse.getBody());
//	
//				String responseBody = httpResponse.getBody();
//	
//				String serviceCode = StringUtils.getMapValue(inputMap, "ServiceCode");
//				if (httpResponse.getStatus() == HttpStatus.SC_OK && responseBody != null && !responseBody.isEmpty()
//						&& !responseBody.contains("\"message\":")) {
//					JSONObject filterResponse = applyFilters(transformDataMap, responseBody);
//					if (!checkMobile(dataObject.getString("MOBNUM").trim())
//							.equals(checkMobile(filterResponse.getString("mobile").trim()))) {
//						JSONObject message = new JSONObject();
//						msg = "Sorry, you have entered the wrong details. Kindly contact our Customer Service for assistance.";
//						msg = MFMessageCodeUtil.getConsoleMessageCode("MOBILE_NOT_MATCH_CBS_ERROR", msg);
//						message.put("Error", msg);
//						/*
//						 * message.put("Error",
//						 * "Dear customer, you have entered the wrong details, please enter valid details and try again"
//						 * );
//						 */
//						return prepareRequestServiceResponse(message, FAILURE_CODE);
//					}
//					LogUtils.println("accountClass:" + filterResponse.getString("accountClass"));
//					if (!filterByAllowedAccountClass(filterResponse.getString("accountClass"))) {
//						JSONObject message = new JSONObject();
//						msg = "Sorry, this account number not allowed. Kindly contact our Customer Service for assistance.";
//						msg = MFMessageCodeUtil.getConsoleMessageCode("ACCOUNT_NOT_ALLOWED_ERROR", msg);
//						message.put("Error", msg);
//						return prepareRequestServiceResponse(message, FAILURE_CODE);
//					}
//					MFCustomerService customerService = new MFCustomerService();
//					MFCustomerRecord[] records = customerService.loadMFCustomerRecords(
//							"select * from customer where mobile='" + checkMobile(dataObject.getString("MOBNUM")) + "'");
//					if (records.length > 0) {
//						JSONObject message = new JSONObject();
//						msg = "Dear customer, you have already registered. Please do login";
//						msg = MFMessageCodeUtil.getConsoleMessageCode("PROFILE_EXIST_ERROR", msg);
//						message.put("Error", msg);
//						return prepareRequestServiceResponse(message, FAILURE_CODE);
//					}
//					boolean isCustomerExist = customerService.isCIFAlreadyRegistered(dataObject.getString("CUSTNO"));
//					if (isCustomerExist == true) {
//						JSONObject message = new JSONObject();
//						msg = "Dear customer, you have already registered. Please do login";
//						msg = MFMessageCodeUtil.getConsoleMessageCode("PROFILE_EXIST_ERROR", msg);
//						message.put("Error", msg);
//						// message.put("Error", "Dear customer, A profile exists
//						// with these details. Please login.");
//						return prepareRequestServiceResponse(message, FAILURE_CODE);
//					}
//	
//					/* Save user details in cust_acq table */
//					return addCustomerAcq(inputMap, filterResponse);
//	
//				} else {
//					JSONObject message = new JSONObject();
//					message.put("Error", "Invalid Customer Number");
//					return prepareRequestServiceResponse(message, FAILURE_CODE);
//				}
//	
//			} catch (Exception e) {
//				// HashMap<String, Object> inputMapja = new HashMap<String,
//				// Object>();
//				JSONObject message = new JSONObject();
//				LogUtils.println("Exception in Customer self onboarding: " + e.getMessage());
//				String errorMessage = MFMessageCodeUtil.getMessage("ERROR_MESG3", "en_US");
//				if (StringUtils.isNullOrEmpty(errorMessage)) {
//					errorMessage = "Unable to connect at this point of time , please try again after some time.";
//				}
//	
//				message.put("Error", errorMessage);
//				message.put("ErrorMessage", errorMessage);
//				// message.put("Error", "Exception in Customer self onboarding");
//				return prepareRequestServiceResponse(message, FAILURE_CODE);
//				// return IntegrationResponse.successResultMessage(inputMapja,
//				// "Android", "ERROR");
//			}
//	
//		}
//	
}

