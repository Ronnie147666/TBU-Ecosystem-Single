package com.deathstar.BattleGenerator.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.deathstar.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.deathstar.BattleGenerator.client.FeignTBUClient;

@Service
public class SingleBattleGenerator {

    private final Logger LOGGER = LoggerFactory.getLogger(SingleBattleGenerator.class);

    @Autowired
    FeignTBUClient feign;

    @Scheduled(fixedRate = 10000)
    public void generateSingleBattle() {


        List<Integer> randomNumbers = IntStream.rangeClosed(1, 36).boxed().collect(Collectors.toList());
        Collections.shuffle(randomNumbers);

        BattleDeclarationNew declaration = new BattleDeclarationNew();

      /*
      Create home Emperor
       */
        EmperorDTO homeEmperor = new EmperorDTO();

        List<String> homeUnits = new ArrayList<>();

        List<Integer> homeDraw = randomNumbers.stream().limit(5).collect(Collectors.toList());

        for (int i = 0; i < 5; i++){
            homeUnits.add(RandomUnitGenerator.getUnit(homeDraw.get(i)));
        }
        homeEmperor.setName("Lord");
        homeEmperor.setUnits(homeUnits);
        homeEmperor.setStatPriority(StatPriority.HEALTH);

        declaration.setHomeEmperor(homeEmperor);
      /*
      Create away Emperor
       */

        EmperorDTO awayEmperor = new EmperorDTO();

        List<String> awayUnits = new ArrayList<>();

        List<Integer> awayDraw = randomNumbers.stream().skip(5).limit(5).collect(Collectors.toList());

        for (int i = 0; i < 5; i++){
            awayUnits.add(RandomUnitGenerator.getUnit(awayDraw.get(i)));
        }
        awayEmperor.setName("King");
        awayEmperor.setUnits(awayUnits);
        awayEmperor.setStatPriority(StatPriority.HEALTH);

        declaration.setAwayEmperor(awayEmperor);

        declaration.setBattleType(BattleType.SINGLE_FIVE);

        LOGGER.info("Created a Single Battle Declaration");

        feign.postBattleDeclaration(declaration);
    }

}