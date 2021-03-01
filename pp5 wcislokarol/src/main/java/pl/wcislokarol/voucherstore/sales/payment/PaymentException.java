package pl.wcislokarol.voucherstore.sales.payment;

import pl.wcislokarol.payment.payu.exceptions.PayUException;

public class PaymentException extends IllegalStateException {
    public PaymentException(PayUException e) {
        super(e);
    }
}
