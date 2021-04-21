package com.example.walletApp_demo.io.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "userTBL") //nanti jadi bgni -> usertbl
@SequenceGenerator(name = "seqUSR", initialValue = 100, allocationSize = 10)
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 8629810098227939460L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUSR")
    private long id;

    @Column(nullable = false)   //artinya kolum ini g bleh null
    private String userid;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)", length = 50) //collumdefinition untuk tipedata //length
    private String username;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
