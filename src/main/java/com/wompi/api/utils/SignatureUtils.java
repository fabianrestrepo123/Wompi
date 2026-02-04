package com.wompi.api.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class SignatureUtils {
    private static final String INTEGRITY_SECRET =
            "stagtest_integrity_nAIBuqayW70XpUqJS4qf4STYiISd89Fp";

    public static String generateSignature(
            String reference,
            int amountInCents,
            String currency,
            String integritySecret) {
        try {
            String dataToSign =
                    reference +
                            amountInCents +
                            currency +
                            INTEGRITY_SECRET;

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(
                    dataToSign.getBytes(StandardCharsets.UTF_8)
            );

            return bytesToHex(hash);

        } catch (Exception e) {
            throw new RuntimeException("Error generating signature", e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }

}
