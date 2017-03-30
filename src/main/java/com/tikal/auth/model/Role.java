package com.tikal.auth.model;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.Set;

/**
 * Created by Sopher on 23/03/2017.
 */

@Entity
@Table(name = "role")
//@NamedQueries(
        @NamedQuery(name = "Role.findAll", query="SELECT r FROM Role r ")
//)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="role_role_id_seq")
    @SequenceGenerator(name="role_role_id_seq", sequenceName="role_role_id_seq")
    private int roleId;

    @Column(nullable = false, unique = true)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<Account> accounts;

    public Role() {}

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);

    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
