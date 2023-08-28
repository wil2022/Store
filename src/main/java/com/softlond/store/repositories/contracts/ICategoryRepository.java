package com.softlond.store.repositories.contracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softlond.store.entities.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

}
