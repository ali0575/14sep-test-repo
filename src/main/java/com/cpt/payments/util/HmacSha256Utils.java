package com.cpt.payments.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacSha256Utils {
	
	
	
	public static String	generateSignature(String secretKey, String jsonInput){
		String Signature = null;
		try {
			// create a SecretKeySpec object from the secret key
			SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

			// Create an instance of the HMAC-SHA256 algorithm
			Mac mac = Mac.getInstance("HmacSHA256");

			// Initialize the Mac object with the secret key
			mac.init(secretKeySpec);

			// Calculate the HMAC-SHA256 hash of the data
			byte[] hmacData = mac.doFinal(jsonInput.getBytes(StandardCharsets.UTF_8));
			// Convert the hash bytes to a Base64-encoded string
			 Signature = Base64.getEncoder().encodeToString(hmacData);

			System.out.println("HMAC-SHA256 Signature: " + Signature);

		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			e.printStackTrace();
		}
	
			return Signature;
		}
}
