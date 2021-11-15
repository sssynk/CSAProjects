package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.GhostControl;
import com.jme3.font.BitmapText;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.math.ColorRGBA;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.PointLight;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Spatial;
import com.jme3.util.SkyFactory;
import com.simsilica.lemur.Container;
import com.jme3.system.AppSettings;
import java.util.Random;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.effects.EffectEventId;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;


public class Main extends SimpleApplication {
    
    
    // init vars for all of the objects (player, collisions, walls)
    public static GhostControl ghost;
    public static Spatial plr;
    public static Spatial cloud;
    public static Geometry left1;
    public static Geometry right1;
    public static Geometry left2;
    public static Geometry right2;
    public static Geometry left3;
    public static Geometry right3;
    public static Geometry left4;
    public static Geometry right4;
    public static Geometry left5;
    public static Geometry right5;
    public static Geometry left6;
    public static Geometry right6;
    public static Geometry left7;
    public static Geometry right7;
    
    // init gui
    public static Screen cs;
    
    // set the movement of bird
    public static float currentmvmnt = 0.002f;
    
    // create UI
    private Nifty nifty;
    
    // initalize physics
    public static  BulletAppState physicsspace = new BulletAppState();
    
    // create a rand num generator
    public static Random r = new Random();
    
    // vars for pipes
    public static Geometry leftpipe;
    public static Geometry rightpipe;
    
    // vars for more ui elements
    public static Container window;
    public static BitmapText score;
    
    // when playing or dead
    public static Boolean running = false;
    public static Boolean dead = false;
        
    // starting velocity
    public static float velocity = 0.000001f;
    
    // x pos and y pos of bird
    public static int xpxl = 0;
    public static int ypxl = 0;
    
    // score
    public static int pipes = -1;

    // app settings var
    public static AppSettings appsettings;

    public static void main(String[] args) {
        // 1280*800 px
        // initalize app and set default settings
        Main app = new Main();
        appsettings = new AppSettings(true);
        appsettings.setTitle("Flappy Fall Alpha");
        appsettings.setSettingsDialogImage("Textures/flappyfallsplashjpgF.jpg");
        appsettings.setResolution(1280, 800);
        app.setSettings( appsettings );
        app.start();
        app.setDisplayFps(false);   
        app.setDisplayStatView(false);
        app.setShowSettings(false);
    }

    @Override
    public void simpleInitApp() {
        // initalize all parts of the scene
        initScene();
        initCam();
        initLighting();
        initSettings();
        initCtrl();
        initGUI();
    }

