package com.deathstar.generator.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.deathstar.domain.BattleDeclaration;



@FeignClient(name = "TBU")
public interface FeignTBUClient {

		@RequestMapping(method = RequestMethod.POST, value = "tbu")
		void postBattleDeclaration(@RequestBody BattleDeclaration battle);
		
	}

