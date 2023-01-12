package com.hexagon.framework.adapters.output.mysql.repository;

import com.hexagon.framework.adapters.output.mysql.data.RouterData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface RouterManagementRepository extends JpaRepository<RouterData, UUID> {

}
