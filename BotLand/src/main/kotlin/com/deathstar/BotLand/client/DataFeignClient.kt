package com.deathstar.BotLand.client

import com.deathstar.BotLand.domain.SingleUnitStats
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(name = "Datahouse", url = "http://178.128.205.221:8085")
public interface DataFeignClient {

    @RequestMapping(method = [RequestMethod.GET], value = ["/data/stats/single"])
    fun getUnitPercentages(): List<SingleUnitStats>

}