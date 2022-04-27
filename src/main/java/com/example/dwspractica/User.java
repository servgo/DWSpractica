package com.example.dwspractica;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_usuario;

    private String nombre;
    private String password;

    @JsonIgnore
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "Orders",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    List<Game>juegosPedidos;

    public User(String nombre, String password){
        this.nombre=nombre;
        this.password= new BCryptPasswordEncoder(10).encode(password);
    }

    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Game> getJuegosPedidos() {
        return juegosPedidos;
    }

    public void setJuegosPedidos(List<Game> juegosPedidos) {
        this.juegosPedidos = juegosPedidos;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public float getPriceOrders(){
        float aux=0;
        for (Game g:this.juegosPedidos){
            aux+=g.getPrice();
        }
        return aux;
    }
    public void deleteOrder(Game game){
        this.juegosPedidos.remove(game);
    }
}
