package com.tutorial.game.JavaGame;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BasicEnemyTest {


    @Test
    void getBounds() {
        Handler handler = new Handler();
        BasicEnemy basicEnemy = Mockito.mock(BasicEnemy.class);

        basicEnemy.getBounds();


    }
}