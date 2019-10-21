/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value;

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
        public static final String TOWER1 = GAMEOBJECT + "/Tower1.png";
        public static final String BULLET1 = GAMEOBJECT + "/Bullet1.png";


        public class Button {

            public static final String BUTTON = IMAGES + "/Button";

            public class StartButton {

                public static final String START_BUTTON = BUTTON + "/StartButton";
                public static final String START_BUTTON_ROOT = START_BUTTON + "/Button_Start_Root.png";
                public static final String START_BUTTON_CLICK = START_BUTTON + "/Button_Start_Click.png";
                public static final String START_BUTTON_HOVER = START_BUTTON + "/Button_Start_Hover.png";
            }

            public class NewGameButton {

                public static final String NEW_GAME_BUTTON = BUTTON + "/NewGameButton";
                public static final String NEW_GAME_BUTTON_ROOT = NEW_GAME_BUTTON + "/Button_NewGame_Root.png";
                public static final String NEW_GAME_BUTTON_CLICK = NEW_GAME_BUTTON + "/Button_NewGame_Click.png";
                public static final String NEW_GAME_BUTTON_HOVER = NEW_GAME_BUTTON + "/Button_NewGame_Hover.png";
            }

            public class LoadGameButton {

                public static final String LOAD_GAME_BUTTON = BUTTON + "/LoadGameButton";
                public static final String LOAD_GAME_BUTTON_ROOT = LOAD_GAME_BUTTON + "/Button_LoadGame_Root.png";
                public static final String LOAD_GAME_BUTTON_CLICK = LOAD_GAME_BUTTON + "/Button_LoadGame_Click.png";
                public static final String LOAD_GAME_BUTTON_HOVER = LOAD_GAME_BUTTON + "/Button_LoadGame_Hover.png";
            }

            public class RankButton {

                public static final String RANK_BUTTON = BUTTON + "/RankButton";
                public static final String RANK_BUTTON_ROOT = RANK_BUTTON + "/Button_Rank_Root.png";
                public static final String RANK_BUTTON_CLICK = RANK_BUTTON + "/Button_Rank_Click.png";
                public static final String RANK_BUTTON_HOVER = RANK_BUTTON + "/Button_Rank_Hover.png";

            }

            public class ExitButton {

                public static final String EXIT_BUTTON = BUTTON + "/ExitButton";
                public static final String EXIT_BUTTON_ROOT = EXIT_BUTTON + "/Button_Exit_Root.png";
                public static final String EXIT_BUTTON_CLICK = EXIT_BUTTON + "/Button_Exit_Click.png";
                public static final String EXIT_BUTTON_HOVER = EXIT_BUTTON + "/Button_Exit_Hover.png";

            }

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

    public class Audio {

        public static final String AUDIO = ROOT + "Audio";
    }

}
