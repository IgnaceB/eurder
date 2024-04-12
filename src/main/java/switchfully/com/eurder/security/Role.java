package switchfully.com.eurder.security;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public enum Role {
    ADMIN(newArrayList(Feature.VIEW_ALL_CUSTOMERS,Feature.VIEW_ONE_CUSTOMER,Feature.ADD_AN_ITEM)),
    CUSTOMER(newArrayList(Feature.ORDER_ITEMS));


    List<Feature> featureList;
     Role(List<Feature> featureList) {
        this.featureList=featureList;
     }

     public boolean isAllowedToFeature(Feature feature){
         return featureList.contains(feature);
     }
}
