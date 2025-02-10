package com.fwd.recruitment.tictactoe;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.fwd.recruitment.tictactoe.controller.GameController;

@SpringBootTest
class TictactoeApplicationTests {

    @Autowired
    private ApplicationContext context;
	
	@Test
	void contextLoads() {
		  // Check if a specific bean is loaded into the context (e.g., the GameController)
        assertNotNull(context.getBean(GameController.class), "GameController bean should be loaded");
  
	}

}
