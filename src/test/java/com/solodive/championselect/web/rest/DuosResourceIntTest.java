package com.solodive.championselect.web.rest;

import com.solodive.championselect.ChampionSelectApp;

import com.solodive.championselect.domain.Duos;
import com.solodive.championselect.repository.DuosRepository;
import com.solodive.championselect.service.DuosService;
import com.solodive.championselect.web.rest.errors.ExceptionTranslator;

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

import javax.persistence.EntityManager;
import java.util.List;


import static com.solodive.championselect.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DuosResource REST controller.
 *
 * @see DuosResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChampionSelectApp.class)
public class DuosResourceIntTest {

    private static final Long DEFAULT_TIMES_PLAYED = 1L;
    private static final Long UPDATED_TIMES_PLAYED = 2L;

    @Autowired
    private DuosRepository duosRepository;
    
    @Autowired
    private DuosService duosService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDuosMockMvc;

    private Duos duos;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DuosResource duosResource = new DuosResource(duosService);
        this.restDuosMockMvc = MockMvcBuilders.standaloneSetup(duosResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Duos createEntity(EntityManager em) {
        Duos duos = new Duos()
            .timesPlayed(DEFAULT_TIMES_PLAYED);
        return duos;
    }

    @Before
    public void initTest() {
        duos = createEntity(em);
    }

    @Test
    @Transactional
    public void createDuos() throws Exception {
        int databaseSizeBeforeCreate = duosRepository.findAll().size();

        // Create the Duos
        restDuosMockMvc.perform(post("/api/duos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duos)))
            .andExpect(status().isCreated());

        // Validate the Duos in the database
        List<Duos> duosList = duosRepository.findAll();
        assertThat(duosList).hasSize(databaseSizeBeforeCreate + 1);
        Duos testDuos = duosList.get(duosList.size() - 1);
        assertThat(testDuos.getTimesPlayed()).isEqualTo(DEFAULT_TIMES_PLAYED);
    }

    @Test
    @Transactional
    public void createDuosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = duosRepository.findAll().size();

        // Create the Duos with an existing ID
        duos.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDuosMockMvc.perform(post("/api/duos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duos)))
            .andExpect(status().isBadRequest());

        // Validate the Duos in the database
        List<Duos> duosList = duosRepository.findAll();
        assertThat(duosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDuos() throws Exception {
        // Initialize the database
        duosRepository.saveAndFlush(duos);

        // Get all the duosList
        restDuosMockMvc.perform(get("/api/duos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(duos.getId().intValue())))
            .andExpect(jsonPath("$.[*].timesPlayed").value(hasItem(DEFAULT_TIMES_PLAYED.intValue())));
    }
    
    @Test
    @Transactional
    public void getDuos() throws Exception {
        // Initialize the database
        duosRepository.saveAndFlush(duos);

        // Get the duos
        restDuosMockMvc.perform(get("/api/duos/{id}", duos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(duos.getId().intValue()))
            .andExpect(jsonPath("$.timesPlayed").value(DEFAULT_TIMES_PLAYED.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDuos() throws Exception {
        // Get the duos
        restDuosMockMvc.perform(get("/api/duos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDuos() throws Exception {
        // Initialize the database
        duosService.save(duos);

        int databaseSizeBeforeUpdate = duosRepository.findAll().size();

        // Update the duos
        Duos updatedDuos = duosRepository.findById(duos.getId()).get();
        // Disconnect from session so that the updates on updatedDuos are not directly saved in db
        em.detach(updatedDuos);
        updatedDuos
            .timesPlayed(UPDATED_TIMES_PLAYED);

        restDuosMockMvc.perform(put("/api/duos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDuos)))
            .andExpect(status().isOk());

        // Validate the Duos in the database
        List<Duos> duosList = duosRepository.findAll();
        assertThat(duosList).hasSize(databaseSizeBeforeUpdate);
        Duos testDuos = duosList.get(duosList.size() - 1);
        assertThat(testDuos.getTimesPlayed()).isEqualTo(UPDATED_TIMES_PLAYED);
    }

    @Test
    @Transactional
    public void updateNonExistingDuos() throws Exception {
        int databaseSizeBeforeUpdate = duosRepository.findAll().size();

        // Create the Duos

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDuosMockMvc.perform(put("/api/duos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duos)))
            .andExpect(status().isBadRequest());

        // Validate the Duos in the database
        List<Duos> duosList = duosRepository.findAll();
        assertThat(duosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDuos() throws Exception {
        // Initialize the database
        duosService.save(duos);

        int databaseSizeBeforeDelete = duosRepository.findAll().size();

        // Get the duos
        restDuosMockMvc.perform(delete("/api/duos/{id}", duos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Duos> duosList = duosRepository.findAll();
        assertThat(duosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Duos.class);
        Duos duos1 = new Duos();
        duos1.setId(1L);
        Duos duos2 = new Duos();
        duos2.setId(duos1.getId());
        assertThat(duos1).isEqualTo(duos2);
        duos2.setId(2L);
        assertThat(duos1).isNotEqualTo(duos2);
        duos1.setId(null);
        assertThat(duos1).isNotEqualTo(duos2);
    }
}
