package br.com.taggedalbum.rest;

import br.com.taggedalbum.model.Photo;
import br.com.taggedalbum.model.User;
import br.com.taggedalbum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rafaelperetta on 17/07/16.
 */

@RestController
@RequestMapping("/users")
public class UserRest {

    @Autowired
    private UserService userService;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Map<String, String>> users() {

        Map<String, String> user = new HashMap<>();
        user.put("name", "Rafael");

        return ResponseEntity.ok(user);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUserData(@RequestBody Map<String, String> params) {
        userService.saveUserData(params.get("accessToken"));
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{facebookId}",method = RequestMethod.GET)
    public ResponseEntity<?> findUser(@PathVariable Long facebookId) {
        User user = userService.findUserById(facebookId);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/{facebookId}/photos", method = RequestMethod.GET)
    public ResponseEntity<?> userPhotos(@PathVariable Long facebookId,
                                        @RequestParam(value = "orderByReactions", required = false) boolean sorted,
                                        @RequestParam(value = "direction", required = false, defaultValue = "1") int direction) {

        List<Photo> photos = userService.findPhotoByUserId(facebookId, sorted, direction);

        return ResponseEntity.ok(photos);
    }

}
