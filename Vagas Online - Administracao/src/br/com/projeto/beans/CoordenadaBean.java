package br.com.projeto.beans;

import java.io.Serializable;

/**
 * Classe que representa as coordenadas latitude e longitude utilizadas na API do Google
 * @author Thiago
 *
 */
public class CoordenadaBean implements Serializable {
	
	private static final long serialVersionUID = -2377554280457852789L;

	private String latitude;
	private String longitude;

	public CoordenadaBean() {}
		
	public CoordenadaBean(String latitude, String longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}
	
	@Override
	public String toString() {
		return latitude + "," + longitude;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result
				+ ((longitude == null) ? 0 : longitude.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoordenadaBean other = (CoordenadaBean) obj;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		return true;
	}
	
}