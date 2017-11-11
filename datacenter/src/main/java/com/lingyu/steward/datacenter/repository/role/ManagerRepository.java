package com.lingyu.steward.datacenter.repository.role;

import com.lingyu.steward.datacenter.base.JpaSearchableRepository;
import com.lingyu.steward.datacenter.entity.role.ManagerDO;
import org.springframework.stereotype.Repository;

/**
 * @author allan
 * @date 11/11/2017
 */
@Repository
public interface ManagerRepository extends JpaSearchableRepository<ManagerDO, Integer> {
    ManagerDO findByUsername(String username);
}