    @Override
    public void simpleUpdate(float tpf) { // occurs every frame, used to update the bird
        if(running == true){  // check to make sure its running
            
        Vector3f pos = plr.getLocalTranslation();
        plr.setLocalTranslation(pos.x, pos.y-velocity, pos.z); // get/check players velocity
        velocity = velocity + 0.000002f; // set the new velocity

        
        // get all of the wall positions
        Vector3f l1p = left1.getLocalTranslation();
        Vector3f r1p = right1.getLocalTranslation();
        Vector3f l2p = left2.getLocalTranslation();
        Vector3f r2p = right2.getLocalTranslation();
        Vector3f l3p = left3.getLocalTranslation();
        Vector3f r3p = right3.getLocalTranslation();
        Vector3f l4p = left4.getLocalTranslation();
        Vector3f r4p = right4.getLocalTranslation();
        Vector3f l5p = left5.getLocalTranslation();
        Vector3f r5p = right5.getLocalTranslation();
        Vector3f l6p = left6.getLocalTranslation();
        Vector3f r6p = right6.getLocalTranslation();
        Vector3f l7p = left7.getLocalTranslation();
        Vector3f r7p = right7.getLocalTranslation();
        
        // get pipes positions
        Vector3f leftt = leftpipe.getLocalTranslation();
        Vector3f rightt = rightpipe.getLocalTranslation();
        
        
        // check to see if any of the walls are passed the reset point. if they are, set back to bottom and continue moving up
        if(l1p.y > 9f) {
            left1.setLocalTranslation(l1p.x, -9, l1p.z);
        }
        if(l2p.y > 9f) {
            left2.setLocalTranslation(l2p.x, -9, l2p.z);
        }
        if(l3p.y > 9f) {
            left3.setLocalTranslation(l3p.x, -9, l3p.z);
        }
        if(l4p.y > 9f) {
            left4.setLocalTranslation(l4p.x, -9, l4p.z);
        }
        if(l5p.y > 9f) {
            left5.setLocalTranslation(l5p.x, -9, l5p.z);
        }
        if(l6p.y > 9f) {
            left6.setLocalTranslation(l6p.x, -9, l6p.z);
        }
        if(l7p.y > 9f) {
            left7.setLocalTranslation(l7p.x, -9, l7p.z);
        }
        
        
        if(r1p.y > 9f) {
            right1.setLocalTranslation(r1p.x, -9, r1p.z);
        }
        if(r2p.y > 9f) {
            right2.setLocalTranslation(r2p.x, -9, r2p.z);
        }
        if(r3p.y > 9f) {
            right3.setLocalTranslation(r3p.x, -9, r3p.z);
        }
        if(r4p.y > 9f) {
            right4.setLocalTranslation(r4p.x, -9, r4p.z);
        }
        if(r5p.y > 9f) {
            right5.setLocalTranslation(r5p.x, -9, r5p.z);
        }
        if(r6p.y > 9f) {
            right6.setLocalTranslation(r6p.x, -9, r6p.z);
        }
        if(r7p.y > 9f) {
            right7.setLocalTranslation(r7p.x, -9, r7p.z);
        }
        
        // reset pos of clouds if too high
        if(cloud.getLocalTranslation().y > 30f) {
            cloud.setLocalTranslation(cloud.getLocalTranslation().x, -30, cloud.getLocalTranslation().z);
        }
        
        // move clouds up
        cloud.setLocalTranslation(cloud.getLocalTranslation().x, cloud.getLocalTranslation().y + (currentmvmnt/2), cloud.getLocalTranslation().z);
        
        // move walls up to simulate falling
        left1.setLocalTranslation(l1p.x, l1p.y + currentmvmnt, l1p.z);
        right1.setLocalTranslation(r1p.x, r1p.y + currentmvmnt, r1p.z);
        left2.setLocalTranslation(l2p.x, l2p.y + currentmvmnt, l2p.z);
        right2.setLocalTranslation(r2p.x, r2p.y + currentmvmnt, r2p.z);
        left3.setLocalTranslation(l3p.x, l3p.y + currentmvmnt, l3p.z);
        right3.setLocalTranslation(r3p.x, r3p.y + currentmvmnt, r3p.z);
        left4.setLocalTranslation(l4p.x, l4p.y + currentmvmnt, l4p.z);
        right4.setLocalTranslation(r4p.x, r4p.y + currentmvmnt, r4p.z);
        left5.setLocalTranslation(l5p.x, l5p.y + currentmvmnt, l5p.z);
        right5.setLocalTranslation(r5p.x, r5p.y + currentmvmnt, r5p.z);
        left6.setLocalTranslation(l6p.x, l6p.y + currentmvmnt, l6p.z);
        right6.setLocalTranslation(r6p.x, r6p.y + currentmvmnt, r6p.z);
        left7.setLocalTranslation(l7p.x, l7p.y + currentmvmnt, l7p.z);
        right7.setLocalTranslation(r7p.x, r7p.y + currentmvmnt, r7p.z);
        
        int result = 0;
        int dist = 1;
                
        // check to see if the pipes are past the y height, if they are, set back to bottom and choose a random x value to set to 
        if(leftt.y < 9) {
            leftpipe.setLocalTranslation(leftt.x, leftt.y + currentmvmnt, leftt.z); // moves pipe up
        } else {
            dist = r.nextInt(2-1) +1;
            result = r.nextInt(3-(-3)) -3;
            
            leftpipe.setLocalTranslation((result-(4+dist)), -9, leftt.z); // sets random x value
        }
        
        // do the same with the right pipe
        if(rightt.y < 9) {
            rightpipe.setLocalTranslation(rightt.x, rightt.y + currentmvmnt, rightt.z); // moves pipe up
        } else {
            rightpipe.setLocalTranslation((result+(4+dist)), -9, leftt.z); // sets random x value
            
            pipes++;
            score.setText("Score: "+pipes); // set the score
        }
        
        currentmvmnt += 0.000000002f; // slowly speed up the falling
    
        if((((pos.y < rightt.y+0.25f)&&(pos.y > rightt.y-0.25f))&&(pos.x+0.7f > rightt.x-4f))&&running){ //  check for collisions between bird and right pipe
            running = false; // you died because you hit the pipe
            System.out.println("hit right at "+pos.y+", "+pos.x);
            if(cs != null) { // check to make sure ui exists
                Element spaceel = cs.findElementByName("dead"); 
                if(spaceel != null) {
                    spaceel.startEffect(EffectEventId.onCustom, null, "onHide"); // fade in "you died" picture
                    spaceel.show(); // fully show "you died" picture
                } else {
                    System.out.println("Unable to show death screen."); // ui doesnt exist
                }
            } else {
                System.out.println("Fatal error: Current screen not found.");
            }
            dead = true; // player is now dead
            score.setText(""); // clear score
            pipes = 0; // reset score
        }
        
        // do the same thing as above, but with the left pipe
        if((((pos.y < leftt.y+0.25f)&&(pos.y > leftt.y-0.25f))&&(pos.x-0.7f < leftt.x+4f))&&running){
            running = false;
            System.out.println("hit left at "+pos.y+", "+pos.x);
            if(cs != null) {
                Element spaceel = cs.findElementByName("dead");
                if(spaceel != null) {
                    spaceel.startEffect(EffectEventId.onCustom, null, "onHide");
                    spaceel.show();
                } else {
                    System.out.println("Unable to show death screen.");
                }
            } else {
                System.out.println("Fatal error: Current screen not found.");
            }
            dead = true;
            score.setText("");
            pipes = -1;
        }
            
        }
    }

