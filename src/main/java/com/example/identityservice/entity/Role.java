package com.example.identityservice.entity;

import java.util.Set;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "permissions")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "roles")
public class Role {
    @Id
    String name;

    String description;

    @ManyToMany // role co nhieu permissions -> chi can khai bao ManyToMany o day va db se tu
    // sinh bang, kh can khai bao
    // trong permission ve ManyToMany voi Role nua

    /// Ban dau co the co truong hop bi loi foreign key, do viec jpa tu dong tao
    /// table va fk thi de loi -> drop table
    // xong lam lai. Neu kh thi phai lam chi tiet nhu o duoi
    // @JoinTable(
    // name = "roles_permissions",
    // joinColumns = @JoinColumn(name = "role_name", referencedColumnName = "name"),
    // inverseJoinColumns = @JoinColumn(name = "permissions_name",
    /// referencedColumnName = "name")
    // )
    Set<Permission> permissions;
}
