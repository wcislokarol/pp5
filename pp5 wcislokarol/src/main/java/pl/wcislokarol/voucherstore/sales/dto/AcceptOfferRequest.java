package pl.wcislokarol.voucherstore.sales.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wcislokarol.voucherstore.sales.ClientDetails;
import pl.wcislokarol.voucherstore.sales.offer.Offer;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcceptOfferRequest {
    @NotNull
    private Offer seenOffer;
    @NotNull
    private ClientDetails clientDetails;
}
