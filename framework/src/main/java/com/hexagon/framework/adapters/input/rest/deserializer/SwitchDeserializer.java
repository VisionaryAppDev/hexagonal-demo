package com.hexagon.framework.adapters.input.rest.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.hexagon.domain.entity.Switch;
import com.hexagon.domain.vo.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SwitchDeserializer extends StdDeserializer<Switch> {

    public SwitchDeserializer() {
        this(null);
    }

    public SwitchDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Switch deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        var id = node.get("id").get("uuid").asText();
        var routerId = node.get("routerId").get("uuid").asText();
        var vendor = node.get("vendor").asText();
        var model = node.get("model").asText();
        var ip = node.get("ip").get("ipAddress").asText();
        var location = node.get("location");
        var switchType = node.get("switchType").asText();
        var switchNetworksNode = node.get("switchNetworks");

        var networkSwitch = Switch.builder()
                .switchId(Id.withId(id))
                .vendor(Vendor.valueOf(vendor))
                .model(Model.valueOf(model))
                .ip(IP.fromAddress(ip))
                .location(LocationDeserializer.getLocation(location))
                .switchType(SwitchType.valueOf(switchType))
                .routerId(Id.withId(routerId))
                .build();

        fetchChildNetworks(switchNetworksNode, networkSwitch);

        return networkSwitch;
    }

    private void fetchChildNetworks(JsonNode switchNetworksNode, Switch networkSwitch) throws IOException {
        List<Network> networks = new ArrayList<>();
        if (switchNetworksNode != null) {
            var childNetworks = switchNetworksNode.iterator();
            while (childNetworks.hasNext()) {
                var childNetwork = childNetworks.next();
                var fetchedNetwork = NetworkDeserializer.getNetworkDeserialized(childNetwork.toString());
                networks.add(fetchedNetwork);
            }
            networkSwitch.setSwitchNetworks(networks);
        }
    }

    public static Switch getSwitchDeserialized(String jsonStr) throws IOException {
        var mapper = new ObjectMapper();
        var module = new SimpleModule();
        module.addDeserializer(Switch.class, new SwitchDeserializer());
        mapper.registerModule(module);
        var networkSwitch = mapper.readValue(jsonStr, Switch.class);
        return networkSwitch;
    }
}
