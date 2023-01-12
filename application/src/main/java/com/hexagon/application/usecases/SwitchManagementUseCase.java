package com.hexagon.application.usecases;


import com.hexagon.domain.entity.EdgeRouter;
import com.hexagon.domain.entity.Switch;
import com.hexagon.domain.vo.*;

public interface SwitchManagementUseCase {

    Switch createSwitch(Vendor vendor, Model model, IP ip, Location location, SwitchType switchType);

    Switch retrieveSwitch(Id id);

    EdgeRouter addSwitchToEdgeRouter(Switch networkSwitch, EdgeRouter edgeRouter);

    EdgeRouter removeSwitchFromEdgeRouter(Switch networkSwitch, EdgeRouter edgeRouter);
}
