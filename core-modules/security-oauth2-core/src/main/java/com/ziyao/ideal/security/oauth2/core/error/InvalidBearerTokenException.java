package com.ziyao.ideal.security.oauth2.core.error;



/**
 * token 无效抛出该异常
 *
 * @author ziyao zhang
 */
public class InvalidBearerTokenException extends OAuth2AuthenticationException {

    
    private static final long serialVersionUID = 4421091380179524396L;

    /**
     * Construct an instance of {@link InvalidBearerTokenException} given the provided
     * description.
     * <p>
     * The description will be wrapped into an
     * {@link OAuth2Error} instance as the
     * {@code error_description}.
     *
     * @param description the description
     */
    public InvalidBearerTokenException(String description) {
        super(BearerTokenErrors.invalidToken(description));
    }

    /**
     * Construct an instance of {@link InvalidBearerTokenException} given the provided
     * description and cause
     * <p>
     * The description will be wrapped into an
     * {@link OAuth2Error} instance as the
     * {@code error_description}.
     *
     * @param description the description
     * @param cause       the causing exception
     */
    public InvalidBearerTokenException(String description, Throwable cause) {
        super(BearerTokenErrors.invalidToken(description), cause);
    }

}
