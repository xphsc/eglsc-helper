package com.github.xphsc.http;

import com.github.xphsc.lang.Validator;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by ${huipei.x} on 2017-6-18
 */
public class HttpBuilder {

    private final RequestConfig.Builder configBuilder;
    private final HttpClientBuilder clientBuilder;
    // 默认超时为 10 分钟
    private static final int TIMEOUT = 10 * 60 * 1000;

    private HttpBuilder() {
        configBuilder = RequestConfig.custom();
        configBuilder.setSocketTimeout(TIMEOUT)
                .setConnectTimeout(TIMEOUT)
                .setConnectionRequestTimeout(TIMEOUT);
        clientBuilder = HttpClientBuilder.create();
    }

    public static HttpBuilder custom() {
        return new HttpBuilder();
    }

    public HttpBuilder timeout(final int timeout, final TimeUnit unit) {
        this.configBuilder.setSocketTimeout((int) unit.toMillis(timeout))
                .setConnectTimeout((int) unit.toMillis(timeout))
                .setConnectionRequestTimeout((int) unit.toMillis(timeout));
        return this;
    }

    public HttpBuilder socketTimeout(final int timeout, final TimeUnit unit) {
        this.configBuilder.setSocketTimeout((int) unit.toMillis(timeout));
        return this;
    }

    public HttpBuilder connectionTimeout(final int timeout, final TimeUnit unit) {
        this.configBuilder.setConnectTimeout((int) unit.toMillis(timeout));
        return this;
    }

    public HttpBuilder requestTimeout(final int timeout, final TimeUnit unit) {
        this.configBuilder.setConnectionRequestTimeout((int) unit.toMillis(timeout));
        return this;
    }

    public HttpBuilder proxy( HttpProxy httpProxy) {
        HttpProxy proxy = Validator.notNull(httpProxy);
        DefaultProxyRoutePlanner router = proxy.getProxy();
        router = Validator.notNull(router);
        clientBuilder.setRoutePlanner(router);
        CredentialsProvider credential = proxy.getCredential();
        if (credential != null) {
            clientBuilder.setDefaultCredentialsProvider(credential);
        }
        return this;
    }

    public CloseableHttpClient build() {
        return clientBuilder.setDefaultRequestConfig(configBuilder.build())
                .build();
    }
}
