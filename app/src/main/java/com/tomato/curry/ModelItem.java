package com.tomato.curry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saulmm on 15/02/16.
 */
public class ModelItem {
    private String city;

    public ModelItem(String city) {
        this.city = city;
    }


    public String getCity() {
        return city;
    }

    public static List<ModelItem> getFakeItems() {
        ArrayList<ModelItem> itemsList = new ArrayList<>();
        itemsList.add(new ModelItem("TIRUPATHI"));
        itemsList.add(new ModelItem("NELLORE"));
        itemsList.add(new ModelItem("CHENNAI"));
        itemsList.add(new ModelItem("HYDERABAD"));
        itemsList.add(new ModelItem("BANGALORE"));
        itemsList.add(new ModelItem("VIJAYAWADA"));
        itemsList.add(new ModelItem("VISAKAPATNAM"));
        return itemsList;
    }
}
