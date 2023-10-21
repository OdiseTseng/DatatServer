package tw.intelegence.ncsist.sstp.utils.func;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAEncoder {
    public static String getSHA256(String str) {
        MessageDigest messageDigest;
        String encodeStr = ""; // 初始化雜湊值字串，以防出現例外
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8")); // 使用 UTF-8 編碼更新 MessageDigest
            byte[] hashBytes = messageDigest.digest(); // 完成雜湊計算
            encodeStr = byte2Hex(hashBytes); // 將雜湊值轉換為十六進制字串
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {

        }
        return encodeStr; // 返回計算的 SHA-256 雜湊值
    }

    // 將位元組陣列轉換為十六進制字串
    private static String byte2Hex(byte[] bytes) {
        StringBuilder stringBuffer = new StringBuilder();
        String temp;
        for (byte b : bytes) {
            temp = Integer.toHexString(b & 0xFF); // 將位元組轉換為十六進制字串
            if (temp.length() == 1) {
                stringBuffer.append("0"); // 對單個位元組的十六進制字串進行補零
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString(); // 返回十六進制表示的雜湊值字串
    }
}
