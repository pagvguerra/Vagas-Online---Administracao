package br.com.projeto.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import br.com.projeto.beans.CoordenadaBean;

/**
 * Classe utilizada para se conectar com a API do Google
 * 
 * @author Thiago
 *
 */
public class GoogleAPI {

	private static final String urlGoogle 	= "https://maps.googleapis.com/maps/api/geocode/json?";
	private static final String key 		= "&key=AIzaSyB_meePMsRkwT59CpQygGg14wQ0eJXC5q8";
	
	
	/**
	 * Método que retorna as coordenadas de um determinado endereço
	 * Quanto mais informações passadas, mais será a precisão
	 * 
	 * @param endereco Ex: "Rua Dias da Cruz, 188 Méier Rio de Janeiro"
	 * @return
	 * @throws IOException
	 */
	public CoordenadaBean buscaCoordenadasPorEndereco(String endereco) throws IOException {
		
		StringBuilder address 	= new StringBuilder("address=") ;
		String str 				= "";
		String json 			= "";
		BufferedReader in 		= null;
		
		try {
			
			montaEndereco(endereco, address);
			
			URL url			= new URL(urlGoogle + address.toString() + key);

			in				= new BufferedReader(new InputStreamReader(url.openStream()));
			
			while ((str = in.readLine()) != null)
				json = json + str;

			in.close();

			String location = json.substring(json.indexOf("\"location\" :"), json.indexOf("\"location_type\"") - 13);
			String lat 		= location.substring((location.indexOf("\"lat\"") + 8), location.indexOf(","));
			String lng 		= location.substring((location.indexOf("\"lng\"") + 8), (location.indexOf("}") - 12));
			
			if (lat.equals("") || lng.equals(""))
				throw new RuntimeException("Não foi possível setar as coordenadas");

			return new CoordenadaBean(lat, lng);
			
		} catch (MalformedURLException e) {
			throw(e);
		} catch (IOException e) {
			throw(e);
		} finally {
			if(in != null) {
				in.close();
			}
		}
	}
	
	/**
	 * Monta o formato a ser enviado via url no browser
	 * 
	 * @param endereco
	 * @param address
	 */
	private void montaEndereco(String endereco, StringBuilder address) {
		String[] arrayEnd = endereco.split(" ");
		
		for (String end : arrayEnd) {
			address.append(end);
			address.append("+");
		}
	}

}
