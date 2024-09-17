/**
 * 网关过滤器
 * <p>
 * 过滤器执行顺序
 *
 * @see com.ziyao.ideal.gateway.filter.FilterOrder#getOrder()
 * 过滤器
 * @see com.ziyao.ideal.gateway.filter.BootstrappingFilter
 * @see com.ziyao.ideal.gateway.filter.AuthorizationFilter
 * @see com.ziyao.ideal.gateway.filter.RateLimitingFilter
 * @see com.ziyao.ideal.gateway.filter.body.RequestBodyDecodeFilter
 * @see com.ziyao.ideal.gateway.filter.header.RevokeAuthHttpHeadersFilter
 * @see com.ziyao.ideal.gateway.filter.body.ResponseBodyEncodeFilter
 */
package com.ziyao.ideal.gateway.filter;
