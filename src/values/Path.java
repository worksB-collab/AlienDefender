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
        public static final String BOOM1 = GAMEOBJECT + "/Boom1.png";
        public static final String BOOM2 = GAMEOBJECT + "/Boom2.png";
        public static final String UPGRADE = GAMEOBJECT + "/Upgrade.png";

        public class Button {

            public static final String BUTTON = IMAGES + "/Button";
            public static final String BACK_BUTTON = BUTTON + "/BackButton.png";

        }

        public class Scene {

            public static final String SCENE = IMAGES + "/Scene";
            public static final String MENU_SCENE = SCENE + "/MenuScene.png";
            public static final String NEW_GAME_SCENE = SCENE + "/NewGameScene.png";
            public static final String LOAD_DATA_SCENE = SCENE + "/LoadDataScene.png";
            public static final String PREPARE_SCENE = SCENE + "/PrepareScene.png";
            public static final String RANK_SCENE = SCENE + "/RankScene.png";
        }
    }

    public class Audios {

        public static final String AUDIOS = ROOT + "/Audios";

        public class Musics {

            public static final String MUSICS = AUDIOS + "/Musics";
            public static final String TEST = MUSICS + "/test.wav";
            public static final String MENU = MUSICS + "/Menu/this.LOOP5_140BPM.wav";
            public static final String BETWEENSCENES = MUSICS + "/Between Scenes/ this.Generdyn - INSTLoops - 05.wav";
            public static final String INTHEGAME = MUSICS + "/In The Game/in the game.wav";
            public static final String ENDING = MUSICS + "/Ending/this.LOOP35_100BPM.wav";
            public static final String WIN = MUSICS + "/Win";
            public static final String WIN1 = WIN + "/this.Generdyn - HITS - 01.wav";
            public static final String WIN2 = WIN + "/this2.99sounds loop_026.wav";
            public static final String LOSE = MUSICS + "/Lose";
            public static final String LOSE1 = LOSE + "/this1.Generdyn - ATMOS - 11.wav";
            public static final String LOSE2 = LOSE + "/this2.Generdyn - ATMOS - 10.wav";

        }

        public class Sounds {

            public static final String SOUNDS = AUDIOS + "/Sounds";

            public class Attack {

                public static final String ATTACK = SOUNDS + "/Attack";
                public static final String TOWER_ATTCK_SOUND = ATTACK + "/laser5.wav";

            }
        }
    }

}
