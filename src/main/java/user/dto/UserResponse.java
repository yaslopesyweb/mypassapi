package user.dto;

import user.User;

public record UserResponse (
        String username,
        String password
) {
    public static UserResponse from (User user) {
        return new UserResponse(
                user.getUsername(),
                user.getPassword()
        );
    }
}
