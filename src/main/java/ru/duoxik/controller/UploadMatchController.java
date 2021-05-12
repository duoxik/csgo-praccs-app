package ru.duoxik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.duoxik.entity.Match;
import ru.duoxik.entity.MatchesRequest;
import ru.duoxik.entity.Platform;
import ru.duoxik.service.uploadmatch.UploadMatchService;

@RestController
@RequestMapping("api/upload-match")
public class UploadMatchController {

    @Autowired
    private UploadMatchService uploadMatchService;

    @PostMapping("/{matchId}")
    public Match uploadMatch(@PathVariable Integer matchId) {
        return uploadMatchService.uploadMatch(Platform.FAST_CUP, matchId);
    }

    @PostMapping("/list")
    public Boolean uploadMatches(@RequestBody MatchesRequest request) {
        request.getMatches().stream().sorted().forEach(matchId -> {
            try {
                uploadMatchService.uploadMatch(Platform.FAST_CUP, matchId);
            } catch (Exception e) {
                // ignored
            }
        });
        return true;
    }
}
