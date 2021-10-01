package com.liu.ob.ob_backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;

@SpringBootTest
class ObBackendApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testCalendar() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        System.out.printf(c.getTime().toString());
    }
}
