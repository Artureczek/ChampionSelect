package com.solodive.championselect.web.rest;

import com.solodive.championselect.ChampionSelectApp;

import com.solodive.championselect.domain.Champion;
import com.solodive.championselect.repository.ChampionRepository;
import com.solodive.championselect.service.ChampionService;
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
 * Test class for the ChampionResource REST controller.
 *
 * @see ChampionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChampionSelectApp.class)
public class ChampionResourceIntTest {

    private static final String DEFAULT_RIOT_ID = "AAAAAAAAAA";
    private static final String UPDATED_RIOT_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_RIOT_KEY = 1L;
    private static final Long UPDATED_RIOT_KEY = 2L;

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_BLURB = "AAAAAAAAAA";
    private static final String UPDATED_BLURB = "BBBBBBBBBB";

    @Autowired
    private ChampionRepository championRepository;
    
    @Autowired
    private ChampionService championService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restChampionMockMvc;

    private Champion champion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ChampionResource championResource = new ChampionResource(championService);
        this.restChampionMockMvc = MockMvcBuilders.standaloneSetup(championResource)
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
    public static Champion createEntity(EntityManager em) {
        Champion champion = new Champion()
            .riotId(DEFAULT_RIOT_ID)
            .riotKey(DEFAULT_RIOT_KEY)
            .version(DEFAULT_VERSION)
            .name(DEFAULT_NAME)
            .title(DEFAULT_TITLE)
            .blurb(DEFAULT_BLURB);
        return champion;
    }

    @Before
    public void initTest() {
        champion = createEntity(em);
    }

    @Test
    @Transactional
    public void createChampion() throws Exception {
        int databaseSizeBeforeCreate = championRepository.findAll().size();

        // Create the Champion
        restChampionMockMvc.perform(post("/api/champions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(champion)))
            .andExpect(status().isCreated());

        // Validate the Champion in the database
        List<Champion> championList = championRepository.findAll();
        assertThat(championList).hasSize(databaseSizeBeforeCreate + 1);
        Champion testChampion = championList.get(championList.size() - 1);
        assertThat(testChampion.getRiotId()).isEqualTo(DEFAULT_RIOT_ID);
        assertThat(testChampion.getRiotKey()).isEqualTo(DEFAULT_RIOT_KEY);
        assertThat(testChampion.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testChampion.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testChampion.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testChampion.getBlurb()).isEqualTo(DEFAULT_BLURB);
    }

    @Test
    @Transactional
    public void createChampionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = championRepository.findAll().size();

        // Create the Champion with an existing ID
        champion.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChampionMockMvc.perform(post("/api/champions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(champion)))
            .andExpect(status().isBadRequest());

        // Validate the Champion in the database
        List<Champion> championList = championRepository.findAll();
        assertThat(championList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllChampions() throws Exception {
        // Initialize the database
        championRepository.saveAndFlush(champion);

        // Get all the championList
        restChampionMockMvc.perform(get("/api/champions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(champion.getId().intValue())))
            .andExpect(jsonPath("$.[*].riotId").value(hasItem(DEFAULT_RIOT_ID.toString())))
            .andExpect(jsonPath("$.[*].riotKey").value(hasItem(DEFAULT_RIOT_KEY.intValue())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].blurb").value(hasItem(DEFAULT_BLURB.toString())));
    }
    
    @Test
    @Transactional
    public void getChampion() throws Exception {
        // Initialize the database
        championRepository.saveAndFlush(champion);

        // Get the champion
        restChampionMockMvc.perform(get("/api/champions/{id}", champion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(champion.getId().intValue()))
            .andExpect(jsonPath("$.riotId").value(DEFAULT_RIOT_ID.toString()))
            .andExpect(jsonPath("$.riotKey").value(DEFAULT_RIOT_KEY.intValue()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.blurb").value(DEFAULT_BLURB.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingChampion() throws Exception {
        // Get the champion
        restChampionMockMvc.perform(get("/api/champions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChampion() throws Exception {
        // Initialize the database
        championService.save(champion);

        int databaseSizeBeforeUpdate = championRepository.findAll().size();

        // Update the champion
        Champion updatedChampion = championRepository.findById(champion.getId()).get();
        // Disconnect from session so that the updates on updatedChampion are not directly saved in db
        em.detach(updatedChampion);
        updatedChampion
            .riotId(UPDATED_RIOT_ID)
            .riotKey(UPDATED_RIOT_KEY)
            .version(UPDATED_VERSION)
            .name(UPDATED_NAME)
            .title(UPDATED_TITLE)
            .blurb(UPDATED_BLURB);

        restChampionMockMvc.perform(put("/api/champions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedChampion)))
            .andExpect(status().isOk());

        // Validate the Champion in the database
        List<Champion> championList = championRepository.findAll();
        assertThat(championList).hasSize(databaseSizeBeforeUpdate);
        Champion testChampion = championList.get(championList.size() - 1);
        assertThat(testChampion.getRiotId()).isEqualTo(UPDATED_RIOT_ID);
        assertThat(testChampion.getRiotKey()).isEqualTo(UPDATED_RIOT_KEY);
        assertThat(testChampion.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testChampion.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testChampion.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testChampion.getBlurb()).isEqualTo(UPDATED_BLURB);
    }

    @Test
    @Transactional
    public void updateNonExistingChampion() throws Exception {
        int databaseSizeBeforeUpdate = championRepository.findAll().size();

        // Create the Champion

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChampionMockMvc.perform(put("/api/champions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(champion)))
            .andExpect(status().isBadRequest());

        // Validate the Champion in the database
        List<Champion> championList = championRepository.findAll();
        assertThat(championList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteChampion() throws Exception {
        // Initialize the database
        championService.save(champion);

        int databaseSizeBeforeDelete = championRepository.findAll().size();

        // Get the champion
        restChampionMockMvc.perform(delete("/api/champions/{id}", champion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Champion> championList = championRepository.findAll();
        assertThat(championList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Champion.class);
        Champion champion1 = new Champion();
        champion1.setId(1L);
        Champion champion2 = new Champion();
        champion2.setId(champion1.getId());
        assertThat(champion1).isEqualTo(champion2);
        champion2.setId(2L);
        assertThat(champion1).isNotEqualTo(champion2);
        champion1.setId(null);
        assertThat(champion1).isNotEqualTo(champion2);
    }
}
