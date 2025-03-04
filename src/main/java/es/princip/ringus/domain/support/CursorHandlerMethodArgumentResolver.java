package es.princip.ringus.domain.support;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CursorHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String DEFAULT_CURSOR_PARAMETER = "cursor";
    private static final Class<Cursor> DEFAULT_CURSOR_TYPE = Cursor.class;
    private final CursorParser cursorParser;

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return CursorPageable.class.equals(parameter.getParameterType()) &&
                parameter.hasParameterAnnotation(CursorDefault.class);
    }

    @Override
    public Object resolveArgument(
            @NonNull final MethodParameter parameter,
            final ModelAndViewContainer mavContainer,
            @NonNull final NativeWebRequest webRequest,
            final WebDataBinderFactory binderFactory
    ) {
        return resolveCursor(parameter, mavContainer, webRequest, binderFactory);
    }

    public Cursor resolveCursor(
            @NonNull MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            @NonNull NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        final Class<? extends Cursor> cursorType = getCursorType(parameter);
        final String cursorString = getCursorString(webRequest, parameter);
        return cursorParser.parse(cursorType, cursorString);
    };

    protected Class<? extends Cursor> getCursorType(final MethodParameter parameter) {
        final CursorDefault annotation = parameter.getParameterAnnotation(CursorDefault.class);
        if (annotation == null) {
            return DEFAULT_CURSOR_TYPE;
        }
        return annotation.type();
    }

    protected String getCursorString(final NativeWebRequest webRequest, final MethodParameter parameter) {
        String cursorString = webRequest.getParameter(DEFAULT_CURSOR_PARAMETER);
        if (cursorString != null) {
            return cursorString;
        }
        final CursorDefault annotation = parameter.getParameterAnnotation(CursorDefault.class);
        cursorString = webRequest.getParameter(Optional.ofNullable(annotation)
                .map(CursorDefault::value)
                .orElseThrow(() -> new IllegalArgumentException("@CursorDefault is required")));
        return cursorString;
    }
}
