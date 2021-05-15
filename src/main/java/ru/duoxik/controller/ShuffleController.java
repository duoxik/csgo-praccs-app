package ru.duoxik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.duoxik.entity.ShuffleRequest;
import ru.duoxik.entity.ShuffleVariation;
import ru.duoxik.service.shuffle.ShuffleService;

@RestController
@RequestMapping("api/shuffle")
public class ShuffleController {

    private final ShuffleService shuffleService;

    @Autowired
    public ShuffleController(ShuffleService shuffleService) {
        this.shuffleService = shuffleService;
    }

    @PostMapping
    public ShuffleVariation uploadMatches(@RequestBody ShuffleRequest request) {
        return shuffleService.shuffle(request.getFastcupUserIds());
    }
}
