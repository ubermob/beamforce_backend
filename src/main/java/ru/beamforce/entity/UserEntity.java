package ru.beamforce.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Andrey Korneychuk on 04-Feb-22
 * @version 1.0
 */
@Entity
@Table(name = "usr")
public class UserEntity extends BaseEntity implements UserDetails {

	private String name;
	private String email;
	private String password;
	private boolean isActive;
	@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
	@Enumerated(EnumType.STRING)
	private Set<Role> roles;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "org_id")
	private OrganizationEntity organization;
	@Transient
	private List<GridEntity> gridEntityList;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setRoleUser() {
		if (roles == null) {
			roles = new HashSet<>();
			roles.add(Role.USER);
		}
	}

	public void setRoleUserAndAdmin() {
		if (roles == null) {
			roles = new HashSet<>();
			roles.add(Role.USER);
			roles.add(Role.ADMIN);
		}
	}

	public boolean isAdmin() {
		return roles.contains(Role.ADMIN);
	}

	public boolean hasUserRole() {
		return roles.contains(Role.USER);
	}

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

	public List<GridEntity> getGridEntityList() {
		return gridEntityList;
	}

	public void setGridEntityList(List<GridEntity> gridEntityList) {
		this.gridEntityList = gridEntityList;
	}

	private enum Role implements GrantedAuthority {
		ADMIN, USER;

		@Override
		public String getAuthority() {
			return name();
		}
	}
}