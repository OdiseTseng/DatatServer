package tw.intelegence.ncsist.sstp.utils.func;

import java.lang.reflect.Field;
import tw.intelegence.ncsist.sstp.utils.text.NettyCode;

public class CodeDecoder {

    public static String getCodeMsg(String cmdHead, int targetCode){
        System.out.println("getCodeMsg ;; cmdHead : " + cmdHead + " ;; targetCode : " + targetCode);
        String targetData ="";
        // 使用反射獲取靜態變數資料
        try {
            Class<?> myClass = NettyCode.class;
            Field field = myClass.getDeclaredField(cmdHead + "_" + targetCode);

            // 設置字段可訪問（因為它是私有的）
//			field.setAccessible(true);

            // 獲取靜態變數的值
            targetData = (String) field.get(null);

            System.out.println("靜態變數" + targetCode + "的資料是：" + targetData);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("無效的靜態變數編碼或訪問錯誤");
        }

        return targetData;
    }
}
