package switchfully.com.eurder.security;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public enum Role {
    ADMIN(newArrayList(Feature.VIEW_ALL_CUSTOMERS,Feature.VIEW_ONE_CUSTOMER,Feature.CREATE_ONE_ITEM,Feature.UPDATE_ONE_ITEM)),
    CUSTOMER(newArrayList(Feature.ORDER_ITEMS,Feature.VIEW_ORDERS));


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
