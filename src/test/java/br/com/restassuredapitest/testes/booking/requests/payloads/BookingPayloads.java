package br.com.restassuredapitest.testes.booking.requests.payloads;

import org.json.JSONException;
import org.json.JSONObject;

public class BookingPayloads {
        public JSONObject payloadValidoBooking(String firstname,String lastname,Number totalprice,Boolean depositpaid,
                                                      String checkin,String checkout) throws JSONException {
            JSONObject payload = new JSONObject();
            JSONObject bookingDates = new JSONObject();


            bookingDates.put("checkin",checkin);
            bookingDates.put("checkout",checkout);

            payload.put("firstname",firstname);
            payload.put("lastname",lastname);
            payload.put("totalprice",totalprice);
            payload.put("depositpaid",depositpaid);
            payload.put("bookingdates",bookingDates);
            payload.put("additionalneeds","breakfeast");

        return payload;
        }



}
