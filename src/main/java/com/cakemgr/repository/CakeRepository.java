package com.cakemgr.repository;

import org.springframework.data.repository.CrudRepository;
import com.cakemgr.model.Cake;

public interface CakeRepository extends CrudRepository<Cake, Integer> {
}
