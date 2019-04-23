package com.manager.quanlyquytrinh.web.rest;

import com.manager.quanlyquytrinh.QuanlyquytrinhApp;

import com.manager.quanlyquytrinh.domain.LoaiQuyTrinh;
import com.manager.quanlyquytrinh.repository.LoaiQuyTrinhRepository;
import com.manager.quanlyquytrinh.service.LoaiQuyTrinhService;
import com.manager.quanlyquytrinh.service.dto.LoaiQuyTrinhDTO;
import com.manager.quanlyquytrinh.service.mapper.LoaiQuyTrinhMapper;
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
 * Test class for the LoaiQuyTrinhResource REST controller.
 *
 * @see LoaiQuyTrinhResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuanlyquytrinhApp.class)
public class LoaiQuyTrinhResourceIntTest {

    private static final String DEFAULT_LOAI_QUY_TRINH_CODE = "AAAAAAAAAA";
    private static final String UPDATED_LOAI_QUY_TRINH_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_METHOD_NAME = "AAAAAAAAAA";
    private static final String UPDATED_METHOD_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ENTITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ENTITY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_NAME = "BBBBBBBBBB";

    @Autowired
    private LoaiQuyTrinhRepository loaiQuyTrinhRepository;

    @Autowired
    private LoaiQuyTrinhMapper loaiQuyTrinhMapper;

    @Autowired
    private LoaiQuyTrinhService loaiQuyTrinhService;

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

    private MockMvc restLoaiQuyTrinhMockMvc;

    private LoaiQuyTrinh loaiQuyTrinh;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LoaiQuyTrinhResource loaiQuyTrinhResource = new LoaiQuyTrinhResource(loaiQuyTrinhService);
        this.restLoaiQuyTrinhMockMvc = MockMvcBuilders.standaloneSetup(loaiQuyTrinhResource)
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
    public static LoaiQuyTrinh createEntity(EntityManager em) {
        LoaiQuyTrinh loaiQuyTrinh = new LoaiQuyTrinh()
            .loaiQuyTrinhCode(DEFAULT_LOAI_QUY_TRINH_CODE)
            .methodName(DEFAULT_METHOD_NAME)
            .entityName(DEFAULT_ENTITY_NAME)
            .serviceName(DEFAULT_SERVICE_NAME);
        return loaiQuyTrinh;
    }

    @Before
    public void initTest() {
        loaiQuyTrinh = createEntity(em);
    }

