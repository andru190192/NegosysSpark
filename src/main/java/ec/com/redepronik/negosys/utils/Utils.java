package ec.com.redepronik.negosys.utils;

import static ec.com.redepronik.negosys.utils.UtilsAplicacion.presentaMensaje;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;

import ec.com.redepronik.negosys.entity.Grupo;

public class Utils {

	public static String checksumEan13(String codigo) {
		codigo = codigo.substring(0, codigo.length() - 1);
		int numImp = 0;
		int numPar = 0;
		int sumTot = 0;
		String codAux = invertirPalabra(codigo);
		for (int i = 1; i <= codAux.length(); i++)
			if (i % 2 == 0 && i != 0)
				numPar += convertir(codAux.charAt(i - 1));
			else
				numImp += convertir(codAux.charAt(i - 1));
		sumTot = (numImp * 3) + numPar;
		String aux = invertirPalabra(String.valueOf(sumTot));
		int decenaSuperior = sumTot + (10 - convertir(aux.charAt(0)));
		int checksum = decenaSuperior - sumTot;
		if (checksum == 10)
			checksum = 0;
		return String.valueOf(checksum);
	}

	public static boolean validarDocumento(String numeroDocumento) {
		if (numeroDocumento.length() != 10 && numeroDocumento.length() != 13)
			return false;
		else {
			int numeroProvincias = 24;
			int provincia = Integer.parseInt(numeroDocumento.substring(0, 2));
			if (provincia <= 0 || provincia > numeroProvincias)
				return false;
			else {
				int d1 = Integer.parseInt(numeroDocumento.substring(0, 1));
				int d2 = Integer.parseInt(numeroDocumento.substring(1, 2));
				int d3 = Integer.parseInt(numeroDocumento.substring(2, 3));
				int d4 = Integer.parseInt(numeroDocumento.substring(3, 4));
				int d5 = Integer.parseInt(numeroDocumento.substring(4, 5));
				int d6 = Integer.parseInt(numeroDocumento.substring(5, 6));
				int d7 = Integer.parseInt(numeroDocumento.substring(6, 7));
				int d8 = Integer.parseInt(numeroDocumento.substring(7, 8));
				int d9 = Integer.parseInt(numeroDocumento.substring(8, 9));
				int d10 = Integer.parseInt(numeroDocumento.substring(9, 10));

				int p1 = 0, p2 = 0, p3 = 0, p4 = 0, p5 = 0, p6 = 0, p7 = 0, p8 = 0, p9 = 0;
				int modulo = 0;
				boolean natural = false, publica = false, privada = false;

				if (d3 == 7 || d3 == 8)
					return false;
				else if (d3 < 6) {
					natural = true;
					modulo = 10;
					p1 = d1 * 2;
					if (p1 >= 10)
						p1 -= 9;
					p2 = d2 * 1;
					if (p2 >= 10)
						p2 -= 9;
					p3 = d3 * 2;
					if (p3 >= 10)
						p3 -= 9;
					p4 = d4 * 1;
					if (p4 >= 10)
						p4 -= 9;
					p5 = d5 * 2;
					if (p5 >= 10)
						p5 -= 9;
					p6 = d6 * 1;
					if (p6 >= 10)
						p6 -= 9;
					p7 = d7 * 2;
					if (p7 >= 10)
						p7 -= 9;
					p8 = d8 * 1;
					if (p8 >= 10)
						p8 -= 9;
					p9 = d9 * 2;
					if (p9 >= 10)
						p9 -= 9;
				} else if (d3 == 6) {
					publica = true;
					modulo = 11;
					p1 = d1 * 3;
					p2 = d2 * 2;
					p3 = d3 * 7;
					p4 = d4 * 6;
					p5 = d5 * 5;
					p6 = d6 * 4;
					p7 = d7 * 3;
					p8 = d8 * 2;
					p9 = 0;
				} else if (d3 == 9) {
					privada = true;
					modulo = 11;
					p1 = d1 * 4;
					p2 = d2 * 3;
					p3 = d3 * 2;
					p4 = d4 * 7;
					p5 = d5 * 6;
					p6 = d6 * 5;
					p7 = d7 * 4;
					p8 = d8 * 3;
					p9 = d9 * 2;
				}

				int suma = p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9;
				int residuo = suma % modulo;

				int digitoVerificador = residuo == 0 ? 0 : modulo - residuo;
				int longitud = numeroDocumento.length();
				if (publica == true
						&& digitoVerificador != d9
						&& !numeroDocumento.substring(9, longitud).equals(
								"0001"))
					return false;
				else if (privada == true
						&& digitoVerificador != d10
						&& !numeroDocumento.substring(10, longitud).equals(
								"001"))
					return false;
				else if (natural == true && digitoVerificador != d10)
					return false;
				else if (natural == true
						&& numeroDocumento.length() > 10
						&& !numeroDocumento.substring(10, longitud).equals(
								"001"))
					return false;
			}
		}
		return true;
	}

	public static boolean comprobarEan13(String codigo) {
		if (!validaNumeros(codigo) && codigo.length() == 13)
			return codigo.substring(codigo.length() - 1, codigo.length())
					.compareToIgnoreCase(checksumEan13(codigo)) == 0 ? true
					: false;
		return false;
	}

	public static int convertir(char c) {
		return Integer.parseInt(String.valueOf(c));
	}

	public static boolean validacion(String palabra, String regexp,
			String message) {
		if (!Pattern.compile(regexp).matcher(palabra).matches()) {
			presentaMensaje(FacesMessage.SEVERITY_INFO, message);
			return false;
		} else
			return true;
	}

	public static String generarEan13(Grupo grupo) {
		String cod = "20" + String.format("%03d", grupo.getId());
		// + String.format("%07d", grupo.getContador() + 1);
		int numImp = 0;
		int numPar = 0;
		int sumTot = 0;
		String codAux = invertirPalabra(cod);
		for (int i = 1; i <= codAux.length(); i++)
			if (i % 2 == 0 && i != 0)
				numPar += convertir(codAux.charAt(i - 1));
			else
				numImp += convertir(codAux.charAt(i - 1));
		sumTot = (numImp * 3) + numPar;
		String aux = invertirPalabra(String.valueOf(sumTot));
		int decenaSuperior = sumTot + (10 - convertir(aux.charAt(0)));
		int checksum = decenaSuperior - sumTot;
		if (checksum == 10)
			checksum = 0;
		return cod = cod + checksum;
	}

	public static String insertarPalabra(int pos, String palabra, String element) {
		String nuevaPalabra = "";
		for (int i = 0; i < palabra.length(); i++)
			nuevaPalabra += (i == pos) ? palabra.charAt(i) + element : palabra
					.charAt(i);
		return nuevaPalabra;
	}

	public static String invertirPalabra(String palabra) {
		return new StringBuilder(palabra).reverse().toString();
	}

	public static String subString(String palabra, int fin) {
		palabra = invertirPalabra(palabra);
		return invertirPalabra(palabra.substring(0, fin));
	}

	public static String subString(String palabra, String subS) {
		palabra = invertirPalabra(palabra);
		return invertirPalabra(palabra.substring(subS.length(),
				palabra.length()));
	}

	public static boolean validaNumeros(String palabra) {
		for (int i = 0; i < palabra.length(); i++)
			if (Character.isLetter(palabra.charAt(i)))
				return true;
		return false;
	}
}
