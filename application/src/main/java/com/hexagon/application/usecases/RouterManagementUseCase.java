package com.hexagon.application.usecases;


import com.hexagon.domain.entity.CoreRouter;
import com.hexagon.domain.entity.Router;
import com.hexagon.domain.vo.*;

public interface RouterManagementUseCase {

    Router createRouter(Id id, Vendor vendor, Model model, IP ip, Location location, RouterType routerType);

    Router removeRouter(Id id);

    Router retrieveRouter(Id id);

    Router persistRouter(Router router);

    Router addRouterToCoreRouter(Router router, CoreRouter coreRouter);

    Router removeRouterFromCoreRouter(Router router, CoreRouter coreRouter);
}
