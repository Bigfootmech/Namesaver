package org.test.endtoend.namesaver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.test.endtoend.namesaver.App;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.test.endtoend.namesaver.controllers.DefaultController.DEFAULT_API;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {App.class})
@WebAppConfiguration
public class DefaultControllerIntegrationTest {
	
    @Autowired WebApplicationContext wac; 

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    
	@Test
	public void testDefaultApiWorks() throws Exception{
		// given

		// when
        mockMvc
        
		// then
        .perform(get(DEFAULT_API))
        .andExpect(status().isOk())
        .andExpect(view().name("form"));
        //.andExpect(model().attribute("names", isNotNull())); // Doesn't work. I'd like something like this though.

	}
	
}
