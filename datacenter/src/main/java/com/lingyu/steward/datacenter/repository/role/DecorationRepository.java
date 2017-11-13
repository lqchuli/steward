package com.lingyu.steward.datacenter.repository.role;

import com.lingyu.steward.datacenter.base.JpaSearchableRepository;
import com.lingyu.steward.datacenter.entity.role.DecorationDO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author allan
 * @date 11/11/2017
 */
@Repository
public interface DecorationRepository extends JpaSearchableRepository<DecorationDO, Integer> {
    DecorationDO findByUsername(String username);
}