    @Override
    public void simpleRender(RenderManager rm) { // old render manager, not reliable, so not used anymore
        /*
        if(running == true){
        Vector3f pos = plr.getLocalTranslation();
        plr.setLocalTranslation(pos.x, pos.y-velocity, pos.z);
        velocity = velocity + 0.000001f;

        Vector3f l1p = left1.getLocalTranslation();
        Vector3f r1p = right1.getLocalTranslation();
        Vector3f l2p = left2.getLocalTranslation();
        Vector3f r2p = right2.getLocalTranslation();
        Vector3f l3p = left3.getLocalTranslation();
        Vector3f r3p = right3.getLocalTranslation();
        Vector3f l4p = left4.getLocalTranslation();
        Vector3f r4p = right4.getLocalTranslation();
        Vector3f l5p = left5.getLocalTranslation();
        Vector3f r5p = right5.getLocalTranslation();
        Vector3f l6p = left6.getLocalTranslation();
        Vector3f r6p = right6.getLocalTranslation();
        Vector3f l7p = left7.getLocalTranslation();
        Vector3f r7p = right7.getLocalTranslation();
        
        if(l1p.y > 9f) {
            left1.setLocalTranslation(l1p.x, -9, l1p.z);
        }
        if(l2p.y > 9f) {
            left2.setLocalTranslation(l2p.x, -9, l2p.z);
        }
        if(l3p.y > 9f) {
            left3.setLocalTranslation(l3p.x, -9, l3p.z);
        }
        if(l4p.y > 9f) {
            left4.setLocalTranslation(l4p.x, -9, l4p.z);
        }
        if(l5p.y > 9f) {
            left5.setLocalTranslation(l5p.x, -9, l5p.z);
        }
        if(l6p.y > 9f) {
            left6.setLocalTranslation(l6p.x, -9, l6p.z);
        }
        if(l7p.y > 9f) {
            left7.setLocalTranslation(l7p.x, -9, l7p.z);
        }
        
        
        if(r1p.y > 9f) {
            right1.setLocalTranslation(r1p.x, -9, r1p.z);
        }
        if(r2p.y > 9f) {
            right2.setLocalTranslation(r2p.x, -9, r2p.z);
        }
        if(r3p.y > 9f) {
            right3.setLocalTranslation(r3p.x, -9, r3p.z);
        }
        if(r4p.y > 9f) {
            right4.setLocalTranslation(r4p.x, -9, r4p.z);
        }
        if(r5p.y > 9f) {
            right5.setLocalTranslation(r5p.x, -9, r5p.z);
        }
        if(r6p.y > 9f) {
            right6.setLocalTranslation(r6p.x, -9, r6p.z);
        }
        if(r7p.y > 9f) {
            right7.setLocalTranslation(r7p.x, -9, r7p.z);
        }
        
        left1.setLocalTranslation(l1p.x, l1p.y + 0.002f, l1p.z);
        right1.setLocalTranslation(r1p.x, r1p.y + 0.002f, r1p.z);
        left2.setLocalTranslation(l2p.x, l2p.y + 0.002f, l2p.z);
        right2.setLocalTranslation(r2p.x, r2p.y + 0.002f, r2p.z);
        left3.setLocalTranslation(l3p.x, l3p.y + 0.002f, l3p.z);
        right3.setLocalTranslation(r3p.x, r3p.y + 0.002f, r3p.z);
        left4.setLocalTranslation(l4p.x, l4p.y + 0.002f, l4p.z);
        right4.setLocalTranslation(r4p.x, r4p.y + 0.002f, r4p.z);
        left5.setLocalTranslation(l5p.x, l5p.y + 0.002f, l5p.z);
        right5.setLocalTranslation(r5p.x, r5p.y + 0.002f, r5p.z);
        left6.setLocalTranslation(l6p.x, l6p.y + 0.002f, l6p.z);
        right6.setLocalTranslation(r6p.x, r6p.y + 0.002f, r6p.z);
        left7.setLocalTranslation(l7p.x, l7p.y + 0.002f, l7p.z);
        right7.setLocalTranslation(r7p.x, r7p.y + 0.002f, r7p.z);
        }
*/
    }


