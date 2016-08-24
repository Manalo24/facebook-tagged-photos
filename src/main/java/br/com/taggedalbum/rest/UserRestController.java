package br.com.taggedalbum.rest;

import br.com.taggedalbum.model.Photo;
import br.com.taggedalbum.model.User;
import br.com.taggedalbum.services.UserService;
import com.restfb.exception.FacebookOAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            userService.saveUserData(params.get("accessToken"), Long.valueOf(params.get("facebookId")));
            return ResponseEntity.ok().build();
        } catch (FacebookOAuthException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @RequestMapping(value = "/{facebookId}",method = RequestMethod.GET)
    public ResponseEntity<?> findUser(@PathVariable Long facebookId) {
        User user = userService.findUserById(facebookId);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/{facebookId}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable Long facebookId) {
        userService.deleteUserById(facebookId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(value = "/{facebookId}/photos", method = RequestMethod.GET)
    public ResponseEntity<?> userPhotos(@PathVariable Long facebookId, Pageable pageRequest) {

        Slice<Photo> photos = userService.findPhotoByUserId(facebookId, pageRequest);

        return ResponseEntity.ok(photos);
    }

}
