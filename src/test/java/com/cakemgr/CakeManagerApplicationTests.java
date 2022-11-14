package com.cakemgr;

import com.cakemgr.model.Cake;
import com.cakemgr.service.CakeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URL;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CakeManagerApplicationTests {

	@LocalServerPort
	private int port;

	private URL base;

	@Autowired
	private CakeService cakeService;

	@Autowired
	private TestRestTemplate template;


	@BeforeEach
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
	}

	@Test
	public void testAddingCake() throws Exception {
		Cake cake = new Cake();
		cake.setId(1);
		cake.setTitle("Cheese Cake");
		cake.setDesc("A cheesecake made of lemon");
		cake.setImage("https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg");
		Assertions.assertNotNull(cake);
		cakeService.saveOrUpdate(cake);
	}
	@Test
	public void testGettingCake(){
		Cake cake = new Cake();
		cake.setId(2);
		cake.setTitle("Cheese Cake");
		cake.setDesc("A cheesecake made of lemon");
		cake.setImage("https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg");
		Assertions.assertNotNull(cake);
		cakeService.saveOrUpdate(cake);
		ResponseEntity<Cake[]> result = template.getForEntity(base.toString() + "/cakes", Cake[].class);
		Cake[] body = result.getBody();
		assert body != null;

		int expected = cakeService.getAllCakes().size();

		Assertions.assertEquals(  expected,body.length);

	}
	@Test
	public void testAddingTwoCakes(){
		Cake cake1 = new Cake();
		cake1.setId(2);
		cake1.setTitle("Cheese Cake");
		cake1.setDesc("A cheesecake made of lemon");
		cake1.setImage("https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg");
		Assertions.assertNotNull(cake1);
		cakeService.saveOrUpdate(cake1);

		Cake cake2 = new Cake();
		cake2.setId(3);
		cake2.setTitle("Lemon Cake");
		cake2.setDesc("A Cake made of lemon");
		cake2.setImage("https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg");
		Assertions.assertNotNull(cake2);
		cakeService.saveOrUpdate(cake2);

		int expected = cakeService.getAllCakes().size();

		ResponseEntity<Cake[]> result = template.getForEntity(base.toString() + "/cakes", Cake[].class);
		Cake[] body = result.getBody();
		assert body != null;
		Assertions.assertEquals(  expected,body.length);

	}
}
