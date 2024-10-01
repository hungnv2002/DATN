package DrSport.config;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Component
public class VNPayConfig {
    public static String vnp_PayUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public static String vnp_Returnurl = "/vnpay-payment";
    public static String vnp_TmnCode = "E6T0BY41";
    public static String vnp_HashSecret = "O9DO650RACC6NLR4LS7AVNIOG9SACT4W";
    public static String vnp_apiUrl = "https://sandbox.vnpayment.vn/merchant_webapi/api/transaction";

    // Phương thức để tạo băm MD5
    public static String md5(String message) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(message.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    // Phương thức để tạo băm SHA-256
    public static String sha256(String message) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(message.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    // Phương thức để băm tất cả các trường sử dụng HMAC-SHA512
    public static String hashAllFields(Map<String, String> fields) {
        List<String> fieldNames = new ArrayList<>(fields.keySet());
        Collections.sort(fieldNames);
        StringBuilder sb = new StringBuilder();
        for (String fieldName : fieldNames) {
            String fieldValue = fields.get(fieldName);
            if (fieldValue != null && !fieldValue.isEmpty()) {
                sb.append(fieldName).append("=").append(fieldValue);
                sb.append("&");
            }
        }
        // Loại bỏ "&" cuối cùng nếu có
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return hmacSHA512(vnp_HashSecret, sb.toString());
    }

    // Phương thức để tạo băm HMAC-SHA512
    public static String hmacSHA512(final String key, final String data) {
        try {
            if (key == null || data == null) {
                throw new NullPointerException();
            }
            Mac hmac512 = Mac.getInstance("HmacSHA512");
            byte[] hmacKeyBytes = key.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
            hmac512.init(secretKey);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] result = hmac512.doFinal(dataBytes);
            StringBuilder sb = new StringBuilder(2 * result.length);
            for (byte b : result) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }

    // Phương thức để lấy địa chỉ IP từ yêu cầu
    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    // Phương thức để tạo số ngẫu nhiên có độ dài cụ thể
    public static String getRandomNumber(int len) {
        Random rnd = new Random();
        String chars = "0123456789";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
