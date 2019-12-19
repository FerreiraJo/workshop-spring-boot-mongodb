package com.jonathanferreira.workshopmongo.services;

import java.util.Date;
import java.util.List;
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
		
		public List<Post> findByTitle(String text)	{
			return repo.searchTitle(text);
		}
		
		public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
			maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 100);
			return repo.fullSearch(text, minDate, maxDate);
		}
		
}
