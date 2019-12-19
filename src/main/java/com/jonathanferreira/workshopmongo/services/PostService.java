package com.jonathanferreira.workshopmongo.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonathanferreira.workshopmongo.domain.Post;
import com.jonathanferreira.workshopmongo.repository.PostRepository;
import com.jonathanferreira.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
		
		@Autowired /*Estancia automaticamente o serviço */
		private PostRepository repo; /* injeção de dependencia, o service acessa o repository */
		
		public Post findById(String id) {
			try {
				Optional<Post> post = repo.findById(id);
				return post.get();
			}
			catch(NoSuchElementException e){
				throw new ObjectNotFoundException("Objeto nao encontrado");
			}
		}
		
}
