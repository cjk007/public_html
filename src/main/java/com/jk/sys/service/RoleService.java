package com.jk.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jk.sys.domain.RoleDO;



@Service
public interface RoleService {

	RoleDO get(Long id);

	List<RoleDO> list();

	int save(RoleDO role);

	int update(RoleDO role);

	int remove(Long id);

	List<RoleDO> list(Long userId);

	int batchremove(Long[] ids);
}
