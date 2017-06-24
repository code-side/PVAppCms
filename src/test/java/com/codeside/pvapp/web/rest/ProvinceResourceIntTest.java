package com.codeside.pvapp.web.rest;

import com.codeside.pvapp.PvAppCmsApp;

import com.codeside.pvapp.domain.Province;
import com.codeside.pvapp.repository.ProvinceRepository;
import com.codeside.pvapp.service.dto.ProvinceDTO;
import com.codeside.pvapp.service.mapper.ProvinceMapper;
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
 * Test class for the ProvinceResource REST controller.
 *
 * @see ProvinceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PvAppCmsApp.class)
public class ProvinceResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COORDINATES = "AAAAAAAAAA";
    private static final String UPDATED_COORDINATES = "BBBBBBBBBB";

    private static final String DEFAULT_HISTORY = "AAAAAAAAAA";
    private static final String UPDATED_HISTORY = "BBBBBBBBBB";

    private static final String DEFAULT_CULTURE = "AAAAAAAAAA";
    private static final String UPDATED_CULTURE = "BBBBBBBBBB";

    private static final String DEFAULT_PHOTO = "AAAAAAAAAA";
    private static final String UPDATED_PHOTO = "BBBBBBBBBB";

    private static final String DEFAULT_CANTONS = "AAAAAAAAAA";
    private static final String UPDATED_CANTONS = "BBBBBBBBBB";

    private static final String DEFAULT_EMERGENCY_CONTACTS = "AAAAAAAAAA";
    private static final String UPDATED_EMERGENCY_CONTACTS = "BBBBBBBBBB";

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private ProvinceMapper provinceMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProvinceMockMvc;

    private Province province;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProvinceResource provinceResource = new ProvinceResource(provinceRepository, provinceMapper);
        this.restProvinceMockMvc = MockMvcBuilders.standaloneSetup(provinceResource)
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
    public static Province createEntity() {
        Province province = new Province()
            .name(DEFAULT_NAME)
            .coordinates(DEFAULT_COORDINATES)
            .history(DEFAULT_HISTORY)
            .culture(DEFAULT_CULTURE)
            .photo(DEFAULT_PHOTO)
            .cantons(DEFAULT_CANTONS)
            .emergencyContacts(DEFAULT_EMERGENCY_CONTACTS);
        return province;
    }

    @Before
    public void initTest() {
        provinceRepository.deleteAll();
        province = createEntity();
    }

    @Test
    public void createProvince() throws Exception {
        int databaseSizeBeforeCreate = provinceRepository.findAll().size();

        // Create the Province
        ProvinceDTO provinceDTO = provinceMapper.toDto(province);
        restProvinceMockMvc.perform(post("/api/provinces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provinceDTO)))
            .andExpect(status().isCreated());

        // Validate the Province in the database
        List<Province> provinceList = provinceRepository.findAll();
        assertThat(provinceList).hasSize(databaseSizeBeforeCreate + 1);
        Province testProvince = provinceList.get(provinceList.size() - 1);
        assertThat(testProvince.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProvince.getCoordinates()).isEqualTo(DEFAULT_COORDINATES);
        assertThat(testProvince.getHistory()).isEqualTo(DEFAULT_HISTORY);
        assertThat(testProvince.getCulture()).isEqualTo(DEFAULT_CULTURE);
        assertThat(testProvince.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testProvince.getCantons()).isEqualTo(DEFAULT_CANTONS);
        assertThat(testProvince.getEmergencyContacts()).isEqualTo(DEFAULT_EMERGENCY_CONTACTS);
    }

    @Test
    public void createProvinceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = provinceRepository.findAll().size();

        // Create the Province with an existing ID
        province.setId("existing_id");
        ProvinceDTO provinceDTO = provinceMapper.toDto(province);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProvinceMockMvc.perform(post("/api/provinces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provinceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Province> provinceList = provinceRepository.findAll();
        assertThat(provinceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProvinces() throws Exception {
        // Initialize the database
        provinceRepository.save(province);

        // Get all the provinceList
        restProvinceMockMvc.perform(get("/api/provinces?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(province.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].coordinates").value(hasItem(DEFAULT_COORDINATES.toString())))
            .andExpect(jsonPath("$.[*].history").value(hasItem(DEFAULT_HISTORY.toString())))
            .andExpect(jsonPath("$.[*].culture").value(hasItem(DEFAULT_CULTURE.toString())))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(DEFAULT_PHOTO.toString())))
            .andExpect(jsonPath("$.[*].cantons").value(hasItem(DEFAULT_CANTONS.toString())))
            .andExpect(jsonPath("$.[*].emergencyContacts").value(hasItem(DEFAULT_EMERGENCY_CONTACTS.toString())));
    }

    @Test
    public void getProvince() throws Exception {
        // Initialize the database
        provinceRepository.save(province);

        // Get the province
        restProvinceMockMvc.perform(get("/api/provinces/{id}", province.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(province.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.coordinates").value(DEFAULT_COORDINATES.toString()))
            .andExpect(jsonPath("$.history").value(DEFAULT_HISTORY.toString()))
            .andExpect(jsonPath("$.culture").value(DEFAULT_CULTURE.toString()))
            .andExpect(jsonPath("$.photo").value(DEFAULT_PHOTO.toString()))
            .andExpect(jsonPath("$.cantons").value(DEFAULT_CANTONS.toString()))
            .andExpect(jsonPath("$.emergencyContacts").value(DEFAULT_EMERGENCY_CONTACTS.toString()));
    }

    @Test
    public void getNonExistingProvince() throws Exception {
        // Get the province
        restProvinceMockMvc.perform(get("/api/provinces/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProvince() throws Exception {
        // Initialize the database
        provinceRepository.save(province);
        int databaseSizeBeforeUpdate = provinceRepository.findAll().size();

        // Update the province
        Province updatedProvince = provinceRepository.findOne(province.getId());
        updatedProvince
            .name(UPDATED_NAME)
            .coordinates(UPDATED_COORDINATES)
            .history(UPDATED_HISTORY)
            .culture(UPDATED_CULTURE)
            .photo(UPDATED_PHOTO)
            .cantons(UPDATED_CANTONS)
            .emergencyContacts(UPDATED_EMERGENCY_CONTACTS);
        ProvinceDTO provinceDTO = provinceMapper.toDto(updatedProvince);

        restProvinceMockMvc.perform(put("/api/provinces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provinceDTO)))
            .andExpect(status().isOk());

        // Validate the Province in the database
        List<Province> provinceList = provinceRepository.findAll();
        assertThat(provinceList).hasSize(databaseSizeBeforeUpdate);
        Province testProvince = provinceList.get(provinceList.size() - 1);
        assertThat(testProvince.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProvince.getCoordinates()).isEqualTo(UPDATED_COORDINATES);
        assertThat(testProvince.getHistory()).isEqualTo(UPDATED_HISTORY);
        assertThat(testProvince.getCulture()).isEqualTo(UPDATED_CULTURE);
        assertThat(testProvince.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testProvince.getCantons()).isEqualTo(UPDATED_CANTONS);
        assertThat(testProvince.getEmergencyContacts()).isEqualTo(UPDATED_EMERGENCY_CONTACTS);
    }

    @Test
    public void updateNonExistingProvince() throws Exception {
        int databaseSizeBeforeUpdate = provinceRepository.findAll().size();

        // Create the Province
        ProvinceDTO provinceDTO = provinceMapper.toDto(province);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProvinceMockMvc.perform(put("/api/provinces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provinceDTO)))
            .andExpect(status().isCreated());

        // Validate the Province in the database
        List<Province> provinceList = provinceRepository.findAll();
        assertThat(provinceList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProvince() throws Exception {
        // Initialize the database
        provinceRepository.save(province);
        int databaseSizeBeforeDelete = provinceRepository.findAll().size();

        // Get the province
        restProvinceMockMvc.perform(delete("/api/provinces/{id}", province.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Province> provinceList = provinceRepository.findAll();
        assertThat(provinceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Province.class);
        Province province1 = new Province();
        province1.setId("id1");
        Province province2 = new Province();
        province2.setId(province1.getId());
        assertThat(province1).isEqualTo(province2);
        province2.setId("id2");
        assertThat(province1).isNotEqualTo(province2);
        province1.setId(null);
        assertThat(province1).isNotEqualTo(province2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProvinceDTO.class);
        ProvinceDTO provinceDTO1 = new ProvinceDTO();
        provinceDTO1.setId("id1");
        ProvinceDTO provinceDTO2 = new ProvinceDTO();
        assertThat(provinceDTO1).isNotEqualTo(provinceDTO2);
        provinceDTO2.setId(provinceDTO1.getId());
        assertThat(provinceDTO1).isEqualTo(provinceDTO2);
        provinceDTO2.setId("id2");
        assertThat(provinceDTO1).isNotEqualTo(provinceDTO2);
        provinceDTO1.setId(null);
        assertThat(provinceDTO1).isNotEqualTo(provinceDTO2);
    }
}
