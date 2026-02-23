package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EshopApplicationTests {

    @Test
    void testApplication() {
        assertDoesNotThrow(() -> {
            EshopApplication.main(new String[] {});
        });
    }
}
