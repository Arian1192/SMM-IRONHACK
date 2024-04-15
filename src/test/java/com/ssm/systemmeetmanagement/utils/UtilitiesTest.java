package com.ssm.systemmeetmanagement.utils;

import jdk.jshell.execution.Util;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class UtilitiesTest {
    @Test
    void test_generateRandomPassword() {
        assertEquals(16, Utilities.generateRandomPassword().getBytes(StandardCharsets.UTF_8).length);
    }
}