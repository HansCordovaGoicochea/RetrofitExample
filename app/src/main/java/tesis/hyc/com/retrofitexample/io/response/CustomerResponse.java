package tesis.hyc.com.retrofitexample.io.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import tesis.hyc.com.retrofitexample.model.Customer;

public class CustomerResponse {

    //Example 1
    ArrayList<Customer> customers;

    //Example 2
//    @SerializedName("customers")
//    ArrayList<Customer> clientes;


    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }



}