    @Test
    @Transactional
    public void createLoaiQuyTrinh() throws Exception {
        int databaseSizeBeforeCreate = loaiQuyTrinhRepository.findAll().size();

        // Create the LoaiQuyTrinh
        LoaiQuyTrinhDTO loaiQuyTrinhDTO = loaiQuyTrinhMapper.toDto(loaiQuyTrinh);
        restLoaiQuyTrinhMockMvc.perform(post("/api/loai-quy-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loaiQuyTrinhDTO)))
            .andExpect(status().isCreated());

        // Validate the LoaiQuyTrinh in the database
        List<LoaiQuyTrinh> loaiQuyTrinhList = loaiQuyTrinhRepository.findAll();
        assertThat(loaiQuyTrinhList).hasSize(databaseSizeBeforeCreate + 1);
        LoaiQuyTrinh testLoaiQuyTrinh = loaiQuyTrinhList.get(loaiQuyTrinhList.size() - 1);
        assertThat(testLoaiQuyTrinh.getLoaiQuyTrinhCode()).isEqualTo(DEFAULT_LOAI_QUY_TRINH_CODE);
        assertThat(testLoaiQuyTrinh.getMethodName()).isEqualTo(DEFAULT_METHOD_NAME);
        assertThat(testLoaiQuyTrinh.getEntityName()).isEqualTo(DEFAULT_ENTITY_NAME);
        assertThat(testLoaiQuyTrinh.getServiceName()).isEqualTo(DEFAULT_SERVICE_NAME);
    }

    @Test
    @Transactional
    public void createLoaiQuyTrinhWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = loaiQuyTrinhRepository.findAll().size();

        // Create the LoaiQuyTrinh with an existing ID
        loaiQuyTrinh.setId(1L);
        LoaiQuyTrinhDTO loaiQuyTrinhDTO = loaiQuyTrinhMapper.toDto(loaiQuyTrinh);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoaiQuyTrinhMockMvc.perform(post("/api/loai-quy-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loaiQuyTrinhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LoaiQuyTrinh in the database
        List<LoaiQuyTrinh> loaiQuyTrinhList = loaiQuyTrinhRepository.findAll();
        assertThat(loaiQuyTrinhList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkLoaiQuyTrinhCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = loaiQuyTrinhRepository.findAll().size();
        // set the field null
        loaiQuyTrinh.setLoaiQuyTrinhCode(null);

        // Create the LoaiQuyTrinh, which fails.
        LoaiQuyTrinhDTO loaiQuyTrinhDTO = loaiQuyTrinhMapper.toDto(loaiQuyTrinh);

        restLoaiQuyTrinhMockMvc.perform(post("/api/loai-quy-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loaiQuyTrinhDTO)))
            .andExpect(status().isBadRequest());

        List<LoaiQuyTrinh> loaiQuyTrinhList = loaiQuyTrinhRepository.findAll();
        assertThat(loaiQuyTrinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLoaiQuyTrinhs() throws Exception {
        // Initialize the database
        loaiQuyTrinhRepository.saveAndFlush(loaiQuyTrinh);

        // Get all the loaiQuyTrinhList
        restLoaiQuyTrinhMockMvc.perform(get("/api/loai-quy-trinhs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loaiQuyTrinh.getId().intValue())))
            .andExpect(jsonPath("$.[*].loaiQuyTrinhCode").value(hasItem(DEFAULT_LOAI_QUY_TRINH_CODE.toString())))
            .andExpect(jsonPath("$.[*].methodName").value(hasItem(DEFAULT_METHOD_NAME.toString())))
            .andExpect(jsonPath("$.[*].entityName").value(hasItem(DEFAULT_ENTITY_NAME.toString())))
            .andExpect(jsonPath("$.[*].serviceName").value(hasItem(DEFAULT_SERVICE_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getLoaiQuyTrinh() throws Exception {
        // Initialize the database
        loaiQuyTrinhRepository.saveAndFlush(loaiQuyTrinh);

        // Get the loaiQuyTrinh
        restLoaiQuyTrinhMockMvc.perform(get("/api/loai-quy-trinhs/{id}", loaiQuyTrinh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(loaiQuyTrinh.getId().intValue()))
            .andExpect(jsonPath("$.loaiQuyTrinhCode").value(DEFAULT_LOAI_QUY_TRINH_CODE.toString()))
            .andExpect(jsonPath("$.methodName").value(DEFAULT_METHOD_NAME.toString()))
            .andExpect(jsonPath("$.entityName").value(DEFAULT_ENTITY_NAME.toString()))
            .andExpect(jsonPath("$.serviceName").value(DEFAULT_SERVICE_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLoaiQuyTrinh() throws Exception {
        // Get the loaiQuyTrinh
        restLoaiQuyTrinhMockMvc.perform(get("/api/loai-quy-trinhs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLoaiQuyTrinh() throws Exception {
        // Initialize the database
        loaiQuyTrinhRepository.saveAndFlush(loaiQuyTrinh);

        int databaseSizeBeforeUpdate = loaiQuyTrinhRepository.findAll().size();

        // Update the loaiQuyTrinh
        LoaiQuyTrinh updatedLoaiQuyTrinh = loaiQuyTrinhRepository.findById(loaiQuyTrinh.getId()).get();
        // Disconnect from session so that the updates on updatedLoaiQuyTrinh are not directly saved in db
        em.detach(updatedLoaiQuyTrinh);
        updatedLoaiQuyTrinh
            .loaiQuyTrinhCode(UPDATED_LOAI_QUY_TRINH_CODE)
            .methodName(UPDATED_METHOD_NAME)
            .entityName(UPDATED_ENTITY_NAME)
            .serviceName(UPDATED_SERVICE_NAME);
        LoaiQuyTrinhDTO loaiQuyTrinhDTO = loaiQuyTrinhMapper.toDto(updatedLoaiQuyTrinh);

        restLoaiQuyTrinhMockMvc.perform(put("/api/loai-quy-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loaiQuyTrinhDTO)))
            .andExpect(status().isOk());

        // Validate the LoaiQuyTrinh in the database
        List<LoaiQuyTrinh> loaiQuyTrinhList = loaiQuyTrinhRepository.findAll();
        assertThat(loaiQuyTrinhList).hasSize(databaseSizeBeforeUpdate);
        LoaiQuyTrinh testLoaiQuyTrinh = loaiQuyTrinhList.get(loaiQuyTrinhList.size() - 1);
        assertThat(testLoaiQuyTrinh.getLoaiQuyTrinhCode()).isEqualTo(UPDATED_LOAI_QUY_TRINH_CODE);
        assertThat(testLoaiQuyTrinh.getMethodName()).isEqualTo(UPDATED_METHOD_NAME);
        assertThat(testLoaiQuyTrinh.getEntityName()).isEqualTo(UPDATED_ENTITY_NAME);
        assertThat(testLoaiQuyTrinh.getServiceName()).isEqualTo(UPDATED_SERVICE_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingLoaiQuyTrinh() throws Exception {
        int databaseSizeBeforeUpdate = loaiQuyTrinhRepository.findAll().size();

        // Create the LoaiQuyTrinh
        LoaiQuyTrinhDTO loaiQuyTrinhDTO = loaiQuyTrinhMapper.toDto(loaiQuyTrinh);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoaiQuyTrinhMockMvc.perform(put("/api/loai-quy-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loaiQuyTrinhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LoaiQuyTrinh in the database
        List<LoaiQuyTrinh> loaiQuyTrinhList = loaiQuyTrinhRepository.findAll();
        assertThat(loaiQuyTrinhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLoaiQuyTrinh() throws Exception {
        // Initialize the database
        loaiQuyTrinhRepository.saveAndFlush(loaiQuyTrinh);

        int databaseSizeBeforeDelete = loaiQuyTrinhRepository.findAll().size();

        // Delete the loaiQuyTrinh
        restLoaiQuyTrinhMockMvc.perform(delete("/api/loai-quy-trinhs/{id}", loaiQuyTrinh.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LoaiQuyTrinh> loaiQuyTrinhList = loaiQuyTrinhRepository.findAll();
        assertThat(loaiQuyTrinhList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoaiQuyTrinh.class);
        LoaiQuyTrinh loaiQuyTrinh1 = new LoaiQuyTrinh();
        loaiQuyTrinh1.setId(1L);
        LoaiQuyTrinh loaiQuyTrinh2 = new LoaiQuyTrinh();
        loaiQuyTrinh2.setId(loaiQuyTrinh1.getId());
        assertThat(loaiQuyTrinh1).isEqualTo(loaiQuyTrinh2);
        loaiQuyTrinh2.setId(2L);
        assertThat(loaiQuyTrinh1).isNotEqualTo(loaiQuyTrinh2);
        loaiQuyTrinh1.setId(null);
        assertThat(loaiQuyTrinh1).isNotEqualTo(loaiQuyTrinh2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoaiQuyTrinhDTO.class);
        LoaiQuyTrinhDTO loaiQuyTrinhDTO1 = new LoaiQuyTrinhDTO();
        loaiQuyTrinhDTO1.setId(1L);
        LoaiQuyTrinhDTO loaiQuyTrinhDTO2 = new LoaiQuyTrinhDTO();
        assertThat(loaiQuyTrinhDTO1).isNotEqualTo(loaiQuyTrinhDTO2);
        loaiQuyTrinhDTO2.setId(loaiQuyTrinhDTO1.getId());
        assertThat(loaiQuyTrinhDTO1).isEqualTo(loaiQuyTrinhDTO2);
        loaiQuyTrinhDTO2.setId(2L);
        assertThat(loaiQuyTrinhDTO1).isNotEqualTo(loaiQuyTrinhDTO2);
        loaiQuyTrinhDTO1.setId(null);
        assertThat(loaiQuyTrinhDTO1).isNotEqualTo(loaiQuyTrinhDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(loaiQuyTrinhMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(loaiQuyTrinhMapper.fromId(null)).isNull();
    }
}
