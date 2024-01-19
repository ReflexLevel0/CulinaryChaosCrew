package com.PROGI.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BackendApplicationTests {

	private final validEmail underTest = new validEmail();
	private final validPassword underTest1 = new validPassword();

	@Test
	public void correctMail() {
		assertThat(underTest.test("hello@gmail.com")).isTrue();
	}

	@Test
	public void mailWithNoAt() {
		assertThat(underTest.test("hellogmail.com")).isFalse();
	}

	@Test
	public void mailWithNoDot() {
		assertThat(underTest.test("hello@gmail")).isFalse();
	}

	@Test
	public void correctPass() {
		assertThat(underTest1.test("baba1223!")).isTrue();
	}

	@Test
	public void passUnder8Char() {
		assertThat(underTest1.test("adsa12")).isFalse();
	}

	@Test
	public void passWithInvalidChars() {
		assertThat(underTest1.test("pass@{}123;")).isFalse();
	}
}
