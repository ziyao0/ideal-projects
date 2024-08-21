package com.ziyao.ideal.gateway.filter.intercept;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class DefaultInterceptContext implements InterceptContext {

    private final String clientIp;
    private final String requestUri;
    private final String domains;

    public DefaultInterceptContext(String clientIp, String requestUri, String domains) {
        this.clientIp = clientIp;
        this.requestUri = requestUri;
        this.domains = domains;
    }

    public static DefaultInterceptContext of(String clientIp, String requestUri, String domains) {
        return new DefaultInterceptContext(clientIp, requestUri, domains);
    }

    public static Builder withRequestUri(String requestUri) {
        return new Builder().withRequestUri(requestUri);
    }

    @Override
    public String clientIp() {
        return this.clientIp;
    }

    @Override
    public String requestUri() {
        return this.requestUri;
    }

    @Override
    public String domains() {
        return this.domains;
    }


    public static class Builder {
        private String clientIp;
        private String requestUri;
        private String domains;

        public Builder withRequestUri(String requestUri) {
            this.requestUri = requestUri;
            return this;
        }

        public Builder domains(String domains) {
            this.domains = domains;
            return this;
        }

        public Builder clientIp(String clientIp) {
            this.clientIp = clientIp;
            return this;
        }

        public InterceptContext build() {
            return new DefaultInterceptContext(clientIp, requestUri, domains);
        }
    }
}
