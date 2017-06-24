package com.codeside.pvapp.web.rest;

import com.codeside.pvapp.PvAppCmsApp;

import com.codeside.pvapp.domain.TouristicInterest;
import com.codeside.pvapp.repository.TouristicInterestRepository;
import com.codeside.pvapp.service.dto.TouristicInterestDTO;
import com.codeside.pvapp.service.mapper.TouristicInterestMapper;
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
 * Test class for the TouristicInterestResource REST controller.
 *
 * @see TouristicInterestResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PvAppCmsApp.class)
public class TouristicInterestResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_WORKING_HOURS = "AAAAAAAAAA";
    private static final String UPDATED_WORKING_HOURS = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCE = "BBBBBBBBBB";

    private static final String DEFAULT_REVIEWS = "AAAAAAAAAA";
    private static final String UPDATED_REVIEWS = "BBBBBBBBBB";

    @Autowired
    private TouristicInterestRepository touristicInterestRepository;

    @Autowired
    private TouristicInterestMapper touristicInterestMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restTouristicInterestMockMvc;

    private TouristicInterest touristicInterest;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TouristicInterestResource touristicInterestResource = new TouristicInterestResource(touristicInterestRepository, touristicInterestMapper);
        this.restTouristicInterestMockMvc = MockMvcBuilders.standaloneSetup(touristicInterestResource)
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
    public static TouristicInterest createEntity() {
        TouristicInterest touristicInterest = new TouristicInterest()
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .workingHours(DEFAULT_WORKING_HOURS)
            .contact(DEFAULT_CONTACT)
            .address(DEFAULT_ADDRESS)
            .province(DEFAULT_PROVINCE)
            .reviews(DEFAULT_REVIEWS);
        return touristicInterest;
    }

    @Before
    public void initTest() {
        touristicInterestRepository.deleteAll();
        touristicInterest = createEntity();
    }

    @Test
    public void createTouristicInterest() throws Exception {
        int databaseSizeBeforeCreate = touristicInterestRepository.findAll().size();

        // Create the TouristicInterest
        TouristicInterestDTO touristicInterestDTO = touristicInterestMapper.toDto(touristicInterest);
        restTouristicInterestMockMvc.perform(post("/api/touristic-interests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(touristicInterestDTO)))
            .andExpect(status().isCreated());

        // Validate the TouristicInterest in the database
        List<TouristicInterest> touristicInterestList = touristicInterestRepository.findAll();
        assertThat(touristicInterestList).hasSize(databaseSizeBeforeCreate + 1);
        TouristicInterest testTouristicInterest = touristicInterestList.get(touristicInterestList.size() - 1);
        assertThat(testTouristicInterest.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTouristicInterest.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testTouristicInterest.getWorkingHours()).isEqualTo(DEFAULT_WORKING_HOURS);
        assertThat(testTouristicInterest.getContact()).isEqualTo(DEFAULT_CONTACT);
        assertThat(testTouristicInterest.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testTouristicInterest.getProvince()).isEqualTo(DEFAULT_PROVINCE);
        assertThat(testTouristicInterest.getReviews()).isEqualTo(DEFAULT_REVIEWS);
    }

    @Test
    public void createTouristicInterestWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = touristicInterestRepository.findAll().size();

        // Create the TouristicInterest with an existing ID
        touristicInterest.setId("existing_id");
        TouristicInterestDTO touristicInterestDTO = touristicInterestMapper.toDto(touristicInterest);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTouristicInterestMockMvc.perform(post("/api/touristic-interests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(touristicInterestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<TouristicInterest> touristicInterestList = touristicInterestRepository.findAll();
        assertThat(touristicInterestList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllTouristicInterests() throws Exception {
        // Initialize the database
        touristicInterestRepository.save(touristicInterest);

        // Get all the touristicInterestList
        restTouristicInterestMockMvc.perform(get("/api/touristic-interests?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(touristicInterest.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].workingHours").value(hasItem(DEFAULT_WORKING_HOURS.toString())))
            .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].province").value(hasItem(DEFAULT_PROVINCE.toString())))
            .andExpect(jsonPath("$.[*].reviews").value(hasItem(DEFAULT_REVIEWS.toString())));
    }

    @Test
    public void getTouristicInterest() throws Exception {
        // Initialize the database
        touristicInterestRepository.save(touristicInterest);

        // Get the touristicInterest
        restTouristicInterestMockMvc.perform(get("/api/touristic-interests/{id}", touristicInterest.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(touristicInterest.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.workingHours").value(DEFAULT_WORKING_HOURS.toString()))
            .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.province").value(DEFAULT_PROVINCE.toString()))
            .andExpect(jsonPath("$.reviews").value(DEFAULT_REVIEWS.toString()));
    }

    @Test
    public void getNonExistingTouristicInterest() throws Exception {
        // Get the touristicInterest
        restTouristicInterestMockMvc.perform(get("/api/touristic-interests/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateTouristicInterest() throws Exception {
        // Initialize the database
        touristicInterestRepository.save(touristicInterest);
        int databaseSizeBeforeUpdate = touristicInterestRepository.findAll().size();

        // Update the touristicInterest
        TouristicInterest updatedTouristicInterest = touristicInterestRepository.findOne(touristicInterest.getId());
        updatedTouristicInterest
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .workingHours(UPDATED_WORKING_HOURS)
            .contact(UPDATED_CONTACT)
            .address(UPDATED_ADDRESS)
            .province(UPDATED_PROVINCE)
            .reviews(UPDATED_REVIEWS);
        TouristicInterestDTO touristicInterestDTO = touristicInterestMapper.toDto(updatedTouristicInterest);

        restTouristicInterestMockMvc.perform(put("/api/touristic-interests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(touristicInterestDTO)))
            .andExpect(status().isOk());

        // Validate the TouristicInterest in the database
        List<TouristicInterest> touristicInterestList = touristicInterestRepository.findAll();
        assertThat(touristicInterestList).hasSize(databaseSizeBeforeUpdate);
        TouristicInterest testTouristicInterest = touristicInterestList.get(touristicInterestList.size() - 1);
        assertThat(testTouristicInterest.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTouristicInterest.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testTouristicInterest.getWorkingHours()).isEqualTo(UPDATED_WORKING_HOURS);
        assertThat(testTouristicInterest.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testTouristicInterest.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testTouristicInterest.getProvince()).isEqualTo(UPDATED_PROVINCE);
        assertThat(testTouristicInterest.getReviews()).isEqualTo(UPDATED_REVIEWS);
    }

    @Test
    public void updateNonExistingTouristicInterest() throws Exception {
        int databaseSizeBeforeUpdate = touristicInterestRepository.findAll().size();

        // Create the TouristicInterest
        TouristicInterestDTO touristicInterestDTO = touristicInterestMapper.toDto(touristicInterest);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTouristicInterestMockMvc.perform(put("/api/touristic-interests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(touristicInterestDTO)))
            .andExpect(status().isCreated());

        // Validate the TouristicInterest in the database
        List<TouristicInterest> touristicInterestList = touristicInterestRepository.findAll();
        assertThat(touristicInterestList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteTouristicInterest() throws Exception {
        // Initialize the database
        touristicInterestRepository.save(touristicInterest);
        int databaseSizeBeforeDelete = touristicInterestRepository.findAll().size();

        // Get the touristicInterest
        restTouristicInterestMockMvc.perform(delete("/api/touristic-interests/{id}", touristicInterest.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TouristicInterest> touristicInterestList = touristicInterestRepository.findAll();
        assertThat(touristicInterestList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TouristicInterest.class);
        TouristicInterest touristicInterest1 = new TouristicInterest();
        touristicInterest1.setId("id1");
        TouristicInterest touristicInterest2 = new TouristicInterest();
        touristicInterest2.setId(touristicInterest1.getId());
        assertThat(touristicInterest1).isEqualTo(touristicInterest2);
        touristicInterest2.setId("id2");
        assertThat(touristicInterest1).isNotEqualTo(touristicInterest2);
        touristicInterest1.setId(null);
        assertThat(touristicInterest1).isNotEqualTo(touristicInterest2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TouristicInterestDTO.class);
        TouristicInterestDTO touristicInterestDTO1 = new TouristicInterestDTO();
        touristicInterestDTO1.setId("id1");
        TouristicInterestDTO touristicInterestDTO2 = new TouristicInterestDTO();
        assertThat(touristicInterestDTO1).isNotEqualTo(touristicInterestDTO2);
        touristicInterestDTO2.setId(touristicInterestDTO1.getId());
        assertThat(touristicInterestDTO1).isEqualTo(touristicInterestDTO2);
        touristicInterestDTO2.setId("id2");
        assertThat(touristicInterestDTO1).isNotEqualTo(touristicInterestDTO2);
        touristicInterestDTO1.setId(null);
        assertThat(touristicInterestDTO1).isNotEqualTo(touristicInterestDTO2);
    }
}
