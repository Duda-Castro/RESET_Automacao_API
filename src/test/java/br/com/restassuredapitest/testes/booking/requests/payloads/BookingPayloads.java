package br.com.restassuredapitest.testes.booking.requests.payloads;

import org.json.JSONException;
import org.json.JSONObject;

public class BookingPayloads {
        public static JSONObject payloadValidoBooking() throws JSONException {
            JSONObject payload = new JSONObject();
            JSONObject bookingDates = new JSONObject();


            bookingDates.put("checkin","2018-01-01");
            bookingDates.put("checkout","2019-01-01");

            payload.put("firstname","Ronaldo");
            payload.put("lastname","Silva");
            payload.put("totalprice",111);
            payload.put("depositpaid",true);
            payload.put("bookingdates",bookingDates);
            payload.put("additionalneeds","breakfeast");

        return payload;
        }

}
