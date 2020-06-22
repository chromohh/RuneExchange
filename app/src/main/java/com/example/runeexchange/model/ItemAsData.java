package com.example.runeexchange.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemAsData implements Parcelable {
    private String id;
    private String name;
    private String price;

    public ItemAsData(String id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    protected ItemAsData(Parcel in) {
        id = in.readString();
        name = in.readString();
        price = in.readString();
    }

    public static final Creator<ItemAsData> CREATOR = new Creator<ItemAsData>() {
        @Override
        public ItemAsData createFromParcel(Parcel in) {
            return new ItemAsData(in);
        }

        @Override
        public ItemAsData[] newArray(int size) {
            return new ItemAsData[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ItemAsData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(price);
    }
}
