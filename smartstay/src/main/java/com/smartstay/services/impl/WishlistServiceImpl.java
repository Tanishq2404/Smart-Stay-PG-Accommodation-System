package com.smartstay.services.impl;
import com.smartstay.services.WishlistService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartstay.dto.WishlistRequest;
import com.smartstay.dto.WishlistResponse;
import com.smartstay.entity.Pg;
import com.smartstay.entity.User;
import com.smartstay.entity.Wishlist;
import com.smartstay.repository.PgRepository;
import com.smartstay.repository.UserRepository;
import com.smartstay.repository.WishlistRepository;
import com.smartstay.services.WishlistService;


@Service
public class WishlistServiceImpl implements WishlistService {


    private final WishlistRepository wishlistRepository;

    private final UserRepository userRepository;

    private final PgRepository pgRepository;



    // Constructor Injection
    public WishlistServiceImpl(WishlistRepository wishlistRepository,
                               UserRepository userRepository,
                               PgRepository pgRepository) {

        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.pgRepository = pgRepository;
    }




    // Add PG to Wishlist
    @Override
    public WishlistResponse addToWishlist(WishlistRequest request) {


        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                    new RuntimeException("User not found"));



        Pg pg = pgRepository.findById(request.getPgId())
                .orElseThrow(() ->
                    new RuntimeException("PG not found"));



        Optional<Wishlist> existingWishlist =
                wishlistRepository.findByUserAndPg(user, pg);



        if(existingWishlist.isPresent()) {

            throw new RuntimeException(
                    "PG already added to wishlist"
            );
        }



        Wishlist wishlist = new Wishlist();

        wishlist.setUser(user);
        wishlist.setPg(pg);



        Wishlist savedWishlist =
                wishlistRepository.save(wishlist);



        return convertToResponse(savedWishlist);
    }





    // Get Wishlist By User
    @Override
    public List<WishlistResponse> getUserWishlist(Long userId) {


        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                    new RuntimeException("User not found"));



        return wishlistRepository.findByUser(user)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

    }





    // Remove Wishlist
    @Override
    @Transactional
    public String removeWishlist(Long userId, Long pgId) {


        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                    new RuntimeException("User not found"));



        Pg pg = pgRepository.findById(pgId)
                .orElseThrow(() ->
                    new RuntimeException("PG not found"));



        Optional<Wishlist> wishlist =
                wishlistRepository.findByUserAndPg(user, pg);



        if(wishlist.isEmpty()) {

            throw new RuntimeException(
                    "Wishlist entry not found"
            );
        }



        wishlistRepository.deleteByUserAndPg(user, pg);



        return "PG removed from wishlist successfully";
    }





    // Check Wishlist
    @Override
    public boolean isWishlisted(Long userId, Long pgId) {


        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                    new RuntimeException("User not found"));



        Pg pg = pgRepository.findById(pgId)
                .orElseThrow(() ->
                    new RuntimeException("PG not found"));



        return wishlistRepository
                .findByUserAndPg(user, pg)
                .isPresent();
    }





    // Clear Wishlist
    @Override
    @Transactional
    public void clearWishlist(Long userId) {


        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                    new RuntimeException("User not found"));



        wishlistRepository.deleteByUser(user);

    }





    // Entity to DTO Conversion
    private WishlistResponse convertToResponse(
            Wishlist wishlist) {


        Pg pg = wishlist.getPg();



        return new WishlistResponse(

                wishlist.getWishlistId(),

                pg.getPgId(),

                pg.getPgName(),

                pg.getCity(),

                pg.getRentStarting(),

                pg.getRating()

        );
    }

}