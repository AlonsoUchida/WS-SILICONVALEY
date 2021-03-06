package com.valmar.silliconvalley.xsecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class JwtUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private final int id;
    private final String nombre;
    private final String empresa;
    private final Date fechaNacimiento;    
    private final String sexo;
    private final String contrasena;
    private final String correo;
    private String cargo;   
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(int id, String nombre, String empresa, Date fechaNacimiento, String sexo,
    		String contrasena, String correo, String cargo, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.nombre = nombre;
        this.empresa = empresa;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.contrasena = contrasena;
        this.correo = correo;
        this.cargo = cargo;
        this.authorities = authorities;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return contrasena;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return correo;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


}
