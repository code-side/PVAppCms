package com.codeside.pvapp.web.rest;

import com.codeside.pvapp.PvAppCmsApp;

import com.codeside.pvapp.domain.PVAppUser;
import com.codeside.pvapp.repository.PVAppUserRepository;
import com.codeside.pvapp.service.dto.PVAppUserDTO;
import com.codeside.pvapp.service.mapper.PVAppUserMapper;
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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PVAppUserResource REST controller.
 *
 * @see PVAppUserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PvAppCmsApp.class)
public class PVAppUserResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_REGISTRATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REGISTRATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_BIRTHDAY = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTHDAY = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NATIONALITY = "AAAAAAAAAA";
    private static final String UPDATED_NATIONALITY = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_PHOTO = "AAAAAAAAAA";
    private static final String UPDATED_PHOTO = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final String DEFAULT_FAVORITE_LIST = "AAAAAAAAAA";
    private static final String UPDATED_FAVORITE_LIST = "BBBBBBBBBB";

    private static final String DEFAULT_ACHIEVEMENTS = "AAAAAAAAAA";
    private static final String UPDATED_ACHIEVEMENTS = "BBBBBBBBBB";

    @Autowired
    private PVAppUserRepository pVAppUserRepository;

    @Autowired
    private PVAppUserMapper pVAppUserMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restPVAppUserMockMvc;

    private PVAppUser pVAppUser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PVAppUserResource pVAppUserResource = new PVAppUserResource(pVAppUserRepository, pVAppUserMapper);
        this.restPVAppUserMockMvc = MockMvcBuilders.standaloneSetup(pVAppUserResource)
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
    public static PVAppUser createEntity() {
        PVAppUser pVAppUser = new PVAppUser()
            .name(DEFAULT_NAME)
            .email(DEFAULT_EMAIL)
            .password(DEFAULT_PASSWORD)
            .registrationDate(DEFAULT_REGISTRATION_DATE)
            .birthday(DEFAULT_BIRTHDAY)
            .nationality(DEFAULT_NATIONALITY)
            .gender(DEFAULT_GENDER)
            .photo(DEFAULT_PHOTO)
            .status(DEFAULT_STATUS)
            .favoriteList(DEFAULT_FAVORITE_LIST)
            .achievements(DEFAULT_ACHIEVEMENTS);
        return pVAppUser;
    }

    @Before
    public void initTest() {
        pVAppUserRepository.deleteAll();
        pVAppUser = createEntity();
    }

    @Test
    public void createPVAppUser() throws Exception {
        int databaseSizeBeforeCreate = pVAppUserRepository.findAll().size();

        // Create the PVAppUser
        PVAppUserDTO pVAppUserDTO = pVAppUserMapper.toDto(pVAppUser);
        restPVAppUserMockMvc.perform(post("/api/p-v-app-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pVAppUserDTO)))
            .andExpect(status().isCreated());

        // Validate the PVAppUser in the database
        List<PVAppUser> pVAppUserList = pVAppUserRepository.findAll();
        assertThat(pVAppUserList).hasSize(databaseSizeBeforeCreate + 1);
        PVAppUser testPVAppUser = pVAppUserList.get(pVAppUserList.size() - 1);
        assertThat(testPVAppUser.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPVAppUser.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testPVAppUser.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testPVAppUser.getRegistrationDate()).isEqualTo(DEFAULT_REGISTRATION_DATE);
        assertThat(testPVAppUser.getBirthday()).isEqualTo(DEFAULT_BIRTHDAY);
        assertThat(testPVAppUser.getNationality()).isEqualTo(DEFAULT_NATIONALITY);
        assertThat(testPVAppUser.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testPVAppUser.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testPVAppUser.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPVAppUser.getFavoriteList()).isEqualTo(DEFAULT_FAVORITE_LIST);
        assertThat(testPVAppUser.getAchievements()).isEqualTo(DEFAULT_ACHIEVEMENTS);
    }

    @Test
    public void createPVAppUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pVAppUserRepository.findAll().size();

        // Create the PVAppUser with an existing ID
        pVAppUser.setId("existing_id");
        PVAppUserDTO pVAppUserDTO = pVAppUserMapper.toDto(pVAppUser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPVAppUserMockMvc.perform(post("/api/p-v-app-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pVAppUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<PVAppUser> pVAppUserList = pVAppUserRepository.findAll();
        assertThat(pVAppUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllPVAppUsers() throws Exception {
        // Initialize the database
        pVAppUserRepository.save(pVAppUser);

        // Get all the pVAppUserList
        restPVAppUserMockMvc.perform(get("/api/p-v-app-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pVAppUser.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].registrationDate").value(hasItem(DEFAULT_REGISTRATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].birthday").value(hasItem(DEFAULT_BIRTHDAY.toString())))
            .andExpect(jsonPath("$.[*].nationality").value(hasItem(DEFAULT_NATIONALITY.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(DEFAULT_PHOTO.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].favoriteList").value(hasItem(DEFAULT_FAVORITE_LIST.toString())))
            .andExpect(jsonPath("$.[*].achievements").value(hasItem(DEFAULT_ACHIEVEMENTS.toString())));
    }

    @Test
    public void getPVAppUser() throws Exception {
        // Initialize the database
        pVAppUserRepository.save(pVAppUser);

        // Get the pVAppUser
        restPVAppUserMockMvc.perform(get("/api/p-v-app-users/{id}", pVAppUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pVAppUser.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()))
            .andExpect(jsonPath("$.registrationDate").value(DEFAULT_REGISTRATION_DATE.toString()))
            .andExpect(jsonPath("$.birthday").value(DEFAULT_BIRTHDAY.toString()))
            .andExpect(jsonPath("$.nationality").value(DEFAULT_NATIONALITY.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.photo").value(DEFAULT_PHOTO.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.favoriteList").value(DEFAULT_FAVORITE_LIST.toString()))
            .andExpect(jsonPath("$.achievements").value(DEFAULT_ACHIEVEMENTS.toString()));
    }

    @Test
    public void getNonExistingPVAppUser() throws Exception {
        // Get the pVAppUser
        restPVAppUserMockMvc.perform(get("/api/p-v-app-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updatePVAppUser() throws Exception {
        // Initialize the database
        pVAppUserRepository.save(pVAppUser);
        int databaseSizeBeforeUpdate = pVAppUserRepository.findAll().size();

        // Update the pVAppUser
        PVAppUser updatedPVAppUser = pVAppUserRepository.findOne(pVAppUser.getId());
        updatedPVAppUser
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .password(UPDATED_PASSWORD)
            .registrationDate(UPDATED_REGISTRATION_DATE)
            .birthday(UPDATED_BIRTHDAY)
            .nationality(UPDATED_NATIONALITY)
            .gender(UPDATED_GENDER)
            .photo(UPDATED_PHOTO)
            .status(UPDATED_STATUS)
            .favoriteList(UPDATED_FAVORITE_LIST)
            .achievements(UPDATED_ACHIEVEMENTS);
        PVAppUserDTO pVAppUserDTO = pVAppUserMapper.toDto(updatedPVAppUser);

        restPVAppUserMockMvc.perform(put("/api/p-v-app-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pVAppUserDTO)))
            .andExpect(status().isOk());

        // Validate the PVAppUser in the database
        List<PVAppUser> pVAppUserList = pVAppUserRepository.findAll();
        assertThat(pVAppUserList).hasSize(databaseSizeBeforeUpdate);
        PVAppUser testPVAppUser = pVAppUserList.get(pVAppUserList.size() - 1);
        assertThat(testPVAppUser.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPVAppUser.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testPVAppUser.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testPVAppUser.getRegistrationDate()).isEqualTo(UPDATED_REGISTRATION_DATE);
        assertThat(testPVAppUser.getBirthday()).isEqualTo(UPDATED_BIRTHDAY);
        assertThat(testPVAppUser.getNationality()).isEqualTo(UPDATED_NATIONALITY);
        assertThat(testPVAppUser.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testPVAppUser.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testPVAppUser.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPVAppUser.getFavoriteList()).isEqualTo(UPDATED_FAVORITE_LIST);
        assertThat(testPVAppUser.getAchievements()).isEqualTo(UPDATED_ACHIEVEMENTS);
    }

    @Test
    public void updateNonExistingPVAppUser() throws Exception {
        int databaseSizeBeforeUpdate = pVAppUserRepository.findAll().size();

        // Create the PVAppUser
        PVAppUserDTO pVAppUserDTO = pVAppUserMapper.toDto(pVAppUser);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPVAppUserMockMvc.perform(put("/api/p-v-app-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pVAppUserDTO)))
            .andExpect(status().isCreated());

        // Validate the PVAppUser in the database
        List<PVAppUser> pVAppUserList = pVAppUserRepository.findAll();
        assertThat(pVAppUserList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deletePVAppUser() throws Exception {
        // Initialize the database
        pVAppUserRepository.save(pVAppUser);
        int databaseSizeBeforeDelete = pVAppUserRepository.findAll().size();

        // Get the pVAppUser
        restPVAppUserMockMvc.perform(delete("/api/p-v-app-users/{id}", pVAppUser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PVAppUser> pVAppUserList = pVAppUserRepository.findAll();
        assertThat(pVAppUserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PVAppUser.class);
        PVAppUser pVAppUser1 = new PVAppUser();
        pVAppUser1.setId("id1");
        PVAppUser pVAppUser2 = new PVAppUser();
        pVAppUser2.setId(pVAppUser1.getId());
        assertThat(pVAppUser1).isEqualTo(pVAppUser2);
        pVAppUser2.setId("id2");
        assertThat(pVAppUser1).isNotEqualTo(pVAppUser2);
        pVAppUser1.setId(null);
        assertThat(pVAppUser1).isNotEqualTo(pVAppUser2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PVAppUserDTO.class);
        PVAppUserDTO pVAppUserDTO1 = new PVAppUserDTO();
        pVAppUserDTO1.setId("id1");
        PVAppUserDTO pVAppUserDTO2 = new PVAppUserDTO();
        assertThat(pVAppUserDTO1).isNotEqualTo(pVAppUserDTO2);
        pVAppUserDTO2.setId(pVAppUserDTO1.getId());
        assertThat(pVAppUserDTO1).isEqualTo(pVAppUserDTO2);
        pVAppUserDTO2.setId("id2");
        assertThat(pVAppUserDTO1).isNotEqualTo(pVAppUserDTO2);
        pVAppUserDTO1.setId(null);
        assertThat(pVAppUserDTO1).isNotEqualTo(pVAppUserDTO2);
    }
}
