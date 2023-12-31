package com.estevanhernandes.workshopmongodb.services;

import com.estevanhernandes.workshopmongodb.domain.User;
import com.estevanhernandes.workshopmongodb.dto.UserDTO;
import com.estevanhernandes.workshopmongodb.repositories.UserRepository;
import com.estevanhernandes.workshopmongodb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = repository.findById(id);

        return user.orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }

    public User insert(User user) {
        return repository.insert(user);
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    public User update(User newUser) {
        Optional<User> user = repository.findById(newUser.getId());

        if (user.isEmpty()) {
            throw new ObjectNotFoundException("User not found");
        }

        updateData(user.get(), newUser);

        return repository.save(user.get());
    }

    private void updateData(User user, User newUser) {
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
    }


    public User fromDTO(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }
}
