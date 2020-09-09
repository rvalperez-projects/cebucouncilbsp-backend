package com.cebucouncilbsp.backend.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.cebucouncilbsp.backend.ResponseBodyWrapper;
import com.cebucouncilbsp.backend.constant.ResponseStatusCode;
import com.cebucouncilbsp.backend.utils.DateUtils;

@ControllerAdvice
@Order(3)
public class ResponseBodyBuilderAdvice implements ResponseBodyAdvice<Object> {
	private static final Logger LOGGER = LoggerFactory.getLogger(ResponseBodyBuilderAdvice.class);
	private static final String SERVER_DATE_HEADER = "ServerDate";

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return converterType == MappingJackson2HttpMessageConverter.class;
	}

	@Override
	public ResponseBodyWrapper beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		LOGGER.info("Wrapping object returned from controller: " + returnType.getMethod());
		ResponseBodyWrapper wrappedResponse;
		if (body instanceof ResponseBodyWrapper) {
			wrappedResponse = (ResponseBodyWrapper) body;
		} else {
			wrappedResponse = createSuccessResponse(body);
			addServerDate(response);
		}

		return wrappedResponse;
	}

	private ResponseBodyWrapper createSuccessResponse(Object response) {
		return new ResponseBodyWrapper(ResponseStatusCode.SUCCESS.getCode(), response, null);
	}

	private void addServerDate(ServerHttpResponse response) {
		response.getHeaders().set(SERVER_DATE_HEADER, DateUtils.getCurrentFormattedDate());
	}
}
