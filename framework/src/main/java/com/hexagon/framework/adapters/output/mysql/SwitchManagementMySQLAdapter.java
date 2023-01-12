package com.hexagon.framework.adapters.output.mysql;

import com.hexagon.framework.adapters.output.mysql.mappers.RouterMapper;
import com.hexagon.framework.adapters.output.mysql.repository.SwitchManagementRepository;
import com.hexagon.application.ports.output.SwitchManagementOutputPort;
import com.hexagon.domain.entity.Switch;
import com.hexagon.domain.vo.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class SwitchManagementMySQLAdapter implements SwitchManagementOutputPort {

    private final SwitchManagementRepository switchManagementRepository;

    @Override
    public Switch retrieveSwitch(Id id) {
        var switchData = switchManagementRepository.findById(id.getUuid()).orElse(null);
        return switchData == null ? null : RouterMapper.switchDataToDomain(switchData);
    }
}
