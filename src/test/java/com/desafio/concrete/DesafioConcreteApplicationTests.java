package com.desafio.concrete;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.desafio.concrete.controlers.LogonController;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest(value = LogonController.class)
public class DesafioConcreteApplicationTests {

	@Test
	public void contextLoads() {
	}

}
