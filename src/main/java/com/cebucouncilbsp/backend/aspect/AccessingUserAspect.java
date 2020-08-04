package com.cebucouncilbsp.backend.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cebucouncilbsp.backend.annotation.AccessingUser;
import com.cebucouncilbsp.backend.entity.AuthorityEntity;
import com.cebucouncilbsp.backend.entity.UserEntity;
import com.cebucouncilbsp.backend.exception.SystemFailureException;
import com.cebucouncilbsp.backend.exception.UserNotFoundException;
import com.cebucouncilbsp.backend.repository.AuthorityRepository;
import com.cebucouncilbsp.backend.security.JwtTokenUtils;
import com.cebucouncilbsp.backend.utils.HttpRequestUtils;

//@Aspect
//@Component
public class AccessingUserAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccessingUserAspect.class);

	@Autowired
	private JwtTokenUtils jwtTokenUtil;

	@Autowired
	private AuthorityRepository authRepository;

	/**
	 * injectAccessingUser。
	 *
	 * @param point オブジェクト
	 * @return Object
	 * @throws Throwable 例外
	 */
	@Around("(within(@org.springframework.web.bind.annotation.RestController *) || "
			+ "within(@org.springframework.stereotype.Controller *)) && "
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
					if (parameterTypes[i] != UserEntity.class) {
						LOGGER.error("{} cannot be used in a parameter of {} type.", AccessingUser.class.getName(),
								parameterTypes[i].getName());
						throw new SystemFailureException();
					}
					HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
							.currentRequestAttributes()).getRequest();
					String username = jwtTokenUtil.getUsernameFromToken(HttpRequestUtils.getAuthToken(request));

					AuthorityEntity accessingUser = authRepository.findAuthUserByUsername(username);
					if (accessingUser == null) {
						// 認証トークンで認証成功直後に認証トークンが削除された場合（ログアウトなど）、ユーザーが特定できなくなる
						// 呼び出し元の実装次第で起こりうるため、エラーログは出力しない
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

}