    private void initCtrl() { // initalize key mappings
        inputManager.addMapping("FlapWings",  new KeyTrigger(KeyInput.KEY_SPACE)); // set wing flap
        
        inputManager.addMapping("Left",  new KeyTrigger(KeyInput.KEY_LEFT)); // set left key
        inputManager.addMapping("Right",  new KeyTrigger(KeyInput.KEY_RIGHT)); // set right key
        inputManager.addListener(actionListener, "FlapWings"); // add as action, cannot be held down
        inputManager.addListener(analogListener, "Left", "Right"); // add as analog, can be held down
    }
    
    private void initGUI() {
        NiftyJmeDisplay niftyDisplay = NiftyJmeDisplay.newNiftyJmeDisplay( // create new gui
                assetManager,
                inputManager,
                audioRenderer,
                guiViewPort);
        nifty = niftyDisplay.getNifty(); // get nifty gui
        nifty.fromXml("Interface/homegui.xml", "start"); // open gui file .xml

        // attach the nifty display to the gui view port as a processor
        guiViewPort.addProcessor(niftyDisplay);
        
        Element logoel2 = nifty.getCurrentScreen().findElementByName("dead"); // hide dead image
        //logoel2.startEffect(EffectEventId.onCustom, null, "onHideTwo");
        logoel2.hide();
        
        Element black = nifty.getCurrentScreen().findElementByName("full"); // not used
        //logoel2.startEffect(EffectEventId.onCustom, null, "fadeIn");
        black.hide(); // not used
        
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt"); // load the font for score
        score = new BitmapText(guiFont, false); // create the score text
        score.setSize(40f); // set text size
        score.setText(""); // clear it
        score.setLocalTranslation(10, 790, 0); // set window position
        guiNode.attachChild(score); // add to window
 
        cs = nifty.getCurrentScreen(); // set var to use later
    }

    private void initSettings() {
        xpxl = appsettings.getWidth(); // set for use with ui
        ypxl = appsettings.getHeight(); // set for use with ui
    }
    
    private void initLighting() {
        PointLight light = new PointLight(); // create new light
        Vector3f lightpos = new Vector3f(0,1,2); // create light pos
        light.setPosition(lightpos); // set light pos
        light.setColor(ColorRGBA.White); // set light color
        
        PointLight light2 = new PointLight(); // create new light
        Vector3f lightpos2 = new Vector3f(0,1,-2); // create light pos
        light2.setPosition(lightpos2); // set light pos
        light2.setColor(ColorRGBA.White); // set light color
        
        rootNode.addLight(light);  // add light to scene
        rootNode.addLight(light2); // add light to scene
    }
    
