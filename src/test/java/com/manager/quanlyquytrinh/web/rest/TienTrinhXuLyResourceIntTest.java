package com.manager.quanlyquytrinh.web.rest;

import com.manager.quanlyquytrinh.QuanlyquytrinhApp;

import com.manager.quanlyquytrinh.domain.TienTrinhXuLy;
import com.manager.quanlyquytrinh.repository.TienTrinhXuLyRepository;
import com.manager.quanlyquytrinh.service.TienTrinhXuLyService;
import com.manager.quanlyquytrinh.service.dto.TienTrinhXuLyDTO;
import com.manager.quanlyquytrinh.service.mapper.TienTrinhXuLyMapper;
import com.manager.quanlyquytrinh.web.rest.errors.ExceptionTranslator;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.manager.quanlyquytrinh.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TienTrinhXuLyResource REST controller.
 *
 * @see TienTrinhXuLyResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuanlyquytrinhApp.class)
public class TienTrinhXuLyResourceIntTest {

    private static final String DEFAULT_BATDAU_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BATDAU_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_KET_THUC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_KET_THUC_CODE = "BBBBBBBBBB";

    @Autowired
    private TienTrinhXuLyRepository tienTrinhXuLyRepository;

    @Autowired
    private TienTrinhXuLyMapper tienTrinhXuLyMapper;

    @Autowired
    private TienTrinhXuLyService tienTrinhXuLyService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restTienTrinhXuLyMockMvc;

