package com.example.dwspractica;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@NoArgsConstructor
@Entity
public class Order {
    @Id
    @GeneratedValue
    private long id;
    private long game_Id;
    private long id_usuario=1;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGame_Id() {
        return game_Id;
    }

    public void setGame_Id(long game_Id) {
        this.game_Id = game_Id;
    }

    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }
}
