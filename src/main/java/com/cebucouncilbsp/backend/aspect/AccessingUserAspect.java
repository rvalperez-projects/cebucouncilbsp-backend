package com.cebucouncilbsp.backend.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cebucouncilbsp.backend.annotation.AccessingUser;
import com.cebucouncilbsp.backend.entity.AuthorityEntity;
import com.cebucouncilbsp.backend.exception.SystemFailureException;
import com.cebucouncilbsp.backend.exception.UserNotFoundException;
import com.cebucouncilbsp.backend.repository.AuthorityRepository;
import com.cebucouncilbsp.backend.security.JwtTokenUtils;
import com.cebucouncilbsp.backend.utils.HttpRequestUtils;

@Aspect
@Component
public class AccessingUserAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccessingUserAspect.class);

	@Autowired
	private JwtTokenUtils jwtTokenUtil;

	@Autowired
	private AuthorityRepository authRepository;

	/**
	 * injectAccessingUser
	 *
	 * @param point Object
	 * @return Object
	 * @throws Throwable Exception
	 */
	@Around("(within(@org.springframework.web.bind.annotation.RestController *)) && "
			+ "execution(* *(.., @com.cebucouncilbsp.backend.annotation.AccessingUser (*), ..))")
	public Object injectAccessingUser(final ProceedingJoinPoint point) throws Throwable {
		Signature signature = point.getSignature();
		if (!(signature instanceof MethodSignature)) {
			LOGGER.error("{} must be used in a method: {}", AccessingUser.class.getName(), signature.getName());
			throw new SystemFailureException();
		}
		Method method = ((MethodSignature) signature).getMethod();
		Annotation[][] paramAnnotations = method.getParameterAnnotations();
		Class<?>[] parameterTypes = method.getParameterTypes();
		Object[] args = point.getArgs();

		for (int i = 0; i < paramAnnotations.length; i++) {
			for (Annotation annotation : paramAnnotations[i]) {
				if (annotation.annotationType() == AccessingUser.class) {
					if (parameterTypes[i] != AuthorityEntity.class) {
						LOGGER.error("{} cannot be used in a parameter of {} type.", AccessingUser.class.getName(),
								parameterTypes[i].getName());
						throw new SystemFailureException();
					}
					HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
							.currentRequestAttributes()).getRequest();
					String username = jwtTokenUtil.getUsernameFromToken(HttpRequestUtils.getAuthToken(request));

					AuthorityEntity accessingUser = authRepository.findAuthUserByUsername(username);
					if (accessingUser == null) {
						LOGGER.warn("Accessing user couldn't be found.");
						throw new UserNotFoundException();
					}
					args[i] = accessingUser;

					break;
				}
			}
		}
		return point.proceed(args);
	}

	/**
	 * logBeforeController
	 *
	 * @param point Object
	 * @return Object
	 * @throws Throwable Exception
	 */
	@Before("execution(* com.cebucouncilbsp.backend.controller.*.*(..)))")
	public void logBeforeController(final JoinPoint point) {
		Signature signature = point.getSignature();
		List<Object> args = new ArrayList<>();
		for (Object arg : point.getArgs()) {
			args.add(arg.toString());
		}
		LOGGER.info("Starting {} with arguments {}.", signature.getName(), args);
	}

	/**
	 * logAfterController
	 *
	 * @param point Object
	 * @return Object
	 * @throws Throwable Exception
	 */
	@AfterReturning("execution(* com.cebucouncilbsp.backend.controller.*.*(..)))")
	public void logAfterController(final JoinPoint point) {
		Signature signature = point.getSignature();
		LOGGER.info("Method {} ends successfully.", signature.getName());
	}

}
