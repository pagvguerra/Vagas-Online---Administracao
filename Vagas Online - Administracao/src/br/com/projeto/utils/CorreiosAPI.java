package br.com.projeto.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import br.com.projeto.beans.EnderecoCorreios;


/**
 * Classe que se conecta a API Correios através de chamada via URL
 * 
 * @author Thiago
 *
 */
public class CorreiosAPI {
	
	
	private static final String URL_VIACEP 	= "https://viacep.com.br/ws/";
	private static final String FORMATO 	= "/json/";
	

	public EnderecoCorreios buscaEnderecoPorCEP(String cep) throws IOException, ParseException {
		
		StringBuilder address 	= new StringBuilder(URL_VIACEP) ;
		String str 				= "";
		String json 			= "";
		BufferedReader in 		= null;
		
		try {
			
			
			URL url			= new URL(address.append(cep).append(FORMATO).toString());
			in				= new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			
			while ((str = in.readLine()) != null)
				json = json + str;

			in.close();
			
			JSONParser parser = new JSONParser();
			
			JSONObject obj = (JSONObject)parser.parse(json);
			
			String logradouro 	= (String)obj.get("logradouro");
			String complemento 	= (String)obj.get("complemento");
			String bairro 		= (String)obj.get("bairro");
			String localidade 	= (String)obj.get("localidade");
			String uf 			= (String)obj.get("uf");
			
			return new EnderecoCorreios(cep, logradouro, complemento, bairro, localidade, uf);
					
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

}
