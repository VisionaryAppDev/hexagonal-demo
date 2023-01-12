package com.hexagon.framework.adapters.input.rest;

import com.hexagon.framework.adapters.input.rest.request.aswitch.CreateSwitch;
import com.hexagon.application.usecases.RouterManagementUseCase;
import com.hexagon.application.usecases.SwitchManagementUseCase;
import com.hexagon.domain.entity.EdgeRouter;
import com.hexagon.domain.entity.Router;
import com.hexagon.domain.entity.Switch;
import com.hexagon.domain.vo.IP;
import com.hexagon.domain.vo.Id;
import com.hexagon.domain.vo.RouterType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/switch")
@RestController
public class SwitchManagementAdapter {

    private final SwitchManagementUseCase switchManagementUseCase;
    private final RouterManagementUseCase routerManagementUseCase;

    @Transactional
    @GetMapping("/{id}")
    public Switch retrieveSwitch(@PathVariable("id") Id switchId) {
        return switchManagementUseCase.retrieveSwitch(switchId);
    }

    @Transactional
    @PostMapping("/create/{edgeRouterId}")
    public EdgeRouter createAndAddSwitchToEdgeRouter(@RequestBody CreateSwitch createSwitch, @PathVariable("edgeRouterId") String edgeRouterId) {
        Switch newSwitch = switchManagementUseCase.
                createSwitch(
                        createSwitch.getVendor(),
                        createSwitch.getModel(),
                        IP.fromAddress(createSwitch.getIp()),
                        createSwitch.getLocation(),
                        createSwitch.getSwitchType());
        Router edgeRouter = routerManagementUseCase.retrieveRouter(Id.withId(edgeRouterId));
        if (!edgeRouter.getRouterType().equals(RouterType.EDGE))
            throw new UnsupportedOperationException("Please inform the id of an edge router to add a switch");
        Router router = switchManagementUseCase.addSwitchToEdgeRouter(newSwitch, (EdgeRouter) edgeRouter);

        return (EdgeRouter) routerManagementUseCase.persistRouter(router);
    }

    @Transactional
    @DeleteMapping("/{switchId}/from/{edgeRouterId}")
    public EdgeRouter removeSwitchFromEdgeRouter(@PathVariable("switchId") String switchId, @PathVariable("edgeRouterId") String edgeRouterId) {
        EdgeRouter edgeRouter = (EdgeRouter) routerManagementUseCase.retrieveRouter(Id.withId(edgeRouterId));
        Switch networkSwitch = switchManagementUseCase.retrieveSwitch(Id.withId(switchId));
        Router router = switchManagementUseCase.removeSwitchFromEdgeRouter(networkSwitch, edgeRouter);

        return (EdgeRouter) routerManagementUseCase.persistRouter(router);
    }
}
