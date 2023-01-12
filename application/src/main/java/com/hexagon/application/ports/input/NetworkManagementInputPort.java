package com.hexagon.application.ports.input;

import com.hexagon.application.ports.output.RouterManagementOutputPort;
import com.hexagon.application.usecases.NetworkManagementUseCase;
import com.hexagon.domain.entity.EdgeRouter;
import com.hexagon.domain.entity.Switch;
import com.hexagon.domain.service.NetworkService;
import com.hexagon.domain.vo.IP;
import com.hexagon.domain.vo.Id;
import com.hexagon.domain.vo.Network;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@RequiredArgsConstructor
@Service
public class NetworkManagementInputPort implements NetworkManagementUseCase {

    private final RouterManagementOutputPort routerManagementOutputPort;

    @Override
    public Network createNetwork(IP networkAddress, String networkName, int networkCidr) {
        return Network.builder().networkAddress(networkAddress).networkName(networkName).networkCidr(networkCidr).build();
    }

    @Override
    public Switch addNetworkToSwitch(Network network, Switch networkSwitch) {
        Id routerId = networkSwitch.getRouterId();
        Id switchId = networkSwitch.getId();
        EdgeRouter edgeRouter = (EdgeRouter) routerManagementOutputPort.retrieveRouter(routerId);
        Switch switchToAddNetwork = edgeRouter.getSwitches().get(switchId);
        switchToAddNetwork.addNetworkToSwitch(network);
        routerManagementOutputPort.persistRouter(edgeRouter);
        return switchToAddNetwork;
    }

    @Override
    public Switch removeNetworkFromSwitch(String networkName, Switch networkSwitch) {
        Id routerId = networkSwitch.getRouterId();
        Id switchId = networkSwitch.getId();
        EdgeRouter edgeRouter = (EdgeRouter) routerManagementOutputPort.retrieveRouter(routerId);
        Switch switchToRemoveNetwork = edgeRouter.getSwitches().get(switchId);
        Predicate<Network> networkPredicate = Network.getNetworkNamePredicate(networkName);
        var network = NetworkService.findNetwork(switchToRemoveNetwork.getSwitchNetworks(), networkPredicate);
        routerManagementOutputPort.persistRouter(edgeRouter);
        return switchToRemoveNetwork.removeNetworkFromSwitch(network) ? switchToRemoveNetwork : null;
    }
}
