package com.estevanhernandes.workshopmongodb.services;

import com.estevanhernandes.workshopmongodb.domain.Post;
import com.estevanhernandes.workshopmongodb.repositories.PostRepository;
import com.estevanhernandes.workshopmongodb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public List<Post> findAll() {
        return repository.findAll();
    }

    public Post findById(String id) {
        Optional<Post> Post = repository.findById(id);

        return Post.orElseThrow(() -> new ObjectNotFoundException("Post not found"));
    }
}
