package com.pitang.Sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pitang.Sms.model.UserModel;

public interface UserModelRepository extends JpaRepository<UserModel, Long>{
	
	

}
