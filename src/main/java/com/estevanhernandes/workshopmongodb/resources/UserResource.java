package com.estevanhernandes.workshopmongodb.resources;

import com.estevanhernandes.workshopmongodb.domain.User;
import com.estevanhernandes.workshopmongodb.dto.UserDTO;
import com.estevanhernandes.workshopmongodb.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> usersList = service.findAll();
        List<UserDTO> listDTO = usersList.stream().map(UserDTO::new).toList();

        return ResponseEntity.ok().body(listDTO);
    }
}
