package org.checkr.adverseactions.repository;

import org.checkr.adverseactions.entity.AdverseAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdverseActionRepository extends JpaRepository<AdverseAction, Integer> {

}