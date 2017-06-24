package com.codeside.pvapp.web.rest;

import com.codeside.pvapp.PvAppCmsApp;

import com.codeside.pvapp.domain.TouristDestination;
import com.codeside.pvapp.repository.TouristDestinationRepository;
import com.codeside.pvapp.service.dto.TouristDestinationDTO;
import com.codeside.pvapp.service.mapper.TouristDestinationMapper;
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
 * Test class for the TouristDestinationResource REST controller.
 *
 * @see TouristDestinationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PvAppCmsApp.class)
public class TouristDestinationResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COORDINATES = "AAAAAAAAAA";
    private static final String UPDATED_COORDINATES = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_PHOTOS = "AAAAAAAAAA";
    private static final String UPDATED_PHOTOS = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCE = "BBBBBBBBBB";

    private static final String DEFAULT_ATTRIBUTES = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTES = "BBBBBBBBBB";

    private static final String DEFAULT_REVIEWS = "AAAAAAAAAA";
    private static final String UPDATED_REVIEWS = "BBBBBBBBBB";

    @Autowired
    private TouristDestinationRepository touristDestinationRepository;

    @Autowired
    private TouristDestinationMapper touristDestinationMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restTouristDestinationMockMvc;

    private TouristDestination touristDestination;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TouristDestinationResource touristDestinationResource = new TouristDestinationResource(touristDestinationRepository, touristDestinationMapper);
        this.restTouristDestinationMockMvc = MockMvcBuilders.standaloneSetup(touristDestinationResource)
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
    public static TouristDestination createEntity() {
        TouristDestination touristDestination = new TouristDestination()
            .name(DEFAULT_NAME)
            .coordinates(DEFAULT_COORDINATES)
            .description(DEFAULT_DESCRIPTION)
            .photos(DEFAULT_PHOTOS)
            .address(DEFAULT_ADDRESS)
            .province(DEFAULT_PROVINCE)
            .attributes(DEFAULT_ATTRIBUTES)
            .reviews(DEFAULT_REVIEWS);
        return touristDestination;
    }

    @Before
    public void initTest() {
        touristDestinationRepository.deleteAll();
        touristDestination = createEntity();
    }

    @Test
    public void createTouristDestination() throws Exception {
        int databaseSizeBeforeCreate = touristDestinationRepository.findAll().size();

        // Create the TouristDestination
        TouristDestinationDTO touristDestinationDTO = touristDestinationMapper.toDto(touristDestination);
        restTouristDestinationMockMvc.perform(post("/api/tourist-destinations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(touristDestinationDTO)))
            .andExpect(status().isCreated());

        // Validate the TouristDestination in the database
        List<TouristDestination> touristDestinationList = touristDestinationRepository.findAll();
        assertThat(touristDestinationList).hasSize(databaseSizeBeforeCreate + 1);
        TouristDestination testTouristDestination = touristDestinationList.get(touristDestinationList.size() - 1);
        assertThat(testTouristDestination.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTouristDestination.getCoordinates()).isEqualTo(DEFAULT_COORDINATES);
        assertThat(testTouristDestination.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTouristDestination.getPhotos()).isEqualTo(DEFAULT_PHOTOS);
        assertThat(testTouristDestination.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testTouristDestination.getProvince()).isEqualTo(DEFAULT_PROVINCE);
        assertThat(testTouristDestination.getAttributes()).isEqualTo(DEFAULT_ATTRIBUTES);
        assertThat(testTouristDestination.getReviews()).isEqualTo(DEFAULT_REVIEWS);
    }

    @Test
    public void createTouristDestinationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = touristDestinationRepository.findAll().size();

        // Create the TouristDestination with an existing ID
        touristDestination.setId("existing_id");
        TouristDestinationDTO touristDestinationDTO = touristDestinationMapper.toDto(touristDestination);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTouristDestinationMockMvc.perform(post("/api/tourist-destinations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(touristDestinationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<TouristDestination> touristDestinationList = touristDestinationRepository.findAll();
        assertThat(touristDestinationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllTouristDestinations() throws Exception {
        // Initialize the database
        touristDestinationRepository.save(touristDestination);

        // Get all the touristDestinationList
        restTouristDestinationMockMvc.perform(get("/api/tourist-destinations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(touristDestination.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].coordinates").value(hasItem(DEFAULT_COORDINATES.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].photos").value(hasItem(DEFAULT_PHOTOS.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].province").value(hasItem(DEFAULT_PROVINCE.toString())))
            .andExpect(jsonPath("$.[*].attributes").value(hasItem(DEFAULT_ATTRIBUTES.toString())))
            .andExpect(jsonPath("$.[*].reviews").value(hasItem(DEFAULT_REVIEWS.toString())));
    }

    @Test
    public void getTouristDestination() throws Exception {
        // Initialize the database
        touristDestinationRepository.save(touristDestination);

        // Get the touristDestination
        restTouristDestinationMockMvc.perform(get("/api/tourist-destinations/{id}", touristDestination.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(touristDestination.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.coordinates").value(DEFAULT_COORDINATES.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.photos").value(DEFAULT_PHOTOS.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.province").value(DEFAULT_PROVINCE.toString()))
            .andExpect(jsonPath("$.attributes").value(DEFAULT_ATTRIBUTES.toString()))
            .andExpect(jsonPath("$.reviews").value(DEFAULT_REVIEWS.toString()));
    }

    @Test
    public void getNonExistingTouristDestination() throws Exception {
        // Get the touristDestination
        restTouristDestinationMockMvc.perform(get("/api/tourist-destinations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateTouristDestination() throws Exception {
        // Initialize the database
        touristDestinationRepository.save(touristDestination);
        int databaseSizeBeforeUpdate = touristDestinationRepository.findAll().size();

        // Update the touristDestination
        TouristDestination updatedTouristDestination = touristDestinationRepository.findOne(touristDestination.getId());
        updatedTouristDestination
            .name(UPDATED_NAME)
            .coordinates(UPDATED_COORDINATES)
            .description(UPDATED_DESCRIPTION)
            .photos(UPDATED_PHOTOS)
            .address(UPDATED_ADDRESS)
            .province(UPDATED_PROVINCE)
            .attributes(UPDATED_ATTRIBUTES)
            .reviews(UPDATED_REVIEWS);
        TouristDestinationDTO touristDestinationDTO = touristDestinationMapper.toDto(updatedTouristDestination);

        restTouristDestinationMockMvc.perform(put("/api/tourist-destinations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(touristDestinationDTO)))
            .andExpect(status().isOk());

        // Validate the TouristDestination in the database
        List<TouristDestination> touristDestinationList = touristDestinationRepository.findAll();
        assertThat(touristDestinationList).hasSize(databaseSizeBeforeUpdate);
        TouristDestination testTouristDestination = touristDestinationList.get(touristDestinationList.size() - 1);
        assertThat(testTouristDestination.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTouristDestination.getCoordinates()).isEqualTo(UPDATED_COORDINATES);
        assertThat(testTouristDestination.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTouristDestination.getPhotos()).isEqualTo(UPDATED_PHOTOS);
        assertThat(testTouristDestination.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testTouristDestination.getProvince()).isEqualTo(UPDATED_PROVINCE);
        assertThat(testTouristDestination.getAttributes()).isEqualTo(UPDATED_ATTRIBUTES);
        assertThat(testTouristDestination.getReviews()).isEqualTo(UPDATED_REVIEWS);
    }

    @Test
    public void updateNonExistingTouristDestination() throws Exception {
        int databaseSizeBeforeUpdate = touristDestinationRepository.findAll().size();

        // Create the TouristDestination
        TouristDestinationDTO touristDestinationDTO = touristDestinationMapper.toDto(touristDestination);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTouristDestinationMockMvc.perform(put("/api/tourist-destinations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(touristDestinationDTO)))
            .andExpect(status().isCreated());

        // Validate the TouristDestination in the database
        List<TouristDestination> touristDestinationList = touristDestinationRepository.findAll();
        assertThat(touristDestinationList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteTouristDestination() throws Exception {
        // Initialize the database
        touristDestinationRepository.save(touristDestination);
        int databaseSizeBeforeDelete = touristDestinationRepository.findAll().size();

        // Get the touristDestination
        restTouristDestinationMockMvc.perform(delete("/api/tourist-destinations/{id}", touristDestination.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TouristDestination> touristDestinationList = touristDestinationRepository.findAll();
        assertThat(touristDestinationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TouristDestination.class);
        TouristDestination touristDestination1 = new TouristDestination();
        touristDestination1.setId("id1");
        TouristDestination touristDestination2 = new TouristDestination();
        touristDestination2.setId(touristDestination1.getId());
        assertThat(touristDestination1).isEqualTo(touristDestination2);
        touristDestination2.setId("id2");
        assertThat(touristDestination1).isNotEqualTo(touristDestination2);
        touristDestination1.setId(null);
        assertThat(touristDestination1).isNotEqualTo(touristDestination2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TouristDestinationDTO.class);
        TouristDestinationDTO touristDestinationDTO1 = new TouristDestinationDTO();
        touristDestinationDTO1.setId("id1");
        TouristDestinationDTO touristDestinationDTO2 = new TouristDestinationDTO();
        assertThat(touristDestinationDTO1).isNotEqualTo(touristDestinationDTO2);
        touristDestinationDTO2.setId(touristDestinationDTO1.getId());
        assertThat(touristDestinationDTO1).isEqualTo(touristDestinationDTO2);
        touristDestinationDTO2.setId("id2");
        assertThat(touristDestinationDTO1).isNotEqualTo(touristDestinationDTO2);
        touristDestinationDTO1.setId(null);
        assertThat(touristDestinationDTO1).isNotEqualTo(touristDestinationDTO2);
    }
}
