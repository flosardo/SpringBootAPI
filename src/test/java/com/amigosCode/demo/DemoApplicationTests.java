package com.amigosCode.demo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled
class DemoApplicationTests {

	@Test
	void contextLoads() {
		// given
		int a = 1;
		int b = 3;
		// when
		int result = Calculator.add(a, b);
		// then
		int expected = 4;
		org.junit.jupiter.api.Assertions.assertEquals(expected, result);

	}

	/**
	 * InnerDemoApplicationTests
	 */
	public class Calculator {
		static int add(int a, int b) {
			return a + b;
		}

	}
}
