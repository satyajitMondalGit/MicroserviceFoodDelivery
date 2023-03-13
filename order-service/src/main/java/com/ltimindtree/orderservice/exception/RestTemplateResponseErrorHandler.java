package com.ltimindtree.orderservice.exception;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.ResponseErrorHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		// TODO Auto-generated method stub
		return (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR
				|| response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR);
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
			// handle SERVER_ERROR
		} else if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
			log.info("response " + StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
			System.out.println(response.getBody().toString());

			throw new CustomRestExceptionHandler(response.getStatusText());

		}

	}

}
