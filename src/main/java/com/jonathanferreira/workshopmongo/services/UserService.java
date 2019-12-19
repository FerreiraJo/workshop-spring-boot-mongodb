package com.jonathanferreira.workshopmongo.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonathanferreira.workshopmongo.domain.User;
import com.jonathanferreira.workshopmongo.dto.UserDTO;
import com.jonathanferreira.workshopmongo.repository.UserRepository;
import com.jonathanferreira.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
		
		@Autowired /*Estancia automaticamente o serviço */
		private UserRepository repo; /* injeção de dependencia, o service acessa o repository */
		
		public List<User> findAll(){
			return repo.findAll(); /* Retorna todos os objetos do banco de dados */
		}
		
		public User findById(String id) {
			try {
				Optional<User> user = repo.findById(id);
				return user.get();
			}
			catch(NoSuchElementException e){
				throw new ObjectNotFoundException("Objeto nao encontrado");
			}
		}
		
		public User insert(User obj) {
			return repo.insert(obj);
		}
		
		public void delete(String id) {
			findById(id);
			repo.deleteById(id);
		}
		
		public User update(User obj) {
			Optional<User> newObj = repo.findById(obj.getId());
			updateData(newObj , obj);
			return repo.save(newObj.get());
		}
		
		private void updateData(Optional<User> newObj, User obj) {
			newObj.get().setName(obj.getName());
			newObj.get().setEmail(obj.getEmail());
			
		}

		public User fromDTO(UserDTO objDto) {
			return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
		}
}
