//package com.xmbl.ops.interceptor;
//
//import java.util.Objects;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.core.MethodParameter;
//import org.springframework.web.bind.support.WebDataBinderFactory;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.method.support.ModelAndViewContainer;
//
//import com.xmbl.ops.util.RequestUtil;
//
////@Component
//public class JsonParamParseInterceptor implements HandlerMethodArgumentResolver{
//
//	@Override
//	public boolean supportsParameter(MethodParameter parameter) {
//		if (Objects.nonNull(parameter.getParameterAnnotation(JsonParam.class))) {
//			return true;
//		}
//		return false;
//	}
//
//	@Override
//	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
//			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//		
//		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
//        JsonParam jsonParam = parameter.getParameterAnnotation(JsonParam.class);
//        if (Objects.nonNull(jsonParam)) {
//			return null;
//		}
//        Class<?> clazz = jsonParam.value();
//		return RequestUtil.parseParam(request, clazz);
//	}
//}
