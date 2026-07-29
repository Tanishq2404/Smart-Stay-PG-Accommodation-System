package com.smartstay.contoller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartstay.dto.WishlistRequest;
import com.smartstay.dto.WishlistResponse;
import com.smartstay.services.WishlistService;


@RestController
@RequestMapping("/api/wishlist")
@CrossOrigin(origins = "*")
public class WishlistController {


    private final WishlistService wishlistService;



    // Constructor Injection
    public WishlistController(WishlistService wishlistService) {

        this.wishlistService = wishlistService;
    }





    // Add PG to Wishlist
    @PostMapping
    public ResponseEntity<WishlistResponse> addToWishlist(
            @RequestBody WishlistRequest request) {


        WishlistResponse response =
                wishlistService.addToWishlist(request);


        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }






    // Get Wishlist By User
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WishlistResponse>> getUserWishlist(
            @PathVariable Long userId) {


        List<WishlistResponse> wishlist =
                wishlistService.getUserWishlist(userId);


        return ResponseEntity.ok(wishlist);
    }







    // Remove PG from Wishlist
    @DeleteMapping("/{userId}/{pgId}")
    public ResponseEntity<String> removeWishlist(
            @PathVariable Long userId,
            @PathVariable Long pgId) {


        String response =
                wishlistService.removeWishlist(userId, pgId);


        return ResponseEntity.ok(response);
    }







    // Check Wishlist
    @GetMapping("/check")
    public ResponseEntity<Boolean> checkWishlist(
            @RequestParam Long userId,
            @RequestParam Long pgId) {


        boolean exists =
                wishlistService.isWishlisted(userId, pgId);


        return ResponseEntity.ok(exists);
    }







    // Clear User Wishlist
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> clearWishlist(
            @PathVariable Long userId) {


        wishlistService.clearWishlist(userId);


        return ResponseEntity.ok(
                "Wishlist cleared successfully"
        );
    }

}