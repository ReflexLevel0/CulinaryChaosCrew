package com.PROGI.backend;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class validEmailTest {
    private final validEmail underTest = new validEmail();

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
}
