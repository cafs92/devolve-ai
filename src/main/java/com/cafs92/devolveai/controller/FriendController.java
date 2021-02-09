package com.cafs92.devolveai.controller;

import com.cafs92.devolveai.model.Friend;
import com.cafs92.devolveai.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/amigos")
public class FriendController {
    @Autowired
    FriendRepository friendRepository;

    @GetMapping
    public List<Friend> getFriends(){
        return friendRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Friend> getFriendById(@PathVariable Long id){
        return friendRepository.findById(id).map(friend ->
                                                        ResponseEntity.ok().body(friend))
                                                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Friend addFriend(@RequestBody Friend friend){
        return friendRepository.save(friend);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Friend> updateFriend(@RequestBody Friend friend, @PathVariable Long id){
        return friendRepository.findById(id).map(f ->{
                                                        f.setName(friend.getName());
                                                        Friend updatedFriend = friendRepository.save(f);
                                                        return ResponseEntity.ok().body(updatedFriend);
                                                        })
                                                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFriend(@PathVariable Long id){
        return friendRepository.findById(id).map(friend ->{
                                                            friendRepository.deleteById(id);
                                                            return ResponseEntity.ok().build();
                                                            })
                                                .orElse(ResponseEntity.notFound().build());
    }
}
