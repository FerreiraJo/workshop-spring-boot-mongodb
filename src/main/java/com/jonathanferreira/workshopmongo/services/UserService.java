package com.jonathanferreira.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonathanferreira.workshopmongo.domain.User;
import com.jonathanferreira.workshopmongo.repository.UserRepository;

@Service
public class UserService {
		
		@Autowired /*Estancia automaticamente o serviço */
		private UserRepository repo; /* injeção de dependencia, o service acessa o repository */
		
		public List<User> findAll(){
			return repo.findAll(); /* Retorna todos os objetos do banco de dados */
		}
}
