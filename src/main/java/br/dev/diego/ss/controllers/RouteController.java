package br.dev.diego.ss.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteController {

    @GetMapping(value = "/public")
    public ResponseEntity<String> publicRoute() {
        return ResponseEntity.ok("<h1 style=color:green;>Public Route 🔓</h1>");
    }

    @GetMapping(value = "/cookie")
    public ResponseEntity<String> cookie(@AuthenticationPrincipal OidcUser user) {
        return ResponseEntity.ok(
                String.format("""
                        <h1 style=color:red;>Private Route OAuth2 🔒</h1>
                        <h3>User: %s</h3>
                        <h3>Email attribute: %s</h3>
                        <h3>Authorities: %s</h3>
                        <h3>JWT: %s</h3>""", user, user.getAttribute("email"), user.getAuthorities(), user.getIdToken().getTokenValue()
                ));
    }

    @GetMapping(value = "/jwt")
    public ResponseEntity<String> jwt(@AuthenticationPrincipal Jwt user) {
        return ResponseEntity.ok(
                String.format("""
                        <h1 style=color:red;>Private Route JWT 🔒</h1>
                        <h3>User: %s</h3>
                        <h3>Email attribute: %s</h3>
                        <h3>Authorities: %s</h3>
                        <h3>JWT: %s</h3>""", user.getClaim("name"), user.getClaim("email"), user.getClaims(), user.getTokenValue()
                ));

    }

}