    private void initCam() {
        flyCam.setMoveSpeed(0); // make the camera not movable by player
        flyCam.setRotationSpeed(0); // make the camera not rotatable
    }
    
    private void initScene() {
        // create boxes for sides and other items in scene
        Box b = new Box(1f, 1.5f, 1f); 
        Box b2 = new Box(0.5f, 0.5f, 0.5f);
        Box c = new Box(2, 2, 2);
        Box pipemesh = new Box(4, 0.5f, 0.5f);
        
        // set the variables created earlier so we can change the scene later
        leftpipe = new Geometry("Box", pipemesh);     
        rightpipe = new Geometry("Box", pipemesh); 
        left1 = new Geometry("Box", b);
        right1 = new Geometry("Box", b);
        left2 = new Geometry("Box", b);
        right2 = new Geometry("Box", b);
        left3 = new Geometry("Box", b);
        right3 = new Geometry("Box", b);
        left4 = new Geometry("Box", b);
        right4 = new Geometry("Box", b);
        left5 = new Geometry("Box", b);
        right5 = new Geometry("Box", b);
        left6 = new Geometry("Box", b);
        right6 = new Geometry("Box", b);
        left7 = new Geometry("Box", b);
        right7 = new Geometry("Box", b);
        
        plr = new Geometry("Box", b2);
        
        // load the flappy bird model
        plr = assetManager.loadModel("Models/flappybird.obj");
        plr.setMaterial(assetManager.loadMaterial("Materials/flappybirdmat.j3m")); // set model material
        
        cloud = new Geometry("Box", b2);
        
        cloud = assetManager.loadModel("Models/coulds.obj"); // load clouds model
        rootNode.attachChild(cloud); // add to scene
        
        cloud.setLocalTranslation(20,-30,-30); // set clouds location
        cloud.rotate(0f,90f,0f); // set rotation of clouds
        cloud.setLocalScale(2f,2f,2f); // set size of clouds
        
        
        // set up the pipe material
        Material pipeMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md"); 
        pipeMat.setBoolean("UseMaterialColors", true); 
        pipeMat.setColor("Ambient", ColorRGBA.Green); 
        pipeMat.setColor("Diffuse", ColorRGBA.Green); 
        

        leftpipe.setMaterial(pipeMat);  // set pipe material
        rootNode.attachChild(leftpipe); // add to scene
        
        rightpipe.setMaterial(pipeMat);  // set pipe material
        rootNode.attachChild(rightpipe); // add to scene
        
        Material mat = assetManager.loadMaterial("Materials/brick.j3m"); // load brick tex
        
        Material white = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md"); // clouds will be unshaded
        white.setColor("Color", ColorRGBA.White);
        cloud.setMaterial(white); // set mat of clouds
        
        
        // set mats of bricks        
        left1.setMaterial(mat);
        right1.setMaterial(mat);
        left2.setMaterial(mat);
        right2.setMaterial(mat);
        left3.setMaterial(mat);
        right3.setMaterial(mat);
        left4.setMaterial(mat);
        right4.setMaterial(mat);
        left5.setMaterial(mat);
        right5.setMaterial(mat);
        left6.setMaterial(mat);
        right6.setMaterial(mat);
        left7.setMaterial(mat);
        right7.setMaterial(mat);
        
        // attatch bricks to scene
        rootNode.attachChild(left1);
        rootNode.attachChild(right1);
        rootNode.attachChild(left2);
        rootNode.attachChild(right2);
        rootNode.attachChild(left3);
        rootNode.attachChild(right3);
        rootNode.attachChild(left4);
        rootNode.attachChild(right4);
        rootNode.attachChild(left5);
        rootNode.attachChild(right5);
        rootNode.attachChild(left6);
        rootNode.attachChild(right6);
        rootNode.attachChild(left7);
        rootNode.attachChild(right7);
      
        rootNode.attachChild(plr); // add player to scene
        
        getRootNode().attachChild(SkyFactory.createSky(getAssetManager(), "Textures/brickpbr/sky2.png", SkyFactory.EnvMapType.EquirectMap)); // create the sky
        
        // set position of side bricks
        left1.setLocalTranslation(-5,5,0);
        right1.setLocalTranslation(5,5,0);
        left2.setLocalTranslation(-5,3,0);
        right2.setLocalTranslation(5,3,0);
        left3.setLocalTranslation(-5,0,0);
        right3.setLocalTranslation(5,0,0);
        left4.setLocalTranslation(-5,-3,0);
        right4.setLocalTranslation(5,-3,0);
        left5.setLocalTranslation(-5,-6,0);
        right5.setLocalTranslation(5,-6,0);
        left6.setLocalTranslation(-5,-9,0);
        right6.setLocalTranslation(5,-9,0);
        left7.setLocalTranslation(-5,-12,0);
        right7.setLocalTranslation(5,-12,0);
        
        // set pipe location
        leftpipe.setLocalTranslation(-4,9,0);
        rightpipe.setLocalTranslation(2,9,0);
        
        // set player location
        plr.setLocalTranslation(0,1,0);
        plr.setLocalScale(0.075f, 0.075f, 0.075f);
    }
    
