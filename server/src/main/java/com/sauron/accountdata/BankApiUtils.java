package com.sauron.accountdata;

import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.web.util.UriComponents;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

public class BankApiUtils {
    private static final String USER_ID_PARAM = "userId";

    public static <T> RequestEntity<T> createRequestEntity(String url, Long userId) {
        UriComponents uriComponents = fromHttpUrl(url).queryParam(USER_ID_PARAM, userId).build();
        return new RequestEntity<>(HttpMethod.GET, uriComponents.toUri());
    }
}
