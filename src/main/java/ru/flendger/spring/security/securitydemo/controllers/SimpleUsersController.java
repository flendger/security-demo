package ru.flendger.spring.security.securitydemo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.flendger.spring.security.securitydemo.entities.User;
import ru.flendger.spring.security.securitydemo.exceptions.NeedAuthorisationException;
import ru.flendger.spring.security.securitydemo.exceptions.NoSuchUserException;
import ru.flendger.spring.security.securitydemo.services.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class SimpleUsersController {

    private final UserService userService;

    @GetMapping("/app/score/get/{id}")
    public String getUserScoreById(@PathVariable Long id) throws NoSuchUserException{
        User user = userService.findById(id).orElseThrow(() -> new NoSuchUserException(String.format("User id [%d] not found", id)));
        return String.format("User [%s] score: %d", user.getUsername(), user.getScore());
    }

    @GetMapping("/app/score/inc")
    public String addScoreCurrentUser(Principal principal) throws NeedAuthorisationException {
        if (principal == null) {
            throw new NeedAuthorisationException("You should login");
        }

        User user = userService.findOneByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User [%s] not found", principal.getName())));
        user.setScore(user.getScore() + 1);
        userService.saveOrUpdate(user);
        return String.format("User [%s] inc score", principal.getName());
    }

    @GetMapping("/app/score/dec")
    public String decScoreCurrentUser(Principal principal) throws NeedAuthorisationException {
        if (principal == null) {
            throw new NeedAuthorisationException("You should login");
        }

        User user = userService.findOneByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User [%s] not found", principal.getName())));
        user.setScore(user.getScore() - 1);
        userService.saveOrUpdate(user);
        return String.format("User [%s] dec score", principal.getName());
    }

    @GetMapping("/app/score/get/current")
    public String getScoreCurrentUser(Principal principal) throws NeedAuthorisationException {
        if (principal == null) {
            throw new NeedAuthorisationException("You should login");
        }

        User user = userService.findOneByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User [%s] not found", principal.getName())));
        return String.format("User [%s] score: %d", principal.getName(), user.getScore());
    }
}