    private final ActionListener actionListener = new ActionListener() { // fired when spacebar
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {
            if (!running) {
                running = true;
                if (!dead) { // check to make sure not dead
                    Element spaceel = nifty.getCurrentScreen().findElementByName("space");
                    Element logoel = nifty.getCurrentScreen().findElementByName("logo");
                    spaceel.startEffect(EffectEventId.onCustom, null, "onHide"); // hide death image
                    logoel.startEffect(EffectEventId.onCustom, null, "onHide"); // hide "try again" image
                } else {
                     Element black = nifty.getCurrentScreen().findElementByName("full"); // not used
                    //logoel2.startEffect(EffectEventId.onCustom, null, "fadeIn");
                    black.show(); // not used
                    Element logoel2 = nifty.getCurrentScreen().findElementByName("dead"); // get dead image
                    logoel2.hide(); // hide dead image
                        
                    Vector3f leftt = leftpipe.getLocalTranslation();
                    Vector3f rightt = rightpipe.getLocalTranslation();
                    int result = 0;
                    int dist = 1;
                    dist = r.nextInt(2-1) +1;
                    result = r.nextInt(3-(-3)) -3;
                    score.setText("Score: 0");
                    leftpipe.setLocalTranslation((result-(4+dist)), -9, leftt.z); // reset pipe locations
                    rightpipe.setLocalTranslation((result+(4+dist)), -9, leftt.z); // reset pipe locations
                    black.hide(); // not used
                }
                /*
                Element logoel = nifty.getCurrentScreen().findElementByName("logo");
                Element spaceel = nifty.getCurrentScreen().findElementByName("space");
                nifty.removeElement(nifty.getCurrentScreen(), logoel);
                nifty.removeElement(nifty.getCurrentScreen(), spaceel);
                */
            }
            if (name.equals("FlapWings") && !keyPressed && running) {
                //Vector3f pos = plr.getLocalTranslation(); 
                velocity = -0.002f; // when spacebar, set velocity to negative to make him go up
                //plr.setLocalTranslation(pos.x, pos.y+2f, pos.z);
                //velocity = 0f;
            }
        }
    };

    private final AnalogListener analogListener = new AnalogListener() {
        @Override
        public void onAnalog(String name, float value, float tpf) {
            if (name.equals("Right") && running) { // if right arrow
                Vector3f pos = plr.getLocalTranslation(); 
                plr.setLocalScale(0.075f, 0.075f, 0.075f);
                if(pos.x < 3.5f) { // check to make sure we arent past the bricks
                    plr.setLocalTranslation(pos.x+0.005f, pos.y, pos.z); // move right
                }
            } else if (name.equals("Left") && running) { // if left arrow
                Vector3f pos = plr.getLocalTranslation(); 
                plr.setLocalScale(-0.075f, 0.075f, 0.075f);
                if(pos.x > -3.5f) { // check to make sure we arent past the bricks
                    plr.setLocalTranslation(pos.x-0.005f, pos.y, pos.z); // move left
                }
            }
        }
    };
 
}
