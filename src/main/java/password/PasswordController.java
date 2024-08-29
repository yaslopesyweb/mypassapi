package password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user.User;
import user.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/passwords")
public class PasswordController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getPasswords() {
        return userRepository.findAll();
    }
}
