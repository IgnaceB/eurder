package switchfully.com.eurder.security;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public enum Role {
    ADMIN(newArrayList(Feature.VIEW_ALL_CUSTOMERS,Feature.VIEW_ONE_CUSTOMER,Feature.CREATE_ONE_ITEM,Feature.UPDATE_ONE_ITEM, Feature.GET_ALL_ITEMS)),
    CUSTOMER(newArrayList(Feature.ORDER_ITEMS,Feature.VIEW_ORDERS, Feature.VIEW_ONE_CUSTOMER,Feature.GET_ALL_ITEMS));


    private List<Feature> featureList;

    Role() {
    }
     Role(List<Feature> featureList) {
        this.featureList=featureList;
     }



    public boolean isAllowedToFeature(Feature feature){
         return featureList.contains(feature);
     }
}
