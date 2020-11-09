package org.example.gorkun.contacts.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @SequenceGenerator(name = "contact", sequenceName = "contact_sequences", allocationSize = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact")
    private long id;

    @NotBlank(message = "Поле обязательно для заполнения")
    @Length(max = 70, message = "Заголовок не может быть длинее 70 знаков")
    private String name;

    @Pattern(regexp="(^(\\+?(38)?0|0)[0-9]{9}$)", message = "Неправильный формат номера телефона")
    private String phone;

    private String photo;

    public Contact() {
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
