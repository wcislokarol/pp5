package pl.wcislokarol.payment.payu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Buyer {
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String language;