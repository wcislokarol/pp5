package pl.wcislokarol.voucherstore.sales.payment;

import pl.wcislokarol.payment.payu.exceptions.PayUException;
import pl.wcislokarol.voucherstore.sales.ordering.Reservation;

public interface PaymentGateway {
    PaymentDetails registerFor(Reservation reservation) throws PayUException;

    boolean isTrusted(PaymentUpdateStatusRequest updateStatusRequest);
}
