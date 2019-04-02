package com.jk.sys.service;

import com.jk.sys.domain.RoleDO;
import com.jk.sys.domain.UserDO;
import com.jk.sys.domain.UserRoleDO;
import com.jk.sys.mapper.RoleMapper;
import com.jk.sys.mapper.UserMapper;
import com.jk.sys.domain.User;
import com.jk.sys.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccountService implements UserDetailsService {
	
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

//	@Autowired
//	private PasswordEncoder passwordEncoder;

	//@PostConstruct
//	protected void initialize() {
		//save(new Account("user", "demo", "ROLE_USER"));
		//save(new Account("admin", "admin", "ROLE_ADMIN"));
//	}

//	@Transactional
//	public Account save(Account account) {
//		account.setPassword(passwordEncoder.encode(account.getPassword()));
//		accountRepository.save(account);
//		return account;
//	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		HashMap<String, Object> u = new HashMap<String, Object>();
		u.put("username", username);
		List<UserDO> accounts = userMapper.list(u);
		if(accounts.size() == 0) {
			throw new UsernameNotFoundException("user not found");
		}

		User user = createUser(accounts.get(0));
		
		
		//跳转到/index时需要获取到用户id，用户名等信息
		signin(user);
		return user;
	}
	
	public void signin(User user) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(user));
	}
	
	private Authentication authenticate(User user) {
		return new UsernamePasswordAuthenticationToken(user, user.getPassword(), null);
	}
	
	private User createUser(UserDO account) {

		return new User(account.getUserId(), account.getUsername(),account.getName(), account.getPassword(),createAuthority(account));//Collections.singleton(all)
	}

	private HashSet<SimpleGrantedAuthority> createAuthority(UserDO account) {
		HashSet<SimpleGrantedAuthority> all = new HashSet<>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userId", account.getUserId());
		List<UserRoleDO> dos = userRoleMapper.list(map);
		for(UserRoleDO do1: dos) {
			//对每一个roleId，找出名称
			RoleDO role = roleMapper.get(do1.getRoleId());
			all.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleSign()));
		}
		return all;
	}

}
