package com.hexagon.domain.entity.factory;

import com.hexagon.domain.entity.CoreRouter;
import com.hexagon.domain.entity.EdgeRouter;
import com.hexagon.domain.entity.Router;
import com.hexagon.domain.vo.*;

public class RouterFactory {

    public static Router getRouter(Id id,
                                   Vendor vendor,
                                   Model model,
                                   IP ip,
                                   Location location,
                                   RouterType routerType){

        switch (routerType){
            case CORE:
                return CoreRouter.builder().
                        id(id==null ? Id.withoutId():id).
                        vendor(vendor).
                        model(model).
                        ip(ip).
                        location(location).
                        routerType(routerType).
                        build();
            case EDGE:
                return EdgeRouter.builder().
                        id(id==null ? Id.withoutId():id).
                        vendor(vendor).
                        model(model).
                        ip(ip).
                        location(location).
                        routerType(routerType).
                        build();
            default:
                return null;
        }
    }
}
