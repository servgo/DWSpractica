package com.example.dwspractica;

import lombok.NoArgsConstructor;

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

    @ManyToMany
    @JoinTable(name = "Orders",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    List<Game>juegosPedidos;

    public User(String nombre, String password){
        this.nombre=nombre;
        this.password=password;
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
}
