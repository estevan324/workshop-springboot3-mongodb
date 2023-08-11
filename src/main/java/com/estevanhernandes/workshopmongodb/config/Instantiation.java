package com.estevanhernandes.workshopmongodb.config;

import com.estevanhernandes.workshopmongodb.domain.Post;
import com.estevanhernandes.workshopmongodb.domain.User;
import com.estevanhernandes.workshopmongodb.dto.AuthorDTO;
import com.estevanhernandes.workshopmongodb.dto.CommentDTO;
import com.estevanhernandes.workshopmongodb.repositories.PostRepository;
import com.estevanhernandes.workshopmongodb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, Instant.parse("2018-03-21T19:53:07Z"), "Partiu viagem", "\"Vou viajar para São Paulo. Abraços!", new AuthorDTO((maria)));
        Post post2 = new Post(null, Instant.parse("2018-03-23T09:30:59Z"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO((maria)));

        CommentDTO c1 = new CommentDTO("Boa viagem mano!", Instant.parse("2018-03-21T20:00:07Z"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite!", Instant.parse("2018-03-21T21:00:07Z"), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!",Instant.parse("2018-03-23T09:45:59Z"), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().add(c3);

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }
}
