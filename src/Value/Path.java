/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package value;

/**
 *
 * @author user
 */
public class Path {

    public static final String ROOT = "/Resources";

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

            public class BackButton {

                public static final String BACK_BUTTON = BUTTON + "/BackButton";
                public static final String BACK_BUTTON_ROOT = BACK_BUTTON + "/Button_Back_Root.png";
                public static final String BACK_BUTTON_CLICK = BACK_BUTTON + "/Button_Back_Click.png";
                public static final String BACK_BUTTON_HOVER = BACK_BUTTON + "/Button_Back_Hover.png";
            }

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
        public class Musics{
            public static final String MUSICS = AUDIOS + "/Musics";
            public static final String TEST = MUSICS + "/test.wav";
        }
 
        public class Sounds{
            public static final String SOUNDS = AUDIOS + "/Sounds";
            
            public class Attack{
                public static final String ATTACK = SOUNDS + "/Attack";
                public static final String TOWER_ATTCK_SOUND = ATTACK + "/laser5.wav";
                
            }
        }
    }

}
