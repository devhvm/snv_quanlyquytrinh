package com.manager.quanlyquytrinh.web.rest;

import com.manager.quanlyquytrinh.QuanlyquytrinhApp;

import com.manager.quanlyquytrinh.domain.TienTrinh;
import com.manager.quanlyquytrinh.repository.TienTrinhRepository;
import com.manager.quanlyquytrinh.service.TienTrinhService;
import com.manager.quanlyquytrinh.service.dto.TienTrinhDTO;
import com.manager.quanlyquytrinh.service.mapper.TienTrinhMapper;
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
 * Test class for the TienTrinhResource REST controller.
 *
 * @see TienTrinhResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuanlyquytrinhApp.class)
public class TienTrinhResourceIntTest {

    private static final String DEFAULT_MENU_ITEM_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MENU_ITEM_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ICON = "AAAAAAAAAA";
    private static final String UPDATED_ICON = "BBBBBBBBBB";

    @Autowired
    private TienTrinhRepository tienTrinhRepository;

    @Autowired
    private TienTrinhMapper tienTrinhMapper;

    @Autowired
    private TienTrinhService tienTrinhService;

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

    private MockMvc restTienTrinhMockMvc;

    private TienTrinh tienTrinh;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TienTrinhResource tienTrinhResource = new TienTrinhResource(tienTrinhService);
        this.restTienTrinhMockMvc = MockMvcBuilders.standaloneSetup(tienTrinhResource)
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
    public static TienTrinh createEntity(EntityManager em) {
        TienTrinh tienTrinh = new TienTrinh()
            .menuItemCode(DEFAULT_MENU_ITEM_CODE)
            .name(DEFAULT_NAME)
            .icon(DEFAULT_ICON);
        return tienTrinh;
    }

    @Before
    public void initTest() {
        tienTrinh = createEntity(em);
    }

