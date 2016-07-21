package br.com.taggedalbum.rest;

import br.com.taggedalbum.exception.ResourceNotFoundException;
import br.com.taggedalbum.model.Photo;
import br.com.taggedalbum.model.User;
import br.com.taggedalbum.services.UserService;
import com.restfb.exception.FacebookOAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by rafaelperetta on 17/07/16.
 */

@RestController
@RequestMapping("/users")
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUserData(@RequestBody Map<String, String> params) {
        try {
            userService.saveUserData(params.get("accessToken"));
            return ResponseEntity.ok().build();
        } catch (FacebookOAuthException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @RequestMapping(value = "/{facebookId}",method = RequestMethod.GET)
    public ResponseEntity<?> findUser(@PathVariable Long facebookId) throws ResourceNotFoundException {
        User user = userService.findUserById(facebookId);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/{facebookId}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable Long facebookId) throws ResourceNotFoundException {
        userService.deleteUserById(facebookId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(value = "/{facebookId}/photos", method = RequestMethod.GET)
    public ResponseEntity<?> userPhotos(@PathVariable Long facebookId,
                                        @RequestParam(value = "orderByReactions", required = false) boolean sorted,
                                        @RequestParam(value = "direction", required = false, defaultValue = "1") int direction) {

        List<Photo> photos = userService.findPhotoByUserId(facebookId, sorted, direction);

        return ResponseEntity.ok(photos);
    }

}
