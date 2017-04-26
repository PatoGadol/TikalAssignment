package com.tikal.dao.repository;

import java.util.List;
import com.tikal.dao.model.Role;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Sopher on 23/03/2017.
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
    public Role findByRoleName(String roleName);

    @Query
    @Override
    @Cacheable
    public List<Role> findAll();
}
