package api;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Test;

import business.api.Uris;
import business.wrapper.AvailableTime;

public class ReserveResourceFunctionalTesting {

    RestService restService = new RestService();

    @Test
    public void testshowAvailability() {
        restService.createCourt("1");
        restService.createCourt("2");
        String token = restService.registerAndLoginPlayer();
        Calendar day = new GregorianCalendar(2016, 00, 29, 12, 0);
        new RestBuilder<String>(RestService.URL).path(Uris.RESERVES).basicAuth(token, "").body(new AvailableTime(1, day)).post().build();
        day = new GregorianCalendar(2016, 00, 29, 14, 0);
        new RestBuilder<String>(RestService.URL).path(Uris.RESERVES).basicAuth(token, "").body(new AvailableTime(2, day)).post().build();
        String day2 = "" + new GregorianCalendar(2016, 00, 29).getTimeInMillis();
        String response = new RestBuilder<String>(RestService.URL).path(Uris.RESERVES).path(Uris.AVAILABILITY).basicAuth(token, "")
                .param("day", day2).clazz(String.class).get().build();
        LogManager.getLogger(this.getClass()).info("testshowAvailability (" + response + ")");
    }

    @Test
    public void testReserveCourt() {
        // TODO Programando...
        restService.createCourt("1");
        restService.createCourt("2");
        String token = restService.registerAndLoginPlayer();
        Calendar day = new GregorianCalendar(2016, 00, 29, 12, 0);
        new RestBuilder<String>(RestService.URL).path(Uris.RESERVES).basicAuth(token, "").body(new AvailableTime(1, day)).post().build();
        day = new GregorianCalendar(2016, 00, 29, 14, 0);
        new RestBuilder<String>(RestService.URL).path(Uris.RESERVES).basicAuth(token, "").body(new AvailableTime(2, day)).post().build();
    }

    @After
    public void deleteAll() {
        new RestService().deleteAll();
    }

}
