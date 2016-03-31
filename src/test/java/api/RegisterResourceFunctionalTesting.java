package api;

import java.util.Calendar;

import org.junit.After;
import org.junit.Test;

import business.api.Uris;
import business.wrapper.AvailableTime;
import business.wrapper.AvailableTraining;

public class RegisterResourceFunctionalTesting {

	RestService restService = new RestService();
	
	@Test
	public void testShowAvailableTrainings()
	{
		restService.createCourt("1");
		restService.createTraining(1, 3, 1);
		String token = restService.registerAndLoginPlayer();
		new RestBuilder<String>(RestService.URL).path(Uris.REGISTRATIONS).basicAuth(token, "").post().build();
	}
	
	@Test
	public void testRegisterPlayerinTraining()
	{
		restService.createCourt("1");
		restService.createTraining(1, 3, 1);
        String token = restService.registerAndLoginPlayer();
        Calendar day = Calendar.getInstance();
        new RestBuilder<String>(RestService.URL).path(Uris.REGISTRATIONS).basicAuth(token, "").body(new AvailableTraining(1,day, 3, 1)).post().build();
	}
	
    @After
    public void deleteAll() {
        new RestService().deleteAll();
    }

}
