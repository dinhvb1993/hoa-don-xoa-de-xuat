package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.CommonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommonRepository extends JpaRepository<CommonEntity, Integer> {
}
