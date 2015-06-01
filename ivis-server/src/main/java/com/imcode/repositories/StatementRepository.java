package com.imcode.repositories;

import com.imcode.entities.Statement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vitaly on 28.05.15.
 */
public interface StatementRepository extends JpaRepository<Statement, Long>{
}