    private TienTrinhXuLy tienTrinhXuLy;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TienTrinhXuLyResource tienTrinhXuLyResource = new TienTrinhXuLyResource(tienTrinhXuLyService);
        this.restTienTrinhXuLyMockMvc = MockMvcBuilders.standaloneSetup(tienTrinhXuLyResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TienTrinhXuLy createEntity(EntityManager em) {
        TienTrinhXuLy tienTrinhXuLy = new TienTrinhXuLy()
            .batdauCode(DEFAULT_BATDAU_CODE)
            .ketThucCode(DEFAULT_KET_THUC_CODE);
        return tienTrinhXuLy;
    }

    @Before
    public void initTest() {
        tienTrinhXuLy = createEntity(em);
    }

    @Test
    @Transactional
    public void createTienTrinhXuLy() throws Exception {
        int databaseSizeBeforeCreate = tienTrinhXuLyRepository.findAll().size();

        // Create the TienTrinhXuLy
        TienTrinhXuLyDTO tienTrinhXuLyDTO = tienTrinhXuLyMapper.toDto(tienTrinhXuLy);
        restTienTrinhXuLyMockMvc.perform(post("/api/tien-trinh-xu-lies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhXuLyDTO)))
            .andExpect(status().isCreated());

        // Validate the TienTrinhXuLy in the database
        List<TienTrinhXuLy> tienTrinhXuLyList = tienTrinhXuLyRepository.findAll();
        assertThat(tienTrinhXuLyList).hasSize(databaseSizeBeforeCreate + 1);
        TienTrinhXuLy testTienTrinhXuLy = tienTrinhXuLyList.get(tienTrinhXuLyList.size() - 1);
        assertThat(testTienTrinhXuLy.getBatdauCode()).isEqualTo(DEFAULT_BATDAU_CODE);
        assertThat(testTienTrinhXuLy.getKetThucCode()).isEqualTo(DEFAULT_KET_THUC_CODE);
    }

    @Test
    @Transactional
    public void createTienTrinhXuLyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tienTrinhXuLyRepository.findAll().size();

        // Create the TienTrinhXuLy with an existing ID
        tienTrinhXuLy.setId(1L);
        TienTrinhXuLyDTO tienTrinhXuLyDTO = tienTrinhXuLyMapper.toDto(tienTrinhXuLy);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTienTrinhXuLyMockMvc.perform(post("/api/tien-trinh-xu-lies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhXuLyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TienTrinhXuLy in the database
        List<TienTrinhXuLy> tienTrinhXuLyList = tienTrinhXuLyRepository.findAll();
        assertThat(tienTrinhXuLyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkBatdauCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tienTrinhXuLyRepository.findAll().size();
        // set the field null
        tienTrinhXuLy.setBatdauCode(null);

        // Create the TienTrinhXuLy, which fails.
        TienTrinhXuLyDTO tienTrinhXuLyDTO = tienTrinhXuLyMapper.toDto(tienTrinhXuLy);

        restTienTrinhXuLyMockMvc.perform(post("/api/tien-trinh-xu-lies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhXuLyDTO)))
            .andExpect(status().isBadRequest());

        List<TienTrinhXuLy> tienTrinhXuLyList = tienTrinhXuLyRepository.findAll();
        assertThat(tienTrinhXuLyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKetThucCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tienTrinhXuLyRepository.findAll().size();
        // set the field null
        tienTrinhXuLy.setKetThucCode(null);

        // Create the TienTrinhXuLy, which fails.
        TienTrinhXuLyDTO tienTrinhXuLyDTO = tienTrinhXuLyMapper.toDto(tienTrinhXuLy);

        restTienTrinhXuLyMockMvc.perform(post("/api/tien-trinh-xu-lies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhXuLyDTO)))
            .andExpect(status().isBadRequest());

        List<TienTrinhXuLy> tienTrinhXuLyList = tienTrinhXuLyRepository.findAll();
        assertThat(tienTrinhXuLyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTienTrinhXuLies() throws Exception {
        // Initialize the database
        tienTrinhXuLyRepository.saveAndFlush(tienTrinhXuLy);

        // Get all the tienTrinhXuLyList
        restTienTrinhXuLyMockMvc.perform(get("/api/tien-trinh-xu-lies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tienTrinhXuLy.getId().intValue())))
            .andExpect(jsonPath("$.[*].batdauCode").value(hasItem(DEFAULT_BATDAU_CODE.toString())))
            .andExpect(jsonPath("$.[*].ketThucCode").value(hasItem(DEFAULT_KET_THUC_CODE.toString())));
    }
    
    @Test
    @Transactional
    public void getTienTrinhXuLy() throws Exception {
        // Initialize the database
        tienTrinhXuLyRepository.saveAndFlush(tienTrinhXuLy);

        // Get the tienTrinhXuLy
        restTienTrinhXuLyMockMvc.perform(get("/api/tien-trinh-xu-lies/{id}", tienTrinhXuLy.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tienTrinhXuLy.getId().intValue()))
            .andExpect(jsonPath("$.batdauCode").value(DEFAULT_BATDAU_CODE.toString()))
            .andExpect(jsonPath("$.ketThucCode").value(DEFAULT_KET_THUC_CODE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTienTrinhXuLy() throws Exception {
        // Get the tienTrinhXuLy
        restTienTrinhXuLyMockMvc.perform(get("/api/tien-trinh-xu-lies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTienTrinhXuLy() throws Exception {
        // Initialize the database
        tienTrinhXuLyRepository.saveAndFlush(tienTrinhXuLy);

        int databaseSizeBeforeUpdate = tienTrinhXuLyRepository.findAll().size();

        // Update the tienTrinhXuLy
        TienTrinhXuLy updatedTienTrinhXuLy = tienTrinhXuLyRepository.findById(tienTrinhXuLy.getId()).get();
        // Disconnect from session so that the updates on updatedTienTrinhXuLy are not directly saved in db
        em.detach(updatedTienTrinhXuLy);
        updatedTienTrinhXuLy
            .batdauCode(UPDATED_BATDAU_CODE)
            .ketThucCode(UPDATED_KET_THUC_CODE);
        TienTrinhXuLyDTO tienTrinhXuLyDTO = tienTrinhXuLyMapper.toDto(updatedTienTrinhXuLy);

        restTienTrinhXuLyMockMvc.perform(put("/api/tien-trinh-xu-lies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhXuLyDTO)))
            .andExpect(status().isOk());

        // Validate the TienTrinhXuLy in the database
        List<TienTrinhXuLy> tienTrinhXuLyList = tienTrinhXuLyRepository.findAll();
        assertThat(tienTrinhXuLyList).hasSize(databaseSizeBeforeUpdate);
        TienTrinhXuLy testTienTrinhXuLy = tienTrinhXuLyList.get(tienTrinhXuLyList.size() - 1);
        assertThat(testTienTrinhXuLy.getBatdauCode()).isEqualTo(UPDATED_BATDAU_CODE);
        assertThat(testTienTrinhXuLy.getKetThucCode()).isEqualTo(UPDATED_KET_THUC_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingTienTrinhXuLy() throws Exception {
        int databaseSizeBeforeUpdate = tienTrinhXuLyRepository.findAll().size();

        // Create the TienTrinhXuLy
        TienTrinhXuLyDTO tienTrinhXuLyDTO = tienTrinhXuLyMapper.toDto(tienTrinhXuLy);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTienTrinhXuLyMockMvc.perform(put("/api/tien-trinh-xu-lies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhXuLyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TienTrinhXuLy in the database
        List<TienTrinhXuLy> tienTrinhXuLyList = tienTrinhXuLyRepository.findAll();
        assertThat(tienTrinhXuLyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTienTrinhXuLy() throws Exception {
        // Initialize the database
        tienTrinhXuLyRepository.saveAndFlush(tienTrinhXuLy);

        int databaseSizeBeforeDelete = tienTrinhXuLyRepository.findAll().size();

        // Delete the tienTrinhXuLy
        restTienTrinhXuLyMockMvc.perform(delete("/api/tien-trinh-xu-lies/{id}", tienTrinhXuLy.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TienTrinhXuLy> tienTrinhXuLyList = tienTrinhXuLyRepository.findAll();
        assertThat(tienTrinhXuLyList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TienTrinhXuLy.class);
        TienTrinhXuLy tienTrinhXuLy1 = new TienTrinhXuLy();
        tienTrinhXuLy1.setId(1L);
        TienTrinhXuLy tienTrinhXuLy2 = new TienTrinhXuLy();
        tienTrinhXuLy2.setId(tienTrinhXuLy1.getId());
        assertThat(tienTrinhXuLy1).isEqualTo(tienTrinhXuLy2);
        tienTrinhXuLy2.setId(2L);
        assertThat(tienTrinhXuLy1).isNotEqualTo(tienTrinhXuLy2);
        tienTrinhXuLy1.setId(null);
        assertThat(tienTrinhXuLy1).isNotEqualTo(tienTrinhXuLy2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TienTrinhXuLyDTO.class);
        TienTrinhXuLyDTO tienTrinhXuLyDTO1 = new TienTrinhXuLyDTO();
        tienTrinhXuLyDTO1.setId(1L);
        TienTrinhXuLyDTO tienTrinhXuLyDTO2 = new TienTrinhXuLyDTO();
        assertThat(tienTrinhXuLyDTO1).isNotEqualTo(tienTrinhXuLyDTO2);
        tienTrinhXuLyDTO2.setId(tienTrinhXuLyDTO1.getId());
        assertThat(tienTrinhXuLyDTO1).isEqualTo(tienTrinhXuLyDTO2);
        tienTrinhXuLyDTO2.setId(2L);
        assertThat(tienTrinhXuLyDTO1).isNotEqualTo(tienTrinhXuLyDTO2);
        tienTrinhXuLyDTO1.setId(null);
        assertThat(tienTrinhXuLyDTO1).isNotEqualTo(tienTrinhXuLyDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(tienTrinhXuLyMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(tienTrinhXuLyMapper.fromId(null)).isNull();
    }
}
