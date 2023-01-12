package com.hexagon.application.ports.input;

import com.hexagon.application.ports.output.RouterManagementOutputPort;
import com.hexagon.application.usecases.RouterManagementUseCase;
import com.hexagon.domain.entity.CoreRouter;
import com.hexagon.domain.entity.Router;
import com.hexagon.domain.entity.factory.RouterFactory;
import com.hexagon.domain.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RouterManagementInputPort implements RouterManagementUseCase {

    private final RouterManagementOutputPort routerManagementOutputPort;

    @Override
    public Router createRouter(Id id, Vendor vendor, Model model, IP ip, Location location, RouterType routerType) {
        return RouterFactory.getRouter(null, vendor, model, ip, location, routerType);
    }

    @Override
    public Router removeRouter(Id id) {
        return routerManagementOutputPort.removeRouter(id);
    }

    @Override
    public Router retrieveRouter(Id id) {
        return routerManagementOutputPort.retrieveRouter(id);
    }

    @Override
    public Router persistRouter(Router router) {
        return routerManagementOutputPort.persistRouter(router);
    }

    @Override
    public CoreRouter addRouterToCoreRouter(Router router, CoreRouter coreRouter) {
        var addedRouter = coreRouter.addRouter(router);
        return (CoreRouter) persistRouter(addedRouter);
    }

    @Override
    public Router removeRouterFromCoreRouter(Router router, CoreRouter coreRouter) {
        var removedRouter = coreRouter.removeRouter(router);
        return persistRouter(removedRouter);
    }
}