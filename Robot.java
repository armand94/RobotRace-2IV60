package robotrace;

import com.jogamp.opengl.util.gl2.GLUT;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import static javax.media.opengl.GL2.*;

/**
* Represents a Robot, to be implemented according to the Assignments.
*/
class Robot {
    
    /** The position of the robot. */
    public Vector position = new Vector(0, 0, 0);
    
    /** The direction in which the robot is running. */
    public Vector direction = new Vector(1, 0, 0);

    /** The material from which this robot is built. */
    private final Material material;
    
    

    /**
     * Constructs the robot with initial parameters.
     */
    public Robot(Material material
            
    ) {
        this.material = material;

        
    }

    /**
     * Draws this robot (as a {@code stickfigure} if specified).
     */
    public void draw(GL2 gl, GLU glu, GLUT glut, float tAnim) {
       gl.glMatrixMode(GL_MODELVIEW);
            gl.glPushMatrix();
            gl.glTranslated(position.x,position.y,position.z);
            gl.glRotated(Math.toDegrees(Math.atan2(-direction.x(), direction.y())),0,0,1f);
            drawBody(gl, glu, glut, tAnim, this.position);
            gl.glPopMatrix(); 
        
    }
    
    public void drawBody(GL2 gl, GLU glu, GLUT glut, float tAnim, Vector position){
       //draws body
       gl.glPushMatrix();
       gl.glColor3f(0f, 0f, 1f);
       gl.glTranslated(position.x, position.y, position.z + 1.25f);
       gl.glRotatef(180, 1, 0, 0);
       glut.glutSolidCone(.35, .85, 90, 90);
       gl.glPopMatrix();
       
       //draw neck
       gl.glPushMatrix();
       gl.glColor3f(.1f, .3f, 0f);
       gl.glTranslated(position.x, position.y, position.z + 1.25);
       glut.glutSolidCylinder(.1, .1, 90, 90);
       gl.glPopMatrix();
       
       //draw head
       gl.glPushMatrix();
       gl.glColor3f(0f, 0f, 1f);
       gl.glTranslated(position.x, position.y, position.z + 1.56f);
       glut.glutSolidSphere(.24, 90, 90);
       drawArms(gl, glu, glut, tAnim, this.position);
       drawLegs(gl, glu, glut, tAnim, this.position);
       gl.glPopMatrix();
    }

    public void drawArms(GL2 gl, GLU glu, GLUT glut, float tAnim, Vector position){
       //draw right link to shoulder
       gl.glPushMatrix();
       gl.glColor3f(1f, 0f, .5f);
       gl.glTranslated(position.x, position.y + .28, position.z - 0.4);
       gl.glRotatef(-90, 1, 0, 0);
       glut.glutSolidCylinder(.04, .25, 90, 90);
       gl.glPopMatrix();
       
       //draw left link to shoulder
       gl.glPushMatrix();
       gl.glColor3f(1f, 0f, .5f);
       gl.glTranslated(position.x, position.y - .28, position.z - 0.4);
       gl.glRotatef(90, 1, 0, 0);
       glut.glutSolidCylinder(.04, .25, 90, 90);
       gl.glPopMatrix();
       
       //draw right shoulder
       gl.glPushMatrix();
       gl.glColor3f(1f,0f,0.5f);
       gl.glTranslated(position.x,position.y+.44,position.z - 0.4);
       gl.glRotatef(90,1,0,0);
       glut.glutSolidSphere(.1,90,90);
       gl.glPopMatrix();
              
       //draw left shoulder
       gl.glPushMatrix();
       gl.glColor3f(1f,0f,0.5f);
       gl.glTranslated(position.x,position.y-.44,position.z - 0.4);
       gl.glRotatef(90,1,0,0);
       glut.glutSolidSphere(.1,90,90);
       gl.glPopMatrix();
       
       //draw left arm
       gl.glPushMatrix();
       gl.glColor3f(1f, 0f, .5f);
       gl.glTranslated(position.x, position.y - .44, position.z - 0.4);
       gl.glRotatef(180, 1, 0, 0);
       glut.glutSolidCylinder(.04, .45, 90, 90);
       gl.glPopMatrix();
       
       //draw right arm
       gl.glPushMatrix();
       gl.glColor3f(1f, 0f, .5f);
       gl.glTranslated(position.x, position.y + .44, position.z - 0.4);
       gl.glRotatef(180, 1, 0, 0);
       glut.glutSolidCylinder(.04, .45, 90, 90);
       gl.glPopMatrix();
    }

    public void drawLegs(GL2 gl, GLU glu, GLUT glut, float tAnim, Vector position){
       //draw left joint
       gl.glPushMatrix();
       gl.glColor3f(0f,0f,1f);
       gl.glTranslated(position.x, position.y + .18, position.z - .9);
       glut.glutSolidSphere(.1, 90, 90);
       gl.glPopMatrix();
       
       //draw right joint
       gl.glPushMatrix();
       gl.glColor3f(0f, 0f, 1f);
       gl.glTranslated(position.x, position.y - .18, position.z - .9);
       glut.glutSolidSphere(.1, 90, 90);
       gl.glPopMatrix();   

       //draw left leg
       gl.glPushMatrix();
       gl.glColor3f(1f, 0f, .5f);
       gl.glTranslated(position.x, position.y + .18, position.z - 1.55);
       glut.glutSolidCylinder(.04, .65, 90, 90);
       gl.glPopMatrix();
       
       //draw right leg
       gl.glPushMatrix();
       gl.glColor3f(1f, 0f, .5f);
       gl.glTranslated(position.x, position.y - .18, position.z - 1.55);
       glut.glutSolidCylinder(.04, .65, 90, 90);
       gl.glPopMatrix();
       
      //draw left foot
       gl.glPushMatrix();
       gl.glColor3f(0f,0f,1f);
       gl.glTranslated(position.x, position.y + .18, position.z - 1.55);
       glut.glutSolidSphere(.1, 90, 90);
       gl.glPopMatrix();
       
       //draw right foot
       gl.glPushMatrix();
       gl.glColor3f(0f, 0f, 1f);
       gl.glTranslated(position.x, position.y - .18, position.z - 1.55);
       glut.glutSolidSphere(.1, 90, 90);
       gl.glPopMatrix();
    }   
}
