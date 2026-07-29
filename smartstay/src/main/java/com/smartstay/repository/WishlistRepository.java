package com.smartstay.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartstay.entity.User;
import com.smartstay.entity.Pg;
import com.smartstay.entity.Wishlist;


public interface WishlistRepository 
        extends JpaRepository<Wishlist, Long> {


    Optional<Wishlist> findByUserAndPg(User user, Pg pg);


    List<Wishlist> findByUser(User user);


    void deleteByUserAndPg(User user, Pg pg);


    void deleteByUser(User user);

}