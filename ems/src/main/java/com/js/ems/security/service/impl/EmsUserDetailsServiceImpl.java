package com.js.ems.security.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.js.ems.entity.User;
import com.js.ems.repository.UserRepository;
import com.js.ems.security.entity.EmsUserDetails;

import lombok.NoArgsConstructor;

/**
 * Implementation of {@link UserDetailsService} to load user-specific data.
 */
@Service
public class EmsUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Locates the user based on the username. In the actual implementation, the
	 * search may be case-sensitive, case-insensitive, or a case-insensitive search
	 * that returns one user only.
	 *
	 * @param username the username identifying the user whose data is required.
	 * @return a fully populated user record (never {@code null})
	 * @throws UsernameNotFoundException if the user could not be found or the user
	 *                                   has no GrantedAuthority
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findByUsername(username);
		if (optionalUser.isEmpty()) {
			throw new UsernameNotFoundException("User with username: " + username + " not found");
		}
		User user = optionalUser.get();
		return new EmsUserDetails(user);
	}
}
