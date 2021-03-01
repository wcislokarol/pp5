package pl.wcislokarol.voucherstore.productcatalog;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductCatalogHttpTest {

    public static final String PRODUCT_1 = "Product 1";
    public static final String PRODUCT_2 = "Product 2";
    public static final String DRAFT_PRODUCT = "draft product";

    @LocalServerPort
    int localServerPort;

    @Autowired
    ProductCatalogFacade productCatalogFacade;

    @Autowired
    TestRestTemplate restTemplate;

    @Before
    public void setUp() {
        productCatalogFacade.emptyCatalog();
    }

    @Test
    public void itShowsAllPublishedProducts() {
        //Arrange
        thereIsDraftProduct(DRAFT_PRODUCT);
        thereIsReadyToBeSellProduct(PRODUCT_1);
        thereIsReadyToBeSellProduct(PRODUCT_2);

        //Act
        var url = String.format("http://localhost:%s/api/products", localServerPort);
        ResponseEntity<Product[]> response = restTemplate.getForEntity(url, Product[].class);

        //Assert
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        assertThat(response.getBody())
                .hasSize(2)
                .extracting(Product::getDescription)
                .contains(PRODUCT_1, PRODUCT_2)
                .doesNotContain(DRAFT_PRODUCT);
    }

    private void thereIsReadyToBeSellProduct(String productDesc) {
        var id = productCatalogFacade.createProduct();
        productCatalogFacade.updateDetails(id, productDesc, "some picture");
        productCatalogFacade.applyPrice(id, BigDecimal.valueOf(100.22));
    }

    private void thereIsDraftProduct(String productDesc) {
        var id = productCatalogFacade.createProduct();
        productCatalogFacade.updateDetails(id, productDesc, null);
    }
}
