//package com.framework.framework.adapters.input.rest;
//
//
//import com.hexagon.application.ports.usecases.NetworkManagementUseCase;
//import com.hexagon.application.ports.usecases.SwitchManagementUseCase;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequestMapping("/network")
//@RestController
//public class NetworkManagementAdapter {
//
//    @Autowired
//    SwitchManagementUseCase switchManagementUseCase;
//    @Autowired
//    NetworkManagementUseCase networkManagementUseCase;
//
//    @Transactional
//    @POST
//    @Path("/add/{switchId}")
//    @Operation(operationId = "addNetworkToSwitch", description = "Add network to a switch")
//    public Uni<Response> addNetworkToSwitch(AddNetwork addNetwork, @PathParam("switchId") String switchId) {
//        Switch networkSwitch = switchManagementUseCase.retrieveSwitch(Id.withId(switchId));
//
//        Network network = Network.builder()
//                .networkAddress(IP.fromAddress(addNetwork.getNetworkAddress()))
//                .networkName(addNetwork.getNetworkName())
//                .networkCidr(addNetwork.getNetworkCidr())
//                .build();
//
//        return Uni.createFrom()
//                .item(networkManagementUseCase.addNetworkToSwitch(network, networkSwitch))
//                .onItem()
//                .transform(f -> f != null ? Response.ok(f) : Response.ok(null))
//                .onItem()
//                .transform(Response.ResponseBuilder::build);
//    }
//
//    @Transactional
//    @DELETE
//    @Path("/{networkName}/from/{switchId}")
//    @Operation(operationId = "removeNetworkFromSwitch", description = "Remove network from a switch")
//    public Uni<Response> removeNetworkFromSwitch(@PathParam("networkName") String networkName, @PathParam("switchId") String switchId) {
//        Switch networkSwitch = switchManagementUseCase.retrieveSwitch(Id.withId(switchId));
//
//        return Uni.createFrom()
//                .item(networkManagementUseCase.removeNetworkFromSwitch(networkName, networkSwitch))
//                .onItem()
//                .transform(f -> f != null ? Response.ok(f) : Response.ok(null))
//                .onItem()
//                .transform(Response.ResponseBuilder::build);
//    }
//}
