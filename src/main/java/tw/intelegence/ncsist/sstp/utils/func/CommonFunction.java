package tw.intelegence.ncsist.sstp.utils.func;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import tw.intelegence.ncsist.sstp.utils.text.CommonString;


public class CommonFunction {

	public static LinkedHashMap< String ,String> getMap(Object sourceObj)
			throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		System.out.println(sourceObj.getClass().getSimpleName() + " getMap");

		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		Field[] fields = sourceObj.getClass().getDeclaredFields();

		for ( int i = 0; i < fields.length; i++ ){
			Field field = fields[i];
			String name = field.getName();
			String methodName = "get" + name.substring( 0,1 ).toUpperCase() + name.substring( 1, name.length() );
			Method method = sourceObj.getClass().getMethod( methodName );
			map.put( field.getName(), String.valueOf(method.invoke(sourceObj)) );
		}

		return map;
	}

	public static void setData( Object sourceObj, ResultSet rs, String key, Integer valType )
			throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		System.out.println(sourceObj.getClass().getSimpleName() + " setData : key -> " + key + " val -> " + valType);

		Field[] fields = sourceObj.getClass().getDeclaredFields();

		for ( int i = 0; i < fields.length; i++ ){
			Field field = fields[i];
			String name = field.getName();
			String methodName = "set" + name.substring( 0,1 ).toUpperCase() + name.substring( 1, name.length() );
			System.out.println("methodName : " + methodName);
			if(methodName.contains("setId")) continue;

			if(name.equals( key )){

				Class[] args = new Class[1];
				Method method = null;

				switch ( valType ){
				case 0:	// boolean
					System.out.println("boolean => " + key + " = " + rs.getBoolean(key));
					args[0] = Boolean.class;
					method = sourceObj.getClass().getMethod( methodName, args);
					method.invoke(sourceObj, rs.getBoolean( key ));
					break;

				case 1:	// float
					System.out.println("float   => " + key + " = " + rs.getFloat(key));
					args[0] = Float.class;
					method = sourceObj.getClass().getMethod( methodName, args);
					method.invoke(sourceObj, rs.getFloat( key ));
					break;

				case 2: // integer
					System.out.println("int     => " + key + " = " + rs.getInt(key));
					args[0] = Integer.class;
					method = sourceObj.getClass().getMethod( methodName, args);
					method.invoke(sourceObj, rs.getInt( key ));
					break;

				default: // string
					System.out.println("string  => " + key + " = " + rs.getString(key));
					args[0] = String.class;
					method = sourceObj.getClass().getMethod( methodName, args);
					method.invoke(sourceObj, rs.getString( key ));
					break;
				}
			}
		}

	}

	public static String getCurrentFunctionName() {
		// 使用當前執行緒的堆疊追蹤（stack trace）獲取當前函式的堆疊框架（stack frame）
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

		// 當前函式的堆疊框架索引為2（索引0為getStackTrace方法，索引1為getCurrentFunctionName方法）
		StackTraceElement currentFrame = stackTrace[2];

		// 獲取函式名稱並返回
		return currentFrame.getMethodName();
	}

	public static String encrypt(String input) {
		try {
			// 創建SHA-256的MessageDigest實例
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			// 將輸入字串轉換為位元組陣列
			byte[] encodedHash = digest.digest(input.getBytes());

			// 將位元組陣列轉換為十六進制字串
			StringBuilder hexString = new StringBuilder();
			for (byte b : encodedHash) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}

			// 返回SHA-256加密後的字串
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String setResponse(String info, String msg){
		return setResponse(info, msg, "");
	}
	public static String setResponse(String info, String msg, String session){
		return info + CommonString.SPLIT_KEY + msg + CommonString.SPLIT_KEY + session;
	}

	public static String getResponseInfo(String response){
		return response.split( CommonString.SPLIT_KEY)[0];
	}

	public static String getResponseMsg(String response){
		return response.split( CommonString.SPLIT_KEY)[1];
	}
}

