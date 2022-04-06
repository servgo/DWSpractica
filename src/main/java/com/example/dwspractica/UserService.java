package com.example.dwspractica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    GameService gameService;

    public void addUser(User user){
        userRepository.save(user);
    }
    public void deleteUser(long n){
        userRepository.deleteById(n);
    }
    public User getUsers(long n){
        return userRepository.findById(n).orElse(null);
    }
    public Collection<User>getUsers(){
        return userRepository.findAll();
    }
    public void updateUser(long id, User user){
        if (userRepository.existsById(id)){
            user.setId_usuario(id);
            userRepository.save(user);
        }
    }
    public boolean containsUser(long id){
        return userRepository.existsById(id);
    }
    public void makeOrder(long u){

    }
    public void deleteOrder(long idGame){
        User u=userRepository.getById((long)1);
        u.deleteOrder(gameService.getGames(idGame));
        userRepository.save(u);
    }
}
