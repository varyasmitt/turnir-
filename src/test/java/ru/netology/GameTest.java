package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.NotRegisteredException;
import ru.netology.domain.Player;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game = new Game();
    private Player player1 = new Player(1, "first", 1);
    private Player player2 = new Player(2, "second", 2);
    private Player player3 = new Player(3, "third", 3);
    private Player player4 = new Player(4, "fourth", 2);

    @BeforeEach
    void shouldRegisterAllPlayers() {
        game.registerAll(List.of(player1, player2, player3, player4));
    }

    @Test
    void shouldFindAllRegisteredPlayers() {
        assertEquals(List.of(player1, player2, player3, player4), game.findAll());
    }

    @Test
    void shouldFindByNameWhenRegister() {
        assertEquals(player3, game.findByName("third"));
    }

    @Test
    void shouldReturnNullWhenNotRegister() {
        assertNull(game.findByName("fifth"));
    }

    @Test
    void shouldShowResultIfPlayer1Wins() {
        assertEquals(1, game.round("second", "first"));
    }

    @Test
    void shouldShowResultIfPlayer2Wins() {
        assertEquals(2, game.round("second", "third"));
    }

    @Test
    void shouldShowResultWhenDraw() {
        assertEquals(0, game.round("second", "fourth"));
    }

    @Test
    void shouldThrowExceptionWhenPlayer1Unregistered() {
        assertThrows(NotRegisteredException.class, () -> game.round("sixth", "fourth"));
    }

    @Test
    void shouldThrowExceptionWhenPlayer2Unregistered() {
        assertThrows(NotRegisteredException.class, () -> game.round("first", "seventh"));
    }

    @Test
    void shouldThrowExceptionWhenPlayersBothUnregistered() {
        assertThrows(NotRegisteredException.class, () -> game.round("eighth", "ninth"));
    }
}