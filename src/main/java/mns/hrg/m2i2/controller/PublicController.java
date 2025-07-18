package mns.hrg.m2i2.controller;

import lombok.AllArgsConstructor;
import mns.hrg.m2i2.dao.entity.Ticket;
import mns.hrg.m2i2.dao.entity.User;
import mns.hrg.m2i2.model.Login;
import mns.hrg.m2i2.security.AppUserDetails;
import mns.hrg.m2i2.service.TicketService;
import mns.hrg.m2i2.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class PublicController {

    private TicketService ticketService;

    @Autowired
    protected AuthenticationProvider authenticationProvider;

    @Autowired
    protected JwtUtils jwtUtils;

    @GetMapping("/api/public/tickets/unresolved")
    public List<Ticket> getAllUnResolved() {
        return ticketService.getAllBySolved(false);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login user){
        try {
            AppUserDetails userDetails = (AppUserDetails) authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getPsoeudo(), user.getPassword()
                    )).getPrincipal();

            String jwt = jwtUtils.generateToken(userDetails);

            return ResponseEntity.ok(jwt);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
