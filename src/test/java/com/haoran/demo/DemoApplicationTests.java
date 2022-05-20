package com.haoran.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DemoApplicationTests {

	Calculator testCalculator = new Calculator();
	@Test
	void itShouldAddNumbers() {
		assertEquals(3, testCalculator.add(1,2));
	}

	class Calculator {
		int add(int a, int b) {
			return a + b;
		}
	}
}
