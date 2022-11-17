package com.hr.employee.api.doa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    @Modifying
    @Transactional
    @Query("update #{#entityName} t SET t.statusCode = :statusCode WHERE t.id = :id")
    void updateEntity(@Param("id") Long id, @Param("statusCode") String statusCode);

}
