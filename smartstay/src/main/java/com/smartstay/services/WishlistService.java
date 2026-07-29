package com.smartstay.services;

import java.util.List;

import com.smartstay.dto.WishlistRequest;
import com.smartstay.dto.WishlistResponse;


public interface WishlistService {


    WishlistResponse addToWishlist(WishlistRequest request);


    List<WishlistResponse> getUserWishlist(Long userId);


    String removeWishlist(Long userId, Long pgId);


    boolean isWishlisted(Long userId, Long pgId);


    void clearWishlist(Long userId);

}