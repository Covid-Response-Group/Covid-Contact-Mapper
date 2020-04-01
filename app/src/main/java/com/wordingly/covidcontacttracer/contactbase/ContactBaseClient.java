package com.wordingly.covidcontacttracer.contactbase;

import org.covid19.contactbase.client.ContactControllerApi;
import org.covid19.contactbase.client.invoker.ApiClient;

public class ContactBaseClient {

    public ContactBaseClient() {
        ApiClient apiClient = new ApiClient();
        ContactControllerApi contactControllerApi = new ContactControllerApi();
    }
}
