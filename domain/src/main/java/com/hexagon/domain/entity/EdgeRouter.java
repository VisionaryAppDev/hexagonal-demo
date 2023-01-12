package com.hexagon.domain.entity;

import com.hexagon.domain.specification.EmptyNetworkSpec;
import com.hexagon.domain.specification.SameCountrySpec;
import com.hexagon.domain.specification.SameIpSpec;
import com.hexagon.domain.vo.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public class EdgeRouter extends Router {

    @Setter
    private Map<Id, Switch> switches;

    @Builder
    public EdgeRouter(Id id, Id parentRouterId, Vendor vendor, Model model, IP ip, Location location, RouterType routerType, Map<Id, Switch> switches) {
        super(id, parentRouterId, vendor, model, ip, location, routerType);
        this.switches = switches;
    }

    public void addSwitch(Switch anySwitch) {
        var sameCountryRouterSpec = new SameCountrySpec(this);
        var sameIpSpec = new SameIpSpec(this);

        sameCountryRouterSpec.check(anySwitch);
        sameIpSpec.check(anySwitch);

        this.switches.put(anySwitch.id,anySwitch);
    }

    public Switch removeSwitch(Switch anySwitch) {
        var emptyNetworkSpec = new EmptyNetworkSpec();
        emptyNetworkSpec.check(anySwitch);

        return this.switches.remove(anySwitch.id);
    }
}