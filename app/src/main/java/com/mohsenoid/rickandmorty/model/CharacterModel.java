package com.mohsenoid.rickandmorty.model;

import com.mohsenoid.rickandmorty.data.Serializer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CharacterModel {

    public static final String ALIVE = "Alive";

    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_STATUS = "status";
    private static final String TAG_SPECIES = "species";
    private static final String TAG_TYPE = "type";
    private static final String TAG_GENDER = "gender";
    private static final String TAG_ORIGIN = "origin";
    private static final String TAG_LOCATION = "location";
    private static final String TAG_IMAGE = "image";
    private static final String TAG_EPISODE = "episode";
    private static final String TAG_URL = "url";
    private static final String TAG_CREATED = "created";
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private OriginModel origin;
    private LocationModel location;
    private String image;
    private List<String> episodes;
    private String url;
    private String created;
    private Boolean isKilledByUser;

    public CharacterModel(Integer id, String name, String status, String species, String type, String gender, OriginModel origin, LocationModel location, String image, String episodesSerialized, String url, String created, Boolean isKilledByUser) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.species = species;
        this.type = type;
        this.gender = gender;
        this.origin = origin;
        this.location = location;
        this.image = image;
        this.episodes = Serializer.deserializeStringList(episodesSerialized);
        this.url = url;
        this.created = created;
        this.isKilledByUser = isKilledByUser;
    }

    public CharacterModel(Integer id, String name, String status, String species, String type, String gender, OriginModel origin, LocationModel location, String image, List<String> episodes, String url, String created, Boolean isKilledByUser) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.species = species;
        this.type = type;
        this.gender = gender;
        this.origin = origin;
        this.location = location;
        this.image = image;
        this.episodes = episodes;
        this.url = url;
        this.created = created;
        this.isKilledByUser = isKilledByUser;
    }

    public static List<CharacterModel> fromJson(JSONArray jsonArray) throws JSONException {
        List<CharacterModel> characters = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject characterJsonObject = jsonArray.getJSONObject(i);
            CharacterModel character = CharacterModel.fromJson(characterJsonObject);
            characters.add(character);
        }

        return characters;
    }

    public static CharacterModel fromJson(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        return fromJson(jsonObject);
    }

    private static CharacterModel fromJson(JSONObject jsonObject) throws JSONException {
        int id = jsonObject.getInt(TAG_ID);
        String name = jsonObject.getString(TAG_NAME);
        String status = jsonObject.getString(TAG_STATUS);
        String species = jsonObject.getString(TAG_SPECIES);
        String type = jsonObject.getString(TAG_TYPE);
        String gender = jsonObject.getString(TAG_GENDER);
        OriginModel origin = OriginModel.fromJson(jsonObject.getJSONObject(TAG_ORIGIN));
        LocationModel location = LocationModel.fromJson(jsonObject.getJSONObject(TAG_LOCATION));
        String image = jsonObject.getString(TAG_IMAGE);
        String url = jsonObject.getString(TAG_URL);
        String created = jsonObject.getString(TAG_CREATED);

        JSONArray episodesJsonArray = jsonObject.getJSONArray(TAG_EPISODE);
        List<String> episodes = new ArrayList<>();
        for (int i = 0; i < episodesJsonArray.length(); i++) {
            String episode = episodesJsonArray.getString(i);
            episodes.add(episode);
        }

        return new CharacterModel(id, name, status, species, type, gender, origin, location, image, episodes, url, created, false);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public OriginModel getOrigin() {
        return origin;
    }

    public void setOrigin(OriginModel origin) {
        this.origin = origin;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<String> episode) {
        this.episodes = episode;
    }

    public String getSerializedEpisodes() {
        return Serializer.serializeStringList(this.episodes);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Boolean getIsKilledByUser() {
        return isKilledByUser;
    }

    public void setIsKilledByUser(Boolean isKilledByUser) {
        this.isKilledByUser = isKilledByUser;
    }

    public boolean isAlive() {
        return getStatus().equals(ALIVE) && !isKilledByUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterModel that = (CharacterModel) o;
        return id.equals(that.id) &&
                name.equals(that.name) &&
                status.equals(that.status) &&
                species.equals(that.species) &&
                type.equals(that.type) &&
                gender.equals(that.gender) &&
                origin.equals(that.origin) &&
                location.equals(that.location) &&
                image.equals(that.image) &&
                episodes.equals(that.episodes) &&
                url.equals(that.url) &&
                created.equals(that.created) &&
                isKilledByUser.equals(that.isKilledByUser);
    }
}
