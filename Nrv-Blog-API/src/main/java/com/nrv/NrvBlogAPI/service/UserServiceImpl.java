package com.nrv.NrvBlogAPI.service;

import com.nrv.NrvBlogAPI.custom_exception.ResourceNotFoundException;
import com.nrv.NrvBlogAPI.dto.APIResponse;
import com.nrv.NrvBlogAPI.dto.userDTO.*;
import com.nrv.NrvBlogAPI.entities.Role;
import com.nrv.NrvBlogAPI.entities.User;
import com.nrv.NrvBlogAPI.log.UserLogMessages;
import com.nrv.NrvBlogAPI.repository.BlogRepository;
import com.nrv.NrvBlogAPI.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    ModelMapper mapper;

/*    @Autowired
    private PasswordEncoder encoder;*/

/*    @Autowired
    private AuthenticationManager mgr;*/

    @Override
    public UserDTO getUserWithBlogs(String userId) {
        logger.trace(UserLogMessages.USER_GET.getMessage(), userId);
        User user = userRepository
                .findByUserIdWithBlogs(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with " + userId + " not found"));

        return new UserDTO(
                user.getUserId(),
                user.getUserName(),
                user.getRole(),
                user.getUserCreationDate(),
                user.getBlogList()
                        .stream()
                        .map(blog -> mapper.map(blog, UserBlogDTO.class))
                        .toList()
        );
    }

    @Override
    public LoginResponseDTO userLogin(LoginUserDTO user) {
/*
        String jwtToken;
        try {
            // Perform authentication
            Authentication principal = mgr.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

            // Generate JWT token if authentication is successful
            jwtToken = utils.generateJwtToken(principal);

            // Log the successful login
            logger.trace(UserLogMessages.USER_LOGIN.getMessage(), user.getUserId());

        } catch (AuthenticationException e) {
            // Handle authentication failure
            logger.error("Authentication failed for user: {}", user.getEmail(), e);
            throw new InvalidCredentialsException("Invalid email or password.");
        }

        // Return successful response
        return new LoginResponseDTO(user.getUserId(), jwtToken, "User Logged In Successfully");
*/
        return null;
    }

    @Override
    public UserRegisteredDTO userRegister(RegisterUserDTO userRegistration) {
        User newUser = new User(
                userRegistration.getUserId(),
                userRegistration.getUserName(),
                Role.ROLE_USER, // There's only One admin so by default any other are ROLE_USER
                //encoder.encode(
                userRegistration.getPassword(),
                //),
                LocalDate.now(),
                List.of()
        );

        return mapper.map(newUser, UserRegisteredDTO.class);
    }

    @Override
    public APIResponse userUpdate(UpdateUserDTO updatedUser) {
        User user = userRepository
                .findById(updatedUser.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User with That ID Not found with that ID"));
/*        if (!encoder.matches(updatedUser.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Old password does not match");
        }
        user.setPassword(encoder.encode(updatedUser.getPassword()));*/
        userRepository.save(user);
        return new APIResponse("User Password Updated");
    }

    @Override
    public List<UserDTO> getListOfUsers() {
        // Fetch all users with their associated blogs in one query
        List<User> userList = userRepository.findAllWithBlogs();
        logger.trace(UserLogMessages.USER_GET_LIST.getMessage());
        // Convert the list of users to a list of UserDTOs
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            userDTOList.add(new UserDTO(
                    user.getUserId(),
                    user.getUserName(),
                    user.getRole(),
                    user.getUserCreationDate(),
                    user.getBlogList()
                            .stream()
                            .map(blog -> mapper.map(blog, UserBlogDTO.class))
                            .toList()
            ));
        }
        return userDTOList;
    }

    @Override
    public DeleteUserResponseDto deleteAUser(String userId) {
        userRepository.deleteById(userId);
        logger.trace(UserLogMessages.BLOG_DELETED.getMessage(), userId);
        return new DeleteUserResponseDto("User with ID: " + userId + " Deleted");
    }
}
