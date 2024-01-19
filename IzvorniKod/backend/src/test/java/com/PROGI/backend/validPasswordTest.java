package com.PROGI.backend;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class validPasswordTest {
        private final validPassword underTest = new validPassword();

        @Test
        public void correctPass() {
            assertThat(underTest.test("baba1223!")).isTrue();
        }

        @Test
        public void passUnder8Char() {
            assertThat(underTest.test("adsa12")).isFalse();
        }

        @Test
        public void passWithInvalidChars() {
            assertThat(underTest.test("pass@{}123;")).isFalse();
        }
}
