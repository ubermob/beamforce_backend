package ru.beamforce.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Andrey Korneychuk on 10-Apr-22
 * @version 1.0
 */
@Component
public class PoCServerDriverComponent {

	@Value("${poc.host}")
	private String url;

	@Bean
	public PoCServerDriver getPoCServerDriver() {
		return new PoCServerDriver(url);
	}
}