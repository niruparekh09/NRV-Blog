package com.nrv.NrvBlogAPI.service;

import com.nrv.NrvBlogAPI.custom_exception.AccessDeniedException;
import com.nrv.NrvBlogAPI.custom_exception.AlreadyExistsException;
import com.nrv.NrvBlogAPI.custom_exception.InvalidCredentialsException;
import com.nrv.NrvBlogAPI.custom_exception.ResourceNotFoundException;
import com.nrv.NrvBlogAPI.dto.APIResponse;
import com.nrv.NrvBlogAPI.dto.userDTO.*;
import com.nrv.NrvBlogAPI.entities.Role;
import com.nrv.NrvBlogAPI.entities.User;
import com.nrv.NrvBlogAPI.log.UserLogMessages;
import com.nrv.NrvBlogAPI.repository.BlogRepository;
import com.nrv.NrvBlogAPI.repository.UserRepository;
import com.nrv.NrvBlogAPI.security.JwtUtils;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Service class for User. Here are all the Business logic is present for various user function.
 *
 * @author Nirav Parekh
 * @see UserService
 * @since 1.0
 */
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

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager mgr;

    @Autowired
    private JwtUtils utils;

    @Override
    public UserDTO getUserWithBlogs(String userId) {
        logger.info(UserLogMessages.USER_GET.getMessage(), userId);
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
        String jwtToken;
        if(!checkUserName(user.getUserId())){
            throw new ResourceNotFoundException("User With This UserId Not Found");
        }
        try {
            Authentication principal = mgr
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    user.getUserId(),
                                    user.getPassword()
                            ));

            // Generate JWT token if authentication is successful
            jwtToken = utils.generateJwtToken(principal);

            logger.info(UserLogMessages.USER_LOGIN.getMessage(), user.getUserId());

        } catch (AuthenticationException e) {
            // Handle authentication failure
            logger.error("Authentication failed for user: {}", user.getUserId(), e);
            throw new InvalidCredentialsException("Invalid email or password.");
        }
        String role = userRepository
                .findById(user.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User with this Id not Found"))
                .getRole()
                .name();
        return new LoginResponseDTO(user.getUserId(), jwtToken, "User Logged In Successfully", role);
    }

    @Override
    public UserRegisteredDTO userRegister(RegisterUserDTO userRegistration) {
        if (checkUserName(userRegistration.getUserId())) {
            throw new AlreadyExistsException("UserId exists");
        }
        User newUser = new User(
                userRegistration.getUserId(),
                userRegistration.getUserName(),
                Role.ROLE_USER, // There's only One admin so by default any other are ROLE_USER
                encoder.encode(
                        userRegistration.getPassword()
                ),
                LocalDate.now(),
                List.of()
        );
        userRepository.save(newUser);
        logger.info(UserLogMessages.USER_REGISTER.getMessage(), newUser.getUserId());
        return mapper.map(newUser, UserRegisteredDTO.class);
    }

    @Override
    public APIResponse userUpdate(UpdateUserDTO updatedUser) {
        //Implement logic of the that user can only update their info.
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        if (currentUsername.equals(updatedUser.getUserId())) {
            User user = userRepository
                    .findById(updatedUser.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User with That ID Not found with that ID"));
            if (encoder.matches(updatedUser.getPassword(), user.getPassword())) {
                throw new InvalidCredentialsException("Old password and New Password Are Same");
            }
            user.setPassword(encoder.encode(updatedUser.getPassword()));
            userRepository.save(user);
            logger.info(UserLogMessages.USER_UPDATE.getMessage(), updatedUser.getUserId());
            return new APIResponse("User Password Updated");
        } else {
            return new APIResponse("You can't update the user");
        }
    }

    @Override
    public List<UserDTO> getListOfUsers() {
        // Fetch all users with their associated blogs in one query
        List<User> userList = userRepository.findAllWithBlogs();
        logger.info(UserLogMessages.USER_GET_LIST.getMessage());
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
        //Logic of Admin or User only
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User deleteUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with this ID not present"));
        if(deleteUser.getRole().name().equals("ROLE_ADMIN")){
            throw new AccessDeniedException("You can't delete Admin");
        }
        if (!deleteUser.getUserId().equals(currentUsername)) {
            if (!isAdmin()) {
                throw new RuntimeException("You are not authorized to delete this user");
            }
        }
        userRepository.delete(deleteUser);
        logger.info(UserLogMessages.BLOG_DELETED.getMessage(), userId);
        return new DeleteUserResponseDto("User with ID: " + userId + " Deleted");
    }

    private boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities()
                .stream()
                .anyMatch(grantedAuthority -> grantedAuthority
                        .getAuthority()
                        .equals("ROLE_ADMIN"));
    }


    private boolean checkUserName(String newUserId) {
        return userRepository
                .findUserIdList()
                .stream()
                .anyMatch(userId -> userId.equals(newUserId));
    }
}
