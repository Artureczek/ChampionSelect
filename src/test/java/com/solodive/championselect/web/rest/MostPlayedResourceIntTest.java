package com.solodive.championselect.web.rest;

import com.solodive.championselect.ChampionSelectApp;

import com.solodive.championselect.domain.MostPlayed;
import com.solodive.championselect.repository.MostPlayedRepository;
import com.solodive.championselect.service.MostPlayedService;
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
 * Test class for the MostPlayedResource REST controller.
 *
 * @see MostPlayedResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChampionSelectApp.class)
public class MostPlayedResourceIntTest {

    private static final Long DEFAULT_TIMES_PLAYED = 1L;
    private static final Long UPDATED_TIMES_PLAYED = 2L;

    @Autowired
    private MostPlayedRepository mostPlayedRepository;
    
    @Autowired
    private MostPlayedService mostPlayedService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMostPlayedMockMvc;

    private MostPlayed mostPlayed;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MostPlayedResource mostPlayedResource = new MostPlayedResource(mostPlayedService);
        this.restMostPlayedMockMvc = MockMvcBuilders.standaloneSetup(mostPlayedResource)
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
    public static MostPlayed createEntity(EntityManager em) {
        MostPlayed mostPlayed = new MostPlayed()
            .timesPlayed(DEFAULT_TIMES_PLAYED);
        return mostPlayed;
    }

    @Before
    public void initTest() {
        mostPlayed = createEntity(em);
    }

    @Test
    @Transactional
    public void createMostPlayed() throws Exception {
        int databaseSizeBeforeCreate = mostPlayedRepository.findAll().size();

        // Create the MostPlayed
        restMostPlayedMockMvc.perform(post("/api/most-playeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mostPlayed)))
            .andExpect(status().isCreated());

        // Validate the MostPlayed in the database
        List<MostPlayed> mostPlayedList = mostPlayedRepository.findAll();
        assertThat(mostPlayedList).hasSize(databaseSizeBeforeCreate + 1);
        MostPlayed testMostPlayed = mostPlayedList.get(mostPlayedList.size() - 1);
        assertThat(testMostPlayed.getTimesPlayed()).isEqualTo(DEFAULT_TIMES_PLAYED);
    }

    @Test
    @Transactional
    public void createMostPlayedWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mostPlayedRepository.findAll().size();

        // Create the MostPlayed with an existing ID
        mostPlayed.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMostPlayedMockMvc.perform(post("/api/most-playeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mostPlayed)))
            .andExpect(status().isBadRequest());

        // Validate the MostPlayed in the database
        List<MostPlayed> mostPlayedList = mostPlayedRepository.findAll();
        assertThat(mostPlayedList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMostPlayeds() throws Exception {
        // Initialize the database
        mostPlayedRepository.saveAndFlush(mostPlayed);

        // Get all the mostPlayedList
        restMostPlayedMockMvc.perform(get("/api/most-playeds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mostPlayed.getId().intValue())))
            .andExpect(jsonPath("$.[*].timesPlayed").value(hasItem(DEFAULT_TIMES_PLAYED.intValue())));
    }
    
    @Test
    @Transactional
    public void getMostPlayed() throws Exception {
        // Initialize the database
        mostPlayedRepository.saveAndFlush(mostPlayed);

        // Get the mostPlayed
        restMostPlayedMockMvc.perform(get("/api/most-playeds/{id}", mostPlayed.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mostPlayed.getId().intValue()))
            .andExpect(jsonPath("$.timesPlayed").value(DEFAULT_TIMES_PLAYED.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMostPlayed() throws Exception {
        // Get the mostPlayed
        restMostPlayedMockMvc.perform(get("/api/most-playeds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMostPlayed() throws Exception {
        // Initialize the database
        mostPlayedService.save(mostPlayed);

        int databaseSizeBeforeUpdate = mostPlayedRepository.findAll().size();

        // Update the mostPlayed
        MostPlayed updatedMostPlayed = mostPlayedRepository.findById(mostPlayed.getId()).get();
        // Disconnect from session so that the updates on updatedMostPlayed are not directly saved in db
        em.detach(updatedMostPlayed);
        updatedMostPlayed
            .timesPlayed(UPDATED_TIMES_PLAYED);

        restMostPlayedMockMvc.perform(put("/api/most-playeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMostPlayed)))
            .andExpect(status().isOk());

        // Validate the MostPlayed in the database
        List<MostPlayed> mostPlayedList = mostPlayedRepository.findAll();
        assertThat(mostPlayedList).hasSize(databaseSizeBeforeUpdate);
        MostPlayed testMostPlayed = mostPlayedList.get(mostPlayedList.size() - 1);
        assertThat(testMostPlayed.getTimesPlayed()).isEqualTo(UPDATED_TIMES_PLAYED);
    }

    @Test
    @Transactional
    public void updateNonExistingMostPlayed() throws Exception {
        int databaseSizeBeforeUpdate = mostPlayedRepository.findAll().size();

        // Create the MostPlayed

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMostPlayedMockMvc.perform(put("/api/most-playeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mostPlayed)))
            .andExpect(status().isBadRequest());

        // Validate the MostPlayed in the database
        List<MostPlayed> mostPlayedList = mostPlayedRepository.findAll();
        assertThat(mostPlayedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMostPlayed() throws Exception {
        // Initialize the database
        mostPlayedService.save(mostPlayed);

        int databaseSizeBeforeDelete = mostPlayedRepository.findAll().size();

        // Get the mostPlayed
        restMostPlayedMockMvc.perform(delete("/api/most-playeds/{id}", mostPlayed.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MostPlayed> mostPlayedList = mostPlayedRepository.findAll();
        assertThat(mostPlayedList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MostPlayed.class);
        MostPlayed mostPlayed1 = new MostPlayed();
        mostPlayed1.setId(1L);
        MostPlayed mostPlayed2 = new MostPlayed();
        mostPlayed2.setId(mostPlayed1.getId());
        assertThat(mostPlayed1).isEqualTo(mostPlayed2);
        mostPlayed2.setId(2L);
        assertThat(mostPlayed1).isNotEqualTo(mostPlayed2);
        mostPlayed1.setId(null);
        assertThat(mostPlayed1).isNotEqualTo(mostPlayed2);
    }
}
