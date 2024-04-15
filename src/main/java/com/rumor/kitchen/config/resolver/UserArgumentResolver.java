package com.rumor.kitchen.config.resolver;

import com.rumor.kitchen.users.domain.Jwt;
import com.rumor.kitchen.users.domain.LoginUser;
import com.rumor.kitchen.users.domain.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    private final Jwt jwt;
    private final UserRepository userRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(LoginUser.class) && parameter.hasParameterAnnotation(Login.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();


        String token = jwt.extract(httpServletRequest);
        boolean isValid = jwt.validateToken(token);

        if (isValid) {
            String subject = jwt.getAuthenticationSubject(token);
            return userRepository.findById(Long.parseLong(subject))
                    .map(user -> new LoginUser(user.getId(), user.getSubject(), user.getName()))
                    .orElseThrow(() -> new RuntimeException("not found user"));
        }

        throw new RuntimeException("fail user resolver");
    }

}
