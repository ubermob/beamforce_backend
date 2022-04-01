package ru.beamforce.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import utoolsdriver.minididriver.MinidiDriver;
import utoolsdriver.minididriver.MinidiDriverWithCache;

/**
 * @author Andrey Korneychuk on 29-Mar-22
 * @version 1.0
 */
@Component
public class MinidiDriverComponent {

	@Value("${minidi.host}")
	private String minidiHost;
	@Value("${minidi.expired_time}")
	private String expiredTime;

	@Bean
	public MinidiDriverWithCache getMinidiDriverWithCache() {
		return new MinidiDriverWithCache(new MinidiDriver(minidiHost), Integer.parseInt(expiredTime));
	}
}