package com.example.device02.cube;

/**
 * Created by device02 on 18.12.2017.
 */

class Vector3 {
    float x;float y;float z;

    public Vector3() {}

    public static Vector3 cross(Vector3 a, Vector3 b)
    {
        //cx = aybz − azby    cy = azbx − axbz    cz = axby − aybx
        Vector3 ans = new Vector3();

        ans.setX(a.getY()*b.getZ()-a.getZ()*b.getY());
        ans.setY(a.getZ()*b.getX()-a.getX()*b.getZ());
        ans.setZ(a.getX()*b.getY()-a.getY()*b.getX());

        return ans;
    }
    public static Vector3 scale(Vector3 a,float scale)
    {
        Vector3 ans = new Vector3();

        ans.setX(a.x*scale);
        ans.setY(a.y*scale);
        ans.setZ(a.z*scale);

        return ans;
    }
    public static Vector3 add(Vector3 a,Vector3 b)
    {
        Vector3 ans = new Vector3();

        ans.setX(a.x+b.x);
        ans.setY(a.y+b.y);
        ans.setZ(a.z+b.z);

        return ans;
    }
    public static Vector3 sub(Vector3 a,Vector3 b)
    {
        //Vector3 ans = new Vector3();
        return add(a,scale(b,-1));
        //return ans;
    }

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
