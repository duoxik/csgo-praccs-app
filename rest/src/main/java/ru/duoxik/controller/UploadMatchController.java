package ru.duoxik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.duoxik.entity.Match;
import ru.duoxik.entity.UploadMatchRequest;
import ru.duoxik.service.uploadmatch.UploadMatchService;

@RestController
@RequestMapping("api/upload-match/")
public class UploadMatchController {

    @Autowired
    private UploadMatchService uploadMatchService;

    @PostMapping
    public Match uploadMatch(@RequestBody UploadMatchRequest request) {
        return uploadMatchService.uploadMatch(request.getPlatform(), request.getMatchId());
    }
}
