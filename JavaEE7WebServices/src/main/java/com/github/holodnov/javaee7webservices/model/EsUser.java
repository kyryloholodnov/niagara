package com.github.holodnov.javaee7webservices.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kyrylo Holodnov
 */
@Entity
@Table(name = "ES_USER")
@SequenceGenerator(name = "seq_gen", sequenceName = "hibernate_sequence", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EsUser.findAll", query = "SELECT e FROM EsUser e"),
    @NamedQuery(name = "EsUser.findById", query = "SELECT e FROM EsUser e WHERE e.id = :id"),
    @NamedQuery(name = "EsUser.findByLogin", query = "SELECT e FROM EsUser e WHERE e.login = :login"),
    @NamedQuery(name = "EsUser.findByPassword", query = "SELECT e FROM EsUser e WHERE e.password = :password"),
    @NamedQuery(name = "EsUser.findByEmail", query = "SELECT e FROM EsUser e WHERE e.email = :email"),
    @NamedQuery(name = "EsUser.findByDescription", query = "SELECT e FROM EsUser e WHERE e.description = :description")})
public class EsUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(nullable = false, length = 20)
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(nullable = false, length = 20)
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String email;
    @Lob
    private String biography;
    @Size(max = 4000)
    @Column(length = 4000)
    private String description;
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EsRole roleId;

    public EsUser() {
    }

    public EsUser(Long id) {
        this.id = id;
    }

    public EsUser(Long id, String login, String password, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EsRole getRoleId() {
        return roleId;
    }

    public void setRoleId(EsRole roleId) {
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof EsUser)) {
            return false;
        }
        EsUser other = (EsUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.github.holodnov.javaee7webservices.model.EsUser[ id=" + id + " ]";
    }
}
