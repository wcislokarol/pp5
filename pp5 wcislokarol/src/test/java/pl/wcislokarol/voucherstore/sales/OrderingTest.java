package pl.wcislokarol.voucherstore.sales;

import org.junit.Before;
import org.junit.Test;
import pl.wcislokarol.payment.payu.exceptions.PayUException;
import pl.wcislokarol.voucherstore.sales.offer.Offer;
import pl.wcislokarol.voucherstore.sales.ordering.ReservationRepository;
import pl.wcislokarol.voucherstore.sales.payment.PaymentDetails;
import static org.assertj.core.api.Assertions.*;

public class OrderingTest extends SalesTestCase {

    @Before
    public void setUp() {
        productCatalog = thereIsProductCatalog();
        basketStorage = thereIsBasketStore();
        alwaysExistsInventory = thereIsInventory();
        currentCustomerContext = thereIsCurrentCustomerContext();
        offerMaker = thereIsOfferMaker(productCatalog);
        paymentGateway = thereIsInMemoryPaymentGateway();
        reservationRepository = thereIsInMemoryReservationRepository();
    }

    @Test
    public void itCreateOrderBasedOnCurrentOffer() throws PayUException {
        //Arrange
        SalesFacade sales = thereIsSalesModule();
        String productId1 = thereIsProductAvailable();
        String productId2 = thereIsProductAvailable();
        customerId = thereIsCustomerWhoIsDoingSomeShopping();

        //Act
        sales.addToBasket(productId1);
        sales.addToBasket(productId2);
        Offer seenOffer = sales.getCurrentOffer();

        PaymentDetails paymentDetails = sales.acceptOffer(new ClientDetails(), seenOffer);

        thereIsPendingReservationWithId(paymentDetails.getReservationId());
        thereIsPaymentRegisteredForReservation(paymentDetails.getReservationId());
    }

    private void thereIsPaymentRegisteredForReservation(String reservationId) {
        var reservation = reservationRepository.loadById(reservationId).get();
        assertThat(reservation.getPaymentId()).isNotNull();
    }

    private void thereIsPendingReservationWithId(String reservationId) {
        var reservation = reservationRepository.loadById(reservationId).get();
        assertThat(reservation.isPending()).isTrue();
        assertThat(reservation.isCompleated()).isFalse();
    }
}
