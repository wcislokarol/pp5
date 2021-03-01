package pl.wcislokarol.voucherstore.sales.productd;

public interface ProductDetailsProvider {
    ProductDetails getByProductId(String productId);
}
