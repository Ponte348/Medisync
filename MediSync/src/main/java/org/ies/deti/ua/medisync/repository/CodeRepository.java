package org.ies.deti.ua.medisync.repository;
import org.ies.deti.ua.medisync.model.Code;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CodeRepository extends JpaRepository<Code, Long> {
    public Code findByCode(String code);
    
}
