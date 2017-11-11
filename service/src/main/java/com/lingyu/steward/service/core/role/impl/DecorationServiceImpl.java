package com.lingyu.steward.service.core.role.impl;

import com.lingyu.steward.datacenter.entity.role.DecorationDO;
import com.lingyu.steward.datacenter.repository.role.DecorationRepository;
import com.lingyu.steward.service.base.AbstractSearchableCrudService;
import com.lingyu.steward.service.core.role.DecorationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author allan
 * @date 11/11/2017
 */
@Service("decorationService")
public class DecorationServiceImpl extends AbstractSearchableCrudService<DecorationDO, Integer, Void> implements DecorationService, UserDetailsService {
    private DecorationRepository decorationRepository;

    @Autowired
    public DecorationServiceImpl(DecorationRepository repository) {
        super(repository);
        decorationRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return decorationRepository.findByUsername(username);
    }
}
