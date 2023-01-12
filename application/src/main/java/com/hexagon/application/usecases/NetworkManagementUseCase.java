package com.hexagon.application.usecases;


import com.hexagon.domain.entity.Switch;
import com.hexagon.domain.vo.IP;
import com.hexagon.domain.vo.Network;

public interface NetworkManagementUseCase {

    Network createNetwork(IP networkAddress, String networkName, int networkCidr);

    Switch addNetworkToSwitch(Network network, Switch networkSwitch);

    Switch removeNetworkFromSwitch(String name, Switch networkSwitch);
}
