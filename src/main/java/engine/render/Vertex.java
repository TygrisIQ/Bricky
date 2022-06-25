package engine.render;

import engine.data.Vector3f;

public class Vertex {
    private Vector3f Position;

    public Vertex(Vector3f position){
            this.Position = position;
    }

    public Vector3f getPosition() {
        return Position;
    }

    public void setPosition(Vector3f position) {
        Position = position;
    }
}
