package robotrace;

/**
 * Implementation of a camera with a position and orientation. 
 */
class Camera {

    /** The position of the camera. */
    public Vector eye = new Vector(3f, 6f, 5f);

    /** The point to which the camera is looking. */
    public Vector center = Vector.O;

    /** The up vector. */
    public Vector up = Vector.Z;

    /**
     * Updates the camera viewpoint and direction based on the
     * selected camera mode.
     */
    public void update(GlobalState gs, Robot focus) {

        switch (gs.camMode) {
            
            // First person mode    
            case 1:
                setFirstPersonMode(gs, focus);
                break;
                
            // Default mode    
            default:
                setDefaultMode(gs);
        }
    }

    /**
     * Computes eye, center, and up, based on the camera's default mode.
     */
private void setDefaultMode(GlobalState gs) {
        //set camera coordinates based on spherical coordinates
        eye.x = gs.vDist * Math.cos(gs.theta) * Math.sin(90 - gs.phi);
        eye.y = gs.vDist * -Math.sin(gs.theta) * Math.sin(90 - gs.phi);
        eye.z = gs.vDist * Math.cos(90 - gs.phi);

        center = gs.cnt;

        up = Vector.Z;
    }


    /**
     * Computes eye, center, and up, based on the first person mode.
     * The camera should view from the perspective of the robot.
     */
private void setFirstPersonMode(GlobalState gs, Robot focus) {
        eye = focus.position;                                                   //the eye point is positioned at the robots head, so first take the robots position
        eye = eye.add(new Vector(0, 0, 2));                                     //then add 2 to the z axis to be at the robots head
        eye = eye.add(focus.direction.normalized().scale(0.5));                 //finally add .5 to be just in front of the head
        up = Vector.Z;                                                          //the up vector is Vector.Z

        // center is in the direction of the tangent
        center = focus.direction;                                               //the center point is located in the direction the robot is running
        center.normalized().scale(10);                                          //normalize this vector and then scale it to 10
        center = eye.add(center);                                               //add the robots position to this vector
    }

}
