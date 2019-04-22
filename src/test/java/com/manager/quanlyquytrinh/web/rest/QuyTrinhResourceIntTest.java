package com.manager.quanlyquytrinh.web.rest;

import com.manager.quanlyquytrinh.QuanlyquytrinhApp;

import com.manager.quanlyquytrinh.domain.QuyTrinh;
import com.manager.quanlyquytrinh.repository.QuyTrinhRepository;
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
 * Test class for the QuyTrinhResource REST controller.
 *
 * @see QuyTrinhResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuanlyquytrinhApp.class)
public class QuyTrinhResourceIntTest {

    private static final String DEFAULT_QUY_TRINH_CODE = "AAAAAAAAAA";
    private static final String UPDATED_QUY_TRINH_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private QuyTrinhRepository quyTrinhRepository;

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

    private MockMvc restQuyTrinhMockMvc;

    private QuyTrinh quyTrinh;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QuyTrinhResource quyTrinhResource = new QuyTrinhResource(quyTrinhRepository);
        this.restQuyTrinhMockMvc = MockMvcBuilders.standaloneSetup(quyTrinhResource)
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
    public static QuyTrinh createEntity(EntityManager em) {
        QuyTrinh quyTrinh = new QuyTrinh()
            .quyTrinhCode(DEFAULT_QUY_TRINH_CODE)
            .name(DEFAULT_NAME);
        return quyTrinh;
    }

    @Before
    public void initTest() {
        quyTrinh = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuyTrinh() throws Exception {
        int databaseSizeBeforeCreate = quyTrinhRepository.findAll().size();

        // Create the QuyTrinh
        restQuyTrinhMockMvc.perform(post("/api/quy-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quyTrinh)))
            .andExpect(status().isCreated());

        // Validate the QuyTrinh in the database
        List<QuyTrinh> quyTrinhList = quyTrinhRepository.findAll();
        assertThat(quyTrinhList).hasSize(databaseSizeBeforeCreate + 1);
        QuyTrinh testQuyTrinh = quyTrinhList.get(quyTrinhList.size() - 1);
        assertThat(testQuyTrinh.getQuyTrinhCode()).isEqualTo(DEFAULT_QUY_TRINH_CODE);
        assertThat(testQuyTrinh.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createQuyTrinhWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = quyTrinhRepository.findAll().size();

        // Create the QuyTrinh with an existing ID
        quyTrinh.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuyTrinhMockMvc.perform(post("/api/quy-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quyTrinh)))
            .andExpect(status().isBadRequest());

        // Validate the QuyTrinh in the database
        List<QuyTrinh> quyTrinhList = quyTrinhRepository.findAll();
        assertThat(quyTrinhList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkQuyTrinhCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = quyTrinhRepository.findAll().size();
        // set the field null
        quyTrinh.setQuyTrinhCode(null);

        // Create the QuyTrinh, which fails.

        restQuyTrinhMockMvc.perform(post("/api/quy-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quyTrinh)))
            .andExpect(status().isBadRequest());

        List<QuyTrinh> quyTrinhList = quyTrinhRepository.findAll();
        assertThat(quyTrinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = quyTrinhRepository.findAll().size();
        // set the field null
        quyTrinh.setName(null);

        // Create the QuyTrinh, which fails.

        restQuyTrinhMockMvc.perform(post("/api/quy-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quyTrinh)))
            .andExpect(status().isBadRequest());

        List<QuyTrinh> quyTrinhList = quyTrinhRepository.findAll();
        assertThat(quyTrinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllQuyTrinhs() throws Exception {
        // Initialize the database
        quyTrinhRepository.saveAndFlush(quyTrinh);

        // Get all the quyTrinhList
        restQuyTrinhMockMvc.perform(get("/api/quy-trinhs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quyTrinh.getId().intValue())))
            .andExpect(jsonPath("$.[*].quyTrinhCode").value(hasItem(DEFAULT_QUY_TRINH_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getQuyTrinh() throws Exception {
        // Initialize the database
        quyTrinhRepository.saveAndFlush(quyTrinh);

        // Get the quyTrinh
        restQuyTrinhMockMvc.perform(get("/api/quy-trinhs/{id}", quyTrinh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(quyTrinh.getId().intValue()))
            .andExpect(jsonPath("$.quyTrinhCode").value(DEFAULT_QUY_TRINH_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingQuyTrinh() throws Exception {
        // Get the quyTrinh
        restQuyTrinhMockMvc.perform(get("/api/quy-trinhs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuyTrinh() throws Exception {
        // Initialize the database
        quyTrinhRepository.saveAndFlush(quyTrinh);

        int databaseSizeBeforeUpdate = quyTrinhRepository.findAll().size();

        // Update the quyTrinh
        QuyTrinh updatedQuyTrinh = quyTrinhRepository.findById(quyTrinh.getId()).get();
        // Disconnect from session so that the updates on updatedQuyTrinh are not directly saved in db
        em.detach(updatedQuyTrinh);
        updatedQuyTrinh
            .quyTrinhCode(UPDATED_QUY_TRINH_CODE)
            .name(UPDATED_NAME);

        restQuyTrinhMockMvc.perform(put("/api/quy-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedQuyTrinh)))
            .andExpect(status().isOk());

        // Validate the QuyTrinh in the database
        List<QuyTrinh> quyTrinhList = quyTrinhRepository.findAll();
        assertThat(quyTrinhList).hasSize(databaseSizeBeforeUpdate);
        QuyTrinh testQuyTrinh = quyTrinhList.get(quyTrinhList.size() - 1);
        assertThat(testQuyTrinh.getQuyTrinhCode()).isEqualTo(UPDATED_QUY_TRINH_CODE);
        assertThat(testQuyTrinh.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingQuyTrinh() throws Exception {
        int databaseSizeBeforeUpdate = quyTrinhRepository.findAll().size();

        // Create the QuyTrinh

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuyTrinhMockMvc.perform(put("/api/quy-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quyTrinh)))
            .andExpect(status().isBadRequest());

        // Validate the QuyTrinh in the database
        List<QuyTrinh> quyTrinhList = quyTrinhRepository.findAll();
        assertThat(quyTrinhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQuyTrinh() throws Exception {
        // Initialize the database
        quyTrinhRepository.saveAndFlush(quyTrinh);

        int databaseSizeBeforeDelete = quyTrinhRepository.findAll().size();

        // Delete the quyTrinh
        restQuyTrinhMockMvc.perform(delete("/api/quy-trinhs/{id}", quyTrinh.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<QuyTrinh> quyTrinhList = quyTrinhRepository.findAll();
        assertThat(quyTrinhList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuyTrinh.class);
        QuyTrinh quyTrinh1 = new QuyTrinh();
        quyTrinh1.setId(1L);
        QuyTrinh quyTrinh2 = new QuyTrinh();
        quyTrinh2.setId(quyTrinh1.getId());
        assertThat(quyTrinh1).isEqualTo(quyTrinh2);
        quyTrinh2.setId(2L);
        assertThat(quyTrinh1).isNotEqualTo(quyTrinh2);
        quyTrinh1.setId(null);
        assertThat(quyTrinh1).isNotEqualTo(quyTrinh2);
    }
}
