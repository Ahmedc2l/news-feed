package com.ahmed.newsfeed.network;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * <h1>ErrorUtils</h1>
 * <p>Handles all retrofit response errors</p>
 *
 * @author Ahmed Fathy
 * @since 2019-4-10
 * @version 1.0
 * */
public class ErrorUtils {

    /**
     * <h1>parseError</h1>
     * <p>Handles all retrofit response errors</p>
     *
     * @see Response
     * @param response retrofit response object of T type
     * */
    public static APIError parseError(final Response<?> response) {
        List<String> messages = new ArrayList<>();
        try {
            ResponseBody errorBody = response.errorBody();
            if (errorBody != null) {
                String string = errorBody.string();
                JSONObject bodyObj = new JSONObject(string);

                messages.add(bodyObj.getString("message"));

                // here you can handle different error response codes
                // response.code();

            } else
                messages.add("Something went wrong!");
        } catch (Exception e) {
            messages.add("Something went wrong!");
        }

        return new APIError(false, messages);
    }
}
