package com.lio.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lio.dto.Authority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_lio_user")
public class User implements Serializable,UserDetails {
	
	private static final long serialVersionUID = 9;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="role_id")
	private Role role;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "phone_no")
	private String phoneNo;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "is_active")
	private String isActive;
	
	@Column(name = "sign_in_on")
	private String signInOn;
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="sign_in_mode_option_id")
//	private SignInModeOption signInModeOption;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Authority> authorities=new HashSet<>();
		authorities.add(new Authority(role.getRoleName()));
		return authorities;
	}

	@Override
	public String getUsername() {
		return this.userName;
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
		return true;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
