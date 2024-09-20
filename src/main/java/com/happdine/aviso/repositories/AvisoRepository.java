package com.happdine.aviso.repositories;

import com.happdine.aviso.models.AvisoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvisoRepository extends JpaRepository<AvisoModel, Long> {
}
