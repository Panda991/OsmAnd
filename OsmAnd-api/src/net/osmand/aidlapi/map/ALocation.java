package net.osmand.aidlapi.map;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import net.osmand.aidlapi.AidlParams;

public class ALocation extends AidlParams {

    private double latitude = 0.0;
    private double longitude = 0.0;
    private long time = 0;
    private boolean hasAltitude = false;
    private double altitude = 0.0f;
    private boolean hasSpeed = false;
    private float speed = 0.0f;
    private boolean hasBearing = false;
    private float bearing = 0.0f;
    private boolean hasAccuracy = false;
    private float accuracy = 0.0f;
    private boolean hasVerticalAccuracy = false;
    private float verticalAccuracy = 0.0f;

    private ALocation() {
    }

    public ALocation(Parcel in) {
        readFromParcel(in);
    }

    public static final Parcelable.Creator<ALocation> CREATOR = new Parcelable.Creator<ALocation>() {
        @Override
        public ALocation createFromParcel(Parcel in) {
            return new ALocation(in);
        }

        @Override
        public ALocation[] newArray(int size) {
            return new ALocation[size];
        }
    };

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public long getTime() {
        return time;
    }

    public boolean hasAltitude() {
        return hasAltitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public boolean hasSpeed() {
        return hasSpeed;
    }

    public float getSpeed() {
        return speed;
    }

    public boolean hasBearing() {
        return hasBearing;
    }

    public float getBearing() {
        return bearing;
    }

    public boolean hasAccuracy() {
        return hasAccuracy;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public boolean hasVerticalAccuracy() {
        return hasVerticalAccuracy;
    }

    public float getVerticalAccuracy() {
        return verticalAccuracy;
    }

    @Override
    protected void writeToBundle(Bundle bundle) {
        bundle.putDouble("latitude", latitude);
        bundle.putDouble("longitude", longitude);
        bundle.putLong("time", time);
        bundle.putBoolean("hasAltitude", hasAltitude);
        bundle.putDouble("altitude", altitude);
        bundle.putBoolean("hasSpeed", hasSpeed);
        bundle.putFloat("speed", speed);
        bundle.putBoolean("hasBearing", hasBearing);
        bundle.putFloat("bearing", bearing);
        bundle.putBoolean("hasAccuracy", hasAccuracy);
        bundle.putFloat("accuracy", accuracy);
        bundle.putBoolean("hasVerticalAccuracy", hasVerticalAccuracy);
        bundle.putFloat("verticalAccuracy", verticalAccuracy);
    }

    @Override
    protected void readFromBundle(Bundle bundle) {
        latitude = bundle.getDouble("latitude");
        longitude = bundle.getDouble("longitude");
        time = bundle.getLong("time");
        hasAltitude = bundle.getBoolean("hasAltitude");
        altitude = bundle.getDouble("altitude");
        hasSpeed = bundle.getBoolean("hasSpeed");
        speed = bundle.getFloat("speed");
        hasBearing = bundle.getBoolean("hasBearing");
        bearing = bundle.getFloat("bearing");
        hasAccuracy = bundle.getBoolean("hasAccuracy");
        accuracy = bundle.getFloat("accuracy");
        hasVerticalAccuracy = bundle.getBoolean("hasVerticalAccuracy");
        verticalAccuracy = bundle.getFloat("verticalAccuracy");
    }

    public static Builder builder() {
        return new ALocation().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setLatitude(double latitude) {
            ALocation.this.latitude = latitude;
            return this;
        }

        public Builder setLongitude(double longitude) {
            ALocation.this.longitude = longitude;
            return this;
        }

        public Builder setTime(long time) {
            ALocation.this.time = time;
            return this;
        }

        public Builder hasAltitude(boolean hasAltitude) {
            ALocation.this.hasAltitude = hasAltitude;
            return this;
        }

        public Builder setAltitude(float altitude) {
            ALocation.this.altitude = altitude;
            return this;
        }

        public Builder hasSpeed(boolean hasSpeed) {
            ALocation.this.hasSpeed = hasSpeed;
            return this;
        }

        public Builder setSpeed(float speed) {
            ALocation.this.speed = speed;
            return this;
        }

        public Builder hasBearing(boolean hasBearing) {
            ALocation.this.hasBearing = hasBearing;
            return this;
        }

        public Builder setBearing(float bearing) {
            ALocation.this.bearing = bearing;
            return this;
        }

        public Builder hasAccuracy(boolean hasAccuracy) {
            ALocation.this.hasAccuracy = hasAccuracy;
            return this;
        }

        public Builder setAccuracy(float accuracy) {
            ALocation.this.accuracy = accuracy;
            return this;
        }

        public Builder hasVerticalAccuracy(boolean hasVerticalAccuracy) {
            ALocation.this.hasVerticalAccuracy = hasVerticalAccuracy;
            return this;
        }

        public Builder setVerticalAccuracy(float verticalAccuracy) {
            ALocation.this.verticalAccuracy = verticalAccuracy;
            return this;
        }

        public ALocation build() {
            return ALocation.this;
        }
    }
}
