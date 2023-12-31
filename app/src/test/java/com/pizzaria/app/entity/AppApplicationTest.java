package com.pizzaria.app.entity;

import com.pizzaria.app.AppApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
@SpringBootTest
class AppApplicationTest {
    @Test
    void mainMethodShouldNotThrowException() {
        assertDoesNotThrow(() -> AppApplication.main(new String[]{}));
    }
}
