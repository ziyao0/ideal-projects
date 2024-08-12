/**
 * 网关过滤器
 * <p>
 * 过滤器执行顺序
 *
 * @see com.ziyao.ideal.gateway.filter.GatewayPreFilter
 * @see com.ziyao.ideal.gateway.filter.AuthorizationPreFilter
 * @see com.ziyao.ideal.gateway.filter.AuthorizationFilter
 * @see com.ziyao.ideal.gateway.filter.RedirectFilter
 * @see com.ziyao.ideal.gateway.filter.DebounceFilter
 * @see com.ziyao.ideal.gateway.filter.body.RequestBodyDecodeFilter
 * @see com.ziyao.ideal.gateway.filter.header.RevokeAuthHttpHeadersFilter
 * @see com.ziyao.ideal.gateway.filter.body.ResponseBodyEncodeFilter
 * @see com.ziyao.ideal.gateway.filter.AccessPostFilter
 * @see com.ziyao.ideal.gateway.filter.StopWatchFilter
 */
package com.ziyao.ideal.gateway.filter;
