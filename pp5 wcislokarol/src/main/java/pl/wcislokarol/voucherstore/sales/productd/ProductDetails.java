package pl.wcislokarol.voucherstore.sales.productd;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class ProductDetails {
    private String productId;
    private String description;
    private BigDecimal unitPrice;
}
