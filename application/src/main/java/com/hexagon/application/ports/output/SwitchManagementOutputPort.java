package com.hexagon.application.ports.output;

import com.hexagon.domain.entity.Switch;
import com.hexagon.domain.vo.Id;

public interface SwitchManagementOutputPort {
    Switch retrieveSwitch(Id id);
}
