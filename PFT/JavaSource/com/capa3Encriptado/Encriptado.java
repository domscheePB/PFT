package com.capa3Encriptado;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Encriptado
{
public static String secretKey = "Internet_Explorers_PBSRSMOMMMGM";
	
	public static String ecnode(String cadena)
    {
        String encriptacion = "";
        try
        {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] llavePassword = md5.digest(secretKey.getBytes("utf-8"));
            byte[] BytesKey = Arrays.copyOf(llavePassword, 24);
            SecretKey key = new SecretKeySpec(BytesKey, "DESede");
            Cipher cifrado = Cipher.getInstance("DESede");
            cifrado.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainTextBytes = cadena.getBytes("utf-8");
            byte[] buf = cifrado.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            encriptacion = new String(base64Bytes);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return encriptacion;
    }
	
  public static String deecnode(String cadenaEncriptada)
  {
      String desencriptacion = "";
      try
      {
          byte[] message = Base64.decodeBase64(cadenaEncriptada.getBytes("utf-8"));
          MessageDigest md5 = MessageDigest.getInstance("MD5");
          byte[] digestOfPassword = md5.digest(secretKey.getBytes("utf-8"));
          byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
          SecretKey key = new SecretKeySpec(keyBytes, "DESede");
          Cipher decipher = Cipher.getInstance("DESede");
          decipher.init(Cipher.DECRYPT_MODE, key);
          byte[] plainText = decipher.doFinal(message);
          desencriptacion = new String(plainText, "UTF-8");
      } catch (Exception ex)
      {
          ex.printStackTrace();
      }
      return desencriptacion;
  }
	
	 public static String contraPrimerInicio()
	 {
    	String contraseña = "";
    	
    	int valorContra = (int) Math.floor(Math.random()*9000+1000);
    	
		int orden = (int) Math.floor(Math.random()*6+1);
		
		if(orden==1)
		{
			contraseña = "PSOMGS" + String.valueOf(valorContra);
		}
		else if(orden==2)
		{
			contraseña = "SOMGSP" + String.valueOf(valorContra);
		}
		else if(orden==3)
		{
			contraseña = "OMGSPS" + String.valueOf(valorContra);
		}
		else if(orden==4)
		{
			contraseña = "MGSPSO" + String.valueOf(valorContra);
		}
		else if(orden==5)
		{
			contraseña = "GSPSOM" + String.valueOf(valorContra);
		}
		else if(orden==6)
		{
			contraseña = "SPSOMG" + String.valueOf(valorContra);
		}
		else
		{
			contraseña = "GMOSPS" + String.valueOf(valorContra);
		}
    	return contraseña;
    }
}
