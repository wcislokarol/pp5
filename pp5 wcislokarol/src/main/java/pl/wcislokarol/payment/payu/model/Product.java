package pl.wcislokarol.payment.payu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String name;
    private Integer unitPrice;
    private Integer quantity;
    /*
    {
                "name": "Magic mouse",
                "unitPrice": "300",
                "quantity": "3"
            },
     */
}
