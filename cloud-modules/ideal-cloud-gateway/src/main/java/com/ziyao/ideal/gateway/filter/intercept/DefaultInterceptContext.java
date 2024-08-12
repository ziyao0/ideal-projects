package com.ziyao.ideal.gateway.filter.intercept;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public record DefaultInterceptContext(String clientIp, String requestUri, String domains) implements InterceptContext {

    public static DefaultInterceptContext of(String clientIp, String requestUri, String domains) {
        return new DefaultInterceptContext(clientIp, requestUri, domains);
    }

    public static Builder withRequestUri(String requestUri) {
        return new Builder().withRequestUri(requestUri);
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
