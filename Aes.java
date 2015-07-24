import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESSecurityUtil {

    private static final String AES = "AES";  
    private static final String CHARSET_NAME = "utf-8";  

    /**
    *获取密钥
    *
    *@parampassword
    *   加密密码
    *@return
    *@throwsNoSuchAlgorithmException
    */
    private static SecretKeySpec getKey(String password) throws NoSuchAlgorithmException{  
        // 密钥加密器生成器
        KeyGenerator kgen = KeyGenerator.getInstance(AES);  
        SecureRandom random=SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(password.getBytes());
        kgen.init(128, random);    
        // 创建加密器
        SecretKey secretKey = kgen.generateKey();  
        byte[] enCodeFormat = secretKey.getEncoded();    
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, AES);   
        return key;  
    }  
    
    /**
    *加密
    *@paramstr原文
    *@parampassword加密密码
    *@return
    */
    public static String encode(String str, String password)  
        {  
    byte[] arr = encodeToArr(str, password);  
    return byteArrToString(arr);  
        }  
    
    /**
    *
    *加密
    *@authorwuhongbo
    *@paramstr原文
    *@parampassword加密密码
    *@return
    */
    private static byte[] encodeToArr(String str, String password)  
        {  
    try
            {  
                Cipher cipher = Cipher.getInstance(AES);// 创建密码器
    byte[] byteContent = str.getBytes(CHARSET_NAME);  
    
    cipher.init(Cipher.ENCRYPT_MODE, getKey(password));// 初始化
    byte[] result = cipher.doFinal(byteContent);    
    return result;  
            }  
    catch (Exception e)  
            {  
    e.printStackTrace();  
            }    
    return null;  
        }  
    
    /**
    *解密
    *
    *@paramhexStr密文
    *@parampassword加密密码
    *@return
    */
    public static String decode(String hexStr, String password){  
        byte[] arr = string2ByteArr(hexStr);  
        return decode(arr, password);  
    }  
    
    /**
    *解密
    *
    *@authorwuhongbo
    *@paramarr密文数组
    *@parampassword加密密码
    *@return
    */
    private static String decode(byte[] arr, String password)  {  
        try{  
            // 创建密码器
            Cipher cipher = Cipher.getInstance(AES);  
            cipher.init(Cipher.DECRYPT_MODE, getKey(password));// 初始化
        
            byte[] result = cipher.doFinal(arr);  
            return new String(result, CHARSET_NAME);  
       }catch (Exception e){  
           e.printStackTrace();  
       }  
    return null;  
   }  
    
    
    /**
    *byte数组转成16进制字符串
    *
    *@paramarr
    *@return
    */
    private static String byteArrToString(byte[] arr)  {  
        StringBuffer sb = new StringBuffer();   
        for (int i = 0; i <arr.length; i++)  {    
            String s = Integer.toString(arr[i] + 128, 16);  
            if (s.length() == 1){  
                s = "0" + s;  
            }    
            sb.append(s);  
        }  
        
        return sb.toString().toUpperCase();  
    }  
    
    /**
    *16进制字符串转成byte数组
    *
    *@paramarr
    *@return
    */
    private static byte[] string2ByteArr(String s)  {  
        s = s.toUpperCase();  
        String str = "0123456789ABCDEF";    
        byte[] arr = new byte[s.length() / 2];   
        for (int i = 0; i <arr.length; i++){  
            char s1 = s.charAt(i * 2);  
            char s2 = s.charAt(i * 2 + 1);    
            int tmp1 = str.indexOf(s1) * 16;  
            int tmp2 = str.indexOf(s2);    
            arr[i] = (byte) (tmp1 + tmp2 - 128);  
            }    
        return arr;  
    }  

    public static void main(String[] args) throws Exception  {
        System.out.println(decode

("29C94643E0954F5E247A705631C534C4596954662B9C0312E46623D3C625FDF9EA9A85511AE9142FAF2F703FCAE5C833A99839801F776618029E75E4FA074776F2A3880C600B3EEDAEE999A382424D07A87A729496E1E3F41D3F9C76FE0E0A0C3E89EF1FC10D9FA641FD5931FB00603BC5A0520DEB6

5B7480053FB554D27DAE85F6C7F8E5A0637B6E7E3F815AF7512A2", "imcc"));
    }
}
