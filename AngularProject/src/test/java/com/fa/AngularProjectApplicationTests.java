package com.fa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AngularProjectApplicationTests {

	@Test
	void contextLoads() {
		System.err.println("Test cases runned");
		assertEquals("", "java");
	}
	
	@Test
	void contextLoads1() {
		assertEquals("", "");
	}

}
