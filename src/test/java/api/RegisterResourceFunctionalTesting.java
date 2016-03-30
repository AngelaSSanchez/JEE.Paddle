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
		
	}
	
	@Test
	public void testRegisterPlayerinTraining()
	{
		restService.createCourt("1");
		restService.createTraining(3, 1);
        String token = restService.registerAndLoginPlayer();
        Calendar day = Calendar.getInstance();
        day.add(Calendar.DAY_OF_YEAR, 1);
        day.set(Calendar.HOUR_OF_DAY,12);
        //new RestBuilder<String>(RestService.URL).path(Uris.REGISTRATION).basicAuth(token, "").body(new AvailableTraining(day, 3, 1)).post().build();
	}
	
    @After
    public void deleteAll() {
        new RestService().deleteAll();
    }
}
