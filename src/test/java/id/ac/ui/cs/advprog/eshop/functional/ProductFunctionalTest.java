package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class ProductFunctionalTest {
    /**
     * The port number assigned to the running application during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */
    @LocalServerPort
    private int serverPort;

    /**
     * The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest(){
        baseUrl = String.format("%s:%d/product", testBaseUrl, serverPort);
    }

    @Test
    void testCreateProductAndCheckInList(ChromeDriver driver) throws Exception {
        // Input data
        String createProductUrl = String.format("%s/create", baseUrl);
        driver.get(createProductUrl);

        String productName = "Six sevennn";
        String productQuantity = "67";
        WebElement productNameField = driver.findElement(By.id("nameInput"));
        productNameField.clear();
        productNameField.sendKeys(productName);

        WebElement productQuantityField = driver.findElement(By.id("quantityInput"));
        productQuantityField.clear();
        productQuantityField.sendKeys(productQuantity);

        WebElement submitButton = driver.findElement(By.tagName("button"));
        submitButton.click();

        // View list
        String productListUrl = String.format("%s/list", baseUrl);
        driver.get(productListUrl);

        List<WebElement> firstRow = driver.findElements(By.tagName("td"));
        String foundProductName = firstRow.get(0).getText();
        String foundProductQuantity = firstRow.get(1).getText();

        assertEquals(productName, foundProductName);
        assertEquals(productQuantity, foundProductQuantity);
    }
}
