package com.hexagon.framework.adapters.output.mysql;


import com.hexagon.framework.adapters.output.mysql.mappers.RouterMapper;
import com.hexagon.framework.adapters.output.mysql.repository.RouterManagementRepository;
import com.hexagon.application.ports.output.RouterManagementOutputPort;
import com.hexagon.domain.entity.Router;
import com.hexagon.domain.vo.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RouterManagementMySQLAdapter implements RouterManagementOutputPort {

    private final RouterManagementRepository routerManagementRepository;

    @Override
    public Router retrieveRouter(Id id) {
        var routerData = routerManagementRepository.findById(id.getUuid()).get();
        return routerData == null ? null : RouterMapper.routerDataToDomain(routerData);
    }

    @Override
    public Router removeRouter(Id id) {
        routerManagementRepository.deleteById(id.getUuid());

        return null;
    }

    @Override
    public Router persistRouter(Router router) {
        var routerData = RouterMapper.routerDomainToData(router);
        routerManagementRepository.save(routerData);
        return router;
    }
}