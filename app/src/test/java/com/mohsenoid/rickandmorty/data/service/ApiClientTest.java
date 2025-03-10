package com.mohsenoid.rickandmorty.data.service;

import com.mohsenoid.rickandmorty.data.Serializer;
import com.mohsenoid.rickandmorty.data.service.network.NetworkHelper;
import com.mohsenoid.rickandmorty.model.CharacterModel;
import com.mohsenoid.rickandmorty.model.EpisodeModel;
import com.mohsenoid.rickandmorty.test.ApiResponseFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class ApiClientTest {

    private ApiClient apiClient;

    @Mock
    private NetworkHelper helper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        apiClient = ApiClientImpl.getInstance(helper);
    }

    @After
    public void tearDown() {
        ApiClientImpl.instance = null;
    }

    @Test
    public void testGetEpisodes() throws Exception {
        // GIVEN
        stubNetworkRequestData(ApiConstants.EPISODE_ENDPOINT, ApiResponseFactory.Episode.EPISODES_JSON);
        List<EpisodeModel> expected = ApiResponseFactory.Episode.episodesResponse();

        // WHEN
        List<EpisodeModel> actual = apiClient.getEpisodes(1);

        // THEN
        assertEquals(expected, actual);
    }

    @Test
    public void testGetCharacters() throws Exception {
        // GIVEN
        List<Integer> characterIds = Collections.singletonList(1);

        String characterEndpoint = ApiConstants.CHARACTER_ENDPOINT + "[" + Serializer.serializeIntegerList(characterIds) + "]";
        stubNetworkRequestData(characterEndpoint, ApiResponseFactory.Characters.CHARACTERS_JSON);

        List<CharacterModel> expected = ApiResponseFactory.Characters.charactersResponse();

        // WHEN
        List<CharacterModel> actual = apiClient.getCharacters(characterIds);

        // THEN
        assertEquals(expected, actual);
    }

    @Test
    public void testGetCharacterDetails() throws Exception {
        // GIVEN
        int characterId = 1;
        stubNetworkRequestData(ApiConstants.CHARACTER_ENDPOINT + characterId, ApiResponseFactory.CharacterDetails.CHARACTER_DETAILS_JSON);
        CharacterModel expected = ApiResponseFactory.CharacterDetails.characterResponse();

        // WHEN
        CharacterModel actual = apiClient.getCharacterDetails(characterId);

        // THEN
        assertEquals(expected, actual);
    }

    private void stubNetworkRequestData(String episodeEndpoint, String response) throws IOException {
        when(helper.requestData(eq(episodeEndpoint), any()))
                .thenReturn(response);
    }
}