    @Test
    @Transactional
    public void createTienTrinh() throws Exception {
        int databaseSizeBeforeCreate = tienTrinhRepository.findAll().size();

        // Create the TienTrinh
        TienTrinhDTO tienTrinhDTO = tienTrinhMapper.toDto(tienTrinh);
        restTienTrinhMockMvc.perform(post("/api/tien-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhDTO)))
            .andExpect(status().isCreated());

        // Validate the TienTrinh in the database
        List<TienTrinh> tienTrinhList = tienTrinhRepository.findAll();
        assertThat(tienTrinhList).hasSize(databaseSizeBeforeCreate + 1);
        TienTrinh testTienTrinh = tienTrinhList.get(tienTrinhList.size() - 1);
        assertThat(testTienTrinh.getMenuItemCode()).isEqualTo(DEFAULT_MENU_ITEM_CODE);
        assertThat(testTienTrinh.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTienTrinh.getIcon()).isEqualTo(DEFAULT_ICON);
    }

    @Test
    @Transactional
    public void createTienTrinhWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tienTrinhRepository.findAll().size();

        // Create the TienTrinh with an existing ID
        tienTrinh.setId(1L);
        TienTrinhDTO tienTrinhDTO = tienTrinhMapper.toDto(tienTrinh);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTienTrinhMockMvc.perform(post("/api/tien-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TienTrinh in the database
        List<TienTrinh> tienTrinhList = tienTrinhRepository.findAll();
        assertThat(tienTrinhList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkMenuItemCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tienTrinhRepository.findAll().size();
        // set the field null
        tienTrinh.setMenuItemCode(null);

        // Create the TienTrinh, which fails.
        TienTrinhDTO tienTrinhDTO = tienTrinhMapper.toDto(tienTrinh);

        restTienTrinhMockMvc.perform(post("/api/tien-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhDTO)))
            .andExpect(status().isBadRequest());

        List<TienTrinh> tienTrinhList = tienTrinhRepository.findAll();
        assertThat(tienTrinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = tienTrinhRepository.findAll().size();
        // set the field null
        tienTrinh.setName(null);

        // Create the TienTrinh, which fails.
        TienTrinhDTO tienTrinhDTO = tienTrinhMapper.toDto(tienTrinh);

        restTienTrinhMockMvc.perform(post("/api/tien-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhDTO)))
            .andExpect(status().isBadRequest());

        List<TienTrinh> tienTrinhList = tienTrinhRepository.findAll();
        assertThat(tienTrinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIconIsRequired() throws Exception {
        int databaseSizeBeforeTest = tienTrinhRepository.findAll().size();
        // set the field null
        tienTrinh.setIcon(null);

        // Create the TienTrinh, which fails.
        TienTrinhDTO tienTrinhDTO = tienTrinhMapper.toDto(tienTrinh);

        restTienTrinhMockMvc.perform(post("/api/tien-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhDTO)))
            .andExpect(status().isBadRequest());

        List<TienTrinh> tienTrinhList = tienTrinhRepository.findAll();
        assertThat(tienTrinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTienTrinhs() throws Exception {
        // Initialize the database
        tienTrinhRepository.saveAndFlush(tienTrinh);

        // Get all the tienTrinhList
        restTienTrinhMockMvc.perform(get("/api/tien-trinhs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tienTrinh.getId().intValue())))
            .andExpect(jsonPath("$.[*].menuItemCode").value(hasItem(DEFAULT_MENU_ITEM_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].icon").value(hasItem(DEFAULT_ICON.toString())));
    }
    
    @Test
    @Transactional
    public void getTienTrinh() throws Exception {
        // Initialize the database
        tienTrinhRepository.saveAndFlush(tienTrinh);

        // Get the tienTrinh
        restTienTrinhMockMvc.perform(get("/api/tien-trinhs/{id}", tienTrinh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tienTrinh.getId().intValue()))
            .andExpect(jsonPath("$.menuItemCode").value(DEFAULT_MENU_ITEM_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.icon").value(DEFAULT_ICON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTienTrinh() throws Exception {
        // Get the tienTrinh
        restTienTrinhMockMvc.perform(get("/api/tien-trinhs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTienTrinh() throws Exception {
        // Initialize the database
        tienTrinhRepository.saveAndFlush(tienTrinh);

        int databaseSizeBeforeUpdate = tienTrinhRepository.findAll().size();

        // Update the tienTrinh
        TienTrinh updatedTienTrinh = tienTrinhRepository.findById(tienTrinh.getId()).get();
        // Disconnect from session so that the updates on updatedTienTrinh are not directly saved in db
        em.detach(updatedTienTrinh);
        updatedTienTrinh
            .menuItemCode(UPDATED_MENU_ITEM_CODE)
            .name(UPDATED_NAME)
            .icon(UPDATED_ICON);
        TienTrinhDTO tienTrinhDTO = tienTrinhMapper.toDto(updatedTienTrinh);

        restTienTrinhMockMvc.perform(put("/api/tien-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhDTO)))
            .andExpect(status().isOk());

        // Validate the TienTrinh in the database
        List<TienTrinh> tienTrinhList = tienTrinhRepository.findAll();
        assertThat(tienTrinhList).hasSize(databaseSizeBeforeUpdate);
        TienTrinh testTienTrinh = tienTrinhList.get(tienTrinhList.size() - 1);
        assertThat(testTienTrinh.getMenuItemCode()).isEqualTo(UPDATED_MENU_ITEM_CODE);
        assertThat(testTienTrinh.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTienTrinh.getIcon()).isEqualTo(UPDATED_ICON);
    }

    @Test
    @Transactional
    public void updateNonExistingTienTrinh() throws Exception {
        int databaseSizeBeforeUpdate = tienTrinhRepository.findAll().size();

        // Create the TienTrinh
        TienTrinhDTO tienTrinhDTO = tienTrinhMapper.toDto(tienTrinh);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTienTrinhMockMvc.perform(put("/api/tien-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TienTrinh in the database
        List<TienTrinh> tienTrinhList = tienTrinhRepository.findAll();
        assertThat(tienTrinhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTienTrinh() throws Exception {
        // Initialize the database
        tienTrinhRepository.saveAndFlush(tienTrinh);

        int databaseSizeBeforeDelete = tienTrinhRepository.findAll().size();

        // Delete the tienTrinh
        restTienTrinhMockMvc.perform(delete("/api/tien-trinhs/{id}", tienTrinh.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TienTrinh> tienTrinhList = tienTrinhRepository.findAll();
        assertThat(tienTrinhList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TienTrinh.class);
        TienTrinh tienTrinh1 = new TienTrinh();
        tienTrinh1.setId(1L);
        TienTrinh tienTrinh2 = new TienTrinh();
        tienTrinh2.setId(tienTrinh1.getId());
        assertThat(tienTrinh1).isEqualTo(tienTrinh2);
        tienTrinh2.setId(2L);
        assertThat(tienTrinh1).isNotEqualTo(tienTrinh2);
        tienTrinh1.setId(null);
        assertThat(tienTrinh1).isNotEqualTo(tienTrinh2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TienTrinhDTO.class);
        TienTrinhDTO tienTrinhDTO1 = new TienTrinhDTO();
        tienTrinhDTO1.setId(1L);
        TienTrinhDTO tienTrinhDTO2 = new TienTrinhDTO();
        assertThat(tienTrinhDTO1).isNotEqualTo(tienTrinhDTO2);
        tienTrinhDTO2.setId(tienTrinhDTO1.getId());
        assertThat(tienTrinhDTO1).isEqualTo(tienTrinhDTO2);
        tienTrinhDTO2.setId(2L);
        assertThat(tienTrinhDTO1).isNotEqualTo(tienTrinhDTO2);
        tienTrinhDTO1.setId(null);
        assertThat(tienTrinhDTO1).isNotEqualTo(tienTrinhDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(tienTrinhMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(tienTrinhMapper.fromId(null)).isNull();
    }
}
