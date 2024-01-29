package com.jpa.crud.repository;

//package com.jpa.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.crud.entity.Usernew;

@Repository
public interface UserRepository extends JpaRepository<Usernew,Long> {

}
