package org.hospital.hospitalbookup.ui.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import org.hospital.hospitalbookup.ui.models.User;


@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM users WHERE email = :email AND password = :password AND role = :role")
    User login(String email, String password, String role);

    @Query("SELECT * FROM users WHERE email = :email AND role = :role")
    User findByEmailAndRole(String email, String role);

}
