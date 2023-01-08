package com.controlFormato;

public class ControlFormato
{
	public static boolean esCIValida(String ci)
	{
		if(ci.length() != 7 && ci.length() != 8)
		{
			return false;
		}
		else
		{
			try
			{
				Integer.parseInt(ci);
			} catch(NumberFormatException e)
			{
				return false;
			}
		}

		if(ci.length() == 8)
		{
			int i = 0;
			int [] digitos = new int [8];

			while(i<= digitos.length-1)
			{
				digitos[i] = Integer.parseInt(ci.charAt(i) + "");
				i++;
			}
			int suma = 0;
			i = 0;

			digitos[0] *= 2;
			digitos[1] *= 9;
			digitos[2] *= 8;
			digitos[3] *= 7;
			digitos[4] *= 6;
			digitos[5] *= 3;
			digitos[6] *= 4;

			while(i<= digitos.length-2)
			{
				suma += digitos[i];
				i++;
			}

			int digitoCorrecto;

			if(suma%10 != 0)
			{
				digitoCorrecto = 10 - (suma%10);
			}
			else
			{
				digitoCorrecto = 0;
			}
			if(digitoCorrecto==digitos[7])
			{
				return true;
			}
			else
			{
				return false;
			}
		}

		if(ci.length() == 7)
		{
			int i = 0;
			int [] digitos = new int [7];

			while(i<= digitos.length-1)
			{
				digitos[i] = Integer.parseInt(ci.charAt(i) + "");
				i++;
			}
			int suma = 0;
			i = 0;

			digitos[0] *= 9;
			digitos[1] *= 8;
			digitos[2] *= 7;
			digitos[3] *= 6;
			digitos[4] *= 3;
			digitos[5] *= 4;

			while(i<= digitos.length-2)
			{
				suma += digitos[i];
				i++;
			}
			int digitoCorrecto;
			
			if(suma%10 != 0)
			{
				digitoCorrecto = 10 - (suma%10);
			}
			else
			{
				digitoCorrecto = 0;
			}
			if(digitoCorrecto==digitos[6])
			{
				return true;
			}
			else
			{
				return false;
			}
		}

		return false;
	}

	public static boolean Alfabetico(String dato)
	{
		for (int i = 0; i < dato.length(); i++)
		{
			char caracter = dato.toUpperCase().charAt(i);
			int valorASCII = (int)caracter;
			if (valorASCII != 32 && valorASCII != 209 && (valorASCII < 65 || valorASCII > 90) && valorASCII != 201 && valorASCII != 193 && valorASCII != 205 && valorASCII != 221 && valorASCII != 218)
				return false;
		}
		return true;
	}

	public static boolean NumericoDecimal(String dato)
	{
		try
		{
			@SuppressWarnings("unused")
			double numero = Double.parseDouble(dato);
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}

	public static boolean Numerico(String dato)
	{
		try
		{
			@SuppressWarnings("unused")
			int numero = Integer.parseInt(dato);
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}

	public static boolean AlfaNumerico (String dato)
	{
		for (int i = 0; i < dato.length(); i++)
		{
			char caracter = dato.toUpperCase().charAt(i);
			int valorASCII = (int)caracter;
			if (valorASCII != 32 && valorASCII != 209 && (valorASCII < 65 || valorASCII > 90) && (valorASCII < 48 || valorASCII > 57) && valorASCII != 201 && valorASCII != 193 && valorASCII != 205 && valorASCII != 221 && valorASCII != 218)
				return false;
		}
		return true;
	}
}