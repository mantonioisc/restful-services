package examples.jaxrs.resource;


import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class CompanyResourceIT {
	private String url = "http://localhost:9090/rest/company";
	private static final Logger logger = Logger.getLogger(CompanyResourceIT.class);

	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testGetCompanies() throws IOException{
		HttpClient httpClient = new HttpClient();
		GetMethod get = new GetMethod(url);
		get.setRequestHeader("Accepts", "application/json");
		int status = httpClient.executeMethod(get);
		assertEquals(200, status);
		assertEquals("application/json", get.getResponseHeader("Content-Type").getValue());
		logger.debug(get.getResponseBodyAsString());
	}
	
	@Test
	public void testGetCompany() throws IOException {
		HttpClient httpClient = new HttpClient();
		GetMethod get = new GetMethod(url+ "/1");
		get.setRequestHeader("Accepts", "application/json");
		int status = httpClient.executeMethod(get);
		assertEquals(200, status);
		assertEquals("application/json", get.getResponseHeader("Content-Type").getValue());
		logger.debug(get.getResponseBodyAsString());
	}

}
