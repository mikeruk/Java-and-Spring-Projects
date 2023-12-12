package com.example.spring20232.model.entity;


import com.example.spring20232.model.enums.UserRoleEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class UserRoleEntity {


  private Long id;


  private UserRoleEnum role;


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getId() {
    return id;
  }

  public UserRoleEntity setId(Long id) {
    this.id = id;
    return this;
  }


  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  public UserRoleEnum getRole() {
    return role;
  }

  public UserRoleEntity setRole(UserRoleEnum role) {
    this.role = role;
    return this;
  }

  @Override
  public String toString() {
    return "UserRoleEntity{" +
        "id=" + id +
        ", role=" + role +
        '}';
  }
}
