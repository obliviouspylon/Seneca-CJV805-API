package com.example.CJV805_API.Users;

import com.example.CJV805_API.Exceptions.InvalidEmailFormatException;
import com.example.CJV805_API.Exceptions.MissingDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUser(String id, String email) {

        if (Objects.equals(email, "")) {
            throw new MissingDataException("Email");
        } else if (emailFormatCheck(email)) {
            throw new InvalidEmailFormatException();
        } else if (Objects.equals(id, "")) {
            throw new MissingDataException("ID");
        }

        Optional<User> user =userRepository.getUserByIDAndEmail(id,email);
        if (user.isEmpty()) {
            throw new IllegalStateException("User Not Found!");
        } else {
            return user;
        }
    }

    public User validateUser(User user){
        Optional<User> userByEmail = userRepository.getUserByEmail(user.getEmail());
        if (userByEmail.isEmpty()) {
            throw new IllegalStateException("Email Not Found!");
        } else {
            String hashedPassword = getHashedPassword(user.getPassword(),userByEmail.get().getSalt());
            if (Objects.equals(userByEmail.get().getPassword(), hashedPassword)){
                return userByEmail.get();
            } else {
                throw new IllegalStateException("Email and Password doesn't match!");
            }
        }
    }

    public boolean addUser(User user) {
        if (Objects.equals(user.getEmail(), "")) {
            throw new MissingDataException("Email");
        } else if (emailFormatCheck(user.getEmail())) {
            throw new InvalidEmailFormatException();
        } else if (Objects.equals(user.getPassword(), "")) {
            throw new MissingDataException("Password");
        } else if (Objects.equals(user.getFirstName(), "")) {
            throw new MissingDataException("First Name");
        } else if (Objects.equals(user.getLastName(), "")) {
            throw new MissingDataException("Last Name");
        }

        Optional<User> userByEmail = userRepository.getUserByEmail(user.getEmail());
        if (userByEmail.isPresent()) {
            throw new IllegalStateException("Email taken!");
        } else {
            List<String> hashedPassword = getHashedPassword(user.getPassword());
            user.setPassword(hashedPassword.get(0));
            user.setSalt(hashedPassword.get(1));
            userRepository.save(user);
            return true;
        }
    }

    private String getHashedPassword(String password, String salt) {
        return (toHexString(getSHA(password + salt)));
    }

    private List<String> getHashedPassword(String password) {
        String salt = toHexString(getSalt());
        return (List.of(toHexString(getSHA(password + salt)), salt));
    }

    //https://www.geeksforgeeks.org/sha-256-hash-in-java/
    private byte[] getSHA(String input) {
        // Static getInstance method is called with hashing SHA
        MessageDigest sha256 = null;
        try {
            sha256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // digest() method called to calculate message digest of an input and return array of byte
        assert sha256 != null;
        return sha256.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public String toHexString(byte[] hash) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    private byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
    //https://emailregex.com/
    private boolean emailFormatCheck(String email){
        String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+))";
        return (!email.matches(EMAIL_REGEX));
    }
}
