package com.github.holodnov.javaee7webservices.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author Kyrylo Holodnov
 */
@Entity
@Table(name = "ES_ROLE")
@SequenceGenerator(name = "seq_gen", sequenceName = "hibernate_sequence", allocationSize = 1)
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "EsRole.findAll", query = "SELECT e FROM EsRole e"),
	@NamedQuery(name = "EsRole.findById", query = "SELECT e FROM EsRole e WHERE e.id = :id"),
	@NamedQuery(name = "EsRole.findByName", query = "SELECT e FROM EsRole e WHERE e.name = :name"),
	@NamedQuery(name = "EsRole.findByDescription", query = "SELECT e FROM EsRole e WHERE e.description = :description") })
public class EsRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String name;
    @Size(max = 4000)
    @Column(length = 4000)
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId", fetch = FetchType.LAZY)
    private List<EsUser> esUserList;

    public EsRole() {
    }

    public EsRole(Long id) {
	this.id = id;
    }

    public EsRole(Long id, String name) {
	this.id = id;
	this.name = name;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @XmlTransient
    public List<EsUser> getEsUserList() {
	return esUserList;
    }

    public void setEsUserList(List<EsUser> esUserList) {
	this.esUserList = esUserList;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (id != null ? id.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	if (!(object instanceof EsRole)) {
	    return false;
	}
	EsRole other = (EsRole) object;
	if ((this.id == null && other.id != null)
		|| (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.github.holodnov.javaee7webservices.model.EsRole[ id=" + id
		+ " ]";
    }
}
