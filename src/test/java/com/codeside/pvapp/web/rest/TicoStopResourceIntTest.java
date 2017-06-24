package com.codeside.pvapp.web.rest;

import com.codeside.pvapp.PvAppCmsApp;

import com.codeside.pvapp.domain.TicoStop;
import com.codeside.pvapp.repository.TicoStopRepository;
import com.codeside.pvapp.service.dto.TicoStopDTO;
import com.codeside.pvapp.service.mapper.TicoStopMapper;
import com.codeside.pvapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TicoStopResource REST controller.
 *
 * @see TicoStopResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PvAppCmsApp.class)
public class TicoStopResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_HISTORICAL_REVIEW = "AAAAAAAAAA";
    private static final String UPDATED_HISTORICAL_REVIEW = "BBBBBBBBBB";

    private static final String DEFAULT_COORDINATES = "AAAAAAAAAA";
    private static final String UPDATED_COORDINATES = "BBBBBBBBBB";

    private static final String DEFAULT_PHOTO = "AAAAAAAAAA";
    private static final String UPDATED_PHOTO = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCE = "BBBBBBBBBB";

    @Autowired
    private TicoStopRepository ticoStopRepository;

    @Autowired
    private TicoStopMapper ticoStopMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restTicoStopMockMvc;

    private TicoStop ticoStop;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TicoStopResource ticoStopResource = new TicoStopResource(ticoStopRepository, ticoStopMapper);
        this.restTicoStopMockMvc = MockMvcBuilders.standaloneSetup(ticoStopResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TicoStop createEntity() {
        TicoStop ticoStop = new TicoStop()
            .name(DEFAULT_NAME)
            .historicalReview(DEFAULT_HISTORICAL_REVIEW)
            .coordinates(DEFAULT_COORDINATES)
            .photo(DEFAULT_PHOTO)
            .address(DEFAULT_ADDRESS)
            .province(DEFAULT_PROVINCE);
        return ticoStop;
    }

    @Before
    public void initTest() {
        ticoStopRepository.deleteAll();
        ticoStop = createEntity();
    }

    @Test
    public void createTicoStop() throws Exception {
        int databaseSizeBeforeCreate = ticoStopRepository.findAll().size();

        // Create the TicoStop
        TicoStopDTO ticoStopDTO = ticoStopMapper.toDto(ticoStop);
        restTicoStopMockMvc.perform(post("/api/tico-stops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ticoStopDTO)))
            .andExpect(status().isCreated());

        // Validate the TicoStop in the database
        List<TicoStop> ticoStopList = ticoStopRepository.findAll();
        assertThat(ticoStopList).hasSize(databaseSizeBeforeCreate + 1);
        TicoStop testTicoStop = ticoStopList.get(ticoStopList.size() - 1);
        assertThat(testTicoStop.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTicoStop.getHistoricalReview()).isEqualTo(DEFAULT_HISTORICAL_REVIEW);
        assertThat(testTicoStop.getCoordinates()).isEqualTo(DEFAULT_COORDINATES);
        assertThat(testTicoStop.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testTicoStop.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testTicoStop.getProvince()).isEqualTo(DEFAULT_PROVINCE);
    }

    @Test
    public void createTicoStopWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ticoStopRepository.findAll().size();

        // Create the TicoStop with an existing ID
        ticoStop.setId("existing_id");
        TicoStopDTO ticoStopDTO = ticoStopMapper.toDto(ticoStop);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTicoStopMockMvc.perform(post("/api/tico-stops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ticoStopDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<TicoStop> ticoStopList = ticoStopRepository.findAll();
        assertThat(ticoStopList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllTicoStops() throws Exception {
        // Initialize the database
        ticoStopRepository.save(ticoStop);

        // Get all the ticoStopList
        restTicoStopMockMvc.perform(get("/api/tico-stops?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ticoStop.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].historicalReview").value(hasItem(DEFAULT_HISTORICAL_REVIEW.toString())))
            .andExpect(jsonPath("$.[*].coordinates").value(hasItem(DEFAULT_COORDINATES.toString())))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(DEFAULT_PHOTO.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].province").value(hasItem(DEFAULT_PROVINCE.toString())));
    }

    @Test
    public void getTicoStop() throws Exception {
        // Initialize the database
        ticoStopRepository.save(ticoStop);

        // Get the ticoStop
        restTicoStopMockMvc.perform(get("/api/tico-stops/{id}", ticoStop.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ticoStop.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.historicalReview").value(DEFAULT_HISTORICAL_REVIEW.toString()))
            .andExpect(jsonPath("$.coordinates").value(DEFAULT_COORDINATES.toString()))
            .andExpect(jsonPath("$.photo").value(DEFAULT_PHOTO.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.province").value(DEFAULT_PROVINCE.toString()));
    }

    @Test
    public void getNonExistingTicoStop() throws Exception {
        // Get the ticoStop
        restTicoStopMockMvc.perform(get("/api/tico-stops/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateTicoStop() throws Exception {
        // Initialize the database
        ticoStopRepository.save(ticoStop);
        int databaseSizeBeforeUpdate = ticoStopRepository.findAll().size();

        // Update the ticoStop
        TicoStop updatedTicoStop = ticoStopRepository.findOne(ticoStop.getId());
        updatedTicoStop
            .name(UPDATED_NAME)
            .historicalReview(UPDATED_HISTORICAL_REVIEW)
            .coordinates(UPDATED_COORDINATES)
            .photo(UPDATED_PHOTO)
            .address(UPDATED_ADDRESS)
            .province(UPDATED_PROVINCE);
        TicoStopDTO ticoStopDTO = ticoStopMapper.toDto(updatedTicoStop);

        restTicoStopMockMvc.perform(put("/api/tico-stops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ticoStopDTO)))
            .andExpect(status().isOk());

        // Validate the TicoStop in the database
        List<TicoStop> ticoStopList = ticoStopRepository.findAll();
        assertThat(ticoStopList).hasSize(databaseSizeBeforeUpdate);
        TicoStop testTicoStop = ticoStopList.get(ticoStopList.size() - 1);
        assertThat(testTicoStop.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTicoStop.getHistoricalReview()).isEqualTo(UPDATED_HISTORICAL_REVIEW);
        assertThat(testTicoStop.getCoordinates()).isEqualTo(UPDATED_COORDINATES);
        assertThat(testTicoStop.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testTicoStop.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testTicoStop.getProvince()).isEqualTo(UPDATED_PROVINCE);
    }

    @Test
    public void updateNonExistingTicoStop() throws Exception {
        int databaseSizeBeforeUpdate = ticoStopRepository.findAll().size();

        // Create the TicoStop
        TicoStopDTO ticoStopDTO = ticoStopMapper.toDto(ticoStop);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTicoStopMockMvc.perform(put("/api/tico-stops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ticoStopDTO)))
            .andExpect(status().isCreated());

        // Validate the TicoStop in the database
        List<TicoStop> ticoStopList = ticoStopRepository.findAll();
        assertThat(ticoStopList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteTicoStop() throws Exception {
        // Initialize the database
        ticoStopRepository.save(ticoStop);
        int databaseSizeBeforeDelete = ticoStopRepository.findAll().size();

        // Get the ticoStop
        restTicoStopMockMvc.perform(delete("/api/tico-stops/{id}", ticoStop.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TicoStop> ticoStopList = ticoStopRepository.findAll();
        assertThat(ticoStopList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TicoStop.class);
        TicoStop ticoStop1 = new TicoStop();
        ticoStop1.setId("id1");
        TicoStop ticoStop2 = new TicoStop();
        ticoStop2.setId(ticoStop1.getId());
        assertThat(ticoStop1).isEqualTo(ticoStop2);
        ticoStop2.setId("id2");
        assertThat(ticoStop1).isNotEqualTo(ticoStop2);
        ticoStop1.setId(null);
        assertThat(ticoStop1).isNotEqualTo(ticoStop2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TicoStopDTO.class);
        TicoStopDTO ticoStopDTO1 = new TicoStopDTO();
        ticoStopDTO1.setId("id1");
        TicoStopDTO ticoStopDTO2 = new TicoStopDTO();
        assertThat(ticoStopDTO1).isNotEqualTo(ticoStopDTO2);
        ticoStopDTO2.setId(ticoStopDTO1.getId());
        assertThat(ticoStopDTO1).isEqualTo(ticoStopDTO2);
        ticoStopDTO2.setId("id2");
        assertThat(ticoStopDTO1).isNotEqualTo(ticoStopDTO2);
        ticoStopDTO1.setId(null);
        assertThat(ticoStopDTO1).isNotEqualTo(ticoStopDTO2);
    }
}
