package com.hexagon.application.ports.input;


import com.hexagon.application.ports.output.SwitchManagementOutputPort;
import com.hexagon.application.usecases.SwitchManagementUseCase;
import com.hexagon.domain.entity.EdgeRouter;
import com.hexagon.domain.entity.Switch;
import com.hexagon.domain.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SwitchManagementInputPort implements SwitchManagementUseCase {

    private final SwitchManagementOutputPort switchManagementOutputPort;

    @Override
    public Switch createSwitch(Vendor vendor, Model model, IP ip, Location location, SwitchType switchType) {
        return Switch.builder().switchId(Id.withoutId()).vendor(vendor).model(model).ip(ip).location(location).switchType(switchType).build();
    }

    public Switch retrieveSwitch(Id id) {
        return switchManagementOutputPort.retrieveSwitch(id);
    }

    @Override
    public EdgeRouter addSwitchToEdgeRouter(Switch networkSwitch, EdgeRouter edgeRouter) {
        networkSwitch.setRouterId(edgeRouter.getId());
        edgeRouter.addSwitch(networkSwitch);
        return edgeRouter;
    }

    @Override
    public EdgeRouter removeSwitchFromEdgeRouter(Switch networkSwitch, EdgeRouter edgeRouter) {
        edgeRouter.removeSwitch(networkSwitch);
        return edgeRouter;
    }
}
