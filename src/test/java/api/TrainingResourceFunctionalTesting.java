package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import business.api.Uris;
import business.wrapper.AvailableTraining;

public class TrainingResourceFunctionalTesting {

	RestService restService = new RestService();
	
	@Test
	public void testCreateTraining(){
		restService.createCourt("1");
		restService.createTraining(1, 2, 1);
	}
	
	@Test
	public void testCreateTrainingUnauthorized(){
		restService.createCourt("1");
		try {
			Calendar day = Calendar.getInstance();
            new RestBuilder<Object>(RestService.URL).path(Uris.TRAININGS).body(new AvailableTraining(1,day, 3, 1)).post().build();
            fail();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.UNAUTHORIZED, httpError.getStatusCode());
            LogManager.getLogger(this.getClass()).info(
                    "testCreateCourt (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
        }
	}

	@Test
	public void testDeleteTraining(){
		restService.createCourt("1");
		restService.createTraining(1, 2, 1);
		String token = restService.loginTrainer();
		new RestBuilder<Object>(RestService.URL).path(Uris.TRAININGS).param("id","1").basicAuth(token, "").delete().build();
	}
	
	@Test
	public void testDeleteTrainingUnauthorized(){
		try {
			new RestBuilder<Object>(RestService.URL).path(Uris.TRAININGS).param("id","1").delete().build();
            fail();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.UNAUTHORIZED, httpError.getStatusCode());
            LogManager.getLogger(this.getClass()).info(
                    "testCreateCourt (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
        }
	}
	
	@Test
	public void testDeletePlayersFromTraining(){
		restService.createCourt("1");
		restService.createTraining(1, 2, 1);
		String token = restService.loginTrainer();
		new RestBuilder<Object>(RestService.URL).path(Uris.TRAININGS).pathId(1).path(Uris.USERS).param("id", "3").basicAuth(token, "").delete().build();
	}
	
    @After
    public void deleteAll() {
        new RestService().deleteAll();
    }
}
