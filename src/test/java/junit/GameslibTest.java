package junit;

import com.gui.api_gameslib.entities.Games;
import com.gui.api_gameslib.entities.Platforms;
import com.gui.api_gameslib.entities.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;


public class GameslibTest {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private Games createGame() {
        Games game = new Games();
        game.setId(1);
        game.setName("Call of Juarez");
        game.setSlug("call-of-juarez");
        game.setDescription("Testing the game in junit");
        game.setRating(80);
        game.setReleased("15/09/2006");
        game.setBackground_image("https://example.com/background_image.jpg");
        game.setImage_logo("https://example.com/image_logo.jpg");
        return game;
    }

    private Platforms createPlatforms() {
        Platforms pc = new Platforms();
        pc.setId(1);
        pc.setName("PC");
        pc.setSlug("pc");
        return pc;
    }

    private Users createUser() {
        Users user = new Users();
        user.setId(1);
        user.setUsername("testusername");
        user.setEmail("testemail@gmail.com");
        user.setName("Test User");
        String encryptedPassword = passwordEncoder.encode("testpassword");
        user.setPassword(encryptedPassword);
        return user;
    }

    private Games createGameWithPlatform() {
        Games game = createGame();
        Platforms platform = createPlatforms();
        Set<Platforms> platformsSet = new HashSet<>();
        platformsSet.add(platform);
        game.setPlatforms(platformsSet);
        return game;
    }

    @Test
    public void GameTest() {
        Games gametest = createGame();
        Assertions.assertNotNull(gametest);
        Assertions.assertEquals("Call of Juarez", gametest.getName());
        Assertions.assertEquals("call-of-juarez", gametest.getSlug());
        Assertions.assertEquals("Testing the game in junit", gametest.getDescription());
        Assertions.assertEquals(80, gametest.getRating());
        Assertions.assertEquals("15/09/2006", gametest.getReleased());
        Assertions.assertEquals("https://example.com/background_image.jpg", gametest.getBackground_image());
        Assertions.assertEquals("https://example.com/image_logo.jpg", gametest.getImage_logo());
        System.out.println(gametest);
    }

    @Test
    public void addingSubModelstoGame() {
        Games gametest = createGameWithPlatform();
        Platforms platformtest = createPlatforms();
        Set<Platforms> platformsSet = new HashSet<>();
        platformsSet.add(platformtest);
        gametest.setPlatforms(platformsSet);
        Assertions.assertEquals(1, gametest.getPlatforms().size());
        Assertions.assertTrue(gametest.getPlatforms().contains(platformtest));
        System.out.println(gametest);
    }

    @Test
    public void creatingUser() {
        Users users = createUser();
        Assertions.assertNotNull(users);
        Assertions.assertEquals("testusername", users.getUsername());
        Assertions.assertEquals("testemail@gmail.com", users.getEmail());
        Assertions.assertEquals("Test User", users.getName());
        Assertions.assertTrue(users.getPassword().startsWith("$2a$"));
        Assertions.assertTrue(passwordEncoder.matches("testpassword", users.getPassword()));
        System.out.println(users);
    }

    @Test
    public void AddingGamestoUser() {
        Users usertest = createUser();
        Games gametest = createGameWithPlatform();
        Set<Games> wishlist = new HashSet<>();
        wishlist.add(gametest);
        usertest.setWishlistGames(wishlist);
        Assertions.assertEquals(1, usertest.getWishlistGames().size());
        Assertions.assertTrue(usertest.getWishlistGames().contains(gametest));
        System.out.println(usertest);
    }
}
