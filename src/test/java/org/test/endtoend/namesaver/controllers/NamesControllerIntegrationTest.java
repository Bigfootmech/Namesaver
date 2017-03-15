package org.test.endtoend.namesaver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.test.endtoend.namesaver.App;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.test.endtoend.namesaver.controllers.NamesController.NAME_APIS;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"/services-test-config.xml"})
@ContextConfiguration(classes = {App.class})
@WebAppConfiguration
public class NamesControllerIntegrationTest {

    private static final String FORENAME = "forename";
    private static final String SURNAME = "surname";
    private static final String EMPTY = "";
    private static final String BLANK = " ";

	@Autowired WebApplicationContext wac; 

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    
	@Test
	public void testNamesGetApiWorks() throws Exception{
		// given

		// when
        mockMvc.perform(get(NAME_APIS))
        
		// then
        .andExpect(status().isOk())
        .andExpect(view().name("existing"));
        //.andExpect(model().attribute("allsaved", isNotNull()));// Doesn't work. I'd like something like this though.

	}
    
	@Test
	public void testNamesPostApiWorks() throws Exception{
		// given
	    RequestBuilder request = post(NAME_APIS)
	            .param(FORENAME, FORENAME)
	            .param(SURNAME, SURNAME);

		// when
        mockMvc.perform(request)
        
		// then
        .andExpect(status().isOk())
        .andExpect(view().name("saved"));

	}
    
	@Test
	public void testNamesPostApiFailsWhenForenameNull() throws Exception{
		// given
	    RequestBuilder request = post(NAME_APIS)
	            .param(SURNAME, SURNAME);

		// when
	    try{
	        mockMvc.perform(request);
	    } catch (Exception e){
	    	
			// then
	    	if(e.getCause().getMessage().contains("Validation failed")){
	    		return;
	    	}
	    }
	    
	    fail();
	}
    
	@Test
	public void testNamesPostApiFailsWhenForenameEmpty() throws Exception{
		// given
	    RequestBuilder request = post(NAME_APIS)
	            .param(FORENAME, EMPTY)
	            .param(SURNAME, SURNAME);

		// when
	    try{
	        mockMvc.perform(request);
	    } catch (Exception e){
	    	
			// then
	    	if(e.getCause().getMessage().contains("Validation failed")){
	    		return;
	    	}
	    }
	    
	    fail();
	}
    
	@Test
	public void testNamesPostApiSucceedsWhenForenameBlank() throws Exception{
		// given
	    RequestBuilder request = post(NAME_APIS)
	            .param(FORENAME, BLANK)
	            .param(SURNAME, SURNAME);

		// when
        mockMvc.perform(request)
        
		// then
        .andExpect(status().isOk())
        .andExpect(view().name("saved"));
	}
    
	@Test
	public void testNamesPostApiFailsWhenSurnameNull() throws Exception{
		// given
	    RequestBuilder request = post(NAME_APIS)
	            .param(FORENAME, FORENAME);

		// when
	    try{
	        mockMvc.perform(request);
	    } catch (Exception e){
	    	
			// then
	    	if(e.getCause().getMessage().contains("Validation failed")){
	    		return;
	    	}
	    }
	    
	    fail();
	}
    
	@Test
	public void testNamesPostApiFailsWhenSurnameEmpty() throws Exception{
		// given
	    RequestBuilder request = post(NAME_APIS)
	            .param(FORENAME, FORENAME)
	            .param(SURNAME, EMPTY);

		// when
	    try{
	        mockMvc.perform(request);
	    } catch (Exception e){
	    	
			// then
	    	if(e.getCause().getMessage().contains("Validation failed")){
	    		return;
	    	}
	    }
	    
	    fail();
	}
    
	@Test
	public void testNamesPostApiSucceedsWhenSurnameBlank() throws Exception{
		// given
	    RequestBuilder request = post(NAME_APIS)
	            .param(FORENAME, FORENAME)
	            .param(SURNAME, BLANK);

		// when
        mockMvc.perform(request)
        
		// then
        .andExpect(status().isOk())
        .andExpect(view().name("saved"));
	}
    
	@Test
	public void testNamesPostApiSucceedsWhenMultipleNames() throws Exception{
		// given
	    RequestBuilder request = post(NAME_APIS)
	            .param(FORENAME, "I have")
	            .param(SURNAME, "Many names");

		// when
        mockMvc.perform(request)
        
		// then
        .andExpect(status().isOk())
        .andExpect(view().name("saved"));
	}
    
	@Test
	public void testNamesPostApiSucceedsWhenHtmlUsed() throws Exception{
		// given
	    RequestBuilder request = post(NAME_APIS)
	            .param(FORENAME, "<b>Hullo</b>")
	            .param(SURNAME, SURNAME);

		// when
        mockMvc.perform(request)
        
		// then
        .andExpect(status().isOk())
        .andExpect(view().name("saved"));
	}
    
	@Test
	public void testNamesPostApiSucceedsWhenSQLUsed() throws Exception{
		// given
	    RequestBuilder request = post(NAME_APIS)
	            .param(FORENAME, FORENAME)
	            .param(SURNAME, "Really;DROP SCHEMA IF EXISTS `namesaver`;");

		// when
        mockMvc.perform(request)
        
		// then
        .andExpect(status().isOk())
        .andExpect(view().name("saved"));
	}
    
	@Test
	public void testNamesPostApiSucceedsWhenNameTooLong() throws Exception{
		// given
	    RequestBuilder request = post(NAME_APIS)
	            .param(FORENAME, FORENAME)
	            .param(SURNAME, "This should really be caught, and returned more nicely. But setting up took a really, really long time.");

		// when
	    try{
	        mockMvc.perform(request);
	    } catch (Exception e){
	    	
			// then
	    	if(e.getCause().getCause() instanceof MysqlDataTruncation){
	    		return;
	    	}
	    }
	    
	    fail();
	}
	
}
