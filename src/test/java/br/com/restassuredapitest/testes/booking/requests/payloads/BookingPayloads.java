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

    public JSONObject payloadInvalido () throws JSONException {
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();


        bookingDates.put("checkin","finde");
        bookingDates.put("checkout","dia de semana");

        payload.put("primeironome",111);
        payload.put("lastname",222);
        payload.put("precinho","caro");
        payload.put("depositpaid","calote");
        payload.put("entreidia:",bookingDates);
        payload.put("additionalneeds","breakfeast");

        return payload;
    }
    public JSONObject payloadInfoExtra (String firstname,String lastname,Number totalprice,Boolean depositpaid,
                                        String checkin,String checkout, int peso, double altura,
                                        String signo, String pet) throws JSONException {
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
        payload.put("peso",peso);
        payload.put("altura",altura);
        payload.put("signo",signo);
        payload.put("pet",pet);


        return payload;
    }

    public JSONObject payloadParcial (String firstname,String lastname) throws JSONException {
        JSONObject payload = new JSONObject();


        payload.put("firstname",firstname);
        payload.put("lastname",lastname);



        return payload;
    }



}
