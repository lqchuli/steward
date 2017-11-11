package com.lingyu.steward.service.core.role.impl;

import com.lingyu.steward.datacenter.base.JpaSearchableRepository;
import com.lingyu.steward.datacenter.entity.role.ManagerDO;
import com.lingyu.steward.datacenter.repository.role.ManagerRepository;
import com.lingyu.steward.service.base.AbstractSearchableCrudService;
import com.lingyu.steward.service.core.role.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author allan
 * @date 11/11/2017
 */
@Service("managerService")
public class ManagerServiceImpl extends AbstractSearchableCrudService<ManagerDO, Integer, Void> implements ManagerService, UserDetailsService {
    private ManagerRepository managerRepository;

    @Autowired
    protected ManagerServiceImpl(ManagerRepository repository) {
        super(repository);
        managerRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return managerRepository.findByUsername(username);
    }
}
