package br.com.taggedalbum.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rafaelperetta on 17/07/16.
 */

@RestController
@RequestMapping("/users")
public class UserRest {


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Map<String, String>> users() {

        Map<String, String> user = new HashMap<>();
        user.put("name", "Rafael");

        return ResponseEntity.ok(user);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUserData(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{facebookId}",method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUserData(@PathVariable Long facebookId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(value = "/{facebookId}/photos", method = RequestMethod.GET)
    public ResponseEntity<?> userPhotos(@PathVariable Long facebookId) {
        return ResponseEntity.ok(facebookId);
    }

}
