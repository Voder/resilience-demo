package de.voder.resiliencedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCircuitBreaker
@EnableRetry
public class ResilienceDemoApplication {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public RetryTemplate retryTemplate() {
		RetryTemplate retryTemplate = new RetryTemplate();

		FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
		fixedBackOffPolicy.setBackOffPeriod(500l);
		retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

		SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
		retryPolicy.setMaxAttempts(2);
		retryTemplate.setRetryPolicy(retryPolicy);
		retryTemplate.setThrowLastExceptionOnExhausted(true);

		return retryTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(ResilienceDemoApplication.class, args);
	}

}

