package com.imcode.repositories;


import com.imcode.misc.TokenInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TokenInfoRepository extends JpaRepository<TokenInfo, UUID> {
}
