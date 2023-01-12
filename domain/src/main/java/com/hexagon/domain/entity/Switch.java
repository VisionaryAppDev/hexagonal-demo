package com.hexagon.domain.entity;

import com.hexagon.domain.specification.CIDRSpecification;
import com.hexagon.domain.specification.NetworkAmountSpec;
import com.hexagon.domain.specification.NetworkAvailabilitySpec;
import com.hexagon.domain.vo.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.function.Predicate;

@Getter
public class Switch extends Equipment {

    private SwitchType switchType;
    @Setter
    private List<Network> switchNetworks;

    @Setter
    private Id routerId;

    @Builder
    public Switch(Id switchId, Id routerId, Vendor vendor, Model model, IP ip, Location location, SwitchType switchType, List<Network> switchNetworks){
        super(switchId, vendor, model, ip, location);
        this.switchType = switchType;
        this.switchNetworks = switchNetworks;
        this.routerId = routerId;
    }

    public static Predicate<Network> getNetworkProtocolPredicate(Protocol protocol){
        return s -> s.getNetworkAddress().getProtocol().equals(protocol);
    }

    public static Predicate<Switch> getSwitchTypePredicate(SwitchType switchType){
        return s -> s.switchType .equals(switchType);
    }

    public boolean addNetworkToSwitch(Network network) {
        var availabilitySpec = new NetworkAvailabilitySpec(network);
        var cidrSpec = new CIDRSpecification();
        var amountSpec = new NetworkAmountSpec();

        cidrSpec.check(network.getNetworkCidr());
        availabilitySpec.check(this);
        amountSpec.check(this);

        return this.switchNetworks.add(network);
    }

    public boolean removeNetworkFromSwitch(Network network){
        return this.switchNetworks.remove(network);
    }
}
