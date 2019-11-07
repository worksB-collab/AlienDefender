/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package values;

/**
 *
 * @author user
 */
public class Path {

    public static final String ROOT = "/resources";

    public class Image {

        public static final String IMAGES = ROOT + "/Images";
        public static final String GAMEOBJECT = IMAGES + "/GameObject";
        public static final String ALIEN1 = GAMEOBJECT + "/Alien1.png";
        public static final String ALIEN2 = GAMEOBJECT + "/Alien2.png";
        public static final String TOWER1 = GAMEOBJECT + "/Tower1.png";
        public static final String TOWER2 = GAMEOBJECT + "/Tower2.png";
        public static final String BULLET1 = GAMEOBJECT + "/Bullet1.png";
        public static final String BULLET2 = GAMEOBJECT + "/Bullet2.png";
        public static final String BOOM1 = GAMEOBJECT + "/Boom1.png";
        public static final String BOOM2 = GAMEOBJECT + "/Boom2.png";
        public static final String UPGRADE = GAMEOBJECT + "/Upgrade.png";
        public static final String TROPHY = GAMEOBJECT + "/Trophy.png";
        public static final String DEAD = GAMEOBJECT + "/Dead.png";

        public class Button {

            public static final String BUTTON = IMAGES + "/Button";
            public static final String BACK_BUTTON = BUTTON + "/BackButton.png";

        }

        public class Scene {

            public static final String SCENE = IMAGES + "/Scene";
            public static final String MENU_SCENE = SCENE + "/MenuScene.png";
            public static final String PREPARE_SCENE = SCENE + "/PrepareScene.png";
            public static final String RANK_SCENE = SCENE + "/RankScene.png";
            public static final String GAMEOVER_SCENE = SCENE + "/GameOverScene.png";
            public static final String LOAD_DATA_SCENE = SCENE + "/LoadDataScene.png";
            public static final String END_SCENE = SCENE + "/EndScene.png";
            public static final String SPACE1 = SCENE + "/space.png";
            public static final String SPACE2 = SCENE + "/space2.png";
            public static final String MASK = SCENE + "/mask.png";
            public static final String HURT_MASK = SCENE + "/hurtMask.png";
        }

        public class Description {

            public static final String DESCRIPTION = IMAGES + "/Description";
            public static final String DESCRIPTION1 = DESCRIPTION + "/description1.png";
            public static final String DESCRIPTION2 = DESCRIPTION + "/description2.png";
            public static final String DESCRIPTION3 = DESCRIPTION + "/description3.png";
            public static final String DESCRIPTION4 = DESCRIPTION + "/description4.png";
        }
    }

    public class Audios {

        public static final String AUDIOS = ROOT + "/Audios";

        public class Musics {

            public static final String MUSICS = AUDIOS + "/Musics";
            public static final String TEST = MUSICS + "/test.wav";
            public static final String TEST1 = MUSICS + "/Ed Sheeran - South of the Border (feat. Camila Cabello & Cardi B) [Official].mp3";
            public static final String MENU = MUSICS + "/Menu/this.wav";
            public static final String BETWEENSCENES = MUSICS + "/Between_Scenes/this.wav";
            public static final String INTHEGAME = MUSICS + "/In_The_Game/in_the_game.wav";
            public static final String INTHEGAME1 = MUSICS + "/In_The_Game/1.wav";
            public static final String INTHEGAME2 = MUSICS + "/In_The_Game/2.wav";
            public static final String INTHEGAME3 = MUSICS + "/In_The_Game/3.wav";
            public static final String INTHEGAME4 = MUSICS + "/In_The_Game/4.wav";
            public static final String INTHEGAME5 = MUSICS + "/In_The_Game/5.wav";
            public static final String ENDING = MUSICS + "/Ending/this.wav";
            public static final String WIN = MUSICS + "/Win";
//            public static final String WIN1 = WIN + "/this.wav";
            public static final String WIN1 = "/resources/Audios/Musics/Win/this.wav";
            public static final String WIN2 = WIN + "/this2.wav";
            public static final String LOSE = MUSICS + "/Lose";
            public static final String LOSE1 = LOSE + "/this1.wav";
            public static final String LOSE2 = LOSE + "/this2.wav";
            public static final String LOSE3 = LOSE + "/this3.wav";
            public static final String LOSE4 = LOSE + "/this4.wav";

        }

        public class Sounds {

            public static final String SOUNDS = AUDIOS + "/Sounds";

            public class Attack {

                public static final String ATTACK = SOUNDS + "/Attack";
                public static final String TOWER_ATTCK_SOUND = ATTACK + "/laser5.wav";
                public static final String SHOT1 = ATTACK + "/shot1.wav";
                public static final String SHOT2 = ATTACK + "/shot2.wav";
                public static final String SHOT3 = ATTACK + "/shot3.wav";
                public static final String SHOT4 = ATTACK + "/shot4.wav";
                public static final String SHOT5 = ATTACK + "/shot5_lazer.wav";
                public static final String SHOT6 = ATTACK + "/kick.wav";

            }

            public class Effect {

                public static final String EFFECT = SOUNDS + "/Effect";
                public static final String UPGRADE = EFFECT + "/upgrade.wav";
                public static final String HURT = EFFECT + "/hurt.wav";
                public static final String BUY = EFFECT + "/buy.wav";
                public static final String BUYMP3 = EFFECT + "/buy.mp3";
                public static final String NOMONEY = EFFECT + "/noMoney.wav";
                public static final String KILL = EFFECT + "/kill.wav";
                public static final String KILLMP3 = EFFECT + "/kill.mp3";
            }
        }
    }

    public class Texts {

        public static final String TEXTS = ROOT + "/Text";
        public static final String TEXT = "/resources/Texts/text.txt";
        public static final String RANK = "/resources/Texts/rank.txt";
    }

}
