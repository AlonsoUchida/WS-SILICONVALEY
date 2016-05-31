package com.valmar.silliconvalley.xsecurity.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TOKEN")
public class Token {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "AUTHTOKEN", length = 500)
    @NotNull
    private String authToken;
    
    @Column(name = "ISSUEDON")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date issuedOn;
    
    @Column(name = "EXPIRESON")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date expiresOn;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USERID")
    private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public Date getIssuedOn() {
		return issuedOn;
	}

	public void setIssuedOn(Date issuedOn) {
		this.issuedOn = issuedOn;
	}

	public Date getExpiresOn() {
		return expiresOn;
	}

	public void setExpiresOn(Date expiresOn) {
		this.expiresOn = expiresOn;
	}

	public Usuario getUser() {
		return usuario;
	}

	public void setUser(Usuario usuario) {
		this.usuario = usuario;
	}

}
