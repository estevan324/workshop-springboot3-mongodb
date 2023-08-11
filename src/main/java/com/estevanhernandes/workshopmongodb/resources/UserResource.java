package com.estevanhernandes.workshopmongodb.resources;

import com.estevanhernandes.workshopmongodb.domain.Post;
import com.estevanhernandes.workshopmongodb.domain.User;
import com.estevanhernandes.workshopmongodb.dto.UserDTO;
import com.estevanhernandes.workshopmongodb.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        User user = service.findById(id);

        return ResponseEntity.ok().body(new UserDTO(user));
    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        User user = service.findById(id);

        return ResponseEntity.ok().body(user.getPosts());
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody  UserDTO userDTO) {
        User user = service.fromDTO(userDTO);

        user = service.insert(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Void> update(@RequestBody UserDTO userDTO, @PathVariable String id) {
        User user = service.fromDTO(userDTO);
        user.setId(id);
        user = service.update(user);

        return ResponseEntity.noContent().build();
    }
}
