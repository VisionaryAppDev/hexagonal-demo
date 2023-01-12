package com.hexagon.framework.adapters.input.rest;

import com.hexagon.framework.adapters.input.rest.request.router.CreateRouter;
import com.hexagon.application.usecases.RouterManagementUseCase;
import com.hexagon.domain.entity.CoreRouter;
import com.hexagon.domain.entity.Router;
import com.hexagon.domain.vo.IP;
import com.hexagon.domain.vo.Id;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/router")
@RestController
public class RouterManagementAdapter {

    private final RouterManagementUseCase routerManagementUseCase;

    @Transactional
    @GetMapping("/{id}")
    public Router retrieveRouter(@PathVariable("id") Id id) {
        return routerManagementUseCase.retrieveRouter(id);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public Router removeRouter(@PathVariable("id") Id id) {
        return routerManagementUseCase.removeRouter(id);
    }

    @Transactional
    @PostMapping("/")
    public Router createRouter(@RequestBody CreateRouter createRouter) {
        var router = routerManagementUseCase.createRouter(
                null,
                createRouter.getVendor(),
                createRouter.getModel(),
                IP.fromAddress(createRouter.getIp()),
                createRouter.getLocation(),
                createRouter.getRouterType()

        );

        return routerManagementUseCase.persistRouter(router);
    }

    @Transactional
    @PostMapping("/{routerId}/to/{coreRouterId}")
    public Router addRouterToCoreRouter(@PathVariable("routerId") String routerId, @PathVariable("coreRouterId") String coreRouterId) {
        Router router = routerManagementUseCase.retrieveRouter(Id.withId(routerId));
        CoreRouter coreRouter = (CoreRouter) routerManagementUseCase.retrieveRouter(Id.withId(coreRouterId));

        return routerManagementUseCase.addRouterToCoreRouter(router, coreRouter);
    }

    @Transactional
    @DeleteMapping("/{routerId}/from/{coreRouterId}")
    public Router removeRouterFromCoreRouter(@PathVariable("routerId") String routerId, @PathVariable("coreRouterId") String coreRouterId) {
        Router router = routerManagementUseCase.retrieveRouter(Id.withId(routerId));
        CoreRouter coreRouter = (CoreRouter) routerManagementUseCase.retrieveRouter(Id.withId(coreRouterId));

        return routerManagementUseCase.removeRouterFromCoreRouter(router, coreRouter);
    }
}
