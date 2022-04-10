package ru.beamforce.bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Andrey Korneychuk on 10-Apr-22
 * @version 1.0
 */
public class PoCServerDriver {

	private final String url;

	public PoCServerDriver(String url) {
		this.url = url;
	}

	public String get() throws IOException {
		URL url = new URL(this.url);
		URLConnection urlConnection = url.openConnection();
		urlConnection.connect();
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		return br.readLine();
	}
}