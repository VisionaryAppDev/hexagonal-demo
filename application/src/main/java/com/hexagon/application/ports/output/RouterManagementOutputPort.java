package com.hexagon.application.ports.output;

import com.hexagon.domain.entity.Router;
import com.hexagon.domain.vo.Id;

public interface RouterManagementOutputPort {
    Router retrieveRouter(Id id);

    Router removeRouter(Id id);

    Router persistRouter(Router router);
}
