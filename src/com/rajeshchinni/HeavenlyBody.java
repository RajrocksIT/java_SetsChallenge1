package com.rajeshchinni;

import java.util.HashSet;
import java.util.Set;

public abstract class HeavenlyBody {

    private final Key key;
    private final double orbitalPeriod;
    private final Set<HeavenlyBody> satellites;

    public enum BodyTypes {
        STAR,
        PLANET,
        DWARF_PLANET,
        MOON,
        COMET,
        ASTEROID
    }

    // constructor
    public HeavenlyBody(String name, double orbitalPeriod, BodyTypes bodyType) {
        this.key = new Key(name, bodyType);
        this.orbitalPeriod = orbitalPeriod;
        this.satellites = new HashSet<>();      // manually added

    }

    // getter
    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public Key getKey() {
        return key;
    }

    public Set<HeavenlyBody> getSatellites() {
        //return satellites;
        return new HashSet<>(this.satellites);
    }

    // method
    public boolean addSatellite(HeavenlyBody moon){    // addMoon is changed to addSattelite
        return this.satellites.add(moon);

    }

    public static Key makeKey(String name, BodyTypes bodyType){      // newly added method
        return new Key(name, bodyType);
    }

    @Override
    public final boolean equals (Object obj){   // added final
        if (this == obj){
            return true;
        }

        if (obj instanceof HeavenlyBody){         // newly added
            HeavenlyBody theObject = (HeavenlyBody)obj;
            return this.key.equals(theObject.getKey());
        }
        return false;
    }
    // https://www.geeksforgeeks.org/equals-hashcode-methods-java/
    // https://www.youtube.com/watch?v=ghswNpRv2t0
    // https://www.youtube.com/watch?v=Nr56SlbMed4
    // https://www.youtube.com/watch?v=c3RVW3KGIIE

    @Override
    public final int hashCode() {    // added final
        //return super.hashCode();
        return this.key.hashCode();
    }

    @Override
    public String toString() {
        //return super.toString();
        return this.key.name + ": " + this.key.bodyType + ", " + this.orbitalPeriod;
    }

    // sublass
    public static final class Key {          // newly added sub class
        private String name;
        private BodyTypes bodyType;

        private Key(String name, BodyTypes bodyType) {
            this.name = name;
            this.bodyType = bodyType;
        }

        public String getName() {
            return name;
        }

        public BodyTypes getBodyType() {
            return bodyType;
        }

        @Override
        public int hashCode() {
            //return super.hashCode();
            return this.name.hashCode() + 57 + this.bodyType.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            //return super.equals(obj);
            Key key = (Key) obj;
            if (this.name.equals(key.getName())){
                return (this.bodyType == key.getBodyType());
            }
            return false;
        }

        @Override
        public String toString() {
            //return super.toString();
            return this.name + ": " + this.bodyType;
        }
    }
}
