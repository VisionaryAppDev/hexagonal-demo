package com.hexagon.framework.adapters.output.mysql.repository;

import com.hexagon.framework.adapters.output.mysql.data.SwitchData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SwitchManagementRepository extends JpaRepository<SwitchData, UUID> {

}
