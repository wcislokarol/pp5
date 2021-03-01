package pl.wcislokarol.payment.payu.http;

import pl.wcislokarol.payment.payu.exceptions.PayUException;

import java.net.http.HttpResponse;
import java.util.Map;

public interface PayuHttp {
    HttpResponse<String> post(String url, String body, Map<String, String> headers) throws PayUException;
}
