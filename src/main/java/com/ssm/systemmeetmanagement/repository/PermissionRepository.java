package com.ssm.systemmeetmanagement.repository;

import com.ssm.systemmeetmanagement.model.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
}
